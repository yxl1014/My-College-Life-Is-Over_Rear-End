package org.mysql.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:13
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_transaction_record")
public class TransactionRecord {
    @TableId
    private String transId;
    private String transSenderId;
    private String transReceiverId;
    private Double transChangedMoney;
    private Timestamp transCreateTime;
    private Double transRestMoney;
    private Short transPayWay;
    private String transType;
    private Short transState;
    private String transNotes;
}
