import org.example.PrivilegeApplication;
import org.example.model.dao.RoleMapper;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.service.impl.RoleMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class RoleServiceTest {

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

    //分配权限
    @Test
    public void testGrantPowerToRole() {
        // 模拟角色ID和权限ID
        Integer roleId = 101;
        Integer powerId = 1004;
        // 执行授权操作
        roleMapperImpl.grantPowerToRole(roleId, powerId);
        System.out.println("授权成功！");

        // 验证授权结果
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        Assertions.assertEquals(true, isGranted, "权限授权失败");
    }
    //测试把不同权限赋予不同角色

    @Test
    public void testGrantUserToRole() {
        // 模拟角色ID和用户ID
        String userId = "0000018b-e2b0-57f4-8ad0-aefad80d6a4e";
        Integer roleId = 101;
        // 执行授权操作
        roleMapperImpl.grantUserToRole(userId, roleId);
        System.out.println("授权成功！");

        // 验证授权结果
        boolean isGranted = roleMapperImpl.isUserGrantedToRole(userId, roleId);
        Assertions.assertEquals(true, isGranted, "权限授权失败");
    }
    //测试把不同用户赋予不同角色

}
