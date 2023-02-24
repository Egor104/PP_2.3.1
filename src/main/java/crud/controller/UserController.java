package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import crud.model.User;
import crud.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersAll";
    }

    @RequestMapping(value = "/userAdd", method = RequestMethod.GET)
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
            userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userUpdate";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String removeUserById(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}

