package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAbusedComments;
import com.itgrids.partyanalyst.model.AbusedComments;

public class AbusedCommentsDAO extends GenericDaoHibernate<AbusedComments,Long> implements IAbusedComments {
	
	public AbusedCommentsDAO() {
		super(AbusedComments.class);
	}

}
