package com.martirosov.sergey.mycriminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    List<Crime> crimes;
    ViewPager viewPager;
    public static final String EXTRA_CRIME_ID = "EXTRA_CRIME_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        crimes = CrimeLab.get(this).getCrimes();
        viewPager = findViewById(R.id.crime_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = crimes.get(i);
                return  CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getId().equals(id)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
        viewPager.setOffscreenPageLimit(5);

    }

    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, id);
        return  intent;
    }
}
