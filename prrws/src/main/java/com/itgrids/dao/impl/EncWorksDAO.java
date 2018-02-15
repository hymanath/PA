package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.model.EncWorks;
import com.itgrids.model.RwsWorkLocation;

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
		}else if(status.equalsIgnoreCase("Not Grounded")){
			sb.append(" ,model.groundedDate,model.completionDate,datediff(model.targetDate,current_date())");
		}
		
		sb.append(" ,model.agrementDate from EncWorks model ");
		if(status.equalsIgnoreCase("Grounded") || status.equalsIgnoreCase("Completed")){
			sb.append(" where model.workStatus <>'Not Grounded'");
		}
		
		if(status.equalsIgnoreCase("Grounded")){
			sb.append(" and model.workStatus ='Grounded' ");
		}else if(status.equalsIgnoreCase("Completed")){
			sb.append(" and model.workStatus ='Completed' ");
		}else if(status.equalsIgnoreCase("Not Grounded")){
			sb.append(" where model.workStatus ='Not Grounded' ");
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

	@Override
	public EncWorks findOneByworkId(Long workId) {
		
		Query query= getSession().createQuery("from EncWorks where workId=:workId");
		query.setParameter("workId", workId);
		RwsWorkLocation workLocation = (RwsWorkLocation) query.uniqueResult();
		return (EncWorks) query.uniqueResult();
	}

	@Override
	public List<Object[]> getExceedWorksBystatus(Date date,String type) {
		Query query =null;
		if(type != null && type.equalsIgnoreCase("ongoing")){
			
			query= getSession().createSQLQuery("select count(work_id) as count,work_status as status from enc_works where target_date >=:date and work_status in ('Not Grounded','Grounded') group by work_status")
					.addScalar("count")
					.addScalar("status");
			query.setParameter("date", date);
		}else{
			query= getSession().createSQLQuery("select count(work_id) as count,work_status as status from enc_works where target_date >=completion_date and work_status='Completed'")
					.addScalar("count")
					.addScalar("status");
		}
		
		return query.list();
	}
	

}
