package com.mysite.sbb;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

//	@Transactional
	@Test
	void testJpa(){
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링 부트 모델 질문입니다.");
		q2.setContent("Id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());

		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()){
			Question question = oq.get();
			assertEquals("sbb가 무엇인가요?", question.getSubject());
		}

		Question question1 = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, question1.getId());

		Question question2 = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, question2.getId());;

		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question question3 = qList.get(0);
		assertEquals("sbb가 무엇인가요?", question3.getSubject());

		assertTrue(oq.isPresent());
		Question question4  = oq.get();
		question4.setSubject("수정된 제목");
		this.questionRepository.save(question4);

		assertEquals(2, this.questionRepository.count());
		assertTrue(oq.isPresent());
		Question question5 = oq.get();
		this.questionRepository.delete(question5);
		assertEquals(1, this.questionRepository.count());

		// 답변 데이터 생성 후 저장하기
		Optional<Question> optionalQuestion = this.questionRepository.findById(2);
		assertTrue(optionalQuestion.isPresent());
		Question question6 = optionalQuestion.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(question6); // 어떤 질문의 답변인지 알기 위해서 Question 객체가 필요하다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);

		//답변 조회하기
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a1 = oa.get();
		assertEquals(2, a1.getQuestion().getId());

	}
	@Transactional
	@Test
	void testJpa2() {
		// 답변에 연결된 질문 찾기 vs 질문에 달린 답변 찾기
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
