package com.example.sra;

// Job.java
import java.io.Serializable;

public class Job implements Serializable {
    private String companyName;
    private String position;
    private String salary;
    private String location;
    private int companyLogo; // drawable resource id

    public Job(String companyName, String position, String salary, String location, int companyLogo) {
        this.companyName = companyName;
        this.position = position;
        this.salary = salary;
        this.location = location;
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() { return companyName; }
    public String getPosition() { return position; }
    public String getSalary() { return salary; }
    public String getLocation() { return location; }
    public int getCompanyLogo() { return companyLogo; }
}

