package com.mysite.sbb.domain.question.entity;

import com.mysite.sbb.domain.answer.entity.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    // CascadeType.REMOVE
    // 질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서 @OneToMany의 속성으로 cascade = CascadeType.REMOVE를 사용했다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // Answer클래스에 question이랑 연결되어 있다.
    private List<Answer> answerList;

}
