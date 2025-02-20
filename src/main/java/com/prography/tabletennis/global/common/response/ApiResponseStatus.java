package com.prography.tabletennis.global.common.response;

import lombok.Getter;

@Getter
public enum ApiResponseStatus {
	SUCCESS(200, "API 요청이 성공했습니다."),
	ERROR(201, "불가능한 요청입니다."),
	SERVER_ERROR(500, "에러가 발생했습니다.");

	private final Integer code;
	private final String message;

	ApiResponseStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
