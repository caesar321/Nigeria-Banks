package com.example.nigerianbanks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.VIEWHOLDER>{
    private Context context;
    private ArrayList<modal> modals;

    public AdapterClass(Context context, ArrayList<modal> modals) {
        this.context = context;
        this.modals = modals;
    }


    @NonNull
    @Override
    public VIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.adapterlayout,parent,false);
        return new VIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER holder, int position) {
        holder.bankName.setText(modals.get(position).getBankName());
        holder.ussdCode.setText(modals.get(position).getUssdCode());
        Picasso.get().load(modals.get(position).getImageView()).into(holder.bankimage);
    }

    @Override
    public int getItemCount() {
        return modals.size();
    }

    public class VIEWHOLDER extends RecyclerView.ViewHolder {
        private TextView bankName,ussdCode;
        private CircleImageView bankimage;
        public VIEWHOLDER(@NonNull View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.bnk);
            ussdCode = itemView.findViewById(R.id.code);
            bankimage = itemView.findViewById(R.id.circular1);
        }
    }
}
