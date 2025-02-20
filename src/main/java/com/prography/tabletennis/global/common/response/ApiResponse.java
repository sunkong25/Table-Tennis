package com.prography.tabletennis.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
	private final Integer code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;

	public ApiResponse(ApiResponseStatus apiResponseStatus) {
		this.code = apiResponseStatus.getCode();
		this.message = apiResponseStatus.getMessage();
	}

	public ApiResponse(ApiResponseStatus apiResponseStatus, T result) {
		this.code = apiResponseStatus.getCode();
		this.message = apiResponseStatus.getMessage();
		this.result = result;
	}
}
