package br.edu.taina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.taina.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
