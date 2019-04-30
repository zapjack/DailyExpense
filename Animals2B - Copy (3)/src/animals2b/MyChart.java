/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2b;

import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author Alan G. Jackson
 */

public class MyChart {
    public static final String [] months = {"January", "February", "March", "April", "May"};
    public static final int [] expenseValues = { -1000, -3500, -2500, -555, -3214};
    public static final int [] incomeValues = { 3200, 2100, 3000, 5000, 3100};
    public static final String TOTAL_LABEL = "TOTAL NET";
    
    public static FlowPane makeChart() {
        XYChart.Series<String, Number> income = new XYChart.Series<>();
        XYChart.Series<String, Number> expenses = new XYChart.Series<>();
        XYChart.Series<String, Number> net = new XYChart.Series<>();
        
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
        
        root.getChildren().add(bc);
        return root;
    }
}

/*
expenses.getData().add(Helper.dat("January", 2003));
expenses.getData().add(Helper.dat("February", 1100));
expenses.getData().add(Helper.dat("March", 4506));  
*/
