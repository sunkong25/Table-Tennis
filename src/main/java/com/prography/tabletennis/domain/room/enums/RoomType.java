package com.prography.tabletennis.domain.room.enums;

import lombok.Getter;

@Getter
public enum RoomType {
	SINGLE(2),
	DOUBLE(4);

	private final int limit;

	RoomType(int limit) {
		this.limit = limit;
	}
}
