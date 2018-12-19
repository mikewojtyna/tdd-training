package pro.buildmysoftware.training.tdd.crud;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ProfileRepository extends MongoRepository<Profile, String> {
	Collection<Profile> findByName(String name);
}
