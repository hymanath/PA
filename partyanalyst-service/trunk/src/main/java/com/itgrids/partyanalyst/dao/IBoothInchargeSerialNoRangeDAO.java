package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.BoothInchargeSerialNoRange;
public interface IBoothInchargeSerialNoRangeDAO extends GenericDao<BoothInchargeSerialNoRange, Long> {
	public List<Object[]> getAllInchargeSerialNoRange();
}
