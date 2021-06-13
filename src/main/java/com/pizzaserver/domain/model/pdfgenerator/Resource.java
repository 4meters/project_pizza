package com.pizzaserver.domain.model.pdfgenerator;

import com.pizzaserver.domain.dto.FileData;

import java.util.List;


public interface Resource {
    String fileName = "files.csv";

    void saveOne(FileData fileData, String path);

    List<FileData> findAll(String path);
}
