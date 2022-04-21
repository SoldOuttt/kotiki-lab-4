package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity_dto.CatOwnerDTO;
import ru.sold_out.serving_web.service.CatOwnerService;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/uv/catOwners")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class UserCatOwnerController {
	private final CatOwnerService catOwnerService;

	@GetMapping()
	public String getInfo(
			Map<String, Object> model,
			@AuthenticationPrincipal User user
	) {
		Optional<CatOwnerDTO> catOwnerResult = catOwnerService.findByUser(user)
				.stream()
				.findFirst();
		CatOwnerDTO catOwner = (catOwnerResult.isEmpty()) ? new CatOwnerDTO() : catOwnerResult.get();
		model.put("catOwner", catOwner);
		return "catOwner/catOwnersUV";
	}

	@PostMapping(value = "/add")
	public String add(
			@RequestParam(required = false) String fullName,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@AuthenticationPrincipal User user
	) {
		catOwnerService.save(fullName, dayOfBirth, user);
		return "redirect:/uv/catOwners";
	}

	@PostMapping(value = "/update")
	public String update(
			@RequestParam(required = false) String fullName,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@AuthenticationPrincipal User user
	) {
		catOwnerService.update(fullName, dayOfBirth, user);
		return "redirect:/uv/catOwners";
	}

	@PostMapping(value = "/delete")
	public String delete(@AuthenticationPrincipal User user) {
		catOwnerService.deleteByUser(user);
		return "redirect:/uv/catOwners";
	}
}
