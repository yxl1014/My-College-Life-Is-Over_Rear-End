package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.user.entity.response.father.Response;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午9:14
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("UUID响应")
public class UuidResponse extends Response {

    @ApiModelProperty("UUID")
    @JsonProperty("uuid")
    private String uuid;

    public UuidResponse (int code,String msg,String uuid){
        this.setMsg(msg);
        this.setCode(code);
        this.uuid=uuid;

    }
}
