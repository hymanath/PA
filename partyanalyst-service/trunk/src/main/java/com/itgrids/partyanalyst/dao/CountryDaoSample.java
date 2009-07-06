package com.itgrids.partyanalyst.dao;

import java.sql.SQLException;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.entity.Country;

public class CountryDaoSample extends GenericDaoHibernate<Country, Long>
		implements ICountryDao {

	public CountryDaoSample(Class<Country> country) {
		super(country);
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

}
