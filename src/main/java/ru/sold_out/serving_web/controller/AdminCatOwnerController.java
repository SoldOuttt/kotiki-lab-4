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

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/catOwners")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminCatOwnerController {
	private final CatOwnerService catOwnerService;

	@GetMapping()
	public String getAll(Map<String, Object> model) {
		List<CatOwnerDTO> catOwners = catOwnerService.findAll();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/add")
	public String add(
			@RequestParam(required = false) String fullName,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@RequestParam(required = false) Long userId
	) {
		catOwnerService.save(fullName, dayOfBirth, userId);
		return "redirect:/catOwners";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam(required = false) Long id) {
		catOwnerService.deleteById(id);
		return "redirect:/catOwners";
	}

	@PostMapping(value = "/update")
	public String update(
			@AuthenticationPrincipal User user,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String fullName,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		catOwnerService.update(id, fullName, dayOfBirth, user);
		return "redirect:/catOwners";
	}

	@PostMapping(value = "/filterById")
	public String filterById(
			Map<String, Object> model,
			@RequestParam(required = false) Long id
	) {
		List<CatOwnerDTO> catOwners = (id == null) ?
				catOwnerService.findAll() :
				Collections.singletonList(catOwnerService.findById(id));
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/filterByName")
	public String filterByName(
			Map<String, Object> model,
			@RequestParam(required = false) String fullName
	) {
		List<CatOwnerDTO> catOwners = (fullName.isBlank()) ?
				catOwnerService.findAll() :
				catOwnerService.findByFullName(fullName).stream().toList();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/filterByDayOfBirth")
	public String filterByDayOfBirth(
			Map<String, Object> model,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		List<CatOwnerDTO> catOwners = (dayOfBirth == null) ?
				catOwnerService.findAll() :
				catOwnerService.findByDayOfBirth(dayOfBirth).stream().toList();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}
}
