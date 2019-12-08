package com.itmayiedu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YJJ on 2019/9/23.
 */
@Controller
public class FreeMarkerController {

    @RequestMapping("getFMModel")
    public String getFreeMarker(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {//MVC中，modelAndView机制 请求会有Model，返回的时候自动返回这个Model（相当于Request）
        model.addAttribute("name","yangjiajun");
        return "getModel";
    }
    @RequestMapping("getModel")
    public String getModel(Model model, HttpServletRequest request){
        System.out.println(request.getAttribute("name"));
        return "first";
    }

    @RequestMapping("getFMMap")
    public String getFreeMarker(Model model,Map<String,String> m,String name){
        model.addAttribute("String a zheshi");
        m.put("name",m.getOrDefault("name","默认值"));
        m.put("name2",name);
        return "first";
    }
}

