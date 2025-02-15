package br.edu.taina.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.edu.taina.controllers.PersonController;
import br.edu.taina.data.vo.v1.PersonDTO;
import br.edu.taina.exceptions.RequiredObjectIsNullException;
import br.edu.taina.exceptions.ResourceNotFoundException;
import br.edu.taina.mapper.DozerMapper;
import br.edu.taina.mapper.custom.PersonMapper;
import br.edu.taina.model.Person;
import br.edu.taina.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper personMapper;
	
	public List<PersonDTO> findAll() {
		logger.info("Finding all people.");
		
		var persons = DozerMapper.parseListObject(repository.findAll(), PersonDTO.class);
		persons
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return persons;
	}

	public PersonDTO findById(Long id) {
		logger.info("Finding one person.");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		var vo = DozerMapper.parseObject(entity, PersonDTO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PersonDTO create(PersonDTO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public PersonDTO update(PersonDTO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person");
		
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGenre(person.getGenre());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		repository.delete(entity);
	}

}
