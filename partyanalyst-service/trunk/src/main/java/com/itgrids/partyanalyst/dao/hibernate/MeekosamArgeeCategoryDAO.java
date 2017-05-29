package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamArgeeCategoryDAO;
import com.itgrids.partyanalyst.model.MeekosamArgeeCategory;

public class MeekosamArgeeCategoryDAO extends GenericDaoHibernate<MeekosamArgeeCategory, Long> implements IMeekosamArgeeCategoryDAO {

	public MeekosamArgeeCategoryDAO(){
		super(MeekosamArgeeCategory.class);
	}
	public List<Object[]> getmeekosamArgeeCategoryList(){
		Query query = getSession().createQuery(" select model.meekosamArgeeCateogoryId, model.meekosamArgeeCategory from MeekosamArgeeCategory model where model.isActive = 'Y'");
		return query.list();
	}
	
}
