package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtProposalCategoryDAO;
import com.itgrids.partyanalyst.model.GovtProposalCategory;

public class GovtProposalCategoryDAO extends GenericDaoHibernate<GovtProposalCategory, Long> implements IGovtProposalCategoryDAO{

	public GovtProposalCategoryDAO() {
		super(GovtProposalCategory.class);
	}

	public List<Object[]> getAllProposalCategories(){
		Query query = getSession().createQuery("select model.govtProposalCategoryId," +
				" model.category " +
				" from GovtProposalCategory model" );
		return query.list();
	}
}
