package pro.buildmysoftware.training.tdd.question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import pro.buildmysoftware.training.tdd.kata.User;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(controllers = QuestionController.class)
class QuestionControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private QuestionService questionService;

	@DisplayName("should create new question")
	@Test
	void test0() throws Exception {
		// given
		when(questionService.create(new User("goobar")))
			.thenReturn("123");

		// when
		mockMvc.perform(post("/questions").param("author", "goobar"))

			// @formatter:off
		// then
		.andExpect(status().isOk())
		.andExpect(content().string("123"));
		// @formatter:on
	}

	@DisplayName("should upvote question")
	@Test
	void test1() throws Exception {
		// given
		User user = new User("goobar");

		// when
		mockMvc.perform(put("/questions/123/upvote")
			.param("user", "goobar"))

			// @formatter:off
		// then
		.andExpect(status().isOk());
		verify(questionService, times(1)).upvote("123", user);
		// @formatter:on
	}

	@DisplayName("should downvote question")
	@Test
	void test2() throws Exception {
		// given
		User user = new User("goobar");

		// when
		mockMvc.perform(put("/questions/123/downvote")
			.param("user", "goobar"))

			// @formatter:off
		// then
		.andExpect(status().isOk());
		verify(questionService, times(1)).downvote("123", user);
		// @formatter:on
	}
}
