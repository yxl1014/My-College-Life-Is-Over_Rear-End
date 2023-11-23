package example.service.validation_code;


import example.tools.RegexValidator;
import example.tools.VerifyCodeGenerator;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
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

    public static String codeSend(String recipientAdd) throws MessagingException {
        if(!RegexValidator.regexEmail(recipientAdd)){
            throw new RuntimeException("目标邮箱格式错误");
        }

        Session session = emailSessionGenerator();
        String code = VerifyCodeGenerator.pureDigitCode();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("我们是LoadRunnerX平台，请接收您的邮箱登录验证码:");
            message.setText("这是您的邮箱登录验证码"+ code +"时间："+new Date());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipientAdd));


        return code;
    }


    private static Session emailSessionGenerator()  {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("email_config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (IOException ioException){
            ioException.getStackTrace();
        }

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
