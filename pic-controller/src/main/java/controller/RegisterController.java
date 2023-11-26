package controller;

import example.service.RegisterService;
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
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    @ApiOperation("注册")
    @ResponseBody
    public ModelAndView register(@RequestBody Object object) {
        return new ModelAndView();
    }

}
