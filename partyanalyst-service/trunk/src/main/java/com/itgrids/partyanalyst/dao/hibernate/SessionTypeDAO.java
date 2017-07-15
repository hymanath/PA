package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISessionTypeDAO;
import com.itgrids.partyanalyst.model.SessionType;

public class SessionTypeDAO extends GenericDaoHibernate<SessionType, Long> implements ISessionTypeDAO{

	public SessionTypeDAO() {
		super(SessionType.class);
		
	}
   public List<Object[]> getAllSessionType(){
	     StringBuilder queryStr = new StringBuilder();
	     queryStr.append(" select model.sessionTypeId,model.type from SessionType model ");
	     Query query = getSession().createQuery(queryStr.toString());
	     return query.list();
   }
   public List<Object[]> getPartyMeetingSession(Long sessionTypeId){
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select model.startTime,model.endTime,model.lateTime from SessionType model where sessionTypeId:=sessionTypeId ");
	     Query query = getSession().createQuery(queryStr.toString());
	     query.setParameter("sessionTypeId",sessionTypeId);
	     return query.list();
	   
   }
}

