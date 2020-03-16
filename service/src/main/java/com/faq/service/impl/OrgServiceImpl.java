package com.faq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.faq.entity.OrgEntity;
import com.faq.repository.OrgRepository;
import com.faq.service.OrgService;
import com.faq.shared.dto.OrgDto;

@Service
public class OrgServiceImpl implements OrgService {	
	
	@Autowired
	OrgRepository orgRepository;

	@Override
	public void approveOrg(String orgId) {
		OrgEntity orgEntity = orgRepository.findByOrgId(orgId);
		orgEntity.setApprove(true);
		orgRepository.save(orgEntity);	
	}
	
	@Override
	public List<OrgDto> getOrgs(int page, int size) {
		List<OrgDto> orgDtoList = new ArrayList<OrgDto>();
		Pageable pageable = PageRequest.of(page, size);
		Page<OrgEntity> orgEntityPage = orgRepository.findAll(pageable);
		List<OrgEntity> orgs = orgEntityPage.getContent();		
		System.out.println(orgEntityPage.getTotalPages());
		for(OrgEntity orgEntity: orgs) {
			OrgDto orgDto = new OrgDto();
			BeanUtils.copyProperties(orgEntity, orgDto);
			orgDtoList.add(orgDto);
		}
		return orgDtoList;
	}

	@Override
	public long getCount() {
		return orgRepository.count();		 
	}

	@Override
	public void disableOrg(String orgId) {
		OrgEntity orgEntity = orgRepository.findByOrgId(orgId);
		orgEntity.setApprove(false);
		orgRepository.save(orgEntity);	
		
	}
	
	@Override
	public void enableOrg(String orgId) {
		OrgEntity orgEntity = orgRepository.findByOrgId(orgId);
		orgEntity.setApprove(true);
		orgRepository.save(orgEntity);	
		
	}

}
