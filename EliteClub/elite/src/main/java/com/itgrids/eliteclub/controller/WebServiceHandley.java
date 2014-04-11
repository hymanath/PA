package com.itgrids.eliteclub.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;

import com.itgrids.eliteclub.dto.BaseObject;

@RestController
public class WebServiceHandley { 

    @RequestMapping(value="/getBase",method=RequestMethod.GET,produces={"application/json"})
    public @ResponseBody BaseObject hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        
        BaseObject bobj= new BaseObject();
        bobj.setId(1);
        bobj.setName(name);
        HandlerAdapter bu =null;
        return bobj;
    }

}
