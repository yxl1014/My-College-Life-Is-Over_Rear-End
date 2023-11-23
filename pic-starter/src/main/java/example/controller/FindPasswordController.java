package example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/23 下午8:56
 */
@RestController
public class FindPasswordController {
    @PostMapping("/findPasswd")
    @ApiOperation("找回密码")
    public ModelAndView findPasswd(Object o){

        return new ModelAndView();
    }
}
