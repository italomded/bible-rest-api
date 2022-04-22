package br.com.italomded.bible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.italomded.bible.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>  {

}
