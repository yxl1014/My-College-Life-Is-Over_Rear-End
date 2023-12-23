import common.encrypt.PasswordEncrypt;
import common.uuid.UuidGenerator;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.Role;
import org.database.mysql.domain.User;
import org.privileges.PrivilegeApplication;
import org.privileges.service.impl.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        user.setUserFlag((short)1);
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
        user.setUserTelephone("17511111111");
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
        User user=new User();
        user.setUserId("0000018c-9627-fb7c-a144-1f7842991415");
        userMapperimpl.deleteUser(user);
        System.out.println("用户注销成功！");

    }

    //更新角色内容(根据userId)
    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setUserId("0000018c-5caf-cba7-8b25-8d2a2844cdab");
        user.setUserName("NoNo");
      user.setUserTelephone("NONO@yean.net");
        userMapperimpl.updateUser(user);
        System.out.println("角色信息已更新！");

    }

}





   /* @Test
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