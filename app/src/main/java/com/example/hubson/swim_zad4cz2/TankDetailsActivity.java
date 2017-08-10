package com.example.hubson.swim_zad4cz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hubson.swim_zad4cz2.data_entry_fragments.TankEntryFragment;
import com.example.hubson.swim_zad4cz2.main_activity_fragments.DataEntryFragment;


public class TankDetailsActivity extends AppCompatActivity {
    private ImageView pictureIv;
    private TextView brandNameTv;
    private TextView modelNameTv;
    private TextView yearProdNameTv;
    private TextView powerNameTv;
    private TextView typTankTv;
    private TextView crewNameTv;
    private TextView fuelNameTv;
    private TextView infoNameTv;
    private RatingBar ratingNameBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_details);
        findViewsById();
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDataFromElementOfList();
    }

    private void findViewsById() {
        pictureIv = (ImageView) findViewById(R.id.pictureIv);
        brandNameTv = (TextView) findViewById(R.id.brandNameTv);
        modelNameTv = (TextView) findViewById(R.id.modelNameTv);
        yearProdNameTv = (TextView) findViewById(R.id.yearProdNameTv);
        powerNameTv = (TextView) findViewById(R.id.powerNameTv);
        typTankTv = (TextView) findViewById(R.id.typNameTv);
        crewNameTv = (TextView) findViewById(R.id.crewNameTv);
        fuelNameTv = (TextView) findViewById(R.id.fuelNameTv);
        infoNameTv = (TextView) findViewById(R.id.infoNameTv);
        ratingNameBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    private void readDataFromElementOfList() {
        Intent intent = getIntent();
        Bundle car = intent.getExtras();
        pictureIv.setImageResource(car.getInt(DataEntryFragment.IMAGE_ID));
        brandNameTv.setText(car.getString(DataEntryFragment.BRAND));
        modelNameTv.setText(car.getString(DataEntryFragment.MODEL));
        yearProdNameTv.setText(car.getString(DataEntryFragment.YEAR));
        typTankTv.setText(car.getString(TankEntryFragment.TYPE));
        powerNameTv.setText(String.format("%s KM", car.getString(DataEntryFragment.POWER)));
        crewNameTv.setText(String.format("%s - osobowa", car.getString(TankEntryFragment.CREW)));
        fuelNameTv.setText(car.getString(DataEntryFragment.FUEL));
        infoNameTv.setText(car.getString(TankEntryFragment.INFO));
        ratingNameBar.setRating(car.getFloat(DataEntryFragment.RATE));
    }
}
