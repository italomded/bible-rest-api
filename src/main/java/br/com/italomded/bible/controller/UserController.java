package br.com.italomded.bible.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.italomded.bible.dto.TokenDTO;
import br.com.italomded.bible.form.LoginForm;
import br.com.italomded.bible.form.SignInForm;
import br.com.italomded.bible.service.UserService;

@RestController
@RequestMapping("user")	
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginForm form) {
		String token = userService.login(form.convert());
		if (token != null) {
			return ResponseEntity.ok().body(new TokenDTO("Bearer", token));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("create")
	@Transactional
	public ResponseEntity<?> createUser(@RequestBody @Valid SignInForm form) {
		String username = userService.createUser(form.converter());
		if (username != null) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
