package uef.edu.vn.model;

import java.sql.Date;

public class TopicRegistration {
    private int registrationID;
    private int topicID;
    private int studentID;
    private String participationRole; // 'Team Leader', 'Team Member'
    private String registrationStatus; // 'Pending', 'Approved', 'Rejected'
    private Date registrationDate;

    // Thuộc tính mở rộng để tìm kiếm và hiển thị
    private String topicName;
    private String studentName;
    private String studentCode;

    public TopicRegistration() {}

    public int getRegistrationID() { return registrationID; }
    public void setRegistrationID(int registrationID) { this.registrationID = registrationID; }
    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public String getParticipationRole() { return participationRole; }
    public void setParticipationRole(String participationRole) { this.participationRole = participationRole; }
    public String getRegistrationStatus() { return registrationStatus; }
    public void setRegistrationStatus(String registrationStatus) { this.registrationStatus = registrationStatus; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
}