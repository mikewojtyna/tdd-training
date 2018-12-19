package pro.buildmysoftware.training.tdd.question;

import org.springframework.data.mongodb.repository.MongoRepository;
import pro.buildmysoftware.training.tdd.kata.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
