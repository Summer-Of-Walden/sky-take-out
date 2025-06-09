package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 侯博文
 */
@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> list(ShoppingCart shoppingCart);

    void update(ShoppingCart cart);

    void insert(ShoppingCart shoppingCart);

    @Delete("delete from sky_take_out.shopping_cart where user_id = #{currentId}")
    void deleteByUserId(Long currentId);

    @Delete("delete from sky_take_out.shopping_cart where id = #{id}")
    void deleteById(Long id);

    void insertBatch(List<ShoppingCart> shoppingCartList);
}
