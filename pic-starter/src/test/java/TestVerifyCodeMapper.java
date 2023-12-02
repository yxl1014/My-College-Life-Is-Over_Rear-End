import example.StarterApplication;
import example.entity.database.VerifyCode;
import example.mapper.VerifyCodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

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
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        verifyCode.setVcTelephoneCode("555544");
        verifyCode.setVcTelephone("16666666666");
        verifyCode.setVcOperationType((short) 1);
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));
        verifyCodeMapper.insert(verifyCode);
    }

    @Test
    public void delete(){

    }

    @Test
    public void selectOne(){
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        verifyCode.setVcTelephoneCode("555544");
        verifyCode.setVcTelephone("16666666666");
        verifyCode.setVcOperationType((short) 1);
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));
        verifyCodeMapper.insert(verifyCode);
        System.out.println(verifyCodeMapper.selectOne(verifyCode));

    }

    @Test
    public void selectAll(){

        for (VerifyCode v:
                verifyCodeMapper.selectAll()) {
            System.out.println(v);
        }
    }
}
