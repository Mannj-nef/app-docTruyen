package com.example.appdoctruyen_v2.model.main;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appdoctruyen_v2.MainCapNhat;
import com.example.appdoctruyen_v2.MainDangBai;
import com.example.appdoctruyen_v2.R;
import com.example.appdoctruyen_v2.adapter.adapterTruyen;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    RecyclerView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adaptertruyen;
    databasedoctruyen databaseDocTruyen;
    ArrayList<Truyen> TruyenArraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonAddTruyen);

        initList();
      /*  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }


        });*/


        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                int id = intent.getIntExtra("Id",0);
                Intent intent1 = new Intent(MainAdmin.this, MainDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent1);
            }
        });


    }


    //Gán DL vào listview
    public void initList(){
        TruyenArrayList = new ArrayList<>();
        databaseDocTruyen = new databasedoctruyen(this);

        Cursor cursor1 = databaseDocTruyen.getData2();

        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);

        adaptertruyen=new adapterTruyen(getApplicationContext(),TruyenArraylist);
        listView.setAdapter(adaptertruyen);
        cursor1.moveToFirst();
        cursor1.close();
    }
}