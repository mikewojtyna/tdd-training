package pro.buildmysoftware.training.tdd.question;

import org.springframework.web.bind.annotation.*;
import pro.buildmysoftware.training.tdd.kata.User;

@RestController
@RequestMapping("/questions")
class QuestionController {
	private QuestionService service;

	public QuestionController(QuestionService service) {
		this.service = service;
	}

	@PutMapping
	@RequestMapping("/{id}/upvote")
	void upvote(@PathVariable("id") String questionId, @RequestParam("user"
	) String user) {
		service.upvote(questionId, new User(user));
	}

	@PutMapping
	@RequestMapping("/{id}/downvote")
	void downvote(@PathVariable("id") String questionId, @RequestParam(
		"user") String user) {
		service.downvote(questionId, new User(user));
	}

	@PostMapping
	String create(@RequestParam String author) {
		return service.create(new User(author));
	}
}
