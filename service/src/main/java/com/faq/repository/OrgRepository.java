package com.faq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.faq.entity.OrgEntity;

@Repository
public interface OrgRepository extends CrudRepository<OrgEntity, Long> {	
	OrgEntity findByOrgId(String orgId);
}
