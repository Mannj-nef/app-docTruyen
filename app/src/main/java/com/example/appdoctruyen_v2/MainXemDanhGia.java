package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.DanhGia;
import com.example.appdoctruyen_v2.adapter.DanhGiaAdapter;

import java.util.ArrayList;

public class MainXemDanhGia extends AppCompatActivity {
      ArrayList<DanhGia> listdanhgia;
    //  static ArrayList<DanhGia> listxem_dg;
      ListView list_view;
    databasedoctruyen databasedoctruyen;
    DanhGiaAdapter DanhGiaAdapter;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_xem_danh_gia);
        databasedoctruyen=new databasedoctruyen(this);


        Intent intent_dg = getIntent();
        String tentk=intent_dg.getStringExtra("tentk");
        String  noidung =intent_dg.getStringExtra("noidung");
        int dl= intent_dg.getIntExtra("dulieu",0);
        AnhXa();





    }

    void AnhXa(){
        list_view =findViewById(R.id.list_view);

        listdanhgia = new ArrayList<>();

        Cursor cursor3 = databasedoctruyen.getData3();
        while (cursor3.moveToNext())
        {
            int id=cursor3.getInt(0);

            String TenTK=cursor3.getString(3);
            String NoiDungDanhGia=cursor3.getString(1);

            listdanhgia.add(new DanhGia(id,NoiDungDanhGia,TenTK, cursor3.getString(2)));

            DanhGiaAdapter = new DanhGiaAdapter(getApplicationContext(),listdanhgia);
            list_view.setAdapter(DanhGiaAdapter);
        }
        cursor3.moveToFirst();
        cursor3.close();

    }

}