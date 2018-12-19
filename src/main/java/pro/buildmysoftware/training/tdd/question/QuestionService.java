package pro.buildmysoftware.training.tdd.question;

import org.springframework.stereotype.Service;
import pro.buildmysoftware.training.tdd.kata.Question;
import pro.buildmysoftware.training.tdd.kata.User;

import java.util.Optional;
import java.util.function.Consumer;

@Service
class QuestionService {
	private QuestionRepository repository;

	QuestionService(QuestionRepository repository) {
		this.repository = repository;
	}

	Optional<Question> findQuestion(String id) {
		return repository.findById(id);
	}

	void downvote(String id, User user) {
		vote(id, question -> question.downvoteBy(user));
	}

	/**
	 * Creates a new question.
	 *
	 * @param author an author of the question
	 * @return the id of the created question
	 */
	String create(User author) {
		return repository.save(new Question(0, author)).getId();
	}

	/**
	 * Upvotes the question.
	 *
	 * @param id   question id
	 * @param user user upvoting the question
	 */
	void upvote(String id, User user) {
		vote(id, question -> question.upvoteBy(user));
	}

	private void vote(String id, Consumer<Question> votingConsumer) {
		repository.findById(id).ifPresent(question -> {
			votingConsumer.accept(question);
			repository.save(question);
		});
	}
}
