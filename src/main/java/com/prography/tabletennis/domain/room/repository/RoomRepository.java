package com.prography.tabletennis.domain.room.repository;

import com.prography.tabletennis.domain.room.entity.Room;
import com.prography.tabletennis.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	Page<Room> findAll(Pageable pageable);

	Optional<Room> findRoomById(int userId);
}
