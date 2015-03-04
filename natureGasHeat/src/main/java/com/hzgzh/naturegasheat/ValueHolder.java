package com.hzgzh.naturegasheat;

import android.os.Parcel;
import android.os.Parcelable;

public class ValueHolder implements Parcelable {
    String name = "";
    String value = "";
    String unit = "";
    public static final Parcelable.Creator<ValueHolder> CREATOR = new Parcelable.Creator<ValueHolder>() {
        public ValueHolder createFromParcel(Parcel in) {
            ValueHolder vh = new ValueHolder();
            vh.name = in.readString();
            vh.value = in.readString();
            vh.unit = in.readString();
            return vh;
        }

        public ValueHolder[] newArray(int size) {
            return new ValueHolder[size];
        }

    };

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub
        arg0.writeString(name);
        arg0.writeString(value);
        arg0.writeString(unit);

    }
}

