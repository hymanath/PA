package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.PmRepresenteeRefDetails;
@Repository
public class PmRepresenteeRefDetailsDAO extends GenericDaoHibernate<PmRepresenteeRefDetails, Long> implements IPmRepresenteeRefDetailsDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeRefDetailsDAO() {
		super(PmRepresenteeRefDetails.class);
	}

	public List<Object[]> getRepresentativeSearchWiseDetails(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		String  filterValue = inputVO.getFilterValue();
		Long searchLevelId = inputVO.getSearchLevelId();
		Long searchLevelValue = inputVO.getSearchLevelValue();
		String filterType = inputVO.getFilterType();
		sb.append(" select model.petition.petitionId,model.petition.endorsmentNo,model.petition.endorsmentDate," +
				" model.petition.estimationCost,model.pmRepresentee.name,model.pmRefCandidate.name," +
				"model.petition.noOfWorks,model2.pmDesignation.designation," +
				" model.petition.insertedTime,model1.updatedTime,model1.pmStatus.pmStatusId" +
				",model.petition.workName from PmRepresenteeRefDetails as model,PmSubWorkDetails as model1" +
				",PmRefCandidateDesignation model2 ");
		
		if(filterType != null && (filterType.equalsIgnoreCase("work") || filterType.equalsIgnoreCase("department") )){
			sb.append(" left join model1.locationAddress locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("referrelDesignation") ){
			sb.append(" left join model.pmRefCandidateDesignation.pmRefCandidate.address locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") ){
			sb.append(" left join model.pmRepresenteeDesignation.pmRepresentee.userAddress locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("referral")){
			sb.append(" left join model.pmRefCandidate.address locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("representee")){
			sb.append(" left join model.pmRepresentee.userAddress locationAddress " );
		}else{
			sb.append(" left join model.pmRepresentee.userAddress locationAddress " );
		}
			
		sb.append("  left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " );
					//" left join locationAddress.localElectionBody localElectionBody " +
					//" left join locationAddress.panchayat panchayat  ");
		
		
		sb.append(" where  model.pmRepresentee.isDeleted = 'N' and model.isDeleted ='N'  and model1.petition.petitionId = model.petition.petitionId and model1.isDeleted='N' " +
				" and  model.pmRefCandidateDesignation.isDeleted = 'N' and model.pmRefCandidate.isDeleted = 'N' and model.pmRepresentee.isDeleted = 'N'  ");
		sb.append(" and model2.pmRefCandidateId=model.pmRefCandidateId   "); 
		if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  state.stateId=:searchLevelValue ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  district.districtId=:searchLevelValue ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  constituency.constituencyId=:searchLevelValue ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  tehsil.tehsilId=:searchLevelValue ");
			}/*else if(searchLevelId.longValue() ==7L){
				sb.append(" and  localElectionBody.localElectionBodyId=:searchLevelValue ");
			}else if(searchLevelId.longValue() ==6L){
				sb.append(" and  panchayat.panchayatId=:searchLevelValue ");
			}*/
		}
		if(filterType != null && filterType.equalsIgnoreCase("name") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.name like '%"+filterValue+"%' ");
		}else if(filterType != null && filterType.equalsIgnoreCase("email") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.email like '%"+filterValue+"%' ");
		}else if(filterType != null && filterType.equalsIgnoreCase("mobile") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.mobileNo =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("endorsmentNO") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.petition.endorsmentNo =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("department") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model1.pmDepartment.pmDepartmentId =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("referrelDesignation") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresenteeDesignation.pmDesignation.pmDesignationId =:filterValue ");
		}
		if(inputVO.getFromRange() != null && inputVO.getToRange() != null){
			sb.append(" and model.petition.noOfWorks between :fromNoOfWorks and :toNoOfWorks " );
		}
		if(inputVO.getMinVal() != null && inputVO.getMaxVal() != null){
			sb.append(" and model.petition.estimationCost between :minEstimationCost and :maxEstimationCost " );
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.petition.insertedTime) between :fromDate and :toDate " );
		}
		Query query = getSession().createQuery(sb.toString());
		if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
			query.setParameter("searchLevelValue", searchLevelValue);
		}
		if(filterType != null && !filterType.equalsIgnoreCase("name") && !filterType.equalsIgnoreCase("email") && filterValue != null && !filterValue.isEmpty()
				&& filterType.equalsIgnoreCase("mobile") && filterType.equalsIgnoreCase("endorsmentNO") ){
			query.setParameter("filterValue", filterValue);
		}else if(filterValue != null && !filterValue.isEmpty() && !filterType.equalsIgnoreCase("name") && !filterType.equalsIgnoreCase("email")){
			query.setParameter("filterValue", Long.valueOf(filterValue));
		}
		if(inputVO.getFromRange() != null && inputVO.getToRange() != null){
			query.setParameter("fromNoOfWorks", inputVO.getFromRange());
			query.setParameter("toNoOfWorks", inputVO.getToRange());
		}
		if(inputVO.getMinVal() != null && inputVO.getMaxVal() != null){
			query.setParameter("minEstimationCost", inputVO.getMinVal());
			query.setParameter("maxEstimationCost", inputVO.getMaxVal());
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}

}
