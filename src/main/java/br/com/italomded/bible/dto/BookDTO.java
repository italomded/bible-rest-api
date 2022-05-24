package br.com.italomded.bible.dto;

import org.springframework.data.domain.Page;

import br.com.italomded.bible.model.Book;
import lombok.Getter;

@Getter
public class BookDTO {

	private String abbreviation;
	private String brief;
	private Page<ChapterDTO> chapters;
	
	public BookDTO(Book book, Page<ChapterDTO> chapterPage) {
		this.abbreviation = book.getAbbreviation();
		this.brief = book.getBrief();
		this.chapters = chapterPage;
	}
	
}
