package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.entity.Role;
import org.example.model.entity.User;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */
@Mapper
// UserMapper.java
public interface RoleMapper {
    void insertRole (Role role);
    void deleteRole (Integer roleId);
    void updateRole (Role role);
    Role selectOneRole(Integer roleId);
    List<Role> selectAllRole();

    //不同角色赋予不同权限
    void grantPowerToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);
    int isPowerGrantedToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);



}
