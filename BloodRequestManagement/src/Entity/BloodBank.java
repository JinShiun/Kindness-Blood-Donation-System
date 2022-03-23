/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import ADT.ArrayStack;
import java.util.Objects;
import ADT.ArrayStackInterface;

/**
 *
 * @author Lee Jin Shiun
 * @author 
 */

public class BloodBank implements Comparable<BloodBank> {

    private String bloodGroup;
    private int bloodQty;
    public ArrayStackInterface<Request> requestStack;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public BloodBank() {
        requestStack = new ArrayStack<>();
    }

    public BloodBank(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public BloodBank(String bloodGroup, int bloodQty) {
        this.bloodGroup = bloodGroup;
        this.bloodQty = bloodQty;
        requestStack = new ArrayStack<>();
    }

    public BloodBank(String bloodGroup, int bloodQty, ArrayStackInterface<Request> requestStack) {
        this.bloodGroup = bloodGroup;
        this.bloodQty = bloodQty;
        this.requestStack = requestStack;
    }

    public ArrayStackInterface<Request> getRequestStack() {
        return requestStack;
    }

    public void setRequestStack(ArrayStackInterface<Request> requestStack) {
        this.requestStack = requestStack;
    }

    public BloodBank(int bloodQty) {
        this.bloodQty = bloodQty;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodType(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getBloodQty() {
        return bloodQty;
    }

    public void setBloodQty(int bloodQty) {
        this.bloodQty = bloodQty;
    }

    public void addRequest(Request r) {
        requestStack.push(r);
    }

    public void displayRequest() {

        if (!requestStack.isEmpty()) {
            int i = 1;
            int top = requestStack.getNumOfEntry();
            for (int j = top; j >= 0; j--) {
                System.out.println(" " + i + ". " + requestStack.peek(j));
                i++;
            }
        } else {
            System.out.println(ANSI_RED + "No record found" + ANSI_RESET);
        }
    }

    @Override
    public String toString() {
        return "\t" + bloodGroup
                + "\t\t" + bloodQty;
    }

    public String toString2() {
        return "Blood Type: " + ANSI_BLUE + bloodGroup + ANSI_RESET
                + "\tBlood Amount: " + ANSI_BLUE + bloodQty + ANSI_RESET
                + "\nRecent request:\n" + requestStack.toString2();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BloodBank other = (BloodBank) obj;
        if (!Objects.equals(this.bloodGroup, other.bloodGroup)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BloodBank o) {
        if (this.getBloodGroup().compareTo(o.getBloodGroup()) > 0) {
            return 1;//current need level greater than r
        } else if (this.getBloodGroup().compareTo(o.getBloodGroup()) > 0) {
            return -1;//current need level less than r
        } else {
            return 0;
        }
    }

}
