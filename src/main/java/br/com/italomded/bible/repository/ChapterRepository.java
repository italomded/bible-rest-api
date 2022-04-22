package br.com.italomded.bible.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.italomded.bible.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	
	@Query("SELECT c FROM chapters c JOIN FETCH c.verses v WHERE c.number = :chapterNumber AND c.book.abbreviation = :bookId")
	public Optional<Chapter> getChapter(Integer chapterNumber, String bookId);
	
	public List<Chapter> findByBook_Abbreviation(String bookId, Pageable page);
	
}
