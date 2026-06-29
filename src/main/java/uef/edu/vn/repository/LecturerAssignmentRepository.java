package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.LecturerAssignment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LecturerAssignmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private LecturerAssignment mapRow(ResultSet rs, int rowNum) throws SQLException {
        LecturerAssignment assignment = new LecturerAssignment();
        assignment.setAssignmentID(rs.getInt("AssignmentID"));
        assignment.setTopicID(rs.getInt("TopicID"));
        assignment.setLecturerID(rs.getInt("LecturerID"));
        assignment.setAssignmentDate(rs.getDate("AssignmentDate"));
        
        assignment.setTopicCode(rs.getString("TopicCode"));
        assignment.setTopicName(rs.getString("TopicName"));
        assignment.setLecturerName(rs.getString("LecturerName"));
        return assignment;
    }

    public List<LecturerAssignment> findAll() {
        String sql = "SELECT la.*, t.TopicCode, t.TopicName, l.FullName AS LecturerName " +
                     "FROM LecturerAssignments la " +
                     "JOIN Topics t ON la.TopicID = t.TopicID " +
                     "JOIN Lecturers l ON la.LecturerID = l.LecturerID";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    // SỬA: Dùng query truyền danh sách thay vì queryForObject để an toàn tuyệt đối
    public LecturerAssignment findById(int id) {
        String sql = "SELECT la.*, t.TopicCode, t.TopicName, l.FullName AS LecturerName " +
                     "FROM LecturerAssignments la " +
                     "JOIN Topics t ON la.TopicID = t.TopicID " +
                     "JOIN Lecturers l ON la.LecturerID = l.LecturerID WHERE la.AssignmentID = ?";
        List<LecturerAssignment> list = jdbcTemplate.query(sql, this::mapRow, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void save(LecturerAssignment assignment) {
        String sql = "INSERT INTO LecturerAssignments (TopicID, LecturerID, AssignmentDate) VALUES(?,?,?)";
        jdbcTemplate.update(sql, 
                assignment.getTopicID(), 
                assignment.getLecturerID(), 
                assignment.getAssignmentDate());
    }

    public void update(LecturerAssignment assignment) {
        String sql = "UPDATE LecturerAssignments SET TopicID = ?, LecturerID = ?, AssignmentDate = ? WHERE AssignmentID = ?";
        jdbcTemplate.update(sql, 
                assignment.getTopicID(), 
                assignment.getLecturerID(), 
                assignment.getAssignmentDate(), 
                assignment.getAssignmentID());
    }
}