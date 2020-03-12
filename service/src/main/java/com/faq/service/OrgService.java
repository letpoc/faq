package com.faq.service;

import com.faq.entity.OrgEntity;
import com.faq.shared.dto.OrgDto;

public interface OrgService {
	OrgDto createOrg(OrgEntity orgEntity);
}
