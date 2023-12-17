package org.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.mysql.test.domain
 * @Create on : 2023/12/17 13:02
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private String annoId;
    private String annoPublisherId;
    private Timestamp annoCreateTime;
    private Timestamp annoPublishTime;
    private Timestamp annoExpireTime;
    private String annoTitle;
    private String annoContentBody;
    private Short annoPriority;
    private String annoLink;
}
