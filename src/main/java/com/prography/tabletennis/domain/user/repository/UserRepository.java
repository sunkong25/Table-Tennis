package com.prography.tabletennis.domain.user.repository;

import com.prography.tabletennis.domain.user.dto.UserListResDto;
import com.prography.tabletennis.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Page<User> findAll(Pageable pageable);

	User findById(int id);
}
