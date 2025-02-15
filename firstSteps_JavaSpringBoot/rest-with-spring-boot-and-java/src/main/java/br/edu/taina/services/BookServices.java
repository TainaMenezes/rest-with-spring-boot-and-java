package br.edu.taina.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.edu.taina.controllers.BookController;
import br.edu.taina.data.vo.v1.BookDTO;
import br.edu.taina.exceptions.RequiredObjectIsNullException;
import br.edu.taina.exceptions.ResourceNotFoundException;
import br.edu.taina.mapper.DozerMapper;
import br.edu.taina.model.Book;
import br.edu.taina.repositories.BookRepository;

@Service
public class BookServices {
	
	private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<BookDTO> findAll() {
		logger.info("Finding all books.");
		
		var books = DozerMapper.parseListObject(repository.findAll(), BookDTO.class);
		books
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		
		return books;
	}

	public BookDTO findById(Long id) {
		logger.info("Finding one book.");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		var vo = DozerMapper.parseObject(entity, BookDTO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public BookDTO create(BookDTO book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public BookDTO update(BookDTO book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one book");
		
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one book");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
		
		repository.delete(entity);
	}

}
