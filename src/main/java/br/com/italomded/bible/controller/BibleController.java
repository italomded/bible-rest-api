package br.com.italomded.bible.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.italomded.bible.dto.BibleDTO;
import br.com.italomded.bible.dto.BookDTO;
import br.com.italomded.bible.dto.ChapterDTO;
import br.com.italomded.bible.dto.ResponseDTO;
import br.com.italomded.bible.dto.StatementDTO;
import br.com.italomded.bible.dto.VerseDTO;
import br.com.italomded.bible.model.Book;
import br.com.italomded.bible.model.Chapter;
import br.com.italomded.bible.model.Verse;
import br.com.italomded.bible.service.BibleService;
import br.com.italomded.bible.service.BookService;
import br.com.italomded.bible.service.ChapterService;
import br.com.italomded.bible.service.UpdateBibleService;
import br.com.italomded.bible.service.VerseService;

@Controller
@RequestMapping("bible")
public class BibleController {
	
	@Autowired
	private UpdateBibleService updateBibleService;
	
	@Autowired
	private VerseService verseService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BibleService bibleService;

	@PutMapping
	@Transactional
	public ResponseEntity<Object> putStatement(@RequestBody @Valid StatementDTO statement) {
		updateBibleService.updateFromStatement(statement);
		return ResponseEntity.status(201).build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Object> postFile(@RequestParam MultipartFile file) {
		updateBibleService.updateFromFile(file);
		return ResponseEntity.status(201).build();
	}
	
	@GetMapping("{book}/{chapter}/{verse}")
	public ResponseEntity<ResponseDTO<VerseDTO>> getVerse(
			@PathVariable String book, @PathVariable String chapter, @PathVariable String verse) {
		Optional<Verse> optVerse = verseService.getVerse(verse, chapter, book.toUpperCase());
		if (optVerse.isPresent()) {
			ResponseDTO<VerseDTO> responseDTO = new ResponseDTO<>(new VerseDTO(optVerse.get()));
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("{book}/{chapter}")
	public ResponseEntity<ResponseDTO<ChapterDTO>> getChapter(@PathVariable String book, @PathVariable String chapter) {
		Optional<Chapter> optChapter = chapterService.getChapter(book.toUpperCase(), chapter);
		if (optChapter.isPresent()) {
			ResponseDTO<ChapterDTO> responseDTO = new ResponseDTO<>(new ChapterDTO(optChapter.get()));
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("{book}")
	public ResponseEntity<ResponseDTO<BookDTO>> getBook(
			@PathVariable String book,
			@PageableDefault(page = 0, size = 5) Pageable page
			) {
		Book bookE = bookService.getBook(book.toUpperCase(), page);
		if (bookE != null) {
			ResponseDTO<BookDTO> responseDTO = new ResponseDTO<>(new BookDTO(bookE));
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<ResponseDTO<BibleDTO>> getBible() {
		BibleDTO bible = new BibleDTO(bibleService.getBible());
		ResponseDTO<BibleDTO> responseDTO = new ResponseDTO<>(bible);
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping("{book}/{chapter}/{verse}")
	public ResponseEntity<?> deleteVerse(
			@PathVariable String book, @PathVariable String chapter, @PathVariable String verse) {
		Boolean sucess = verseService.deleteVerse(verse, chapter, book);
		if (sucess) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{book}/{chapter}")
	public ResponseEntity<?> deleteChapter(@PathVariable String book, @PathVariable String chapter) {
		Boolean sucess = chapterService.deleteChapter(book, chapter);
		if (sucess) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{book}")
	public ResponseEntity<?> deleteBook(@PathVariable String book) {
		Boolean sucess = bookService.deleteBook(book);
		if (sucess) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
