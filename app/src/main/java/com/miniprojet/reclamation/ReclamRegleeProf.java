package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ReclamRegleeProf extends AppCompatActivity {
    Button retourPanEtudiant;
    Button btnRegler;
    EditText editext;
    DatabaseHandler db = new DatabaseHandler(this, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclam_reglee_prof);

        Intent b = getIntent();
        final String login = b.getStringExtra("EXTRA_LOGINP");
        String mdp = b.getStringExtra("EXTRA_MDPP");
        String gid = db.getGidByLoginPassword(login,mdp);
        List<ReclamationV2> list_ReclamationV2 = db.getReclamationsUnresolvedProfesseurById(gid);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(this,list_ReclamationV2));

    }
}
