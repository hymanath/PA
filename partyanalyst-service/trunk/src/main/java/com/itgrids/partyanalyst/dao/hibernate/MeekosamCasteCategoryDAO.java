package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamCasteCategoryDAO;
import com.itgrids.partyanalyst.model.MeekosamCasteCategory;

public class MeekosamCasteCategoryDAO extends GenericDaoHibernate<MeekosamCasteCategory, Long> implements IMeekosamCasteCategoryDAO {

	public MeekosamCasteCategoryDAO(){
		super(MeekosamCasteCategory.class);
	}
	public List<Object[]> getMeekosamCasteCategoryList(){
		Query query = getSession().createQuery(" select model.meekosamCasteCategoryId, model.meekosamCasteCategory from MeekosamCasteCategory model where model.isActive = 'Y'");
		return query.list();
	}
	
}
