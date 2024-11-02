package ru.tonkoshkurov.mytestsecurity2dbthemeleaf.controller;


import jakarta.validation.Valid;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.dto.UserDto;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.entity.User;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.service.UserService;

import java.util.List;


@Controller
public class SecurityController {
    private UserService userService;

    public SecurityController(UserService userService) {this.userService = userService;}

    @GetMapping("/index")
    public String home() { return "index";}


    @GetMapping("/login")
    public String login() { return "login";}


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


}
