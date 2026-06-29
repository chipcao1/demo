package uef.edu.vn.model;

import java.sql.Date;

public class LecturerAssignment {
    private int assignmentID;
    private int topicID;
    private int lecturerID;
    private Date assignmentDate;
    
    // Thuộc tính hiển thị kết hợp (DTO pattern phục vụ View)
    private String topicCode;
    private String topicName;
    private String lecturerName;

    public LecturerAssignment() {}

    public int getAssignmentID() { return assignmentID; }
    public void setAssignmentID(int assignmentID) { this.assignmentID = assignmentID; }
    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public int getLecturerID() { return lecturerID; }
    public void setLecturerID(int lecturerID) { this.lecturerID = lecturerID; }
    public Date getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(Date assignmentDate) { this.assignmentDate = assignmentDate; }
    public String getTopicCode() { return topicCode; }
    public void setTopicCode(String topicCode) { this.topicCode = topicCode; }
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
}