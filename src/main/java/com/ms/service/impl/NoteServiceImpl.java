package com.ms.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ms.dto.NotesDto;
import com.ms.dto.NotesDto.CategoryDto;
import com.ms.entity.Notes;
import com.ms.exception.ResourceNotFoundException;
import com.ms.repository.CategoryRepository;
import com.ms.repository.NotesRepository;
import com.ms.service.NotesService;


@Service
public class NoteServiceImpl implements NotesService{

	@Autowired
	ModelMapper mapper;
	@Autowired
	NotesRepository notesRepo;
	@Autowired
	CategoryRepository categoryRepo;
	
	@Override
	public Boolean saveNotes(NotesDto notesDto) throws ResourceNotFoundException {
		//Category Validation
		checkCategoryExist(notesDto.getCategory());
		
		Notes note = mapper.map(notesDto, Notes.class);
		Notes saveNote = notesRepo.save(note);
		
		if (!ObjectUtils.isEmpty(saveNote)) {
			return true;
		}
		return false;
	}

	private void checkCategoryExist(CategoryDto category) throws ResourceNotFoundException {
		categoryRepo.findById(category.getId()).orElseThrow(()-> new ResourceNotFoundException("Invalid category id"));
	}

	@Override
	public List<NotesDto> getAllNotes() {
		return notesRepo.findAll().stream().map(note->mapper.map(note, NotesDto.class)).toList();
	}

}
