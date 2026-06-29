package uef.edu.vn.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Topic {
    private int topicID;

    @NotBlank(message = "Topic code cannot be empty")
    @Size(max = 20, message = "Topic code max 20 characters")
    private String topicCode;

    @NotBlank(message = "Topic name cannot be empty")
    private String topicName;

    @NotBlank(message = "Research field cannot be empty")
    private String researchField;

    private String description;

    @Min(value = 2000, message = "Invalid start year")
    private int startYear;

    @Min(value = 2000, message = "Invalid end year")
    private int endYear;

    @Min(value = 1, message = "Max students must be at least 1")
    private int maxStudents;

    private String status;

    public Topic() {}

    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public String getTopicCode() { return topicCode; }
    public void setTopicCode(String topicCode) { this.topicCode = topicCode; }
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
    public String getResearchField() { return researchField; }
    public void setResearchField(String researchField) { this.researchField = researchField; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) { this.startYear = startYear; }
    public int getEndYear() { return endYear; }
    public void setEndYear(int endYear) { this.endYear = endYear; }
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}