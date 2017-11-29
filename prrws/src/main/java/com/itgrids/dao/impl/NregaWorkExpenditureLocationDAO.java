package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaWorkExpenditureLocationDAO;
import com.itgrids.model.NregaWorkExpenditureLocation;

@Repository
public class NregaWorkExpenditureLocationDAO extends GenericDaoHibernate<NregaWorkExpenditureLocation, Long> implements INregaWorkExpenditureLocationDAO{

	public NregaWorkExpenditureLocationDAO() {
		super(NregaWorkExpenditureLocation.class);
		
	}

	public List<Object[]> getWorkWiseExpenditureOverviewInConstituency(Long constituencyId,List<Long> financialYearIds,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.nregaWorkExpenditure.totalxpenditure),"
					+ " sum(model.nregaWorkExpenditure.groundedWorks),"
					+ " sum(model.nregaWorkExpenditure.inprogressWorks),"
					+ " sum(model.nregaWorkExpenditure.completedWorks),"
					+ " sum(model.nregaWorkExpenditure.materialExpenditure)"
					+ " from NregaWorkExpenditureLocation model"
					+ " where model.isDeleted = 'N' and model.nregaWorkExpenditure.isDeleted = 'N'");
		
		if(type != null && type.trim().equalsIgnoreCase("district") && constituencyId != null && constituencyId.longValue() > 0L)
			sb.append(" and model.locationAddress.districtId = :constituencyId");
		else if(type != null && type.trim().equalsIgnoreCase("constituency") && constituencyId != null && constituencyId.longValue() > 0L)
			sb.append(" and model.locationAddress.constituencyId = :constituencyId");
		if(financialYearIds != null && !financialYearIds.isEmpty())
			sb.append(" and model.nregaWorkExpenditure.nregaFinancialYear.nregaFinancialYearId in (:financialYearIds)");
		//sb.append(" group by model.nregaWorkExpenditure.nregaWorkType.nregaWorkTypeId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(constituencyId != null && constituencyId.longValue() > 0L)
			query.setParameter("constituencyId", constituencyId);
		if(financialYearIds != null && !financialYearIds.isEmpty())
			query.setParameterList("financialYearIds", financialYearIds);
		
		return query.list();
	}
	
	public List<Object[]> getWorkWiseExpenditureDetailsInConstituency(Long constituencyId,List<Long> financialYearIds,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nregaWorkExpenditure.nregaWorkType.nregaWorkTypeId,"
					+ " model.nregaWorkExpenditure.nregaWorkType.workType,"
					+ " count(model.nregaWorkExpenditureLocationId),"
					+ " sum(model.nregaWorkExpenditure.wageExpenditure),"
					+ " sum(model.nregaWorkExpenditure.materialExpenditure),"
					+ " sum(model.nregaWorkExpenditure.totalxpenditure),"
					+ " sum(model.nregaWorkExpenditure.groundedWorks),"
					+ " sum(model.nregaWorkExpenditure.inprogressWorks),"
					+ " sum(model.nregaWorkExpenditure.completedWorks)"
					+ " from NregaWorkExpenditureLocation model"
					+ " where model.isDeleted = 'N' and model.nregaWorkExpenditure.isDeleted = 'N'");
		
		if(type != null && type.trim().equalsIgnoreCase("district") && constituencyId != null && constituencyId.longValue() > 0L)
			sb.append(" and model.locationAddress.districtId = :constituencyId");
		else if(type != null && type.trim().equalsIgnoreCase("constituency") && constituencyId != null && constituencyId.longValue() > 0L)
			sb.append(" and model.locationAddress.constituencyId = :constituencyId");
		if(financialYearIds != null && !financialYearIds.isEmpty())
			sb.append(" and model.nregaWorkExpenditure.nregaFinancialYear.nregaFinancialYearId in (:financialYearIds)");
		sb.append(" group by model.nregaWorkExpenditure.nregaWorkType.nregaWorkTypeId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(constituencyId != null && constituencyId.longValue() > 0L)
			query.setParameter("constituencyId", constituencyId);
		if(financialYearIds != null && !financialYearIds.isEmpty())
			query.setParameterList("financialYearIds", financialYearIds);
		
		return query.list();
	}
	
	public List<Object[]> getProgramExpenditureDetailsInConstituency(Long constituencyId,List<Long> financialYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nregaWorkExpenditure.nregaWorkType.nregaProgram.nregaProgramId,"
					+ " model.nregaWorkExpenditure.nregaWorkType.nregaProgram.programName,"
					+ " count(model.nregaWorkExpenditure.nregaWorkType.nregaWorkTypeId),"
					+ " count(model.nregaWorkExpenditureLocationId),"
					+ " sum(model.nregaWorkExpenditure.wageExpenditure),"
					+ " sum(model.nregaWorkExpenditure.materialExpenditure),"
					+ " sum(model.nregaWorkExpenditure.totalxpenditure),"
					+ " sum(model.nregaWorkExpenditure.groundedWorks),"
					+ " sum(model.nregaWorkExpenditure.inprogressWorks),"
					+ " sum(model.nregaWorkExpenditure.completedWorks)"
					+ " from NregaWorkExpenditureLocation model"
					+ " where model.isDeleted = 'N' and model.nregaWorkExpenditure.isDeleted = 'N'");
		
		if(constituencyId != null && constituencyId.longValue() > 0L)
			sb.append(" and model.locationAddress.constituencyId = :constituencyId");
		if(financialYearIds != null && !financialYearIds.isEmpty())
			sb.append(" and model.nregaWorkExpenditure.nregaFinancialYear.nregaFinancialYearId in (:financialYearIds)");
		sb.append(" group by model.nregaWorkExpenditure.nregaWorkType.nregaProgram.nregaProgramId");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(constituencyId != null && constituencyId.longValue() > 0L)
			query.setParameter("constituencyId", constituencyId);
		if(financialYearIds != null && !financialYearIds.isEmpty())
			query.setParameterList("financialYearIds", financialYearIds);
		
		return query.list();
	}
}
