package example.entity.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @description: 交易单号实体
 * @author: HammerRay
 * @date: 2023/12/2 下午11:12
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_transaction_record")
public class Transaction {
    @Id
    @JsonProperty("transId")
    private String transId;

    @JsonProperty("SenderId")
    private String transSenderId;

    @JsonProperty("transReceiverId")
    private String transReceiverId;

    @JsonProperty("transHowMuch")
    private double transHowMuch;

    @JsonProperty("transTime")
    private Timestamp transTime;

    @JsonProperty("restMoney")
    private double transRestMoney;

    @JsonProperty("transPayWay")
    private String transPayWay;

    @JsonProperty("transType")
    private String transType;

    @JsonProperty("transState")
    private String transState;

    @JsonProperty("transNote")
    private String transNote;
}
