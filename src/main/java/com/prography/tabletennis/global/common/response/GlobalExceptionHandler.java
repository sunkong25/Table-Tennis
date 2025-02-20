package com.prography.tabletennis.global.common.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiException.class)
	protected ApiResponse<ApiResponseStatus> apiException(ApiException e) {
		return new ApiResponse<>(e.getApiResponseStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ApiResponse<ApiResponseStatus> generalException(Exception e) {
		return new ApiResponse<>(ApiResponseStatus.SERVER_ERROR);
	}
}
