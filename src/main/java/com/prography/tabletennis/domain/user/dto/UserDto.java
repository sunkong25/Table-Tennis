package com.prography.tabletennis.domain.user.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
	private int id;
	private int fakerId;
	private String name;
	private String email;
	@Enumerated(value = EnumType.STRING)
	private String status;
	private String createdAt;
	private String updatedAt;
}
