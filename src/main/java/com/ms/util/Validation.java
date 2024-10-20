package com.ms.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ms.dto.CategoryDto;
import com.ms.exception.ValidationException;


//Custom validation
@Component
public class Validation {
	public void categoryValidation(CategoryDto categoryDto) {
		//LinkedHashMap making sure that a single field can have multiple errors, so allow duplication and preserve order as well. 
		Map<String, Object> errors = new LinkedHashMap<String, Object>();
		
		//category object validation
		if (ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("category object/json should not be empty");
		}
		else {
			//category name validation
			if (ObjectUtils.isEmpty(categoryDto.getName())) {
				errors.put("name", "name should not be empty or null");
			}else {
				if (categoryDto.getName().length() < 3) {
					errors.put("name", "name must be greater than or equal to 3");
				}
				if (categoryDto.getName().length() > 100) {
					errors.put("name", "name must be less than or equal to 100");
				}
			}
			
			//category description validation
			if (ObjectUtils.isEmpty(categoryDto.getDescription())) {
				errors.put("description", "description should not be empty or null");
			}
			
			//category isActive validation
			if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
				errors.put("isActive", "isActive filed must not be empty or null");
			}else {
				if (categoryDto.getIsActive() != Boolean.TRUE.booleanValue() && categoryDto.getIsActive() != Boolean.FALSE .booleanValue()) {
					errors.put("isActive", "isActive field must has boolean value only");
				}
			}
		}
		
		//checking map(errors) object, if it is not empty then it means it is having error inside it
		if (!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
		
		
		
		
	}

}
