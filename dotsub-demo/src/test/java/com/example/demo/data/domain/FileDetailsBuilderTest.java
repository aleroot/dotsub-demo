package com.example.demo.data.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.demo.data.domain.FileDetails.FileDetailsBuilder;

public class FileDetailsBuilderTest {

	@Test
	public void testBuild() {
		FileDetailsBuilder builder = new FileDetailsBuilder();
		final FileDetails fdt = builder.withId(1L).withDescription("Test").withTitle("Tester").withFileName("file.txt").build();
		
		assertEquals(1L, fdt.getId());
		assertEquals("Test", fdt.getDescrption());
		assertEquals("Tester", fdt.getTitle());
		assertEquals("file.txt", fdt.getFileName());
	}

}
