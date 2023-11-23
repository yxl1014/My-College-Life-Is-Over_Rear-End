package example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/7 下午5:35
 */
@Controller
public class ErrorController {
    @GetMapping("/401")
    @ApiOperation("未授权错误")
    public ModelAndView state_401(Object o){

        return new ModelAndView();
    }

    @GetMapping("/404")
    @ApiOperation("找不到服务器资源")
    public ModelAndView state_404(Object o){

        return new ModelAndView();
    }

   @GetMapping("/500")
   @ApiOperation("服务器内部错误")
   public ModelAndView state_500(Object o){

        return new ModelAndView();
   }
   @GetMapping("/503")
   @ApiOperation("服务器不可用")
    public ModelAndView state_503(Object o){

        return new ModelAndView();
   }
}
