package example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.service.validation_code.entity.StringCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午4:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "找回密码实体类")
public class FindPasswdRequest {

    @JsonProperty("flag")
    @ApiModelProperty(value = "flag 1.用户名密保验证  2.手机+密保 3.邮箱+密保 4.手机短信验证  5.邮箱短信验证")
    private short flag;
    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("user_sec_problem_1")
    @ApiModelProperty(value = "密保1")
    private String userSecProblem1;
    @JsonProperty("user_sec_answer_1")
    @ApiModelProperty(value = "密保答案1")
    private String userSecAnswer1;
    @JsonProperty("user_sec_problem_2")
    @ApiModelProperty(value = "密保2")
    private String userSecProblem2;
    @JsonProperty("user_sec_answer_2")
    @ApiModelProperty(value = "密保答案2")
    private String userSecAnswer2;
    @JsonProperty("user_sec_problem_3")
    @ApiModelProperty(value = "密保3")
    private String userSecProblem3;
    @JsonProperty("user_sec_answer_3")
    @ApiModelProperty(value = "密保答案3")
    private String userSecAnswer3;

    @JsonProperty("user_telephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("vc_telephone_code")
    @ApiModelProperty(value = "手机短信验证码")
    private StringCode vcTelephoneCode;

    @JsonProperty("user_sys_email")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;
    @JsonProperty("vc_email_code")
    @ApiModelProperty(value = "邮箱验证码")
    private StringCode vcEmailCode;


    @JsonProperty("user_new_passwd")
    @ApiModelProperty(value = "新密码")
    private String newPasswd;

}
