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

public class Historique extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

/*On affiche dans une liste tout l'historique en fonction de l'user et on injecte dans un adapter pour l'affichage */
        Intent b = getIntent();
        final String login = b.getStringExtra("EXTRA_LOGINP");
        List<History> list_Historique = db.getHistorique(login);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView2);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter5(this,list_Historique));

    }
}
