package com.pizzaserver.helper;

import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
  public static String TYPE = "text/csv";
  public static String TYPE2 = "application/vnd.ms-excel";
  static String[] HEADERs = {"Id", "Title", "Description", "Published"};

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType()) && !TYPE2.equals(file.getContentType())) {
      return false;
    }

    return true;
  }
}