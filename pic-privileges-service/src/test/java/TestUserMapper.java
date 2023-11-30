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
@SpringBootTest(classes = PrivilegeApplication.class)
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
        user1.setUserName("Sheen");
        user1.setUserTelephone("15623401234");
        user1.setUserSysEmail("Sheen@yeah.com");
        user1.setUserPassword(PasswordEncrypt.hashPassword("IAmSheen"));
        user1.setUserNickName("Fairy");
        user1.setUserGender("女");
        user1.setUserBornDay(new Date(sdf1.parse("2012-09-21").getTime()));
        user1.setUserIdCard("234454567890");
        user1.setUserMoney(100032.0);
        user1.setUserCompany("USA.company");
        user1.setUserHome("BeiJin");
        user1.setUserIp("192.168.1.1");
        user1.setUserFlag(101);
        user1.setUserPersonalProfile("I am Sheen");
        user1.setUserCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        userMapperimpl.insertUser(user1);
        System.out.println("用户创建成功！");
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setUserTelephone("16666666666");
        User user1 = userMapperimpl.selectOneUser(user);
        userMapperimpl.deleteUser(user1);
        System.out.println("用户注销成功！");
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserTelephone("15600001234");
        User user1 = userMapperimpl.selectOneUser(user);
        user1.setUserName("CoCo");
        userMapperimpl.updateUser(user1);
        System.out.println("用户信息更新成功！");
    }

    @Test
    public void selectUser() {
        User user = new User();
        user.setUserSysEmail("Sheen@yeah.com");
        User user1 = userMapperimpl.selectOneUser(user);
        if (user1 != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user1);
        } else {
            System.out.println("用户不存在！");
        }


        System.out.println("############################################################");
        System.out.println("以下是查询所有的用户得到信息:");
        List<User> userList = userMapperimpl.selectAllUser();
        for (User u : userList) {
            System.out.println(u);
        }
    }


}
