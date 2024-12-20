package com.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, Integer>{

}
