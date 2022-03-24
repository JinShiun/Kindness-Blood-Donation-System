/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import ADT.List;
import ADT.ListInterface;

/**
 *
 * @author teren
 */
public class Campaign implements Comparable<Campaign>{
    private String campaignID;
    private String campaignName;
    private String campaignDate;
    ListInterface<NeedList> needlist = new List<NeedList>();

    public Campaign(String campaignID, String campaignName, String campaignDate) {
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.campaignDate = campaignDate;
    }

    public void setNeedlist(ListInterface<NeedList> needlist) {
        this.needlist = needlist;
    }
    public ListInterface<NeedList> getNeedlist() {
        return needlist;
    }

    public String getId() {
        return campaignID;
    }

    public void setId(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getName() {
        return campaignName;
    }

    public void setName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getDate() {
        return campaignDate;
    }

    public void setDate(String date) {
        this.campaignDate = campaignDate;
    }
    
    public int compareTo(Campaign o) {
        return ((int)this.campaignID.compareTo(o.campaignID));
    }
    
    @Override
    public String toString() {
        return String.format("%-7s %-15s %-15s \n---------"
                + "\n%-15s\n--------- \n%7s %8s %15s\n--------------------------------------- \n%-15s"
                + "\n=======================================" , campaignID, campaignName, campaignDate, "NeedList", "Price","Item", "Quantity", needlist.toString()) ;
    }
}
