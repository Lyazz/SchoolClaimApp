package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModififerReclamAdmin extends AppCompatActivity {
Button btnmodifier;
EditText ReclamNew;

    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modififer_reclam_admin);
        ReclamNew=findViewById(R.id.editext);
        btnmodifier=findViewById(R.id.button4);

        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Reclam = ReclamNew.getText().toString();
                Intent b = getIntent();
                String rid = b.getStringExtra("EXTRA_RID");
                db.ModifierContenuReclamation(rid,Reclam);
                Toast.makeText(ModififerReclamAdmin.this, "Le contenu a été changé", Toast.LENGTH_SHORT).show();
                Intent c =  new Intent(ModififerReclamAdmin.this,GererReclams.class);
                startActivity(c);
                finish();

            }
        });

    }
}
