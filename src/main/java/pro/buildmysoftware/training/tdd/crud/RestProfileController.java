package pro.buildmysoftware.training.tdd.crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/profiles")
public class RestProfileController {

	private ProfileService profileService;

	public RestProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping
	Collection<Profile> findAllProfiles() {
		return profileService.findAllProfiles();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	void saveProfile(@RequestBody Profile profile) {
		profileService.save(profile);
	}
}
