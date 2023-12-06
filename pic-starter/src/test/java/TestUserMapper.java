import common.encrypt.PasswordEncrypt;

import common.uuid.UuidGenerator;
import example.StarterApplication;
import example.entity.database.User;
import example.mapper.UserMapper;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/4
 */

@SpringBootTest(classes=StarterApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        User user1 = new User();

        user1.setUserId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        user1.setUserName("John");
//        user1.setUserTelephone("22345657890");
        user1.setUserTelephone(null);
//        user1.setUserSysEmail("john@example.com");

        user1.setUserPassword(PasswordEncrypt.hashPassword("password123"));
//        user1.setUserNickName("Johnny");
//        user1.setUserGender("男");
        user1.setUserBornDay(new Date(sdf1.parse("2003-09-10").getTime()));

//        user1.setUserIdCard("1234567890");
//        user1.setUserMoney(1000.0);
//        user1.setUserCompany("ABC Inc.");
//        user1.setUserHome("123 Main St");

//        user1.setUserIp("192.168.1.1");
//        user1.setUserFlag();
//        user1.setUserPersonalProfile("I am John");
        user1.setUserCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        System.out.println(user1);
        userMapper.insert(user1);
    }

    @Test
    public void delete(){
        User user = new User();
        user.setUserTelephone("22345657890");
        User user1 = userMapper.selectOne(user);
        userMapper.delete(user1);
        
    }
    @Test
    public void update(){
        User user = new User();
        user.setUserTelephone("22345657890");
        User user1 = userMapper.selectOne(user);
        user1.setUserNickName("Hammer");
        userMapper.update(user1);
    }
    @Test
    public void select(){
        User user = new User();
        user.setUserSysEmail("john@example.com");
        User user1 = userMapper.selectOne(user);
        System.out.println(user1);

        System.out.println("############################################################");
        System.out.println("以下是查询所有的用户得到信息:");
        List<User> userList = userMapper.selectAll();
        for (User u: userList) {
            System.out.println(u);
        }
    }



}
