package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.ICountryDAO;
import com.itgrids.cardprint.model.Country;

public class CountryDAO extends GenericDaoHibernate<Country, Long> implements ICountryDAO {

	public CountryDAO(){
		super(Country.class);
	}

}
