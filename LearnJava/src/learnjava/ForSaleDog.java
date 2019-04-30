/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnjava;

/**
 *
 * @author agjackso
 */
public class ForSaleDog extends Dog {
    private int price;

    public ForSaleDog(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ForSaleDog{" + "price=" + price + '}';
    }
    
    
}
