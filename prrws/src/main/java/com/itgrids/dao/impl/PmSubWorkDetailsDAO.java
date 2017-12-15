package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.model.PmSubWorkDetails;

@Repository
public class PmSubWorkDetailsDAO extends GenericDaoHibernate<PmSubWorkDetails, Long> implements IPmSubWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmSubWorkDetailsDAO(){
		super(PmSubWorkDetails.class);
	}
	
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append(" select  model.petitionId, model.costEstimation, model.grievanceDescrption, model.eOfficeId , pmSubject.pmSubjectId, pmSubSubject.pmSubjectId , " +//0,1,2,3,4,5
				" pmLead.pmLeadId,pmBriefLead.pmBriefLeadId,pmGrant.pmGrantId,pmStatus.pmStatusId,pmDepartment.pmDepartmentId,pmWorkType.pmWorkTypeId, " +//6,7,8,9,10,11
				" state.stateId,district.districtId,constituency.constituencyId,tehsil.tehsilId,localBody.localElectionBodyId,model.pmSubWorkDetailsId," +//12,13,14,15,16,17
				" model.uiBuildSeriesNo,model.locationScopeId,model.locationValue, " +//18,19,20
				//"'','','','','','',"+
				" state.stateName,district.districtName,constituency.name,tehsil.tehsilName,localBody.name,electionType.electionType," +//21,22,23,24,25,26
				//"'','','','','','','',''"+
				" pmLead.leadName,pmBriefLead.briefLead,pmGrant.pmGrantName,pmStatus.status,pmDepartment.department,pmWorkType.workType, pmSubject.subject,pmSubSubject.subject " +//27,28,29,30,31,32,33,34
				" ,model.uiBuildSeriesNo " +//35
				" from PmSubWorkDetails model " +
				" left join model.locationAddress address" +
				" left join address.state state " +
				" left join address.district district" +
				" left join address.constituency constituency" +
				" left join address.tehsil tehsil" +
				" left join address.localElectionBody localBody " +
				" left join localBody.electionType electionType" +
				" left join model.pmSubject pmSubject " +
				" left join model.pmSubSubject pmSubSubject" +
				" left join model.pmLead pmLead " +
				" left join model.pmBriefLead pmBriefLead" +
				" left join model.pmGrant pmGrant " +
				" left join model.pmStatus pmStatus " +
				" left join model.pmDepartment pmDepartment " +
				" left join model.pmWorkType pmWorkType " +
				" where model.petitionId =:petitionId and model.petition.isDeleted='N' ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	
	public List<Object[]> getAllDistricts(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.district.districtId");
		sb.append(",model.locationAddress.district.districtName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted = 'N' order by model.locationAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByDistricId(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.constituency.constituencyId");
		sb.append(",model.locationAddress.constituency.name ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(districtId != null && districtId.longValue() >0L ){ 
			sb.append("and model.locationAddress.districtId =:districtId ");
		}
		sb.append( "order by model.locationAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() >0L ){ 
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsByDistricId(Long constincyIdId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.tehsil.tehsilId");
		sb.append(",model.locationAddress.tehsil.tehsilName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(constincyIdId != null && constincyIdId.longValue() >0L ){ 
			sb.append("and model.locationAddress.constituencyId =:constincyIdId ");
		}
		sb.append( "order by model.locationAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constincyIdId != null && constincyIdId.longValue() >0L ){ 
			query.setParameter("constincyIdId", constincyIdId);
		}
		return query.list();
	}
	
	public List<Object[]> getDepartmentsByWorks(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartment.pmDepartmentId,model.pmDepartment.department "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmDepartment.isDeleted='N' "
				+ "order by model.pmDepartment.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}

	public List<Long> getPmSubWorkDetailsIds(Long petitionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.pmSubWorkDetailsId from PmSubWorkDetails model where model.isDeleted='N' and model.petitionId =:petitionId ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public Integer updatePmsubWorkDetails(List<Long> subWorkDetailsIds,Date updateTime,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append("update PmSubWorkDetails model set model.isDeleted='Y', model.updatedTime =:updateTime,model.updatedUserId =:userId "
				+ " where model.pmSubWorkDetailsId  in (:subWorkDetailsIds) ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("subWorkDetailsIds", subWorkDetailsIds);
		query.setParameter("updateTime", updateTime);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}
}
