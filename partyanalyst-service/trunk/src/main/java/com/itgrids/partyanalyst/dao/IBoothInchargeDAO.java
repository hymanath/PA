package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.model.BoothIncharge;

public interface IBoothInchargeDAO extends GenericDao<BoothIncharge,Long>{
	//public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId);
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId,String cadreType);
	
	public List<Object[]> getCadreIdsForLocation(List<Long> tdpCadreIds);
	public BoothIncharge getExistingMember(Long locationId,String type);
	public Long getStartedBothCountByConstiId(Long constituencyId);
	public Long getBoothAssignInchargeCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Date startDate,Date endDate,List<Long> committeeEnrollmentYearsIdsLst,List<Long> bothIds);
	public List<Object[]> getBoothInchargeCountDetails(Long userAccessLevelId ,Set<Long> userAccessLevelValues,List<Long> boothCommEnrollYrIds,Date startDate,Date endDate);
	public List<Object[]> getBoothInchargeCountByRoleIds(Set<Long> keySet,List<Long> boothEnrollmentYrIds);
	public List<Long> checkIsBoothAlreadySaved(Long boothId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds);
	public List<Object[]> getAddedMemberInBoothRoleWise(Long boothId,Long boothInchargeEnrollmentId);
	public List<Object[]> gettingBoothInchargeFinalCount(Long boothId,Long boothInchargeEnrollmentId,Long locationValue);
	public List<Object[]> getBoothInchargeRangeIds(Long boothId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds);
	public List<Object[]> getLocationSerialNoRangeWiseVoterCount(InputVO inputVO);
	public List<Object[]> getLocationLevelWseCadreDetails(InputVO inputVO,String type);
	public Long getRoleWiseTotalAddedMember(Long boothId,Long boothInchargeEnrollmentId,Long boothInchargeRoleId);
}
