// By Krzysiek

package com.example.birds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonReport;
    EditText editTextBird, editTextZipReport, editTextObserver;
        private Object Observation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReport = findViewById(R.id.buttonReport);
        editTextBird = findViewById(R.id.editText_Bird);
        editTextZipReport = findViewById(R.id.editText_ZipReport);
        editTextObserver = findViewById(R.id.editText_Observer);

        buttonReport.setOnClickListener(this);
    }

    @Override
    //Adding observations to Firebase
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Observations");


        if (view == buttonReport) {
            //Registering
            String bird = editTextBird.getText().toString();
            String zip = editTextZipReport.getText().toString();
            String observer = editTextObserver.getText().toString();

            Observation createObservation = new Observation(bird, zip, observer);
            myRef.push().setValue(createObservation);

        }

    }

    @Override
    //Making menu items show
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.dropdown_menu, menu);
    return super.onCreateOptionsMenu(menu);
    }

    @Override
    //Making menu items work
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Report_menu_item:
                return true;
            case R.id.Search_menu_item:
                Intent SearchActivityIntent = new Intent(MainActivity.this, Search.class);
                startActivity(SearchActivityIntent);
                return true;
            default:
                return false;

        }
    }



}
