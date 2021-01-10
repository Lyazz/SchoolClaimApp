package com.miniprojet.reclamation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miniprojet.reclamation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private Activity activity;
    List<ReclamationV3> characters = Collections.emptyList();

    public MyAdapter2(Activity activity,List<ReclamationV3> characters){
        this.activity=activity;
        this.characters=characters;
    }

    public MyAdapter2(String rid){

    }



    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String rid = characters.get(position).getRid();
        holder.name.setText("id:"+rid+"");
        String nompp = characters.get(position).getNomp();
        String prenompp = characters.get(position).getPrenomp();
        holder.description.setText("Professeur:"+nompp+" "+prenompp+"");
        String date = characters.get(position).getDate_heure();
        holder.date.setText("Date:"+date+"");
        String etat = characters.get(position).getEtat();
        if(etat.equals("0"))
        holder.etat.setText("Etat:Non Reglée");
        else
        holder.etat.setText("Etat:Reglée");
        String contenu = characters.get(position).getContenu();
        holder.contents.setText("Contenu:"+contenu+"");


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView date;
        private final TextView etat;
        private final TextView contents;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));
            date = ((TextView) itemView.findViewById(R.id.date));
            etat = ((TextView) itemView.findViewById(R.id.etat));
            contents = ((TextView) itemView.findViewById(R.id.contents));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(itemView.getContext())

                            .setTitle(name.getText().toString())
                            .setMessage(contents.getText().toString())
                            .show();
                    //Bouton de modification on sait jamais
                            /*.setPositiveButton("regler", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.reglerReclam(rid);
                                }
                            })*/


                }
            });
        }
    }

}
