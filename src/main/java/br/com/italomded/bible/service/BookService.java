package br.com.italomded.bible.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.model.Book;
import br.com.italomded.bible.model.Chapter;
import br.com.italomded.bible.repository.BookRepository;
import br.com.italomded.bible.repository.ChapterRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	public Book getBook(String bookId, Pageable page) {
		Optional<Book> optBook = bookRepository.findById(bookId);
		if (optBook.isPresent()) {
			List<Chapter> chapters = chapterRepository.findByBook_Abbreviation(bookId, page);
			Book book = optBook.get();
			book.setChapters(chapters);
			return book;
		}
		return null;
	}
	
	public Boolean deleteBook(String book) {
		Optional<Book> optBook = bookRepository.findById(book);
		if (optBook.isPresent()) {
			bookRepository.delete(optBook.get());
			return true;
		} else {return false;}
	}
	
}
