package com.pizzaserver.service;

import com.pizzaserver.domain.dto.FileData;
import com.pizzaserver.domain.dto.UserDataDto;

import java.util.List;

public interface FileService {

    FileData createFile(UserDataDto userDataDto, String path);

    List<FileData> findAll(String path);
}
