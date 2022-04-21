package ru.sold_out.serving_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity.additional_info.CatColor;
import ru.sold_out.serving_web.entity_dto.CatDTO;
import ru.sold_out.serving_web.mapping_util.CatMappingUtil;
import ru.sold_out.serving_web.repository.CatOwnerRepository;
import ru.sold_out.serving_web.repository.CatRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {
	private final CatRepository catRepository;
	private final CatOwnerRepository catOwnerRepository;

	public void save(String name, Date dayOfBirth, String breed, String color, Long ownerId) {
		CatColor catColor;
		try {
			catColor = CatColor.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			return;
		}
		Optional<CatOwner> catOwner = catOwnerRepository.findById(ownerId);
		if (name.isBlank()
				|| dayOfBirth == null
				|| breed.isBlank()
				|| catOwner.isEmpty()) {
			return;
		}
		Cat cat = new Cat(name, dayOfBirth, breed, catColor, catOwner.get());
		catRepository.save(cat);
	}

	public void save(String name, Date dayOfBirth, String breed, String color, User user) {
		if (user == null || user.getCatOwner() == null) {
			return;
		}
		save(name, dayOfBirth, breed, color, user.getCatOwner().getId());
	}

	public void deleteById(Long id) {
		if (id == null) {
			return;
		}
		try {
			catRepository.deleteById(id);
		} catch (Exception exception) {
			System.out.println(exception.getStackTrace());
			System.out.println(exception.getMessage());
		}
	}

	public void deleteByIdAndUser(Long id, User user) {
		Optional<Cat> cat = catRepository.findByIdAndCatOwnerUser(id, user);
		if (cat.isEmpty()) {
			return;
		}
		catRepository.delete(cat.get());
	}

	public void update(
			Long id,
			String name,
			Date dayOfBirth,
			String breed,
			String color,
			Long ownerId
	) {
		CatColor catColor;
		try {
			catColor = CatColor.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			return;
		}
		Optional<CatOwner> catOwner = catOwnerRepository.findById(ownerId);
		if (name.isBlank()
				|| dayOfBirth == null
				|| breed.isBlank()
				|| catOwner.isEmpty()
				|| catRepository.findById(id).isEmpty()) {
			return;
		}
		Cat cat = new Cat(id, name, dayOfBirth, breed, catColor, catOwner.get());
		catRepository.save(cat);
	}

	public void update(
			Long id,
			String name,
			Date dayOfBirth,
			String breed,
			String color,
			User user
	) {
		Optional<Cat> cat = catRepository.findByIdAndCatOwnerUser(id, user);
		if (user == null || user.getCatOwner() == null || cat.isEmpty()) {
			return;
		}
		update(id, name, dayOfBirth, breed, color, user.getCatOwner().getId());
	}

	public List<CatDTO> findAll() {
		return catRepository.findAll().stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public CatDTO findById(Long id) {
		return CatMappingUtil.mapToDTO(catRepository.findById(id).orElse(new Cat()));
	}

	public List<CatDTO> findByName(String name) {
		return catRepository.findByName(name).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatDTO> findByDayOfBirth(Date dayOfBirth) {
		return catRepository.findByDayOfBirth(dayOfBirth).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatDTO> findByBreed(String breed) {
		return catRepository.findByBreed(breed).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatDTO> findByColor(String color) {
		CatColor catColor;
		try {
			catColor = CatColor.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			return Collections.emptyList();
		}
		return catRepository.findByColor(catColor).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatDTO> findByOwnerId(Long ownerId) {
		return catRepository.findByCatOwnerId(ownerId).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatDTO> findByOwnerUser(User user) {
		return catRepository.findByCatOwnerUser(user).stream()
				.map(CatMappingUtil::mapToDTO)
				.toList();
	}
}
