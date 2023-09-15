package com.razik.market.controller;

import com.razik.market.Constant;
import com.razik.market.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TemplateController {

    @Autowired
    private Service service;

    @GetMapping(value = {"/", "/index/{id}"})
    public String getIndex(@PathVariable(required = false) Long id, Model model) {
        if (id == null) {
            id = Constant.ROOT_ID;
        }
        model.addAttribute("id", id);
        model.addAttribute("children", service.getChildren(id));
        model.addAttribute("parent", service.findParentById(id));
        return "link-list";
    }


}
