package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {
	// @formatter:off
	@DisplayName(
		"given question with initial score 0, " +
		"when upvote the question by user other than author, " +
		"then score is 1")
	// @formatter:on
	@Test
	void test0() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(0, "author");
		User user = userWithName("goobar");

		// when
		question.upvoteBy(user);

		// then
		assertThat(question.getScore()).isEqualTo(1);
	}

	// @formatter:off
	@DisplayName(
		"given question with initial score 0, " +
		"when upvote the question by two different users, "+
		"then score is 2")
	// @formatter:on
	@Test
	void test1() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(0, "author");
		User goobar = userWithName("goobar");
		User foobar = userWithName("foobar");

		// when
		question.upvoteBy(goobar);
		question.upvoteBy(foobar);

		// then
		assertThat(question.getScore()).isEqualTo(2);
	}

	// @formatter:off
	@DisplayName(
		"given question with initial score 0, " +
		"when upvote the question by author, " +
		"then score is 0")
	// @formatter:on
	@Test
	void test2() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(0, "author");
		User author = userWithName("author");

		// when
		question.upvoteBy(author);

		// then
		assertThat(question.getScore()).isEqualTo(0);
	}

	// @formatter:off
	@DisplayName(
		"given question with initial score 0, " +
		"when upvote the question by the same user twice, " +
		"then score is 1")
	// @formatter:on
	@Test
	void test3() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(0, "author");
		User user = userWithName("goobar");

		// when
		question.upvoteBy(user);
		question.upvoteBy(user);

		// then
		assertThat(question.getScore()).isEqualTo(1);
	}

	private Question questionWithScoreAndAuthor(int score, String author) {
		return new Question(score, userWithName(author));
	}

	private User userWithName(String user) {
		return new User(user);
	}
}
