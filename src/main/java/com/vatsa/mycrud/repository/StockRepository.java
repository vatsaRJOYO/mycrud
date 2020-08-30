package com.vatsa.mycrud.repository;

import com.vatsa.mycrud.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository< Stock, Long> {
    
}