package pro.buildmysoftware.training.tdd.kata;

import java.util.ArrayList;
import java.util.Collection;

public class Question {
	private int score;
	private User author;
	private Collection<User> voters;

	public Question(int score, User author) {
		this.score = score;
		this.author = author;
		voters = new ArrayList<>();
	}

	public void upvoteBy(User user) {
		boolean userIsDifferentThanAuthor = !author.equals(user);
		boolean userAlreadyVoted = voters.contains(user);
		if (userIsDifferentThanAuthor && !userAlreadyVoted) {
			score++;
			voters.add(user);
		}
	}

	public int getScore() {
		return score;
	}
}
