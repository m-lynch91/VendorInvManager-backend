package com.info5059.casestudy.purchaseOrder;

import java.math.BigDecimal; 
import java.math.MathContext; 
import java.math.RoundingMode; 
import java.text.NumberFormat; 
import java.util.Locale; 
import java.util.Optional; 
import java.net.URL; 
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.io.ByteArrayInputStream; 
import java.io.ByteArrayOutputStream; 
import com.itextpdf.io.exceptions.IOException; 
import com.itextpdf.io.font.constants.StandardFonts; 
import com.itextpdf.kernel.font.PdfFontFactory; 
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.kernel.font.PdfFont; 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Image; 
import com.itextpdf.layout.element.Paragraph; 
import com.itextpdf.io.image.ImageDataFactory; 
import com.itextpdf.kernel.colors.ColorConstants; 
import com.itextpdf.layout.element.Table; 
import com.itextpdf.layout.properties.TextAlignment; 
import com.itextpdf.layout.properties.UnitValue; 
import com.itextpdf.layout.properties.HorizontalAlignment;
import org.springframework.web.servlet.view.document.AbstractPdfView; 

import com.info5059.casestudy.purchaseOrder.*;
import com.info5059.casestudy.product.*;
import com.info5059.casestudy.vendor.*;

public abstract class PDFGenerator extends AbstractPdfView {
    public static ByteArrayInputStream generateReportPdf(
        String purchaseorderid, 
        PurchaseOrderRepository purchaseOrderRepository, 
        ProductRepository productRepository,
        VendorRepository vendorRepository
    ) throws IOException {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        
        try { 
 
            PdfWriter writer = new PdfWriter(baos); 
            PdfDocument pdf = new PdfDocument(writer); 
            Document document = new Document(pdf); 
 
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA); 
 
            URL imageUrl = PDFGenerator.class.getResource("/static/images/catlogo.png"); 
            Image img = new Image(ImageDataFactory.create(imageUrl)).setHorizontalAlignment(HorizontalAlignment.CENTER); 
            document.add(img); 
            document.add(new Paragraph(String.format("Purchase Order ID #" + purchaseorderid)) 
                    .setFont(font).setFontSize(12) 
                    .setTextAlignment(TextAlignment.CENTER).setBold()); 
 
            // Table created, but not added yet 
            Table table = new Table(5); 
            table.setWidth(new UnitValue(UnitValue.PERCENT, 100)); 
 
            // Headers 
            Cell cell = new Cell().add(new Paragraph("Product Code") 
                    .setFont(font).setFontSize(12).setBold()).setTextAlignment(TextAlignment.CENTER); 
            table.addCell(cell); 
            cell = new Cell().add(new Paragraph("Description") 
                    .setFont(font).setFontSize(12).setBold()).setTextAlignment(TextAlignment.CENTER); 
            table.addCell(cell); 
            cell = new Cell().add(new Paragraph("Quantity Sold") 
                    .setFont(font).setFontSize(12).setBold()).setTextAlignment(TextAlignment.CENTER); 
            table.addCell(cell); 
            cell = new Cell().add(new Paragraph("Price") 
                    .setFont(font).setFontSize(12).setBold()).setTextAlignment(TextAlignment.CENTER); 
            table.addCell(cell); 
            cell = new Cell().add(new Paragraph("Est. Price") 
                    .setFont(font).setFontSize(12).setBold()).setTextAlignment(TextAlignment.CENTER); 
            table.addCell(cell); 
 
            // Table Data 
            Optional<PurchaseOrder> nullableOrder = purchaseOrderRepository.findById(Long.parseLong(purchaseorderid)); 
            if (nullableOrder.isPresent()) { 
 
                PurchaseOrder order = nullableOrder.get(); 
                order.getVendorid(); 
 
                Optional<Vendor> nullableVendor = vendorRepository.findById(order.getVendorid()); 
                if (nullableVendor.isPresent()) { 
 
                    // Vendor data 
                    Vendor vendor = nullableVendor.get(); 
                    String vendorInfo = "Vendor: " 
                        + " " + vendor.getName() + "\n" 
                        + " " + vendor.getAddress() + "\n" 
                        + " " + vendor.getCity() + ", " 
                        + " " + vendor.getProvince() + ", " 
                        + " " + vendor.getPostalCode() + "\n" 
                        + " " + vendor.getEmail(); 
 
                    document.add(new Paragraph(vendorInfo) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER).setBold()); 
                } 
 
                // cost variables
                BigDecimal subtotal = new BigDecimal(0); 
                BigDecimal tax = new BigDecimal(0); 
                BigDecimal poTotal = new BigDecimal(0); 
                
                // currency formatter
                Locale locale = Locale.of("en", "US"); 
                NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(locale); 

                // loop through line items
                for (PurchaseOrderLineItem item : order.getLineItems()) { 
                    Optional<Product> nullableProduct = productRepository.findById(item.getProductid()); 
                    if (!nullableVendor.isPresent()) { 
                        continue; 
                    } 
 
                    Product product = nullableProduct.get(); 

                    // calculate costs
                    subtotal = subtotal.add(product.getPurchaseprice().multiply(new BigDecimal(item.getQuantity())), new MathContext(8, RoundingMode.UP)); 

 
                    String productid = item.getProductid(); 
                    String productName = product.getName();
                    String quantity = "" + item.getQuantity();
                    String price = "" + item.getPrice();
                    String estPrice = numberFormatter.format(item.getPrice().multiply(new BigDecimal(item.getQuantity())));

 
                    // Rows 
                    cell = new Cell().add(new Paragraph(productid) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER)); 
                    table.addCell(cell); 
                    cell = new Cell().add(new Paragraph(productName) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER)); 
                    table.addCell(cell); 
                    cell = new Cell().add(new Paragraph(quantity) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.CENTER)); 
                    table.addCell(cell); 
                    cell = new Cell().add(new Paragraph(price) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)); 
                    table.addCell(cell); 
                    cell = new Cell().add(new Paragraph(estPrice) 
                        .setFont(font).setFontSize(12).setTextAlignment(TextAlignment.RIGHT)); 
                    table.addCell(cell); 
                } 

                tax = tax.add(subtotal.multiply(new BigDecimal(0.13)), new MathContext(8, RoundingMode.UP)); 
                poTotal = poTotal.add(subtotal.add(tax), new MathContext(8, RoundingMode.UP)); 

                // formatted costs
                String formattedSubtotal = numberFormatter.format(subtotal);
                String formattedTax = numberFormatter.format(tax);
                String formattedPOtotal = numberFormatter.format(poTotal);
 
                cell = new Cell(1, 4).add(new Paragraph("Sub Total:") 
                    .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT)); 
                table.addCell(cell); 
                cell = new Cell().add(new Paragraph(formattedSubtotal) 
                    .setFont(font).setFontSize(12) 
                    .setBold().setTextAlignment(TextAlignment.RIGHT)); 
                table.addCell(cell); 

                cell = new Cell(1, 4).add(new Paragraph("Tax:") 
                    .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT)); 
                table.addCell(cell); 
                cell = new Cell().add(new Paragraph(formattedTax) 
                    .setFont(font).setFontSize(12) 
                    .setBold().setTextAlignment(TextAlignment.RIGHT)); 
                table.addCell(cell); 

                cell = new Cell(1, 4).add(new Paragraph("PO Total:") 
                    .setFont(font).setFontSize(12).setBold().setTextAlignment(TextAlignment.RIGHT)); 
                table.addCell(cell); 
                cell = new Cell().add(new Paragraph(formattedPOtotal) 
                    .setFont(font).setFontSize(12) 
                    .setBold().setTextAlignment(TextAlignment.RIGHT) 
                    .setBackgroundColor(ColorConstants.YELLOW)); 
                table.addCell(cell); 
            } 
 
            document.add(new Paragraph("\n")); 
            document.add(table); 
            document.add(new Paragraph("\n")); 
 
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"); 
            document.add(new Paragraph(dateTimeFormatter.format(LocalDateTime.now())) 
                .setTextAlignment(TextAlignment.CENTER)); 
            document.close(); 
        } 
        catch (Exception ex) { 
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex); 
        } 
 
        return new ByteArrayInputStream(baos.toByteArray()); 
    }
}
