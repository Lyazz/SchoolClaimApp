package com.miniprojet.reclamation;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;



import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    //Ativité du professeur
    Button btnCnxP;
    CheckBox checkBox;
    EditText editText2;
    DatabaseHandler db = new DatabaseHandler(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCnxP=findViewById(R.id.btnCnxP);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        final EditText editText2 = (EditText) findViewById(R.id.iPassword);

        btnCnxP.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.iLogin);
                EditText editText2 = (EditText) findViewById(R.id.iPassword);
                String login = editText1.getText().toString();
                String mdp = editText2.getText().toString();

                //On verifie si i lest un professeur

                Cursor cr = db.checkManagersDB(login,mdp);

                if (cr.moveToFirst()) {
                    db.AddHistoricConnex(login);
                    Intent b=new Intent(MainActivity.this,PanneauProfesseur.class);
                    b.putExtra("EXTRA_LOGINP",login);
                    b.putExtra("EXTRA_MDPP",mdp);
                    startActivity(b);
                }

                else{
                    Toast toast = Toast.makeText(MainActivity.this, "Login et/ou mot de passe est/sont incorrect(s)", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.rouge);
                    toast.show();

                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value){
                if (value)
                {
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
    });

    }
}
