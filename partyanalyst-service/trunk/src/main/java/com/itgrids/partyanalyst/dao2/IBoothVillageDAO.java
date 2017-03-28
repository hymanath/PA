package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothVillage;

public interface IBoothVillageDAO extends GenericDao<BoothVillage, Long> {

	public List getVillagesForABoothInAConstituency(String constituencyName, Long partNo);
}
