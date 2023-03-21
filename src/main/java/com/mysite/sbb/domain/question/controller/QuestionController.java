package com.mysite.sbb.domain.question.controller;

import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.question.repository.QuestionRepository;
import com.mysite.sbb.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor // final이 붙은 것에 대해서 생성자를 생성
@Controller
public class QuestionController {

    // 구조 Controller -> Service -> Repository
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
