package com.mysite.sbb.domain.question.entity;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime modifyDate;
    // CascadeType.REMOVE
    // 질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서 @OneToMany의 속성으로 cascade = CascadeType.REMOVE를 사용했다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // Answer클래스에 question이랑 연결되어 있다.
    // oneToMany에는 직접 객체 초기화를 진행해준다.
    private List<Answer> answerList = new ArrayList<>();

    @ManyToOne
    private SiteUser author;

    public void addAnswer(Answer a) {
        a.setQuestion(this);
        answerList.add(a);
    }
}
