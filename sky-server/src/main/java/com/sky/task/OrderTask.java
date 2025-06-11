package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 侯博文
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理支付超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    @ApiOperation("处理支付超时订单")
    public void processTimeoutOrder(){
        log.info("处理支付超时订单: {}", LocalDateTime.now());
        List<Orders> outTimeOrders = orderMapper.getOrdersByStatusAndTime(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-15));
        for (Orders outTimeOrder : outTimeOrders) {
            log.info("处理支付超时订单：{}", outTimeOrder);
            outTimeOrder.setStatus(Orders.CANCELLED);
            outTimeOrder.setCancelReason("支付超时");
            outTimeOrder.setCancelTime(LocalDateTime.now());
            orderMapper.update(outTimeOrder);
        }
    }

    /**
     * 处理派送中订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){
        log.info("处理派送中订单: {}", LocalDateTime.now());
        List<Orders> ordersList = orderMapper.getOrdersByStatusAndTime(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));
        for (Orders orders : ordersList) {
            log.info("处理派送中订单：{}", orders);
            orders.setStatus(Orders.COMPLETED);
            orderMapper.update(orders);
        }
    }
}
