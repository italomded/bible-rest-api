package br.com.italomded.bible.dto;

import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class StatementDTO {
	
	@NonNull
	@Pattern(regexp="[a-zA-Z0-9][a-zA-Z][a-zA-Z]\\s[0-9]+:[0-9]+\\s.*$",
	message = "Need to match: [Book Abbreviation (3) characters] [Chapter Number]:[Verse Number] [VerseText] without brackets")
	String statement;
	
	private String bookId;
	private Integer chapterNumber;
	private Integer verseNumber;
	private String verseText;

	public void prepare() {
		String[] stmArray = statement.split(" ", 3);
		this.bookId = stmArray[0];
		this.verseText = stmArray[2];
		stmArray = stmArray[1].split(":");
		this.chapterNumber = Integer.parseInt(stmArray[0]);
		this.verseNumber = Integer.parseInt(stmArray[1]);
		System.out.println(this);
	}
	
}
