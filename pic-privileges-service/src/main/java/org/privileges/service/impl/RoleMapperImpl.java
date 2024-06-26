package org.privileges.service.impl;

import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.commons.log.LogEnum;
import org.commons.log.LogType;
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


    private static final Logger log = LogComp.getLogger(RoleMapperImpl.class);

    @Autowired
    public RoleMapperImpl(RoleMapper roleMapper, PowerMapper powerMapper, UserMapper userMapper, BaseMysqlComp baseMysqlComp) {
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.baseMysqlComp = baseMysqlComp;

    }


    /**
     * 增加角色
     * 参数校验：角色id不为空；
     * 角色id不能已存在；
     * 当前操作用户有添加角色的权限（未完成）
     *
     * @param role
     * @throws Exception
     */
    public void insertRole(Role role) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            if (role == null || role.getRoleId() == null) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                Role role1 = new Role();
                MysqlBuilder<Role> builder = new MysqlBuilder<>(Role.class);
                builder.setCondition(role1);

                MysqlBuilder<Role> insertRole = new MysqlBuilder<>(Role.class);
                insertRole.setCondition(role);

                if (roleMapper.selectById(role.getRoleId()) != null || baseMysqlComp.selectOne(builder) != null) {
                    logMessage.build(LogEnum.ROLE_EXISTS);
                    log.error(logMessage.log());
                } else {
                    // 执行插入操作
                    baseMysqlComp.insert(insertRole);
                }
            }
        } catch (Exception e) {
            log.error("Failed to insert role!", e);

        }
    }


    /**
     * 查找角色信息
     * 参数校验：角色、角色id、角色名不为空；
     * 当前操作用户有查找角色的权限（未完成）
     *
     * @param role 角色
     * @throws Exception 错误
     */
    public void selectOneRole(Role role) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            if (role == null || (role.getRoleId() == null && role.getRoleName() == null)) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Role> selectOneRole = new MysqlBuilder<>(Role.class);
                selectOneRole.setCondition(role);
                if (baseMysqlComp.selectOne(selectOneRole) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    //执行查找操作
                    baseMysqlComp.selectOne(selectOneRole);
                }
            }
        } catch (Exception e) {
            log.error("Failed to select role!", e);

        }
    }

    /**
     * 查询角色列表信息
     *
     * @return
     * @throws Exception
     */

    public List<Role> selectAllRole() throws Exception {
        MysqlBuilder<Role> selectAllRole = new MysqlBuilder<>(Role.class);
        return baseMysqlComp.selectList(selectAllRole);
    }

    /**
     * 所有可用状态角色列表
     *
     * @return
     * @throws Exception
     */

    public List<Role> selectAllDoRole() throws Exception {
        MysqlBuilder<Role> selectAllDoRole = new MysqlBuilder<>(Role.class);
        Role role = new Role();
        role.setRoleStatusFlag((short) 0);
        return baseMysqlComp.selectList(selectAllDoRole.buildIn(role));
    }


    /**
     * 删除角色（根据权限id）
     * 参数校验:角色id不为空；
     * 当前操作用户有删除角色的权限（未完成）
     *
     * @param role
     * @throws Exception
     */
    public void deleteRole(Role role) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            if (role == null || role.getRoleId() == null) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Role> deleteRole = new MysqlBuilder<>(Role.class);
                deleteRole.setCondition(role);
                if (baseMysqlComp.selectOne(deleteRole) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    baseMysqlComp.delete(deleteRole);
                }
            }
        } catch (Exception e) {
            log.error("Failed to delete role!", e);
        }
    }

    /**
     * 参数校验：角色id不为空；
     * 当前操作用户有更新角色的权限（未完成）；
     *
     * @param role
     * @throws Exception
     */
    //更新角色信息
    public void updateRole(Role role) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            if (role == null || role.getRoleId() == null) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Role> updateRole = new MysqlBuilder<>(Role.class);
                Role role1 = roleMapper.selectById(role.getRoleId());
                updateRole.setCondition(role1);
                updateRole.setUpdate(role);
                if (roleMapper.selectById(role.getRoleId()) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    baseMysqlComp.update(updateRole);
                }
            }
        } catch (Exception e) {
            log.error("Failed to select role!", e);
        }
    }

    /**
     * 分配角色给用户
     * 参数校验:角色信息存在，用户信息存在，用户无角色;
     * 当前操作用户有分配角色给用户权限
     *
     * @param userRoleRef
     * @return
     * @throws Exception
     */
    public Boolean grantRoleToUser(UserRoleRef userRoleRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            LogComp.LogMessage logMessage1 = LogComp.buildData(LogType.USER);

            if (userRoleRef.getRefRoleId() == null || userRoleRef.getRefUserId() == null) {
                logMessage.build(LogEnum.ROLE_USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (userMapper.selectById(userRoleRef.getRefUserId()) == null) {
                    logMessage1.build(LogEnum.USER_NO_Exists);
                    log.warn(logMessage1.log());
                    return false;
                } else {
                    if (roleMapper.selectById(userRoleRef.getRefRoleId()) == null) {
                        logMessage.build(LogEnum.ROLE_NO_EXISTS);
                        log.warn(logMessage.log());
                    } else {
                        MysqlBuilder<UserRoleRef> grantRoleToUser = new MysqlBuilder<>(UserRoleRef.class);
                        grantRoleToUser.setCondition(userRoleRef);
                        if (baseMysqlComp.selectOne(grantRoleToUser) != null) {
                            logMessage1.build(LogEnum.USER_HAS_ROLE);
                            log.warn(logMessage1.log());
                        } else {
                            baseMysqlComp.insert(grantRoleToUser);
                        }
                        return baseMysqlComp.selectOne(grantRoleToUser) != null;

                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to grantRoleToUser!", e);
        }
        return null;
    }

    /**
     * 撤销已分配给用户的角色
     * 参数校验:用户信息存在，角色信息存在，用户已拥有该角色;
     * 当前操作用户有撤销用户角色的权限的权限
     *
     * @param userRoleRef
     * @return
     * @throws Exception
     */
    public Boolean revokeRoleFromUser(UserRoleRef userRoleRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            LogComp.LogMessage logMessage1 = LogComp.buildData(LogType.USER);
            if (userRoleRef.getRefRoleId() == null || userRoleRef.getRefUserId() == null) {
                logMessage.build(LogEnum.ROLE_USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (userMapper.selectById(userRoleRef.getRefUserId()) == null) {
                    logMessage1.build(LogEnum.USER_NO_Exists);
                    log.warn(logMessage1.log());
                    return false;
                } else {
                    if (roleMapper.selectById(userRoleRef.getRefRoleId()) == null) {
                        logMessage.build(LogEnum.ROLE_NO_EXISTS);
                        log.warn(logMessage.log());
                    } else {
                        MysqlBuilder<UserRoleRef> revokeRoleFromUser = new MysqlBuilder<>(UserRoleRef.class);
                        revokeRoleFromUser.setCondition(userRoleRef);
                        if (baseMysqlComp.selectOne(revokeRoleFromUser) == null) {
                            logMessage1.build(LogEnum.USER_NO_ROLE);
                            log.warn(logMessage1.log());
                        } else {
                            baseMysqlComp.delete(revokeRoleFromUser);
                            //撤销角色给用户
                        }
                        return baseMysqlComp.selectOne(revokeRoleFromUser) == null;

                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to revokeRoleFromUser!", e);
        }
        return null;
    }

    /**
     * 分配可操作权限给角色
     * 参数校验:角色信息存在，权限信息存在，角色无该权限；
     * 当前操作用户有分配权限给角色的权限
     *
     * @param rolePowerRef
     * @return
     * @throws Exception
     */

    public Boolean grantDoPowerToRole(RolePowerRef rolePowerRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            LogComp.LogMessage logMessage1 = LogComp.buildData(LogType.POWER);
            if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
                logMessage.build(LogEnum.ROLE_POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    Power power = powerMapper.selectById(rolePowerRef.getRefPowerId());
                    Short type = power.getPowerType();
                    if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null || type == 0) {
                        logMessage1.build(LogEnum.POWER_NO_EXISTS);
                        log.warn(logMessage1.log());
                    } else {
                        MysqlBuilder<RolePowerRef> grantDoPowerToRole = new MysqlBuilder<>(RolePowerRef.class);
                        grantDoPowerToRole.setCondition(rolePowerRef);
                        if (baseMysqlComp.selectOne(grantDoPowerToRole) != null) {
                            logMessage.build(LogEnum.ROLE_HAS_POWER);
                            log.warn(logMessage.log());

                        } else {
                            baseMysqlComp.insert(grantDoPowerToRole);
                            //分配可操作权限给角色
                        }
                        return baseMysqlComp.selectOne(grantDoPowerToRole) != null;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to grantDoPowerToRole role!", e);
        }
        return null;
    }


    /**
     * 分配可操作权限给角色
     * 参数校验:角色信息存在，权限信息存在，角色无该权限;
     * 当前操作用户有分配权限给角色的权限
     *
     * @param rolePowerRef
     * @return
     * @throws Exception
     */

    public Boolean grantSeePowerToRole(RolePowerRef rolePowerRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            LogComp.LogMessage logMessage1 = LogComp.buildData(LogType.POWER);
            if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
                logMessage.build(LogEnum.ROLE_POWER_EMPTY);
                log.warn(logMessage.log());

            } else {
                if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    Power power = powerMapper.selectById(rolePowerRef.getRefPowerId());
                    Short type = power.getPowerType();
                    if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null || type == 1) {
                        logMessage.build(LogEnum.POWER_NO_EXISTS);
                        log.warn(logMessage.log());

                    } else {
                        MysqlBuilder<RolePowerRef> grantSeePowerToRole = new MysqlBuilder<>(RolePowerRef.class);
                        grantSeePowerToRole.setCondition(rolePowerRef);
                        if (baseMysqlComp.selectOne(grantSeePowerToRole) != null) {
                            logMessage.build(LogEnum.ROLE_HAS_POWER);
                            log.warn(logMessage.log());

                        } else {
                            baseMysqlComp.insert(grantSeePowerToRole);
                        }
                        return baseMysqlComp.selectOne(grantSeePowerToRole) != null;

                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to grantSeePowerToRole role!", e);
        }
        return null;
    }

    /**
     * 撤销已分配给角色的权限
     * 参数校验:角色信息存在，权限信息存在，角色已拥有该权限
     * 当前操作用户有撤销已分配给角色的权限的权限
     *
     * @param rolePowerRef
     * @return
     * @throws Exception
     */
    public Boolean revokePowerFromRole(RolePowerRef rolePowerRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            LogComp.LogMessage logMessage1 = LogComp.buildData(LogType.POWER);
            if (rolePowerRef.getRefRoleId() == null || rolePowerRef.getRefPowerId() == null) {
                logMessage.build(LogEnum.ROLE_POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    if (powerMapper.selectById(rolePowerRef.getRefPowerId()) == null) {
                        logMessage1.build(LogEnum.POWER_NO_EXISTS);
                        log.warn(logMessage1.log());
                    } else {
                        MysqlBuilder<RolePowerRef> revokePowerFromRole = new MysqlBuilder<>(RolePowerRef.class);
                        revokePowerFromRole.setCondition(rolePowerRef);
                        if (baseMysqlComp.selectOne(revokePowerFromRole) == null) {
                            logMessage.build(LogEnum.ROLE_NO_POWER);
                            log.warn(logMessage.log());
                        } else {
                            baseMysqlComp.delete(revokePowerFromRole);
                        }
                        return baseMysqlComp.selectOne(revokePowerFromRole) == null;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to revokePowerFromRole!", e);

        }
        return null;
    }


    /**
     * 查询角色状态查询id（0 正常或1 停用）
     * 参数校验:角色信息存在
     * 当前操作用户有分配权限给角色的权限（未完成）
     *
     * @param role
     * @return
     * @throws Exception
     */
    public Boolean checkRoleOperate(Role role) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);

            if (role == null || role.getRoleId() == null) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (roleMapper.selectById(role.getRoleId()) == null) {
                    logMessage.build(LogEnum.ROLE_NO_EXISTS);
                    log.warn(logMessage.log());

                } else {
                    Role role1 = roleMapper.selectById(role.getRoleId());
                    if (role1.getRoleStatusFlag() == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to checkRoleOperate role!", e);

        }
        return null;
    }
}




