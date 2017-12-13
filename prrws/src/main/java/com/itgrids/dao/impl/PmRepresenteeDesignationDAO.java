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
	
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.district.districtId,model.pmRepresentee.userAddress.district.districtName ");
		sb.append( " from PmRepresenteeDesignation model ");
		sb.append( "order by model.pmRepresentee.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
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
	
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.constituency.constituencyId ");
		sb.append( ",model.pmRepresentee.userAddress.constituency.name from PmRepresenteeDesignation model "
				+ "where model.pmRepresentee.isDeleted='N' ");
		if(districtId != null && districtId.longValue() > 0L){
			sb.append("and model.pmRepresentee.userAddress.districtId =:districtId ");
		}
		sb.append(" order by model.pmRepresentee.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() > 0L){
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}
	
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.tehsil.tehsilId ");
		sb.append( ",model.pmRepresentee.userAddress.tehsil.tehsilName from PmRepresenteeDesignation model "
				+ "where model.pmRepresentee.isDeleted='N' ");
		if(constituencyId != null && constituencyId.longValue() > 0L){
			sb.append("and model.pmRepresentee.userAddress.constituencyId =:constituencyId ");
		}
		sb.append(" order by model.pmRepresentee.userAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() > 0L){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
}
