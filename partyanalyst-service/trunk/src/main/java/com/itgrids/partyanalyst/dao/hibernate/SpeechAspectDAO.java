package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpeechAspectDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.SpeechAspect;

public class SpeechAspectDAO extends GenericDaoHibernate<SpeechAspect, Long> implements ISpeechAspectDAO{

	public SpeechAspectDAO() {
		super(SpeechAspect.class);
	}
}
