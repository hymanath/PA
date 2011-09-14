package com.itgrids.partyanalyst.dao;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.model.Country;

public class CountryDaoSample extends GenericDaoHibernate<Country, Long>
		implements ICountryDAO {

	public CountryDaoSample() {
		super(Country.class);
	}

	public Country findByCountryName(final String countryName) {
		
		return (Country) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(org.hibernate.Session session)
							throws HibernateException, SQLException {
						
						Criteria criteria = session.createCriteria(Country.class);
						
						criteria.add(Expression.eq("countryName", countryName));
						
						return criteria.uniqueResult();
						
					}
				});
	
	}

	public List<com.itgrids.partyanalyst.model.Country> findByCapital(
			Object capital) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<com.itgrids.partyanalyst.model.Country> findByCountryName(
			Object countryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<com.itgrids.partyanalyst.model.Country> findByIsoCode(
			Object isoCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<com.itgrids.partyanalyst.model.Country> findByProperty(
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public com.itgrids.partyanalyst.model.Country save(
			com.itgrids.partyanalyst.model.Country arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getCountryIdByCountryName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Object[]> getAllCountryDetails(){
		return null;
	}

}
