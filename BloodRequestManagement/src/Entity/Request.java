/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Lee Jin Shiun
 */
public class Request implements Comparable<Request>{
    private Donee doneeId;
    private String requestId;
    private String requestDate;
    private String bloodGroup;
    private int requestQty;
    private int priorityLvl;
    private String status;
    

    public Request() {
    }

    public Request(String requestId, Donee doneeId, String bloodGroup, 
         int requestQty, String requestDate, String status, int priorityLvl) {
        this.requestId = requestId;
        this.doneeId = doneeId;
        this.bloodGroup = bloodGroup;
        this.requestDate = requestDate;
        this.requestQty = requestQty;
        this.status = status;
        this.priorityLvl = priorityLvl;
    }
    
    public Donee getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(Donee doneeId) {
        this.doneeId = doneeId;
    }
    
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getRequestQty() {
        return requestQty;
    }

    public void setRequestQty(int requestQty) {
        this.requestQty = requestQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriorityLvl() {
        return priorityLvl;
    }

    public void setPriorityLvl(int priorityLvl) {
        this.priorityLvl = priorityLvl;
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
        return  "  " + requestId + "\t\t" + doneeId.toString2() + "\t\t" + bloodGroup +
                "\t\t " + requestDate + "\t   " + requestQty + "\t\t\t" + status + "\t\t    " + priorityLvl + "\n";
    }

    @Override
    public int compareTo(Request r) {
        if(this.getPriorityLvl() < r.getPriorityLvl()){
            return 1;//current need level greater than r
        }else if(this.getPriorityLvl() > r.getPriorityLvl()){
            return -1;//current need level less than r
        }else{
            return 0;
        }
    }
}


