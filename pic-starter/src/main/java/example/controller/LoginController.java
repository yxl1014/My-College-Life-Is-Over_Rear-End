package example.controller;

import example.entity.database.User;
import example.service.LoginService;
import example.tools.RegexValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录注册的Controller接口
 * @author: HammerRay
 * @date: 2023/11/4 下午11:20
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
    @ApiOperation("登录")
    @ResponseBody
    public ModelAndView login(@RequestBody Object object){

        ModelAndView modelAndView = new ModelAndView();
        if(object instanceof User) {
            User user = (User) object;
            if(RegexValidator.isMediumPasswd(user.getUserPassword())){
                RegexValidator.isStrongPasswd(user.getUserPassword());
            }else {
                if(RegexValidator.isLowPasswd(user.getUserPassword())){

                }else {
                    modelAndView.addObject("errorMsg","errorMsg: 登录密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                    modelAndView.setViewName("/401");
                    return modelAndView;
                }
            }

            Object o = loginService.login(user);

            if (o instanceof User) {
                //验证成功
                modelAndView.addObject("user", (User) o);
                modelAndView.setViewName("/main_page");
                return modelAndView;
            }
            //账号或密码不过关
            modelAndView.addObject("errorMsg",(String) o);
            modelAndView.setViewName("/login_html");
            return modelAndView;
        }

            //可以在此添加一些错误的提示信息   没有找到对应的User类
            modelAndView.addObject("errorMsg","errorMsg: 找不到User类");
            modelAndView.setViewName("/401");
            return modelAndView;
        }




    /**
     * description: 验证字符串的正确性通过比较每一个字符
     * @paramType [java.lang.String, java.lang.String]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean stringValidationVerify(String var1, String var2){
        return true;
    }
    /**
     * description: 验证数字运算的验证码的正确性，通过运算的结果
     * @paramType [java.lang.Integer, java.lang.Integer]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean integerValidationVerify(Integer var1,Integer var2){
        return true;
    }


}


