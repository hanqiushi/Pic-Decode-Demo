package com.example.spreadtrumqiushihan.picdemo;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    Handler mHandler;

    final long ANIMATION_DURATION = 1000;
    final long DELAY_TIME = 2500;

    TextView textViewAppName;
    TextView textViewSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textViewAppName = (TextView) findViewById(R.id.text_view_app_name);
        textViewSlogan = (TextView) findViewById(R.id.text_view_slogan);


        animateTextViews();
        /*mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        }, DELAY_TIME);*/
    }

    public void onAcceptCalled(View view) {
        openMainActivity();
    }

    private void openMainActivity() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // https://crazygui.wordpress.com/2010/09/05/high-quality-radial-gradient-in-android/
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Window window = getWindow();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(new int[]{
                ContextCompat.getColor(this, R.color.ff_splash_gradientColor),
                ContextCompat.getColor(this, R.color.ff_splash_background)
        });
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gradientDrawable.setGradientRadius(screenHeight);
        gradientDrawable.setGradientCenter(screenWidth / 2, screenHeight * 0.3f);

        window.setBackgroundDrawable(gradientDrawable);
        window.setFormat(PixelFormat.RGBA_8888);
    }

    private void animateTextViews() {
        final int TRANSLATE_Y = 100;
        textViewAppName.setAlpha(0f);
        textViewAppName.setTranslationY(TRANSLATE_Y);
        textViewSlogan.setAlpha(0f);
        ViewPropertyAnimatorCompat appNameAnimator = ViewCompat.animate(textViewAppName)
                .alpha(1)
                .setDuration(ANIMATION_DURATION)
                .translationYBy(-TRANSLATE_Y);
        ViewPropertyAnimatorCompat appSloganAnimator = ViewCompat.animate(textViewSlogan)
                .alpha(1)
                .setDuration(ANIMATION_DURATION);
        ViewPropertyAnimatorCompatSet animatorSet = new ViewPropertyAnimatorCompatSet();
        animatorSet.playSequentially(appNameAnimator, appSloganAnimator);
    }
}
