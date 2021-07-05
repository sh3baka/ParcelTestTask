package com.parcel.task.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleTruck implements Truck {
    @JsonProperty("name")
    private String truckName;
    @JsonProperty("weight")
    private double truckWeight;
    private List<Parcel> load = new ArrayList<>();

    public SimpleTruck() {
    }

    public SimpleTruck(String truckName, double truckWeight) {
        this.truckName = truckName;
        this.truckWeight = truckWeight;
    }

    public String getTruckName() {
        return truckName;
    }

    public List<Parcel> getLoad() {
        return load;
    }

    public void addParcel(Parcel parcel) {
        if (null != parcel && null != parcel.getName() && parcel.getWeight() > 0 && !load.contains(parcel)) {
            load.add(parcel);
        }
    }

    public void removeParcelByName(String parcelName) {
        load.stream()
                .filter(parcel -> parcelName.equals(parcel.getName()))
                .collect(Collectors.toList())
                .forEach(parcel -> load.remove(parcel));
    }

    @Override
    public double getWeight() {
        return load.stream().mapToDouble(Parcel::getWeight).sum() + truckWeight;
    }

    @Override
    public int getParcelCount() {
        return load.size();
    }
}
