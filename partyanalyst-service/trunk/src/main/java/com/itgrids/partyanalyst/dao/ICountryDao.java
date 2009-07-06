
package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.entity.Country;


public interface ICountryDao extends GenericDao<Country, Long>{
	
	Country findByCountryName(String countryName);
}