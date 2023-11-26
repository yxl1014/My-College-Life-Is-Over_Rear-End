package controller;

import example.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    /**
     * $$考虑添加LoginRequest实体类，来对应前端传来的jsonBody中的各个属性  同理RegisterRequest
     * @paramType: [java.lang.Object]
     * @param object:
     * @returnType: org.springframework.web.servlet.ModelAndView
     * @author: GodHammer
     * @date: 2023-11-14 下午10:07
     * @version: v1.0
     */
    @PostMapping("/login")
    @ApiOperation("登录 通过各种方式的登录，成功后返回Token UUID 这个UUID存储于redis中")
    @ResponseBody
    public ModelAndView login(@RequestBody Object object) {

        return new ModelAndView();
    }

    @PostMapping("/logout")
    @ApiOperation("登出 redis中删除相关UUID")
    @ResponseBody
    public ModelAndView logout(@RequestBody Object object){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("info","登出成功,返回登录页面");
        modelAndView.setViewName("/loginhtml");
        return modelAndView;
    }
}
