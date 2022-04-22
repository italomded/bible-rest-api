package br.com.italomded.bible.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.model.Chapter;
import br.com.italomded.bible.repository.ChapterRepository;

@Service
public class ChapterService {
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	public Optional<Chapter> getChapter(String bookId, String chapterId) {
		return chapterRepository.getChapter(Integer.parseInt(chapterId), bookId);
	}
	
	public Boolean deleteChapter(String bookId, String chapterNumber) {
		Optional<Chapter> optChapter = chapterRepository.getChapter(Integer.parseInt(chapterNumber), bookId);
		if (optChapter.isPresent()) {
			chapterRepository.delete(optChapter.get());
			return true;
		} else {return false;}
	}
	
}
