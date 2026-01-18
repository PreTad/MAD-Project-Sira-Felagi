package com.example.sra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class JobDetailActivity extends AppCompatActivity {

    private static final String TAG = "JobDetailActivity";

    private MaterialButton applyButton;
    private TextView viewCompanyDetails;

    private TextView companyTextView, positionTextView, salaryTextView, locationTextView;
    private ImageView companyLogo;

    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        initializeViews();
        receiveJobData();      // Get data from intent
        populateJobDetails();   // Update UI
        setupClickListeners();
    }

    private void initializeViews() {
        applyButton = findViewById(R.id.applyButton);
        viewCompanyDetails = findViewById(R.id.companyDetailsLink);

        companyTextView = findViewById(R.id.companyTextView);
        positionTextView = findViewById(R.id.positionTextView);
        salaryTextView = findViewById(R.id.salaryTextView);
        locationTextView = findViewById(R.id.locationTextView);
        companyLogo = findViewById(R.id.companyLogo);
    }

    private void receiveJobData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("jobObject")) {
            job = (Job) intent.getSerializableExtra("jobObject");
        }
    }

    private void populateJobDetails() {
        if (job != null) {
            companyTextView.setText(job.getCompanyName());
            positionTextView.setText(job.getPosition());
            salaryTextView.setText(job.getSalary());
            locationTextView.setText(job.getLocation());
            companyLogo.setImageResource(job.getCompanyLogo());
        } else {
            Log.e(TAG, "Job object is null!");
        }
    }

    private void setupClickListeners() {
        applyButton.setOnClickListener(v -> handleApplyJob());

        viewCompanyDetails.setOnClickListener(v -> {
            if (getIntent() != null) {
                Job job = (Job) getIntent().getSerializableExtra("jobObject");
                if (job != null) {
                    Intent intent = new Intent(JobDetailActivity.this, CompanyDetail.class);
                    intent.putExtra("jobObject", job); // pass the job object
                    startActivity(intent);
                }
            }
        });
    }


    private void handleApplyJob() {
        try {
            Intent intent = new Intent(JobDetailActivity.this, ApplyFormActivity.class);
            if (job != null) {
                intent.putExtra("JOB_TITLE", job.getPosition());
                intent.putExtra("COMPANY_NAME", job.getCompanyName());
            }
            startActivity(intent);
            Toast.makeText(this, "Opening application form...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Error starting ApplyFormActivity: " + e.getMessage(), e);
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void handleViewCompanyDetails() {
        try {
            Intent intent = new Intent(JobDetailActivity.this, CompanyDetail.class);
            startActivity(intent);
            Toast.makeText(this, "Opening company details...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Error starting CompanyDetail: " + e.getMessage(), e);
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
