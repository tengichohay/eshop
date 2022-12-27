package com.example.storeservice.repository;

import com.example.storeservice.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
}
