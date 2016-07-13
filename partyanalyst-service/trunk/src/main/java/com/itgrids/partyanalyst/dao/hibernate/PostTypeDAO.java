package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPostTypeDAO;
import com.itgrids.partyanalyst.model.PostType;

public class PostTypeDAO extends GenericDaoHibernate<PostType, Long> implements IPostTypeDAO{

	public PostTypeDAO() {
		super(PostType.class);
		
	}

}
