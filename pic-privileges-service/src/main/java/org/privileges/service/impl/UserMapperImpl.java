
package org.privileges.service.impl;

import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.commons.log.LogEnum;
import org.commons.log.LogType;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
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
public class UserMapperImpl {
    private final RoleMapper roleMapper;
    private final PowerMapper powerMapper;
    private final UserMapper userMapper;

    private final BaseMysqlComp baseMysqlComp;
    private static final Logger log = LogComp.getLogger(UserMapperImpl.class);


    @Autowired
    public UserMapperImpl(RoleMapper roleMapper, PowerMapper powerMapper, UserMapper userMapper, BaseMysqlComp baseMysqlComp) {
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.baseMysqlComp = baseMysqlComp;

    }

    /**
     * 增加用户（用户注册）
     * 参数校验:用户电话 用户邮箱 用户名不能重复存在;
     * 当前操作用户有添加用户的权限（未完成）
     *
     * @param user
     * @throws Exception
     */

    public void insertUser(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);

            if (user == null || user.getUserId() == null || user.getUserName() == null || user.getUserTelephone() == null || user.getUserSysEmail() == null) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<User> insertUser = new MysqlBuilder<>(User.class);
                insertUser.setCondition(user);

                MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
                User user1 = new User();
                user1.setUserName(user.getUserName());
                User user1Flag = baseMysqlComp.selectOne(builder.buildIn(user1));

                User user2 = new User();
                user2.setUserTelephone(user.getUserTelephone());
                User user2Flag = baseMysqlComp.selectOne(builder.buildIn(user2));

                User user3 = new User();
                user3.setUserSysEmail(user.getUserSysEmail());
                User user3Flag = baseMysqlComp.selectOne(builder.buildIn(user3));

