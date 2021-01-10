package com.miniprojet.reclamation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReclamRecusProf extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclam_recus_prof);
        RecyclerView ReclamSpec = (RecyclerView) findViewById(R.id.rvAnimals);
        ReclamSpec.setLayoutManager(new LinearLayoutManager(this));




    }
}