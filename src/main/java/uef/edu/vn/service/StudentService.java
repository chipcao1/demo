package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Student;
import uef.edu.vn.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        if (student.getStudentID() == 0) {
            studentRepository.save(student);
        } else {
            studentRepository.update(student);
        }
    }

    public Student getStudentByCode(String code) {
        return studentRepository.findByCode(code);
    }
}
