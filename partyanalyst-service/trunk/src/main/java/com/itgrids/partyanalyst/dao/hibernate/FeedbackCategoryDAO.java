package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFeedbackCategoryDAO;
import com.itgrids.partyanalyst.model.FeedbackCategory;

public class FeedbackCategoryDAO extends GenericDaoHibernate<FeedbackCategory, Long> implements IFeedbackCategoryDAO{

	public FeedbackCategoryDAO() {
		super(FeedbackCategory.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getAllNamesOfIds(List<Long> ids){
		Query query = getSession().createQuery(" select model.feedbackCategoryId,model.categoryName " +
				" from FeedbackCategory model where model.feedbackCategoryId in (:ids)");
		query.setParameterList("ids", ids);
		return query.list();
	}
}
