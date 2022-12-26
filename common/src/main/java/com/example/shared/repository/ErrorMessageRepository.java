package com.example.shared.repository;

import com.example.shared.entity.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage, Integer> {

	ErrorMessage findByKeyAndLocale(String key, String locale);

}
