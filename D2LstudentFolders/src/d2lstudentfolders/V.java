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
public class V {
    public static void it(boolean allIsOK) {
        if (!allIsOK)
            throw new VerifyException("");
    }
    
    public static void it(boolean allIsOK, String msg) {
        if (!allIsOK)
            throw new VerifyException(msg);
    }
}
