package com.ajlabs.demo.repository;

import com.ajlabs.demo.model.Classroom;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    Iterable<Classroom> findAllByIdAndPasscode(Long id, String passcode);
}

