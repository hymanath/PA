package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationVillage;

public interface IDelimitationVillageDAO extends GenericDao<DelimitationVillage, Long>{

	public List getVillagesFromPartialMandal(Long dcmId);
	public List<DelimitationVillage> findByDelimitationMandalAndTownship(Long delimitationMandalId,Long townshipId);
}
