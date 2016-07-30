package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.UserDataVO;

public interface ICoreDashboardService {
	
	public UserDataVO getUserBasicDetails(Long userId);
	public List<CommitteeBasicVO> getMainCommitteeCountDetails(Long committeeId,String state);
	public List<CommitteeVO> getCommitteesWiseLevelsBasedDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String startDateString,String endDateString);
	public List<CommitteeVO> getBasicComparativeWiseCommitteesCounts(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
	public List<CommitteeVO> levelWiseComparativeCountsByBasicCommittees(Long userAccessLevelId,List<Long> userAccessLevelValues,String state,List<Long> basicCommitteeIds,String firstMonthString,String secondMonthString);
}
