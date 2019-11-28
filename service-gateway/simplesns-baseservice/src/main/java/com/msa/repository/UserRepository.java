package com.msa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msa.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

	User findByUsernameAndPassword(String username, String password);

	List<User> findByIdIn(List<Long> userIdList);

}
