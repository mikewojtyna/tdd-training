package pro.buildmysoftware.training.tdd.crud;

import org.springframework.stereotype.Service;

import java.util.Collection;

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
}
