package com.example.appdoctruyen_v2.model.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appdoctruyen_v2.R;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongTin = findViewById(R.id.textviewthongtin);

        String thongtin = " Ứng dụng được phát hành bởi Nhóm 7 \n" +
                " Ứng dụng vẫn còn những thiếu sót cần đưược phát triển sau. \n";
        txtThongTin.setText(thongtin);
    }
}