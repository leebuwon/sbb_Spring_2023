package com.mysite.sbb.domain.answer.service;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}
