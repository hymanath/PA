package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreEnrollmentInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreEnrollmentInfoDAO extends GenericDaoHibernate<TdpCadreEnrollmentInfo, Long> implements ITdpCadreEnrollmentInfoDAO {

	
	public TdpCadreEnrollmentInfoDAO() {
		super(TdpCadreEnrollmentInfo.class);
	}
	public List<Object[]>  getTotalNewRenewalCadreStateWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,String startDate, String endDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" S.state_id as sId, S.state_name as sName,sum(TCEI.total_cadre) as totalCadre, sum(TCEI.new_cadre) as newCadre, sum(TCEI.renewal_cadre) as renewalCadre " +
						" from " +
						" tdp_cadre_enrollment_info TCEI, " +
						" user_address UA " +
						" state S ");
		if(accessLvlId != null && accessLvlId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" , district D ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			queryStr.append(" , constituency C ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" , constituency C ");  
		}
		queryStr.append(" where TCEI.state_id = S.state_id ");
		if(startDate != null && startDate.length() >= 10 && endDate != null && endDate.length() >= 10){
			queryStr.append("and date(TCEI.survey_time) between :startDate and :endDate");
		}
		queryStr.append(" group by S.state_id order by TCEI.state_id");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("sId", Hibernate.LONG)
				.addScalar("sName", Hibernate.STRING)
				.addScalar("totalCadre", Hibernate.LONG)
				.addScalar("newCadre", Hibernate.LONG)
				.addScalar("renewalCadre", Hibernate.LONG);
		if(startDate != null && startDate.length() >= 10 && endDate != null && endDate.length() >= 10){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}  
	public Long getTotalConstituencyForCdrRegStarted(Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct TCEI.constituencyId) from TdpCadreEnrollmentInfo TCEI where TCEI.stateId = :stateId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult();
	}//select  count(distinct constituency_id) from tdp_cadre_enrollment_info TCEI where TCEI.state_id = 1;
	
}
