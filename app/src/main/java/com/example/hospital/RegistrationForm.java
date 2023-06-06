package com.example.hospital;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hospital.Models.ProfileModel;
import com.example.hospital.databinding.ActivityRegistrationFormBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.InputStream;
import java.util.List;

public class RegistrationForm extends AppCompatActivity {
    private ActivityRegistrationFormBinding binding;
    private Uri filepath;
    private Bitmap bitmap;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase rootNode;
    private DatabaseReference referenceTwo;
    private final int REQUEST_CODE = 207;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseDatabase.getInstance().goOnline();


        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = sharedPreferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(RegistrationForm.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }

        binding.selectImage.setOnClickListener(v -> Dexter.withActivity(RegistrationForm.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), REQUEST_CODE);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check());


        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.Name.getEditableText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Your Crop Name", Toast.LENGTH_SHORT).show();
                } else if (binding.MobileNumber.getEditableText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Your MobileNumber", Toast.LENGTH_SHORT).show();
                } else if (binding.MobileNumberTwo.getEditableText().toString().isEmpty() || binding.emailId.getEditableText().toString().isEmpty()) {

                    upload();
                } else {
                    upload();

                }
            }
        });


    }
    private void nextActivity() {
        Intent intent = new Intent(RegistrationForm.this, MainActivity.class);
        startActivity(intent);
    }


    private void upload() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        String Name = binding.Name.getEditableText().toString();
        String MobileNumber = binding.MobileNumber.getEditableText().toString();
        String MobileNumberTwo = binding.MobileNumberTwo.getEditableText().toString();
        String emailId = binding.emailId.getEditableText().toString();

        String Address = binding.Address.getText().toString();
        String pincode = binding.pincode.getText().toString();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("UserProfile").child("" + MobileNumber);


        storageReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        rootNode = FirebaseDatabase.getInstance();
//                        reference = rootNode.getReference("Patient").child(""+MobileNumber);
                        referenceTwo = rootNode.getReference("User");
                        ProfileModel profileModel = new ProfileModel(Name, MobileNumber, MobileNumberTwo, emailId, Address,pincode, uri.toString());
                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
//                        reference.setValue(profileModel);
                        referenceTwo.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(profileModel);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int percentage = (int) ((100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount());
                dialog.setMessage("Uploading " + (int) percentage + " %...");
                if (percentage == 100) {
                    dialog.dismiss();
                    nextActivity();
                    Toast.makeText(RegistrationForm.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                binding.Image.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}