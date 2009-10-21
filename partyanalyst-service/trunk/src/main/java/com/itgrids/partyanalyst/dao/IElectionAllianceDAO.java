package com.itgrids.partyanalyst.dao;

import java.util.List;

import com.itgrids.partyanalyst.model.ElectionAlliance;

public interface IElectionAllianceDAO {
	public List<ElectionAlliance> findByElectionYear(String year);
}
