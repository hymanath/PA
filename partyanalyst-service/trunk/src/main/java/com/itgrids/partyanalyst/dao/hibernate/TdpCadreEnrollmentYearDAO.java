package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;

public class TdpCadreEnrollmentYearDAO extends GenericDaoHibernate<TdpCadreEnrollmentYear, Long> implements ITdpCadreEnrollmentYearDAO{

	public TdpCadreEnrollmentYearDAO() {
		super(TdpCadreEnrollmentYear.class);
	}
	
	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId){
		
		Query query = getSession().createQuery("select model.enrollmentYear.year from TdpCadreEnrollmentYear model " +
				" where model.tdpCadreId = :tdpCadreId and model.enrollmentYear.isActive = 'N'");
		
		query.setParameter("tdpCadreId",tdpCadreId);
		
		return query.list();
	}
	public Long getMaxRecordFromEnrollmentYear(Long tdpCadreId){
		
		Query query = getSession().createQuery("select max(model.enrollmentYear.year) from TdpCadreEnrollmentYear model " +
				" where model.tdpCadreId = :tdpCadreId ");
		
		query.setParameter("tdpCadreId",tdpCadreId);
		
		return (Long) query.uniqueResult();
	}
	
	
}
