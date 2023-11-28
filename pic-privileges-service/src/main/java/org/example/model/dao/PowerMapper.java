package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.entity.Power;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Mapper
public interface PowerMapper {

    void insertPower (Power power);
    void deletePower (Integer powerId);
    void updatePower (Power power);
    Power selectOnePower(Integer powerId);
    List<Power> selectAllPower();
    Power findByPowerName(String powerName);


}
