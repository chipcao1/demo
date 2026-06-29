package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Lecturer;
import uef.edu.vn.repository.LecturerRepository;
import java.util.List;

@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerById(int id) {
        return lecturerRepository.findById(id);
    }

    public void saveLecturer(Lecturer lecturer) {
        if (lecturer.getLecturerID() == 0) {
            lecturerRepository.save(lecturer);
        } else {
            lecturerRepository.update(lecturer);
        }
    }
}