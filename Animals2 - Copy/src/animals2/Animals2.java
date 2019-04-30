/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2;

/**
 *
 * @author agjackso
 */
public class Animals2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Entity food = new Entity("food");
        Entity shelter = new Entity("shelter");
        
        Entity person = new Entity("bob");
        person.addAssociation("mandatory", food, shelter);
        System.out.println(person);
    }
}
