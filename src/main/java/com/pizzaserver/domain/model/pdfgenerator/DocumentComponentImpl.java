package com.pizzaserver.domain.model.pdfgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.pizzaserver.domain.dto.CheckoutCalculatedDto;
import com.pizzaserver.domain.dto.UserDataDto;
import com.pizzaserver.domain.model.CheckoutCalculate;
import com.pizzaserver.domain.object.ProductOnReceipt;
import com.pizzaserver.service.CheckoutService;
import com.pizzaserver.service.impl.CheckoutServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

@Component
public class DocumentComponentImpl implements DocumentComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentComponentImpl.class);
    DecimalFormat decimalFormat=new DecimalFormat("0.00");
    private CheckoutService checkoutService;

    @Override
    public void createDocument(UserDataDto userDataDto, String fileDestination) {
        try {



            checkoutService=new CheckoutServiceImpl();
            CheckoutCalculate checkoutCalculate=new CheckoutCalculate(checkoutService.checkoutOrderListDecode(userDataDto.getOrderList()));
            CheckoutCalculatedDto checkoutCalculatedDto = checkoutCalculate.getCheckoutCalculatedDto();
            ArrayList<ProductOnReceipt> productOnReceiptList = checkoutCalculate.getCheckoutProducts();



            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDestination));
            document.open();

            //header
            Paragraph p=new Paragraph();
            p.add("Pizzerria");
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            p.clear();
            document.add(new Paragraph("\n"));
            document.add(new LineSeparator());
            document.add(new Paragraph("\n"));

            //table dimensions and column names
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{8, 2, 1, 2});
            table.addCell(createCell("Nazwa produktu", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("Rozmiar", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("Ilość", 2, Element.ALIGN_CENTER));
            table.addCell(createCell("Koszt", 2, Element.ALIGN_CENTER));


            //table content
            for(ProductOnReceipt productOnReceipt : productOnReceiptList){
                Double cost = Double.parseDouble(productOnReceipt.getCost());
                table.addCell(createCell(getProductNameForCell(productOnReceipt), 1, Element.ALIGN_LEFT));
                table.addCell(createCell(productOnReceipt.getSize(), 1, Element.ALIGN_CENTER));
                table.addCell(createCell(productOnReceipt.getCount(), 1, Element.ALIGN_RIGHT));
                table.addCell(createCell((decimalFormat.format(cost)).replace('.',',')+"zł", 1, Element.ALIGN_RIGHT));
            }

            document.add(table);
            table.deleteBodyRows();

            //table - total cost and discounts of order
            PdfPTable table2 = new PdfPTable(3);
            table2.setWidths(new int[]{8, 3, 2});
            double value;

            value=Double.parseDouble(checkoutCalculatedDto.getCost());
            table2.addCell(createCell("", 0, Element.ALIGN_LEFT));
            table2.addCell(createCell("Łącznie", 2, Element.ALIGN_LEFT));
            table2.addCell(createCell(decimalFormat.format(value).replace('.',',')+"zł", 1, Element.ALIGN_RIGHT));

            value=Double.parseDouble(checkoutCalculatedDto.getCost())-Double.parseDouble(checkoutCalculatedDto.getCostDiscount());
            if(value!=0.0) {
                value=Double.parseDouble(checkoutCalculatedDto.getCost())-Double.parseDouble(checkoutCalculatedDto.getCostDiscount());
                table2.addCell(createCell("", 0, Element.ALIGN_LEFT));
                table2.addCell(createCell("Rabat", 2, Element.ALIGN_LEFT));
                table2.addCell(createCell("-" + decimalFormat.format(value).replace('.', ',')+"zł", 1, Element.ALIGN_RIGHT));

                value=Double.parseDouble(checkoutCalculatedDto.getCostDiscount());
                table2.addCell(createCell("", 0, Element.ALIGN_LEFT));
                table2.addCell(createCell("Łącznie ze zniżką", 2, Element.ALIGN_LEFT));
                table2.addCell(createCell(decimalFormat.format(value).replace('.',',')+"zł", 1, Element.ALIGN_RIGHT));
            }

            document.add(table2);

            //summary
            document.add(new Paragraph("\n"));
            value=Double.parseDouble(checkoutCalculatedDto.getCostDiscount());

            table.addCell(createCell("Do zapłaty:  "+decimalFormat.format(value).replace('.',',')+"zł", 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));

            table.addCell(createCell("Zamawiający:  "+userDataDto.getFirstName()+' '+userDataDto.getLastName(), 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));
            table.addCell(createCell("", 0, Element.ALIGN_LEFT));

            document.add(table);

            setBackgroundAsGradient(document, writer);

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("i can't create document or file not exists");
        }
    }

    private PdfPCell createCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/arial.ttf";

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);

        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(3);
        cell.setPaddingBottom(6);
        cell.setPaddingLeft(3);
        cell.setPaddingRight(3);
        return cell;
    }

    private String getProductNameForCell(ProductOnReceipt productOnReceipt){
        String productNameForCell;
        if(productOnReceipt.getType().equals("0")){productNameForCell="PIZZA "+productOnReceipt.getName();}
        else if(productOnReceipt.getType().equals("1")){productNameForCell="NAPÓJ "+productOnReceipt.getName();}
        else {
            productNameForCell=productOnReceipt.getName();
        }
        return productNameForCell;
    }
    private void setBackgroundAsGradient(Document document, PdfWriter writer) {
        Rectangle pageSize = document.getPageSize();
        PdfShading axial = PdfShading.simpleAxial(writer,
                pageSize.getLeft(pageSize.getWidth()/10), pageSize.getBottom(),
                pageSize.getRight(pageSize.getWidth()/10), pageSize.getBottom(),
                new BaseColor(255, 255, 255),
                new BaseColor(255, 255, 255), true, true);
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.paintShading(axial);
    }
}
