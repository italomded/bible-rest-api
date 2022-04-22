package br.com.italomded.bible.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="chapters")
public class Chapter {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer number;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chapter", cascade = CascadeType.ALL)
	@OrderBy("number ASC")
	private List<Verse> verses = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Book book;
	
}
