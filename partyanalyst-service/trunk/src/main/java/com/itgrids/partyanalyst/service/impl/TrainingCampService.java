package com.itgrids.partyanalyst.service.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBatchStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreComminicationSkillsStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreLeadershipLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreLeadershipSkillsStatusDAO;
import com.itgrids.partyanalyst.dao.ICampCallPurposeDAO;
import com.itgrids.partyanalyst.dao.ICampCallStatusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingUserDAO;
import com.itgrids.partyanalyst.dao.IScheduleInviteeStatusDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementHistoryDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsHistoryDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalHistoryDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDistrictDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeTrackDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserProgramDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserRelationDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampUserTypeDAO;
import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreFeedbackVO;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievement;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievementHistory;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetailsHistory;
import com.itgrids.partyanalyst.model.TrainingCampCadreGoal;
import com.itgrids.partyanalyst.model.TrainingCampCadreGoalHistory;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
class TrainingCampService implements ITrainingCampService{

	public static Logger LOG = Logger.getLogger(TrainingCampService.class);
	private ITrainingCampScheduleInviteeCallerDAO 	trainingCampScheduleInviteeCallerDAO;
	private ITrainingCampDAO 						trainingCampDAO;
	private ITrainingCampDistrictDAO 				trainingCampDistrictDAO;
	private IScheduleInviteeStatusDAO 				scheduleInviteeStatusDAO;
	private ITrainingCampScheduleDAO 				trainingCampScheduleDAO;
	private ITrainingCampScheduleInviteeTrackDAO 	trainingCampScheduleInviteeTrackDAO;
	private ITrainingCampProgramDAO 				trainingCampProgramDAO;
	private ITrainingCampBatchDAO 					trainingCampBatchDAO;
	private IBatchStatusDAO 						batchStatusDAO;
	private ITrainingCampScheduleInviteeDAO 		trainingCampScheduleInviteeDAO;
	private ITrainingCampUserDAO 					trainingCampUserDAO;
	private ITrainingCampUserTypeDAO 				trainingCampUserTypeDAO;
	private ICampCallPurposeDAO 					campCallPurposeDAO;
	private ICampCallStatusDAO 						campCallStatusDAO;
	private TransactionTemplate            			transactionTemplate;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private DateUtilService dateUtilService;
	private IVoterDAO voterDAO;
	private IPartyMeetingUserDAO partyMeetingUserDAO;
	private IPartyMeetingTypeDAO partyMeetingTypeDAO;
	private IPartyMeetingDAO partyMeetingDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IUserAddressDAO userAddressDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IWardDAO wardDAO;
	private IPanchayatDAO panchayatDAO;
	private ITrainingCampUserRelationDAO trainingCampUserRelationDAO;
	private IPartyMeetingUserAccessLevelDAO		   partyMeetingUserAccessLevelDAO;
	private IUserAccessLevelValueDAO   			   userAccessLevelValueDAO;
	private IPartyMeetingLevelDAO				   partyMeetingLevelDAO;
	private IDistrictConstituenciesDAO			   districtConstituenciesDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IPartyMeetingMinuteDAO partyMeetingMinuteDAO;
	private IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO;
	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO;
    private ITrainingCampCadreAchievementDAO trainingCampCadreAchievementDAO;
    private ITrainingCampCadreGoalDAO trainingCampCadreGoalDAO;
    private IUserDAO userDAO;
	private ICadreLeadershipLevelDAO cadreLeadershipLevelDAO; 
    private ICadreComminicationSkillsStatusDAO cadreComminicationSkillsStatusDAO; 
    private ICadreLeadershipSkillsStatusDAO cadreLeadershipSkillsStatusDAO; 
    private ICadreHealthStatusDAO cadreHealthStatusDAO;

    private ICadreCommitteeService cadreCommitteeService;
    