                if (user1Flag != null) {
                    log.warn("用户名已存在，请重新输入");
                } else if (user2Flag != null) {
                    log.warn("用户电话已存在，请重新输入");

                } else if (user3Flag != null) {
                    log.warn("用户邮箱已存在，请重新输入");
                } else {
                    baseMysqlComp.insert(insertUser);
                }
            }
        } catch (Exception e) {
            log.error("Failed to insert user!", e);
        }
    }

    /**
     * 判断字符串是否为纯数字
     *
     * @param str
     * @return
     */

    private Boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    /**
     * 判断字符串是否为有效邮箱地址
     *
     * @param str
     * @return
     */
    private Boolean isValidEmail(String str) {
        return str != null && str.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}");
    }

    /**
     * 判断字符串是否为uuid
     *
     * @param str
     * @return
     */
    private boolean isValidUuid(String str) {
        return str != null && str.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    }

    /**
     * 查找用户信息
     * 参数校验：可通过用户名、用户电话、用户id、用户邮箱找到用户；
     * 当前操作用户有查找用户信息权限
     *
     * @param user
     * @throws Exception
     */
    public void selectOneUser(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || (user.getUserId() == null && user.getUserName() == null && user.getUserTelephone() == null && user.getUserSysEmail() == null)) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<User> selectOneUser = new MysqlBuilder<>(User.class);
                selectOneUser.setCondition(user);
                if (baseMysqlComp.selectOne(selectOneUser) == null) {
                    logMessage.build(LogEnum.USER_NO_Exists);
                    log.error(logMessage.log());

                } else {
                    baseMysqlComp.selectOne(selectOneUser);
                }
            }
        } catch (Exception e) {
            log.error("Failed to select user!", e);
        }
    }

    /**
     * 查询用户列表信息
     *
     * @return
     * @throws Exception
     */

    public List<User> selectAllUser() throws Exception {
        MysqlBuilder<User> selectAllUser = new MysqlBuilder<>(User.class);
        return baseMysqlComp.selectList(selectAllUser);
    }

    /**
     * 删除角色（注销）
     * 应该获取当前用户信息再注销，而不是输入id
     * 这里还没有完成获取当前用户的功能
     * 参数校验:角色id不为空；
     * 当前操作用户有注销的权限（未完成）
     *
     * @param user
     * @throws Exception
     */
    public void deleteUser(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || user.getUserId() == null) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<User> deleteUser = new MysqlBuilder<>(User.class);
                deleteUser.setCondition(user);
                if (baseMysqlComp.selectOne(deleteUser) == null) {
                    logMessage.build(LogEnum.USER_NO_Exists);
                    log.error(logMessage.log());
                } else {
                    baseMysqlComp.delete(deleteUser);
                }
            }
        } catch (Exception e) {
            log.error("Failed to delete user!", e);
        }
    }

    /**
     * 更新角色信息
     * 这里应该获取当前用户id（但是未完成）
     * 参数校验：用户id不为空；
     * 当前操作用户有更新用户的权限（未完成）；
     *
     * @param user
     * @throws Exception
     */

    public void updateUser(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || user.getUserId() == null) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (userMapper.selectById(user.getUserId()) == null) {
                    logMessage.build(LogEnum.USER_NO_Exists);
                    log.error(logMessage.log());
                } else {
                    MysqlBuilder<User> updateUser = new MysqlBuilder<>(User.class);
                    User user1 = userMapper.selectById(user.getUserId());
                    updateUser.setCondition(user1);
                    updateUser.setUpdate(user);
                    baseMysqlComp.update(updateUser);
                }
            }


        } catch (Exception e) {
            log.error("Failed to update user!", e);
        }
    }

    /**
     * 验证登陆
     * 参数校验 登陆信息不为空
     * 用户账号可以是用户名
     *
     * @param user
     * @return
     */

    public Boolean loginUserByName(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || (user.getUserName() == null && user.getUserTelephone() == null && user.getUserSysEmail() == null)) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            }
            MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
            User user1 = new User();
            user1.setUserName(user.getUserName());
            User user1Flag = baseMysqlComp.selectOne(builder.buildIn(user1));
            if (user1Flag == null) {
                log.warn("用户名不存在，请重新输入");
            } else {
                MysqlBuilder<User> loginUser = new MysqlBuilder<>(User.class);
                loginUser.setCondition(user);
                return baseMysqlComp.selectOne(loginUser) != null;

            }
        } catch (Exception e) {
            log.error("Failed to loginUser!", e);
        }
        return null;
    }

    /**
     * 验证登陆
     * 参数校验 登陆信息不为空
     * 用户账号可以是用户邮箱
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Boolean loginUserByEmail(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || (user.getUserName() == null && user.getUserTelephone() == null && user.getUserSysEmail() == null)) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            }
            MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
            User user1 = new User();
            user1.setUserSysEmail(user.getUserSysEmail());
            User user1Flag = baseMysqlComp.selectOne(builder.buildIn(user1));
            if (user1Flag == null) {
                log.warn("用户邮箱不存在，请重新输入");
            } else {
                MysqlBuilder<User> loginUser = new MysqlBuilder<>(User.class);
                loginUser.setCondition(user);
                return baseMysqlComp.selectOne(loginUser) != null;

            }
        } catch (Exception e) {
            log.error("Failed to loginUserByEmail!", e);
        }
        return null;
    }

    /**
     * 验证登陆
     * 参数校验 登陆信息不为空
     * 用户账号可以是用户电话
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Boolean loginUserByTelephone(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || (user.getUserName() == null && user.getUserTelephone() == null && user.getUserSysEmail() == null)) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            }
            MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);

            User user1 = new User();
            user1.setUserTelephone(user.getUserTelephone());
            User user1Flag = baseMysqlComp.selectOne(builder.buildIn(user1));
            if (user1Flag == null) {
                log.warn("用户电话不存在，请重新输入");
            } else {
                MysqlBuilder<User> loginUser = new MysqlBuilder<>(User.class);
                loginUser.setCondition(user);
                return baseMysqlComp.selectOne(loginUser) != null;

            }
        } catch (Exception e) {
            log.error("Failed to loginUserByTelephone!", e);
        }
        return null;
    }

    /**
     * 判断用户的角色
     * 参数校验 用户信息不为空(预设了用户已经有角色)
     * 用户存在，用户已赋予角色
     *
     * @param user
     * @return
     * @throws Exception
     */

    public Short isUserWhatToRole(User user) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.USER);
            if (user == null || user.getUserId() == null) {
                logMessage.build(LogEnum.USER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<User> isUserWhatToRole = new MysqlBuilder<>(User.class);
                isUserWhatToRole.setCondition(user);
                if (baseMysqlComp.selectOne(isUserWhatToRole) == null) {
                    logMessage.build(LogEnum.USER_NO_Exists);
                    log.error(logMessage.log());
                } else {
                    UserRoleRef userRoleRef1 = new UserRoleRef();
                    userRoleRef1.setRefUserId(user.getUserId());
                    MysqlBuilder<UserRoleRef> builder = new MysqlBuilder<>(UserRoleRef.class);
                    builder.setCondition(userRoleRef1);
                    return baseMysqlComp.selectOne(builder).getRefRoleId();
                }
            }

        } catch (Exception e) {
            log.error("Failed to isUserWhatToRole!", e);
        }
        return null;
    }


}

