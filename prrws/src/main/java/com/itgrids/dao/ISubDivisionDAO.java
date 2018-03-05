package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.SubDivision;

public interface ISubDivisionDAO extends GenericDao<SubDivision,Long>{
	public List<Object[]> getSubDivisionIdAndNameByIds(List<Long> subDivisionIds); 
}
