package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportProgramDAO;
import com.itgrids.partyanalyst.model.CustomReportProgram;

public class CustomReportProgramDAO extends GenericDaoHibernate<CustomReportProgram, Long> implements ICustomReportProgramDAO {

	public CustomReportProgramDAO(){
		super(CustomReportProgram.class);
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomReportPogram(Date fromDate,Date toDate){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("select model.customReportProgramId,model.programName from CustomReportProgram model where ");
		
		sb.append(" model.isDeleted = 'N' ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.lastDate) between :fromDate and :toDate ");
		}
		
		Query query=getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate);
			query.setDate("toDate",toDate);
		}
		return query.list();
	}	
}
