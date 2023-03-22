package com.mysite.sbb.domain.question.controller;

import com.mysite.sbb.domain.answer.dto.CreateAnswerDto;
import com.mysite.sbb.domain.question.dto.CreateQuestionDto;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor // final이 붙은 것에 대해서 생성자를 생성
@Controller
public class QuestionController {

    // 구조 Controller -> Service -> Repository
    private final QuestionService questionService;


//    @GetMapping("/list")
//    public String list(Model model) {
//        List<Question> questionList = this.questionService.getList();
//        model.addAttribute("questionList", questionList);
//        return "question_list";
//    }

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping
    public String root() {
        return "redirect:/question/list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,
                         CreateAnswerDto createAnswerDto){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(CreateQuestionDto createQuestionDto) {
        return "question_form";
    }

    @PostMapping("/create")
    public String createQuestion(@Valid CreateQuestionDto createQuestionDto,
                                 BindingResult bindingResult){ //BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        questionService.create(createQuestionDto.getSubject(), createQuestionDto.getContent());
        return "redirect:/question/list";
    }
}
