package com.prography.tabletennis.domain.room.repository;

import com.prography.tabletennis.domain.room.entity.Room;
import com.prography.tabletennis.domain.room.entity.UserRoom;
import com.prography.tabletennis.domain.room.enums.Team;
import com.prography.tabletennis.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {
	Optional<UserRoom> findByUser(User user);

	Optional<UserRoom> findByUserAndRoom(User user, Room room);

	int countAllByRoom(Room room);

	int countAllByRoomAndTeam(Room room, Team team);

	void deleteUserRoomByUser(User user);

	void deleteUserRoomByRoom(Room room);
}
