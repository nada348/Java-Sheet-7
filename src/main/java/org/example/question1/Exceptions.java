package org.example.question1;
class StorageException extends Exception {

    public StorageException(String message) {
        super(message);
    }
}

class CorruptedFileException extends StorageException {

    public CorruptedFileException(String message) {
        super(message);
    }
}