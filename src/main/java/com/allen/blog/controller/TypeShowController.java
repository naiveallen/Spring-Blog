package com.allen.blog.controller;

import com.allen.blog.bean.Blog;
import com.allen.blog.bean.Type;
import com.allen.blog.service.BlogService;
import com.allen.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
           id = types.get(0).getId();
        }
        Blog blog = new Blog();
        Type t = new Type();
        t.setId(id);
        blog.setType(t);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
