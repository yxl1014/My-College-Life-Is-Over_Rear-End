package org.mysql;

import exception.FormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mysql.domain.Power;
import org.mysql.domain.User;
import org.mysql.entity.MysqlBuilder;
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

@SpringBootTest(classes = MysqlTestApplication.class)
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
        builder.setIn(user);

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
        builder.setIn(power);
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
        builder.setIn(power);

        Power update = new Power();
        update.setPowerName("xxx");
        builder.setUpdate(update);
        try {
            System.out.println(mysqlComp.update(builder));
        } catch (FormatException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println("删除成功！");
    }
}


