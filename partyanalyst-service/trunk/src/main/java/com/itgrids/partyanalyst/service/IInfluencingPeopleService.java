package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IInfluencingPeopleService {
	
	public Long saveInfluencePeople(InfluencingPeopleVO influencingPeopleVO);
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions();
	
	public List<SelectOptionVO> getInfluenceRange();
	
	public void readAndSaveInfluencePeopleDataIntoDB(File file);
}
