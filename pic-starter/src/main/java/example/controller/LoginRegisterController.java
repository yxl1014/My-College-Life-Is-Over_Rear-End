package example.controller;

import example.entity.database.User;
import example.service.Login;
import example.service.Register;
import example.tools.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录注册的Controller接口
 * @author: HammerRay
 * @date: 2023/11/4 下午11:20
 */
@RestController
public class LoginRegisterController {
    @Autowired
    Login login ;
    @Autowired
    Register register;

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
                    modelAndView.addObject("errorMsg","errorMsg: 密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                    modelAndView.setViewName("/login500");
                    return modelAndView;
                }
            }

            Object o = login.login(user);

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
            modelAndView.setViewName("/login500");
            return modelAndView;
        }

    @PostMapping("/register")
    @ResponseBody
    public ModelAndView register(@RequestBody Object object)  {
        ModelAndView modelAndView = new ModelAndView();
        if(object instanceof User){

            User user = (User)object;
            if(!RegexValidator.regexEmail(user.getUserSysEmail())){
                modelAndView.addObject("errorMsg","errorMsg: 邮箱格式错误,正确：" +RegexValidator.EMAIL_FM);
                modelAndView.setViewName("/register500");
                return modelAndView;
            }
            if(!RegexValidator.regexTelephone(user.getUserTelephone())){
                modelAndView.addObject("errorMsg","errorMsg: 手机号码格式错误,正确："+RegexValidator.TELEPHONE_FM);
                modelAndView.setViewName("/register500");
                return modelAndView;
            }
            if(!RegexValidator.regexUsername(user.getUserName()) && (!RegexValidator.regexUsernameChinese(user.getUserName()))){
                modelAndView.addObject("errorMsg","errorMsg: 用户名格式错误,正确："+RegexValidator.USERNAME_FM);
                modelAndView.setViewName("/register500");
                return modelAndView;
            }

            if(RegexValidator.isMediumPasswd(user.getUserPassword())){
                RegexValidator.isStrongPasswd(user.getUserPassword());
            }else {
                if(RegexValidator.isLowPasswd(user.getUserPassword())){

                }else {
                    modelAndView.addObject("errorMsg","errorMsg: 密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                    modelAndView.setViewName("/register500");
                    return modelAndView;
                }
            }


            User user1 = register.register(user);

            modelAndView.addObject("user",user1);
            modelAndView.setViewName("/login_html");
            return modelAndView;
        }
            modelAndView.addObject("errorMsg","errorMsg: 找不到User类");
            modelAndView.setViewName("/register500");
            return modelAndView;
    }

    @GetMapping("get_validation_code")
    public String validator (){
            return "4gi6";
    }
}


