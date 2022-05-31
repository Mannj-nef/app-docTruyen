package com.example.appdoctruyen_v2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.appdoctruyen_v2.MainActivity.email;
import static com.example.appdoctruyen_v2.MainActivity.tentaikhoan;


public class AccountFragmment extends Fragment {
    private View view;
    private TextView tvtaikhoan,tvgmail;
    private Button btndangxuat;


    public AccountFragmment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_account_fragmment, container, false);

        //ánh xạ
        tvtaikhoan = view.findViewById(R.id.tvtentaikhoan);
        tvgmail = view.findViewById(R.id.tvgmail);
        btndangxuat = view.findViewById(R.id.btndangxuat);
        tvtaikhoan.setText(tentaikhoan);
        tvgmail.setText(email);

        btndangxuat.setOnClickListener(view ->{
            Intent intent = new Intent(getContext(),MainDangNhap.class);
            startActivity(intent);
        });
        return view;
    }
}