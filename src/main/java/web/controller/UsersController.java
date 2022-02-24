package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService service;

    @GetMapping()
    public String printUsers(Model model){
        List<User> users = service.getUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = service.findUserByID(id);
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user){
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/del/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model){
        User user = service.findUserByID(id);
        model.addAttribute("user", user);
        return "deleteUser";
    }

    @PostMapping("/del")
    public String delUser(@ModelAttribute("user") User user){
        service.deleteUser((int) user.getId());
        return "redirect:/users";
    }
}
