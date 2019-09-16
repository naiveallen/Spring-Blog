package com.allen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BlogController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable("id") String id, @PathVariable("name") String name) {

        return "index";
    }

}
