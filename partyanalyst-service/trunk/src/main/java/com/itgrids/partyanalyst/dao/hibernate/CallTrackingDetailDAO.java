package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallTrackingDetailDAO;
import com.itgrids.partyanalyst.model.CallTrackingDetail;

public class CallTrackingDetailDAO extends GenericDaoHibernate<CallTrackingDetail, Long> implements ICallTrackingDetailDAO{

	public CallTrackingDetailDAO() {
		super(CallTrackingDetail.class);
	}
	public List<CallTrackingDetail> getCallTrackingDetailByCallTrackingProblemId(Long problemId){
		return getHibernateTemplate().find("select model from CallTrackingDetail model where model.callTrackingProblem.problemId = ? ",problemId);
	}
	
}
