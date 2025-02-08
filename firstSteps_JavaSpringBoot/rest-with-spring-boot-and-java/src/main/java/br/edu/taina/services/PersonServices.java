package br.edu.taina.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.taina.data.vo.v1.PersonVO;
import br.edu.taina.data.vo.v2.PersonVOv2;
import br.edu.taina.exceptions.ResourceNotFoundException;
import br.edu.taina.mapper.DozerMapper;
import br.edu.taina.mapper.custom.PersonMapper;
import br.edu.taina.model.Person;
import br.edu.taina.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper personMapper;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people.");
		
		return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person.");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public PersonVOv2 createV2(PersonVOv2 person) {
		logger.info("Creating one person");
		
		var entity = personMapper.convertVoToEntity(person);
		var vo = personMapper.convertEntityToVo(repository.save(entity));
		
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person");
		
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGenre(person.getGenre());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		repository.delete(entity);
	}

}
