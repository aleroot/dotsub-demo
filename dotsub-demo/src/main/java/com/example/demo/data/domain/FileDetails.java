package com.example.demo.data.domain;

import java.time.LocalDate;

public class FileDetails {

    private long id;
    private String title;
    private String descrption;
    private LocalDate creationDate;
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetails{");
        sb.append("id=").append(id);
        sb.append(", descrption='").append(title).append('\'');
        sb.append(", first_name='").append(descrption).append('\'');
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append('}');
        return sb.toString();
    }
	
	public static class FileDetailsBuilder {
	    private long id;
	    private String title;
	    private String descrption;
	    private LocalDate creationDate;
	    private String fileName;
	    
	    public FileDetailsBuilder withId(long id) {
	    	this.id = id;
	    	return this;
	    }
	    
	    public FileDetailsBuilder withTitle(String title) {
	    	this.title = title;
	    	return this;
	    }
	    
	    public FileDetailsBuilder withDescription(String description) {
	    	this.descrption = description;
	    	return this;
	    }
	    
	    public FileDetailsBuilder withCreationDate(LocalDate creationDate) {
	    	this.creationDate = creationDate;
	    	return this;
	    }
	    
	    public FileDetailsBuilder withFileName(String fileName) {
	    	this.fileName = fileName;
	    	return this;
	    }
	    
	    public FileDetails build() {
	    	FileDetails details = new FileDetails();
	    	details.creationDate = this.creationDate;
	    	details.descrption = this.descrption;
	    	details.fileName = this.fileName;
	    	details.id = this.id;
	    	details.title = this.title;
	    	return details;
	    }
	}
}