package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.model.PmRefCandidate;

@Repository
public class PmRefCandidateDAO extends GenericDaoHibernate<PmRefCandidate, Long> implements IPmRefCandidateDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRefCandidateDAO() {
		super(PmRefCandidate.class);
		
	}
	public List<Object[]> getAllDistrictsByReferral(Date fromDate,Date toDate,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.district.districtId ");
		sb.append( ",model.pmRefCandidate.address.district.districtName from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where " +
				"model.pmRefCandidate.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model1.insertedTime) between :fromDate and :toDate ) ");
		}
		sb.append(" order by model.pmRefCandidate.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(List<Long> districtIds,  List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.constituency.constituencyId ");
		sb.append( ",model.pmRefCandidate.address.constituency.name from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where " +
				"model.pmRefCandidate.isDeleted='N' and model1.isDeleted='N'and  model.petition.petitionId = model1.petition.petitionId ");
		if(districtIds != null && districtIds.size() > 0){
			sb.append("and model.pmRefCandidate.address.district.districtId in (:districtIds) ");
		}
		
		if(deptIds != null && deptIds.size() > 0){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by model.pmRefCandidate.address.constituency.name asc");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() > 0){
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}

		return query.list();
	}
	
	public List<Object[]> getAllMandalsByReferralAndDistrict(List<Long> constituencyIds,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId ");
		sb.append( ",tehsil.tehsilName" +
				" , localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId  " +
				" from PmRepresenteeRefDetails model left join model.pmRefCandidate.address.localElectionBody localElectionBody left join  model.pmRefCandidate.address.tehsil tehsil ,PmSubWorkDetails model1 where model.isDeleted ='N' and model.pmRefCandidate.isDeleted ='N'" +
				" and model.petition.petitionId = model1.petition.petitionId  ");
		if(constituencyIds != null && constituencyIds.size() > 0){
			sb.append("and model.pmRefCandidate.address.constituencyId in (:constituencyIds) ");
		}
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmRefCandidate.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by tehsil.tehsilName asc,localElectionBody.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}

}
