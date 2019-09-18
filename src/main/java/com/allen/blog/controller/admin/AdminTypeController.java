package com.allen.blog.controller.admin;

import com.allen.blog.bean.Type;
import com.allen.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String save(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.saveType(type);
        if (type1 == null) {
            attributes.addFlashAttribute("message", "Failed to add a type, please try later.");
        } else {
            attributes.addFlashAttribute("message", "Add a new type Successfully.");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String update(@PathVariable("id") Long id,
                       Type type,
                       RedirectAttributes attributes) {
        Type type1 = typeService.updateType(id, type);
        if (type1 == null) {
            attributes.addFlashAttribute("message", "Failed to update a type, please try later.");
        } else {
            attributes.addFlashAttribute("message", "Update a new type Successfully.");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        typeService.deteleType(id);
        attributes.addFlashAttribute("message", "Delete a type Successfully.");
        return "redirect:/admin/types";
    }


}
