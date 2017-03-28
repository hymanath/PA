package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionTypeMemberDAO;
import com.itgrids.partyanalyst.model.AlertActionTypeMember;

public class AlertActionTypeMemberDAO extends GenericDaoHibernate<AlertActionTypeMember, Long> implements
		IAlertActionTypeMemberDAO {

	public AlertActionTypeMemberDAO(){
		super(AlertActionTypeMember.class);
	}
}
