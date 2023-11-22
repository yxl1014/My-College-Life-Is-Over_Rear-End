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
 * @description:
 * @author: HammerRay
 * @date: 2023/11/19 下午12:45
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_verify_code")
public class VerifyCode {
    @Id
    @JsonProperty("vc_id")
    private String vcId;
    @JsonProperty("vc_create_time")
    private Timestamp vcCreateTime;
    @JsonProperty("vc_operation_type")
    private short vcOperationType;

    @JsonProperty("vc_user_name")
    private String vcUserName;
    @JsonProperty("vc_password")
    private String vcPassword;
    @JsonProperty("vc_picture_code")
    private String vcPictureCode;

    @JsonProperty("vc_telephone")
    private String vcTelephone;
    @JsonProperty("vc_telephone_code")
    private String vcTelephoneCode;

    @JsonProperty("vc_email")
    private String vcEmail;
    @JsonProperty("vc_email_code")
    private String vcEmailCode;

}
