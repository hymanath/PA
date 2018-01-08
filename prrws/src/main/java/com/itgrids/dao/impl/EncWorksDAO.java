package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.EncWorks;

@Repository
public class EncWorksDAO extends GenericDaoHibernate<EncWorks, Long>  implements IEncWorksDAO {

	public EncWorksDAO()
	{
		super(EncWorks.class);
	}

	@Override
	public List<Long> getAllDistinctWorkIds() {
		Query query= getSession().createQuery("select distinct model.workId from EncWorks model ");
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate, String status) {
		// 0-workid, 1-workName,2-schemeId,3-schemeName, 4-agrementAmount,5-mandalId,6-mandalName,7-districtId,8-districtName,9-constituencyId,10-constituencyname
		//11-targetDate,12-status,13-groundedDate 14-completionDate, 15-no of days
		StringBuilder sb = new StringBuilder();
		sb.append("select model.workId,model.workName,model.schemeId, model.schemeName,model.agreementAmount,model.mandalId,model.mandalName,model.districtId," +
				" model.ditrictName,model.assemblyId,model.assemblyName,model.targetDate, model.workStatus");
		if(status.equalsIgnoreCase("Grounded")){
			sb.append(" ,model.groundedDate,model.completionDate,datediff(model.targetDate,current_date())");
		}else if(status.equalsIgnoreCase("Completed")){
			sb.append(" ,model.groundedDate,model.completionDate,datediff(model.targetDate,model.completionDate)");
		}
		sb.append(" from EncWorks model  where model.workStatus <>'Not Grounded'");
		if(status.equalsIgnoreCase("Grounded")){
			sb.append(" and model.workStatus ='Grounded' ");
		}else if(status.equalsIgnoreCase("Completed")){
			sb.append(" and model.workStatus ='Completed' ");
		}
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.adminSanctionDate between :fromDate and :toDate ");
		}
		Query query= getSession().createQuery(sb.toString());
		
		if(fromDate!= null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	

}
