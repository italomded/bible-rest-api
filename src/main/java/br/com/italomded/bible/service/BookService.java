package br.com.italomded.bible.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.dto.BookDTO;
import br.com.italomded.bible.dto.ChapterDTO;
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
	
	public BookDTO getBook(String bookId, Pageable page) {
		Optional<Book> optBook = bookRepository.findById(bookId);
		if (optBook.isPresent()) {
			Page<Chapter> chapters = chapterRepository.findByBook_Abbreviation(bookId, page);
			Page<ChapterDTO> chaptersDTO = chapters.map(ChapterDTO::new);
			Book book = optBook.get();
			BookDTO returnBook = new BookDTO(book, chaptersDTO);
			return returnBook;
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
