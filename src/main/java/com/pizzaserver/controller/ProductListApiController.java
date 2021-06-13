package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.dto.ResponseMessage;
import com.pizzaserver.helper.CSVHelper;
import com.pizzaserver.service.ProductListService;
import com.pizzaserver.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Controller
@RequestMapping(value="/api")
public class ProductListApiController {
    private final ProductListService productListService;
    private final UserService userService;
    String PRODUCT_DATABASE_PATH ="productList.csv";
    //String PRODUCT_DATABASE_PATH ="chuck.pdf";
    @Autowired
    public ProductListApiController(ProductListService productListService, UserService userService) {
        this.productListService = productListService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(value="/get-productlist")
    public ResponseEntity<ProductListDto> getProductList(){
        ProductListDto productListDto = productListService.getProductList();
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }


    /*@GetMapping(value="/get-productdatabase")
    public ResponseEntity<ProductListDto> getProductList(){
        ProductListDto productListDto = productListService.getProductList();
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }*/
    /*@CrossOrigin
    @GetMapping(value = "/get-productdatabase")
    public void getFile(HttpServletResponse response) {
        try {
            // get your file as InputStream

            InputStream is = convertToInputStream(PRODUCT_DATABASE_PATH);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            //LOGGER.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

    }*/
    @CrossOrigin
    @GetMapping(value = "/get-productdatabase", produces = "text/csv")
    public ResponseEntity<Resource> exportCSV(@RequestParam(value="token") String token) throws IOException {
        if(userService.checkTokenAdmin(token)){
            InputStream is = convertToInputStream(PRODUCT_DATABASE_PATH);
            InputStreamResource inputStreamResource = new InputStreamResource(is);

            String csvFileName = "productList.csv";

            //setting HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
            //defining content type as csv
            headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

            return new ResponseEntity<>(
                    inputStreamResource,
                    headers,
                    HttpStatus.OK
            );
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @CrossOrigin
    @PostMapping("/update-productdatabase")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(value="file") MultipartFile file, @RequestParam("token") String token) {
        if(userService.checkTokenAdmin(token)) {
            String message = "";

            if (CSVHelper.hasCSVFormat(file)) {
                try {
                    file.transferTo(Paths.get("test.csv"));
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }

            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
    }

    @Test
    public InputStream convertToInputStream(String fileName)
            throws IOException {
        File initialFile = new File(fileName);
        InputStream targetStream = new FileInputStream(initialFile);
        return targetStream;
    }

}
