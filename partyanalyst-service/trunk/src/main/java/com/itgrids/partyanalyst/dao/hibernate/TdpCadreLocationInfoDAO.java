package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreLocationInfoDAO extends GenericDaoHibernate<TdpCadreLocationInfo, Long> implements ITdpCadreLocationInfoDAO{

	public TdpCadreLocationInfoDAO() {
		super(TdpCadreLocationInfo.class);
	}
	
	 public int deleteAllRecords(){
	    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_location_info ");
    	return query.executeUpdate();
    }
    public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_location_info AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
    
public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct ");
				
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  district.districtId,district.districtName as name ,'','','','',model.cadre2014,model.cadre2014Percent," +
						" model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId,constituency.name as name ,'','',constituency.areaType,model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId  as name  ,booth.partNo ,'','','','',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" tehsil.tehsilId , tehsil.tehsilName  as name  ,'RURAL','','','',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" '','','',localElectionBody.localElectionBodyId,localElectionBody.name,'"+IConstants.MUNCIPALITY_CORPORATION_LEVEL+"',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent  ");
			}
			else {
				queryStr.append(" booth.boothId,booth.partNo,'','','','POLLINGSTATION',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" TdpCadreLocationInfo model ");
			//queryStr.append(" left join tdpCadre.userAddress userAddress ");
			//queryStr.append(" left join userAddress.state state ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" ,State state ,District district ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){			
				queryStr.append(" ,District district  ,Constituency constituency ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){			
				queryStr.append(" ,Constituency constituency,LocalElectionBody localElectionBody ,Tehsil tehsil,Booth booth ");
			}else{
				queryStr.append(" ,LocalElectionBody localElectionBody ,Tehsil tehsil ,Booth booth  ");
			}
			queryStr.append(" where  model.type ='Total' ");
			/*if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
				queryStr.append("  (date(model.insertedTime) between :startDate and :endDate) ");
			}*/
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.locationScopeId = 3 and model.locationValue = district.districtId and state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  and district.districtId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						queryStr.append(" and constituency.constituencyId = :parentLocationTypeId ");
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
							queryStr.append(" and model.locationScopeId = 9 and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getAreaType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ) ){
							queryStr.append(" and model.locationScopeId = 7 and model.locationValue = localElectionBody.localElectionBodyId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append("  and localElectionBody.localElectionBodyId = :childLocationTypeId ");
							}
						}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("  and model.locationScopeId = 9 and model.locationValue = booth.boothId and  tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.boothId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and model.locationScopeId = 7 and model.locationValue = localElectionBody.localElectionBodyId and localElectionBody.localElectionBodyId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and localElectionBody.localElectionBodyId = :childLocationTypeId ");
					}
				
				}
			}
			
			if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" tehsil.tehsilId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" localElectionBody.localElectionBodyId ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append(" booth.boothId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" localElectionBody.localElectionBodyId ");
			}
			
			queryStr.append("  ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if( inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if( inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				query.setDate("startDate", format.parse(inputVO.getStartDate()));
				query.setDate("endDate", format.parse(inputVO.getEndDate()));
			}
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
