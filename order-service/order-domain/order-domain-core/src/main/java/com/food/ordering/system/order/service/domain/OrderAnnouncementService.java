package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.AnnouncementText;

public class OrderAnnouncementService {

    public AnnouncementText generateAnnouncementText(Order order) {
        if (order == null || order.getOrderStatus() == null) {
            return new AnnouncementText("");
        }

        OrderStatus status = order.getOrderStatus();
        String trackingId = order.getTrackingId() != null ? order.getTrackingId().getValue().toString() : "";

        String text = switch (status) {
            case PENDING -> String.format("Order %s is pending for confirmation.", trackingId);
            case PAID -> String.format("Order %s has been paid and is being prepared.", trackingId);
            case APPROVED -> String.format("Order %s has been approved and is ready for pickup.", trackingId);
            case CANCELLING -> String.format("Order %s is being cancelled.", trackingId);
            case CANCELLED -> String.format("Order %s has been cancelled.", trackingId);
        };

        return new AnnouncementText(text);
    }
}
