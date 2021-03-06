package com.faq.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.faq.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	List<UserEntity> findByOrgId(String userId);
	Page<UserEntity> findAllByOrgId(String orgId, Pageable pageable);
	List<UserEntity> findAll();	
}
