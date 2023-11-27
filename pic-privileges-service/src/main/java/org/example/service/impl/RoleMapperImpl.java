package org.example.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
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
    public RoleMapperImpl(RoleMapper roleMapper){
        this.roleMapper=roleMapper;
    }
    public void insertRole(Role role){
        roleMapper.insertRole(role);
    }
    public void deleteRole(Integer roleId){
        roleMapper.deleteRole(roleId);
    }
    public void updateRole(Role role){roleMapper.updateRole(role);}
    public Role selectOneRole(Integer roleId){
        return roleMapper.selectOneRole(roleId);
    }
    public List<Role> selectAllRole(){
        return roleMapper.selectAllRole();
    }

}