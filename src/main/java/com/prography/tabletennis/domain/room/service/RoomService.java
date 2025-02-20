package com.prography.tabletennis.domain.room.service;

import com.prography.tabletennis.domain.room.dto.RoomDetailsResDto;
import com.prography.tabletennis.domain.room.dto.RoomDto;
import com.prography.tabletennis.domain.room.dto.RoomListResDto;
import com.prography.tabletennis.domain.room.dto.RoomReqDto;
import com.prography.tabletennis.domain.room.dto.UserReqDto;
import com.prography.tabletennis.domain.room.entity.Room;
import com.prography.tabletennis.domain.room.entity.UserRoom;
import com.prography.tabletennis.domain.room.enums.RoomStatus;
import com.prography.tabletennis.domain.room.enums.RoomType;
import com.prography.tabletennis.domain.room.enums.Team;
import com.prography.tabletennis.domain.room.repository.RoomRepository;
import com.prography.tabletennis.domain.room.repository.UserRoomRepository;
import com.prography.tabletennis.domain.user.dto.UserDto;
import com.prography.tabletennis.domain.user.dto.UserListResDto;
import com.prography.tabletennis.domain.user.entity.User;
import com.prography.tabletennis.domain.user.enums.UserStatus;
import com.prography.tabletennis.domain.user.repository.UserRepository;
import com.prography.tabletennis.global.common.response.ApiException;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final UserRoomRepository userRoomRepository;

	@Transactional
	public void createRoom(RoomReqDto request) {
		User findUser = userRepository.findById(request.getUserId());
		Optional<UserRoom> findUserRoom = userRoomRepository.findByUser(findUser);

		if (findUser.getUserStatus() != UserStatus.ACTIVE) {
			throw new ApiException(ApiResponseStatus.ERROR);
		}
		if (findUserRoom.isPresent()) {
			throw new ApiException(ApiResponseStatus.ERROR);
		}

		Room room = Room.builder()
				.title(request.getTitle())
				.host(request.getUserId())
				.roomType(RoomType.valueOf(request.getRoomType()))
				.roomStatus(RoomStatus.WAIT)
				.build();
		roomRepository.save(room);

		UserRoom userRoom = UserRoom.builder()
				.user(findUser)
				.room(room)
				.team(Team.RED)
				.build();
		userRoomRepository.save(userRoom);
	}

	public RoomListResDto getRoomList(int size, int page) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		Page<Room> roomPage = roomRepository.findAll(pageable);
		List<Room> rooms = roomPage.getContent();

		List<RoomDto> roomDtoList = rooms.stream()
				.map(room -> {
					return RoomDto.builder()
							.id(room.getId())
							.title(room.getTitle())
							.hostId(room.getHost())
							.roomType(String.valueOf(room.getRoomType()))
							.status(String.valueOf(room.getRoomStatus()))
							.build();
				}).toList();

		return RoomListResDto.builder()
				.totalElements((int) roomPage.getTotalElements())
				.totalPages(roomPage.getTotalPages())
				.roomList(roomDtoList)
				.build();
	}

	public RoomDetailsResDto getRoomDetails(int roomId) {
		Room findRoom = roomRepository.findRoomById(roomId)
				.orElseThrow(() -> new ApiException(ApiResponseStatus.ERROR));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return RoomDetailsResDto.builder()
				.id(findRoom.getId())
				.title(findRoom.getTitle())
				.hostId(findRoom.getHost())
				.roomType(String.valueOf(findRoom.getRoomType()))
				.status(String.valueOf(findRoom.getRoomStatus()))
				.createdAt(findRoom.getCreatedAt().format(formatter))
				.updatedAt(findRoom.getUpdatedAt().format(formatter))
				.build();
	}
}

