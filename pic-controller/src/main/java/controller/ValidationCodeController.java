package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 三种验证码的获取验证
 * @author: HammerRay
 * @date: 2023/11/26 上午7:46
 */
@RestController
@RequestMapping("valida_code")
public class ValidationCodeController {
    /**
     * description:
     * @paramType []
     * @returnType: java.lang.String
     * @author: GodHammer
     * @date: 2023-11-23 下午9:59
     */
    @GetMapping("get_picture")
    public String picture (){
        return "4gi6";
    }

    @GetMapping("get_phone")
    public String phone(){return "244455";}

    @GetMapping("get_email")
    public String email(){return "233444";}
}
