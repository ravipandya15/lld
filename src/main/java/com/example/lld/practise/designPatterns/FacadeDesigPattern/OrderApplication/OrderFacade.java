package com.example.lld.practise.designPatterns.FacadeDesigPattern.OrderApplication;

public class OrderFacade {

    // Put all complex logic in facade layer so that client doesn't need to worry if any changes occurs in createOrder() method.

    private final ProductDao productDao;
    private final Payment payment;
    private final Invoice invoice;
    private final Notification notification;

    public OrderFacade() {
        this.productDao = new ProductDao();
        this.payment = new Payment();
        this.invoice = new Invoice();
        this.notification = new Notification();
    }

    public void createOrder() {
        Product product = productDao.getProduct("123");
        payment.makePayment();
        invoice.generateInvoice();
        notification.sendNotification();
    }
}
