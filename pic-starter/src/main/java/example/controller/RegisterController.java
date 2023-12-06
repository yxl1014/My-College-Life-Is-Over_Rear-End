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
import java.text.ParseException;
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
    @ApiOperation("注册 返回注册好的账户的uuid，可做注册后自动登录")
    @ApiResponse(code = 200,message = "注册成功",response = UuidResponse.class)
    public UuidResponse register(@RequestBody RegisterRequest request) throws  ParseException {


        return registerService.register(request);
    }

}
