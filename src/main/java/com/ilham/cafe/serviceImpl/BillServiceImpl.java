package com.ilham.cafe.serviceImpl;

import com.ilham.cafe.JWT.JwtFilter;
import com.ilham.cafe.POJO.Bill;
import com.ilham.cafe.constents.CafeConstants;
import com.ilham.cafe.dao.BillDao;
import com.ilham.cafe.service.BillService;
import com.ilham.cafe.utils.CafeUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.Map;

import java.util.stream.Stream;

@Slf4j
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    BillDao billDao;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = CafeUtils.getUuid();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }

                String data = "Name: " + requestMap.get("name") + "\n" + "Contact Number: " + requestMap.get("contactNumber") + "\n" + "Email: " + requestMap.get("email") + "\n" + "Payment Method: " + requestMap.get("paymentMethod");

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(CafeConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));
                document.open();
                setRectangleInPdf(document);

                Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);


                // make sure
                JSONArray jsonArray = CafeUtils.getJsonArrayFromString((String) requestMap.get(("productDetails")));
                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, CafeUtils.getMapFromJson(jsonArray.getString(i)));
                }


                Paragraph footer = new Paragraph("Total : " + requestMap.get("totalAmount") + "\n" + "Thank you for visiting. Please visit again!!", getFont("Data"));
                document.add(footer);
                document.close();

                return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Required data not found.", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");
        table.addCell(getStringOrNull(data, "name"));
        table.addCell(getStringOrNull(data, "category"));
        table.addCell(getStringOrNull(data, "quantity"));

        Double price = (Double) data.get("price");
        table.addCell(price != null ? Double.toString(price) : "");

        Double total = (Double) data.get("total");
        table.addCell(total != null ? Double.toString(total) : "");
    }

    private String getStringOrNull(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : "";
    }



    private void addTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.WHITE);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangleInPdf");
        Rectangle rect = new Rectangle(577, 825, 18, 15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBackgroundColor(BaseColor.BLACK);
        rect.setBorderWidth(1);
        document.add(rect);

    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreatedBy(jwtFilter.getCurrentUser());
            billDao.save(bill);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount");

    }
}
