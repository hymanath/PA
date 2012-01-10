package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public interface IPanchayatHamletDAO extends GenericDao<PanchayatHamlet,Long>{
	
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId);
}
