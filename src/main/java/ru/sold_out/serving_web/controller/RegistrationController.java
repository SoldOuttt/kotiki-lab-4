package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity.additional_info.Role;
import ru.sold_out.serving_web.service.UserService;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
	private final UserService userService;

	@GetMapping("/register")
	public String register(Map<String, Object> model) {
		return "registration";
	}

	@PostMapping("/register")
	public String addUser(Map<String, Object> model,
						  @RequestParam(required = false) String username,
						  @RequestParam(required = false) String password
	) {
		Optional<User> userResult = userService.findByUserName(username);
		if (userResult.isPresent()
				|| username.isBlank()
				|| password.isBlank()
		) {
			model.put("message", "username already exists");
			return "registration";
		}
		userService.save(username, password, true, Collections.singleton(Role.USER));
		return "redirect:/login";
	}
}
