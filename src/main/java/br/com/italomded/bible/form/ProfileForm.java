package br.com.italomded.bible.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.italomded.bible.model.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileForm {
	
	@NotNull @NotBlank
	private String authority;
	
	public ProfileForm(Profile profile) {
		this.authority = profile.getAuthority();
	}
	
	public Profile toProfile() {
		Profile profile = new Profile();
		profile.setAuthority(authority);
		return profile;
	}
	
}
