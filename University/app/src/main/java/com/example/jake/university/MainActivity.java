package com.example.jake.university;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jake.university.exams.FragmentExamsAndArrears;
import com.example.jake.university.news.FragmentNews;
import com.example.jake.university.notifications.ScheduleAlarms;
import com.example.jake.university.payment.FragmentPayment;
import com.example.jake.university.profile.FragmentProfile;
import com.example.jake.university.profile.Singleton;
import com.example.jake.university.ranked.FragmentRanked;
import com.example.jake.university.scholarships.FragmentScholarships;
import com.example.jake.university.timetable.FragmentLecturerSchedule;
import com.example.jake.university.timetable.FragmentTimetable;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private FragmentTransaction ftrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerlayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        TextView tvHeaderName = (TextView) headView.findViewById(R.id.tvHeaderName);
        try {
            tvHeaderName.setText(Singleton.getInstance("").getProfileInfo().getFIO());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ScheduleAlarms.getInstance().startAlarmBundle(this);
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
        }
        else {
            if(getSupportFragmentManager().getBackStackEntryCount()==0) {
                new AlertDialog.Builder(this)
                        .setTitle("Выйти из приложения?")
                        .setMessage("Вы действительно хотите выйти?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                //SomeActivity - имя класса Activity для которой переопределяем onBackPressed();
                                MainActivity.super.onBackPressed();
                            }
                        }).create().show();
            }
            else {
                super.onBackPressed();
            }
        }
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        ftrans = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.nav_news:
                ftrans.replace(R.id.fragment_container, new FragmentNews()).addToBackStack(null).commit();
                break;
            case R.id.nav_profile:
                try {
                    ftrans.replace(R.id.fragment_container, new FragmentProfile()).addToBackStack(null).commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.nav_arrears:
                ftrans.replace(R.id.fragment_container, new FragmentExamsAndArrears()).addToBackStack(null).commit();
                break;
            case R.id.nav_payment:
                ftrans.replace(R.id.fragment_container, new FragmentPayment()).addToBackStack(null).commit();
                break;
            case R.id.nav_scholarships:
                try {
                    ftrans.replace(R.id.fragment_container, new FragmentScholarships()).addToBackStack(null).commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.nav_ranked:
                try {
                    ftrans.replace(R.id.fragment_container, new FragmentRanked()).addToBackStack(null).commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.nav_timetable:
                try {
                    ftrans.replace(R.id.fragment_container, new FragmentTimetable()).addToBackStack(null).commit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.nav_timetable_lec:
                ftrans.replace(R.id.fragment_container, new FragmentLecturerSchedule()).addToBackStack(null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
