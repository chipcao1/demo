package uef.edu.vn.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Student {
    private int studentID;
    
    @NotBlank(message = "Student code is required")
    @Size(max = 20, message = "Max 20 characters allowed")
    private String studentCode;
    
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String fullName;
    
    @NotBlank(message = "Major is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String major;
    
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String email;
    
    @Size(max = 20, message = "Max 20 characters allowed")
    private String phoneNumber;

    public Student() {}

    public Student(int studentID, String studentCode, String fullName, String major, String email, String phoneNumber) {
        this.studentID = studentID;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.major = major;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}