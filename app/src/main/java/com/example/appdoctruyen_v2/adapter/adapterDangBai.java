package com.example.appdoctruyen_v2.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.DangBaiFragment;
import com.example.appdoctruyen_v2.MainCapNhat;
import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterDangBai extends RecyclerView.Adapter<adapterDangBai.ViewHolder> {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterDangBai(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen=filteredList;
        notifyDataSetChanged();
    }


    public void setdata(Context context,ArrayList<Truyen> listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public adapterDangBai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtruyen, parent, false);
        adapterDangBai.ViewHolder viewHolder = new adapterDangBai.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterDangBai.ViewHolder holder,  int position) {
        position = holder.getAdapterPosition();
        Truyen truyen=(Truyen) listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);

        int finalPosition = position;
        holder.imgtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition);
            }
        });

        int finalPosition1 = position;
        holder.txtTenTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDelete(finalPosition1);
            }
        });
    }


    //Dialog Delete
    private void DialogDelete(int position) {
        Truyen truyen=(Truyen) listTruyen.get(position);
        databasedoctruyen databaseDocTruyen;
        databaseDocTruyen = new databasedoctruyen(context);

        //T???o ?????i t?????ng c???a s??? dialog
        Dialog dialog  =  new Dialog(context);

        //N???p layout v??o
        dialog.setContentView(R.layout.dialogdelete);
        /*Click No m???i tho??t, click ngo??i ko tho??t
        dialog.setCanceledOnTouchOutside(false);*/

        //??nh x???
        Button btnDelete = dialog.findViewById(R.id.buttonDelete);
        Button btnUpdate = dialog.findViewById(R.id.buttonUpdate);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = listTruyen.get(position).getID();
                //X??a trong SQL
                databaseDocTruyen.Delete(idtruyen);
                listTruyen.remove(position);
                //C???p nh???t l???i listview
                setdata(context, listTruyen);
                //finish();
               // startActivity(intent);
                Toast.makeText(context,"X??a truy???n th??nh c??ng",Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent=new Intent(context, MainCapNhat.class);
                String tent= truyen.getTenTruyen();
                String noidungt=truyen.getNoiDung();
                String imgtruyen = truyen.getAnh();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                intent.putExtra("imgtruyen",imgtruyen);
                int idtruyen = listTruyen.get(position).getID();
                //X??a trong SQL
                databaseDocTruyen.Delete(idtruyen);
                listTruyen.remove(position);
                //C???p nh???t l???i listview
                setdata(context, listTruyen);

                context.startActivity(intent);


            }
        });
        dialog.show();
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
