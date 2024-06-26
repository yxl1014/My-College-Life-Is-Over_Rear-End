import org.commons.common.encrypt.PasswordEncrypt;
import org.commons.common.uuid.UuidGenerator;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.Role;
import org.database.mysql.domain.User;
import org.database.mysql.domain.UserRoleRef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.privileges.PrivilegeApplication;
import org.privileges.service.impl.UserMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */


@SpringBootTest(classes = PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestUserMapper {

    @Autowired
    private UserMapperImpl userMapperimpl;

    @Test
    public void insertUser() throws Exception {

        User user = new User();
        user.setUserId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        user.setUserName("yiyi");
        user.setUserTelephone("15625402279");
        user.setUserSysEmail("yiyiO@yeah.com");
        user.setUserPassword(PasswordEncrypt.hashPassword("IAmFairy"));
        user.setUserNickName("Fairy");
        user.setUserGender("女");
        user.setUserBornDay(Timestamp.valueOf("2012-09-21 00:00:00"));
        user.setUserIdCard("234454567890");
        user.setUserMoney(100032.0);
        user.setUserCompany("USA.company");
        user.setUserHome("BeiJin");
        user.setUserIp("192.168.1.1");
        user.setUserFlag((short) 1);
        user.setUserPersonalProfile("I am Sheen");
        user.setUserCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        userMapperimpl.insertUser(user);
        System.out.println("用户创建成功！");
    }

    //根据userid或userName、UserTelephone、setUserSysEmail查询
    @Test
    public void selectOneUser() throws Exception {
        User user = new User();
        //user.setUserId("0000018c-9625-4068-9372-b4ad2bac297f");
        user.setUserName("sheen");
        // user.setUserTelephone("17511111111");
        //  user.setUserSysEmail("Fairy@yeah.com");

        userMapperimpl.selectOneUser(user);
        System.out.println("权限查找成功！");


        System.out.println("############################################################");
        System.out.println("以下是查询所有的权限得到信息:");
        List<User> userList = userMapperimpl.selectAllUser();
        for (User p : userList) {
            System.out.println(p);
        }

    }

    //删除角色
    @Test
    public void deleteRole() throws Exception {
        User user = new User();
        user.setUserId("0000018c-9695-d415-b4cc-2139567c2c13");
        userMapperimpl.deleteUser(user);
        System.out.println("用户注销成功！");

    }

    //更新角色内容(根据userId)
    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setUserId("0000018c-5caf-cba7-8b25-8d2a2844cdab");
        user.setUserName("cire");
        user.setUserSysEmail("Cob@yean.net");
        //  user.setUserTelephone("12345678457");
        userMapperimpl.updateUser(user);
        System.out.println("角色信息已更新！");
    }

    //验证用户名登陆
    @Test
    public void loginUserByName() throws Exception {
        User user = new User();
        user.setUserId("0000018c-a3e0-8f55-861b-daa075e0eb38");
        user.setUserName("yiyi");
        user.setUserPassword(PasswordEncrypt.hashPassword("IAmFairy"));
        if (userMapperimpl.loginUserByName(user)) {
            System.out.println("登陆成功！");
        } else {
            System.out.println("登陆失败！");
        }
    }


    //验证用户邮箱登陆
    @Test
    public void loginUserByEmail() throws Exception {
        User user = new User();
        user.setUserId("0000018c-a3e0-8f55-861b-daa075e0eb38");
        user.setUserSysEmail("yiyiO@yeah.com");
        user.setUserPassword(PasswordEncrypt.hashPassword("IAmFairy"));
        if (userMapperimpl.loginUserByEmail(user)) {
            System.out.println("登陆成功！");
        } else {
            System.out.println("登陆失败！");
        }

    }

    //验证用户电话登陆
    @Test
    public void loginUserByTelephone() throws Exception {
        User user = new User();
        user.setUserId("0000018c-a3e0-8f55-861b-daa075e0eb38");
        user.setUserTelephone("15625402279");
        user.setUserPassword(PasswordEncrypt.hashPassword("IAmFairy"));
        if (userMapperimpl.loginUserByTelephone(user)) {
            System.out.println("登陆成功！");
        } else {
            System.out.println("登陆失败！");
        }
        System.out.println("登陆成功！");
    }

    //验证用户名登陆
    @Test
    public void isUserWhatToRole() throws Exception {
        User user = new User();
        user.setUserId("0000018c-251b-4676-bd25-17bad9ba3899");
        System.out.println("该用户的角色是:" + userMapperimpl.isUserWhatToRole(user));
    }
}

