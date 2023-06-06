package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.example.hospital.databinding.ActivityMainBinding;
import com.example.hospital.fragments.EmerencyFragment;
import com.example.hospital.fragments.HomeFragment;
import com.example.hospital.fragments.ProfileFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int selectedTab = 1;
    //Map api key
    //AIzaSyD3sfPrT3DmzdEhYC8s_fGCB-2mUFc2G64

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.frame_layout, HomeFragment.class,null)
                .commit();
        binding.Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 1) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                                    .replace(R.id.frame_layout, HomeFragment.class,null)
                                            .commit();
                    binding.noticationTxt.setVisibility(View.GONE);
                    binding.profileText.setVisibility(View.GONE);

                    binding.notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.profile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.HomeText.setVisibility(View.VISIBLE);
                    binding.HomeImage.setImageResource(R.drawable.home);
                    binding.Home.setBackgroundResource(R.drawable.roundback);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    binding.Home.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }
            }
        });

        binding.notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 2) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.frame_layout, EmerencyFragment.class,null)
                            .commit();


                    binding.HomeText.setVisibility(View.GONE);
                    binding.profileText.setVisibility(View.GONE);

                    binding.HomeImage.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.profile.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.noticationTxt.setVisibility(View.VISIBLE);
                    binding.notificatonImage.setImageResource(R.drawable.baseline_location_on_24);

                    binding.notificationLayout.setBackgroundResource(R.drawable.roundback);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0F, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    binding.notificationLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }
            }
        });
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 3) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.frame_layout, ProfileFragment.class,null)
                            .commit();
                    binding.noticationTxt.setVisibility(View.GONE);
                    binding.HomeText.setVisibility(View.GONE);

                    binding.notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    binding.Home.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    binding.profileText.setVisibility(View.VISIBLE);
                    binding.profileImage.setImageResource(R.drawable.baseline_settings_24);

                    binding.profile.setBackgroundResource(R.drawable.roundback);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0F, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    binding.profile.startAnimation(scaleAnimation);

                    selectedTab = 3;
                }
            }
        });
    }
}