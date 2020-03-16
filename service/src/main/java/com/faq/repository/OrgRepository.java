package com.faq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.faq.entity.OrgEntity;

@Repository
public interface OrgRepository extends PagingAndSortingRepository<OrgEntity, Long> {	
	OrgEntity findByOrgId(String orgId);

}
