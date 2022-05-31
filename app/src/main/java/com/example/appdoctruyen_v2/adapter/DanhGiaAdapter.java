package com.example.appdoctruyen_v2.adapter;

import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.MainNoiDungTruyen;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.model.DanhGia;

import java.util.ArrayList;

public class DanhGiaAdapter extends BaseAdapter {
    private Context contex;
    private  ArrayList<DanhGia> listDG;
    public DanhGiaAdapter(Context context, ArrayList<DanhGia> listdg) {
        this.contex = context;
        this.listDG = listdg;

    }


    public int getCount() {
        return listDG.size();
    }


    public Object getItem(int position) {
        return listDG.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) contex.getSystemService(contex.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custum_danhgia,null);
        //  TextView txtTenTaiKhoan = (TextView) convertView.findViewById(R.id.Text_Name);
        //  TextView txtGmail = (TextView) convertView.findViewById(R.id.Text_Gmail);

        //TaiKhoan taiKhoan = taiKhoanList.get(position);

        DanhGia danhGia = listDG.get(position);
        //Hiển thị lên màn hình
        //txtTenTaiKhoan.setText(taiKhoan.getmTenTaiKhoan());
        // txtGmail.setText(taiKhoan.getmEmail());

        TextView txtnoidung =   convertView.findViewById(R.id.txt_noidung);
        TextView txt_tentk = convertView.findViewById(R.id.txttentk);
        TextView txt_tentruyen = convertView.findViewById(R.id.txt_tentruyen);

        //  txtnoidung.setText(listDG.get(i).getNoiDungDanhGia());
        //  txt_tentk.setText(listDG.get(i).getTenTK());
        txtnoidung.setText(danhGia.getNoiDungDanhGia());
        txt_tentk.setText(danhGia.getTenTK());
        txt_tentruyen.setText("Truyện: "+danhGia.getTenTruyen());

        return convertView;
    }
}
