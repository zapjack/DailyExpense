/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d2lstudentfolders;

/**
 *
 * @author agjackso
 */
public class VerifyException extends RuntimeException {
    private String msg;
    
    public VerifyException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "VerifyException: " + msg;
    }  
}
