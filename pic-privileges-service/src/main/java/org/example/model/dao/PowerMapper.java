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
    //增加权限
    void deletePower (Power power);
    //删除权限
    void updatePower (Power power);
    //更新列表
    Power selectOnePower(Power power);
    //选择权限
    List<Power> selectAllPower();
    //查看所有权限


}
