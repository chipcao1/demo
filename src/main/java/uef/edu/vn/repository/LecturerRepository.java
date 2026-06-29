package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Lecturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LecturerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Lecturer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerID(rs.getInt("LecturerID"));
        lecturer.setLecturerCode(rs.getString("LecturerCode"));
        lecturer.setFullName(rs.getString("FullName"));
        lecturer.setFaculty(rs.getString("Faculty"));
        lecturer.setEmail(rs.getString("Email"));
        lecturer.setPhoneNumber(rs.getString("PhoneNumber"));
        return lecturer;
    }

    public List<Lecturer> findAll() {
        String sql = "SELECT * FROM Lecturers";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Lecturer findById(int id) {
        String sql = "SELECT * FROM Lecturers WHERE LecturerID = ?";
        List<Lecturer> list = jdbcTemplate.query(sql, this::mapRow, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void save(Lecturer lecturer) {
        String sql = "INSERT INTO Lecturers (LecturerCode, FullName, Faculty, Email, PhoneNumber) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                lecturer.getLecturerCode(),
                lecturer.getFullName(),
                lecturer.getFaculty(),
                lecturer.getEmail(),
                lecturer.getPhoneNumber());
    }

    public void update(Lecturer lecturer) {
        String sql = "UPDATE Lecturers SET LecturerCode = ?, FullName = ?, Faculty = ?, Email = ?, PhoneNumber = ? WHERE LecturerID = ?";
        jdbcTemplate.update(sql,
                lecturer.getLecturerCode(),
                lecturer.getFullName(),
                lecturer.getFaculty(),
                lecturer.getEmail(),
                lecturer.getPhoneNumber(),
                lecturer.getLecturerID());
    }

    public Lecturer findByCode(String code) {
        String sql = "SELECT * FROM Lecturers WHERE LecturerCode = ?";
        List<Lecturer> list = jdbcTemplate.query(sql, this::mapRow, code);
        return list.isEmpty() ? null : list.get(0);
    }
}
