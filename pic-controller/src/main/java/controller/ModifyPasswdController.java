package controller;

import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.ObjectExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午8:45
 */
@RestController
public class ModifyPasswdController {
    @PostMapping("/modify_passwd")
    @ApiOperation("通过用户名原密码、用户名密保方式、手机号验证码、邮箱验证码 四种方式找回  //json_body中加入一个属性，标识哪种方式")
    public ModelAndView modifyPasswd(@RequestBody Object o){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
