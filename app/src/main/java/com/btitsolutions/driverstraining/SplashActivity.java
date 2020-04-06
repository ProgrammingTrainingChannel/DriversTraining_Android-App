package com.btitsolutions.driverstraining;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.btitsolutions.driverstraining.Utilities.Initializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    SetupProperLanguage(context);

                    finish();
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    public void SetupProperLanguage(Context context) {
        SharedPreferences GeneralSettings = getSharedPreferences("GeneralSettings", MODE_PRIVATE);
        String languageSetting = GeneralSettings.getString("Language", "English");
        if(languageSetting.equals("Amharic"))
        {
            String language_code = "am";
            Resources res = getApplicationContext().getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.setLocale(new Locale(language_code.toLowerCase())); // API 17+ only.
            // Use conf.locale = new Locale(...) if targeting lower versions
            res.updateConfiguration(conf, dm);
        }
        else
        {
            String language_code = "en-US";
            Resources res = getApplicationContext().getResources();
            // Change locale settings in the app.
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.setLocale(new Locale(language_code.toLowerCase())); // API 17+ only.
            // Use conf.locale = new Locale(...) if targeting lower versions
            res.updateConfiguration(conf, dm);
        }
    }
}
