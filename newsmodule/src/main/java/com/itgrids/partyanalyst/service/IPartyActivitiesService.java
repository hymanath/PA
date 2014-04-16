package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.PartyActivitiesVO;

public interface IPartyActivitiesService {
	public PartyActivitiesVO getNewsToUpdateKeywords(List<Long> categoryIds,Date fromDate,Date toDate,List<Long> districtIds,Integer startIndex,Integer maxIndex);
	public String updateKeywords(List<PartyActivitiesVO> activities);
	public List<PartyActivitiesVO> getActivitiesStatus(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,List<Long> categoryIds);
	public List<PartyActivitiesVO> getCategoryWiseActivities(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,List<Long> categoryIds);
}
