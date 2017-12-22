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
		str.append(" select model from PmRepresenteeDesignation model where model.pmRepresenteeId =:representeeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("representeeId", representeeId);
		return query.list();
	}
	
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtId,model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName ");
		sb.append( " from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				" where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId  ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append( "order by model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	public List<Object[]> getDesignationsByRepresenteeDesigtion(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDesignation.pmDesignationId,model.pmDesignation.designation "
				+ "from PmRepresenteeDesignation model where model.pmDesignation.isDeleted ='N' order by"
				+ " model.pmDesignation.orderNo asc " );
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(List<Long> districtIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.constituency.constituencyId ");
		sb.append( ",model.pmRepresentee.userAddress.constituency.name from PmRepresenteeDesignation model "
				+ "where model.pmRepresentee.isDeleted='N' ");
		if(districtIds != null && districtIds.size() > 0L){
			sb.append("and model.pmRepresentee.userAddress.districtId in (:districtIds) ");
		}
		sb.append(" order by model.pmRepresentee.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtIds != null && districtIds.size() > 0L){
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}
	
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.tehsil.tehsilId ");
		sb.append( ",model.pmRepresentee.userAddress.tehsil.tehsilName from PmRepresenteeDesignation model "
				+ "where model.pmRepresentee.isDeleted='N' ");
		if(constituencyIds != null && constituencyIds.size() > 0L){
			sb.append("and model.pmRepresentee.userAddress.constituencyId  in (:constituencyIds) ");
		}
		sb.append(" order by model.pmRepresentee.userAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0L){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		return query.list();
	}
}
