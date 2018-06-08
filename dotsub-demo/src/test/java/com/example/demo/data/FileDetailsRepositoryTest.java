package com.example.demo.data;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.domain.FileDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileDetailsRepositoryTest {

	@Autowired
	FileDetailsRepository repo;
	
	@Test
	public void test() {
		assertNotNull(repo);
		
		FileDetails details = new FileDetails();
		details.setFileName("file.txt");
		details.setTitle("The title");
		
		assertTrue(repo.add(details));
		final List<FileDetails> items = repo.all();
		assertEquals(1, items.size());
		final FileDetails detail = items.get(0);
		assertEquals(1L, detail.getId());
		assertEquals("file.txt", detail.getFileName());
	}

}
