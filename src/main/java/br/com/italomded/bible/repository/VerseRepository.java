package br.com.italomded.bible.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.italomded.bible.model.Verse;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {

	@Query("SELECT v FROM verses v WHERE v.number = :verseNumber AND v.chapter.number = :chapterNumber "
			+ "AND v.chapter.book.abbreviation = :bookId")
	public Optional<Verse> getVerse(Integer verseNumber, Integer chapterNumber, String bookId);
	
}
