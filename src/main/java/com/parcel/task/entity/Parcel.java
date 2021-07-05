package com.parcel.task.entity;

import java.util.Objects;

public class Parcel {
    private String name;
    private double weight;

    public Parcel() {
    }

    public Parcel(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(parcel.weight, weight) == 0 && Objects.equals(name, parcel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
