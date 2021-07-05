package com.parcel.task.controller;

import com.parcel.task.data.DataCenter;
import com.parcel.task.data.DataRepository;
import com.parcel.task.entity.Parcel;
import com.parcel.task.entity.SimpleTruck;
import com.parcel.task.entity.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TruckController {

    @Autowired
    DataRepository dataRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Truck>> getAllTrucksR() {
        return ResponseEntity.ok(DataCenter.getInstance().getAllTrucks());
    }

    @PostMapping("/add")
    public ResponseEntity<List<Truck>> addNewTruck(@RequestBody SimpleTruck simpleTruck) {
        dataRepository.addTruckToRepo(simpleTruck);
        return ResponseEntity.ok(DataCenter.getInstance().getAllTrucks());
    }

    @PostMapping("/add-parcel/{truckName}")
    public ResponseEntity<List<Truck>> addParcelToTruck(@RequestBody Parcel parcel, @PathVariable String truckName) {
        List<Truck> truckList = dataRepository.getTruckByName(truckName);
        truckList.forEach(truck -> truck.addParcel(parcel));
        return ResponseEntity.ok(DataCenter.getInstance().getAllTrucks());
    }

    @DeleteMapping("/remove-parcel")
    public ResponseEntity<List<Truck>> removeParcelFromTruck(@RequestParam("parcelName") String parcelName, @RequestParam("truckName") String truckName) {
        List<Truck> truckList = dataRepository.getTruckByName(truckName);
        truckList.forEach(truck -> truck.removeParcelByName(parcelName));
        return ResponseEntity.ok(DataCenter.getInstance().getAllTrucks());
    }
}
