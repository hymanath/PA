package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreComminicationSkillsStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreLeadershipLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreLeadershipSkillsStatusDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievement;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampBatchAttendeeDAOHibernateTest extends BaseDaoTestCase{
	
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO;
    private ITrainingCampCadreAchievementDAO trainingCampCadreAchievementDAO;
    private ITrainingCampCadreGoalDAO trainingCampCadreGoalDAO;
    private ICadreLeadershipLevelDAO cadreLeadershipLevelDAO; 
    private ICadreComminicationSkillsStatusDAO cadreComminicationSkillsStatusDAO; 
    private ICadreLeadershipSkillsStatusDAO cadreLeadershipSkillsStatusDAO; 
    private ICadreHealthStatusDAO cadreHealthStatusDAO;
    private TransactionTemplate transactionTemplate=new TransactionTemplate();
    
	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}
	
	public void setTrainingCampCadreFeedbackDetailsDAO(
			ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO) {
		this.trainingCampCadreFeedbackDetailsDAO = trainingCampCadreFeedbackDetailsDAO;
	}
	public void setTrainingCampCadreAchievementDAO(
			ITrainingCampCadreAchievementDAO trainingCampCadreAchievementDAO) {
		this.trainingCampCadreAchievementDAO = trainingCampCadreAchievementDAO;
	}
	public void setTrainingCampCadreGoalDAO(
			ITrainingCampCadreGoalDAO trainingCampCadreGoalDAO) {
		this.trainingCampCadreGoalDAO = trainingCampCadreGoalDAO;
	}
	
	public void setCadreLeadershipLevelDAO(
			ICadreLeadershipLevelDAO cadreLeadershipLevelDAO) {
		this.cadreLeadershipLevelDAO = cadreLeadershipLevelDAO;
	}

	public void setCadreComminicationSkillsStatusDAO(
			ICadreComminicationSkillsStatusDAO cadreComminicationSkillsStatusDAO) {
		this.cadreComminicationSkillsStatusDAO = cadreComminicationSkillsStatusDAO;
	}

	public void setCadreLeadershipSkillsStatusDAO(
			ICadreLeadershipSkillsStatusDAO cadreLeadershipSkillsStatusDAO) {
		this.cadreLeadershipSkillsStatusDAO = cadreLeadershipSkillsStatusDAO;
	}

	

	//TEST CASES
	public void test(){
		
		List<String> achieveList=new ArrayList<String>();
		achieveList.add("podam");
		achieveList.add("povadu");//5161994l
		ResultStatus resultStatus=saveDetailsOfCadre(5161993l,1l,achieveList,2l,2l,1l,0l,"haghaghaghjhjshj",40l);
			
	}
	public ResultStatus saveDetailsOfCadre(final Long tdpCadreId,final Long batchId,final List<String> achieveList,final Long leaderShipLevelId,final Long communicationSkillsId,final Long leaderShipSkillsId,final Long healthId,final String comments,final Long userId)
	{
		final ResultStatus resultStatus=new ResultStatus();
		try{
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			   public void doInTransactionWithoutResult(TransactionStatus arg0) {
					  
				  
					//feedback details saving or updating.
					TrainingCampCadreFeedbackDetails feedBackDetails=null;
					Long trainingCampCadreFeedBackDetailsId=trainingCampCadreFeedbackDetailsDAO.checkFeedBackForCadreBycadreAndBatch(tdpCadreId,batchId);
					if(trainingCampCadreFeedBackDetailsId!=null){//update
						feedBackDetails=trainingCampCadreFeedbackDetailsDAO.get(trainingCampCadreFeedBackDetailsId);
						
					}else{//save
						feedBackDetails=new TrainingCampCadreFeedbackDetails();
						feedBackDetails.setTdpCadreId(tdpCadreId);
						feedBackDetails.setTrainingCampBatchId(batchId);
					    feedBackDetails.setInsertedBy(userId);
					    feedBackDetails.setUpdatedBy(userId);
					    Date date=new DateUtilService().getCurrentDateAndTime();
					    feedBackDetails.setInsertedTime(date);
					    feedBackDetails.setUpdatedTime(date);
					}
					feedBackDetails.setCadreLeadershipLevelId(leaderShipLevelId!=0l?leaderShipLevelId:null);
					feedBackDetails.setCadreComminicationSkillsStatusId(communicationSkillsId!=0l?communicationSkillsId:null);
					feedBackDetails.setCadreLeadershipSkillsStatusId(leaderShipSkillsId!=0l?leaderShipSkillsId:null);
					feedBackDetails.setCadreHealthStatusId(healthId!=0l?healthId:null);
					feedBackDetails.setRemarks(comments.trim().length()>0?comments:null);
					trainingCampCadreFeedbackDetailsDAO.save(feedBackDetails);
					
					
					
					//Achievement details saving or updating.
					Long achieveCount=trainingCampCadreAchievementDAO.checkAchievementsForCadreBycadreAndBatch(tdpCadreId,batchId);
					if(achieveCount==0){ //save
						
					}else{//update=delete+save
						trainingCampCadreAchievementDAO.deleteAchievementsforACadre(tdpCadreId,batchId);
					}
					if(achieveList!=null && achieveList.size()>0){
						
						for(String achieve:achieveList){
							
							TrainingCampCadreAchievement tca=new TrainingCampCadreAchievement();
							tca.setTdpCadreId(tdpCadreId);
							tca.setTrainingCampBatchId(batchId);
							tca.setAchievement(achieve);
							tca.setInsertedBy(userId);
							tca.setUpdatedBy(userId);
							Date date=new DateUtilService().getCurrentDateAndTime();
							tca.setInsertedTime(date);
							tca.setUpdatedTime(date);
							trainingCampCadreAchievementDAO.save(tca);
						}
					}
		  }
		});
		resultStatus.setResultCode(1);	
		}catch(Exception e){
			//LOG.error(" Error Occured in getDocsOfPartyMeetingId" ,e);
			e.printStackTrace();
			resultStatus.setResultCode(0);
		}
		return resultStatus;
	}
}
