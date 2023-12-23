
package org.privileges.service.impl;

import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
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

    @Autowired
    public UserMapperImpl(RoleMapper roleMapper, PowerMapper powerMapper, UserMapper userMapper, BaseMysqlComp baseMysqlComp) {
        this.roleMapper = roleMapper;
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.baseMysqlComp = baseMysqlComp;

    }

    //增加用户（用户注册）
    public void insertUser(User user) throws Exception {
        // 参数校验 用户电话 用户邮箱 用户名不能重复存在，当前操作用户有添加用户的权限（未完成）
        if (user == null || user.getUserId() == null || user.getUserName() == null || user.getUserTelephone() == null || user.getUserSysEmail() == null) {
            //throw new UserExceptions.EmptyUserException();
            //抛出用户信息为空异常
        } else {
            MysqlBuilder<User> insertUser = new MysqlBuilder<>(User.class);
            insertUser.setIn(user);

            MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
            User user1 = new User();
            user1.setUserName(user.getUserName());

            User user2 = new User();
            user2.setUserTelephone(user.getUserTelephone());

            User user3 = new User();
            user3.setUserSysEmail(user.getUserSysEmail());
            if (baseMysqlComp.selectOne(builder.buildIn(user1)) != null) {
                throw new IllegalArgumentException("用户名已存在，请重新输入");
            } else if (baseMysqlComp.selectOne(builder.buildIn(user2)) != null) {
                throw new IllegalArgumentException("用户电话已存在，请重新输入");
            } else if (baseMysqlComp.selectOne(builder.buildIn(user3)) != null) {
                throw new IllegalArgumentException("用户邮箱已存在，请重新输入");
            } else {
                baseMysqlComp.insert(insertUser);
            }
        }
    }


    //查找用户信息
    public void selectOneUser(User user) throws Exception {
        // 参数校验 可通过用户名、用户电话、用户id、用户邮箱找到用户
        if (user == null || (user.getUserId() == null && user.getUserName() == null && user.getUserTelephone() == null && user.getUserSysEmail() == null)) {
            //throw new UserExceptions.EmptyUserException();
            //抛出用户信息为空异常
        } else {
            MysqlBuilder<User> selectOneUser = new MysqlBuilder<>(User.class);
            selectOneUser.setIn(user);
            if (baseMysqlComp.selectOne(selectOneUser) == null) {
                //throw new UserExceptions.UserNoExistsException();
                //抛出自定义的用户不存在的异常
            } else {

                //执行查找操作
                baseMysqlComp.selectOne(selectOneUser);
            }
        }
    }

    //查询用户列表信息
    public List<User> selectAllUser() throws Exception {
        MysqlBuilder<User> selectAllUser = new MysqlBuilder<>(User.class);
        return baseMysqlComp.selectList(selectAllUser);
    }

    //删除角色（注销）
    //应该获取当前用户信息再注销，而不是应该输入id
    //这里还没有完成获取当前用户的功能
    public void deleteUser(User user) throws Exception {
        // 参数校验:角色id不为空；当前操作用户有注销的权限（未完成）
        if (user == null || user.getUserId() == null) {
            //throw new UserExceptions.EmptyUserException();
            //抛出自定义的角色信息为空的异常
        } else {
            MysqlBuilder<User> deleteUser = new MysqlBuilder<>(User.class);
            deleteUser.setIn(user);
            if (baseMysqlComp.selectOne(deleteUser) == null) {
                //throw new UserExceptions.UserNoExistsException();
                //抛出自定义的角色不存在的异常
            } else {
                //执行删除操作
                baseMysqlComp.delete(deleteUser);
            }
        }
    }

    //更新角色信息
    //这里应该获取当前用户id（但是未完成）
    public void updateUser(User user) throws Exception {
        // 参数校验：用户id不为空；当前操作用户有更新用户的权限（未完成）；
        if (user == null || user.getUserId() == null) {
            //throw new UserExceptions.EmptyUserException();
            //抛出自定义的角色信息为空的异常
        } else {
            if (userMapper.selectById(user.getUserId()) == null) {
                //throw new UserExceptions.UserNoExistsException();
                //抛出自定义的角色不存在的异常
            } else {
                MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);

                User user2 = new User();
                user2.setUserName(user.getUserName());

                User user3 = new User();
                user3.setUserTelephone(user.getUserTelephone());

                User user4 = new User();
                user4.setUserSysEmail(user.getUserSysEmail());

              /*  if (baseMysqlComp.selectOne(builder.buildIn(user2)) != null) {
                    throw new IllegalArgumentException("用户名已存在，请重新输入");
                } else if (baseMysqlComp.selectOne(builder.buildIn(user3)) != null) {
                    throw new IllegalArgumentException("用户电话已存在，请重新输入");
                } else if (baseMysqlComp.selectOne(builder.buildIn(user4)) != null) {
                    throw new IllegalArgumentException("用户邮箱已存在，请重新输入");
                }*/
                MysqlBuilder<User> updateUser = new MysqlBuilder<>(User.class);
                updateUser.setIn(user);
                User user1 = userMapper.selectById(user.getUserId());
                updateUser.setIn(user1);
                updateUser.setUpdate(user);
                //执行更新操作
                baseMysqlComp.update(updateUser);
            }

        }
    }

}


 /*

    public void updateUser(User user) {
        // 参数校验
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        } else if (userMapper.findUserById(user.getUserId()) == null) {
            throw new IllegalArgumentException("用户不存在（用户id不可修改）");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        // 执行更新
        userMapper.updateUser(user);
    }


    // 根据传入的查询条件判断是使用用户ID、用户名还是用户邮箱进行查询
    public User selectOneUser(String query) {
        // 参数校验
        if (query == null) {
            throw new IllegalArgumentException("查询信息不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        User user = null;
        if (isValidUuid(query)) {
            // 如果查询条件符合uuid，则使用用户id进行查询
            user = userMapper.findUserById(query);
        } else if (isNumeric(query)) {
            // 如果查询条件是纯数字，则使用用户电话进行查询
            user = userMapper.findUserByTelephone(query);
        } else if (isValidEmail(query)) {
            // 如果查询条件是有效的邮箱地址，则使用用户邮箱进行查询
            user = userMapper.findUserByEmail(query);
        } else {
            // 否则，默认使用用户名进行查询
            user = userMapper.findUserByUserName(query);
        }
        return user;
    }


















/*
    //验证登陆
    public Boolean loginUser(String userName, String userPassword) {
        // 参数校验 用户名和用户密码不能重复存在
        if (userName == null || userPassword == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        User user = userMapper.findByUsernameAndPassword(userName, userPassword);
        return user!=null;
    }

    public String GetUser(String userName, String userPassword) throws AuthenticationException {
        // 参数校验 用户名和用户密码不能重复存在
        if (userName == null || userPassword == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }

        User user = userMapper.findByUsernameAndPassword(userName, userPassword);
        if(user == null){
            throw new AuthenticationException("Invalid userName or userPassword");
        }
        //生成JWT
        String token=Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(new Date(System.currentTimeMillis()+3600000))//过期时间是1小时
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();
        return token;
    }





/*

    public void insertUser(User user) {
        // 参数校验,用户id 用户电话 用户邮箱 用户名不能重复存在
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        } else if (userMapper.findUserById(user.getUserId()) != null || userMapper.findUserByEmail(user.getUserSysEmail()) != null || userMapper.findUserByTelephone(user.getUserTelephone()) != null || userMapper.findUserByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException("用户已存在");
        }

        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        //执行插入
        userMapper.insertUser(user);
    }

    public void deleteUser(String userId) {
        // 参数校验
        if (userId == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }

        // 权限验证
       */
