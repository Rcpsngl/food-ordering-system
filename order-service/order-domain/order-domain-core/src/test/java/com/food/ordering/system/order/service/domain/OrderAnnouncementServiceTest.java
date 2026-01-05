package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.valueobject.AnnouncementText;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderAnnouncementServiceTest {

    private OrderAnnouncementService orderAnnouncementService;
    private Order testOrder;

    @BeforeEach
    public void setUp() {
        orderAnnouncementService = new OrderAnnouncementService();

        Product product = new Product(new ProductId(UUID.randomUUID()));
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .price(new Money(new BigDecimal("10.00")))
                .quantity(2)
                .subTotal(new Money(new BigDecimal("20.00")))
                .build();

        testOrder = Order.builder()
                .customerId(new CustomerId(UUID.randomUUID()))
                .restaurantId(new RestaurantId(UUID.randomUUID()))
                .deliveryAddress(new StreetAddress(UUID.randomUUID(), "Street 1", "12345", "City"))
                .price(new Money(new BigDecimal("20.00")))
                .items(List.of(orderItem))
                .build();
        
        testOrder.initializeOrder();
    }

    @Test
    public void testGenerateAnnouncementTextForPendingOrder() {
        AnnouncementText announcementText = orderAnnouncementService.generateAnnouncementText(testOrder);
        
        assertNotNull(announcementText);
        assertTrue(announcementText.getText().contains("pending"));
        assertTrue(announcementText.getText().contains(testOrder.getTrackingId().getValue().toString()));
    }

    @Test
    public void testGenerateAnnouncementTextForPaidOrder() {
        testOrder.pay();
        AnnouncementText announcementText = orderAnnouncementService.generateAnnouncementText(testOrder);
        
        assertNotNull(announcementText);
        assertTrue(announcementText.getText().contains("paid"));
        assertTrue(announcementText.getText().contains("prepared"));
    }

    @Test
    public void testGenerateAnnouncementTextForApprovedOrder() {
        testOrder.pay();
        testOrder.approve();
        AnnouncementText announcementText = orderAnnouncementService.generateAnnouncementText(testOrder);
        
        assertNotNull(announcementText);
        assertTrue(announcementText.getText().contains("approved"));
        assertTrue(announcementText.getText().contains("ready"));
    }

    @Test
    public void testGenerateAnnouncementTextForCancelledOrder() {
        testOrder.cancel(List.of("Payment failed"));
        AnnouncementText announcementText = orderAnnouncementService.generateAnnouncementText(testOrder);
        
        assertNotNull(announcementText);
        assertTrue(announcementText.getText().contains("cancelled"));
    }

    @Test
    public void testGenerateAnnouncementTextForNullOrder() {
        AnnouncementText announcementText = orderAnnouncementService.generateAnnouncementText(null);
        
        assertNotNull(announcementText);
        assertEquals("", announcementText.getText());
    }
}
