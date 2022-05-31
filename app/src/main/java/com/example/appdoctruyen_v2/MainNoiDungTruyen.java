package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainNoiDungTruyen extends AppCompatActivity {

    TextView txtTenTruyen,txtNoidung;
    Button btnDanhGia, btnYeuThich, btnChiaSe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung_truyen);

        AnhXa();


        txtNoidung = findViewById(R.id.NoiDung);
        txtTenTruyen = findViewById(R.id.TenTruyen);

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNoiDungTruyen.this,MainDanhGia.class);
                intent.putExtra("tentruyen",txtTenTruyen.getText().toString());
                startActivity(intent);
            }
        });

        btnChiaSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNoiDungTruyen.this,MainChiaSe.class);
                startActivity(intent);
            }
        });

        btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainNoiDungTruyen.this,"Đã thêm vào danh sách yêu thích",Toast.LENGTH_SHORT).show();
                Log.e("Yêu thích: ","Đã thêm vào danh sách yêu thích");
            }
        });




        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");

        txtTenTruyen.setText(tenTruyen);
        txtNoidung.setText(noidung);

        //Cuộn textview
        txtNoidung.setMovementMethod(new ScrollingMovementMethod());

    }

    private void AnhXa() {
        txtTenTruyen = findViewById(R.id.TenTruyen);
        txtNoidung = findViewById(R.id.NoiDung);
        btnDanhGia = findViewById(R.id.buttonDanhgia);
        btnYeuThich = findViewById(R.id.buttonYeuthich);
        btnChiaSe = findViewById(R.id.buttonChiase);
    }
}