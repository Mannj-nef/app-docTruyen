package com.example.appdoctruyen_v2;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen_v2.adapter.adapterTruyen;
import com.example.appdoctruyen_v2.adapter.adapterTruyenV2;
import com.example.appdoctruyen_v2.adapter.adapterTruyenYeuThich;
import com.example.appdoctruyen_v2.database.databasedoctruyen;
import com.example.appdoctruyen_v2.model.Truyen;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class YeuThichFragment extends Fragment {
    RecyclerView listViewNew;

    ArrayList<Truyen> TruyenArraylist;

    com.example.appdoctruyen_v2.adapter.adapterTruyenYeuThich adapterTruyen;



    //databasedoctruyen databasedoctruyen;
    databasedoctruyen databaseDocTruyen;

    public YeuThichFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        databaseDocTruyen=new databasedoctruyen(getContext());
        listViewNew = getView().findViewById(R.id.listviewyeuthich);




        AnhXa();



       /* listViewNew.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDeleteYeuThic(position);
                return false;
            }


        });*/


    }

    private void DialogDeleteYeuThic(int position) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(getContext());

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
                int idtruyen = TruyenArraylist.get(position).getID();
                //Xóa trong SQL
              //  databaseDocTruyen.Delete(idtruyen);
                //Cập nhật lại listview
                //Intent intent = new Intent(getContext(), MainYeuThich.class);
                //getActivity().finish();
                //startActivity(intent);

                //startActivity(intent);
                Toast.makeText(getContext(),"Bỏ yêu thích thành công",Toast.LENGTH_SHORT).show();
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


    private void AnhXa()
    {
        //toolbar=findViewById(R.id.toolbarmanhinhchinh);
        listViewNew=getView().findViewById(R.id.listviewyeuthich);

        TruyenArraylist=new ArrayList<>();

        Cursor cursor1 = databaseDocTruyen.getData4();
        while (cursor1.moveToNext())
        {
            int id=cursor1.getInt(0);
            String tentruyen=cursor1.getString(1);
            String noidung=cursor1.getString(2);
            String anh=cursor1.getString(3);
            int id_tk=cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listViewNew.setLayoutManager(gridLayoutManager);

        adapterTruyen=new adapterTruyenYeuThich(getContext(),TruyenArraylist);
        listViewNew.setAdapter(adapterTruyen);
        cursor1.moveToFirst();
        cursor1.close();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yeuthich, container, false);
    }

}
