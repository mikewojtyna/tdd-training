package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.buildmysoftware.training.tdd.kata.Yahtzee.Category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pro.buildmysoftware.training.tdd.kata.Yahtzee.Category.*;

public class YahtzeeTest {

	// @formatter:off
	@DisplayName(
		"given any roll and special category, " +
		"then score is according to the examples"
	)
	@ParameterizedTest(name = "roll: {0}, category: {1}, expected score: " +
				  "{2}")
	@MethodSource("specialExamples")
	// @formatter:on
	void test0(List<Integer> roll, Category category, int expectedScore) throws Exception {
		// when
		int score = Yahtzee.score(roll, category);

		// then
		assertThat(score).isEqualTo(expectedScore);
	}

	// @formatter:off
	@DisplayName(
		"given any roll and number category, " +
		"then score is sum of selected number"
	)
	@ParameterizedTest(name = "roll: {0}, number category: {1}, expected " +
				  "score: {2}")
	@MethodSource("numberExamples")
	// @formatter:on
	void test1(List<Integer> roll, int numberCategory, int expectedScore) throws Exception {
		// when
		int score = Yahtzee.score(roll, numberCategory);

		// then
		assertThat(score).isEqualTo(expectedScore);
	}

	private static Stream<Arguments> numberExamples() {
		return Stream.of(
			// @formatter:off
			// ones examples
			Arguments
				.of(Arrays.asList(1, 1, 1, 1, 1), 1, 5),
			Arguments
				.of(Arrays.asList(1, 2, 3, 4, 5), 1, 1),
			Arguments
				.of(Arrays.asList(1, 1, 3, 1, 5), 1, 3),
			Arguments
				.of(Arrays.asList(2, 3, 4, 5, 6), 1, 0),
			// twos examples
			Arguments.of(Arrays
				.asList(2, 3, 4, 5, 6), 2, 2),
			Arguments
				.of(Arrays
					.asList(2, 2, 4, 5, 6), 2, 4),
			Arguments
				.of(Arrays
					.asList(2, 3, 4, 2, 2), 2, 6),
			Arguments
				.of(Arrays.asList(1, 3, 4, 5, 6), 2, 0),
			// threes examples
			Arguments
				.of(Arrays.asList(3, 2, 4, 5, 6), 3, 3),
			Arguments
				.of(Arrays.asList(3, 3, 2, 4, 5), 3, 6),
			Arguments
				.of(Arrays.asList(1, 2, 4, 5, 6), 3, 0),
			// fours examples
			Arguments
				.of(Arrays.asList(4, 2, 3, 5, 6), 4, 4),
			Arguments
				.of(Arrays.asList(4, 4, 2, 3, 5), 4, 8),
			Arguments
				.of(Arrays.asList(1, 2, 3, 5, 6), 4, 0),
			// fives examples
			Arguments
				.of(Arrays.asList(5, 2, 3, 4, 6), 5, 5),
			Arguments
				.of(Arrays.asList(5, 5, 1, 2, 3), 5, 10),
			Arguments
				.of(Arrays.asList(1, 2, 3, 4, 6), 5, 0),
			// sixes examples
			Arguments
				.of(Arrays.asList(6, 2, 3, 4, 5), 6, 6),
			Arguments
				.of(Arrays.asList(6, 6, 1, 2, 3), 6, 12),
			Arguments
				.of(Arrays.asList(1, 2, 3, 4, 5), 6, 0)
			// @formatter:on
		);
	}

	private static Stream<Arguments> specialExamples() {
		// @formatter:off
		return Stream.of(
			// chance examples
			Arguments.of(Arrays.asList(1, 1, 1, 1, 1), CHANCE, 5),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5), CHANCE, 15),
			// yahtzee examples
			Arguments.of(Arrays.asList(1, 1, 1, 1, 1), YAHTZEE, 50),
			Arguments.of(Arrays.asList(2, 2, 2, 2, 2), YAHTZEE, 50),
			Arguments.of(Arrays.asList(2, 1, 1, 1, 1), YAHTZEE, 0),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5), YAHTZEE, 0),
			// small straight examples
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5),
				SMALL_STRAIGHT, 15),
			Arguments.of(Arrays.asList(2, 2, 3, 4, 5),
				SMALL_STRAIGHT, 0),
			Arguments.of(Arrays.asList(2, 3, 4, 5, 6),
				SMALL_STRAIGHT, 0),
			// large straight examples
			Arguments.of(Arrays.asList(2, 3, 4, 5, 6),
				Category.LARGE_STRAIGHT, 20),
			Arguments.of(Arrays.asList(3, 3, 4, 5, 6),
				LARGE_STRAIGHT
				, 0),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5),
				LARGE_STRAIGHT, 0),
			// pairs
			Arguments.of(Arrays.asList(3, 3, 3, 4, 4), PAIR, 8),
			Arguments.of(Arrays.asList(3, 3, 3, 1, 4), PAIR, 6),
			Arguments.of(Arrays.asList(1, 1, 2, 2, 5), PAIR, 4),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5), PAIR, 0),
			Arguments.of(Arrays.asList(6, 6, 6, 6, 6), PAIR, 12),
			// two pairs
			Arguments.of(Arrays.asList(1, 1, 2, 3, 3), TWO_PAIRS,
				8),
			Arguments.of(Arrays.asList(4, 4, 2, 3, 3), TWO_PAIRS,
				14),
			Arguments.of(Arrays.asList(1, 1, 2, 3, 4), TWO_PAIRS,
				0),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5), TWO_PAIRS,
				0),
			Arguments.of(Arrays.asList(6, 2, 1, 2, 1), TWO_PAIRS,
				6),
			// three of a kind
			Arguments.of(Arrays.asList(3, 3, 3, 4, 5),
				THREE_OF_A_KIND, 9),
			Arguments.of(Arrays.asList(1, 2, 4, 4, 4),
				THREE_OF_A_KIND, 12),
			Arguments.of(Arrays.asList(1, 1, 1, 1, 1),
				THREE_OF_A_KIND, 3),
			Arguments.of(Arrays.asList(5, 2, 5, 5, 2),
				THREE_OF_A_KIND, 15),
			// four of a kind
			Arguments.of(Arrays.asList(2, 2, 2, 2, 5),
				FOUR_OF_A_KIND, 8),
			Arguments.of(Arrays.asList(2, 2, 2, 2, 2),
				FOUR_OF_A_KIND, 8),
			Arguments.of(Arrays.asList(1, 2, 3, 4, 5),
				FOUR_OF_A_KIND, 0),
			Arguments.of(Arrays.asList(2, 2, 2, 1, 3),
				FOUR_OF_A_KIND, 0),
			Arguments.of(Arrays.asList(6, 2, 6, 6, 6),
				FOUR_OF_A_KIND, 24),
			// full house
			Arguments.of(Arrays.asList(1, 1, 2, 2, 2), FULL_HOUSE
				, 8),
			Arguments.of(Arrays.asList(1, 1, 3, 2, 2), FULL_HOUSE
				, 0),
			Arguments.of(Arrays.asList(4, 4, 4, 4, 4), FULL_HOUSE
				, 0),
			Arguments.of(Arrays.asList(4, 4, 4, 4, 1), FULL_HOUSE
				, 0),
			Arguments.of(Arrays.asList(1, 5, 5, 1, 1), FULL_HOUSE
				, 13)
		);
		// @formatter:on
	}

}
