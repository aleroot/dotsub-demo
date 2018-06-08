package com.example.demo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.data.domain.FileDetails;

@Service
public class DatabaseFileDetailsService implements FileDetailService {

	private FileDetailsRepository repository;
	
	@Autowired
	public DatabaseFileDetailsService(FileDetailsRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean save(FileDetails details) {
		return repository.add(details);
	}

}
