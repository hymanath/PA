package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHolidayDAO;
import com.itgrids.partyanalyst.model.Holiday;

public class HolidayDAO extends GenericDaoHibernate<Holiday, Long> implements IHolidayDAO {
	public HolidayDAO(){
		super (Holiday.class);
	}
	public Long getHolidayCount(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select count(model.holidayId) from Holiday model where model.date between :fromDate and :toDate ");
		query.setDate("fromDate",fromDate);
		query.setDate("toDate",toDate);
		return (Long) query.uniqueResult();
	}

}
