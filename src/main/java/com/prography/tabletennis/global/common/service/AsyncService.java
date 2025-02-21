package com.prography.tabletennis.global.common.service;

import com.prography.tabletennis.domain.room.entity.Room;
import com.prography.tabletennis.domain.room.enums.RoomStatus;
import com.prography.tabletennis.domain.room.repository.RoomRepository;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
public class AsyncService {
	private final RoomRepository roomRepository;
	@Async
	public void updateStateAfterDelay(Room findRoom) {
		try {
			TimeUnit.MINUTES.sleep(1);
			findRoom.update(RoomStatus.FINISH);
			roomRepository.save(findRoom);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
