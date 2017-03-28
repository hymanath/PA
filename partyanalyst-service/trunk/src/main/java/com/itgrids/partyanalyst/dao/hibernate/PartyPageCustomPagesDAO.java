package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyPageCustomPagesDAO;
import com.itgrids.partyanalyst.model.PartyPageCustomPages;

public class PartyPageCustomPagesDAO extends GenericDaoHibernate<PartyPageCustomPages,Long> implements IPartyPageCustomPagesDAO{

	public PartyPageCustomPagesDAO()
	{
		super(PartyPageCustomPages.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomPagesOfAPartyPage(Long partyId)
	{
		return getHibernateTemplate().find("select model.customPage.name,model.customPage.customPageType.customPageType from PartyPageCustomPages model where " +
				"model.party.partyId = ? order by model.customPage.customPageType.orderNo",partyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyPageCustomPages> partyExistsOrNot(Long pageId,String customPageName)
	{
		Object[] params = {pageId,customPageName};
		return getHibernateTemplate().find("select model.customPage.customPageId from PartyPageCustomPages model where model.party.partyId = ? and model.customPage.name = ?",params);
	}
	public List<Object[]> getPartyCustomPage(Long pageId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select  model.customPage.name,model.customPage.customPageType.customPageType,model.customPage.customPageType.customPageTypeId from PartyPageCustomPages model "
				+ " where model.party.partyId =:pageId");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("pageId", pageId);
		return queryObject.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> getPartyPageId(Long pageId)
	{
		return getHibernateTemplate().find("select model.customPage.customPageId from PartyPageCustomPages model where model.party.partyId = ? ",pageId);
	}
}
