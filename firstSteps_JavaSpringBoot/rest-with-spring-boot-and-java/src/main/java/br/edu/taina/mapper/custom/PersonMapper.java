package br.edu.taina.mapper.custom;

import org.springframework.stereotype.Service;

import br.edu.taina.data.vo.v1.PersonDTO;
import br.edu.taina.model.Person;

@Service
public class PersonMapper {
	
	public PersonDTO convertEntityToVo(Person person) {
		PersonDTO vo = new PersonDTO();
		
		vo.setKey(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGenre(person.getGenre());
		//vo.setBirthday(new Date());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonDTO person) {
		Person entity = new Person();
		
		entity.setId(person.getKey());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGenre(person.getGenre());
		//entity.setBirthday(new Date());
		
		return entity;
	}
}
