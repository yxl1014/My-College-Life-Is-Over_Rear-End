package example.entity.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRUDRequest {
    private  String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @JsonProperty("user_password")
    @ApiModelProperty(value = "密码")
    private String userPassword;

    @JsonProperty("user_id_card")
    @ApiModelProperty(value = "身份证卡")
    private String userIdCard;
}
