package com.pizzaserver.domain.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for storing generated file parameters.
 */
public class FileData {
    private String fileName;
    private Long size;
    private ZonedDateTime creationDate;

    public FileData(Builder builder) {
        this.fileName = builder.fileName;
        this.size = builder.size;
        this.creationDate = builder.creationDate;
    }

    public FileData(String fileName, Long size, ZonedDateTime creationDate) {
        this.fileName = fileName;
        this.size = size;
        this.creationDate = creationDate;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getSize() {
        return size;
    }

    public String getCreationDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(creationDate);
    }

    public static final class Builder {
        private String fileName;
        private Long size;
        private ZonedDateTime creationDate;

        public Builder() {
        }

        public static Builder aFileData() {
            return new Builder();
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder size(Long size) {
            this.size = size;
            return this;
        }

        public Builder creationDate(ZonedDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public FileData build() {
            return new FileData(this);
        }
    }
}
