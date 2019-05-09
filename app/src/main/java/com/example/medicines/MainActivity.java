package com.example.medicines;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicines.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.medicines.EditorActivity.MEDICINE_DATA;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private MedicineViewModel medicineViewModel;
    private ActivityMainBinding binding;
    private MedicineAdapter adapter;


    //aby móc porównywać kod, w celu wyświetlenia, deklarujemy jeden z nich
    private static final int NEW_MEDICINE = 1;
    private static final int EDIT_MEDICINE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.appBarMain.toolbar);



        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                //uruchamia aktywnosc ustalona w intencie
                startActivityForResult(intent, NEW_MEDICINE);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        RecyclerViewClickListener listener = (view, position) -> {
            //Toast.makeText(this, "Position " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
            intent.putExtra(MEDICINE_DATA, adapter.getMedicineByPosition(position));
            startActivityForResult(intent, EDIT_MEDICINE);
        };

        // zaladuj ArrayList do adaptera i wywolaj loadData
        adapter = new MedicineAdapter(new ArrayList<>(), listener);
        loadData();

        RecyclerView rv = binding.appBarMain.contentMain.myRecyclerView;
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }



    //jesli requestCode taki jak podany, i resultCode równy 666 to wywolaj loadData
    //po co drugi raz skoro wywoływalismy w 67 linii?
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == NEW_MEDICINE){
            if(resultCode == 666){
                loadData();
            }
        }
        else if(requestCode == EDIT_MEDICINE) {
            if (resultCode == 777) {
                loadData(); // rekord usunięty w innej klasie wiec teraz tylko odwiezyc widok
                //TODO gdzie był usunięty ten rekord? W MedicineViewModel czy w MedicineService? Czemu nie znika tylko trzeba odswieżyć?
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //pobieram "kontakt" do MedicineService, zaladowuje liste medicines za pomoca getAllMedicine i wywoluje update
    private void loadData(){
        //todo move to view model like in EditorActivity
        MedicineService service = getMedicineApp().getMedicineService();
        List<Medicine> medicines = service.getAllMedicine();
        adapter.updateList(medicines);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void email(){

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:myAddress@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "I have a suggestion");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.today) {
            // Handle the camera action
        } else if (id == R.id.all) {

        } else if (id == R.id.pharmacy) {

        } else if (id == R.id.nav_send) {

            email();

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
