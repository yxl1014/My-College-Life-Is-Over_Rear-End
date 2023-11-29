import example.tools.PasswordEncrypt;
import example.tools.UuidGenerator;
import org.example.PrivilegeApplication;
import org.example.model.dao.UserMapper;
import org.example.model.entity.User;
import org.example.service.impl.UserMapperImpl;
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
 * @Author: eensh
 * @CreateDate: 2023/11/28
 */
@SpringBootTest(classes= PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestUserMapper {

    @Autowired
    private UserMapperImpl userMapperimpl;
    @Test
    public void insertUser() throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        User user1 = new User();
        user1.setUserId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        user1.setUserName("Coco");
        user1.setUserTelephone("15600001234");
        user1.setUserSysEmail("Coco@yeah.com");
        user1.setUserPassword(PasswordEncrypt.hashPassword("IAmCoco"));
        user1.setUserNickName("Mood");
        user1.setUserGender("女");
        user1.setUserBornDay(new Date(sdf1.parse("2010-09-21").getTime()));
        user1.setUserIdCard("1234567890");
        user1.setUserMoney(1000.0);
        user1.setUserCompany("ABC.company");
        user1.setUserHome("BeiJin");
        user1.setUserIp("192.168.1.1");
        user1.setUserFlag(101);
        user1.setUserPersonalProfile("I am COCO");
        user1.setUserCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        userMapperimpl.insertUser(user1);
    }

    @Test
    public void deleteUser(){
        User user = new User();
        user.setUserTelephone("15600002200");
        User user1 = userMapperimpl.selectOneUser(user);
        userMapperimpl.deleteUser(user1);

    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setUserTelephone("15600002200");
        User user1 = userMapperimpl.selectOneUser(user);
        user1.setUserNickName("Sheer");
        userMapperimpl.updateUser(user1);
    }
    @Test
    public void select(){
        User user = new User();
        user.setUserSysEmail("john@example.com");
        User user1 = userMapperimpl.selectOneUser(user);
        System.out.println(user1);

        System.out.println("############################################################");
        System.out.println("以下是查询所有的用户得到信息:");
        List<User> userList = userMapperimpl.selectAllUser();
        for (User u: userList) {
            System.out.println(u);
        }
    }



}
