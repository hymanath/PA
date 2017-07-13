package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.model.BoothIncharge;

public interface IBoothInchargeDAO extends GenericDao<BoothIncharge,Long>{
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId);
	
	public List<Object[]> getCadreIdsForLocation(List<Long> tdpCadreIds);
	public BoothIncharge getExistingMember(Long locationId,String type);
	public Long getStartedBothCountByConstiId(Long constituencyId);
	public Long getBoothAssignInchargeCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Date startDate,Date endDate,List<Long> committeeEnrollmentYearsIdsLst,List<Long> bothIds);
	public List<Object[]> getBoothInchargeCountDetails(Long userAccessLevelId ,Set<Long> userAccessLevelValues,List<Long> boothCommEnrollYrIds,Date startDate,Date endDate);
	public List<Object[]> getLocationLevelWiseBoothCount(InputVO inputVO,String resultType);
	public List<Object[]> getLocationBasedOnSelection(InputVO inputVO);
	public List<Object[]> getLocationLevelWiseBoothDetails(InputVO inputVO);
}
