package com.prography.tabletennis.domain.room.entity;

import com.prography.tabletennis.domain.room.enums.RoomType;
import com.prography.tabletennis.domain.room.enums.RoomStatus;
import com.prography.tabletennis.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
public class Room extends BaseEntity {
	@Id
	@GeneratedValue
	private int id;

	private String title;

	private int host;

	@Enumerated(EnumType.STRING)
	@Column(name = "room_type")
	private RoomType roomType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RoomStatus roomStatus;
}
