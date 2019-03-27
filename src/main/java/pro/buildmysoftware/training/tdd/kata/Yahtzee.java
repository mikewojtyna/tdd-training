package pro.buildmysoftware.training.tdd.kata;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Yahtzee {
	public static int score(List<Integer> roll, int numberCategory) {
		return numbers(roll, numberCategory);
	}

	public static int score(List<Integer> roll, Category category) {
		int score;
		switch (category) {
			case FULL_HOUSE:
				score = fullHouse(roll);
				break;
			case FOUR_OF_A_KIND:
				score = nOfAKind(roll, 4, false);
				break;
			case THREE_OF_A_KIND:
				score = nOfAKind(roll, 3, false);
				break;
			case TWO_PAIRS:
				score = twoPairs(roll);
				break;
			case PAIR:
				score = pair(roll);
				break;
			case LARGE_STRAIGHT:
				score = largeStraight(roll);
				break;
			case SMALL_STRAIGHT:
				score = smallStraight(roll);
				break;
			case YAHTZEE:
				score = yahtzee(roll);
				break;
			default:
				score = chance(roll);
		}
		return score;
	}

	private static int fullHouse(List<Integer> roll) {
		int twoOfAKind = nOfAKind(roll, 2, true);
		int threeOfAKind = nOfAKind(roll, 3, true);
		if (twoOfAKind != 0 && threeOfAKind != 0) {
			return twoOfAKind + threeOfAKind;
		}
		return 0;
	}

	private static int nOfAKind(List<Integer> roll, int n,
		boolean exactMatch) {
		return sumsOfNOfAKind(roll, n, exactMatch).findAny().orElse(0);
	}

	private static int twoPairs(List<Integer> roll) {
		List<Integer> sumsOfPairs = sumsOfPairs(roll).boxed()
			.collect(Collectors.toList());
		if (sumsOfPairs.size() > 1) {
			return sumsOfPairs.get(0) + sumsOfPairs.get(1);
		}
		return 0;
	}

	private static int pair(List<Integer> roll) {
		return sumsOfPairs(roll).max().orElse(0);
	}

	private static IntStream sumsOfPairs(List<Integer> roll) {
		return sumsOfNOfAKind(roll, 2, false);
	}

	private static IntStream sumsOfNOfAKind(List<Integer> roll,
		int nOfAKind, boolean exactMatch) {
		Map<Integer, List<Integer>> groups = roll.stream()
			.collect(Collectors.groupingBy(d -> d));
		Predicate<Map.Entry<Integer, List<Integer>>> predicate;
		if (exactMatch) {
			predicate = e -> e.getValue().size() == nOfAKind;
		}
		else {
			predicate = e -> e.getValue().size() >= nOfAKind;
		}
		return groups.entrySet().stream().filter(predicate)
			.mapToInt(e -> sum(e.getValue()
				.subList(0, nOfAKind), Yahtzee::alwaysTrue));
	}

	private static boolean alwaysTrue(int i) {
		return true;
	}

	private static int numbers(List<Integer> roll, int number) {
		return sum(roll, d -> d == number);
	}

	private static int chance(List<Integer> roll) {
		return sum(roll, Yahtzee::alwaysTrue);
	}

	private static int sum(List<Integer> roll, IntPredicate predicate) {
		return roll.stream().mapToInt(Integer::intValue)
			.filter(predicate).sum();
	}

	private static int yahtzee(List<Integer> roll) {
		if (new HashSet<>(roll).size() == 1) {
			return 50;
		}
		return 0;
	}

	private static int smallStraight(List<Integer> roll) {
		if (roll.get(0) == 1 && roll.get(1) == 2 && roll.get(2) == 3 && roll.get(3) == 4 && roll.get(4) == 5) {
			return 15;
		}
		return 0;
	}

	private static int largeStraight(List<Integer> roll) {
		if (roll.get(0) == 2 && roll.get(1) == 3 && roll.get(2) == 4 && roll.get(3) == 5 && roll.get(4) == 6) {
			return 20;
		}
		return 0;
	}

	public enum Category {
		YAHTZEE, SMALL_STRAIGHT, LARGE_STRAIGHT, PAIR, TWO_PAIRS,
		THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, CHANCE
	}
}
