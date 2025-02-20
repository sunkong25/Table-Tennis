package com.prography.tabletennis.domain.room.repository;

import com.prography.tabletennis.domain.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
