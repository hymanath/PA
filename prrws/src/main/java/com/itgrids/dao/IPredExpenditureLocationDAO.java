package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PredExpenditureLocation;

public interface IPredExpenditureLocationDAO extends GenericDao<PredExpenditureLocation, Long> {

	public List<Object[]> getLocationWisePrExpenditureDtls(String locationType,String filterType, List<Long> locationIds);
}
