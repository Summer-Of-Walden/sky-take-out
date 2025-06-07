package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

/**
 * @author 侯博文
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void add(ShoppingCartDTO shoppingCartDTO);
}
