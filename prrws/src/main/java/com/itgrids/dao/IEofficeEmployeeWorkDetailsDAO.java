package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.EofficeEmployeeWorkDetails;

public interface IEofficeEmployeeWorkDetailsDAO extends GenericDao<EofficeEmployeeWorkDetails, Long>{
	
	public List<Object[]> getEOfcDepartmentCunts(Date fromDate,Date toDate);
	public Long deleteRecrdsFrmTable();
}
