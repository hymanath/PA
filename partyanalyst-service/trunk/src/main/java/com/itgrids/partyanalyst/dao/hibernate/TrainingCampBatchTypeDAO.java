package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchTypeDAO;
import com.itgrids.partyanalyst.model.TrainingCampBatchType;

public class TrainingCampBatchTypeDAO extends GenericDaoHibernate<TrainingCampBatchType, Long> implements ITrainingCampBatchTypeDAO{
	public TrainingCampBatchTypeDAO() {
		super(TrainingCampBatchType.class);
	}
}
