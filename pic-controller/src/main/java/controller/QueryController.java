package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/26 上午8:54
 */
@RestController
public class QueryController {

    @GetMapping("/passwdSecQuery")
    public ModelAndView passwdSecQuery(@RequestBody Object o){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