/* if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        //执行删除
        userMapper.deleteUser(userId);
    }

    public void updateUser(User user) {
        // 参数校验
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        } else if (userMapper.findUserById(user.getUserId()) == null) {
            throw new IllegalArgumentException("用户不存在（用户id不可修改）");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        // 执行更新
        userMapper.updateUser(user);
    }


    // 根据传入的查询条件判断是使用用户ID、用户名还是用户邮箱进行查询
    public User selectOneUser(String query) {
        // 参数校验
        if (query == null) {
            throw new IllegalArgumentException("查询信息不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        User user = null;
        if (isValidUuid(query)) {
            // 如果查询条件符合uuid，则使用用户id进行查询
            user = userMapper.findUserById(query);
        } else if (isNumeric(query)) {
            // 如果查询条件是纯数字，则使用用户电话进行查询
            user = userMapper.findUserByTelephone(query);
        } else if (isValidEmail(query)) {
            // 如果查询条件是有效的邮箱地址，则使用用户邮箱进行查询
            user = userMapper.findUserByEmail(query);
        } else {
            // 否则，默认使用用户名进行查询
            user = userMapper.findUserByUserName(query);
        }
        return user;
    }


    public User findUserById(String userId) {
        // 参数校验
        if (userId == null) {
            throw new IllegalArgumentException("用户id信息不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserById(userId);
    }

    public User findUserByUserName(String userName) {
        // 参数校验
        if (userName == null) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByUserName(userName);
    }

    public User findUserByTelephone(String userTelephone) {
        // 参数校验
        if (userTelephone == null) {
            throw new IllegalArgumentException("用户电话不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByTelephone(userTelephone);
    }

    public User findUserByEmail(String userSysEmail) {
        // 参数校验
        if (userSysEmail == null) {
            throw new IllegalArgumentException("用户email不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByEmail(userSysEmail);
    }

    //判断字符串是否为纯数字
    private Boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    //判断字符串是否为有效邮箱地址
    private Boolean isValidEmail(String str) {
        return str != null && str.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}");
    }

    //判断字符串是否为uuid
    private boolean isValidUuid(String str) {
        return str != null && str.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    }

    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }



}
*/
