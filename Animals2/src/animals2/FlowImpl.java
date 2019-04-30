/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2;

import java.time.LocalDateTime;

/**
 *
 * @author agjackso
 */
public class FlowImpl implements Flow {
    private String name;
    private int dollars;  // per time period
    private int perNumWeeks;  // the time period, in weeks
    private double inflationRate;  // e.g., 0.03 for 3 percent
    private TimeSpan timeSpan;

    public FlowImpl(String name, int dollars, int perNumWeeks, double inflationRate, TimeSpan timeSpan) {
        this.name = name;
        this.dollars = dollars;
        this.perNumWeeks = perNumWeeks;
        this.inflationRate = inflationRate;
        this.timeSpan = timeSpan;
    }
    

    @Override
    public void set(int dollars, int perNumWeeks, LocalDateTime dateTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDollars(LocalDateTime dateTime) {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getPerNumWeeks() {
        return perNumWeeks;
    }

    public void setPerNumWeeks(int perNumWeeks) {
        this.perNumWeeks = perNumWeeks;
    }

    public double getInflationRate() {
        return inflationRate;
    }

    public void setInflationRate(double inflationRate) {
        this.inflationRate = inflationRate;
    }

    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(TimeSpan timeSpan) {
        this.timeSpan = timeSpan;
    }

    
}
