package pro.buildmysoftware.training.tdd.kata;

import java.util.HashMap;
import java.util.Map;

public class Question {
	private int score;
	private User author;
	private Map<String, Integer> voters;
	private String id;

	public Question(int score, User author) {
		this.score = score;
		this.author = author;
		voters = new HashMap<>();
	}

	public void upvoteBy(User user) {
		changeScoreBy(1, user);
	}

	public int getScore() {
		return score;
	}

	public void downvoteBy(User user) {
		changeScoreBy(-1, user);
	}

	public String getId() {
		return id;
	}

	private void changeScoreBy(int scoreChange, User user) {
		boolean userIsDifferentThanAuthor = !author.equals(user);
		Integer oldUserScore = voters.getOrDefault(user.getName(), 0);
		int scoreAfterChange = oldUserScore + scoreChange;
		if (isVoteAllowed(scoreAfterChange) && userIsDifferentThanAuthor) {
			score += scoreChange;
			voters.put(user.getName(), scoreAfterChange);
		}
	}

	private boolean isVoteAllowed(int scoreAfterChange) {
		return scoreAfterChange > -2 && scoreAfterChange < 2;
	}
}
