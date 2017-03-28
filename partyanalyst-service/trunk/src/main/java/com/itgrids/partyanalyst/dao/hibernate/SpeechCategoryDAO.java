package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpeechCategoryDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.SpeechCategory;

public class SpeechCategoryDAO extends GenericDaoHibernate<SpeechCategory, Long> implements ISpeechCategoryDAO{

	public SpeechCategoryDAO() {
		super(SpeechCategory.class);
	}

}
