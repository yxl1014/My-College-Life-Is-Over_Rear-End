package org.example.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.MathVerityBody;

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
    private R_Code rCode;

    @ApiModelProperty("响应数据")
    private Object data;

    public ReBody(R_Code rCode) {
        this.rCode = rCode;
    }
}
