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
   public List<Object[]> getActivitiesList(String key){
	   Query query = getSession().createQuery("select model.file.synopsysDescription,model.file.synopsysFont.fontId,model.file.fileDate," +
	   		"model.file.userAddress.district.districtId,model.file.userAddress.district.districtName,model.file.userAddress.constituency.constituencyId," +
	   		"model.file.userAddress.constituency.name,cpc.gallary.gallaryId,cpc.gallary.name from ActivityReportFiles model,CandidatePartyCategory cpc where model.activityReport.reportKey = :key " +
	   		"  and model.file.fileId = cpc.candidatePartyFile.file.fileId and cpc.gallary.gallaryId in(select AR.categories from ActivityReport AR where AR.reportKey = :key) " +
	   		" order by cpc.gallary.gallaryId,model.file.userAddress.district.districtName,model.file.userAddress.constituency.name,model.file.fileDate desc");
	   query.setParameter("key", key);
	   return query.list();
   }
}
