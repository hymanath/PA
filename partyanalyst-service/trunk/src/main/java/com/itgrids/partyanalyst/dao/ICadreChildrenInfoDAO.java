package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreChildrenInfo;



public interface ICadreChildrenInfoDAO extends GenericDao<CadreChildrenInfo, Long>{
	
	public List<Object[]> findByCadreId(Long cadreId); 

}
