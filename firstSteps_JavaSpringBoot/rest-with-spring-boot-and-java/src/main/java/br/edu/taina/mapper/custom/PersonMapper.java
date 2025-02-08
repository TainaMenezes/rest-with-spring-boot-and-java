package br.edu.taina.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.edu.taina.data.vo.v2.PersonVOv2;
import br.edu.taina.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOv2 convertEntityToVo(Person person) {
		PersonVOv2 vo = new PersonVOv2();
		
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGenre(person.getGenre());
		vo.setBirthday(new Date());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOv2 person) {
		Person entity = new Person();
		
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGenre(person.getGenre());
		//entity.setBirthday(new Date());
		
		return entity;
	}
}
