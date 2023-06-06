package com.example.hospital.ProfileActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.hospital.R;
import com.example.hospital.databinding.ActivitySeeProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeeProfile extends AppCompatActivity {

    private ActivitySeeProfileBinding binding;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profile();
        binding.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeeProfile.this, EditProfile.class);
                startActivity(intent);
            }
        });
    }
    private void profile() {

        reference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    binding.profileProgress.setVisibility(View.GONE);
                    String getfarmerName = snapshot.child("name").getValue().toString();
                    binding.customerName.setText(getfarmerName);
                    String profileImage = snapshot.child("image").getValue().toString();
                    Glide.with(getApplicationContext()).load(profileImage).into(binding.getCustomerProfileImage);
                    String getfarmerMobileNumber = snapshot.child("mobileNumber").getValue().toString();
                    binding.customerMobileNumber.setText(getfarmerMobileNumber);
                    String getfarmerMobileNumberTwo = snapshot.child("mobileNumberTwo").getValue().toString();
                    binding.customerMobileNumberTwo.setText(getfarmerMobileNumberTwo);
                    String getfarmerEmail = snapshot.child("emailId").getValue().toString();
                    binding.customerEmail.setText(getfarmerEmail);
                    String getSatbaraNo = snapshot.child("address").getValue().toString();
                    binding.Address.setText(getSatbaraNo);
                    String getPincode = snapshot.child("pincode").getValue().toString();
                    binding.PinCode.setText(getPincode);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}