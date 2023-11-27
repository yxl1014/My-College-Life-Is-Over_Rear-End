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

    void insert (Role role);

    void  delete (Role role);

    void update (Role role);
    Role selectOne(Integer roleId);

    List<Role> selectAll();
}
