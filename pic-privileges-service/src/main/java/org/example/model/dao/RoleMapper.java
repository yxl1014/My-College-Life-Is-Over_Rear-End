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
    //增加角色
    void deleteRole (Role role);
    //删除角色
    void updateRole (Role role);
    //更新列表
    Role selectOneRole(Role role);
    //选择角色
    List<Role> selectAllRole();
    //查看所有角色
    void grantPowerToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);
    //不同角色赋予不同权限
    int isPowerGrantedToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);
    //判断不同角色赋予不同权限是否成功

    void grantUserToRole(@Param("userId") String userId, @Param("roleId") Integer roleId);
    //不同用户赋予不同角色
    int isUserGrantedToRole(@Param("userId") String userId, @Param("roleId") Integer roleId);
    //判断不同用户赋予角色是否成功



}
