package com.example.Spring_Pagination.Sorting_demo.student;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentModel toEntity(StudentDTO studentDTO);
    StudentDTO toDto(StudentModel studentModel);
}
