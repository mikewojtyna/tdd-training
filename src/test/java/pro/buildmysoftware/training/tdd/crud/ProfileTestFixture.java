package pro.buildmysoftware.training.tdd.crud;

public class ProfileTestFixture {
	public static Profile profileWithName(String name) {
		Profile profile = new Profile();
		profile.setName(name);
		return profile;
	}
}
