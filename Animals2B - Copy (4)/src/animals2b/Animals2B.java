/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2b;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author agjackso
 */
public class Animals2B extends Application { 
    public static final String [] months = {"January", "February", "March", "April", "May"};
    public static final int [] expenseValues = { -1000, -3500, -2500, -555, -3214};
    public static final int [] incomeValues = { 3200, 2100, 3000, 5000, 3100};
    
    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        root.getChildren().addAll(MyChart.makeChart(months, expenseValues, incomeValues), MyChart.makeChart(months, expenseValues, incomeValues));
        Scene scene = new Scene(root);
        
        File f = new File("styles.css");
        scene.getStylesheets().clear();
        String sFileName = "file:///" + f.getAbsolutePath().replace("\\", "/");
        scene.getStylesheets().add(sFileName);
        System.out.println(sFileName);
        
        primaryStage.setTitle("Animals");
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
