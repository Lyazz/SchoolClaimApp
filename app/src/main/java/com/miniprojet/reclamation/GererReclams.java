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

public class GererReclams extends AppCompatActivity {
Button btnModifReclam;
Button btnSuppReclam;
EditText editText;
    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*on recupere les composants de l'activité*/
        setContentView(R.layout.activity_gerer_reclams);
        btnModifReclam=findViewById(R.id.button3);
        btnSuppReclam=findViewById(R.id.button2);
        editText=findViewById(R.id.editText3);
        /*on injecte les info dans l'adapteateru pour afficher la liste selon les cellules selectionnées*/
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rvAnimals);
        List<ReclamationV4> list_ReclamationV4 = db.getReclamationsAdmin();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter3(this,list_ReclamationV4));
        /*Action SUPPRIMER UNE reclamation suivie d'un raffraichissement de la page */
        btnModifReclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rid = editText.getText().toString();
                Intent b =  new Intent(GererReclams.this,ModififerReclamAdmin.class);
                b.putExtra("EXTRA_RID",rid);
                startActivity(b);

            }
        });
        /*Action SUPPRIMER UNE reclamation suivie d'un raffraichissement de la page */
        btnSuppReclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rid = editText.getText().toString();
                db.suppReclamation(rid);
                finish();
                startActivity(getIntent());
            }
        });




    }
}
