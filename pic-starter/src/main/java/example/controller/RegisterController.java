package example.controller;

import example.entity.database.User;
import example.service.RegisterService;
import example.tools.RegexValidator;
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
 * @date: 2023/11/23 下午9:11
 */
@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    @ApiOperation("注册")
    @ResponseBody
    public ModelAndView register(@RequestBody Object object)  {
        ModelAndView modelAndView = new ModelAndView();
        if(object instanceof User){

            User user = (User)object;
            if(!RegexValidator.regexEmail(user.getUserSysEmail())){
                modelAndView.addObject("errorMsg","errorMsg: 邮箱格式错误,正确：" +RegexValidator.EMAIL_FM);
                modelAndView.setViewName("/401");
                return modelAndView;
            }
            if(!RegexValidator.regexTelephone(user.getUserTelephone())){
                modelAndView.addObject("errorMsg","errorMsg: 手机号码格式错误,正确："+RegexValidator.TELEPHONE_FM);
                modelAndView.setViewName("/401");
                return modelAndView;
            }
            if(!RegexValidator.regexUsername(user.getUserName()) && (!RegexValidator.regexUsernameChinese(user.getUserName()))){
                modelAndView.addObject("errorMsg","errorMsg: 用户名格式错误,正确："+RegexValidator.USERNAME_FM);
                modelAndView.setViewName("/401");
                return modelAndView;
            }

            if(RegexValidator.isMediumPasswd(user.getUserPassword())){
                RegexValidator.isStrongPasswd(user.getUserPassword());
            }else {
                if(RegexValidator.isLowPasswd(user.getUserPassword())){

                }else {
                    modelAndView.addObject("errorMsg","errorMsg: 注册密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                    modelAndView.setViewName("/401");
                    return modelAndView;
                }
            }


            User user1 = registerService.register(user);

            modelAndView.addObject("user",user1);
            modelAndView.setViewName("/login_html");
            return modelAndView;
        }
        modelAndView.addObject("errorMsg","errorMsg: 找不到User类");
        modelAndView.setViewName("/404");
        return modelAndView;
    }
}
