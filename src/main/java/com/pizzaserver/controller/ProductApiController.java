package com.pizzaserver.controller;

import com.pizzaserver.domain.dto.ProductDto;
import com.pizzaserver.domain.dto.ProductListDto;
import com.pizzaserver.domain.dto.ResponseMessage;
import com.pizzaserver.helper.CSVHelper;
import com.pizzaserver.service.ProductService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Controller
@CrossOrigin
@RequestMapping(value="/api/product")
public class ProductApiController {
    private final ProductService productService;
    private final UserService userService;
    String PRODUCT_DATABASE_PATH ="productList.csv";

    @Autowired
    public ProductApiController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    /**
     * Api for getting productList from database
     * @return productlist in JSON
     */

    @GetMapping(value="/get-productlist")
    public ResponseEntity<ProductListDto> getProductList(){
        ProductListDto productListDto = productService.getProductList();
        return new ResponseEntity<>(productListDto, HttpStatus.OK);
    }

    /**
     * Api for downloading database in CSV file. Verifies if it's request by admin by verifying token
     * @param token admin token
     * @return product database CSV file
     * @throws IOException error saving file
     */

    @RequestMapping(value = "/get-product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getFile(
            @PathVariable("productId") String productId,
            HttpServletResponse response) {

        ProductDto productDto = productService.getProduct(productId);
        if(productDto!=null){
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }



    }

    @GetMapping(value = "/get-database", produces = "text/csv")
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

    /**
     * Api for receiving edited productlist in csv and updating it in server
     * @param file csv file
     * @param token admin token
     * @return HTTP Status
     */

    @PostMapping("/update-database")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(value="file") MultipartFile file, @RequestParam("token") String token) {
        if(userService.checkTokenAdmin(token)) {
            String message = "";

            if (CSVHelper.hasCSVFormat(file)) {
                try {
                    file.transferTo(Paths.get("productListUpdate.csv"));
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

    @PostMapping("/add-product/{token}")
    public ResponseEntity<ResponseMessage> addProduct(@RequestParam("token") String token, @RequestBody ProductDto productDto) {
        if (userService.checkTokenAdmin(token)) {
            String message = "";
            if (productService.addProduct(productDto) == true) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("OK"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
            }
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
    }

    @PostMapping("/edit-product/{token}")
    public ResponseEntity<ResponseMessage> editProduct(@RequestParam("token") String token, @RequestBody ProductDto productDto) {
        if (userService.checkTokenAdmin(token)) {
            String message = "";
            if (productService.editProduct(productDto) == true) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("OK"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
            }
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
    }

    @DeleteMapping("/edit-product/{productId}{token}")
    public ResponseEntity<ResponseMessage> deleteProduct(@RequestParam("productId") String productId, @RequestParam("token") String token) {
        if (userService.checkTokenAdmin(token)) {
            String message = "";
            if (productService.deleteProduct(productId) == true) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("OK"));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
            }
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage("Forbidden"));
    }

    @Test
    public InputStream convertToInputStream(String fileName)
            throws IOException {
        File initialFile = new File(fileName);
        InputStream targetStream = new FileInputStream(initialFile);
        return targetStream;
    }

}
