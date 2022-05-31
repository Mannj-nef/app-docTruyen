package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.main.MainAdmin;

public class MainCapNhat extends AppCompatActivity {
    databasedoctruyen databaseDocTruyen;

    // TextView txtNoidung;
    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cap_nhat);
        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnCapNhat = findViewById(R.id.buttonCapNhat);
        edtAnh = findViewById(R.id.dbimg);


        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");
        String img = intent.getStringExtra("imgtruyen");
        edtTieuDe.setText(tenTruyen);
        edtNoiDung.setText(noidung);
        edtAnh.setText(img);

        databaseDocTruyen = new databasedoctruyen(this);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();
                Truyen truyen = CreatTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(MainCapNhat.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseDocTruyen.AddTruyen(truyen);
                    Intent intent = new Intent(MainCapNhat.this, MainAdmin.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(MainCapNhat.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    Log.e("Cập nhật truyện : ","Thành công");
                }
                //Cuộn textview
                edtNoiDung.setMovementMethod(new ScrollingMovementMethod());
            }
        });


    }

    private Truyen CreatTruyen(){
        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);
        Truyen truyen = new Truyen(tentruyen,noidung,img,id);
        return truyen;
    }




}