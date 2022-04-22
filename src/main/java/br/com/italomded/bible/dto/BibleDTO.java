package br.com.italomded.bible.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.italomded.bible.model.Bible;
import lombok.Getter;

@Getter
public class BibleDTO {
	
	private String brief;
	private List<String> books = new ArrayList<>();
	
	public BibleDTO(Bible bible) {
		this.brief = bible.getBrief();
		bible.getBooks().forEach(bk -> this.books.add(bk.getAbbreviation()));
	}

}
