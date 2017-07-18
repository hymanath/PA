package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;

public interface IBoothInchargeRoleConditionMappingDAO extends GenericDao<BoothInchargeRoleConditionMapping, Long> {

	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId,List<Long> enrollmentYrIds);
	public List<Object[]> getBoothMinMaxRequiredMemberRoleWise(Long boothId,Long boothInchargeEnrollmentId);
	public int updateBoothStatus(Long boothId,Long boothInchargeEnrollmentId,Date dateTime);
	public List<Object[]> getBoothDetailsForTehsilId(List<Long> tehsilIds,Long constituencyId);
	public List<Object[]> getBoothsDetailsForMuncipality(List<Long> lcalElcBdyId,Long constituencyId);
	public List<Object[]> gettingBoothInchargeMaxCount(Long boothId,Long boothInchargeEnrollmentId,Long locationValue);
	public List<Object[]> getLocationLevelWiseBoothCount(InputVO inputVO,String resultType);
	public List<Object[]> getLocationBasedOnSelection(InputVO inputVO);
	public List<Object[]> getLocationLevelWiseBoothDetails(InputVO inputVO);
	public List<Object[]> getBoothInchargeRoles();

}
