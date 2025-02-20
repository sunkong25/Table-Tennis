package com.prography.tabletennis.domain.user.controller;

import com.prography.tabletennis.domain.user.dto.InitReqDto;
import com.prography.tabletennis.domain.user.dto.UserListResDto;
import com.prography.tabletennis.domain.user.service.UserService;
import com.prography.tabletennis.global.common.response.ApiResponse;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/init")
	public ApiResponse<Void> init(@RequestBody InitReqDto request) {
		userService.init(request);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}

	@GetMapping("/user")
	public ApiResponse<UserListResDto> getUserList(@RequestParam int size, @RequestParam int page) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, userService.getUserList(size, page));
	}
}
