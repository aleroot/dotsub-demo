package com.example.demo.storage;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 6104082427061265833L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
