package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;

public interface IBoothInchargeRoleConditionMappingDAO extends GenericDao<BoothInchargeRoleConditionMapping, Long> {

	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId,List<Long> enrollmentYrIds);
	public List<Object[]> getBoothMinMaxRequiredMemberRoleWise(Long boothId,Long boothInchargeEnrollmentId);
	public int updateBoothStatus(Long boothId,Long boothInchargeEnrollmentId,Date dateTime);
}
