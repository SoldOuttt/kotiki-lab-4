package ru.sold_out.serving_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity_dto.CatOwnerDTO;
import ru.sold_out.serving_web.mapping_util.CatOwnerMappingUtil;
import ru.sold_out.serving_web.repository.CatOwnerRepository;
import ru.sold_out.serving_web.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatOwnerService {
	private final CatOwnerRepository catOwnerRepository;
	private final UserRepository userRepository;

	public void save(String fullName, Date dayOfBirth, User user) {
		if (fullName.isBlank()
				|| dayOfBirth == null
				|| user == null
				|| catOwnerRepository.findByUser(user).isPresent()
		) {
			return;
		}
		CatOwner catOwner = new CatOwner(fullName, dayOfBirth, user);
		catOwnerRepository.save(catOwner);
	}

	public void save(String fullName, Date dayOfBirth, Long userId) {
		if (userId == null) { // поправь потом
			return;
		}
		User user = userRepository.findById(userId).orElse(null);
		save(fullName, dayOfBirth, user);
	}

	public void update(String fullName, Date dayOfBirth, User user) {
		if (user == null
				|| fullName.isBlank()
				|| dayOfBirth == null
		) {
			return;
		}
		CatOwner catOwner = user.getCatOwner();
		if (catOwner == null) {
			return;
		}
		catOwner.setFullName(fullName);
		catOwner.setDayOfBirth(dayOfBirth);
		catOwnerRepository.save(catOwner);
	}

	public void deleteById(Long id) {
		if (id == null) {
			return;
		}
		try {
			catOwnerRepository.deleteById(id);
		} catch (Exception exception) {
			System.out.println(exception.getStackTrace());
			System.out.println(exception.getMessage());
		}
	}

	public void deleteByUser(User user) {
		if (user == null || user.getCatOwner() == null) {
			return;
		}
		CatOwner catOwner = user.getCatOwner();
		catOwnerRepository.delete(catOwner);
	}

	public void update(Long id, String fullName, Date dayOfBirth, User user) {
		if (id == null
				|| fullName.isBlank()
				|| dayOfBirth == null
				|| catOwnerRepository.findById(id).isEmpty()
		) {
			return;
		}
		CatOwner catOwner = new CatOwner(id, fullName, dayOfBirth, user);
		catOwnerRepository.save(catOwner);
	}

	public List<CatOwnerDTO> findAll() {
		return catOwnerRepository.findAll().stream()
				.map(CatOwnerMappingUtil::mapToDTO)
				.toList();
	}

	public CatOwnerDTO findById(Long id) {
		return CatOwnerMappingUtil.mapToDTO(catOwnerRepository.findById(id).orElse(new CatOwner()));
	}

	public List<CatOwnerDTO> findByFullName(String fullName) {
		return catOwnerRepository.findByFullName(fullName).stream()
				.map(CatOwnerMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatOwnerDTO> findByDayOfBirth(Date dayOfBirth) {
		return catOwnerRepository.findByDayOfBirth(dayOfBirth).stream()
				.map(CatOwnerMappingUtil::mapToDTO)
				.toList();
	}

	public List<CatOwnerDTO> findByUser(User user) {
		return catOwnerRepository.findByUser(user).stream()
				.map(CatOwnerMappingUtil::mapToDTO)
				.toList();
	}
}
