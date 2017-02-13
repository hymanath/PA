package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertDepartmentStatusDAO extends GenericDaoHibernate<AlertDepartmentStatus, Long> implements IAlertDepartmentStatusDAO{

	public AlertDepartmentStatusDAO() {
		super(AlertDepartmentStatus.class);
		
	}
	public List<Object[]> getAllStatus(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertDepartmentStatus.alertStatus.alertStatusId, " +
						" alertDepartmentStatus.alertStatus.alertStatus " +
						" from " +
						" AlertDepartmentStatus alertDepartmentStatus " +
						" where " +
						" alertDepartmentStatus.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		//queryStr.append(" and alertDepartmentStatus.govtDepartment.govtDepartmentId in (:govtDepartmentId)");
		
		queryStr.append(" order by alertDepartmentStatus.alertStatus.statusOrder ");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	public List<Object[]> getAllStatusForDepartment(Long govtDepartmentId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertDepartmentStatus.alertStatus.alertStatusId, " +
						" alertDepartmentStatus.alertStatus.alertStatus " +
						" from " +
						" AlertDepartmentStatus alertDepartmentStatus " +
						" where " +
						" alertDepartmentStatus.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		queryStr.append(" and alertDepartmentStatus.govtDepartment.govtDepartmentId =:govtDepartmentId ");
   public List<Object[]> getAlertStatusByAlertType(Long alertTypeId){
	     StringBuilder queryStr = new StringBuilder();
	     queryStr.append(" select distinct model.alertStatus.alertStatusId,model.alertStatus.alertStatus from AlertDepartmentStatus model " +
	     				"  where model.alertType.alertTypeId in (:alertTypeId)  ");
	     Query query = getSession().createQuery(queryStr.toString());
	      query.setParameter("alertTypeId", alertTypeId);
	      return query.list();
	    
   }
}
