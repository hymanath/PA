package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.model.PmRepresenteeDesignation;

@Repository
public class PmRepresenteeDesignationDAO extends GenericDaoHibernate<PmRepresenteeDesignation, Long> implements IPmRepresenteeDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeDesignationDAO() {
		super(PmRepresenteeDesignation.class);
		
	}

	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId){
		StringBuilder str = new StringBuilder();
		str.append(" select model from PmRepresenteeDesignation model where model.pmRepresenteeId =:representeeId and model.isActive ='Y' and model.isDeleted='N'  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("representeeId", representeeId);
		return query.list();
	}
	
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList){
		if(pmRepresenteeDesignationIdsList != null && pmRepresenteeDesignationIdsList.size()>0){
			StringBuilder str = new StringBuilder();
			str.append(" update PmRepresenteeDesignation model set model.isActive='N',model.isDeleted='Y' where model.pmRepresenteeDesignationId in (:pmRepresenteeDesignationIdsList) and model.isActive ='Y' " +
					" and  model.isDeleted='N' ");
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("pmRepresenteeDesignationIdsList", pmRepresenteeDesignationIdsList);
			return query.executeUpdate();
		}
		return 0;
	}
	
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtId,model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName ");
		sb.append( " from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				" where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId  ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append( "  and model.pmRepresenteeDesignation.isDeleted='N' order by model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	public List<Object[]> getDesignationsByRepresenteeDesigtion(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDesignation.pmDesignationId,model.pmDesignation.designation "
				+ "from PmRepresenteeDesignation model where model.pmDesignation.isDeleted ='N' and model.isDeleted ='N' order by "
				+ " model.pmDesignation.designation asc " );
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(List<Long> districtIds,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.constituencyId ");
		sb.append( ",model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.name from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				" where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId  ");
		if(districtIds != null && districtIds.size() > 0L){
			sb.append("and model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtId in (:districtIds) ");
		}		
		if(deptIds != null && deptIds.size() > 0L){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" and model.pmRepresenteeDesignation.isDeleted='N' order by model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() > 0L){
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}

		return query.list();
	}
	
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId ");
		sb.append( ",tehsil.tehsilName, localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId from PmRepresenteeRefDetails model left join model.pmRepresenteeDesignation.pmRepresentee.userAddress.localElectionBody localElectionBody" +
				" left join model.pmRepresenteeDesignation.pmRepresentee.userAddress.tehsil  tehsil ,PmSubWorkDetails model1  "
				+ "where model.pmRepresenteeDesignation.pmRepresentee.isDeleted='N' and  model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(constituencyIds != null && constituencyIds.size() > 0L){
			sb.append("and model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituencyId  in (:constituencyIds) ");
		}
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by tehsil.tehsilName asc,localElectionBody.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0L){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
}
