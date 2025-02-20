package com.prography.tabletennis.global.common.controller;

import com.prography.tabletennis.global.common.response.ApiResponse;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommonController {
	@GetMapping("/health")
	public ApiResponse<Void> healthCheck() {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}
}
