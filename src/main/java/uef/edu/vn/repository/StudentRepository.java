package uef.edu.vn.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentID(rs.getInt("StudentID"));
        student.setStudentCode(rs.getString("StudentCode"));
        student.setFullName(rs.getString("FullName"));
        student.setMajor(rs.getString("Major"));
        student.setEmail(rs.getString("Email"));
        student.setPhoneNumber(rs.getString("PhoneNumber"));
        return student;
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM Students";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM Students WHERE StudentID = ?";
        List<Student> list = jdbcTemplate.query(sql, this::mapRow, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public void save(Student student) {
        String sql = "INSERT INTO Students (StudentCode, FullName, Major, Email, PhoneNumber) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                student.getStudentCode(),
                student.getFullName(),
                student.getMajor(),
                student.getEmail(),
                student.getPhoneNumber());
    }

    public void update(Student student) {
        String sql = "UPDATE Students SET StudentCode = ?, FullName = ?, Major = ?, Email = ?, PhoneNumber = ? WHERE StudentID = ?";
        jdbcTemplate.update(sql,
                student.getStudentCode(),
                student.getFullName(),
                student.getMajor(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getStudentID());
    }

    public Student findByCode(String code) {
        String sql = "SELECT * FROM Students WHERE StudentCode = ?";
        List<Student> list = jdbcTemplate.query(sql, this::mapRow, code);
        return list.isEmpty() ? null : list.get(0);
    }
}
