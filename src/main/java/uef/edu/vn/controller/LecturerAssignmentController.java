package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uef.edu.vn.model.LecturerAssignment;
import uef.edu.vn.service.LecturerAssignmentService;
import uef.edu.vn.service.TopicService;
import uef.edu.vn.service.LecturerService;
import java.sql.Date;

@Controller
@RequestMapping("/assignments")
public class LecturerAssignmentController {

    @Autowired
    private LecturerAssignmentService assignmentService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private LecturerService lecturerService;

    private static final String VIEW = "shared/main";
    private static final String PATH = "/WEB-INF/views/assignment/";

    @GetMapping
    public String list(Model model) {
        // SỬA: Đổi getAll() thành getAllAssignments()
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        model.addAttribute("body", PATH + "list.jsp");
        return VIEW;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("assignment", new LecturerAssignment());
        model.addAttribute("topics", topicService.getAllTopics());
        model.addAttribute("lecturers", lecturerService.getAllLecturers());
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    // BỔ SUNG: Chức năng Edit (Sửa giảng viên phân công) theo đúng yêu cầu đề bài
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("assignment", assignmentService.getAssignmentById(id));
        model.addAttribute("topics", topicService.getAllTopics());
        model.addAttribute("lecturers", lecturerService.getAllLecturers());
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    // BỔ SUNG: Chức năng xử lý Save thông tin phân công
    @PostMapping("/save")
    public String save(@ModelAttribute("assignment") LecturerAssignment assignment, Model model) {
        // Kiểm tra nếu người dùng chưa chọn Đề tài hoặc Giảng viên (vẫn giữ nguyên lựa chọn mặc định)
        if (assignment.getTopicID() == 0 || assignment.getLecturerID() == 0) {
            model.addAttribute("error", "Please select both a topic and a lecturer!");
            model.addAttribute("topics", topicService.getAllTopics());
            model.addAttribute("lecturers", lecturerService.getAllLecturers());
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        }

        // Tự động gán ngày hiện tại khi thực hiện phân công hợp lệ
        assignment.setAssignmentDate(new java.sql.Date(System.currentTimeMillis()));
        assignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }
}
