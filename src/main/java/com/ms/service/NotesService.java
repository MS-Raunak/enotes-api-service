package com.ms.service;

import java.util.List;

import com.ms.dto.NotesDto;
import com.ms.exception.ResourceNotFoundException;

public interface NotesService {
	Boolean saveNotes(NotesDto notesDto) throws ResourceNotFoundException;
	List<NotesDto> getAllNotes();
}
