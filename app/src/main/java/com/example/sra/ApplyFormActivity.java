package com.example.sra;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ApplyFormActivity extends AppCompatActivity {

    private static final String TAG = "ApplyFormActivity";

    LinearLayout uploadResumeLayout;
    EditText fullName, emailInput, phoneInput;
    MaterialButton submitButton;

    Uri resumeFileUri = null;

    // Modern File Picker launcher
    private final ActivityResultLauncher<String> filePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    resumeFileUri = uri;
                    Toast.makeText(this, "Resume Uploaded", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "ApplyFormActivity onCreate started");

        try {
            setContentView(R.layout.activity_apply_form);
            Log.d(TAG, "Layout inflated successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error inflating layout: " + e.getMessage());
            Toast.makeText(this, "Error loading form", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
            return;
        }

        try {
            uploadResumeLayout = findViewById(R.id.uploadResumeLayout);
            fullName = findViewById(R.id.fullName);
            emailInput = findViewById(R.id.emailInput);
            phoneInput = findViewById(R.id.phoneInput);
            submitButton = findViewById(R.id.submitButton);

            Log.d(TAG, "All views found successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error finding views: " + e.getMessage());
            Toast.makeText(this, "Error initializing form", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
            return;
        }

        uploadResumeLayout.setOnClickListener(v -> {
            // Open file picker for PDFs
            filePickerLauncher.launch("application/pdf");
        });

        submitButton.setOnClickListener(v -> validateAndSubmit());

        Log.d(TAG, "ApplyFormActivity created successfully");
    }

    private void validateAndSubmit() {
        String name = fullName.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();

        if (name.isEmpty() && email.isEmpty() && phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (resumeFileUri == null) {
            Toast.makeText(this, "Please upload your resume", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Application Submitted Successfully!", Toast.LENGTH_LONG).show();
    }
}