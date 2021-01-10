package com.miniprojet.reclamation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateChercherReclam extends AppCompatActivity {

    private static final String TAG = "DateChercherReclam";
    public String formatDate=null;
    public String dateaff=null;
    private TextView mDisplayDate;
    private Button btnChercher;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatabaseHandler db = new DatabaseHandler(this, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chercher_reclam);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        btnChercher = findViewById(R.id.btnChercher);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        DateChercherReclam.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        /*ACtion sur bouton set date*/
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                if(day<10){formatDate= year+"-"+month+"-0"+day;}
                else if(month<10) {formatDate= year+"-0"+month+"-"+day;}
                else if((month<10)&&(day<10)){ formatDate= year+"-0"+month+"-0"+day;}
                else {formatDate= year+"-"+month+"-"+day;}
                dateaff=day+"-"+month+"-"+year;
                mDisplayDate.setText(date);

            }
        };
        Intent b = getIntent();
        final String login = b.getStringExtra("EXTRA_LOGINP");
       final String mdp = b.getStringExtra("EXTRA_MDPP");
       final String appelant = b.getStringExtra("EXTRA_CALL");
       System.out.println("l'appelant est "+appelant+"");
        btnChercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*si lactivité appelante est admin on affiche toutes e fontions sinon on affiche que celle du prof concerné*/
               if(login.equals("admin")){
                   List<ReclamationV4> list = db.GetReclByDateAdmin(formatDate);
                   if (list.isEmpty()) {

                       Toast.makeText(DateChercherReclam.this, "Aucune reclamation a cette date", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Intent b = new Intent(DateChercherReclam.this, AfficherReclamCherchee.class);
                       b.putExtra("EXTRA_DATE", formatDate);
                       b.putExtra("EXTRA_DATET", dateaff);
                       b.putExtra("EXTRA_LOGINP","admin");
                       b.putExtra("EXTRA_MDPP","");


                       startActivity(b);

                   }
               }
               else if(appelant.equals("prof")){

                   String gid= db.getGidByLoginPassword(login,mdp);
                   List<ReclamationV2> list = db.GetReclByDateEid(formatDate,gid);
                   if (list.isEmpty()) {

                       Toast.makeText(DateChercherReclam.this, "Aucune reclamation a cette date", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Intent b = new Intent(DateChercherReclam.this, AfficherReclamCherchee.class);
                       b.putExtra("EXTRA_DATE", formatDate);
                       b.putExtra("EXTRA_DATET", dateaff);
                       b.putExtra("EXTRA_LOGINP", login);
                       b.putExtra("EXTRA_MDPP", mdp);
                       b.putExtra("EXTRA_CALL","prof");
                       startActivity(b);

                   }

               }

               else if(appelant.equals("etud")) {

                   String id= db.getEidByLoginPassword(login,mdp);
                   List<ReclamationV2> list = db.GetReclByDateEtudiant(formatDate,id);
                   if (list.isEmpty()) {

                       Toast.makeText(DateChercherReclam.this, "Aucune reclamation a cette date", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Intent b = new Intent(DateChercherReclam.this, AfficherReclamCherchee.class);
                       b.putExtra("EXTRA_DATE", formatDate);
                       b.putExtra("EXTRA_DATET", dateaff);
                       b.putExtra("EXTRA_LOGINP", login);
                       b.putExtra("EXTRA_MDPP", mdp);
                       b.putExtra("EXTRA_CALL","etud");
                       startActivity(b);

                   }

               }

            }
        });
    }
}

























