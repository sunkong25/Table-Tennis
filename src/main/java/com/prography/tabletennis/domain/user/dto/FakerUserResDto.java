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
public class FakerUserResDto {
	private String status;
	private int code;
	private String locale;
	private int total;
	private List<FakerUserDto> data;
}
