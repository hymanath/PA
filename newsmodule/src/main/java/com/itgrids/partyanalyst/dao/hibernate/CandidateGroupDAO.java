package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateGroupDAO;
import com.itgrids.partyanalyst.model.CandidateGroup;

public class CandidateGroupDAO extends GenericDaoHibernate<CandidateGroup,Long> implements ICandidateGroupDAO {

	public CandidateGroupDAO(){
		super(CandidateGroup.class);
	}
}
