package org.example.service.impl;

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
    private final RoleMapper roleMapper;
    private final PowerMapper powerMapper;
    private final UserMapper userMapper;

    @Autowired
    public RoleMapperImpl(RoleMapper roleMapper, PowerMapper powerMapper, UserMapper userMapper) {
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
    }

    //增加角色
    public void insertRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        } else if (roleMapper.findRoleById(role.getRoleId()) != null || roleMapper.findRoleByRoleName(role.getRoleName()) != null) {
            throw new IllegalArgumentException("角色信息已存在");
        }

        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权限插入角色信息");
        }*/
        //执行插入
        roleMapper.insertRole(role);
    }

    //删除角色
    public void deleteRole(Integer roleId) {
        // 参数校验
        if (roleId == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
           /* if (!checkPowers()) {
                throw new SecurityException("无权限删除角色信息");
            }*/

        // 执行删除
        roleMapper.deleteRole(roleId);
    }

    //更新角色信息
    public void updateRole(Role role) {
        // 参数校验
        if (role == null || role.getRoleId() == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        } else if (roleMapper.findRoleById(role.getRoleId()) == null) {
            throw new IllegalArgumentException("角色不存在");
        }

        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权更新角色信息");
        }*/

        // 执行更新
        roleMapper.updateRole(role);
    }

    //查找角色信息
    public Role selectOneRole(String query) {
        // 参数校验
        if (query == null) {
            throw new IllegalArgumentException("角色信息不能为空");
        }

        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权查找角色");
        }*/
        Role role = null;
        if (isNumeric(query)) {
            // 执行查找
            return roleMapper.findRoleById(Integer.parseInt(query));
        } else {
            return roleMapper.findRoleByRoleName(query);
        }
    }

    //判断字符串是否为纯数字
    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    public Role findRoleById(Integer roleId) {
        // 参数校验
        if (roleId == null) {
            throw new IllegalArgumentException("角色id信息不能为空");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*/
        //执行查找
        return roleMapper.findRoleById(roleId);
    }

    public Role findRoleByRoleName(String roleName) {
        // 参数校验
        if (roleName == null) {
            throw new IllegalArgumentException("角色名不能为空");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*/
        //执行查找
        return roleMapper.findRoleByRoleName(roleName);
    }


    //展开所有角色列表信息
    public List<Role> selectAllRole() {
        return roleMapper.selectAllRole();
    }


    //不同角色赋予不同权限（可访问or可操作）
    public Boolean grantPowerToRole(Integer roleId, Integer powerId) {
        // 参数校验
        if (roleId == null || powerId == null) {
            throw new IllegalArgumentException("角色信息、权限信息不为空！");
        } else if (roleMapper.findRoleById(roleId) == null || powerMapper.selectOnePower(powerId) == null) {
            throw new IllegalArgumentException("角色不存在或者权限不存在！");
        } else if (roleMapper.isPowerGrantedToRole(roleId, powerId)) {
            throw new IllegalArgumentException("此用户已拥有此权限！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限为角色赋予不同权限");
        }*/
        roleMapper.grantPowerToRole(roleId, powerId);
        //验证授权结果
        return roleMapper.isPowerGrantedToRole(roleId, powerId);
    }

    //判断权限是否分配给角色成功
    public Boolean isPowerGrantedToRole(Integer roleId, Integer powerId) {
        // 参数校验
        if (roleId == null || powerId == null) {
            throw new IllegalArgumentException("用户信息权限信息均不能为空！");
        } else if (roleMapper.findRoleById(roleId) == null || powerMapper.selectOnePower(powerId) == null) {
            throw new IllegalArgumentException("用户不存在或者角色不存在！");
        } else if (roleMapper.isPowerGrantedToRole(roleId, powerId)) {
            throw new IllegalArgumentException("此角色已分配权限！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限判断角色是否分配给用户成功！");
        }*/
        return roleMapper.isPowerGrantedToRole(roleId, powerId);
    }

    //由姓名和权限状态查询id（可访问or可操作）和授权一起组合使用
    public Integer grantPowerToRoleOperate(String powerName, int powerType) {
        // 参数校验
        if (powerName == null || (powerType != 1 && powerType != 2)) {
            throw new IllegalArgumentException("权限信息和权限状态不能为空,可操作为1，可访问为2");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
        throw new SecurityException("无权限查询角色的权限不同操作！");
    }*/
        return roleMapper.grantPowerToRoleOperate(powerName, powerType);
    }


    //分配角色给用户
    public Boolean grantUserToRole(String userId, Integer roleId) {
        // 参数校验
        if (userId == null || roleId == null) {
            throw new IllegalArgumentException("用户信息角色信息均不能为空！");
        } else if (userMapper.findUserById(userId) == null || roleMapper.findRoleById(roleId) == null) {
            throw new IllegalArgumentException("用户不存在或者角色不存在！");
        } else if (roleMapper.isUserGrantedToRole(userId, roleId)) {
            throw new IllegalArgumentException("此用户已分配角色！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限分配角色给用户！");
        }*/
        roleMapper.grantUserToRole(userId, roleId);
        //验证授权结果
        return roleMapper.isUserGrantedToRole(userId, roleId);

    }

    //判断角色是否分配给用户成功
    public Boolean isUserGrantedToRole(String userId, Integer roleId) {
        // 参数校验
        if (userId == null || roleId == null) {
            throw new IllegalArgumentException("用户信息角色信息均不能为空！");
        } else if (userMapper.findUserById(userId) == null || roleMapper.findRoleById(roleId) == null) {
            throw new IllegalArgumentException("用户不存在或者角色不存在！");
        } else if (roleMapper.isUserGrantedToRole(userId, roleId)) {
            throw new IllegalArgumentException("此角色已分配权限！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限判断角色是否分配给用户成功！");
        }*/

        return roleMapper.isUserGrantedToRole(userId, roleId);
    }


    //撤销已分配给角色的权限
    public Boolean revokePowerFromRole(Integer roleId, Integer powerId) {
        // 参数校验
        if (roleId == null || powerId == null) {
            throw new IllegalArgumentException("用户信息、权限信息均不能为空！");
        } else if (roleMapper.findRoleById(roleId) == null || powerMapper.selectOnePower(powerId) == null) {
            throw new IllegalArgumentException("用户不存在或者权限不存在！");
        } else if (!roleMapper.isPowerGrantedToRole(roleId, powerId)) {
            throw new IllegalArgumentException("此角色无需撤销该权限！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销已分配给角色的权限！");
        }*/
        roleMapper.revokePowerFromRole(roleId, powerId);
        //验证授权结果

        return !roleMapper.isPowerGrantedToRole(roleId, powerId);
    }

    //撤销已分配给用户的角色
    public Boolean revokeUserFromRole(String userId, Integer roleId) {
        // 参数校验
        if (userId == null || roleId == null) {
            throw new IllegalArgumentException("用户信息、角色信息均不能为空！");
        } else if (userMapper.findUserById(userId) == null || roleMapper.findRoleById(roleId) == null) {
            throw new IllegalArgumentException("用户不存在或者角色不存在！");
        } else if (!roleMapper.isUserGrantedToRole(userId, roleId)) {
            throw new IllegalArgumentException("此用户无需撤销该角色！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销撤销已分配给用户的角色！");
        }*/
        roleMapper.revokeUserFromRole(userId, roleId);
        //验证授权结果

        return !roleMapper.isUserGrantedToRole(userId, roleId);
    }

    //所有可用状态角色列表
    public List<Role> isAbleToRole(int roleFlag) {
        if (roleFlag != 0 && roleFlag != 1 && roleFlag != 2) {
            throw new IllegalArgumentException("角色状态无效，0为可用1为停用2为删除！");
        }
        // 权限验证
        /*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销撤销已分配给用户的角色！");
        }*/
        return roleMapper.isAbleToRole(roleFlag);
    }

    //判断用户的角色
    public String isUserWhatToRole(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("用户信息不能为空！");
        } else if (userMapper.findUserById(userId) == null) {
            throw new IllegalArgumentException("用户不存在！");
        }
        Integer roleId = roleMapper.isUserWhatToRole(userId);
        Role role = roleMapper.findRoleById(roleId);
        return role.getRoleName();
    }


   /* //查询角色的权限不同操作（可访问or可操作）
    public Integer grantPowerToRoleOperate(String powerName, int powerType) {
        // 参数校验
        if (powerName == null || (powerType != 1 && powerType != 2)) {
            throw new IllegalArgumentException("权限信息和权限状态不能为空,可操作为1，可访问为2");
        } else if (powerMapper.findPowerByPowerName(powerName) == null) {
            throw new IllegalArgumentException("权限不存在！");
        }
        // 权限验证
        *//*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限查询角色的权限不同操作！");
        }*//*
        return grantPowerToRoleOperate(powerName,powerType);

    }*/


}