package pdfGeneration;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import sample.Order;
import sample.SubOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class testPDF {

    public static void main(String[] args) {

        Order testorder = new Order("ORD0001","Testname", "pickup", "JL.afjwefawe", 20000, LocalDate.now(), LocalDateTime.now(), "Pending", 20000, 10);
        SubOrder subOrder = new SubOrder(1, "SUB0001", "PRD00001", "TEST", 2, "This is a test description", 100000);
        ArrayList<SubOrder> subOrders = new ArrayList<>();

        subOrders.add(subOrder);

        Invoice x = new Invoice(testorder,subOrders);

        x.makeInvoice();




    }
}
