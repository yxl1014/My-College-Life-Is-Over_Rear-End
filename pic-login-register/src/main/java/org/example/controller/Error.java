package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/7 下午5:35
 */
@Controller
public class Error {

    @GetMapping("/register500")
    public ModelAndView register500(Object o){

        return new ModelAndView();
    }

    @GetMapping("/login500")
    public ModelAndView login500(Object o){

        return new ModelAndView();
    }
}
