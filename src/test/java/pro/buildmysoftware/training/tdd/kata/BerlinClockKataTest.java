package pro.buildmysoftware.training.tdd.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BerlinClockKataTest {
	@DisplayName("full clock")
	@Nested
	class FullClock {
		// @formatter:off
		@DisplayName("should show all rows of full clock")
		@CsvSource({
			"'00:00:00', 'R\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO'",
			"'23:59:59', 'O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY'",
			"'16:50:06', 'R\nRRRO\nROOO\nYYRYYRYYRYO\nOOOO'",
			"'11:37:01', 'O\nRROO\nROOO\nYYRYYRYOOOO\nYYOO'"
		})
		@ParameterizedTest(name = "given: {0}, expected: {1}")
		// @formatter:on
		void test(LocalTime time, String expectedTime) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String fullTime = clock.fullTime();

			// then
			assertThat(fullTime).isEqualTo(expectedTime);
		}
	}

	@DisplayName("seconds")
	@Nested
	class Seconds {
		// @formatter:off
		@DisplayName("should show blinking seconds lamp")
		@CsvSource({
			"'00:00:00', 'R'",
			"'00:00:01', 'O'",
			"'00:00:58', 'R'",
			"'00:00:59', 'O'"
		})
		@ParameterizedTest(name = "given: {0}, expected: {1}")
		// @formatter:on
		void test(LocalTime time, String expectedSeconds) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String seconds = clock.seconds();

			// then
			assertThat(seconds).isEqualTo(expectedSeconds);
		}
	}

	@DisplayName("five hours")
	@Nested
	class FiveHours {
		// @formatter:off
		@DisplayName("should show five hours row")
		@CsvSource({
			"'00:00:00', 'OOOO'",
			"'01:00:00', 'OOOO'",
			"'05:00:00', 'ROOO'",
			"'09:00:00', 'ROOO'",
			"'10:00:00', 'RROO'",
			"'16:00:00', 'RRRO'",
			"'23:00:00', 'RRRR'"
		})
		@ParameterizedTest(name = "given: {0}, expected: {1}")
		// @formatter:on
		void test(LocalTime time, String expectedFiveHours) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String fiveHours = clock.fiveHours();

			// then
			assertThat(fiveHours).isEqualTo(expectedFiveHours);
		}
	}

	@DisplayName("single hours")
	@Nested
	class SingleHours {
		// @formatter:off
		@DisplayName("should show single hours row")
		@CsvSource({
			"'00:00:00', 'OOOO'",
			"'01:00:00', 'ROOO'",
			"'02:00:00', 'RROO'",
			"'04:00:00', 'RRRR'",
			"'05:00:00', 'OOOO'",
			"'06:00:00', 'ROOO'",
			"'14:00:00', 'RRRR'"
		})
		@ParameterizedTest(name = "given: {0}, expected: {1}")
		// @formatter:on
		void test(LocalTime time, String expectedHours) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String hours = clock.hours();

			// then
			assertThat(hours).isEqualTo(expectedHours);
		}
	}

	@DisplayName("five minutes")
	@Nested
	class FiveMinutes {
		// @formatter:off
		@DisplayName("should show five minutes row only")
		@CsvSource({
			"'00:00:00', 'OOOOOOOOOOO'",
			"'00:01:00', 'OOOOOOOOOOO'",
			"'00:05:00', 'YOOOOOOOOOO'",
			"'00:06:00', 'YOOOOOOOOOO'",
			"'00:10:00', 'YYOOOOOOOOO'",
			"'00:14:00', 'YYOOOOOOOOO'",
			"'00:15:00', 'YYROOOOOOOO'",
			"'00:34:00', 'YYRYYROOOOO'",
			"'00:59:00', 'YYRYYRYYRYY'"
		})
		@ParameterizedTest(name = "given: {0}, expected: {1}")
		// @formatter:on
		void test(LocalTime time, String expectedFiveMinutes) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String fiveMinutes = clock.fiveMinutes();

			// then
			assertThat(fiveMinutes).isEqualTo(expectedFiveMinutes);
		}
	}

	@DisplayName("single minutes")
	@Nested
	class SingleMinutes {
		// @formatter:off
		@DisplayName("should show minutes row only")
		@CsvSource({
			"'00:00:00', 'OOOO'",
			"'00:01:00', 'YOOO'",
			"'00:02:00', 'YYOO'",
			"'00:04:00', 'YYYY'",
			"'00:05:00', 'OOOO'",
			"'00:06:00', 'YOOO'",
			"'00:10:00', 'OOOO'",
			"'00:13:00', 'YYYO'",
			"'00:14:00', 'YYYY'"
		})
		@ParameterizedTest(name =
			"given: {0}, expected: {1}")
		// @formatter:on
		void test(@ConvertWith(TimeConverter.class) LocalTime time,
			  String expectedMinutes) throws Exception {
			// given
			BerlinClock clock = new BerlinClock(time);

			// when
			String minutes = clock.minutes();

			// then
			assertThat(minutes).isEqualTo(expectedMinutes);
		}
	}
}
