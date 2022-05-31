package com.example.appdoctruyen_v2.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.YeuThichFragment;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyenYeuThich extends RecyclerView.Adapter<adapterTruyenYeuThich.ViewHolder> {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyenYeuThich(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();
    }

    public void setdata(Context context,ArrayList<Truyen> listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
        notifyDataSetChanged();

    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen=filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapterTruyenYeuThich.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyenv2, parent, false);
        adapterTruyenYeuThich.ViewHolder viewHolder = new adapterTruyenYeuThich.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTruyenYeuThich.ViewHolder holder, int position) {
        Truyen truyen=(Truyen) listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(holder.imgtruyen);

        holder.imgtruyen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogDeleteYeuThic(position);
                return false;
            }
        });

       holder.txtTenTruyen.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               DialogDeleteYeuThic(position);
               return false;
           }
       });
    }
    private void DialogDeleteYeuThic(int position) {
        databasedoctruyen databaseDocTruyen;
        databaseDocTruyen=new databasedoctruyen(context);

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(context);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeleteyeuthic);
        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = listTruyen.get(position).getID();
                //Xóa trong SQL
                databaseDocTruyen.Delete(idtruyen);
                //Cập nhật lại listview
                listTruyen.remove(position);
               setdata(context, listTruyen);
                Toast.makeText(context,"Bỏ yêu thích thành công",Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
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
            txtTenTruyen =  itemView.findViewById(R.id.textviewTenTruyenv2);
            //viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTenTruyen);
            imgtruyen=itemView.findViewById(R.id.imgNewTruyenv2);
        }
    }
}
