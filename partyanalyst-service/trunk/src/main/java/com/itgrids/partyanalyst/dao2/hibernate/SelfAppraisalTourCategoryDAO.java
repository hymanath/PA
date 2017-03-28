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
	
	public List<Object[]> getAllTourCategorys(Long cadreId,Long designationId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("SELECT distinct model1.selfAppraisalTourCategory.selfAppraisalTourCategoryId,model1.selfAppraisalTourCategory.tourCategory " +
				"  FROM SelfAppraisalCandidate model,SelfAppraisalDesignationTarget model1  " +
				"  WHERE model.selfAppraisalDesignationId = model1.selfAppraisalDesignationId " + 
				" and model.isActive ='Y' and model1.isActive='Y' " +
				" ");
		if(cadreId != null && cadreId.longValue()>0L)
			queryStr.append(" and model.tdpCadreId=:cadreId  ");
		if(designationId != null && designationId.longValue()>0L)
			queryStr.append(" and model.selfAppraisalDesignationId = :designationId ");
		
		Query query = getSession().createQuery(queryStr.toString()+" order by model1.selfAppraisalTourCategory.tourCategory " );
		
		if(cadreId != null && cadreId.longValue()>0L)
			query.setParameter("cadreId", cadreId);
		if(designationId != null && designationId.longValue()>0L)
			query.setParameter("designationId", designationId);
		
		return query.list();
	}
	
}
