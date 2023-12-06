package example.entity.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.entity.response.father.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @description: 充值响应
 * @author: HammerRay
 * @date: 2023/12/2 下午11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("充值响应")
public class RechargeResponse{
    @ApiModelProperty(value = "交易单号uuid")
    @JsonProperty("transId")
    private String transId;

    @ApiModelProperty(value = "打款人uuid")
    @JsonProperty("senderId")
    private String transSenderId;

    @ApiModelProperty(value = "收款人uuid")
    @JsonProperty("receiverId")
    private String transReceiverId;

    @ApiModelProperty(value = "充值金额")
    @JsonProperty("howMuch")
    private double transHowMuch;

    @ApiModelProperty(value = "现在余额")
    @JsonProperty("restMoney")
    private double transRestMoney;

    @ApiModelProperty(value = "交易时间")
    @JsonProperty("transTime")
    private Timestamp transTime;

    @ApiModelProperty(value = "交易方式")
    @JsonProperty("transPayWay")
    private String transPayWay;

    @ApiModelProperty(value = "交易类型")
    @JsonProperty("transType")
    private String transType;

    @ApiModelProperty(value = "交易状态")
    @JsonProperty("transState")
    private String transState;

    @ApiModelProperty(value = "交易备注")
    @JsonProperty("transNotes")
    private String transNotes;
}
