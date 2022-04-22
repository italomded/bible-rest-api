package br.com.italomded.bible.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.italomded.bible.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginForm {

	@NotBlank @NotNull
	private String username;
	
	@NotBlank @NotNull
	private String password;
	
	public LoginForm(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	public UsernamePasswordAuthenticationToken convert() {
		UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(this.username, this.password);
		return loginData;
	}
	
}
