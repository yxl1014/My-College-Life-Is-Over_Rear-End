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

    //增加权限
    void insertPower(Power power);

    //删除权限
    void deletePower(Power power);

    //更新列表
    void updatePower(Power power);

    //选择权限
    Power selectOnePower(Power power);

    //查看所有权限
    List<Power> selectAllPower();

    //查询角色对应的权限列表
    List<Power> getRolePowers(Integer roleId);


}
