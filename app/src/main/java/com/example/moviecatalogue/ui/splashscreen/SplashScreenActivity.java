package com.example.moviecatalogue.ui.splashscreen;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.ui.home.HomeActivity;
import com.example.moviecatalogue.ui.until.Const;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){
            public void run(){
                    try {
                        sleep(Const.DELAY_SPLASH_SCREEN);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        finish();
                    }
            }
        };
        thread.start();
    }
}