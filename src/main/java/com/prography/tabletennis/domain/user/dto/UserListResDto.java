package com.prography.tabletennis.domain.user.dto;

import com.prography.tabletennis.domain.user.entity.User;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserListResDto {
	private int totalElements;
	private int totalPages;
	private List<UserDto> userList;
}
