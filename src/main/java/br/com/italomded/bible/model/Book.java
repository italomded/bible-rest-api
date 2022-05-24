package br.com.italomded.bible.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="books")
public class Book {

	@Id	
	@Column(length = 3)
	private String abbreviation;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Chapter> chapters = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Bible bible;
	
	@Column(length = 500)
	private String brief;

}
