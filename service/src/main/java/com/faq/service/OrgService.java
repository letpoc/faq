package com.faq.service;

import java.util.List;

import com.faq.shared.dto.OrgDto;

public interface OrgService {	
	void approveOrg(String orgId);
	List<OrgDto> getOrgs(int page, int size);
	long getCount();
}
