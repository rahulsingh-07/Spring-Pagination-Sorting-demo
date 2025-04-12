package com.example.Spring_Pagination.Sorting_demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDTOAssembler studentDTOAssembler;

    // Return paginated, sorted results with HATEOAS links
    @GetMapping("/students")
    public ResponseEntity<PagedModel<EntityModel<StudentDTO>>> getAllStudent(
            Pageable pageable, // Automatically binds ?page=0&size=10&sort=name
            PagedResourcesAssembler<StudentDTO> assembler // Helps wrap DTOs with links
    ) {
        // Get page of StudentDTOs and convert to PagedModel<EntityModel<DTO>>
        return ResponseEntity.ok(
                assembler.toModel(studentService.getAllStudents(pageable), studentDTOAssembler)
        );
    }

    // Needed for the self link to work
    @GetMapping("/students/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(studentDTOAssembler.toModel(student)))
                .orElse(ResponseEntity.notFound().build());
    }

}
