import example.StarterApplication;
import example.mapper.VerifyCodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/19 下午4:11
 */

@SpringBootTest(classes= StarterApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class TestVerifyCodeMapper {
    @Autowired
    VerifyCodeMapper verifyCodeMapper;

    @Test
    public void insert(){

    }

    @Test
    public void delete(){

    }

    @Test
    public void selectOne(){

    }

    @Test
    public void selectAll(){

    }
}
