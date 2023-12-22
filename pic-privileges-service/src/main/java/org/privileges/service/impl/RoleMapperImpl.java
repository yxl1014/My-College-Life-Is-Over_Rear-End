package org.privileges.service.impl;

import exception.PowerExceptions;
import exception.RoleExceptions;
import exception.UserExceptions;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.Role;
import org.database.mysql.domain.RolePowerRef;
import org.database.mysql.domain.UserRoleRef;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.mapper.PowerMapper;
import org.database.mysql.mapper.RoleMapper;
import org.database.mysql.mapper.UserMapper;
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

    private final BaseMysqlComp baseMysqlComp;

    @Autowired
    public RoleMapperImpl(RoleMapper roleMapper, PowerMapper powerMapper, UserMapper userMapper, BaseMysqlComp baseMysqlComp) {
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.baseMysqlComp = baseMysqlComp;

    }

    //增加角色
    public void insertRole(Role role) throws Exception {
        // 参数校验：角色id不为空；角色id不能已存在；当前操作用户有添加角色的权限（未完成）
        if (role == null || role.getRoleId() == null) {
            throw new RoleExceptions.EmptyRoleException();
            //抛出自定义的角色信息为空的异常
        } else {
            Role role1 = new Role();
            MysqlBuilder<Role> builder = new MysqlBuilder<>(Role.class);
            builder.setIn(role1);
            builder.getIn().setRoleName(role.getRoleName());

            MysqlBuilder<Role> insertRole = new MysqlBuilder<>(Role.class);
            insertRole.setIn(role);
            insertRole.getIn().setRoleId(role.getRoleId());

            if (roleMapper.selectById(role.getRoleId()) != null || baseMysqlComp.selectOne(builder) != null) {
                throw new RoleExceptions.RoleExistsException();
                //抛出自定义的角色信息已存在的异常
            } else {
                // 执行插入操作
                baseMysqlComp.insert(insertRole);
            }
        }
    }

    //查找角色信息
    public void selectOneRole(Role role) throws Exception {
        // 参数校验
        if (role == null || (role.getRoleId() == null && role.getRoleName() == null)) {
            throw new RoleExceptions.EmptyRoleException();
        } else {
            MysqlBuilder<Role> selectOneRole = new MysqlBuilder<>(Role.class);
            selectOneRole.setIn(role);
            selectOneRole.getIn().setRoleId(role.getRoleId());
            selectOneRole.getIn().setRoleName(role.getRoleName());
            if (baseMysqlComp.selectOne(selectOneRole) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出自定义的权限不存在的异常
            } else {
                //执行查找操作
                baseMysqlComp.selectOne(selectOneRole);
            }
        }
    }

    //查询角色列表信息
    public List<Role> selectAllRole() throws Exception {
        MysqlBuilder<Role> selectAllRole = new MysqlBuilder<>(Role.class);
        return baseMysqlComp.selectList(selectAllRole);
    }


    //删除角色（根据权限id）
    public void deleteRole(Role role) throws Exception {
        // 参数校验:角色id不为空；当前操作用户有删除角色的权限（未完成）
        if (role == null || role.getRoleId() == null) {
            throw new RoleExceptions.EmptyRoleException();
            //抛出自定义的角色信息为空的异常
        } else {
            MysqlBuilder<Role> deleteRole = new MysqlBuilder<>(Role.class);
            deleteRole.setIn(role);
            deleteRole.getIn().setRoleId(role.getRoleId());
            if (baseMysqlComp.selectOne(deleteRole) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出自定义的角色不存在的异常
            } else {
                //执行删除操作
                baseMysqlComp.delete(deleteRole);
            }
        }
    }

    //更新角色信息
    public void updateRole(Role role) throws Exception {
        // 参数校验：角色id不为空；当前操作用户有更新角色的权限（未完成）；
        if (role == null || role.getRoleId() == null) {
            throw new RoleExceptions.EmptyRoleException();
            //抛出自定义的角色信息为空的异常
        } else {
            MysqlBuilder<Role> updateRole = new MysqlBuilder<>(Role.class);
            Role role1 = roleMapper.selectById(role.getRoleId());
            updateRole.setIn(role1);
            updateRole.setUpdate(role);
            if (roleMapper.selectById(role.getRoleId()) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出自定义的角色不存在的异常
            } else {
                //执行更新操作
                baseMysqlComp.update(updateRole);
            }

        }
    }

    //分配角色给用户
    public Boolean grantRoleToUser(UserRoleRef userRoleRef) throws Exception {
        // 参数校验,角色信息存在，用户信息存在，用户无角色，当前操作用户有分配角色给用户权限
        if (userRoleRef.getRefRoleId() == null || userRoleRef.getRefUserId() == null) {
            throw new RoleExceptions.RoleAndUserEmptyException();
            //抛出自定义的角色或用户信息为空的异常
        } else {
            if (userMapper.selectById(userRoleRef.getRefUserId()) == null) {
                throw new UserExceptions.UserNoExistsException();
                //抛出自定义的用户不存在的异常
            } else {
                if (roleMapper.selectById(userRoleRef.getRefRoleId()) == null) {
                    throw new RoleExceptions.RoleNoExistsException();
                    //抛出自定义的角色不存在的异常
                } else {
                    MysqlBuilder<UserRoleRef> grantRoleToUser = new MysqlBuilder<>(UserRoleRef.class);
                    grantRoleToUser.setIn(userRoleRef);
                    grantRoleToUser.getIn().setRefRoleId(userRoleRef.getRefRoleId());
                    if (baseMysqlComp.selectOne(grantRoleToUser) != null) {
                        throw new UserExceptions.UserHasRoleException();
                        //抛出自定义的用户已有角色异常
                    } else {
                        baseMysqlComp.insert(grantRoleToUser);
                        //分配角色给用户
                    }
                    return baseMysqlComp.selectOne(grantRoleToUser) != null;

                }
            }
        }
    }


    //分配可操作权限给角色
    public Boolean grantDoPowerToRole(RolePowerRef rolePowerRef) throws Exception {
        // 参数校验,角色信息存在，权限信息存在，角色无该权限，当前操作用户有分配权限给角色的权限
        if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
            throw new RoleExceptions.RoleAndPowerEmptyException();
            //抛出自定义的角色或权限信息为空的异常
        } else {
            if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出角色不存在的异常
            } else {
                Power power = powerMapper.selectById(rolePowerRef.getRefPowerId());
                Short type = power.getPowerType();
                if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null || type == 0) {
                    throw new PowerExceptions.PowerNoExistsException();
                    //抛出不存权限在的异常
                } else {
                    MysqlBuilder<RolePowerRef> grantDoPowerToRole = new MysqlBuilder<>(RolePowerRef.class);
                    grantDoPowerToRole.setIn(rolePowerRef);
                    grantDoPowerToRole.getIn().setRefPowerId(rolePowerRef.getRefPowerId());
                    if (baseMysqlComp.selectOne(grantDoPowerToRole) != null) {
                        throw new RoleExceptions.RoleHasPowerException();
                        //抛出自定义角色已有权限异常
                    } else {
                        baseMysqlComp.insert(grantDoPowerToRole);
                        //分配可操作权限给角色
                    }
                    return baseMysqlComp.selectOne(grantDoPowerToRole) != null;

                }
            }
        }
    }

    //分配可操作权限给角色
    public Boolean grantSeePowerToRole(RolePowerRef rolePowerRef) throws Exception {
        // 参数校验,角色信息存在，权限信息存在，角色无该权限，当前操作用户有分配权限给角色的权限
        if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
            throw new RoleExceptions.RoleAndPowerEmptyException();
            //抛出自定义的角色或权限信息为空的异常
        } else {
            if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出角色不存在的异常
            } else {
                Power power = powerMapper.selectById(rolePowerRef.getRefPowerId());
                Short type = power.getPowerType();
                if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null || type == 1) {
                    throw new PowerExceptions.PowerNoExistsException();
                    //抛出角色不存在的异常
                } else {
                    MysqlBuilder<RolePowerRef> grantSeePowerToRole = new MysqlBuilder<>(RolePowerRef.class);
                    grantSeePowerToRole.setIn(rolePowerRef);
                    grantSeePowerToRole.getIn().setRefPowerId(rolePowerRef.getRefPowerId());
                    if (baseMysqlComp.selectOne(grantSeePowerToRole) != null) {
                        throw new RoleExceptions.RoleHasPowerException();
                        //抛出自定义角色已有权限异常
                    } else {
                        baseMysqlComp.insert(grantSeePowerToRole);
                        //分配可执行权限给角色
                    }
                    return baseMysqlComp.selectOne(grantSeePowerToRole) != null;

                }
            }
        }
    }
}




