package br.com.italomded.bible.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.italomded.bible.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	public Optional<Profile> findByAuthority(String authority);

}
