package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.MeekosamGrievanceReferDetails;

public class MeekosamGrievanceReferDetailsDAO extends GenericDaoHibernate<MeekosamGrievanceReferDetails, Long>{

	public MeekosamGrievanceReferDetailsDAO() {
		super(MeekosamGrievanceReferDetails.class);
		
	}

}
