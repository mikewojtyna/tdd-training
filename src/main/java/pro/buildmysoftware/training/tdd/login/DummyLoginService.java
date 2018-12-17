package pro.buildmysoftware.training.tdd.login;

public class DummyLoginService implements LoginService {
	@Override
	public int post(String loginParam, String passwordParam) {
		if (credentialsAreCorrect(loginParam, passwordParam)) {
			return 200;
		}
		return 401;
	}

	private boolean credentialsAreCorrect(String loginParam,
					      String passwordParam) {
		return "goobar".equals(loginParam) && "password"
			.equals(passwordParam);
	}
}
