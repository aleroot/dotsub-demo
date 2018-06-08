package com.example.demo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.data.FileDetailService;
import com.example.demo.data.domain.FileDetails;
import com.example.demo.storage.StorageFileNotFoundException;
import com.example.demo.storage.StorageService;

@Controller
public class RestUploadFileController {
	private final StorageService storageService;
	private final FileDetailService dataService;
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    return multipartResolver;
	}
	
    @Autowired
    public RestUploadFileController(StorageService storageService, FileDetailService dataService) {
        this.storageService = storageService;
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(RestUploadFileController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("title") String title,
    		@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate creationDate,
    		@RequestParam("desc") String description,
            RedirectAttributes redirectAttributes) {
    	/**
    	 * The saving of the file is now just 
    	 * working based on filename, a possible improvement 
    	 * would be to calculate an hash and then save the 
    	 * files by hash, keeping a reference to it ...
    	 */
        dataService.save(new FileDetails.FileDetailsBuilder()
        		.withCreationDate(creationDate)
        		.withFileName(storageService.store(file))
        		.withDescription(description)
        		.withTitle(title)
        		.build()
        		);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
