package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import uef.edu.vn.model.Topic;
import uef.edu.vn.service.TopicService;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    private static final String VIEW = "shared/main";
    private static final String PATH = "/WEB-INF/views/topic/";

    @GetMapping
    public String list(@RequestParam(value = "search", required = false) String search, Model model) {
        model.addAttribute("topics", (search != null && !search.isEmpty()) ? topicService.searchTopics(search) : topicService.getAllTopics());
        model.addAttribute("body", PATH + "list.jsp");
        return VIEW;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("topic", new Topic());
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("topic", topicService.getTopicById(id));
        model.addAttribute("body", PATH + "form.jsp");
        return VIEW;
    }

    @PostMapping("/save")
    public String saveTopic(@Valid @ModelAttribute("topic") Topic topic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        }

        try {
            // Gọi Service thực hiện lưu dữ liệu
            topicService.saveTopic(topic);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            // Bắt lỗi trùng mã TopicCode trong Database và ném thông báo ra giao diện
            result.rejectValue("topicCode", "error.topic", "This Topic Code already exists! Please use another code.");
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        } catch (Exception e) {
            result.rejectValue("topicCode", "error.topic", "An error occurred while saving. Please try again.");
            model.addAttribute("body", PATH + "form.jsp");
            return VIEW;
        }

        return "redirect:/topics";
    }
}
