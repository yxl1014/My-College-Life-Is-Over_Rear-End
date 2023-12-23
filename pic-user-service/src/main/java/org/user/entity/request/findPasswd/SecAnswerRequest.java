package org.user.entity.request.findPasswd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 密保答案请求
 * @author: HammerRay
 * @date: 2023/12/3 下午9:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("密保答案请求")
public class SecAnswerRequest {

    @JsonProperty("string")
    @ApiModelProperty(value ="用户名/电话号码/邮箱")
    private String string;

    @JsonProperty("userSecAnswer1")
    @ApiModelProperty(value = "密保答案1")
    private String userSecAnswer1;

    @JsonProperty("userSecAnswer2")
    @ApiModelProperty(value = "密保答案2")
    private String userSecAnswer2;

    @JsonProperty("userSecAnswer3")
    @ApiModelProperty(value = "密保答案3")
    private String userSecAnswer3;

}
