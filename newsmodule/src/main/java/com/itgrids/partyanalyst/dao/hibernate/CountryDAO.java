/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CountryColumnNames;
import com.itgrids.partyanalyst.model.Country;

/**
 * Implementation for <code>ICountryDAO</code> interface.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 * 
 */
public class CountryDAO extends GenericDaoHibernate<Country, Long> implements
		ICountryDAO {

	public CountryDAO() {
		super(Country.class);
	}

	/*	public List<Country> findByCapital(Object capital) {
		return findByProperty(CountryColumnNames.CAPITAL, capital);
	}

	public List<Country> findByCountryName(Object countryName) {
		return findByProperty(CountryColumnNames.COUNTRY_NAME, countryName);
	}

	public List<Country> findByIsoCode(Object isoCode) {
		return findByProperty(CountryColumnNames.ISO_CODE, isoCode);
	}

	@SuppressWarnings("unchecked")
	public List<Country> findByProperty(CountryColumnNames propertyName,
			Object value) {
		return getHibernateTemplate().find("from Country where " + propertyName.getValue() + "=?", value);
	}

	public List<Country> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getCountryIdByCountryName(String name) {
		return getHibernateTemplate().find("select model.countryId from Country model where model.countryName = ?",name);
	}
	
	public List<Object[]> getAllCountryDetails(){
		return getHibernateTemplate().find("select model.countryId,model.countryName  from Country model");
	}

*/}