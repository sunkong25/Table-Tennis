package com.prography.tabletennis.global.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiException.class)
	protected ApiResponse<ApiResponseStatus> apiException(ApiException e) {
		log.error("Handle ApiException: {}", e.getMessage());
		return new ApiResponse<>(e.getApiResponseStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ApiResponse<ApiResponseStatus> generalException(Exception e) {
		log.error("Handle generalException: {}", e.getMessage());
		return new ApiResponse<>(ApiResponseStatus.SERVER_ERROR);
	}
}
