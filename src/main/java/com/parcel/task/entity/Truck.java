package com.parcel.task.entity;

public interface Truck {
    String getTruckName();

    double getWeight();

    int getParcelCount();

    void addParcel(Parcel parcel);

    void removeParcelByName(String parcelName);
}
