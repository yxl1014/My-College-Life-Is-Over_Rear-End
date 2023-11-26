package controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午7:45
 */
@RestController
public class FindPasswdController {
    @PostMapping("/findPasswd")
    @ApiOperation("找回密码：密保验证  手机验证  邮箱验证  //前端小伙伴 json_body中加入一个属性标识吧 比如 1：密保验证")
    public ModelAndView findPasswd(Object o){

        return new ModelAndView();
    }
}
