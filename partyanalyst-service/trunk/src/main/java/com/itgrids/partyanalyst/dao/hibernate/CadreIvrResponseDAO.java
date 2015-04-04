package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.Set;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.model.CadreIvrResponse;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreIvrResponseDAO extends GenericDaoHibernate<CadreIvrResponse, Long> implements ICadreIvrResponseDAO{

	public CadreIvrResponseDAO() {
		super(CadreIvrResponse.class);
		
	}

	
	public List<Date> getDates()
	{
		Query query = getSession().createQuery("select distinct date(model.date) from CadreIvrResponse model");
		return query.list();
	}
	
	public Long getIvrStatusCount(Date date,Long Id,String searchType)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where ");
		if(date != null)
			str.append(" date(model.date) =:date");
		else if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCadreDetails(Date date,Long Id,String searchType,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.firstname,model.mobileNo,model.currentStatus");
		str.append(" ,constituency.name");	
		str.append(" ,tehsil.tehsilName");
		str.append(" ,panc.panchayatName");
		str.append(" ,localElectionBody.name");
		str.append(" from CadreIvrResponse model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
	    str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" where");
		if(date != null)
			str.append(" date(model.date) =:date");
		if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		else if(searchType.equalsIgnoreCase(IConstants.Response))
			 str.append(" and model.responseKey in (1,2,3)");
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	public  Long  getTotalIvrCount(String state,List<Long> accessLocationIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where model.isDeleted = 'N' ");
		if(state.equalsIgnoreCase("AP")){
			str.append(" and model.userAddress.district.districtId > 10");
		}else if(state.equalsIgnoreCase("TS")){
			str.append(" and model.userAddress.district.districtId < 11");
		}
		if(accessLocationIds.size() > 0){
			str.append(" and model.userAddress.constituency.constituencyId in(:accessLocationIds)");
		}
		Query query = getSession().createQuery(str.toString());
		if(accessLocationIds.size() > 0){
			query.setParameterList("accessLocationIds", accessLocationIds);
		}
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCountForAPAndTS()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.responseKey from CadreIvrResponse model where model.isDeleted = 'N' ");
		str.append(" group by model.responseKey");
		Query query = getSession().createQuery(str.toString());
			
		return query.list();
	}
	public List<Object[]> getIvrCountByDate(Date fromDate,Date toDate,String state,List<Long> accessLocationIds,Long campaignId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.responseKey,model.callStatus,model.optionId from CadreIvrResponse model ");
		//str.append(" left join model.ivrOptions ivrOptions ");
		str.append(" where model.isDeleted = 'N' and model.ivrCampaign.isDeleted = 'N' and model.ivrCampaign.ivrCampaignId = :campaignId");
		if(state.equalsIgnoreCase("AP")){
			str.append(" and model.tdpCadre.userAddress.district.districtId > 10");
		}else if(state.equalsIgnoreCase("TS")){
			str.append(" and model.tdpCadre.userAddress.district.districtId < 11");
		}
		if(accessLocationIds.size() > 0){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in(:accessLocationIds)");
		}
		if((fromDate != null && toDate != null) && !fromDate.equals(toDate))
		str.append(" and date(model.date) >=:fromDate and date(model.date) <=:toDate");
		else if((fromDate != null && toDate != null) && fromDate.equals(toDate))
		str.append(" and date(model.date) =:fromDate");
		str.append(" group by model.callStatus,model.responseKey");
		Query query = getSession().createQuery(str.toString());
		if((fromDate != null && toDate != null) && !fromDate.equals(toDate))
		{
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		}
		if((fromDate != null && toDate != null) && fromDate.equals(toDate))
		query.setDate("fromDate", fromDate);	
		if(accessLocationIds.size() > 0){
			query.setParameterList("accessLocationIds", accessLocationIds);
		}
		query.setParameter("campaignId", campaignId);
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseIvrCount()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name,model.userAddress.district.districtId,model.userAddress.district.districtName,model.responseKey from CadreIvrResponse model where ");
		str.append(" model.tdpCadre.userAddress.constituency.constituencyId is not null and model.isDeleted = 'N' ");
		str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId,model.responseKey");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}	
	public List<Object[]> getTehsilWiseIVRInfo(){
		//0 districtId,1constituencyId,2constituencyName,3tehsilId,4tehsilName,5count,6response key,7districtName
		Query query = getSession().createQuery("select model.userAddress.district.districtId,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.userAddress.tehsil.tehsilId,concat(model.userAddress.tehsil.tehsilName,' Mandal')," +
				" count(*),model.responseKey,model.userAddress.district.districtName from  CadreIvrResponse model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId is null and " +
				" model.userAddress.tehsil.tehsilId is not null and model.responseKey is not null group by model.userAddress.tehsil.tehsilId,model.responseKey ");
		
		return query.list();
	}
	public List<Object[]> getTehsilWiseIVRTotalCountInfo(){
		//0 id,1count
		Query query = getSession().createQuery("select model.userAddress.tehsil.tehsilId,count(*) " +
				"  from  CadreIvrResponse model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId is null and " +
				" model.userAddress.tehsil.tehsilId is not null group by model.userAddress.tehsil.tehsilId ");
		
		return query.list();
	}
	
	public List<Object[]> getLocalBodyWiseIVRInfo(){
		//0 districtId,1constituencyId,2constituencyName,localElectionBodyId,4localElectionBodyName,5count,6response key,7districtName
		Query query = getSession().createQuery("select model.userAddress.district.districtId,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.userAddress.localElectionBody.localElectionBodyId," +
				" concat(model.userAddress.localElectionBody.name, model.userAddress.localElectionBody.electionType.electionType),count(*),model.responseKey,model.userAddress.district.districtName from  CadreIvrResponse model where model.isDeleted = 'N' and " +
				" model.userAddress.localElectionBody.localElectionBodyId is not null and model.responseKey is not null group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId,model.responseKey ");
		
		return query.list();
	}
	public List<Object[]> getLocalBodyWiseIVRTotalCountInfo(){
		//0 constituencyId,1 localBodyId,2 count
		Query query = getSession().createQuery("select model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId,count(*) from CadreIvrResponse model where model.isDeleted = 'N' " +
				"  and model.userAddress.localElectionBody.localElectionBodyId is not null group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId ");
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseIVRInfo(String state){
		StringBuilder queryStr = new StringBuilder();
		//0panchayatId,1panchayatName,2districtName,3constituencyName,4count,5responseKey
		queryStr.append("select model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName,model.userAddress.district.districtName,model.userAddress.constituency.name,count(*),model.responseKey from " +
				" CadreIvrResponse model where model.isDeleted = 'N' and model.responseKey is not null ");
		if(state.equalsIgnoreCase("AP")){
			queryStr.append(" and model.userAddress.district.districtId > 10");
		}else{
			queryStr.append(" and model.userAddress.district.districtId < 11");
		}
		queryStr.append(" and model.userAddress.panchayat.panchayatId is not null group by model.userAddress.panchayat.panchayatId,model.responseKey");
		Query query = getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseIVRCountInfo(String state){
		StringBuilder queryStr = new StringBuilder();
		//0panchayatId,1count
		queryStr.append("select model.userAddress.panchayat.panchayatId,count(*) from " +
				" CadreIvrResponse model  where model.isDeleted = 'N' ");
		if(state.equalsIgnoreCase("AP")){
			queryStr.append(" and model.userAddress.district.districtId > 10");
		}else{
			queryStr.append(" and model.userAddress.district.districtId < 11");
		}
		queryStr.append("and model.userAddress.panchayat.panchayatId is not null group by model.userAddress.panchayat.panchayatId");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getLocationWiseIVRInfo(Set<Long> locationIds,String locationType,Date startDate,Date endDate,List<Long> accessLocationIds){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count,2responseKey
		queryStr.append("select "+getLocation(locationType));
		
		queryStr.append(",count(model.userAddress.userAddressId),model.responseKey from CadreIvrResponse model where "+getLocation(locationType)+"" +
				" in(:locationIds) and model.isDeleted = 'N' and model.responseKey is not null and "+getLocation(locationType)+" is not null ");
		if(startDate != null){
			queryStr.append(" and date(model.date) >= :startDate ");
		}
		if(endDate != null){
			queryStr.append(" and date(model.date) <= :endDate ");
		}
		if(accessLocationIds.size() > 0){
			queryStr.append(" and model.userAddress.constituency.constituencyId in(:accessLocationIds)");
		}
		queryStr.append(" group by "+getLocation(locationType)+",model.responseKey");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("locationIds", locationIds);
		if(accessLocationIds.size() > 0){
			query.setParameterList("accessLocationIds", accessLocationIds);
		}
		if(startDate != null){
		  query.setParameter("startDate", startDate);
		}
		if(endDate != null){
		  query.setParameter("endDate", endDate);
		}
		return query.list();
	}
	
	public String getLocation(String location){
		if(location.equalsIgnoreCase("district")){
			return " model.tdpCadre.userAddress.district.districtId ";
		}else if(location.equalsIgnoreCase("constituency")){
			return " model.tdpCadre.userAddress.constituency.constituencyId ";
		}else if(location.equalsIgnoreCase("mandal")){
			return " model.tdpCadre.userAddress.tehsil.tehsilId ";
		}else if(location.equalsIgnoreCase("localBody")){
			return " model.tdpCadre.userAddress.localElectionBody.localElectionBodyId ";
		}else if(location.equalsIgnoreCase("panchayat")){
			return " model.tdpCadre.userAddress.panchayat.panchayatId ";
		}else{
			return " model.tdpCadre.userAddress.booth.boothId ";
		}
	}
	
	public List<Object[]> getLocationWiseIVRCountsInfo(Set<Long> locationIds,String locationType,Date startDate,Date endDate,Long constituencyId,Long campaignId){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count,2callStatus,3responseKey
		queryStr.append("select "+getLocation(locationType));
		
		queryStr.append(",count(model.tdpCadre.userAddress.userAddressId),model.callStatus,model.responseKey,model.optionId from CadreIvrResponse model where "+getLocation(locationType)+"" +
				" in(:locationIds) and model.isDeleted = 'N' and model.ivrCampaign.isDeleted= 'N' and model.ivrCampaign.ivrCampaignId = :campaignId " +
				" and "+getLocation(locationType)+" is not null ");
		if(startDate != null){
			queryStr.append(" and date(model.date) >= :startDate ");
		}
		if(endDate != null){
			queryStr.append(" and date(model.date) <= :endDate ");
		}
		if(constituencyId != null){
			queryStr.append(" and model.userAddress.constituency.constituencyId =:constituencyId ");
		}
		queryStr.append(" group by "+getLocation(locationType)+",model.callStatus,model.responseKey");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("locationIds", locationIds);
		if(startDate != null){
		  query.setParameter("startDate", startDate);
		}
		if(endDate != null){
		  query.setParameter("endDate", endDate);
		}
		if(constituencyId != null){
			  query.setParameter("constituencyId", constituencyId);
			}
		query.setParameter("campaignId",campaignId);
		return query.list();
	}
	
	public List<Object[]> getIvrCountByLocationIdsAndType(List<Long> locationIds,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId)" +
				   " ,model.responseKey,model.callStatus");
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			str.append(" ,model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name,district.districtId,district.districtName");
		}
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
		{
			str.append(" ,model.tdpCadre.userAddress.tehsil.tehsilId,model.tdpCadre.userAddress.tehsil.tehsilName,constituency.constituencyId,constituency.name");	
		}
		 if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" , model.tdpCadre.userAddress.localElectionBody.localElectionBodyId, model.tdpCadre.userAddress.localElectionBody.name,constituency.constituencyId,constituency.name");	
		}
		str.append(" from CadreIvrResponse model");
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			
		str.append(" left join model.userAddress.district district where model.isDeleted = 'N' ");
		str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in(:locationIds)  ");
		str.append("  group by model.tdpCadre.userAddress.constituency.constituencyId,model.responseKey");
		}
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		{
			 str.append(" left join model.userAddress.constituency constituency where model.isDeleted = 'N' ");
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in(:locationIds)  ");
			str.append(" group by model.tdpCadre.userAddress.tehsil.tehsilId,model.responseKey");	
		}
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" left join model.userAddress.constituency constituency where model.isDeleted = 'N' ");
			str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in(:locationIds)  ");
			str.append(" group by model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,model.responseKey");	
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationIds", locationIds);
		return query.list();
	}	
	
	public List<Object[]> getCadreCommitteesIvRDetails(Long reportType,Long campainId)
	{
		
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		if(reportType == 1)
		{
			sb.append("  CIR.tdpCadre.userAddress.district.districtId,CIR.tdpCadre.userAddress.district.districtName, ");
		}
		else if(reportType == 2)
		{
			sb.append("  CIR.tdpCadre.userAddress.constituency.constituencyId,CIR.tdpCadre.userAddress.constituency.name, ");
		}
		else if(reportType == 3)
		{
			sb.append("  CIR.tdpCadre.userAddress.panchayat.panchayatId,CIR.tdpCadre.userAddress.panchayat.panchayatName, ");
		}
		else
		{
			sb.append("  CIR.tdpCadre.userAddress.ward.constituencyId,CIR.tdpCadre.userAddress.ward.name, ");
		}
		sb.append(" CIR.callStatus,CIR.optionId,count(*)  ");
		sb.append("  from CadreIvrResponse CIR where  ");
		sb.append(" CIR.campaignId = :campainId group by  ");
		if(reportType == 1)
		{
			sb.append("  CIR.tdpCadre.userAddress.district.districtId, ");
		}
		else if(reportType == 2)
		{
			sb.append("  CIR.tdpCadre.userAddress.constituency.constituencyId, ");
		}
		else if(reportType == 3)
		{
			sb.append("  CIR.tdpCadre.userAddress.panchayat.panchayatId, ");
		}
		else
		{
			sb.append("  CIR.tdpCadre.userAddress.ward.constituencyId, ");
		}
		sb.append("  CIR.callStatus,CIR.optionId ");
		
		sb.append(" order by  ");
		if(reportType == 1)
		{
			sb.append("  CIR.tdpCadre.userAddress.district.districtId, ");
		}
		else if(reportType == 2)
		{
			sb.append("  CIR.tdpCadre.userAddress.constituency.constituencyId, ");
		}
		else if(reportType == 3)
		{
			sb.append("  CIR.tdpCadre.userAddress.ward.constituencyId, ");
		}
		else
		{
			
		}
		sb.append("  CIR.callStatus,CIR.optionId ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("campainId", campainId);
		return query.list();
	}
	
	public List<Object[]> getStateWiseCommitterIvrDetails(List<Long> distIds,Long campainId)
	{
		StringBuffer sb = new StringBuffer();
		/*sb.append("select CIR.callStatus,CIR.optionId,count(*) from CadreIvrResponse CIR where CIR.campaignId = :campainId ");
		if(distIds != null && distIds.size() > 0)
		{
			sb.append(" and CIR.tdpCadre.userAddress.district.districtId in (:distIds) " );
		}
		sb.append(" group by CIR.callStatus,CIR.optionId ");
		Query query = getSession().createQuery(sb.toString());
		*/
		sb.append("select call_status,option_id,sum(count) from cadre_ivr_response_info where campaign_id=:campainId ");
		if(distIds != null && distIds.size() > 0)
		{
			sb.append(" and location_id in (:distIds) and location_type like '%District%'" );
		}
		sb.append(" group by option_id ");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameter("campainId", campainId);
		if(distIds != null && distIds.size() > 0)
		{
			query.setParameterList("distIds", distIds);
		}
		
		return query.list();
	}
	
	public List<Long> getPanchayatsCountIvrStarted(List<Long> distIds,Long campainId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct CIR.tdpCadre.userAddress.panchayat.panchayatId from CadreIvrResponse CIR where CIR.campaignId = :campainId  ");
		if(distIds != null && distIds.size() > 0)
		{
			sb.append(" and CIR.tdpCadre.userAddress.district.districtId in (:distIds) " );
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("campainId", campainId);
		if(distIds != null && distIds.size() > 0)
		{
			query.setParameterList("distIds", distIds);
		}
		
		return query.list();
	}
	
	public List<Long> getTotalIvrCalls(List<Long> distIds,Long campainId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select count(CIR.mobileNo) from CadreIvrResponse CIR where CIR.campaignId = :campainId  ");
		if(distIds != null && distIds.size() > 0)
		{
			sb.append(" and CIR.tdpCadre.userAddress.district.districtId in (:distIds) " );
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("campainId", campainId);
		if(distIds != null && distIds.size() > 0)
		{
			query.setParameterList("distIds", distIds);
		}
		
		return query.list();
	}
	public List<Long> getTotalAnsweredIvrCalls(List<Long> distIds,Long campainId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select count(CIR.mobileNo) from CadreIvrResponse CIR where CIR.campaignId = :campainId and CIR.callStatus = 'NORMAL_CLEARING'  ");
		if(distIds != null && distIds.size() > 0)
		{
			sb.append(" and CIR.tdpCadre.userAddress.district.districtId in (:distIds) " );
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("campainId", campainId);
		if(distIds != null && distIds.size() > 0)
		{
			query.setParameterList("distIds", distIds);
		}
		
		return query.list();
	}
}
