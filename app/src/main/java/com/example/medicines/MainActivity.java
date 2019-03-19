package com.example.medicines;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.medicines.databinding.ActivityMainBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //@BindView(R.id.fab) FloatingActionButton fab;
//    @BindView(R.id.toolbar) Toolbar toolbar;
//    @BindView(R.id.drawer_layout) DrawerLayout drawer;
//    @BindView(R.id.nav_view) NavigationView navigationView;

    private MedicineViewModel medicineViewModel;
    private ActivityMainBinding binding;


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
                startActivity(intent);
            }
        });

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        binding.navView.setNavigationItemSelectedListener(this);
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
