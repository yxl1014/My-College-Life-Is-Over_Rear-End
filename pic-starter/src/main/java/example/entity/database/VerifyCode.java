package example.entity.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/19 下午12:45
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_verify_code")
@ApiModel(description = "验证码实体")
public class VerifyCode {
    @Id
    @JsonProperty("vc_id")
    @ApiModelProperty(value = "验证码的uuid")
    private String vcId;
    @JsonProperty("vc_create_time")
    @ApiModelProperty(value = "验证码的生成时间")
    private Timestamp vcCreateTime;
    @JsonProperty("vc_operation_type")
    @ApiModelProperty(value = "验证码的对应的操作类型0000 0001表示登录 0000 0010表示注册")
    private short vcOperationType;

    @JsonProperty("vc_user_name")
    @ApiModelProperty(value = "对应用户的用户名")
    private String vcUserName;
    @JsonProperty("vc_password")
    @ApiModelProperty(value = "对应用户的密码")
    private String vcPassword;
    @JsonProperty("vc_picture_code")
    @ApiModelProperty(value = "图形验证码")
    private String vcPictureCode;

    @JsonProperty("vc_telephone")
    @ApiModelProperty(value = "对应的手机号")
    private String vcTelephone;
    @JsonProperty("vc_telephone_code")
    @ApiModelProperty(value = "手机短信验证码")
    private String vcTelephoneCode;

    @JsonProperty("vc_email")
    @ApiModelProperty(value = "邮箱地址")
    private String vcEmail;
    @JsonProperty("vc_email_code")
    @ApiModelProperty(value = "邮箱验证码")
    private String vcEmailCode;

}
