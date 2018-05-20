package com.example.masteradar.diabetesdataholder;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Master Adar on 13-Sep-17.
 */

public final class UserProfile implements Serializable {
    String username;
    ArrayList<UserDetails> details = new ArrayList<UserDetails>();
    int currentDetail=0;//

    public int getCurrentDetail() {
        return currentDetail;
    }

    public void setCurrentDetail(int currentDetail) {
        this.currentDetail = currentDetail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<UserDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<UserDetails> details) {
        this.details = details;
    }
}
