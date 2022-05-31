package com.example.appdoctruyen_v2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyen extends RecyclerView.Adapter<adapterTruyen.ViewHolder> {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtruyen, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen=(Truyen) listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);

        holder.imgtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainNoiDungTruyen.class);
                String tent= truyen.getTenTruyen();
                String noidungt=truyen.getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                context.startActivity(intent);
            }
        });

        holder.txtTenTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainNoiDungTruyen.class);
                String tent= truyen.getTenTruyen();
                String noidungt=truyen.getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTruyen;
        ImageView imgtruyen;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenTruyen =  itemView.findViewById(R.id.textviewTenTruyen);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtruyen=itemView.findViewById(R.id.imgNewTruyen);
        }
    }
}
