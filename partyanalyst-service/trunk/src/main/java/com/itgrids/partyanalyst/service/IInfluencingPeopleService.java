package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IInfluencingPeopleService {
	
	public Long saveInfluencePeople(InfluencingPeopleVO influencingPeopleVO);
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions();
	
	public List<SelectOptionVO> getInfluenceRange();
	
	public void readAndSaveInfluencePeopleDataIntoDB(File file);
	
	public InfluencingPeopleBeanVO saveInfluencePeopleInfo(
			InfluencingPeopleBeanVO influencingPeopleBeanVO1,
			Map<String, Long> influRangeAndValueMap1);
	public InfluencingPeopleBeanVO getDetailsByInfluencingPersonId(Long influencingPersonId);
	public Integer deleteInfluencingPeople(Long influencingPeopleId);
}
