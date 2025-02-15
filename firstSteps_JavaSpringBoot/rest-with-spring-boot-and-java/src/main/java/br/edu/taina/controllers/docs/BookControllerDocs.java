package br.edu.taina.controllers.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.taina.data.vo.v1.BookDTO;
import br.edu.taina.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface BookControllerDocs {

	@Operation(
			summary = "Finding By Id", 
			description = "Finds one book by your id",
			tags = {"Book"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=BookDTO.class))),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	BookDTO findById(Long id);

	@Operation(
			summary = "Finding All Books", 
			description = "Finds all books",
			tags = {"Book"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = {
								@Content(
										mediaType=MediaType.APPLICATION_JSON,
										array = @ArraySchema(schema = @Schema(implementation=BookDTO.class))
										)
							}),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	List<BookDTO> findAll();

	@Operation(
			summary = "Creating a book", 
			description = "Creates a book that doesn't exists yet.",
			tags = {"Book"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=BookDTO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	BookDTO create(BookDTO BookVO);

	@Operation(
			summary = "Updating a book", 
			description = "Updates a book.",
			tags = {"Book"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=BookDTO.class))),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	BookDTO update(BookDTO BookVO);

	@Operation(
			summary = "Deleting a book", 
			description = "Deletes a book.",
			tags = {"Book"}, 
			responses = {
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	ResponseEntity<?> delete(Long id);

}