package com.miniprojet.reclamation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainGestionnaireActivity extends AppCompatActivity {
CheckBox checkBox;
        Button btnCnxG;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_gestionnaire);
        btnCnxG=findViewById(R.id.btnCnxG);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);
        final EditText editText2 = (EditText) findViewById(R.id.iPassword);
/*ACTION SUR LE BOUTON SE CONNECTER*/
        btnCnxG.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.iLogin);
                EditText editText2 = (EditText) findViewById(R.id.iPassword);
                String login1 = editText1.getText().toString();
                String mdp1 = editText2.getText().toString();
                String logintest="admin";

                if ((login1.toLowerCase().equals("admin"))&&(mdp1.toLowerCase().equals("admin"))) {
                    Intent b=new Intent(MainGestionnaireActivity.this,PanneauAdministrateur.class);
                    startActivity(b);
                }
                else{
                    Toast toast = Toast.makeText(MainGestionnaireActivity.this, "Login et/ou mot de passe est/sont incorrect(s)", Toast.LENGTH_SHORT);
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
