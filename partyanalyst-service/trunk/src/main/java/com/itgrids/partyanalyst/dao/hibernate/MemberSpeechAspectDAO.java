package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberSpeechAspectDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.MemberSpeechAspect;

public class MemberSpeechAspectDAO extends GenericDaoHibernate<MemberSpeechAspect, Long> implements IMemberSpeechAspectDAO{

	public MemberSpeechAspectDAO() {
		super(MemberSpeechAspect.class);
		
	}

}
