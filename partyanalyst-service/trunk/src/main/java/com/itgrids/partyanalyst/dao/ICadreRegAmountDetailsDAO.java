package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegAmountDetails;

public interface ICadreRegAmountDetailsDAO extends GenericDao<CadreRegAmountDetails, Long>{
	public List<Object[]> getAmountDetailsOfUser(Date fromDate,Date toDate);
}
