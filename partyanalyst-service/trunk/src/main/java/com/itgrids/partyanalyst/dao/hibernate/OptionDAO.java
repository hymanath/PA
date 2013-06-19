package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IOptionDAO;
import com.itgrids.partyanalyst.model.Option;

public class OptionDAO extends GenericDaoHibernate<Option, Long> implements IOptionDAO {

	public OptionDAO() {
		super(Option.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Option> getSubOptionsByParentOptionId(Long parentOptionsId){
		return getHibernateTemplate().find("from Option model where model.parentOption.optionsId = ?",parentOptionsId);
	}
	
	public Long getSubOptionsCountParentOptionId(Long parentOptionsId){
		Query query = getSession().createQuery("select count(*) from Option model where model.parentOption.optionsId = :parentOptionsId");
		 
		query.setParameter("parentOptionsId", parentOptionsId);
		return (Long)query.uniqueResult();
	}
}
