package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IUserApprovalDetailsDAO;
import com.itgrids.partyanalyst.model.UserApprovalDetails;


@SuppressWarnings("unused")
public class UserApprovalDetailsDAO extends GenericDaoHibernate<UserApprovalDetails, Long> implements IUserApprovalDetailsDAO  {

	public UserApprovalDetailsDAO() {
		super(UserApprovalDetails.class);		
	}	
	
	@SuppressWarnings("unchecked")
	public List findUserApprovalStatusbetweendates(Date fromDate, Date toDate){
		
		StringBuffer query=new StringBuffer("select model.approvalDetails.reason, model.approvalDetails.isApproved, model.approvalDetails.postedDate, model.user.firstName, model.user.lastName,model.approvalDetails.approvalDetailsId from");
		query.append(" UserApprovalDetails model where date(model.approvalDetails.postedDate) between :start and :end and");
		query.append(" model.approvalDetails.isAdminApproved is null");
		Query queryObject=getSession().createQuery(query.toString());
		queryObject.setParameter("start", fromDate);
		queryObject.setParameter("end", toDate);
		return queryObject.list();
	
	
	}
	
	}

