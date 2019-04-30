/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jusummary;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author agjackso
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea txtIn;

    @FXML
    private Button btnAdd;

    @FXML
    private TextArea txtContent;


    @FXML
    private Button btnSummarize;

    @FXML
    private TextArea txtSummarize;
    
    @FXML
    private Button btnReset;
  

    @FXML
    private TextField txtPoints;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    

    @FXML
    void onBtnAdd(ActionEvent event) {
        String s = txtIn.getText();
        txtContent.appendText("\n");
        txtContent.appendText(s);
        txtIn.clear();
    }

    @FXML
    void onBtnSummarize(ActionEvent event) {
        String s = txtContent.getText();
        Scanner scan = new Scanner(s);
        
        int totalEarned = 0;
        int totalPossible = 0;
        int processed = 0;
        String resultLines = "";
        
        int points = Integer.parseInt(txtPoints.getText().trim());
        
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.startsWith("RESULT") || line.startsWith("TESTING")) {
                if (line.startsWith("TESTING")) {
                    resultLines += (line.split(" "))[1] + " ";
                    continue;
                }
                processed++;
                resultLines += line + "\n";
                String [] tokens = line.split(" ");
                assert tokens.length == 6;
                totalEarned += Integer.parseInt(tokens[1].trim());
                totalPossible += Integer.parseInt(tokens[3].trim());
            }
        }
        
        double percent = ((double) totalEarned) / totalPossible;
     
        txtSummarize.setText(String.format("%s%d entries; %d of %d = %.2f%%\nPoints: %.1f of %d",
                resultLines, processed, totalEarned, totalPossible, percent * 100.0,
                percent * points, points));
    }
    
    @FXML
    void onBtnReset(ActionEvent event) {
        txtIn.clear();
        txtContent.clear();
        txtSummarize.clear();
    }
}

/*

TESTING: Account
toString
--- passed: 5
overdrawn
--- passed: 5
toString
--- passed: 5
deposit - negative
--- passed: 5
withdraw
--- passed: 5
withdraw - negative
--- passed: 5
setNumber
--- passed: 5
getBalance
--- passed: 5
deposit
--- passed: 5
getNumber
--- passed: 5
RESULT: 50 of 50 pts, 100.00

TESTING: Customer
toString
--- passed: 5
hasAnAccount
--- passed: 5
toString
--- passed: 5
getAccount
--- passed: 5
setAccount
--- passed: 5
deleteAccount - Reference Deleted
--- passed: 5
deleteAccount - Reference Deleted
--- passed: 5
getAccount null
--- passed: 5
setAccount - no overwrite
--- passed: 5
hasAnAccount
--- passed: 5
RESULT: 50 of 50 pts, 100.00

TESTING: Manager
constructor
--- passed: 5
constructor - bad value
--- passed: 5
constructor - id only
--- passed: 5
toString
--- passed: 5
setSalary
--- passed: 5
getSalary
--- passed: 5
RESULT: 30 of 30 pts, 100.00

TESTING: Person
toString
--- passed: 5
getName
--- passed: 5
getAddress
--- passed: 5
setName
--- passed: 5
Constructor - default
--- passed: 5
setAddress
--- passed: 5
--- passed: 5
toString - default values
--- passed: 5
Constructor - Name Only
--- passed: 5
RESULT: 45 of 45 pts, 100.00
*/
