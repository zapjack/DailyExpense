/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d2lstudentfolders;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author agjackso
 */
public class D2LstudentFolders extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        final FileChooser fileChooser = new FileChooser();
 
//        final Button openButton = new Button("Convert a Folder (pick any file in folder)");
        final Button openMultipleButton = new Button("Select Files to Change Names");
/* 
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        String sPath = file.getAbsolutePath();
                        File fPath = new File(sPath);
                        
                        if (fPath.isDirectory()) {
                            
                        }
                        //openFile(file);
                    }
                }
            });
*/ 
        openMultipleButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    try {
                        List<File> list =
                            fileChooser.showOpenMultipleDialog(primaryStage);
                        if (list != null) {
                            for (File file : list) {
                                String sPath = file.getAbsolutePath();
                                sPath = (new File(sPath)).getParent();
                                String sName = file.getName();
                                
                                String sOne = sName.substring(sName.indexOf('-') + 1, sName.length());
                                String sStudent = sOne.substring(sOne.indexOf('-') + 1, sOne.length()).trim();
                               
/*                                String [] parsed = sName.split("-");
                                
                                if (parsed.length != 5) {
                                    System.err.println("Error - incorrect line format");
                                    System.exit(1);
                                }

                                String sStudent = parsed[2];
                                String sDate = parsed[3];
                                String sAssign = parsed[4];
*/
                                file.renameTo(new File(sPath + "/" + sStudent));

                                System.out.println("Converting: " + file.toString());
                                System.out.println(sPath);
                                System.out.println(sStudent);
                            }
                        }

                    }
                    catch(Exception exc) {
                            System.err.println("Error!");
                    }
                }
            });
        
        StackPane root = new StackPane();
        root.getChildren().add(openMultipleButton);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
