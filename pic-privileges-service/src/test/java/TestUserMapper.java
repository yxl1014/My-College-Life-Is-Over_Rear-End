/*
import common.encrypt.PasswordEncrypt;
import common.uuid.UuidGenerator;
import org.privileges.PrivilegeApplication;
import org.privileges.model.entity.User;
import org.privileges.service.impl.UserMapperImpl;
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

*/
/**
 * @Author: eensh
 * @CreateDate: 2023/11/28
 *//*

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
        user1.setUserName("Fairy");
        user1.setUserTelephone("15623402234");
        user1.setUserSysEmail("Fairy@yeah.com");
        user1.setUserPassword(PasswordEncrypt.hashPassword("IAmFairy"));
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
        userMapperimpl.deleteUser("0000018c-42d4-7a7a-a2ef-164497e596e8");
        System.out.println("用户注销成功！");
    }

    @Test
    public void updateUser() {
        User user = userMapperimpl.selectOneUser("0000018b-e2b0-57f4-8ad0-aefad80d6a4e");
        user.setUserName("fiona");
        System.out.println(user);
        userMapperimpl.updateUser(user);
        System.out.println("用户信息更新成功！");
    }

    @Test
    public void selectUser() {
        User user1 = userMapperimpl.selectOneUser("0000018b-e2b0-57f4-8ad0-aefad80d6a4e");
        User user2 = userMapperimpl.selectOneUser("Sheen");
        User user3 = userMapperimpl.selectOneUser("15623402234");
        User user4 = userMapperimpl.selectOneUser("Coco@yeah.com");
        if (user1 != null || user2 != null || user3 != null || user4 != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user3);
            System.out.println(user4);
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

    @Test
    public void findUserById() {
        User user = userMapperimpl.findUserById("0000018b-e2b0-57f4-8ad0-aefad80d6a4e");
        if (user != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user);
        } else {
            System.out.println("用户不存在！");
        }
    }

    @Test
    public void findUserByUserName() {
        User user = userMapperimpl.findUserByUserName("Sheen");
        if (user != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user);
        } else {
            System.out.println("用户不存在！");
        }
    }

    @Test
    public void findUserByTelephone() {
        User user = userMapperimpl.findUserByTelephone("15623402234");
        if (user != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user);
        } else {
            System.out.println("用户不存在！");
        }
    }

    @Test
    public void findUserByEmail() {
        User user = userMapperimpl.findUserByEmail("Coco@yeah.com");
        if (user != null) {
            System.out.println("用户信息查询成功");
            System.out.println(user);
        } else {
            System.out.println("用户不存在！");
        }
    }


}
*/
