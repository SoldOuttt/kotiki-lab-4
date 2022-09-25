package ru.sold_out.serving_web.entity_dto;

import lombok.Data;
import ru.sold_out.serving_web.entity.additional_info.CatColor;

import java.util.Date;

@Data
public class CatDTO {
	private Long id;
	private String name;
	private String breed;
	private CatColor color;
	private Long ownerId;
	private Date dayOfBirth;
}
