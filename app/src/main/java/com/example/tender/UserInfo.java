package com.example.tender;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
    public String email, password;
    public String v, vg, df, gf;
    public String hookUp, longTerm;

    /**
     * Creating constructors to save account information and user information
     * that goes to realtime database in Firebase
     */
    public UserInfo(){

    }

    public UserInfo(String email, String password){
        this.email = email;
        this.password = password;
    }

    public UserInfo(String email, String password, String v, String vg, String df, String gf,
                    String hookUp, String longTerm){
        this.email = email;
        this.password = password;
        this.v = v;
        this.vg = vg;
        this.df = df;
        this.gf = gf;
        this.hookUp = hookUp;
        this.longTerm = longTerm;
    }

    protected UserInfo(Parcel in) {
        email = in.readString();
        password = in.readString();
        v = in.readString();
        vg = in.readString();
        df = in.readString();
        gf = in.readString();
        hookUp = in.readString();
        longTerm = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(v);
        dest.writeString(vg);
        dest.writeString(df);
        dest.writeString(gf);
        dest.writeString(hookUp);
        dest.writeString(longTerm);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getVg() {
        return vg;
    }

    public void setVg(String vg) {
        this.vg = vg;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getHookUp() {
        return hookUp;
    }

    public void setHookUp(String hookUp) {
        this.hookUp = hookUp;
    }

    public String getLongTerm() {
        return longTerm;
    }

    public void setLongTerm(String longTerm) {
        this.longTerm = longTerm;
    }
}
