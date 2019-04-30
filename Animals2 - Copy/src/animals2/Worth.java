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
public interface Worth {
    public void setDollars(int dollars, LocalDateTime dateTime);
    public int getDollars(LocalDateTime dateTime);
}
