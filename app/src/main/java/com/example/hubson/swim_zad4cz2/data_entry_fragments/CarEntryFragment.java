package com.example.hubson.swim_zad4cz2.data_entry_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hubson.swim_zad4cz2.R;

public class CarEntryFragment extends Fragment {
    private AppCompatActivity A1;
    private OnChangeImageListener imageListener;
    private OnSaveVehicleListener saveVehicleListener;

    private CheckBox extra1Cb;
    private CheckBox extra2Cb;
    private CheckBox extra3Cb;
    private CheckBox extra4Cb;
    private CheckBox extra5Cb;
    private CheckBox extra6Cb;
    private CheckBox extra7Cb;
    private CheckBox extra8Cb;
    private CheckBox extra9Cb;
    private CheckBox extra10Cb;
    private SeekBar seatsSb;
    private Spinner typeSp;
    private TextView numOfSeatsTv;

    public final static String TYPE_CAR = "TypeCar";
    public final static String NUM_OF_SEATS = "NumOfSeats";
    public final static String EXTRAS = "Extras";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_entry, container, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            A1 = (AppCompatActivity) context;
            imageListener = (OnChangeImageListener) context;
            saveVehicleListener = (OnSaveVehicleListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " musi implementowac OnChangeImageListener i OnSaveVehicleListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(A1);
        chooseSeats();
        choosePicture();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_item:
                saveVehicleListener.prepareVehicleToSave(getCarData());
                clearViews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewsById(FragmentActivity activity) {
        extra1Cb = (CheckBox) activity.findViewById(R.id.extra1Cb);
        extra2Cb = (CheckBox) activity.findViewById(R.id.extra2Cb);
        extra3Cb = (CheckBox) activity.findViewById(R.id.extra3Cb);
        extra4Cb = (CheckBox) activity.findViewById(R.id.extra4Cb);
        extra5Cb = (CheckBox) activity.findViewById(R.id.extra5Cb);
        extra6Cb = (CheckBox) activity.findViewById(R.id.extra6Cb);
        extra7Cb = (CheckBox) activity.findViewById(R.id.extra7Cb);
        extra8Cb = (CheckBox) activity.findViewById(R.id.extra8Cb);
        extra9Cb = (CheckBox) activity.findViewById(R.id.extra9Cb);
        extra10Cb = (CheckBox) activity.findViewById(R.id.extra10Cb);
        seatsSb = (SeekBar) activity.findViewById(R.id.seatsSb);
        typeSp = (Spinner) activity.findViewById(R.id.typeSp);
        numOfSeatsTv = (TextView) activity.findViewById(R.id.numOfSeatsTv);
    }

    private void chooseSeats() {
        seatsSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numOfSeatsTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void choosePicture() {
        typeSp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                switch(position) {
                    case 0:
                        imageListener.setImage(R.drawable.default_car);
                        break;
                    case 1:
                        imageListener.setImage(R.drawable.hatchback);
                        break;
                    case 2:
                        imageListener.setImage(R.drawable.kombi);
                        break;
                    case 3:
                        imageListener.setImage(R.drawable.sedan);
                        break;
                    case 4:
                        imageListener.setImage(R.drawable.sportowe);
                        break;
                    case 5:
                        imageListener.setImage(R.drawable.suw);
                        break;
                    case 6:
                        imageListener.setImage(R.drawable.wan);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private Bundle getCarData() {
        Bundle data = new Bundle();
        String seats = numOfSeatsTv.getText().toString();
        String type = typeSp.getSelectedItem().toString();
        String extras = getExtrasText();
        data.putString(NUM_OF_SEATS, seats);
        data.putString(TYPE_CAR, type);
        data.putString(EXTRAS, extras);
        return data;
    }

    private void clearViews() {
        seatsSb.setProgress(0);
        extra1Cb.setChecked(false);
        extra2Cb.setChecked(false);
        extra3Cb.setChecked(false);
        extra4Cb.setChecked(false);
        extra5Cb.setChecked(false);
        extra6Cb.setChecked(false);
        extra7Cb.setChecked(false);
        extra8Cb.setChecked(false);
        extra9Cb.setChecked(false);
        extra10Cb.setChecked(false);
    }

    private String getExtrasText() {
        return new StringBuilder().append(extra1Cb.isChecked() ? extra1Cb.getText().toString() + "\n" : "")
                                 .append(extra2Cb.isChecked() ? extra2Cb.getText().toString() + "\n" : "")
                                 .append(extra3Cb.isChecked() ? extra3Cb.getText().toString() + "\n" : "")
                                 .append(extra4Cb.isChecked() ? extra4Cb.getText().toString() + "\n" : "")
                                 .append(extra5Cb.isChecked() ? extra5Cb.getText().toString() + "\n" : "")
                                 .append(extra6Cb.isChecked() ? extra6Cb.getText().toString() + "\n" : "")
                                 .append(extra7Cb.isChecked() ? extra7Cb.getText().toString() + "\n" : "")
                                 .append(extra8Cb.isChecked() ? extra8Cb.getText().toString() + "\n" : "")
                                 .append(extra9Cb.isChecked() ? extra9Cb.getText().toString() + "\n" : "")
                                 .append(extra10Cb.isChecked() ? extra10Cb.getText().toString() + "\n" : "")
                                 .toString();
    }

    public interface OnChangeImageListener {
        public void setImage(int id);
    }

    public interface OnSaveVehicleListener {
        public void prepareVehicleToSave(Bundle vehicleData);
    }
}
