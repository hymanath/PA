package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpeechAspectDAO;
import com.itgrids.partyanalyst.model.SpeechAspect;

public class SpeechAspectDAO extends GenericDaoHibernate<SpeechAspect, Long> implements ISpeechAspectDAO{

	public SpeechAspectDAO() {
		super(SpeechAspect.class);
	}
	
	public List<Object[]> getAllSpeechAspectNames(){
		Query query = getSession().createQuery("select model.speechAspectId," +
				" model.aspect " +
				" from SpeechAspect model ");
		
		return query.list();
	}
}
