package pdfGeneration;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import sample.Order;
import sample.SubOrder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Invoice {

    private Order order;
    private ArrayList<SubOrder> subOrdersList;

    public Invoice(Order order, ArrayList<SubOrder> subOrdersList) {
        this.order = order;
        this.subOrdersList = subOrdersList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<SubOrder> getSubOrdersList() {
        return subOrdersList;
    }

    public void setSubOrdersList(ArrayList<SubOrder> subOrdersList) {
        this.subOrdersList = subOrdersList;
    }

    public void makeInvoice(){

        try{
            PdfWriter writer = new PdfWriter("C:/Users/Nicholas/Desktop/testPDF.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Order details
            document.add(new Paragraph("Customer:  " + order.getCustomerName()));
            document.add(new Paragraph("Order ID: " + order.getOrderID()));
            document.add(new Paragraph("Order TypeD: " + order.getOrderType()));
            document.add(new Paragraph("Order Date: " + order.getOrderDate()));
            document.add(new Paragraph("Delivery Date: " + order.getDeliveryDateTime()));
            document.add(new Paragraph("Status:  " + order.getOrderStatus()));
            document.add(new Paragraph("Address:  " + order.getDeliveryAddress()));

//            document.add(new Paragraph("Customer:  " ));
//            document.add(new Paragraph("Order ID: " ));
//            document.add(new Paragraph("Order TypeD: "));
//            document.add(new Paragraph("Order Date: " ));
//            document.add(new Paragraph("Delivery Date: "));
//            document.add(new Paragraph("Status:  "));
//            document.add(new Paragraph("Address:  " ));

            document.add(new Paragraph("Items:" ));

            // Creating a table
            Table table = new Table(new float[]{20, 100, 20, 50});

            table.addCell(new Cell().add(new Paragraph("#")));
            table.addCell(new Cell().add(new Paragraph("Product Name")));
            table.addCell(new Cell().add(new Paragraph("Qty")));
            table.addCell(new Cell().add(new Paragraph("Price")));

            int itemNo = 1;
            int total = 0;

            // Inserting sub orders into the table
            for(SubOrder subOrder : subOrdersList){
                table.addCell(new Cell().add(new Paragraph(String.valueOf(itemNo))));
                table.addCell(new Cell().add(new Paragraph(subOrder.getProductName())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(subOrder.getQty()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(subOrder.getPrice()))));
                total += subOrder.getQty() * subOrder.getPrice();
            }

            // Adding Table to document
            document.add(table);

            document.add(new Paragraph("Total Price: " + total));

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
