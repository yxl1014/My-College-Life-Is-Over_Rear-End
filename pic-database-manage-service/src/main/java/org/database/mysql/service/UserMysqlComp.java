package org.database.mysql.service;

import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @author yxl17
 * @Package : org.database.mysql.service
 * @Create on : 2023/12/24 16:28
 **/

@Component
public class UserMysqlComp {

    private final BaseMysqlComp mysqlComp;

    private final UserMapper userMapper;

    public UserMysqlComp(BaseMysqlComp mysqlComp) {
        this.mysqlComp = mysqlComp;
        this.userMapper = mysqlComp.getUserMapper();
    }

    @SneakyThrows
    public User getUserId(User user) {
        MysqlBuilder<User> mysqlBuilder = new MysqlBuilder<>(User.class);
        mysqlBuilder.setCondition(user);
        User out = new User();
        out.setUserId("a");
        mysqlBuilder.setOut(out);
        return mysqlComp.selectOne(mysqlBuilder);
    }

    @SneakyThrows
    public User findUserByUserId(String userId) {
        User user = new User();
        user.setUserId(userId);
        MysqlBuilder<User> mysqlBuilder = new MysqlBuilder<>(User.class);
        mysqlBuilder.setCondition(user);
        return mysqlComp.selectOne(mysqlBuilder);
    }

    @SneakyThrows
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUserName(username);
        MysqlBuilder<User> mysqlBuilder = new MysqlBuilder<>(User.class);
        mysqlBuilder.setCondition(user);
        return mysqlComp.selectOne(mysqlBuilder);
    }

    @SneakyThrows
    public boolean checkUserByUsername(String username) {
        User user = new User();
        user.setUserName(username);
        return getUserId(user) != null;
    }

    @SneakyThrows
    public boolean checkUserByTel(String tel) {
        User user = new User();
        user.setUserTelephone(tel);
        return getUserId(user) != null;
    }

    @SneakyThrows
    public boolean checkUserByEmail(String email) {
        User user = new User();
        user.setUserSysEmail(email);
        return getUserId(user) != null;
    }

    @SneakyThrows
    public boolean updateUserByUserId(User user) {
        if (Strings.isEmpty(user.getUserId())){
            return false;
        }
        MysqlBuilder<User> mysqlBuilder = new MysqlBuilder<>(User.class);
        mysqlBuilder.setCondition(new User(user.getUserId()));
        mysqlBuilder.setUpdate(user);
        Integer update = mysqlComp.update(mysqlBuilder);
        return update == 1;
    }

}
