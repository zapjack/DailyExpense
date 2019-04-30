package base7;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FXMLDocumentController implements Initializable {
//    ArrayList<MyDigit> content = new ArrayList<MyDigit>();
    
    @FXML
    private TextField inText;

    @FXML
    private Button translate;

    @FXML
    private Button reset;

    @FXML
    private GridPane outText;
    
    @FXML
    void onReset(ActionEvent event) {
        inText.clear();
//        outText.getChildren().removeAll(content);
    }

    @FXML
    void onTranslate(ActionEvent event) {
//        content.clear();
        
        String s = inText.getText().toString();
        if (s.length() > 60) {
            s = s.substring(0, 60);
            inText.setText("TRIMMED: " + s);
        }
        
        int x = 0, y = 0;
        int i = 0;
        
        for (; i < s.length(); i++) {
            System.out.println(x + ", " + y);
            MyDigit digit = new MyDigit(s.substring(i, i+1));
//            content.add(digit);
            outText.add(digit, x, y);
            x = (i + 1) % 10;
            y = (i + 1) / 10;
        }
        
        while (i < 60) {
                        System.out.println(x + ", " + y);
            outText.add(new MyDigit(null), x, y);
            x = (i + 1) % 10;
            y = (i + 1) / 10;
            i++;
        }       
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
