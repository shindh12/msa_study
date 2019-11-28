package com.msa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msa.domain.Feed;
import com.msa.domain.FeedPK;

public interface FeedRepository extends JpaRepository<Feed, FeedPK> {

	List<Feed> findByUserId(Long userId);

	void deleteByUserIdAndFolloweeId(Long userId, Long followeeId);

}
