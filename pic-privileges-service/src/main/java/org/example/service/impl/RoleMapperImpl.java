package org.example.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.PowerMapper;
import org.example.model.dao.RoleMapper;
import org.example.model.dao.UserMapper;
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
    private final PowerMapper powerMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleMapperImpl(PowerMapper powerMapper, UserMapper userMapper, RoleMapper roleMapper) {
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }
    //增加角色
    public void insertRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
       /* if (!checkAdminPermissions()) {
            throw new SecurityException("无权限插入角色信息");
        }*/
        //执行插入
        roleMapper.insertRole(role);
    }

    //删除角色
    public void deleteRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
           /* if (!checkAdminPermissions()) {
                throw new SecurityException("无权限删除角色信息");
            }*/

        // 执行删除
        roleMapper.deleteRole(role);
    }

    //更新角色信息
    public void updateRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null)  {
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
       /* if (!checkAdminPermissions()) {
            throw new SecurityException("无权更新角色信息");
        }*/

        // 执行更新
        roleMapper.updateRole(role);
    }

    //查找角色信息
    public Role selectOneRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null){
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
       /* if (!checkAdminPermissions()) {
            throw new SecurityException("无权查找角色");
        }*/

        // 执行查找
        return roleMapper.selectOneRole(role);
    }

    //展开所有角色列表信息
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }

    //不同角色赋予权限不同操作（可访问or可操作）
    public Integer grantPowerToRoleOperate(String powerName, int powerType) {
        return roleMapper.grantPowerToRoleOperate(powerName, powerType);
    }

    //角色分配权限（需要和grantPowerToRoleOperate组合）
    public void grantPowerToRole(Integer roleId, Integer powerId) {
        roleMapper.grantPowerToRole(roleId, powerId);
    }

    //判断权限是否分配给角色成功
    public boolean isPowerGrantedToRole(Integer roleId, Integer powerId) {
        int count = roleMapper.isPowerGrantedToRole(roleId, powerId);
        return count > 0;
    }

    //分配角色给用户
    public void grantUserToRole(String userId, Integer powerId) {
        roleMapper.grantUserToRole(userId, powerId);
    }

    //判断角色是否分配给用户成功
    public boolean isUserGrantedToRole(String userId, Integer roleId) {
        int count = roleMapper.isUserGrantedToRole(userId, roleId);
        return count > 0;
    }

    //撤销已分配给角色的权限
    public void revokePowerFromRole(Integer roleId, Integer powerId) {
        roleMapper.revokePowerFromRole(roleId, powerId);
    }

    //撤销已分配给用户的角色
    public void revokeUserFromRole(String userId, Integer roleId) {
        roleMapper.revokeUserFromRole(userId, roleId);
    }

    //所有可用状态角色列表
    public List<Role> isAbleToRole(int roleFlag) {
        return roleMapper.isAbleToRole(roleFlag);
    }

    //判断用户的角色
    public Integer isUserWhatToRole(String userId){
        return roleMapper.isUserWhatToRole(userId);
    }


}