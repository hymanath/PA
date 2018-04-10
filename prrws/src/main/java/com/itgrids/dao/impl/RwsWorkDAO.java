package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsWorkDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.RwsWork;
import com.itgrids.utils.IConstants;

@Repository
public class RwsWorkDAO extends GenericDaoHibernate<RwsWork, Long> implements IRwsWorkDAO {

	public RwsWorkDAO()
	{
		super(RwsWork.class);
	}
	
	public List<String> getWorkdetailsById(String type) {
		StringBuilder sb = new StringBuilder();
		if(type !=null && type.equalsIgnoreCase("all")){
			sb.append("select distinct model.workId from RwsWork model ");
			
		}else{
			sb.append("select distinct model.workId from RwsWork model where is_active='Y'");
		}
		Query query= getSession().createQuery(sb.toString());
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate,String status,String assetType, String locationType,String locationValue,String districtId,List<String> schemeIds) {
		
		StringBuilder sb = new StringBuilder();
		//0-workId,1-WorkName,2-status,3-assetType,4-adminDate,5-groundDate,6-targetrDate,7-completionDate
		//8-dCode,9-dname,10-ccode ,11-Cname,12-mCode,13-mname ,14-habcode,15-habname
		sb.append("select model.rwsWork.workId,model.rwsWork.workName,model.rwsWork.workStatus,model.rwsWork.assetType,model.rwsWork.adminDate,model.rwsWork.groundedDate,model.rwsWork.targetDate,model.rwsWork.completedDate," +
				" model.districtCode,model.districtName, model.constituencyCode,model.constituencyName,model.mandalCode,model.mandalName,model.habitationCode," +
				"model.habitationName,model.rwsWork.sanctionedAmount, 01, 'AndraPradesh',model.rwsWork.stipulatedTargetDate,model.rwsWork.programCode,model.rwsWork.programName"
				+ " from RwsWorkLocation model where model.rwsWork.workStatus != 'Not grounded'" +
				" and model.rwsWork.isActive ='Y' and  model.rwsWork.stipulatedTargetDate is not null");
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		if(assetType!= null && assetType.length()>0){
			sb.append(" and model.rwsWork.assetType in (:assetType)");
		}
		if(districtId!=null && districtId.length()>0){
			sb.append(" and model.districtCode =:districtId");
		}
		if(status != null && status.length()>0){
			sb.append(" and model.rwsWork.workStatus =:status ");
		}
		if(schemeIds !=null && schemeIds.size()>0){
			sb.append(" and model.rwsWork.programCode in(:schemeIds) ");
		}
		if(locationType!= null && locationType.length()>0 ){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" and model.districtCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" and model.constituencyCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" and model.mandalCode=:locationValue");
			}
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate!= null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(assetType!= null && assetType.length()>0){
			query.setParameter("assetType", assetType);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && locationValue.length()>0 && !locationType.equalsIgnoreCase("state")){
			query.setParameter("locationValue", locationValue.toString());
		}
		if(status != null && status.length()>0){
			if(status.equalsIgnoreCase("onGoing")){
				status="Grounded";
			}
			query.setParameter("status",status);
		}
		if(districtId!=null && districtId.length()>0){
			query.setParameter("districtId",districtId.toString());
		}
		if(schemeIds !=null && schemeIds.size()>0){
			query.setParameterList("schemeIds",schemeIds);
		}
		return query.list();
	}

	@Override
	public RwsWork getWorkdetailsByIds(String workId) {
		Query query= getSession().createQuery("from RwsWork model where workId=:workId and model.isActive='Y' ");
		query.setParameter("workId", workId);
		RwsWork work = (RwsWork) query.uniqueResult();
		return work;
	}

	@Override
	public List<RwsWork> getWorksbyWorkIdList(List<String> workIds) {
		Query query= getSession().createQuery("from RwsWork where workId in(:workId)");
		query.setParameterList("workId", workIds);
		return query.list();
	}

	@Override
	public List<Object[]> getnotGroundedWorkList(Date fromDate, Date toDate,String assetType, String locationType, String locationValue, String districtId,List<String> schemeIds) {
		//0-workId,2-workName,3-workStatus,4-assesttype,5-admindate,6-diff,7-dcode, 8-dname,9-ccode,10-cname,11-mcode,12-mname,13-hcode,14-hname,15-sancAmnt,16-sid,17-sname
		StringBuilder sb = new StringBuilder();
		sb.append("select  model.rwsWork.workId,model.rwsWork.workName,model.rwsWork.workStatus,model.rwsWork.assetType,model.rwsWork.adminDate, datediff(curdate(),model.rwsWork.adminDate), " +
				" model.districtCode,model.districtName, model.constituencyCode,model.constituencyName,model.mandalCode,model.mandalName,model.habitationCode," +
				" model.habitationName,model.rwsWork.sanctionedAmount, 01, 'AndraPradesh',model.rwsWork.programCode,model.rwsWork.programName from RwsWorkLocation model where model.rwsWork.isActive='Y' and model.rwsWork.workStatus='Not Grounded' ");
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		if(assetType!= null && assetType.length()>0){
			sb.append(" and model.rwsWork.assetType in (:assetType)");
		}
		if(districtId!=null && districtId.length()>0){
			sb.append(" and model.districtCode =:districtId");
		}
		if(locationType!= null && locationType.length()>0 ){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" and model.districtCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" and model.constituencyCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" and model.mandalCode=:locationValue");
			}
		}
		if(schemeIds !=null && schemeIds.size()>0){
			sb.append(" and model.rwsWork.programCode in(:schemeIds)");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate!= null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(assetType!= null && assetType.length()>0){
			query.setParameter("assetType", assetType);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && locationValue.length()>0 && !locationType.equalsIgnoreCase("state")){
			query.setParameter("locationValue", locationValue.toString());
		}
		if(schemeIds !=null && schemeIds.size()>0){
			query.setParameterList("schemeIds",schemeIds);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getRwsProgramsCodeAndName(){
		 Query query = getSession().createQuery("select distinct model.programCode,model.programName from RwsWork model");
		 return query.list();
	}
	

	@Override
	public List<Object[]> getLocationWiseSchemeWiseWorkDetails(InputVO inputVO) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select count(model.rwsWork.rwsWorkId),model.rwsWork.workStatus,model.rwsWork.assetType ");
		
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append(" ,model.districtCode,model.districtName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.constituencyCode,model.constituencyName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" ,model.mandalCode,model.mandalName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("state")){
			sb.append(" ,'1','Andra Pradesh' ");
		}
		
		sb.append(" from RwsWorkLocation model where model.rwsWork.isActive='Y' ");
		
		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
				if (inputVO.getFilterType().equalsIgnoreCase("district")) {
					sb.append(" and trim(model.districtCode) =:locationValue ");
				} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
					sb.append(" and trim(model.constituencyCode) =:locationValue ");
				}
		}
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			sb.append(" and  model.rwsWork.adminDate between :fromDate and :toDate ");  
		 }
		sb.append(" group by model.rwsWork.workStatus,model.rwsWork.assetType ");
		
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append(" ,model.districtCode");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.constituencyCode ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" ,model.mandalCode ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			query.setDate("fromDate", inputVO.getStartDate());
			query.setDate("toDate", inputVO.getEndDate());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		return query.list();
	}

	@Override
	public List<Object[]> getLocationWiseSchemeWiseAdminWorkDetails(InputVO inputVO, String sanctionedType) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select count(model.rwsWork.rwsWorkId),'"+sanctionedType+"',model.rwsWork.assetType ");
		
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append(" ,model.districtCode,model.districtName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.constituencyCode,model.constituencyName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" ,model.mandalCode,model.mandalName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("state")){
			sb.append(" ,'1','Andra Pradesh' ");
		}
		
		sb.append(" from RwsWorkLocation model where model.rwsWork.isActive='Y' ");
		
		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
				if (inputVO.getFilterType().equalsIgnoreCase("district")) {
					sb.append(" and trim(model.districtCode) =:locationValue ");
				} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
					sb.append(" and trim(model.constituencyCode) =:locationValue ");
				}
		}
		
		if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_ADMIN_SANC)){
			sb.append(" and  model.rwsWork.adminDate is not null  ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and  model.rwsWork.entrustedDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and  model.rwsWork.technicalSanctionDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_GROUNDED)){ // ongoing
			sb.append(" and  model.rwsWork.groundedDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_NOTGROUNDED)){
			sb.append(" and model.rwsWork.groundedDate is null and model.rwsWork.completedDate is null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_UNDER_PROCESS)){
			sb.append(" and model.rwsWork.completedDate is null and model.rwsWork.groundedDate is not null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_COMPLETION)){
			sb.append(" and model.rwsWork.completedDate is not null and model.rwsWork.commissionedDate is null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_COMMISSIONED)){
			sb.append(" and model.rwsWork.completedDate is not null and model.rwsWork.commissionedDate is not null ");
		}
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			sb.append("and  model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		if(inputVO.getSchemeIdStr() !=null && inputVO.getSchemeIdStr().size()>0){
			sb.append("and  model.rwsWork.programCode in (:schemeIds)");
		}
		sb.append(" group by model.rwsWork.assetType ");
		
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append(" ,model.districtCode");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.constituencyCode ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" ,model.mandalCode ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			query.setDate("fromDate", inputVO.getStartDate());
			query.setDate("toDate", inputVO.getEndDate());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		if(inputVO.getSchemeIdStr() !=null && inputVO.getSchemeIdStr().size()>0){
			query.setParameterList("schemeIds",inputVO.getSchemeIdStr());
		}
		return query.list();
	}

	@Override
	public List<Object[]> getstatustWiseWorks(InputVO vo,List<String> dbStatuses) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.districtCode,model.districtName,model.mandalCode,model.mandalName,model.constituencyCode,"    //0-,1,2,3,4
				+ " model.constituencyName,model.habitationCode, model.habitationName,model.rwsWork.workId,model.rwsWork.sanctionedAmount,"//5,6,7,8,9
				+ " model.rwsWork.workName,model.rwsWork.programName, model.rwsWork.adminDate,model.rwsWork.stipulatedTargetDate,"//10,11,12,13
				+ " model.rwsWork.groundedDate,model.rwsWork.completedDate " //14,15
				+ " from RwsWorkLocation model where model.rwsWork.isActive='Y' ");
		if(vo.getAssetType() !=null && vo.getAssetType().length()>0){
			sb.append(" and model.rwsWork.assetType =:assetType ");
		}
		if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && dbStatuses.contains(vo.getWorkStatus().toLowerCase())){
			sb.append(" and model.rwsWork.workStatus =:workStatus ");
		}else if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && vo.getWorkStatus().equalsIgnoreCase(IConstants.WORK_ADMIN_SANC)){
			sb.append(" and  model.rwsWork.adminDate is not null  ");  
		}else if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && vo.getWorkStatus().equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and  model.rwsWork.technicalSanctionDate is not null ");  
		}else if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && vo.getWorkStatus().equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and  model.rwsWork.entrustedDate is not null ");  
		}else if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && vo.getWorkStatus().equalsIgnoreCase(IConstants.WORK_GROUNDED)){
			sb.append(" and  model.rwsWork.groundedDate is not null ");  
		}
		if(vo.getStartDate() != null && vo.getEndDate()!=null){
			sb.append(" and  model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(vo.getAssetType() !=null && vo.getAssetType().length()>0){
			query.setParameter("assetType", vo.getAssetType());
		}
		if(vo.getWorkStatus() !=null && vo.getWorkStatus().length()>0 && dbStatuses.contains(vo.getWorkStatus())){
			query.setParameter("workStatus", vo.getWorkStatus());
		}
		if(vo.getStartDate() != null && vo.getEndDate()!=null){
			query.setDate("fromDate", vo.getStartDate());
			query.setDate("toDate", vo.getEndDate());
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getAllWorksByScheme(InputVO inputVO, String sanctionedType) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select count(model.rwsWorkId),'"+sanctionedType+"',model.programCode,model.programName ");
		sb.append(" from RwsWork model where model.isActive='Y' ");
		
	
		if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_ADMIN_SANC)){
			sb.append(" and  model.adminDate is not null  ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and  model.entrustedDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and  model.technicalSanctionDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_GROUNDED)){ // ongoing
			sb.append(" and  model.groundedDate is not null ");  
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_NOTGROUNDED)){
			sb.append(" and model.groundedDate is null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_UNDER_PROCESS)){
			sb.append(" and model.completedDate is null and model.groundedDate is not null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_COMPLETION)){
			sb.append(" and model.completedDate is not null and model.commissionedDate is null ");
		}else if(sanctionedType !=null && sanctionedType.equalsIgnoreCase(IConstants.WORK_COMMISSIONED)){
			sb.append(" and model.completedDate is not null and model.commissionedDate is not null ");
		}
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			sb.append("and  date(model.adminDate) between :fromDate and :toDate ");
		}
		
		sb.append(" group by model.programCode,model.programName ");
		
		
		Query query = getSession().createQuery(sb.toString());
		
		if(inputVO.getStartDate() != null && inputVO.getEndDate()!=null){
			query.setDate("fromDate", inputVO.getStartDate());
			query.setDate("toDate", inputVO.getEndDate());
		}  
		
		return query.list();
	}


}
