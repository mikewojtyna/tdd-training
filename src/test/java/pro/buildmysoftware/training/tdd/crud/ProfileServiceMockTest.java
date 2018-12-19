package pro.buildmysoftware.training.tdd.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pro.buildmysoftware.training.tdd.crud.ProfileTestFixture.profileWithName;

@SpringJUnitConfig
@SpringBootTest
public class ProfileServiceMockTest {

	@Autowired
	private ProfileService profileService;
	@MockBean
	private ProfileRepository profileRepository;

	@DisplayName("should find all profiles by name")
	@Test
	void test1() throws Exception {
		// TODO: change the implementation inside ProfileService to
		//  use findByName repository method and see what happens
		//  with this test
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
		when(profileRepository.findAll())
			.thenReturn(Arrays.asList(profiles));
	}
}
