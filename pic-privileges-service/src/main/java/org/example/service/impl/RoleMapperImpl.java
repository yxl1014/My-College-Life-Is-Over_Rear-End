package org.example.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.PowerMapper;
import org.example.model.dao.RoleMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */
@Service
public class RoleMapperImpl {
    private final RoleMapper roleMapper;

    @Autowired
    public RoleMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    public void deleteRole(Role role) {
        roleMapper.deleteRole(role);
    }

    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    public Role selectOneRole(Role role) {
        return roleMapper.selectOneRole(role);
    }

    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    //角色分配权限
    public void grantPowerToRole(Integer roleId, Integer powerId) {
        roleMapper.grantPowerToRole(roleId, powerId);
    }

    //判断是否分配成功
    public boolean isPowerGrantedToRole(Integer roleId, Integer powerId) {
        int count = roleMapper.isPowerGrantedToRole(roleId, powerId);
        return count > 0;
    }

    public void grantUserToRole(String userId, Integer powerId) {
        roleMapper.grantUserToRole(userId, powerId);
    }

    //判断是否分配成功
    public boolean isUserGrantedToRole(String userId, Integer roleId) {
        int count = roleMapper.isUserGrantedToRole(userId, roleId);
        return count > 0;
    }

}