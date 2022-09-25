package ru.sold_out.serving_web.mapping_util;

import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity_dto.CatDTO;

public class CatMappingUtil {
	public static CatDTO mapToDTO(Cat cat) {
		CatDTO catDTO = new CatDTO();
		catDTO.setId(cat.getId());
		catDTO.setName(cat.getName());
		catDTO.setBreed(cat.getBreed());
		catDTO.setColor(cat.getColor());
		if (cat.getCatOwner() == null) {
			cat.setCatOwner(new CatOwner());
		}
		catDTO.setOwnerId(cat.getCatOwner().getId());
		catDTO.setDayOfBirth(cat.getDayOfBirth());
		return catDTO;
	}
}
