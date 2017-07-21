package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ConvergenceType;

public interface IConvergenceTypeDAO extends GenericDao<ConvergenceType, Long>{
	
	public List<Object[]> getAllConvergenceTypes();

}
