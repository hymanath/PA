package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalTourCategoryDAO;
import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.SelfAppraisalTourCategory;

public class SelfAppraisalTourCategoryDAO extends GenericDaoHibernate<SelfAppraisalTourCategory, Long> implements ISelfAppraisalTourCategoryDAO {
		
	public SelfAppraisalTourCategoryDAO() {
		super(SelfAppraisalTourCategory.class);
	}
	
	public List<Object[]> getAllTourCategorys(){
		
		Query query = getSession().createQuery(" SELECT model.selfAppraisalTourCategoryId,model.tourCategory " +
				"  FROM SelfAppraisalTourCategory model  " +
				"  WHERE model.isDeleted ='N' ");
		
		return query.list();
	}
	
}
