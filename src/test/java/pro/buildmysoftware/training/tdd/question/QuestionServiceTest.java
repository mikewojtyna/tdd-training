package pro.buildmysoftware.training.tdd.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pro.buildmysoftware.training.tdd.kata.Question;
import pro.buildmysoftware.training.tdd.kata.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class QuestionServiceTest {

	@Autowired
	private QuestionService questionService;

	@DisplayName("should upvote and save")
	@Test
	void test0() throws Exception {
		// given
		String newQuestionId = questionService.create(author("mike"));
		User user = user("goobar");

		// when
		questionService.upvote(newQuestionId, user);

		// then
		Question questionAfterUpdate = questionService
			.findQuestion(newQuestionId).get();
		assertThat(questionAfterUpdate.getScore()).isEqualTo(1);
	}

	@DisplayName("should downvote and save")
	@Test
	void test1() throws Exception {
		// given
		String newQuestionId = questionService.create(author("mike"));
		User user = user("goobar");

		// when
		questionService.downvote(newQuestionId, user);

		// then
		Question questionAfterUpdate = questionService
			.findQuestion(newQuestionId).get();
		assertThat(questionAfterUpdate.getScore()).isEqualTo(-1);
	}

	private User user(String name) {
		return new User(name);
	}

	private User author(String author) {
		return new User(author);
	}
}
