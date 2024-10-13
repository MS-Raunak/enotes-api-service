package com.ms.service;

import java.util.List;

import com.ms.dto.CategoryDto;
import com.ms.dto.CategoryResponse;
import com.ms.entity.Category;

public interface CategoryService {
	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> getAllCategory();
	public List<CategoryResponse> getActiveCategory();
}
