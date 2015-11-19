package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.IdNameVO;

public interface ICadreDetailsUtils {
	public Map<Long,List<IdNameVO>> getAreaWiseList(String searchType,Long stateId,String stateType,boolean isRequuredAreaNo);
	public String getCommitteeLocationNameByLocationTypeAndId(Long committeeLevelId,Long locationValue);
	public String getPublicRepresentativesLocationNameByLocationTypeAndId(Long levelId,Long locationValue);
}
