package com.wolfie.waterreminder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.ViewHolder;

public class todayadapter extends RecyclerView.Adapter<todayadapter.My> {
    Context context;
    ArrayList<todayrecordgetter> al;

    public todayadapter(Context context, ArrayList<todayrecordgetter> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public My onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todayrecordview, viewGroup, false);
        return new My(v);
    }

    @Override
    public void onBindViewHolder(@NonNull My my, int i) {
        todayrecordgetter trg = al.get(i);
        my.tvVol.setText(trg.getVolume()+" ML");
        my.tvDate.setText(trg.getDate());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class My extends ViewHolder {
        TextView  tvVol, tvDate;

        public My(@NonNull View itemView) {
            super(itemView);
            tvVol = itemView.findViewById(R.id.recordvolume);
            tvDate = itemView.findViewById(R.id.recorddate);
        }
    }
}
