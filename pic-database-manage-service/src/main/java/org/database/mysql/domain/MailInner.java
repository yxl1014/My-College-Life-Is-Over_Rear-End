package org.database.mysql.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_mail_inner")
public class MailInner {
    @TableId
    private String mailId;
    private String mailSenderId;
    private String mailReceiverId;
    private String mailSubject;
    private String mailMessage;
    private String mailAttachment;
    private String mailSendDate;
    private Short mailType;
    private Short mailStatus;
}
