import org.example.PrivilegeApplication;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
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
    public void insertRole() throws ParseException {
        Role role1 = new Role();
        role1.setRoleId(112);
        role1.setRoleName("销售员");
        role1.setRoleDate(LocalDateTime.now());
        role1.setRoleFlag("0");
        role1.setRoleMark("销售设备");
        roleMapperImpl.insertRole(role1);
        System.out.println("角色创建成功！");
    }

    //删除角色
    @Test
    public void deleteRole() {
        Role role = new Role();
        role.setRoleId(112);
        Role role1 = roleMapperImpl.selectOneRole(role);
        if (role1 != null) {
            roleMapperImpl.deleteRole(role1);
            System.out.println("角色删除成功！");
        } else {
            // 处理角色不存在的情况
            System.out.println("角色不存在！");
        }
    }

    //修改角色状态
    @Test
    public void updateRole() {
        Role role = new Role();
        role.setRoleId(111);
        Role role1 = roleMapperImpl.selectOneRole(role);
        role1.setRoleFlag("0");
        roleMapperImpl.updateRole(role1);
        System.out.println("角色状态已更新！");

    }

    //查询角色
    @Test
    public void selectRole() {
        Role role = new Role();
        role.setRoleName("管理员");
        Role role1 = roleMapperImpl.selectOneRole(role);
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
        Integer roleId = 101;
        Integer powerId = 1004;
        // 执行授权操作
        roleMapperImpl.grantPowerToRole(roleId, powerId);

        // 验证授权结果
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        if (isGranted) {
            System.out.println("权限成功授权给角色！");
        } else {
            Assertions.fail("权限授予角色失败！");
        }
    }

    //分配角色给用户
    @Test
    public void testGrantUserToRole() {
        // 模拟角色ID和用户ID
        String userId = "0000018b-e2b0-57f4-8ad0-aefad80d6a4e";
        Integer roleId = 100;
        // 执行授权操作
        roleMapperImpl.grantUserToRole(userId, roleId);

        // 验证授权结果
        boolean isGranted = roleMapperImpl.isUserGrantedToRole(userId, roleId);
        if (isGranted) {
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
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        if (isGranted) {
            roleMapperImpl.revokePowerFromRole(roleId, powerId);
            //验证是否撤销成功
            boolean isRevoked = !roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
            if (isRevoked) {
                System.out.println("权限已成功撤销！");
            } else {
                Assertions.fail("权限撤销失败！");
            }
        } else {
            System.out.println("该角色无此权限，无需删除！");
        }
    }

    @Test
    public void testGrantPowerToRoleVisit() {
        // 模拟角色ID和权限ID
        Integer roleId = 101;
        String powerName = "用户管理";
        int powerType = 2;//可操作为1，可访问为2
        Integer powerId = roleMapperImpl.grantPowerToRoleOperate(powerName,powerType);
        roleMapperImpl.grantPowerToRole(roleId, powerId);
        // 验证授权结果
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        if (isGranted) {
            System.out.println("权限成功授权给角色(可访问）！");
        } else {
            Assertions.fail("权限授予角色失败（可访问）！");
        }
    }

}
