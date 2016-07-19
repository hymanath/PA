package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpMemberDAO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.model.TdpMember;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpMemberDAO extends GenericDaoHibernate<TdpMember, Long> implements ITdpMemberDAO{

	public TdpMemberDAO() {
		super(TdpMember.class);
		// TODO Auto-generated constructor stub
	}

	

	
	  public List<Object[]>  searchTdpMemberByCriteria(String searchType,String searchValue,LocationInputVO locationVo){
			
			StringBuilder sb=new StringBuilder();
			sb.append(" SELECT  model.tdpCadre.tdpCadreId ,model.tdpCadre.firstname,model.tdpCadre.mobileNo,model.tdpCadre.userAddress.constituency.name," +
					"           model.tdpCadre.memberShipNo, voter.voterIDCardNo, model.tdpCadre.image " +
					"   FROM    TdpMember model LEFT JOIN model.tdpCadre.voter voter" +
					"   WHERE   model.tdpCadre.isDeleted='N' AND model.isDeleted ='N' AND model.tdpCadre.enrollmentYear = :enrollmentYear ");
			if(searchType.equalsIgnoreCase("mobileno")){
				
				sb.append(" AND model.tdpCadre.mobileNo = :searchValue ");
				
			}else if(searchType.equalsIgnoreCase("mebershipno")){
				
				sb.append(" AND model.tdpCadre.memberShipNo = :searchValue ");
				
			}else if(searchType.equalsIgnoreCase("votercardno")){
				
				sb.append(" AND voter.voterIDCardNo = :searchValue ");
			}
			else 
			{
				
				sb.append(" AND model.memberTypeId =:searchTypeId ");
				
				if(searchValue != null && !searchValue.isEmpty())
					sb.append(" AND model.tdpCadre.mobileNo = :searchValue ");
				//sb.append(" AND model.tdpCadre.firstname LIKE '%"+searchValue+"%' ");
				 if(locationVo != null)
					{
							if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)//State
							{
								if(locationVo.getStateId() == 1)
								sb.append("and model.tdpCadre.userAddress.district.districtId > 10  and model.tdpCadre.userAddress.district.districtId <=23");
								if(locationVo.getStateId() == 36)
									sb.append("and model.tdpCadre.userAddress.district.districtId >= 1  and model.tdpCadre.userAddress.district.districtId <=11");	
								//sb.append("and model.userAddress.state.stateId in (:locationStateIds) ");
							}
							if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)//District
							{
								sb.append("and model.tdpCadre.userAddress.district.districtId in(:districtIds) ");
							}
							if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)//Constituency
							{
								sb.append("and model.tdpCadre.userAddress.constituency.constituencyId in(:constituencyIds) ");
							}
							if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)//Tehsil
							{
								sb.append("and model.tdpCadre.userAddress.tehsil.tehsilId in(:tehsilIds) ");
							}
							if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)//Village
							{
								sb.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:panchayatIds) ");
							}
							if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)//ward
							{
								sb.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:wardIds) ");
							}
							if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)//Town
							{
								sb.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in(:townIds) ");
							}
							if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)//Divison
							{
								sb.append(" and model.tdpCadre.userAddress.ward.constituencyId in(:divisonIds) ");
							}
							
					}
			}
			
			
			Query query = getSession().createQuery(sb.toString());
			if(!searchType.equalsIgnoreCase("name"))
			query.setParameter("searchTypeId",new Long(searchType));
			if(searchValue != null && !searchValue.isEmpty())
				query.setParameter("searchValue",searchValue);
				
			 if(searchType.equalsIgnoreCase("name"))
			 {
				 query.setFirstResult(0);
				 query.setMaxResults(100);
				 if(locationVo != null)
					{
						
						if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)
							query.setParameterList("districtIds", locationVo.getDistrictIdsList());
						if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)
							query.setParameterList("constituencyIds", locationVo.getConstituencyIds());
						if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
							query.setParameterList("tehsilIds", locationVo.getTehsilIdsList());
						if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
							query.setParameterList("panchayatIds", locationVo.getVillageIdsList());
						if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
							query.setParameterList("wardIds", locationVo.getWardIdsList());
						if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
							query.setParameterList("townIds", locationVo.getTownIdsList());
						if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
							query.setParameterList("divisonIds", locationVo.getDivisionIdsList());
					}
			 }
			 
			query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
}
