package pro.buildmysoftware.training.tdd.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
	@DisplayName("given two integers, then return sum")
	@CsvSource({"1, 2, 3", "3, 4, 7"})
	@ParameterizedTest(name = "given {0} and {1}, then sum should be {2}")
	void test(int a, int b, int expected) throws Exception {
		// when
		int sum = Calculator.add(a, b);

		// then
		assertThat(sum).isEqualTo(expected);
	}
}
