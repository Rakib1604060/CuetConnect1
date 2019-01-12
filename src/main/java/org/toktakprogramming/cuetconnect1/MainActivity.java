package org.toktakprogramming.cuetconnect1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("loged",false)){
            Intent intent =new Intent(MainActivity.this,login.class);
            startActivity(intent);
            finish();
        }



        BottomNavigationView bottomNavigationView=findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new FragmentHome())
                .addToBackStack(null)
                .commit();

  }
     private  BottomNavigationView.OnNavigationItemSelectedListener navListener=
             new BottomNavigationView.OnNavigationItemSelectedListener() {
                 @Override
                 public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                     android.support.v4.app.Fragment selectedFragment=null;
                     switch (item.getItemId()){
                         case R.id.navigation_home:
                             selectedFragment=new FragmentHome();
                             break;
                         case R.id.navigation_menu:
                             selectedFragment=new FragmentMenu();
                             break;
                         case R.id.navigation_friend:
                             selectedFragment=new FragmentFriends();
                             break;
                         case R.id.navigation_profile:
                             selectedFragment=new FragmentProfile();
                             break;

                 }
                     getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,selectedFragment)
                             .commit();
                     return true;
                 }
             };
}





