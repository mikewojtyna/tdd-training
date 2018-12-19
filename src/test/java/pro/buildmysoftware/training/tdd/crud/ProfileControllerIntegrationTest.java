package pro.buildmysoftware.training.tdd.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@WebMvcTest
class ProfileControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProfileService profileService;

	@DisplayName("when GET /profiles, then returns all profiles")
	@Test
	void test0() throws Exception {
		// given
		Profile testProfile = profileWithName("first name");
		when(profileService.findAllProfiles())
			.thenReturn(Collections.singleton(testProfile));

		// when
		// @formatter:off
		mockMvc.perform(get("/profiles"))

		// then
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].name", is("first name")));
		// @formatter:on
	}

	// @formatter:off
	@DisplayName(
		"when POST /profiles with profile body as JSON, " +
		"then posted profile is saved")
	// @formatter:on
	@Test
	void test1() throws Exception {
		// given
		String profileAsJson = "{ \"name\": \"first name\" }";
		Profile expectedProfile = profileWithName("first name");

		// when
		// @formatter:off
		mockMvc.perform(post("/profiles")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(profileAsJson))

		// then
		.andExpect(status().isOk());
		// @formatter:on
		verify(profileService, times(1)).save(expectedProfile);
	}

	private Profile profileWithName(String firstName) {
		Profile profile = new Profile();
		profile.setName(firstName);
		return profile;
	}
}
