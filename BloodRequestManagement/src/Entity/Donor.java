/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class Donor {
    private String donorId;
    private String donorName;
    private String donorState;
    private String donorPhoneNum;
    private String donorMail;

    public Donor() {
    }

    public Donor(String donorId) {
        this.donorId = donorId;
    }

    public Donor(String donorId, String donorName, String donorState, String donorPhoneNum, String donorMail) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorState = donorState;
        this.donorPhoneNum = donorPhoneNum;
        this.donorMail = donorMail;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorState() {
        return donorState;
    }

    public String getDonorPhoneNum() {
        return donorPhoneNum;
    }

    public String getDonorMail() {
        return donorMail;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setDonorstate(String donorState) {
        this.donorState = donorState;
    }

    public void setDonorPhoneNum(String donorPhoneNum) {
        this.donorPhoneNum = donorPhoneNum;
    }

    public void setDonorMail(String donorMail) {
        this.donorMail = donorMail;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-25s %-25s %-20s %-30s", donorId, donorName, donorState, donorPhoneNum, donorMail);
    }
    
    public String toString2(){
        return donorId;
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
        final Donor other = (Donor) obj;
        if (!Objects.equals(this.donorId, other.donorId)) {
            return false;
        }
        if (!Objects.equals(this.donorName, other.donorName)) {
            return false;
        }
        if (!Objects.equals(this.donorState, other.donorState)) {
            return false;
        }
        if (!Objects.equals(this.donorPhoneNum, other.donorPhoneNum)) {
            return false;
        }
        if (!Objects.equals(this.donorMail, other.donorMail)) {
            return false;
        }
        return true;
    }
    
}
