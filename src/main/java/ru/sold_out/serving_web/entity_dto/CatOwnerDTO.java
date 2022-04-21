package ru.sold_out.serving_web.entity_dto;

import lombok.Data;

import java.util.Date;

@Data
public class CatOwnerDTO {
	private Long id;
	private String fullName;
	private Date dayOfBirth;
	private Long userId;
	private String username;
}
