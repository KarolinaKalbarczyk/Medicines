package com.example.medicines;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.medicines.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //@BindView(R.id.fab) FloatingActionButton fab;
//    @BindView(R.id.toolbar) Toolbar toolbar;
//    @BindView(R.id.drawer_layout) DrawerLayout drawer;
//    @BindView(R.id.nav_view) NavigationView navigationView;

    private MedicineViewModel medicineViewModel;
    private ActivityMainBinding binding;
    private MedicineAdapter adapter;

    private static final int NEW_MEDICINE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.appBarMain.toolbar);

//        medicineViewModel = new MedicineViewModel();
//
//        binding.setMedicineViewModel(medicineViewModel);


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                //intent.putExtra(EditorActivity.MEDICINE_DATA, new Medicine("Vit C", 10, 20, 1, new byte[0]));
                //startActivity(intent);
                startActivityForResult(intent, NEW_MEDICINE);
            }
        });

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        binding.navView.setNavigationItemSelectedListener(this);

        //todo tymczasowe tworzenie listy
        /*List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("Vit A", 10, 20, 1, new byte[0]));
        medicines.add(new Medicine("Vit B", 10, 20, 1, new byte[0]));
        medicines.add(new Medicine("Vit C", 10, 20, 1, new byte[0]));*/
        adapter = new MedicineAdapter(new ArrayList<>());
        loadData();

        RecyclerView rv = binding.appBarMain.contentMain.myRecyclerView;//.findViewById(R.id.myRecyclerView);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == NEW_MEDICINE){
            if(resultCode == 666){
                loadData();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        loadData();
    }*/

    private void loadData(){
        //todo move to view model like in EditorActivity
        MedicineService service = getMedicineApp().getMedicineService();
        List<Medicine> medicines = service.getAllMedicine();
        adapter.updateList(medicines);
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
