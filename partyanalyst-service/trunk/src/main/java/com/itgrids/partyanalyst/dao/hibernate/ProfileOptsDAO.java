package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProfileOptsDAO;
import com.itgrids.partyanalyst.model.ProfileOpts;

public class ProfileOptsDAO extends GenericDaoHibernate<ProfileOpts, Long> implements IProfileOptsDAO{

	public ProfileOptsDAO() {
		super(ProfileOpts.class);
	}

	public List getAllProfileOpts(){
		return getHibernateTemplate().find("select model.profileOptsId, model.name, model.description from ProfileOpts model ");
	}
	
}
