package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;

public class CadreParticipatedTrainingCampsDAO extends GenericDaoHibernate<CadreParticipatedTrainingCamps, Long> implements ICadreParticipatedTrainingCampsDAO {

	public CadreParticipatedTrainingCampsDAO() {
		super(CadreParticipatedTrainingCamps.class);		
	}

}
