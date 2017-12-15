package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PrPanchayat;

public interface IPrPanchayatDAO extends GenericDao<PrPanchayat, Long> {
	
	public List<Object[]> getpanchayatsFrTehsil(String tehsilId);

}
