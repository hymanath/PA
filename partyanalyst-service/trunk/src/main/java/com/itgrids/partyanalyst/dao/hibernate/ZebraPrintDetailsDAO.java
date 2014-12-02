package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
		else if(searchType.equalsIgnoreCase("Parliament"))
		{
			queryStr.append(" model.tdpCadre.userAddress.constituency.constituencyId  =:locationId");
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
		queryStr.append(" group by date(model.insertedTime)");
		else
		queryStr.append(" group by date(model.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("locationId", locationId);
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
		queryStr.append(" group by date(ZPD.insertedTime)");
		else
		queryStr.append(" group by date(ZPD.updatedTime)");
		Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameter("parliamentId", parliamentId);
		return query.list();
	}
}
