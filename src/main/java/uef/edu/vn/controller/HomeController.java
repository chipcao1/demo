package uef.edu.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Tự động chuyển hướng người dùng sang trang danh sách đề tài khi mở ứng dụng
        return "redirect:/topics"; 
    }
}