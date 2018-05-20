package com.example.masteradar.diabetesdataholder;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Master Adar on 13-Sep-17.
 */

public class UserDetails implements Serializable {
    String time;
    boolean ate;
    float BPHigh;
    float BPLow;
    float diabetesLevel;

    public boolean isAte() {
        return ate;
    }

    public void setAte(boolean ate) {
        this.ate = ate;
    }

    public float getBPHigh() {
        return BPHigh;
    }

    public void setBPHigh(float BPHigh) {
        this.BPHigh = BPHigh;
    }

    public float getBPLow() {
        return BPLow;
    }

    public void setBPLow(float BPLow) {
        this.BPLow = BPLow;
    }

    public float getDiabetesLevel() {
        return diabetesLevel;
    }

    public void setDiabetesLevel(float diabetesLevel) {
        this.diabetesLevel = diabetesLevel;
    }

    public String getTime() {
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, h:mm a");
        time = df.format(Calendar.getInstance().getTime());
        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }


}
