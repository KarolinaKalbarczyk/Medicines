package com.example.medicines;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.medicines.databinding.ActivityEditorBinding;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityEditorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
//            Spinner dropdown = (Spinner) binding.spinner;
//            ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
//                    .createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
//
//            // Specify the layout to use when the list of choices appears
//            staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//            // Apply the adapter to the spinner
//            dropdown.setAdapter(staticAdapter);
            ////String times[] = {"Hours","Days","Weeks","Months","Years"};
            ////Spinner spinner = (Spinner) findViewById(R.id.spinner);

            //Spinner spinner = new Spinner(this);
            ////ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, times);
            ////spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            ////spinner.setAdapter(spinnerArrayAdapter);
            //////Spinner spinnerCount = (Spinner)findViewById(R.id.spinner);
            //////ArrayAdapter<String> spinnerCountArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.time));
            //////spinnerCount.setAdapter(spinnerCountArrayAdapter);

            // set the spinner data programmatically, from a string array or list

// (1) get a reference to the spinner
            Spinner spinner = (Spinner) findViewById(R.id.spinner);

// (2) create a simple static list of strings
            List<String> spinnerArray = new ArrayList<>();
            spinnerArray.add("Hours");
            spinnerArray.add("Days");

// (3) create an adapter from the list
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// (4) set the adapter on the spinner
            spinner.setAdapter(adapter);


        }
    }

}
