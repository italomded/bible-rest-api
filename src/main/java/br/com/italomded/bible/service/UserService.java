package br.com.italomded.bible.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.italomded.bible.config.security.TokenService;
import br.com.italomded.bible.model.Profile;
import br.com.italomded.bible.model.User;
import br.com.italomded.bible.repository.ProfileRepository;
import br.com.italomded.bible.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository; 
	
	@PostMapping
	public String login(UsernamePasswordAuthenticationToken loginData) {
		try {
			Authentication authentication = authManager.authenticate(loginData);
			return tokenService.gerarToken(authentication);
		} catch (BadCredentialsException exception) {
			return null;
		}
	}
	
	public String createUser(User user) {
		Optional<User> optUser = userRepository.findById(user.getUsername());
		if (optUser.isPresent()) {
			return null;
		} else {
			List<Profile> profileList = profileRepository.findAll();
			Profile profile;
			if (profileList.isEmpty()) {
				profile = new Profile();
				profile.setAuthority("ADM");
				profileRepository.save(profile);
			} else {
				profile = profileList.get(0);
			}
			profile.getUsersAuthority().add(user);
			user.getProfiles().add(profile);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			
			userRepository.save(user);
			return user.getUsername();
		}
	}
	
}
