import org.example.PrivilegeApplication;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.service.impl.RoleMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestRoleMapper {

    @Autowired
    private RoleMapperImpl roleMapperImpl;

    //增加角色
    @Test
    public void insertRole() {
        Role role1 = new Role();
        role1.setRoleId(112);
        role1.setRoleName("销售员");
        role1.setRoleDate(LocalDateTime.now());
        role1.setRoleFlag(0);
        role1.setRoleMark("销售设备");
        roleMapperImpl.insertRole(role1);
        System.out.println("角色创建成功！");
    }

    //删除角色
    @Test
    public void deleteRole() {
        Role role = roleMapperImpl.selectOneRole("112");
        if (role != null) {
            roleMapperImpl.deleteRole(112);
            System.out.println("角色删除成功！");
        } else {
            // 处理角色不存在的情况
            System.out.println("角色不存在！");
        }
    }

    //修改角色状态
    @Test
    public void updateRole() {
        Role role = roleMapperImpl.selectOneRole("101");
        role.setRoleFlag(0);
        roleMapperImpl.updateRole(role);
        System.out.println("角色状态已更新！");

    }

    //查询角色
    @Test
    public void selectRole() {
        Role role = roleMapperImpl.selectOneRole("管理员");
        if (role != null) {
            System.out.println("角色信息查询成功");
            System.out.println(role);
        } else {
            System.out.println("角色不存在！");
        }
        Role role1 = roleMapperImpl.selectOneRole("101");
        if (role1 != null) {
            System.out.println("角色信息查询成功");
            System.out.println(role1);
        } else {
            System.out.println("角色不存在！");
        }
        System.out.println("############################################################");
        System.out.println("以下是查询所有的角色得到信息:");
        List<Role> roleList = roleMapperImpl.selectAllRole();
        for (Role r : roleList) {
            System.out.println(r);
        }
    }

    //分配权限给角色
    @Test
    public void testGrantPowerToRole() {
        // 模拟角色ID和权限ID
        Integer roleId = 110;
        String powerName="报酬系统";
        int powerType=2;
        // 执行授权并验证授权结果
        if (roleMapperImpl.grantPowerToRole(roleId,powerName,powerType)){
            System.out.println("权限成功授权给角色！");
        } else {
            Assertions.fail("权限授予角色失败！");
        }
    }


    //分配角色给用户
    @Test
    public void testGrantUserToRole() {
        // 模拟角色ID和用户ID
        String userId = "0000018b-e2c3-eb02-bad0-896cbb1b7143b";
        Integer roleId = 110;
        // 执行授权并验证授权结果
        if (roleMapperImpl.grantUserToRole(userId, roleId)) {
            System.out.println("分配角色给用户成功！");
        } else {
            Assertions.fail("分配角色给用户失败！");
        }
    }



    //测试角色撤销已有权限
    @Test
    public void revokePowerFromRole() {
        //模拟角色Id和权限Id
        Integer roleId = 101;
        Integer powerId = 1004;
        if (roleMapperImpl.revokePowerFromRole(roleId, powerId)) {
                System.out.println("权限已成功撤销！");
            } else {
                Assertions.fail("权限撤销失败！");
            }
    }


    //测试用户撤销已有角色
    @Test
    public void revokeUserFromRole() {
        //模拟角色Id和权限Id
        String userId = "0000018b-e2c3-eb02-bad0-896cbb1b7143";
        Integer roleId = 110;

        if (roleMapperImpl.revokeUserFromRole(userId, roleId)) {
                System.out.println("用户已成功撤销该角色！");
            } else {
                Assertions.fail("角色撤销失败！");
            }
    }

    //可用状态用户列表
    @Test
    public void isAbleToRole() {
        System.out.println("############################################################");
        System.out.println("以下是查询所有状态可用的角色信息:");
        int roleFlag = 0;//如果为0则为可用
        List<Role> roleList = roleMapperImpl.isAbleToRole(roleFlag);
        for (Role r : roleList) {
            System.out.println(r);
        }
    }

    //判断用户的角色
    @Test
    public void isUserWhatToRole() {
        String userId = "0000018b-e2b0-2c75-988b-44cac59dd328";
        String roleName = roleMapperImpl.isUserWhatToRole(userId);
        System.out.println(userId+"的角色是:"+roleName);
    }


    //不同角色赋予权限不同操作（可访问or可操作）
    @Test
    public void testGrantPowerToRoleOperate() {
        // 模拟角色ID和权限ID
        String powerName = "用户管理";
        int powerType = 2;//可操作为1，可访问为2
        Integer powerId = roleMapperImpl.grantPowerToRoleOperate(powerName, powerType);
        System.out.println(powerId);
    }

}
