package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
