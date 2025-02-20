package com.prography.tabletennis.domain.room.service;

import com.prography.tabletennis.domain.room.dto.RoomReqDto;
import com.prography.tabletennis.domain.room.entity.Room;
import com.prography.tabletennis.domain.room.entity.UserRoom;
import com.prography.tabletennis.domain.room.enums.RoomStatus;
import com.prography.tabletennis.domain.room.enums.RoomType;
import com.prography.tabletennis.domain.room.enums.Team;
import com.prography.tabletennis.domain.room.repository.RoomRepository;
import com.prography.tabletennis.domain.room.repository.UserRoomRepository;
import com.prography.tabletennis.domain.user.entity.User;
import com.prography.tabletennis.domain.user.enums.UserStatus;
import com.prography.tabletennis.domain.user.repository.UserRepository;
import com.prography.tabletennis.global.common.response.ApiException;
import com.prography.tabletennis.global.common.response.ApiResponseStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
