package br.com.italomded.bible.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.italomded.bible.model.Bible;
import br.com.italomded.bible.repository.BibleRepository;

@Service
public class BibleService {

	@Autowired
	private BibleRepository bibleRepository;
	
	public Bible getBible() {
		List<Bible> bibleList = bibleRepository.findAll();
		if (bibleList.isEmpty()) {
			Bible bible = new Bible();
			bible = this.updateBible(bible);
			return bible;
		} else {
			return bibleList.get(0);
		}
	}
	
	public Bible updateBible(Bible bible) {
		bibleRepository.save(bible);
		return bible;
	}
	
}
