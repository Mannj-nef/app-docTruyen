package com.example.appdoctruyen_v2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.appdoctruyen_v2.adapter.adapterTruyen;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.TaiKhoan;
import com.example.appdoctruyen_v2.model.Truyen;
import com.example.appdoctruyen_v2.model.chuyenmuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import  com.example.appdoctruyen_v2.database.*;
import java.util.ArrayList;
import  com.example.appdoctruyen_v2.adapter.*;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    FloatingActionButton fab;
    RecyclerView listViewNew2,listViewNew;


    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;

    ArrayList<Truyen> TruyenArraylist2;

    com.example.appdoctruyen_v2.adapter.adapterTruyenV2 adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    com.example.appdoctruyen_v2.database.databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databasedoctruyen=new databasedoctruyen(getContext());
        fab = getActivity().findViewById(R.id.fab_btn);
        Intent intentpq = getActivity().getIntent();
        int i= intentpq.getIntExtra("phanq",0);
        int idd=intentpq.getIntExtra("idd",0);
        email=intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MainTimKiem.class);
                startActivity(intent);
            }
        });

        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionBar() {
       // setSupportActionBar(toolbar);

      //  getContext().setDisplayHomeAsUpEnabled(true);

//        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

      //  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {
            //    drawerLayout.openDrawer(GravityCompat.START);
            //}
        //});


    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://photo-cms-plo.zadn.vn/w800/Uploaded/2021/wopsvun/2020_09_03/doaremon_wdyw.jpg");
        mangquangcao.add("https://techtimes.vn/wp-content/uploads/2020/10/conan.jpg");
        mangquangcao.add("https://www.fullphim.net/static/5fe2d564b3fa6403ffa11d1c/6090030698d96c54c4b714ec_one-piece-3.jpg");
        mangquangcao.add("https://fado.vn/blog/wp-content/uploads/2020/09/vu-tru-dragon-ball.jpg");

        for (int i=0; i<mangquangcao.size();i++)
        {
            ImageView imageView=new ImageView(getContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(4000);

        viewFlipper.setAutoStart(true);

        Animation animation_slide_in= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa()
    {
       // toolbar = getActivity().findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper= getActivity().findViewById(R.id.viewflipper);
        listViewNew= getActivity().findViewById(R.id.listviewNew);
        listViewNew2= getActivity().findViewById(R.id.listviewNew2);
       // listView= getActivity().findViewById(R.id.listviewmanhinhchinh);
        //listviewThongtin=getActivity().findViewById(R.id.listviewThongTin);
        //navigationView= getActivity().findViewById(R.id.navigationview);
        //drawerLayout= getActivity().findViewById(R.id.drawerlayout);

        TruyenArraylist=new ArrayList<>();

        Cursor cursor1 = databasedoctruyen.getData1();
        Cursor cursor2 = databasedoctruyen.getData2();
        while (cursor1.moveToNext())
        {
            int id=cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int id_tk=cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));


        }
        TruyenArraylist2 = new ArrayList<>();
        while (cursor2.moveToNext())
        {
            int id=cursor2.getInt(0);
            String tentruyen=cursor2.getString(1);
            String noidung=cursor2.getString(2);
            String anh=cursor2.getString(3);
            int id_tk=cursor2.getInt(4);

            TruyenArraylist2.add(new Truyen(id,tentruyen,noidung,anh,id_tk));


        }
        listViewNew.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew.setLayoutManager(gridLayoutManager);

        adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist);
        listViewNew.setAdapter(adapterTruyen);
        //LISTVIEW2
        listViewNew2.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        gridLayoutManager2.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew2.setLayoutManager(gridLayoutManager2);

        adapterTruyen=new adapterTruyenV2(getContext(),TruyenArraylist2);
        listViewNew2.setAdapter(adapterTruyen);


        cursor1.moveToFirst();
        cursor1.close();
        cursor2.moveToFirst();
        cursor2.close();

        taiKhoanArrayList=new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Tăng bố cục cho fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
