package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.entity.Role;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */
@Mapper
// UserMapper.java
public interface RoleMapper {
    //增加角色
    void insertRole(Role role);

    //删除角色
    void deleteRole(Integer roleId);

    //更新列表
    void updateRole(Role role);

    //选择角色
    Role selectOneRole(String query);

    Role findRoleById(Integer roleId);

    Role findRoleByRoleName(String roleName);

    //查看所有角色
    List<Role> selectAllRole();

    //不同角色赋予不同权限
    Boolean grantPowerToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);

    //判断不同角色赋予不同权限是否成功
    Boolean isPowerGrantedToRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);

    //由姓名和权限状态查询id（可访问or可操作）和授权一起组合使用）
    Integer grantPowerToRoleOperate(@Param("powerName") String powerName, @Param("powerType") int powerType);


    //不同用户赋予不同角色
    Boolean grantUserToRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    //判断不同用户赋予角色是否成功
    Boolean isUserGrantedToRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    //撤销已分配给角色的权限
    Boolean revokePowerFromRole(@Param("roleId") Integer roleId, @Param("powerId") Integer powerId);

    //撤销已分配给用户的角色
    Boolean revokeUserFromRole(@Param("userId") String userId, @Param("roleId") Integer roleId);

    //所有可用状态角色列表（0为可用）
    List<Role> isAbleToRole(@Param("roleFlag") int roleFlag);

    //判断用户的角色
    Integer isUserWhatToRole(@Param("userId") String userId);


}
