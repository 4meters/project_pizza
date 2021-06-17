package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.ErrorDto;
import com.pizzaserver.domain.dto.ErrorMessage;
import com.pizzaserver.domain.dto.FileData;
import com.pizzaserver.domain.dto.UserDataDto;
import com.pizzaserver.service.FileService;
import com.pizzaserver.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

    private static final String PATH = "pdf/";

    private FileService fileService;

    private UserService userService;

    PdfGeneratorApiController(FileService fileService, UserService userService){
        this.fileService=fileService;
        this.userService=userService;
    }

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

    /**
     * Api for request to generate pdf receipt
     * @param userDataDto firstname, lastname, orderlist, token
     * @return filename of generated pdf
     */
    @CrossOrigin
    @PostMapping(value = "/files/create-file") //Proceed Order
    public ResponseEntity<?> createFile(@RequestBody UserDataDto userDataDto) {
        LOGGER.info("--- create pdf file for user: {}", userDataDto.getFirstName());

        if(userService.checkTokenUser(userDataDto.getToken())) {
            FileData fileData = fileService.createFile(userDataDto, PATH);

            ErrorDto errorMessage = new ErrorDto(ErrorMessage.ERROR_PATH.getErrorMessage());
            HttpStatus errorCode = ErrorMessage.ERROR_PATH.getErrorCode();

            return fileData != null ?
                    new ResponseEntity<>(fileData, HttpStatus.CREATED) :
                    new ResponseEntity<>(errorMessage, errorCode);
        }
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    /**
     * Api for downloading pdf file with receipt
     * @param fileName name of file to download
     * @param token user login token
     * @param response used for detailing error message
     * @return pdf file
     */
    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile(
            @PathVariable("file_name") String fileName, @RequestParam String token,
            HttpServletResponse response) {
        if(userService.checkTokenUser(token)) {
            try {
                InputStream is = convertToInputStream(fileName);

                InputStreamResource inputStreamResource = new InputStreamResource(is);

                //setting HTTP headers
                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName+".pdf");
                //defining content type as pdf
                headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");

                return new ResponseEntity<Resource>(inputStreamResource, headers, HttpStatus.OK);

            } catch (IOException ex) {
                LOGGER.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
    @Test
    public InputStream convertToInputStream(String fileName)
            throws IOException {
        File initialFile = new File(PATH, fileName + ".pdf");
        InputStream targetStream = new FileInputStream(initialFile);
        return targetStream;
    }

}
