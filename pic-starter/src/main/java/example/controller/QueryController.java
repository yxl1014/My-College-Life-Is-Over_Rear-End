package example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.entity.database.User;
import example.entity.response.SecProblemResponse;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import example.service.UserInfoQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午8:54
 */
@RestController
@Api(tags = "查询信息接口")
public class QueryController {
    @Autowired
    UserInfoQueryService queryService;

    @PostMapping("/passwdSecQuery")
    @ApiOperation("查询密保")
    @ApiResponse(code = 200, message = "请求成功", response = SecProblemResponse.class)
    public SecProblemResponse passwdSecQuery(@RequestBody String uuid)  {

        return queryService.passwdSecQuery(uuid);

    }


}
