package org.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yxl17
 * @Package : org.user.config
 * @Create on : 2023/12/25 23:51
 **/
@Configuration
public class EmailConfig {

    @Value("${email_username_zy}")
    private String emailUserNameZy;

    @Value("${email_password_zy}")
    private String emailPasswordZy;

    @Value("${smtp_hostname_qq}")
    private String smtpHostNameQq;

    @Value("${smtp_port_qq}")
    private String smtpPortQq;

    @Bean(name = "mailSession")
    public Session emailSessionGenerator() throws IOException {

        //	账号信息
        //	邮箱发送账号
        String username = emailUserNameZy;
        //	邮箱授权码
        String password = emailPasswordZy;

        //	创建一个配置文件，并保存
        Properties props = new Properties();

        //	SMTP主机名
        props.put("mail.smtp.host", smtpHostNameQq);

        //	主机端口号
        props.put("mail.smtp.port", smtpPortQq);
        //	是否需要用户认证
        props.put("mail.smtp.auth", "true");
        //	启用TlS加密
        props.put("mail.smtp.starttls.enable", "true");

        //	创建session会话
        //	参数1:smtp服务器连接参数
        //	参数2:账号和密码的授权认证对象
        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        return session;
    }
}
