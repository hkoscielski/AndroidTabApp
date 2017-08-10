package com.example.hubson.swim_zad4cz2.serializable_class;

import java.io.Serializable;

public class Car implements Serializable {
    private String brand;
    private String model;
    private String year;
    private String power;
    private String type;
    private String numOfSeats;
    private String fuel;
    private String extras;
    private float rate;
    private int id;
    private static final long serialVersionUID = 1L;



    public Car(String brand, String model, String year, String power, String type, String numOfSeats, String fuel, String extras, float rate, int id) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.power = power;
        this.type = type;
        this.numOfSeats = numOfSeats;
        this.fuel = fuel;
        this.extras = extras;
        this.rate = rate;
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getNumOfSeats() {
        return numOfSeats;
    }

    public String getFuel() {
        return fuel;
    }

    public String getExtras() {
        return extras;
    }

    public float getRate() {
        return rate;
    }

    public int getId() {
        return id;
    }
}
