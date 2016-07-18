package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.model.NominatedPostMember;

public class NominatedPostMemberDAO extends GenericDaoHibernate<NominatedPostMember, Long> implements INominatedPostMemberDAO{

	public NominatedPostMemberDAO() {
		super(NominatedPostMember.class);
	}

	public List<Object[]> getNominatedPostsLevelWiseDeptAndBoard(){
		
		return null;
	}
}
