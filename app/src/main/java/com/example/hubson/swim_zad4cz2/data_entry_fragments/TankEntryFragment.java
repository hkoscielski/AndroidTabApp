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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hubson.swim_zad4cz2.R;

public class TankEntryFragment extends Fragment {
    private AppCompatActivity A1;
    private OnChangeImageListener imageListener;
    private OnSaveVehicleListener saveVehicleListener;

    private EditText infoEv;
    private SeekBar crewSb;
    private Spinner typeTankSp;
    private TextView crewTv;

    public final static String INFO = "Info";
    public final static String CREW = "Crew";
    public final static String TYPE = "Type";

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            A1 = (AppCompatActivity) context;
            imageListener = (OnChangeImageListener) context;
            saveVehicleListener = (OnSaveVehicleListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " musi implementowac TankEntryFragment.OnChangeImageListener oraz TankEntryFragment.OnSaveVehicleListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tank_entry, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(A1);
        choosePicture();
        chooseCrew();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_item:
                saveVehicleListener.prepareVehicleToSave(getTankData());
                clearData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewsById(FragmentActivity activity) {
        infoEv = (EditText) activity.findViewById(R.id.infoEv);
        typeTankSp = (Spinner) activity.findViewById(R.id.typeTankSp);
        crewSb = (SeekBar) activity.findViewById(R.id.crewSb);
        crewTv = (TextView) activity.findViewById(R.id.crewTv);
    }

    private void chooseCrew() {
        crewSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                crewTv.setText(String.valueOf(progress));
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
        typeTankSp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                switch(position) {
                    case 0:
                        imageListener.setImage(R.drawable.default_tank);
                        break;
                    case 1:
                        imageListener.setImage(R.drawable.light_tank);
                        break;
                    case 2:
                        imageListener.setImage(R.drawable.medium_tank);
                        break;
                    case 3:
                        imageListener.setImage(R.drawable.heavy_tank);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Bundle getTankData() {
        Bundle data = new Bundle();
        String info = infoEv.getText().toString();
        String crew = String.valueOf(crewSb.getProgress());
        String type = typeTankSp.getSelectedItem().toString();
        data.putString(INFO, info);
        data.putString(CREW, crew);
        data.putString(TYPE, type);
        return data;
    }

    private void clearData() {
        infoEv.setText("");
        crewSb.setProgress(0);
    }

    public interface OnChangeImageListener {
        public void setImage(int id);
    }

    public interface OnSaveVehicleListener {
        public void prepareVehicleToSave(Bundle vehicleData);
    }
}
