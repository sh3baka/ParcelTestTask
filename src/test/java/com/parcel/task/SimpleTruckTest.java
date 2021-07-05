package com.parcel.task;

import com.parcel.task.entity.Parcel;
import com.parcel.task.entity.SimpleTruck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTruckTest {

    private SimpleTruck testTruck;
    private Parcel testParcel;

    @Before
    public void setUp() throws Exception {
        testTruck = new SimpleTruck("Name", 111);
        testParcel = new Parcel("ParcelName", 222);
    }

    @Test
    public void testTruckIsEmpty() {
        assertEquals(0, testTruck.getParcelCount());
    }

    @Test
    public void testAddParcel() {
        testTruck.addParcel(testParcel);

        assertEquals(1, testTruck.getParcelCount());
        assertEquals("ParcelName", testTruck.getLoad().get(0).getName());
        assertEquals(222, testTruck.getLoad().get(0).getWeight());
    }

    @Test
    public void testAddParcelNull() {
        testTruck.addParcel(null);

        assertEquals(0, testTruck.getParcelCount());
    }

    @Test
    public void testAddParcelWithEmptyName() {
        Parcel mutatedParcel = testParcel;
        mutatedParcel.setName("");

        testTruck.addParcel(mutatedParcel);

        assertEquals(1, testTruck.getParcelCount());
        assertEquals("", testTruck.getLoad().get(0).getName());
        assertEquals(222, testTruck.getLoad().get(0).getWeight());
    }

    @Test
    public void testAddParcelWithZeroWeight() {
        Parcel mutatedParcel = testParcel;
        mutatedParcel.setWeight(0);

        testTruck.addParcel(mutatedParcel);

        assertEquals(0, testTruck.getParcelCount());
    }

    @Test
    public void testAddParcelSameParcel() {
        testTruck.addParcel(testParcel);
        testTruck.addParcel(testParcel);

        assertEquals(1, testTruck.getParcelCount());
    }

    @Test
    public void testGetWeight() {
        testTruck.addParcel(testParcel);

        assertEquals(1, testTruck.getParcelCount());
        assertEquals(333, testTruck.getWeight());
    }

    @Test
    public void testGetWeightWithZeroParcels() {
        assertEquals(0, testTruck.getParcelCount());
        assertEquals(111, testTruck.getWeight());
    }

    @Test
    public void testGetWeightWithMultipleParcels() {
        testTruck.addParcel(testParcel);
        testTruck.addParcel(new Parcel("SecondParcel", 333));

        assertEquals(2, testTruck.getParcelCount());
        assertEquals(666, testTruck.getWeight());
    }

    @Test
    public void removeParcel() {
        testTruck.addParcel(testParcel);

        assertEquals(1, testTruck.getParcelCount());

        testTruck.removeParcelByName("ParcelName");

        assertEquals(0, testTruck.getParcelCount());
    }

    @Test
    public void removeParcelWithMultipleParcels() {
        testTruck.addParcel(testParcel);
        testTruck.addParcel(new Parcel("SecondParcel", 333));

        assertEquals(2, testTruck.getParcelCount());

        testTruck.removeParcelByName("ParcelName");

        assertEquals(1, testTruck.getParcelCount());
    }

    @Test
    public void removeParcelWithMultipleParcelsWithSameName() {
        testTruck.addParcel(testParcel);
        testTruck.addParcel(new Parcel("ParcelName", 333));

        assertEquals(2, testTruck.getParcelCount());

        testTruck.removeParcelByName("ParcelName");

        assertEquals(0, testTruck.getParcelCount());
    }

    @Test
    public void removeParcelNotExist() {
        testTruck.addParcel(testParcel);

        assertEquals(1, testTruck.getParcelCount());

        testTruck.removeParcelByName("RandomName");

        assertEquals(1, testTruck.getParcelCount());
    }
}