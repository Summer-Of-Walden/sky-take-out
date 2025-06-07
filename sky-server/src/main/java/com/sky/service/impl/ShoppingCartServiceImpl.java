package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 侯博文
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        // 判断当前加入的商品是否在购物车中
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> shoppingCarts = shoppingCartMapper.list(shoppingCart);
        // 如果在，则数量加1
        if (shoppingCarts != null && !shoppingCarts.isEmpty()) {
            ShoppingCart cart = shoppingCarts.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.update(cart);
        }
        // 如果不在，则添加到购物车，数量默认为1
        else {
            if (shoppingCart.getDishId() != null){
                Long DishId = shoppingCart.getDishId();

                Dish dish = dishMapper.getById(DishId);
                String name = dish.getName();
                BigDecimal price = dish.getPrice();
                String image = dish.getImage();

                shoppingCart.setName(name);
                shoppingCart.setAmount(price);
                shoppingCart.setImage(image);
            }else {
                Long setmealId = shoppingCart.getSetmealId();

                Setmeal setmeal = setmealMapper.getById(setmealId);
                String name = setmeal.getName();
                BigDecimal price = setmeal.getPrice();
                String image = setmeal.getImage();

                shoppingCart.setName(name);
                shoppingCart.setAmount(price);
                shoppingCart.setImage(image);
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
    }
}
