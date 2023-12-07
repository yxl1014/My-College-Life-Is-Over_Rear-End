import org.example.PrivilegeApplication;
import org.example.model.dao.UserMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.service.impl.PowerMapperImpl;
import org.example.service.impl.RoleMapperImpl;
import org.example.service.impl.UserMapperImpl;
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

/**
 * @Author: eensh
 * @CreateDate: 2023/11/28
 */

@SpringBootTest(classes = PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestPowerMapper {

    @Autowired
    private PowerMapperImpl powerMapperImpl;


    //增加权限
    @Test
    public void insertRole() {
        Power power = new Power();
        power.setPowerId(1033);
        power.setPowerName("权限管理");
        power.setPowerDate(LocalDateTime.now());
        powerMapperImpl.insertPower(power);
        System.out.println("权限创建成功！");
    }

    //删除权限
    @Test
    public void deletePower() {
            powerMapperImpl.deletePower(1031);
            System.out.println("权限删除成功！");

    }

    //修改权限内容
    @Test
    public void updatePower() {
        Power power1 = powerMapperImpl.selectOnePower(1027);
        power1.setPowerNote("可操作");
        powerMapperImpl.updatePower(power1);
        System.out.println("权限状态已更新！");

    }

    @Test
    public void selectPower() {

        Power power1 = powerMapperImpl.selectOnePower(1005);
        if (power1 != null) {
            System.out.println("权限信息查询成功");
            System.out.println(power1);
        } else {
            System.out.println("权限不存在！");
        }
        System.out.println("############################################################");
        System.out.println("以下是查询所有的权限得到信息:");
        List<Power> powerList = powerMapperImpl.selectAllPower();
        for (Power p : powerList) {
            System.out.println(p);
        }
    }

    //查询角色对应的权限列表
    @Test
    public void getRolePowers() {
        // 执行授权操作
        List<Power> powers = powerMapperImpl.getRolePowers(101);
        if (powers != null) {
            System.out.println("角色所属权限查询成功");
        } else {
            System.out.println("此角色无权限");
        }
        System.out.println("############################################################");
        System.out.println("以下是查询到的角色所属权限列表:");

        assert powers != null;
        for (Power p : powers) {
            System.out.println(p);
        }

    }

    //查询权限状态（1为可操作,2为可访问）
    @Test
    public void isStatusToPower() {
        Integer powerId = 1001;
        Power power1 = powerMapperImpl.selectOnePower(powerId);
        String powerName = power1.getPowerName();
        boolean isStatusToPower = powerMapperImpl.isStatusToPower(powerId);
        if (isStatusToPower) {
            System.out.println(powerId + powerName + "是可操作的！");
        } else {
            System.out.println(powerId + powerName + "是可访问的！");
        }

    }

}


