package com.razik.market.controller;

import com.razik.market.data.TreeEntity;
import com.razik.market.service.Service;
import com.razik.market.service.TreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/add")
    public TreeEntity saveLink(@RequestBody TreeDTO treeDTO) {
        return service.save(treeDTO);
    }

}
