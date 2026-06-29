package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import uef.edu.vn.model.Student;
import uef.edu.vn.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final String VIEW = "shared/main";
    private static final String PATH = "/WEB-INF/views/student/";

    @GetMapping
    public String listStudents(Model model) {
        // SỬA: Đổi từ getAll() thành getAllStudents()
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("body", PATH + "list.jsp");
        return VIEW;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        // SỬA: Đổi từ getById() thành getStudentById()
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        // 1. Kiểm tra lỗi trống hoặc độ dài ký tự cấu hình từ Model trước
        if (result.hasErrors()) {
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        }

        // 2. Kiểm tra nghiệp vụ trùng mã Student Code dưới Database
        Student existingStudent = studentService.getStudentByCode(student.getStudentCode());
        if (existingStudent != null) {
            // Nếu là Thêm mới (ID = 0) mà mã đã tồn tại, hoặc Sửa (ID khác 0) mà trùng mã của SV khác
            if (student.getStudentID() == 0 || (student.getStudentID() != existingStudent.getStudentID())) {
                result.rejectValue("studentCode", "error.student", "This Student Code already exists! Please use another one.");
                model.addAttribute("body", PATH + "form.jsp");
                return VIEW;
            }
        }

        // 3. SỬA TẠI ĐÂY: Gọi đúng hàm saveStudent gộp chung logic rẽ nhánh của Service
        studentService.saveStudent(student);

        return "redirect:/students";
    }
}
