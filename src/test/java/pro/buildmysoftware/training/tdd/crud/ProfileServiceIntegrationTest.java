package pro.buildmysoftware.training.tdd.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pro.buildmysoftware.training.tdd.crud.ProfileTestFixture.profileWithName;

@SpringBootTest
// Dirties context is used to create a completely new spring context (e.g.
// new db etc). In this case, the new context is created before each test
// method.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProfileServiceIntegrationTest {

	@Autowired
	private ProfileService profileService;

	@DisplayName("should save a new profile")
	@Test
	void test0() throws Exception {
		// given
		Collection<Address> addresses = Arrays
			.asList(new Address("Smolanska 4"), new Address("M" +
				"ściwoja II 9"), new Address("Sienna 5"));
		Profile profile = new Profile("first name", "last name", 20,
			"firstname@email.com", addresses);

		// when
		profileService.save(profile);

		// then
		Collection<Profile> allProfiles = profileService
			.findAllProfiles();
		assertThat(allProfiles).containsOnly(profile);
	}

	@DisplayName("should find all profiles by name")
	@Test
	void test1() throws Exception {
		// given
		Profile mikeProfile = profileWithName("mike");
		Profile gregProfile = profileWithName("greg");
		Profile johnProfile = profileWithName("john");
		populate(mikeProfile, gregProfile, johnProfile);

		// when
		Collection<Profile> matchingProfiles = profileService
			.findProfilesByName("greg");

		// then
		assertThat(matchingProfiles).containsOnly(gregProfile);
	}

	private void populate(Profile... profiles) {
		for (Profile profile : profiles) {
			profileService.save(profile);
		}
	}
}
