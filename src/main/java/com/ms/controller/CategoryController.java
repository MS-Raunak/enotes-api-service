package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dto.CategoryDto;
import com.ms.dto.CategoryResponse;
import com.ms.service.CategoryService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
		Boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			return new ResponseEntity<>("saved success", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		if(CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(allCategory, HttpStatus.OK);
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveCategory() {
		List<CategoryResponse> allCategory = categoryService.getActiveCategory();
		if(CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(allCategory, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception {
		 CategoryDto categoryDto = categoryService.getCategoryById(id);
		 
		 if (ObjectUtils.isEmpty(categoryDto)) {
				return new ResponseEntity<>("internal server error " + id, HttpStatus.NOT_FOUND);

		}
			return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
		Boolean isDeleted = categoryService.deleteCategoryById(id);
		if (isDeleted) {
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);

		}
		return new ResponseEntity<>("something error found on server", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
