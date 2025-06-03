package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 侯博文
 */
@Mapper
public interface SetmealDishMapper {


    public List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
