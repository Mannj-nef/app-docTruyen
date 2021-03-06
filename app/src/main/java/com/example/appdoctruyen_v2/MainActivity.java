package com.example.appdoctruyen_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.appdoctruyen_v2.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    public  static int check_admin;
    public static String tentaikhoan;
    public static String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intentpq = getIntent();
        check_admin= intentpq.getIntExtra("phanq",0);
        email=intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        init();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    sNavigationDrawer.setAppbarTitleTV("Home");
                    return true;
                case R.id.navigation_profile:
                    fragment = new AccountFragmment();
                    loadFragment(fragment);
                    sNavigationDrawer.setAppbarTitleTV("Account");
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void init(){

        //Inside onCreate()

        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        //Creating a list of menu Items

        List<MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        menuItems.add(new MenuItem("Home",R.drawable.news_bg));
        menuItems.add(new MenuItem("????ng b??i",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Truy???n ???? y??u th??ch",R.drawable.message_bg));
        menuItems.add(new MenuItem("T???t c??? truy???n",R.drawable.music_bg));
        menuItems.add(new MenuItem("Th??ng tin app",R.drawable.feed_bg));
        menuItems.add(new MenuItem("????ng xu???t",R.drawable.news_bg));

        //then add them to navigation drawer

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }



        //Tr??nh nghe ????? x??? l?? nh???p chu???t v??o m???c menu. N?? tr??? v??? v??? tr?? c???a m???c menu ???????c nh???p v??o. D???a v??o ????, b???n c?? th??? chuy???n ?????i gi???a c??c  fragments.

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        fragmentClass = HomeFragment.class;
                        break;
                    }
                    case 1:{
                        fragmentClass = DangBaiFragment.class;
                        break;
                    }
                    case 2:{
                        fragmentClass = YeuThichFragment.class;
                        break;
                    }
                    case 3:{
                        fragmentClass = TatcatruyenFragment.class;
                        break;
                    }
                    case 4:{
                        fragmentClass = thongtinappFragment.class;
                        break;
                    }
                    case 5:{
                        finish();
                        break;
                    }

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });
    }
}