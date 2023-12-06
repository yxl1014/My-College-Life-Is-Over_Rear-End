package example.entity.request.findPasswd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/3 下午11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "找回密码实体类")
public class FindPasswdRequest {
    /**
     * 第一步检验用户是否存在的时候返回了这三者的信息
     */
    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("user_telephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("user_sys_email")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;

    @JsonProperty("newPasswd")
    @ApiModelProperty(value = "新密码")
    private String newPasswd;
    @JsonProperty("twiceNewPasswd")
    @ApiModelProperty(value = "二次输入新密码")
    private String twiceNewPasswd;
}
