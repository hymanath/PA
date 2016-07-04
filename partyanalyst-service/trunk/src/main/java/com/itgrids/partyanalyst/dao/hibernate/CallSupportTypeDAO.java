package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallSupportTypeDAO;
import com.itgrids.partyanalyst.model.CallSupportType;

public class CallSupportTypeDAO extends GenericDaoHibernate<CallSupportType, Long> implements ICallSupportTypeDAO {
	public CallSupportTypeDAO() {
		super(CallSupportType.class);

	}
public List<Object[]> getCallSupportType(){
		
		Query query = getSession().createQuery(" select  model.callSupportTypeId," +
				" model.supportType " +
				" from CallSupportType model " +
				" where model.isDeleted='false' " +
				" order by model.orderNo");
		
		return query.list();
	}	
}
