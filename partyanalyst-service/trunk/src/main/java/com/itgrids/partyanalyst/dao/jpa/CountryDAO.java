/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.columns.enums.CountryColumnNames;
import com.itgrids.partyanalyst.model.Country;

/**
 * Implementation for <code>ICountryDAO</code> interface.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 *
 */
public class CountryDAO extends GenericDaoJpa<Country, Long> implements
		ICountryDAO {

	public CountryDAO() {
		super(Country.class);
	}

	public List<Country> findByCapital(Object capital) {
		return findByProperty(CountryColumnNames.CAPITAL, capital);
	}

	public List<Country> findByCountryName(Object countryName) {
		return findByProperty(CountryColumnNames.COUNTRY_NAME, countryName);
	}

	public List<Country> findByIsoCode(Object isoCode) {
		return findByProperty(CountryColumnNames.ISO_CODE, isoCode);
	}

	@SuppressWarnings("unchecked")
	public List<Country> findByProperty(CountryColumnNames propertyName, Object value) {
		final String queryString = "select model from Country model where model."
			+ propertyName.getValue() + "= :propertyValue";
        Query q = super.entityManager.createQuery(queryString);
        q.setParameter(1, value);
        return q.getResultList();
	}

}