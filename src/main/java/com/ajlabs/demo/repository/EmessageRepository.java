package com.ajlabs.demo.repository;

import com.ajlabs.demo.model.Emessage;
import org.springframework.data.repository.CrudRepository;

public interface EmessageRepository extends CrudRepository<Emessage, Long> {
}
