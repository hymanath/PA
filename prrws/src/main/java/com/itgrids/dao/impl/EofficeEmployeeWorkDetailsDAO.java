package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEofficeEmployeeWorkDetailsDAO;
import com.itgrids.model.EofficeEmployeeWorkDetails;

@Repository
public class EofficeEmployeeWorkDetailsDAO extends GenericDaoHibernate<EofficeEmployeeWorkDetails, Long> implements IEofficeEmployeeWorkDetailsDAO{

	public EofficeEmployeeWorkDetailsDAO() {
		super(EofficeEmployeeWorkDetails.class);
	}

	public List<Object[]> getEOfcDepartmentCunts(Date fromDate,Date toDate){
		StringBuilder sb = new  StringBuilder();
		sb.append("select model.departmentId,model.departmentName,model.fileCreated,model.fileReceived,model.opBalanceCount,model.firstCount,model.secondCount,"
				+ "model.thirdCount,model.fourthCount,model.fifthCount,model.filesForwarded,model.filesParked,model.filesClosed,model.employeeName,model.postName"
				+ " from EofficeEmployeeWorkDetails model"
				+ " where model.isDeleted = 'N'");
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.fromDate) = :fromDate and date(model.toDate) = :toDate");
		}
		
		Query query = getSession().createQuery(sb.toString());
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
}
