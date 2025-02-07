package br.edu.taina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.taina.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
