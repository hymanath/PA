package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEofficeEmployeeWorkDetailsDAO;
import com.itgrids.model.EofficeEmployeeWorkDetails;
import com.itgrids.utils.DateUtilService;

@Repository
public class EofficeEmployeeWorkDetailsDAO extends GenericDaoHibernate<EofficeEmployeeWorkDetails, Long> implements IEofficeEmployeeWorkDetailsDAO{

	public EofficeEmployeeWorkDetailsDAO() {
		super(EofficeEmployeeWorkDetails.class);
	}

	public List<Object[]> getEOfcDepartmentCunts(Date fromDate,Date toDate,List<Long> deptIds){
		StringBuilder sb = new  StringBuilder();
		sb.append("select model.departmentId,model.departmentName,model.fileCreated,model.fileReceived,model.opBalanceCount,"
				+ " model.firstCount,model.secondCount,model.thirdCount,model.fourthCount,model.fifthCount,model.filesForwarded,model.filesParked,model.filesClosed,"
				+ " model.employeeName,model.postName,model.insertedTime,model.orgName"
				+ " from EofficeEmployeeWorkDetails model"
				+ " where model.isDeleted = 'N'");
		if(deptIds != null && !deptIds.isEmpty()){
			sb.append(" and model.departmentId in (:deptIds)");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.fromDate) = :fromDate and date(model.toDate) = :toDate");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(deptIds != null && !deptIds.isEmpty())
			query.setParameterList("deptIds", deptIds);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	public Long deleteRecrdsFrmTable(){
		int query = getSession().createQuery("delete from EofficeEmployeeWorkDetails").executeUpdate();
		
		return (long) query;
	}
	
	public int updateoldData(){
		Query query = getSession().createQuery("update EofficeEmployeeWorkDetails model set model.isDeleted = 'Y' where model.isDeleted = 'N'");
		return query.executeUpdate();
	}
	
	public List<Object[]> geteOfficeDataExists(){
		Query query = getSession().createQuery("select count(model.eofficeEmployeeWorkDetailsId),model.insertedTime"
										+ " from EofficeEmployeeWorkDetails model where model.isDeleted = 'N'"
										+ " and date(model.insertedTime) = :today");
		query.setParameter("today", new DateUtilService().getCurrentDateAndTime());
		return query.list();
	}
}
