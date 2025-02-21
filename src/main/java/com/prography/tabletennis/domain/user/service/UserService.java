package com.prography.tabletennis.domain.user.service;

import com.prography.tabletennis.domain.room.dto.UserReqDto;
import com.prography.tabletennis.domain.room.repository.RoomRepository;
import com.prography.tabletennis.domain.room.repository.UserRoomRepository;
import com.prography.tabletennis.domain.user.dto.FakerUserResDto;
import com.prography.tabletennis.domain.user.dto.InitReqDto;
import com.prography.tabletennis.domain.user.dto.UserDto;
import com.prography.tabletennis.domain.user.dto.UserListResDto;
import com.prography.tabletennis.domain.user.entity.User;
import com.prography.tabletennis.domain.user.enums.UserStatus;
import com.prography.tabletennis.domain.user.repository.UserRepository;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final UserRoomRepository userRoomRepository;
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String FAKER_API_URL = "https://fakerapi.it/api/v1/users?_seed=%d&_quantity=%d&_locale=ko_KR";

	@Transactional
	public void init(InitReqDto request) {
		deleteAllData();

		String url = String.format(FAKER_API_URL, request.getSeed(), request.getQuantity());

		FakerUserResDto response = restTemplate.getForObject(url, FakerUserResDto.class);

		if (response == null) {
			throw new RuntimeException();
		}

		List<User> users = response.getData().stream()
				.sorted((u1, u2) -> Integer.compare(u1.getId(), u2.getId()))
				.map(fakerUser -> User.builder()
						.fakerId(fakerUser.getId())
						.name(fakerUser.getUsername())
						.email(fakerUser.getEmail())
						.userStatus(determineStatus(fakerUser.getId()))
						.build()
				).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	private void deleteAllData() {
		userRoomRepository.deleteAll();
		userRepository.deleteAll();
		roomRepository.deleteAll();
	}

	private UserStatus determineStatus(int id) {
		if (id <= 30) {
			return UserStatus.ACTIVE;
		} else if (id <= 60) {
			return UserStatus.WAIT;
		} else {
			return UserStatus.NON_ACTIVE;
		}
	}

	public UserListResDto getUserList(int size, int page) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		Page<User> userPage = userRepository.findAll(pageable);
		List<User> users = userPage.getContent();

		List<UserDto> userDtoList = users.stream()
				.map(user -> {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					return UserDto.builder()
							.id(user.getId())
							.fakerId(user.getFakerId())
							.name(user.getName())
							.email(user.getEmail())
							.status(String.valueOf(user.getUserStatus()))
							.createdAt(user.getCreatedAt().format(formatter))
							.updatedAt(user.getUpdatedAt().format(formatter))
							.build();
				}).toList();

		return UserListResDto.builder()
				.totalElements((int) userPage.getTotalElements())
				.totalPages(userPage.getTotalPages())
				.userList(userDtoList)
				.build();
	}
}
