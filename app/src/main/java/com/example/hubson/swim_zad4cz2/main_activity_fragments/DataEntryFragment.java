package com.example.hubson.swim_zad4cz2.main_activity_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hubson.swim_zad4cz2.R;
import com.example.hubson.swim_zad4cz2.data_entry_fragments.CarEntryFragment;
import com.example.hubson.swim_zad4cz2.data_entry_fragments.TankEntryFragment;
import com.example.hubson.swim_zad4cz2.serializable_class.Car;
import com.example.hubson.swim_zad4cz2.serializable_class.CarGarage;
import com.example.hubson.swim_zad4cz2.serializable_class.Tank;
import com.example.hubson.swim_zad4cz2.serializable_class.TankGarage;

public class DataEntryFragment extends Fragment {
    private FragmentTransaction fragmentTransaction;
    private AppCompatActivity A1;
    private CarEntryFragment carEntryFragment;
    private TankEntryFragment tankEntryFragment;
    private Toast toast;

    private EditText brandEv;
    private EditText modelEv;
    private EditText powerEv;
    private ImageView imgView;
    private RadioGroup fuelRg;
    private RatingBar ratingBar;
    private Spinner typeCarSp;
    private Spinner yearSp;

    public final static String BRAND = "Brand";
    public final static String MODEL = "Model";
    public final static String YEAR = "Year";
    public final static String POWER = "Power";
    public final static String FUEL = "Fuel";
    public final static String RATE = "Rate";
    public final static String IMAGE_ID = "ImageId";

    public DataEntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_data_entry, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            A1 = (AppCompatActivity) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(A1.toString() + " musi implementowac OnCarTypeOptionListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(A1);
        prepareFragments();
        setTypeCar();
    }

    private void findViewsById(FragmentActivity activity) {
        imgView = (ImageView) activity.findViewById(R.id.photoIv);
        brandEv = (EditText) activity.findViewById(R.id.brandEv);
        modelEv = (EditText) activity.findViewById(R.id.modelEv);
        powerEv = (EditText) activity.findViewById(R.id.powerEv);
        ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar);
        fuelRg = (RadioGroup) activity.findViewById(R.id.colorRg);
        yearSp = (Spinner) activity.findViewById(R.id.yearSp);
        typeCarSp = (Spinner) activity.findViewById(R.id.typeCarSp);
    }

    private void prepareFragments() {
        carEntryFragment = new CarEntryFragment();
        tankEntryFragment = new TankEntryFragment();
    }

    private void setTypeCar() {
        typeCarSp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                switch(position) {
                    case 0:
                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.typeContener, carEntryFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.typeContener, tankEntryFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void saveVehicle(Bundle vehicleData) {
        int pos = typeCarSp.getSelectedItemPosition();
        switch(pos) {
            case 0:
                CarGarage cars = new CarGarage(A1);
                Car car = prepareCarToSave(vehicleData);
                cars.addCar(car);
                showToast("Zapisano samochod pomyslnie");
                break;
            case 1:
                TankGarage tanks = new TankGarage(A1);
                Tank tank = prepareTankToSave(vehicleData);
                tanks.addTank(tank);
                showToast("Zapisano czołg pomyślnie");
                break;
        }
        clearViews();
    }

    private Car prepareCarToSave(Bundle carData) {
        String brand = brandEv.getText().toString();
        String model = modelEv.getText().toString();
        String power = powerEv.getText().toString();
        String year = yearSp.getSelectedItem().toString();
        String fuel = fuelRg.getCheckedRadioButtonId() != -1 ? ((RadioButton) A1.findViewById(fuelRg.getCheckedRadioButtonId())).getText().toString() : "";
        float rate = ratingBar.getRating();
        int id = (int) imgView.getTag();
        String seats = carData.getString(CarEntryFragment.NUM_OF_SEATS);
        String type = carData.getString(CarEntryFragment.TYPE_CAR);
        String extras = carData.getString(CarEntryFragment.EXTRAS);
        return new Car(brand, model, year, power, type, seats, fuel, extras, rate, id);
    }

    private Tank prepareTankToSave(Bundle tankData) {
        String brand = brandEv.getText().toString();
        String model = modelEv.getText().toString();
        String power = powerEv.getText().toString();
        String year = yearSp.getSelectedItem().toString();
        String fuel = fuelRg.getCheckedRadioButtonId() != -1 ? ((RadioButton) A1.findViewById(fuelRg.getCheckedRadioButtonId())).getText().toString() : "";
        float rate = ratingBar.getRating();
        int id = (int) imgView.getTag();
        String info = tankData.getString(TankEntryFragment.INFO);
        String crew = tankData.getString(TankEntryFragment.CREW);
        String type = tankData.getString(TankEntryFragment.TYPE);
        return new Tank(brand, model, year, power, fuel, type, crew, info, rate, id);
    }

    public void setImage(int id) {
        imgView.setImageResource(id);
        imgView.setTag(id);
    }

    private void showToast(String message) {
        if(toast == null) {
            toast = Toast.makeText(A1, message, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            toast.cancel();
            toast = Toast.makeText(A1, message, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void clearViews() {
        brandEv.setText("");
        modelEv.setText("");
        powerEv.setText("");
        yearSp.setSelection(0);
        fuelRg.clearCheck();
        ratingBar.setRating(0);
        if(typeCarSp.getSelectedItemPosition() == 0)
            setImage(R.drawable.default_car);
        else setImage(R.drawable.default_tank);
    }
}
