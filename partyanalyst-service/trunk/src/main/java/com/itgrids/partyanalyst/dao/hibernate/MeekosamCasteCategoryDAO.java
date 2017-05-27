package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamCasteCategoryDAO;
import com.itgrids.partyanalyst.model.MeekosamCasteCategory;

public class MeekosamCasteCategoryDAO extends GenericDaoHibernate<MeekosamCasteCategory, Long> implements IMeekosamCasteCategoryDAO {

	public MeekosamCasteCategoryDAO(){
		super(MeekosamCasteCategory.class);
	}
	
}
