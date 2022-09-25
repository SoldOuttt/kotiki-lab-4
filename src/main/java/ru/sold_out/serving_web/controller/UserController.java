package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity.additional_info.Role;
import ru.sold_out.serving_web.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
	private final UserService userService;

	@GetMapping()
	public String getAll(Map<String, Object> model) {
		List<User> users = userService.findAll();
		model.put("roles", Role.values());
		model.put("users", users);
		return "user/users";
	}

	@PostMapping(value = "/add")
	public String add(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String password,
			@RequestParam Map<String, String> form
	) {
		Set<Role> userRoles = new HashSet<>();
		Set<String> roles = Arrays.stream(Role.values())
				.map(Role::name)
				.collect(Collectors.toSet());
		for (String key : form.keySet()) {
			if (roles.contains(key)) {
				userRoles.add(Role.valueOf(key));
			}
		}
		userService.save(username, password, true, userRoles);
		return "redirect:/users";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam(required = false) Long id) {
		userService.deleteById(id);
		return "redirect:/users";
	}
}
