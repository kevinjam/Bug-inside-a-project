package com.kevinjanvier.slauversion1.Main;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kevinjanvier.slauversion1.IntroductionScreen.IntroductionViewPager;
import com.kevinjanvier.slauversion1.R;

import android.os.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class welcomeScreen extends FragmentActivity {

    @Bind(R.id.welcometxt)
    TextView welcomeslau;
    private Typeface font;

    private Handler handler;
    private Runnable callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      getWindow().setBackgroundDrawableResource(R.drawable.slau);
        getWindow().setBackgroundDrawableResource(R.drawable.btn_selector);
        setContentView(R.layout.welcome_screen);

        ButterKnife.bind(this);

        font = Typeface.createFromAsset(getAssets(), "fonts/Android.ttf");
        welcomeslau.setTypeface(font);

        handler = new Handler();
        callback = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), IntroductionViewPager.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        handler.postDelayed(callback, 2000);


    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(callback);
    }
}
