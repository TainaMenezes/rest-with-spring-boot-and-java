package br.edu.taina.controllers.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.taina.data.vo.v1.PersonDTO;
import br.edu.taina.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PersonControllerDocs {

	@Operation(
			summary = "Finding By Id", 
			description = "Finds one person by your id",
			tags = {"People"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=PersonDTO.class))),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	PersonDTO findById(Long id);

	@Operation(
			summary = "Finding All People", 
			description = "Finds all people",
			tags = {"People"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = {
								@Content(
										mediaType=MediaType.APPLICATION_JSON,
										array = @ArraySchema(schema = @Schema(implementation=PersonDTO.class))
										)
							}),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	List<PersonDTO> findAll();

	@Operation(
			summary = "Creating a person", 
			description = "Creates a person that doesn't exists yet.",
			tags = {"People"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=PersonDTO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	PersonDTO create(PersonDTO PersonVO);

	@Operation(
			summary = "Updating a person", 
			description = "Updates a person.",
			tags = {"People"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200",
							content = @Content(schema = @Schema(implementation=PersonDTO.class))),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	PersonDTO update(PersonDTO PersonVO);

	@Operation(
			summary = "Deleting a person", 
			description = "Deletes a person.",
			tags = {"People"}, 
			responses = {
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
	ResponseEntity<?> delete(Long id);

}