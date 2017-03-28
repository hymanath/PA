package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallStatusDAO;
import com.itgrids.partyanalyst.model.CallStatus;

public class CallStatusDAO extends GenericDaoHibernate<CallStatus, Long> implements ICallStatusDAO{

	public CallStatusDAO() {
		super(CallStatus.class);
		
	}
public List<Object[]> getCallStatus(){
		
		Query query = getSession().createQuery("select model.callStatusId," +
				" model.status" +
				" from CallStatus model " +
				" where model.isDeleted='false' " +
				" order by model.orderNo  ");
		
		return query.list();
	}	
}
