package com.example.beas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class PustakaActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pustaka);

        int[] imageIds = new int[] { R.drawable.pustaka_ngalagena, R.drawable.pustaka_rarangken, R.drawable.pustaka_angka };

        mViewPager = findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageIds);
        mViewPager.setAdapter(adapter);
    }
}