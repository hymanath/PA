package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;



import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IBiometricHolidayDAO;
import com.itgrids.model.BiometricHoliday;

@Repository
public class BiometricHolidayDAO extends GenericDaoHibernate<BiometricHoliday, Long> implements IBiometricHolidayDAO {

	public BiometricHolidayDAO() {
		super(BiometricHoliday.class);
	}
	
	public List<Object[]> getBioMetricHolidays(Date fromDate,Date toDate) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select model.date,model.holidayType,model.holidayName from BiometricHoliday model where model.isDeleted='N' and date(model.date) between :fromDate and :toDate ");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("fromDate", fromDate);
		 query.setParameter("toDate", toDate);
		 return query.list();
	}
}
