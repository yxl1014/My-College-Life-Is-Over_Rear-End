package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
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
}
