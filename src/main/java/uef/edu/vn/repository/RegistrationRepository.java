package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.TopicRegistration;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RegistrationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TopicRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
        TopicRegistration tr = new TopicRegistration();
        tr.setRegistrationID(rs.getInt("RegistrationID"));
        tr.setTopicID(rs.getInt("TopicID"));
        tr.setStudentID(rs.getInt("StudentID"));
        tr.setParticipationRole(rs.getString("ParticipationRole"));
        tr.setRegistrationStatus(rs.getString("RegistrationStatus"));
        tr.setRegistrationDate(rs.getDate("RegistrationDate"));
        tr.setTopicName(rs.getString("TopicName"));
        tr.setStudentName(rs.getString("FullName"));
        tr.setStudentCode(rs.getString("StudentCode"));
        return tr;
    }

    public List<TopicRegistration> findAll() {
        String sql = "SELECT r.*, t.TopicName, s.FullName, s.StudentCode FROM TopicRegistrations r " +
                     "JOIN Topics t ON r.TopicID = t.TopicID JOIN Students s ON r.StudentID = s.StudentID";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public List<TopicRegistration> findByStudentId(int studentId) {
        String sql = "SELECT r.*, t.TopicName, s.FullName, s.StudentCode FROM TopicRegistrations r " +
                     "JOIN Topics t ON r.TopicID = t.TopicID JOIN Students s ON r.StudentID = s.StudentID WHERE r.StudentID = ?";
        return jdbcTemplate.query(sql, this::mapRow, studentId);
    }

    public boolean hasStudentRegistered(int studentId) {
        String sql = "SELECT COUNT(*) FROM TopicRegistrations WHERE StudentID = ? AND RegistrationStatus != 'Rejected'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, studentId);
        return count != null && count > 0;
    }

    public void save(TopicRegistration tr) {
        String sql = "INSERT INTO TopicRegistrations (TopicID, StudentID, ParticipationRole, RegistrationStatus, RegistrationDate) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, tr.getTopicID(), tr.getStudentID(), tr.getParticipationRole(), tr.getRegistrationStatus(), tr.getRegistrationDate());
    }

    public void updateStatus(int registrationId, String status) {
        String sql = "UPDATE TopicRegistrations SET RegistrationStatus = ? WHERE RegistrationID = ?";
        jdbcTemplate.update(sql, status, registrationId);
    }

    public List<TopicRegistration> search(String keyword) {
        String sql = "SELECT r.*, t.TopicName, s.FullName, s.StudentCode FROM TopicRegistrations r " +
                     "JOIN Topics t ON r.TopicID = t.TopicID JOIN Students s ON r.StudentID = s.StudentID " +
                     "WHERE s.FullName LIKE ? OR t.TopicName LIKE ?";
        String match = "%" + keyword + "%";
        return jdbcTemplate.query(sql, this::mapRow, match, match);
    }
}