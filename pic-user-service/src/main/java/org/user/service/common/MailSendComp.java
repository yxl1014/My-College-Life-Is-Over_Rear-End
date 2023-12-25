package org.user.service.common;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author yxl17
 * @Package : org.user.service.common
 * @Create on : 2023/12/25 22:41
 **/

@Component
public class MailSendComp {


    private final Session mailSession;

    public MailSendComp(Session mailSession) {
        this.mailSession = mailSession;
    }

    public void sendEmailVerityCode(String sendMail,String code) throws Exception {

        MimeMessage message = new MimeMessage(mailSession);

        message.setSubject("我们是LoadRunnerX平台，请接收您的邮箱登录验证码,验证码有效时间为60s:");
        message.setText("这是您的邮箱登录验证码: "+ code +"\n时间："+new Date());

        message.setFrom(new InternetAddress("2624773733@qq.com","LoadRunnerX"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(sendMail));

        Transport.send(message);
    }
}
