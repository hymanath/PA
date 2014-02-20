package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IParlimentActionItemsDAO;
import com.itgrids.partyanalyst.model.ParlimentActionItems;

public class ParlimentActionItemsDAO extends GenericDaoHibernate<ParlimentActionItems, Long> implements IParlimentActionItemsDAO{

	public ParlimentActionItemsDAO() {
		super(ParlimentActionItems.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getParlimentActionItems(List<Long> pfbIds)
	{
		Query query = getSession().createQuery("select model.parlimentPoliticalFeedback.parlimentPoliticalFeedbackId , " +
				" model.actionItem ,model.source from ParlimentActionItems model where model.parlimentPoliticalFeedback.parlimentPoliticalFeedbackId in (:pfbIds)");
		query.setParameterList("pfbIds", pfbIds);
		return query.list();
	}
}
