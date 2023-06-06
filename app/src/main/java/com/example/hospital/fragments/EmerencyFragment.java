package com.example.hospital.fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.hospital.MapsActivity;
import com.example.hospital.R;
import com.example.hospital.databinding.FragmentEmerencyBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EmerencyFragment extends Fragment {

    private FragmentEmerencyBinding binding;
    private DatabaseReference reference;

    public EmerencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmerencyBinding.inflate(getLayoutInflater());
        getProfileData();
        binding.Hii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
                } else {
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isvisible = binding.showLayout.getVisibility();
                if (isvisible == View.VISIBLE) {
                    binding.showLayout.setVisibility(View.GONE);
//                    HI.setText("Hiiii");
                } else {
//                    HI.setText("Bye");
                    binding.showLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.GoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }


    private void getProfileData() {
        reference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String getfarmerName = snapshot.child("name").getValue().toString();
                    binding.name.setText(getfarmerName);
                    String profileImage = snapshot.child("image").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(binding.profileImageView);
                    String getfarmerMobileNumber = snapshot.child("mobileNumber").getValue().toString();
                    binding.mobilenumber.setText(getfarmerMobileNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}