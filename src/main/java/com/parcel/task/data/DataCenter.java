package com.parcel.task.data;

import com.parcel.task.entity.Truck;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {
    private static DataCenter instance;
    private List<Truck> truckList = new ArrayList<>();

    private DataCenter() {
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }

    public List<Truck> getAllTrucks() {
        return truckList;
    }
}
