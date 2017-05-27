package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamArgeeCategoryDAO;
import com.itgrids.partyanalyst.model.MeekosamArgeeCategory;

public class MeekosamArgeeCategoryDAO extends GenericDaoHibernate<MeekosamArgeeCategory, Long> implements IMeekosamArgeeCategoryDAO {

	public MeekosamArgeeCategoryDAO(){
		super(MeekosamArgeeCategory.class);
	}
	
}
