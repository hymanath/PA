package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;

public interface IBoothInchargeRoleConditionMappingDAO extends GenericDao<BoothInchargeRoleConditionMapping, Long> {

	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId);
}
