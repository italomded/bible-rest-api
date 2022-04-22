package br.com.italomded.bible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.italomded.bible.model.Bible;

@Repository
public interface BibleRepository extends JpaRepository<Bible, Long> {

}
