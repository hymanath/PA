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
	   Query query = getSession().createQuery(" select distinct model.file.synopsysDescription,model.file.synopsysFont.fontId,model.file.fileDate, " +
	   		" ua.district.districtId,ua.district.districtName,ua.constituency.constituencyId, " +
	   		"  ua.constituency.name,cpc.gallary.gallaryId,cpc.gallary.name from ActivityReportFiles model,CandidatePartyCategory cpc,UserAddress ua where model.activityReport.reportKey = :key " +
	   		"   and model.file.fileId = cpc.candidatePartyFile.file.fileId and model.file.fileId = ua.file.fileId and (cpc.candidatePartyFile.sourceParty.partyId in(:partyIds) or (cpc.candidatePartyFile.sourceParty.partyId is null and cpc.candidatePartyFile.destinationParty.partyId in(:partyIds)) ) " +
	   		" and cpc.gallary.gallaryId in(:catgList)  and model.file.isDeleted = 'N' " +
	   		"  order by cpc.gallary.gallaryId,ua.district.districtId,ua.constituency.name,model.file.fileDate desc");
	   query.setParameter("key", key);
	   query.setParameterList("catgList", catgList);
	   query.setParameterList("partyIds", partyIds);
	   return query.list();
   }
}
