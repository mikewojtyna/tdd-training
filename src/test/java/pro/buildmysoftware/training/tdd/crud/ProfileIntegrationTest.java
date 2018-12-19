package pro.buildmysoftware.training.tdd.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest
class ProfileIntegrationTest {

	@Autowired
	private ProfileService profileService;

	@DisplayName("should save a new profile")
	@Test
	void test() throws Exception {
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
}
