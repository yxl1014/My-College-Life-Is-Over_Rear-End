package org.user.entity.request.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 充值请求
 * @author: HammerRay
 * @date: 2023/12/2 下午10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "充值请求")
public class RechargeRequest {

    @ApiModelProperty(value = "打款人uuid")
    @JsonProperty("senderId")
    private String senderId;
    @ApiModelProperty(value = "收款人uuid")
    @JsonProperty("receiverId")
    private String receiverId;
    @JsonProperty("homMuch")
    @ApiModelProperty(value = "充值多少钱")
    private double howMuch;
    @JsonProperty("transNotes")
    @ApiModelProperty(value = "交易备注")
    private String transNotes;
}
