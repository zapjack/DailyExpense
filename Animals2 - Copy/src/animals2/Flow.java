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
public interface Flow {
    public void set(int dollars, int perNumWeeks, LocalDateTime dateTime);
    public int getDollars(LocalDateTime dateTime);
    public int getPerNumWeeks(LocalDateTime dateTime);
}

/*
http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
LocalDateTime timePoint = LocalDateTime.now(
    );     // The current date and time
LocalDate.of(2012, Month.DECEMBER, 12); // from values
LocalDate.ofEpochDay(150);  // middle of 1970
LocalTime.of(17, 18); // the train I took home today
LocalTime.parse("10:15:30"); // From a String
*/
