package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hospital.Models.PersonalDetailModel;
import com.example.hospital.databinding.ActivityPersonalDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalDetail extends AppCompatActivity {

    private DatabaseReference reference, referenceTwo;
    private ActivityPersonalDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.submitPersonalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            insertData();
            }
        });
    }

    private void insertData() {

        String fathername = binding.fatherName.getEditableText().toString();
        String mothername = binding.motherName.getEditableText().toString();
        String fatherMobileNumber = binding.fatherMobileNumber.getEditableText().toString();
        String motherMobileNumber = binding.motherMobileNumber.getEditableText().toString();
        String yourMobileNumber = binding.YourMobileNummber.getEditableText().toString();
        String aadharCard = binding.aadharCard.getEditableText().toString();
        String panCard = binding.panCard.getEditableText().toString();
        String drivingLicence = binding.drivingLicence.getEditableText().toString();
        reference = FirebaseDatabase.getInstance().getReference("PersonalDetail").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        PersonalDetailModel personalDetailModel = new PersonalDetailModel(fathername, mothername,fatherMobileNumber,motherMobileNumber,yourMobileNumber,
                aadharCard,panCard,drivingLicence);

        reference.setValue(personalDetailModel);
        referenceTwo.setValue(personalDetailModel);

    }
}