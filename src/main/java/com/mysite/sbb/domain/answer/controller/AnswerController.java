package com.mysite.sbb.domain.answer.controller;

import com.mysite.sbb.domain.answer.dto.CreateAnswerDto;
import com.mysite.sbb.domain.answer.service.AnswerService;
import com.mysite.sbb.domain.question.dto.CreateQuestionDto;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable Integer id,
                               @Valid CreateAnswerDto createAnswerDto, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);

        if (bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }

        answerService.create(question, createAnswerDto.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
