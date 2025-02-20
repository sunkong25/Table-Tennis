package com.prography.tabletennis.domain.room.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomDetailsResDto {
	private int id;
	private String title;
	private int hostId;
	private String roomType;
	private String status;
	private String createdAt;
	private String updatedAt;
}
