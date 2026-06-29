package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.LecturerAssignment;
import uef.edu.vn.repository.LecturerAssignmentRepository; // SỬA: Import đúng tên class
import java.util.List;

@Service
public class LecturerAssignmentService {

    @Autowired
    private LecturerAssignmentRepository assignmentRepository; // SỬA: Đổi đúng kiểu Class Repository

    public List<LecturerAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public LecturerAssignment getAssignmentById(int id) {
        return assignmentRepository.findById(id);
    }

    public void saveAssignment(LecturerAssignment assignment) {
        if (assignment.getAssignmentID() == 0) {
            assignmentRepository.save(assignment);
        } else {
            assignmentRepository.update(assignment);
        }
    }
}