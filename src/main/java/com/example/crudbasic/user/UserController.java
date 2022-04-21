package com.example.crudbasic.user;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> Listusers = service.listAll();
        model.addAttribute("ListUsers", Listusers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);

        ra.addFlashAttribute("message", "The user has been saved successfully");


        return "redirect:/users";

    }
    @GetMapping("/users/edit/{id}")
        public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User (ID:" + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + "has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
    @RequestMapping("/users/sreach")
    public String sreach(ModelMap model,@RequestParam(defaultValue = "") String lastName) {
        List<User> list = UserService.findByLastNameNameLikeOrderByLastName(lastName);
        model.addAttribute("users", list);
        return "users/sreach";
    }
}
