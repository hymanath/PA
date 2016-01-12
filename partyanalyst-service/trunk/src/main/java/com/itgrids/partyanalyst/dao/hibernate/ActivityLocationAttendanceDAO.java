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
	
	
	public List<Object[]> getActivityAttendanceCount(SearchAttributeVO inputVO,String memberType)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getSearchType().equalsIgnoreCase("district"))
		{
			str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName ");
		}
		if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
		{
			str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append(" T.tehsilId,T.tehsilName, ");
		}
		
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" P.panchayatId,P.panchayatName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append("  C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
		}
		
		//Count
		str.append(" ,count(model.attendance.activityLocationPublicAttendance.activityLocationPublicAttendanceId)");
		str.append(",model.attendance.syncSource");
		
		
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
		
		str.append(" where model.activityDocument.activityDate is not null ");
		if(memberType.equalsIgnoreCase("cadre"))
		str.append(" and model.tdpCadreId is not null ");
		else
		str.append(" and model.tdpCadreId is null ");	
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
		{
			str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
		}
		if(inputVO.getAttributesIdsList() != null)
		{
			str.append(" and model.activityLocationInfo.activityScopeId in(:attributesIdsList)");
		}
		 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" group by model.attendance.syncSource,model.activityLocationInfo.constituency.district.districtId ");
			}
		if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
			 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.attendance.syncSource,model.activityLocationInfo.constituency.constituencyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by  model.attendance.syncSource,P.tehsil.tehsilId ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by model.attendance.syncSource,LEB.localElectionBodyId ");
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
		if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
			query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
		return query.list();
	}
	

}
