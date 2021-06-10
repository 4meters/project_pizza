package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.ErrorDto;
import com.pizzaserver.domain.dto.ErrorMessage;
import com.pizzaserver.domain.dto.FileData;
import com.pizzaserver.domain.dto.UserDataDto;
import com.pizzaserver.service.FileService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PdfGeneratorApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfGeneratorApiController.class);

    private static final String PATH = "D:/pdf/";

    @Autowired
    private FileService fileService;

    @CrossOrigin
    @GetMapping(value = "/server-test")
    public ResponseEntity<Map<String, String>> serverTest() {
        LOGGER.info("--- run server status");

        Map<String, String> serverTestMessage = new HashMap<>();
        serverTestMessage.put("server-status", "RUN :-)");

        return new ResponseEntity<>(serverTestMessage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/files/find-all")
    public ResponseEntity<List<FileData>> findAll() {
        LOGGER.info("--- find all");

        List<FileData> files = fileService.findAll(PATH);

        return files.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(files, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/files/create-file")
    public ResponseEntity<?> createFile(@RequestBody UserDataDto userDataDto) {
        LOGGER.info("--- create pdf file for user: {}", userDataDto.getFirstName());

        FileData fileData = fileService.createFile(userDataDto, PATH);

        ErrorDto errorMessage = new ErrorDto(ErrorMessage.ERROR_PATH.getErrorMessage());
        HttpStatus errorCode = ErrorMessage.ERROR_PATH.getErrorCode();

        return fileData != null ?
                new ResponseEntity<>(fileData, HttpStatus.CREATED) :
                new ResponseEntity<>(errorMessage, errorCode);
    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            // get your file as InputStream

            InputStream is = convertToInputStream(fileName);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            LOGGER.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
    @Test
    public InputStream convertToInputStream(String fileName)
            throws IOException {
        File initialFile = new File(PATH, fileName + ".pdf");
        InputStream targetStream = new FileInputStream(initialFile);
        return targetStream;
    }

}
