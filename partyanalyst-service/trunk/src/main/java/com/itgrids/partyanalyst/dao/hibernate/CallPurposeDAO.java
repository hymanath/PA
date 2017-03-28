package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallPurposeDAO;
import com.itgrids.partyanalyst.model.CallPurpose;
import com.itgrids.partyanalyst.utils.IConstants;

public class CallPurposeDAO extends GenericDaoHibernate<CallPurpose, Long> implements ICallPurposeDAO {
	public CallPurposeDAO() {
		super(CallPurpose.class);

	}
public List<Object[]> getAllCallPurposes(){
		
		Query query = getSession().createQuery("select model.callPurposeId," +
				" model.purpose " +
				" from CallPurpose model" +
				" where model.isDeleted='false'" +
				" order by model.orderNo");
		return query.list();
	}	
}
