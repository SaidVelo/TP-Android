package com.example.cdi182.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cdi182.R;
import com.example.cdi182.model.bean.CityBean;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<CityBean> data;

    public CityAdapter(ArrayList<CityBean> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_city, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final CityBean datum = data.get(position);
        final int tempPos = position;
        viewHolder.tv1.setText(datum.getCp());
        viewHolder.tv2.setText(datum.getVille());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        View root;

        public ViewHolder(View v) {
            super(v);
            root = v.findViewById(R.id.root);
            tv1 = v.findViewById(R.id.tv1);
            tv2 = v.findViewById(R.id.tv2);
        }

    }


}
