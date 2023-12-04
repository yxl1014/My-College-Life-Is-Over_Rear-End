import org.example.PrivilegeApplication;
import org.example.model.entity.Power;
import org.example.service.impl.PowerMapperImpl;
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
    public void insertRole() throws ParseException {
        Power power1 = new Power();
        power1.setPowerId(1031);
        power1.setPowerName("邮件系统");
        power1.setPowerType(1);
        power1.setPowerDate(LocalDateTime.now());
        power1.setPowerNote("可访问");
        powerMapperImpl.insertPower(power1);
        System.out.println("权限创建成功！");
    }

    //删除权限
    @Test
    public void deletePower() {
        Power power = new Power();
        power.setPowerId(1031);
        Power power1 = powerMapperImpl.selectOnePower(power);
        if (power1 != null) {
            powerMapperImpl.deletePower(power1);
            System.out.println("权限删除成功！");
        } else {
            // 处理角色不存在的情况
            System.out.println("权限不存在！");
        }
    }

    //修改权限内容
    @Test
    public void updatePower() {
        Power power = new Power();
        power.setPowerId(1028);
        Power power1 = powerMapperImpl.selectOnePower(power);
        power1.setPowerNote("可访问");
        powerMapperImpl.updatePower(power1);
        System.out.println("权限状态已更新！");

    }

    @Test
    public void selectPower() {
        Power power = new Power();
        power.setPowerId(1005);
        Power power1 = powerMapperImpl.selectOnePower(power);
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
        Integer roleId = 101;
        // 执行授权操作
        List<Power> powers = powerMapperImpl.getRolePowers(roleId);
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




}


