package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampStaffDAO;
import com.itgrids.partyanalyst.model.TrainingCamp;
import com.itgrids.partyanalyst.model.TrainingCampStaff;

public class TrainingCampStaffDAO extends GenericDaoHibernate<TrainingCampStaff, Long> implements ITrainingCampStaffDAO{

	public TrainingCampStaffDAO() {
		super(TrainingCampStaff.class);
	}
    
	
}
