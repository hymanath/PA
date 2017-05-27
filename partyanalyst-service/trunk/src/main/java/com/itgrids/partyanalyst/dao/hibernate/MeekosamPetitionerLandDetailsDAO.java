package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamPetitionerLandDetailsDAO;
import com.itgrids.partyanalyst.model.MeekosamPetitionerLandDetails;

public class MeekosamPetitionerLandDetailsDAO extends
		GenericDaoHibernate<MeekosamPetitionerLandDetails, Long> implements
		IMeekosamPetitionerLandDetailsDAO {
	public MeekosamPetitionerLandDetailsDAO() {
		super(MeekosamPetitionerLandDetails.class);
		
	}
}
