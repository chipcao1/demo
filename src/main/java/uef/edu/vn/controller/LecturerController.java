package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import uef.edu.vn.model.Lecturer;
import uef.edu.vn.service.LecturerService;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    private static final String VIEW = "shared/main";
    private static final String PATH = "/WEB-INF/views/lecturer/";

    @GetMapping
    public String listLecturers(Model model) {
        // SỬA: Đổi từ getAll() thành getAllLecturers()
        model.addAttribute("lecturers", lecturerService.getAllLecturers());
        model.addAttribute("body", PATH + "list.jsp");
        return VIEW;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        // SỬA: Đổi từ getById() thành getLecturerById()
        model.addAttribute("lecturer", lecturerService.getLecturerById(id));
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("lecturer") Lecturer lecturer, BindingResult result, Model model) {
        // 1. Kiểm tra lỗi rỗng / độ dài từ JSR-303 Validation trước
        if (result.hasErrors()) {
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        }

        // 2. Kiểm tra trùng mã Lecturer Code dựa trên Database
        // Tìm thử trong DB xem mã code này đã có ai dùng chưa
        Lecturer existingLecturer = lecturerService.getAllLecturers().stream()
                .filter(l -> l.getLecturerCode().equalsIgnoreCase(lecturer.getLecturerCode()))
                .findFirst().orElse(null);

        // [Mẹo nhanh sử dụng stream hoặc bạn có thể gọi qua Repository nếu muốn tối ưu]
        // Để gọi trực tiếp qua Repository một cách chuẩn chỉnh nhất, bạn dùng logic sau:
        // (Vì bạn chưa viết hàm trung gian qua Service, ta có thể dùng trực tiếp hoặc sửa LecturerService)
        if (existingLecturer != null) {
            // Trường hợp thêm mới mà mã đã tồn tại, hoặc sửa mà mã trùng với người khác
            if (lecturer.getLecturerID() == 0 || (lecturer.getLecturerID() != existingLecturer.getLecturerID())) {
                result.rejectValue("lecturerCode", "error.lecturer", "This Lecturer Code already exists!");
                model.addAttribute("body", PATH + "form.jsp");
                return VIEW;
            }
        }

        // 3. Nếu mọi thứ hợp lệ thì tiến hành lưu
        lecturerService.saveLecturer(lecturer);
        return "redirect:/getLecturers" != null ? "redirect:/lecturers" : "redirect:/lecturers";
    }
}
