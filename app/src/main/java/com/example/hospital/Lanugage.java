package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.hospital.databinding.ActivitySelectLanguageBinding;

import java.util.Locale;

public class Lanugage extends AppCompatActivity {


    private ActivitySelectLanguageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadLocale();

        binding.English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocal(" ");
                recreate();
            }
        });
        binding.Marathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocal("mr");
                recreate();
            }
        });
    }

    private void setLocal(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getBaseContext().getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("app_lang", language);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("app_lang", "");
        setLocal(language);
    }
}