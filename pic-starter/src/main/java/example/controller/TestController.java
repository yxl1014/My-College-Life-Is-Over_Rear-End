package example.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import org.example.response.R_Code;
import org.example.response.ReBody;
/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/22 下午7:40
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/get")
    @ApiOperation("测试 hello world!")
    public String get(){return "hello world!";}

    @PostMapping("/post")
    @ApiOperation("测试 表单处理 Post方法")
    public String post(@ApiParam("用户的名字")@RequestParam(name = "username") String username){return "获取到表单数据"+username+"hello world!";}

    @GetMapping("/res_code")
    @ApiOperation("测试系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "1:ok 2:fail 3:error other:paramError", required = true, paramType = "form", dataType = "Integer")
    })
    public ReBody testContent(@RequestParam(name = "code") int code) {
        switch (code) {
            case 1:
                return new ReBody(R_Code.R_Ok);
            case 2:
                return new ReBody(R_Code.R_Fail);
            case 3:
                return new ReBody(R_Code.R_Error);
            default:
                return new ReBody(R_Code.R_ParamError);
        }
    }

}
