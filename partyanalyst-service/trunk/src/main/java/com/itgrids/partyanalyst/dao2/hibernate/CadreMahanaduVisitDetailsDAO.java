package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreMahanaduVisitDetailsDAO;
import com.itgrids.partyanalyst.model.CadreMahanaduVisitDetails;

public class CadreMahanaduVisitDetailsDAO extends GenericDaoHibernate<CadreMahanaduVisitDetails, Long>   implements
		ICadreMahanaduVisitDetailsDAO {
	public CadreMahanaduVisitDetailsDAO( ) {
		super(CadreMahanaduVisitDetails.class);
		
	}
	
	public List<Object[]> getLatestInfoRecord(Date currentDate,Long parentEventId){
		//0id,1date
		Query query = getSession().createQuery("select model.cadreMahanaduVisitInfoId,model.insertedTime from CadreMahanaduVisitInfo model " +
				"                               where model.cadreMahanaduVisitInfoId " +
				" in ( select max(model1.cadreMahanaduVisitInfoId) from CadreMahanaduVisitInfo model1 " +
				"      where date(model1.insertedTime) = :currentDate and model1.parentEventId=:parentEventId" +
				"     )"
				);
		query.setDate("currentDate", currentDate);
		query.setParameter("parentEventId", parentEventId);
		return query.list();
	}
	public List<Object[]> getLatestRecords(Long cadreMahanaduVisitInfoId){
		//0type 1count
		Query query = getSession().createQuery("select model.type,model.count,model.inviteCount from CadreMahanaduVisitDetails model where " +
				"model.cadreMahanaduVisitInfo.cadreMahanaduVisitInfoId =:cadreMahanaduVisitInfoId");
		query.setParameter("cadreMahanaduVisitInfoId", cadreMahanaduVisitInfoId);
		return query.list();
	}
}
