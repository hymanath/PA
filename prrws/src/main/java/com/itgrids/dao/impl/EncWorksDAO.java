package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.EncHabLocation;
import com.itgrids.model.EncHabtation;
import com.itgrids.model.EncWorks;
import com.itgrids.utils.IConstants;

@Repository
public class EncWorksDAO extends GenericDaoHibernate<EncWorks, Long>  implements IEncWorksDAO {

	public EncWorksDAO()
	{
		super(EncWorks.class);
	}

	@Override
	public List<Long> getAllDistinctWorkIds() {
		Query query= getSession().createQuery("select distinct model.workId from EncWorks model ");
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate, String status,List<Long> schemeIds,String locationType,Long locationValue) {
		// 0-workid, 1-workName,2-schemeId,3-schemeName , 4-agrementAmount,5-mandalId,6-mandalName,7-districtId,8-districtName,9-constituencyId,10-constituencyname
		//11-targetDate,12-status,13-groundedDate 14-completionDate, 15-no of days
		StringBuilder sb = new StringBuilder();
		sb.append("select model.work_id as workId,model.work_name as workName,"
				+ " model.grant_id as grantId, model.grant_name as grantName,"
				+ " model.sanctioned_amount as sannctionedAmount,"
				+ " model.mandal_id as mandalId,model.mandal_name as mandalName,"
				+ " model.district_id as distrctid,model.ditrict_name as districtName,"
				+ " model.assembly_id as assemblyId,model.assembly_name as assemblyName,"
				+ " model.target_date as targetDate, model.work_status as workStatus");
		
		if(status.equalsIgnoreCase("Grounded")){
			sb.append(" ,model.grounded_date as groundedDate,model.completion_date as completionDate,datediff(current_date(),model.target_date) as difference ");
		}else if(status.equalsIgnoreCase("Completed")){
			sb.append(" ,model.grounded_date as groundedDate,model.completion_date as completionDate,datediff(model.completion_date,model.target_date) as difference ");
		}else if(status.equalsIgnoreCase("Not Grounded")){
			sb.append(" ,model.grounded_date as groundedDate,model.completion_date as completionDate,model.completion_date,datediff(current_date(),model.target_date) as difference ");
		}
		
		sb.append(" ,model.entrusted_date as entrustedDate,EH.han_code as habCode ,EH.hab_name as habName from enc_works model,enc_hab_location EHL left join enc_habitation EH on EHL.habitation_code=EH.han_code  where EHL.enc_works_id=model.enc_works_id ");
		if(status.equalsIgnoreCase("Grounded") || status.equalsIgnoreCase("Completed")){
			sb.append(" and model.work_status <>'Not Grounded'");
		}
		
		if(status.equalsIgnoreCase("Grounded")){
			sb.append(" and completion_date is null and grounded_date is not null ");
		}else if(status.equalsIgnoreCase("Completed")){
			sb.append(" and completion_date is not null  ");
		}else if(status.equalsIgnoreCase("Not Grounded")){
			sb.append(" and model.entrusted_date is not null and model.grounded_date is null ");
		}
		
		if(schemeIds !=null && schemeIds.size()>0){
			sb.append(" and model.grant_id in(:schemeIds) ");
		}
		
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.admin_sanction_date between :fromDate and :toDate ");
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && locationValue>0  ){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" and model.district_id =:locationValue");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" and model.assembly_id =:locationValue");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" and model.mandal_id=:locationValue");
			}
		}
		Query query= getSession().createSQLQuery(sb.toString())
				.addScalar("workId").addScalar("workName")
				.addScalar("grantId").addScalar("grantName")
				.addScalar("sannctionedAmount")
				.addScalar("mandalId").addScalar("mandalName")
				.addScalar("distrctid").addScalar("districtName")
				.addScalar("assemblyId").addScalar("assemblyName")
				.addScalar("targetDate").addScalar("workStatus")
				.addScalar("groundedDate").addScalar("completionDate")
				.addScalar("difference").addScalar("entrustedDate")
				.addScalar("habCode").addScalar("habName");
		
		if(fromDate!= null && toDate!=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(schemeIds !=null && schemeIds.size()>0){
			query.setParameterList("schemeIds", schemeIds);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && locationValue>0 && !locationType.equalsIgnoreCase("state")){
			query.setParameter("locationValue", locationValue);
		}
		return query.list();
	}

	@Override
	public EncWorks findOneByworkId(Long workId) {
		
		Query query= getSession().createQuery("from EncWorks where workId=:workId");
		query.setParameter("workId", workId);
		EncWorks workLocation = (EncWorks) query.uniqueResult();
		return (EncWorks) query.uniqueResult();
	}

	@Override
	public List<Object[]> getExceedWorksBystatus(Date date,String type) {
		Query query =null;
		if(type != null && type.equalsIgnoreCase("ongoing")){
			
			query= getSession().createSQLQuery("select count(work_id) as count,work_status as status from enc_works where target_date >=:date and work_status in ('Not Grounded','Grounded') group by work_status")
					.addScalar("count")
					.addScalar("status");
			query.setParameter("date", date);
		}else{
			query= getSession().createSQLQuery("select count(work_id) as count,work_status as status from enc_works where target_date >=completion_date and work_status='Completed'")
					.addScalar("count")
					.addScalar("status");
		}
		
		return query.list();
	}

	@Override
	public Long getEncWorkId(Long mandalId, Long districtCode, Long constituencycode,Long workId) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select encWorkId from EncWorks model where model.workId=:workId ");
		if(mandalId !=null && mandalId>0l){
			sb.append(" and model.mandalId=:mandalId");
		}if(districtCode !=null && districtCode>0l){
			sb.append(" and model.districtId=:districtCode");
		}if(constituencycode !=null && constituencycode>0l){
			sb.append(" and model.assemblyId=:constituencycode");
		}
		Query query= getSession().createQuery(sb.toString());
		query.setParameter("workId",workId);
		if(mandalId !=null && mandalId>0l){
			query.setParameter("mandalId",mandalId);
		}if(districtCode !=null && districtCode>0l){
			query.setParameter("districtCode",districtCode);
		}if(constituencycode !=null && constituencycode>0l){
			query.setParameter("constituencycode",constituencycode);
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public EncHabLocation getENCHablocationId(Long encWorkId, String habCode) {
		
		Query query= getSession().createQuery("from EncHabLocation where encWorksId=:encWorkId  and habitationCode=:habCode");
		query.setParameter("habCode", habCode);
		query.setParameter("encWorkId", encWorkId);
		return (EncHabLocation) query.uniqueResult();
	}

	@Override
	public List<Object[]> getSchemeWiseWorkDetails(InputVO inputVO, String StatusType) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select count(encWorkId),'"+StatusType+"'");
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("state")){
			sb.append(" ,'1','Andra Pradesh' ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append(" ,model.districtId,model.ditrictName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.assemblyId,model.assemblyName ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" ,model.mandalId,model.mandalName ");
		}
		sb.append(" from EncWorks model ");
		if((inputVO.getStartDate() !=null && inputVO.getEndDate() !=null )|| StatusType !=null ){
			sb.append(" where ");
			
		}
		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			sb.append(" adminSanctionDate between :startdate and  :endDate ");
		}
		if(inputVO.getSchemeIdsList() !=null && inputVO.getSchemeIdsList().size()>0){
			sb.append(" and model.grantid in(:schemeIds) ");
		}
		if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and techSanctionDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and entrustedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_GROUNDED)){  //ongoing
			sb.append(" and groundedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_NOTGROUNDED)){
			sb.append(" and entrustedDate is not null and groundedDate is null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_UNDER_PROCESS)){
			sb.append(" and completionDate is null and groundedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_COMPLETION)){
			sb.append(" and completionDate is not null  ");
		}
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district")){
			sb.append("  group by model.districtId ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency")){
			sb.append(" group by  model.assemblyId ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
			sb.append(" group by model.mandalId ");
		}
	
		Query query = getSession().createQuery(sb.toString());
		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			query.setDate("startdate", inputVO.getStartDate());
			query.setDate("endDate", inputVO.getEndDate());
		}
		if(inputVO.getSchemeIdsList() !=null && inputVO.getSchemeIdsList().size()>0){
			query.setParameterList("schemeIds", inputVO.getSchemeIdsList());
		}
		return query.list();
	}

	@Override
	public List<Object[]> getSchemeWiseOnclickWorkDetails(InputVO inputVO) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select ew.work_id as workId, ew.work_name as workName,"
				+ " ew.sanctioned_amount as sanctionedAmount, ew.grant_id as grantId, ew.grant_name as grantName, ew.sub_grant_id as subGrantId,"
				+ " ew.sub_grant_name as subGrantName,ew.admin_sanction_date as adminDate, ew.target_date as targetdate,ew.grounded_date as groundDate,"
				+ " ew.completion_date as completeDate, ew.district_id as districtId,ew.ditrict_name as districtName,ew.assembly_id as assmblyId,"
				+ " ew.assembly_name as assemblyName,ew.mandal_id as mandalId,ew.mandal_name as mandalName,eh.pcode as panchaythId,eh.pname as pancahaythName,"
				+ " eh.hab_name as habitation,eh.han_code as hanCode fROM enc_works ew, enc_hab_location ehl left join enc_habitation eh on ehl.habitation_code = eh.han_code WHERE"
				+ " ew.enc_works_id = ehl.enc_works_id ");

		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			sb.append(" and ew.admin_sanction_date between :startDate and :endDate ");
		}
		if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and ew.tech_sanction_date is not null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase("notTechSanctioned")){
			sb.append(" and ew.tech_sanction_date is null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and ew.entrusted_date is not null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase("notEntrusted")){
			sb.append(" and ew.entrusted_date is null  ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_GROUNDED)){  //ongoing
			sb.append(" and ew.grounded_date is not null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_NOTGROUNDED)){
			sb.append(" and ew.entrusted_date is not null and ew.grounded_date is null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_UNDER_PROCESS)){
			sb.append(" and ew.completion_date is null and ew.grounded_date is not null ");
		}else if(inputVO.getWorkStatus() !=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMPLETION)){
			sb.append(" and ew.completion_date is not null ");
		}
		if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("district") && inputVO.getLocationValue() !=null && inputVO.getLocationValue().longValue()>0){
			sb.append(" and ew.district_id =:locationValue");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("constituency") && inputVO.getLocationValue() !=null && inputVO.getLocationValue().longValue()>0){
			sb.append(" and ew.assembly_id=:locationValue ");
		}else if(inputVO.getLocationType() !=null && inputVO.getLocationType().equalsIgnoreCase("mandal") && inputVO.getLocationValue() !=null && inputVO.getLocationValue().longValue()>0){
			sb.append(" and mandal_id=:locationValue ");
		}
		if(inputVO.getSchemeIdsList() !=null && inputVO.getSchemeIdsList().size()>0){
			sb.append(" and ew.grant_id in(:schemeIds) ");
		}
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("workId").addScalar("workName")
				.addScalar("sanctionedAmount")
				.addScalar("grantId").addScalar("grantName")
				.addScalar("subGrantId").addScalar("subGrantName")
				.addScalar("adminDate").addScalar("targetdate")
				.addScalar("groundDate").addScalar("completeDate")
				.addScalar("districtId").addScalar("districtName")
				.addScalar("assmblyId").addScalar("assemblyName")
				.addScalar("mandalId").addScalar("mandalName")
				.addScalar("panchaythId").addScalar("pancahaythName")
				.addScalar("habitation").addScalar("hanCode");
		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			query.setDate("startDate", inputVO.getStartDate());
			query.setDate("endDate", inputVO.getEndDate());
		}
		if(inputVO.getLocationType() !=null && ! inputVO.getLocationType().equalsIgnoreCase("state") && inputVO.getLocationValue() !=null && inputVO.getLocationValue().longValue()>0){
			query.setParameter("locationValue", inputVO.getLocationValue());
		}
		if(inputVO.getSchemeIdsList() !=null && inputVO.getSchemeIdsList().size()>0){
			query.setParameterList("schemeIds", inputVO.getSchemeIdsList());
		}
		return query.list();
	}

	@Override
	public List<Object[]> getPRProgramsCodeAndName() {
		Query query = getSession().createQuery("select distinct model.grantid,model.grantName from EncWorks model");
		 return query.list();
	}
	
	@Override
	public List<Object[]> getAllSchemeWiseWorkDetails(InputVO inputVO, String StatusType) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select count(model.encWorkId),'"+StatusType+"', model.grantid,model.grantName ");
		
		sb.append(" from EncWorks model ");
		if((inputVO.getStartDate() !=null && inputVO.getEndDate() !=null )|| StatusType !=null ){
			sb.append(" where ");
			
		}
		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			sb.append(" adminSanctionDate between :startdate and  :endDate ");
		}
		if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_TECH_SANCTIONED)){
			sb.append(" and techSanctionDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_ENTRUST)){
			sb.append(" and entrustedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_GROUNDED)){  //ongoing
			sb.append(" and groundedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_NOTGROUNDED)){
			sb.append(" and entrustedDate is not null and groundedDate is null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_UNDER_PROCESS)){
			sb.append(" and completionDate is null and groundedDate is not null ");
		}else if(StatusType !=null && StatusType.equalsIgnoreCase(IConstants.WORK_COMPLETION)){
			sb.append(" and completionDate is not null  ");
		}
			sb.append(" group by model.grantid ");
	
		Query query = getSession().createQuery(sb.toString());
		if( inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
			query.setDate("startdate", inputVO.getStartDate());
			query.setDate("endDate", inputVO.getEndDate());
		}
		return query.list();
	}

}
