package com.deskconn.androidlocaltization;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Locale locale1 = new Locale(getStringFromSharedPreferences("language"));
        Locale.setDefault(locale1);
        Configuration config1 = new Configuration();
        config1.locale = locale1;
        getBaseContext().getResources().updateConfiguration(config1,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(view -> {
            String languageToLoad;
            if (getStringFromSharedPreferences("language").equals("en")) {
                languageToLoad = "ur";
            } else {
                languageToLoad = "en";
            }
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            saveStringToSharedPreferences("language", languageToLoad);
            recreate();
        });
    }


    public SharedPreferences getPreferenceManager() {
        return getSharedPreferences("shared_prefs", MODE_PRIVATE);
    }


    public void saveStringToSharedPreferences(String key, String value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getStringFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(key, "en");
    }
}