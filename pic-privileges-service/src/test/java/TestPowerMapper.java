import exception.FormatException;
import exception.PowerExceptions;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.RolePowerRef;
import org.database.redis.RedisComp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.privileges.PrivilegeApplication;
import org.privileges.service.impl.PowerMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
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

    @Autowired
    private RedisComp redisComp;


    //增加权限
    @Test
    public void insertPower() throws PowerExceptions.EmptyPowerException, PowerExceptions.PowerExistsException, FormatException, IllegalAccessException {
        Power power = new Power();
        power.setPowerId(1061);
        power.setPowerName("角色管理");
        power.setPowerType((short) 0);
        power.setPowerCreateTime(Timestamp.valueOf(LocalDateTime.now()));

        powerMapperImpl.insertPower(power);
        System.out.println("权限创建成功！");
    }

    //根据powerId查询
    @Test
    public void selectOnePower() throws FormatException, IllegalAccessException, PowerExceptions.PowerIsNullException, PowerExceptions.PowerNoExistsException {
        Power power = new Power();
        power.setPowerId(1051);
        powerMapperImpl.selectOnePower(power);
        System.out.println("权限查找成功！");

        System.out.println("############################################################");
        System.out.println("以下是查询所有的权限得到信息:");
        List<Power> powerList = powerMapperImpl.selectAllPower();
        for (Power p : powerList) {
            System.out.println(p);
        }
    }


    @Test
    public void testRedis(){
        redisComp.set("a","b");
        Assert.assertEquals(redisComp.get("a"),"b");
    }

    //根据powerId删除
    @Test
    public void deletePower() throws PowerExceptions.PowerIsNullException, FormatException, IllegalAccessException, PowerExceptions.PowerNoExistsException {
        Power power = new Power();
        power.setPowerId(1051);
        powerMapperImpl.deletePower(power);
        System.out.println("权限删除成功！");
    }


    //更新权限内容(根据powerId)
    @Test
    public void updatePower() throws PowerExceptions.PowerNoExistsException, PowerExceptions.EmptyPowerException, FormatException, IllegalAccessException, PowerExceptions.PowerIsNullException {
        Power power = new Power();
        power.setPowerId(3);
        power.setPowerNotes("可操作");
        power.setPowerType((short) 1);
        powerMapperImpl.updatePower(power);
        System.out.println("权限状态已更新！");

    }

    //查询角色对应的权限列表
    @Test
    public void getRolePowers() throws Exception {
        // 执行授权操作
        RolePowerRef rolePowerRef = new RolePowerRef();
        rolePowerRef.setRefRoleId((short) 101);
        List<RolePowerRef> powerList = powerMapperImpl.getRolePowers(rolePowerRef);

        if (powerList != null) {
            System.out.println("角色所属权限查询成功");
        } else {
            System.out.println("此角色无权限");
        }
        System.out.println("############################################################");
        System.out.println("以下是查询到的角色所属权限列表:");
        for (RolePowerRef p : powerList) {
            System.out.println(p);
        }
    }


}




/*




        //查询权限状态（1为可操作,2为可访问）
        @Test
        public void isStatusToPower() {
            Integer powerId = 1002;
            Power power1 = powerMapperImpl.selectOnePower(powerId);
            String powerName = power1.getPowerName();
            if (powerMapperImpl.isStatusToPower(powerId)) {
                System.out.println(powerId + powerName + "是可操作的！");
            } else {
                System.out.println(powerId + powerName + "是可访问的！");
            }

        }


    }


*/