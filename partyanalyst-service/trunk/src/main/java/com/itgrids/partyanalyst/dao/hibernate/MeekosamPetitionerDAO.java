package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.model.MeekosamPetitioner;

public class MeekosamPetitionerDAO extends
		GenericDaoHibernate<MeekosamPetitioner, Long> implements
		IMeekosamPetitionerDAO {

	public MeekosamPetitionerDAO() {
		super(MeekosamPetitioner.class);
		
	}
}
