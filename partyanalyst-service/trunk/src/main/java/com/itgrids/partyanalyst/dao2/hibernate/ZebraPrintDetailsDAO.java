package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.model.ZebraPrintDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class ZebraPrintDetailsDAO extends GenericDaoHibernate<ZebraPrintDetails, Long> implements IZebraPrintDetailsDAO {

	public ZebraPrintDetailsDAO() {
		super(ZebraPrintDetails.class);		
	}
	
	public List<Object[]> getMemberShipCardPrintStatusByDate(List<Long> constituencyIds,Date fromDate, Date toDate, String type)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select UA.constituency.constituencyId,UA.constituency.name, UA.constituency.district.districtName, date(ZPD.insertedTime) , count(distinct ZPD.zebraPrintDetailsId) from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA where ");
		queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId and  ");
		queryStr.append("  TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and UA.constituency.constituencyId in (:constituencyIds)  ");
		
		
		
		if(type != null && type.equalsIgnoreCase("printStatus"))
		{
			if(fromDate != null && toDate != null)
			{
				queryStr.append(" and ( date(ZPD.updatedTime) >= :fromDate and date(ZPD.updatedTime) <= :toDate ) ");
			}
			
			queryStr.append(" and ( ZPD.printStatus like '%Y%' OR ZPD.printStatus like '%y%' ) ");
		}		
		else if(type != null && type.equalsIgnoreCase("errorStatus"))
		{
			if(fromDate != null && toDate != null)
			{
				queryStr.append(" and ( date(ZPD.updatedTime) >= :fromDate and date(ZPD.updatedTime) <= :toDate ) ");
			}
			
			queryStr.append(" and ( ZPD.errorStatus like '%Y%' OR ZPD.errorStatus like '%y%' ) ");
		}
		else
		{
			if(fromDate != null && toDate != null)
			{
				queryStr.append(" and ( date(ZPD.insertedTime) >= :fromDate and date(ZPD.insertedTime) <= :toDate ) ");
			}
			
			queryStr.append(" and ZPD.printStatus is not null  ");
		}
		queryStr.append(" group by UA.constituency.constituencyId, date(ZPD.insertedTime) order by date(ZPD.insertedTime) desc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			
		}
		return query.list();
		
	}
	
	public List<Object[]> getMemberShipPrintCardStatusByDate(List<Long> locationIdList,Date fromDate, Date toDate,String queryStr)
	{
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("locationIdList", locationIdList);
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
		
	}
	
	
	public List<Object[]> getMemberShipCardPushDataStatusByDate(List<Long> locationIdList,Date fromDate, Date toDate,String queryStr)
	{
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("locationIdList", locationIdList);
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();	
	}
	public List<Object[]> getPrintedCardsDetails()
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.tdpCadreId, model.printSerialNo from ZebraPrintDetails model where ( model.printStatus ='Y' or model.printStatus ='y' )");
		Query query = getSession().createQuery(queryStr.toString()); 
		return query.list();
	}
	
	public Long getPrintedCountByLocations(List<Long> consituencyIdsList, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(!searchType.equalsIgnoreCase(IConstants.MP))
		{
			queryStr.append(" select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:consituencyIdsList) ");
		}
		else
		{
			queryStr.append(" select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where  ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:consituencyIdsList) ");
		}
		
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((model.printStatus ='Y' or model.printStatus ='y' ) and (model.errorStatus is null or model.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (model.errorStatus is not null or model.errorStatus !='0' )  ");
		}
		
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("consituencyIdsList", consituencyIdsList);
		return (Long) query.uniqueResult();
	}
	
	

	public List<Object[]> getPrintedCountByLocationWise(List<Long> locationIdsList, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" select model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name, count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:locationIdsList) ");
			if(dataType.equalsIgnoreCase("printStatus"))
			{
				queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
			}
			else if(dataType.equalsIgnoreCase("errorStatus"))
			{
				queryStr.append(" and (model.errorStatus is not null and model.errorStatus !='0') ");
			}
			queryStr.append(" group by model.tdpCadre.userAddress.constituency.constituencyId ");
			queryStr.append(" order by model.tdpCadre.userAddress.constituency.name asc ");
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" select model.tdpCadre.userAddress.district.districtId, model.tdpCadre.userAddress.district.districtName, count(model.zebraPrintDetailsId) from ZebraPrintDetails model where  ");
			queryStr.append(" model.tdpCadre.userAddress.district.districtId in (:locationIdsList) ");
			if(dataType.equalsIgnoreCase("printStatus"))
			{
				queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
			}
			else if(dataType.equalsIgnoreCase("errorStatus"))
			{
				queryStr.append(" and (model.errorStatus is not null and  model.errorStatus !='0' ) ");
			}
			queryStr.append(" group by model.tdpCadre.userAddress.district.districtId ");
			queryStr.append(" order by model.tdpCadre.userAddress.district.districtName asc ");
		}
		
		
		
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("locationIdsList", locationIdsList);
		return query.list();
	}
	public List<Object[]> getCadreDetailsByStatus(Long Id,String Status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.lastname,model.tdpCadre.relativename,model.tdpCadre.mobileNo,model.tdpCadre.memberShipNo,model.errorStatus from ZebraPrintDetails model");
		if(Status.equalsIgnoreCase("ERROR"))
		{
			str.append(" where model.errorStatus is not null and model.errorStatus !='0' ");
			
		}
		str.append(" and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and model.tdpCadre.userAddress.constituency.constituencyId =:Id");
		Query query = getSession().createQuery(str.toString()); 
		query.setParameter("Id", Id);
		return query.list();	}
	

	public List<Object[]> getParliamentWiseResults(List<Long> parliamentIdsList, String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		
		queryStr.append("  select DCA.delimitationConstituency.constituency.constituencyId, DCA.delimitationConstituency.constituency.name, count(ZPD.zebraPrintDetailsId)  " +
				" from ZebraPrintDetails ZPD, DelimitationConstituencyAssemblyDetails DCA ,DelimitationConstituency DC  " +
				" where DC.year = 2009 and " +
				" DCA.delimitationConstituency.delimitationConstituencyID = DC.delimitationConstituencyID and " +
				" ZPD.tdpCadre.userAddress.constituency.constituencyId = DCA.constituency.constituencyId and " +
				" DCA.delimitationConstituency.constituency.constituencyId in (:parliamentIdsList) and " +
				" ZPD.tdpCadre.isDeleted = 'N' and " +
				" ZPD.tdpCadre.enrollmentYear = 2014 ");
				
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((ZPD.printStatus ='Y' or ZPD.printStatus ='y' ) and (ZPD.errorStatus is null or ZPD.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (ZPD.errorStatus is not null and ZPD.errorStatus !='0' )  ");
		}
		
		queryStr.append(" group by DCA.delimitationConstituency.constituency.constituencyId order by DCA.delimitationConstituency.constituency.name asc  ");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("parliamentIdsList", parliamentIdsList);
		return query.list();
	}
	
	public List<ZebraPrintDetails> getCadreDetailsByMembershipId(String membershipId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model from  ZebraPrintDetails model where model.memberShipNumber =:membershipId ");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("membershipId", membershipId);
		return query.list();
	}
	
	
	
	public Long getTotalPrintStatusCount(List<Long> consituencyIdsList, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(!searchType.equalsIgnoreCase(IConstants.MP)){
			queryStr.append(" select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:consituencyIdsList) ");
		}
		else{
			queryStr.append(" select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where  ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:consituencyIdsList) ");
		}
		queryStr.append(" and model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
		if(dataType.equalsIgnoreCase("printStatus")){
			queryStr.append(" and ((model.printStatus ='Y' or model.printStatus ='y' ) and (model.errorStatus is null or model.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus")){
			queryStr.append(" and (model.errorStatus is not null and model.errorStatus !='0' )  ");
		}
	
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("consituencyIdsList", consituencyIdsList);
		return (Long)query.uniqueResult();
	}
	
	public int updateVoterName(Long zebraPrintDetailsId,String voterName)
	{
		Query query = getSession().createQuery("update ZebraPrintDetails model set model.voterName = :voterName where model.zebraPrintDetailsId = :zebraPrintDetailsId ");
		query.setParameter("voterName",voterName);
		query.setParameter("zebraPrintDetailsId",zebraPrintDetailsId);
		return query.executeUpdate();
	}
		
	public List<Object[]> getAllCadreDetailsByBatchCode(String batchCode){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.member_ship_member,model.voter_name,model.mobile_no,model.district_name,model.constituency_name,model.mandal_name,model.muncipality_name," +
				"model.panchayat_name,model.part_no,model.house_no,model.zebra_print_details_id,tca.location,tca.town from zebra_print_details " +
				" model LEFT JOIN tdp_cadre_address tca on model.tdp_cadre_id = tca.tdp_cadre_id where model.job_code =:batchCode and model.print_status ='Y' and (model.error_Status is null or model.error_Status='0') ");
		Query query = getSession().createSQLQuery(queryStr.toString()); 
		query.setParameter("batchCode", batchCode);
		return query.list();
	}
	
	public List<Object[]> getPrintedCountByLocationWise(Long locationId, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(model.zebraPrintDetailsId)"); 
		if(dataType.equalsIgnoreCase("totalCount"))
		queryStr.append(",date(model.insertedTime)" );
		else
		queryStr.append(",date(model.updatedTime)" );
		queryStr.append(" from ZebraPrintDetails model where ");
		
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId  =:locationId");
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" model.tdpCadre.userAddress.district.districtId =:locationId ");
		}
		
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (model.errorStatus is not null and  model.errorStatus !='0' )  ");
		}
		if(dataType.equalsIgnoreCase("totalCount"))
		queryStr.append(" and model.insertedTime is not null group by date(model.insertedTime)");
		else
		queryStr.append(" group by date(model.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("locationId", locationId);
		return query.list();
	}
	public List<Object[]> getPrintedCountByInsertedTime(Long locationId, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(model.zebraPrintDetailsId)"); 
		queryStr.append(",date(model.insertedTime)" );
		if(dataType.equalsIgnoreCase("printStatus"))
		queryStr.append(",date(model.updatedTime)" );
		queryStr.append(" from ZebraPrintDetails model where ");
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId  =:locationId");
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" model.tdpCadre.userAddress.district.districtId =:locationId ");
		}
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (model.errorStatus is not null and  model.errorStatus !='0' )  ");
		}
		queryStr.append(" and model.insertedTime is not null group by date(model.insertedTime)");
		if(dataType.equalsIgnoreCase("printStatus"))
		queryStr.append(" ,date(model.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	
	public List<Object[]> getPrintedCountByParlmentInsertedTime(Long parliamentId, String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(ZPD.zebraPrintDetailsId)"); 
		queryStr.append(",date(ZPD.insertedTime)" );
		if(dataType.equalsIgnoreCase("printStatus"))
		queryStr.append(",date(ZPD.updatedTime)" );
		queryStr.append(" from ZebraPrintDetails ZPD, DelimitationConstituencyAssemblyDetails DCA ,DelimitationConstituency DC  " +
				" where DC.year = 2009 and " +
				" DCA.delimitationConstituency.delimitationConstituencyID = DC.delimitationConstituencyID and " +
				" ZPD.tdpCadre.userAddress.constituency.constituencyId = DCA.constituency.constituencyId and " +
				" DCA.delimitationConstituency.constituency.constituencyId =:parliamentId and " +
				" ZPD.tdpCadre.isDeleted = 'N' and " +
				" ZPD.tdpCadre.enrollmentYear = 2014 ");
				
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((ZPD.printStatus ='Y' or ZPD.printStatus ='y' ) and (ZPD.errorStatus is null or ZPD.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (ZPD.errorStatus is not null and ZPD.errorStatus !='0' )  ");
		}
		queryStr.append(" and ZPD.insertedTime is not null group by date(ZPD.insertedTime)");
		if(dataType.equalsIgnoreCase("printStatus"))
		queryStr.append(" ,date(ZPD.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("parliamentId", parliamentId);
		return query.list();
	}
	public List<Object[]> getPrintedCountByParliamentise(Long parliamentId, String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		
		queryStr.append("  select count(ZPD.zebraPrintDetailsId)"); 
		if(dataType.equalsIgnoreCase("totalCount"))
		queryStr.append(",date(ZPD.insertedTime)" );
		else
		queryStr.append(",date(ZPD.updatedTime)" );
		queryStr.append(" from ZebraPrintDetails ZPD, DelimitationConstituencyAssemblyDetails DCA ,DelimitationConstituency DC  " +
				" where DC.year = 2009 and " +
				" DCA.delimitationConstituency.delimitationConstituencyID = DC.delimitationConstituencyID and " +
				" ZPD.tdpCadre.userAddress.constituency.constituencyId = DCA.constituency.constituencyId and " +
				" DCA.delimitationConstituency.constituency.constituencyId =:parliamentId and " +
				" ZPD.tdpCadre.isDeleted = 'N' and " +
				" ZPD.tdpCadre.enrollmentYear = 2014 ");
				
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((ZPD.printStatus ='Y' or ZPD.printStatus ='y' ) and (ZPD.errorStatus is null or ZPD.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (ZPD.errorStatus is not null and ZPD.errorStatus !='0' )  ");
		}
		if(dataType.equalsIgnoreCase("totalCount"))
		queryStr.append(" and ZPD.insertedTime is not null group by date(ZPD.insertedTime)");
		else
		queryStr.append(" group by date(ZPD.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("parliamentId", parliamentId);
		return query.list();
	}
	
	
	
	public List<Object[]> getJobCodesByLocationWise(Long locationId, String searchType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct date(model.updatedTime),model.tdpCadre.userAddress.constituency.name"); 
			
		queryStr.append(" from ZebraPrintDetails model where model.updatedTime is not null ");
		queryStr.append(" and model.printStatus ='Y' and (model.errorStatus is null or model.errorStatus='0')");
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId  =:locationId");
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and model.tdpCadre.userAddress.district.districtId =:locationId  group by  date(model.updatedTime), model.tdpCadre.userAddress.constituency.constituencyId");
		}
		
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	public List<Object[]> getJobCodesByParliament(Long parliamentId, String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct date(ZPD.updatedTime),DCA.constituency.name"); 
		queryStr.append(" from ZebraPrintDetails ZPD, DelimitationConstituencyAssemblyDetails DCA ,DelimitationConstituency DC  " +
				" where DC.year = 2009 and " +
				" DCA.delimitationConstituency.delimitationConstituencyID = DC.delimitationConstituencyID and " +
				" ZPD.tdpCadre.userAddress.constituency.constituencyId = DCA.constituency.constituencyId and " +
				" DCA.delimitationConstituency.constituency.constituencyId =:parliamentId and " +
				" ZPD.tdpCadre.isDeleted = 'N' and " +
				" ZPD.tdpCadre.enrollmentYear = 2014 and ZPD.updatedTime is not null");
		queryStr.append(" and ZPD.printStatus ='Y' and (ZPD.errorStatus is null or ZPD.errorStatus='0') group by date(ZPD.updatedTime),DCA.constituency.constituencyId");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("parliamentId", parliamentId);
		return query.list();
	}
	
	
	public List<Object[]> getAllCadreDetailsByBatchCodeandLocation(Date batchCode,String searchType,Long Id){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.member_ship_member,model.voter_name,model.mobile_no,model.district_name,model.constituency_name,model.mandal_name,model.muncipality_name," +
				"model.panchayat_name,model.part_no,model.house_no,model.zebra_print_details_id,tca.location,tca.town from zebra_print_details " +
				" model LEFT JOIN tdp_cadre_address tca on model.tdp_cadre_id = tca.tdp_cadre_id ");
		queryStr.append(" LEFT JOIN tdp_cadre tc ON model.tdp_cadre_id = tc.tdp_cadre_id");
		queryStr.append(" LEFT JOIN user_address ua ON tc.address_id = ua.user_address_id");
		queryStr.append(" where date(model.update_time) =:batchCode and model.print_status ='Y' and (model.error_Status is null or model.error_Status='0') "); 
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and ua.constituency_id = :Id");	
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and ua.district_id = :Id");	
		}
		Query query = getSession().createSQLQuery(queryStr.toString()); 
		query.setDate("batchCode", batchCode);
		query.setParameter("Id", Id);
		return query.list();
	}
	
	public List<Object[]> getAllCadreDetailsByParliament(Date batchCode,String searchType,List<Long> Ids){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.member_ship_member,model.voter_name,model.mobile_no,model.district_name,model.constituency_name,model.mandal_name,model.muncipality_name," +
				"model.panchayat_name,model.part_no,model.house_no,model.zebra_print_details_id,tca.location,tca.town from zebra_print_details " +
				" model LEFT JOIN tdp_cadre_address tca on model.tdp_cadre_id = tca.tdp_cadre_id ");
		queryStr.append(" LEFT JOIN tdp_cadre tc ON model.tdp_cadre_id = tc.tdp_cadre_id ");
		queryStr.append(" LEFT JOIN user_address ua ON tc.address_id = ua.user_address_id ");
		queryStr.append(" where date(model.update_time) =:batchCode and model.print_status ='Y' and (model.error_Status is null or model.error_Status='0') "); 
		queryStr.append(" and ua.constituency_id in(:Ids) ");	
		
		Query query = getSession().createSQLQuery(queryStr.toString()); 
		query.setDate("batchCode", batchCode);
		query.setParameterList("Ids", Ids);
		return query.list();
	}
	
	public List<Object[]> getPrintedCountDetailsByStatusTypeSeacrh(List<Long> locationIdsList, String searchType,String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" select model.tdpCadre.userAddress.constituency.constituencyId,count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:locationIdsList) ");
			if(dataType.equalsIgnoreCase("printStatus"))
			{
				queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
			}
			else if(dataType.equalsIgnoreCase("errorStatus"))
			{
				queryStr.append(" and (model.errorStatus is not null and model.errorStatus !='0') ");
			}
			queryStr.append(" group by model.tdpCadre.userAddress.constituency.constituencyId ");
			queryStr.append(" order by model.tdpCadre.userAddress.constituency.name asc ");
		}
		else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" select model.tdpCadre.userAddress.district.districtId,count(model.zebraPrintDetailsId) from ZebraPrintDetails model where  ");
			queryStr.append(" model.tdpCadre.userAddress.district.districtId in (:locationIdsList) ");
			if(dataType.equalsIgnoreCase("printStatus"))
			{
				queryStr.append(" and ((model.printStatus is not null and model.printStatus ='Y' ) and (model.errorStatus is null or model.errorStatus ='0' )) ");
			}
			else if(dataType.equalsIgnoreCase("errorStatus"))
			{
				queryStr.append(" and (model.errorStatus is not null and  model.errorStatus !='0' ) ");
			}
			queryStr.append(" group by model.tdpCadre.userAddress.district.districtId ");
			queryStr.append(" order by model.tdpCadre.userAddress.district.districtName asc ");
		}
		
		
		
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("locationIdsList", locationIdsList);
		return query.list();
	}
	
	public List<Object[]> getParliamentWiseResultsByStatusType(List<Long> parliamentIdsList, String dataType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		
		queryStr.append("  select DCA.delimitationConstituency.constituency.constituencyId, count(ZPD.zebraPrintDetailsId)  " +
				" from ZebraPrintDetails ZPD, DelimitationConstituencyAssemblyDetails DCA ,DelimitationConstituency DC  " +
				" where DC.year = 2009 and " +
				" DCA.delimitationConstituency.delimitationConstituencyID = DC.delimitationConstituencyID and " +
				" ZPD.tdpCadre.userAddress.constituency.constituencyId = DCA.constituency.constituencyId and " +
				" DCA.delimitationConstituency.constituency.constituencyId in (:parliamentIdsList) and " +
				" ZPD.tdpCadre.isDeleted = 'N' and " +
				" ZPD.tdpCadre.enrollmentYear = 2014 ");
				
		if(dataType.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ((ZPD.printStatus ='Y' or ZPD.printStatus ='y' ) and (ZPD.errorStatus is null or ZPD.errorStatus ='0' ))  ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and (ZPD.errorStatus is not null and ZPD.errorStatus !='0' )  ");
		}
		
		queryStr.append(" group by DCA.delimitationConstituency.constituency.constituencyId order by DCA.delimitationConstituency.constituency.name asc  ");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("parliamentIdsList", parliamentIdsList);
		return query.list();
	}
	
	public Long getPrintingCompletedCount(String state,String dataType,List<Long> accessLocationIds) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
		str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
		if(dataType.equalsIgnoreCase("printStatus")){
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
				" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
		}
		else if(dataType.equalsIgnoreCase("errorStatus")){
			str.append(" and (model.errorStatus is not null and model.errorStatus !='0' ) ");
		}
		
		if(state.equalsIgnoreCase("AP")){
			str.append(" and model.tdpCadre.userAddress.district.districtId > 10");
		}else if(state.equalsIgnoreCase("TS")){
			str.append(" and model.tdpCadre.userAddress.district.districtId < 11");
		}
		if(accessLocationIds.size() > 0){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in(:accessLocationIds)");
		}
		Query query = getSession().createQuery(str.toString());
		if(accessLocationIds.size() > 0){
			query.setParameterList("accessLocationIds", accessLocationIds);
		}
		return (Long) query.uniqueResult();
	}
	public Long getIvrReadyCount(Date date,String state,List<Long> accessLocationIds) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ((model.printStatus = 'Y' or model.printStatus ='y') " +
				" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
		str.append(" and model.mobileNo is not null and model.updatedTime is not null and date(updatedTime) < :date ");
		if(state.equalsIgnoreCase("AP")){
			str.append(" and model.tdpCadre.userAddress.district.districtId > 10");
		}else if(state.equalsIgnoreCase("TS")){
			str.append(" and model.tdpCadre.userAddress.district.districtId < 11");
		}
		if(accessLocationIds.size() > 0){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in(:accessLocationIds)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("date", date);
		if(accessLocationIds.size() > 0){
			query.setParameterList("accessLocationIds", accessLocationIds);
		}
		return (Long) query.uniqueResult();
	}
	
	public Long getLocationWisePrintingCompletedCount(Set<Long> locationIds,String locationType) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.zebraPrintDetailsId), from ZebraPrintDetails model where ((model.printStatus = 'Y' or model.printStatus ='y') " +
				" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
		Query query = getSession().createQuery(str.toString());
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getLocationWiseCadreRegisterInfo(Set<Long> locationIds,String locationType,Long constituencyId){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count,2jobids
		queryStr.append("select "+getLocation(locationType));
		
		queryStr.append(",count(model.zebraPrintDetailsId),date(updatedTime) from ZebraPrintDetails model where "+getLocation(locationType)+"" +
				" in(:locationIds) and model.tdpCadre.enrollmentYear ='2014' and  "+getLocation(locationType)+" is not null and ((model.printStatus = 'Y' or model.printStatus ='y') " +
						" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null and model.updatedTime is not null ");
		if(constituencyId != null){
			queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:constituencyId ");
		}
		queryStr.append(" group by "+getLocation(locationType)+",date(updatedTime) ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("locationIds", locationIds);
		if(constituencyId != null){
			  query.setParameter("constituencyId", constituencyId);
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
	
	
	
	public Long getPrintedCount(List<Long> locationIdsList, String type)
	{
		StringBuilder str = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{			
			str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append("and model.tdpCadre.userAddress.constituency.constituencyId in (:locationIdsList) ");
			
		}
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		{
			str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:locationIdsList) ");		
			
		}
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:locationIdsList) ");		
			
		}
		
		Query query = getSession().createQuery(str.toString()); 
		query.setParameterList("locationIdsList", locationIdsList);
		
		return (Long)query.uniqueResult();
	}
	
	
	public List<Object[]> getLocationWisePrintedCountDetails(List<Long> locationIdsList, String type)
	{
		StringBuilder str = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{			
			str.append("select count(model.zebraPrintDetailsId),model.tdpCadre.userAddress.constituency.constituencyId from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append("and model.tdpCadre.userAddress.constituency.constituencyId in (:locationIdsList) ");
			str.append("group by model.tdpCadre.userAddress.constituency.constituencyId ");
		}
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		{
			str.append("select count(model.zebraPrintDetailsId),model.tdpCadre.userAddress.tehsil.tehsilId from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:locationIdsList) ");	
			str.append(" group by model.tdpCadre.userAddress.tehsil.tehsilId ");	
			
		}
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append("select count(model.zebraPrintDetailsId),model.tdpCadre.userAddress.localElectionBody.localElectionBodyId from ZebraPrintDetails model where ");
			str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
			str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
					" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
			str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:locationIdsList) ");
			str.append(" group by model.tdpCadre.userAddress.localElectionBody.localElectionBodyId ");			
		}
		
		Query query = getSession().createQuery(str.toString()); 
		query.setParameterList("locationIdsList", locationIdsList);
		
		return query.list();
	}
	
	
	public Long getTotalPrintingCountByState(Long stateTypeId) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
		str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
		str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
				" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
				
		if(stateTypeId.longValue() == 0L)
		{
			str.append(" and model.tdpCadre.userAddress.district.districtId between 1 and 23 ");
		}
		else if(stateTypeId.longValue() == 1L)
		{
			str.append(" and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
		}
		else if(stateTypeId.longValue() == 2L)
		{
			str.append(" and model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
		}
		
	
		Query query = getSession().createQuery(str.toString());

		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getTotalPrintingCountByDistrict(Long districtId) 
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name,count(model.zebraPrintDetailsId) from ZebraPrintDetails model where ");
		str.append(" model.tdpCadre.isDeleted = 'N' and  model.tdpCadre.enrollmentYear = 2014 ");
		str.append(" and ((model.printStatus = 'Y' or model.printStatus ='y') " +
				" and (model.errorStatus is null or model.errorStatus ='0' or  model.errorStatus  = '' or  model.errorStatus = 'null')) and model.serialNo is not null ");
		str.append(" and model.tdpCadre.userAddress.district.districtId=:districtId ");
		str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId ");
		str.append(" order by model.tdpCadre.userAddress.constituency.name");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("districtId", districtId);
		return  query.list();
	}
}
