package com.prography.tabletennis.domain.user.entity;

import com.prography.tabletennis.domain.user.enums.Status;
import com.prography.tabletennis.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "\"user\"")
public class User extends BaseEntity {
	@Id
	@GeneratedValue
	private int id;
	private int fakerId;
	private String name;
	private String email;
	@Enumerated(EnumType.STRING)
	private Status status;
}
