package com.example.beas;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity{

    private InterstitialAd mInterstitialAd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ADMOB
        MobileAds.initialize(this, initializationStatus -> {
        });

        //ADMOB Interstitial
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-1594974002597382/7193557755", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        //ADMOB BANNER
        AdView mAdView = findViewById(R.id.adView);
        mAdView.loadAd(adRequest);

        Button button = findViewById(R.id.bt_th);
        Button bt_ts = findViewById(R.id.bt_ts);
        Button bt_pustaka = findViewById(R.id.bt_pustaka);
        Button bt_profile = findViewById(R.id.bt_profil);

        button.setOnClickListener(view12 -> {
            Intent intent = new Intent(MainActivity.this, TebakActivity.class);
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            }
            startActivity(intent);
        });

        bt_ts.setOnClickListener(view1 -> {
            Intent it = new Intent(MainActivity.this, LihatSkorActivity.class);
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            }
            startActivity(it);
        });

        bt_pustaka.setOnClickListener(view1 -> {
            Intent i = new Intent(MainActivity.this, PustakaActivity.class);
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            }
            startActivity(i);
        });
        bt_profile.setOnClickListener(view1 -> {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            }
            startActivity(i);
        });
    }
}