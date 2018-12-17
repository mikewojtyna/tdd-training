package pro.buildmysoftware.training.tdd.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CalculatorTest {

	@Nested
	@DisplayName("division feature")
	class Division {
		// @formatter:off
		@DisplayName(
			"given two integers," +
			"then return division result")
		@CsvSource({
			"4, 2, 2",
			"6, 2, 3",
			"0, 1000, 0"
		})
		@ParameterizedTest(name =
			"given: first={0}, second={1}; " +
			"expected: {2} ")
		// @formatter:on
		void test0(int first, int second, int expected) throws Exception {
			// when
			int result = Calculator.divide(first, second);

			// then
			assertThat(result).isEqualTo(expected);
		}

		// @formatter:off
		@DisplayName(
			"given 0 as second number," +
			"then throw illegal argument exception")
		@Test
		// @formatter:on
		void test1() throws Exception {
			// given
			int a = 1000;
			int b = 0;

			// when
			Throwable e = catchThrowable(() -> Calculator
				.divide(a, b));

			// then
			assertThat(e)
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@DisplayName("sum feature")
	class Sum {
		@DisplayName("given two integers, then return sum")
		@CsvSource({"1, 2, 3", "3, 4, 7"})
		@ParameterizedTest(name = "given {0} and {1}, then sum should "
			+ "be {2}")
		void test(int a, int b, int expected) throws Exception {
			// when
			int sum = Calculator.add(a, b);

			// then
			assertThat(sum).isEqualTo(expected);
		}
	}

	@Nested
	@DisplayName("multiplication feature")
	class Multiplication {
		@DisplayName("given 1 and 2, then return 2")
		@Test
		void test0() throws Exception {
			// given
			int a = 1;
			int b = 2;

			// when
			int result = Calculator.multiply(1, 2);

			// then
			assertThat(result).isEqualTo(2);
		}

		@DisplayName("given 2 and 3, then return 6")
		@Test
		void test1() throws Exception {
			// given
			int a = 2;
			int b = 3;

			// when
			int result = Calculator.multiply(2, 3);

			// then
			assertThat(result).isEqualTo(6);
		}
	}
}
