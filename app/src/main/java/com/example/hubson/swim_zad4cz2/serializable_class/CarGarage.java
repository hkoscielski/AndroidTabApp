package com.example.hubson.swim_zad4cz2.serializable_class;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CarGarage implements Serializable {
    private Context ctx;
    private ArrayList<Car> cars;
    private static final String RECORDS_FILE = "CarRecordsFile.ser";
    private static final long serialVersionUID = 2L;


    public CarGarage(Context ctx) {
        super();
        this.ctx = ctx;
        cars = new ArrayList<>();
        loadGarage(this.ctx);
    }

    public void addCar(Car car)
    {
        cars.add(car);
        saveGarage(ctx);
    }

    public void removeCar(Car car)
    {
        cars.remove(car);
        saveGarage(ctx);
    }

    public void removeAllCar() {
        cars.removeAll(cars);
        saveGarage(ctx);
    }

    private void saveGarage(Context ctx) {
        try {
            FileOutputStream fileOut = ctx.openFileOutput(RECORDS_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(cars);

            out.close();
            fileOut.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGarage(Context ctx) {
        try {
            FileInputStream fileIn = ctx.openFileInput(RECORDS_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ArrayList<Car> tmp = (ArrayList<Car>) in.readObject();
            cars.clear();
            cars.addAll(tmp);

            in.close();
            fileIn.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
        }
    }

    public Car getCar(int position) {
        return cars.get(position);
    }

    public int size() {
        return cars.size();
    }

    public void sortByBrand() {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car o1, Car o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
    }

    public void sortByYear() {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car o1, Car o2) {
                return o2.getYear().compareTo(o1.getYear());
            }
        });
    }

    public void sortByRating() {
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car o1, Car o2) {
                return o1.getRate() < o2.getRate() ? 1 : -1;
            }
        });
    }
}


