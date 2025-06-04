package com.sky.service;

import com.sky.dto.SetmealDTO;

/**
 * @author 侯博文
 */
public interface SetmealService {


    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
