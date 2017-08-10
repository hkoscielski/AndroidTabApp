package com.example.hubson.swim_zad4cz2.main_activity_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubson.swim_zad4cz2.CarDetailsActivity;
import com.example.hubson.swim_zad4cz2.R;
import com.example.hubson.swim_zad4cz2.data_entry_fragments.CarEntryFragment;
import com.example.hubson.swim_zad4cz2.serializable_class.CarGarage;


public class CarListFragment extends Fragment {
    private ActionMode mActionMode;
    private ActionMode.Callback amInterface;
    private AppCompatActivity A2;
    private ListView carListView;
    private CarGarage carGarage;
    private Toast toast;
    private MyAdapter2 adapter;

    public CarListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            A2 = (AppCompatActivity) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(A2.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(A2);
        carGarage = new CarGarage(A2);
        adapter = new MyAdapter2();
        carListView.setAdapter(adapter);
        initContextMode();
        removeRecord();
        showDetails();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sort_brand_item:
                carGarage.sortByBrand();
                //carListView.setAdapter(new MyAdapter2());
                refreshList();
                break;
            case R.id.sort_year_item:
                carGarage.sortByYear();
                //carListView.setAdapter(new MyAdapter2());
                refreshList();
                break;
            case R.id.sort_rate_item:
                carGarage.sortByRating();
                //carListView.setAdapter(new MyAdapter2());
                refreshList();
                break;
            case R.id.delete_all_item:
                createDeleteAllAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private void createDeleteAllAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(A2);
        dialogBuilder.setTitle("Usuń wszystkie");
        dialogBuilder.setMessage("Czy na pewno chcesz usunąć wszystkie samochody?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                carGarage.removeAllCar();
                //carListView.setAdapter(new MyAdapter2());
                refreshList();
                showToast("Usunięto wszystkie");
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog ad = dialogBuilder.create();
        ad.show();
    }

    private void findViewsById(FragmentActivity activity) {
        carListView = (ListView) activity.findViewById(R.id.carListView);
    }

        private class MyAdapter2 extends BaseAdapter {
            private LayoutInflater inflater = null;
            private ImageView img;
            private TextView brandTv;
            private TextView markTv;
            private TextView yearsTv;
            private RatingBar rating;


            @Override
            public int getCount() {
                return carGarage.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View mV;
                inflater = (LayoutInflater) A2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(convertView == null) {
                    convertView = inflater.inflate(R.layout.list_row, null);
                }
                mV = convertView;
                findViewsById(mV);
                img.setImageResource(carGarage.getCar(position).getId());
                brandTv.setText(carGarage.getCar(position).getBrand());
                markTv.setText(carGarage.getCar(position).getModel());
                yearsTv.setText(carGarage.getCar(position).getYear());
                rating.setRating(carGarage.getCar(position).getRate());
                return mV;
            }

            private void findViewsById(View mV) {
                img = (ImageView) mV.findViewById(R.id.row_image);
                brandTv = (TextView) mV.findViewById(R.id.brand_tv1);
                markTv = (TextView) mV.findViewById(R.id.mark_tv2);
                yearsTv = (TextView) mV.findViewById(R.id.year_tv3);
                rating = (RatingBar) mV.findViewById(R.id.listRatingBar);
            }
        }

        private void showDetails() {
            carListView.setOnItemClickListener(new ListView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Intent intent = new Intent(A2.getApplicationContext(), CarDetailsActivity.class);
                    Bundle car = new Bundle();
                    car.putString(DataEntryFragment.BRAND, carGarage.getCar(position).getBrand());
                    car.putString(DataEntryFragment.MODEL, carGarage.getCar(position).getModel());
                    car.putString(DataEntryFragment.POWER, carGarage.getCar(position).getPower());
                    car.putString(DataEntryFragment.YEAR, carGarage.getCar(position).getYear());
                    car.putString(CarEntryFragment.NUM_OF_SEATS, carGarage.getCar(position).getNumOfSeats());
                    car.putString(DataEntryFragment.FUEL, carGarage.getCar(position).getFuel());
                    car.putString(CarEntryFragment.TYPE_CAR, carGarage.getCar(position).getType());
                    car.putString(CarEntryFragment.EXTRAS, carGarage.getCar(position).getExtras());
                    car.putFloat(DataEntryFragment.RATE, carGarage.getCar(position).getRate());
                    car.putInt(DataEntryFragment.IMAGE_ID, carGarage.getCar(position).getId());
                    intent.putExtras(car);
                    startActivity(intent);
                }
            });
        }

        private void initContextMode() {
            amInterface = new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    carListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    carListView.setItemsCanFocus(false);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    mActionMode = null;
                }
            };
        }
//
//        private void removeRecord() {
//            carListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//                @Override
//                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    if(mActionMode != null)
//                        return false;
//                    mActionMode = A2.startSupportActionMode(amInterface);
//                    return true;
//                }
//            });
//        }

        private void removeRecord() {
            carListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    createAlertDialogWithList(position);
                    return true;
                }
            });
        }

        private void createAlertDialogWithList(final int position) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(A2);
            final String[] options = {"Usuń"};
            dialogBuilder.setTitle("Wybierz opcję");
            dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(which) {
                        case 0:
                            createConfirmAlertDialog(position);
                            break;
                    }
                }
            });
            AlertDialog ad = dialogBuilder.create();
            ad.show();
        }

        private void createConfirmAlertDialog(final int position) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(A2);
            dialogBuilder.setTitle("Usuń");
            dialogBuilder.setMessage("Czy na pewno chcesz usunąć samochód z listy?");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    carGarage.removeCar(carGarage.getCar(position));
                    //carListView.setAdapter(new MyAdapter2());
                    refreshList();
                    showToast("Usunięto");
                }
            });
            dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog ad = dialogBuilder.create();
            ad.show();
        }

    private void showToast(String message) {
        if(toast != null) toast.cancel();
        toast = Toast.makeText(A2, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
