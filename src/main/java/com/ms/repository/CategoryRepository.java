package com.ms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.dto.CategoryDto;
import com.ms.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByIsActiveTrue();

	Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	List<Category> findByIsActiveTrueAndIsDeletedFalse();

	Boolean existsByName(String name);

	
}
