package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidatePageCustomPagesDAO;
import com.itgrids.partyanalyst.model.CandidatePageCustomPages;

public class CandidatePageCustomPagesDAO extends GenericDaoHibernate<CandidatePageCustomPages,Long> implements ICandidatePageCustomPagesDAO{

	public CandidatePageCustomPagesDAO()
	{
		super(CandidatePageCustomPages.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomPagesOfACandidatePage(Long candidateId)
	{
		return getHibernateTemplate().find("select model.customPage.name,model.customPage.customPageType.customPageType from CandidatePageCustomPages model where " +
				"model.candidate.candidateId = ? order by model.customPage.customPageType.orderNo",candidateId);
	}

}
