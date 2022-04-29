package br.com.italomded.bible.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.italomded.bible.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
	
	@NotBlank @NotNull
	private String username;
	
	@NotBlank @NotNull
	private String password;
	
	@NotBlank @NotNull
	private String authority;
	
	public User convert() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

}
