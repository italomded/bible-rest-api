package br.com.italomded.bible.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="verses")
public class Verse {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = false, length = 500)
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Chapter chapter;
	
}
