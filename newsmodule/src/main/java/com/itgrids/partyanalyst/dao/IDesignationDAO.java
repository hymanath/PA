package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Designation;

public interface IDesignationDAO extends GenericDao<Designation, Long>{
	
	public List<Object[]> getDesignationsList();
	
	public Long getDesignation(String designation);

}
