package kami.liyf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kami.liyf.bean.LombokBean;
import kami.liyf.utils.FreemarkerUtil;

@Controller
@RequestMapping("/freemarkerController")
public class FreemarkerController {

    @Autowired
    private FreemarkerUtil freemarkerUtil;

    @PostMapping("/creatingFreemarkerHtml")
    @ResponseBody
    public String creatingFreemarkerHtml(){
        freemarkerUtil.createStaticPage("testModel", null, "testModel.ftl");
        return "";
    }

    @GetMapping("/jumpFtl")
    @ResponseBody
    public ModelAndView jumpFtl(ModelAndView modelAndView){
        modelAndView.setViewName("jumpFtl");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("list", list);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}
