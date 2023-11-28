import org.example.PrivilegeApplication;
import org.example.model.dao.RoleMapper;
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
@SpringBootTest(classes= PrivilegeApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class RoleServiceTest {

    @Autowired
    private RoleMapperImpl roleMapperImpl;
    @Test
    public void testGrantPowerToRole() {
        // 模拟角色ID和权限ID
        Integer roleId = 101;
        Integer powerId = 1002;
        // 执行授权操作
        roleMapperImpl.grantPowerToRole(roleId,powerId);

        // 验证授权结果
        boolean isGranted = roleMapperImpl.isPowerGrantedToRole(roleId, powerId);
        Assertions.assertEquals(true,isGranted, "权限授权失败");

    }

}
