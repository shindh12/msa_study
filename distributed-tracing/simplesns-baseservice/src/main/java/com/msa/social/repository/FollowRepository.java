package com.msa.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msa.domain.Follow;
import com.msa.domain.FollowPK;

public interface FollowRepository extends JpaRepository<Follow, FollowPK> {

	List<Follow> findByFolloweeId(Long followeeId);
	
	void deleteByFolloweeIdAndFollowerId(Long followeeId, Long followerId);

	List<Follow> findByFollowerIdAndFolloweeIdIn(Long followerId, List<Long> userIdList);
}
