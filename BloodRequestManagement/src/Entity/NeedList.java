/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author teren
 */
public class NeedList implements Comparable<NeedList>{

    private double itemPrice;
    private String item;
    private int itemQuantity;

    public NeedList(String item, int itemQuantity, double itemPrice) {
        this.itemPrice = itemPrice;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }


    public double getPrice() {
        return itemPrice;
    }

    public void setPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public void setQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    @Override
    public String toString() {
        return String.format("%-7s %-15s %-15s", itemPrice, item, itemQuantity);
    }

    @Override
    public int compareTo(NeedList o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

