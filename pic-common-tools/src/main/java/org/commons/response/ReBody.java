package org.commons.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl17
 * @Package : org.example.response
 * @Create on : 2023/11/11 20:55
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("响应结果")
public class ReBody {

    @ApiModelProperty("响应码")
    private int code;

    @ApiModelProperty("信息")
    private String msg;

    @ApiModelProperty("响应数据")
    private Object data;

    public ReBody(RepCode rCode) {
        this.code = rCode.getCode();
        this.msg = rCode.getMsg();
    }

    public ReBody(RepCode rCode, Object data) {
        this.code = rCode.getCode();
        this.msg = rCode.getMsg();
        this.data = data;
    }

    public void setRepCode(RepCode rCode) {
        this.code = rCode.getCode();
    }
}
