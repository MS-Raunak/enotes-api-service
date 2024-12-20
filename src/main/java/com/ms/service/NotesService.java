package com.ms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ms.dto.NotesDto;

public interface NotesService {
	Boolean saveNotes(String notes, MultipartFile file) throws Exception;
	List<NotesDto> getAllNotes();
}
