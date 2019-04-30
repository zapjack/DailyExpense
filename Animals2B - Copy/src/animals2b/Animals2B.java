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
    
    public static final String TOTAL_LABEL = "TOTAL NET";
    
    XYChart.Series<String, Number> income = new XYChart.Series<>();
    XYChart.Series<String, Number> expenses = new XYChart.Series<>();
    XYChart.Series<String, Number> net = new XYChart.Series<>();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Change Color");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //  final String backgroundColorStyle = "-fx-background-color: green, white;";
              //  final String strokeStyle = "-fx-stroke: green;";
              //  income.getNode().setStyle(strokeStyle);
            }
        });
        
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        
        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel("time");
        
        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel("dollars");
        
        // StackedBarChart<String, Number> bc = new StackedBarChart<>(hAxis, vAxis);
        BarChart<String, Number> bc = new BarChart<>(hAxis, vAxis);
        
        income.setName("Income");
        for (int i = 0; i < months.length; i++)
            income.getData().add(Helper.dat(months[i], incomeValues[i]));
        
        income.getData().add(Helper.dat(TOTAL_LABEL, 0));
        
        expenses.setName("Expenses");
        for (int i = 0; i < months.length; i++)
            expenses.getData().add(Helper.dat(months[i], expenseValues[i]));
        
        expenses.getData().add(Helper.dat(TOTAL_LABEL, 0));

        net.setName("Net");
        int total = 0;
        for (int i = 0; i < months.length; i++) {
            int sum = incomeValues[i] + expenseValues[i];
            total += sum;
            net.getData().add(Helper.dat(months[i], sum));
        }
        
        net.getData().add(Helper.dat(TOTAL_LABEL, total));
        
        bc.getData().add(income);
        bc.getData().add(expenses);
        bc.getData().add(net);
        
//        root.getChildren().add(btn);
        root.getChildren().add(bc);
        
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

        /*
        expenses.getData().add(Helper.dat("January", 2003));
        expenses.getData().add(Helper.dat("February", 1100));
        expenses.getData().add(Helper.dat("March", 4506));  */
