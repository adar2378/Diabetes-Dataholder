package com.example.masteradar.diabetesdataholder;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Master Adar on 15-Sep-17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    ArrayList<UserDetails> data;
    Context context;

    public MyAdapter(Context context , ArrayList<UserDetails> data) {
        this.context = context;
        Collections.reverse(data);//reversing the list order
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.user_details_row,parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {//setting the values on viewholder
        UserDetails current = data.get(position);
        holder.diabetesText.setText("Diabetes Level: "+current.getDiabetesLevel());
        holder.bpText.setText("Blood Pressure: "+current.getBPHigh()+" - "+current.getBPLow());
        if(!current.isAte()){
            holder.ateText.setText("After eating");
        }
        else{
            holder.ateText.setText("Before eating");
        }
        holder.timeText.setText(current.time+"\n");



    }

    @Override
    public int getItemCount() {

        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{//setting the viewholder class
        TextView diabetesText, ateText, bpText, timeText;

        public MyViewHolder(View itemView) {
            super(itemView);

            diabetesText = itemView.findViewById(R.id.diabetesLevelTextView);
            ateText = itemView.findViewById(R.id.ateTextView);
            bpText = itemView.findViewById(R.id.BpTextView);
            timeText = itemView.findViewById(R.id.timeTextView);

            diabetesText.setTextColor(Color.BLACK);
            ateText.setTextColor(Color.BLACK);
            bpText.setTextColor(Color.BLACK);
            timeText.setTextColor(Color.BLACK);
        }


    }
}
