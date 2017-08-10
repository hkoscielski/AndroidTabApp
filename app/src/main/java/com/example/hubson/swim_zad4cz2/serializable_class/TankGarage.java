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


public class TankGarage implements Serializable {
    private Context ctx;
    private ArrayList<Tank> tanks;
    private static final String RECORDS_FILE = "TankRecordsFile.ser";
    private static final long serialVersionUID = 4L;


    public TankGarage(Context ctx) {
        super();
        this.ctx = ctx;
        tanks = new ArrayList<>();
        loadGarage(this.ctx);
    }

    public void addTank(Tank tank)
    {
        tanks.add(tank);
        saveGarage(ctx);
    }

    public void removeTank(Tank tank)
    {
        tanks.remove(tank);
        saveGarage(ctx);
    }

    public void removeAllTank() {
        tanks.removeAll(tanks);
        saveGarage(ctx);
    }

    private void saveGarage(Context ctx) {
        try {
            FileOutputStream fileOut = ctx.openFileOutput(RECORDS_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(tanks);

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

            ArrayList<Tank> tmp = (ArrayList<Tank>) in.readObject();
            tanks.clear();
            tanks.addAll(tmp);

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

    public Tank getTank(int position) {
        return tanks.get(position);
    }

    public int size() {
        return tanks.size();
    }

    public void sortByBrand() {
        Collections.sort(tanks, new Comparator<Tank>() {
            public int compare(Tank o1, Tank o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
    }

    public void sortByYear() {
        Collections.sort(tanks, new Comparator<Tank>() {
            public int compare(Tank o1, Tank o2) {
                return o2.getYear().compareTo(o1.getYear());
            }
        });
    }

    public void sortByRating() {
        Collections.sort(tanks, new Comparator<Tank>() {
            public int compare(Tank o1, Tank o2) {
                return o1.getRate() < o2.getRate() ? 1 : -1;
            }
        });
    }
}



