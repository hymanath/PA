package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerificationCommentsDAO;
import com.itgrids.partyanalyst.model.VerificationComments;

public class VerificationCommentsDAO extends GenericDaoHibernate<VerificationComments, Long> implements IVerificationCommentsDAO {

	public VerificationCommentsDAO(){
		super(VerificationComments.class);
	}

}
