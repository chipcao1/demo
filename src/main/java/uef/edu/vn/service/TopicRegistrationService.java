package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.TopicRegistration;
import uef.edu.vn.repository.RegistrationRepository;
import java.util.List;

@Service
public class TopicRegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<TopicRegistration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public List<TopicRegistration> getRegistrationsByStudent(int studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

    public List<TopicRegistration> searchRegistrations(String keyword) {
        return registrationRepository.search(keyword);
    }

    public void updateRegistrationStatus(int id, String status) {
        registrationRepository.updateStatus(id, status);
    }

    /**
     * Logic đăng ký đề tài kèm kiểm tra ràng buộc (Business Rules)
     * Thỏa mãn: Sinh viên chưa có đề tài được duyệt hoặc đang chờ duyệt.
     */
    public String registerTopic(TopicRegistration registration) {
        if (registrationRepository.hasStudentRegistered(registration.getStudentID())) {
            return "Student has already registered or has an active topic assignment!";
        }
        registrationRepository.save(registration);
        return "Success";
    }
}