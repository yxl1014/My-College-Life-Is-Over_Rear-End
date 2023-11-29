/*
package example.controller.deprecated;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import response.R_Code;
import test.Add;


*/
/**
 *
 * @description: 一个测试各个如何使用的Controller 各位可以再次任意测试
 * @author: HammerRay
 * @date: 2023/11/22 下午7:40
 *//*

@RestController
@Api(tags = "Swagger注解学习", description = "测试接口：测试请求是否成功、测试各种处理方法、各种注解使用效果")
@RequestMapping("/test")
public class TestController<ReBody> {
    @GetMapping("/get")
    @ApiOperation("测试 返回hello world!")
    public String get(){
     //   System.out.println(Add.add());
        return "hello world!";
    }

    @PostMapping("/post")
    @ApiOperation("测试 表单处理 Post方法")
    public String post(@ApiParam("用户的名字")@RequestParam(name = "username") String username){return "获取到表单数据"+username+"hello world!";}

    @GetMapping("/res_code")
    @ApiOperation("测试系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "1:ok 2:fail 3:error other:paramError", required = true, paramType = "form", dataType = "Integer")
    })
    public ReBody testCondtent(@RequestParam(name = "code") int code) {
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
    @GetMapping("/testMv")
    @ResponseBody
    @ApiResponse(code = 200,message = "成功", response = ModelAndView.class)
    public ModelAndView testMv(){
        return new ModelAndView();
    }
}
*/
