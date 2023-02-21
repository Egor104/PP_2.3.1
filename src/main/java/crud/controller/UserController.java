package crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import crud.model.User;
import crud.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "users_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new User());
        return "user_add";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
//        if (user.getId() == 0) {
            userService.addUser(user);
//        } else {
//            userService.updateUser(user);
//        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users_all";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String removeUserById(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}
