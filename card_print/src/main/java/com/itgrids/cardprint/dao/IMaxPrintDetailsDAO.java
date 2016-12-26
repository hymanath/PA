package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.MaxPrintDetails;

public interface IMaxPrintDetailsDAO extends GenericDao<MaxPrintDetails, Long> {
	
	public List<Object[]>  getPrintDetailsByConstituencyId(Long constituencyId);
}
