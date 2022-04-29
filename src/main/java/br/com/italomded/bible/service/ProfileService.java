package br.com.italomded.bible.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.form.ProfileForm;
import br.com.italomded.bible.model.Profile;
import br.com.italomded.bible.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	
	public List<Profile> getAll() {
		return profileRepository.findAll();
	}
	
	public Long create(ProfileForm form) {
		Profile profile = form.toProfile();
		try {
		profileRepository.save(profile);
		} catch (Exception exception) {
			return null;
		}
		return profile.getId();
	}
	
	
}
