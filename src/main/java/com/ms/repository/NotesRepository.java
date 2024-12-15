package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
