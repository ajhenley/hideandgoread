package com.ajlabs.demo.repository;

import com.ajlabs.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByUsername(String username);
}
