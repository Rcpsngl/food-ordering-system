package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Slf4j
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    @Override
    public void orderApproved(com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse restaurantApprovalResponse) {
        // Implementation for handling order approval
    }

    @Override
    public void orderRejected(com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse restaurantApprovalResponse) {
        // Implementation for handling order rejection
    }
}
