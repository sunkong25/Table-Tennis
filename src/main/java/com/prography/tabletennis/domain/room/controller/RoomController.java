package com.prography.tabletennis.domain.room.controller;

import com.prography.tabletennis.domain.room.dto.RoomDetailsResDto;
import com.prography.tabletennis.domain.room.dto.RoomListResDto;
import com.prography.tabletennis.domain.room.dto.RoomReqDto;
import com.prography.tabletennis.domain.room.dto.UserReqDto;
import com.prography.tabletennis.domain.room.service.RoomService;
import com.prography.tabletennis.global.common.response.ApiResponse;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/room")
	public ApiResponse<RoomListResDto> getRoomList(@RequestParam int size, @RequestParam int page) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, roomService.getRoomList(size, page));
	}

	@GetMapping("/room/{roomId}")
	public ApiResponse<RoomDetailsResDto> getRoomDetails(@PathVariable(name = "roomId") int roomId) {
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, roomService.getRoomDetails(roomId));
	}
}
