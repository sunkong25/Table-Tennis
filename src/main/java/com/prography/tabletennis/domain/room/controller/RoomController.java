package com.prography.tabletennis.domain.room.controller;

import com.prography.tabletennis.domain.room.dto.RoomReqDto;
import com.prography.tabletennis.domain.room.service.RoomService;
import com.prography.tabletennis.global.common.response.ApiResponse;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	@PostMapping("/room")
	public ApiResponse<Void> createRoom(@RequestBody RoomReqDto request) {
		roomService.createRoom(request);
		return new ApiResponse(ApiResponseStatus.SUCCESS);
	}
}
