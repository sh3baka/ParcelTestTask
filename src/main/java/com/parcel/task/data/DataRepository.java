package com.parcel.task.data;

import com.parcel.task.entity.Truck;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataRepository {
    public List<Truck> getTruckByName(String name) {
        return DataCenter.getInstance().getAllTrucks().stream()
                .filter(truck -> truck.getTruckName().equals(name))
                .collect(Collectors.toList());
    }

    public void addTruckToRepo(Truck truck) {
        DataCenter.getInstance().getAllTrucks().add(truck);
    }

}
