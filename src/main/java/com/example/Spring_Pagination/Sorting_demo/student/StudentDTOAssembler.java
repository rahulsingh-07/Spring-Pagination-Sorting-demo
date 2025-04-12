package com.example.Spring_Pagination.Sorting_demo.student;
//This class helps to convert a DTO to a HATEOAS EntityModel and add links like /students/1.

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


// This class wraps StudentDTO with HATEOAS links
@Component
public class StudentDTOAssembler implements RepresentationModelAssembler<StudentDTO, EntityModel<StudentDTO>> {

    @Override
    public EntityModel<StudentDTO> toModel(StudentDTO dto) {
        // Adds a link like "_links": { "self": "/students/1" }
        return EntityModel.of(dto,
                linkTo(methodOn(StudentController.class).getStudentById(dto.getId())).withSelfRel()
        );
    }
}
