package pro.buildmysoftware.training.tdd.crud;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
class ProfileService {

	private ProfileRepository repository;

	public ProfileService(ProfileRepository repository) {
		this.repository = repository;
	}

	void save(Profile profile) {
		repository.save(profile);
	}

	Collection<Profile> findAllProfiles() {
		return repository.findAll();
	}

	Collection<Profile> findProfilesByName(String name) {
		// TODO: uncomment this line to see what happens in
		//  ProfileServiceMockTest
		// return repository.findByName(name);

		return repository.findAll().stream()
			.filter(profile -> name.equals(profile.getName()))
			.collect(Collectors.toList());
	}
}
