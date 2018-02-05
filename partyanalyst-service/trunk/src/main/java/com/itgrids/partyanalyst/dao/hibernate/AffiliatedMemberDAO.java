package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.model.AffiliatedMember;

public class AffiliatedMemberDAO extends GenericDaoHibernate<AffiliatedMember, Long> implements IAffiliatedMemberDAO {

	public AffiliatedMemberDAO(){
		super(AffiliatedMember.class);
	}
	  public List<Object[]> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValues){
					
				StringBuilder sb=new StringBuilder();
				
				sb.append(" SELECT  model.tdpCadreId ,model.mobileNo," +
						"   model.memberShipNo,voter.voterId voter.voterIDCardNo,model.relativename,  model. houseNo, model.casteState.caste.casteName,model.firstname,model.casteStateId " +
						"   FROM  TdpCadre model LEFT JOIN model.voter voter" +
						"   WHERE   model.voterId=model.voterId and model.isDeleted='N' ");
				if(searchType.equalsIgnoreCase("mobileno")){
					
					sb.append(" AND model.mobileNo = :searchValue ");
					
				}else if(searchType.equalsIgnoreCase("mebershipno")){
					
					sb.append(" AND model.memberShipNo = :searchValue ");
					
				}else if(searchType.equalsIgnoreCase("votercardno")){
					
					sb.append(" AND voter.voterIDCardNo = :searchValue ");
				}

				if( locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue()>0){
	    			    			
	    			if(locationType.equalsIgnoreCase("constituency")){	    			
	    				sb.append(" and model.userAddress.constituency.constituencyId =:locationValues ");
	    			}else if(locationType.equalsIgnoreCase("mandal")){
	    				sb.append(" and model.userAddress.tehsil.tehsilId =:locationValues ");
	    			}else if(locationType.equalsIgnoreCase("panchayat")){
	    				sb.append(" and model.userAddress.panchayat.panchayatId =:locationValues ");
	    			}else if(locationType.equalsIgnoreCase("panchayat")){
	    				sb.append(" and model.userAddress.panchayat.panchayatId =:locationValues ");
	    			}
	    			else if(locationType.equalsIgnoreCase("muncipality")){
	    				sb.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValues ");
	    			}
			
				}	
				Query query = getSession().createQuery(sb.toString());
				
				if(locationType != null && locationType.trim().length() > 0 && locationValues != null && locationValues.longValue()>0){
					   query.setParameter("locationValues",locationValues);
				 }
				
				if(searchType != null && searchType.trim().length() > 0){
					   query.setParameter("searchType",searchType);
				 }
				
				if(searchValue != null && searchValue.trim().length() > 0){
					   query.setParameter("searchValue",searchValue);
				 }
				return query.list();
				
			
	  }
	  }