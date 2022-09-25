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
import ru.sold_out.serving_web.entity_dto.CatDTO;
import ru.sold_out.serving_web.service.CatService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/uv/cats")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class UserCatController {
	private final CatService catService;

	@GetMapping()
	public String getAll(
			Map<String, Object> model,
			@AuthenticationPrincipal User user
	) {
		List<CatDTO> cats = catService.findByOwnerUser(user);
		model.put("cats", cats);
		return "cat/catsUV";
	}

	@PostMapping(value = "/add")
	public String add(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@RequestParam(required = false) String breed,
			@RequestParam(required = false) String color,
			@AuthenticationPrincipal User user
	) {

		catService.save(name, dayOfBirth, breed, color, user);
		return "redirect:/uv/cats";
	}

	@PostMapping(value = "/delete")
	public String delete(
			@RequestParam(required = false) Long id,
			@AuthenticationPrincipal User user
	) {
		catService.deleteByIdAndUser(id, user);
		return "redirect:/uv/cats";
	}

	@PostMapping(value = "/update")
	public String update(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@RequestParam(required = false) String breed,
			@RequestParam(required = false) String color,
			@AuthenticationPrincipal User user
	) {
		catService.update(id, name, dayOfBirth, breed, color, user);
		return "redirect:/uv/cats";
	}

}
