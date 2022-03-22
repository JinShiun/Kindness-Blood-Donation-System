/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Chai Jia Hao
 */
public class Request implements Comparable<Request>{
    private String requestId;
    private Donee doneeId;
    private String bloodType;
    private String requestDate;
    private int requestAmount;
    private String requestStatus;
    private int needLevel;

    public Request() {
    }

    public Request(String requestId, Donee donooId, String bloodType, 
            int requestAmount, String requestDate, String requestStatus, int needLevel) {
        this.requestId = requestId;
        this.doneeId = donooId;
        this.bloodType = bloodType;
        this.requestDate = requestDate;
        this.requestAmount = requestAmount;
        this.requestStatus = requestStatus;
        this.needLevel = needLevel;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Donee getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(Donee doneeId) {
        this.doneeId = doneeId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getNeedLevel() {
        return needLevel;
    }

    public void setNeedLevel(int needLevel) {
        this.needLevel = needLevel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Request other = (Request) obj;
        if (!Objects.equals(this.requestId, other.requestId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "  " + requestId + "\t\t" + doneeId.toString2() + "\t\t" + bloodType +
                "\t\t " + requestDate + "\t   " + requestAmount + 
                "\t\t\t" + requestStatus + "\t\t    " + needLevel + "\n";
    }

    @Override
    public int compareTo(Request r) {
        if(this.getNeedLevel() < r.getNeedLevel()){
            return 1;//current need level greater than r
        }else if(this.getNeedLevel() > r.getNeedLevel()){
            return -1;//current need level less than r
        }else{
            return 0;
        }
    }
}


