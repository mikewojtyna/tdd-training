package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowableOfType;

class StringCalculatorTest {
	// @formatter:off
	@DisplayName("given input string, then sum integers")
	@CsvSource({
		" '', 		0 ",
		" '1', 		1 ",
		" '2',	 	2 ",
		" '1,2', 	3 ",
		" '1,2,3',	6 ",
		" '1\n2,3',	6 ",
		" '//;\n1;2',	3 ",
		" '//%\n1%2',	3 ",
		" '//%!\n1%!2',	3 ",
		" '2,1001',	2 ",
		" '2,1000',	1002 ",
		" '//%!\n1%!2%!1000%!1001%!2%!999',	2004 ",
	})
	@ParameterizedTest(name = "given: \"{0}\"; expected: {1}")
	// @formatter:on
	void test0(String input, int expected) throws Exception {
		// when
		int result = StringCalculator.add(input);

		// then
		assertThat(result).isEqualTo(expected);
	}

	// @formatter:off
	@DisplayName("given negative numbers in string, then throw exception " +
		"with message containing all negative numbers")
	@CsvSource({
		" '1,-2',		'negatives not allowed: -2'",
		" '1,-2,-3,4,-5',	'negatives not allowed: -2,-3,-5'"
	})
	@ParameterizedTest(name = "given input: {0}; expected msg: {1}")
	// @formatter:on
	void test1(String input, String expectedExMsg) throws Exception {
		// when
		IllegalArgumentException exception =
			catchThrowableOfType(() -> StringCalculator
			.add(input), IllegalArgumentException.class);

		// then
		assertThat(exception.getMessage()).isEqualTo(expectedExMsg);
	}
}
