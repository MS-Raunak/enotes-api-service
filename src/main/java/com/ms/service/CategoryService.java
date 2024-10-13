package com.ms.service;

import java.util.List;

import com.ms.entity.Category;

public interface CategoryService {
	public Boolean saveCategory(Category category);
	public List<Category> getAllCategory();
}
