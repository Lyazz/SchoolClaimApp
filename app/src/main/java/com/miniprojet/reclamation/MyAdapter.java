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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Activity activity;
    List<ReclamationV2> characters = Collections.emptyList();

    public MyAdapter(Activity activity,List<ReclamationV2> characters){
        this.activity=activity;
        this.characters=characters;
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell_professeur, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String rid = characters.get(position).getRid();
        holder.name.setText("id:"+rid+"");
        String contenu = characters.get(position).getEid();
        holder.description.setText("Etudiant:"+contenu+"");
        String date = characters.get(position).getDate();
        holder.date.setText("Date:"+date+"");
        String content= characters.get(position).getContenu();
        holder.etat.setText("contenu:"+content+"");
        String etatt= characters.get(position).getEtat();
        if (etatt.equals("0"))
        holder.etat2.setText("Etat:Non reglée");
        else
            holder.etat2.setText("Etat:Reglée");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private final TextView date;
        private final TextView etat;
        private final TextView etat2;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));
            date = ((TextView) itemView.findViewById(R.id.date));
            etat = ((TextView) itemView.findViewById(R.id.etat));
            etat2 = ((TextView) itemView.findViewById(R.id.etat2));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(itemView.getContext())

                            .setTitle(name.getText().toString())
                            .setMessage(etat.getText().toString())
                            .show();


                }
            });
        }
    }

}
