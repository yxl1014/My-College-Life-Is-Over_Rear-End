package example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.entity.database.User;
import example.entity.request.RegisterRequest;
import example.entity.response.UuidResponse;
import example.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
@Api(tags =  "注册接口")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    @ApiOperation("注册 不返回注册好的账户的uuid")
    @ApiResponse(code = 200,message = "注册成功",response = UuidResponse.class)
    public UuidResponse register(@RequestBody RegisterRequest request) throws IOException {


        return registerService.register(request);
    }

    @PostMapping("/register_return_uuid")
    @ApiOperation("注册 返回注册好的账户的uuid，做注册后即自动登录")
    @ApiResponse(code = 200,message = "注册成功",response = UuidResponse.class)
    public UuidResponse registerReturnUuid(@RequestBody RegisterRequest request, HttpServletResponse response) throws IOException {
        Map<String,String > dataMap = new HashMap<>();
        dataMap.put("success","注册成功");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(dataMap);
        response.getWriter().write(json);


        return new UuidResponse();
    }

}
