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

            MysqlBuilder<Role> insertRole = new MysqlBuilder<>(Role.class);
            insertRole.setIn(role);

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

    //所有可用状态角色列表
    public List<Role> selectAllDoRole() throws Exception {
        MysqlBuilder<Role> selectAllDoRole = new MysqlBuilder<>(Role.class);
        Role role =new Role();
        role.setRoleStatusFlag((short)0);
        return baseMysqlComp.selectList(selectAllDoRole.buildIn(role));
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

    //撤销已分配给用户的角色
    public Boolean revokeRoleFromUser(UserRoleRef userRoleRef) throws Exception {
        // 参数校验,用户信息存在，角色信息存在，用户已拥有该角色，当前操作用户有撤销用户角色的权限的权限
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
                    MysqlBuilder<UserRoleRef> revokeRoleFromUser = new MysqlBuilder<>(UserRoleRef.class);
                    revokeRoleFromUser.setIn(userRoleRef);
                    if (baseMysqlComp.selectOne(revokeRoleFromUser) == null) {
                        throw new UserExceptions.UserNoRoleException();
                        //抛出用户无该角色无需撤销异常
                    } else {
                        baseMysqlComp.delete(revokeRoleFromUser);
                        //撤销角色给用户
                    }
                    return baseMysqlComp.selectOne(revokeRoleFromUser) == null;

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

    //撤销已分配给角色的权限
    public Boolean revokePowerFromRole(RolePowerRef rolePowerRef) throws Exception {
        //参数校验，角色信息存在，权限信息存在，角色已拥有该权限，当前操作用户有撤销已分配给角色的权限的权限
        if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
            throw new RoleExceptions.RoleAndPowerEmptyException();
            //抛出自定义的角色或权限信息为空的异常
        } else {
            if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出角色不存在的异常
            } else {
                if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null) {
                    throw new PowerExceptions.PowerNoExistsException();
                    //抛出角色不存在的异常
                } else {
                    MysqlBuilder<RolePowerRef> revokePowerFromRole = new MysqlBuilder<>(RolePowerRef.class);
                    revokePowerFromRole.setIn(rolePowerRef);
                    if (baseMysqlComp.selectOne(revokePowerFromRole) == null) {
                        throw new RoleExceptions.RoleNoPowerException();
                        //抛出权限无需撤销异常
                    } else {
                        baseMysqlComp.delete(revokePowerFromRole);
                        //撤销权限给角色
                    }
                    return baseMysqlComp.selectOne(revokePowerFromRole) == null;


                }
            }
        }

    }


    //查询角色状态查询id（0 正常或1 停用）
    public Boolean checkRoleOperate(Role role) throws Exception {
        // 参数校验,角色信息存在，当前操作用户有分配权限给角色的权限（未完成）
        if (role == null || role.getRoleId() == null) {
            throw new RoleExceptions.EmptyRoleException();
            //抛出自定义的角色信息为空的异常
        } else {
            if (roleMapper.selectById(role.getRoleId()) == null) {
                throw new RoleExceptions.RoleExistsException();
                //抛出自定义权限不存在的异常
            } else {
                Role role1 = roleMapper.selectById(role.getRoleId());
                if (role1.getRoleStatusFlag() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

}





      /*




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
