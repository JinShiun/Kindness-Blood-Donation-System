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
 * @author Chai Jia Hao
 * @author Gan Wei Zhe
 */
public class BloodBank implements Comparable<BloodBank> {

    private String bloodType;
    private int bloodAmount;
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

    public BloodBank(String bloodType) {
        this.bloodType = bloodType;
    }

    public BloodBank(String bloodType, int bloodAmount) {
        this.bloodType = bloodType;
        this.bloodAmount = bloodAmount;
        requestStack = new ArrayStack<>();
    }

    public BloodBank(String bloodType, int bloodAmount, ArrayStackInterface<Request> requestStack) {
        this.bloodType = bloodType;
        this.bloodAmount = bloodAmount;
        this.requestStack = requestStack;
    }

    public ArrayStackInterface<Request> getRequestStack() {
        return requestStack;
    }

    public void setRequestStack(ArrayStackInterface<Request> requestStack) {
        this.requestStack = requestStack;
    }

    public BloodBank(int bloodAmount) {
        this.bloodAmount = bloodAmount;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getBloodAmount() {
        return bloodAmount;
    }

    public void setBloodAmount(int bloodAmount) {
        this.bloodAmount = bloodAmount;
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
        return "\t" + bloodType
                + "\t\t" + bloodAmount;
    }

    public String toString2() {
        return "Blood Type: " + ANSI_BLUE + bloodType + ANSI_RESET
                + "\tBlood Amount: " + ANSI_BLUE + bloodAmount + ANSI_RESET
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
        if (!Objects.equals(this.bloodType, other.bloodType)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BloodBank o) {
        if (this.getBloodType().compareTo(o.getBloodType()) > 0) {
            return 1;//current need level greater than r
        } else if (this.getBloodType().compareTo(o.getBloodType()) > 0) {
            return -1;//current need level less than r
        } else {
            return 0;
        }
    }

}
