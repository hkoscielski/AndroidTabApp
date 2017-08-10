package com.example.hubson.swim_zad4cz2;

import com.example.hubson.swim_zad4cz2.main_activity_fragments.*;
import com.example.hubson.swim_zad4cz2.data_entry_fragments.*;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, CarEntryFragment.OnChangeImageListener, CarEntryFragment.OnSaveVehicleListener, TankEntryFragment.OnChangeImageListener, TankEntryFragment.OnSaveVehicleListener {
    private ActionBar myActionBar;
    private FragmentTransaction fragmentTransaction;
    private MainFragment mainFragment;
    private DataEntryFragment dataEntryFragment;
    private CarListFragment carListFragment;
    private TankListFragment tankListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareFragments();
        setActionBar();
    }

    private void prepareFragments() {
        mainFragment = new MainFragment();
        dataEntryFragment = new DataEntryFragment();
        carListFragment = new CarListFragment();
        tankListFragment = new TankListFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContener, mainFragment);
        fragmentTransaction.detach(mainFragment);
        fragmentTransaction.add(R.id.mainContener, dataEntryFragment);
        fragmentTransaction.detach(dataEntryFragment);
        fragmentTransaction.add(R.id.mainContener, carListFragment);
        fragmentTransaction.detach(carListFragment);
        fragmentTransaction.add(R.id.mainContener, tankListFragment);
        fragmentTransaction.detach(tankListFragment);
        fragmentTransaction.commit();
    }

    private void setActionBar() {
        myActionBar = getSupportActionBar();
        myActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addTab(R.drawable.ic_home_white_24dp);
        addTab(R.drawable.ic_add_white_24dp);
        addTab(R.drawable.ic_car_white_24dp);
        addTab(R.drawable.ic_tank_white_24dp);
    }

    private void addTab(int imageId) {
        ActionBar.Tab tab = myActionBar.newTab();
        tab.setIcon(imageId);
        tab.setTabListener(this);
        myActionBar.addTab(tab);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                ft.attach(mainFragment);
                break;
            case 1:
                ft.attach(dataEntryFragment);
                break;
            case 2:
                ft.attach(carListFragment);
                break;
            case 3:
                ft.attach(tankListFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        int position = tab.getPosition();
        switch (position) {
            case 0:
                ft.detach(mainFragment);
                break;
            case 1:
                ft.detach(dataEntryFragment);
                break;
            case 2:
                ft.detach(carListFragment);
                break;
            case 3:
                ft.detach(tankListFragment);
                break;
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void setImage(int id) {
        dataEntryFragment.setImage(id);
    }

    @Override
    public void prepareVehicleToSave(Bundle vehicleData) {
        dataEntryFragment.saveVehicle(vehicleData);
    }
}
