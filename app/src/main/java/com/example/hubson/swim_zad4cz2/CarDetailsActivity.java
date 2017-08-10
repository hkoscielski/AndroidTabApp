package com.example.hubson.swim_zad4cz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hubson.swim_zad4cz2.data_entry_fragments.CarEntryFragment;
import com.example.hubson.swim_zad4cz2.main_activity_fragments.DataEntryFragment;


public class CarDetailsActivity extends AppCompatActivity {
    private ImageView pictureIv;
    private TextView brandNameTv;
    private TextView modelNameTv;
    private TextView yearProdNameTv;
    private TextView powerNameTv;
    private TextView typNameTv;
    private TextView numSeatsNameTv;
    private TextView fuelNameTv;
    private TextView extrasNameTv;
    private RatingBar ratingNameBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
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
        typNameTv = (TextView) findViewById(R.id.typNameTv);
        numSeatsNameTv = (TextView) findViewById(R.id.numSeatsNameTv);
        fuelNameTv = (TextView) findViewById(R.id.fuelNameTv);
        extrasNameTv = (TextView) findViewById(R.id.extrasNameTv);
        ratingNameBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    private void readDataFromElementOfList() {
        Intent intent = getIntent();
        Bundle car = intent.getExtras();
        pictureIv.setImageResource(car.getInt(DataEntryFragment.IMAGE_ID));
        brandNameTv.setText(car.getString(DataEntryFragment.BRAND));
        modelNameTv.setText(car.getString(DataEntryFragment.MODEL));
        yearProdNameTv.setText(car.getString(DataEntryFragment.YEAR));
        typNameTv.setText(car.getString(CarEntryFragment.TYPE_CAR));
        powerNameTv.setText(String.format("%s KM", car.getString(DataEntryFragment.POWER)));
        numSeatsNameTv.setText(car.getString(CarEntryFragment.NUM_OF_SEATS));
        fuelNameTv.setText(car.getString(DataEntryFragment.FUEL));
        extrasNameTv.setText(car.getString(CarEntryFragment.EXTRAS));
        ratingNameBar.setRating(car.getFloat(DataEntryFragment.RATE));
    }
}
