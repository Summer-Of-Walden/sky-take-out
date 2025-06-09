package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;

/**
 * @author 侯博文
 */
public interface OrderService {

    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
}
