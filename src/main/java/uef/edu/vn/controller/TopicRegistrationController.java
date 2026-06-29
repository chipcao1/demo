package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uef.edu.vn.model.TopicRegistration;
import uef.edu.vn.service.TopicRegistrationService;
import uef.edu.vn.service.TopicService;
import uef.edu.vn.service.StudentService;
import java.sql.Date;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/registrations")
public class TopicRegistrationController {

    @Autowired
    private TopicRegistrationService registrationService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private StudentService studentService;

    private static final String VIEW = "shared/main";
    private static final String PATH = "/WEB-INF/views/registration/";

    // 1. Dành cho Admin xem toàn bộ hệ thống
    @GetMapping("/admin")
    public String adminList(@RequestParam(value = "search", required = false) String search, Model model) {
        // SỬA: Đổi tên hàm gọi Service cho đúng cấu trúc class Service
        model.addAttribute("registrations", (search != null && !search.isEmpty()) ? registrationService.searchRegistrations(search) : registrationService.getAllRegistrations());
        model.addAttribute("body", PATH + "admin_list.jsp");
        return VIEW;
    }

    // 2. Dành cho sinh viên mở form đăng ký
    @GetMapping("/student/register")
    public String registerForm(Model model) {
        model.addAttribute("topics", topicService.getAvailableTopics());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("body", PATH + "register.jsp");
        return VIEW;
    }

    // 3. BỔ SUNG: Xử lý submit form đăng ký từ sinh viên gửi lên
    @PostMapping("/student/submit")
    public String submitRegistration(@RequestParam("topicID") int topicID,
            @RequestParam("studentID") int studentID,
            @RequestParam("role") String role,
            Model model) {
        TopicRegistration tr = new TopicRegistration();
        tr.setTopicID(topicID);
        tr.setStudentID(studentID);
        tr.setParticipationRole(role);
        tr.setRegistrationStatus("Pending");
        tr.setRegistrationDate(new Date(System.currentTimeMillis()));

        String result = registrationService.registerTopic(tr);

        if (!result.equals("Success")) {
            // Nếu dính business rule (ví dụ sinh viên đã có đề tài trước đó), báo lỗi quay lại form
            model.addAttribute("error", result);
            model.addAttribute("topics", topicService.getAvailableTopics());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("body", PATH + "register.jsp");
            return VIEW;
        }

        // SỬA TẠI ĐÂY: Thành công chuyển hướng ngay sang trang xem danh sách của chính sinh viên đó
        return "redirect:/registrations/student/my-list?studentID=" + studentID;
    }

    // 4. BỔ SUNG: Xem danh sách đăng ký cá nhân của sinh viên
    // Thêm hàm này vào trong TopicRegistrationController nếu chưa có
    @GetMapping("/student/my-list")
    public String myRegistrations(@RequestParam("studentID") int studentID, Model model) {
        // Gọi sang service để lấy danh sách đăng ký của riêng sinh viên này
        model.addAttribute("registrations", registrationService.getRegistrationsByStudent(studentID));

        // Đặt thuộc tính body trỏ tới file my_list.jsp nằm trong thư mục registration
        model.addAttribute("body", PATH + "my_list.jsp");
        return VIEW; // Trả về giao diện shared/main.jsp làm layout chung
    }

    // 5. BỔ SUNG: Admin thực hiện duyệt đơn (Approve)
    @GetMapping("/admin/approve/{id}")
    public String approve(@PathVariable("id") int id) {
        registrationService.updateRegistrationStatus(id, "Approved");
        return "redirect:/registrations/admin";
    }

    // 6. BỔ SUNG: Admin thực hiện từ chối đơn (Reject)
    @GetMapping("/admin/reject/{id}")
    public String reject(@PathVariable("id") int id) {
        registrationService.updateRegistrationStatus(id, "Rejected");
        return "redirect:/registrations/admin";
    }

    @PostMapping("/save")
    public String register(@ModelAttribute("registration") TopicRegistration registration, BindingResult result, Model model) {

        // 1. Kiểm tra nếu chưa chọn Đề tài hoặc Sinh viên (Validation thủ công hoặc qua @Valid)
        if (registration.getTopicID() == 0 || registration.getStudentID() == 0) {
            model.addAttribute("error", "Please select both a student and an available topic!");

            // SỬA: Bắt buộc phải nạp lại danh sách giống như lúc @GetMapping("/add") để form không bị kẹt
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("topics", topicService.getAvailableTopics()); // Hoặc getAllTopics() tùy logic của bạn

            model.addAttribute("body", PATH + "form.jsp");
            return VIEW; // Trả về giao diện main (chứa form) để hiển thị lỗi đỏ
        }

        // 2. Logic kiểm tra Business Rule (Sinh viên đã đăng ký chưa...) viết tiếp ở đây
        // ...
        return "redirect:/registrations";
    }
}
