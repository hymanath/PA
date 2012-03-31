package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
	
}
