package pro.buildmysoftware.training.tdd.kata;

import java.time.LocalTime;

class BerlinClock {
	private static final String SINGLE_MINUTES_PATTERN = "OOOO";
	private static final String FIVE_MINUTES_PATTERN = "OOOOOOOOOOO";
	private static final String SINGLE_HOURS_PATTERN = "OOOO";
	private static final String HOURS_PATTERN = "OOOO";
	private LocalTime time;

	BerlinClock(LocalTime time) {
		this.time = time;
	}

	public String fullTime() {
		return String
			.format("%s\n%s\n%s\n%s\n%s", seconds(), fiveHours(),
				hours(), fiveMinutes(), minutes());
	}

	String fiveHours() {
		return lightLamps(time.getHour() / 5, HOURS_PATTERN, 'R');
	}

	String seconds() {
		return (time.getSecond() % 2 == 0) ? "R" : "O";
	}

	String hours() {
		return lightLamps(time
			.getHour() % 5, SINGLE_HOURS_PATTERN, 'R');
	}

	String fiveMinutes() {
		return lightLamps(time.getMinute() / 5, FIVE_MINUTES_PATTERN)
			.replaceAll("YYY", "YYR");
	}

	String minutes() {
		return lightLamps(time.getMinute() % 5,
			SINGLE_MINUTES_PATTERN);
	}

	private String lightLamps(int numberOfLamps, String pattern) {
		return lightLamps(numberOfLamps, pattern, 'Y');
	}

	private String lightLamps(int numberOfLamps, String pattern,
				  char onChar) {
		char[] lamps = pattern.toCharArray();
		for (int i = 0; i < numberOfLamps; i++) {
			lamps[i] = onChar;
		}
		return String.valueOf(lamps);
	}
}
