package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreInsuranceInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreInsuranceInfo;

public class TdpCadreInsuranceInfoDAO extends GenericDaoHibernate<TdpCadreInsuranceInfo, Long> implements ITdpCadreInsuranceInfoDAO{

	public TdpCadreInsuranceInfoDAO() {
		super(TdpCadreInsuranceInfo.class);
	}

	public List<Object[]> getDeathsAndHospitalizationDetails(Long typeId,String type){
		
		StringBuilder str=new StringBuilder();
		str.append(" select count(model.tdpCadreInsuranceInfoId),model.insuranceType.insuranceTypeId,model.insuranceType.type from TdpCadreInsuranceInfo model " );
		
		if(type.equalsIgnoreCase("panchayat")){
			str.append("left join model.tdpCadre.userAddress.panchayat panchayat " +
					" where panchayat.panchayatId=:typeId ");
		}
		else if(type.equalsIgnoreCase("mandal")){
			str.append(" left join model.tdpCadre.userAddress.tehsil tehsil " +
					" where tehsil.tehsilId=:typeId ");
		}
		else if(type.equalsIgnoreCase("constituency")){
			str.append(" left join model.tdpCadre.userAddress.constituency constituency " +
					" where constituency.constituencyId=:typeId");
		}
		else if(type.equalsIgnoreCase("district")){
			str.append(" left join model.tdpCadre.userAddress.district district " +
					" where district.districtId=:typeId");
		}
		
		str.append(" group by model.insuranceType.insuranceTypeId");
			
		Query query=getSession().createQuery(str.toString());
		
		if(type !="" && type !=null){
			query.setParameter("typeId",typeId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getDeathAndHospitalizationDetails(Long locationValue,String searchType){
		/*
		 * 
		 * SELECT #distinct CM.membership_id,
				CM.issue_type
			    #GIS.grievance_insurance_status_id,GIS.status
				,COUNT(CM.Complaint_id)
				FROM grievance_insurance_status GIS,complaint_master CM 
			    left join tdp_cadre TC on CM.membership_id = TC.membership_id,user_address UA
				WHERE CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id 
				AND CM.delete_status IS NULL 
				#and CM.issue_type = 'Death'
			    and CM.type_of_issue = 'Insurance'
			    and TC.address_id = UA.user_address_id
			    and UA.tehsil_id = 1102
			    #and CM.assembly_id = 282
			    and CM.state_id_cmp IN (1,2)
			    and (CM.Subject != '' OR CM.Subject IS NOT NULL)
				#GROUP BY GIS.grievance_insurance_status_id;
				GROUP BY CM.issue_type;
		 */
		StringBuilder str=new StringBuilder();
		str.append("SELECT COUNT(CM.Complaint_id)," +
						" CM.issue_type" +
						" FROM complaint_master CM" +
						" LEFT JOIN tdp_cadre TC ON CM.membership_id = TC.membership_id," +
						" user_address UA" +
						" WHERE CM.delete_status IS NULL" +
						" AND CM.type_of_issue = 'Insurance'" +
						" AND TC.address_id = UA.user_address_id" +
						" AND CM.state_id_cmp IN (1,2)" +
						" AND (CM.Subject != '' OR CM.Subject IS NOT NULL)");
		
		if(searchType.equalsIgnoreCase("panchayat"))
			str.append(" AND UA.panchayat_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("mandal"))
    		str.append(" AND UA.tehsil_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("leb"))
    		str.append(" AND UA.local_election_body = :locationValue");
    	else if(searchType.equalsIgnoreCase("constituency"))
    		str.append(" AND UA.constituency_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("parliament"))
    		str.append(" AND UA.parliament_constituency_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("district"))
    		str.append(" AND UA.district_id = :locationValue");
		
		str.append(" GROUP BY CM.issue_type");
		
		Query query=getSession().createSQLQuery(str.toString());
		
		if(searchType !="" && searchType !=null){
			query.setParameter("locationValue",locationValue);
		}
		
		return query.list();
	}

	public List<Object[]> getDeathsAndHospitalizationDetailsForParliament(List<Long> typeIds,String type){
	
		StringBuilder str=new StringBuilder();
		str.append(" select count(model.tdpCadreInsuranceInfoId),model.insuranceType.insuranceTypeId,model.insuranceType.type from TdpCadreInsuranceInfo model " );
		
		if(type.equalsIgnoreCase("parliament")){
			str.append(" left join model.tdpCadre.userAddress.constituency constituency " +
					" where constituency.constituencyId in (:typeIds) ");
		}
		
		str.append(" group by model.insuranceType.insuranceTypeId");
		
		Query query=getSession().createQuery(str.toString());
		
		if(type !="" && type !=null){
			query.setParameterList("typeIds",typeIds);
		}
		
		return query.list();
	}
	public List<Long> getCadresIdsByInsuranceType(Long locationId,String locationType,Long insuranceTypeId){
		
		StringBuilder str=new StringBuilder();
		str.append(" select distinct model.tdpCadre.tdpCadreId from TdpCadreInsuranceInfo model " );
		
		if(locationType.equalsIgnoreCase("panchayat")){
			str.append("left join model.tdpCadre.userAddress.panchayat panchayat " +
					" where panchayat.panchayatId=:locationId ");
		}
		else if(locationType.equalsIgnoreCase("mandal")){
			str.append(" left join model.tdpCadre.userAddress.tehsil tehsil " +
					" where tehsil.tehsilId=:locationId ");
		}
		else if(locationType.equalsIgnoreCase("constituency")){
			str.append(" left join model.tdpCadre.userAddress.constituency constituency " +
					" where constituency.constituencyId=:locationId ");
		}
		else if(locationType.equalsIgnoreCase("district")){
			str.append(" left join model.tdpCadre.userAddress.district district " +
					" where district.districtId=:locationId ");
		}
		str.append(" and model.insuranceType.insuranceTypeId =:insuranceTypeId ");
			
		Query query=getSession().createQuery(str.toString());
		
		if(locationType !="" && locationType !=null){
			query.setParameter("locationId",locationId);
			query.setParameter("insuranceTypeId", insuranceTypeId);
		}
		
		return query.list();
	}
	public List<Long> getCadresIdsByParliament(List<Long> locationIds,String locationType,Long insuranceTypeId){
	
		StringBuilder str=new StringBuilder();
		str.append(" select distinct model.tdpCadre.tdpCadreId from TdpCadreInsuranceInfo model " );
		
		if(locationType.equalsIgnoreCase("parliament")){
			str.append(" left join model.tdpCadre.userAddress.constituency constituency " +
					" where constituency.constituencyId in (:locationIds) ");
		}
		str.append(" and model.insuranceType.insuranceTypeId =:insuranceTypeId ");
		
		Query query=getSession().createQuery(str.toString());
		
		if(locationType !="" && locationType !=null){
			query.setParameterList("locationIds",locationIds);
			query.setParameter("insuranceTypeId", insuranceTypeId);
		}
		
		return query.list();
	}
	
}
