package pro.buildmysoftware.training.tdd.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StringCalculator {
	public static int add(String input) {
		if (input.isEmpty()) {
			return 0;
		}

		List<Integer> extractedNumbers = extractNumbers(input);
		validateNegatives(extractedNumbers);

		return extractedNumbers.stream().filter(n -> n <= 1000)
			.reduce(0, Integer::sum);
	}

	private static void validateNegatives(List<Integer> numbers) {
		String negativeNumbers = numbers.stream().filter(n -> n < 0)
			.map(Object::toString).collect(Collectors.joining(","
			));
		if (!negativeNumbers.isEmpty()) {
			throw new IllegalArgumentException("negatives not " +
				"allowed: " + negativeNumbers);
		}
	}

	private static List<Integer> extractNumbers(String input) {
		String delimiterPrefix = "//";
		String supportedDelimites = "[,\n]";
		if (input.startsWith(delimiterPrefix)) {
			int newLineIndex = input.indexOf("\n");
			String delimiter = input.substring(delimiterPrefix
				.length(), newLineIndex);
			supportedDelimites = delimiter;
			input = input.substring(newLineIndex + 1);
		}
		return Stream.of(input.split(supportedDelimites))
			.map(Integer::valueOf).collect(Collectors.toList());
	}
}