    private ITrainingCampCadreFeedbackDetailsHistoryDAO trainingCampCadreFeedbackDetailsHistoryDAO;
    private ITrainingCampCadreAchievementHistoryDAO trainingCampCadreAchievementHistoryDAO;
    private ITrainingCampCadreGoalHistoryDAO trainingCampCadreGoalHistoryDAO;
    private ITrainingCampUserProgramDAO trainingCampUserProgramDAO;
    private IPartyMeetingService partyMeetingService;
    private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
    private IDelimitationConstituencyDAO delimitationConstituencyDAO; 
    
	
	

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}

	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}

	public IPartyMeetingLevelDAO getPartyMeetingLevelDAO() {
		return partyMeetingLevelDAO;
	}

	public void setPartyMeetingLevelDAO(IPartyMeetingLevelDAO partyMeetingLevelDAO) {
		this.partyMeetingLevelDAO = partyMeetingLevelDAO;
	}

	public IPartyMeetingUserAccessLevelDAO getPartyMeetingUserAccessLevelDAO() {
		return partyMeetingUserAccessLevelDAO;
	}

	public void setPartyMeetingUserAccessLevelDAO(
			IPartyMeetingUserAccessLevelDAO partyMeetingUserAccessLevelDAO) {
		this.partyMeetingUserAccessLevelDAO = partyMeetingUserAccessLevelDAO;
	}

	

	public IUserAccessLevelValueDAO getUserAccessLevelValueDAO() {
		return userAccessLevelValueDAO;
	}

	public void setUserAccessLevelValueDAO(
			IUserAccessLevelValueDAO userAccessLevelValueDAO) {
		this.userAccessLevelValueDAO = userAccessLevelValueDAO;
	}
	
	public IPartyMeetingDocumentDAO getPartyMeetingDocumentDAO() {
		return partyMeetingDocumentDAO;
	}

	public void setPartyMeetingDocumentDAO(
			IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}

	public IPartyMeetingMinuteDAO getPartyMeetingMinuteDAO() {
		return partyMeetingMinuteDAO;
	}

	public void setPartyMeetingMinuteDAO(
			IPartyMeetingMinuteDAO partyMeetingMinuteDAO) {
		this.partyMeetingMinuteDAO = partyMeetingMinuteDAO;
	}

	public IPartyMeetingAtrPointDAO getPartyMeetingAtrPointDAO() {
		return partyMeetingAtrPointDAO;
	}

	public void setPartyMeetingAtrPointDAO(
			IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO) {
		this.partyMeetingAtrPointDAO = partyMeetingAtrPointDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

	public IPartyMeetingTypeDAO getPartyMeetingTypeDAO() {
		return partyMeetingTypeDAO;
	}

	public void setPartyMeetingTypeDAO(IPartyMeetingTypeDAO partyMeetingTypeDAO) {
		this.partyMeetingTypeDAO = partyMeetingTypeDAO;
	}

	public IPartyMeetingUserDAO getPartyMeetingUserDAO() {
		return partyMeetingUserDAO;
	}

	public void setPartyMeetingUserDAO(IPartyMeetingUserDAO partyMeetingUserDAO) {
		this.partyMeetingUserDAO = partyMeetingUserDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}

	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}

	public void setTrainingCampDAO(ITrainingCampDAO trainingCampDAO) {
		this.trainingCampDAO = trainingCampDAO;
	}

	public void setTrainingCampDistrictDAO(
			ITrainingCampDistrictDAO trainingCampDistrictDAO) {
		this.trainingCampDistrictDAO = trainingCampDistrictDAO;
	}

	public void setScheduleInviteeStatusDAO(
			IScheduleInviteeStatusDAO scheduleInviteeStatusDAO) {
		this.scheduleInviteeStatusDAO = scheduleInviteeStatusDAO;
	}

	public void setTrainingCampScheduleDAO(
			ITrainingCampScheduleDAO trainingCampScheduleDAO) {
		this.trainingCampScheduleDAO = trainingCampScheduleDAO;
	}

	public void setTrainingCampScheduleInviteeTrackDAO(
			ITrainingCampScheduleInviteeTrackDAO trainingCampScheduleInviteeTrackDAO) {
		this.trainingCampScheduleInviteeTrackDAO = trainingCampScheduleInviteeTrackDAO;
	}

	public void setTrainingCampProgramDAO(
			ITrainingCampProgramDAO trainingCampProgramDAO) {
		this.trainingCampProgramDAO = trainingCampProgramDAO;
	}

	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}

	public void setBatchStatusDAO(IBatchStatusDAO batchStatusDAO) {
		this.batchStatusDAO = batchStatusDAO;
	}

	public void setTrainingCampScheduleInviteeDAO(
			ITrainingCampScheduleInviteeDAO trainingCampScheduleInviteeDAO) {
		this.trainingCampScheduleInviteeDAO = trainingCampScheduleInviteeDAO;
	}

	public void setTrainingCampUserDAO(ITrainingCampUserDAO trainingCampUserDAO) {
		this.trainingCampUserDAO = trainingCampUserDAO;
	}

	public void setTrainingCampUserTypeDAO(
			ITrainingCampUserTypeDAO trainingCampUserTypeDAO) {
		this.trainingCampUserTypeDAO = trainingCampUserTypeDAO;
	}

	public void setCampCallPurposeDAO(ICampCallPurposeDAO campCallPurposeDAO) {
		this.campCallPurposeDAO = campCallPurposeDAO;
	}

	public void setCampCallStatusDAO(ICampCallStatusDAO campCallStatusDAO) {
		this.campCallStatusDAO = campCallStatusDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public void setTrainingCampScheduleInviteeCallerDAO(
			ITrainingCampScheduleInviteeCallerDAO trainingCampScheduleInviteeCallerDAO) {
		this.trainingCampScheduleInviteeCallerDAO = trainingCampScheduleInviteeCallerDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	
	public ITrainingCampUserRelationDAO getTrainingCampUserRelationDAO() {
		return trainingCampUserRelationDAO;
	}

	public void setTrainingCampUserRelationDAO(
			ITrainingCampUserRelationDAO trainingCampUserRelationDAO) {
		this.trainingCampUserRelationDAO = trainingCampUserRelationDAO;
	}
    
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

	public void setCadreHealthStatusDAO(ICadreHealthStatusDAO cadreHealthStatusDAO) {
		this.cadreHealthStatusDAO = cadreHealthStatusDAO;
	}
	
	public void setTrainingCampCadreFeedbackDetailsHistoryDAO(
			ITrainingCampCadreFeedbackDetailsHistoryDAO trainingCampCadreFeedbackDetailsHistoryDAO) {
		this.trainingCampCadreFeedbackDetailsHistoryDAO = trainingCampCadreFeedbackDetailsHistoryDAO;
	}

	public void setTrainingCampCadreAchievementHistoryDAO(
			ITrainingCampCadreAchievementHistoryDAO trainingCampCadreAchievementHistoryDAO) {
		this.trainingCampCadreAchievementHistoryDAO = trainingCampCadreAchievementHistoryDAO;
	}

	public void setTrainingCampCadreGoalHistoryDAO(
			ITrainingCampCadreGoalHistoryDAO trainingCampCadreGoalHistoryDAO) {
		this.trainingCampCadreGoalHistoryDAO = trainingCampCadreGoalHistoryDAO;
	}
    
	public void setTrainingCampUserProgramDAO(
			ITrainingCampUserProgramDAO trainingCampUserProgramDAO) {
		this.trainingCampUserProgramDAO = trainingCampUserProgramDAO;
	}
	
	public List<BasicVO> getAllPrograms()
	{
		try{
			List<Object[]> programs = trainingCampProgramDAO.getPrograms();
			return getBasicList1(programs);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public List<IdNameVO> getAllTrainingCampsInfoByDistrictIds(List<Long> districtIds)
	{
		List<IdNameVO> trainingCampsList = null;
		try {
			List<Object[]> trainingCamps = trainingCampDistrictDAO.getCampDetailsByDistrictIds(districtIds);
			if(trainingCamps != null && trainingCamps.size()>0){
				trainingCampsList = new ArrayList<IdNameVO>();
				for (Object[] camp : trainingCamps) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					trainingCampsList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return trainingCampsList;
	}
	
	public List<IdNameVO> getProgrammesDetailsByCamps(List<Long> campIdsList)
	{
		List<IdNameVO> programmesList = null;
		try {
			List<Object[]> programmes = trainingCampScheduleDAO.getProgrammesListByCampsList(campIdsList);
			if(programmes != null && programmes.size()>0){
				programmesList = new ArrayList<IdNameVO>();
				for (Object[] camp : programmes) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					programmesList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return programmesList;
	}
	
	public List<IdNameVO> getScheduledDetailsByProgrammes(List<Long> programIds)
	{
		List<IdNameVO> scheduleList = null;
		try {
			List<Object[]> programmes = trainingCampScheduleDAO.getScheduledDetailsByProgrammes(programIds);
			if(programmes != null && programmes.size()>0){
				scheduleList = new ArrayList<IdNameVO>();
				for (Object[] camp : programmes) {
					Long campId = commonMethodsUtilService.getLongValueForObject(camp[0]);
					String campName = commonMethodsUtilService.getStringValueForObject(camp[1]);
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(campId);
					idNameVO.setName(campName);
					scheduleList.add(idNameVO);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleInfo method in TrainingCampService class.",e);
		}
		return scheduleList;
	}
	
	public Long getAvailableCountForMemberConfirmation(Long scheduleId)
	{
		Long availableCount = 0L;
		try {
			availableCount = trainingCampScheduleInviteeDAO.getAvailableInvitedCandidatesListByScheduleIdAndCount(scheduleId);
			if(availableCount != null && availableCount.longValue()>0L)
			{
				Long alreadyAssignedCount = trainingCampScheduleInviteeDAO.getAreadyAssignedCandidatesListByScheduleIdAndCount(scheduleId);
				if(alreadyAssignedCount != null && alreadyAssignedCount.longValue()>0L){
					availableCount = availableCount - alreadyAssignedCount;
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getAvailableCountForMemberConfirmation method in TrainingCampService class.",e);
		}
		return availableCount;
	}
	
	public TrainingMemberVO getAvailableMembersCountDetails(Long scheduleId,Long callerId)
	{
		TrainingMemberVO returnVO = new TrainingMemberVO();
		try {
			List<Long> callerIdsList = new ArrayList<Long>();
			callerIdsList.add(callerId);
			List<Long> alreadyInvitedMemberIdsForCallerList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation(callerIdsList, scheduleId,null, IConstants.INVITATION, IConstants.INTERESTED);
			List<Long> AllalreadyInvitedMemberIdsForCallerList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation( null, scheduleId,null, IConstants.INVITATION, IConstants.INTERESTED);
			
			if(alreadyInvitedMemberIdsForCallerList != null && alreadyInvitedMemberIdsForCallerList.size()>0)
				returnVO.setAvailableCount(commonMethodsUtilService.getIntegerToLong(alreadyInvitedMemberIdsForCallerList.size()));
			if(AllalreadyInvitedMemberIdsForCallerList != null && AllalreadyInvitedMemberIdsForCallerList.size()>0)
				returnVO.setTotalCount(commonMethodsUtilService.getIntegerToLong(AllalreadyInvitedMemberIdsForCallerList.size()));
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getAvailableMembersCountDetails method in TrainingCampService class.",e);
			returnVO = null;
		}
		return returnVO;
	}
	
	public ResultStatus assignMembersToCallerForMemberConfirmation(final Long userId, final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId,final List<TrainingCampVO> locationTypeList)
	{
		ResultStatus status  = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					if(locationTypeList != null && locationTypeList.size()>0)
					{
						List<Long> inviteeIdsList = new ArrayList<Long>(0);
						List<Long> alreadyInvitedMemberIdsList = new ArrayList<Long>(0);
						for (TrainingCampVO trainingCampVO : locationTypeList) {
							if(trainingCampVO.getLocationTypeId().longValue() == IConstants.DISTRICT_SCOPE_ID)
							{
								List<TrainingCampVO> districtsIdsVoList = trainingCampVO.getTrainingCampVOList();
								List<Long> districtIdsList = new ArrayList<Long>(0);
								if(districtsIdsVoList != null && districtsIdsVoList.size()>0)
								{
									for (TrainingCampVO districtVO : districtsIdsVoList) {
										districtIdsList.add(districtVO.getId());
									}
								}
								List<Long> distirctInviteeIdsList = trainingCampScheduleInviteeDAO.getScheduleWiseInviteesListByLocationIdLocationType(scheduleId, trainingCampVO.getLocationTypeId().longValue(), districtIdsList);
								inviteeIdsList.addAll(distirctInviteeIdsList);
							}					
							if(trainingCampVO.getLocationTypeId().longValue() == IConstants.CONSTITUENCY_SCOPE_ID)
							{
								List<TrainingCampVO> constituencysIdsVoList = trainingCampVO.getTrainingCampVOList();
								List<Long> constituencyIdsList = new ArrayList<Long>(0);
								if(constituencysIdsVoList != null && constituencysIdsVoList.size()>0)
								{
									for (TrainingCampVO districtVO : constituencysIdsVoList) {
										constituencyIdsList.add(districtVO.getId());
									}
								}
								List<Long>  constiinviteeIdsList = trainingCampScheduleInviteeDAO.getScheduleWiseInviteesListByLocationIdLocationType(scheduleId, trainingCampVO.getLocationTypeId().longValue(), constituencyIdsList);
								inviteeIdsList.addAll(constiinviteeIdsList);
							}
							if(trainingCampVO.getLocationTypeId().longValue() == IConstants.TEHSIL_SCOPE_ID)
							{
								List<TrainingCampVO> tehsilsIdsVoList = trainingCampVO.getTrainingCampVOList();
								List<Long> tehsilIdsList = new ArrayList<Long>(0);
								if(tehsilsIdsVoList != null && tehsilsIdsVoList.size()>0)
								{
									for (TrainingCampVO districtVO : tehsilsIdsVoList) {
										tehsilIdsList.add(districtVO.getId());
									}
								}
								List<Long>  tehsilinviteeIdsList = trainingCampScheduleInviteeDAO.getScheduleWiseInviteesListByLocationIdLocationType(scheduleId, trainingCampVO.getLocationTypeId().longValue(), tehsilIdsList);
								inviteeIdsList.addAll(tehsilinviteeIdsList);
							}
							if(trainingCampVO.getLocationTypeId().longValue() == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID)
							{
								List<TrainingCampVO> municipalityList = trainingCampVO.getTrainingCampVOList();
								List<Long> municipalityIdsList = new ArrayList<Long>(0);
								if(municipalityList != null && municipalityList.size()>0)
								{
									for (TrainingCampVO munivipalityVo : municipalityList) {
										municipalityIdsList.add(munivipalityVo.getId());
									}
								}
								List<Long> munipaliteeIdsList = trainingCampScheduleInviteeDAO.getScheduleWiseInviteesListByLocationIdLocationType(scheduleId, trainingCampVO.getLocationTypeId().longValue(), municipalityIdsList);
								inviteeIdsList.addAll(munipaliteeIdsList);
							}
						}
						
						if(inviteeIdsList != null && inviteeIdsList.size()>0)
						{
							alreadyInvitedMemberIdsList = trainingCampScheduleInviteeCallerDAO.getAlreadyInvitedMembersInviteeIdsListByScheduleId(scheduleId,callPurposeId);
							if(inviteeIdsList != null && inviteeIdsList.size()>0)
							{
								DateUtilService dateutilService = new DateUtilService();
								if(alreadyInvitedMemberIdsList == null) alreadyInvitedMemberIdsList = new ArrayList<Long>(0);
								for (Long trainingCampScheduleInviteeId : inviteeIdsList) {
									if(!alreadyInvitedMemberIdsList.contains(trainingCampScheduleInviteeId))
									{
										TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = new TrainingCampScheduleInviteeCaller();
										trainingCampScheduleInviteeCaller.setTrainingCampScheduleInviteeId(trainingCampScheduleInviteeId);
										trainingCampScheduleInviteeCaller.setTrainingCampCallerAdminId(userId);
										trainingCampScheduleInviteeCaller.setCallPurposeId(callPurposeId);
										trainingCampScheduleInviteeCaller.setTrainingCampCallerId(callerId);
										trainingCampScheduleInviteeCaller.setInsertedBy(userId);
										trainingCampScheduleInviteeCaller.setUpdatedBy(userId);
										trainingCampScheduleInviteeCaller.setInsertedTime(dateutilService.getCurrentDateAndTime());
										trainingCampScheduleInviteeCaller.setUpdatedTime(dateutilService.getCurrentDateAndTime());
										trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
									}
								}
							}
						}
					}
				}
			});			
			status.setResultCode(0);
			status.setMessage("SUCCESS");
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("FAILURE");
			LOG.error(" Exception occured in assignMembersToCallerForMemberConfirmation method in TrainingCampService class.",e);
		}
		
		return status;
	}
	 
	public ResultStatus assignMembersToCallerForBatchConfirmation(final Long userId, final boolean isOwnMembers , final Long scheduleId, final Long membersCount,final Long callerId,final Long callPurposeId,final Long batchId, final List<Long> callerIdsList)
	{
		ResultStatus status  = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					List<Long> alreadyInvitedMemberIdsList = null;
					alreadyInvitedMemberIdsList = trainingCampScheduleInviteeCallerDAO.getInterestedAndInvitedMembersListForBatchConfirmation(null,scheduleId,batchId, IConstants.CONFIRMATION, IConstants.INTERESTED);
					List<Long> invitedMemberIdsList = trainingCampScheduleInviteeDAO.getInvitedCandidatesListByScheduleIdAndCount(callerIdsList,scheduleId,null,Integer.valueOf(membersCount.toString()),batchId);
					if(invitedMemberIdsList != null && invitedMemberIdsList.size()>0)
					{
						DateUtilService dateutilService = new DateUtilService();
						if(alreadyInvitedMemberIdsList == null) alreadyInvitedMemberIdsList = new ArrayList<Long>(0);
						for (Long trainingCampScheduleInviteeId : invitedMemberIdsList) {
								if(!alreadyInvitedMemberIdsList.contains(trainingCampScheduleInviteeId))
								{
									TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = new TrainingCampScheduleInviteeCaller();
									trainingCampScheduleInviteeCaller.setTrainingCampScheduleInviteeId(trainingCampScheduleInviteeId);
									trainingCampScheduleInviteeCaller.setTrainingCampCallerAdminId(userId);
									trainingCampScheduleInviteeCaller.setCallPurposeId(callPurposeId);
									trainingCampScheduleInviteeCaller.setTrainingCampCallerId(callerId);
									trainingCampScheduleInviteeCaller.setInsertedBy(userId);
									trainingCampScheduleInviteeCaller.setUpdatedBy(userId);
									trainingCampScheduleInviteeCaller.setInsertedTime(dateutilService.getCurrentDateAndTime());
									trainingCampScheduleInviteeCaller.setUpdatedTime(dateutilService.getCurrentDateAndTime());
									trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
								}
						}
					}
				}
			});
			status.setResultCode(0);
			status.setMessage("SUCCESS");
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("FAILURE");
			LOG.error(" Exception occured in assignMembersToCallerForBatchConfirmation method in TrainingCampService class.",e);
		}
		return status;
	}
	
	public TrainingCampScheduleVO getCallerWiseCallsDetails(List<Long> userIds,String searchTypeId,String startDateString,String endDateString,String agentType)
	{
		List<TrainingCampScheduleVO> finalList=null;
		TrainingCampScheduleVO finalCallersVODetails=new TrainingCampScheduleVO();
		try { 
			
			Date startDate =null;
			Date endDate =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			if(!startDateString.isEmpty())
				startDate= sdf.parse(startDateString);
			if(!endDateString.isEmpty())
				endDate= sdf.parse(endDateString);
			
			/*Date today = new DateUtilService().getCurrentDateAndTime();
			today = sdf.parse(new SimpleDateFormat("MM/dd/yyyy").format(today));*/
			
			
			Map<Long,TrainingCampScheduleVO> finalmap=new LinkedHashMap<Long,TrainingCampScheduleVO>();
			
			//1)getting All status and set to list.
			  List<Object[]>  allStatus=scheduleInviteeStatusDAO.getAllStatusList();
			//DAO calls.
			  List<Object[]> totalAssignedList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, null,agentType);//callerId,count
			  List<Object[]> completedCallsList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, "completedCount",agentType);
			  List<Object[]> pendingCallsList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds,startDate,endDate,"pendingCount",agentType);
		      List<Object[]> callStatusOfinviteesList = trainingCampScheduleInviteeCallerDAO.getCallStatusContsOfInvitees(userIds,startDate,endDate,agentType);//0.callerId,1.scheduleInviteeStatusId,2.status,3.count
		      
			
			//iterating.
			Map<Long,TrainingCampScheduleVO> assignedMap=new HashMap<Long, TrainingCampScheduleVO>();
			if(totalAssignedList !=null && totalAssignedList.size()>0){
				setResultToMap(totalAssignedList,assignedMap);
			}
			
			Map<Long,TrainingCampScheduleVO> completedMap=new HashMap<Long, TrainingCampScheduleVO>();
			if(completedCallsList !=null && completedCallsList.size()>0){
				setResultToMap(completedCallsList,completedMap);
			}
			
			Map<Long,TrainingCampScheduleVO> pendingMap=new HashMap<Long, TrainingCampScheduleVO>();

			if(pendingCallsList !=null && pendingCallsList.size()>0){
				setResultToMap(pendingCallsList,pendingMap);
			}
			
			//FinalMap For Caller Count
			if(assignedMap !=null && assignedMap.size()>0){
				setResultToAssignedMap(assignedMap,finalmap,"assigned",allStatus,null);
			}
			if(completedMap !=null && completedMap.size() > 0){
				setResultToAssignedMap(completedMap,finalmap,"completed",allStatus,null);
			}
			if(pendingMap !=null && pendingMap.size()>0){
				setResultToAssignedMap(pendingMap,finalmap,"pending",allStatus,null);
			}
			
			if(callStatusOfinviteesList !=null && callStatusOfinviteesList.size()>0){
				setResultToAssignedMap(null,finalmap,"status",allStatus,callStatusOfinviteesList);
			}
			
			
			if(finalmap!=null && finalmap.size()>0){
				finalList=new ArrayList<TrainingCampScheduleVO>(finalmap.values());
			}
			
			//totalAssignedCount
			Long totalAssignedCountofAgents=0l;
			if(finalList !=null && finalList.size()>0){
				for (TrainingCampScheduleVO vo : finalList) {
					totalAssignedCountofAgents=totalAssignedCountofAgents+vo.getAssignedCallsCount();
				}
			}
			
			
			//building For allocated Calls
			finalCallersVODetails.setTotalAssignedCount(totalAssignedCountofAgents);//allocating totalAssigned Count
			Long countForTotalCallers=trainingCampScheduleInviteeDAO.getAllCallersCount(startDate,endDate,"totalCallers");
			if(countForTotalCallers !=null){
				finalCallersVODetails.setTotalCount(countForTotalCallers);//allocating calls To caller
			}
				
			Date currentDate =dateUtilService.getCurrentDateAndTime();

			Long todayAllocatedCount= trainingCampScheduleInviteeDAO.getAllCallersCount(currentDate,null,"todayCallers");
			
			if(todayAllocatedCount !=null){
				finalCallersVODetails.setTodayAllocatedCalls(todayAllocatedCount);//today Allocated Calls Count
			}
			
			
			 List<Object[]> totalDialedCalls=trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCalls(userIds, startDate, endDate, "dialedCalls",null);
			
			 Long dialedCallsCount =0l;
			if(totalDialedCalls !=null && totalDialedCalls.size()>0){
				for(Object[] dialed:totalDialedCalls){
					if(dialed[1] !=null){
						dialedCallsCount=dialedCallsCount+commonMethodsUtilService.getLongValueForObject(dialed[1]) ;//allocating dialedCallsCount
					}
				}
			}
			
			finalCallersVODetails.setDialedCallsCount(dialedCallsCount);
			 //
			
			//statusWiseCount For upper Block in call center admin page
			List<Object[]> statusWiseCountList = trainingCampScheduleInviteeCallerDAO.getStatusWiseCount(null,startDate,endDate,null);
			
			List<TrainingCampScheduleVO> statusWiseCountLi = new ArrayList<TrainingCampScheduleVO>();
			if(statusWiseCountList !=null && statusWiseCountList.size()>0){
				for (Object[] object : statusWiseCountList) {
					TrainingCampScheduleVO vo =new TrainingCampScheduleVO();
					
					vo.setStatusId(commonMethodsUtilService.getLongValueForObject(object[0]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(object[1]));
					vo.setCount(commonMethodsUtilService.getLongValueForObject(object[2]));
					
					statusWiseCountLi.add(vo);
				}
			}
			
			
			if(finalList !=null && finalList.size()>0)
			{
				finalCallersVODetails.setTrainingCampVOList(finalList);
			}
			
			if(statusWiseCountLi !=null && statusWiseCountLi.size()>0){
				finalCallersVODetails.setTrainingCampScheduleVOList(statusWiseCountLi);
			}
			
			return finalCallersVODetails;
			
		}catch (Exception e){
			LOG.error(" Exception occured in getCallerWiseCallsDetails method in TrainingCampService class.",e);
		}
		return finalCallersVODetails;
	}
	public void setResultToMap(List<Object[]> listObj,Map<Long,TrainingCampScheduleVO> corespondentmap)
	{
		try{
			if(listObj !=null && listObj.size()>0){
				for(Object[] Obj:listObj){
					
					TrainingCampScheduleVO vo=new TrainingCampScheduleVO();
					
					Long assignedCount=commonMethodsUtilService.getLongValueForObject(Obj[1]);
					
					vo.setId(Long.parseLong(commonMethodsUtilService.getStringValueForObject(Obj[0])));
					vo.setName(Obj[2] !=null ? Obj[2].toString() : "");
					vo.setCount(assignedCount);
					
					corespondentmap.put(Long.parseLong(commonMethodsUtilService.getStringValueForObject(Obj[0])),vo);
				}
			}
		}catch (Exception e) {
			LOG.error(" Exception occured in setResultToMap method in TrainingCampService class.",e);
		}
	}
	public void setResultToAssignedMap(Map<Long,TrainingCampScheduleVO> assignedMap,Map<Long,TrainingCampScheduleVO> finalMap,String type,List<Object[]> allStatus,List<Object[]> callStatusOfinviteesList){
		
		try{
			if(type.equalsIgnoreCase("status")){
				
				for(Object[] obj:callStatusOfinviteesList){////0.callerId,1.scheduleInviteeStatusId,2.status,3.count,name
					TrainingCampScheduleVO assignCampVo =finalMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					boolean agentExist=true;
					
					if(assignCampVo==null){
						agentExist=false;
						assignCampVo=new TrainingCampScheduleVO();
						assignCampVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						assignCampVo.setName("");
						assignCampVo.setAssignedCallsCount(0l);
						assignCampVo.setCompletedCallsCount(0l);
						assignCampVo.setPendingCallsCount(0l);
						setStatusSchedules(assignCampVo,allStatus);
					}
					if(obj[1]!=null){
						Long statusId=commonMethodsUtilService.getLongValueForObject(commonMethodsUtilService.getLongValueForObject(obj[1]));
						List<TrainingCampScheduleVO> schedulesList=assignCampVo.getTrainingCampVOList();
						if(schedulesList!=null && schedulesList.size()>0){
							for(TrainingCampScheduleVO schedule:schedulesList){
								if(schedule.getStatusId().longValue()==statusId.longValue()){
									schedule.setCount(commonMethodsUtilService.getLongValueForObject(obj[3]));
								}
							}
								
						}
					}
					if(!agentExist){
						finalMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), assignCampVo);
					}
					
				}
				
			}else{
				for (Entry<Long, TrainingCampScheduleVO> assignedEntry : assignedMap.entrySet()){
					
					Long callerId=assignedEntry.getKey();
					TrainingCampScheduleVO assingedCount=assignedEntry.getValue();
					
					TrainingCampScheduleVO assignCampVo =finalMap.get(callerId);
					boolean agentExist=true;
					
					if(assignCampVo ==null){
						agentExist=false;
						assignCampVo=new TrainingCampScheduleVO();
						assignCampVo.setId(callerId);
						assignCampVo.setName(assingedCount.getName());
						assignCampVo.setAssignedCallsCount(0l);
						assignCampVo.setCompletedCallsCount(0l);
						assignCampVo.setPendingCallsCount(0l);
						setStatusSchedules(assignCampVo,allStatus);
					}
					if(type.equalsIgnoreCase("assigned")){
						assignCampVo.setAssignedCallsCount(assingedCount.getCount());
					}
					else if(type.equalsIgnoreCase("completed")){
						assignCampVo.setCompletedCallsCount(assingedCount.getCount());
					}
					else if(type.equalsIgnoreCase("pending")){
						assignCampVo.setPendingCallsCount(assingedCount.getCount());
					}
					if(!agentExist){
						finalMap.put(callerId, assignCampVo);
					}
				}
			}
		}
		catch (Exception e) {
			LOG.error(" Exception occured in setResultToAssignedMap method in TrainingCampService class.",e);
		}
		
	}
	
	public void setStatusSchedules(TrainingCampScheduleVO assignCampVo,List<Object[]> allStatus){
		try{
			List<TrainingCampScheduleVO> statusScheduleList=new ArrayList<TrainingCampScheduleVO>();
			if(allStatus !=null && allStatus.size()>0){
				for(Object[] status:allStatus){
					TrainingCampScheduleVO trainingCampScheduleVO=new TrainingCampScheduleVO();
					trainingCampScheduleVO.setStatusId(commonMethodsUtilService.getLongValueForObject(status[0]));
					trainingCampScheduleVO.setStatus(commonMethodsUtilService.getStringValueForObject(status[1]));
					statusScheduleList.add(trainingCampScheduleVO);
				  }
			}
			assignCampVo.setTrainingCampVOList(statusScheduleList);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in setStatusSchedules method in TrainingCampService class.",e);
		}
	}
	
	public TrainingCampVO getCampusWiseBatchWiseMembersDetails(List<Long> callerIdsList,String searchType,String startDateStr,String endDateStr)
	{
		TrainingCampVO returnVO = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate= null;
			Date endDate= null;
			
			if(!startDateStr.isEmpty())
				startDate= format.parse(startDateStr);
			if(!endDateStr.isEmpty())
			 endDate= format.parse(endDateStr);
			Date today = new DateUtilService().getCurrentDateAndTime();
			today = format.parse(new SimpleDateFormat("MM/dd/yyyy").format(today));
			Map<String,Map<String,Map<Long,Long>>> programWiseCallsSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,Long>>>(0);
			Map<String,Map<String,Map<Long,Long>>> programWiseNotDialdCallsSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,Long>>>(0);
			Map<String,Map<String,Map<Long,Long>>> programWiseDialdCallsSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,Long>>>(0);
			
				List<Object[]> assignedSchedulesList=trainingCampScheduleInviteeCallerDAO.getScheduleWiseDetailsCount(callerIdsList,startDate,endDate,null,searchType,today);
				for (Object[] campObj : assignedSchedulesList) {
						String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
						String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
						//String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
						Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[3]);
						Long count = commonMethodsUtilService.getLongValueForObject(campObj[4]);
						
						Map<String,Map<Long,Long>> campWiseMap = new LinkedHashMap<String,Map<Long,Long>>(0);
						Map<Long,Long> scheduleWiseMap = new LinkedHashMap<Long,Long>(0);
						Long totalCount = 0L;
						if(programWiseCallsSceduleWiseMap.get(programName) != null)
						{
							campWiseMap = programWiseCallsSceduleWiseMap.get(programName);
						}
						if(campWiseMap.get(campName) != null)
						{
							scheduleWiseMap = campWiseMap.get(campName);
						}
						if(scheduleWiseMap.get(scheduleId) != null)
						{
							totalCount = scheduleWiseMap.get(scheduleId);
						}
						totalCount = totalCount+ count;					
						
						scheduleWiseMap.put(scheduleId, totalCount);
						campWiseMap.put(campName,scheduleWiseMap);
						programWiseCallsSceduleWiseMap.put(programName, campWiseMap);
				}
				
				List<Object[]> dialedSchedulesList=trainingCampScheduleInviteeCallerDAO.getScheduleWiseDetailsCount(callerIdsList,startDate,endDate,"dialedCalls",searchType,today);
				
				for (Object[] campObj : dialedSchedulesList) {
						String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
						String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
						//String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
						Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[3]);
						Long count = commonMethodsUtilService.getLongValueForObject(campObj[4]);
						
						Map<String,Map<Long,Long>> campWiseMap = new LinkedHashMap<String,Map<Long,Long>>(0);
						Map<Long,Long> scheduleWiseMap = new LinkedHashMap<Long,Long>(0);
						Long totalCount = 0L;
						if(programWiseDialdCallsSceduleWiseMap.get(programName) != null)
						{
							campWiseMap = programWiseDialdCallsSceduleWiseMap.get(programName);
						}
						if(campWiseMap.get(campName) != null)
						{
							scheduleWiseMap = campWiseMap.get(campName);
						}
						if(scheduleWiseMap.get(scheduleId) != null)
						{
							totalCount = scheduleWiseMap.get(scheduleId);
						}
						totalCount = totalCount+ count;					
						
						scheduleWiseMap.put(scheduleId, totalCount);
						campWiseMap.put(campName,scheduleWiseMap);
						programWiseDialdCallsSceduleWiseMap.put(programName, campWiseMap);
				}
				
				List<Object[]> notDialedSchedulesList=trainingCampScheduleInviteeCallerDAO.getScheduleWiseDetailsCount(callerIdsList,startDate,endDate,"notDialed",searchType,today);
				
				for (Object[] campObj : notDialedSchedulesList) {
						String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
						String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
						//String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
						Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[3]);
						Long count = commonMethodsUtilService.getLongValueForObject(campObj[4]);
						
						Map<String,Map<Long,Long>> campWiseMap = new LinkedHashMap<String,Map<Long,Long>>(0);
						Map<Long,Long> scheduleWiseMap = new LinkedHashMap<Long,Long>(0);
						Long totalCount = 0L;
						if(programWiseNotDialdCallsSceduleWiseMap.get(programName) != null)
						{
							campWiseMap = programWiseNotDialdCallsSceduleWiseMap.get(programName);
						}
						if(campWiseMap.get(campName) != null)
						{
							scheduleWiseMap = campWiseMap.get(campName);
						}
						if(scheduleWiseMap.get(scheduleId) != null)
						{
							totalCount = scheduleWiseMap.get(scheduleId);
						}
						totalCount = totalCount+ count;					
						
						scheduleWiseMap.put(scheduleId, totalCount);
						campWiseMap.put(campName,scheduleWiseMap);
						programWiseNotDialdCallsSceduleWiseMap.put(programName, campWiseMap);
				}
				
				List<Object[]>  allStatusList=scheduleInviteeStatusDAO.getAllStatusList();
				List<Object[]> campAndSchedulewiseResultsList = trainingCampScheduleInviteeDAO.getCampusWiseScheduleWiseMembersDetails(searchType, startDate, endDate,today);
				
				Map<String,Map<String,Map<Long,List<TrainingCampVO>>>> programWiseSceduleWiseMap = new LinkedHashMap<String,Map<String,Map<Long,List<TrainingCampVO>>>>(0);
				Map<Long,Long> interestedMembersForSchedulMap = new LinkedHashMap<Long, Long>(0);
				if(campAndSchedulewiseResultsList != null && campAndSchedulewiseResultsList.size()>0)
				{
					returnVO = new TrainingCampVO();
					for (Object[] campObj : campAndSchedulewiseResultsList) {
						
						
						String memberStatus = commonMethodsUtilService.getStringValueForObject(campObj[4]);

						String campName = commonMethodsUtilService.getStringValueForObject(campObj[0]);
						String programName = commonMethodsUtilService.getStringValueForObject(campObj[1]);
						String scheduleName =commonMethodsUtilService.getStringValueForObject(campObj[2]);
						Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[6]);
						Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = new LinkedHashMap<String,Map<Long,List<TrainingCampVO>>>(0);
						Map<Long,List<TrainingCampVO>> scheduleWiseMap = new LinkedHashMap<Long,List<TrainingCampVO>>(0);
						List<TrainingCampVO> trainingCampVOList = null;
						if(programWiseSceduleWiseMap.get(programName) != null)
						{
							campWiseMap = programWiseSceduleWiseMap.get(programName);
						}
						if(campWiseMap.get(campName) != null)
						{
							scheduleWiseMap = campWiseMap.get(campName);
						}
						if(scheduleWiseMap.get(scheduleId) != null)
						{
							trainingCampVOList = scheduleWiseMap.get(scheduleId);
						}
						else
						{
							trainingCampVOList = new ArrayList<TrainingCampVO>(0);
							for (Object[] status : allStatusList) {
								TrainingCampVO trainingCampVO = new TrainingCampVO();
								trainingCampVO.setMemberStatus(commonMethodsUtilService.getStringValueForObject(status[1]));
								trainingCampVO.setInterestedCount(0L);
								trainingCampVOList.add(trainingCampVO);
							}
						}
						
						TrainingCampVO trainingCampVO = getMatchedVOforMemberStatus(trainingCampVOList,memberStatus);
						if(trainingCampVO != null)
						{
							if(memberStatus != null && memberStatus.equalsIgnoreCase(IConstants.NOTNOW))
								trainingCampVO.setMemberStatus("LATER");
							
							trainingCampVO.setTrainingCampName(campName);
							trainingCampVO.setScheduleName(scheduleName);
							trainingCampVO.setMemberStatus(memberStatus);
							 if(trainingCampVO.getMemberStatus() != null && !trainingCampVO.getMemberStatus().isEmpty() && 
										trainingCampVO.getMemberStatus().trim().equalsIgnoreCase("Interested"))	
								{
								 Long interestedCount =  trainingCampVO.getInterestedCount() != null ? trainingCampVO.getInterestedCount() :0L;
								 interestedCount = interestedCount+ commonMethodsUtilService.getLongValueForObject(campObj[5]);
								 trainingCampVO.setInterestedCount(interestedCount);
								}
							 else
							 {
								 trainingCampVO.setInterestedCount(commonMethodsUtilService.getLongValueForObject(campObj[5]));
							 }
							
							//trainingCampVOList.add(trainingCampVO);
							if(trainingCampVO.getMemberStatus() != null && !trainingCampVO.getMemberStatus().isEmpty() && 
									trainingCampVO.getMemberStatus().trim().equalsIgnoreCase("Interested"))
								interestedMembersForSchedulMap.put(scheduleId, trainingCampVO.getNextBatchInterestedCount());
							
							if(trainingCampVO.getMemberStatus() != null && !trainingCampVO.getMemberStatus().isEmpty() && 
									trainingCampVO.getMemberStatus().trim().equalsIgnoreCase("Confirmed"))
							{
								try {
									TrainingCampVO trainingCampVO1 = getMatchedVOforMemberStatus(trainingCampVOList,"Interested");
									trainingCampVO1.setScheduleName(scheduleName);
									Long interestedCount = trainingCampVO1.getInterestedCount();// interested
									Long confirmedCount = trainingCampVO.getInterestedCount(); // confirmed
									interestedCount = interestedCount+confirmedCount;
									trainingCampVO1.setInterestedCount(interestedCount);
								} catch (Exception e) {
								}
								
								trainingCampVOList.remove(trainingCampVO);
							}
							
							scheduleWiseMap.put(scheduleId, trainingCampVOList);
							campWiseMap.put(campName,scheduleWiseMap);
							programWiseSceduleWiseMap.put(programName, campWiseMap);
						}
					}
					
					Map<Long,Long> scheduleWiseBatchConfirmedCountMap = new LinkedHashMap<Long,Long>(0);
					List<Object[]> batchConfirmedDetails = trainingCampScheduleInviteeCallerDAO.getBatchConfirmedMemberDetails(null, startDate, endDate,searchType,IConstants.CONFIRMATION);
					if(batchConfirmedDetails != null && batchConfirmedDetails.size()>0)
					{
						for (Object[] campObj : batchConfirmedDetails) {
							
							Long scheduleId =commonMethodsUtilService.getLongValueForObject(campObj[3]);
							
							Long confirmedCount =commonMethodsUtilService.getLongValueForObject(campObj[5]);
							
							if(scheduleWiseBatchConfirmedCountMap.get(scheduleId) != null)
							{
								confirmedCount = scheduleWiseBatchConfirmedCountMap.get(scheduleId);
							}
							
							scheduleWiseBatchConfirmedCountMap.put(scheduleId, confirmedCount);
						}
					}
					
					if(programWiseCallsSceduleWiseMap != null && programWiseCallsSceduleWiseMap.size()>0)
					{
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						Map<Long,Long> allDiaCallsscheduleWiseMap = new LinkedHashMap<Long, Long>(0);
						for (String programStr : programWiseCallsSceduleWiseMap.keySet()) {
							Map<String,Map<Long,Long>> allocatedCampWisesMap = programWiseCallsSceduleWiseMap.get(programStr);
							Map<String,Map<Long,List<TrainingCampVO>>> campWiseMap = programWiseSceduleWiseMap.get(programStr);
							Map<String,Map<Long,Long>> allCallsCampWiseMap =  programWiseCallsSceduleWiseMap.get(programStr);
							Map<String,Map<Long,Long>> allDiaCallsCampWiseMap =  programWiseDialdCallsSceduleWiseMap.get(programStr);
							//Map<String,Map<Long,Long>> allNotDialdCallsCampWiseMap =  programWiseDialdCallsSceduleWiseMap.get(programStr);
							TrainingCampVO programVO = new TrainingCampVO();
							programVO.setName(programStr);
							if(allocatedCampWisesMap != null && allocatedCampWisesMap.size()>0)
							{
								for (String campNameStr : allocatedCampWisesMap.keySet()) {

									Map<Long,List<TrainingCampVO>> scheduleWiseCountMap = campWiseMap.get(campNameStr);
									Map<Long,Long> allCallsscheduleWiseMap = new LinkedHashMap<Long, Long>(0);
									if(allCallsCampWiseMap != null)
										allCallsscheduleWiseMap=  allCallsCampWiseMap.get(campNameStr);
									
									if(allDiaCallsCampWiseMap != null){
										allDiaCallsscheduleWiseMap =  allDiaCallsCampWiseMap.get(campNameStr);}
									//Map<Long,Long> allNotDialdCallsscheduleWiseMap =  allNotDialdCallsCampWiseMap.get(campNameStr);
									
									programVO.setTrainingCampName(campNameStr);
									if(scheduleWiseCountMap != null && scheduleWiseCountMap.size()>0)
									{
										List<TrainingCampVO> finalList = new ArrayList<TrainingCampVO>(0);
										TrainingCampVO finalVO = new TrainingCampVO();
										
										for (Long scheduleId : scheduleWiseCountMap.keySet()) {
											List<TrainingCampVO> scheduleVOList = scheduleWiseCountMap.get(scheduleId);
											
											Long allocatedCallsCount=0l;
											Long dialdCallsCount=0l;
											Long unDialdCallsCount=0l;
											
											allocatedCallsCount =  allCallsscheduleWiseMap.get(scheduleId);
											if(allDiaCallsscheduleWiseMap != null)
											{
											dialdCallsCount =  allDiaCallsscheduleWiseMap.get(scheduleId) !=null ?allDiaCallsscheduleWiseMap.get(scheduleId):0L ;
											}
											
											if(allocatedCallsCount !=null && allocatedCallsCount !=0l && dialdCallsCount !=null ){
												unDialdCallsCount =  allocatedCallsCount - dialdCallsCount;
											}
											
											finalVO.setAllocatedCalls(allocatedCallsCount);
											finalVO.setDialedCallsCount(dialdCallsCount);
											finalVO.setUnDialedCount(unDialdCallsCount);
											
											Long  batchConfirmationCount =0l;
											Long  interestedCount=0l;
											Long  availableCount=0l;
											
											batchConfirmationCount= scheduleWiseBatchConfirmedCountMap.get(scheduleId);
											
											if(batchConfirmationCount !=null){
												finalVO.setBatchConfirmationCount(batchConfirmationCount);
											}
											else{
												batchConfirmationCount =0l;
												finalVO.setBatchConfirmationCount(batchConfirmationCount);
											}
											
											interestedCount= interestedMembersForSchedulMap.get(scheduleId);
											
											if(interestedCount !=null && interestedCount !=0l)
											{
												availableCount = interestedCount - batchConfirmationCount;
											}
											
											finalVO.setAvailableMembersCount(availableCount);
											
											Long othrsCount = 0l;
											finalVO.setName(programVO.getName());
											finalVO.setTrainingCampName(programVO.getTrainingCampName());
											
												for (TrainingCampVO trainingCampVO2 : scheduleVOList) {
													if(trainingCampVO2.getScheduleName() != null && !trainingCampVO2.getScheduleName().isEmpty())
														finalVO.setScheduleName(trainingCampVO2.getScheduleName());
													String countBelongsTo = trainingCampVO2.getMemberStatus();
													
													if(countBelongsTo.equalsIgnoreCase(IConstants.INTERESTED)){
														finalVO.setInterestedCount(trainingCampVO2.getInterestedCount());
													}
													else if(countBelongsTo.equalsIgnoreCase(IConstants.NOTINTERESTED)){
														finalVO.setNotInterestedCount(trainingCampVO2.getInterestedCount());
													} 
													else if(countBelongsTo.equalsIgnoreCase("Call Back - Busy")){
														finalVO.setBusyCount(trainingCampVO2.getInterestedCount());
													}
													else if(countBelongsTo.equalsIgnoreCase(IConstants.CALLBACK_CONFIRM_LATER)){
														finalVO.setConformLaterCount(trainingCampVO2.getInterestedCount());
													}
													else{
														othrsCount = othrsCount+trainingCampVO2.getInterestedCount();
														finalVO.setOthersCount(othrsCount);
													}
												}

											finalList.add(finalVO);
											programVO.getTrainingCampVOList().addAll(finalList);
										}
									}
									else
									{
										Map<Long,Long> allocatedScheduleWiseCountMap = allocatedCampWisesMap.get(campNameStr);
										if(allCallsCampWiseMap != null)
											allCallsscheduleWiseMap=  allCallsCampWiseMap.get(campNameStr);
										
										if(allDiaCallsCampWiseMap != null){
											allDiaCallsscheduleWiseMap =  allDiaCallsCampWiseMap.get(campNameStr);}
										//Map<Long,Long> allNotDialdCallsscheduleWiseMap =  allNotDialdCallsCampWiseMap.get(campNameStr);
										
										programVO.setTrainingCampName(campNameStr);
										if(allocatedScheduleWiseCountMap != null && allocatedScheduleWiseCountMap.size()>0)
										{
											List<TrainingCampVO> finalList = new ArrayList<TrainingCampVO>(0);
											TrainingCampVO finalVO = new TrainingCampVO();
											finalVO.setTrainingCampName(campNameStr);
											for (Long scheduleId : allocatedScheduleWiseCountMap.keySet()) {
												
												Long allocatedCallsCount=0l;
												Long dialdCallsCount=0l;
												Long unDialdCallsCount=0l;
												
												allocatedCallsCount =  allocatedScheduleWiseCountMap.get(scheduleId);
												finalVO.setScheduleName(trainingCampScheduleDAO.get(scheduleId).getTrainingCampScheduleCode());
												if(allDiaCallsscheduleWiseMap != null){
													dialdCallsCount =  allDiaCallsscheduleWiseMap.get(scheduleId) !=null ?allDiaCallsscheduleWiseMap.get(scheduleId):0L ;
												}
												
												if(allocatedCallsCount !=null && allocatedCallsCount !=0l && dialdCallsCount !=null ){
													unDialdCallsCount =  allocatedCallsCount - dialdCallsCount;
												}
												
												finalVO.setAllocatedCalls(allocatedCallsCount);
												finalVO.setDialedCallsCount(dialdCallsCount);
												finalVO.setUnDialedCount(unDialdCallsCount);
												
												finalList.add(finalVO);
												programVO.getTrainingCampVOList().addAll(finalList);
											}
										}
									}
								}
								trainingCampVOList.add(programVO);
							}
							
							returnVO.setTrainingCampVOList(trainingCampVOList);
						}
					}
				}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseBatchWiseMembersDetails method in TrainingCampService class.",e);
			returnVO = null;
		}
		
		return returnVO;
	}
	public TrainingCampVO getMatchedVOforMemberStatus(List<TrainingCampVO> list,String memberStatus)
	{
		TrainingCampVO returnVO = null;
		try {
			if(list != null && list.size()>0 && memberStatus != null && !memberStatus.isEmpty())
			{
				for (TrainingCampVO trainingCampVO : list) {
					if(trainingCampVO.getMemberStatus().equalsIgnoreCase(memberStatus))
						return trainingCampVO;
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getMatchedVOforMemberStatus method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	public TrainingCampVO getCampusWiseDateWiseInterestedMembersDetails(String searchType,String startDateStr,String endDateStr)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		TrainingCampVO returnVO = new TrainingCampVO();
		try {
			Date startDate= null;
			Date endDate= null;
			
			if(!startDateStr.isEmpty())
				startDate= format.parse(startDateStr);
			if(!endDateStr.isEmpty())
			 endDate= format.parse(endDateStr);
			Date today = new DateUtilService().getCurrentDateAndTime();
			today = format.parse(new SimpleDateFormat("MM/dd/yyyy").format(today));
			List<Object[]> interestedMembersList = trainingCampScheduleInviteeDAO.getBatchWiseConformationDetails(searchType,  startDate,  endDate,today);
			
			List<Object[]> allocatedCallsCountList = trainingCampScheduleInviteeCallerDAO.getAllocatedCallsForBatchConfirmationDetails(searchType,  startDate,  endDate,today);
			List<Object[]> dialedCallsCountList = trainingCampScheduleInviteeCallerDAO.getdialedCallsForBatchConfirmationDetails(searchType,  startDate,  endDate,today);
			
			//Map<Long,Long> allocatedCountMap = new HashMap<Long, Long>(0);
			Map<Long,Long> dialedCountMap = new HashMap<Long, Long>(0);
			
			/*if(allocatedCallsCountList != null && allocatedCallsCountList.size() > 0)
			{
				for (Object[] objects : allocatedCallsCountList) {
					Long allocatedcount = commonMethodsUtilService.getLongValueForObject(objects[0]);
					Long batchId = commonMethodsUtilService.getLongValueForObject(objects[2]);
					
					allocatedCountMap.put(batchId,allocatedcount);
				}
			}*/
			
			if(dialedCallsCountList != null && dialedCallsCountList.size() > 0)
			{
				for (Object[] objects : dialedCallsCountList) {
					Long dialedCount = commonMethodsUtilService.getLongValueForObject(objects[7]);
					Long batchId = commonMethodsUtilService.getLongValueForObject(objects[4]);
					Long cnt = dialedCountMap.get(batchId);
					if(cnt == null)
					dialedCountMap.put(batchId, dialedCount);
					else
						dialedCountMap.put(batchId, dialedCount + cnt);	
				}
			}
			
			Map<String,Map<String,Map<Long,List<TrainingCampVO>>>> programWiseInterestedMembersMap = new LinkedHashMap<String,Map<String, Map<Long,List<TrainingCampVO>>>>(0);
			Map<String,Map<String,Map<Long,TrainingCampVO>>> programWiseAllocatedMembersMap = new LinkedHashMap<String,Map<String, Map<Long,TrainingCampVO>>>(0);
			if(allocatedCallsCountList != null && allocatedCallsCountList.size()>0)
			{
				for (Object[] member : allocatedCallsCountList) {
					String programName = commonMethodsUtilService.getStringValueForObject(member[1]);
					String campName = commonMethodsUtilService.getStringValueForObject(member[3]);
					Map<String,Map<Long,List<TrainingCampVO>>> campsWiseInterestedMembersMap = new LinkedHashMap<String,Map<Long,List<TrainingCampVO>>>(0);
					Map<String,Map<Long,TrainingCampVO>> campsWiseAllocatedMembersMap = new LinkedHashMap<String,Map<Long,TrainingCampVO>>(0);
					Map<Long,List<TrainingCampVO>> batchwiseInterestedMembersMap = new LinkedHashMap<Long, List<TrainingCampVO>>(0);
					Map<Long,TrainingCampVO> batchwiseAllocatedMembersMap = new LinkedHashMap<Long, TrainingCampVO>(0);
					
					List<TrainingCampVO>  batchVOList = new ArrayList<TrainingCampVO>(0);
					
					if(programWiseInterestedMembersMap.get(programName) != null)
					{
						campsWiseInterestedMembersMap = programWiseInterestedMembersMap.get(programName);
					}
					if(campsWiseInterestedMembersMap.get(campName) != null)
					{
						batchwiseInterestedMembersMap = campsWiseInterestedMembersMap.get(campName);
					}
					if(batchwiseInterestedMembersMap.get(commonMethodsUtilService.getLongValueForObject(member[4])) != null)
					{
						batchVOList = batchwiseInterestedMembersMap.get(commonMethodsUtilService.getLongValueForObject(member[4]));
					}

					if(programWiseAllocatedMembersMap.get(programName) != null)
					{
						campsWiseAllocatedMembersMap = programWiseAllocatedMembersMap.get(programName);
					}
					if(campsWiseAllocatedMembersMap.get(campName) != null)
					{
						batchwiseAllocatedMembersMap = campsWiseAllocatedMembersMap.get(campName);
					}
					
						TrainingCampVO vo = new TrainingCampVO();
						vo.setName(programName);
						vo.setTrainingCampName(campName);
						vo.setBatchId(commonMethodsUtilService.getLongValueForObject(member[4]));
						vo.setStartDateStr(commonMethodsUtilService.getStringValueForObject(member[5]));
						vo.setEndDateStr(commonMethodsUtilService.getStringValueForObject(member[6]));
						//vo.setBatchConfirmationCount(commonMethodsUtilService.getLongValueForObject(member[7]));
						vo.setSchdlStatusId(commonMethodsUtilService.getLongValueForObject(member[8]));
						vo.setSchdlStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
						vo.setMemberStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
						
						//vo.setDialedCallsCount(dialedCountMap.get(commonMethodsUtilService.getLongValueForObject(member[4])));
						if(vo.getAllocatedCallsCount() == null)
							vo.setAllocatedCallsCount(0l);
						vo.setAllocatedCallsCount(vo.getAllocatedCallsCount() + commonMethodsUtilService.getLongValueForObject(member[7]));
						batchVOList.add(vo);
						
						if(batchVOList != null && batchVOList.size()>0)
						{
							Long allocatedCount = 0L;
							for (TrainingCampVO batchVO2 : batchVOList) {
								allocatedCount = allocatedCount+batchVO2.getAllocatedCallsCount();
							}
							
							TrainingCampVO batchVO1 = new TrainingCampVO();
							batchVO1.setName(programName);
							batchVO1.setTrainingCampName(campName);
							batchVO1.setBatchId(commonMethodsUtilService.getLongValueForObject(member[4]));
							batchVO1.setStartDateStr(commonMethodsUtilService.getStringValueForObject(member[5]));
							batchVO1.setEndDateStr(commonMethodsUtilService.getStringValueForObject(member[6]));
							//vo.setBatchConfirmationCount(commonMethodsUtilService.getLongValueForObject(member[7]));
							batchVO1.setSchdlStatusId(commonMethodsUtilService.getLongValueForObject(member[8]));
							batchVO1.setSchdlStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
							batchVO1.setMemberStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
							batchVO1.setAllocatedCallsCount(allocatedCount);
							
							batchwiseAllocatedMembersMap.put(commonMethodsUtilService.getLongValueForObject(member[4]), batchVO1);
						}
					
					batchwiseInterestedMembersMap.put(commonMethodsUtilService.getLongValueForObject(member[4]), batchVOList);
					
					campsWiseAllocatedMembersMap.put(campName, batchwiseAllocatedMembersMap);
					programWiseAllocatedMembersMap.put(programName, campsWiseAllocatedMembersMap);
					
					campsWiseInterestedMembersMap.put(campName, batchwiseInterestedMembersMap);
					programWiseInterestedMembersMap.put(programName, campsWiseInterestedMembersMap);
					
				}
				
				programWiseInterestedMembersMap.clear();
				if(interestedMembersList != null && interestedMembersList.size()>0)
				{
					for (Object[] member : interestedMembersList) {

						String programName = commonMethodsUtilService.getStringValueForObject(member[1]);
						String campName = commonMethodsUtilService.getStringValueForObject(member[3]);
						Map<String,Map<Long,List<TrainingCampVO>>> campsWiseInterestedMembersMap = new LinkedHashMap<String,Map<Long,List<TrainingCampVO>>>(0);
						Map<Long,List<TrainingCampVO>> batchwiseInterestedMembersMap = new LinkedHashMap<Long, List<TrainingCampVO>>(0);
						
						List<TrainingCampVO>  batchVOList = new ArrayList<TrainingCampVO>(0);
						
						if(programWiseInterestedMembersMap.get(programName) != null)
						{
							campsWiseInterestedMembersMap = programWiseInterestedMembersMap.get(programName);
						}
						if(campsWiseInterestedMembersMap.get(campName) != null)
						{
							batchwiseInterestedMembersMap = campsWiseInterestedMembersMap.get(campName);
						}
						if(batchwiseInterestedMembersMap.get(commonMethodsUtilService.getLongValueForObject(member[4])) != null)
						{
							batchVOList = batchwiseInterestedMembersMap.get(commonMethodsUtilService.getLongValueForObject(member[4]));
						}
						
							TrainingCampVO vo = new TrainingCampVO();
						
							vo.setName(programName);
							vo.setTrainingCampName(campName);
							vo.setBatchId(commonMethodsUtilService.getLongValueForObject(member[4]));
							vo.setStartDateStr(commonMethodsUtilService.getStringValueForObject(member[5]));
							vo.setEndDateStr(commonMethodsUtilService.getStringValueForObject(member[6]));
							vo.setBatchConfirmationCount(commonMethodsUtilService.getLongValueForObject(member[7]));
							vo.setSchdlStatusId(commonMethodsUtilService.getLongValueForObject(member[8]));
							vo.setSchdlStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
							vo.setMemberStatus(commonMethodsUtilService.getStringValueForObject(member[9]));
							if(vo.getDialedCallsCount() == null)
								vo.setDialedCallsCount(0l);
							if(dialedCountMap != null && dialedCountMap.size()> 0 && dialedCountMap.get(commonMethodsUtilService.getLongValueForObject(member[4]))!= null)
								vo.setDialedCallsCount(vo.getDialedCallsCount() + dialedCountMap.get(commonMethodsUtilService.getLongValueForObject(member[4])));
							
							vo.setInterestedCount(commonMethodsUtilService.getLongValueForObject(member[7]));
							
							batchVOList.add(vo);
						
						batchwiseInterestedMembersMap.put(commonMethodsUtilService.getLongValueForObject(member[4]), batchVOList);
						campsWiseInterestedMembersMap.put(campName, batchwiseInterestedMembersMap);
						programWiseInterestedMembersMap.put(programName, campsWiseInterestedMembersMap);
						
					
					}
				}
				
				if(programWiseInterestedMembersMap != null && programWiseInterestedMembersMap.size()>0)
				{
					List<TrainingCampVO> trainingProgramsList = new ArrayList<TrainingCampVO>(0);
					for (String programNameStr : programWiseInterestedMembersMap.keySet()) {
						TrainingCampVO programVO = new TrainingCampVO();
						programVO.setName(programNameStr);
						
						Map<String,Map<Long,List<TrainingCampVO>>> campsWiseInterestedMembersMap = programWiseInterestedMembersMap.get(programNameStr);
						
						if(campsWiseInterestedMembersMap != null && campsWiseInterestedMembersMap.size()>0)
						{
							for (String campusName : campsWiseInterestedMembersMap.keySet()) {
								Map<Long,List<TrainingCampVO>> batchwiseMap = campsWiseInterestedMembersMap.get(campusName);
								if(batchwiseMap != null && batchwiseMap.size()>0)
								{
									
									List<TrainingCampVO> batchVOList = new ArrayList<TrainingCampVO>(0);
									for (Long batchId : batchwiseMap.keySet()) {
										TrainingCampVO campVO = new TrainingCampVO();
										
										campVO.setTrainingCampVOList(batchwiseMap.get(batchId));
										
										TrainingCampVO batchVO = new TrainingCampVO();
										Long othrsCount = 0l;
											List<TrainingCampVO> vo = campVO.getTrainingCampVOList();
											for (TrainingCampVO trainingCampVO2 : vo) {
												batchVO.setName(trainingCampVO2.getName());
												batchVO.setTrainingCampName(trainingCampVO2.getTrainingCampName());
												batchVO.setStartDateStr(trainingCampVO2.getStartDateStr());
												batchVO.setEndDateStr(trainingCampVO2.getEndDateStr());
												batchVO.setAllocatedCallsCount(trainingCampVO2.getAllocatedCallsCount());
												batchVO.setDialedCallsCount(trainingCampVO2.getDialedCallsCount());
												String countBelongsTo = trainingCampVO2.getSchdlStatus();
												
												Map<String,Map<Long,TrainingCampVO>> campsWiseAllocatdMembersMap = programWiseAllocatedMembersMap.get(programNameStr);
												if(campsWiseAllocatdMembersMap != null && campsWiseAllocatdMembersMap.size()>0){
													Map<Long,TrainingCampVO> batchwiseAllocatdMembersMap = campsWiseAllocatdMembersMap.get(campusName);
													if(batchwiseAllocatdMembersMap != null && batchwiseAllocatdMembersMap.size()>0)
													{
														TrainingCampVO batchVO3 = batchwiseAllocatdMembersMap.get(batchId);
														if(batchVO3 != null)
															batchVO.setAllocatedCalls(batchVO3.getAllocatedCallsCount());
													}
												}
												
												if(countBelongsTo.equalsIgnoreCase("Interested")){
													batchVO.setInterestedCount(trainingCampVO2.getInterestedCount());
												}
												else if(countBelongsTo.equalsIgnoreCase("Confirmed")){
													batchVO.setAcceptedCount(trainingCampVO2.getInterestedCount());
												}
												else if(countBelongsTo.equalsIgnoreCase("Not Interested")){
													batchVO.setNotInterestedCount(trainingCampVO2.getInterestedCount());
												}
												else if(countBelongsTo.equalsIgnoreCase("Call Back - Busy")){
													batchVO.setBusyCount(trainingCampVO2.getInterestedCount());
												}
												else if(countBelongsTo.equalsIgnoreCase("Call Back - Confirm Later")){
													batchVO.setConformLaterCount(trainingCampVO2.getInterestedCount());
												}
												else{
													if(trainingCampVO2.getAllocatedCallsCount() != null)
														othrsCount = othrsCount+trainingCampVO2.getInterestedCount();
													
													batchVO.setOthersCount(othrsCount);
												}
												
											}
											batchVOList.add(batchVO);
									}
									if(batchVOList != null && batchVOList.size()>0)
									{
										programVO.getTrainingCampVOList().addAll(batchVOList);
									}
								}
							}
						}
						trainingProgramsList.add(programVO);
					}
					returnVO.setTrainingCampVOList(trainingProgramsList);
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseDateWiseInterestedMembersDetails method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	
	public TrainingCampVO getCampusWiseDateWiseCampDetails(List<Long> campusIdsList,String searchTypeId,String startDate,String endDate)
	{
		TrainingCampVO returnVO = null;
		try {
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getCampusWiseDateWiseCampDetails method in TrainingCampService class.",e);
		}
		return returnVO;
	}
	
	public List<TraingCampCallerVO> getScheduleCallStatusCount(Long userId,Long callPurposeId)
	{
		List<TraingCampCallerVO> returnList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getScheduleWiseCallStatusCount(userId,callPurposeId);
			if(list != null)
			{
				
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
							programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
							campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
							scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
							scheduleVo.setSubList(getStatusList());
							scheduleVo.setScheduleStatusList(getScheduleStatusList());
							campVo.getSubList().add(scheduleVo);
							programVo.setSpanCnt(programVo.getSpanCnt()+1);
						}
						scheduleVo.setTotal(scheduleVo.getTotal() + commonMethodsUtilService.getLongValueForObject(params[0]));
						TraingCampCallerVO schedulestatusVo  = getMatchedVo(scheduleVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params[9])); // Status
						if(schedulestatusVo != null)
						{
							schedulestatusVo.setCount(schedulestatusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
						}
						if(params[7] != null)
						{
							TraingCampCallerVO statusVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Status
							if(statusVo != null)
							{
								statusVo.setCount(statusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
							}
						}
						
				}
				DateUtilService date = new DateUtilService();
				List<Object[]> list1 = trainingCampScheduleInviteeCallerDAO.getScheduleWiseDayWiseCallBackCount(userId,callPurposeId,date.getCurrentDateAndTime());
				if(list1 != null)
				{
					for(Object[] params1 : list1)
					{
						TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params1[1])); // Program
						if(programVo != null)
						{
							TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params1[3])); // Camp
							if(campVo != null)
							{
								TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params1[5])); // Schedule
								if(scheduleVo != null)
								{
									TraingCampCallerVO schedulestatusVo  = getMatchedVo(scheduleVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params1[9])); // Status
									if(schedulestatusVo != null)
										schedulestatusVo.setTodayCnt(schedulestatusVo.getTodayCnt() + commonMethodsUtilService.getLongValueForObject(params1[0]));
								}
								
							}
						}
					}
				}
			}
			if(returnList.size() > 0)
			  returnList.get(0).setCampCallerId(userId);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallStatusCount() method", e);
		}
		return returnList;
	}

	public List<TraingCampCallerVO> getAgentCallDetailsByCampCallerId(Long campCallerId)
	{
		List<TraingCampCallerVO> resultList = new ArrayList<TraingCampCallerVO>(0);
		List<Long> batchStatusIds = new ArrayList<Long>(0);
		batchStatusIds.add(1l);
		batchStatusIds.add(2l);
		Date toDayDate = new DateUtilService().getCurrentDateAndTime();
		
		try{
			
		List<Object[]> calendarScheduleList = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, 1l, null, batchStatusIds);
		if(calendarScheduleList != null && calendarScheduleList.size() > 0)
		{
			TraingCampCallerVO calendarScheduleCallStatusVO = new TraingCampCallerVO();
			calendarScheduleCallStatusVO.setName("Schedule Confirmation");
			calendarScheduleCallStatusVO.setSubList(getStatusList());
			calendarScheduleCallStatusVO.setScheduleStatusList(getScheduleStatusList());
			setAgentCallDetailsByCampCallerId(calendarScheduleCallStatusVO, calendarScheduleList);
			try {
				Long interestedCount = calendarScheduleCallStatusVO.getScheduleStatusList().get(3).getCount();// interested
				Long confirmedCount = calendarScheduleCallStatusVO.getScheduleStatusList().get(9).getCount(); // confirmed
				interestedCount = interestedCount+confirmedCount;
				calendarScheduleCallStatusVO.getScheduleStatusList().get(3).setCount(interestedCount);
			} catch (Exception e) {
			}
			
			List<Object[]> toDaycalendarScheduleList = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, 1l, toDayDate, batchStatusIds);
			if(toDaycalendarScheduleList != null && toDaycalendarScheduleList.size() > 0)
			  setTodayAgentCallDetailsByCampCallerId(calendarScheduleCallStatusVO, toDaycalendarScheduleList);
			
			resultList.add(calendarScheduleCallStatusVO);
		}
		
		List<Object[]> batchConfirmationList = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, 2l, null, batchStatusIds);
		if(batchConfirmationList != null && batchConfirmationList.size() > 0)
		{
			TraingCampCallerVO batchConfirmationCallStatusVO = new TraingCampCallerVO();
			batchConfirmationCallStatusVO.setName(" Batch Confirmation ");
			batchConfirmationCallStatusVO.setSubList(getStatusList());
			batchConfirmationCallStatusVO.setScheduleStatusList(getScheduleStatusList());
			setAgentCallDetailsByCampCallerId(batchConfirmationCallStatusVO, batchConfirmationList);
			
			List<Object[]> toDayBatchConfirmationList = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, 2l, toDayDate, batchStatusIds);
			if(toDayBatchConfirmationList != null && toDayBatchConfirmationList.size() > 0)
			  setTodayAgentCallDetailsByCampCallerId(batchConfirmationCallStatusVO, toDayBatchConfirmationList);
			
			resultList.add(batchConfirmationCallStatusVO);
			
		}
		
		List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, null, null, null);
		if(list != null && list.size() > 0)
		{
			TraingCampCallerVO agentCallStatusVO = new TraingCampCallerVO();
			agentCallStatusVO.setName(" Total ");
			agentCallStatusVO.setSubList(getStatusList());
			agentCallStatusVO.setScheduleStatusList(getScheduleStatusList());
			setAgentCallDetailsByCampCallerId(agentCallStatusVO, list);
			
			List<Object[]> todayAgentList = trainingCampScheduleInviteeCallerDAO.getAgentCallDetailsByCampCallerId(campCallerId, null, toDayDate, null);
			if(todayAgentList != null && todayAgentList.size() > 0)
			 setTodayAgentCallDetailsByCampCallerId(agentCallStatusVO, todayAgentList);
			  
			resultList.add(agentCallStatusVO);
			
		}
		
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAgentCallDetailsByCampCallerId() method, Exception - ",e) ;
		}
		
		return resultList;
		
	}
	
	public void setAgentCallDetailsByCampCallerId(TraingCampCallerVO agentCallStatusVO,List<Object[]> list)
	{
		try{
			for(Object[] params:list)
			{
				if(params[2] != null)
				{
				TraingCampCallerVO statusVo = getMatchedVo(agentCallStatusVO.getSubList(), (Long)params[2]);
				if(statusVo != null)
				 statusVo.setCount(statusVo.getCount() + (Long)params[0]);
				}
				if(params[4] != null)
				{
				  TraingCampCallerVO scheduleStatusVo = getMatchedVo(agentCallStatusVO.getScheduleStatusList(), (Long)params[4]);
					if(scheduleStatusVo != null)
					{
						scheduleStatusVo.setCount(scheduleStatusVo.getCount()+(Long)params[0]);
						
					}
				}
				agentCallStatusVO.setTotal(agentCallStatusVO.getTotal()+(Long)params[0]);
			}
		}catch (Exception e) {
			LOG.error(" Exception Occured in setAgentCallDetailsByCampCallerId() method, Exception - ",e);
		}
	}
	
	public void setTodayAgentCallDetailsByCampCallerId(TraingCampCallerVO agentCallStatusVO,List<Object[]> list)
	{
	  try{
		   for(Object[] params:list)
		   {
			  TraingCampCallerVO scheduleStatusVo = getMatchedVo(agentCallStatusVO.getScheduleStatusList(), (Long)params[4]);
				if(scheduleStatusVo != null)
					scheduleStatusVo.setTodayCnt(scheduleStatusVo.getTodayCnt()+(Long)params[0]);
					
		   }
		  
	  }catch (Exception e) {
		  LOG.error(" Exception Occured in setTodayAgentCallDetailsByCampCallerId() method, Exception - ",e);
	}
	}
	
	public List<TraingCampCallerVO> getBatchCallStatusCount(Long userId,Long callPurposeId)
	{
		List<TraingCampCallerVO> returnList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getBatchWiseWiseCallStatusCount(userId,callPurposeId);
			if(list != null)
			{
				
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); // Program
						if(programVo == null)
						{
							programVo = new TraingCampCallerVO()	;
							programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
							programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
							returnList.add(programVo);
						}
						
						TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); // Camp
						if(campVo == null)
						{
							campVo = new TraingCampCallerVO()	;
							campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
							campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
							
							programVo.getSubList().add(campVo);
						}
						TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
						if(scheduleVo == null)
						{
							scheduleVo = new TraingCampCallerVO()	;
							scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
							scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
							campVo.getSubList().add(scheduleVo);
						}
						
						TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[10])); // Batch
						if(batchVo == null)
						{
							batchVo = new TraingCampCallerVO()	;
							batchVo.setId(commonMethodsUtilService.getLongValueForObject(params[10]));
							batchVo.setName(commonMethodsUtilService.getStringValueForObject(params[11]));
							batchVo.setSubList(getStatusList());
							batchVo.setScheduleStatusList(getScheduleStatusList());
							scheduleVo.getSubList().add(batchVo);
							programVo.setSpanCnt(programVo.getSpanCnt() + 1);
							campVo.setSpanCnt(campVo.getSpanCnt() + 1);
							scheduleVo.setSpanCnt(scheduleVo.getSpanCnt() + 1);

						} 
						batchVo.setTotal(batchVo.getTotal() + commonMethodsUtilService.getLongValueForObject(params[0]));
						TraingCampCallerVO batchstatusVo  = getMatchedVo(batchVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params[9])); // Status
						if(batchstatusVo != null)
						{
							batchstatusVo.setCount(batchstatusVo.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
						}
						if(params[7] != null)
						{
							TraingCampCallerVO batchstatusVo1  = getMatchedVo(batchVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Status
							if(batchstatusVo1 != null)
							{
								batchstatusVo1.setCount(batchstatusVo1.getCount() + commonMethodsUtilService.getLongValueForObject(params[0]));
							}
						}
						
				}
				DateUtilService date= new DateUtilService();
				List<Object[]> list1 = trainingCampScheduleInviteeCallerDAO.getBatchWiseDayWiseCallBackCount(userId,callPurposeId,date.getCurrentDateAndTime());
				if(list1 != null)
				{
					
					for(Object[] params1 : list1)
					{
						TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params1[1])); // Program
						if(programVo != null)
						{
							TraingCampCallerVO campVo  = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params1[3])); // Camp
							if(campVo != null)
							{
								TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params1[5])); // Schedule
								if(scheduleVo != null)
								{
									TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params1[10])); // Batch
									if(batchVo != null)
									{
										TraingCampCallerVO batchstatusVo  = getMatchedVo(batchVo.getScheduleStatusList(),commonMethodsUtilService.getLongValueForObject(params1[9])); // Status
										if(batchstatusVo != null)
										{
											batchstatusVo.setTodayCnt(batchstatusVo.getTodayCnt() + commonMethodsUtilService.getLongValueForObject(params1[0]));	
										}
									}
								}
							}
						}
					}
			}
		  }
			if(returnList.size() > 0)
			  returnList.get(0).setCampCallerId(userId);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallStatusCount() method", e);
		}
		return returnList;
	}

	public List<TraingCampCallerVO> getMembersCountByBatchStatus(Long campCallerId,String batchStatus)
	{
		List<TraingCampCallerVO> returnList = null;
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getMembersCountByBatchStatusAndCallerId(campCallerId, batchStatus);
			
			if(list != null && list.size() > 0)
			{
				returnList = new ArrayList<TraingCampCallerVO>(0);
				for(Object[] params : list)
				{
					TraingCampCallerVO programVo  = getMatchedVo(returnList,commonMethodsUtilService.getLongValueForObject(params[1])); //program
					if(programVo == null)
					{
						programVo = new TraingCampCallerVO();
						programVo.setId(commonMethodsUtilService.getLongValueForObject(params[1]));
						programVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]));
						returnList.add(programVo);
					}
					
					TraingCampCallerVO campVo = getMatchedVo(programVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[3])); //camp
					if(campVo == null)
					{
						campVo = new TraingCampCallerVO();
						campVo.setId(commonMethodsUtilService.getLongValueForObject(params[3]));
						campVo.setName(commonMethodsUtilService.getStringValueForObject(params[4]));
						programVo.getSubList().add(campVo);
					}
					TraingCampCallerVO scheduleVo  = getMatchedVo(campVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[5])); // Schedule
					if(scheduleVo == null)
					{
						scheduleVo = new TraingCampCallerVO()	;
						scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(params[5]));
						scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(params[6]));
						campVo.getSubList().add(scheduleVo);
					}
					TraingCampCallerVO batchVo  = getMatchedVo(scheduleVo.getSubList(),commonMethodsUtilService.getLongValueForObject(params[7])); // Batch
					if(batchVo == null)
					{
						batchVo = new TraingCampCallerVO()	;
						batchVo.setId(commonMethodsUtilService.getLongValueForObject(params[7]));
						batchVo.setName(commonMethodsUtilService.getStringValueForObject(params[8]));
						batchVo.setCount(commonMethodsUtilService.getLongValueForObject(params[0]));
						batchVo.setSpanCnt(1l);
						
						scheduleVo.getSubList().add(batchVo);
						
						programVo.setSpanCnt(programVo.getSpanCnt() + 1);
						campVo.setSpanCnt(campVo.getSpanCnt() + 1);
						scheduleVo.setSpanCnt(scheduleVo.getSpanCnt()+1);
					} 
					
					
				}
				
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getMembersCountByBatchStatus() method, Exception - ", e);
		}
		
		return returnList;
		
	}
	
	
	public TrainingMemberVO getScheduleCallMemberDetails(TraingCampDataVO inputVo,Integer startIndex,Integer maxIndex)
	{

		List<Long> statusIds = new ArrayList<Long>();
		 TrainingMemberVO returnVo = new TrainingMemberVO();
		 List count =  null;
		List<Object[]> list = null;
		try{
			statusIds = getCallStatusIds(inputVo.getStatus());
			
			Date toDayDate = null;
			if(inputVo.getDateStr() != null && inputVo.getDateStr().trim().length() > 0)
			{
			   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			   toDayDate = sdf.parse(inputVo.getDateStr());
			}
			List<Long> cadreIds = new ArrayList<Long>();
			if(inputVo.getSearchType() == null){
			 list = trainingCampScheduleInviteeCallerDAO.getScheduleWisememberDetailsCount(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus(),toDayDate,startIndex,maxIndex);
				/*if(inputVo.getPurposeId()==1l){
					List<Object[]> sdlCnfmDtls1 = trainingCampScheduleInviteeCallerDAO.getScheduleConfirmationDetails(inputVo.getPurposeId(),inputVo.getUserId());
					list = sdlCnfmDtls1;
					
						List<Object[]> sdlCnfmDtls2 = trainingCampScheduleInviteeTrackDAO.getScheduleConfirmationDetails(inputVo.getPurposeId(),inputVo.getUserId());
						boolean flag=true;
						if(sdlCnfmDtls2!=null && sdlCnfmDtls2.size()>0){
							Map<Long,String> subMap = new HashMap<Long, String>();
							
							for (Object[] objects : sdlCnfmDtls1) {
								if(objects[9]!=null && objects[11]!=null){
									subMap.put((Long)objects[9], "");
								}
							}
						
							for (Object[] objects : sdlCnfmDtls2) {
								if(subMap.get((Long)objects[9])!=null){
									String temp = subMap.get((Long)objects[9]);
									if(flag){
										temp=objects[11].toString();
										flag=false;
									}else{
										temp=temp+","+objects[11].toString();
									}
									subMap.put((Long)objects[9], temp);
								}
							}
						
							for (Object[] objects : sdlCnfmDtls1) {
								String temp = subMap.get((Long)objects[9]);
								objects[11]=(Object)temp;
							}
						}
						
						list = sdlCnfmDtls1;
					
					
					
				}else if(inputVo.getPurposeId()==2l){
					List<Object[]> sdlCnfmDtls1 = trainingCampScheduleInviteeCallerDAO.getScheduleConfirmationDetails(inputVo.getPurposeId(),inputVo.getUserId());
					List<Object[]> sdlCnfmDtls2 = trainingCampScheduleInviteeTrackDAO.getScheduleConfirmationDetails(inputVo.getPurposeId(),inputVo.getUserId());
					boolean flag=true;
					if(sdlCnfmDtls2!=null && sdlCnfmDtls2.size()>0){
						
						Map<Long,String> subMap = new HashMap<Long, String>();
						
						for (Object[] objects : sdlCnfmDtls1) {
							if(objects[9]!=null && objects[11]!=null){
								subMap.put((Long)objects[9], "");
							}
						}
					
						for (Object[] objects : sdlCnfmDtls2) {
							if(subMap.get((Long)objects[9])!=null){
								String temp = subMap.get((Long)objects[9]);
								if(flag){
									temp=objects[11].toString();
									flag=false;
								}else{
									temp=temp+","+objects[11].toString();
								}
								subMap.put((Long)objects[9], temp);
							}
						}
					
						for (Object[] objects : sdlCnfmDtls1) {
							String temp = subMap.get((Long)objects[9]);
							objects[11]=(Object)temp;
						}
					}
					
					list = sdlCnfmDtls1;
					
				}*/
			 count = trainingCampScheduleInviteeCallerDAO.getScheduleWisememberDetailsTotalCount(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus(),toDayDate);
			}
			else
			{
				 list = trainingCampScheduleInviteeCallerDAO.getScheduleWisememberDetailsCountForSearch(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus(),toDayDate,startIndex,maxIndex);
				 count = trainingCampScheduleInviteeCallerDAO.getScheduleWisememberDetailsCountForSearchCount(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus(),toDayDate);
			}
			
			if(list != null && list.size() > 0)
			{
				
				List<TrainingMemberVO> resultList = setMemberDetails(list,cadreIds);
			
				if(count != null && count.size() > 0)
				returnVo.setTotalCount(new Long(count.get(0).toString()));
				else
					returnVo.setTotalCount(0l);
				returnVo.setSubList(resultList);
			}
			List<Object[]> remarks = trainingCampScheduleInviteeTrackDAO.getMemberRemarks(inputVo,statusIds,inputVo.getStatusType(),inputVo.getStatus(),toDayDate,cadreIds);
			if(remarks != null && remarks.size() > 0)
			{
				DateUtilService date = new DateUtilService();
				for(Object[] params : remarks)
				{
					TrainingMemberVO vo = getMatchedVo1(returnVo.getSubList(), (Long)params[0]);
					if(vo != null)
					{
						TrainingMemberVO remarkVo = new TrainingMemberVO();
						remarkVo.setName(params[1] != null ? params[1].toString() : "");
						if(params[2] != null && !params[2].toString().isEmpty())
						remarkVo.setImage(date.convert12HoursDateFormat(params[2].toString().substring(0, 19)));
						else
						remarkVo.setImage("");	
						vo.getSubList().add(remarkVo);
					}
					
				}
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService getScheduleCallMemberDetails() method", e);
		}
		return returnVo;
	
	}
	
	
	public List<Long> getCallStatusIds(String status)
	{
		List<Long> statusIds = new ArrayList<Long>();
		if(status.equalsIgnoreCase("dialed"))
		{
			statusIds.add(1l);statusIds.add(2l);statusIds.add(3l); //all
		}
		else if(status.equalsIgnoreCase("answered"))
		{
			statusIds.add(1l); // answered
		}
		else if(status.equalsIgnoreCase("busy"))
		{
			statusIds.add(3l); // userbusy
		}
		else if(status.equalsIgnoreCase("Switchoff"))
		{
			statusIds.add(2l); // switchOff
		}
		
		else if(status.equalsIgnoreCase("callback"))
		{
			statusIds.add(6l);statusIds.add(7l);
		}
		else if(status.equalsIgnoreCase("interested"))
		{
			statusIds.add(4l);
		}
		else if(status.equalsIgnoreCase("notInterested"))
		{
		statusIds.add(5l);
		}
		else if(status.equalsIgnoreCase("later"))
		{
		statusIds.add(3l);
		}
		else if(status.equalsIgnoreCase("Pending"))
		{
		statusIds.add(2l);
		}
		return statusIds;	
	}
	public List<TrainingMemberVO> setMemberDetails(List<Object[]> list,List<Long> cadreIds)
	{
		//List<Long> cadreIds = new ArrayList<Long>();
		List<TrainingMemberVO>  returnList = new ArrayList<TrainingMemberVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TrainingMemberVO vo =new TrainingMemberVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					String fname = commonMethodsUtilService.getStringValueForObject(params[1]);
					String lname = commonMethodsUtilService.getStringValueForObject(params[2]);
					vo.setName(fname +" "+lname);
					vo.setMobileNumber(commonMethodsUtilService.getStringValueForObject(params[3]));
					vo.setImage(commonMethodsUtilService.getStringValueForObject(params[4]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(params[6]));
					vo.setAge(commonMethodsUtilService.getStringValueForObject(params[7]));
					vo.setLocation(commonMethodsUtilService.getStringValueForObject(params[8]));
					vo.setInviteeId(params[9] != null ? (Long)params[9] : 0l);
					vo.setInviteeCallerId(params[10] != null ? (Long)params[10] : 0l);
					vo.setRemarks(params[11] != null ? params[11].toString() : "");
					vo.setConstituency(params[12] != null ? params[12].toString() : "");
					vo.setTrainingCampBatch(params[13]!= null?(Long)params[13]:null);
					vo.setCallStatus(params[14] != null ? params[14].toString() : "");
					returnList.add(vo);
					if(!cadreIds.contains(commonMethodsUtilService.getLongValueForObject(params[0])))
						cadreIds.add(commonMethodsUtilService.getLongValueForObject(params[0]));
				}
				 List<Object[]> roles = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(cadreIds);
				 if(roles != null && roles.size() > 0)
				 {
					 for(Object[] params : roles)
					 {
						 TrainingMemberVO vo =  getMatchedVo1(returnList, commonMethodsUtilService.getLongValueForObject(params[0]));
						 if(vo != null)
						 {
							 vo.setRole(commonMethodsUtilService.getStringValueForObject(params[2]));
							 vo.setRoleCategory(commonMethodsUtilService.getStringValueForObject(params[3]));
							 String committeeLocation = cadreCommitteeService.getLocationName((Long)params[4], (Long)params[5]); 
							 vo.setCommitteeLocation(committeeLocation != null?committeeLocation:"");
						 }
					 }
				 }
				 
			}
		}
		catch (Exception e) {
			LOG.error("Exception Occured in TrainingCampService setMemberDetails() method", e);
		}
		return returnList;
	}
	
	public List<TraingCampCallerVO> getStatusList()
	{
		List<TraingCampCallerVO> statusList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = campCallStatusDAO.getCallStatusList();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TraingCampCallerVO vo = new TraingCampCallerVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
					statusList.add(vo);
				}
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return statusList;
	}
	
	public List<TraingCampCallerVO> getScheduleStatusList()
	{
		List<TraingCampCallerVO> statusList = new ArrayList<TraingCampCallerVO>();
		try{
			
			List<Object[]> list = scheduleInviteeStatusDAO.getAllStatusList();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					TraingCampCallerVO vo = new TraingCampCallerVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
					statusList.add(vo);
				}
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return statusList;
	}
	public TraingCampCallerVO getMatchedVo(List<TraingCampCallerVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(TraingCampCallerVO vo : resultList)
		{
			if(id!= null && vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	public TrainingMemberVO getMatchedVo1(List<TrainingMemberVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(TrainingMemberVO vo : resultList)
		{
			if(vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	public TrainingCampVO getMatchedCampVo(List<TrainingCampVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(TrainingCampVO vo : resultList)
		{
			if(vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	public List<Long> getTrainingCampUserTypeIds(Long adminId,boolean isAdmin){
	
		List<Long> users = new ArrayList<Long>(0);
		try {
			List<Object[]> callerAdminCallersList=trainingCampUserRelationDAO.getAgentsByCampCallerAdminId(adminId,isAdmin);
			if(callerAdminCallersList != null && callerAdminCallersList.size()>0)
			{
				for (Object[] caller : callerAdminCallersList) {
					users.add(commonMethodsUtilService.getLongValueForObject(caller[0]));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return users;
	}
			
	public List<IdNameVO> getBasicList(List<Object[]> list)
	{
		List<IdNameVO>  returnList = new ArrayList<IdNameVO>();
		try{
			List<Object[]> programs = trainingCampProgramDAO.getPrograms();
			return getBasicList(programs);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	public List<BasicVO> getCampsByProgramId(Long programId)
	{
		try{
			List<Object[]> camps = trainingCampScheduleDAO.getCampsForProgram(programId);
			return getBasicList1(camps);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getSchedulesByCampId(Long campId)
	{
		try{
			List<Object[]> schedules = trainingCampScheduleDAO.getSchedules(campId);
			return getBasicList1(schedules);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<BasicVO> getBatchesByScheduleId(Long scheduleId)
	{
		try{
			List<Object[]> batches = trainingCampBatchDAO.getBatchesForSchedule(scheduleId);
			return getBasicList1(batches);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	public List<BasicVO> getBasicList1(List<Object[]> list)
	{
		List<BasicVO>  returnList = new ArrayList<BasicVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					BasicVO vo = new BasicVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
					returnList.add(vo);
					
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return returnList;
	}
	
  public TrainingCampScheduleVO getTrainingProgramMembersBatchCount(String startDateString,String endDateString){
		
		TrainingCampScheduleVO finalTrainingVo=new TrainingCampScheduleVO();
		
		try{
			
			Date startDate =null;
			Date endDate =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			if(!startDateString.isEmpty())
				startDate= sdf.parse(startDateString);
			if(!endDateString.isEmpty())
			 endDate= sdf.parse(endDateString);

		
			//0.id,1.program/camp name 3.membersCount 4.batchCount 
			List<Object[]> programDetails = trainingCampScheduleInviteeCallerDAO.getTrainingProgramMembersBatchCount(startDate, endDate, IConstants.CONFIRMED,"program");
			List<Object[]> campDetails = trainingCampScheduleInviteeCallerDAO.getTrainingProgramMembersBatchCount(startDate, endDate, IConstants.CONFIRMED,"camp");
			
			
			List<TrainingCampScheduleVO> listForProgramVo=new ArrayList<TrainingCampScheduleVO>();
			List<TrainingCampScheduleVO> listForCampVo=new ArrayList<TrainingCampScheduleVO>();
			
			if(programDetails !=null && programDetails.size()>0){
				setListObjectsForTrainingProgramMembersBatchCount(programDetails,listForProgramVo,"program");
			}
			if(campDetails !=null && campDetails.size()>0){
				setListObjectsForTrainingProgramMembersBatchCount(campDetails,listForCampVo,"camp");
			}
			
			if(listForProgramVo !=null && listForProgramVo.size()>0){
				Long programCount=commonMethodsUtilService.getIntegerToLong(listForProgramVo.size());
				finalTrainingVo.setProgramCount(programCount);
				finalTrainingVo.setTrainingCampVOList(listForProgramVo);//ProgramWise List
			}
			if(listForCampVo !=null && listForCampVo.size()>0){
				Long campCount=commonMethodsUtilService.getIntegerToLong(listForCampVo.size());
				finalTrainingVo.setCampCount(campCount);
				finalTrainingVo.setTrainingCampScheduleVOList(listForCampVo);//CampWise List
			}
			
			return finalTrainingVo;
	
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return finalTrainingVo;
		
	}
	public void setListObjectsForTrainingProgramMembersBatchCount(List<Object[]> programDetails,List<TrainingCampScheduleVO> listVo,String type){
		
		for (Object[] objects : programDetails) {
			TrainingCampScheduleVO progamVo=new TrainingCampScheduleVO();
			
			progamVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
			progamVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
			progamVo.setTotalCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//members Count
			progamVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));//batchCount
			
			listVo.add(progamVo);
		}
	}
	
	public ResultStatus updateCadreStatusForTraining(final TrainingCadreVO inputVO)
	{
		
		final ResultStatus resultStatus = new ResultStatus();
		final DateUtilService date = new DateUtilService();
		final ResultStatus resultStatus1 =  (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						try{
			boolean flag = false;
			if(inputVO.getCallStatusId() == 1l && inputVO.getStatus().equalsIgnoreCase("callstatus"))//Answered
			{
				if(inputVO.getScheduleStatusId().longValue() > 0L && inputVO.getScheduleStatusId() == 10l)
				{
					Long maxNo = trainingCampBatchDAO.getMaxNumbersForBacth(inputVO.getBatchId());
					Long totalInBatch = trainingCampScheduleInviteeDAO.getBatchMembersCountByStatus(inputVO.getBatchId(),10l); // Confirm Batch
					if(totalInBatch != null && totalInBatch >= maxNo)
					{
						resultStatus.setResultPartial(true);
						resultStatus.setResultCode(2);
						flag = true;
					}
						
				}
				if(flag)
				return resultStatus;
				TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
					if(inputVO.getScheduleStatusId() != null && inputVO.getScheduleStatusId().longValue() == 4L  && inputVO.getBatchId().longValue() > 0L)
						trainingCampScheduleInvitee.setAttendingBatchId(inputVO.getBatchId());	
					if(inputVO.getRamarks() != null && inputVO.getRamarks().length() > 0)
						trainingCampScheduleInvitee.setRemarks(inputVO.getRamarks());
					
					if(inputVO.getScheduleStatusId().longValue() > 0L)
					{
						trainingCampScheduleInvitee.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
						
					}
					if(!flag)
					{
						trainingCampScheduleInvitee.setUpdatedBy(inputVO.getUserId());
						trainingCampScheduleInvitee.setUpdatedTime(date.getCurrentDateAndTime());
						trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
					}
			}
			else if(inputVO.getCallStatusId() == 2l)// switch-off
			{
				TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
				//trainingCampScheduleInvitee.setAttendingBatchId(null);
				//trainingCampScheduleInvitee.setScheduleInviteeStatusId(11L); 
				trainingCampScheduleInvitee.setUpdatedBy(inputVO.getUserId());
				trainingCampScheduleInvitee.setUpdatedTime(date.getCurrentDateAndTime());
				trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
			}
			else if(inputVO.getCallStatusId() == 3l)// User-busy
			{
				TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
				//trainingCampScheduleInvitee.setAttendingBatchId(null);
				//trainingCampScheduleInvitee.setScheduleInviteeStatusId(2L); 
				trainingCampScheduleInvitee.setUpdatedBy(inputVO.getUserId());
				trainingCampScheduleInvitee.setUpdatedTime(date.getCurrentDateAndTime());
				trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
			}
			if(flag == false)
			{
				TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
				if(trainingCampScheduleInviteeCaller != null)
				{
					inputVO.setTrainingCampCallerId(trainingCampScheduleInviteeCaller.getTrainingCampCallerId());
					
					trainingCampScheduleInviteeCaller.setCallStatusId(inputVO.getCallStatusId());
					trainingCampScheduleInviteeCaller.setUpdatedBy(inputVO.getUserId());
					trainingCampScheduleInviteeCaller.setUpdatedTime(date.getCurrentDateAndTime());
					trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
					voterDAO.flushAndclearSession();
					
				}
				
				saveTrackingInfo(inputVO);	
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					}
					
		catch (Exception e) {
			LOG.error("Exception Occured in updateCadreStatusForTraining()", e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
						return resultStatus;
				}
		});
		return resultStatus;
	}
	public void saveTrackingInfo(TrainingCadreVO inputVO)
	{
		try{
			DateUtilService date= new DateUtilService();
			TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
			TrainingCampScheduleInviteeTrack trainingCampScheduleInviteeTrack = new TrainingCampScheduleInviteeTrack();
		
			if(inputVO.getRamarks() != null && !inputVO.getRamarks().isEmpty())
				trainingCampScheduleInviteeTrack.setRemarks(inputVO.getRamarks());
			if(trainingCampScheduleInviteeCaller.getCallPurposeId() != null)
			trainingCampScheduleInviteeTrack.setCampCallPurposeId(trainingCampScheduleInviteeCaller.getCallPurposeId());
			trainingCampScheduleInviteeTrack.setCampCallStatusId(inputVO.getCallStatusId());
			if(inputVO.getScheduleStatusId() != null && inputVO.getScheduleStatusId() > 0)
			trainingCampScheduleInviteeTrack.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
			//trainingCampScheduleInviteeTrack.setTdpCadreId(inputVO.getTdpCadreId());
			trainingCampScheduleInviteeTrack.setTrainingCampCallerId(inputVO.getTrainingCampCallerId());
			trainingCampScheduleInviteeTrack.setCalledTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setInsertedBy(inputVO.getUserId());
			trainingCampScheduleInviteeTrack.setUpdatedBy(inputVO.getUserId());
			trainingCampScheduleInviteeTrack.setInsertedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setUpdatedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeTrack.setTrainingCampScheduleInviteeId(inputVO.getInvitteId());
			trainingCampScheduleInviteeTrack.setTrainingCampScheduleInviteeCallerId(inputVO.getInviteeCallerId());
			if(trainingCampScheduleInviteeCaller.getTrainingCampCallerAdminId() != null)
			trainingCampScheduleInviteeTrack.setTrainingCampCallerAdminId(trainingCampScheduleInviteeCaller.getTrainingCampCallerAdminId());
			
			if(inputVO.getCallBackDate() != null)
			{
				String dateSample =inputVO.getCallBackDate()+ " "+inputVO.getCallBackTime();
				 trainingCampScheduleInviteeTrack.setCampCallStatusId(1l);
				 String oldScheduledDate = inputVO.getCallBackDate() +" "+inputVO.getCallBackTime();
			     DateFormat oldFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			    // DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			     Date oldDate = (Date)oldFormatter .parse(oldScheduledDate);
			trainingCampScheduleInviteeTrack.setCallBackTime(oldDate);
			}
			trainingCampScheduleInviteeTrackDAO.save(trainingCampScheduleInviteeTrack);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public ResultStatus updateCallBackCadreStatusForTraining(final TrainingCadreVO inputVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		final DateUtilService date = new DateUtilService();
		final ResultStatus resultStatus1 =  (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						try{
			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		    Date date1 = parseFormat.parse(inputVO.getCallBackTime());
		    System.out.println(parseFormat.format(date1) + " = " + displayFormat.format(date1));
		    inputVO.setCallBackTime(displayFormat.format(date1));
			TrainingCampScheduleInvitee trainingCampScheduleInvitee = trainingCampScheduleInviteeDAO.get(inputVO.getInvitteId());
					
					if(inputVO.getRamarks() != null && inputVO.getRamarks().length() > 0)
						trainingCampScheduleInvitee.setRemarks(inputVO.getRamarks());
					if(inputVO.getScheduleStatusId().longValue() > 0L)
						trainingCampScheduleInvitee.setScheduleInviteeStatusId(inputVO.getScheduleStatusId());
					if(inputVO.getScheduleStatusId().longValue() != 4L)
						trainingCampScheduleInvitee.setAttendingBatchId(null);
						trainingCampScheduleInvitee.setUpdatedBy(inputVO.getUserId());
						//String dateSample =inputVO.getCallBackDate()+ " "+inputVO.getCallBackTime();

						 String oldScheduledDate = inputVO.getCallBackDate() +" "+inputVO.getCallBackTime();
					     DateFormat oldFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
					    // DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
					     Date oldDate = (Date)oldFormatter .parse(oldScheduledDate);
						trainingCampScheduleInvitee.setCallBackTime(oldDate);
						trainingCampScheduleInvitee.setUpdatedTime(date.getCurrentDateAndTime());
						trainingCampScheduleInviteeDAO.save(trainingCampScheduleInvitee);
			
					
			TrainingCampScheduleInviteeCaller trainingCampScheduleInviteeCaller = trainingCampScheduleInviteeCallerDAO.get(inputVO.getInviteeCallerId());
			trainingCampScheduleInviteeCaller.setCallStatusId(1l);
			trainingCampScheduleInviteeCaller.setUpdatedBy(inputVO.getUserId());
			//trainingCampScheduleInviteeCaller.setInsertedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCaller.setUpdatedTime(date.getCurrentDateAndTime());
			trainingCampScheduleInviteeCallerDAO.save(trainingCampScheduleInviteeCaller);
			voterDAO.flushAndclearSession();
			saveTrackingInfo(inputVO);	
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
		catch (Exception e) {
			LOG.error("Exception Occured in updateCallBackCadreStatusForTraining()", e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
					}
				});
				return resultStatus;
			
	}
	
	
	public TrainingCampScheduleVO getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,String startDateString,String endDateString){
		TrainingCampScheduleVO returnVO = new TrainingCampScheduleVO();
		List<TrainingCampScheduleVO> finalList=null;
		try{
			
			/*SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate=sdf.parse("08/02/2015");
			Date endDate=sdf.parse("08/05/2015");
			
			List<Long> userIds=new ArrayList<Long>();
			
			userIds.add(1l);*/
			
			Date startDate =null;
			Date endDate =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			if(!startDateString.isEmpty())
				startDate= sdf.parse(startDateString);
			if(!endDateString.isEmpty())
			 endDate= sdf.parse(endDateString);

			
			List<Object[]>  scheduleAndConfirmationCallsTotal= trainingCampScheduleInviteeCallerDAO.getScheduleAndConfirmationCallsOfCallerToAgent(userIds,startDate,endDate,"totalCalls");
			List<Object[]> scheduleAndConfirmationCallsDialed=trainingCampScheduleInviteeCallerDAO.getScheduleAndConfirmationCallsOfCallerToAgent(userIds,startDate,endDate,"dialedCalls");
			
			Map<Long,TrainingCampScheduleVO> finalVo=new HashMap<Long, TrainingCampScheduleVO>();
			
			List<Object[]>  allPurposes = campCallPurposeDAO.getAllCampCallPurpose();
			
			if(allPurposes !=null && allPurposes.size()>0){
				
				for(Object[] purpose:allPurposes){
					TrainingCampScheduleVO vo = new TrainingCampScheduleVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(purpose[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(purpose[1]));
					vo.setCount(0l);//call Purpose wise count
					vo.setDialedCallsCount(0l);//dialed count forcall purpose
					
					finalVo.put(vo.getId(), vo);
				}
				
				
			}
			
			
			if(scheduleAndConfirmationCallsTotal !=null && scheduleAndConfirmationCallsTotal.size()>0){
				setScheduleAndConfirmationCallsOfCallerToAgent(scheduleAndConfirmationCallsTotal,finalVo,"total");
			}
			if(scheduleAndConfirmationCallsDialed !=null && scheduleAndConfirmationCallsDialed.size()>0){
				setScheduleAndConfirmationCallsOfCallerToAgent(scheduleAndConfirmationCallsDialed,finalVo,"dialed");
			}
			
			if(finalVo !=null && finalVo.size()>0){
				
				finalList=new ArrayList<TrainingCampScheduleVO>(finalVo.values());
				
				//For total assigned And Total Dialed Calls Count
				Long totalAssignedCount=0l;
				Long totalDialedCount=0l;
				if(finalList !=null && finalList.size()>0){
					for(TrainingCampScheduleVO vo:finalList){
						totalAssignedCount =totalAssignedCount+vo.getCount();
						totalDialedCount=totalDialedCount+vo.getDialedCallsCount();
					}
					
					TrainingCampScheduleVO listVo=finalList.get(0);
					
					listVo.setTotalAssignedCount(totalAssignedCount);//totalAssignedCalls To Agents
					listVo.setTotalDialedCallsCount(totalDialedCount);//total Agent Dialed calls
				}
			}
			
			if(finalList != null && finalList.size()>0)
				returnVO.setTrainingCampScheduleVOList(finalList);
			
			return returnVO;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return returnVO;
	}
	

	public void setScheduleAndConfirmationCallsOfCallerToAgent(List<Object[]>  scheduleAndConfirmationCalls,Map<Long,TrainingCampScheduleVO> finalVo,String type){
		
		if(scheduleAndConfirmationCalls !=null && scheduleAndConfirmationCalls.size()>0){
			
			for (Object[] objects : scheduleAndConfirmationCalls) {
				
				TrainingCampScheduleVO scheduleVo =finalVo.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
				
				if(scheduleVo ==null){
					scheduleVo=new TrainingCampScheduleVO();
				}
				scheduleVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				scheduleVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				
				if(type.equalsIgnoreCase("total")){
					scheduleVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//purpose wise assigned calls
				}
				else if(type.equalsIgnoreCase("dialed")){
					scheduleVo.setDialedCallsCount(commonMethodsUtilService.getLongValueForObject(objects[2]));//purpose wise dialed calls
				}
				
				finalVo.put(scheduleVo.getId(), scheduleVo);
			}
			
		}
		
	}
	
	public TrainingCampCallStatusVO getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId)
	{
		TrainingCampCallStatusVO callStatusVO = new TrainingCampCallStatusVO();
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallStatusCountByTrainingCampCallerId(trainingCampCallerId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params: list)
				{
					if(params[1] !=null && params[1].toString().equalsIgnoreCase("Call Answered"))
						callStatusVO.setAnswerdeCallsCount((Long)params[0]);
						
					else if(params[1] !=null && (params[1].toString().equalsIgnoreCase(IConstants.SWITCHOFF) || params[1].toString().equalsIgnoreCase(IConstants.TRAINING_USER_BUSY)))
						callStatusVO.setUserBusyCallsCount(callStatusVO.getUserBusyCallsCount()+(Long)params[0]);
					
					
					callStatusVO.setAllocatedCallsCount(callStatusVO.getAllocatedCallsCount()+(Long)params[0]);
					
				}
				callStatusVO.setDialledCallsCount(callStatusVO.getAnswerdeCallsCount()+callStatusVO.getUserBusyCallsCount());
			}
			
			List<Object[]> list1 = trainingCampScheduleInviteeCallerDAO.getInterestedMembersCountByCampCallerId(trainingCampCallerId);
			if(list1 != null && list1.size() > 0)
			{
				for(Object[] obj: list1)
				{
					if(obj[1] !=null && obj[1].toString().equalsIgnoreCase(IConstants.INTERESTED))
						callStatusVO.setInterestedMemCount((Long)obj[0]);
						
					else if(obj[1] !=null && obj[1].toString().equalsIgnoreCase(IConstants.NOTINTERESTED))
						callStatusVO.setNotIntereMemCount((Long)obj[0]);
					
					else
					 callStatusVO.setCurrentlyNotIntMemCount((Long)obj[0]);
					
				}
				
			}
			
			
			
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCallStatusCountByTrainingCampCallerId() method, Exception - ",e);
		}
		return callStatusVO;
		
		
	}
	public List<IdNameVO> getUserIdsByType(){
		
		List<IdNameVO> listOfUsers=new ArrayList<IdNameVO>();
		try{
			List<Object[]> users=trainingCampUserDAO.getUserIdsByType(5l);
			if(users !=null && users.size()>0){
				for(Object[] user:  users){
					IdNameVO vo = new IdNameVO();
					vo.setId(user[0] !=null ? (Long)user[0] :0l);//userId
					vo.setName(user[1] !=null ? user[1].toString() : "");
					
					listOfUsers.add(vo);
				}
				return listOfUsers;
			}
		}catch(Exception e){
			LOG.error(" Exception Occured in getUserIdsByType() method, Exception - ",e);
		}
		return listOfUsers;
	}
	
	public List<CallStatusVO> getTheMeetingLevelDetails(Long userId){
		List<CallStatusVO> levelDetails = new ArrayList<CallStatusVO>();
		try {
			LOG.info("Entered into getTheMeetingLevelDetails");
			
			List<Object[]> meetingLevelDetails= partyMeetingUserDAO.getTheMeetingLevelDetails(userId);
			
			if(meetingLevelDetails!=null && meetingLevelDetails.size()>0){
				for (Object[] objects : meetingLevelDetails) {
					CallStatusVO vo = new CallStatusVO();
					vo.setLocationId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setLocationLevel(objects[1]!=null?objects[1].toString():"");
					levelDetails.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getTheMeetingLevelDetails",e);
		}
		
		return levelDetails;
	}
	
	public List<CallStatusVO> getMeetingTypes(Long locationLevel){
		
		List<CallStatusVO> meetingTypes = new ArrayList<CallStatusVO>();
		try {
			LOG.info("Entered into getMeetingTypes");
			
			List<Object[]> meetingTypesList = partyMeetingTypeDAO.getMeetingTypesBasedOnLocationLevel(locationLevel);
			
			if(meetingTypesList!=null && meetingTypesList.size()>0){
				for (Object[] objects : meetingTypesList) {
					CallStatusVO vo = new CallStatusVO();
					
					vo.setId((Long)objects[0]);
					vo.setMeetingType(objects[1].toString());
					
					meetingTypes.add(vo);
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised in getMeetingTypes",e);
		}
		return meetingTypes;
	}
	
	public List<CallStatusVO> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateIds,List<Long> districtIds,List<Long> constituencyIds,List<Long> mandalTownDivisonIds,List<Long> villageWardIds,String startDateString,String endDateString){
		List<CallStatusVO> allMeetings = new ArrayList<CallStatusVO>();
		try {
			LOG.info("Entered into getAllMeetings");
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate=sdf.parse(startDateString);
			Date endDate=sdf.parse(endDateString);
			
			List<Long> mandalList=new ArrayList<Long>();
			List<Long> townList=new ArrayList<Long>();
			List<Long> divisonList=new ArrayList<Long>();
			List<Long> villageList=new ArrayList<Long>();
			List<Long> wardList=new ArrayList<Long>();
			
			if(locationLevel==4l){
				for(int i=0;i<mandalTownDivisonIds.size();i++){
					String manTowDiv = mandalTownDivisonIds.get(i).toString();
					char temp = manTowDiv.charAt(0);
					locationLevel=Long.parseLong(temp+"");
					if(locationLevel==4l){
						mandalList.add(Long.parseLong(manTowDiv.substring(1)));
					}
					if(locationLevel==5l){
						townList.add(Long.parseLong(manTowDiv.substring(1)));
					}
					if(locationLevel==6l){
						divisonList.add(Long.parseLong(manTowDiv.substring(1)));
					}
					
				}
			}
			
			if(locationLevel==5l){
				for(int i=0;i<villageWardIds.size();i++){
					String vilwrdId = villageWardIds.get(i).toString();
					char temp = vilwrdId.charAt(0);
					locationLevel=Long.parseLong(temp+"");
					
					if(locationLevel==7l){
						villageList.add(Long.parseLong(vilwrdId.substring(1)));
					}
					if(locationLevel==8l){
						wardList.add(Long.parseLong(vilwrdId.substring(1)));
					}
				}
			}
			
			
			List<Object[]> meetings = partyMeetingDAO.getAllMeetings(meetingType,locationLevel,stateIds,districtIds,constituencyIds,mandalList,townList,divisonList,villageList,wardList,startDate,endDate,0l);
			
			List<Long> level1List = new ArrayList<Long>();
			List<Long> level2List = new ArrayList<Long>();
			List<Long> level3List = new ArrayList<Long>();
			List<Long> level4List = new ArrayList<Long>();
			List<Long> level5List = new ArrayList<Long>();
			List<Long> level6List = new ArrayList<Long>();
			List<Long> level7List = new ArrayList<Long>();
			List<Long> level8List = new ArrayList<Long>();
						
			Map<Long,String> level1Map = new HashMap<Long,String>();
			Map<Long,String> level2Map = new HashMap<Long,String>();
			Map<Long,String> level3Map = new HashMap<Long,String>();
			Map<Long,String> level4Map = new HashMap<Long,String>();
			Map<Long,String> level5Map = new HashMap<Long,String>();
			Map<Long,String> level6Map = new HashMap<Long,String>();
			Map<Long,String> level7Map = new HashMap<Long,String>();
			Map<Long,String> level8Map = new HashMap<Long,String>();
						
			if(meetings!=null && meetings.size()>0){
				for (Object[] objects : meetings) {
					if(objects[2]!=null && (Long)objects[2]==1l){
						level1List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==2l){
						level2List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==3l){
						level3List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==4l){
						level4List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==5l){
						level5List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==6l){
						level6List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==7l){
						level7List.add((Long)objects[4]);
					}
					else if(objects[2]!=null && (Long)objects[2]==8l){
						level8List.add((Long)objects[4]);
					}
				}
			}
			
			if(level1List!=null && level1List.size()>0){
				List<Object[]> stateDetails = stateDAO.getStatesForList(level1List);
				
				if(stateDetails!=null && stateDetails.size()>0){
					for (Object[] objects : stateDetails) {
						level1Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level2List!=null && level2List.size()>0){
				List<Object[]> distDetails = districtDAO.getDistrictDetailsByDistrictIds(level2List);
				
				if(distDetails!=null && distDetails.size()>0){
					for (Object[] objects : distDetails) {
						level2Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level3List!=null && level3List.size()>0){
				List<Object[]> constDetails = constituencyDAO.getConstituencyInfoByConstituencyIdList(level3List);
				
				if(constDetails!=null && constDetails.size()>0){
					for (Object[] objects : constDetails) {
						level3Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level4List!=null && level4List.size()>0){
				List<Object[]> mandalDetails = tehsilDAO.getTehsilNameByTehsilIdsList(level4List);
				
				if(mandalDetails!=null && mandalDetails.size()>0){
					for (Object[] objects : mandalDetails) {
						level4Map.put((Long)objects[0],objects[1].toString());
					}
				}
			}
			
			if(level5List!=null && level5List.size()>0){
				List<Object[]> townDetails = localElectionBodyDAO.findByLocalElecBodyIds(level5List);
				if(townDetails!=null && townDetails.size()>0){
					for (Object[] objects : townDetails) {
						level5Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level6List!=null && level6List.size()>0){
				List<Object[]> divisonDetails = wardDAO.getWardDetailsForList(level6List);
				if(divisonDetails!=null && divisonDetails.size()>0){
					for (Object[] objects : divisonDetails) {
						level6Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level7List!=null && level7List.size()>0){
				List<Object[]> villageDetails = panchayatDAO.getPanchayatIdsByMandalIdsList(level7List);
				if(villageDetails!=null && villageDetails.size()>0){
					for (Object[] objects : villageDetails) {
						level7Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			if(level8List!=null && level8List.size()>0){
				List<Object[]> wardDetails = wardDAO.getWardDetailsForList(level8List);
				if(wardDetails!=null && wardDetails.size()>0){
					for (Object[] objects : wardDetails) {
						level8Map.put((Long)objects[0], objects[1].toString());
					}
				}
			}
			
			List<Long> partyMeetingIdsList = new ArrayList<Long>();
			if(meetings!=null && meetings.size()>0){
				for (Object[] objects : meetings) {
					partyMeetingIdsList.add((Long)objects[9]);
				}
			}
			
			List<PartyMeetingSummaryVO> momAndAtrRsltLst = new ArrayList<PartyMeetingSummaryVO>();
			if(partyMeetingIdsList!=null && partyMeetingIdsList.size()>0){
				momAndAtrRsltLst = partyMeetingService.getAtrAndMOMOfMeetings(partyMeetingIdsList);
			}
			
			
			//meetingtypeId,meetingtype,meetinglevelid,level,locationvalue,startime,endtime,meetinfaddressId,meetingName,partymeetingid
			if(meetings!=null && meetings.size()>0){
				for (Object[] objects : meetings) {
					CallStatusVO vo = new CallStatusVO();
					
					vo.setMeetingTypeId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setMeetingType(objects[1]!=null?objects[1].toString():"");
					
					if(objects[2]!=null && (Long)objects[2]==1l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level1Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==2l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level2Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==3l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level3Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==4l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level4Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==5l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level5Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==6l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level6Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==7l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level7Map.get((Long)objects[4]));
					}else if(objects[2]!=null && (Long)objects[2]==8l){
						vo.setLocationId((Long)objects[4]);
						vo.setLocation(level8Map.get((Long)objects[4]));
					}
					
					vo.setLocationLevelId(locationLevel);
					vo.setStartTime(objects[5].toString());
					vo.setEndTime(objects[6].toString());
					vo.setMeetingName(objects[8].toString());
					vo.setPartyMeetingId((Long)objects[9]);
					allMeetings.add(vo);
					
				}
			}
			
			if(momAndAtrRsltLst!=null && momAndAtrRsltLst.size()>0 && allMeetings!=null && allMeetings.size()>0){
				for (CallStatusVO callStatusVO : allMeetings) {
					PartyMeetingSummaryVO vo = getMatchedMeeting(callStatusVO.getPartyMeetingId(),momAndAtrRsltLst);
					callStatusVO.setDocTxtInfo(vo);
				}
				
			}
			
			//appending const num to const (based on constID)
			if(allMeetings!=null && allMeetings.size()>0 && locationLevel==3l){
				List<Long> constIds = new ArrayList<Long>();
				for (CallStatusVO callStatusVO : allMeetings) {
					constIds.add(callStatusVO.getLocationId());
				}
				
				Map<Long,Long> temp = new HashMap<Long, Long>();
				if(constIds!=null && constIds.size()>0){
					List<Object[]> constNums = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(constIds);

					if(constNums!=null && constNums.size()>0){
						for (Object[] objects2 : constNums) {
							temp.put((Long)objects2[0],(Long)objects2[1]);
						}
						
						for (CallStatusVO callStatusVO : allMeetings) {
							callStatusVO.setConstLocationNum(temp.get(callStatusVO.getLocationId()));
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getAllMeetings",e);
		}
		return allMeetings;
	}
	
	public PartyMeetingSummaryVO getMatchedMeeting(Long meetingId,List<PartyMeetingSummaryVO> meetings){
		if(meetingId !=null && meetings!=null && meetings.size()>0){
			for(PartyMeetingSummaryVO temp:meetings){
				if(temp.getMeetingId().equals(meetingId)){
					return temp;
				}
			}
		}
		return null;
	}
	
	public CallBackCountVO getCallBackDayWiseDetails(Long campCallerId)
	{
		CallBackCountVO callBackCountVO = new CallBackCountVO();
		try{
			List<Long> scheduleInviteeStatusIdsList = new ArrayList<Long>(0);
			scheduleInviteeStatusIdsList.add(6l);
			scheduleInviteeStatusIdsList.add(7l);
			DateUtilService dateUtilService = new DateUtilService();
			Date toDayDate = dateUtilService.getCurrentDateAndTime();
			
			List<Long> batchStatusList = new ArrayList<Long>();
			batchStatusList.add(1l);
			batchStatusList.add(2l);
			
			List<Object[]> schduleConfirmList = trainingCampScheduleInviteeCallerDAO.getSchduleBatchConfirmationCallBackDetails(campCallerId, 1l, null, scheduleInviteeStatusIdsList,batchStatusList);
			if(schduleConfirmList != null && schduleConfirmList.size() > 0)
			{
				for(Object[] params:schduleConfirmList)
				{
					if(params[1] != null && params[1].toString().equalsIgnoreCase("Call Back - Confirm Later"))
						callBackCountVO.setScheduleConfirmLaterCount((Long)params[0]);
					else
						callBackCountVO.setScheduleConfirmationCount((Long)params[0]);
				}
			}
			
			List<Object[]> toDaySchduleConfirmList = trainingCampScheduleInviteeCallerDAO.getSchduleBatchConfirmationCallBackDetails(campCallerId, 1l, toDayDate, scheduleInviteeStatusIdsList,batchStatusList);
			if(toDaySchduleConfirmList != null && toDaySchduleConfirmList.size() > 0)
			{
				for(Object[] params:toDaySchduleConfirmList)
				{
					if(params[1] != null && params[1].toString().equalsIgnoreCase("Call Back - Confirm Later"))
						callBackCountVO.setTodayScheduleConfirmLaterCount((Long)params[0]);
					else
						callBackCountVO.setTodayScheduleConCount((Long)params[0]);
				}
			}
			
			
			List<Object[]> batchConfirmList = trainingCampScheduleInviteeCallerDAO.getSchduleBatchConfirmationCallBackDetails(campCallerId, 2l, null, scheduleInviteeStatusIdsList,batchStatusList);
			if(batchConfirmList != null && batchConfirmList.size() > 0)
			{
				for(Object[] params:batchConfirmList)
				{
					if(params[1] != null && params[1].toString().equalsIgnoreCase("Call Back - Confirm Later"))
						callBackCountVO.setBatchConfirmLaterCount((Long)params[0]);
					else
						callBackCountVO.setBatchConfirmationCount((Long)params[0]);
				}
			}
			
			List<Object[]> toDaybatchConfirmList = trainingCampScheduleInviteeCallerDAO.getSchduleBatchConfirmationCallBackDetails(campCallerId, 2l, toDayDate, scheduleInviteeStatusIdsList,batchStatusList);
			if(toDaybatchConfirmList != null && toDaybatchConfirmList.size() > 0)
			{
				for(Object[] params:toDaybatchConfirmList)
				{
					if(params[1] != null && params[1].toString().equalsIgnoreCase("Call Back - Confirm Later"))
						callBackCountVO.setTodaybatchConfirmLaterCount((Long)params[0]);
					else
						callBackCountVO.setTodaybatchConCount((Long)params[0]);
				}
			}
			
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCallBackDayWiseDetails() method, Exception - ",e);
		}
		return callBackCountVO;
		
	}
	
	public List<IdNameVO> getCallerAgentDistricts(Long userId)
	{
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallerDistricts(userId);
			return getSelectOptions(list);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<IdNameVO> getCallerAgentConstituencies(Long userId,Long districtId)
	{
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallerConstituenciesByDistrict(userId,districtId);
			return getSelectOptions(list);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<IdNameVO> getCallerAgentMandals(Long userId,Long constituencyId)
	{
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallerAgentMandalsByConstituency(userId,constituencyId);
			return getSelectOptions(list);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<IdNameVO> getCallerAgentVillages(Long userId,Long mandalId)
	{
		try{
			List<Object[]> list = trainingCampScheduleInviteeCallerDAO.getCallerAgentVillagesByMandal(userId,mandalId);
			return getSelectOptions(list);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public List<IdNameVO> getSelectOptions(List<Object[]> list)
	{
		List<IdNameVO>  returnList = new ArrayList<IdNameVO>();
		try{
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(params[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]));
					returnList.add(vo);
					
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return returnList;
	}
	
	public List<BasicVO> getAgentsByCampCallerAdminId(Long campCallerAdminId,boolean isAdmin)
	{
		List<BasicVO> basicVOList = null;
		try{
			List<Object[]> list = trainingCampUserRelationDAO.getAgentsByCampCallerAdminId(campCallerAdminId,isAdmin);
			if(list != null && list.size() > 0)
			{
				basicVOList = new ArrayList<BasicVO>(0);
				for(Object[] params : list)
				{
					BasicVO basicVO = new BasicVO();
					String name = "";
					 name += params[1] != null?params[1].toString():"";
					 name += params[2]!= null?(" "+params[2].toString()):"";
					 basicVO.setId((Long)params[0]);
					 basicVO.setName(name);
					 basicVOList.add(basicVO);
				}
			}
				
		}catch (Exception e) {
			LOG.error("Exception Occured in getAgents() method, Exception - ",e); 
		}
		
		return basicVOList;
	}
	
	public List<TrainingCampScheduleVO>  getStausList(List<Object[]>  allStatus){
		
		List<TrainingCampScheduleVO> statusList=new ArrayList<TrainingCampScheduleVO>();
		 for (Object[] stuts : allStatus) {
			  TrainingCampScheduleVO vo =new TrainingCampScheduleVO();
			  vo.setStatusId((Long)stuts[0]);
			  vo.setStatus(stuts[1].toString());
			  vo.setCount(0l);
			  statusList.add(vo);
		  }
		return statusList;
	}
	public List<TrainingCampScheduleVO> getCallsDetailsOfCallCenterAdmin(List<Long> userIds,String startDateString,String endDateString){
		
		
		List<TrainingCampScheduleVO> finalList = null ;
		try{
			
			Date startDate =null;
			Date endDate =null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			if(!startDateString.isEmpty())
				startDate= sdf.parse(startDateString);
			if(!endDateString.isEmpty())
			 endDate= sdf.parse(endDateString);
    		
    		//1)getting All status and set to list.//0.statusId,status
			 List<Object[]>  allStatus=scheduleInviteeStatusDAO.getAllStatusList();
    		
    		//FINALmAP
    		Map<String,TrainingCampScheduleVO> finalMap = new LinkedHashMap<String, TrainingCampScheduleVO>();
    		TrainingCampScheduleVO assignedVO=new TrainingCampScheduleVO();
    		assignedVO.setTrainingCampVOList(getStausList(allStatus));
    		assignedVO.setAssignedCallsCount(0l);
    		assignedVO.setDialedCallsCount(0l);
    		assignedVO.setPendingCallsCount(0l);
    		
    		TrainingCampScheduleVO scheduledVO=new TrainingCampScheduleVO();
    		scheduledVO.setTrainingCampVOList(getStausList(allStatus));
    		scheduledVO.setAssignedCallsCount(0l);
    		scheduledVO.setDialedCallsCount(0l);
    		scheduledVO.setPendingCallsCount(0l);
    		
    		TrainingCampScheduleVO confirmedVO=new TrainingCampScheduleVO();
    		confirmedVO.setTrainingCampVOList(getStausList(allStatus));
    		confirmedVO.setAssignedCallsCount(0l);
    		confirmedVO.setDialedCallsCount(0l);
    		confirmedVO.setPendingCallsCount(0l);
    		
    		finalMap.put("Assigned",assignedVO);
    		finalMap.put("Scheduled",scheduledVO);
    		finalMap.put("Confirmed",confirmedVO);
    		
			 
    		
    		 /*Map<String,TrainingCampScheduleVO> invitationMap = new HashMap<String, TrainingCampScheduleVO>();  
    		 TrainingCampScheduleVO assignedMainVo = new TrainingCampScheduleVO();
    		 List<TrainingCampScheduleVO> voList = new ArrayList<TrainingCampScheduleVO>();*/
    		
    		//Assigned
    		  Long asgAgent = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, null,null);
			  Long dialedAsgAgent = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, "dialedCalls",null);
			  Long undialedAsgAgent = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds,startDate,endDate,"notDialed",null); 
			  
			  //0.statusId,1.status,2.count
			  List<Object[]> statusAsigned = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCallerByStatus(userIds,startDate,endDate,null);
			  
			  if(statusAsigned !=null && statusAsigned.size()>0){
				
				  for (Object[] status : statusAsigned) {
					  
					  TrainingCampScheduleVO assignedAgentvo= finalMap.get("Assigned");
					  if(status[0] !=null){
						  Long statusId=(Long)status[0];
						  for(TrainingCampScheduleVO statusVO :assignedAgentvo.getTrainingCampVOList()){
							  if(statusVO.getStatusId().longValue()==statusId.longValue()){
								  statusVO.setCount(status[2] !=null ? (Long)status[2] : 0l);
							  }
						  }
					  }  
						 
					}
			  }
			TrainingCampScheduleVO assigvo= finalMap.get("Assigned");
			assigvo.setAssignedCallsCount(asgAgent !=null ? asgAgent.longValue() : 0l);
			assigvo.setDialedCallsCount(dialedAsgAgent !=null ? dialedAsgAgent.longValue() :0l);
			assigvo.setPendingCallsCount(undialedAsgAgent !=null ? undialedAsgAgent.longValue() : 0l);//not dialedCallsCount
			assigvo.setName("Assigned");
    		
    		 
			  //Scheduled
			  Long asgInvit = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, null,IConstants.INVITATION);
			  Long dialedInvit = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, "dialedCalls",IConstants.INVITATION);
			  Long undialedInvit = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds,startDate,endDate,"notDialed",IConstants.INVITATION); 
			  
			  //0.statusId,1.status,2.count
			  List<Object[]> statusInvit = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCallerByStatus(userIds,startDate,endDate,IConstants.INVITATION);
			  
			  if(statusInvit !=null && statusInvit.size()>0){
				
				  for (Object[] status : statusInvit) {
					  
					  TrainingCampScheduleVO assignedvo= finalMap.get("Scheduled");
					  if(status[0] !=null){
						  Long statusId=(Long)status[0];
						  for(TrainingCampScheduleVO statusVO :assignedvo.getTrainingCampVOList()){
							  if(statusVO.getStatusId().longValue()==statusId.longValue()){
								  statusVO.setCount(status[2] !=null ? (Long)status[2] : 0l);
							  }
						  }
					  }  
						 
					}
			  }
			TrainingCampScheduleVO assignedvo= finalMap.get("Scheduled");
			assignedvo.setAssignedCallsCount(asgInvit !=null ? asgInvit.longValue() : 0l);
			assignedvo.setDialedCallsCount(dialedInvit !=null ? dialedInvit.longValue() :0l);
			assignedvo.setPendingCallsCount(undialedInvit !=null ? undialedInvit.longValue() : 0l);//not dialedCallsCount
			assignedvo.setName("Scheduled");
			
			
		  //Confirmation.
			  
			  Long asgCon = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, null,IConstants.CONFIRMATION);
			  Long dialedCon = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds, startDate, endDate, "dialedCalls",IConstants.CONFIRMATION);
			  Long undialedCon = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCaller(userIds,startDate,endDate,"notDialed",IConstants.CONFIRMATION); 
			  List<Object[]> statusCon = trainingCampScheduleInviteeCallerDAO.getCallDetailsOfCallerByStatus(userIds,startDate,endDate,IConstants.CONFIRMATION);
			 
			  
			  if(statusCon !=null && statusCon.size()>0){
				
				  for (Object[] status : statusCon) {
					  
					  TrainingCampScheduleVO confvo= finalMap.get("Confirmed");
					  if(status[0] !=null){
						  Long statusId=(Long)status[0];
						  for(TrainingCampScheduleVO statusVO :confvo.getTrainingCampVOList()){
							  if(statusVO.getStatusId().longValue()==statusId.longValue()){
								  statusVO.setCount(status[2] !=null ? (Long)status[2] : 0l);
							  }
						  }
					  }  
						 
					}
			  }
			TrainingCampScheduleVO confvo= finalMap.get("Confirmed");
			confvo.setAssignedCallsCount(asgCon !=null ? asgCon.longValue() : 0l);
			confvo.setDialedCallsCount(dialedCon !=null ? dialedCon.longValue() :0l);
			confvo.setPendingCallsCount(undialedCon !=null ? undialedCon.longValue() : 0l);//not dialedCallsCount
			confvo.setName("Confirmed");
			
			if(finalMap !=null && finalMap.size()>0){
				finalList =new ArrayList<TrainingCampScheduleVO>(finalMap.values());
			}
			
			return finalList;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCallsDetailsOfCallCenterAdmin() method, Exception - ",e);
		}
		
		return finalList;
	}
	
	public TrainingCampScheduleVO getUpComingBatchDetails(String startDatestr,String endDateStr){
		
		TrainingCampScheduleVO finalVo = new TrainingCampScheduleVO();
		
		try{
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate= null;
			Date endDate= null;
			
			if(!startDatestr.isEmpty())
				startDate= format.parse(startDatestr);
			if(!endDateStr.isEmpty())
			 endDate= format.parse(endDateStr);
			
			Date today = new DateUtilService().getCurrentDateAndTime();
			today = format.parse(new SimpleDateFormat("MM/dd/yyyy").format(today));
			
			//get All Upcoming Schedules 
			List<Long>  scheduleIds=trainingCampScheduleDAO.getAllUpcomingTrainingCampSchedules(startDate,endDate,"Not Started",today);
			
			Long upcomingScheduleCount=0l;
			if(scheduleIds !=null){
				upcomingScheduleCount=(long) scheduleIds.size();
			}
			List<Long> returnScheduleIds=  trainingCampScheduleInviteeCallerDAO.getAllUpcomingTrainingCampScheduleDetails(scheduleIds,startDate,endDate,IConstants.INVITATION,today);
			
			Long upAllocatedToagents=0l;
			Long upNotAllcoated =0l;
			if(returnScheduleIds !=null){
				upAllocatedToagents=(long) returnScheduleIds.size();
			}
			if(upcomingScheduleCount !=null && upcomingScheduleCount !=0l){
				upNotAllcoated=upcomingScheduleCount - upAllocatedToagents;
			}
			
			//batch Confirmation
			/*List<Long> batchesCount = trainingCampBatchDAO.getUpcomingBatchConfirmation(startDate,endDate,"Not Started");*/
			
			List<Long> batches = trainingCampScheduleInviteeCallerDAO.getUpcomingBatchConfirmation(startDate,endDate,IConstants.CONFIRMED,today);
			
			Long btchTotalCnt=0l;
			if(batches !=null && batches.size()>0){
				btchTotalCnt=(long) batches.size();
			}
			
			
			List<Long> allocatedConfirmed = trainingCampScheduleInviteeCallerDAO.getAllocatedCountForConfirmation(startDate,endDate,"Not Started",2l,today);
			
			Long allocatedconfirmedCnt=0l;
			Long btchNotAllocated =0l; 
			
			if(allocatedConfirmed !=null && allocatedConfirmed.size()>0){
				allocatedconfirmedCnt =(long) allocatedConfirmed.size();
			}
			
			if(btchTotalCnt !=null && btchTotalCnt !=0l){
				btchNotAllocated = btchTotalCnt - allocatedconfirmedCnt;
			}
			
			
			finalVo.setUpcomingscheduleCnt(upcomingScheduleCount);
			finalVo.setUpcomingAllocatedAgnt(upAllocatedToagents);
			finalVo.setUpNotAllocated(upNotAllcoated);
			finalVo.setBatchConfirmCnt(btchTotalCnt);
			finalVo.setBtchAllocatedCnt(allocatedconfirmedCnt);
			finalVo.setBtchNotAllocated(btchNotAllocated);
			
			return finalVo;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalVo;
	}
	
	
	/*
	 *	@Since 15thAug 2015
	 *	@Author	Sasi 
	 * 	Method is to Give User Access Levels & Values in Meetings Module
	 * */
	public MeetingVO getUserAccessLevelAndLocations(Long userId){
		LOG.debug("Entered Into getUserAccessLevelAndLocations");
		MeetingVO finalVO = new MeetingVO();
		try{
			List<MeetingVO> meetingLevels = new ArrayList<MeetingVO>();
			List<MeetingVO> meetingAccessValues = new ArrayList<MeetingVO>();
			Map<Long,List<Long>> userLevelValuesMap = new HashMap<Long, List<Long>>();
			
			//GETTING USER ACCESS LEVELS
			List<Object[]> allLevels = partyMeetingUserAccessLevelDAO.getrAccessLevelsOfUserId(userId);
			if(allLevels!=null && allLevels.size()>0){
				for(Object[] obj:allLevels){
					MeetingVO mv = new MeetingVO();
					mv.setLevelId(Long.valueOf(obj[0].toString()));
					mv.setName(obj[1].toString());
					meetingLevels.add(mv);
				}
			}
			
			//GETTING USER ACCESS LOCATIONS
			List<Object[]> allLevelValues = userAccessLevelValueDAO.getAccessValuesOfUserId(userId);
			if(allLevelValues!=null && allLevelValues.size()>0){
				
				for(Object[] obj:allLevelValues){
					List<Long> locationIds = userLevelValuesMap.get(Long.valueOf(obj[0].toString()));
					if(locationIds==null){
						locationIds = new ArrayList<Long>();
					}
					locationIds.add(Long.valueOf(obj[1].toString()));
					userLevelValuesMap.put(Long.valueOf(obj[0].toString()), locationIds);
				}
			}
			
			MeetingVO mv = new MeetingVO();
			mv.setLevelId(1l);
			mv.setLevelValues(new ArrayList<Long>());
			meetingAccessValues.add(mv);
			
			mv = new MeetingVO();
			mv.setLevelId(2l);
			mv.setLevelValues(new ArrayList<Long>());
			meetingAccessValues.add(mv);
			
			mv = new MeetingVO();
			mv.setLevelId(3l);
			mv.setLevelValues(new ArrayList<Long>());
			meetingAccessValues.add(mv);
			
			mv = new MeetingVO();
			mv.setLevelId(4l);
			mv.setLevelValues(new ArrayList<Long>());
			meetingAccessValues.add(mv);
			
			
			if(userLevelValuesMap!=null && userLevelValuesMap.size()>0){
				for (Entry<Long, List<Long>> entry : userLevelValuesMap.entrySet()){
					MeetingVO temp = getMatchedLocation(entry.getKey(), meetingAccessValues);
					if(temp!=null){
						temp.setLevelValues(entry.getValue());
					}
					
				}
			}
			
			if(meetingAccessValues!=null && meetingAccessValues.size()>0){
				for(MeetingVO temp:meetingAccessValues){
					if(temp.getLevelId().equals(2l)){
						MeetingVO stateVO = getMatchedLocation(1l, meetingAccessValues);
						List<Long> states = new ArrayList<Long>();
						if(stateVO!=null){
							states = stateVO.getLevelValues();
							List<Long> stateIds = getStatesOfDistrict(temp.getLevelValues());
							if(stateIds!=null && stateIds.size()>0){
									Set<Long> statesSet = new HashSet<Long>(states);
									statesSet.addAll(stateIds);
									stateVO.setLevelValues(new ArrayList<Long>(statesSet));
							}
							
						}
					}else if(temp.getLevelId().equals(4l)){
						getDistsOfConstituencies(temp.getLevelValues(), meetingAccessValues);
					}else if(temp.getLevelId().equals(3l)){
						Set<Long> constiIds = new HashSet<Long>();
						List<Object[]> constiRslt = new ArrayList<Object[]>();
						if(temp.getLevelValues()!=null && temp.getLevelValues().size()>0){
						constiRslt = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesDetailsByParliamentList(temp.getLevelValues());
						}
						if(constiRslt!=null && constiRslt.size()>0){
							for(Object[] obj:constiRslt){
								constiIds.add(Long.valueOf(obj[0].toString()));
							}
						}
						MeetingVO constiVO = getMatchedLocation(4l, meetingAccessValues);
						constiIds.addAll(constiVO.getLevelValues());
						constiVO.setLevelValues(new ArrayList<Long>(constiIds));
						
						getDistsOfConstituencies(new ArrayList<Long>(constiIds), meetingAccessValues);
					}
				}
			}
			
		finalVO.setUserAccessLevelList(meetingLevels);
		finalVO.setUserAccessLevelValuesList(meetingAccessValues);

			
		}catch (Exception e) {
			LOG.error("Exception Raised In getUserAccessLevelAndLocations",e);
		}
		
		return finalVO;
	}
	
	public List<Long> getStatesOfDistrict(List<Long> dists){
		Set<Long> states = new HashSet<Long>();
		if(dists.size()>0){
			List<Object[]> statesRslt = districtDAO.getStatesForDistricts(dists);
			if(statesRslt!=null && statesRslt.size()>0){
				for(Object[] obj:statesRslt){
					Long stateId = Long.valueOf(obj[0].toString());
					if(Long.valueOf(obj[2].toString())<11 || Long.valueOf(obj[2].toString()).equals(518l)){
						stateId = 36l;
					}
					states.add(stateId);
				}
			}
		}
		return new ArrayList<Long>(states);
	}
	
	public void getDistsOfConstituencies(List<Long> constis, List<MeetingVO> meetingLevels){
		
		List<Object[]> rsltNew = districtConstituenciesDAO.getConstituenciesOfDistrict();
		Map<Long,List<Long>> distMap = new HashMap<Long, List<Long>>();
		Map<Long, Long> constisSplttdMap = new HashMap<Long, Long>();
		
		if(rsltNew!=null && rsltNew.size()>0){
			for(Object[] obj:rsltNew){
				List<Long> consties = distMap.get(Long.valueOf(obj[0].toString()));
				if(consties==null){
					consties = new ArrayList<Long>();
				}
				consties.add(Long.valueOf(obj[2].toString()));
				constisSplttdMap.put(Long.valueOf(obj[2].toString()),Long.valueOf(obj[0].toString()));
				distMap.put(Long.valueOf(obj[0].toString()), consties);
				
			}
		}
		
		List<Object[]> rslt = new ArrayList<Object[]>();
		if(constis!=null && constis.size()>0){
			rslt = constituencyDAO.getStateAndDistricsOfConstituency(constis);
		}
		
		Set<Long> districtSet = new HashSet<Long>();
		Set<Long> statesSet = new HashSet<Long>();
		
		if(rslt!=null && rslt.size()>0){
			for(Object[] obj:rslt){
				Long dist = constisSplttdMap.get(Long.valueOf(obj[0].toString()));
				if(dist!=null){
					districtSet.add(dist);
					statesSet.add(Long.valueOf(obj[2].toString()));
				}else{
					districtSet.add(Long.valueOf(obj[1].toString()));
					statesSet.add(Long.valueOf(obj[2].toString()));
				}
			}
		}
		
		MeetingVO stateVO = getMatchedLocation(1l, meetingLevels);
		if(stateVO==null){stateVO = new MeetingVO();stateVO.setLevelId(1l);}
		statesSet.addAll(stateVO.getLevelValues());
		stateVO.setLevelValues(new ArrayList<Long>(statesSet));
		
		MeetingVO distVO = getMatchedLocation(2l, meetingLevels);
		if(distVO==null){distVO = new MeetingVO();distVO.setLevelId(2l);}
		districtSet.addAll(distVO.getLevelValues());
		distVO.setLevelValues(new ArrayList<Long>(districtSet));
		 
	}
	
	public MeetingVO getMatchedLocation(Long id, List<MeetingVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(MeetingVO mv:list){
				if(mv.getLevelId().equals(id)){
					return mv;
				}
			}
		}
		return null;
	}
	
	public List<TraingCampCallerVO> getScheduleAvailableCallsCountLocationWiseInfo(Long campId,Long programId,Long scheduleId,String type)
	{
		List<TraingCampCallerVO> returnList = new ArrayList<TraingCampCallerVO>();
		
		try{
			 List<Object[]> list = null;
		 List<Long> inviteeIdsList = null;//trainingCampScheduleInviteeCallerDAO.getAssignedInviteesIdsList();
		 if(type.equalsIgnoreCase("District"))
		 list = trainingCampScheduleInviteeDAO.getScheduleAvailableCallsCountLocationWiseInfo(campId,programId,scheduleId,1l,inviteeIdsList);
		 else
			 list = trainingCampScheduleInviteeDAO.getScheduleAvailableCallsCountParliamentWiseInfo(campId,programId,scheduleId,1l,inviteeIdsList);	 
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				if(params[1] != null)
				{
					 TraingCampCallerVO districtVo = getMatchedVo(returnList, commonMethodsUtilService.getLongValueForObject(params[1])); // District
					 if(districtVo == null)
					 {
						 districtVo = new TraingCampCallerVO();
						 districtVo.setName(params[2].toString());
						 districtVo.setId((Long)params[1]);
						 returnList.add(districtVo);
					 }
					 districtVo.setCount(districtVo.getCount() + (Long)params[0]);
					 if(params[3] != null)
						{
						 TraingCampCallerVO constituencyVo = getMatchedVo(districtVo.getSubList(), commonMethodsUtilService.getLongValueForObject(params[3])); // Constituency
						 if(constituencyVo == null)
						 {
							 constituencyVo = new TraingCampCallerVO();
							 constituencyVo.setName(params[4].toString());
							 constituencyVo.setId((Long)params[3]);
							 districtVo.getSubList().add(constituencyVo);
						 }
						 constituencyVo.setCount(constituencyVo.getCount() + (Long)params[0]);
						 
						 if(params[7] !=null){
							 
							 List<String> namesList=localElectionBodyDAO.getLocalElectionBodyNameById((Long)params[7]);
							 String name="";
							 if(namesList !=null && namesList.size()>0){
								 name = namesList.get(0).concat(" Municipality");
							 }
							 //Municipality Allocation
							 TraingCampCallerVO municipalityVo = getMatchedVo(constituencyVo.getScheduleStatusList(), commonMethodsUtilService.getLongValueForObject(params[7])); // Municipality
							 
							 if(municipalityVo ==null){
								 municipalityVo = new TraingCampCallerVO();
								 municipalityVo.setName(name);
								 municipalityVo.setId((Long)params[7]);
								 constituencyVo.getScheduleStatusList().add(municipalityVo); 
							 }
							 municipalityVo.setCount(municipalityVo.getCount() + (Long)params[0]);
							 
							// districtVo.setCount(districtVo.getCount() + municipalityVo.getCount());
							// constituencyVo.setCount(constituencyVo.getCount() + municipalityVo.getCount());
						 }
						 else if(params[5] != null)
							{
							 TraingCampCallerVO mandalVo = getMatchedVo(constituencyVo.getSubList(), commonMethodsUtilService.getLongValueForObject(params[5])); // Mandal
							 if(mandalVo == null)
							 {
								 mandalVo = new TraingCampCallerVO();
								 mandalVo.setName(params[6].toString());
								 mandalVo.setId((Long)params[5]);
								 constituencyVo.getSubList().add(mandalVo);
							 }
							 mandalVo.setCount(mandalVo.getCount() + (Long)params[0]);
					    }
				   }
				}
			 }
		 }
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getScheduleAvailableCallsCountLocationWiseInfo() method", e);
		}
		return returnList;
	}
	
	public PartyMeetingVO getPartyMeetingMinutesAtrDetails(Long partyMeeingId){
		PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
		
		try{
			LOG.info("Entered into getPartyMeetingMinutesAtrDetails");
			
			PartyMeeting partyMeetingDetails = partyMeetingDAO.get(partyMeeingId);
			List<Object[]> minutesDetails = partyMeetingMinuteDAO.getMinuteDetailsForAMeeting(partyMeeingId);
			List<Object[]> atrDetails = partyMeetingAtrPointDAO.getAtrDetailsForAMeeting(partyMeeingId);
			List<Object[]> documentDetails = partyMeetingDocumentDAO.getDocumentDetailsForMinutesAtr(partyMeeingId);
			
			if(partyMeetingDetails != null){
				partyMeetingVO.setId(partyMeetingDetails.getPartyMeetingId()!=null?partyMeetingDetails.getPartyMeetingId():0l);
				partyMeetingVO.setName(partyMeetingDetails.getMeetingName()!=null?partyMeetingDetails.getMeetingName():"");
				partyMeetingVO.setPartyMeetingTypeId(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getPartyMeetingTypeId():0l);
				partyMeetingVO.setPartyMeetingType(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getType():"");
				partyMeetingVO.setMeetingLevelId(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getPartyMeetingLevelId():0l);
				partyMeetingVO.setMeetingLevel(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getLevel():"");
				partyMeetingVO.setLocationValue(partyMeetingDetails.getLocationValue()!=null?partyMeetingDetails.getLocationValue():0l);
				if(partyMeetingDetails.getStartDate()!=null && partyMeetingDetails.getEndDate()!=null){
					partyMeetingVO.setStartDate(partyMeetingDetails.getStartDate());
					partyMeetingVO.setEndDate(partyMeetingDetails.getEndDate());
				}
				
				if(partyMeetingVO.getMeetingLevelId()!=0 && partyMeetingVO.getLocationValue()!=0){
					List<Long> locationIds = new ArrayList<Long>();
					locationIds.add(partyMeetingVO.getLocationValue());
					List<IdNameVO> rslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, partyMeetingVO.getMeetingLevelId());
					if(rslt!=null && rslt.size()>0){
						partyMeetingVO.setLocationName(rslt.get(0).getName());
					}
				}
				
			}
			
			if(minutesDetails!=null && minutesDetails.size()>0){
				List<PartyMeetingVO> vo = new ArrayList<PartyMeetingVO>();
				for (Object[] objects : minutesDetails) {
					PartyMeetingVO subVO = new PartyMeetingVO();
					
					subVO.setPartyMeetingMinuteId(objects[0]!=null?(Long)objects[0]:0l);
					subVO.setId(objects[1]!=null?(Long)objects[1]:0l);
					subVO.setMinutePoint(objects[2]!=null?objects[2].toString():"");
					subVO.setInsertedById(objects[3]!=null?(Long)objects[3]:0l);
					subVO.setInsertedBy(objects[4]!=null?objects[4].toString():"");
					subVO.setUpdatedById(objects[5]!=null?(Long)objects[5]:0l);
					subVO.setUpdatedBy(objects[6]!=null?objects[6].toString():"");
					subVO.setInsertedTime(objects[7]!=null?objects[7].toString():"");
					subVO.setUpdatedTime(objects[8]!=null?objects[8].toString():"");
					
					vo.add(subVO);
					
				}
				partyMeetingVO.setMinutesDetails(vo);
			}
			
			if(atrDetails!=null && atrDetails.size()>0){
				List<PartyMeetingVO> vo = new ArrayList<PartyMeetingVO>();
				for (Object[] objects : atrDetails) {
					PartyMeetingVO subVO = new PartyMeetingVO();
					
					subVO.setPartyMeetingAtrPointId(objects[0]!=null?(Long)objects[0]:0l);
					subVO.setId(objects[1]!=null?(Long)objects[1]:0l);
					subVO.setRequest(objects[2]!=null?objects[2].toString():"");
					subVO.setActionTaken(objects[3]!=null?objects[3].toString():"");
					subVO.setRequestFrom(objects[4]!=null?objects[4].toString():"");
					subVO.setLocationScopeId(objects[5]!=null?(Long)objects[5]:0l);
					subVO.setLocationValue(objects[6]!=null?(Long)objects[6]:0l);
					subVO.setRaisedBy(objects[7]!=null?objects[7].toString():"");
					subVO.setInsertedById(objects[8]!=null?(Long)objects[8]:0l);
					subVO.setInsertedBy(objects[9]!=null?objects[9].toString():"");
					subVO.setUpdatedById(objects[10]!=null?(Long)objects[10]:0l);
					subVO.setUpdatedBy(objects[11]!=null?objects[11].toString():"");
					subVO.setInsertedTime(objects[12]!=null?objects[12].toString():"");
					subVO.setUpdatedTime(objects[13]!=null?objects[13].toString():"");
					
					if(subVO.getLocationValue()!=0 && subVO.getLocationScopeId()!=0){
						List<Long> locationIds = new ArrayList<Long>();
						if(subVO.getLocationScopeId().equals(3l)){
							locationIds.add(Long.parseLong((subVO.getLocationValue().toString()).substring(1)));
						}else{
							locationIds.add(subVO.getLocationValue());
						}
						List<IdNameVO> rslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, subVO.getLocationScopeId()+1);
						if(rslt!=null && rslt.size()>0){
							subVO.setLocationName(rslt.get(0).getName());
						}
					}
					
					vo.add(subVO);
				}
				partyMeetingVO.setAtrDetails(vo);
			}
			
			if(documentDetails!=null && documentDetails.size()>0){
				
				List<CallTrackingVO> atrDocs = new ArrayList<CallTrackingVO>();
				List<CallTrackingVO> minDocs = new ArrayList<CallTrackingVO>();
				List<CallTrackingVO> meetingDocs = new ArrayList<CallTrackingVO>();
				
				for (Object[] objects : documentDetails) {
			
					CallTrackingVO vo = new CallTrackingVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setUrl(objects[2]!=null?IConstants.LOCAL_FILES+"/"+objects[2].toString().trim():"");
					vo.setName(objects[10]!=null?objects[10].toString():"");
					
					if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("MINUTE")){
						minDocs.add(vo);
					}else if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("ATR")){
						atrDocs.add(vo);
					}else if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("MEETING")){
						meetingDocs.add(vo);
					}
				}
				
				partyMeetingVO.setMinutesDocuments(minDocs);
				partyMeetingVO.setAtrDocuments(atrDocs);
				partyMeetingVO.setMeetingDocs(meetingDocs);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception raised at getPartyMeetingMinutesAtrDetails",e);
		}
		
		return partyMeetingVO;
	}
	
	
	public TrainingCampVO getAdminCallersWiseOverView(Long userId,Long campId,Long programId,Long scheduleId,Long batchId,boolean isAdmin)
	{
		TrainingCampVO returnVo = new TrainingCampVO();
		try{
		
			List<Long> callerIdsList = new ArrayList<Long>();
			List<Object[]> list = trainingCampUserRelationDAO.getAgentsByCampCallerAdminId(userId,isAdmin);
			if(list != null && list.size() > 0)
				for(Object[] params : list)
				{
				if(!callerIdsList.contains((Long)params[0]))
						callerIdsList.add((Long)params[0]);	
				}
			
			if(callerIdsList != null && callerIdsList.size() > 0)
				returnVo =  getCallerWiseOverView(callerIdsList);
			if(returnVo.getTrainingCampVOList() != null && returnVo.getTrainingCampVOList().size() > 0)
			{
				List<Object[]> list1 = trainingCampScheduleInviteeDAO.getAvailableCallCountsForBatch(campId,programId,scheduleId,4l,batchId);
				if(list1 != null && list1.size() > 0)
				{
					for(Object[] params : list1)
					{
						TrainingCampVO vo = getMatchedCampVo(returnVo.getTrainingCampVOList(),commonMethodsUtilService.getLongValueForObject(params[1]));
						if(vo != null)
						{
							if(vo.getInterestedCount() == null )
								vo.setInterestedCount(0l);
							vo.setInterestedCount(vo.getInterestedCount() + (Long)params[0]);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnVo;
	}
	public TrainingCampVO getCallerWiseOverView(List<Long> callerIdsList)
	{
		TrainingCampVO returnVO = new TrainingCampVO();
		try {
			Map<Long,List<TrainingCampVO>> userWiseCallStatusMap = new LinkedHashMap<Long, List<TrainingCampVO>>(0);
			if(callerIdsList != null && callerIdsList.size()>0)
			{
				List<Object[]> assignedListscheduleList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCount("schedule", callerIdsList);
				List<Object[]> assignedListbatchList = trainingCampScheduleInviteeCallerDAO.getCallerWiseAssignedCount("batch", callerIdsList);
				
				if(assignedListscheduleList != null && assignedListscheduleList.size()>0)
				{
					for (Object[] caller : assignedListscheduleList) {
						Long callerId = commonMethodsUtilService.getLongValueForObject(caller[0]);
						Long count =  commonMethodsUtilService.getLongValueForObject(caller[2]);
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						if(userWiseCallStatusMap.get(callerId) != null)
						{
							trainingCampVOList = userWiseCallStatusMap.get(callerId);
						}
						else
						{
							TrainingCampVO scheduleVO = new TrainingCampVO();
							scheduleVO.setMemberStatus("schedule");
							trainingCampVOList.add(scheduleVO);
							
							TrainingCampVO batchVO = new TrainingCampVO();
							batchVO.setMemberStatus("batch");
							trainingCampVOList.add(batchVO);
						}
						TrainingCampVO scheduleVO = trainingCampVOList.get(0);
						scheduleVO.setId(callerId);
						scheduleVO.setName(commonMethodsUtilService.getStringValueForObject(caller[1]));
						scheduleVO.setAllocatedCalls(count);
						scheduleVO.setPendingCalls(count);
						userWiseCallStatusMap.put(callerId, trainingCampVOList);
					}
				}
				
				if(assignedListbatchList != null && assignedListbatchList.size()>0)
				{
					for (Object[] caller : assignedListbatchList) {
						Long callerId = commonMethodsUtilService.getLongValueForObject(caller[0]);
						Long count =  commonMethodsUtilService.getLongValueForObject(caller[2]);
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						if(userWiseCallStatusMap.get(callerId) != null)
						{
							trainingCampVOList = userWiseCallStatusMap.get(callerId);
						}
						else
						{
							TrainingCampVO scheduleVO = new TrainingCampVO();
							scheduleVO.setMemberStatus("schedule");
							trainingCampVOList.add(scheduleVO);
							
							TrainingCampVO batchVO = new TrainingCampVO();
							batchVO.setMemberStatus("batch");
							trainingCampVOList.add(batchVO);
						}
						TrainingCampVO scheduleVO = trainingCampVOList.get(1);
						scheduleVO.setName(commonMethodsUtilService.getStringValueForObject(caller[1]));
						scheduleVO.setAllocatedCalls(count);
						scheduleVO.setPendingCalls(count);
						userWiseCallStatusMap.put(callerId, trainingCampVOList);
					}
				}
				List<Object[]> scheduleList = trainingCampScheduleInviteeCallerDAO.getCallStatusWiseCountDetailsForCallers("schedule", callerIdsList);
				List<Object[]> batchList = trainingCampScheduleInviteeCallerDAO.getCallStatusWiseCountDetailsForCallers("batch", callerIdsList);
				
				if(scheduleList != null && scheduleList.size()>0)
				{
					for (Object[] caller : scheduleList) {
						Long callerId = commonMethodsUtilService.getLongValueForObject(caller[0]);
						//Long statusId = commonMethodsUtilService.getLongValueForObject(caller[1]);
						//String statusStr = commonMethodsUtilService.getStringValueForObject(caller[2]);
						Long count =  commonMethodsUtilService.getLongValueForObject(caller[1]);
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						
						if(userWiseCallStatusMap.get(callerId) != null)
						{
							trainingCampVOList = userWiseCallStatusMap.get(callerId);
						}
						if((trainingCampVOList != null && trainingCampVOList.size() > 0) && trainingCampVOList.get(0) != null)
						{
						TrainingCampVO trainingCampVO = trainingCampVOList.get(0);
						//trainingCampVO.setStatus(statusStr);
						trainingCampVO.setCompletedCalls(count);
						Long pendingCalls = 0L;
						if(trainingCampVO.getAllocatedCalls() == null)
							trainingCampVO.setAllocatedCalls(0L);
						if(trainingCampVO.getCompletedCalls() == null)
							trainingCampVO.setCompletedCalls(0L);
						
						pendingCalls = trainingCampVO.getAllocatedCalls() - trainingCampVO.getCompletedCalls();
						trainingCampVO.setPendingCalls(pendingCalls);
						
						userWiseCallStatusMap.put(callerId, trainingCampVOList);
						}
					}
				}
				
				if(batchList != null && batchList.size()>0)
				{
					for (Object[] caller : batchList) {
						Long callerId = commonMethodsUtilService.getLongValueForObject(caller[0]);
						//Long statusId = commonMethodsUtilService.getLongValueForObject(caller[1]);
						//String statusStr = commonMethodsUtilService.getStringValueForObject(caller[2]);
						Long count =  commonMethodsUtilService.getLongValueForObject(caller[1]);
						List<TrainingCampVO> trainingCampVOList = new ArrayList<TrainingCampVO>(0);
						
						if(userWiseCallStatusMap.get(callerId) != null)
						{
							trainingCampVOList = userWiseCallStatusMap.get(callerId);
						}
						if((trainingCampVOList != null && trainingCampVOList.size() > 0) && trainingCampVOList.get(1) != null)
								{
						TrainingCampVO trainingCampVO = trainingCampVOList.get(1);
						//trainingCampVO.setStatus(statusStr);
						trainingCampVO.setCompletedCalls(count);
						Long pendingCalls = 0L;
						if(trainingCampVO.getAllocatedCalls() == null)
							trainingCampVO.setAllocatedCalls(0L);
						if(trainingCampVO.getCompletedCalls() == null)
							trainingCampVO.setCompletedCalls(0L);
						
						pendingCalls = trainingCampVO.getAllocatedCalls() - trainingCampVO.getCompletedCalls();
						trainingCampVO.setPendingCalls(pendingCalls);
						
						userWiseCallStatusMap.put(callerId, trainingCampVOList);
								}
					}
				}
				
				List<TrainingCampVO> trainingCampvoList = new LinkedList<TrainingCampVO>();
				if(userWiseCallStatusMap != null && userWiseCallStatusMap.size()>0)
				{
					for (Long callerId : userWiseCallStatusMap.keySet()) {
						List<TrainingCampVO> list = userWiseCallStatusMap.get(callerId);
						if(list != null)
						{
							TrainingCampVO userVO = new TrainingCampVO();
							userVO.setName(userDAO.get(callerId).getFirstName());
							userVO.setTrainingCampVOList(list);
							userVO.setId(callerId);
							trainingCampvoList.add(userVO);
						}
					}
					returnVO.setTrainingCampVOList(trainingCampvoList);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCallerWiseOverView",e);
		}
		
		return returnVO;
	}
	
	public List<CadreDetailsVO> getSchedulesListByProgramAndCenter(Long programId, Long centerId)
	{
		List<CadreDetailsVO> finalList=null;
		try{
			List<Long> schedulesList = new ArrayList<Long>(0);
			
			schedulesList = trainingCampScheduleDAO.getSchedulesByProgramAndCenter(programId, centerId);
			
			if(schedulesList != null && schedulesList.size() > 0){
				finalList = getTdpCadreDetailsforASchedule(schedulesList);
			}
			
		}catch(Exception e){
			LOG.error(" Exception occured in getSchedulesListByProgramAndCenter method in TrainingCampService class.",e);
		}
		return finalList;
	}
	
	public List<CadreDetailsVO> getTdpCadreDetailsforASchedule(List<Long> schedulesList){
		 
		List<CadreDetailsVO> finalList=null;
		try{
			Map<Long,CadreDetailsVO> batchMap=new LinkedHashMap<Long,CadreDetailsVO>();
			
			List<Object[]> cadreDetails= trainingCampBatchAttendeeDAO.getTdpCadreDetailsforASchedule(schedulesList);
			
			if(cadreDetails!=null && cadreDetails.size()>0){
				
				 for(Object[] obj :cadreDetails){//scid,bid,bcode,,cadreid,firstname,mobileno,image,cid,cname,llid,csid,lsid,hid,isUpdatable
					 
					 Long batchId=Long.valueOf(((Integer)obj[1]).longValue());
					 CadreDetailsVO batchVO=batchMap.get(batchId);
					 boolean batchExist=true;
					 if(batchVO==null){
						 batchExist=false;
						 batchVO=new CadreDetailsVO();
						 batchVO.setId(batchId);
						 batchVO.setName(obj[2]!=null?obj[2].toString():"");
						 batchVO.setIsFeedbackUpdatable(obj[13].toString());
					 }
					
					 if(batchVO.getSubMap()==null){
						 batchVO.setSubMap(new LinkedHashMap<Long, CadreDetailsVO>());
					 }
					 Long cadreId=Long.valueOf(((BigInteger)obj[3]).longValue());
					 CadreDetailsVO cadreVO=new CadreDetailsVO();
					 cadreVO.setId(cadreId);
					 cadreVO.setName(obj[4]!=null?obj[4].toString():"");
					 cadreVO.setMobileno(obj[5]!=null?obj[5].toString():"");	 
					 cadreVO.setImage(obj[6]!=null?obj[6].toString():"");
					 cadreVO.setConstituencyId(obj[7]!=null?Long.valueOf(((BigInteger)obj[7]).longValue()):0l);
					 cadreVO.setConstituency(obj[8]!=null?obj[8].toString():"");
					 cadreVO.setLeaderShipLevels(obj[9]!=null?true:false);
					 cadreVO.setCommunicationSkills(obj[10]!=null?true:false);
					 cadreVO.setLeaderShipSkills(obj[11]!=null?true:false);
					 cadreVO.setHealth(obj[12]!=null?true:false);
					 cadreVO.setAchievements(false);
					 cadreVO.setGoals(false);
					 batchVO.getSubMap().put(cadreId, cadreVO);
					 if(!batchExist){
						 batchMap.put(batchId, batchVO);
					 }
				 }
			 }
			List<Object[]> achievements=trainingCampBatchAttendeeDAO.getAchievementsForCadreBySchedule(schedulesList);
			if(achievements!=null && achievements.size()>0){
				for(Object[] param:achievements){//bid,bcode,cid,ach
					
					if(param[0]!=null){
						CadreDetailsVO batchVO=batchMap.get((Long)param[0]);
						if(param[2]!=null){
							batchVO.getSubMap().get((Long)param[2]).setAchievements(true);
						}
						
					}
				}
			}
			List<Object[]> goals=trainingCampBatchAttendeeDAO.getGoalsForCadreBySchedule(schedulesList);
			if(goals!=null && goals.size()>0){
				for(Object[] param:goals){//bid,bcode,cid,ach
					
					if(param[0]!=null){
						CadreDetailsVO batchVO=batchMap.get((Long)param[0]);
						if(param[2]!=null){
							batchVO.getSubMap().get((Long)param[2]).setGoals(true);
						}
						
					}
				}
			}
		
			if(batchMap!=null && batchMap.size()>0){
				
            for (Map.Entry<Long, CadreDetailsVO> entry : batchMap.entrySet())
            {
            	CadreDetailsVO batchVO= entry.getValue();
            	if(batchVO.getSubMap()!=null && batchVO.getSubMap().size()>0){
            		batchVO.setSubList(new ArrayList<CadreDetailsVO>(batchVO.getSubMap().values()));
            		batchVO.getSubMap().clear();
            	}
            }				
				finalList=new ArrayList<CadreDetailsVO>(batchMap.values());
				batchMap.clear();
			}
		}catch(Exception e){
			
			LOG.error(" Exception occured in getTdpCadreDetailsforASchedule method in TrainingCampService class.",e);
		} 
		return finalList;
	}
	public String saveFilePaths(Long partyMeetingId,String fileType, String documentType, String filePath, Long userId, String fileName){
		LOG.debug("Entered Into saveFilePaths");
		try{
			
			PartyMeetingDocument partyMeetingDocument = new PartyMeetingDocument();
			partyMeetingDocument.setPartyMeetingId(partyMeetingId);
			partyMeetingDocument.setUploadedById(userId);
			partyMeetingDocument.setUpdatedById(userId);
			partyMeetingDocument.setPath(filePath);
			partyMeetingDocument.setUploadedTime(new DateUtilService().getCurrentDateAndTime());
			partyMeetingDocument.setDocumentFormat(getStandardFormatOfFile(fileType));
			partyMeetingDocument.setDocumentType(documentType);
			partyMeetingDocument.setDocumentName(fileName);
			partyMeetingDocument.setIsDeleted("N");
			
			partyMeetingDocumentDAO.save(partyMeetingDocument);
			return "success";
		}catch (Exception e) {
			LOG.error(" Error in saveFilePaths",e);
			return "failed";
		}
		
	}
	
	public String getStandardFormatOfFile(String type){
		String format = "OTHERS";
		if(type.equalsIgnoreCase("jpeg") || type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("png") || type.equalsIgnoreCase("gif")){
			format = "IMAGE";
		}else if(type.equalsIgnoreCase("doc") || type.equalsIgnoreCase("docx")){
			format = "WORD";
		}else if(type.equalsIgnoreCase("xls") || type.equalsIgnoreCase("xlsx") || type.equalsIgnoreCase("csv")){
			format = "EXCEL";
		}else if(type.equalsIgnoreCase("pdf")){
			format = "PDF";
		}
		return format;
	}
	public CadreDetailsVO getDetailsForACadre(Long tdpCadreId,Long batchId){
		CadreDetailsVO vo=new CadreDetailsVO();
		try{
			
			Object[] cadreInfo= trainingCampBatchAttendeeDAO.getCadreDetailsByCadreIdAndBatchId(tdpCadreId,batchId);
			Object[] designationDetails = tdpCommitteeMemberDAO.getPartyPositionBycadre(tdpCadreId);//tdpCommitteeLevel,role
			String designation = "";
			if(designationDetails != null && designationDetails.length > 0){
				designation = designationDetails[0]+" level "+designationDetails[1];
			}
			if(cadreInfo!=null && cadreInfo.length>0){//cid,fname,mobile,cname,dname,pname,image
				vo.setId(tdpCadreId);
				vo.setName(cadreInfo[1]!=null?cadreInfo[1].toString():"");
				vo.setMobileno(cadreInfo[2]!=null?cadreInfo[2].toString():"");
				vo.setConstituency(cadreInfo[3]!=null?cadreInfo[3].toString():"");
				vo.setDistrictName(cadreInfo[4]!=null?cadreInfo[4].toString():"");
				vo.setProgramName(cadreInfo[5]!=null?cadreInfo[5].toString():"");
				vo.setImage(cadreInfo[6]!=null?cadreInfo[6].toString():"");
				if(designation != null && designation.length() > 0){
					vo.setDesignation(designation);
				}
			}
			
			Object[] feedBackInfo=trainingCampCadreFeedbackDetailsDAO.getFeedBackDetailsforCadre(tdpCadreId,batchId);
			if(feedBackInfo!=null && feedBackInfo.length>0){//ll,css,lss,hea,rem
				vo.setLeaderShipLevelId(feedBackInfo[0]!=null?(Long)feedBackInfo[0]:0l);
				vo.setCommunicationSkillsStatusId(feedBackInfo[1]!=null?(Long)feedBackInfo[1]:0l);
				vo.setLeaderShipSkillsStatusId(feedBackInfo[2]!=null?(Long)feedBackInfo[2]:0l);
				vo.setHealthStatusId(feedBackInfo[3]!=null?(Long)feedBackInfo[3]:0l);
				vo.setRemarks(feedBackInfo[4]!=null?feedBackInfo[4].toString():"");
			}
			List<Object[]> achievments=trainingCampCadreAchievementDAO.getAchievmentDetailsforCadre(tdpCadreId,batchId);
			if(achievments!=null && achievments.size()>0){
				if(vo.getAchievementsList()==null){
					vo.setAchievementsList(new ArrayList<SimpleVO>());
				}
				for(Object[] param:achievments){
					  SimpleVO simplevo=new SimpleVO();
					  simplevo.setId(param[0]!=null?(Long)param[0]:0l);
					  simplevo.setName(param[1]!=null?param[1].toString():"");
					  vo.getAchievementsList().add(simplevo);
				}
			}
			List<Object[]> goals=trainingCampCadreGoalDAO.getGoalsDetailsforCadre(tdpCadreId,batchId);
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			if(goals!=null && goals.size()>0){
				if(vo.getGoalsList()==null){
					vo.setGoalsList(new ArrayList<SimpleVO>());
				}
				for(Object[] param:goals){
					SimpleVO simplevo=new SimpleVO();
					 simplevo.setId(param[0]!=null?(Long)param[0]:0l);
					 simplevo.setName(param[1]!=null?param[1].toString():"");
					 simplevo.setDateString(param[2]!=null?sdf.format((Date)param[2]):null);
					 vo.getGoalsList().add(simplevo);
				}
			}
			
		}catch(Exception e){
			LOG.error(" Error in getDetailsForACadre",e);
		}
		return vo;
	}
	public CadreDetailsVO getAllStatusForCadre(){
		 CadreDetailsVO finalVO=new CadreDetailsVO();
		try{
			List<Object[]> leaderShiplevels=cadreLeadershipLevelDAO.getAllLeaderShipLevels();//id,name
			List<Object[]> communicationSkills=cadreComminicationSkillsStatusDAO.getAllCadreComminicationSkills();//id,name
			List<Object[]> leaderShipSkills=cadreLeadershipSkillsStatusDAO.getAllCadreLeadershipSkills();////id,name
			List<Object[]> healthStatus=cadreHealthStatusDAO.getAllCadreHealthStatus();
			
			if(leaderShiplevels!=null && leaderShiplevels.size()>0){
				if( finalVO.getLeadershiplevelslist()==null){
					finalVO.setLeadershiplevelslist(new ArrayList<IdNameVO>());
				}
				setDetails(finalVO.getLeadershiplevelslist(),leaderShiplevels);
			}
			if(communicationSkills!=null && communicationSkills.size()>0){
				if( finalVO.getCommunicationsSkillslist()==null){
					finalVO.setCommunicationsSkillslist(new ArrayList<IdNameVO>());
				}
				setDetails(finalVO.getCommunicationsSkillslist(),communicationSkills);
			}
			if(leaderShipSkills!=null && leaderShipSkills.size()>0){
				if( finalVO.getLeadershipSkillslist()==null){
					finalVO.setLeadershipSkillslist(new ArrayList<IdNameVO>());
				}
				setDetails(finalVO.getLeadershipSkillslist(),leaderShipSkills);
			}
			if(healthStatus!=null && healthStatus.size()>0){
				if( finalVO.getHealthStatuslist()==null){
					finalVO.setHealthStatuslist(new ArrayList<IdNameVO>());
				}
				setDetails(finalVO.getHealthStatuslist(),healthStatus);
			}
			
		}catch(Exception e){
			LOG.error(" Error in getCadreStatus",e);
		}
		return finalVO;
	}
	public void setDetails(List<IdNameVO> finalList,List<Object[]> valuesList){
		
		for(Object[] obj:valuesList){
			IdNameVO idNameVO=new IdNameVO();
			idNameVO.setId(obj[0]!=null?(Long)obj[0]:0l);
			idNameVO.setName(obj[1]!=null?obj[1].toString():"");
			finalList.add(idNameVO);
		}
	}
	
	public List<CallTrackingVO> getDocsOfPartyMeetingId(Long partyMeetingId, String docSourceType){
		LOG.debug("Entered into getDocsOfPartyMeetingId");
		List<CallTrackingVO> finalDocs = new ArrayList<CallTrackingVO>();
		try{
			List<Object[]> documentDetails = partyMeetingDocumentDAO.getPartyMeetingDocsOf(partyMeetingId, docSourceType);
			if(documentDetails!=null && documentDetails.size()>0){
				
				for (Object[] objects : documentDetails) {
			
					CallTrackingVO vo = new CallTrackingVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setUrl(objects[2]!=null?IConstants.LOCAL_FILES+"/"+objects[2].toString().trim():"");
					vo.setName(objects[10]!=null?objects[10].toString():"");
					
					finalDocs.add(vo);
				}
				
			}
		}catch (Exception e) {
			LOG.error(" Error Occured in getDocsOfPartyMeetingId" ,e);
		}
		return finalDocs;
	}
	
	public CadreDetailsVO saveDetailsOfCadre(final Long tdpCadreId,final Long batchId,final List<String> achieveList,final List<SimpleVO> goalsList,final Long leaderShipLevelId,final Long communicationSkillsId,final Long leaderShipSkillsId,final Long healthId,final String comments,final Long userId)
	{
		final CadreDetailsVO cadreDetailsVO = new CadreDetailsVO();
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
					feedBackDetails.setUpdatedBy(userId);
					Date date1=new DateUtilService().getCurrentDateAndTime();
					feedBackDetails.setUpdatedTime(date1);
					feedBackDetails=trainingCampCadreFeedbackDetailsDAO.save(feedBackDetails);
					
					//feedback details history.
					TrainingCampCadreFeedbackDetailsHistory feedBackDetailshistory=new TrainingCampCadreFeedbackDetailsHistory();
					feedBackDetailshistory.setTrainingCampCadreFeedbackDetailsId(feedBackDetails.getTrainingCampCadreFeedbackDetailsId());
					feedBackDetailshistory.setTdpCadreId(feedBackDetails.getTdpCadreId());
					feedBackDetailshistory.setCadreLeadershipLevelId(feedBackDetails.getCadreLeadershipLevelId());
					feedBackDetailshistory.setCadreComminicationSkillsStatusId(feedBackDetails.getCadreComminicationSkillsStatusId());
					feedBackDetailshistory.setCadreLeadershipSkillsStatusId(feedBackDetails.getCadreLeadershipSkillsStatusId());
					feedBackDetailshistory.setCadreHealthStatusId(feedBackDetails.getCadreHealthStatusId());
					feedBackDetailshistory.setRemarks(feedBackDetails.getRemarks());
					feedBackDetailshistory.setInsertedBy(feedBackDetails.getInsertedBy());
					feedBackDetailshistory.setUpdatedBy(feedBackDetails.getUpdatedBy());
					feedBackDetailshistory.setInsertedTime(feedBackDetails.getInsertedTime());
					feedBackDetailshistory.setUpdatedTime(feedBackDetails.getUpdatedTime());
					feedBackDetailshistory.setTrainingCampBatchId(feedBackDetails.getTrainingCampBatchId());
					trainingCampCadreFeedbackDetailsHistoryDAO.save(feedBackDetailshistory);
					
					//Achievement details saving or updating.
					Long achieveCount=trainingCampCadreAchievementDAO.checkAchievementsForCadreBycadreAndBatch(tdpCadreId,batchId);
					if(achieveCount==0){ //save
						
					}else{//update=delete+save
						trainingCampCadreAchievementDAO.deleteAchievementsforACadre(tdpCadreId,batchId);
					}
					if(achieveList!=null && achieveList.size()>0){
						cadreDetailsVO.setAchievements(true);
						
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
							tca=trainingCampCadreAchievementDAO.save(tca);
							
							//Achievement details history.
							TrainingCampCadreAchievementHistory tcah=new TrainingCampCadreAchievementHistory();
							tcah.setTrainingCampCadreAchievementId(tca.getTrainingCampCadreAchievementId());
							tcah.setTdpCadreId(tca.getTdpCadreId());
							tcah.setTrainingCampBatchId(tca.getTrainingCampBatchId());
							tcah.setAchievement(tca.getAchievement());
							tcah.setInsertedBy(tca.getInsertedBy());
							tcah.setUpdatedBy(tca.getUpdatedBy());
							tcah.setInsertedTime(tca.getInsertedTime());
							tcah.setUpdatedTime(tca.getUpdatedTime());
							trainingCampCadreAchievementHistoryDAO.save(tcah);
						}
					}
				   //Goals details saving or updating.
					Long goalCount=trainingCampCadreGoalDAO.checkGoalsForCadreBycadreAndBatch(tdpCadreId,batchId);
                    if(goalCount==0){ //save
						
					}else{//update=delete+save
						trainingCampCadreGoalDAO.deleteGoalsforACadre(tdpCadreId,batchId);
					}
                    if( goalsList!=null && goalsList.size()>0){
                    	cadreDetailsVO.setGoals(true);
                    	
                      SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
                      for(SimpleVO goal:goalsList){
                    	  
                    	  TrainingCampCadreGoal tcg=new TrainingCampCadreGoal();
                    	  tcg.setTdpCadreId(tdpCadreId);
                    	  tcg.setTrainingCampBatchId(batchId);
                    	  tcg.setGoal(goal.getName());
                    	  try{
							tcg.setAchievedOn(sdf.parse(goal.getDateString()));
						  }catch(ParseException e){
							e.printStackTrace();
						  }
                    	  tcg.setInsertedBy(userId);
                    	  tcg.setUpdatedBy(userId);
						  Date date=new DateUtilService().getCurrentDateAndTime();
						  tcg.setInsertedTime(date);
						  tcg.setUpdatedTime(date);
						  tcg=trainingCampCadreGoalDAO.save(tcg);
						  
						 //Goals details history.
						   TrainingCampCadreGoalHistory tcgh=new TrainingCampCadreGoalHistory();
						   tcgh.setTrainingCampCadreGoalId(tcg.getTrainingCampCadreGoalId());
						   tcgh.setTdpCadreId(tcg.getTdpCadreId());
						   tcgh.setTrainingCampBatchId(tcg.getTrainingCampBatchId());
						   tcgh.setGoal(tcg.getGoal());
						   tcgh.setAchievedOn(tcg.getAchievedOn());
						   tcgh.setInsertedBy(tcg.getInsertedBy());
						   tcgh.setUpdatedBy(tcg.getUpdatedBy());
						   tcgh.setInsertedTime(tcg.getInsertedTime());
						   tcgh.setUpdatedTime(tcg.getUpdatedTime());
						   trainingCampCadreGoalHistoryDAO.save(tcgh);
                      }
                    }
		  }
		});
			cadreDetailsVO.setResultCode(1);
			//resultStatus.setResultCode(1);	
		}catch(Exception e){
			LOG.error(" Error Occured in getDocsOfPartyMeetingId" ,e);
			cadreDetailsVO.setResultCode(0);
			//resultStatus.setResultCode(0);
		}
		return cadreDetailsVO;
	}
	
    public SimpleVO getProgramsByUser(Long userId){
		
		SimpleVO simplevo=new SimpleVO();
		try{
			List<Object[]> programs=trainingCampUserProgramDAO.getProgramsByUser(userId);
			if(programs!=null && programs.size()>0){
				simplevo.setSimpleVOList1(new ArrayList<SimpleVO>());
				for(Object[] obj:programs){
					SimpleVO vo=new SimpleVO();
					vo.setId(obj[0]!=null?(Long)obj[0]:0l);
					vo.setName(obj[1]!=null?obj[1].toString():"");
					simplevo.getSimpleVOList1().add(vo);
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return simplevo;
	}
    
    public SimpleVO getAllProgramsAndCamps(){
		
		SimpleVO simpleVO=new SimpleVO();
		try{
			List<Object[]> programs=trainingCampProgramDAO.getPrograms();
			List<Object[]> camps=trainingCampDAO.getAllCamps();
			if(programs!=null && programs.size()>0){
				simpleVO.setSimpleVOList1(new ArrayList<SimpleVO>());
				for(Object[] obj:programs){
					SimpleVO vo=new SimpleVO();
					vo.setId(obj[0]!=null?(Long)obj[0]:0l);
					vo.setName(obj[1]!=null?obj[1].toString():"");
					simpleVO.getSimpleVOList1().add(vo);
					
				}
			}
			if(camps!=null && camps.size()>0){
				simpleVO.setSimpleVOList2(new ArrayList<SimpleVO>());
				for(Object[] obj:camps){
					SimpleVO vo=new SimpleVO();
					vo.setId(obj[0]!=null?(Long)obj[0]:0l);
					vo.setName(obj[1]!=null?obj[1].toString():"");
					simpleVO.getSimpleVOList2().add(vo);
					
				}
			}
			
		}catch (Exception e){
		   e.printStackTrace();
		}
		return simpleVO;
	}
    public List<IdNameVO> getCampsByProgramAndUser(Long campProgramId,Long userId){
		
		List<IdNameVO> campsList=null;
		try{
			List<Object[]> camps=trainingCampUserProgramDAO.getCampsByProgramAndUser(campProgramId,userId);
			if(camps!=null && camps.size()>0){
				campsList=new ArrayList<IdNameVO>();
				for(Object[] obj:camps){
					IdNameVO vo=new IdNameVO();
					vo.setId(obj[0]!=null?(Long)obj[0]:0l);
					vo.setName(obj[1]!=null?obj[1].toString():"");
					campsList.add(vo);
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return campsList;
	}
    
    public Map<String,TrainingCampVO> getCompletedRunningUpcomingBatchIds(String startDateString,String endDateString,Long stateId,String type){
    	Map<String,TrainingCampVO> finalMap = new HashMap<String, TrainingCampVO>();
    	try {
			LOG.info("Entered into getCompletedRunningUpcomingBatchIds");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(startDateString);
			Date endDate = sdf.parse(endDateString);
			
			List<Long> completedBatchIds = new ArrayList<Long>(0);
			List<Long> runningBatchIds = new ArrayList<Long>(0);
			List<Long> upComingBatchIds = new ArrayList<Long>(0);
			
			List<Long> totalTrainingCenters= new ArrayList<Long>(0);
			
			List<Object[]> completedBatches = new ArrayList<Object[]>(0); 
			List<Object[]> runningBatches = new ArrayList<Object[]>(0);
			List<Object[]> upComingBatches = new ArrayList<Object[]>(0);
			
			if(type.equalsIgnoreCase("All")){
				completedBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"completed",stateId);
				setBatchesInformation(finalMap,completedBatches,"completed",completedBatchIds,totalTrainingCenters);
				Map<Long,Long> completedMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(completedBatchIds,"attendence");
				setBatchCount(finalMap,"completed",completedMembersCounts);
				
				runningBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"running",stateId);
				setBatchesInformation(finalMap,runningBatches,"running",runningBatchIds,totalTrainingCenters);
				Map<Long,Long> runningMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(runningBatchIds,"attendee");
				setBatchCount(finalMap,"running",runningMembersCounts);
				
				upComingBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"upcoming",stateId);
				setBatchesInformation(finalMap,upComingBatches,"upcoming",upComingBatchIds,totalTrainingCenters);
				Map<Long,Long> upCommingMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(upComingBatchIds,"attendee");
				setBatchCount(finalMap,"upcoming",upCommingMembersCounts);
			}
			else if(type.equalsIgnoreCase("completed")){
				completedBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"completed",stateId);
				setBatchesInformation(finalMap,completedBatches,"completed",completedBatchIds,totalTrainingCenters);
				Map<Long,Long> completedMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(completedBatchIds,"attendence");
				setBatchCount(finalMap,"completed",completedMembersCounts);
				finalMap.get("completed").setCompletedBatchIds(completedBatchIds);
			}
			else if(type.equalsIgnoreCase("running")){
				runningBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"running",stateId);
				setBatchesInformation(finalMap,runningBatches,"running",runningBatchIds,totalTrainingCenters);
				Map<Long,Long> runningMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(runningBatchIds,"attendee");
				setBatchCount(finalMap,"running",runningMembersCounts);
				finalMap.get("completed").setRunningBatchIds(runningBatchIds);
			}
			else if(type.equalsIgnoreCase("upcoming")){
				upComingBatches = trainingCampBatchDAO.getCompletedBatchIds(startDate,endDate,"upcoming",stateId);
				setBatchesInformation(finalMap,upComingBatches,"upcoming",upComingBatchIds,totalTrainingCenters);
				Map<Long,Long> upCommingMembersCounts = getCompletedRunningUpcomingCountsForBatchIds(upComingBatchIds,"attendee");
				setBatchCount(finalMap,"upcoming",upCommingMembersCounts);
				finalMap.get("completed").setUpComingBatchIds(upComingBatchIds);
			}
			
			/*if(finalMap.get("completed")!=null){
				finalMap.get("completed").setTotalTrainingCenters(totalTrainingCenters.size());
				finalMap.get("completed").setCompletedBatchIds(completedBatchIds);
				finalMap.get("completed").setRunningBatchIds(runningBatchIds);
				finalMap.get("completed").setUpComingBatchIds(upComingBatchIds);
			}*/
			
			if(completedBatches.size()>0){
				setProgramInformation(finalMap,completedBatches,"completed");
			}
			if(runningBatches.size()>0){
				setProgramInformation(finalMap,runningBatches,"running");
			}
			if(upComingBatches.size()>0){
				setProgramInformation(finalMap,upComingBatches,"upcoming");
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at getCompletedRunningUpcomingBatchIds", e);
		}
    	return finalMap;
    }
    
    public void setBatchCount(Map<String,TrainingCampVO> finalMap,String type,Map<Long,Long> MembersCounts){
    	if(finalMap.get(type)!=null){
    		TrainingCampVO vo = finalMap.get(type);
    		
    		if(vo.getProgramDetails()!=null){
    			for (TrainingCampVO progTrainingCampVO : vo.getProgramDetails()) {
					if(progTrainingCampVO.getCampDetails()!=null){
						for (TrainingCampVO campTrainingCampVO : progTrainingCampVO.getCampDetails()) {
							if(campTrainingCampVO.getScheduleDetails()!=null){
								for(TrainingCampVO scheduleTrainingCampVO : campTrainingCampVO.getScheduleDetails()){
									if(scheduleTrainingCampVO.getBatchDetails()!=null){
										for(TrainingCampVO batchTrainingCampVO : scheduleTrainingCampVO.getBatchDetails()){
											if(type.equalsIgnoreCase("completed"))
												batchTrainingCampVO.setCompletedMemberCount(MembersCounts.get(batchTrainingCampVO.getBatchId())!=null?MembersCounts.get(batchTrainingCampVO.getBatchId()):0l);
											else if(type.equalsIgnoreCase("running"))
											batchTrainingCampVO.setRunningMemberCount(MembersCounts.get(batchTrainingCampVO.getBatchId())!=null?MembersCounts.get(batchTrainingCampVO.getBatchId()):0l);
											else if(type.equalsIgnoreCase("upcoming"))
											batchTrainingCampVO.setUpCommingMemberCount(MembersCounts.get(batchTrainingCampVO.getBatchId())!=null?MembersCounts.get(batchTrainingCampVO.getBatchId()):0l);
										
										}
									}
										
								}
							}	
						}
					}
				}
    		}
    	}
    }
    
    public void setProgramInformation(Map<String,TrainingCampVO> finalMap,List<Object[]> result,String type){
    	LOG.info("Entered into setProgramInformation");
    	try {
    		if(result!=null && result.size()>0){
				TrainingCampVO tmp = finalMap.get(type);
				if(tmp!=null){
					if(tmp.getProgramWiseDetails()==null){
						tmp.setProgramWiseDetails(new ArrayList<TrainingCampVO>());
					}
					List<TrainingCampVO> tempList = tmp.getProgramWiseDetails();
					for(Object[] obj:result){	
						
						TrainingCampVO matechedProgVO = getMatchedOne(tempList,(Long)obj[4],"prog");
						boolean isNewProg = false;
						if(matechedProgVO==null){
							isNewProg = true;
							matechedProgVO = new TrainingCampVO();
							matechedProgVO.setProgramId((Long)obj[4]);
							matechedProgVO.setProgramName(obj[5].toString());
						}
						
						List<TrainingCampVO> campList = matechedProgVO.getCampDetails();
						if(campList==null){
							campList = new ArrayList<TrainingCampVO>();
						}
						
						TrainingCampVO matechedCampVO = getMatchedOne(campList,(Long)obj[2],"camp");
						boolean isNewCamp = false;
						if(matechedCampVO==null){
							isNewCamp = true;
							matechedCampVO = new TrainingCampVO();
							matechedCampVO.setCampId((Long)obj[2]);
							matechedCampVO.setCampName(obj[3].toString());
						}
						
						List<TrainingCampVO> scheduleList = matechedCampVO.getScheduleDetails();
						if(scheduleList==null){
							scheduleList = new ArrayList<TrainingCampVO>();
						}
						
						TrainingCampVO matchesScheduleVO = getMatchedOne(scheduleList,(Long)obj[6],"schedule");
						boolean isNewSchedule = false;
						if(matchesScheduleVO==null){
							isNewSchedule = true;
							matchesScheduleVO = new TrainingCampVO();
							matchesScheduleVO.setScheduleId((Long)obj[6]);
							matchesScheduleVO.setScheduleName(obj[7].toString());
						}
						
						List<TrainingCampVO> batchList = matchesScheduleVO.getBatchDetails();
						if(batchList==null){
							batchList = new ArrayList<TrainingCampVO>();
						}
						
						TrainingCampVO matchedBatchVo = getMatchedOne(batchList, (Long)obj[0], "batch");
						if(matchedBatchVo==null){
							matchedBatchVo = new TrainingCampVO();
							matchedBatchVo.setBatchId((Long)obj[0]);
							matchedBatchVo.setBatchName(obj[1].toString());
							batchList.add(matchedBatchVo);
							
							if(type.equalsIgnoreCase("completed")){
								matechedCampVO.setCompletedBatches(matechedCampVO.getCompletedBatches()+1);
								List temp = new ArrayList<Long>();
								temp.add((Long)obj[0]);
								Map<Long,Long> count = getCompletedRunningUpcomingCountsForBatchIds(temp,"attendence");
								if(count!=null && count.size()>0){
									if(count.get((Long)obj[0])!=null){
										matechedCampVO.setCompletedMemberCount(matechedCampVO.getCompletedMemberCount()+count.get((Long)obj[0]));
									}
								}
								
							}else if(type.equalsIgnoreCase("running")){
								matechedCampVO.setRunningBatches(matechedCampVO.getUpComingBatches()+1);
								List temp = new ArrayList<Long>();
								temp.add((Long)obj[0]);
								Map<Long,Long> count = getCompletedRunningUpcomingCountsForBatchIds(temp,"attendee");
								if(count!=null && count.size()>0){
									if(count.get((Long)obj[0])!=null){
										matechedCampVO.setRunningMemberCount((matechedCampVO.getRunningMemberCount()+count.get((Long)obj[0])));
									}
								}
								
							}else if(type.equalsIgnoreCase("upcoming")){
								matechedCampVO.setUpComingBatches(matechedCampVO.getUpComingBatches()+1);
								List temp = new ArrayList<Long>();
								temp.add((Long)obj[0]);
								Map<Long,Long> count = getCompletedRunningUpcomingCountsForBatchIds(temp,"attendee");
								if(count!=null && count.size()>0){
									if(count.get((Long)obj[0])!=null){
										matechedCampVO.setCompletedMemberCount((matechedCampVO.getCompletedMemberCount()+count.get((Long)obj[0])));
									}
								}
								
							}
						}
						matchesScheduleVO.setBatchDetails(batchList);
						
								
						if(isNewSchedule){
							scheduleList.add(matchesScheduleVO);
						}
						matechedCampVO.setScheduleDetails(scheduleList);
								
						if(isNewCamp){
							campList.add(matechedCampVO);
						}
						matechedProgVO.setCampDetails(campList);
						
						if(isNewProg){
							tempList.add(matechedProgVO);
						}
						
						
	    			}
				}
    		}
		} catch (Exception e) {
			LOG.error("Exception raised at setProgramInformation", e);
		}
    }
    
    public TrainingCampVO getMatchedOne(List<TrainingCampVO> tempList,Long matchedId,String type){
    	if(type.equalsIgnoreCase("prog")){
    		for (TrainingCampVO trainingCampVO : tempList) {
    			if(trainingCampVO.getProgramId()!=null && trainingCampVO.getProgramId().longValue()==matchedId){
    				return trainingCampVO;
    			}
    		}
    	}
    	
    	if(type.equalsIgnoreCase("camp")){
    		for (TrainingCampVO trainingCampVO : tempList) {
    			if(trainingCampVO.getCampId()!=null && trainingCampVO.getCampId().longValue()==matchedId){
    				return trainingCampVO;
    			}
    				
   			}
   		}
    	
    	if(type.equalsIgnoreCase("schedule")){
    		for (TrainingCampVO trainingCampVO : tempList) {
    			if(trainingCampVO.getScheduleId()!=null && trainingCampVO.getScheduleId().longValue()==matchedId){
    				return trainingCampVO;
    			}
    				
   			}
   		}
    	
    	if(type.equalsIgnoreCase("batch")){
    		for (TrainingCampVO trainingCampVO : tempList) {
    			if(trainingCampVO.getBatchId()!=null && trainingCampVO.getBatchId().longValue()==matchedId){
    				return trainingCampVO;
    			}
    				
   			}
   		}
    	return null;
    }
 
    public void setBatchesInformation(Map<String,TrainingCampVO> finalMap, List<Object[]> result, String type,List<Long> batchIdsList,List<Long> totalTrainingCenters){
    	LOG.debug("Entered Into SetBatchesInformation");
    	try{
    		
    		if(result!=null && result.size()>0){
    			for(Object[] obj:result){
    				TrainingCampVO tmp = finalMap.get(type);
            		if(tmp==null){
            			tmp = new TrainingCampVO();
            		}
            		
            		List<TrainingCampVO> prgDtlsLst = tmp.getProgramDetails();
            		if(prgDtlsLst==null){
            			prgDtlsLst = new ArrayList<TrainingCampVO>();
            		}
            		TrainingCampVO prgrm = getMatchedProgCampBatch(Long.valueOf(obj[4].toString()), prgDtlsLst, "program");
            		boolean isNewProgram = false;
            		if(prgrm==null){
            			isNewProgram = true;
            			prgrm = new TrainingCampVO();
            			prgrm.setProgramId(Long.valueOf(obj[4].toString()));
            			prgrm.setProgramName(obj[5].toString());
            		}
            		
            		List<TrainingCampVO> cmpDtlsLst =prgrm.getCampDetails();
            		if(cmpDtlsLst==null){
            			cmpDtlsLst = new ArrayList<TrainingCampVO>();
            		}
            		
            		TrainingCampVO cmp = getMatchedProgCampBatch(Long.valueOf(obj[2].toString()), cmpDtlsLst, "camp");
            		boolean isNewCamp = false;
            		if(cmp==null){
            			isNewCamp = true;
            			cmp = new TrainingCampVO();
            			cmp.setCampId(Long.valueOf(obj[2].toString()));
            			cmp.setCampName(obj[3].toString());
            		}
            		
            		if(!totalTrainingCenters.contains((Long)obj[2])){
            			totalTrainingCenters.add((Long)obj[2]);
            		}
            		
            		List<TrainingCampVO> schdulDtlsLst = cmp.getScheduleDetails();
            		if(schdulDtlsLst==null){
            			schdulDtlsLst = new ArrayList<TrainingCampVO>();
            		}
            		
            		TrainingCampVO scdle = getMatchedProgCampBatch(Long.valueOf(obj[6].toString()), schdulDtlsLst, "schedule");
            		boolean isNewSchedule = false;
            		if(scdle==null){
            			isNewSchedule= true;
            			scdle = new TrainingCampVO();
            			scdle.setScheduleId((Long)obj[6]);
            			scdle.setScheduleDates(obj[10].toString()+" - "+obj[11].toString());
            			scdle.setScheduleCode(obj[7].toString());
            		}
            		
            		
            		
            		List<TrainingCampVO> btchDtlsLst =scdle.getBatchDetails();
            		if(btchDtlsLst==null){
            			btchDtlsLst = new ArrayList<TrainingCampVO>();
            		}
            		TrainingCampVO btch = getMatchedProgCampBatch(Long.valueOf(obj[0].toString()), btchDtlsLst, "batch");
            		if(btch==null){
            			btch = new TrainingCampVO();
            			btch.setBatchId(Long.valueOf(obj[0].toString()));
            			btch.setBatchName(obj[1].toString());
            			btch.setBatchDates(obj[8].toString()+" - "+obj[9].toString());
                		btchDtlsLst.add(btch);
                		batchIdsList.add(Long.valueOf(obj[0].toString()));
                		if(!isNewCamp){
                			cmp.setCmpBatchCount(cmp.getCmpBatchCount()+1);
                		}
            		}
            		
            		scdle.setBatchDetails(btchDtlsLst);
            		
            		if(isNewSchedule){
            			schdulDtlsLst.add(scdle);
            		}
            		cmp.setScheduleDetails(schdulDtlsLst);
            		
            		if(isNewCamp){
            			cmpDtlsLst.add(cmp);
            		}
            		
            		prgrm.setCampDetails(cmpDtlsLst);
        			if(isNewProgram){
        				prgDtlsLst.add(prgrm);
        			}
        			tmp.setProgramDetails(prgDtlsLst);
        			finalMap.put(type, tmp);
    			}
    			
    		}
    		
    		
    		
    	}catch (Exception e) {
    		LOG.error("Exception Raised in SetBatchesInformation",e);
		}
    }
    
    
    public TrainingCampVO getMatchedProgCampBatch(Long id, List<TrainingCampVO> result, String type){
    	if(id!=null && result!=null && result.size()>0){
    		if(type.equalsIgnoreCase("program")){
    			for(TrainingCampVO temp:result){
    				if(temp.getProgramId().equals(id)){
    					return temp;
    				}
    			}
    		}
    		
    		if(type.equalsIgnoreCase("camp")){
    			for(TrainingCampVO temp:result){
    				if(temp.getCampId().equals(id)){
    					return temp;
    				}
    			}
    		}
    		
    		if(type.equalsIgnoreCase("schedule")){
    			for(TrainingCampVO temp:result){
    				if(temp.getScheduleId().equals(id)){
    					return temp;
    				}
    			}
    		}
    		
    		if(type.equalsIgnoreCase("batch")){
    			for(TrainingCampVO temp:result){
    				if(temp.getBatchId().equals(id)){
    					return temp;
    				}
    			}
    		}
    	}
    	return null;
    }
    
    
    
    public Map<Long,Long> getCompletedRunningUpcomingCountsForBatchIds(List<Long> batchIds,String type){
    	
    	Map<Long,Long> finalMap = new HashMap<Long, Long>();
    	List<Object[]> countsList = new ArrayList<Object[]>();
    	
    	if(batchIds.size()>0){
	    	if(type.equalsIgnoreCase("attendee")){
	    		countsList = trainingCampBatchAttendeeDAO.getRunningUpcomingCounts(batchIds);
	    	}else if(type.equalsIgnoreCase("attendence")){
	    		countsList = trainingCampAttendanceDAO.getCompletedCounts(batchIds);
	    	}
    	}
    	if(countsList!=null && countsList.size()>0){
    		for (Object[] objects : countsList) {
    			finalMap.put((Long)objects[0], (Long)objects[1]);
			}
    	}
    	
    	return finalMap;
    }
    
   public List<IdNameVO> getAttendedCountForBatchesByLocation(String startDateString,String endDateString,Long stateId){
		
		List<IdNameVO> finalList=new ArrayList<IdNameVO>();
		try{
	   		
			Map<Long,IdNameVO> finalMap=new LinkedHashMap<Long,IdNameVO>();
			IdNameVO villageVO=new IdNameVO();
			villageVO.setId(6l);
			villageVO.setName("village");
			villageVO.setDistrictid(0l);
			finalMap.put(6l,villageVO);
			
			IdNameVO mandalVO=new IdNameVO();
			mandalVO.setId(5l);
			mandalVO.setName("mandal");
			mandalVO.setDistrictid(0l);
			finalMap.put(5l,mandalVO);
			
			IdNameVO districtVO=new IdNameVO();
			districtVO.setId(11l);
			districtVO.setName("district");
			districtVO.setDistrictid(0l);
			finalMap.put(11l,districtVO);
			
			
			Map<String,TrainingCampVO>  compBatchMap =getCompletedRunningUpcomingBatchIds(startDateString,endDateString,stateId,"completed");
			if(compBatchMap!=null && compBatchMap.size()>0){
				TrainingCampVO trainingCampVO=compBatchMap.get("completed");
				if(trainingCampVO!=null){
					
					List<Long> batchIds=trainingCampVO.getCompletedBatchIds();
					if(batchIds!=null && batchIds.size()>0){
						List<Object[]> list=trainingCampAttendanceDAO.getAttendedCountForBatchesByLocation(batchIds);
						if(list!=null && list.size()>0){
							for(Object[] obj:list){
								IdNameVO vo=finalMap.get((Long)obj[0]);
								vo.setDistrictid(obj[2]!=null?(Long)obj[2]:0l);
							}
						}
					}
				}
			}
			finalList.addAll(finalMap.values());
			
		}catch(Exception e){
			LOG.error(" Error Occured in getAttendedCountForBatchesByLocation" ,e);
		}
		return finalList;
	}
   public SimpleVO getInvitedAttendedCadreCountByBatchIds(String startDateString,String endDateString,Long stateId){
   	
	    SimpleVO finalVO=new SimpleVO();
   	try{
   		
   		Map<String,TrainingCampVO>  compBatchMap =getCompletedRunningUpcomingBatchIds(startDateString,endDateString,stateId,"completed");
		if(compBatchMap!=null && compBatchMap.size()>0){
			TrainingCampVO trainingCampVO=compBatchMap.get("completed");
			if(trainingCampVO!=null){
				
		        List<Long> batchIds=trainingCampVO.getCompletedBatchIds();
   		
   		
		   		if(batchIds!=null && batchIds.size()>0){
		   		
		   		
			   		Map<Long,SimpleVO> districtMap=new LinkedHashMap<Long,SimpleVO>();
			   		Map<Long,SimpleVO> constituencyMap=new LinkedHashMap<Long,SimpleVO>();
			   		
			   		
			   		List<Object[]> invitedListDist=trainingCampAttendanceDAO.getInvitedCadreCountByBatchIds(batchIds,"district");
			   		List<Object[]> attendedListDist=trainingCampAttendanceDAO.getAttendedCadreCountByBatchIds(batchIds,"district");
			   		
			   		List<Object[]> invitedListCon=trainingCampAttendanceDAO.getInvitedCadreCountByBatchIds(batchIds,"constituency");
			   		List<Object[]> attendedListCon=trainingCampAttendanceDAO.getAttendedCadreCountByBatchIds(batchIds,"constituency");
			   		
			   		
			   		
			   		if(invitedListDist!=null && invitedListDist.size()>0){
			   			settingData(districtMap,invitedListDist,"invited");
			   		}
			   		if(attendedListDist!=null && attendedListDist.size()>0){
			   			settingData(districtMap,attendedListDist,"attended");
			   		}
			   		
			   		if(invitedListCon!=null && invitedListCon.size()>0){
			   			settingData(constituencyMap,invitedListCon,"invited");
			   		}
			   		if(attendedListCon!=null && attendedListCon.size()>0){
			   			settingData(constituencyMap,attendedListCon,"attended");
			   		}
			   	
			   		
			   		if(districtMap!=null && districtMap.size()>0){
			   			finalVO.setSimpleVOList1(new ArrayList<SimpleVO>(districtMap.values()));
			   		}
			   		if(constituencyMap!=null && constituencyMap.size()>0){
			   			finalVO.setSimpleVOList2(new ArrayList<SimpleVO>(constituencyMap.values()));
			   		}
		   		
		   		}
			}
		}
		}catch (Exception e){
			LOG.error(" Error Occured in getInvitedAttendedCadreCountByBatchIds" ,e);
		}
   	return finalVO;
   }
   public void  settingData(Map<Long,SimpleVO> constituencyMap,List<Object[]> invitedList,String type){
   	
   	try{
   		
			for(Object[] obj:invitedList){
				
				boolean locExist=true;
				SimpleVO vo=constituencyMap.get((Long)obj[0]);
				
				if(vo==null){
					locExist=false;
					vo=new SimpleVO();
					vo.setId((Long)obj[0]);
					vo.setName(obj[1]!=null?obj[1].toString():"");
					vo.setTotal(0l);//invited
					vo.setCount(0l);//attended
					
				}
				if(type.equalsIgnoreCase("invited")){
					vo.setTotal(obj[2]!=null?(Long)obj[2]:0l);
				}else{
					vo.setCount(obj[2]!=null?(Long)obj[2]:0l);
				}
				if(!locExist){
					constituencyMap.put((Long)obj[0],vo);
				}
			}
   		
		}catch(Exception e){
			LOG.error(" Error Occured in settingData" ,e);
		}
   }
   
   
  /**
   *   @author    : Sreedhar
   *   Description:This Service is used to get the CadreCount feedbacks By Program Or By Camp Or By Batch
   *   inputs: programId,campId,batchId
   *   output: CadreFeedbackVO
   *   
  */
	public CadreFeedbackVO  getattendedcountByFeedBacks(Long programId,Long campId,Long batchId){
		
		CadreFeedbackVO finalVO=new CadreFeedbackVO();
		try{
			//setStaticData
			List<Object[]> llData=cadreLeadershipLevelDAO.getAllLeaderShipLevels();
			List<Object[]> csData=cadreComminicationSkillsStatusDAO.getAllCadreComminicationSkills();
			List<Object[]> lsData=cadreLeadershipSkillsStatusDAO.getAllCadreLeadershipSkills();
			List<Object[]> hsData=cadreHealthStatusDAO.getAllCadreHealthStatus();
			
			finalVO.setMap(new LinkedHashMap<String, CadreFeedbackVO>());
			putMainMapDetails(finalVO);
			
			if(llData!=null && llData.size()>0){
				putSubMapDetails(finalVO.getMap(),llData,"leaderShiplevels");
			}
			if(csData!=null && csData.size()>0){
				putSubMapDetails(finalVO.getMap(),csData,"communuicationSkills");
			}
			if(lsData!=null && lsData.size()>0){
				putSubMapDetails(finalVO.getMap(),lsData,"leadershipSkills");
			}
			if(hsData!=null && hsData.size()>0){
				putSubMapDetails(finalVO.getMap(),hsData,"healthStatus");
			}
			
			String queryString=getQueryforFeedbacks(programId,campId,batchId);
			String achieveQuery=getQueryforAchieveOrGoal(programId,campId,batchId,"achieve");
			String goalQuery=getQueryforAchieveOrGoal(programId,campId,batchId,"goal");
			
			Long achievementCount=trainingCampCadreFeedbackDetailsDAO.getattendedcount1(achieveQuery,programId,campId,batchId);
			Long goalsCount=trainingCampCadreFeedbackDetailsDAO.getattendedcount1(goalQuery,programId,campId,batchId);
			finalVO.setAchievementCount(achievementCount!=null?achievementCount:0l); 
			finalVO.setGaoalCount(goalsCount!=null?goalsCount:0l);
			
			
           List<Object[]> feedBacks=trainingCampCadreFeedbackDetailsDAO.getattendedcount(queryString,programId,campId,batchId);
           if(feedBacks!=null && feedBacks.size()>0){
				for(Object[] obj:feedBacks){
					
					if(obj[1]!=null){
						CadreFeedbackVO llVO =finalVO.getMap().get("leaderShiplevels");
						llVO.setCount(llVO.getCount()+1);
						llVO.getSubMap().get((Long)obj[1]).setCount(llVO.getSubMap().get((Long)obj[1]).getCount()+1);
					}
					if(obj[3]!=null){
						CadreFeedbackVO csVO =finalVO.getMap().get("communuicationSkills");
						csVO.setCount(csVO.getCount()+1);
						csVO.getSubMap().get((Long)obj[3]).setCount(csVO.getSubMap().get((Long)obj[3]).getCount()+1);
					}
					if(obj[5]!=null){
						CadreFeedbackVO lsVO =finalVO.getMap().get("leadershipSkills");
						lsVO.setCount(lsVO.getCount()+1);
						lsVO.getSubMap().get((Long)obj[5]).setCount(lsVO.getSubMap().get((Long)obj[5]).getCount()+1);
					}
					if(obj[7]!=null){
						CadreFeedbackVO hsVO =finalVO.getMap().get("healthStatus");
						hsVO.setCount(hsVO.getCount()+1);
						hsVO.getSubMap().get((Long)obj[7]).setCount(hsVO.getSubMap().get((Long)obj[7]).getCount()+1);
					}
				}
			}
         //converting
			if(finalVO.getMap()!=null && finalVO.getMap().size()>0){
				for (Map.Entry<String, CadreFeedbackVO> entry : finalVO.getMap().entrySet())
		        {
					CadreFeedbackVO typeVO= entry.getValue();
		           if(typeVO.getSubMap()!=null && typeVO.getSubMap().size()>0){
		        	   typeVO.setSubList( new ArrayList<CadreFeedbackVO>(typeVO.getSubMap().values()));
		        	   typeVO.getSubMap().clear();
		            }
		         }
				finalVO.setList(new ArrayList<CadreFeedbackVO>(finalVO.getMap().values()));
				finalVO.getMap().clear();
			}
			
		}catch(Exception e){
			LOG.error(" Error Occured in getattendedcountByFeedBacks" ,e);
		}
		return finalVO;
	}
	  public void  putMainMapDetails(CadreFeedbackVO finalVO){
			
			CadreFeedbackVO vo=new CadreFeedbackVO();
			vo.setName("leaderShiplevels");
			
			CadreFeedbackVO vo1=new CadreFeedbackVO();
			vo1.setName("communuicationSkills");
			
			CadreFeedbackVO vo2=new CadreFeedbackVO();
			vo2.setName("leadershipSkills");
			
			CadreFeedbackVO vo3=new CadreFeedbackVO();
			vo3.setName("healthStatus");
			
			finalVO.getMap().put("leaderShiplevels",vo);
			finalVO.getMap().put("communuicationSkills",vo1);
			finalVO.getMap().put("leadershipSkills",vo2);
			finalVO.getMap().put("healthStatus",vo3);
		}
	
	  public void putSubMapDetails(Map<String,CadreFeedbackVO> mainMap,List<Object[]> feedbacks,String type){
			
			for(Object[] obj:feedbacks){
				CadreFeedbackVO vo=new CadreFeedbackVO();
				vo.setId((Long)obj[0]);
				vo.setName(obj[1].toString());
				if( mainMap.get(type).getSubMap() ==null){
					mainMap.get(type).setSubMap(new LinkedHashMap<Long, CadreFeedbackVO>());
				}
				mainMap.get(type).getSubMap().put((Long)obj[0],vo);
			}
	 }
	  public String getQueryforFeedbacks(Long programId,Long campId,Long batchId){
			String queryString=null;
			StringBuilder sbM=new StringBuilder();
			try{
				//Query.
				
				sbM.append(" select distinct tccfd.trainingCampCadreFeedbackDetailsId," +
						   " ll.cadreLeadershipLevelId,ll.leadershipLevel," +
						   " cs.cadreComminicationSkillsStatusId,cs.status," +
						   " ls.cadreLeadershipSkillsStatusId,ls.status," +
						   " hs.cadreHealthStatusId,hs.status");
				
				sbM.append(" from TrainingCampAttendance tca,TrainingCampCadreFeedbackDetails tccfd " +
						   " left join tccfd.cadreLeadershipLevel ll " +
						   " left join tccfd.cadreComminicationSkillsStatus cs " +
						   " left join tccfd.cadreLeadershipSkillsStatus ls " +
						   " left join tccfd.cadreHealthStatus hs " +
						   " where tca.attendance.tdpCadreId=tccfd.tdpCadreId ");
				           
	           if(batchId==null && campId==null && programId!=null){
					
					sbM.append(" and tca.trainingCampProgramId=:programId");
					
				}else if(batchId==null && campId!=null){
					
					sbM.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					if(programId!=null)
						sbM.append(" and tca.trainingCampProgramId=:programId");
					
				}else if(batchId!=null){
					
					if(programId!=null)
						sbM.append(" and tca.trainingCampProgramId=:programId");
					if(campId!=null)
					   sbM.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					
					sbM.append(" and tca.trainingCampBatchId=:batchId");
					
				}
			}catch(Exception e){
				LOG.error(" Error Occured in getQueryforFeedbacks" ,e);
			}
			queryString=sbM.toString();
			return queryString;
		}
		public String getQueryforAchieveOrGoal(Long programId,Long campId,Long batchId,String type){
			String queryString=null;
			StringBuilder sbM=new StringBuilder();
			try{
				//Query.
				
				sbM.append(" select count(distinct model.tdpCadreId)" );
			    sbM.append(" from TrainingCampAttendance tca ");
			    if(type.equalsIgnoreCase("achieve")){
			    	sbM.append(",TrainingCampCadreAchievement model ");
			    }else if(type.equalsIgnoreCase("goal")){
			    	sbM.append(",TrainingCampCadreGoal model ");
			    }
				sbM.append(" where tca.attendance.tdpCadreId=model.tdpCadreId ");
				           
	           if(batchId==null && campId==null && programId!=null){
					sbM.append(" and tca.trainingCampProgramId=:programId");
				}else if(batchId==null && campId!=null){
					sbM.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					if(programId!=null)
						sbM.append(" and tca.trainingCampProgramId=:programId");
				}else if(batchId!=null){
					
					if(programId!=null)
						sbM.append(" and tca.trainingCampProgramId=:programId");
					if(campId!=null)
					   sbM.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					
					sbM.append(" and tca.trainingCampBatchId=:batchId");
					
				}
			}catch(Exception e){
				LOG.error(" Error Occured in getQueryforAchieveOrGoal" ,e);
			}
			queryString=sbM.toString();
			return queryString;
		}
		
	 /**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the CadreCount committee level wise By Program Or By Camp Or By Batch
	   *   inputs: programId,campId,batchId
	   *   Return: List<SimpleVO> 
	   *   
	  */
		public List<SimpleVO> getAttendedCountsByProgramOrCampOrBatch(Long programId,Long campId,Long batchId){
			List<SimpleVO> finalList=null;
			try{
				 Map<Long,SimpleVO> finalMap=new LinkedHashMap<Long,SimpleVO>();
				 
				//preinstantiate
				preInstantiate(finalMap,programId,campId,batchId);
				
				String queryString=getAttendedlocWiseCountsByProgramOrCampOrBatch(programId,campId,batchId);
				List<Object[]> totalCounts=trainingCampAttendanceDAO.getAttendedlocWiseCountsByProgramOrCampOrBatch(queryString,programId,campId,batchId);
				
				if(totalCounts!=null && totalCounts.size()>0){
					
					for(Object[] obj:totalCounts){//did,dname,count
						
						Long districtId=(Long)obj[0];
						SimpleVO districtVO=finalMap.get(districtId);
						if(districtVO==null){
							districtVO=finalMap.get(0l);
							districtVO.setCount(districtVO.getCount()+ (obj[2]!=null?(Long)obj[2]:0l));
						}else{
							districtVO.setCount(obj[2]!=null?(Long)obj[2]:0l);
						}
					}
				}
				
				String queryString1=getCommiteesCadreCountByLoc(programId,campId,batchId);
			    List<Object[]> levelCounts=trainingCampAttendanceDAO.getAttendedlocWiseCountsByProgramOrCampOrBatch(queryString1,programId,campId,batchId);
				if(levelCounts!=null && levelCounts.size()>0){//did,dname,lid,lname,count
					
					for(Object[] obj:levelCounts){
						Long districtId=(Long)obj[0];
						SimpleVO districtVO=finalMap.get(districtId);
						if(districtVO==null){
							
							districtVO=finalMap.get(0l);
							SimpleVO levelVO=districtVO.getMap().get((Long)obj[2]);
							levelVO.setCount(levelVO.getCount()+(obj[4]!=null?(Long)obj[4]:0l));
						}else{
							
						  SimpleVO levelVO=districtVO.getMap().get((Long)obj[2]);
						  levelVO.setCount(obj[4]!=null?(Long)obj[4]:0l);
							
						}
					}
				}
				
			  	
				 //converting
				if(finalMap!=null && finalMap.size()>0){
					for (Map.Entry<Long, SimpleVO> entry : finalMap.entrySet())
			        {
						SimpleVO districtVO= entry.getValue();
			           if(districtVO.getMap()!=null && districtVO.getMap().size()>0){
			        	   districtVO.setSimpleVOList1( new ArrayList<SimpleVO>(districtVO.getMap().values()));
			        	   districtVO.getMap().clear();
			            }
			         }
					finalList=new ArrayList<SimpleVO>(finalMap.values());
					finalMap.clear();
				}
				
			}catch(Exception e){
				LOG.error(" Error Occured in getAttendedCountsByProgramOrCampOrBatch method in TraininingCampService class" ,e);
			}
			return finalList;
	  }
		public void preInstantiate(Map<Long,SimpleVO> finalMap,Long programId,Long campId,Long batchId){
			
			try{
			    
	             List<Object[]>	districts=null;
				
				if(programId!=null && batchId==null && campId==null){
					
					districts=trainingCampProgramDAO.getDistrictsByProgramId(programId);
					
				}else if(batchId==null && campId!=null){
					
				   districts=trainingCampDAO.getCampDistrictsByCampId(campId);
				}
				else if(batchId!=null){
					
				  if(campId!=null){
				    districts=trainingCampDAO.getCampDistrictsByCampId(campId);
				  }else{
				    districts=trainingCampBatchDAO.getCampDistrictsByBatchId(batchId);
				  }
					
				}
				
	            if(districts!=null && districts.size()>0){
				    for(Object[] obj:districts){
				    	
				    	SimpleVO districtVO=new SimpleVO();
				    	districtVO.setId((Long)obj[0]);
				    	districtVO.setName(obj[1]!=null?obj[1].toString():"");
				    	districtVO.setCount(0l);
				    	Map<Long,SimpleVO> levelMap=setlevelData();
				    	districtVO.setMap(levelMap);
				    	finalMap.put((Long)obj[0],districtVO);
				    }
				    SimpleVO districtVO=new SimpleVO();
			    	districtVO.setId(0l);
			    	districtVO.setName("Others");
			    	districtVO.setCount(0l);
			    	Map<Long,SimpleVO> levelMap=setlevelData();
			    	districtVO.setMap(levelMap);
			    	finalMap.put(0l,districtVO);
				}
				
			}catch(Exception e){
				LOG.error(" Error Occured in preInstantiate method in TraininingCampService class" ,e);
			}
		}
		
		public Map<Long,SimpleVO> setlevelData(){
			
			Map<Long,SimpleVO> map=new LinkedHashMap<Long,SimpleVO>();
			
			SimpleVO distVO=new SimpleVO();
			distVO.setName("district");
			distVO.setId(11l);
			distVO.setCount(0l);
			
			SimpleVO mandalVO=new SimpleVO();
			mandalVO.setName("mandal");
			mandalVO.setId(5l);
			mandalVO.setCount(0l);
			
			SimpleVO villageVO=new SimpleVO();
			villageVO.setName("village");
			villageVO.setId(6l);
			villageVO.setCount(0l);
			
			map.put(11l, distVO);
			map.put(5l, mandalVO);
			map.put(6l, villageVO);
			
			return map;
		}
		
		public String getAttendedlocWiseCountsByProgramOrCampOrBatch(Long programId,Long campId,Long batchId){
			
			String queryString=null;
			 try{
				
				 StringBuilder sb= new StringBuilder(); 
				 sb.append(" select d.districtId,d.districtName,count(distinct tc.tdpCadreId) " +
				 		    " from  TrainingCampAttendance tca,TdpCadre tc,District d " +
				 		    " where  tca.attendance.tdpCadreId=tc.tdpCadreId and tc.userAddress.district.districtId=d.districtId");
				 
				 if(batchId==null && campId==null && programId!=null ){
					 sb.append(" and tca.trainingCampProgramId=:programId");
				 }
				 else if(batchId==null && campId!=null){
					 sb.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					 if(programId!=null)
					   sb.append(" and tca.trainingCampProgramId=:programId");
				 }else if(batchId!=null){
						
					if(programId!=null)
					  sb.append(" and tca.trainingCampProgramId=:programId");
					if(campId!=null)
					  sb.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					
				    sb.append(" and tca.trainingCampBatchId=:batchId");
				 }
				 sb.append(" group by d.districtId order by d.districtName ");
				
				 queryString=sb.toString();
				 
			 }catch(Exception e){
				 LOG.error(" Error Occured in getAttendedlocWiseCountsByProgramOrCampOrBatch method in TraininingCampService class" ,e);
			 }
			 return queryString;
		}
		
		
		public String getCommiteesCadreCountByLoc(Long programId,Long campId,Long batchId){
			String queryString=null;
			try{
				
				StringBuilder sb=new StringBuilder();
				
				sb.append(" select " +
						   " model2.tdpCommitteeRole.tdpCommittee.district.districtId,model2.tdpCommitteeRole.tdpCommittee.district.districtName," +
						   " model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +
						   " count(distinct tca.attendance.tdpCadreId)");
				sb.append(" from TrainingCampAttendance tca,TdpCommitteeMember model2 " +
						   " where tca.attendance.tdpCadreId=model2.tdpCadreId and " +
						   "       model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (5,6,11) ");
				
				if(batchId==null && campId==null && programId!=null){
					sb.append(" and tca.trainingCampProgramId=:programId");
				}else if(batchId==null && campId!=null){
					sb.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					if(programId!=null)
						sb.append(" and tca.trainingCampProgramId=:programId");
				}else if(batchId!=null){
					
					if(programId!=null)
						sb.append(" and tca.trainingCampProgramId=:programId");
					if(campId!=null)
					   sb.append(" and tca.trainingCampSchedule.trainingCampId=:campId");
					
					sb.append(" and tca.trainingCampBatchId=:batchId");
					
				}
			   sb.append(" group by model2.tdpCommitteeRole.tdpCommittee.district.districtId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId " +
			 		    " order by FIELD(model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId , 6,5,11)");
			   queryString=sb.toString();
			}catch(Exception e){
				 LOG.error(" Error Occured in getCommiteesCadreCountByLoc method in TraininingCampService class" ,e);
			}
			return queryString;
		}
		
	 /**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the CadreCount for Batch By Date Wise.
	   *   inputs: batchId
	   *   Return:SimpleVO 
	   *   
	  */
		public SimpleVO getAttendedCountSummaryByBatch(Long batchId){
			SimpleVO simpleVO=new SimpleVO();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try{ 
				
				//Confirmed Count.
				 Long confirmedCount=trainingCampBatchAttendeeDAO.getConfirmedCountsByBatch(batchId);
				 //total count attended.
				 Long totalBatchCount=trainingCampAttendanceDAO.getAttendedCountByBatch(batchId);
				 String perc=null;
				 if(confirmedCount!=null && confirmedCount!=0l && totalBatchCount!=null){
					 float percentage = (totalBatchCount * 100/(float)confirmedCount);
					 perc=String.format("%.2f", percentage);
				 }
				 
				 //preinstantiate.
				  Object[] batchDates=trainingCampBatchDAO.getBatchDates(batchId);
				  Date fromDate=null;
				  Date toDate=null;
				  if(batchDates!=null){
					  fromDate=batchDates[0]!=null?(Date)batchDates[0]:null;
					  toDate=batchDates[1]!=null?(Date)batchDates[1]:null;
				  }
				  List<Date> dates=getBetweenDates(fromDate,toDate);
				  Map<Date,SimpleVO> finalMap=new LinkedHashMap<Date,SimpleVO>();
				  if(dates!=null && dates.size()>0){
					  int count=1;
					  for(Date date:dates){
						  SimpleVO vo=new SimpleVO();
						  vo.setDate(date);
						  vo.setDateString(sdf.format(date));
						  vo.setName("Day "+count);
						  vo.setTotal(0l);//attended
						  vo.setCount(confirmedCount);//absent
						  finalMap.put(date,vo);
						  count=count+1;
					  }
				  }
				 
				 //date wise counts.
				 List<Object[]> dateWiseCounts=trainingCampAttendanceDAO.getDateWiseCountsByBatch(batchId);
				 
				 if(dateWiseCounts!=null && dateWiseCounts.size()>0){
					 
					 for(Object[] obj: dateWiseCounts){
						 if(obj[0]!=null){
							SimpleVO vo= finalMap.get((Date)obj[0]);
							if(vo!=null){
								vo.setTotal(obj[1]!=null?(Long)obj[1]:0l);//attended.
								vo.setCount(confirmedCount-vo.getTotal());//absent.
							}
						 }
					 }
				 }
				 
			 simpleVO.setTotal(confirmedCount);//confirmed people	
			 simpleVO.setCount(totalBatchCount);//total attended.
			 simpleVO.setDateString(perc);
			//converting.
			 if(finalMap!=null && finalMap.size()>0){
				 simpleVO.setSimpleVOList1(new ArrayList<SimpleVO>(finalMap.values()));
			 } 
			 
			}catch(Exception e){
				 LOG.error(" Error Occured in getAttendedCountSummaryByBatch method in TraininingCampService class" ,e);
			}
			return simpleVO;
		}
		
		
		public List<Date> getBetweenDates(Date fromDate,Date toDate){
			
			List<Date> dates = new ArrayList<Date>(0);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			cal.add(Calendar.DATE, -1);
			
			while (cal.getTime().before(toDate)) {
			    cal.add(Calendar.DATE, 1);
			    dates.add(cal.getTime());
			}
			return dates;
		}
}
