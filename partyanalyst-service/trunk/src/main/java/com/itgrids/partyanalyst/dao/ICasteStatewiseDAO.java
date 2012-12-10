package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CasteStatewise;

public interface ICasteStatewiseDAO extends GenericDao<CasteStatewise, Long>{
	
	public List<Object[]> getStatewiseCastNames(Long casteCategoryGroupId);
	 public List<Object[]> getCasteNamesByAutoPopulate(Long stateId,String letters) ;
	 public List<Object[]> getAllCasteDetails();
	 
	 //public List<Object[]> getAllCasteInfoDetails();
}
