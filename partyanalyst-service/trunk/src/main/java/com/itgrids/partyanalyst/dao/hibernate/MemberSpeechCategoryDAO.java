package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberSpeechCategoryDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.MemberSpeechCategory;

public class MemberSpeechCategoryDAO extends GenericDaoHibernate<MemberSpeechCategory, Long> implements IMemberSpeechCategoryDAO{

	public MemberSpeechCategoryDAO() {
		super(MemberSpeechCategory.class);
	}

}
