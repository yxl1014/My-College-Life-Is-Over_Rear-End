package org.mysql;

import org.commons.exception.FormatException;
import org.database.DatabaseCompApplication;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.User;
import org.database.mysql.entity.MysqlBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/28
 */

@SpringBootTest(classes = DatabaseCompApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestPowerMapper {

    @Autowired
    private BaseMysqlComp mysqlComp;


    //增加权限
    @Test
    public void insertPower() {
        for (int i = 201; i < 300; i++) {
            Power power = new Power();
            power.setPowerId(i);
            power.setPowerName("权限管理权限管理");
            power.setPowerType((short) 0);
            power.setPowerCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            MysqlBuilder<Power> builder = new MysqlBuilder<>(Power.class);
            builder.buildIn(power);
            try {
                mysqlComp.insert(builder);
            } catch (FormatException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("权限创建成功！");
    }

    @Test
    public void selectPower() {
        MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
        User user = new User();
        user.setUserName("xxx");
        user.setUserPassword("123456");
        builder.setCondition(user);

        User out = new User();
        out.setUserId("xxx");
        builder.setOut(out);
        try {
            System.out.println(mysqlComp.selectList(builder));
        } catch (FormatException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("查询成功！");
    }

    @Test
    public void deletePower() {
        MysqlBuilder<Power> builder = new MysqlBuilder<>(Power.class);
        Power power = new Power();
        power.setPowerId(20);
        builder.setCondition(power);
        try {
            System.out.println(mysqlComp.delete(builder));
        } catch (FormatException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("删除成功！");
    }

    @Test
    public void updatePower() {
        MysqlBuilder<Power> builder = new MysqlBuilder<>(Power.class);
        Power power = new Power();
        power.setPowerId(21);
        builder.setCondition(power);

        Power update = new Power();
        update.setPowerName("用户");
        builder.setUpdate(update);
        try {
            System.out.println(mysqlComp.update(builder));
        } catch (FormatException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("更新成功！");
    }
}


