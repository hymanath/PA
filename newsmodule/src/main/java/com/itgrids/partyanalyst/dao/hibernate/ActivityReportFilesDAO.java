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
public List<Object[]> getActivitiesList(String key,List<Long> catgList){
	   Query query = getSession().createQuery(" select distinct model.file.synopsysDescription,model.file.synopsysFont.fontId,model.file.fileDate, " +
	   		"  model.file.userAddress.district.districtId,model.file.userAddress.district.districtName,model.file.userAddress.constituency.constituencyId, " +
	   		"  model.file.userAddress.constituency.name,cpc.gallary.gallaryId,cpc.gallary.name from ActivityReportFiles model,CandidatePartyCategory cpc where model.activityReport.reportKey = :key " +
	   		"  and model.file.fileId = cpc.candidatePartyFile.file.fileId and cpc.gallary.gallaryId in(:catgList) " +
	   		"  and model.file.isDeleted = 'N' " +
	   		"  order by cpc.gallary.gallaryId,model.file.userAddress.district.districtName,model.file.userAddress.constituency.name,model.file.fileDate desc");
	   query.setParameter("key", key);
	   query.setParameterList("catgList", catgList);
	   return query.list();
   }
}
