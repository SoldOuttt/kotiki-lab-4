package ru.sold_out.serving_web.entity.additional_info;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	USER,
	ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
