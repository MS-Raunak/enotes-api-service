package com.ms.service;

import java.util.List;

import com.ms.dto.CategoryDto;
import com.ms.dto.CategoryResponse;

public interface CategoryService {
	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> getAllCategory();
	public List<CategoryResponse> getActiveCategory();
	public CategoryDto getCategoryById(Integer id);
	public Boolean deleteCategoryById(Integer id);
}
