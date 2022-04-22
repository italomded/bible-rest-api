package br.com.italomded.bible.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.italomded.bible.dto.StatementDTO;
import br.com.italomded.bible.model.Bible;
import br.com.italomded.bible.model.Book;
import br.com.italomded.bible.model.Chapter;
import br.com.italomded.bible.model.Verse;
import br.com.italomded.bible.repository.BookRepository;
import br.com.italomded.bible.repository.ChapterRepository;
import br.com.italomded.bible.repository.VerseRepository;

@Service
public class UpdateBibleService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired
	private VerseRepository verseRepository;
	
	@Autowired
	private BibleService bibleService;

	public void updateFromStatement(StatementDTO statement) {
		statement.prepare();
		Bible bible = bibleService.getBible();
		Book book = this.addBook(statement, bible);
		Chapter chapter = this.addChapter(statement, book);
		this.addVerse(statement, chapter, book);
	}
	
	public void updateFromFile(MultipartFile file) {
		Bible bible = bibleService.getBible();
		Book book = null;
		Chapter chapter = null;
		Boolean valid;
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = bf.readLine();
			line = line.replace("\ufeff", "");
			while (line != null) {
				valid = line.matches("[a-zA-Z0-9][a-zA-Z][a-zA-Z]\\s[0-9]+:[0-9]+\\s.*$");
				if (!valid) {
					throw new Error("Invalid line! Need to match: [Book Abbreviation (3) characters] [Chapter Number]:[Verse Number] [VerseText] without brackets");
				}
				StatementDTO statement = new StatementDTO(line);
				statement.prepare();
				if (book == null) {
					book = this.addBook(statement, bible);
					chapter = this.addChapter(statement, book);
				}
				Boolean isDifferentBook = !(book.getAbbreviation().equals(statement.getBookId()));
				if (isDifferentBook) {
					book = this.addBook(statement, bible);
					chapter = this.addChapter(statement, book);
				}
				if (chapter == null || chapter.getNumber() != statement.getChapterNumber()) {
					chapter = this.addChapter(statement, book);
				}
				this.addVerse(statement, chapter, book);
				line = bf.readLine();
			}
		} catch (IOException e) {
			throw new Error(e.getMessage());
		}
	}
	
	private Book addBook(StatementDTO statement, Bible bible) {
		Book book;
		Optional<Book> optBook = bookRepository.findById(statement.getBookId());
		if (optBook.isPresent()) {
			book = optBook.get();
		} else {
			book = new Book();
			book.setAbbreviation(statement.getBookId());
			book.setBible(bible);
			bible.getBooks().add(book);
			bibleService.updateBible(bible);
			bookRepository.save(book);
		}
		return book;
	}
	
	private Chapter addChapter(StatementDTO statement, Book book) {
		Chapter chapter;
		Optional<Chapter> optChapter = chapterRepository.getChapter(statement.getChapterNumber(), book.getAbbreviation());
		if (optChapter.isPresent()) {
			chapter = optChapter.get();
		} else {
			chapter = new Chapter();
			chapter.setNumber(statement.getChapterNumber());
			chapter.setBook(book);
			book.getChapters().add(chapter);
			chapterRepository.save(chapter);
		}
		return chapter;
	}
	
	private Verse addVerse(StatementDTO statement, Chapter chapter, Book book) {
		Verse verse;
		Optional<Verse> optVerse = verseRepository.getVerse(
				statement.getVerseNumber(), chapter.getNumber(), book.getAbbreviation());
		if (optVerse.isPresent()) {
			verse = optVerse.get();
			verse.setText(statement.getVerseText());
		} else {
			verse = new Verse();
			verse.setNumber(statement.getVerseNumber());
			verse.setChapter(chapter);
			verse.setText(statement.getVerseText());
			chapter.getVerses().add(verse);
		}
		verseRepository.save(verse);
		return verse;
	}
	
}
