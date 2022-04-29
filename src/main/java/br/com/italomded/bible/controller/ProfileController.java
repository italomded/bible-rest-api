package br.com.italomded.bible.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.italomded.bible.form.ProfileForm;
import br.com.italomded.bible.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping
	public ResponseEntity<List<ProfileForm>> get() {
		List<ProfileForm> list = profileService.getAll().
				stream().
				map(ProfileForm::new).
				collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Long> create(@RequestBody ProfileForm form) {
		Long profileId = profileService.create(form);
		if (profileId != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(profileId);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
