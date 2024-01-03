package org.database.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.SneakyThrows;
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

    /**
     * 通过电话、邮箱、用户名判断用户是否存在
     *
     * @param key 电话|邮箱|用户名
     * @return 是否存在
     */
    public boolean checkUserIsExist(String key) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, key)
                .or().eq(User::getUserTelephone, key)
                .or().eq(User::getUserSysEmail, key);
        Integer count = userMapper.selectCount(wrapper);
        return count > 0;
    }

    @SneakyThrows
    public User getUserId(User user) {
        MysqlBuilder<User> mysqlBuilder = new MysqlBuilder<>(User.class);
        mysqlBuilder.setIn(user);
        User out = new User();
        out.setUserId("a");
        mysqlBuilder.setOut(out);
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

}
