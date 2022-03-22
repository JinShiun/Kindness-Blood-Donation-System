/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Lim Jun Hua
 */
public class Donee {

    private String doneeId;
    private String doneeName;
    private String doneeState;
    private String doneePhoneNum;
    private String doneeMail;

    public Donee() {
    }

    public Donee(String doneeId) {
        this.doneeId = doneeId;
    }

    public Donee(String doneeId, String doneeName, String doneeState, String doneePhoneNum, String doneeMail) {
        this.doneeId = doneeId;
        this.doneeName = doneeName;
        this.doneeState = doneeState;
        this.doneePhoneNum = doneePhoneNum;
        this.doneeMail = doneeMail;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public String getDoneeState() {
        return doneeState;
    }

    public String getDoneePhoneNum() {
        return doneePhoneNum;
    }

    public String getDoneeMail() {
        return doneeMail;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public void setDoneestate(String doneeState) {
        this.doneeState = doneeState;
    }

    public void setDoneePhoneNum(String doneePhoneNum) {
        this.doneePhoneNum = doneePhoneNum;
    }

    public void setDoneeMail(String doneeMail) {
        this.doneeMail = doneeMail;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-25s %-25s %-20s %-30s", doneeId, doneeName, doneeState, doneePhoneNum, doneeMail);
    }
    
    public String toString2(){
        return doneeId;
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
        final Donee other = (Donee) obj;
        if (!Objects.equals(this.doneeId, other.doneeId)) {
            return false;
        }
        if (!Objects.equals(this.doneeName, other.doneeName)) {
            return false;
        }
        if (!Objects.equals(this.doneeState, other.doneeState)) {
            return false;
        }
        if (!Objects.equals(this.doneePhoneNum, other.doneePhoneNum)) {
            return false;
        }
        if (!Objects.equals(this.doneeMail, other.doneeMail)) {
            return false;
        }
        return true;
    }

}
