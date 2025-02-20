package com.prography.tabletennis.domain.user.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FakerUserDto {
	private int id;
	private String uuid;
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String ip;
	private String macAddress;
	private String website;
	private String image;
}
