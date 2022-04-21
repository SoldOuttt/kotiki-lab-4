package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.serving_web.entity_dto.CatDTO;
import ru.sold_out.serving_web.service.CatService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminCatController {
	private final CatService catService;

	@GetMapping()
	public String getAll(Map<String, Object> model) {
		List<CatDTO> cats = catService.findAll();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/add")
	public String add(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@RequestParam(required = false) String breed,
			@RequestParam(required = false) String color,
			@RequestParam(required = false) Long ownerId
	) {
		catService.save(name, dayOfBirth, breed, color, ownerId);
		return "redirect:/cats";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam(required = false) Long id) {
		catService.deleteById(id);
		return "redirect:/cats";
	}

	@PostMapping(value = "/update")
	public String update(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
			@RequestParam(required = false) String breed,
			@RequestParam(required = false) String color,
			@RequestParam(required = false) Long ownerId
	) {
		catService.update(id, name, dayOfBirth, breed, color, ownerId);
		return "redirect:/cats";
	}

	@PostMapping(value = "/filterById")
	public String filterById(
			Map<String, Object> model,
			@RequestParam(required = false) Long id
	) {
		List<CatDTO> cats = (id == null) ?
				catService.findAll() :
				Collections.singletonList(catService.findById(id));
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByName")
	public String filterByName(
			Map<String, Object> model,
			@RequestParam(required = false) String name
	) {
		List<CatDTO> cats = (name.isBlank()) ?
				catService.findAll() :
				catService.findByName(name).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByDayOfBirth")
	public String filterByDayOfBirth(
			Map<String, Object> model,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		List<CatDTO> cats = (dayOfBirth == null) ?
				catService.findAll() :
				catService.findByDayOfBirth(dayOfBirth).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByBreed")
	public String filterByBreed(
			Map<String, Object> model,
			@RequestParam(required = false) String breed
	) {
		List<CatDTO> cats = (breed.isBlank()) ?
				catService.findAll() :
				catService.findByBreed(breed).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByColor")
	public String filterByColor(
			Map<String, Object> model,
			@RequestParam(required = false) String color
	) {
		List<CatDTO> cats = (color.isBlank()) ?
				catService.findAll() :
				catService.findByColor(color).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByOwnerId")
	public String filterByOwnerId(
			Map<String, Object> model,
			@RequestParam(required = false) Long ownerId
	) {
		List<CatDTO> cats = (ownerId == null) ?
				catService.findAll() :
				catService.findByOwnerId(ownerId).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}
}
