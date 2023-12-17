package org.mysql.domain;

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
public class MailInner {
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
