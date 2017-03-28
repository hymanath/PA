package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityLocationAttendanceDAO extends GenericDaoHibernate<ActivityLocationAttendance, Long> implements IActivityLocationAttendanceDAO{

	public ActivityLocationAttendanceDAO() {
		super(ActivityLocationAttendance.class);
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Object[]> getActivityAttendanceCount(SearchAttributeVO inputVO,String memberType,Long stateId)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getSearchType().equalsIgnoreCase("state"))
		{
			str.append(" select model.activityLocationInfo.constituency.state.stateId, model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("district"))
		{
			str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
		{
			str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append(" select T.tehsilId,T.tehsilName, ");
		}
		
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" select LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" select P.panchayatId,P.panchayatName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append("  select C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
		}
		
		//Count
		str.append(" count(model.attendance.activityLocationPublicAttendance.activityLocationPublicAttendanceId)");
		str.append(",model.attendance.syncSource");
		
		
		str.append(" from ActivityLocationAttendance model ");
		
		if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append("  ,Tehsil T ,Panchayat P ");
		}
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" ,LocalElectionBody LEB, Constituency C  ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" , Panchayat P,Tehsil T ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append(" ,Constituency C ");
		}
		
		str.append(" where model.attendance.attendedTime is not null ");
		
		if(stateId != null && stateId.longValue() == 36L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		
		if(memberType.equalsIgnoreCase("cadre"))
			str.append(" and model.attendance.activityLocationPublicAttendance.tdpCadreId is not null ");
			else
			str.append(" and model.attendance.activityLocationPublicAttendance.tdpCadreId is null ");	
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
		{
			str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
		}
		if(inputVO.getAttributesIdsList() != null)
		{
			str.append(" and model.activityLocationInfo.activityScopeId in(:attributesIdsList)");
		}
		if(inputVO.getSearchType().equalsIgnoreCase("state"))
		{
			str.append(" group by model.attendance.syncSource,model.activityLocationInfo.constituency.state.stateId ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" group by model.attendance.syncSource,model.activityLocationInfo.constituency.district.districtId ");
			}
		else if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
			 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.constituency.constituencyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by  model.attendance.syncSource,P.tehsil.tehsilId ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" and C.constituencyId = model.activityLocationInfo.constituency.constituencyId and " +
						" LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by model.attendance.syncSource,LEB.localElectionBodyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.locationValue");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append(" and model.activityLocationInfo.locationValue = C.constituencyId and LEB.localElectionBodyId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.locationValue");
			}
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAttributesIdsList() != null)
			query.setParameterList("attributesIdsList",inputVO.getAttributesIdsList());
		if(!inputVO.getSearchType().equalsIgnoreCase("state"))
			if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
				query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
		return query.list();
	}
	
	
	public List<Object[]> getActivityLocationsCount(SearchAttributeVO inputVO,Long stateId)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getSearchType().equalsIgnoreCase("state"))
		{
			str.append(" select model.activityLocationInfo.constituency.state.stateId, model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("district"))
		{
			str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
		{
			str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append(" select T.tehsilId,T.tehsilName, ");
		}
		
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" select LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" select P.panchayatId,P.panchayatName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append("  select C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
		}
		//Count
		str.append(" count(distinct model.activityLocationInfo.locationValue)");
		str.append(" from ActivityLocationAttendance model ");
		
		if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append("  ,Tehsil T ,Panchayat P ");
		}
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" ,LocalElectionBody LEB, Constituency C  ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" , Panchayat P ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append(" ,Constituency C ");
		}
		
		str.append(" where model.attendance.attendedTime is not null ");
		
		if(stateId != null && stateId.longValue() == 36L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
		{
			str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
		}
		if(inputVO.getAttributesIdsList() != null)
		{
			str.append(" and model.activityLocationInfo.activityScopeId in(:attributesIdsList)");
		}
		if(inputVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
			str.append(" group by model.activityLocationInfo.constituency.state.stateId ");
		}
		 else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" group by model.activityLocationInfo.constituency.district.districtId ");
			}
		 else if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
			 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.activityLocationInfo.constituency.constituencyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by P.tehsil.tehsilId ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by LEB.localElectionBodyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) group by model.activityLocationInfo.locationValue");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append(" and model.activityLocationInfo.locationValue = C.constituencyId and LEB.localElectionBodyId in (:locationIdsList) group by model.activityLocationInfo.locationValue");
			}
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAttributesIdsList() != null)
			query.setParameterList("attributesIdsList",inputVO.getAttributesIdsList());
		if(!inputVO.getSearchType().equalsIgnoreCase("state"))
			if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
				query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
		return query.list();
	}
	
	
	
	public List<Object[]> getDayWiseActivityLocationsCount(SearchAttributeVO inputVO,String memberType,Long stateId)
	{
	
			StringBuilder str = new StringBuilder();
			if(inputVO.getSearchType().equalsIgnoreCase("state"))
			{
				str.append(" select model.activityLocationInfo.constituency.state.stateId, model.activityLocationInfo.constituency.state.stateName, ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase("district"))
			{
				str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
			{
				str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" select T.tehsilId,T.tehsilName, ");
			}
			
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" select LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" select P.panchayatId,P.panchayatName, ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append("  select C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
			}
			
			//Count
			str.append(" count(model.attendance.activityLocationPublicAttendance.activityLocationPublicAttendanceId)");
			str.append(",model.attendance.syncSource,date(model.attendance.attendedTime)");
			
			
			str.append(" from ActivityLocationAttendance model ");
			
			if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append("  ,Tehsil T ,Panchayat P ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" ,LocalElectionBody LEB, Constituency C  ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" , Panchayat P,Tehsil T ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append(" ,Constituency C ");
			}
			
			str.append(" where model.attendance.attendedTime is not null ");
			
			if(stateId != null && stateId.longValue() == 36L)
				str.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				str.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
			
			
			if(memberType.equalsIgnoreCase("cadre"))
			str.append(" and model.attendance.activityLocationPublicAttendance.tdpCadreId is not null ");
			else
			str.append(" and model.attendance.activityLocationPublicAttendance.tdpCadreId is null ");	
			if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			{
				str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
			}
			if(inputVO.getAttributesIdsList() != null)
			{
				str.append(" and model.activityLocationInfo.activityScopeId in(:attributesIdsList)");
			}
			if(inputVO.getSearchType().equalsIgnoreCase("state"))
			{
				str.append(" group by model.attendance.syncSource,model.activityLocationInfo.constituency.state.stateId,date(model.attendance.attendedTime) ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.constituency.district.districtId,date(model.attendance.attendedTime) ");
				}
			else if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
				 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					str.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.constituency.constituencyId,date(model.attendance.attendedTime) ");
				}
				else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and P.tehsil.tehsilId in (:locationIdsList) group by  model.attendance.syncSource,P.tehsil.tehsilId,date(model.attendance.attendedTime) ");
				}
				else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					str.append(" and C.constituencyId = model.activityLocationInfo.constituency.constituencyId and " +
							" LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and LEB.localElectionBodyId in (:locationIdsList) group by model.attendance.syncSource,LEB.localElectionBodyId,date(model.attendance.attendedTime) ");
				}
				else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					str.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.locationValue,date(model.attendance.attendedTime)");
				}
				else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					str.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.locationValue,date(model.attendance.attendedTime)");
				}
			}
			Query query = getSession().createQuery(str.toString());
			if(inputVO.getAttributesIdsList() != null)
				query.setParameterList("attributesIdsList",inputVO.getAttributesIdsList());
			if(!inputVO.getSearchType().equalsIgnoreCase("state"))
				if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
					query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
			if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
				query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
			return query.list();
		
	}
	
	public List<Object[]> getActivityDetailsByTdpCadreId(Long cadreId){
		
		/*Query query = getSession().createQuery(" select model.activityLocationAttendanceId, " +
							" model.activityLocationInfo.activityLocationInfoId, " +
							" model.activityLocationInfo.activityScope.activityLevelId, " +
							" model.attendance.tdpCadre.tdpCadreId " +
							" from ActivityLocationAttendance model " +
							" where model.attendance.tdpCadre.tdpCadreId = :cadreId ");*/
		/*Query query = getSession().createQuery(" select model.activityLocationInfo.activityScope.activityLevelId, " +
							" count(model.activityLocationInfo.activityLocationInfoId) " +
							" from ActivityLocationAttendance model " +
							" where model.attendance.tdpCadre.tdpCadreId = :cadreId " +
							" group by model.activityLocationInfo.activityScope.activityLevelId ");*/
		
		Query query = getSession().createSQLQuery("  select  distinct ass.activity_level_id, count(ali.activity_location_info_id) from  activity_location_info ali,"
		+" activity_scope ass, activity_level al,activity_location_attendance ala,"
		+" attendance a, tdp_cadre td, user_address ua "
		+" left join district d on ua.district_id = d.district_id"
		+" left join  constituency c on ua.constituency_id = c.constituency_id"
		+" left join  constituency w on ua.ward = w.constituency_id"
		+" left join   panchayat p on ua.panchayat_id = p.panchayat_id"
		+" left join  local_election_body leb on ua.local_election_body = leb.local_election_body_id "
		+" left join tehsil t on ua.tehsil_id = t.tehsil_id"
		
		+" where "
		+" ali.activity_scope_id = ass.activity_scope_id and "
		+" ass.activity_level_id = al.activity_level_id and"
		+" td.address_id = ua.user_address_id and "
		+" a.tdp_cadre_id = td.tdp_cadre_id and "
		+" td.tdp_cadre_id = :cadreId and"
		+" ali.activity_location_info_id = ala.activity_location_info_id and"
		+" ala.attendance_id = a.attendance_id "
		+" group by "
		+" ass.activity_level_id ");
		
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	
	public List<Object[]> getCadreAttendedActivityDetails(List<Long> activityLocationInfoIds,Long cadreId){
		Query query = getSession().createQuery(" select model.activityLocationAttendanceId, " +
							" model.activityLocationInfo.activityScope.activityScopeId " +
							" from ActivityLocationAttendance model " +
							" where model.activityLocationInfoId in (:activityLocationInfoIds) " +
							" and model.attendance.tdpCadre.tdpCadreId = :cadreId ");
		query.setParameterList("activityLocationInfoIds", activityLocationInfoIds);
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	
	public List<Long> getActivityInfoIdsByCadreIdFromAttendence(Long locationLevel,Long cadreId){
		Query query = getSession().createQuery(" select distinct  model.activityLocationInfoId from ActivityLocationAttendance model " +
							" where model.attendance.tdpCadre.tdpCadreId = :cadreId and model.activityLocationInfo.locationLevel =:locationLevel ");
		query.setParameter("cadreId", cadreId);
		query.setParameter("locationLevel", locationLevel);
		return query.list();
	}
}
