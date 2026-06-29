    package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Topic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TopicRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topic topic = new Topic();
        topic.setTopicID(rs.getInt("TopicID"));
        topic.setTopicCode(rs.getString("TopicCode"));
        topic.setTopicName(rs.getString("TopicName"));
        topic.setResearchField(rs.getString("ResearchField"));
        topic.setDescription(rs.getString("Description"));
        topic.setStartYear(rs.getInt("StartYear"));
        topic.setEndYear(rs.getInt("EndYear"));
        topic.setMaxStudents(rs.getInt("MaxStudents"));
        topic.setStatus(rs.getString("Status"));
        return topic;
    }

    public List<Topic> findAll() {
        String sql = "SELECT * FROM Topics";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public List<Topic> findAvailableTopics() {
        String sql = "SELECT t.* FROM Topics t WHERE t.Status = 'Open' AND " +
                     "(SELECT COUNT(*) FROM TopicRegistrations r WHERE r.TopicID = t.TopicID AND r.RegistrationStatus = 'Approved') < t.MaxStudents";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Topic findById(int id) {
        String sql = "SELECT * FROM Topics WHERE TopicID = ?";
        List<Topic> list = jdbcTemplate.query(sql, this::mapRow, id);
        // Nếu danh sách không rỗng thì trả về phần tử đầu tiên, ngược lại trả về null
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Topic> search(String keyword) {
        String sql = "SELECT * FROM Topics WHERE TopicCode LIKE ? OR TopicName LIKE ?";
        String wildCard = "%" + keyword + "%";
        return jdbcTemplate.query(sql, this::mapRow, wildCard, wildCard);
    }

    public void save(Topic topic) {
        String sql = "INSERT INTO Topics (TopicCode, TopicName, ResearchField, Description, StartYear, EndYear, MaxStudents, Status) VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, topic.getTopicCode(), topic.getTopicName(), topic.getResearchField(),
                topic.getDescription(), topic.getStartYear(), topic.getEndYear(), topic.getMaxStudents(), topic.getStatus());
    }

    public void update(Topic topic) {
        String sql = "UPDATE Topics SET TopicCode=?, TopicName=?, ResearchField=?, Description=?, StartYear=?, EndYear=?, MaxStudents=?, Status=? WHERE TopicID=?";
        jdbcTemplate.update(sql, topic.getTopicCode(), topic.getTopicName(), topic.getResearchField(),
                topic.getDescription(), topic.getStartYear(), topic.getEndYear(), topic.getMaxStudents(), topic.getStatus(), topic.getTopicID());
    }
}