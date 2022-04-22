package br.com.italomded.bible.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.model.Verse;
import br.com.italomded.bible.repository.VerseRepository;

@Service
public class VerseService {

	@Autowired
	private VerseRepository verseRepository;
	
	public Optional<Verse> getVerse(String verseNumber, String chapterNumber, String bookId) {
		return verseRepository.getVerse(
				Integer.parseInt(verseNumber), Integer.parseInt(chapterNumber), bookId);
	}
	
	public Boolean deleteVerse(String verseNumber, String chapterNumber, String bookId) {
		Optional<Verse> optVerse = verseRepository.getVerse(
				Integer.parseInt(verseNumber), Integer.parseInt(chapterNumber), bookId);
		if (optVerse.isPresent()) {
			verseRepository.delete(optVerse.get());
			return true;
		} else {return false;}
	}
	
}
