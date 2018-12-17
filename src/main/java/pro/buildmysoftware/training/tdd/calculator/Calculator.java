package pro.buildmysoftware.training.tdd.calculator;

public class Calculator {
	public static int add(int a, int b) {
		return a + b;
	}

	public static int multiply(int a, int b) {
		return a * b;
	}

	public static int divide(int first, int second) {
		if (second == 0) {
			throw new IllegalArgumentException("Second argument " + "cannot be zero");
		}
		return first / second;
	}
}