/*





    //不同角色赋予不同权限（可访问or可操作）
    public Boolean grantPowerToRole(Integer roleId, String powerName, int powerType) {
        // 参数校验
        if (roleId == null || powerName == null || (powerType != 1 && powerType != 2)) {
            throw new IllegalArgumentException("角色信息、权限信息、权限状态不为空（1:可访问or2:可操作）！");
        } else if (roleMapper.findRoleById(roleId) == null || roleMapper.grantPowerToRoleOperate(powerName, powerType) == null) {
            throw new IllegalArgumentException("角色不存在或者权限不存在！");
        }
        Integer powerId = roleMapper.grantPowerToRoleOperate(powerName, powerType);
        if (roleMapper.isPowerGrantedToRole(roleId, powerId)) {
            throw new IllegalArgumentException("此用户已拥有此权限！");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限为角色赋予不同权限");
        }*//*

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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限判断角色是否分配给用户成功！");
        }*//*

        return roleMapper.isPowerGrantedToRole(roleId, powerId);
    }

    //由姓名和权限状态查询id（可访问or可操作）和授权一起组合使用
    public Integer grantPowerToRoleOperate(String powerName, int powerType) {
        // 参数校验
        if (powerName == null || (powerType != 1 && powerType != 2)) {
            throw new IllegalArgumentException("权限信息和权限状态不能为空,可操作为1，可访问为2");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
        throw new SecurityException("无权限查询角色的权限不同操作！");
    }*//*

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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限分配角色给用户！");
        }*//*

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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限判断角色是否分配给用户成功！");
        }*//*


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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销已分配给角色的权限！");
        }*//*

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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销撤销已分配给用户的角色！");
        }*//*

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
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限撤销撤销已分配给用户的角色！");
        }*//*

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


}*/
