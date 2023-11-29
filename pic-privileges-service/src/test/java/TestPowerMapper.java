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

@SpringBootTest(classes= PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestPowerMapper {

    @Autowired
    private PowerMapperImpl powerMapperImpl;

    @Test
    public void insertRole() throws ParseException {
        Power power1=new Power();
        power1.setPowerId(1029);
        power1.setPowerName("日志管理");
        power1.setPowerType("1");
        power1.setPowerDate(LocalDateTime.now());
        power1.setPowerNote("可访问");
        powerMapperImpl.insertPower(power1);
        System.out.println("权限创建成功！");
    }

    @Test
    public void deletePower() {
        Power power=new Power();
        power.setPowerId(1029);
        Power power1 = powerMapperImpl.selectOnePower(power);
        if (power1 != null) {
            powerMapperImpl.deletePower(power1);
            System.out.println("权限删除成功！");
        } else {
            // 处理角色不存在的情况
            System.out.println("权限不存在！");
        }
    }

    @Test
    public void updatePower(){
        Power power = new Power();
        power.setPowerId( 1028 );
        Power power1 = powerMapperImpl.selectOnePower( power );
        power1.setPowerNote( "可访问" );
        powerMapperImpl.updatePower( power1 );
        System.out.println( "权限状态已更新！");

    }

    @Test
    public void selectPower(){
        Power power = new Power();
        power.setPowerName("日志管理");
        Power power1=powerMapperImpl.selectOnePower(power);
        if(power1 != null){
            System.out.println( "权限信息查询成功");
            System.out.println(power1);
        }else{
            System.out.println( "权限不存在！" );
        }
        System.out.println("############################################################");
        System.out.println("以下是查询所有的权限得到信息:");
        List<Power> powerList = powerMapperImpl.selectAllPower();
        for (Power p:powerList) {
            System.out.println(p);
        }
    }
}


