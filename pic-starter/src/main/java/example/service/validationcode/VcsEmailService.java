package example.service.validationcode;


import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.database.VerifyCode;
import example.mapper.VerifyCodeMapper;
import example.entity.response.StringCodeResponse;
import example.tools.VerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

/**
 * 邮箱验证码 还需要配置 发送方的 账号 邮箱授权码   使用的哪个邮箱，端口号
 * @description:
 * @author: HammerRay
 * @date: 2023/11/17 下午11:37
 */

@Service
public class VcsEmailService {
    @Autowired
    VerifyCodeMapper verifyCodeMapper;
    @Autowired
    ValidationCacheService validationCacheService;

    public StringCodeResponse codeSend(String recipientAddr) throws MessagingException, IOException {
        if (recipientAddr.startsWith("\"") && recipientAddr.endsWith("\"")) {
            recipientAddr = recipientAddr.substring(1, recipientAddr.length() - 1);
        }
        
        if(!RegexValidator.regexEmail(recipientAddr)){
            throw new RuntimeException("目标邮箱格式错误");
        }

        Session session = emailSessionGenerator();
        StringCodeResponse code = VerifyCodeGenerator.pureDigitCode();

        validationCacheService.cacheCode(code.getVcId(),code.getValidation());

        codeInsert(code,recipientAddr, (short) 1);


        MimeMessage message = new MimeMessage(session);

        message.setSubject("我们是LoadRunnerX平台，请接收您的邮箱登录验证码,验证码有效时间为60s:");
        message.setText("这是您的邮箱登录验证码: "+ code.getValidation() +"\n时间："+new Date());

        message.setFrom(new InternetAddress("2624773733@qq.com","LoadRunnerX"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipientAddr));

        Transport.send(message);

        User user = new User();
        user.setUserSysEmail(recipientAddr);

        code.setCode(200);
        code.setMsg("发送成功！验证码有效时长为60s");

        return code;
    }

    private int codeInsert(StringCodeResponse stringCodeResponse, String emailAddr, short flag){
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(stringCodeResponse.getVcId());
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));
        verifyCode.setVcOperationType(flag);
        verifyCode.setVcEmailCode(stringCodeResponse.getValidation());
        verifyCode.setVcEmail(emailAddr);
        return verifyCodeMapper.insert(verifyCode);
    }

    private Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = VcsEmailService.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Sorry, unable to find " + fileName);
            }
            properties.load(input);
        }
        return properties;
    }

    private Session emailSessionGenerator() throws IOException {
        Properties properties = loadProperties("email_config.properties");;

        //	账号信息
        //	邮箱发送账号
        String username = properties.getProperty("email_username_zy");
        //	邮箱授权码
        String password = properties.getProperty("email_password_zy");

        //	创建一个配置文件，并保存
        Properties props = new Properties();

        //	SMTP主机名
        props.put("mail.smtp.host", properties.getProperty("smtp_hostname_qq"));

        //	主机端口号
        props.put("mail.smtp.port", properties.getProperty("smtp_port_qq"));
        //	是否需要用户认证
        props.put("mail.smtp.auth", "true");
        //	启用TlS加密
        props.put("mail.smtp.starttls.enable", "true");

        //	创建session会话
        //	参数1:smtp服务器连接参数
        //	参数2:账号和密码的授权认证对象
        Session session =Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        session.setDebug(true);

        return session;
    }
}
