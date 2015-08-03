package com.itgrids.partyanalyst.dao.hibernate;
import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.partyanalyst.dao.ITrainingCampDistrictDAO;
import com.itgrids.partyanalyst.model.TrainingCampDistrict;

public class TrainingCampDistrictDAO extends GenericDaoHibernate<TrainingCampDistrict, Long> implements ITrainingCampDistrictDAO{

	public TrainingCampDistrictDAO() {
		super(TrainingCampDistrict.class);
		// TODO Auto-generated constructor stub
	}

}
