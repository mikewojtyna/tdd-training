package pro.buildmysoftware.training.tdd.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DummyLoginServiceTest {

	private LoginService loginService;

	@BeforeEach
	void beforeEach() {
		loginService = createLoginService();
	}

	// @formatter:off
	@DisplayName(
		"given correct login and password parameters," +
		"when POST on login service," +
		"then user is authorized and returned status is 200")
	// @formatter:on
	@Test
	void test0() {
		// given
		String loginParam = "goobar";
		String passwordParam = "password";

		// when
		int status = loginService.post(loginParam, passwordParam);

		// then
		assertThat(status).isEqualTo(200);
	}

	// @formatter:off
	@DisplayName(
		"given incorrect login,"+
		"when POST on login service,"+
		"then user is unauthorized and returned status is 401"
	)
	// @formatter:on
	@Test
	void test1() {
		// given
		String loginParam = "badLogin";
		String passwordParam = "badPassword";

		// when
		int status = loginService.post(loginParam, passwordParam);

		// then
		assertThat(status).isEqualTo(401);
	}

	private LoginService createLoginService() {
		return new DummyLoginService();
	}
}
