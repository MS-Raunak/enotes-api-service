package com.ms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ms.dto.CategoryDto;
import com.ms.dto.CategoryResponse;
import com.ms.entity.Category;
import com.ms.exception.ResourceNotFoundException;
import com.ms.repository.CategoryRepository;
import com.ms.service.CategoryService;
import com.ms.util.Validation;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	Validation validation;
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		//checking validation
		validation.categoryValidation(categoryDto);
		
		Category category = mapper.map(categoryDto, Category.class);
		
		if (ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
//			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
		}
		else {
			updateCategory(category);
		}
		
		
		Category savedCategory = categoryRepo.save(category);
		
		if (ObjectUtils.isEmpty(savedCategory)) {
			return false;
		}
		return true;
	}

	private void updateCategory(Category category) {
		Optional<Category> findById = categoryRepo.findById(category.getId());
		
		if (findById.isPresent()) {
			Category existCategory = findById.get();
			
			category.setCreatedBy(existCategory.getId());
			category.setCreatedOn(existCategory.getCreatedOn());
			category.setIsDeleted(existCategory.getIsDeleted());
//			category.setUpdatedBy(1);
//			category.setUpdatedOn(new Date());
		}
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
		List<CategoryDto> categoryDtoList = categories.stream().map(category -> mapper.map(category, CategoryDto.class)).toList();
		
		return categoryDtoList;
	}
	
	
	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepo.findByIsActiveTrue();
		List<CategoryResponse> categoryList = categories.stream().map(category -> mapper.map(category, CategoryResponse.class)).toList();
		
		return categoryList;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		Category category = categoryRepo.findByIdAndIsDeletedFalse(id)
				.orElseThrow( () -> new ResourceNotFoundException("category not found with id " + id));
		
		if (!ObjectUtils.isEmpty(category)) {
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		
		Optional<Category> categoryOpt = categoryRepo.findById(id);
		
		
		if (categoryOpt.isPresent()) {
			Category category = categoryOpt.get();
			category.setIsDeleted(true);
			return true;
		}
		return false;
	}

}
