package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateCustomPage(Long pageId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.customPage.name,model.customPage.customPageType.customPageType,model.customPage.customPageType.customPageTypeId from CandidatePageCustomPages model "
				+ " where model.candidate.candidateId =:pageId");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("pageId", pageId);
		
		return queryObject.list(); 
	}
	@SuppressWarnings("unchecked")
	public List<CandidatePageCustomPages> candidateExistsOrNot(Long pageId,String customPageName)
	{ 
		Object[] params = {pageId,customPageName};
		return getHibernateTemplate().find("select model.customPage.customPageId from CandidatePageCustomPages model where model.candidate.candidateId = ? and model.customPage.name = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCustomPageId(Long pageId)
	{
		return getHibernateTemplate().find("select model.customPage.customPageId from CandidatePageCustomPages model where model.candidate.candidateId = ? ",pageId);
	}
	

}
