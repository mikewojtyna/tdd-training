package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

	// @formatter:off
	@DisplayName(
		"given question with initial score 0, " +
		"when upvote the question and downvote it later by " +
		"the same user, " +
		"then the score should remain 0"
	)
	// @formatter:on
	@Test
	void test0() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(0, "author");
		User user = userWithName("goobar");

		// when
		question.upvoteBy(user);
		question.downvoteBy(user);

		// then
		assertThat(question.getScore()).isZero();
	}

	// @formatter:off
	@DisplayName(
		"given question with high score, " +
		"then voting should behave as expected")
	// @formatter:on
	@Test
	void test1() throws Exception {
		// given
		Question question = questionWithScoreAndAuthor(100, "mike");
		User goobar = userWithName("goobar");
		User foobar = userWithName("foobar");
		User hoobar = userWithName("hoobar");

		// when
		question.upvoteBy(goobar);
		question.upvoteBy(goobar);
		question.upvoteBy(foobar);
		question.upvoteBy(foobar);
		question.downvoteBy(foobar);
		question.downvoteBy(hoobar);
		question.upvoteBy(hoobar);
		// expected result: 101

		// then
		assertThat(question.getScore()).isEqualTo(101);
	}

	private Question questionWithScoreAndAuthor(int score, String author) {
		return new Question(score, userWithName(author));
	}

	private User userWithName(String user) {
		return new User(user);
	}

	@DisplayName("downvote examples")
	@Nested
	class Downvote {
		// @formatter:off
		@DisplayName(
			"given question with initial score 0, " +
			"when downvote the question by user other than author, " +
			"then score is -1")
		// @formatter:on
		@Test
		void test0() throws Exception {
			// given
			Question question = questionWithScoreAndAuthor(0,
				"author");
			User foobar = userWithName("foobar");

			// when
			question.downvoteBy(foobar);

			// then
			assertThat(question.getScore()).isEqualTo(-1);
		}

		// @formatter:off
		@DisplayName(
			"given question with initial score 0, " +
			"when downvote the question twice by user other than author, " +
			"then score is -1")
		// @formatter:on
		@Test
		void test1() throws Exception {
			// given
			Question question = questionWithScoreAndAuthor(0,
				"author");
			User user = userWithName("foobar");

			// when
			question.downvoteBy(user);
			question.downvoteBy(user);

			// then
			assertThat(question.getScore()).isEqualTo(-1);
		}

		// @formatter:off
		@DisplayName(
			"given question with initial score 0, " +
			"when downvote the question twice by author, " +
			"then score is 0")
		// @formatter:on
		@Test
		void test2() throws Exception {
			// given
			Question question = questionWithScoreAndAuthor(0,
				"author");
			User author = userWithName("author");

			// when
			question.downvoteBy(author);

			// then
			assertThat(question.getScore()).isEqualTo(0);
		}

		// @formatter:off
		@DisplayName(
			"given question with initial score 0, " +
			"when downvote the question by 2 authors, " +
			"then score is -2")
		// @formatter:on
		@Test
		void test3() throws Exception {
			// given
			Question question = questionWithScoreAndAuthor(0,
				"author");
			User foobar = userWithName("foobar");
			User goobar = userWithName("goobar");

			// when
			question.downvoteBy(foobar);
			question.downvoteBy(goobar);

			// then
			assertThat(question.getScore()).isEqualTo(-2);
		}
	}

	@DisplayName("upvote examples")
	@Nested
	class Upvote {
		// @formatter:off
		@DisplayName(
			"given question with initial score 0, " +
			"when upvote the question by user other than author, " +
			"then score is 1")
		// @formatter:on
		@Test
		void test0() throws Exception {
			// given
			Question question = questionWithScoreAndAuthor(0,
				"author");
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
			Question question = questionWithScoreAndAuthor(0,
				"author");
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
			Question question = questionWithScoreAndAuthor(0,
				"author");
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
			Question question = questionWithScoreAndAuthor(0,
				"author");
			User user = userWithName("goobar");

			// when
			question.upvoteBy(user);
			question.upvoteBy(user);

			// then
			assertThat(question.getScore()).isEqualTo(1);
		}
	}
}
