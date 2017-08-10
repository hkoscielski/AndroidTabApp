package com.example.hubson.swim_zad4cz2.serializable_class;

import java.io.Serializable;

public class Tank implements Serializable {
    private String brand;
    private String model;
    private String power;
    private String year;
    private String fuel;
    private String type;
    private String crew;
    private String info;
    private float rate;
    private int id;
    private static final long serialVersionUID = 3L;


    public Tank(String brand, String model, String year, String power, String fuel, String type, String crew, String info, float rate, int id) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.year = year;
        this.fuel = fuel;
        this.type = type;
        this.crew = crew;
        this.info = info;
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

    public String getCrew() {
        return crew;
    }

    public String getFuel() {
        return fuel;
    }

    public String getInfo() {
        return info;
    }

    public float getRate() {
        return rate;
    }

    public int getId() {
        return id;
    }
}

