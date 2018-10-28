package com.example.jake.university;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.jake.university.fragments.FragmentExamsAndArrears;
import com.example.jake.university.fragments.FragmentNews;
import com.example.jake.university.fragments.FragmentPayment;
import com.example.jake.university.fragments.FragmentProfile;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setCheckedItem(R.id.nav_news);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentNews()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else
            {
            /*new AlertDialog.Builder(this)
                    .setTitle("Выйти из приложения?")
                    .setMessage("Вы действительно хотите выйти?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //SomeActivity - имя класса Activity для которой переопределяем onBackPressed();
                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();*/
            super.onBackPressed();
            }
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.nav_news:
                ftrans.replace(R.id.fragment_container, new FragmentNews()).addToBackStack(null).commit();
                break;
            case R.id.nav_profile:
                ftrans.replace(R.id.fragment_container, new FragmentProfile()).addToBackStack(null).commit();
                break;
            case R.id.nav_arrears:
                ftrans.replace(R.id.fragment_container, new FragmentExamsAndArrears()).addToBackStack(null).commit();
                break;
            case R.id.nav_learn_action:
                break;
            case R.id.nav_achievements:
                break;
            case R.id.nav_payment:
                ftrans.replace(R.id.fragment_container, new FragmentPayment()).addToBackStack(null).commit();
                break;
            case R.id.nav_scholarships:
                break;
            case R.id.nav_literature:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
