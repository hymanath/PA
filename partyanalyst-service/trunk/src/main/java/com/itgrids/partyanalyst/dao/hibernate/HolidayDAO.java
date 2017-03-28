package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHolidayDAO;
import com.itgrids.partyanalyst.model.Holiday;

public class HolidayDAO extends GenericDaoHibernate<Holiday, Long> implements IHolidayDAO {
	public HolidayDAO(){
		super (Holiday.class);
	}
	public Long getHolidayCount(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select count(model.holidayId) from Holiday model where model.isHoliday='Y' and model.date between :fromDate and :toDate ");
		query.setDate("fromDate",fromDate);
		query.setDate("toDate",toDate);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getHolidayList(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select model.holidayId,model.date from Holiday model where model.isHoliday='Y' and model.date between :fromDate and :toDate ");
		query.setDate("fromDate",fromDate);
		query.setDate("toDate",toDate);
		return query.list();
	}

}
