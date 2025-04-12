package com.example.Spring_Pagination.Sorting_demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentMapper studentMapper;
    // Converts each StudentModel to StudentDTO inside a Page
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepo.findAll(pageable).map(student ->
                new StudentDTO(student.getId(), student.getName(), student.getMarks()));
    }

    // For single student details (used for self link)
    public Optional<StudentDTO> getStudentById(int id) {
        return studentRepo.findById(id)
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getMarks()));
    }
}
