package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewAppointment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnplacesnearme;

    public void init(){
        toolbar = findViewById(R.id.action_barnewAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnplacesnearme = findViewById(R.id.placesnearme);
        btnplacesnearme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAppointment.this, PlacesNearMe.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        init();
    }
}