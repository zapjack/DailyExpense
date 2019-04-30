/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2b;

import javafx.scene.chart.XYChart;

/**
 *
 * @author agjackso
 */
public class Helper {
    public static XYChart.Data dat(String name, Number num) {
        return new XYChart.Data<String, Number>(name, num);
    }
}
