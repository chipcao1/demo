package uef.edu.vn.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Lecturer {
    private int lecturerID;
    
    @NotBlank(message = "Lecturer code is required")
    @Size(max = 20, message = "Max 20 characters allowed")
    private String lecturerCode;
    
    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String fullName;
    
    @NotBlank(message = "Faculty is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String faculty;
    
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Max 100 characters allowed")
    private String email;
    
    @Size(max = 20, message = "Max 20 characters allowed")
    private String phoneNumber;

    public Lecturer() {}

    public Lecturer(int lecturerID, String lecturerCode, String fullName, String faculty, String email, String phoneNumber) {
        this.lecturerID = lecturerID;
        this.lecturerCode = lecturerCode;
        this.fullName = fullName;
        this.faculty = faculty;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getLecturerID() { return lecturerID; }
    public void setLecturerID(int lecturerID) { this.lecturerID = lecturerID; }
    public String getLecturerCode() { return lecturerCode; }
    public void setLecturerCode(String lecturerCode) { this.lecturerCode = lecturerCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}