package com.ajlabs.demo.repository;

import com.ajlabs.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByUsername(String username);
    Student findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
    Iterable<Student> findAllByUsertype(String usertype);
    Iterable<Student> findAllByClassidAndUsertype(Long id, String usertype);
}
