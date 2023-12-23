
import org.database.mysql.domain.*;
import org.privileges.PrivilegeApplication;
import org.privileges.service.impl.RoleMapperImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
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
    public void insertRole() throws Exception {
        Role role = new Role();
        role.setRoleId((short) 109);
        role.setRoleName("测试者");
        role.setRoleCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        role.setRoleStatusFlag((short) 0);
        role.setRoleRemark("销售设备");

        roleMapperImpl.insertRole(role);
        System.out.println("角色创建成功！");
    }

    //根据roleId或roleName查询
    @Test
    public void selectOneRole() throws Exception {
        Role role = new Role();
        // role.setRoleId((short)101);
        role.setRoleName("被测者");
        roleMapperImpl.selectOneRole(role);
        System.out.println("权限查找成功！");

        System.out.println("############################################################");
        System.out.println("以下是查询所有的权限得到信息:");
        List<Role> roleList = roleMapperImpl.selectAllRole();
        for (Role p : roleList) {
            System.out.println(p);
        }
        System.out.println("以下是查询所有的可用权限得到信息:");
        List<Role> role2List = roleMapperImpl.selectAllDoRole();
        for (Role p : role2List) {
            System.out.println(p);
        }
    }

    //删除角色
    @Test
    public void deleteRole() throws Exception {
        Role role = new Role();
        role.setRoleId((short) 104);
        roleMapperImpl.deleteRole(role);
        System.out.println("角色删除成功！");

    }

    @Test
    public void updateRole() throws Exception {
        Role role = new Role();
        role.setRoleId((short) 103);
        role.setRoleRemark("维修");
        roleMapperImpl.updateRole(role);
        System.out.println("角色状态已更新！");

    }

    //分配角色给用户
    @Test
    public void grantUserToRole() throws Exception {
        UserRoleRef userRoleRef = new UserRoleRef();
        userRoleRef.setRefUserId("0000018c-5caf-cba7-8b25-8d2a2844cdab");
        userRoleRef.setRefRoleId((short) 101);
        // 执行授权并验证授权结果
        if (roleMapperImpl.grantRoleToUser(userRoleRef)) {
            System.out.println("分配角色给用户成功！");
        } else {
            System.out.println("分配角色给用户失败！");
        }
    }

    //撤销角色给用
    @Test
    public void revokeRoleFromUser() throws Exception {
        UserRoleRef userRoleRef = new UserRoleRef();
        userRoleRef.setRefUserId("0000018c-5caf-cba7-8b25-8d2a2844cdab");
        userRoleRef.setRefRoleId((short) 101);
        // 执行撤销并验证授权结果
        if (roleMapperImpl.revokeRoleFromUser(userRoleRef)) {
            System.out.println("撤销角色给用户成功！");
        } else {
            System.out.println("撤销角色给用户失败！");
        }
    }


    //分配可操作权限给角色
    @Test
    public void grantDoPowerToRole() throws Exception {
        RolePowerRef rolePowerRef = new RolePowerRef();
        rolePowerRef.setRefPowerId(3);
        rolePowerRef.setRefRoleId((short) 103);
        // 执行授权并验证授权结果
        if (roleMapperImpl.grantDoPowerToRole(rolePowerRef)) {
            System.out.println("分配可操作权限给角色成功！");
        } else {
            System.out.println("分配可操作权限给角色失败！");
        }
    }

    //分配可查看权限给角色
    @Test
    public void grantSeePowerToRole() throws Exception {
        RolePowerRef rolePowerRef = new RolePowerRef();
        rolePowerRef.setRefPowerId(17);
        rolePowerRef.setRefRoleId((short) 103);
        // 执行授权并验证授权结果
        if (roleMapperImpl.grantSeePowerToRole(rolePowerRef)) {
            System.out.println("分配可查看作权限给角色成功！");
        } else {
            System.out.println("分配查看权限给角色失败！");
        }
    }

    //撤销已分配权限给角色
    @Test
    public void revokePowerFromRole() throws Exception {
        RolePowerRef rolePowerRef = new RolePowerRef();
        rolePowerRef.setRefPowerId(17);
        rolePowerRef.setRefRoleId((short) 103);
        // 执行撤销并验证授权结果
        if (roleMapperImpl.revokePowerFromRole(rolePowerRef)) {
            System.out.println("撤销权限给角色成功！");
        } else {
            System.out.println("撤销权限给角色失败！");
        }
    }



    //查询角色状态
    @Test
    public void checkRoleOperate() throws Exception {
        Role role = new Role();
        role.setRoleId((short) 105);
        if(roleMapperImpl.checkRoleOperate(role)) {
            System.out.println(role.getRoleId() + "状态是正常使用");
        }else { System.out.println(role.getRoleId() + "状态是已停用");

        }

    }

}




    /*












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
*/
