package com.example.rajatjain.slicepay;


import android.os.Parcel;
import android.os.Parcelable;

class Items implements Parcelable {
    public String name;
    private String phone;

    Items(String name, String phone){
        super();
        this.name=name;
        this.phone=phone;
    }

    protected Items(Parcel in) {
        name = in.readString();
        phone = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getname(){
        return name;
    }

    public String getphone(){
        return phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(phone);
    }
}
