package ru.sold_out.serving_web.mapping_util;

import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity_dto.CatOwnerDTO;

@Service
public class CatOwnerMappingUtil {
	public static CatOwnerDTO mapToDTO(CatOwner catOwner) {
		CatOwnerDTO catOwnerDTO = new CatOwnerDTO();
		catOwnerDTO.setId(catOwner.getId());
		catOwnerDTO.setFullName(catOwner.getFullName());
		catOwnerDTO.setDayOfBirth(catOwner.getDayOfBirth());
		if (catOwner.getUser() == null) {
			catOwner.setUser(new User());
		}
		catOwnerDTO.setUserId(catOwner.getUser().getId());
		catOwnerDTO.setUsername(catOwner.getUser().getUsername());
		return catOwnerDTO;
	}
}
