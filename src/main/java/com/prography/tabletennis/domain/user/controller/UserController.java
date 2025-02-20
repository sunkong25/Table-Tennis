package com.prography.tabletennis.domain.user.controller;

import com.prography.tabletennis.domain.user.dto.InitReqDto;
import com.prography.tabletennis.domain.user.service.UserService;
import com.prography.tabletennis.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/init")
	public ApiResponse<Void> init(@RequestBody InitReqDto request) {
		return new ApiResponse<>(userService.init(request));
	}
}
