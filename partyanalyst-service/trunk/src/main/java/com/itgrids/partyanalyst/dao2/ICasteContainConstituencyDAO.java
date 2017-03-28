package com.itgrids.partyanalyst.dao;

import java.util.List;

import java.util.Set;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CasteContainConstituency;


public interface ICasteContainConstituencyDAO extends GenericDao<CasteContainConstituency,Long>{

public	Long getRecordsCountToCasteContainConsti(Long constituencyId);

	
	

}
