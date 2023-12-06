import common.uuid.UuidGenerator;
import example.StarterApplication;
import example.service.validationcode.ValidationCacheService;
import example.service.validationcode.VcsPictureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/6 下午3:08
 */
@SpringBootTest(classes= StarterApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class CacheCodeTest {
    @Autowired
    ValidationCacheService validationCacheService;

    @Test
    public void testWriteCache(){
        String vcId = String.valueOf(UuidGenerator.getCustomUuid(System.currentTimeMillis()));
        String code = "452363";
        validationCacheService.cacheCode(vcId,code);
    }

    @Test
    public void testReadCache(){
        String vcId = "0000018c-3ec9-451b-96de-68c2d7bb19e4";
        String code = "452363";

        System.out.println(validationCacheService.codeCheck(vcId,code));
    }
}
