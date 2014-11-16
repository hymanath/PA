package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegAmountDetails;

public interface ICadreRegAmountDetailsDAO extends GenericDao<CadreRegAmountDetails, Long>{
	public List<Object[]> getAmountDetailsOfUser(Date fromDate,Date toDate);
	public List<Object[]> getAmountDetailsOfUserByDate(Date fromDate,Date toDate);
	public List<Object[]> getAmountDetailsDateWise(String type);
	public List<Object[]> getAmountDetailsOfWebUser(Date fromDate,Date toDate);
	public List<Object[]> getAmountDetailsOfWebUserByDate(Date fromDate,Date toDate);
	
	public List<Object[]> getPaidAmountDetailsOfWebUserByDateANDType(Long webUserId, Date fromDate,Date toDate,String type);
	
}
