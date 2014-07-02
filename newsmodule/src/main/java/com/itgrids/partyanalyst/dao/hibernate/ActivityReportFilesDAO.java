package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityReportFilesDAO;
import com.itgrids.partyanalyst.model.ActivityReportFiles;

public class ActivityReportFilesDAO  extends GenericDaoHibernate<ActivityReportFiles, Long>  implements IActivityReportFilesDAO{
   public ActivityReportFilesDAO(){
	   super(ActivityReportFiles.class);
   }
   @SuppressWarnings("unchecked")
public List<Object[]> getActivitiesList(String key,List<Long> catgList,List<Long> partyIds){
	//0 synopsys,1 fontId ,2 date,3 districtId,4 districtName,5 constituencyId,6 constituencyName ,7 gallaryId,8gallary Name,9 fileId,10 fileTitle,11 fontId
	   Query query = getSession().createQuery(" select distinct model.file.fileDescription,model.file.descFont.fontId,model.file.fileDate, " +
	   		" ua.district.districtId,ua.district.districtName,ua.constituency.constituencyId, " +
	   		"  ua.constituency.name,cpc.gallary.gallaryId,cpc.gallary.name,model.file.fileId,model.file.fileTitle,model.file.font.fontId from ActivityReportFiles model,CandidatePartyCategory cpc,UserAddress ua where model.activityReport.reportKey = :key " +
	   		"   and model.file.fileId = cpc.candidatePartyFile.file.fileId and model.file.fileId = ua.file.fileId and (cpc.candidatePartyFile.sourceParty.partyId in(:partyIds) or (cpc.candidatePartyFile.sourceParty.partyId is null and cpc.candidatePartyFile.destinationParty.partyId in(:partyIds)) ) " +
	   		" and cpc.gallary.gallaryId in(:catgList)  and model.file.isDeleted = 'N' " +
	   		"  order by cpc.gallary.gallaryId,ua.district.districtId,ua.constituency.name,model.file.fileDate desc");
	   query.setParameter("key", key);
	   query.setParameterList("catgList", catgList);
	   query.setParameterList("partyIds", partyIds);
	   return query.list();
   }
}
