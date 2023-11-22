package example.mapper;

import org.apache.ibatis.annotations.Mapper;
import example.entity.database.VerifyCode;

import java.util.List;


/**
 * 验证码的Mapper层，用spring-mybatis根据.xml文件自动生成对象
 * @author: GodHammer
 * @date: 2023-11-19 下午1:57
 */
@Mapper
public interface VerifyCodeMapper {
    /**
     * description: 插入一条验证码记录
     * @paramType: [org.example.entity.database.VerifyCode]
     * @param verifyCode:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-11-19 下午1:59
     */
    int insert(VerifyCode verifyCode);
    /**
     * description: 删除一个账户的所有验证码记录
     * @paramType [org.example.entity.database.VerifyCode]
     * @param verifyCode:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-11-19 下午2:46
     */
    int delete(VerifyCode verifyCode);

    /**
     * description: 验证码无需更改，只有删除和插入。
     * @paramType [org.example.entity.database.VerifyCode]
     * @param verifyCode:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-11-19 下午2:54
     */
    int update(VerifyCode verifyCode);
    /**
     * description: 查询一个账户的所有验证码记录， java代码中选取匹配最新的验证码来检验用户是否输入正确
     * @paramType [org.example.entity.database.VerifyCode]
     * @param verifyCode:
     * @returnType: java.util.List<org.example.entity.database.VerifyCode>
     * @author: GodHammer
     * @date: 2023-11-19 下午2:55
     */
    List<VerifyCode> selectOne(VerifyCode verifyCode);
    /**
     * description: 查询数据库中所有的验证码记录
     * @paramType []
     * @returnType: java.util.List<org.example.entity.database.VerifyCode>
     * @author: GodHammer
     * @date: 2023-11-19 下午2:56
     */
    List<VerifyCode> selectAll();

}
