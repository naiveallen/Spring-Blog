package com.allen.blog.controller.admin;

import com.allen.blog.bean.Tag;
import com.allen.blog.service.TagService;
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
public class AdminTagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String save(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.saveTag(tag);
        if (tag1 == null) {
            attributes.addFlashAttribute("message", "Failed to add a tag, please try later.");
        } else {
            attributes.addFlashAttribute("message", "Add a new tag Successfully.");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String update(@PathVariable("id") Long id,
                         Tag tag,
                         RedirectAttributes attributes) {
        Tag tag1 = tagService.updateTag(id, tag);
        if (tag1 == null) {
            attributes.addFlashAttribute("message", "Failed to update a tag, please try later.");
        } else {
            attributes.addFlashAttribute("message", "Update a new tag Successfully.");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        tagService.deteleTag(id);
        attributes.addFlashAttribute("message", "Delete a tag Successfully.");
        return "redirect:/admin/tags";
    }

}
