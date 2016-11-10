package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.model.TrainingCampDetailsInfo;

public class TrainingCampDetailsInfoDAO extends GenericDaoHibernate<TrainingCampDetailsInfo, Long> implements
		ITrainingCampDetailsInfoDAO {
	public TrainingCampDetailsInfoDAO() {
		super(TrainingCampDetailsInfo.class);
	}
}
