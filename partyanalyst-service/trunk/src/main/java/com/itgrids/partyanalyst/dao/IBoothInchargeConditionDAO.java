package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothInchargeCondition;

public interface IBoothInchargeConditionDAO extends GenericDao<BoothInchargeCondition, Long> {
	public List<Long> getboothInchargeConditionIdByRange(Long minValue, Long maxValue);
}
