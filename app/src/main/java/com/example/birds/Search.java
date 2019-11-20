package com.example.birds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends AppCompatActivity implements View.OnClickListener {

    Button buttonShow;
    EditText editTextZipSearch;
    TextView textViewShowBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonShow = findViewById(R.id.button_Show);
        editTextZipSearch = findViewById(R.id.editText_ZipSearch);
        textViewShowBird = findViewById(R.id.textView_ShowBird);

        buttonShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Observations");

    if (view == buttonShow) {
        String findZip = editTextZipSearch.getText().toString();

        myRef.orderByChild("zip").equalTo(findZip).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String findKey = dataSnapshot.getKey();
                Observation foundObservation = dataSnapshot.getValue(Observation.class);
                String findBird = foundObservation.bird;

                textViewShowBird.setText(findBird);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); {



    }}}

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
                Intent ReportActivityIntent = new Intent(Search.this, MainActivity.class);
                startActivity(ReportActivityIntent);
                return true;
            case R.id.Search_menu_item:

                return true;
            default:
                return false;

        }
    }

}
