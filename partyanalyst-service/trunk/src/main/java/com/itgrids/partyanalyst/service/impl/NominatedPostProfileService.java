package com.itgrids.partyanalyst.service.impl;


import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IApplicationDocumentDAO;
import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.IBoardDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IGovtOrderDAO;
import com.itgrids.partyanalyst.dao.IGovtOrderDocumentsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostAgeRangeDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationHistoryDAO;
import com.itgrids.partyanalyst.dao.INominatedPostCommentDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.dao.INominatedPostReferDetailsDAO;
import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.dao.ISchedulersInfoDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreReportDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCadreDAO;
import com.itgrids.partyanalyst.dao.hibernate.TehsilDAO;
import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreBasicPerformaceVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreEventsVO;
import com.itgrids.partyanalyst.dto.CadrePerformanceVO;
import com.itgrids.partyanalyst.dto.CadreStatsVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.GovtOrderVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationsVO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ApplicationDocument;
import com.itgrids.partyanalyst.model.GovtOrder;
import com.itgrids.partyanalyst.model.GovtOrderDocuments;
import com.itgrids.partyanalyst.model.NominatedPost;
import com.itgrids.partyanalyst.model.NominatedPostAgeRange;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.model.NominatedPostApplicationHistory;
import com.itgrids.partyanalyst.model.NominatedPostComment;
import com.itgrids.partyanalyst.model.NominatedPostFinal;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;
import com.itgrids.partyanalyst.model.NominatedPostReferDetails;
import com.itgrids.partyanalyst.model.NominationPostCandidate;
import com.itgrids.partyanalyst.model.SchedulersInfo;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.INominatedPostProfileService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;


public class NominatedPostProfileService implements INominatedPostProfileService{

	private final static Logger LOG =  Logger.getLogger(NominatedPostProfileService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private IUserAddressDAO userAddressDAO;
	private TransactionTemplate transactionTemplate = null;
	public IConstituencyDAO constituencyDAO;
	private IPanchayatDAO panchayatDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	private IBoardLevelDAO boardLevelDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService ;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IDepartmentsDAO departmentsDAO;
	private IDepartmentBoardDAO departmentBoardDAO;
	private IDepartmentBoardPositionDAO departmentBoardPositionDAO;
	private TehsilDAO tehsilDAO;
	private INominatedPostFinalDAO nominatedPostFinalDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private IApplicationStatusDAO applicationStatusDAO;
	private INominationPostCandidateDAO nominationPostCandidateDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private INominatedPostStatusDAO nominatedPostStatusDAO;
	private INominatedPostDAO nominatedPostDAO;
	private INominatedPostMemberDAO nominatedPostMemberDAO;
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private INominatedPostCommentDAO nominatedPostCommentDAO;
	
	private ICasteCategoryDAO casteCategoryDAO;
	private ITdpCadreReportDAO tdpCadreReportDAO;
	private INominatedPostReferDetailsDAO nominatedPostReferDetailsDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ActivityService					activityService;
	private IApplicationDocumentDAO         applicationDocumentDAO;
	private IVoterDAO         				voterDAO;
	 private INominatedPostApplicationHistoryDAO nominatedPostApplicationHistoryDAO;
	private ICasteStateDAO casteStateDAO;
	private ICadreCommitteeService cadreCommitteeService;
	private ICountryDAO countryDAO;
	private INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO;
	private IPositionDAO positionDAO;
	private IBoardDAO boardDAO;
	private IGovtOrderDAO govtOrderDAO;
	private IGovtOrderDocumentsDAO govtOrderDocumentsDAO;
	private INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IEventAttendeeDAO eventAttendeeDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO; 
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO; 
	private IAlertAssignedDAO alertAssignedDAO;
	private IAlertCandidateDAO alertCandidateDAO;
	private ICadreDetailsService cadreDetailsService;
	private ISchedulersInfoDAO schedulersInfoDAO;
	private ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO;   
	private IEventDAO eventDAO;
	    
	public void setSchedulersInfoDAO(ISchedulersInfoDAO schedulersInfoDAO) {
		this.schedulersInfoDAO = schedulersInfoDAO;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public IAlertCandidateDAO getAlertCandidateDAO() {
		return alertCandidateDAO;
	}

	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}

	public IAlertAssignedDAO getAlertAssignedDAO() {
		return alertAssignedDAO;
	}

	public void setAlertAssignedDAO(IAlertAssignedDAO alertAssignedDAO) {
		this.alertAssignedDAO = alertAssignedDAO;
	}

	public IPartyMeetingAttendanceDAO getPartyMeetingAttendanceDAO() {
		return partyMeetingAttendanceDAO;
	}

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}

	public IPartyMeetingInviteeDAO getPartyMeetingInviteeDAO() {
		return partyMeetingInviteeDAO;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public ITrainingCampBatchAttendeeDAO getTrainingCampBatchAttendeeDAO() {
		return trainingCampBatchAttendeeDAO;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}

	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}

	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}

	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}

	public INominatedPostGovtOrderDAO getNominatedPostGovtOrderDAO() {
		return nominatedPostGovtOrderDAO;
	}

	public void setNominatedPostGovtOrderDAO(
			INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO) {
		this.nominatedPostGovtOrderDAO = nominatedPostGovtOrderDAO;
	}

	public IGovtOrderDocumentsDAO getGovtOrderDocumentsDAO() {
		return govtOrderDocumentsDAO;
	}

	public void setGovtOrderDocumentsDAO(
			IGovtOrderDocumentsDAO govtOrderDocumentsDAO) {
		this.govtOrderDocumentsDAO = govtOrderDocumentsDAO;
	}

	public IGovtOrderDAO getGovtOrderDAO() {
		return govtOrderDAO;
	}

	public void setGovtOrderDAO(IGovtOrderDAO govtOrderDAO) {
		this.govtOrderDAO = govtOrderDAO;
	}

	public IBoardDAO getBoardDAO() {
		return boardDAO;
	}

	public void setBoardDAO(IBoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public IPositionDAO getPositionDAO() {
		return positionDAO;
	}

	public void setPositionDAO(IPositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}

	public INominatedPostAgeRangeDAO getNominatedPostAgeRangeDAO() {
		return nominatedPostAgeRangeDAO;
	}

	public void setNominatedPostAgeRangeDAO(
			INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO) {
		this.nominatedPostAgeRangeDAO = nominatedPostAgeRangeDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public INominatedPostApplicationHistoryDAO getNominatedPostApplicationHistoryDAO() {
		return nominatedPostApplicationHistoryDAO;
	}

	public void setNominatedPostApplicationHistoryDAO(
			INominatedPostApplicationHistoryDAO nominatedPostApplicationHistoryDAO) {
		this.nominatedPostApplicationHistoryDAO = nominatedPostApplicationHistoryDAO;
	}
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public IApplicationDocumentDAO getApplicationDocumentDAO() {
		return applicationDocumentDAO;
	}
	public void setApplicationDocumentDAO(
			IApplicationDocumentDAO applicationDocumentDAO) {
		this.applicationDocumentDAO = applicationDocumentDAO;
	}
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		activityService = activityService;
	}
	
	
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}

	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}

	public INominatedPostReferDetailsDAO getNominatedPostReferDetailsDAO() {
		return nominatedPostReferDetailsDAO;
	}

	public void setNominatedPostReferDetailsDAO(
			INominatedPostReferDetailsDAO nominatedPostReferDetailsDAO) {
		this.nominatedPostReferDetailsDAO = nominatedPostReferDetailsDAO;
	}

	public ITdpCadreReportDAO getTdpCadreReportDAO() {
		return tdpCadreReportDAO;
	}

	public void setTdpCadreReportDAO(ITdpCadreReportDAO tdpCadreReportDAO) {
		this.tdpCadreReportDAO = tdpCadreReportDAO;
	}

	public INominatedPostCommentDAO getNominatedPostCommentDAO() {
		return nominatedPostCommentDAO;
	}

	public void setNominatedPostCommentDAO(
			INominatedPostCommentDAO nominatedPostCommentDAO) {
		this.nominatedPostCommentDAO = nominatedPostCommentDAO;
	}

	
	public INominatedPostMemberDAO getNominatedPostMemberDAO() {
		return nominatedPostMemberDAO;
	}

	public void setNominatedPostMemberDAO(
			INominatedPostMemberDAO nominatedPostMemberDAO) {
		this.nominatedPostMemberDAO = nominatedPostMemberDAO;
	}

	public INominatedPostStatusDAO getNominatedPostStatusDAO() {
		return nominatedPostStatusDAO;
	}

	public void setNominatedPostStatusDAO(
			INominatedPostStatusDAO nominatedPostStatusDAO) {
		this.nominatedPostStatusDAO = nominatedPostStatusDAO;
	}

	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}
	
	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}

	public TehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(TehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IApplicationStatusDAO getApplicationStatusDAO() {
		return applicationStatusDAO;
	}

	public void setApplicationStatusDAO(IApplicationStatusDAO applicationStatusDAO) {
		this.applicationStatusDAO = applicationStatusDAO;
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

	public INominatedPostFinalDAO getNominatedPostFinalDAO() {
		return nominatedPostFinalDAO;
	}

	public void setNominatedPostFinalDAO(
			INominatedPostFinalDAO nominatedPostFinalDAO) {
		this.nominatedPostFinalDAO = nominatedPostFinalDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}

	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public INominationPostCandidateDAO getNominationPostCandidateDAO() {
		return nominationPostCandidateDAO;
	}

	public void setNominationPostCandidateDAO(
			INominationPostCandidateDAO nominationPostCandidateDAO) {
		this.nominationPostCandidateDAO = nominationPostCandidateDAO;
	}

	public IDepartmentsDAO getDepartmentsDAO() {
		return departmentsDAO;
	}

	public void setDepartmentsDAO(IDepartmentsDAO departmentsDAO) {
		this.departmentsDAO = departmentsDAO;
	}

	public IDepartmentBoardPositionDAO getDepartmentBoardPositionDAO() {
		return departmentBoardPositionDAO;
	}
	public void setDepartmentBoardPositionDAO(
			IDepartmentBoardPositionDAO departmentBoardPositionDAO) {
		this.departmentBoardPositionDAO = departmentBoardPositionDAO;
	}
	public IDepartmentBoardDAO getDepartmentBoardDAO() {
		return departmentBoardDAO;
	}
	public void setDepartmentBoardDAO(IDepartmentBoardDAO departmentBoardDAO) {
		this.departmentBoardDAO = departmentBoardDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}


	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public IBoardLevelDAO getBoardLevelDAO() {
		return boardLevelDAO;
	}
	public void setBoardLevelDAO(IBoardLevelDAO boardLevelDAO) {
		this.boardLevelDAO = boardLevelDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
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
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	
	public ITrainingCampCadreFeedbackDetailsDAO getTrainingCampCadreFeedbackDetailsDAO() {
		return trainingCampCadreFeedbackDetailsDAO;
	}

	public void setTrainingCampCadreFeedbackDetailsDAO(
			ITrainingCampCadreFeedbackDetailsDAO trainingCampCadreFeedbackDetailsDAO) {
		this.trainingCampCadreFeedbackDetailsDAO = trainingCampCadreFeedbackDetailsDAO;
	}
	
	public IEventDAO getEventDAO() {
		return eventDAO;
	}
	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	/**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 13, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All BoardLevels From Database }
	 */
	public List<IdNameVO> getBoardLevels(){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = boardLevelDAO.getBoardLevels();
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getBoardLevels()", e);
		}
		return returnList;
	}
	/**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 13, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All Departments From Database }
	 */
	public List<IdNameVO> getDepartments(Long postTpe,Long boardLevelId,Long searchLevelValue,Long seachLevelId,Long positionId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			Long applicationId =0L;
			List<Object[]> list =nominatedPostDAO.getBoardLevelWiseDepartments(postTpe,boardLevelId,searchLevelValue,seachLevelId,applicationId,positionId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"id","name"};
				returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getDepartments()", e);
		}
		return returnList;
	}

	/**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 13, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All DepartmentBoards From Database }
	 */
	public List<IdNameVO> getDepartmentBoard(Long depmtId,Long boardLevlId,Long searchLevelValue,Long seachLevelId,Long applicationId,Long positionId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			//Long applicationId =0L;
			List<Object[]> list = null;
			Object[] obj = null;
			if(applicationId == 0l){
			 list = nominatedPostDAO.getLevelWiseDepartmentsBoard(depmtId,boardLevlId,searchLevelValue,seachLevelId,applicationId);
			}else{
				obj = nominatedPostApplicationDAO.getBoardLevel(applicationId);
			}
			
			if(obj != null){
				list = nominatedPostDAO.getApllicationDepmtBoards(depmtId,(Long)obj[0],(Long)obj[1],positionId);
			}
			
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"id","name"};
				returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getDepartmentBoard()", e);
		}
		return returnList;
	}
	/**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 13, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All Departments From Database }
	 */
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId,Long boardLevlId,Long searchLevelValue,Long seachLevelId,Long nominatedPostCandId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		List<IdNameVO> appliedApplsList = new ArrayList<IdNameVO>();
		 
		try{
			Long applicationId =0L;
			List<Object[]> appliedPositions =null;
			List<Object[]> linkedPositions =null;
			List<Long> appliedApplctnIds = new ArrayList<Long>();
			List<Object[]> upadatedPositions =null;
			Map<Long,IdNameVO> aplliedAppltnMap = new HashMap<Long, IdNameVO>();
			List<Long> appliedPositionIds = new ArrayList<Long>();
			List<Long> updatedPositionIds = new ArrayList<Long>();
			List<Object[]> appltnIds = null;
			if(nominatedPostCandId != null && nominatedPostCandId.longValue() > 0l){
				 //appltnIds = nominatedPostApplicationDAO.getApplicationIdsByCAndidateId(nominatedPostCandId);
				appliedPositions = nominatedPostApplicationDAO.getAppliedPositionsForCandidate(deptId, boardId,boardLevlId,searchLevelValue,seachLevelId,nominatedPostCandId);
				if(commonMethodsUtilService.isListOrSetValid(appliedPositions)){
					for(Object[] appltnId : appliedPositions){
						IdNameVO vo = new IdNameVO();
						vo.setId((Long)appltnId[0] != null ? (Long)appltnId[0] : null);//positionId
						vo.setDistrictid(commonMethodsUtilService.getLongValueForObject(appltnId[1]));//applicationId
						aplliedAppltnMap.put(commonMethodsUtilService.getLongValueForObject(appltnId[1]), vo);
						appliedApplctnIds.add(commonMethodsUtilService.getLongValueForObject(appltnId[1]));
						//appliedPositionIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
						appliedApplsList.add(vo);
						}
					}
			}
			linkedPositions = nominatedPostFinalDAO.getLinkedPositions(deptId, boardId,boardLevlId,searchLevelValue,seachLevelId,nominatedPostCandId);
			if(commonMethodsUtilService.isListOrSetValid(linkedPositions)){
				for(Object[] appltnId : linkedPositions){
					IdNameVO vo = null;
					if(commonMethodsUtilService.isMapValid(aplliedAppltnMap)){
						 vo = aplliedAppltnMap.get(commonMethodsUtilService.getLongValueForObject(appltnId[1]));
						if(vo != null){
							if(vo.getId() == null){
								vo.setId(0l);//positionId
							}else{
								vo.setId(commonMethodsUtilService.getLongValueForObject(appltnId[0]));//positionId
							}
							
						}else{
							vo = new IdNameVO();
							vo.setId((Long)appltnId[0] != null ? (Long)appltnId[0] : null);//positionId
							vo.setDistrictid(commonMethodsUtilService.getLongValueForObject(appltnId[1]));//applicationId
							aplliedAppltnMap.put(commonMethodsUtilService.getLongValueForObject(appltnId[1]), vo);
							appliedApplctnIds.add(commonMethodsUtilService.getLongValueForObject(appltnId[1]));
							appliedApplsList.add(vo);
						}
					}/*else{
						vo = new IdNameVO();
						vo.setId((Long)appltnId[0] != null ? (Long)appltnId[0] : null);//positionId
						vo.setDistrictid(commonMethodsUtilService.getLongValueForObject(appltnId[1]));//applicationId
						aplliedAppltnMap.put(commonMethodsUtilService.getLongValueForObject(appltnId[1]), vo);
						appliedApplctnIds.add(commonMethodsUtilService.getLongValueForObject(appltnId[1]));
						//appliedPositionIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
						appliedApplsList.add(vo);
					}*/
					}
				}
			
			/*if(nominatedPostCandId != null && nominatedPostCandId.longValue() > 0l){
				if(commonMethodsUtilService.isListOrSetValid(appliedApplctnIds)){
				upadatedPositions = nominatedPostFinalDAO.getUpdatedPositionsForCandidate(appliedApplctnIds);
				if(commonMethodsUtilService.isListOrSetValid(upadatedPositions)){
					for(Object[] obj : upadatedPositions){
						if(commonMethodsUtilService.isMapValid(aplliedAppltnMap)){
						IdNameVO vo = aplliedAppltnMap.get(commonMethodsUtilService.getLongValueForObject(obj[1]));
						if(vo != null){
							if(vo.getId() == null || vo.getId() == 0l){
								vo.setId(0l);//positionId
							}else{
								vo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));//positionId
							}
							}
						 }
						}
					}
				}
			}*/
			if(commonMethodsUtilService.isListOrSetValid(appliedApplsList)){
				for(IdNameVO vo : appliedApplsList){
					appliedPositionIds.add(vo.getId());
					}
				}
			
				
			List<Object[]>  list = nominatedPostDAO.getLevelWiseDepartmentsBoardPosition(deptId, boardId,boardLevlId,searchLevelValue,seachLevelId,applicationId);
			List<Object[]> nonAppliedPostns = new ArrayList<Object[]>();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for(Object[] obj : list){
					if(commonMethodsUtilService.isListOrSetValid(appliedPositionIds)){
						if(!appliedPositionIds.contains((Long)obj[0])){
							nonAppliedPostns.add(obj);
						}
					}else{
						nonAppliedPostns.add(obj);
					}
				}
			}
			String positnAnyAppld = "NotApplied";
			if(commonMethodsUtilService.isListOrSetValid(appliedPositionIds)){
				for(Long position : appliedPositionIds){
					if(position == null){
						positnAnyAppld = "Applied";
					}
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(nonAppliedPostns)){
				String[] setterPropertiesList = {"id","name"};
				returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(nonAppliedPostns, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
			if(returnList !=  null && returnList.size()  >0){
			returnList.get(0).setStatus(positnAnyAppld);
			}else{
				if(positnAnyAppld.equalsIgnoreCase("NotApplied")){
					IdNameVO vo = new IdNameVO();
					 vo.setStatus(positnAnyAppld);
					returnList.add(vo);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getDepartmentBoardPositions()", e);
		}
		return returnList;
	}
	
	
	

	/**
	 * @Author  SRAVANTH
	 * @Version NominatedPostProfileService.java  July 15, 2016 02:50:00 PM 
	 * @return NomintedPostMemberVO
	 * description  { Getting All Applied Member Details From Database }
	 */
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,
			 Long positionId,String type,Long searchLevelId,Long applicationStatusId){
		NomintedPostMemberVO returnvo = new NomintedPostMemberVO();
		try {
			List<Long> tdpCadreIds = new ArrayList<Long>();
			List<NomintedPostMemberVO> subList = new ArrayList<NomintedPostMemberVO>();
			Set<Long> nominatedPostCandidateIds = new LinkedHashSet<Long>();
			Map<Long,String> partyPostionMap = new LinkedHashMap<Long, String>();
			Map<Long,Long> departmentsMap = new LinkedHashMap<Long, Long>();
			Map<Long,String> deptShortListedMap = new LinkedHashMap<Long, String>();
			Map<Long,List<IdNameVO>> reportMap = new LinkedHashMap<Long, List<IdNameVO>>();
			Map<Long,Long> referMap = new LinkedHashMap<Long, Long>();
			Map<Long,List<IdNameVO>> nomDocsMap = new LinkedHashMap<Long, List<IdNameVO>>();
			Map<Long,String> publicReprMap = new LinkedHashMap<Long, String>();
			//Long applicationStatusId=0L;
			
			Set<Long> applicationIds = new LinkedHashSet<Long>();//Sandeep
			
			//0.nominationPostCandidateId,1.tdpCadreId,2.voterId,3.candidateName,4.mobileNo,5.cadreFirstname,6.cadreMobileNo,7.age,
						//8.caste,9.subCaste,10.casteName,11.applicationStatusId,12.status,13.nominatedPostId
			List<Object[]> list = nominatedPostFinalDAO.getNominatedPostMemberDetails(levelId, levelValue, departmentId, boardId, positionId, type,searchLevelId,applicationStatusId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"nominatedPostCandidateId","tdpCadreId","voterId","voterName","voterMoblie","age",
							"caste","subCaste","casteName","applStatusId","status","nominatePostApplicationId","boardLevelId","levelValue","imageURL",
									"cadreName","cadreMobile","cadreAge","candCaste","candCasteName","gender","cadreGen"};
				subList = (List<NomintedPostMemberVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.NomintedPostMemberVO");
			}
			
			if(commonMethodsUtilService.isListOrSetValid(subList)){
				for (NomintedPostMemberVO vo : subList) {
					Long cadreId = vo.getTdpCadreId();
					Long nominatedPostCandidateId = vo.getNominatedPostCandidateId();
					Long applicationCandidateId =  vo.getNominatePostApplicationId();
					if(cadreId != null && cadreId.longValue() > 0l){
						tdpCadreIds.add(cadreId);
					}
					if(nominatedPostCandidateId != null && nominatedPostCandidateId.longValue() > 0l){
						nominatedPostCandidateIds.add(nominatedPostCandidateId);
					}
					if(applicationCandidateId !=null && applicationCandidateId.longValue()>0l){
						applicationIds.add(applicationCandidateId);
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(nominatedPostCandidateIds)){
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getAnyAppliedDepartmentsCountForCandidateList(nominatedPostCandidateIds,departmentId,boardId,applicationStatusId);
				if(commonMethodsUtilService.isListOrSetValid(totalDepartments)){
					for (Object[] obj : totalDepartments) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						departmentsMap.put(id, count);
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 Long count = departmentsMap.get(vo.getNominatedPostCandidateId());
							 vo.setOtherDepartmentsCount(count);
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(nominatedPostCandidateIds)){
				List<Long> otherDeptShortListed = nominatedPostFinalDAO.getAnyShortlistedDepartmentsForCandidateList(nominatedPostCandidateIds,departmentId,boardId,applicationStatusId);
				if(commonMethodsUtilService.isListOrSetValid(otherDeptShortListed)){
					for (Long id : otherDeptShortListed) {
						//deptShortListedMap.put(id, "YES");
						deptShortListedMap.put(id, String.valueOf(otherDeptShortListed.size()));
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 String status = deptShortListedMap.get(vo.getNominatedPostCandidateId());
							 vo.setOtherDeptShortListed(status);
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(tdpCadreIds)){
				List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(partyPositionDetails)){
					 for (Object[] obj : partyPositionDetails) {
						 
						 String level = obj[0] != null ? obj[0].toString() : "" ;
						 String role = obj[1] != null ? obj[1].toString() : "";
						 Long cadreId = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
						 String state = commonMethodsUtilService.getStringValueForObject(obj[6]);
						 String commiteestr = obj[2] != null ? obj[2].toString() : "";
						 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
						 {
							 level = state+" "+level;
						 }
						 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
						 partyPostionMap.put(cadreId, partyPositionStr);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 String postion = partyPostionMap.get(vo.getTdpCadreId());
							 vo.setPartyPosition(postion);
						 }
					}
				 }
				 
				 List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(cadrePublicRepresentativList)){
					 for (Object[] obj : cadrePublicRepresentativList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						String pubRep = obj[3] != null ? obj[3].toString():"";
						publicReprMap.put(id, pubRep);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 String pubReprStr = publicReprMap.get(vo.getTdpCadreId());
							 vo.setPublicReprStr(pubReprStr);
						 }
					}
				 }
				 
				 List<Object[]> reportsList = tdpCadreReportDAO.getCadreReportDetailsByCadreList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(reportsList)){
					 for (Object[] obj : reportsList) {
						Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						List<IdNameVO> voList = reportMap.get(cadreId);
						if(voList != null && voList.size() > 0){
							IdNameVO vo = new IdNameVO();
							vo.setId(cadreId);
							vo.setName(obj[1] != null ? obj[1].toString():"");//reportType
							vo.setOrderId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));//statusId
							vo.setStatus(obj[3] != null ? obj[3].toString():"");//status
							vo.setMobileNo(obj[4] != null ? obj[4].toString():"");//reportPath
							voList.add(vo);
						}
						else{
							voList = new ArrayList<IdNameVO>();
							IdNameVO vo = new IdNameVO();
							vo.setId(cadreId);
							vo.setName(obj[1] != null ? obj[1].toString():"");//reportType
							vo.setOrderId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));//statusId
							vo.setStatus(obj[3] != null ? obj[3].toString():"");//status
							vo.setMobileNo(obj[4] != null ? obj[4].toString():"");//reportPath
							voList.add(vo);
							reportMap.put(cadreId, voList);
						}
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 List<IdNameVO> voList = reportMap.get(vo.getTdpCadreId());
							 vo.setIdNamevoList(voList);
						 }
					}
				 }
			}
			 
			if(commonMethodsUtilService.isListOrSetValid(applicationIds)){
				List<Object[]> referList = nominatedPostReferDetailsDAO.getReferedCountForCandidateList(applicationIds);
				 if(commonMethodsUtilService.isListOrSetValid(referList)){
					 for (Object[] obj : referList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						referMap.put(id, count);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 Long count = referMap.get(vo.getNominatePostApplicationId());
						 vo.setReferCandCount(count);
					 }
				 }
				 
				 List<Object[]> nomDocsList = applicationDocumentDAO.getNominatedPostDocumentDetails(applicationIds);
				 if(commonMethodsUtilService.isListOrSetValid(nomDocsList)){
					 for (Object[] obj : nomDocsList) {
						Long candId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						String filePath = obj[1] != null ? obj[1].toString():"";
						Long applciationId = commonMethodsUtilService.getLongValueForObject(obj[2]);
								
						List<IdNameVO> voList = nomDocsMap.get(applciationId);
						if(voList != null && voList.size() > 0){
							IdNameVO vo = new IdNameVO();
							vo.setStatus("NP-Profile");
							vo.setMobileNo(filePath);
							voList.add(vo);
						}
						else{
							voList = new ArrayList<IdNameVO>();
							IdNameVO vo = new IdNameVO();
							vo.setStatus("NP-Profile");
							vo.setMobileNo(filePath);
							voList.add(vo);
							nomDocsMap.put(applciationId, voList);
						}
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO nomintedPostMemberVO : subList) {
						List<IdNameVO> voList = nomDocsMap.get(nomintedPostMemberVO.getNominatePostApplicationId());
						nomintedPostMemberVO.setNomDocsList(voList);
					}
				 }
			}
			
			
			
			//Applied And ShortlistedCounts Of candidate
			if(commonMethodsUtilService.isListOrSetValid(nominatedPostCandidateIds)){				
				Map<Long,Long> appliedCandidates = new HashMap<Long, Long>();
				Map<Long,Long> shortListedCandidates = new HashMap<Long, Long>();
				
				List<Object[]> appliedCountOfCandidate = nominatedPostApplicationDAO.getApplicationDetailsOfCandidate(nominatedPostCandidateIds);	
				
				setStatusWiseCountsMap(appliedCountOfCandidate,appliedCandidates);
				
				List<Object[]> shortlistedCountCandidate= nominatedPostFinalDAO.getShortlistedApplicationDetailsOfCandidate(nominatedPostCandidateIds);
				setStatusWiseCountsMap(shortlistedCountCandidate,shortListedCandidates);
				
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					for (NomintedPostMemberVO obj : subList) {						
						if(obj.getNominatedPostCandidateId() !=null){							
							if(commonMethodsUtilService.isMapValid(appliedCandidates)){								
								obj.setAppliedCount(appliedCandidates.get(obj.getNominatedPostCandidateId()) !=null ? appliedCandidates.get(obj.getNominatedPostCandidateId()).longValue():0l);
							}
							if(commonMethodsUtilService.isMapValid(shortListedCandidates)){
								obj.setShortListedCount(shortListedCandidates.get(obj.getNominatedPostCandidateId()) !=null ? shortListedCandidates.get(obj.getNominatedPostCandidateId()).longValue():0l);
							}
						}						
					}
				}				
			}
			
			
			returnvo.setSubList(subList);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostMemberDetails()", e);
		}
		return returnvo;
	}
	
	public Map<Long,Long> setStatusWiseCountsMap(List<Object[]> appliedCountOfCandidate,Map<Long,Long> appliedCandidates){
		
		try{
			if(commonMethodsUtilService.isListOrSetValid(appliedCountOfCandidate)){					
				for (Object[] obj : appliedCountOfCandidate) {
					if(obj[0] !=null){
						appliedCandidates.put((Long)obj[0], (Long)obj[1]);
					}						
				}					
			}
		}catch (Exception e) {
			LOG.error("Exception Occured in setStatusWiseCountsMap()", e);
		}
		return appliedCandidates;
	}
	
	public List<IdNameVO> getReferCadreDetailsForCandidate(Long applicationId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try {
			List<Long> cadreIds = new ArrayList<Long>();
			Map<Long,String> publicRepMap = new LinkedHashMap<Long, String>();
			Map<Long,String> partyPostMap = new LinkedHashMap<Long, String>();
			Map<Long,Long> referedCount = new LinkedHashMap<Long, Long>();
			
			List<Object[]> list = nominatedPostReferDetailsDAO.getReferedCadreDetailsForCandidate(applicationId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					
					IdNameVO vo = new IdNameVO();
					vo.setId(id);
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setPercentage(obj[2] != null ? obj[2].toString():"");//membershipNo
					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
					vo.setStatus(obj[4] != null ? obj[4].toString():"");//image
					returnList.add(vo);
					cadreIds.add(id);
				}
			}
			
			List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(cadreIds);
			if(commonMethodsUtilService.isListOrSetValid(publicRepDertails)){
				for(Object[] obj:publicRepDertails){
					Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String type = obj[2] != null ? obj[2].toString():"";
					String position = publicRepMap.get(cadreId);
					if(position != null && position.trim().length() > 0l){
						position = position+" , "+type;
					}
					else{
						position = type;						
					}
					publicRepMap.put(cadreId, position);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for (IdNameVO vo : returnList) {
					vo.setPublicRepr(publicRepMap.get(vo.getId()));
				}
			}
			
			 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIds);
			 if(commonMethodsUtilService.isListOrSetValid(partyPositionDetails)){
				 for (Object[] obj : partyPositionDetails) {
					 
					 String level = obj[0] != null ? obj[0].toString() : "" ;
					 String role = obj[1] != null ? obj[1].toString() : "";
					 Long cadreId = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					 String state = commonMethodsUtilService.getStringValueForObject(obj[6]);
					 String commiteestr = obj[2] != null ? obj[2].toString() : "";
					 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					 {
						 level = state+" "+level;
					 }
					 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
					 partyPostMap.put(cadreId, partyPositionStr);
				}
			 }
			 if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for (IdNameVO vo : returnList) {
					vo.setPartyPos(partyPostMap.get(vo.getId()));
				}
			}
			 
			List<Object[]> referedCountList = nominatedPostReferDetailsDAO.getReferedCandidatesCountForCandidate(cadreIds);
			if(commonMethodsUtilService.isListOrSetValid(referedCountList)){
				for (Object[] obj : referedCountList) {
					Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					referedCount.put(cadreId, count);
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for (IdNameVO vo : returnList) {
					vo.setCount(referedCount.get(vo.getId()));
				}
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostMemberDetails()", e);
		}
		return returnList;
	}
	
	public NomintedPostMemberVO getMatchedVO(List<NomintedPostMemberVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(NomintedPostMemberVO vo : returnList)
			{
				if(vo.getTdpCadreId().longValue()== id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public NomintedPostMemberVO getMatchedVOByCandidate(List<NomintedPostMemberVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(NomintedPostMemberVO vo : returnList)
			{
				if(vo.getNominatedPostCandidateId().longValue()== id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateApplicationStatusDetails(final Long userId,final Long nominatePostApplicationId,final Long statusId,final String comment,final Long levelId,
								final Long levelValue,final Long deptId,final Long boardId,final Long positionId,final Long candidateId){
		String status = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					//Long nominatedPostFinalId = nominatedPostFinalDAO.getNominatedPostFinalDetails(nominatedPostId, nominatedPostCandidateId);
					//if(nominatedPostFinalId != null && nominatedPostFinalId.longValue() > 0l){
					List<NominatedPostFinal> nomianationPostFinalList = nominatedPostFinalDAO.getNominatedPostApplicationDetailsByApplciationId(nominatePostApplicationId);
					NominatedPostFinal nominatedPostFinal = null;
					Long nominatedPostMemberId = 0L;
					if(commonMethodsUtilService.isListOrSetValid(nomianationPostFinalList)){
						nominatedPostFinal = nomianationPostFinalList.get(0);
						nominatedPostMemberId = nominatedPostFinal.getNominatedPostMemberId();
					}
					
					if(nominatedPostFinal == null){
						nominatedPostFinal = new NominatedPostFinal();
						nominatedPostFinal.setNominationPostCandidateId(candidateId);
						nominatedPostFinal.setInsertedBy(userId);
						nominatedPostFinal.setNominatedPostApplicationId(nominatePostApplicationId);
						nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						if(statusId != null && (statusId.longValue() == 3L || statusId.longValue() == 5L ||  statusId.longValue() == 6L ||  statusId.longValue() == 7L)){
							nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
							if(nominatedPostFinal != null && nominatedPostMemberId.longValue() > 0l){
								List<NominatedPost> nominatedPostObjList = nominatedPostDAO.getNominatedPostDetailsByNominatedPostMember(nominatedPostMemberId);
								if(commonMethodsUtilService.isListOrSetValid(nominatedPostObjList))
									nominatedPostFinal.setNominatedPostId(nominatedPostObjList.get(0).getNominatedPostId());
								nominatedPostFinal.setNominatedPostMemberId(nominatedPostMemberId);
							}
						}
					}
					
						nominatedPostFinal.setApplicationStatusId(statusId);
						nominatedPostFinal.setUpdatedBy(userId);
						nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostFinal.setIsDeleted("N");
						nominatedPostFinal.setIsExpired("N");
						nominatedPostFinal.setIsPrefered("N");
						
						nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
						
						NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(nominatePostApplicationId);
						 
						savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
						if(statusId != null && (statusId.longValue() == 3L || statusId.longValue() == 5L ||  statusId.longValue() == 6L ||  statusId.longValue() == 7L)){
							nominatedPostApplication.setNominatedPostMemberId(nominatedPostMemberId);
						}
						nominatedPostApplication.setApplicationStatusId(statusId);
						nominatedPostApplication.setUpdatedBy(userId);
						nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
						
						NominatedPostComment nominatedPostComment = new NominatedPostComment();
						
						nominatedPostComment.setNominatedPostApplicationId(nominatePostApplicationId);
						nominatedPostComment.setRemarks(comment);
						nominatedPostComment.setInsertedBy(userId);
						nominatedPostComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostComment = nominatedPostCommentDAO.save(nominatedPostComment);
					//}
						
				}
			});
			status = "success";
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
			LOG.error("Exception Occured in updateApplicationStatusDetails()", e);
		}
		return status;
	}
	
	public void savingNominatedPostApplicationHistoryDetails(final NominatedPostApplication nominatedPostApplication,final Long statusId,final Long userId){
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					NominatedPostApplicationHistory nominatedPostApplicationHistory = new NominatedPostApplicationHistory();
					
					nominatedPostApplicationHistory.setTrackedTime(dateUtilService.getCurrentDateAndTime());
					nominatedPostApplicationHistory.setNominatedPostApplicationId(nominatedPostApplication.getNominatedPostApplicationId());
					nominatedPostApplicationHistory.setNominationPostCandidateId(nominatedPostApplication.getNominationPostCandidateId());
					nominatedPostApplicationHistory.setDepartmentId(nominatedPostApplication.getDepartmentId());
					nominatedPostApplicationHistory.setBoardId(nominatedPostApplication.getBoardId());
					nominatedPostApplicationHistory.setPositionId(nominatedPostApplication.getPositionId());
					nominatedPostApplicationHistory.setBoardLevelId(nominatedPostApplication.getBoardLevelId());
					nominatedPostApplicationHistory.setLocationValue(nominatedPostApplication.getLocationValue());
					if(statusId != null && statusId.longValue() > 0l && userId != null && userId.longValue() > 0l){
					nominatedPostApplicationHistory.setApplicationStatusId(statusId);
					nominatedPostApplicationHistory.setUpdatedBy(userId.longValue());
					nominatedPostApplicationHistory.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					}else{
					nominatedPostApplicationHistory.setApplicationStatusId(nominatedPostApplication.getApplicationStatusId());
					nominatedPostApplicationHistory.setUpdatedBy(nominatedPostApplication.getUpdatedBy());
					nominatedPostApplicationHistory.setUpdatedTime(nominatedPostApplication.getUpdatedTime());
					}
					nominatedPostApplicationHistory.setInsertedBy(nominatedPostApplication.getInsertedBy());
					nominatedPostApplicationHistory.setInsertedTime(nominatedPostApplication.getInsertedTime());
					nominatedPostApplicationHistory.setIsDeleted(nominatedPostApplication.getIsDeleted());
					
					nominatedPostApplicationHistory = nominatedPostApplicationHistoryDAO.save(nominatedPostApplicationHistory);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in savingNominatedPostApplicationHistoryDetails()", e);
		}
	}
	
	/**
	 * @Author  HYMAVATHI
	 * @Version NominatedPostProfileService.java  July 15, 2016 11:50:00 AM 
	 * @return ResultStatus
	 * description  { Saving Nominated Post Prifile Application Candidate into Database }
	 */
	public ResultStatus savingNominatedPostProfileApplication(final NominatedPostVO nominatedPostVO,final Long loggerUserId,final Map<File,String> mapfiles){
		final ResultStatus status = new ResultStatus ();
		
		try{
			
			 transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					NominationPostCandidate nominationPostCandidate = new NominationPostCandidate();
					NominatedPostApplication nominatedPostApplication = null;
					
					Long nominatedCandiPostId= null;
					Long voterId = null;
					
					if(nominatedPostVO.getNominatedCandId() == null && nominatedPostVO.getId() != null && nominatedPostVO.getId().longValue() > 0l)
					{
						voterId = tdpCadreDAO.getVoterIdByTdpCadreId(nominatedPostVO.getId().longValue());
						nominationPostCandidate.setVoterId(voterId != null ? voterId : null);
						nominationPostCandidate.setTdpCadreId(nominatedPostVO.getId());
						List<Object[]> cadreDetails = tdpCadreDAO.getApplicationMemberDetails(nominatedPostVO.getId(),nominatedPostVO.getCandidateType());
						if(cadreDetails != null && cadreDetails.size() >0){
							for(Object[] cadre : cadreDetails){
								
								String fnamr = cadre[12] != null ? cadre[12].toString() : "";
								String lastnaem = cadre[13] != null ? cadre[13].toString() : "";
								nominationPostCandidate.setCandidateName(fnamr + " " + lastnaem) ;	
								
								nominationPostCandidate.setMobileNo(commonMethodsUtilService.getStringValueForObject(cadre[0]));
								nominationPostCandidate.setHouseno(commonMethodsUtilService.getStringValueForObject(cadre[1]));
								nominationPostCandidate.setGender(commonMethodsUtilService.getStringValueForObject(cadre[16]));
								
								List<NominatedPostAgeRange> nominatedPostAgeRanges = nominatedPostAgeRangeDAO.getAll();
								Long nominatedPostAgeRangeId =1L;
								Long age = commonMethodsUtilService.getLongValueForObject(cadre[17]);
								
								if(commonMethodsUtilService.isListOrSetValid(nominatedPostAgeRanges)){
									for (NominatedPostAgeRange rangevo : nominatedPostAgeRanges) 
										if(rangevo.getMinValue().longValue()>= age && rangevo.getMaxValue().longValue() <= age.longValue())
											nominatedPostAgeRangeId = rangevo.getNominatedPostAgeRangeId();
								}
								
								nominationPostCandidate.setAge(age);
								nominationPostCandidate.setNominatedPostAgeRangeId(nominatedPostAgeRangeId);
								
								nominationPostCandidate.setDob(commonMethodsUtilService.getStringValueForObject(cadre[18]));
								nominationPostCandidate.setRelativename(commonMethodsUtilService.getStringValueForObject(cadre[14]));
								nominationPostCandidate.setRelativetype(commonMethodsUtilService.getStringValueForObject(cadre[15]));
								nominationPostCandidate.setImageurl(commonMethodsUtilService.getStringValueForObject(cadre[19]));
								nominationPostCandidate.setCastestateId(commonMethodsUtilService.getLongValueForObject(cadre[20]));
								
								//New OR Replica, UserAddress Object created For NominatedPost Candidate By using Clone()
								UserAddress newAddress =null;
								UserAddress userAddress = (UserAddress) cadre[21];//userAddressDAO.get(commonMethodsUtilService.getLongValueForObject(cadre[21]));
								if(userAddress !=null){
									UserAddress address = new UserAddress();									
									try {
										address = (UserAddress)userAddress.clone();
									} catch (CloneNotSupportedException e) {
										LOG.error("Exception Occured at UserAddress Cloning in savingNominatedPostProfileApplication()", e);
									}
									if(address !=null){
										address.setUserAddressId(null);
										address.setCountry(countryDAO.get(1L));
										
										if(address.getDistrict() !=null &&address.getDistrict().getDistrictId() !=null && ((address.getDistrict().getDistrictId() >=11) && (address.getDistrict().getDistrictId()<=23))){
											address.setState(stateDAO.get(1l));
										}else if(address.getDistrict() !=null &&address.getDistrict().getDistrictId() !=null && ((address.getDistrict().getDistrictId() >=1) && (address.getDistrict().getDistrictId()<=10))){
											address.setState(stateDAO.get(36l));
										}										
										newAddress = userAddressDAO.save(address);
									}
									
								}
								if(newAddress !=null){
									nominationPostCandidate.setAddress(newAddress);
									nominationPostCandidate.setAddressId(newAddress.getUserAddressId());
								}
								
								nominationPostCandidate.setInsertedBy(loggerUserId);
								nominationPostCandidate.setUpdatedBy(loggerUserId);
								nominationPostCandidate.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								nominationPostCandidate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								nominationPostCandidate.setIsDeleted("N");
								nominationPostCandidate = nominationPostCandidateDAO.save(nominationPostCandidate);
								nominatedCandiPostId = nominationPostCandidate.getNominationPostCandidateId();
							}
						}
					}else {
						nominatedCandiPostId = nominatedPostVO.getNominatedCandId();
					}
					
					//Address Change Scenario
					
					if(nominatedCandiPostId !=null && nominatedCandiPostId.longValue()>0l && nominatedPostVO.getIsCheckedMigrateAddressField() != null && nominatedPostVO.getIsCheckedMigrateAddressField().equalsIgnoreCase("true")){						
						NominationPostCandidate tc = nominationPostCandidateDAO.getUserAddressByCandidate(nominatedCandiPostId);//tdpCadreId						
						//UserAddress UA = new UserAddress();					
						UserAddress UA = null;					
						if(tc !=null){ 
							 UA = userAddressDAO.get(tc.getAddressId());
							 tc.setMobileNo(nominatedPostVO.getPhoneNumName());
							 tc.setHouseno(nominatedPostVO.getHouseNumberName());
						}
						
						if(UA !=null){
							UA.setHouseNo(nominatedPostVO.getHouseNumberName() != null?nominatedPostVO.getHouseNumberName().toString():null);
							UA.setAddressLane1(nominatedPostVO.getAddressLane1Name() != null?nominatedPostVO.getAddressLane1Name().toString():null);
							UA.setAddressLane2(nominatedPostVO.getAddressLane2Name() != null?nominatedPostVO.getAddressLane2Name():null);
							UA.setPinCode(nominatedPostVO.getAddPincodeName() != null?nominatedPostVO.getAddPincodeName():null);
							UA.setState(nominatedPostVO.getAddStateName() != null  && nominatedPostVO.getAddStateName().longValue()>0L ?stateDAO.get(nominatedPostVO.getAddStateName()):null);
							UA.setDistrict(nominatedPostVO.getAddDistrictName() != null && nominatedPostVO.getAddDistrictName().longValue()>0L ?districtDAO.get(nominatedPostVO.getAddDistrictName()):null);
							UA.setConstituency(nominatedPostVO.getAddConstituencyName() != null  && nominatedPostVO.getAddConstituencyName().longValue()>0L  ?constituencyDAO.get(nominatedPostVO.getAddConstituencyName()):null);
							
							if(nominatedPostVO.getAddMandalsName() !=null && nominatedPostVO.getAddMandalsName().longValue()>0l){
								char value = nominatedPostVO.getAddMandalsName().toString().charAt(0);
								Long temp = Long.parseLong(value+"");
								if(temp !=null && temp==2L){ 
									UA.setTehsil(tehsilDAO.get(Long.parseLong(nominatedPostVO.getAddMandalsName().toString().substring(1))));
								}else if(temp !=null && temp==1L){
									UA.setLocalElectionBody(localElectionBodyDAO.get(Long.parseLong(nominatedPostVO.getAddMandalsName().toString().substring(1))));
								}						
							}						
							//UA.setTehsil(nominatedPostVO.getMandalId() != null?tehsilDAO.get(nominatedPostVO.getMandalId()):null);
							
							if(nominatedPostVO.getAddVillageName() !=null && nominatedPostVO.getAddVillageName().longValue()>0l){
								char value = nominatedPostVO.getAddVillageName().toString().charAt(0);
								Long temp = Long.parseLong(value+"");
								
								if(temp !=null && temp.longValue()==1L){
									UA.setPanchayat(panchayatDAO.get(Long.parseLong(nominatedPostVO.getAddVillageName().toString().substring(1))));
									UA.setPanchayatId(Long.parseLong(nominatedPostVO.getAddVillageName().toString().substring(1)));
								}else if(temp !=null && temp==2L){
									UA.setWard(constituencyDAO.get(Long.parseLong(nominatedPostVO.getAddVillageName().toString().substring(1))));
								}
								
							}
							UA.setCountry(countryDAO.get(1L));
							userAddressDAO.save(UA);	
						}										
					}
					
					
					
					
					if(nominatedPostVO.getNominatdList() != null && !nominatedPostVO.getNominatdList().isEmpty() ){
						
						for(NominatedPostVO Vo : nominatedPostVO.getNominatdList()){
							if(Vo != null ){
							String[] positnArr = null ;
							String positions = Vo.getPositions();
							if(positions != null && positions.length() > 0){
								positnArr = new String[0];
								 positnArr = positions.split(",");
							}
							
								if(positnArr != null && positnArr.length >0){
									for(String position : positnArr){
										nominatedPostApplication = saveNominatedPostApplication(Vo,nominatedCandiPostId,position.trim(),loggerUserId,mapfiles);
									}
								}else{
									nominatedPostApplication = saveNominatedPostApplication(Vo,nominatedCandiPostId,null,loggerUserId,mapfiles);
								}
							}
						
						}
				}
					
					if(nominatedPostVO.getRefferCadreIds() != null && nominatedPostVO.getRefferCadreIds().length() >0){
						String[] refferalCadreIdArr = null ;
						String refferalCadreIds =nominatedPostVO.getRefferCadreIds();
						if(refferalCadreIds != null && refferalCadreIds.length() > 0){
							refferalCadreIdArr = new String[0];
							refferalCadreIdArr = refferalCadreIds.split(",");
						}
						
							if(refferalCadreIdArr != null && refferalCadreIdArr.length >0){
								for(String cadreId: refferalCadreIdArr){
										NominatedPostReferDetails NPRD = new NominatedPostReferDetails();
										NPRD.setNominationPostCandidateId(nominatedCandiPostId);
										NPRD.setReferCadreId(Long.parseLong(cadreId.trim()));
										NPRD.setInsertedBy(loggerUserId);
										NPRD.setUpdatedBy(loggerUserId);
										NPRD.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										NPRD.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										NPRD.setNominatedPostApplicationId(nominatedPostApplication.getNominatedPostApplicationId());
										NPRD.setIsDeleted("N");
										nominatedPostReferDetailsDAO.save(NPRD);
								}
							}
					}
					
					status.setResultCode(0);
					status.setMessage("SUCCESS - "+nominationPostCandidate.getNominationPostCandidateId());
				return status;
		}
	});
}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in savingNominatedPostProfileApplication()", e);
		status.setMessage("FAIL");
		status.setResultCode(1);
	}
		return status;
	}
	/**
	 * @Author  HYMAVATHI
	 * @Version NominatedPostProfileService.java  July 15, 2016 11:50:00 AM 
	 * @return void
	 * description  { Saving Nominated Post Prifile Application Candidate into Database for Each Position}
	 */
	public NominatedPostApplication saveNominatedPostApplication(NominatedPostVO Vo ,Long nominatedPostCandi,String position,final Long loggerUserId,final Map<File,String> mapfiles){
		NominatedPostApplication nominatedPostApplication = null;
		try{
			
		nominatedPostApplication = new NominatedPostApplication();
		
				
		nominatedPostApplication.setNominationPostCandidateId(nominatedPostCandi != null ? nominatedPostCandi : null);
		
		if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 1){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(1l) ;
		}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 2){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(Vo.getStateId() != null ? Vo.getStateId() : null) ;
		}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 3){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(Vo.getDistrictId() != null ? Vo.getDistrictId() : null) ;
		}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 4){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(Vo.getConstituencyId() != null ? Vo.getConstituencyId() : null) ;
		}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 5 || Vo.getBoardLevelId().longValue() == 6){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(Vo.getMandalId() != null ? Long.parseLong(Vo.getMandalId().toString().trim().substring(1)) : null) ;
		}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 7){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
			nominatedPostApplication.setLocationValue(Vo.getPanchayatId() != null ? Long.parseLong(Vo.getPanchayatId().toString().trim().substring(1)) : null) ;
		}
		
		UserAddress nominatedPostAddress = new UserAddress();
		if(Vo.getStateId() != null && Vo.getStateId().longValue()>0L)
			nominatedPostAddress.setState(stateDAO.get(Vo.getStateId().longValue()));
		if(Vo.getDistrictId() != null && Vo.getDistrictId().longValue()>0L)
			nominatedPostAddress.setDistrict(districtDAO.get(Vo.getDistrictId().longValue()));
		if(Vo.getConstituencyId() != null && Vo.getConstituencyId().longValue()>0L)
			nominatedPostAddress.setConstituency(constituencyDAO.get(Vo.getConstituencyId()));
		if(Vo.getMandalId() != null && Vo.getMandalId().longValue()>0L){
			char value =Vo.getMandalId().toString().charAt(0);
			Long temp = Long.parseLong(value+"");
			if(temp !=null && temp==2L){ 
				nominatedPostAddress.setTehsil(tehsilDAO.get(Long.parseLong(Vo.getMandalId().toString().substring(1))));
			}else if(temp !=null && temp==1L){
				nominatedPostAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.parseLong(Vo.getMandalId().toString().substring(1))));
			}						
		}
		if(Vo.getPanchayatId() != null && Vo.getPanchayatId().longValue()>0L){
			char value = Vo.getPanchayatId().toString().charAt(0);
			Long temp = Long.parseLong(value+"");
			
			if(temp !=null && temp.longValue()==1L){
				nominatedPostAddress.setPanchayat(panchayatDAO.get(Long.parseLong(Vo.getPanchayatId().toString().substring(1))));
				nominatedPostAddress.setPanchayatId(Long.parseLong(Vo.getPanchayatId().toString().substring(1)));
			}else if(temp !=null && temp==2L){
				nominatedPostAddress.setWard(constituencyDAO.get(Long.parseLong(Vo.getPanchayatId().toString().substring(1))));
			}
		}
		nominatedPostAddress.setCountry(countryDAO.get(1L));
		nominatedPostAddress = userAddressDAO.save(nominatedPostAddress);
		
		boolean isMemberAvailable = false;
		if(Vo.getDeptId() !=null && Vo.getDeptId() >0){
			nominatedPostApplication.setDepartmentId(Vo.getDeptId());
			 isMemberAvailable = true;
		}
		else
			isMemberAvailable = false;
		if(Vo.getDeptBoardId() !=null && Vo.getDeptBoardId()>0){
			nominatedPostApplication.setBoardId(Vo.getDeptBoardId());
			isMemberAvailable = true;
		}
		else
			isMemberAvailable = false;
		
		if(position !=null && !position.isEmpty() && !position.trim().equalsIgnoreCase("0")){
			nominatedPostApplication.setPositionId(Long.parseLong(position.trim())) ;
			isMemberAvailable = true;
		}
		else
			isMemberAvailable = false;
		//(Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId){
		if(isMemberAvailable){
			Long nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(nominatedPostApplication.getBoardLevelId(),nominatedPostApplication.getLocationValue(),
					nominatedPostApplication.getDepartmentId(),nominatedPostApplication.getBoardId(),nominatedPostApplication.getPositionId());
			if(nominatedPostMemberId != null && nominatedPostMemberId.longValue()>0L)
				nominatedPostApplication.setNominatedPostMemberId(nominatedPostMemberId);
		}
		
	/*	nominatedPostApplication.setDepartmentId(Vo.getDeptId() != null ? Vo.getDeptId() : null) ;
		nominatedPostApplication.setBoardId(Vo.getDeptBoardId() != null ? Vo.getDeptBoardId() : null) ;*/
		nominatedPostApplication.setPostTypeId(Vo.getPostTypeId() != null ? Vo.getPostTypeId() : null);
		nominatedPostApplication.setInsertedBy(loggerUserId);
		nominatedPostApplication.setUpdatedBy(loggerUserId);
		nominatedPostApplication.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		
		nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		nominatedPostApplication.setIsDeleted("N");
		nominatedPostApplication.setIsExpired("N");
		nominatedPostApplication.setApplicationStatusId(1l);
		nominatedPostApplication.setAddressId(nominatedPostAddress.getUserAddressId());
		Long nominatedPostApplicationId =nominatedPostApplicationDAO.nominatedPostAppPostionDetails(nominatedPostApplication.getBoardLevelId(),nominatedPostApplication.getLocationValue(),nominatedPostApplication.getDepartmentId(),nominatedPostApplication.getBoardId(),nominatedPostApplication.getPositionId(),nominatedPostApplication.getPostTypeId(),nominatedPostApplication.getNominationPostCandidateId(),nominatedPostApplication.getApplicationStatusId());
		if(nominatedPostApplicationId == null)
		nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
		saveApplicationDocuments(nominatedPostApplication.getNominatedPostApplicationId(),nominatedPostCandi,mapfiles);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in saveNominatedPostApplication()", e);
		}
		return nominatedPostApplication;
	}
	public void saveApplicationDocuments(Long applctnId,Long candId,final Map<File,String> mapfiles){
		
		try{
		String folderName = folderCreation();
		ApplicationDocument applicationDocument = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		 //int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		
		 StringBuilder pathBuilder = null;
		 StringBuilder str ;
		 
			
		 for (Map.Entry<File, String> entry : mapfiles.entrySet())
		 {
			 pathBuilder = new StringBuilder();
			 str = new StringBuilder();
			 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
				
			 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
			 .append(entry.getValue());
			 str.append(randomNumber).append(".").append(entry.getValue());
			 activityService = new ActivityService();
			String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
			 
				if(fileCpyStts.equalsIgnoreCase("error")){
					//status.setResultCode(ResultCodeMapper.FAILURE);
					LOG.error(" Exception Raise in copying file");
					throw new ArithmeticException();
				}
				
				applicationDocument = new ApplicationDocument();
				applicationDocument.setFilePath(pathBuilder.toString());
				applicationDocument.setNominationPostCandidateId(candId != null ? candId : null);
				applicationDocument.setNominatedPostApplicationId(applctnId != null ? applctnId : null);
				applicationDocument.setInsertedDate(dateUtilService.getCurrentDateAndTime());
				applicationDocument.setIsDeleted("N");
				applicationDocument = applicationDocumentDAO.save(applicationDocument);
				
		 }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in saveNominatedPostApplication()", e);
			//status.setMessage("FAIL");
			//status.setResultCode(1);
		}
		
	}
	/**
	 * @Author  Teja
	 * @Version NominatedPostProfileService.java  July 15, 2016 02:50:00 PM 
	 * @return NomintedPostVO
	 */
	

	public String savechangeAddressForNominatedPost(final NominatedPostVO nominatedPostVO){
		LOG.info("Entered into the savechangeAddressForNominatedPost service method");
		String status = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					
					
					NominationPostCandidate tc = nominationPostCandidateDAO.getUserAddressByCandidate(nominatedPostVO.getNominatedCandId());//tdpCadreId
					
					//UserAddress UA = new UserAddress();					
					UserAddress UA = null;					
					if(tc !=null){
						 UA = tc.getAddress();
						 tc.setMobileNo(nominatedPostVO.getMobileNo());
					}
					
					UA.setHouseNo(nominatedPostVO.getHno() != null?nominatedPostVO.getHno():null);
					UA.setAddressLane1(nominatedPostVO.getAddress1() != null?nominatedPostVO.getAddress1():null);
					UA.setAddressLane2(nominatedPostVO.getAddress2() != null?nominatedPostVO.getAddress2():null);
					UA.setPinCode(nominatedPostVO.getPincode() != null?nominatedPostVO.getPincode():null);
					UA.setState(nominatedPostVO.getStateId() != null?stateDAO.get(nominatedPostVO.getStateId()):null);
					UA.setDistrict(nominatedPostVO.getDistrictId() != null?districtDAO.get(nominatedPostVO.getDistrictId()):null);
					UA.setConstituency(nominatedPostVO.getConstituencyId() != null?constituencyDAO.get(nominatedPostVO.getConstituencyId()):null);
					
					if(nominatedPostVO.getMandalId() !=null && nominatedPostVO.getMandalId().longValue()>0l){
						char value = nominatedPostVO.getMandalId().toString().charAt(0);
						Long temp = Long.parseLong(value+"");
						if(temp !=null && temp==4){
							UA.setTehsil(tehsilDAO.get(Long.parseLong(nominatedPostVO.getMandalId().toString().substring(1))));
						}else if(temp !=null && temp==5){
							UA.setLocalElectionBody(localElectionBodyDAO.get(Long.parseLong(nominatedPostVO.getMandalId().toString().substring(1))));
						}else if(temp !=null && temp==6){
							UA.setWard(constituencyDAO.get(Long.parseLong(nominatedPostVO.getMandalId().toString().substring(1))));
						}
						
					}
					
					//UA.setTehsil(nominatedPostVO.getMandalId() != null?tehsilDAO.get(nominatedPostVO.getMandalId()):null);
					
					if(nominatedPostVO.getPanchayatId() !=null && nominatedPostVO.getPanchayatId().longValue()>0l){
						char value = nominatedPostVO.getPanchayatId().toString().charAt(0);
						Long temp = Long.parseLong(value+"");
						
						if(temp !=null && temp==7){
							UA.setPanchayat(panchayatDAO.get(Long.parseLong(nominatedPostVO.getPanchayatId().toString().substring(1))));
						}else if(temp !=null && temp==8){
							UA.setWard(constituencyDAO.get(Long.parseLong(nominatedPostVO.getPanchayatId().toString().substring(1))));
						}
						
					}
					//UA.setPanchayatId(nominatedPostVO.getPanchayatId() != null?nominatedPostVO.getPanchayatId():null);
					UA.setCountry(countryDAO.get(1L));
					UserAddress presentAddress =  userAddressDAO.save(UA);
					
					/*TdpCadre tc = tdpCadreDAO.get(nominatedPostVO.getId());//tdpCadreId
					if(tc != null){
						tc.setPresentAddress(presentAddress);
						tc.setMobileNo(nominatedPostVO.getMobileNo());
						tdpCadreDAO.save(tc);
					}*/
					
					
					/*if(tc != null){
						tc.setPresentAddress(presentAddress);
						tc.setMobileNo(nominatedPostVO.getMobileNo());
						tdpCadreDAO.save(tc);
					}*/
					
					
					
				}
			});
			status = "success";
		} catch (Exception e) {
			status = "failure";
			Log.error("Exception Occured in savechangeAddressForNominatedPost method in NominatedPostProfileService ",e);
		}
		return status;
	}
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId,String searchType){
		List<NominatedPostVO> appMembersList = new ArrayList<NominatedPostVO>();
		NominatedPostVO vo = null;
		 try {			
				 
				List<Object[]> memberDetails =  tdpCadreDAO.getApplicationMemberDetails(tdpCadreId,searchType);
				if(memberDetails != null && memberDetails.size() >0){
				for(Object[] obj : memberDetails){
					vo = new NominatedPostVO();
					vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(obj[0]));
					vo.setHno(commonMethodsUtilService.getStringValueForObject(obj[1]));
					vo.setAddress1(commonMethodsUtilService.getStringValueForObject(obj[2]));
					vo.setAddress2(commonMethodsUtilService.getStringValueForObject(obj[3]));
					vo.setStateId(commonMethodsUtilService.getLongValueForObject(obj[4]));
					
					UserAddress address =  (obj[21] != null ?(UserAddress) obj[21]:null);
					if(address != null){
						vo.setDistrictId(address.getDistrict() != null ? address.getDistrict().getDistrictId():0L);
						if(vo.getDistrictId()!= null && vo.getDistrictId() > 0){
							List<Object[]> distList = districtDAO.getDistrictsWithNewSplitted(address.getState() != null ? address.getState().getStateId():0L);
							if(distList != null && distList.size() > 0){
								for (Object[] objects : distList) {
									IdNameVO voIn = new IdNameVO();
									voIn.setId((Long)objects[0]);
									voIn.setName(objects[1].toString());
									vo.getDistList().add(voIn);
								}
							}
						}
						
						vo.setConstituencyId(address.getConstituency() != null? address.getConstituency().getConstituencyId():0L);
						//const
						if(vo.getConstituencyId()!= null && vo.getConstituencyId() > 0){
							List<Object[]> constList = constituencyDAO.getConstituenciesByDistrictId(vo.getDistrictId());
							if(constList != null && constList.size() > 0){
								for (Object[] objects : constList) {
									IdNameVO voCon = new IdNameVO();
									voCon.setId((Long)objects[0]);
									voCon.setName(objects[1].toString());
									vo.getConsList().add(voCon);
								}
							}
						}
					}
					
					//District
					/*vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[5]));
					if(vo.getDistrictId()!= null && vo.getDistrictId() > 0){
						List<Object[]> distList = districtDAO.getDistrictsWithNewSplitted(vo.getStateId());
						if(distList != null && distList.size() > 0){
							for (Object[] objects : distList) {
								IdNameVO voIn = new IdNameVO();
								voIn.setId((Long)objects[0]);
								voIn.setName(objects[1].toString());
								vo.getDistList().add(voIn);
							}
						}
					}*/
					/*vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(obj[6]));
					//const
					if(vo.getConstituencyId()!= null && vo.getConstituencyId() > 0){
						List<Object[]> constList = constituencyDAO.getConstituenciesByDistrictId(vo.getDistrictId());
						if(constList != null && constList.size() > 0){
							for (Object[] objects : constList) {
								IdNameVO voCon = new IdNameVO();
								voCon.setId((Long)objects[0]);
								voCon.setName(objects[1].toString());
								vo.getConsList().add(voCon);
							}
						}
					}*/
					//vo.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[7])); 
					
					if(obj[7] != null)//mandal
						vo.setMandalId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(obj[7])));
					else if(obj[20] != null)//localelectionBody
						vo.setMandalId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(obj[20])));
					
					if(vo.getMandalId()!= null && vo.getMandalId() > 0){
						List<Long> constituencyIds = new ArrayList<Long>(0);
						if(vo.getConstituencyId() != null && vo.getConstituencyId().longValue() > 0l){
						constituencyIds.add(vo.getConstituencyId());
						}
						List<LocationWiseBoothDetailsVO> list = null;
						if(constituencyIds != null && constituencyIds.size() > 0){
						 list = cadreCommitteeService.getMandalMunicCorpDetailsOfConstituencies(constituencyIds);
						}
						
						if(commonMethodsUtilService.isListOrSetValid(list))
							for (LocationWiseBoothDetailsVO vo1 : list) {
								String digit = vo1.getLocationId().toString().charAt(0)+"";
								Long id = vo1.getLocationId();
								if(digit.trim().equalsIgnoreCase("4")){
									String locatnId = vo1.getLocationId().toString().substring(1);
									id = Long.valueOf("2"+locatnId);
								}
								else if(digit.trim().equalsIgnoreCase("5") || digit.trim().equalsIgnoreCase("6")){
									String locatnId = vo1.getLocationId().toString().substring(1);
									id = Long.valueOf("1"+locatnId);
								}
								vo.getMandalsList().add(new IdNameVO(id,vo1.getLocationName()));
							}
							
						
						/*List<Object[]> manList = tehsilDAO.getMandalsForConstituencyId(vo.getConstituencyId());
						if(manList != null && manList.size() > 0){
							for (Object[] objects : manList) {
								IdNameVO voMan = new IdNameVO();
								voMan.setId((Long)objects[0]);
								voMan.setName(objects[1].toString());
								vo.getMandalsList().add(voMan);
							}
						}*/
					}
					if(obj[8] != null)
						vo.setPanchayatId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(obj[8])));
					else if(obj[22] != null)
						vo.setPanchayatId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(obj[22])));
					//Panchayats
					//if(vo.getPanchayatId()!= null && vo.getPanchayatId() > 0){
						List<Long>  locationIds = new ArrayList<Long>(0);
						locationIds.add(commonMethodsUtilService.getLongValueForObject(obj[7]));
						
						List<Long>  lebsList = new ArrayList<Long>(0);
						lebsList.add(commonMethodsUtilService.getLongValueForObject(obj[22]));
						
						
						List<LocationWiseBoothDetailsVO> list = cadreCommitteeService.getPanchayatWardDivisionDetailsOfSubLocation(null, locationIds, lebsList);
						if(commonMethodsUtilService.isListOrSetValid(list))
							for (LocationWiseBoothDetailsVO vo1 : list) {
								String digit = vo1.getLocationId().toString().charAt(0)+"";
								Long id =vo1.getLocationId();
								if(digit.trim().equalsIgnoreCase("7")){
									String locatnId = vo1.getLocationId().toString().substring(1);
									id = Long.valueOf("1"+locatnId);
								}
								else if(digit.trim().equalsIgnoreCase("8")){
									String locatnId = vo1.getLocationId().toString().substring(1);
									id = Long.valueOf("2"+locatnId);
								}
								vo.getPanList().add(new IdNameVO(id,vo1.getLocationName()));
							}
							
						/*List<Object[]> panList = panchayatDAO.getPanchayatsByTehsilId(vo.getMandalId());
						if(panList != null && panList.size() > 0){
							for (Object[] objects : panList) {
								IdNameVO voPan = new IdNameVO();
								voPan.setId((Long)objects[0]);
								voPan.setName(objects[1].toString());
								vo.getPanList().add(voPan);
							}
						}*/
					//}
					vo.setPincode(commonMethodsUtilService.getStringValueForObject(obj[9]));
					
					appMembersList.add(vo);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getApplicantDetailsForMember in NominatedPostProfileService",e);
		}
		 return appMembersList;
	 } 
	public List<IdNameVO> getDistrictsForState(Long stateId){
		List<IdNameVO> finaleVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> distList = districtDAO.getAllDistrictDetails(stateId);
			
			if(distList != null && distList.size() > 0){
				for (Object[] objects : distList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finaleVoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getDistrictsForState", e);
		}
		return finaleVoList;
	}
	public List<IdNameVO> getVillagesForMandalId(Long mandalId){
		List<IdNameVO> finaleVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> manList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
			
			if(manList != null && manList.size() > 0){
				for (Object[] objects : manList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finaleVoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getDistrictsForState", e);
		}
		return finaleVoList;
	}	
	
	/**
	 * @author Srishailam Pittala
	 * @version 15th July, 2016
	 * @return List<NominatedPostVO>
	 * description { getting overall nominated posts status details in all levels}
	 * 
	 * */
	
	public List<NominatedPostVO> getNominatedPostsStatusDetailsInAllLevels(Long levelId,String startDateStr, String endDateStr,Long stateId){
		List<NominatedPostVO> returnList = null;
		try {

			//{"TOTAL AVAILABLE","APPLICATIONS NOT RECIEVED","READY TO SHORT LIST","RUNNING","READY FOR FINAL REVIEW","FINALYZED","GO ISSUED / COMPLETED","TOTAL"}
			Map<String,NominatedPostVO> applicationsStatusDtlsMap = new LinkedHashMap<String,NominatedPostVO>(0);
			String[] applicationStatusArr = IConstants.NOMINATED_POST_APPLICATION_STATUSES;
			if(applicationStatusArr != null && applicationStatusArr.length>0)
				for (int i = 0; i < applicationStatusArr.length; i++) 
					applicationsStatusDtlsMap.put(applicationStatusArr[i].trim(),  new NominatedPostVO(Long.valueOf(String.valueOf(i)),applicationStatusArr[i]) );
			
			List<Object[]> totalAvailablePostsList = nominatedPostDAO.getTotalAvaiablePostDetails(levelId,null,null,stateId,"Total");
			List<Object[]> totalAvailableApplicationsList = nominatedPostApplicationDAO.getTotalAvaiableApplicationsDetails(levelId,stateId,0L);
			Map<Long,Long> applciationCountMap = new HashMap<Long, Long>(0);
			Long totalApplcationsCount = 0L;
			if(commonMethodsUtilService.isListOrSetValid(totalAvailableApplicationsList)){
				for (Object[] param : totalAvailableApplicationsList){
					applciationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					totalApplcationsCount = totalApplcationsCount+commonMethodsUtilService.getLongValueForObject(param[1]);
				}
			}
			
			Long totalPositionsCount=0L;
			Long totalCorpCount=0L;
			if(commonMethodsUtilService.isListOrSetValid(totalAvailablePostsList)){
				for (Object[] param : totalAvailablePostsList) {
					NominatedPostVO vo = applicationsStatusDtlsMap.get("TOTAL");
					if(vo != null){
						if(vo.getTotalPositions() == null || vo.getTotalPositions().longValue() == 0L)
							vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
						else
							vo.setTotalPositions(vo.getTotalPositions()+commonMethodsUtilService.getLongValueForObject(param[3]));
						vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
						vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
						vo.setTotalApplicationReceivedCnt(totalApplcationsCount);
						
						totalPositionsCount = vo.getTotalPositions();
						totalCorpCount = vo.getTotalCorp();
					}
				}
			}
			
			List<Object[]> totalDeptsNCorpNDesig = nominatedPostDAO.getTotalCorpIdsAndBoardsIdsAndPositionsIds(levelId,2L,stateId,null);
			Map<String,Long> totalPostMemeberCountMap = new HashMap<String, Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
				for (Object[] param : totalDeptsNCorpNDesig) {
					Long maxCount = commonMethodsUtilService.getLongValueForObject(param[0]);
					totalPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), maxCount);
				}
			}
			
			
			NominatedPostVO vo2 = applicationsStatusDtlsMap.get("APPLICATIONS NOT RECIEVED".trim());
			if(vo2 != null){
				if(levelId != null && levelId.longValue()>0L){						
					
					List<Object[]> totalAppliedDeptsNCorpNDesig = nominatedPostApplicationDAO.getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(levelId,2L,stateId);
					Map<String,Long> appliedPostMemeberCountMap = new HashMap<String, Long>(0);
					if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
						for (Object[] param : totalAppliedDeptsNCorpNDesig) {
							Long count = commonMethodsUtilService.getLongValueForObject(param[0]);
							if(appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim()) != null){
								Long meberIdsCount=appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim());
								if(meberIdsCount != null)
									count = meberIdsCount+count;
							}
							appliedPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), count);
						}
					}
					
					Long totalavailableCount = 0L;
					if(commonMethodsUtilService.isMapValid(totalPostMemeberCountMap)){
						for (String DCP : totalPostMemeberCountMap.keySet()) {
							Long maxPositionsCount = totalPostMemeberCountMap.get(DCP) != null?totalPostMemeberCountMap.get(DCP):0L;
							Long appliedCount = appliedPostMemeberCountMap.get(DCP) != null?appliedPostMemeberCountMap.get(DCP):0L;
							
							if(maxPositionsCount>appliedCount){
								totalavailableCount = totalavailableCount+ (maxPositionsCount-appliedCount);
							}
						}
					}
							
					List<Long> totalDeptIsList = nominatedPostDAO.getTotalDeptsCount(levelId);
					List<Long> applRecievedDeptIsList = nominatedPostDAO.getTotalApplicationsDeptsCount(levelId);
					List<Long> applRecievedAnyBoardsDeptIdsList = nominatedPostDAO.getTotalApplicationsDeptsCountforAnyBoards(levelId);
					List<Long> remainingDeptIdsList = new ArrayList<Long>(0);
					
					if(commonMethodsUtilService.isListOrSetValid(totalDeptIsList) && 
							 commonMethodsUtilService.isListOrSetValid(applRecievedDeptIsList)){
						if(commonMethodsUtilService.isListOrSetValid(applRecievedAnyBoardsDeptIdsList))
							applRecievedDeptIsList.addAll(applRecievedAnyBoardsDeptIdsList);
						
							for (Long deptIid : totalDeptIsList) {
								if(!applRecievedDeptIsList.contains(deptIid))
									remainingDeptIdsList.add(deptIid);
							}
					}
					
					
					List<Object[]> totalCorpsIdsList = nominatedPostDAO.getTotalCorpsIdsCount(levelId);
					List<Object[]> applRecievedCorpsIdList = nominatedPostDAO.getTotalApplicationsCorpsIdsCount(levelId);
					List<Object[]> applRecievedCorpsIdandAnyPostList = nominatedPostDAO.getTotalApplicationsCorpsIdsForAnyPostsCount(levelId);
					
					
					Set<String> totalCorpList = new HashSet<String>(0);
					Set<String> applnRecvdCorpList = new HashSet<String>(0);
					Set<String> remainingCorpsIdsList = new HashSet<String>(0);
					if(commonMethodsUtilService.isListOrSetValid(totalCorpsIdsList)){
						for (Object[] param : totalCorpsIdsList) {
							totalCorpList.add(commonMethodsUtilService.getStringValueForObject(param[0]+"-"+commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(applRecievedCorpsIdList)){
						for (Object[] param : applRecievedCorpsIdList) {
							applnRecvdCorpList.add(commonMethodsUtilService.getStringValueForObject(param[0]+"-"+commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(applRecievedCorpsIdandAnyPostList)){
						for (Object[] param : applRecievedCorpsIdandAnyPostList) {
							applnRecvdCorpList.add(commonMethodsUtilService.getStringValueForObject(param[0]+"-"+commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(totalCorpList)){
						for (String deptCorpStr : totalCorpList) {
							if(!applnRecvdCorpList.contains(deptCorpStr))
								remainingCorpsIdsList.add(deptCorpStr);
						}
					}
					
					vo2.setTotalPositions(totalavailableCount);
					vo2.setTotalCorp(Long.valueOf(String.valueOf(remainingCorpsIdsList.size())));
					vo2.setTotalDept(Long.valueOf(String.valueOf(remainingDeptIdsList.size())));
					
					if(totalavailableCount != null && totalavailableCount.longValue()>0L){
						if(vo2.getTotalPositions() != null && vo2.getTotalPositions().longValue()>0L){
							String perc = String.valueOf(vo2.getTotalPositions()*100.0/totalPositionsCount);
							vo2.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
						}
						
						if(vo2.getTotalCorp() != null && vo2.getTotalCorp().longValue()>0L){
							String perc1 = String.valueOf(vo2.getTotalCorp()*100.0/totalCorpCount);
							vo2.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
						}
					}
				}
			}
			
			List<Long> statusList = nominatedPostStatusDAO.getStatusIdsList();
			List<Object[]> levelWiseAvailablePostsList = nominatedPostDAO.getAvaiablePostDetails(levelId,null,null,statusList,stateId);
			Map<Long,Map<String,Long>> movedPostsStatusDetailsMap = new HashMap<Long, Map<String,Long>>(0);
			Map<String,List<Long>> statusWiseDeptMap = new HashMap<String, List<Long>>(0);
			Map<String,List<Long>> statusWisecorporationsMap = new HashMap<String, List<Long>>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(levelWiseAvailablePostsList)){
				for (Object[] param : levelWiseAvailablePostsList) {
					String statusStr = commonMethodsUtilService.getStringValueForObject(param[1]);
					Long memberId = commonMethodsUtilService.getLongValueForObject(param[7]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[3]);
					Long deptId = commonMethodsUtilService.getLongValueForObject(param[8]);
					Long boardId = commonMethodsUtilService.getLongValueForObject(param[9]);
					//System.out.println(memberId +" "+count);
						Map<String,Long> posionwiseMovedMap = new HashMap<String, Long>(0);
						 if(statusStr.trim().equalsIgnoreCase("2")){// nominatedPostStatusid
							NominatedPostVO vo = applicationsStatusDtlsMap.get("READY FOR FINAL REVIEW".trim());
							if(vo != null){
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("READY FOR FINAL REVIEW") != null)
										filledCount = posionwiseMovedMap.get("READY FOR FINAL REVIEW");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("READY FOR FINAL REVIEW", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
								
								vo.setTotalPositions(vo.getTotalPositions()+count);
								
								Long deptCount=commonMethodsUtilService.getLongValueForObject(param[4]);
								Long corpCount=commonMethodsUtilService.getLongValueForObject(param[6]);
								
								List<Long> deptList =statusWiseDeptMap.get("2");
								List<Long> corpList = statusWisecorporationsMap.get("2");
								
								if(deptList == null)
									deptList = new ArrayList<Long>(0);
								if(corpList == null)
									corpList = new ArrayList<Long>(0);
								
								if(!deptList.contains(deptId)){
									vo.setTotalDept(vo.getTotalDept()+deptCount);
									deptList.add(deptId);
								}
								if(!corpList.contains(boardId)){
									vo.setTotalCorp(vo.getTotalCorp()+corpCount);
									corpList.add(boardId);
								}
								
								statusWiseDeptMap.put("2",deptList) ;
								statusWisecorporationsMap.put("2",corpList);
								
								vo.setTotalApplicationReceivedCnt(applciationCountMap.get(6L) != null?applciationCountMap.get(6L):0L); // ApplicationStatusId for ready to final review
								
								if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
									if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
										String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
										vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
									}
									
									if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
										String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
										vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
									}
								}
							}
						}
						else if(statusStr.trim().equalsIgnoreCase("3")){// nominatedPostStatusid
							NominatedPostVO vo = applicationsStatusDtlsMap.get("FINALIZED".trim());
							if(vo != null){
								
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("FINALIZED") != null)
										filledCount = posionwiseMovedMap.get("FINALIZED");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("FINALIZED", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
								
								vo.setTotalPositions(vo.getTotalPositions()+count);
								Long deptCount=commonMethodsUtilService.getLongValueForObject(param[4]);
								Long corpCount=commonMethodsUtilService.getLongValueForObject(param[6]);
								
								List<Long> deptList =statusWiseDeptMap.get("3");
								List<Long> corpList = statusWisecorporationsMap.get("3");
								
								if(deptList == null)
									deptList = new ArrayList<Long>(0);
								if(corpList == null)
									corpList = new ArrayList<Long>(0);
								
								if(!deptList.contains(deptId)){
									vo.setTotalDept(vo.getTotalDept()+deptCount);
									deptList.add(deptId);
								}
								if(!corpList.contains(boardId)){
									vo.setTotalCorp(vo.getTotalCorp()+corpCount);
									corpList.add(boardId);
								}
								
								statusWiseDeptMap.put("3",deptList) ;
								statusWisecorporationsMap.put("3",corpList);
								
								vo.setTotalApplicationReceivedCnt(applciationCountMap.get(5L) != null?applciationCountMap.get(5L):0L); // ApplicationStatusId for Confirmed/finalyszed
								
								if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
									if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
										String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
										vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
									}
									
									if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
										String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
										vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
									}
								}
							}
						}
						else if(statusStr.trim().equalsIgnoreCase("4")){//nominatedPostStatusid
							NominatedPostVO vo = applicationsStatusDtlsMap.get("GO ISSUED / COMPLETED".trim());
							if(vo != null){
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("GO ISSUED / COMPLETED") != null)
										filledCount = posionwiseMovedMap.get("GO ISSUED / COMPLETED");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("GO ISSUED / COMPLETED", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
								
								vo.setTotalPositions(vo.getTotalPositions()+count);
								Long deptCount=commonMethodsUtilService.getLongValueForObject(param[4]);
								Long corpCount=commonMethodsUtilService.getLongValueForObject(param[6]);
								
								List<Long> deptList =statusWiseDeptMap.get("4");
								List<Long> corpList = statusWisecorporationsMap.get("4");
								
								if(deptList == null)
									deptList = new ArrayList<Long>(0);
								if(corpList == null)
									corpList = new ArrayList<Long>(0);
								
								if(!deptList.contains(deptId)){
									vo.setTotalDept(vo.getTotalDept()+deptCount);
									deptList.add(deptId);
								}
								if(!corpList.contains(boardId)){
									vo.setTotalCorp(vo.getTotalCorp()+corpCount);
									corpList.add(boardId);
								}
								
								statusWiseDeptMap.put("4",deptList) ;
								statusWisecorporationsMap.put("4",corpList);
								vo.setTotalApplicationReceivedCnt(applciationCountMap.get(7L)); // ApplicationStatusId for GO Issue
								
								if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
									if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
										String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
										vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
									}
									
									if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
										String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
										vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
									}
								}
							}
						}
				}
			}
			
			Map<Long,Long> runningPostsMap = new HashMap<Long, Long>();
			
			List<Object[]> levelPostCountList = nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDetalsNew(levelId,null,null,stateId,"running");	
			Long runningStatusPostsCount =0L;
			if(commonMethodsUtilService.isListOrSetValid(levelPostCountList)){
				for (Object[] param : levelPostCountList) {
					
					Long applinCount  =  commonMethodsUtilService.getLongValueForObject(param[2]);
					Long memberId  =  commonMethodsUtilService.getLongValueForObject(param[3]);
					
						Map<String,Long> movedPostsStatusMap = movedPostsStatusDetailsMap.get(memberId) != null ? movedPostsStatusDetailsMap.get(memberId):new HashMap<String, Long>(0);
						
						Long readyToFinalReviewPostsCount = movedPostsStatusMap.get("READY FOR FINAL REVIEW") != null?movedPostsStatusMap.get("READY FOR FINAL REVIEW"):0L;
						Long finalyzedPostsCount = movedPostsStatusMap.get("FINALIZED") != null?movedPostsStatusMap.get("FINALIZED"):0L;
						Long G_O_passedPostsCount = movedPostsStatusMap.get("GO ISSUED / COMPLETED") != null?movedPostsStatusMap.get("GO ISSUED / COMPLETED"):0L;
						
						Long almostFilledPosts = readyToFinalReviewPostsCount+finalyzedPostsCount+G_O_passedPostsCount;
						Long maxPostsCount = totalPostMemeberCountMap.get(memberId.toString().trim()) != null ?  totalPostMemeberCountMap.get(memberId.toString().trim()) :0L ;
						Long runningPostsCount =0L;
						
						if(maxPostsCount>almostFilledPosts){// available posts are there
							Long openPostsCount = maxPostsCount-almostFilledPosts;
							if(openPostsCount>applinCount){// applications count less than open posts
								runningPostsCount = applinCount;
							}else if(openPostsCount<=applinCount){// open posts count less than applications count
								runningPostsCount = openPostsCount;
							}
						}
						runningStatusPostsCount = runningStatusPostsCount+runningPostsCount;
						runningPostsMap.put(memberId, runningPostsCount);
				}
			}
			
			List<Object[]> levelWiseRunningApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsRunningAppliedApplicationsDtals(levelId,null,null,stateId);
			if(commonMethodsUtilService.isListOrSetValid(levelWiseRunningApplicatinStatusDetailsList)){
				for (Object[] param : levelWiseRunningApplicatinStatusDetailsList) {
					
					Long applicationStatusId = commonMethodsUtilService.getLongValueForObject(param[7]);
					
					Long deptCount = commonMethodsUtilService.getLongValueForObject(param[2]);
					Long corpCount = commonMethodsUtilService.getLongValueForObject(param[3]);
					
					Long deptId = commonMethodsUtilService.getLongValueForObject(param[5]);
					Long boardId = commonMethodsUtilService.getLongValueForObject(param[6]);
					if(applicationStatusId.toString().contains("2") || applicationStatusId.toString().contains("4") ||
							 applicationStatusId.toString().contains("8") ){//2,4,8 are rejected status ids , we are showing only not rejected dept counts and boards counts.
						deptCount = 0L;
						corpCount=0L;
					}
						
						NominatedPostVO vo = applicationsStatusDtlsMap.get("RUNNING".trim());//"RUNNING"
						if(vo != null){
							vo.setTotalPositions(runningStatusPostsCount);
							
							List<Long> deptList =statusWiseDeptMap.get("RUNNING");
							List<Long> corpList = statusWisecorporationsMap.get("RUNNING");
							
							if(deptList == null)
								deptList = new ArrayList<Long>(0);
							if(corpList == null)
								corpList = new ArrayList<Long>(0);
							
							if(deptId != null && deptId.longValue()>0L && !deptList.contains(deptId)){
								vo.setTotalDept(vo.getTotalDept()+deptCount);
								deptList.add(deptId);
							}
							if(boardId != null && boardId.longValue()>0L &&  !corpList.contains(boardId)){
								vo.setTotalCorp(vo.getTotalCorp()+corpCount);
								corpList.add(boardId);
							}
							
							statusWiseDeptMap.put("RUNNING",deptList) ;
							statusWisecorporationsMap.put("RUNNING",corpList);
							
							//vo.setTotalDept(vo.getTotalDept() + commonMethodsUtilService.getLongValueForObject(param[2]));
							//vo.setTotalCorp(vo.getTotalCorp() + commonMethodsUtilService.getLongValueForObject(param[3]));
							
							vo.setTotalApplicationReceivedCnt((applciationCountMap.get(2L) !=null ? applciationCountMap.get(2L).longValue():0l) +  // rejected
									(applciationCountMap.get(3L) !=null ? applciationCountMap.get(3L).longValue():0l) + // short listed
									(applciationCountMap.get(4L) !=null ? applciationCountMap.get(4L).longValue():0l) + // rejected in final review
									(applciationCountMap.get(8L) !=null ? applciationCountMap.get(8L).longValue():0l)); // rejected in finalized
							
							if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
								if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
									String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
									vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
								}
								
								if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
									String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
									vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
								}
							}
						}
				}
			}
			
			List<Object[]> levelWiseAppliedPostCountList = nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDetalsNew(levelId,null,null,stateId,"applied");	
			Long appliedStatusPostsCount =0L;
			//Map<Long,Long> appliedPostsMap = new HashMap<Long, Long>();
			
			if(commonMethodsUtilService.isListOrSetValid(levelWiseAppliedPostCountList)){
				for (Object[] param : levelWiseAppliedPostCountList) {
					
					Long applinCount  =  commonMethodsUtilService.getLongValueForObject(param[2]);
					Long memberId  =  commonMethodsUtilService.getLongValueForObject(param[3]);
					
					Map<String,Long> movedPostsStatusMap = movedPostsStatusDetailsMap.get(memberId) != null ? movedPostsStatusDetailsMap.get(memberId):new HashMap<String, Long>(0);
						
						Long readyToFinalReviewPostsCount = movedPostsStatusMap.get("READY FOR FINAL REVIEW") != null?movedPostsStatusMap.get("READY FOR FINAL REVIEW"):0L;
						Long finalyzedPostsCount = movedPostsStatusMap.get("FINALIZED") != null?movedPostsStatusMap.get("FINALIZED"):0L;
						Long G_O_passedPostsCount = movedPostsStatusMap.get("GO ISSUED / COMPLETED") != null?movedPostsStatusMap.get("GO ISSUED / COMPLETED"):0L;
						
						
						Long almostFilledPosts = readyToFinalReviewPostsCount+finalyzedPostsCount+G_O_passedPostsCount;
						Long maxPostsCount = totalPostMemeberCountMap.get(memberId.toString().trim()) != null ?  totalPostMemeberCountMap.get(memberId.toString().trim()) :0L ;
						Long appliedPostsCount =0L;
						
						if(maxPostsCount>almostFilledPosts){// available posts are there
							Long runnngPostsCount = runningPostsMap.get(memberId) != null?runningPostsMap.get(memberId):0L;
							Long openPostsCount = maxPostsCount-almostFilledPosts;
							openPostsCount = openPostsCount-runnngPostsCount;
							if(openPostsCount>applinCount){// applications count less than open posts
								appliedPostsCount = applinCount;
							}else if(openPostsCount<=applinCount){// open posts count less than applications count
								appliedPostsCount = openPostsCount;
							}
						}
						appliedStatusPostsCount = appliedStatusPostsCount+appliedPostsCount;
						//runningPostsMap.put(memberId, appliedPostsCount);
				}
			}
			
			List<Object[]> levelWiseApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDtals(levelId,null,null,stateId);
			if(commonMethodsUtilService.isListOrSetValid(levelWiseApplicatinStatusDetailsList)){
				for (Object[] param : levelWiseApplicatinStatusDetailsList) {
					Long deptCount = commonMethodsUtilService.getLongValueForObject(param[2]);
					Long corpCount = commonMethodsUtilService.getLongValueForObject(param[3]);
					
					Long deptId = commonMethodsUtilService.getLongValueForObject(param[5]);
					Long boardId = commonMethodsUtilService.getLongValueForObject(param[6]);
					
						NominatedPostVO vo = applicationsStatusDtlsMap.get("READY TO SHORT LIST".trim());//""
						if(vo != null){
							vo.setTotalPositions(appliedStatusPostsCount);
							
							List<Long> deptList =statusWiseDeptMap.get("READY TO SHORT LIST");
							List<Long> corpList = statusWisecorporationsMap.get("READY TO SHORT LIST");
							
							if(deptList == null)
								deptList = new ArrayList<Long>(0);
							if(corpList == null)
								corpList = new ArrayList<Long>(0);
							
							if(deptId != null && deptId.longValue()>0L && !deptList.contains(deptId)){
								vo.setTotalDept(vo.getTotalDept()+deptCount);
								deptList.add(deptId);
							}
							if(boardId != null && boardId.longValue()>0L &&  !corpList.contains(boardId)){
								vo.setTotalCorp(vo.getTotalCorp()+corpCount);
								corpList.add(boardId);
							}
							
							statusWiseDeptMap.put("READY TO SHORT LIST",deptList) ;
							statusWisecorporationsMap.put("READY TO SHORT LIST",corpList);
							
							vo.setTotalApplicationReceivedCnt((applciationCountMap.get(1L) !=null ? applciationCountMap.get(1L).longValue():0l));  // applied									(applciationCountMap.get(3L) !=null ? applciationCountMap.get(3L).longValue():0l) + // short listed
							
							if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
								if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
									String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
									vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
								}
								
								if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
									String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
									vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
								}
							}
						}
				}
			}
			
			

			vo2 = applicationsStatusDtlsMap.get("APPLICATIONS NOT RECIEVED".trim());
			if(vo2 != null){
				if(levelId != null && levelId.longValue()>0L){
					vo2.setTotalPositions(0L);
					vo2.setTotalCorp(0L);
					vo2.setTotalDept(0L);
					
					if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
						for (String type : applicationsStatusDtlsMap.keySet()) {
							if(type != null && !type.trim().equalsIgnoreCase("APPLICATIONS NOT RECIEVED") && !type.trim().equalsIgnoreCase("TOTAL AVAILABLE") && !type.trim().equalsIgnoreCase("TOTAL")){
								NominatedPostVO vo1 = applicationsStatusDtlsMap.get(type.trim());
								vo2.setTotalPositions(vo2.getTotalPositions()+vo1.getTotalPositions());
							}
						}
						
						NominatedPostVO vo1 = applicationsStatusDtlsMap.get("TOTAL".trim());
						vo2.setTotalPositions(vo1.getTotalPositions()-vo2.getTotalPositions());
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
				returnList = new ArrayList<NominatedPostVO>();
				for (String status : applicationsStatusDtlsMap.keySet()) {
					if(status.equalsIgnoreCase("TOTAL"))
						returnList.add(0,applicationsStatusDtlsMap.get("TOTAL"));
					else
						returnList.add(applicationsStatusDtlsMap.get(status));
				}
				
			//	returnList.addAll(applicationsStatusDtlsMap.values());
						
			}
			
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getNominatedPostsStatusDetailsInAllLevels() in NominatedPostProfileService model", e);
		}
			return returnList;
	}	
			
	public List<NominatedPostVO> getNominatedPostsStatusDetailsInAllLevel1s(Long levelId,String startDateStr, String endDateStr,Long stateId){
		List<NominatedPostVO> returnList = null;
		try {
			Date startDate = null;
			Date endDate = null;
			if(startDateStr != null && !startDateStr.isEmpty()){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				startDate = format.parse(startDateStr);
				endDate = format.parse(endDateStr);
			}
			
			/*List<IdNameVO> boardLevels  = getBoardLevels();
			Map<Long,NominatedPostVO> levelwiseNominatedPostsMap = new LinkedHashMap<Long,NominatedPostVO>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(boardLevels)){
				for (IdNameVO level : boardLevels) {
					if(levelId != null && levelId.longValue()>0L){
						if(level.getId().longValue() == levelId.longValue())
							levelwiseNominatedPostsMap.put(level.getId(), new NominatedPostVO(level.getId(),level.getName()));
					}
					else if(levelwiseNominatedPostsMap.get(level.getId()) == null)
						levelwiseNominatedPostsMap.put(level.getId(), new NominatedPostVO(level.getId(),level.getName()));
				}
			}*/
			
			//{"TOTAL AVAILABLE","APPLICATIONS NOT RECIEVED","YET TO START","RUNNING","READY FOR FINAL REVIEW","FINALYZED","GO ISSUED / COMPLETED","TOTAL"}
			Map<String,NominatedPostVO> applicationsStatusDtlsMap = new LinkedHashMap<String,NominatedPostVO>(0);
			String[] applicationStatusArr = IConstants.NOMINATED_POST_APPLICATION_STATUSES;
			if(applicationStatusArr != null && applicationStatusArr.length>0)
				for (int i = 0; i < applicationStatusArr.length; i++) 
					applicationsStatusDtlsMap.put(applicationStatusArr[i].trim(),  new NominatedPostVO(Long.valueOf(String.valueOf(i)),applicationStatusArr[i]) );
			
			//if(commonMethodsUtilService.isMapValid(levelwiseNominatedPostsMap)){
				
				List<Object[]> totalAvailablePostsList = nominatedPostDAO.getTotalAvaiablePostDetails(levelId,startDate,endDate,stateId,"Total");
				List<Object[]> totalAvailableApplicationsList = nominatedPostApplicationDAO.getTotalAvaiableApplicationsDetails(levelId,stateId,0L);
				Map<Long,Long> applciationCountMap = new HashMap<Long, Long>(0);
				Long totalApplcationsCount = 0L;
				if(commonMethodsUtilService.isListOrSetValid(totalAvailableApplicationsList)){
					for (Object[] param : totalAvailableApplicationsList){
						applciationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
						totalApplcationsCount = totalApplcationsCount+commonMethodsUtilService.getLongValueForObject(param[1]);
					}
				}
				
				Long totalPositionsCount=0L;
				Long totalCorpCount=0L;
				if(commonMethodsUtilService.isListOrSetValid(totalAvailablePostsList)){
					for (Object[] param : totalAvailablePostsList) {
						NominatedPostVO vo = applicationsStatusDtlsMap.get("TOTAL");
						if(vo != null){
							vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
							vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
							vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
							vo.setTotalApplicationReceivedCnt(totalApplcationsCount);
							
							totalPositionsCount = vo.getTotalPositions();
							totalCorpCount = vo.getTotalCorp();
						}
					}
				}
				
			/*	List<Object[]> totalAvailablePostsList1 = nominatedPostDAO.getTotalAvaiablePostDetails(levelId,startDate,endDate,stateId,"Open");
				if(commonMethodsUtilService.isListOrSetValid(totalAvailablePostsList1)){
					for (Object[] param : totalAvailablePostsList1) {
						NominatedPostVO vo = applicationsStatusDtlsMap.get("TOTAL AVAILABLE");
						if(vo != null){
							vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
							vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
							vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							//totalPositionsCount = vo.getTotalPositions();
							//totalCorpCount = vo.getTotalCorp();
						}
					}
				}
				*/
				List<Long> statusList = nominatedPostStatusDAO.getStatusIdsList();
				List<Object[]> levelWiseAvailablePostsList = nominatedPostDAO.getAvaiablePostDetails(levelId,startDate,endDate,statusList,stateId);
			
				if(commonMethodsUtilService.isListOrSetValid(levelWiseAvailablePostsList)){
					for (Object[] param : levelWiseAvailablePostsList) {
						//Long postLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
						String statusStr = commonMethodsUtilService.getStringValueForObject(param[1]);
						//NominatedPostVO vo1 = levelwiseNominatedPostsMap.get(postLevelId);
						//if(vo1 != null){
						
						/*if(statusStr.trim().equalsIgnoreCase("1")){
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[0].trim());
								if(vo != null){
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									
									totalPositionsCount = vo.getTotalPositions();
									totalCorpCount = vo.getTotalCorp();
								}
							}*/
						
							 if(statusStr.trim().equalsIgnoreCase("2")){
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[4].trim());//"READY FOR FINAL REVIEW"
								if(vo != null){
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									vo.setTotalApplicationReceivedCnt(applciationCountMap.get(6L));
									
									if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
										if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
											String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
											vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
										}
										
										if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
											String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
											vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
										}
									}
									
								}
							}
							else if(statusStr.trim().equalsIgnoreCase("3")){
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[5].trim());//"FINALYZED"
								if(vo != null){
									
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									vo.setTotalApplicationReceivedCnt(applciationCountMap.get(5L));
									
									if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
										if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
											String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
											vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
										}
										
										if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
											String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
											vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
										}
									}
								}
							}
							else if(statusStr.trim().equalsIgnoreCase("4")){
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[6].trim());//"GO ISSUED / COMPLETED"
								if(vo != null){
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									vo.setTotalApplicationReceivedCnt(applciationCountMap.get(7L));
									
									if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
										if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
											String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
											vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
										}
										
										if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
											String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
											vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
										}
									}
								}
							}
						//}
					}
				}
				
				
				
				//Unique Available Posts
				Map<Long,Long> appliedPostsMap = new HashMap<Long, Long>();
				Map<Long,Long> runningPostsMap = new HashMap<Long, Long>();
				
				appliedPostsMap = getAppliedAndRunningPostsCounts(appliedPostsMap,levelId,startDate,endDate,stateId,"applied");
				runningPostsMap = getAppliedAndRunningPostsCounts(runningPostsMap,levelId,startDate,endDate,stateId,"running");
				
								
				List<Object[]> levelWiseApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDtals(levelId,startDate,endDate,stateId);
				if(commonMethodsUtilService.isListOrSetValid(levelWiseApplicatinStatusDetailsList)){
					for (Object[] param : levelWiseApplicatinStatusDetailsList) {
						//Long postLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
						//NominatedPostVO vo1 = levelwiseNominatedPostsMap.get(postLevelId);
						//if(vo1 != null){
							NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[2].trim());//"READY TO SHORT LISTT"
							if(vo != null){
								vo.setTotalPositions(appliedPostsMap.get((Long)param[0]) !=null ? appliedPostsMap.get((Long)param[0]).longValue():0l);
								vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[2]));
								vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[3]));
								vo.setTotalApplicationReceivedCnt(applciationCountMap.get(1L) !=null ? applciationCountMap.get(1L).longValue():0l);
								
								if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
									if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
										String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
										vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
									}
									
									if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
										String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
										vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
									}
								}
							}
						//}
					}
				}
				
				List<Object[]> levelWiseRunningApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsRunningAppliedApplicationsDtals(levelId,startDate,endDate,stateId);
				if(commonMethodsUtilService.isListOrSetValid(levelWiseRunningApplicatinStatusDetailsList)){
					for (Object[] param : levelWiseRunningApplicatinStatusDetailsList) {
						//Long postLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
						//NominatedPostVO vo1 = levelwiseNominatedPostsMap.get(postLevelId);
						//if(vo1 != null){
							NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[3].trim());//"RUNNING"
							if(vo != null){
								vo.setTotalPositions(runningPostsMap.get((Long)param[0]) !=null ? runningPostsMap.get((Long)param[0]).longValue():0l);
								vo.setTotalDept(vo.getTotalDept() + commonMethodsUtilService.getLongValueForObject(param[2]));
								vo.setTotalCorp(vo.getTotalCorp() + commonMethodsUtilService.getLongValueForObject(param[3]));
								vo.setTotalApplicationReceivedCnt((applciationCountMap.get(2L) !=null ? applciationCountMap.get(2L).longValue():0l) + 
										(applciationCountMap.get(3L) !=null ? applciationCountMap.get(3L).longValue():0l) + 
										(applciationCountMap.get(4L) !=null ? applciationCountMap.get(4L).longValue():0l) );
								
								if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
									if(vo.getTotalPositions() != null && vo.getTotalPositions().longValue()>0L){
										String perc = String.valueOf(vo.getTotalPositions()*100.0/totalPositionsCount);
										vo.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
									}
									
									if(vo.getTotalCorp() != null && vo.getTotalCorp().longValue()>0L){
										String perc1 = String.valueOf(vo.getTotalCorp()*100.0/totalCorpCount);
										vo.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
									}
								}
							}
						//}
					}
				}
				
				//String[] aa =  IConstants.NOMINATED_POST_APPLICATION_STATUSES;
				NominatedPostVO vo2 = applicationsStatusDtlsMap.get(applicationStatusArr[1].trim());//"APPLICATIONS NOT RECIEVED"
				if(vo2 != null){
					if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
						if(levelId != null && levelId.longValue()>0L){
							
							List<Object[]> totalDeptsNCorpNDesig = nominatedPostDAO.getTotalCorpIdsAndBoardsIdsAndPositionsIds(levelId,2L,stateId,null);
							Map<String,Long> totalPostMemeberCountMap = new HashMap<String, Long>(0);
							if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
								for (Object[] param : totalDeptsNCorpNDesig) {
									Long maxCount = commonMethodsUtilService.getLongValueForObject(param[0]);
									totalPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), maxCount);
								}
							}
							
							List<Object[]> totalAppliedDeptsNCorpNDesig = nominatedPostApplicationDAO.getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(levelId,2L,stateId);
							Map<String,Long> appliedPostMemeberCountMap = new HashMap<String, Long>(0);
							if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
								for (Object[] param : totalAppliedDeptsNCorpNDesig) {
									Long count = commonMethodsUtilService.getLongValueForObject(param[0]);
									if(appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim()) != null){
										Long meberIdsCount=appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim());
										if(meberIdsCount != null)
											count = meberIdsCount+count;
									}
									appliedPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), count);
								}
							}
							
							Long totalavailableCount = 0L;
							if(commonMethodsUtilService.isMapValid(totalPostMemeberCountMap)){
								for (String DCP : totalPostMemeberCountMap.keySet()) {
									Long maxPositionsCount = totalPostMemeberCountMap.get(DCP) != null?totalPostMemeberCountMap.get(DCP):0L;
									Long appliedCount = appliedPostMemeberCountMap.get(DCP) != null?appliedPostMemeberCountMap.get(DCP):0L;
									
									if(maxPositionsCount>appliedCount){
										totalavailableCount = totalavailableCount+ (maxPositionsCount-appliedCount);
									}
								}
							}
									
									List<Long> totalDeptIsList = nominatedPostDAO.getTotalDeptsCount(levelId);
									List<Long> applRecievedDeptIsList = nominatedPostDAO.getTotalApplicationsDeptsCount(levelId);
									List<Long> remainingDeptIdsList = new ArrayList<Long>(0);
									
									if(commonMethodsUtilService.isListOrSetValid(totalDeptIsList) && 
											 commonMethodsUtilService.isListOrSetValid(applRecievedDeptIsList)){
										for (Long deptIid : totalDeptIsList) {
											if(!applRecievedDeptIsList.contains(deptIid))
												remainingDeptIdsList.add(deptIid);
										}
									}
									
									
									List<Object[]> totalCorpsIdsList = nominatedPostDAO.getTotalCorpsIdsCount(levelId);
									List<Object[]> applRecievedCorpsIdList = nominatedPostDAO.getTotalApplicationsCorpsIdsCount(levelId);
									
									
									List<String> totalCorpList = new ArrayList<String>(0);
									List<String> applnRecvdCorpList = new ArrayList<String>(0);
									List<String> remainingCorpsIdsList = new ArrayList<String>(0);
									if(commonMethodsUtilService.isListOrSetValid(totalCorpsIdsList)){
										for (Object[] param : totalCorpsIdsList) {
											totalCorpList.add(commonMethodsUtilService.getStringValueForObject(param[0]+"-"+commonMethodsUtilService.getStringValueForObject(param[1])));
										}
									}
									
									if(commonMethodsUtilService.isListOrSetValid(applRecievedCorpsIdList)){
										for (Object[] param : applRecievedCorpsIdList) {
											applnRecvdCorpList.add(commonMethodsUtilService.getStringValueForObject(param[0]+"-"+commonMethodsUtilService.getStringValueForObject(param[1])));
										}
									}
									
									if(commonMethodsUtilService.isListOrSetValid(totalCorpList)){
										for (String deptCorpStr : totalCorpList) {
											if(!applnRecvdCorpList.contains(deptCorpStr))
												remainingCorpsIdsList.add(deptCorpStr);
										}
									}
									
									vo2.setTotalPositions(totalavailableCount);
									vo2.setTotalCorp(Long.valueOf(String.valueOf(remainingCorpsIdsList.size())));
									vo2.setTotalDept(Long.valueOf(String.valueOf(remainingDeptIdsList.size())));
									
									if(totalavailableCount != null && totalavailableCount.longValue()>0L){
										if(vo2.getTotalPositions() != null && vo2.getTotalPositions().longValue()>0L){
											String perc = String.valueOf(vo2.getTotalPositions()*100.0/totalPositionsCount);
											vo2.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc)));
										}
										
										if(vo2.getTotalCorp() != null && vo2.getTotalCorp().longValue()>0L){
											String perc1 = String.valueOf(vo2.getTotalCorp()*100.0/totalCorpCount);
											vo2.setPerc1(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc1)));
										}
									}
						}
					}
				}
			//}

				if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
					returnList = new ArrayList<NominatedPostVO>();
					for (String status : applicationsStatusDtlsMap.keySet()) {
						if(status.equalsIgnoreCase("TOTAL"))
							returnList.add(0,applicationsStatusDtlsMap.get("TOTAL"));
						else
							returnList.add(applicationsStatusDtlsMap.get(status));
					}
					
				//	returnList.addAll(applicationsStatusDtlsMap.values());
							
				}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostsStatusDetailsInAllLevels()", e);
		}
		return returnList;
	}
	
	public Map<Long,Long> getAppliedAndRunningPostsCounts(Map<Long,Long> genericMap,Long levelId,Date startDate,Date endDate,Long stateId,String type){
		
		try{
			
			//0.levelId,1.count Of Applied Posts
			List<Object[]> levelPostCount = nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDetalsNew(levelId,startDate,endDate,stateId,type);				
			if(commonMethodsUtilService.isListOrSetValid(levelPostCount)){					
				for( Object[] obj : levelPostCount){						
					//Post Count       //ApplicationsCount
					if((Long)obj[1] >= (Long)obj[2]){
						genericMap.put((Long)obj[0], (Long)obj[2]);
					}else{
						genericMap.put((Long)obj[0], (Long)obj[1]);
					}					
				}					
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return genericMap;
	}
	
	
	public List<NominatedPostVO> getLevelAndStatuswiseNominatedPostsDtals(String searchTypeStr,String statusTypeStr,Long locationValue,String startDateStr, String endDateStr){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	
	public List<NominatedPostVO> getDepartmentsDetailsByLevel(Long levelId,Long levelValue){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	
	public List<NominatedPostVO> getCentralLevelNominatedPostsDetails(){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	public List<NominatedPostVO> getStrateLevelNominatedPostsDetails(){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	
	public List<NominatedPostVO> getAssemblyLevelNominatedPostsDetails(){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	public List<NominatedPostVO> getMandalORMuncipalityLevelNominatedPostsDetails(){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	public List<NominatedPostVO> getVillageORWardLevelNominatedPostsDetails(){
		List<NominatedPostVO> returnList = null;
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLevelAndStatuswiseNominatedPostsDtals()", e);
		}
		
		return returnList;
	}
	public NomintedPostMemberVO getCandidateAppliedPostsByCadre(Long tdpCadreId,String searchType,Long nominateCandId){
		NomintedPostMemberVO returnVo = new NomintedPostMemberVO();
		List<NomintedPostMemberVO> subList = new ArrayList<NomintedPostMemberVO>();
		List<NomintedPostMemberVO> subList1 = new ArrayList<NomintedPostMemberVO>();
		try {
			
			List<Long> applicationIds = new ArrayList<Long>();
			//0-statusId,1-status,2-boardLevelId,3-level,4-deptId,5-deptName,6-boardId,7-boardName,8-positionId,9-positionName,10.postTypeId,11.applicationId
			List<Object[]> appCandList = nominatedPostApplicationDAO.getCandidateAppliedPostsByCadre(tdpCadreId, searchType,nominateCandId,null);
			
			setAppliedCandidatePostsByCadre(subList, subList1, appCandList, tdpCadreId
					, searchType,applicationIds);
			List<Object[]> appCandList1 = nominatedPostApplicationDAO.getCandidateAppliedPostsByCadre(tdpCadreId, searchType,nominateCandId,9l);
			
			setAppliedCandidatePostsByCadre(subList, subList1, appCandList1, tdpCadreId
					, searchType,applicationIds);
			
			returnVo.getSubList().addAll(subList);
			returnVo.getSubList1().addAll(subList1);
			
			if(applicationIds != null && applicationIds.size() >0){
				if(returnVo.getSubList() != null && returnVo.getSubList().size() >0){
					anyPositionLinkedApplicationsUpdation(applicationIds,returnVo.getSubList(),null);
					anyPositionLinkedApplicationsUpdation(applicationIds,returnVo.getSubList(),9l);
				}
				if(returnVo.getSubList1() != null && returnVo.getSubList1().size() >0){
					anyPositionLinkedApplicationsUpdation(applicationIds,returnVo.getSubList1(),null);
					anyPositionLinkedApplicationsUpdation(applicationIds,returnVo.getSubList(),9l);
				}
			}
			getGoPassedDocumentDuration(subList,nominateCandId);
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getAppliedCandidatePostsByCadre", e);
		}
		return returnVo;
	}
	
	public void setAppliedCandidatePostsByCadre(List<NomintedPostMemberVO> subList,List<NomintedPostMemberVO> subList1,List<Object[]> appCandList,Long tdpCadreId
			,String searchType,List<Long> applicationIds){
		try{
			
			List<Long> condidates = null;
			if(appCandList!= null && appCandList.size() >0){
				for (Object[] obj : appCandList) {
					NomintedPostMemberVO Vo = new NomintedPostMemberVO();
					applicationIds.add((Long)obj[11]);//applicationIds
					Vo.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(obj[10]));//postTypeId
					if(Vo.getNominatedPostId() == 1l){
						Vo.setNominatePostApplicationId((Long)obj[11]);
						Vo.setApplStatusId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						Vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[1]));
						Vo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));//boardLevelId
						Vo.setLevel(commonMethodsUtilService.getStringValueForObject(obj[3]));
						if(!Vo.getLevel().equalsIgnoreCase("Central")){
						List<Object[]> list = nominationPostCandidateDAO.getLevelName(Vo.getLevel().toString(),tdpCadreId, searchType,(Long)obj[11]);
					     condidates = nominationPostCandidateDAO.getNominatedPostCondidates(tdpCadreId);
						for(Object[] levl : list){
						Vo.setLevelId(commonMethodsUtilService.getLongValueForObject(levl[0]));
						Vo.setLevelName(commonMethodsUtilService.getStringValueForObject(levl[1]));
						}					
						}
						if(obj[5] != null){
							Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[5]));//deptName
						}else{
							Vo.setCadreName("Any Department ");
						}
						if(obj[7] != null){
							Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[7]));//boardName
						}else{
							Vo.setSubCaste("Any Corporation/Board");
						}
						if(obj[9] != null){
							Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[9]));//positionName
						}else{
							Vo.setVoterName("Any Position");
						}
						Vo.setDeptId(commonMethodsUtilService.getLongValueForObject(obj[4]));//deptId
						Vo.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));//boardId
						
						Vo.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));//positionId
						if(condidates != null && condidates.size() >0)
						Vo.setNominatedPostCandidateId(condidates.get(0));
						
						subList.add(Vo);
					}
					else if(Vo.getNominatedPostId() == 2l){
						Vo.setNominatePostApplicationId((Long)obj[11]);
						Vo.setApplStatusId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						Vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[1]));
						Vo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));//boardLevelId
						Vo.setLevel(commonMethodsUtilService.getStringValueForObject(obj[3]));
						if(!Vo.getLevel().equalsIgnoreCase("Central")){
						List<Object[]> list = nominationPostCandidateDAO.getLevelName(Vo.getLevel().toString(),tdpCadreId, searchType,(Long)obj[11]);
						condidates = nominationPostCandidateDAO.getNominatedPostCondidates(tdpCadreId);
						for(Object[] levl : list){
						Vo.setLevelId(commonMethodsUtilService.getLongValueForObject(levl[0]));
						Vo.setLevelName(commonMethodsUtilService.getStringValueForObject(levl[1]));
						}
						}
						if(obj[5] != null){
							Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[5]));
						}else{							
							Vo.setCadreName("Any Department ");
						}
						if(obj[7] != null){
							Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[7]));
						}else{
							Vo.setSubCaste("Any Corporation/Board");
						}
						if(obj[9] != null){
							Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[9]));
						}else{
							Vo.setVoterName("Any Position");
						}
						Vo.setDeptId(commonMethodsUtilService.getLongValueForObject(obj[4]));
						//Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[5]));
						Vo.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));
						//Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[7]));
						Vo.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));
						//Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[9]));
						if(condidates != null && condidates.size() >0)
						Vo.setNominatedPostCandidateId(condidates.get(0));
						subList1.add(Vo);
					}
					
				}				
			}
			getGovtOrderDocumentsPath(subList);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exceptionr riased at setAppliedCandidatePostsByCadre", e);
		}
	}
	public void anyPositionLinkedApplicationsUpdation(List<Long> applicationIds, List<NomintedPostMemberVO> list,Long goExpiredStatusId){
		List<Object[]> upadatedAppltns = nominatedPostFinalDAO.getApplicationDataByApplctnIds(applicationIds,goExpiredStatusId);
		
		if(upadatedAppltns != null && upadatedAppltns.size() >0){
			for(Object[] obj : upadatedAppltns){
				NomintedPostMemberVO Vo = getMatchedVOForApplication(list,(Long)obj[0]);
				
				if(Vo != null){
					Vo.setDeptId(commonMethodsUtilService.getLongValueForObject(obj[1]));//deptId
					Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[2]));//deptName
					Vo.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[3]));//bordId
					Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[4]));//boardName
					Vo.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[5]));//positionId
					Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[6]));//positionName
				}
			}
		}
	}
	public NomintedPostMemberVO getMatchedVOForApplication(List<NomintedPostMemberVO> voList,Long applicationId){
		NomintedPostMemberVO returnVo = new NomintedPostMemberVO();
		try {
			if(commonMethodsUtilService.isListOrSetValid(voList)){
				for (NomintedPostMemberVO vo : voList) {
					if(vo.getNominatePostApplicationId().longValue() == applicationId.longValue()){
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getMatchedVOForApplication() method of NominatedPostProfileService", e);
		}
		return returnVo;
	}
	public List<NominatedPostVO> getNominatedPostPostionDetails(Long departmentId,Long boardId,Long positionId,Long boardLevelId,Long locationValue,Long searchLevelId){
		
		List<NominatedPostVO> finalList = new ArrayList<NominatedPostVO>(0);
		
		try{
			/*Long departmentId = 0l;
			Long boardId = 0l;
			Long positionId = 0l;
			Long boardLevelId = 2l;
			Long locationValue = 1l;			
			Long searchLevelId = 2l;*/
			
			Map<Long,NominatedPostVO> finalMap = new HashMap<Long, NominatedPostVO>();
			
			if(positionId !=null && positionId>0){
				NominatedPostVO VO = new NominatedPostVO();				
				VO.setId(positionId);		
				VO.setIdNameVoList(getCasteCategoryDetails());
				finalMap.put(positionId, VO);
			}
			
			NominatedPostVO anyVO = new NominatedPostVO();
			anyVO.setId(null);
			anyVO.setIdNameVoList(getCasteCategoryDetails());			
			
			finalMap.put(null, anyVO);
			
			List<Object[]> receivedObj = nominatedPostApplicationDAO.getAppliationsReceievedStatus(departmentId,boardId,
					positionId,boardLevelId,locationValue,null,searchLevelId);
			
			if(receivedObj !=null && receivedObj.size()>0){
				for(Object[] obj : receivedObj){
					
					NominatedPostVO mainVo =null;
					if(obj[0] ==null){
						 mainVo =	finalMap.get(null);
					}else{
						 mainVo = finalMap.get((Long)obj[0]);
					}
					
					if(mainVo != null){
						mainVo.setName(obj[1] !=null ? obj[1].toString():"");
						mainVo.setReceivedCount(obj[2] !=null ? (Long)obj[2]:0l);						
					}						
				}
			}
			
			
			if(positionId !=null && positionId>0l){
				List<Object[]> receivedAnyObj = nominatedPostApplicationDAO.getAppliationsReceievedStatus(departmentId,boardId,
						null,boardLevelId,locationValue,"Any",searchLevelId);
				
				if(receivedAnyObj !=null && receivedAnyObj.size()>0){
					for(Object[] obj : receivedAnyObj){
						NominatedPostVO mainVo =null;
						if(obj[0] ==null){
							 mainVo =	finalMap.get(null);
						}else{
							 mainVo = finalMap.get((Long)obj[0]);
						}
						
						if(mainVo != null){
							mainVo.setName(obj[1] !=null ? obj[1].toString():"");
							mainVo.setReceivedCount(obj[2] !=null ? (Long)obj[2]:0l);						
						}					
					}
				}
			}
			
			//Short Listed Candidates
			List<Object[]> readyToShortList = nominatedPostFinalDAO.getPendingCandidatesStatus(departmentId, boardId, positionId, boardLevelId, locationValue, null,searchLevelId);
			
			if(readyToShortList !=null && readyToShortList.size()>0){
				for(Object[] obj : readyToShortList){
					Long statusId = commonMethodsUtilService.getLongValueForObject(obj[3]);
					Long count = commonMethodsUtilService.getLongValueForObject(obj[2]);
					String name = obj[1] !=null ? obj[1].toString():"";
					
					NominatedPostVO mainVo =null;
					if(obj[0] ==null){
						 mainVo =	finalMap.get(null);
					}else{
						 mainVo = finalMap.get((Long)obj[0]);
					}
					if(mainVo != null){
						mainVo.setName(name); //position name
						if(statusId.longValue() == 1L)
							mainVo.setReadyToShortListedCnt(count); // short listed count 
					}						
				}
			} 
			
		List<Object[]> shrtObj = nominatedPostFinalDAO.getShortlistdCandidatesStatus(departmentId, boardId, positionId, boardLevelId, locationValue, null,searchLevelId);
			
		List<Object[]> totalPostsObj = nominatedPostDAO.getTotalPostCandidates(departmentId,boardId,positionId);
		List<Object[]> openPostsObj = nominatedPostDAO.getOpenPostCandidates(departmentId,boardId,positionId);
			
			if(totalPostsObj !=null && totalPostsObj.size()>0){
				for(Object[] obj : totalPostsObj){
					Long count = commonMethodsUtilService.getLongValueForObject(obj[1]);
					NominatedPostVO mainVo =null;
					if(obj[0] ==null){
						 mainVo =	finalMap.get(null);
					}else{
						 mainVo = finalMap.get((Long)obj[0]);
					}
					if(mainVo != null){
						mainVo.setTotalPosts(count); //total posts
					}						
				}
			} 
			
			if(openPostsObj !=null && openPostsObj.size()>0){
				for(Object[] obj : openPostsObj){
					Long count = commonMethodsUtilService.getLongValueForObject(obj[1]);
					NominatedPostVO mainVo =null;
					if(obj[0] ==null){
						 mainVo =	finalMap.get(null);
					}else{
						 mainVo = finalMap.get((Long)obj[0]);
					}
					if(mainVo != null){
						mainVo.setOpenPosts(count); //total posts
					}						
				}
			} 
			
			
			/*NominatedPostVO VO = new NominatedPostVO();	
			 if(totalPostsObj != null && totalPostsObj.longValue()>0){ 
				 VO.setTotalPosts(totalPostsObj);
			  }
			 if(openPostsObj != null && openPostsObj.longValue()>0){
				 VO.setOpenPosts(openPostsObj);
			 }*/
			
					if(shrtObj !=null && shrtObj.size()>0){
						for(Object[] obj : shrtObj){
							Long statusId = commonMethodsUtilService.getLongValueForObject(obj[3]);
							Long count = commonMethodsUtilService.getLongValueForObject(obj[2]);
							String name = obj[1] !=null ? obj[1].toString():"";
							
							NominatedPostVO mainVo =null;
							
							if(obj[0] ==null){
								 mainVo =	finalMap.get(null);
							}else{
								 mainVo = finalMap.get((Long)obj[0]);
							}
							
							
							if(mainVo != null){
								mainVo.setName(name); //position name
								
								if(statusId.longValue() == 3L)
									mainVo.setShortListedCount(count); // short listed count 
								
								else if (statusId.longValue() == 5L || statusId.longValue() == 6L){ // finalized and GO Passed
									mainVo.setFinalizeAndGOCount(mainVo.getFinalizeAndGOCount()+count);
								}
							    
							}						
						}
					} 
					
		if(positionId !=null && positionId>0l){
			List<Object[]> readyToShortAnyList = nominatedPostFinalDAO.getPendingCandidatesStatus(departmentId, boardId, positionId, boardLevelId, locationValue, null,searchLevelId);
					
				if(readyToShortAnyList !=null && readyToShortAnyList.size()>0){
						for(Object[] obj : readyToShortAnyList){
							Long statusId = commonMethodsUtilService.getLongValueForObject(obj[3]);
							Long count = commonMethodsUtilService.getLongValueForObject(obj[2]);
							String name = obj[1] !=null ? obj[1].toString():"";
							
							NominatedPostVO mainVo =null;
							if(obj[0] ==null){
								 mainVo =	finalMap.get(null);
							}else{
								 mainVo = finalMap.get((Long)obj[0]);
							}
							if(mainVo != null){
								mainVo.setName(name); //position name
								if(statusId.longValue() == 1L)
									mainVo.setReadyToShortListedCnt(count); // short listed count 
							}						
						}
					}
					List<Object[]> shrtAnyObj = nominatedPostFinalDAO.getShortlistdCandidatesStatus(departmentId, boardId, null, boardLevelId, locationValue, "Any",searchLevelId);
										
									if(shrtAnyObj !=null && shrtAnyObj.size()>0){
											for(Object[] obj : shrtAnyObj){
												Long statusId = commonMethodsUtilService.getLongValueForObject(obj[3]);
												Long count = commonMethodsUtilService.getLongValueForObject(obj[2]);
												String name = obj[1] !=null ? obj[1].toString():"";
												NominatedPostVO mainVo =null;
												if(obj[0] ==null){
													 mainVo =	finalMap.get(null);
												}else{
													 mainVo = finalMap.get((Long)obj[0]);
												}
												
												if(mainVo != null){
													mainVo.setName(name); //position name
													//mainVo.setTotalPosts(totalPostsObj); //total posts
													//mainVo.setOpenPosts(openPostsObj); //open posts
													
													if(statusId.longValue() == 3L)
														mainVo.setShortListedCount(count); // short listed count 
													
													else if (statusId.longValue() == 5L || statusId.longValue() == 6L){ // finalized and GO Passed
														mainVo.setFinalizeAndGOCount(mainVo.getFinalizeAndGOCount()+count);
													}
												    
												}						
											}
									}
				}
				
		List<Object[]> casteObj = nominatedPostApplicationDAO.getCasteWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, null,searchLevelId);
		if(casteObj !=null && casteObj.size()>0){
			for(Object[] obj : casteObj){
				//Long statusId = commonMethodsUtilService.getLongValueForObject(obj[5]);
				Long count = commonMethodsUtilService.getLongValueForObject(obj[4]);
				NominatedPostVO mainVo =null;
				if(obj[0] ==null){
					 mainVo =	finalMap.get(null);
				}else{
					 mainVo = finalMap.get((Long)obj[0]);
				}						
				if(mainVo !=null){
					mainVo.setName(obj[1] !=null ? obj[1].toString():"");
					
					List<IdNameVO> lst = mainVo.getIdNameVoList();
						if(lst !=null && lst.size()>0){
						for (IdNameVO idNameVO : lst) {
							String idStr = obj[2].toString();
			                if(idNameVO.getId().toString().equalsIgnoreCase(idStr)){
			                	idNameVO.setCount(count);
								//idNameVO.setCount(mainVo.getReadyToShortListedCnt()+count);
							}
						}
					}
				}
			}
		}
		
			if(positionId !=null && positionId>0l){
				List<Object[]> casteAnyObj = nominatedPostApplicationDAO.getCasteWiseApplications(departmentId, boardId, null, boardLevelId, locationValue, "Any",searchLevelId);
				
				if(casteAnyObj !=null && casteAnyObj.size()>0){
					for(Object[] obj : casteAnyObj){
						Long statusId = commonMethodsUtilService.getLongValueForObject(obj[5]);
						NominatedPostVO mainVo =null;
						if(obj[0] ==null){
							 mainVo =	finalMap.get(null);
						}else{
							 mainVo = finalMap.get((Long)obj[0]);
						}						
						if(mainVo !=null){
							mainVo.setName(obj[1] !=null ? obj[1].toString():"");
							
							List<IdNameVO> lst = mainVo.getIdNameVoList();
	 						if(lst !=null && lst.size()>0){
								for (IdNameVO idNameVO : lst) {
									String idStr = obj[2].toString();
					                if(idNameVO.getId().toString().equalsIgnoreCase(idStr)){
										idNameVO.setCount(obj[4] !=null ? (Long)obj[4]:0l);
									}
								}
							}
						}
					}
				}
			}
			
			List<Object[]> ageObj = nominatedPostApplicationDAO.getAgeRangeWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, null,searchLevelId);
			
			if(ageObj !=null && ageObj.size()>0){
				for(Object[] obj : ageObj){
					
					NominatedPostVO mainVo =null;
					if(obj[0] ==null){
						 mainVo =	finalMap.get(null);
					}else{
						 mainVo = finalMap.get((Long)obj[0]);
					}
					if(mainVo !=null){
						mainVo.setName(obj[1] !=null ? obj[1].toString():"");
						
						if(obj[2] !=null){
							if((Long)obj[2]>=20l && (Long)obj[2]<=29l){
								mainVo.setFirstAgeGroupCount(mainVo.getFirstAgeGroupCount() + (obj[3] !=null ? (Long)obj[3]:0l));
							}else if((Long)obj[2]>=30l && (Long)obj[2]<=39l){
								mainVo.setSecondAgeGroupCount(mainVo.getSecondAgeGroupCount() + ( obj[3] !=null ? (Long)obj[3]:0l));
							}else if((Long)obj[2]>=40l && (Long)obj[2]<=49l){
								mainVo.setThirdAgeGroupCount(mainVo.getThirdAgeGroupCount() + ( obj[3] !=null ? (Long)obj[3]:0l));
							}else if((Long)obj[2]>=50l && (Long)obj[2]<=59l){
								mainVo.setFourthAgeGroupCount(mainVo.getFourthAgeGroupCount() +( obj[3] !=null ? (Long)obj[3]:0l));
							}else{
								mainVo.setFifthAgeGroupCount(mainVo.getFifthAgeGroupCount() +( obj[3] !=null ? (Long)obj[3]:0l));
							}
						} 
													
					}
					
				}
			}
			
			if(positionId !=null && positionId>0l){
				List<Object[]> ageAnyObj = nominatedPostApplicationDAO.getAgeRangeWiseApplications(departmentId, boardId, null, boardLevelId, locationValue, "Any",searchLevelId);
				if(ageAnyObj !=null && ageAnyObj.size()>0){
					for(Object[] obj : ageAnyObj){
						NominatedPostVO mainVo =null;
						if(obj[0] ==null){
							 mainVo =	finalMap.get(null);
						}else{
							 mainVo = finalMap.get((Long)obj[0]);
						}
						if(mainVo !=null){
							mainVo.setName(obj[1] !=null ? obj[1].toString():"");
							
							if(obj[2] !=null){
								if((Long)obj[2]>=20l && (Long)obj[2]<=29l){
									mainVo.setFirstAgeGroupCount(mainVo.getFirstAgeGroupCount() + (obj[3] !=null ? (Long)obj[3]:0l));
								}else if((Long)obj[2]>=30l && (Long)obj[2]<=39l){
									mainVo.setSecondAgeGroupCount(mainVo.getSecondAgeGroupCount() + ( obj[3] !=null ? (Long)obj[3]:0l));
								}else if((Long)obj[2]>=40l && (Long)obj[2]<=49l){
									mainVo.setThirdAgeGroupCount(mainVo.getThirdAgeGroupCount() + ( obj[3] !=null ? (Long)obj[3]:0l));
								}else if((Long)obj[2]>=50l && (Long)obj[2]<=59l){
									mainVo.setFourthAgeGroupCount(mainVo.getFourthAgeGroupCount() +( obj[3] !=null ? (Long)obj[3]:0l));
								}else{
									mainVo.setFifthAgeGroupCount(mainVo.getFifthAgeGroupCount() +( obj[3] !=null ? (Long)obj[3]:0l));
								}
							} 
														
						}
					}
				}
			}
			
			if(finalMap !=null && finalMap.size()>0){
				finalList = new ArrayList<NominatedPostVO>(finalMap.values());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalList;
		
    }
	public List<IdNameVO> getCasteCategoryDetails(){
		List<IdNameVO> casteGrpList = new ArrayList<IdNameVO>(0);
		try{
			List<Object[]> categObjList = casteCategoryDAO.getCasteCategoryDetails();
			if(categObjList !=null && categObjList.size()>0){
				String[] setterPropertiesList = {"id","name"};
				casteGrpList = setterAndGetterUtilService.setValuesToVO(categObjList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return casteGrpList;
		
	} 
	/**
	 * @Author  HYMAVATHI
	 * @Version NominatedPostProfileService.java  July 15, 2016 11:50:00 AM 
	 * @return ResultStatus
	 * description  { Saving Nominated Post Prifile Application Candidate Uploaded Files into Database }
	 */
	public ResultStatus saveNominatedPostUploadFiles(final Map<File,String> mapfiles,final Long nomiPostCandiId){
		
		final ResultStatus resultStatus = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
		String folderName = folderCreation();
		ApplicationDocument applicationDocument = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		
		 StringBuilder pathBuilder = new StringBuilder();
		 StringBuilder str ;
		 
			
		 for (Map.Entry<File, String> entry : mapfiles.entrySet())
		 {
			 str = new StringBuilder();
			 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
				
			 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
			 .append(entry.getValue());
			 str.append(randomNumber).append(".").append(entry.getValue());
			 activityService = new ActivityService();
			String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
			 
				if(fileCpyStts.equalsIgnoreCase("error")){
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					LOG.error(" Exception Raise in copying file");
					throw new ArithmeticException();
				}
				
				applicationDocument = new ApplicationDocument();
				applicationDocument.setFilePath(pathBuilder.toString());
				applicationDocument.setNominationPostCandidateId(nomiPostCandiId);
				applicationDocument.setIsDeleted("N");
				applicationDocument = applicationDocumentDAO.save(applicationDocument);
				
		 }
		 resultStatus.setResultCode(0);
		 resultStatus.setResultState(applicationDocument.getApplicationDocumentId());
		 
				}
			});
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error(" Exception Occured in saveNominatedPostUploadFiles() method, Exception - ",e);
		}
		return resultStatus;
	}
	public static String folderCreation(){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
	  		
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			
			String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String nominatedPostDir = ActivityService.createFolder(staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS);
			 
			 String yr = String.valueOf(year); // YEAR YYYY
			
			 StringBuilder str = new StringBuilder();
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			 str.append(monthText).append("-").append(yr);
			 
			 
			 String mnthYr = str.toString();
			 String mnthYrDir = staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS+"/"+mnthYr;
			 String mnthDirSts = ActivityService.createFolder(mnthYrDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS+"/"+mnthYr;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}
	  	public static String getMonthForInt(int num) {
	        String month = "";
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        String[] months = dfs.getMonths();
	        if (num >= 0 && num <= 11 ) {
	            month = months[num-1];
	        }
	        return month;
	    }
	  	/**
		 * @Author  HYMAVATHI
		 * @Version NominatedPostProfileService.java  July 19, 2016 04:50:00 PM 
		 * @return ResultStatus
		 * description  { Deleting Nomination Post Profile Uploaded Forms From Database By application Document Id}
		 */
	  	public ResultStatus deleteNominatedUploadedFile(String applctnDocId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				List<Long> appltnDocIdList = new ArrayList<Long>();
				String[] idStr = applctnDocId.split(",");
				for(String id: idStr){
					appltnDocIdList.add(Long.parseLong(id));
				}
				
				applicationDocumentDAO.deleteNominatedUploadedFile(appltnDocIdList);
				resultStatus.setResultCode(0);
			}catch (Exception e) {
				resultStatus.setResultCode(1);
				LOG.error(" Exception Occured in deleteNominatedUploadedFile() method, Exception - ",e);
			}
			return resultStatus;
		}
	public List<NominatedPostVO> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId,String type){
		List<NominatedPostVO> returnVoList = new ArrayList<NominatedPostVO>();
		try {
			
			List<Object[]> depOCorpList = new ArrayList<Object[]>(0);
			List<Long> apllicationIds = new ArrayList<Long>();
			List<Object[]> depOCorpList1 =null;
			//0-statusId,1-status,2-boardLevelId,3-level,4-deptId,5-deptName,6-boardId,7-boardName,8-positionId,9-positionName
			if(type !=null && type.trim().equalsIgnoreCase("applied")){
			     depOCorpList1 = new ArrayList<Object[]>(0);
				depOCorpList1 = nominatedPostApplicationDAO.getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,9l);
				setBrdWisNominPstAppliedDepOrCorpApplledDetails(depOCorpList1,apllicationIds,returnVoList,"expired");
				depOCorpList = nominatedPostApplicationDAO.getBrdWisNominPstAppliedDepOrCorpDetails(candidateId,null);
				setBrdWisNominPstAppliedDepOrCorpApplledDetails(depOCorpList,apllicationIds,returnVoList,"applied");
			}else if(type !=null && type.trim().equalsIgnoreCase("shortlisted")){
				depOCorpList = nominatedPostFinalDAO.getBrdWisNominPstAppliedDepOrCorpDetails(candidateId);
				setBrdWisNominPstAppliedDepOrCorpApplledDetails(depOCorpList,apllicationIds,returnVoList,"shortlisted");
			}
			
			List<Object[]> postnLinkedData = nominatedPostFinalDAO.getApplicationDataByApplctnIds(apllicationIds,null);
			if(postnLinkedData != null && postnLinkedData.size() > 0){
				for (Object[] obj : postnLinkedData) {
					NominatedPostVO VO = getMatchedVOForMember(returnVoList,(Long)obj[0]);
					
					if(VO != null){
					
						VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[1]));//deptId
						VO.setMobileNo(commonMethodsUtilService.getStringValueForObject(obj[2]));//deptName
						VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[3]));//boardId
						VO.setPincode(commonMethodsUtilService.getStringValueForObject(obj[4]));//boardName
						VO.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[5]));//positnId
						VO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(obj[6]));//positnName	
						
					}
					
					}
				}
			
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getBrdWisNominPstAppliedDepOrCorpDetails", e);
		}
		return returnVoList;
	}
	
	public NominatedPostVO getMatchedVOForMember(List<NominatedPostVO> voList,Long id){
		NominatedPostVO returnvo = new NominatedPostVO();
		try {
			if(commonMethodsUtilService.isListOrSetValid(voList)){
				for (NominatedPostVO nominatedPostVO : voList) {
					if(nominatedPostVO.getAddDistrictName().longValue() == id.longValue()){
						return nominatedPostVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getMatchedVO() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	public List<IdNameVO> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> locationValues,String statusType,String task, Long searchlevelId, Long searchLevelValue){
		
		List<IdNameVO>  finalList = new ArrayList<IdNameVO>(0);
		try{
			
			
			Map<Long,IdNameVO> deptMap = new HashMap<Long, IdNameVO>();
			
			List<Long> mandalList = new ArrayList<Long>(0);
			List<Long> townList = new ArrayList<Long>(0);
			List<Long> divisonList = new ArrayList<Long>(0);
			
			List<Object[]> deptBoardObj = new ArrayList<Object[]>(0);
			
			/*List<Object[]> mandalObj = new ArrayList<Object[]>(0);
			List<Object[]> townObj = new ArrayList<Object[]>(0);
			List<Object[]> divObj = new ArrayList<Object[]>(0);*/
			
			if(statusType != null && statusType.equalsIgnoreCase("notRecieved"))
			{
				
				Map<Long,String> deptNameMap = new HashMap<Long, String>(0);
				Map<Long,String> boardNameMap = new HashMap<Long, String>(0);
				
				
				List<Object[]> totalDeptsNCorpNDesig = nominatedPostDAO.getTotalCorpIdsAndBoardsIdsAndPositionsIds(boardLevelId,searchlevelId,searchLevelValue,null);
				Map<String,Long> totalPostMemeberCountMap = new HashMap<String, Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
					for (Object[] param : totalDeptsNCorpNDesig) {
						Long maxCount = commonMethodsUtilService.getLongValueForObject(param[0]);
						totalPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), maxCount);
					}
				}
				
				List<Object[]> totalAppliedDeptsNCorpNDesig = nominatedPostApplicationDAO.getTotalAppliedCorpIdsAndBoardsIdsAndPositionsIds(boardLevelId,searchlevelId,searchLevelValue);
				Map<String,Long> appliedPostMemeberCountMap = new HashMap<String, Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
					for (Object[] param : totalAppliedDeptsNCorpNDesig) {
						Long count = commonMethodsUtilService.getLongValueForObject(param[0]);
						if(appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim()) != null){
							Long meberIdsCount=appliedPostMemeberCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim());
							if(meberIdsCount != null)
								count = meberIdsCount+count;
						}
						appliedPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), count);
					}
				}
				
				//Map<Long, Map<Long,Map<Long,Long>>> finalNotApplnRecievedPositionsMap = new HashMap<Long, Map<Long,Map<Long,Long>>>(0);
				Map<Long,Long> missedPostiontsMap = new HashMap<Long, Long>(0);
				Long totalavailablesCount = 0L;
				Long existavailablesCount = 0L;
				if(commonMethodsUtilService.isMapValid(totalPostMemeberCountMap)){
					for (String DCP : totalPostMemeberCountMap.keySet()) {
						Long maxPositionsCount = totalPostMemeberCountMap.get(DCP) != null?totalPostMemeberCountMap.get(DCP):0L;
						Long appliedCount = appliedPostMemeberCountMap.get(DCP) != null?appliedPostMemeberCountMap.get(DCP):0L;
						
						if(maxPositionsCount>appliedCount){
							Long availableCount = maxPositionsCount-appliedCount;
							if(missedPostiontsMap.get(Long.valueOf(DCP)) == null){
								totalavailablesCount = totalavailablesCount+ availableCount;
								missedPostiontsMap.put(Long.valueOf(DCP),availableCount);
							}
							else
							{
								existavailablesCount = existavailablesCount+availableCount;
							}
							//finalNotApplnRecievedPositionsMap.put(DCP, maxPositionsCount-appliedCount);
						}
					}
				}
				
				Map<String,Map<Long,Long>> deptCorpMap = new HashMap<String,Map<Long,Long>>(0);
				
				if(commonMethodsUtilService.isMapValid(missedPostiontsMap)){
					List<Long> idsList = new ArrayList<Long>(missedPostiontsMap.keySet());
					List<Object[]> deptCorpList =  new ArrayList<Object[]>(0);
					
					if(idsList != null && idsList.size()>0){
						 
				    	 int filterCount = 300;
				    	 int i = 0; 
				    	 int j = filterCount;
				    	 int maxcount = idsList.size();
				    	 while (maxcount >0){  
				    		 if(maxcount<filterCount)
				    			 j = i+maxcount;
				    		 
				    		 //System.out.println("j :"+j);
				    		 //System.out.println(tempIdsList.subList(i, j));
				    		 
				    		 List<Object[]>  tempList  = nominatedPostMemberDAO.getTotalBoardsAndCorpIdsByMembrIdsList(idsList.subList(i, j));
								if(commonMethodsUtilService.isListOrSetValid(tempList)){
									deptCorpList.addAll(tempList);
								}
				    		 i=j;
				    		 maxcount = maxcount-filterCount;
				    		 j=j+filterCount;
				    		 
				    		 //System.out.println("maxcount :"+maxcount);
				    	 }
					}
					
					if(commonMethodsUtilService.isListOrSetValid(deptCorpList)){
						for (Object[] param : deptCorpList) {
							Long memberId = commonMethodsUtilService.getLongValueForObject(param[0]);
							Long deptId =  commonMethodsUtilService.getLongValueForObject(param[1]);
							Long corpId =  commonMethodsUtilService.getLongValueForObject(param[3]);
							
							deptNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
							boardNameMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
							Map<Long,Long> memberCountMap = new HashMap<Long, Long>(0);
							if(deptCorpMap.get(deptId+"-"+corpId) != null)
								memberCountMap = deptCorpMap.get(deptId+"-"+corpId);
							
							memberCountMap.put(memberId, missedPostiontsMap.get(memberId));
							deptCorpMap.put(deptId+"-"+corpId, memberCountMap);
							}
						}
					}		
				if(commonMethodsUtilService.isMapValid(deptNameMap)){
					for (Long dept : deptNameMap.keySet()) {
						
						IdNameVO vo = new IdNameVO();
						vo.setId(dept);
						vo.setName(deptNameMap.get(dept));
						
						List<IdNameVO> corpsList = new ArrayList<IdNameVO>(0);
						Long totalavailableCount = 0L;
						if(commonMethodsUtilService.isMapValid(deptCorpMap)){
							for (String deptCorpIdsStr : deptCorpMap.keySet()) {
								
								Map<Long,Long> corpPositionMap = deptCorpMap.get(deptCorpIdsStr);
								Long availableCount = 0L;
								if(commonMethodsUtilService.isMapValid(corpPositionMap)){
									for (Long positionId : corpPositionMap.keySet()) {
										availableCount = availableCount+corpPositionMap.get(positionId);
									}
								}
								
								String[] deptCorpIdsArr = deptCorpIdsStr.split("-");
								Long searchDeptId = Long.valueOf(deptCorpIdsArr[0].toString());
								if(searchDeptId != null && searchDeptId.longValue() == dept.longValue() ){
									IdNameVO vo1 = new IdNameVO();
									vo1.setId(Long.valueOf(deptCorpIdsArr[1].toString()));
									vo1.setName(boardNameMap.get(vo1.getId()));
									vo1.setAvailableCount(availableCount);
									corpsList.add(vo1);
									
									totalavailableCount = totalavailableCount+(vo1.getAvailableCount() != null && vo1.getAvailableCount().longValue()>0L? vo1.getAvailableCount():0L);
								}
							}
						}
						
						vo.setAvailableCount(totalavailableCount);
						vo.setIdnameList(corpsList);
						
						finalList.add(vo);
					}
				}
				Collections.sort(finalList, new Comparator<IdNameVO>() {
					public int compare(IdNameVO o1, IdNameVO o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
				return finalList;
			}
			else if(task !=null && task.trim().equalsIgnoreCase("Total")){
				
				if(boardLevelId.equals(5l)){
					if(locationValues !=null && locationValues.size()>0){
						for (Long manTowDivId : locationValues) {
							
							String mtdId = manTowDivId.toString();
							char temp = mtdId.charAt(0);
							boardLevelId=Long.parseLong(temp+"");
							if(boardLevelId==4l){
								mandalList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 5l;
							}else if(boardLevelId==5l){
								townList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 6l;
							}else if(boardLevelId==6l){
								divisonList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 7l;
							}						
						}
					}

					if(mandalList !=null && mandalList.size()>0){
						List<Object[]> mandalObj = nominatedPostDAO.getAllDeptsAndBoardsByLevel(5l, mandalList,statusType, searchlevelId, searchLevelValue);	
						deptMap = setDataToDeptBoardMap(mandalObj,deptMap);
					}
					if(townList !=null && townList.size()>0){
						List<Object[]> townObj = nominatedPostDAO.getAllDeptsAndBoardsByLevel(6l, townList,statusType, searchlevelId, searchLevelValue);	
						deptMap = setDataToDeptBoardMap(townObj,deptMap);
					}
					if(divisonList !=null && divisonList.size()>0){
						List<Object[]> divObj = nominatedPostDAO.getAllDeptsAndBoardsByLevel(7l, divisonList,statusType, searchlevelId, searchLevelValue);
						 deptMap = setDataToDeptBoardMap(divObj,deptMap);
					}else{
						List<Object[]> divObj = nominatedPostDAO.getAllDeptsAndBoardsByLevel(5l, null,statusType, searchlevelId, searchLevelValue);
						 deptMap = setDataToDeptBoardMap(divObj,deptMap);
					}
					
				}else{
					deptBoardObj = nominatedPostDAO.getAllDeptsAndBoardsByLevel(boardLevelId, locationValues,statusType, searchlevelId, searchLevelValue);
					deptMap = setDataToDeptBoardMap(deptBoardObj,deptMap);
				}
				
				
			}else{
				
				if(boardLevelId.equals(5l)){
					if(locationValues !=null && locationValues.size()>0){
						for (Long manTowDivId : locationValues) {
							
							String mtdId = manTowDivId.toString();
							char temp = mtdId.charAt(0);
							boardLevelId=Long.parseLong(temp+"");
							if(boardLevelId==4l){
								mandalList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 5l;
							}else if(boardLevelId==5l){
								townList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 6l;
							}else if(boardLevelId==6l){
								divisonList.add(Long.parseLong(mtdId.substring(1)));
								boardLevelId = 7l;
							}						
						}
					}

					if(mandalList !=null && mandalList.size()>0){
						List<Object[]> mandalObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(5l, mandalList,statusType, searchlevelId, searchLevelValue);	
						deptMap = setDataToDeptBoardMap(mandalObj,deptMap);
					}
					if(townList !=null && townList.size()>0){
						List<Object[]> townObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(6l, townList,statusType, searchlevelId, searchLevelValue);	
						deptMap = setDataToDeptBoardMap(townObj,deptMap);
					}
					if(divisonList !=null && divisonList.size()>0){
						List<Object[]> divObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(7l, divisonList,statusType, searchlevelId, searchLevelValue);
						 deptMap = setDataToDeptBoardMap(divObj,deptMap);
					}else{
						List<Object[]> mandalObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(5l, null,statusType, searchlevelId, searchLevelValue);	
						deptMap = setDataToDeptBoardMap(mandalObj,deptMap);
					}
					
				}else{
					deptBoardObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(boardLevelId, locationValues,statusType, searchlevelId, searchLevelValue);
					deptMap = setDataToDeptBoardMap(deptBoardObj,deptMap);
				}
			}
			
			List<Long> statusList = nominatedPostStatusDAO.getStatusIdsList();
			List<Object[]> levelWiseAvailablePostsList = nominatedPostDAO.getAvaiablePostDetails(boardLevelId,null,null,statusList,null);
			Map<Long,Map<String,Long>> movedPostsStatusDetailsMap = new HashMap<Long, Map<String,Long>>(0);
			if(commonMethodsUtilService.isListOrSetValid(levelWiseAvailablePostsList)){
				for (Object[] param : levelWiseAvailablePostsList) {
					String statusStr = commonMethodsUtilService.getStringValueForObject(param[1]);
					Long memberId = commonMethodsUtilService.getLongValueForObject(param[7]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[3]);
					
						Map<String,Long> posionwiseMovedMap = new HashMap<String, Long>(0);
						 if(statusStr.trim().equalsIgnoreCase("2")){// nominatedPostStatusid
							
							Long filledCount =0L;
							if(movedPostsStatusDetailsMap.get(memberId) != null){
								posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
								if(posionwiseMovedMap.get("READY FOR FINAL REVIEW") != null)
									filledCount = posionwiseMovedMap.get("READY FOR FINAL REVIEW");
							}
							
							filledCount = filledCount+count;
							posionwiseMovedMap.put("READY FOR FINAL REVIEW", filledCount);
							movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
						}
						else if(statusStr.trim().equalsIgnoreCase("3")){// nominatedPostStatusid
								
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("FINALIZED") != null)
										filledCount = posionwiseMovedMap.get("FINALIZED");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("FINALIZED", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
						}
						else if(statusStr.trim().equalsIgnoreCase("4")){//nominatedPostStatusid
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("GO ISSUED / COMPLETED") != null)
										filledCount = posionwiseMovedMap.get("GO ISSUED / COMPLETED");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("GO ISSUED / COMPLETED", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
						}
				}
			}
			
			
			List<Object[]> totalDeptsNCorpNDesig = nominatedPostDAO.getTotalCorpIdsAndBoardsIdsAndPositionsIds(boardLevelId,2L,null,null);
			Map<String,Long> totalPostMemeberCountMap = new HashMap<String, Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(totalDeptsNCorpNDesig)){
				for (Object[] param : totalDeptsNCorpNDesig) {
					Long maxCount = commonMethodsUtilService.getLongValueForObject(param[0]);
					totalPostMemeberCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]).toString().trim(), maxCount);
				}
			}
			

		
			List<Object[]> levelPostCountList = nominatedPostApplicationDAO.getNominatedPostsAppliedApplcitionsDetalsNew(boardLevelId,null,null,null,"running");	
			Long runningStatusPostsCount =0L;
			Map<Long,Long> runningPostsMap  = new HashMap<Long, Long>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(levelPostCountList)){
				for (Object[] param : levelPostCountList) {
					
					Long applinCount  =  commonMethodsUtilService.getLongValueForObject(param[2]);
					Long memberId  =  commonMethodsUtilService.getLongValueForObject(param[3]);
					
					if(memberId != null && memberId.longValue() ==864L)
						System.out.println(memberId);
					
						Map<String,Long> movedPostsStatusMap = movedPostsStatusDetailsMap.get(memberId) != null ? movedPostsStatusDetailsMap.get(memberId):new HashMap<String, Long>(0);
						
						Long readyToFinalReviewPostsCount = movedPostsStatusMap.get("READY FOR FINAL REVIEW") != null?movedPostsStatusMap.get("READY FOR FINAL REVIEW"):0L;
						Long finalyzedPostsCount = movedPostsStatusMap.get("FINALIZED") != null?movedPostsStatusMap.get("FINALIZED"):0L;
						Long G_O_passedPostsCount = movedPostsStatusMap.get("GO ISSUED / COMPLETED") != null?movedPostsStatusMap.get("GO ISSUED / COMPLETED"):0L;
						
						Long almostFilledPosts = readyToFinalReviewPostsCount+finalyzedPostsCount+G_O_passedPostsCount;
						Long maxPostsCount = totalPostMemeberCountMap.get(memberId.toString().trim()) != null ?  totalPostMemeberCountMap.get(memberId.toString().trim()) :0L ;
						Long runningPostsCount =0L;
						
						if(maxPostsCount>almostFilledPosts){// available posts are there
							Long openPostsCount = maxPostsCount-almostFilledPosts;
							if(openPostsCount>applinCount){// applications count less than open posts
								runningPostsCount = applinCount;
							}else if(openPostsCount<=applinCount){// open posts count less than applications count
								runningPostsCount = openPostsCount;
							}
						}
						runningStatusPostsCount = runningStatusPostsCount+runningPostsCount;
						runningPostsMap.put(memberId, runningPostsCount);
				}
			}
			if(statusType != null && statusType.trim().equalsIgnoreCase("running"))
			{
				if(commonMethodsUtilService.isMapValid(runningPostsMap)){
					List<Object[]> memberDeptBoardList = nominatedPostMemberDAO.getTotalBoardsAndCorpIdsByMembrIdsList(new ArrayList<Long>(runningPostsMap.keySet()));
					//Map<Long,Map<Long,Map<Long,Map<Long,Long>>>> memberDeptBoardPositionCountMap = new HashMap<Long, Map<Long,Map<Long,Map<Long,Long>>>>(0);
					Map<Long,Map<Long,Long>> memberDeptBoardMap = new HashMap<Long, Map<Long,Long>>(0);
					
					Map<Long,Long> deptWiseOpenPostsMap = new HashMap<Long,Long>(0);
					Map<Long,Long> deptWiseAppliedApplnMap = new HashMap<Long,Long>(0);
					
					Map<Long,Long> boardWiseOpenPostsMap = new HashMap<Long,Long>(0);
					Map<Long,Long> boardWiseAppliedApplnMap = new HashMap<Long,Long>(0);
					
					if(commonMethodsUtilService.isListOrSetValid(memberDeptBoardList)){
						for (Object[] param : memberDeptBoardList) {
							Long memberId = commonMethodsUtilService.getLongValueForObject(param[0]);
							Long deptId = commonMethodsUtilService.getLongValueForObject(param[1]);
							//String deptStr = commonMethodsUtilService.getStringValueForObject(param[2]);
							Long boardId = commonMethodsUtilService.getLongValueForObject(param[3]);
							//String boardStr = commonMethodsUtilService.getStringValueForObject(param[4]);

							if(memberId != null && memberId.longValue() ==864L)
								System.out.println(memberId);
							
							Map<Long,Long> deptBoardIdssMap = new HashMap<Long, Long>();
							if(memberDeptBoardMap.get(deptId) != null){
								deptBoardIdssMap = memberDeptBoardMap.get(deptId);
							}
							deptBoardIdssMap.put(deptId, boardId);
							memberDeptBoardMap.put(memberId, deptBoardIdssMap);
							
							
							// present open posts in dept
							Long availablePstsCount = 0L;
							if(deptWiseOpenPostsMap.get(deptId) != null){
								availablePstsCount = deptWiseOpenPostsMap.get(deptId);
							}
							
							availablePstsCount = availablePstsCount+(runningPostsMap.get(memberId) != null ? runningPostsMap.get(memberId):0L);
							deptWiseOpenPostsMap.put(deptId, availablePstsCount);
							
							// present applied applications count in dept
							Long appliedApplnCount = 0L;
							if(deptWiseAppliedApplnMap.get(deptId) != null){
								appliedApplnCount = deptWiseAppliedApplnMap.get(deptId);
							}
							
							appliedApplnCount = appliedApplnCount+(runningPostsMap.get(memberId) != null ? runningPostsMap.get(memberId):0L);
							deptWiseAppliedApplnMap.put(deptId, appliedApplnCount);
							
							
							// present open posts in board
							Long availableBorardPstsCount = 0L;
							if(boardWiseOpenPostsMap.get(boardId) != null){
								availableBorardPstsCount = boardWiseOpenPostsMap.get(boardId);
							}
							
							availableBorardPstsCount = availableBorardPstsCount+(runningPostsMap.get(memberId) != null ? runningPostsMap.get(memberId):0L);
							boardWiseOpenPostsMap.put(boardId, availableBorardPstsCount);
							
							// present applied applications count  in board
							Long appliedBoardApplnCount = 0L;
							if(boardWiseAppliedApplnMap.get(boardId) != null){
								appliedBoardApplnCount = boardWiseAppliedApplnMap.get(boardId);
							}
							appliedBoardApplnCount = appliedBoardApplnCount+(runningPostsMap.get(memberId) != null ? runningPostsMap.get(memberId):0L);
							boardWiseAppliedApplnMap.put(boardId, appliedBoardApplnCount);
						}
					}
					
					if(commonMethodsUtilService.isMapValid(deptMap)){
						for (Long deptsId : deptMap.keySet()) {
							IdNameVO deptVO = deptMap.get(deptsId);
							if(deptVO != null){
								deptVO.setAvailableCount(deptWiseOpenPostsMap.get(deptsId));
								deptVO.setApplicationsCount(deptWiseAppliedApplnMap.get(deptsId));
								
								if(commonMethodsUtilService.isListOrSetValid(deptVO.getIdnameList())){
									for (IdNameVO boardVO : deptVO.getIdnameList()) {
										boardVO.setAvailableCount(boardWiseOpenPostsMap.get(boardVO.getId()));
										boardVO.setApplicationsCount(boardWiseAppliedApplnMap.get(boardVO.getId()));
									}
								}
								finalList.add(deptVO);
							}
						}
					}
				}
			}
			else if(statusType != null && statusType.trim().equalsIgnoreCase("notyet"))
			{
				List<Object[]> levelWiseAppliedPostCountList = nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDetalsNew(boardLevelId,null,null,null,"applied");	
				Long appliedStatusPostsCount =0L;
				Map<Long,Long> appliedPostsMap = new HashMap<Long, Long>();
				Map<Long,Long> appliedApplciationsMap = new HashMap<Long, Long>();
				
				if(commonMethodsUtilService.isListOrSetValid(levelWiseAppliedPostCountList)){
					for (Object[] param : levelWiseAppliedPostCountList) {
						
						Long applinCount  =  commonMethodsUtilService.getLongValueForObject(param[2]);
						Long memberId  =  commonMethodsUtilService.getLongValueForObject(param[3]);
						
						if(memberId != null && memberId.longValue() ==864L)
							System.out.println(memberId);
						
						Map<String,Long> movedPostsStatusMap = movedPostsStatusDetailsMap.get(memberId) != null ? movedPostsStatusDetailsMap.get(memberId):new HashMap<String, Long>(0);
							
							Long readyToFinalReviewPostsCount = movedPostsStatusMap.get("READY FOR FINAL REVIEW") != null?movedPostsStatusMap.get("READY FOR FINAL REVIEW"):0L;
							Long finalyzedPostsCount = movedPostsStatusMap.get("FINALIZED") != null?movedPostsStatusMap.get("FINALIZED"):0L;
							Long G_O_passedPostsCount = movedPostsStatusMap.get("GO ISSUED / COMPLETED") != null?movedPostsStatusMap.get("GO ISSUED / COMPLETED"):0L;
							
							
							Long almostFilledPosts = readyToFinalReviewPostsCount+finalyzedPostsCount+G_O_passedPostsCount;
							Long maxPostsCount = totalPostMemeberCountMap.get(memberId.toString().trim()) != null ?  totalPostMemeberCountMap.get(memberId.toString().trim()) :0L ;
							Long appliedPostsCount =0L;
							
							if(maxPostsCount>almostFilledPosts){// available posts are there
								Long runnngPostsCount = 0L;// running posts not consider as filled runningPostsMap.get(memberId) != null?runningPostsMap.get(memberId):0L;
								Long openPostsCount = maxPostsCount-almostFilledPosts;
								openPostsCount = openPostsCount-runnngPostsCount;
								if(openPostsCount>applinCount){// applications count less than open posts
									appliedPostsCount = openPostsCount;
								}else if(openPostsCount<=applinCount){// open posts count less than applications count
									appliedPostsCount = openPostsCount;
								}
							}
							
							appliedStatusPostsCount = appliedStatusPostsCount+appliedPostsCount;
							appliedPostsMap.put(memberId, appliedPostsCount);
							appliedApplciationsMap.put(memberId, applinCount);
					}
				}
				
				
				if(commonMethodsUtilService.isMapValid(appliedPostsMap)){
					List<Object[]> memberDeptBoardList = nominatedPostMemberDAO.getTotalBoardsAndCorpIdsByMembrIdsList(new ArrayList<Long>(appliedPostsMap.keySet()));
					//Map<Long,Map<Long,Map<Long,Map<Long,Long>>>> memberDeptBoardPositionCountMap = new HashMap<Long, Map<Long,Map<Long,Map<Long,Long>>>>(0);
					Map<Long,Map<Long,Long>> memberDeptBoardMap = new HashMap<Long, Map<Long,Long>>(0);
					
					Map<Long,Long> deptWiseOpenPostsMap = new HashMap<Long,Long>(0);
					Map<Long,Long> deptWiseAppliedApplnMap = new HashMap<Long,Long>(0);
					
					Map<Long,Long> boardWiseOpenPostsMap = new HashMap<Long,Long>(0);
					Map<Long,Long> boardWiseAppliedApplnMap = new HashMap<Long,Long>(0);
					
					if(commonMethodsUtilService.isListOrSetValid(memberDeptBoardList)){
						for (Object[] param : memberDeptBoardList) {
							Long memberId = commonMethodsUtilService.getLongValueForObject(param[0]);
							Long deptId = commonMethodsUtilService.getLongValueForObject(param[1]);
						//	String deptStr = commonMethodsUtilService.getStringValueForObject(param[2]);
							Long boardId = commonMethodsUtilService.getLongValueForObject(param[3]);
						//	String boardStr = commonMethodsUtilService.getStringValueForObject(param[4]);
							
							Map<Long,Long> deptBoardIdssMap = new HashMap<Long, Long>();
							if(memberDeptBoardMap.get(deptId) != null){
								deptBoardIdssMap = memberDeptBoardMap.get(deptId);
							}
							deptBoardIdssMap.put(deptId, boardId);
							memberDeptBoardMap.put(memberId, deptBoardIdssMap);
							
							
							// present open posts in dept
							Long availablePstsCount = 0L;
							if(deptWiseOpenPostsMap.get(deptId) != null){
								availablePstsCount = deptWiseOpenPostsMap.get(deptId);
							}
							
							availablePstsCount = availablePstsCount+(appliedPostsMap.get(memberId) != null ? appliedPostsMap.get(memberId):0L);
							deptWiseOpenPostsMap.put(deptId, availablePstsCount);
							
							// present applied applications count in dept
							Long appliedApplnCount = 0L;
							if(deptWiseAppliedApplnMap.get(deptId) != null){
								appliedApplnCount = deptWiseAppliedApplnMap.get(deptId);
							}
							
							appliedApplnCount = appliedApplnCount+(appliedApplciationsMap.get(memberId) != null ? appliedApplciationsMap.get(memberId):0L);
							deptWiseAppliedApplnMap.put(deptId, appliedApplnCount);
							
							
							// present open posts in board
							Long availableBorardPstsCount = 0L;
							if(boardWiseOpenPostsMap.get(boardId) != null){
								availableBorardPstsCount = boardWiseOpenPostsMap.get(boardId);
							}
							
							availableBorardPstsCount = availableBorardPstsCount+(appliedPostsMap.get(memberId) != null ? appliedPostsMap.get(memberId):0L);
							boardWiseOpenPostsMap.put(boardId, availableBorardPstsCount);
							
							// present applied applications count  in board
							Long appliedBoardApplnCount = 0L;
							if(boardWiseAppliedApplnMap.get(boardId) != null){
								appliedBoardApplnCount = boardWiseAppliedApplnMap.get(boardId);
							}
							appliedBoardApplnCount = appliedBoardApplnCount+(appliedApplciationsMap.get(memberId) != null ? appliedApplciationsMap.get(memberId):0L);
							boardWiseAppliedApplnMap.put(boardId, appliedBoardApplnCount);
							
							
						}
						
					}
					
					if(commonMethodsUtilService.isMapValid(deptMap)){
						for (Long deptsId : deptMap.keySet()) {
							IdNameVO deptVO = deptMap.get(deptsId);
							if(deptVO != null){
								deptVO.setAvailableCount(deptWiseOpenPostsMap.get(deptsId));
								deptVO.setApplicationsCount(deptWiseAppliedApplnMap.get(deptsId));
								
								if(commonMethodsUtilService.isListOrSetValid(deptVO.getIdnameList())){
									for (IdNameVO boardVO : deptVO.getIdnameList()) {
										boardVO.setAvailableCount(boardWiseOpenPostsMap.get(boardVO.getId()));
										boardVO.setApplicationsCount(boardWiseAppliedApplnMap.get(boardVO.getId()));
									}
								}
								
								finalList.add(deptVO);
							}
						}
					}
					
					
				}
			}
			List<Object[]> anyPositionData = nominatedPostApplicationDAO.getAnyPositionDetailsByLevelId(boardLevelId);
			if(commonMethodsUtilService.isListOrSetValid(anyPositionData)){
				for (Object[] param : anyPositionData) {
					IdNameVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(deptVO != null){
						if(commonMethodsUtilService.isListOrSetValid(deptVO.getIdnameList())){
							
							IdNameVO boardVO = getMatchVO(commonMethodsUtilService.getLongValueForObject(param[0]), deptVO.getIdnameList());
								if(boardVO != null){
									Long cnt = boardVO.getApplicationsCount() != null ?boardVO.getApplicationsCount().longValue():0l;
									boardVO.setApplicationsCount(cnt+commonMethodsUtilService.getLongValueForObject(param[2]));
								}
						}
						
						finalList.add(deptVO);
					}
				}
			}
			
			
			
			Map<Long,IdNameVO> deptBoardsMap = new HashMap<Long,IdNameVO>(0);
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (IdNameVO idNameVO : finalList) {
					deptBoardsMap.put(idNameVO.getId(), idNameVO);
				}
				if(commonMethodsUtilService.isMapValid(deptBoardsMap)){
					finalList.clear();
					finalList.addAll(deptBoardsMap.values());
				}
			}
			
			Collections.sort(finalList, new Comparator<IdNameVO>() {
				public int compare(IdNameVO o1, IdNameVO o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			
			
			/*if(deptBoardObj !=null && deptBoardObj.size()>0){
				for (Object[] obj : deptBoardObj) {
				
					IdNameVO VO = deptMap.get((Long)obj[0]);
					if(VO == null){
						VO = new IdNameVO();
						VO.setId((Long)obj[0]);
						VO.setName(obj[1] !=null ? obj[1].toString():"");
						deptMap.put((Long)obj[0], VO);
					}
					
					List<IdNameVO> boardList = VO.getIdnameList();
					
					if(boardList ==null || boardList.size() == 0){
						boardList = new ArrayList<IdNameVO>();					
						VO.setIdnameList(boardList);
					}
						IdNameVO inneVo = new IdNameVO();
						inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
						inneVo.setName(obj[3] !=null ? obj[3].toString():"");			
					
						boardList.add(inneVo);								
				}
				
				if(deptMap !=null && deptMap.size()>0){
					finalList = new ArrayList<IdNameVO>(deptMap.values());
				}
				
				//System.out.println(finalList);
				
			}*/
			/*Map<Long,Long> deptPosMap = new LinkedHashMap<Long, Long>();
			Map<Long,List<Long>> deptAvailableBoardsMap = new LinkedHashMap<Long, List<Long>>();
			Map<Long,Map<Long,Long>> deptBrdPosMap = new LinkedHashMap<Long, Map<Long,Long>>();
			Map<Long,List<Long>> deptBrdApplMap = new LinkedHashMap<Long,List<Long>>();
			
			List<Object[]> positionsCountsList = nominatedPostDAO.getOpenedPositionsCountByDepartment(boardLevelId, searchlevelId, searchLevelValue,statusType);
			if(commonMethodsUtilService.isListOrSetValid(positionsCountsList)){
				Map<Long,Map<String,Long>> deptBoardPosotionMap = new HashMap<Long, Map<String,Long>>(0);
				for (Object[] obj : positionsCountsList) {
					Long depId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					if(statusType != null && statusType.equalsIgnoreCase("notYet"))
					{
						Long postionId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						Long boardId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						Map<String,Long> positionCountMap = new HashMap<String, Long>(0);
						List<Long> boardIdsList  = new ArrayList<Long>(0);
						if(deptBoardPosotionMap.get(depId) != null){
							positionCountMap = deptBoardPosotionMap.get(depId);
							if(commonMethodsUtilService.isMapValid(positionCountMap) && positionCountMap.get(postionId+""+boardId+"-"+postionId) != null){
								count = count+positionCountMap.get(postionId+""+boardId+"-"+postionId);
							}
							boardIdsList = deptAvailableBoardsMap.get(depId);
						}
						
						List<Long> bordPositionsList = new ArrayList<Long>(0);
						if(deptBrdApplMap.get(postionId) != null)
							bordPositionsList = deptBrdApplMap.get(postionId);
						
						bordPositionsList.add(postionId);
						deptBrdApplMap.put(boardId, bordPositionsList);
						
						positionCountMap.put(postionId+""+boardId+"-"+postionId, count);
						deptBoardPosotionMap.put(depId, positionCountMap);
						boardIdsList.add(boardId);
						deptAvailableBoardsMap.put(depId, boardIdsList);
					}
					else
						deptPosMap.put(depId, count);
				}
				if(statusType != null && statusType.equalsIgnoreCase("notYet") && commonMethodsUtilService.isMapValid(deptBoardPosotionMap)){
					for (Long deptId : deptBoardPosotionMap.keySet()) {
						Map<String,Long> positionCountMap =  deptBoardPosotionMap.get(deptId);
						Long count=0L;
						if(commonMethodsUtilService.isMapValid(positionCountMap)){
							for (String positionId : positionCountMap.keySet()) {
								if(positionId != null && !positionId.toString().substring(0,1).equalsIgnoreCase("0"))
									count = count+positionCountMap.get(positionId);
							}
						}
						deptPosMap.put(deptId, count);
					}
				}
			}
			
			List<Object[]> boardPosCountsList = nominatedPostDAO.getOpenedPositionsCountForBoardsByDepartment(boardLevelId, searchlevelId, searchLevelValue,statusType);
			if(commonMethodsUtilService.isListOrSetValid(boardPosCountsList)){
				for (Object[] obj : boardPosCountsList) {
					Long deptId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long brdId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Map<Long,Long> boardMap = deptBrdPosMap.get(deptId);
					if(boardMap == null){
						boardMap = new LinkedHashMap<Long, Long>();
						boardMap.put(brdId, count);
						deptBrdPosMap.put(deptId, boardMap);
					}
					else{
						boardMap.put(brdId, count);
						deptBrdPosMap.put(deptId, boardMap);
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(deptPosMap)){
				for (Long deptId : deptPosMap.keySet()) {
					if(deptPosMap.get(deptId) != null){
						Map<Long,Long> boardMap = deptBrdPosMap.get(deptId);
						Long availableCount = 0L;
						List<Long> boardIdsList  = deptAvailableBoardsMap.get(deptId);
						if(commonMethodsUtilService.isMapValid(boardMap) && commonMethodsUtilService.isListOrSetValid(boardIdsList)){
							for (Long batchId : boardMap.keySet()) {
								if(boardIdsList.contains(batchId))
									availableCount = availableCount+(boardMap.get(batchId) != null?boardMap.get(batchId):0L);
							}
						}
						Long appliedPostsCount = deptPosMap.get(deptId);
						if(availableCount >0L && appliedPostsCount>availableCount){
							deptPosMap.put(deptId,availableCount);
						}
					}
				}
			}
			
			Map<Long,Map<Long,Long>> applTotalMap = new LinkedHashMap<Long, Map<Long,Long>>();
			Map<Long,Map<Long,Long>> applShrtMap = new LinkedHashMap<Long, Map<Long,Long>>();
			List<Object[]> totalList = nominatedPostApplicationDAO.getTotalApplicationCountsByBoard(boardLevelId, searchlevelId, searchLevelValue, 0l);
			if(commonMethodsUtilService.isListOrSetValid(totalList)){
				for (Object[] obj : totalList) {
					Long depId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long brdId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Map<Long,Long> bdMap = applTotalMap.get(depId);
					if(bdMap == null){
						bdMap = new LinkedHashMap<Long, Long>();
						bdMap.put(brdId, count);
						applTotalMap.put(depId, bdMap);
					}
					else{
						bdMap.put(brdId, count);
						applTotalMap.put(depId, bdMap);
					}
				}
			}
			
			Map<Long,Map<Long,Long>> applYetTosStartTotalMap = new LinkedHashMap<Long, Map<Long,Long>>();
			List<Object[]> applYetTosStartTotalList = nominatedPostApplicationDAO.getTotalApplicationCountsByBoard(boardLevelId, searchlevelId, searchLevelValue, 1l);
			if(commonMethodsUtilService.isListOrSetValid(applYetTosStartTotalList)){
				for (Object[] obj : applYetTosStartTotalList) {
					Long depId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long brdId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Map<Long,Long> bdMap = applYetTosStartTotalMap.get(depId);
					if(bdMap == null){
						bdMap = new LinkedHashMap<Long, Long>();
						bdMap.put(brdId, count);
						applYetTosStartTotalMap.put(depId, bdMap);
					}
					else{
						bdMap.put(brdId, count);
						applYetTosStartTotalMap.put(depId, bdMap);
					}
				}
			}
			
			List<Object[]> shortList = nominatedPostApplicationDAO.getTotalApplicationCountsByBoard(boardLevelId, searchlevelId, searchLevelValue, 3l);
			if(commonMethodsUtilService.isListOrSetValid(shortList)){
				for (Object[] obj : shortList) {
					Long depId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long brdId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Map<Long,Long> bdMap = applShrtMap.get(depId);
					if(bdMap == null){
						bdMap = new LinkedHashMap<Long, Long>();
						bdMap.put(brdId, count);
						applShrtMap.put(depId, bdMap);
					}
					else{
						bdMap.put(brdId, count);
						applShrtMap.put(depId, bdMap);
					}
				}
			}
			
			
			if(deptMap !=null && deptMap.size()>0){
				finalList = new ArrayList<IdNameVO>(deptMap.values());
			}
			
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (IdNameVO idNameVO : finalList) {
					Long count = deptPosMap.get(idNameVO.getId() != null?idNameVO.getId():0L);
					//idNameVO.setAvailableCount(count);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (IdNameVO idNameVO : finalList) {
					Long depId = idNameVO.getId();
					Map<Long,Long> deMap = deptBrdPosMap.get(depId != null ? depId:0L);
					if(commonMethodsUtilService.isListOrSetValid(idNameVO.getIdnameList())){
						List<IdNameVO> brdList = idNameVO.getIdnameList();
						for (IdNameVO idNameVO2 : brdList) {
							Long brdId = idNameVO2.getId();
							if(deMap !=null && deMap.size()>0){
								Long count = deMap.get(brdId != null ?brdId:0L);
								if(count !=null){
									idNameVO2.setAvailableCount(idNameVO2.getAvailableCount()+count);
								}	
							}
													
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (IdNameVO idNameVO : finalList) {
					Long depId = idNameVO.getId();
					Map<Long,Long> ttlMap = applTotalMap.get(depId);
					Map<Long,Long> shrMap = applShrtMap.get(depId);
					Map<Long,Long> applnyettoStartBrdMap = applYetTosStartTotalMap.get(depId);
					if(commonMethodsUtilService.isListOrSetValid(idNameVO.getIdnameList())){
						List<IdNameVO> brdList = idNameVO.getIdnameList();
						Long deptOpenPostsCount =0L;
						for (IdNameVO idNameVO2 : brdList) {
							Map<Long,Long> boardAvailablePostsCountMap= deptBrdPosMap.get(idNameVO.getId());
							if(idNameVO2.getAvailableCount() != null){
								deptOpenPostsCount = deptOpenPostsCount+idNameVO2.getAvailableCount();
							}
							
							if(commonMethodsUtilService.isMapValid(boardAvailablePostsCountMap)){
								idNameVO2.setCount(boardAvailablePostsCountMap.get(idNameVO2.getId()));
							}
							
							Long brdId = idNameVO2.getId();
							Long ttlAppl = 0l;//
							Long shtAppl = 0l;
							Long yetToStart=0L;
							if(ttlMap != null)
								ttlAppl = ttlMap.get(brdId != null?brdId:0L);
							if(shrMap != null)
								shtAppl = shrMap.get(brdId != null?brdId:0L);
							if(applnyettoStartBrdMap != null && applnyettoStartBrdMap.get(brdId) != null)
								yetToStart = applnyettoStartBrdMap.get(brdId);
							String perc = "0.00";
							if(ttlAppl != null && ttlAppl.longValue() > 0l && shtAppl != null && shtAppl.longValue() > 0l)
								perc = (new BigDecimal((shtAppl * 100.0)/ttlAppl.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
							idNameVO2.setPercentage(perc);
							idNameVO2.setApplicationsCount(yetToStart);
						}
						idNameVO.setAvailableCount(deptOpenPostsCount);
						Collections.sort(brdList,sortByName);
					}
				}
			}
			
			Collections.sort(finalList,sortByName);*/
			
			//Any Board Scenario Of A department
		/*	
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (IdNameVO obj : finalList) {					
					List<IdNameVO> subList = obj.getIdnameList();											
					IdNameVO VO = new IdNameVO();
					VO.setId(null);
					VO.setName("ANY CORPORATION / BOARD ");						
					List<IdNameVO> list = new ArrayList<IdNameVO>();					
					list.add(VO);
					if(subList !=null){
						list.addAll(subList);
					}					
					subList.clear();
					subList.addAll(list);				
				}
			}
			
				*/
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getAllDeptsAndBoardsByLevel", e);
		}
		
		return finalList;
		}
	public static Comparator<IdNameVO> sortByName = new Comparator<IdNameVO>()
    {    
          public int compare(IdNameVO arg1,IdNameVO arg2)
          {
            return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
          }
    };
	
	
	public Map<Long,IdNameVO> setDataToDeptBoardMap(List<Object[]> deptBoardObj,Map<Long,IdNameVO> deptMap){
		try{
			
			if(deptBoardObj !=null && deptBoardObj.size()>0){
				for (Object[] obj : deptBoardObj) {
				
					IdNameVO VO = deptMap.get((Long)obj[0]);
					if(VO == null){
						VO = new IdNameVO();
						VO.setId(obj[0] !=null ? (Long)obj[0] : 0l);
						VO.setName(obj[1] !=null ? obj[1].toString():"");
						deptMap.put((Long)obj[0], VO);
					}
					
					List<IdNameVO> boardList = VO.getIdnameList();
					
					if(boardList ==null || boardList.size() == 0){
						boardList = new ArrayList<IdNameVO>();					
						VO.setIdnameList(boardList);
					}
					
						if(obj[2] !=null && (boardList !=null)){
							
							if(boardList.size()==0){
								IdNameVO inneVo = new IdNameVO();
								inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
								inneVo.setName(obj[3] !=null ? obj[3].toString():"");
								boardList.add(inneVo);
							}else if(boardList.size()>0){
								for (IdNameVO idNameVO : boardList) {
									if(idNameVO.getId() != null && !(idNameVO.getId().equals((	Long)obj[2]))){
										IdNameVO inneVo = new IdNameVO();
										inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
										inneVo.setName(obj[3] !=null ? obj[3].toString():"");
										boardList.add(inneVo);
										break;
									}else{
										IdNameVO inneVo = new IdNameVO();
										inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
										inneVo.setName(obj[3] !=null ? obj[3].toString():"");
										boardList.add(inneVo);
										break;
									}
								}
							}
						}else{
							IdNameVO inneVo = new IdNameVO();
							inneVo.setId(null);
							inneVo.setName(obj[3] !=null ? obj[3].toString():"ANY CORPORATION / BOARD ");
							boardList.add(inneVo);
						}
				}				
			}
			
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at setDataToDeptBoardMap", e);
		}
		return deptMap;
	}
	
	
	public List<NominatedPostVO> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValues,List<Long> deptIds,
			List<Long> boardIds,String statusType,String task){
		
		List<NominatedPostVO> finalList = new ArrayList<NominatedPostVO>(0);
		
		try{			
			
			//Setting default "Any Post" key and Name to the finalMap 
			Map<Long,NominatedPostVO> finalMap = new HashMap<Long, NominatedPostVO>();
			
			Object[] anyObj = {null,""};
			List<Object[]> anyTypeListObj = new ArrayList<Object[]>();
			anyTypeListObj.add(anyObj);
			
			finalMap = setDataToPostWiseDetailsMap(anyTypeListObj,finalMap,null);
			
			
			List<Long> mandalList = new ArrayList<Long>(0);
			List<Long> townList = new ArrayList<Long>(0);
			List<Long> divisonList = new ArrayList<Long>(0);
			
			//List<Object[]> deptBoardObj = new ArrayList<Object[]>(0);
			
			/*List<Object[]> mandalObj = new ArrayList<Object[]>(0);
			List<Object[]> townObj = new ArrayList<Object[]>(0);
			List<Object[]> divObj = new ArrayList<Object[]>(0);*/
			
			if(boardLevelId.equals(5l)){
				if(levelValues !=null && levelValues.size()>0){
					for (Long manTowDivId : levelValues) {
						
						String mtdId = manTowDivId.toString();
						char temp = mtdId.charAt(0);
						boardLevelId=Long.parseLong(temp+"");
						if(boardLevelId==4l){
							mandalList.add(Long.parseLong(mtdId.substring(1)));
							boardLevelId = 5l;
						}else if(boardLevelId==5l){
							townList.add(Long.parseLong(mtdId.substring(1)));
							boardLevelId = 6l;
						}else if(boardLevelId==6l){
							divisonList.add(Long.parseLong(mtdId.substring(1)));
							boardLevelId = 7l;
						}						
					}
				}
				
				if(mandalList !=null && mandalList.size()>0){
					
				//getting status wise total positions
					if(task !=null && task.trim().equalsIgnoreCase("Total")){
						List<Object[]> mandalObj = nominatedPostDAO.getNominatedPostsByBoardsAndDeptsForOpen(5l,mandalList,deptIds,boardIds,statusType);
						finalMap = setDataToPostWiseDetailsMap(mandalObj,finalMap,null);
					}else{
						List<Object[]> mandalObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(5l,mandalList,deptIds,boardIds,statusType);
						
						if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
							finalMap = setDataToPostWiseDetailsMap(mandalObj,finalMap,null);
						}else{
							finalMap = setDataToPostWiseDetailsMap(mandalObj,finalMap,"notRecieved");
						}
						
					}
					
					
					
					if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
				//Nomination Post Status Wise  details For Post and Any Post Related
					
						//postionId,position,nomiatedPostStatusId,status,count
						List<Object[]> totalApplicnReceived = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(5l,mandalList,deptIds,boardIds,statusType,"post");
						
						if(totalApplicnReceived !=null && totalApplicnReceived.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived,"applicationStatus");
						}
						
						List<Object[]> totalApplicnReceived1 = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
						
						if(totalApplicnReceived1 !=null && totalApplicnReceived1.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived1,"applicationStatus");
						}
						
						List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(5l,mandalList,deptIds,boardIds,statusType,"post");
						
						if(deptsObj !=null && deptsObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
						}
							
						List<Object[]> deptsObj1  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsObj1 !=null && deptsObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj1,"nominatedStatus");
						}
						/*List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(5l,mandalList,deptIds,boardIds,statusType,"anyPost");					
						if(deptsAnyObj !=null && deptsAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
						}*/
					
				//Application Status Wise  details For Post and Any Post Related
					
						//postionId,position,applicationStatusId,status,count
						List<Object[]> applicationSttusObj = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						List<Object[]> applicationSttusObj1 = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusObj1 !=null && applicationSttusObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj1,"applicationStatus");
						}
						/*List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
						}*/
					}
					
				}
				if(townList !=null && townList.size()>0){
					
					if(task !=null && task.trim().equalsIgnoreCase("Total")){
						List<Object[]> townObj = nominatedPostDAO.getNominatedPostsByBoardsAndDeptsForOpen(6l,townList,deptIds,boardIds,statusType);
						finalMap = setDataToPostWiseDetailsMap(townObj,finalMap,null);
					}else{
						List<Object[]> townObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(6l,townList,deptIds,boardIds,statusType);
						
						if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
							finalMap = setDataToPostWiseDetailsMap(townObj,finalMap,null);
						}else{
							finalMap = setDataToPostWiseDetailsMap(townObj,finalMap,"notRecieved");
						}
						
						
					}
					
					if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
						//postionId,position,nomiatedPostStatusId,status,count
						List<Object[]> totalApplicnReceived = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(6l,townList,deptIds,boardIds,statusType,"post");
						
						if(totalApplicnReceived !=null && totalApplicnReceived.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived,"applicationStatus");
						}
						
						List<Object[]> totalApplicnReceived1 = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(6l,townList,deptIds,boardIds,statusType,"anyPost");
						
						if(totalApplicnReceived1 !=null && totalApplicnReceived1.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived1,"applicationStatus");
						}
						
						List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(6l,townList,deptIds,boardIds,statusType,"post");
						
						if(deptsObj !=null && deptsObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
						}
						
						List<Object[]> deptsObj1  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(6l,townList,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsObj1 !=null && deptsObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj1,"nominatedStatus");
						}
						
						/*List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(6l,townList,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsAnyObj !=null && deptsAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
						}*/
						
						//postionId,position,applicationStatusId,status,count
						List<Object[]> applicationSttusObj = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						List<Object[]> applicationSttusObj1 = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusObj1 !=null && applicationSttusObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj1,"applicationStatus");
						}
						/*List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
						}*/
					}
				}
				if(divisonList !=null && divisonList.size()>0){
					if(task !=null && task.trim().equalsIgnoreCase("Total")){
						List<Object[]> divObj = nominatedPostDAO.getNominatedPostsByBoardsAndDeptsForOpen(7l,divisonList,deptIds,boardIds,statusType);
						finalMap = setDataToPostWiseDetailsMap(divObj,finalMap,null);
					}else{
						List<Object[]> divObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(7l,divisonList,deptIds,boardIds,statusType);
						
						if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
							finalMap = setDataToPostWiseDetailsMap(divObj,finalMap,null);
						}else{
							finalMap = setDataToPostWiseDetailsMap(divObj,finalMap,"notRecieved");
						}
						
						
					}
					
					if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
						//postionId,position,nomiatedPostStatusId,status,count
						List<Object[]> totalApplicnReceived = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(7l,divisonList,deptIds,boardIds,statusType,"post");
						
						if(totalApplicnReceived !=null && totalApplicnReceived.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived,"applicationStatus");
						}
						
						List<Object[]> totalApplicnReceived1 = nominatedPostApplicationDAO.getPositionWiseTotalApplicationsReceived(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
						
						if(totalApplicnReceived1 !=null && totalApplicnReceived1.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived1,"applicationStatus");
						}
						
						List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(7l,divisonList,deptIds,boardIds,statusType,"post");
						
						if(deptsObj !=null && deptsObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
						}
						
						List<Object[]> deptsObj1  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsObj1 !=null && deptsObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj1,"nominatedStatus");
						}
						
						/*List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsAnyObj !=null && deptsAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
						}*/
						
						
						//postionId,position,applicationStatusId,status,count
						List<Object[]> applicationSttusObj = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						List<Object[]> applicationSttusObj1 = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusObj1 !=null && applicationSttusObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj1,"applicationStatus");
						}
						/*List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
						}
						
						
						List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
						}*/
					}
				}
				
			}else{
				
				if(task !=null && task.trim().equalsIgnoreCase("Total")){
					List<Object[]> postObj = nominatedPostDAO.getNominatedPostsByBoardsAndDeptsForOpen(boardLevelId,levelValues,deptIds,boardIds,statusType);
					finalMap = setDataToPostWiseDetailsMap(postObj,finalMap,null);
				}else{
					List<Object[]> postObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(boardLevelId,levelValues,deptIds,boardIds,statusType);
					
					if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
						finalMap = setDataToPostWiseDetailsMap(postObj,finalMap,null);
					}else{
						finalMap = setDataToPostWiseDetailsMap(postObj,finalMap,"notRecieved");
					}
					
					
				}
				
				if(statusType != null && !statusType.equalsIgnoreCase("notRecieved")){
				
						//postionId,position,nomiatedPostStatusId,status,count
						List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
						
						if(deptsObj !=null && deptsObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
						}
						
						/*List<Object[]> deptsObj1  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsObj1 !=null && deptsObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsObj1,"nominatedStatus");
						}
						*/
						
						/*List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
						
						if(deptsAnyObj !=null && deptsAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
						}*/
					
						List<Object[]> totalApplicnReceived = nominatedPostApplicationDAO.getPositionWiseTotalApplicationnsReceived(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
						if(totalApplicnReceived !=null && totalApplicnReceived.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived,"applicationStatus");
						}	
						List<Object[]> totalApplicnReceived1 = nominatedPostApplicationDAO.getPositionWiseTotalApplicationnsReceived(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
						if(totalApplicnReceived1 !=null && totalApplicnReceived1.size()>0){
							finalMap = setDataToFinalMap(finalMap,totalApplicnReceived1,"applicationStatus");
						}
						//postionId,position,applicationStatusId,status,count
						//List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
						List<Object[]> applicationSttusObj = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationnStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
						
						if(applicationSttusObj !=null && applicationSttusObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatusFromFinal");
						}
						
						List<Object[]> applicationSttusObj1 = nominatedPostFinalDAO.getPositionDetaislOfEveryApplicationnStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
						
						if(applicationSttusObj1 !=null && applicationSttusObj1.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusObj1,"applicationStatusFromFinal");
						}
						
						//List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
						
						/*if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
							finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
						}*/
				}
				
			}
			
			
			/*//postionId,position,nomiatedPostStatusId,status,count
			List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds);
			
			if(deptsObj !=null && deptsObj.size()>0){
				finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
			}
			//postionId,position,applicationStatusId,status,count
			List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds);
			
			if(applicationSttusObj !=null && applicationSttusObj.size()>0){
				finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
			}*/
			
			if(finalMap !=null &&  finalMap.size()>0){				
				finalList = new ArrayList<NominatedPostVO>(finalMap.values());
			}
			
			if(finalList !=null && finalList.size()>0){
				for (NominatedPostVO obj : finalList) {				
					Long applicationReceivedCount =0l;					
					List<IdNameVO> totalApplications =  obj.getDistList();
					
					if(totalApplications !=null && totalApplications.size()>0){
						for (IdNameVO idNameVO : totalApplications) {							
							applicationReceivedCount = applicationReceivedCount +  (idNameVO.getCount() !=null ? idNameVO.getCount().longValue():0l);
						}						
						obj.setReceivedCount(applicationReceivedCount);					
					}
				}
			}
			
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getDepartmentWiseBoardAndPositionDetails", e);
		}
		
		return finalList;
	}
	
	public Map<Long,NominatedPostVO> setDataToFinalMap(Map<Long,NominatedPostVO> finalMap,List<Object[]> objArr,String type){
		try{
			
			for (Object[] obj : objArr) {
				NominatedPostVO mainVo =null;
				if(obj[0] ==null){
					 mainVo =	finalMap.get(null);
				}else{
					 mainVo = finalMap.get((Long)obj[0]);
				}

				List<IdNameVO> statusList =new ArrayList<IdNameVO>(0);
				if(mainVo !=null){
					if(type !=null && type.trim().equalsIgnoreCase("nominatedStatus")){
						statusList = mainVo.getIdNameVoList();	
					}else if(type !=null && (type.trim().equalsIgnoreCase("applicationStatus") || type.trim().equalsIgnoreCase("applicationStatusFromFinal"))){
						statusList = mainVo.getDistList();	
					}
				}
				
								
				if(statusList !=null && statusList.size()>0){						
					for (IdNameVO idNameVO : statusList) {							
						if(idNameVO.getId() == (obj[2] !=null ? (Long)obj[2]:0l)){
							idNameVO.setCount((obj[4] !=null ? (Long)obj[4]:0l));//idNameVO.setCount(idNameVO.getCount() + (obj[4] !=null ? (Long)obj[4]:0l));
						}					
						/*
						 * if(type !=null && type.trim().equalsIgnoreCase("applicationStatus")){
							if(idNameVO.getName() != null && idNameVO.getName().trim().equalsIgnoreCase("Shortlisted"))
								idNameVO.setCount(idNameVO.getCount() + (obj[4] !=null ? (Long)obj[4]:0l));
						}else if(idNameVO.getName() != null && !idNameVO.getName().trim().equalsIgnoreCase("Shortlisted")){
							if(idNameVO.getId() == (obj[2] !=null ? (Long)obj[2]:0l)){
								idNameVO.setCount(idNameVO.getCount() + (obj[4] !=null ? (Long)obj[4]:0l));
							}
						}
						else{
							idNameVO.setCount((obj[4] !=null ? (Long)obj[4]:0l));
						}
						*/
					}						
				}
			}
			
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at setDataToFinalMap", e);
		}
		return finalMap;
	}
	
	public List<IdNameVO> getAllNominatedStatusList(){
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		
		try{
			List<Object[]> objList  = nominatedPostStatusDAO.getAllNominatedStatusList();
			if(objList !=null && objList.size()>0){
				String[] setterPropertiesList = {"id","name"};
				finalList = setterAndGetterUtilService.setValuesToVO(objList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getAllNominatedStatusList", e);
		}
		return finalList;
	}
	
	public List<IdNameVO> getAllApplicationStatusList(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		
		try{
			List<Object[]> objList  = applicationStatusDAO.getAllApplicationStatusList();
			if(objList !=null && objList.size()>0){
				String[] setterPropertiesList = {"id","name"};
				finalList = setterAndGetterUtilService.setValuesToVO(objList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getAllApplicationStatusList", e);
		}
		return finalList;
	}	
	
	/**
	 * @Author  SRAVANTH
	 * @Version NominatedPostProfileService.java  July 21, 2016 06:00:00 PM 
	 * @return status
	 * description  { Updating any post members to particular post }
	 */
	public String savingAnyPostCandidatesToPosition(final Long userId,final Long applicationId,final Long candidateId,final Long levelId,final Long levelValue,
													final Long deptId,final Long boardId,final Long positionId,final Long statusId,final String comment){
		String status = null;
		try {
			Long memeberId = (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					List<NominatedPostFinal> nomianationPostFinalList = nominatedPostFinalDAO.getNominatedPostApplicationDetailsByApplciationId(applicationId);
					NominatedPostFinal nominatedPostFinal = null;
					Long nominatedPostMemberId = 0L;
					if(commonMethodsUtilService.isListOrSetValid(nomianationPostFinalList)){
						nominatedPostFinal = nomianationPostFinalList.get(0);
						nominatedPostMemberId = nominatedPostFinal.getNominatedPostMemberId();
					}
					
					if(nominatedPostFinal == null){
						nominatedPostFinal = new NominatedPostFinal();
						nominatedPostFinal.setNominationPostCandidateId(candidateId);
						nominatedPostFinal.setInsertedBy(userId);
						nominatedPostFinal.setNominatedPostApplicationId(applicationId);
						nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
						
						if(nominatedPostMemberId != null && nominatedPostFinal != null && nominatedPostMemberId.longValue() > 0l){
							List<NominatedPost> nominatedPostObjList = nominatedPostDAO.getNominatedPostDetailsByNominatedPostMember(nominatedPostMemberId);
							if(commonMethodsUtilService.isListOrSetValid(nominatedPostObjList))
								nominatedPostFinal.setNominatedPostId(nominatedPostObjList.get(0).getNominatedPostId());
							nominatedPostFinal.setNominatedPostMemberId(nominatedPostMemberId);
						}
					}
					
					
					//Long nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
					if(nominatedPostMemberId != null && nominatedPostMemberId.longValue() > 0l){
						
						
						//NominatedPostFinal nominatedPostFinal = new NominatedPostFinal();
						  
						//nominatedPostFinal.setNominatedPostMemberId(nominatedPostMemberId);
						//nominatedPostFinal.setNominationPostCandidateId(candidateId);
						nominatedPostFinal.setApplicationStatusId(statusId);
						//nominatedPostFinal.setInsertedBy(userId);
						//nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostFinal.setUpdatedBy(userId);
						nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostFinal.setIsDeleted("N");
						nominatedPostFinal.setIsPrefered("N");
						nominatedPostFinal.setIsExpired("N");
						nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
						
						NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(applicationId);
						
						savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
						
						nominatedPostApplication.setNominatedPostMemberId(nominatedPostMemberId);
						nominatedPostApplication.setApplicationStatusId(statusId);
						//nominatedPostApplication.setDepartmentId(deptId);
						//nominatedPostApplication.setBoardId(boardId);
						//nominatedPostApplication.setPositionId(positionId);
						nominatedPostApplication.setUpdatedBy(userId);
						nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
						
						NominatedPostComment nominatedPostComment = new NominatedPostComment();
						
						nominatedPostComment.setNominatedPostApplicationId(applicationId);
						nominatedPostComment.setRemarks(comment);  
						nominatedPostComment.setInsertedBy(userId);
						nominatedPostComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostComment = nominatedPostCommentDAO.save(nominatedPostComment);
						
					}
					return nominatedPostMemberId;
				}
			});
			if(memeberId == null)
				status="failure";
			else
				status = "success";
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
			LOG.error("Exception raised at savingAnyPostCandidatesToPosition", e);
		}
		return status;
	}
	
	public Map<Long,NominatedPostVO> setDataToPostWiseDetailsMap(List<Object[]> postObj,Map<Long,NominatedPostVO> finalMap,String status){
		try{
			//total Positions Available to the Requested inputs
			if(postObj !=null && postObj.size()>0){
				for (Object[] obj : postObj) {					
					NominatedPostVO VO = new NominatedPostVO();
					if(obj[0] !=null){
						VO.setId((Long)obj[0]);//postionId
					}else{
						VO.setId(null);
					}
					VO.setName(obj[1] !=null ? obj[1].toString():"");//positionName					
					VO.setIdNameVoList(getAllNominatedStatusList());//statusList 
					VO.setDistList(getAllApplicationStatusList());// for all status
					if(status != null && status.equalsIgnoreCase("notRecieved"))
						VO.setCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
					
					finalMap.put(VO.getId(), VO);
				}
			}
		}catch (Exception e) {
			LOG.error("Exceptionr riased at setDataToDeptBoardMap", e);
		}
		return finalMap;
	}
	/**
	 * @Author  Santosh & srishailam
	 * @Version NominatedPostProfileService.java  July 22, 2016 06:00:00 PM 
	 * @return List<IdNameVO>
	 * description  { This service is used to get final Review Candidate count  }
	 */
	public List<IdNameVO> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId,String status){
		List<IdNameVO> fnlCnddtCuntLst = new ArrayList<IdNameVO>(0);
		  try{
			  
			  Map<Long,IdNameVO> finalMap = new HashMap<Long, IdNameVO>();
			  
			  List<Long> mandalList = new ArrayList<Long>();
			  List<Long> townList = new ArrayList<Long>();
			  List<Long> divisonList = new ArrayList<Long>();			  
			  
			  List<Long> statusList = nominatedPostStatusDAO.getStatusIdsList();
				List<Object[]> levelWiseAvailablePostsList = nominatedPostDAO.getAvaiablePostDetails(LocationLevelId,null,null,statusList,null);
				Map<Long,Map<String,Long>> movedPostsStatusDetailsMap = new HashMap<Long, Map<String,Long>>(0);
				if(commonMethodsUtilService.isListOrSetValid(levelWiseAvailablePostsList)){
					for (Object[] param : levelWiseAvailablePostsList) {
						String statusStr = commonMethodsUtilService.getStringValueForObject(param[1]);
						Long memberId = commonMethodsUtilService.getLongValueForObject(param[7]);
						Long count = commonMethodsUtilService.getLongValueForObject(param[3]);
						
							Map<String,Long> posionwiseMovedMap = new HashMap<String, Long>(0);
							 if(statusStr.trim().equalsIgnoreCase("2")){// nominatedPostStatusid
								
								Long filledCount =0L;
								if(movedPostsStatusDetailsMap.get(memberId) != null){
									posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
									if(posionwiseMovedMap.get("READY FOR FINAL REVIEW") != null)
										filledCount = posionwiseMovedMap.get("READY FOR FINAL REVIEW");
								}
								
								filledCount = filledCount+count;
								posionwiseMovedMap.put("READY FOR FINAL REVIEW", filledCount);
								movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
							}
							else if(statusStr.trim().equalsIgnoreCase("3")){// nominatedPostStatusid
									
									Long filledCount =0L;
									if(movedPostsStatusDetailsMap.get(memberId) != null){
										posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
										if(posionwiseMovedMap.get("FINALIZED") != null)
											filledCount = posionwiseMovedMap.get("FINALIZED");
									}
									
									filledCount = filledCount+count;
									posionwiseMovedMap.put("FINALIZED", filledCount);
									movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
							}
							else if(statusStr.trim().equalsIgnoreCase("4")){//nominatedPostStatusid
									Long filledCount =0L;
									if(movedPostsStatusDetailsMap.get(memberId) != null){
										posionwiseMovedMap = movedPostsStatusDetailsMap.get(memberId);
										if(posionwiseMovedMap.get("GO ISSUED / COMPLETED") != null)
											filledCount = posionwiseMovedMap.get("GO ISSUED / COMPLETED");
									}
									
									filledCount = filledCount+count;
									posionwiseMovedMap.put("GO ISSUED / COMPLETED", filledCount);
									movedPostsStatusDetailsMap.put(memberId, posionwiseMovedMap);
							}
					}
				}
				
				//Map<Long,Long> memberwisePostsCountMap = new HashMap<Long, Long>(0);
			  if(LocationLevelId.equals(5l)){
				    if(lctnLevelValueList !=null && lctnLevelValueList.size()>0){
			          for (Long manTowDivId : lctnLevelValueList) {
			        	  
			            String mtdId = manTowDivId.toString();
			            char temp = mtdId.charAt(0);
			            Long firstChar=Long.parseLong(temp+"");
			            if(firstChar==4l){
			              mandalList.add(Long.parseLong(mtdId.substring(1)));
			            }else if(firstChar==5l){
			              townList.add(Long.parseLong(mtdId.substring(1)));
			            }else if(firstChar==6l){
			              divisonList.add(Long.parseLong(mtdId.substring(1)));
			            }            
			          }
			        }
			        if(mandalList !=null && mandalList.size()>0){
			        	 List<Object[]> mandalObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(5l, mandalList, departmentId, boardId,status);
			        	 List<Object[]> mandalWishList = nominatedPostFinalDAO.getWishListCount(5l, mandalList, departmentId, boardId);
					     finalMap = setDataToMapForFinalReview(mandalObjList,finalMap,movedPostsStatusDetailsMap,status);
					     if(departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
					    	 setWishListCountToVO(mandalWishList,finalMap);
			        }
			        if(townList != null && townList.size() > 0){
			        	List<Object[]> townObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(6l, townList, departmentId, boardId,status);
					      finalMap =  setDataToMapForFinalReview(townObjList,finalMap,movedPostsStatusDetailsMap,status);
					      List<Object[]> townWishList = nominatedPostFinalDAO.getWishListCount(6l, townList, departmentId, boardId);  
					      if(departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
					    	  setWishListCountToVO(townWishList,finalMap);
			        }
			        if(divisonList != null && divisonList.size()>0){
			        	 List<Object[]> divObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(7l, divisonList, departmentId, boardId,status);
					        finalMap = setDataToMapForFinalReview(divObjList,finalMap,movedPostsStatusDetailsMap,status);
					        List<Object[]> divWishList = nominatedPostFinalDAO.getWishListCount(7l, divisonList, departmentId, boardId); 
					        if(departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
					        	setWishListCountToVO(divWishList,finalMap);
			        }
			  }else{
				  List<Object[]> rtrnObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(LocationLevelId, lctnLevelValueList, departmentId, boardId,status);
				  finalMap = setDataToMapForFinalReview(rtrnObjList,finalMap,movedPostsStatusDetailsMap,status);
				  List<Object[]> returnWishList = nominatedPostFinalDAO.getWishListCount(LocationLevelId, lctnLevelValueList, departmentId, boardId);
				  if(departmentId != null && departmentId.longValue() > 0l && boardId != null && boardId.longValue() > 0l)
					  setWishListCountToVO(returnWishList,finalMap);
			  }
				
			 /* for (Long  deptId: finalMap.keySet()) {
				  IdNameVO deptVo = finalMap.get(deptId);
				  movedPostsStatusDetailsMap.get(deptVo.getMemberId());
				  	if(status != null && status.trim().equalsIgnoreCase("finalReview")){
				  		
				  	}
			  }*/
			  
			  if(finalMap !=null && finalMap.size() > 0){
				  fnlCnddtCuntLst = new ArrayList<IdNameVO>(finalMap.values());
			  }
			  //Setting Open Post Count only instead of total post Count in the case of Total Status  
			  if(status != null && status.equalsIgnoreCase("Total")){
				   mandalList.clear();
				   townList.clear();
				   divisonList.clear();	
				   
				   if(fnlCnddtCuntLst != null && fnlCnddtCuntLst.size() > 0l){
						  for(IdNameVO VO:fnlCnddtCuntLst){
							  VO.setCount(0l);//clearing total post cnt
						  }
					}
				   
				  if(LocationLevelId.equals(5l)){
				        if(lctnLevelValueList !=null && lctnLevelValueList.size()>0){
				          for (Long manTowDivId : lctnLevelValueList) {
				        	  
					            String mtdId = manTowDivId.toString();
					            char temp = mtdId.charAt(0);
					            Long firstChar=Long.parseLong(temp+"");
					            if(firstChar==4l){
					              mandalList.add(Long.parseLong(mtdId.substring(1)));
					            }else if(firstChar==5l){
					              townList.add(Long.parseLong(mtdId.substring(1)));
					            }else if(firstChar==6l){
					              divisonList.add(Long.parseLong(mtdId.substring(1)));
					            }            
				          }
				        }
				        if(mandalList !=null && mandalList.size()>0){
				        	 List<Object[]> mandalObjList = nominatedPostDAO.getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(5l, mandalList, departmentId, boardId);
				        	 setOpenCntToResultLst(mandalObjList,fnlCnddtCuntLst);
				        }
				        if(townList != null && townList.size() > 0){
				        	List<Object[]> townObjList = nominatedPostDAO.getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(6l, townList, departmentId, boardId);
				        	setOpenCntToResultLst(townObjList,fnlCnddtCuntLst);
					    }
				        if(divisonList != null && divisonList.size()>0){
				        	 List<Object[]> divObjList = nominatedPostDAO.getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(7l, divisonList, departmentId, boardId);
				        	 setOpenCntToResultLst(divObjList,fnlCnddtCuntLst);
					    }
				}else{
					  List<Object[]> rtrnOpenCntOblLst = nominatedPostDAO.getNominatedOpenPostCntBasedOnDeptBoardAndPositionWise(LocationLevelId,lctnLevelValueList, departmentId, boardId);
					  setOpenCntToResultLst(rtrnOpenCntOblLst,fnlCnddtCuntLst);
				}
			 }
			  Collections.sort(fnlCnddtCuntLst, new Comparator<IdNameVO>() {
					public int compare(IdNameVO o1, IdNameVO o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			  
		 }catch(Exception e) {
			 LOG.error("Exceptionr riased at getFinalReviewCandidateCountLocationWise in NominatedPostProfileService class", e); 
		 }
		  return fnlCnddtCuntLst;
	}
	public void setOpenCntToResultLst(List<Object[]> objList,List<IdNameVO> resultList){
		   if(objList != null && objList.size() > 0){
			   for(Object[] param:objList){
				   IdNameVO VO = getMatchVO(commonMethodsUtilService.getLongValueForObject(param[0]),resultList);   
				   		if(VO != null){
				   			VO.setCount(VO.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
				   		}
			   }
		   } 
	}
    @SuppressWarnings("null")
	public IdNameVO getMatchVO(Long id,List<IdNameVO> resultList){
    	if(resultList == null && resultList.size() == 0l){
    		return null;
    	}
    	for(IdNameVO VO:resultList){
    		if(VO.getId().equals(id)){
    			return VO;
    		}
    	}
    	return null;
    }
	public Map<Long,IdNameVO> setDataToMapForFinalReview(List<Object[]> rtrnObjList,Map<Long,IdNameVO> finalMap,Map<Long,Map<String,Long>> movedPostsStatusDetailsMap,String status){
		try{
			if(rtrnObjList != null && !rtrnObjList.isEmpty()){
		    	for (Object[] obj : rtrnObjList) {
		    		
		    		IdNameVO vo  = finalMap.get(obj[0] !=null ? (Long)obj[0]:0l);
		    		if(vo == null){
		    			vo = new IdNameVO();
		    			 vo.setId((Long)obj[0]);
						 vo.setName(obj[1] != null ? obj[1].toString(): "");
						 vo.setApplicationStatusId(obj[2] != null ? (Long)obj[2]: 0l);
						 vo.setApplicationStatus(obj[3] != null ? obj[3].toString() : "");
						 finalMap.put((Long)obj[0], vo);
		    		}
		    		if(movedPostsStatusDetailsMap.get(obj[5] != null ? (Long)obj[5]:0l) != null){
		    			Map<String,Long> statusWiseMap = movedPostsStatusDetailsMap.get(obj[5] != null ? (Long)obj[5]:0l);
		    			Long count =0L;
		    			if(status.equalsIgnoreCase("finalReview")){
		    				//count = statusWiseMap.get("READY FOR FINAL REVIEW");// santhosh and srishailam 
		    				count = commonMethodsUtilService.getLongValueForObject(obj[4]); //  in ready to final review overriding the existing value which is already set in previous iteration
		    			}
		    			else if(status.equalsIgnoreCase("finaliZed"))
		    				count = statusWiseMap.get("FINALIZED");
		    			else if(status.equalsIgnoreCase("goPassed"))
		    				count = statusWiseMap.get("GO ISSUED / COMPLETED");
		    			
		    			count = count!= null?count:0L;
		    			
		    			vo.setCount(vo.getCount() +count);
		    		}else{
		    			vo.setCount(vo.getCount() +( obj[4] != null ? (Long)obj[4]:0l));
		    		}
				}
		    }
			
		}catch (Exception e) {
			 LOG.error("Exceptionr riased at setDataToMapForFinalReview in NominatedPostProfileService class", e); 
		}
		return finalMap;
	}

public  List<CadreCommitteeVO> notCadresearch(String searchType,String searchValue){
		List<CadreCommitteeVO>  finalList = null;
		 
		 try {
			  
			    List<Object[]> membersList = nominationPostCandidateDAO.notCadresearch(searchType,searchValue);
			      if(membersList!=null && membersList.size()>0){
			    	  finalList = new ArrayList<CadreCommitteeVO>();
			    		   for(Object[] obj: membersList){
			    			   CadreCommitteeVO vo = new CadreCommitteeVO();
			    			   vo.setTdpCadreId(obj[0]!=null?(Long)obj[0]:0l);//nominatedpostCandidateId
			    			   vo.setId(commonMethodsUtilService.getLongValueForObject(obj[7]));//tdpcadreId
			    			   vo.setMemberShipCardId("");
			    			   vo.setMobileNo(obj[1]!=null?obj[1].toString():"");
			    			   vo.setCadreName(obj[2]!=null?obj[2].toString():"");
			    			   vo.setVoterCardNo(obj[3]!=null?obj[3].toString():"");
			    			   vo.setImageURL(obj[4]!=null?obj[4].toString():null);
			    			   vo.setConstituencyId(obj[5]!=null?(Long)obj[5]:01);
			    			   vo.setConstituency(obj[6]!=null?obj[6].toString():"");
			    			   finalList.add(vo);
				    	}
			    	  }
			        
			   	} catch (Exception e) {
			LOG.error("Exception raised at notCadresearch() method of NominatedPostProfileService", e);
		}
		 return finalList;
	 }
	
	// adding new service method
	
		public ResultStatus saveNotcadreRegistrationPost(final  AddNotcadreRegistrationVO notcadreRegistrationVO,final Map<File,String> mapfiles,final Long loggerUserId){
			//LOG.info("Entered into the savechangeNotcadreRegistrationPost service method");
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
						
						UserAddress UA = new UserAddress();
						UA.setHouseNo(notcadreRegistrationVO.getHouseno() != null?notcadreRegistrationVO.getHouseno():null);
						UA.setAddressLane1(notcadreRegistrationVO.getAddress1()!= null?notcadreRegistrationVO.getAddress1():null);
						UA.setAddressLane2(notcadreRegistrationVO.getAddress2() != null?notcadreRegistrationVO.getAddress2():null);
						UA.setPinCode(notcadreRegistrationVO.getPincode() != null?notcadreRegistrationVO.getPincode():null);
						UA.setState(notcadreRegistrationVO.getStateId() != null?stateDAO.get(notcadreRegistrationVO.getStateId()):null);
						UA.setDistrict(notcadreRegistrationVO.getDistrictId() != null?districtDAO.get(notcadreRegistrationVO.getDistrictId()):null);
						UA.setConstituency(notcadreRegistrationVO.getConstituencyId() != null?constituencyDAO.get(notcadreRegistrationVO.getConstituencyId()):null);
						UA.setCountry(countryDAO.get(1L));
						//UA.setPanchayatId(notcadreRegistrationVO.getPanchayatId() != null?notcadreRegistrationVO.getPanchayatId():null);
						
						String mandalORLEBIdStr = notcadreRegistrationVO.getMandalId() != null && notcadreRegistrationVO.getMandalId().longValue()>0L?notcadreRegistrationVO.getMandalId().toString():"";
						if(mandalORLEBIdStr != null && !mandalORLEBIdStr.trim().isEmpty())
						{
							char digit = mandalORLEBIdStr.charAt(0);
							Long temp = Long.parseLong(digit+"");
							
							if(temp.longValue() == 2L)
								UA.setTehsil(tehsilDAO.get(Long.valueOf(notcadreRegistrationVO.getMandalId().toString().substring(1))));
							else if(temp.longValue() == 1L)
								UA.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(notcadreRegistrationVO.getMandalId().toString().substring(1))));
						}
						
						String villageORWardIdStr = notcadreRegistrationVO.getPanchayatId() != null && notcadreRegistrationVO.getPanchayatId().longValue()>0L?notcadreRegistrationVO.getPanchayatId().toString():"";
						if(villageORWardIdStr != null && !villageORWardIdStr.trim().isEmpty())
						{
							char digit = villageORWardIdStr.charAt(0);
							Long temp = Long.parseLong(digit+"");
							
							if(temp.longValue() == 2L)
								UA.setWard(constituencyDAO.get(Long.valueOf(notcadreRegistrationVO.getPanchayatId().toString().substring(1))));
							else if(temp.longValue() == 1L)
								UA.setPanchayatId(Long.valueOf(notcadreRegistrationVO.getPanchayatId().toString().substring(1)));
						}
						
						
					    UA=  userAddressDAO.save(UA);
						//UA.getUserAddressId();
                        NominationPostCandidate NPC=new NominationPostCandidate();
                        
                        List<Long> voterIdsList = voterDAO.getVoterIdByIdCardNoNew(notcadreRegistrationVO.getVoterId().toString());
                        Long voterId = commonMethodsUtilService.isListOrSetValid(voterIdsList)?voterIdsList.get(0):null;
                        
                        NPC.setVoterId(voterId != null ? voterId : null);
                        NPC.setCandidateName(notcadreRegistrationVO.getName()!=null?notcadreRegistrationVO.getName():null);
	                    NPC.setAge(notcadreRegistrationVO.getAge()!=null?notcadreRegistrationVO.getAge():null);
	                    NPC.setDob(notcadreRegistrationVO.getDob() != null? notcadreRegistrationVO.getDob():null);
	                    List<NominatedPostAgeRange> nominatedPostAgeRanges = nominatedPostAgeRangeDAO.getAll();
						Long nominatedPostAgeRangeId =1L;
						Long age = NPC.getAge();
						
						if(commonMethodsUtilService.isListOrSetValid(nominatedPostAgeRanges)){
							for (NominatedPostAgeRange rangevo : nominatedPostAgeRanges) 
								if(rangevo.getMinValue().longValue()>= age && rangevo.getMaxValue().longValue() <= age.longValue()){
									nominatedPostAgeRangeId = rangevo.getNominatedPostAgeRangeId();break;}
						}
						
						NPC.setNominatedPostAgeRangeId(nominatedPostAgeRangeId);
						
	                    NPC.setGender(notcadreRegistrationVO.getGender()!=null?notcadreRegistrationVO.getGender():null);
	                    NPC.setHouseno(notcadreRegistrationVO.getHouseno()!=null?notcadreRegistrationVO.getHouseno():null);
	                    NPC.setMobileNo(notcadreRegistrationVO.getMobileno()!=null?notcadreRegistrationVO.getMobileno():null);
	                    NPC.setRelativename(notcadreRegistrationVO.getRelativename()!=null?notcadreRegistrationVO.getRelativename():null);
	                    if(notcadreRegistrationVO.getRelativetype() !=null && !notcadreRegistrationVO.getRelativetype().trim().isEmpty() &&
	                    		!notcadreRegistrationVO.getRelativetype().trim().equalsIgnoreCase("0")){
	                    	 NPC.setRelativetype(notcadreRegistrationVO.getRelativetype());
	                    }
	                   
	                    NPC.setAddressId(UA.getUserAddressId());
	                    NPC.setCastestateId(notcadreRegistrationVO.getCastestateId() !=null?notcadreRegistrationVO.getCastestateId():null);
	                    Date fromDate = null;
	                    if(notcadreRegistrationVO.getDob() != null && notcadreRegistrationVO.getDob().trim().length()>0){
	                    	 try {
								fromDate = format.parse(notcadreRegistrationVO.getDob().toString());
								NPC.setDob1(fromDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}
	                    }
	                    String folderName = folderCreationForNotCadre();
                    StringBuilder pathBuilder = null; 
	                    for (Map.Entry<File, String> entry : mapfiles.entrySet())
	           		 {
	                    	 pathBuilder = new StringBuilder();
	                    	 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
	                    	// String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	         				String destinationPath = folderName+"/"+randomNumber+"."+entry.getValue();
	         				 pathBuilder.append(IConstants.NOT_CADRE_IMAGES).append("/").append(randomNumber).append(".")
	         				 .append(entry.getValue());
	         				activityService = new ActivityService();
	         				   String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destinationPath);
	         				   LOG.error("Status : "+status);
	         				   if(fileCpyStts.equalsIgnoreCase("success")){
	         					  NPC.setImageurl(pathBuilder.toString());
	         					   LOG.error("Success:"+pathBuilder.toString()+".jpg");
	         				   }else if(fileCpyStts.equalsIgnoreCase("error")){
	         					  rs.setResultCode(1);
	         					 rs.setMessage("FAIL"); 
	         					  LOG.error("Error:"+pathBuilder.toString()+".jpg");
	         				   }
	         			   	
	                  }
	                    NPC.setInsertedBy(loggerUserId);
	                    NPC.setUpdatedBy(loggerUserId);
	                    NPC.setInsertedTime(dateUtilService.getCurrentDateAndTime());
	                    NPC.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
	                    NPC.setIsDeleted("N");
	                    NPC=nominationPostCandidateDAO.save(NPC);
				   
				    rs.setResultCode(0);
				    rs.setMessage("SUCCESS - "+NPC.getNominationPostCandidateId());
				   
			}
		});
	} catch (Exception e) {
		rs.setResultCode(1);
		rs.setMessage("FAIL");
				e.printStackTrace();
				LOG.error("Exception raised at saveNotcadreRegistrationPost", e);
			}
				return rs;
		}
		
	public List<IdNameVO> getCastesForAP(){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L);
		if(castesList != null && castesList.size() >0){
			for(Object[] obj : castesList){
				IdNameVO vo = new IdNameVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				vo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				returnList.add(vo);
			}
		}
		return returnList;
		
	}
	public static String folderCreationForNotCadre(){
	  	 try {
	  		 LOG.debug(" in FolderForNotCadre ");
	  		
	  		 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String notCadreImagesFoldr = ActivityService.createFolder(staticPath+"/images/"+IConstants.NOT_CADRE_IMAGES);
			 
			 String foldrSts = ActivityService.createFolder(notCadreImagesFoldr);
			 if(!foldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/images/"+IConstants.NOT_CADRE_IMAGES;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}
	public  List<CadreCommitteeVO> getNotCadreDetailsById(Long nominatedPostCandiId){
		List<CadreCommitteeVO>  finalList = null;
		 
		 try {
			  
			    List<Object[]> membersList = nominationPostCandidateDAO.getNotCadreDetailsById(nominatedPostCandiId);
			      if(membersList!=null && membersList.size()>0){
			    	  finalList = new ArrayList<CadreCommitteeVO>();
			    		   for(Object[] obj: membersList){
			    			   CadreCommitteeVO vo = new CadreCommitteeVO();
			    			   vo.setTdpCadreId(obj[0]!=null?(Long)obj[0]:0l);
			    			   vo.setMemberShipCardId("");
			    			   vo.setMobileNo(obj[1]!=null?obj[1].toString():"");
			    			   vo.setCadreName(obj[2]!=null?obj[2].toString():"");
			    			   vo.setVoterCardNo(obj[3]!=null?obj[3].toString():"");
			    			   vo.setImageURL(obj[4]!=null?obj[4].toString():null);
			    			   vo.setConstituencyId(obj[5]!=null?(Long)obj[5]:01);
			    			   vo.setConstituency(obj[6]!=null?obj[6].toString():"");
			    			   vo.setId(commonMethodsUtilService.getLongValueForObject(obj[7]));//tdp cadreId
			    			   finalList.add(vo);
				    	}
			    	  }
			        
			   	} catch (Exception e) {
			LOG.error("Exception raised at notCadresearch() method of NominatedPostProfileService", e);
		}
		 return finalList;
	 }
	
	public ResultStatus updateNominatedPostStatusDetails(final Long deptId,final Long boardId,final List<Long> positions,final Long levelId,final List<Long> searchLevelValues,final Long statusId,final Long userId,final Long sizeOfMember){
		ResultStatus status = new ResultStatus();
		try {
			String statusStr = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					List<Long> mandalList = new ArrayList<Long>();
					List<Long> townList = new ArrayList<Long>();
					
					List<NominatedPost> nominatedPostsList = new ArrayList<NominatedPost>(0);
					
					if(levelId.equals(5l)){
						
						if(searchLevelValues !=null && searchLevelValues.size()>0){
							for (Long manTowDivId : searchLevelValues) {
								
								String mtdId = manTowDivId.toString();
								char temp = mtdId.charAt(0);								
								if(temp==4l){
									mandalList.add(Long.parseLong(mtdId.substring(1)));									
								}else if(temp==5l){
									townList.add(Long.parseLong(mtdId.substring(1)));									
								}					
							}
						}
						
						
						if(mandalList !=null && mandalList.size()>0){
							List<NominatedPost> nominatedPostsList1 = nominatedPostDAO.getNominatedPostDetailsBySearchCriteria(deptId,boardId,positions,5l,mandalList);
							
							nominatedPostsList.addAll(nominatedPostsList1); 
							
						}if(townList !=null && townList.size()>0){
							List<NominatedPost> nominatedPostsList2 = nominatedPostDAO.getNominatedPostDetailsBySearchCriteria(deptId,boardId,positions,6l,townList);
							nominatedPostsList.addAll(nominatedPostsList2);
						}
						
					}else{
						 nominatedPostsList = nominatedPostDAO.getNominatedPostDetailsBySearchCriteria(deptId,boardId,positions,levelId,searchLevelValues);
					}
										
					if(commonMethodsUtilService.isListOrSetValid(nominatedPostsList)){
					
						Long i=1l;
						for(NominatedPost nominatedPostObj:nominatedPostsList){
							if(i<=sizeOfMember){
								nominatedPostObj.setNominatedPostStatusId(statusId);
								nominatedPostObj.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								nominatedPostObj.setUpdatedBy(userId);
								nominatedPostDAO.save(nominatedPostObj);
								i++;
							}												
						}
					}
					List<Long> statusIds = new ArrayList<Long>();
					statusIds.add(3l);
					int count =0;
					if(levelId.equals(5l)){
						if(mandalList !=null && mandalList.size()>0){
							List<Long> finalIds = nominatedPostApplicationDAO.getApplicationIds(deptId,boardId,positions,5l,mandalList,userId,statusIds);   //updateApplicationStatusToFinal();
							
							if(finalIds !=null && finalIds.size()>0){
								int count1=nominatedPostApplicationDAO.updateApplicationStatusToFinal(finalIds,userId);
								count = count+count1;
							}
							
						}
						if(townList !=null && townList.size()>0){
							List<Long> finalIds = nominatedPostApplicationDAO.getApplicationIds(deptId,boardId,positions,6l,townList,userId,statusIds); //updateApplicationStatusToFinal(deptId,boardId,positions,6l,townList,userId);
							
							if(finalIds !=null && finalIds.size()>0){
								int count2=nominatedPostApplicationDAO.updateApplicationStatusToFinal(finalIds,userId);
								count = count+count2;
							}						
						}
					}else{
						
						//Shortlisted And Applied applications moved into FinalReview. 
						List<Long> finalIds = nominatedPostApplicationDAO.getApplicationIds(deptId,boardId,positions,levelId,searchLevelValues,userId,statusIds); //updateApplicationStatusToFinal(deptId,boardId,positions,levelId,searchLevelValues,userId);
						
						if(finalIds !=null && finalIds.size()>0){
							count=nominatedPostApplicationDAO.updateApplicationStatusToFinal(finalIds,userId);	
							for (Long appliId : finalIds) {
								savingNominatedPostApplicationHistoryDetails(nominatedPostApplicationDAO.get(appliId),6L,userId);
							}
						}							
					}
					
					/*if(count>0){
						return "success";
					}*/
					
					int finalCount =0;
					if(levelId.equals(5l)){
						if(mandalList !=null && mandalList.size()>0){
							
							
							List<Long> finalIds =  nominatedPostFinalDAO.getApplicationFinalModels(deptId,boardId,positions,5l,mandalList);
							
							if(finalIds !=null){
								int count1 = nominatedPostFinalDAO.updateApplicationStatusToFinalReview(userId,finalIds);
								finalCount = count+count1;
							}
							
						}
						if(townList !=null && townList.size()>0){
							
							List<Long> finalIds =  nominatedPostFinalDAO.getApplicationFinalModels(deptId,boardId,positions,6l,townList);
							if(finalIds !=null){
								int count2 = nominatedPostFinalDAO.updateApplicationStatusToFinalReview(userId,finalIds);
								finalCount=count+count2;
							}
						}
					}else{
						
						List<Long> finalIds =  nominatedPostFinalDAO.getApplicationFinalModels(deptId,boardId,positions,levelId,searchLevelValues);
						
						if(finalIds !=null){
							finalCount = nominatedPostFinalDAO.updateApplicationStatusToFinalReview(userId,finalIds);	
							for (Long finaliId : finalIds) {
								try {
									NominatedPostFinal finalModel = nominatedPostFinalDAO.get(finaliId);
									savingNominatedPostApplicationHistoryDetails(finalModel.getNominatedPostApplication(),6L,userId);
								} catch (Exception e) {
									LOG.error("Exception raised at updateNominatedPostStatusDetails() method of NominatedPostProfileService when application model status changing.", e);
								}
							}
						}
						
						
					}
					if(finalCount>0){
						return "success";
					}
					
					return null;
				}
			});
			if(statusStr != null&& statusStr.equalsIgnoreCase("success")){
			status.setResultCode(0);
			status.setMessage("SUCCESS");
			}
			else
			{
				status.setResultCode(1);
				status.setMessage("FAILURE");
			}
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("FAILURE");
			LOG.error("Exception raised at updateNominatedPostStatusDetails() method of NominatedPostProfileService", e);
		}
		return status;
	}
	
	public NominatedPostReferVO getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,Long statusId){
		NominatedPostReferVO returnvo = new NominatedPostReferVO();
		try {
			
			List<NominatedPostReferVO> subList = new ArrayList<NominatedPostReferVO>();
			List<Long> tdpCadreIds = new ArrayList<Long>();
			Set<Long> candidateIds = new LinkedHashSet<Long>();
			Map<Long,Long> appliedDeptMap = new LinkedHashMap<Long, Long>();
			Map<Long,Long> shortListedDeptMap = new LinkedHashMap<Long, Long>();
			Map<Long,String> partyPositionMap = new LinkedHashMap<Long, String>();
			Map<Long,List<IdNameVO>> reportMap = new LinkedHashMap<Long, List<IdNameVO>>();
			Map<Long,Long> referenceMap = new LinkedHashMap<Long, Long>();
			Map<Long,Long> commentMap = new LinkedHashMap<Long, Long>();
			Map<Long,List<IdNameVO>> nomDocsMap = new LinkedHashMap<Long, List<IdNameVO>>();
			Map<Long,String> publicReprMap = new LinkedHashMap<Long, String>();
			
			Set<Long> applicationIds = new LinkedHashSet<Long>();
			
			
			List<Object[]> list = nominatedPostFinalDAO.getAllReferredMemberDetailsForPosition(levelId, levelValue, departmentId, boardId, positionId,statusId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"nominatedPostFinalId","nominatedPostCandidateId","tdpCadreId","voterId","voterName","voterMoblie","voterGender","age",
						"caste","subCaste","casteName","applStatusId","status","isPrefered","nominatedPostApplicationId","imageURL","cadreName","cadreMobile",
						"cadreAge","cadreGender","candCaste","candCasteName"};
			subList = (List<NominatedPostReferVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostReferVO");
			}
		
			
			if(commonMethodsUtilService.isListOrSetValid(subList)){
				for (NominatedPostReferVO vo : subList) {
					Long cadreId = vo.getTdpCadreId();
					Long nominatedPostCandidateId = vo.getNominatedPostCandidateId();
					Long applicationCandidateId = vo.getNominatedPostApplicationId();
					
					if(cadreId != null && cadreId.longValue() > 0l){
						tdpCadreIds.add(cadreId);
					}
					if(nominatedPostCandidateId != null && nominatedPostCandidateId.longValue() > 0l){
						candidateIds.add(nominatedPostCandidateId);
					}
					if(applicationCandidateId !=null && applicationCandidateId.longValue()>0l){
						applicationIds.add(applicationCandidateId);
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getAnyAppliedDepartmentsCountForCandidateList(candidateIds,departmentId,boardId,0L);
				if(commonMethodsUtilService.isListOrSetValid(totalDepartments)){
					for (Object[] obj : totalDepartments) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						appliedDeptMap.put(id, count);
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = appliedDeptMap.get(vo.getNominatedPostCandidateId());
							 vo.setAppliedDeptCount(count);
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getShortlistedDepartmentsCountForCandidateList(candidateIds,departmentId,boardId);
				if(commonMethodsUtilService.isListOrSetValid(totalDepartments)){
					for (Object[] obj : totalDepartments) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						shortListedDeptMap.put(id, count);
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = shortListedDeptMap.get(vo.getNominatedPostCandidateId());
							 vo.setShortListedDeptCount(count);
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(tdpCadreIds)){
				List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(partyPositionDetails)){
					 for (Object[] obj : partyPositionDetails) {
						 
						 String level = obj[0] != null ? obj[0].toString() : "" ;
						 String role = obj[1] != null ? obj[1].toString() : "";
						 Long cadreId = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
						 String state = commonMethodsUtilService.getStringValueForObject(obj[6]);
						 String commiteestr = obj[2] != null ? obj[2].toString() : "";
						 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
						 {
							 level = state+" "+level;
						 }
						 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
						 partyPositionMap.put(cadreId, partyPositionStr);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 String postion = partyPositionMap.get(vo.getTdpCadreId());
							 vo.setPartyPosition(postion);
						 }
					}
				 }
				 
				 List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(cadrePublicRepresentativList)){
					 for (Object[] obj : cadrePublicRepresentativList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						String pubRep = obj[3] != null ? obj[3].toString():"";
						publicReprMap.put(id, pubRep);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 String pubReprStr = publicReprMap.get(vo.getTdpCadreId());
							 vo.setPublicReprStr(pubReprStr);
						 }
					}
				 }
				 
				 List<Object[]> reportsList = tdpCadreReportDAO.getCadreReportDetailsByCadreList(tdpCadreIds);
				 if(commonMethodsUtilService.isListOrSetValid(reportsList)){
					 for (Object[] obj : reportsList) {
						Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						List<IdNameVO> voList = reportMap.get(cadreId);
						if(voList != null && voList.size() > 0){
							IdNameVO vo = new IdNameVO();
							vo.setId(cadreId);
							vo.setName(obj[1] != null ? obj[1].toString():"");//reportType
							vo.setOrderId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));//statusId
							vo.setStatus(obj[3] != null ? obj[3].toString():"");//status
							vo.setMobileNo(obj[4] != null ? obj[4].toString():"");//reportPath
							voList.add(vo);
						}
						else{
							voList = new ArrayList<IdNameVO>();
							IdNameVO vo = new IdNameVO();
							vo.setId(cadreId);
							vo.setName(obj[1] != null ? obj[1].toString():"");//reportType
							vo.setOrderId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));//statusId
							vo.setStatus(obj[3] != null ? obj[3].toString():"");//status
							vo.setMobileNo(obj[4] != null ? obj[4].toString():"");//reportPath
							voList.add(vo);
							reportMap.put(cadreId, voList);
						}
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue() > 0l){
							 List<IdNameVO> voList = reportMap.get(vo.getTdpCadreId());
							 vo.setIdNamevoList(voList);
						 }
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(applicationIds)){
				List<Object[]> referList = nominatedPostReferDetailsDAO.getReferedCountForCandidateList(applicationIds);
				 if(commonMethodsUtilService.isListOrSetValid(referList)){
					 for (Object[] obj : referList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						referenceMap.put(id, count);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = referenceMap.get(vo.getNominatedPostApplicationId());
						 vo.setReferenceCount(count);
					 }
				 }
				 
				 List<Object[]> nomDocsList = applicationDocumentDAO.getNominatedPostDocumentDetails(applicationIds);
				 if(commonMethodsUtilService.isListOrSetValid(nomDocsList)){
					 for (Object[] obj : nomDocsList) {
						Long applicationId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						String filePath = obj[1] != null ? obj[1].toString():"";
						List<IdNameVO> voList = nomDocsMap.get(applicationId);
						if(voList != null && voList.size() > 0){
							IdNameVO vo = new IdNameVO();
							vo.setId(applicationId);
							vo.setStatus("NP-Profile");
							vo.setMobileNo(filePath);
							voList.add(vo);
						}
						else{
							voList = new ArrayList<IdNameVO>();
							IdNameVO vo = new IdNameVO();
							vo.setId(applicationId);
							vo.setStatus("NP-Profile");
							vo.setMobileNo(filePath);
							voList.add(vo);
							nomDocsMap.put(applicationId, voList);
						}
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						List<IdNameVO> voList = nomDocsMap.get(vo.getNominatedPostApplicationId());
						vo.setNomDocsList(voList);
					}
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(applicationIds)){
				List<Object[]> commentList = nominatedPostCommentDAO.getCommentsCountForCandidateIds(applicationIds);
				if(commonMethodsUtilService.isListOrSetValid(commentList)){
					for (Object[] obj : commentList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						commentMap.put(id, count);
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = commentMap.get(vo.getNominatedPostApplicationId());
						 vo.setCommentCount(count);
					 }
				 }
			}
			
			
			
			//Applied And ShortlistedCounts Of candidate
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){				
				Map<Long,Long> appliedCandidates = new HashMap<Long, Long>();
				Map<Long,Long> shortListedCandidates = new HashMap<Long, Long>();
				
				List<Object[]> appliedCountOfCandidate = nominatedPostApplicationDAO.getApplicationDetailsOfCandidate(candidateIds);	
				
				setStatusWiseCountsMap(appliedCountOfCandidate,appliedCandidates);
				
				List<Object[]> shortlistedCountCandidate= nominatedPostFinalDAO.getShortlistedApplicationDetailsOfCandidate(candidateIds);
				setStatusWiseCountsMap(shortlistedCountCandidate,shortListedCandidates);
				
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					for (NominatedPostReferVO obj : subList) {						
						if(obj.getNominatedPostCandidateId() !=null){							
							if(commonMethodsUtilService.isMapValid(appliedCandidates)){								
								obj.setAppliedCount(appliedCandidates.get(obj.getNominatedPostCandidateId()) !=null ? appliedCandidates.get(obj.getNominatedPostCandidateId()).longValue():0l);
							}
							if(commonMethodsUtilService.isMapValid(shortListedCandidates)){
								obj.setShortListedCount(shortListedCandidates.get(obj.getNominatedPostCandidateId()) !=null ? shortListedCandidates.get(obj.getNominatedPostCandidateId()).longValue():0l);
							}
						}						
					}
				}				
			}
			
			
			returnvo.setSubList(subList);
		} catch (Exception e) {
			LOG.error("Exception raised at getAllReferredMemberDetailsForPosition() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	public IdNameVO getOverAllCommentsForCandidate(Long candidateId,Long applicationId,Long postFinalId){
		IdNameVO returnvo = new IdNameVO();
		try {
			List<IdNameVO> subList = new ArrayList<IdNameVO>();
			List<IdNameVO> subList1 = new ArrayList<IdNameVO>();
			
			List<Object[]> finalList = nominatedPostCommentDAO.getFinalyzedCommentsForCandidate(postFinalId);
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (Object[] obj : finalList) {
					IdNameVO vo = new IdNameVO();
					vo.setStatus(obj[0] != null ? obj[0].toString():"");   //remarks
					vo.setDateStr(obj[1] != null ? obj[1].toString():"");  //time
					vo.setName(obj[2] != null ? obj[2].toString():"");    //firstName
					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");//lastName
					subList.add(vo);
				}
			}
			List<Object[]> shortList = nominatedPostCommentDAO.getShortListingCommentsForCandidate(applicationId);
			if(commonMethodsUtilService.isListOrSetValid(shortList)){
				for (Object[] obj : shortList) {
					IdNameVO vo = new IdNameVO();
					vo.setStatus(obj[0] != null ? obj[0].toString():"");
					vo.setDateStr(obj[1] != null ? obj[1].toString():"");
					vo.setName(obj[2] != null ? obj[2].toString():"");
					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
					subList1.add(vo);
				}
			}
			
			returnvo.setIdnameList(subList);
			returnvo.setSubList1(subList1);
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllCommentsForCandidate() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	@Transactional
	public String updateFinalyzationStatusForPost(final Long postFinalId,final Long statusId,final String comment,final Long userId,final Long postApplicationId,final Long candidateId){
		String status = null;
		try {
			
			Long effectedPostsCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					NominatedPostFinal nominatedPostFinal = nominatedPostFinalDAO.get(postFinalId);
					if(nominatedPostFinal != null)
					{
						Long nominatedPostMemberId = nominatedPostFinal.getNominatedPostMemberId();
						List<NominatedPost> nominatedPostList = nominatedPostDAO.getNominatedPostDetailsByNominatedPostMember(nominatedPostMemberId);
						if(commonMethodsUtilService.isListOrSetValid(nominatedPostList)){
							if(statusId != null && statusId.longValue() == 5l && nominatedPostFinal != null){// finalyzed status id
								//List<NominatedPost> nominatedPostList = nominatedPostDAO.getNominatedPostDetailsByNominatedPostMember(nominatedPostMemberId);
								if(commonMethodsUtilService.isListOrSetValid(nominatedPostList)){
									NominatedPost nominatedPost = nominatedPostList.get(0);
									nominatedPost.setNominationPostCandidateId(candidateId);
									nominatedPost.setNominatedPostStatusId(3l);
									nominatedPost.setUpdatedBy(userId);
									nominatedPost.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									nominatedPost = nominatedPostDAO.save(nominatedPost);
									
									nominatedPostFinal.setNominatedPostId(nominatedPost.getNominatedPostId());
									nominatedPostFinal.setApplicationStatusId(statusId);
									nominatedPostFinal.setUpdatedBy(userId);
									nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
									
									NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(postApplicationId);
									savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
									
									nominatedPostApplication.setApplicationStatusId(statusId);
									nominatedPostApplication.setUpdatedBy(userId);
									nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
									
									changingApplicationsToRejectStatus(nominatedPostFinal.getNominatedPostMemberId(),userId,postApplicationId);
								}
								else{
									
									return 0L; // no open posts are available. so we are unable to assign this candidate to any post.
									
									/*nominatedPostFinal.setApplicationStatusId(statusId);
									nominatedPostFinal.setUpdatedBy(userId);
									nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);*/
								}
							}
							else if(nominatedPostFinal != null){
								
								
								NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(postApplicationId);
								savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
								
								nominatedPostApplication.setApplicationStatusId(statusId);
								nominatedPostApplication.setUpdatedBy(userId);
								nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
								
								//Moving into OPEN Status if Applications Are less 
								if(nominatedPostFinal !=null){							
									Long memberId = nominatedPostFinal.getNominatedPostMemberId();
									if(memberId !=null && memberId>0){
										
										//FinalReview Applications && Posts
										List<Long> applicationList =  nominatedPostFinalDAO.getNominatedPostApplicationIdsByMemberOfFinalReview(memberId);	
										List<NominatedPost> postsList   = nominatedPostDAO.getNominatedPostByMemberOfFinalReview(memberId);
										
										nominatedPostFinal.setApplicationStatusId(statusId);
										nominatedPostFinal.setUpdatedBy(userId);
										nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
										if(nominatedPostFinal != null){
											applicationList.remove(0);// one appln changed to rejected here so am removing one applin 
										}
										//if application count is less than final review posts then we will move that remaining posts to open status
										if(postsList != null && (applicationList == null || applicationList.size()==0)){
											for (NominatedPost nominatedPost : postsList) {											
													nominatedPost.setNominatedPostStatusId(1l);// moving nominated post to open status 
													nominatedPost.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
													nominatedPost.setUpdatedBy(userId);												
													nominatedPostDAO.save(nominatedPost);												
											}	
										}
										else if(postsList !=null && (postsList.size()>applicationList.size())){
											int diff = (postsList.size() - (applicationList !=null ? applicationList.size():0));									
											if(diff>0){		
												int i=1;
												for (NominatedPost nominatedPost : postsList) {											
													if(i<=diff){												
														nominatedPost.setNominatedPostStatusId(1l);// moving nominated post to open status 
														nominatedPost.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
														nominatedPost.setUpdatedBy(userId);												
														nominatedPostDAO.save(nominatedPost);												
														i++;
													}
												}										
											}								
										}
									}
								}
							}
												
							NominatedPostComment nominatedPostComment = new NominatedPostComment();
							nominatedPostComment.setNominatedPostApplicationId(postApplicationId);
							nominatedPostComment.setNominatedPostFinalId(postFinalId);
							nominatedPostComment.setRemarks(comment);
							nominatedPostComment.setInsertedBy(userId);
							nominatedPostComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							nominatedPostComment = nominatedPostCommentDAO.save(nominatedPostComment);
						}
						else{
							return 0L;
						}
					}
					else{
						return 0L;
					}
					return 1L;
				}
			}); 
			
			if(effectedPostsCount != null && effectedPostsCount.longValue()>0)
				status = "success";
			else
				status = "Already all posts are filled out.";
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception raised at updateFinalyzationStatusForPost() method of NominatedPostProfileService", e);
		}
		return status;
	}
	
	/**
	 * @Author  Hymavathi
	 * @Version NominatedPostProfileService.java  Sep 01, 2016 12:00:00 PM 
	 * @return void
	 * description  { If all positions are filled out then remaining applications of same position are making to reject status  }
	 */
	public void changingApplicationsToRejectStatus(Long memberId,final Long userId,final Long postApplicationId){
		try {
			Long openPositions = nominatedPostDAO.getOpenedPositions(memberId);
			
			if(openPositions == null || openPositions.longValue() ==0l){
				int updateCnt = nominatedPostApplicationDAO.updateApllicationStatusToReject(memberId, userId);
					updateCnt = nominatedPostFinalDAO.updateApllicationStatusToReject(memberId, userId);
				List<NominatedPostApplication> nominatedPostApplications = nominatedPostApplicationDAO.getApplicationIdsByMemberId(memberId);
				if(commonMethodsUtilService.isListOrSetValid(nominatedPostApplications)){
					for(NominatedPostApplication nominatedPostApplication : nominatedPostApplications){
						savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,4l,userId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at changingApplicationsToRejectStatus() method of NominatedPostProfileService", e);
		}
	}
	
	public String updateWishListForCandidate(final Long postFinalId,final String remark,final Long userId){
		String status = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					NominatedPostFinal nominatedPostFinal = nominatedPostFinalDAO.get(postFinalId);
					nominatedPostFinal.setUpdatedBy(userId);
					nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					if(remark.equalsIgnoreCase("Y")){
						nominatedPostFinal.setIsPrefered("N");
					}
					else if(remark.equalsIgnoreCase("N")){
						nominatedPostFinal.setIsPrefered("Y");
					}
					nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
				}
			});
			status = "success";
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception raised at updateWishListForCandidate() method of NominatedPostProfileService", e);
		}
		return status;
	}
	
	public NominatedPostDashboardVO getOverAllTotalCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		NominatedPostDashboardVO returnvo = new NominatedPostDashboardVO();
		Map<Long,NominatedPostDashboardVO> casteGroupMap = new HashMap<Long, NominatedPostDashboardVO>();
		Map<Long,NominatedPostDashboardVO> ageRangeMap = new HashMap<Long, NominatedPostDashboardVO>();
		
		try {
			List<NominatedPostDashboardVO> genderList = new ArrayList<NominatedPostDashboardVO>();
			
			List<Object[]> genList = nominatedPostFinalDAO.getGenderWiseTotalCountsForPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,stateId);
			if(commonMethodsUtilService.isListOrSetValid(genList)){
				for (Object[] obj : genList) {
					NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
					vo.setStatusName(obj[0] != null ? obj[0].toString():"");
					vo.setStatusCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					genderList.add(vo);
				}
			}
	/*		Long totalApplicationCount=nominatedPostFinalDAO.getTotalApplicationsByLocation(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,stateId);
			if(genderList != null && genderList.size() > 0 && totalApplicationCount != null ){
				genderList.get(0).setTotalApplicationCount(totalApplicationCount);
			}*/
			List<Object[]> casteGropList = casteCategoryDAO.getCasteCategoryDetails();
			if(commonMethodsUtilService.isListOrSetValid(casteGropList)){ //preparing template for caste group
				for (Object[] obj : casteGropList) {
					NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
					  Long csteGroupId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					  vo.setStatusId(csteGroupId);
					  vo.setStatusName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					  casteGroupMap.put(csteGroupId, vo);
				}
			}
			List<Object[]> casList = nominatedPostFinalDAO.getCasteWiseTotalCountsForPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,stateId);
			if(commonMethodsUtilService.isListOrSetValid(casList)){
				for (Object[] obj : casList) {
					NominatedPostDashboardVO vo = casteGroupMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					 if(vo != null){
					  vo.setStatusCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					 }
					}
			}
			List<Object[]> ageRangeList = nominatedPostAgeRangeDAO.getAllAgeRanges();
		    if(commonMethodsUtilService.isListOrSetValid(ageRangeList)){ // preparing template for age range group
		    	for (Object[] obj : ageRangeList) {
					NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
					Long ageRangeId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					vo.setStatusId(ageRangeId);
					vo.setStatusName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					if(ageRangeId != 6l)
						ageRangeMap.put(ageRangeId, vo);
				}
		    }
			List<Object[]> list = nominatedPostFinalDAO.getAgeGroupWiseTotalCountsForPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,stateId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					NominatedPostDashboardVO vo = ageRangeMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(vo!= null){
						vo.setStatusCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
				}
			}
			
			returnvo.setApplicatnStatsList(genderList);
			if(casteGroupMap != null && !casteGroupMap.isEmpty()){
				returnvo.getNominatedStatusList().addAll(casteGroupMap.values());
			}
			if(ageRangeMap != null && !ageRangeMap.isEmpty()){
				returnvo.getPositinsList().addAll(ageRangeMap.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllTotalCountsByPosition() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	public List<NominatedPostDashboardVO> getCasteGroupWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		List<NominatedPostDashboardVO> returnList = new ArrayList<NominatedPostDashboardVO>();
		try {
			Map<Long,NominatedPostDashboardVO> casteMap = new LinkedHashMap<Long, NominatedPostDashboardVO>();
			Long totalCount = 0l;
			
			//0.casteId,1.caste,2.ageId,3.age,4.gender,5.count.
			List<Object[]> list = nominatedPostFinalDAO.getCasteCategoryGroupWiseCountsForPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,"casteCategory",stateId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long casteId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long ageId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					String gender = obj[4] != null ? obj[4].toString():"";
					Long count = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					NominatedPostDashboardVO castevo = casteMap.get(casteId);
					
					if(castevo == null){
						castevo = new NominatedPostDashboardVO();
						castevo.setId(casteId);
						castevo.setName(obj[1] != null ? obj[1].toString():"");
						List<NominatedPostDashboardVO> ageList = getAgeGroupList();
						NominatedPostDashboardVO vo = getMatchedVOByList(ageList, ageId);
						vo.setId(casteId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
							vo.setMaleCount(count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(count);
						
						castevo.setApplicatnStatsList(ageList);
						casteMap.put(casteId, castevo);
					}
					else{
						NominatedPostDashboardVO vo = getMatchedVOByList(castevo.getApplicatnStatsList(), ageId);
						vo.setId(casteId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
							vo.setMaleCount(vo.getMaleCount()+count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(vo.getFemaleCount()+count);
					}
				}
			}
			Long twentyTo29AgeRangeCount=0l;
			Long thirtyTo39AgeRangeCount=0l;
			Long fourtyTo49AgeRangeCount=0l;
			Long fiftyTo59AgeRangeCount=0l;
			Long sixtyAvoveAgeRangeCount=0l;
			Long overAllCount=0l;
			if(commonMethodsUtilService.isMapValid(casteMap)){
				for (Map.Entry<Long, NominatedPostDashboardVO> entry : casteMap.entrySet()){
					NominatedPostDashboardVO castevo = entry.getValue();
					List<NominatedPostDashboardVO> ageList = castevo.getApplicatnStatsList();
					NominatedPostDashboardVO totalvo = new NominatedPostDashboardVO();
					totalvo.setId(castevo.getId());
					totalvo.setName(castevo.getName());
					totalvo.setStatusName("Total");
					if(commonMethodsUtilService.isListOrSetValid(ageList)){
						for (NominatedPostDashboardVO vo : ageList) {
							totalvo.setMaleCount(totalvo.getMaleCount()+vo.getMaleCount());
							totalvo.setFemaleCount(totalvo.getFemaleCount()+vo.getFemaleCount());
							totalvo.setStatusCount(totalvo.getStatusCount()+vo.getMaleCount()+vo.getFemaleCount());
							totalCount = totalCount+totalvo.getStatusCount();
							overAllCount = overAllCount + vo.getMaleCount()+vo.getFemaleCount();
							if(vo.getStatusId().longValue()==1l){
								twentyTo29AgeRangeCount = twentyTo29AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
							}else if(vo.getStatusId().longValue()==2l){
								thirtyTo39AgeRangeCount = thirtyTo39AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
							}else if(vo.getStatusId().longValue()==3l){
								fourtyTo49AgeRangeCount = fourtyTo49AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
							}else if(vo.getStatusId().longValue()==4l){
								fiftyTo59AgeRangeCount = fiftyTo59AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
							}else{
								sixtyAvoveAgeRangeCount = sixtyAvoveAgeRangeCount +	vo.getMaleCount()+vo.getFemaleCount();
							}
						}
					}
					ageList.add(0, totalvo);
					castevo.setTotalCnt(totalvo.getStatusCount());
				}
			}
			
			if(commonMethodsUtilService.isMapValid(casteMap)){
				for (Map.Entry<Long, NominatedPostDashboardVO> entry : casteMap.entrySet()){
					NominatedPostDashboardVO vo = entry.getValue();
					Long count = vo.getTotalCnt();
					if(totalCount != null && totalCount.longValue() > 0l && count != null && count.longValue() > 0l){
					String percentage = (new BigDecimal((count * 100.0)/overAllCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
					vo.setPercentage(percentage);
					}
				}
			}
			returnList = new ArrayList<NominatedPostDashboardVO>(casteMap.values());
			if(returnList !=null && returnList.size() > 0){
				NominatedPostDashboardVO vo = returnList.get(0);
				  vo.setTwentyTo29AgeRangeCount(twentyTo29AgeRangeCount);
				  vo.setThirtyTo39AgeRangeCount(thirtyTo39AgeRangeCount);
				  vo.setFourtyTo49AgeRangeCount(fourtyTo49AgeRangeCount);
				  vo.setFiftyTo59AgeRangeCount(fiftyTo59AgeRangeCount);
				  vo.setSixtyAvoveAgeRangeCount(sixtyAvoveAgeRangeCount);
				  vo.setOverAllCount(overAllCount);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupWiseCountsByPosition() method of NominatedPostProfileService", e);
		}
		return returnList;
	}
	
	public List<NominatedPostDashboardVO> getCasteWiseCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long stateId){
		List<NominatedPostDashboardVO> returnList = new ArrayList<NominatedPostDashboardVO>();
		try {
			Map<Long,NominatedPostDashboardVO> casteMap = new LinkedHashMap<Long, NominatedPostDashboardVO>();
			Long totalCount = 0l;
			
			//0.casteId,1.caste,2.ageId,3.age,4.gender,5.count.
			List<Object[]> list = nominatedPostFinalDAO.getCasteCategoryGroupWiseCountsForPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId,"casteName",stateId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long casteId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long ageId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					String gender = obj[4] != null ? obj[4].toString():"";
					Long count = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					NominatedPostDashboardVO castevo = casteMap.get(casteId);
					
					if(castevo == null){
						castevo = new NominatedPostDashboardVO();
						castevo.setId(casteId);
						castevo.setName(obj[1] != null ? obj[1].toString():"");
						List<NominatedPostDashboardVO> ageList = getAgeGroupList();
						NominatedPostDashboardVO vo = getMatchedVOByList(ageList, ageId);
						vo.setId(casteId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
							vo.setMaleCount(count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(count);
						
						castevo.setApplicatnStatsList(ageList);
						casteMap.put(casteId, castevo);
					}
					else{
						NominatedPostDashboardVO vo = getMatchedVOByList(castevo.getApplicatnStatsList(), ageId);
						vo.setId(casteId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male") )
							vo.setMaleCount(vo.getMaleCount()+count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(vo.getFemaleCount()+count);
					}
				}
			}
			Long overAllCount=0l;
			if(commonMethodsUtilService.isMapValid(casteMap)){
				for (Map.Entry<Long, NominatedPostDashboardVO> entry : casteMap.entrySet()){
					NominatedPostDashboardVO castevo = entry.getValue();
					List<NominatedPostDashboardVO> ageList = castevo.getApplicatnStatsList();
					NominatedPostDashboardVO totalvo = new NominatedPostDashboardVO();
					totalvo.setId(castevo.getId());
					totalvo.setName(castevo.getName());
					totalvo.setStatusName("Total");
					if(commonMethodsUtilService.isListOrSetValid(ageList)){
						for (NominatedPostDashboardVO vo : ageList) {
							overAllCount = overAllCount +vo.getMaleCount()+vo.getFemaleCount();
							totalvo.setMaleCount(totalvo.getMaleCount()+vo.getMaleCount());
							totalvo.setFemaleCount(totalvo.getFemaleCount()+vo.getFemaleCount());
							totalvo.setStatusCount(totalvo.getStatusCount()+vo.getMaleCount()+vo.getFemaleCount());
							totalCount = totalCount+totalvo.getStatusCount();
						}
					}
					ageList.add(0, totalvo);
					castevo.setTotalCnt(totalvo.getStatusCount());
				}
			}
			
			if(commonMethodsUtilService.isMapValid(casteMap)){
				for (Map.Entry<Long, NominatedPostDashboardVO> entry : casteMap.entrySet()){
					NominatedPostDashboardVO vo = entry.getValue();
					Long count = vo.getTotalCnt();
					if(totalCount != null && totalCount.longValue() > 0l && count != null && count.longValue() > 0l){
					String percentage = (new BigDecimal((count * 100.0)/overAllCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
					vo.setPercentage(percentage);
					}
				}
			}
			returnList = new ArrayList<NominatedPostDashboardVO>(casteMap.values());
			if(returnList !=null && returnList.size() > 0){
				returnList.get(0).setOverAllCount(overAllCount);	
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupWiseCountsByPosition() method of NominatedPostProfileService", e);
		}
		return returnList;
	}
	
	public NominatedPostDashboardVO getMatchedVOByList(List<NominatedPostDashboardVO> voList,Long id){
		NominatedPostDashboardVO returnvo = new NominatedPostDashboardVO();
		try {
			if(commonMethodsUtilService.isListOrSetValid(voList)){
				for (NominatedPostDashboardVO nominatedPostDashboardVO : voList) {
					if(nominatedPostDashboardVO.getStatusId().longValue() == id.longValue()){
						return nominatedPostDashboardVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getMatchedVOByList() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	public List<NominatedPostDashboardVO> getAgeGroupList(){
		List<NominatedPostDashboardVO> voList = new ArrayList<NominatedPostDashboardVO>();
		try {
			List<Object[]> list = nominatedPostAgeRangeDAO.getAllAgeRanges();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
					vo.setStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setStatusName(obj[1] != null ? obj[1].toString():"");
					if(vo.getStatusId() != 6l)
						voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAgeGroupList() method of NominatedPostProfileService", e);
		}
		return voList;
	}
	
	public List<NominatedPostDashboardVO> getCasteWisePositionsCountsByPosition(Long positionId,Long levelId,Long deptId,Long boardId,Long casteGroupId,Long applStatusId,Long casteId,Long stateId){
		List<NominatedPostDashboardVO> returnList = new ArrayList<NominatedPostDashboardVO>();
		try {
			Map<Long,NominatedPostDashboardVO> positionMap = new LinkedHashMap<Long, NominatedPostDashboardVO>();
			
		/*	if(positionId != null && positionId.longValue() > 0l){
				List<Object[]> list = positionDAO.getAllPositions();
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						//NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						positionMap.put(id, null);
					}
				}
			}*/
			
			//0.positionId,1.position,2.ageId,3.age,4.gender,5.count.
			List<Object[]> list = nominatedPostFinalDAO.getCasteWisePositionsCountsByPosition(positionId, levelId, deptId, boardId, casteGroupId, applStatusId, casteId,stateId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long ageId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					String gender = obj[4] != null ? obj[4].toString():"";
					Long count = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
					NominatedPostDashboardVO positionvo = positionMap.get(id);
					
					if(positionvo == null){
						positionvo = new NominatedPostDashboardVO();
						positionvo.setId(id);
						positionvo.setName(obj[1] != null ? obj[1].toString():"");
						List<NominatedPostDashboardVO> ageList = getAgeGroupList();
						NominatedPostDashboardVO vo = getMatchedVOByList(ageList, ageId);
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
							vo.setMaleCount(count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(count);
						vo.setStatusCount(vo.getStatusCount()+count);
						
						positionvo.setApplicatnStatsList(ageList);
						positionMap.put(id, positionvo);
					}
					else{
						NominatedPostDashboardVO vo = getMatchedVOByList(positionvo.getApplicatnStatsList(), ageId);
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
							vo.setMaleCount(vo.getMaleCount()+count);
						else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
							vo.setFemaleCount(vo.getFemaleCount()+count);
						vo.setStatusCount(vo.getStatusCount()+count);
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(positionMap)){
				for (Map.Entry<Long, NominatedPostDashboardVO> entry : positionMap.entrySet()){
					NominatedPostDashboardVO posvo = entry.getValue();
					if(posvo != null){
					List<NominatedPostDashboardVO> ageList = posvo.getApplicatnStatsList();
					NominatedPostDashboardVO totalvo = new NominatedPostDashboardVO();
					totalvo.setId(posvo.getId());
					totalvo.setName(posvo.getName());
					totalvo.setStatusName("Total");
					if(commonMethodsUtilService.isListOrSetValid(ageList)){
						for (NominatedPostDashboardVO vo : ageList) {
							totalvo.setMaleCount(totalvo.getMaleCount()+vo.getMaleCount());
							totalvo.setFemaleCount(totalvo.getFemaleCount()+vo.getFemaleCount());
							totalvo.setStatusCount(totalvo.getStatusCount()+vo.getMaleCount()+vo.getFemaleCount());
						}
					}
					ageList.add(0,totalvo);
					}
				}
			}
			returnList = new ArrayList<NominatedPostDashboardVO>(positionMap.values());
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteWisePositionsCountsByPosition() method of NominatedPostProfileService", e);
		}
		return returnList;
	}
	
	public ResultStatus validateVoterIdCardNo(String voterIdCardNo){
		ResultStatus resultStatus =new ResultStatus();
		//Long finalVoterId = 0l;
		try{
			List<Long> condidateIds=null;
			if(voterIdCardNo !=null && voterIdCardNo.trim().length()>0 && !voterIdCardNo.trim().isEmpty()){
				List<Long> list = voterDAO.getVoterIdByIdCardNoNew(voterIdCardNo);
				if(commonMethodsUtilService.isListOrSetValid(list))
				{
					resultStatus.setResultCode(0);
					resultStatus.setMessage("valid");
					 condidateIds= nominationPostCandidateDAO.getCandidateByVoterId(list.get(0));
					 if(commonMethodsUtilService.isListOrSetValid(condidateIds))
					 resultStatus.setMessage("applied");
				}else{
					resultStatus.setResultCode(1);
				}
				//finalVoterId = commonMethodsUtilService.isListOrSetValid(list)?list.get(0):null;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultStatus;
	}
	
	public List<IdNameVO> getOpenedPositionsBoardLevels(){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = nominatedPostDAO.getBoardLevelsForOpenedPositions();
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getBoardLevels()", e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getStatesForOpenedPositions(Long boardLevelId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = nominatedPostDAO.getStatesForOpenedPositions(boardLevelId);
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getStatesForOpenedPositions()", e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getOpenPositionDistrictsForState(Long stateId,Long boardLevelId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = nominatedPostDAO.getOpenPositionDistrictsForState(stateId,boardLevelId);
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getOpenPositionDistrictsForState()", e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getOpenPositionConstituenciesForDistrict(Long districtId,Long boardLevelId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = nominatedPostDAO.getOpenPositionConstituenciesForDistrict(districtId,boardLevelId);
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getOpenPositionConstituenciesForDistrict()", e);
		}
		return returnList;
	}
	
	public List<LocationWiseBoothDetailsVO> getMandalMuncilIdsForConstituency(Long constituencyId,Long boardLevelId){
		List<LocationWiseBoothDetailsVO> returnList = new ArrayList<LocationWiseBoothDetailsVO>();
		try{
			List<Long> ids = new ArrayList<Long>();
			List<Object[]> list = nominatedPostDAO.getMandalMuncilIdsForConstituency(constituencyId,boardLevelId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long tehsilId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long lebId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					if(lebId != null && lebId.longValue() > 0l){
						lebId = Long.valueOf("1"+lebId.toString());
						ids.add(lebId);
					}
					tehsilId = Long.valueOf("2"+tehsilId.toString());
					ids.add(tehsilId);
				}
			}
			List<LocationWiseBoothDetailsVO> list1 = cadreCommitteeService.getMandalMunicCorpDetails1(constituencyId);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for (LocationWiseBoothDetailsVO vo : list1) {
					Long locationId = vo.getLocationId();
					if(ids.contains(locationId))
						returnList.add(vo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getMandalMuncilIdsForConstituency()", e);
		}
		return returnList;
	}
	
	public List<LocationWiseBoothDetailsVO> getPanchaytWardForMandal(String mandalId,Long constituencyId,Long boardLevelId){
		List<LocationWiseBoothDetailsVO> returnList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			Long id = 0l;
			String type = null;
			List<Long> ids = new ArrayList<Long>();
			
			if((mandalId.substring(0,1)).equalsIgnoreCase("2")){
				id = Long.valueOf(mandalId.substring(1));
				type = "mandal";
			}
			if((mandalId.substring(0,1)) .equalsIgnoreCase("1")){
				id = Long.valueOf(mandalId.substring(1));
				type = "muncipality";
			}
			
			List<Object[]> list = nominatedPostDAO.getPanchayWardIdsForMandal(id, type, constituencyId,boardLevelId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long iid = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					if(type != null && type.equalsIgnoreCase("mandal"))
						iid = Long.valueOf("1"+iid.toString());
					else if(type != null && type.equalsIgnoreCase("muncipality"))
						iid = Long.valueOf("2"+iid.toString());
					ids.add(iid);
					
				}
			}
			List<LocationWiseBoothDetailsVO> list1 = cadreCommitteeService.getPanchayatWardByMandalId(mandalId, constituencyId);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for (LocationWiseBoothDetailsVO vo : list1) {
					Long locationId = vo.getLocationId();
					if(ids.contains(locationId))
						returnList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getPanchaytWardForMandal()", e);
		}
		return returnList;
	}
	public void setWishListCountToVO(List<Object[]> rtrnObjList, Map<Long,IdNameVO> finalMap){
		try{
			if(rtrnObjList != null && !rtrnObjList.isEmpty()){
		    	for (Object[] obj : rtrnObjList) {
		    		
		    		IdNameVO vo  = finalMap.get(obj[0] !=null ? (Long)obj[0]:0l);
		    		if(vo != null){
		    			 vo.setWishCount(vo.getWishCount() +( obj[2] != null ? (Long)obj[2]:0l));
		    		}
		   		 }
		    }
			
		}catch (Exception e) {
			 LOG.error("Exceptionr riased at setWishListCountToVO in NominatedPostProfileService class", e); 
		}
		
	}
	 public List<NominatedPostVO> getAnyDeptApplicationOverviewCountLocationWise(Long departmentId,Long boardId,Long positionId,Long boardLevelId,
		      List<Long> locationValue,Long searchLevelId,String statusStr){
	  
	   List<NominatedPostVO> resultList = new ArrayList<NominatedPostVO>(0);
	   Map<Long,NominatedPostVO> positionMap = new HashMap<Long, NominatedPostVO>(0);
	   try{
		  List<Object[]> rtrnPstnObjLst = nominatedPostDAO.getPositionByLevelId(boardLevelId);
		  if(rtrnPstnObjLst != null && !rtrnPstnObjLst.isEmpty()){
			  for (Object[] param: rtrnPstnObjLst) {
				NominatedPostVO vo = new NominatedPostVO();
				Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
				vo.setId(id);
				vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setTotalApplicationReceivedCnt(0l);
				vo.setPositionLinkedCnt(0l);
				vo.setReadyToShortListedCnt(0l);
				vo.setPstnLnkedAndRjctdCnt(0l);
				vo.setPstnLnkedAndShrtLstdCnt(0l);
				positionMap.put(id, vo);
			}
		  }
		  
		  //Default Any Post adding statically 
		    NominatedPostVO vo = new NominatedPostVO();
		    vo.setId(0l);
		    vo.setName("Any Post");
		    vo.setTotalApplicationReceivedCnt(0l);
		    positionMap.put(0L, vo);
		  
		    List<Object[]> rtrnObjLst = new ArrayList<Object[]>(0);
		    
		    //If Dept is Not NUll
		    		    
		    List<Long> mandalList = new ArrayList<Long>(0);
	    	List<Long> townList = new ArrayList<Long>(0);
	    	
		    if(departmentId !=null && departmentId>0l){
		    	
		    	if(boardLevelId.equals(5l)){
					
					if(locationValue !=null && locationValue.size()>0){
						for (Long manTowDivId : locationValue) {
							
							String mtdId = manTowDivId.toString();
							char temp = mtdId.charAt(0);								
							if(temp==4l){
								mandalList.add(Long.parseLong(mtdId.substring(1)));									
							}else if(temp==5l){
								townList.add(Long.parseLong(mtdId.substring(1)));									
							}					
						}
					}
		    	
		    	
					if(commonMethodsUtilService.isListOrSetValid(mandalList)){
						 List<Object[]> rtrnObjLst1 = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, 5l, mandalList, searchLevelId,"",statusStr);
						 if(commonMethodsUtilService.isListOrSetValid(rtrnObjLst1)){
							 rtrnObjLst.addAll(rtrnObjLst1);
						 }
					}
					if(commonMethodsUtilService.isListOrSetValid(townList)){
						 List<Object[]> rtrnObjLst2 = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, 6l, townList, searchLevelId,"",statusStr);
						 if(commonMethodsUtilService.isListOrSetValid(rtrnObjLst2)){
							 rtrnObjLst.addAll(rtrnObjLst2);
						 }
					}
					
		    	
		    	}else{
		    		rtrnObjLst = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, boardLevelId, locationValue, searchLevelId,"",statusStr);
		    	}
		    	
		    }else{
		    	rtrnObjLst = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, boardLevelId, locationValue, searchLevelId,"",statusStr);
		    }
		    			
		  if(rtrnObjLst != null && !rtrnObjLst.isEmpty() ){
			  for (Object[] param : rtrnObjLst) {
				Long pstnId = commonMethodsUtilService.getLongValueForObject(param[0]);
				Long count = commonMethodsUtilService.getLongValueForObject(param[1]);
				if(pstnId == null){ // any post
					NominatedPostVO anyPostVO = positionMap.get(0l);
					anyPostVO.setTotalApplicationReceivedCnt(count);
				}else{
					  NominatedPostVO nominatedPostVO = positionMap.get(pstnId);	
					 if(nominatedPostVO != null){
						 Long existingCount = nominatedPostVO.getTotalApplicationReceivedCnt() != null ? nominatedPostVO.getTotalApplicationReceivedCnt():0L;
						 nominatedPostVO.setTotalApplicationReceivedCnt(existingCount+ count);
					 }
				}
			}
		  }
		  
		  
		  
		  
		  List<Object[]> rtrnLst = new ArrayList<Object[]>(0);
		  
		  if(departmentId !=null && departmentId>0l){
		    	
		    	if(boardLevelId.equals(5l)){		    		
		    		
		    		if(commonMethodsUtilService.isListOrSetValid(mandalList)){
		    			List<Object[]>	rtrnLst1 = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, 5l, mandalList, searchLevelId,"nominatedPostMemeber",statusStr);
		    			rtrnLst.addAll(rtrnLst1);
		    		}		    		
		    		if(commonMethodsUtilService.isListOrSetValid(townList)){		    			
		    			List<Object[]> rtrnLst2 = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, 6l, townList, searchLevelId,"nominatedPostMemeber",statusStr);
		    			rtrnLst.addAll(rtrnLst2);
		    		}
		    	}else{
		    		rtrnLst = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, boardLevelId, locationValue, searchLevelId,"nominatedPostMemeber",statusStr);
		    	}
		    	
		  }else{
			  rtrnLst = nominatedPostApplicationDAO.getAnyDeptApplicationOverviewCountLocationWise(departmentId, boardId, positionId, boardLevelId, locationValue, searchLevelId,"nominatedPostMemeber",statusStr);
		  }
 
		  if(rtrnLst != null && !rtrnLst.isEmpty()){
			   for (Object[] param : rtrnLst) {
					Long pstnId = commonMethodsUtilService.getLongValueForObject(param[0]);// memberid
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long count = commonMethodsUtilService.getLongValueForObject(param[2]);
					 NominatedPostVO nominatedPostVO = positionMap.get(pstnId);	
					 if(nominatedPostVO != null){
						// nominatedPostVO.setPositionLinkedCnt(nominatedPostVO.getPositionLinkedCnt()+count);
						 if(statusId != null && statusId.longValue()==1l){
						  nominatedPostVO.setReadyToShortListedCnt(count); 
						 }else if(statusId != null && statusId.longValue()==2l){
						  nominatedPostVO.setPstnLnkedAndRjctdCnt(count);	 
						 }else if(statusId != null && statusId.longValue()==3l){
						  nominatedPostVO.setPstnLnkedAndShrtLstdCnt(count); 
						  nominatedPostVO.setPositionLinkedCnt(nominatedPostVO.getPositionLinkedCnt()+count);
						 }else if(statusId != null && statusId.longValue()==5l){
							  nominatedPostVO.setPstnLnkedAndFinalized(count); 
							  nominatedPostVO.setPositionLinkedCnt(nominatedPostVO.getPositionLinkedCnt()+count);
						 }else if(statusId != null && statusId.longValue()==6l){
							  nominatedPostVO.setPstnLnkedAndFinalReview(count); 
							  nominatedPostVO.setPositionLinkedCnt(nominatedPostVO.getPositionLinkedCnt()+count);
						 }
					 }
			}
		   }
		 if(positionMap != null && !positionMap.isEmpty()){
			 resultList.addAll(positionMap.values());
			 positionMap.clear();
		 }
	  }catch(Exception e){
		  LOG.error("Exceptionr riased at getAnyDeptApplicationOverviewCountLocationWise in NominatedPostProfileService class", e); 
	  }
	  return resultList;
  }
	 
	 public List<IdNameVO> getPositionsForABoard(Long locationLevelId,List<Long> locationLevelValueList,Long departmentId,Long boardId){
		 List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		 try {
			List<Object[]> positionsObj = nominatedPostDAO.getPositionsForABoard(locationLevelId,locationLevelValueList,departmentId,boardId);
			if(positionsObj != null && positionsObj.size() > 0){
				for (Object[] objects : positionsObj) {
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getPositionsForABoard",e);
		}
		 return voList;
	 }
	 
	 public ResultStatus confirmGOForNominatedPosts(final GovtOrderVO govtOrderVO,final Long userId,final Map<File,String> mapfiles){
		 final ResultStatus rs = new ResultStatus();
		 try {
			 
			 final List<Long> positionsList = new ArrayList<Long>(0);
			 if(govtOrderVO.getPositionIdsString() != null && !govtOrderVO.getPositionIdsString().isEmpty()){
				 String posiString[] = govtOrderVO.getPositionIdsString().split(",");
				 if(posiString != null && posiString.length > 0){
					for (int i = 0; i < posiString.length; i++) {
						if(!posiString[i].isEmpty() && Long.parseLong(posiString[i])>0l)
								positionsList.add(Long.parseLong(posiString[i]));
					}
				}
			 }
			 
			 final List<Long> locationLevelValueList = new ArrayList<Long>(0);
			if(govtOrderVO.getLocationLevelValuesStr() != null && !govtOrderVO.getLocationLevelValuesStr().isEmpty()){
				String str[] = govtOrderVO.getLocationLevelValuesStr().split(",");
				if(str != null && str.length > 0){
					for (int i = 0; i < str.length; i++) {
						if(!str[i].isEmpty() && Long.parseLong(str[i])>0l)
							locationLevelValueList.add(Long.parseLong(str[i]));
					}
				}
			}
			
			
				
			final Date date = dateUtilService.getCurrentDateAndTime();
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			final SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					//update status in nominated post
					List<Long> nominatedPostIds = nominatedPostDAO.getNominatedPostIds(govtOrderVO.getLocationLevelId(),locationLevelValueList,govtOrderVO.getDepartmentId(),govtOrderVO.getBoardId(),positionsList);
					if(nominatedPostIds != null && nominatedPostIds.size() > 0)
						nominatedPostDAO.updateGoIssuedStatusInNominatedPost(nominatedPostIds,date);
					 
					//update status in nominated post final
					List<Long> nominatedPostFinalIds = nominatedPostFinalDAO.getNominatedPostFinalIds(govtOrderVO.getLocationLevelId(),locationLevelValueList,govtOrderVO.getDepartmentId(),govtOrderVO.getBoardId(),positionsList);
					if(nominatedPostFinalIds != null && nominatedPostFinalIds.size() > 0)
						nominatedPostFinalDAO.updateGoIssuedStatusInNominatedPostFinal(nominatedPostFinalIds,date);
					
					//move old record to history
					List<Object[]> objList = nominatedPostApplicationDAO.getRecord(govtOrderVO.getLocationLevelId(),locationLevelValueList,govtOrderVO.getDepartmentId(),govtOrderVO.getBoardId(),positionsList);
					List<Long> nominatedPostApplicationIds = new ArrayList<Long>(0);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							NominatedPostApplicationHistory npah = new NominatedPostApplicationHistory();
							
							npah.setTrackedTime(date);
							npah.setNominatedPostApplicationId((Long)objects[0]);
							nominatedPostApplicationIds.add((Long)objects[0]);
							npah.setNominationPostCandidateId((Long)objects[1]);
							npah.setDepartmentId((Long)objects[2]);
							npah.setBoardId((Long)objects[3]);
							npah.setPositionId((Long)objects[4]);
							npah.setBoardLevelId((Long)objects[5]);
							npah.setLocationValue((Long)objects[6]);
							npah.setApplicationStatusId((Long)objects[7]);
							npah.setInsertedBy((Long)objects[8]);
							try {
								npah.setInsertedTime(sdf.parse(objects[9].toString()));
								npah.setUpdatedTime(sdf.parse(objects[11].toString()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							npah.setUpdatedBy((Long)objects[10]);
							npah.setIsDeleted(objects[11].toString());
							
							nominatedPostApplicationHistoryDAO.save(npah);
							
						}
					}
					
					if(nominatedPostApplicationIds != null && nominatedPostApplicationIds.size() > 0)
						nominatedPostApplicationDAO.updateApplicationStatusForGO(nominatedPostApplicationIds,date);
					
					GovtOrder go = new GovtOrder();
					go.setOrderName(govtOrderVO.getGoName());
					go.setOrderCode(govtOrderVO.getGoCode());
					try {
						if(govtOrderVO.getFromDate() != null && govtOrderVO.getToDate() != null){
							go.setFromDate(sdf1.parse(govtOrderVO.getFromDate()));
							go.setToDate(sdf1.parse(govtOrderVO.getToDate()));
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					go.setBoardId(govtOrderVO.getBoardId());
					go.setRemarks(govtOrderVO.getRemarks());
					go.setInsertedBy(userId);
					go.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					go.setUpdatedBy(userId);
					go.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					go.setIsDeleted("N");
					
					go = govtOrderDAO.save(go);
					
					if(mapfiles.size() > 0){
						String status1 = saveGODocuments(go.getGovtOrderId(),mapfiles,userId);
						rs.setExceptionMsg("success");
					}
				}
			});
		} catch (Exception e) {
			LOG.error("Exception raised at confirmGOForNominatedPosts", e);
			rs.setExceptionMsg("failure");
		}
		 return rs;
	 }
	 
	 public String saveGODocuments(Long GOId,Map<File, String> mapfiles,Long userId){
			String status = null;
			try{
			String folderName = folderCreationForGODocuments();
			GovtOrderDocuments god = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 //int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			
			 StringBuilder pathBuilder = null;
			 StringBuilder str ;
			 
				
			 for (Map.Entry<File, String> entry : mapfiles.entrySet())
			 {
				 pathBuilder = new StringBuilder();
				 str = new StringBuilder();
				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
					
				 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
				 .append(entry.getValue());
				 str.append(randomNumber).append(".").append(entry.getValue());
				 activityService = new ActivityService();
				String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
				 
					if(fileCpyStts.equalsIgnoreCase("error")){
						//status.setResultCode(ResultCodeMapper.FAILURE);
						LOG.error(" Exception Raise in copying file");
						throw new ArithmeticException();
					}
					
					god = new GovtOrderDocuments();
					god.setGovtOrderId(GOId);
					god.setPath(pathBuilder.toString());
					god.setIsDeleted("N");
					god.setInsertedBy(userId);
					god.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					god.setUpdatedBy(userId);
					god.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					
					govtOrderDocumentsDAO.save(god);
					
			 }
			 
			 status = "success";
			}catch(Exception e){
				LOG.error("Exception Occured in saveNominatedPostApplication()", e);
				status = "failure";
			}
			return status;
		}
	 
	 public static String folderCreationForGODocuments(){
	  	 try {
	  		 LOG.debug(" in folderCreationForGODocuments ");
	  		
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			
			String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String nominatedPostDir = ActivityService.createFolder(staticPath+"/"+IConstants.GO_DOCUMENTS);
			 
			 String yr = String.valueOf(year); // YEAR YYYY
			
			 StringBuilder str = new StringBuilder();
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			 str.append(monthText).append("-").append(yr);
			 
			 
			 String mnthYr = str.toString();
			 String mnthYrDir = staticPath+"/"+IConstants.GO_DOCUMENTS+"/"+mnthYr;
			 String mnthDirSts = ActivityService.createFolder(mnthYrDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/"+IConstants.GO_DOCUMENTS+"/"+mnthYr;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}
	 
	 /**
		 * @Author  HYMAVATHI
		 * @Version NominatedPostProfileService.java  Aug 17, 2016 02:00:00 PM 
		 * @return ResultStatus
		 * description  { In This Method Checking Whether this position Available Or Not For Shortlisting }
		 */
	 public ResultStatus checkPositionAvailableOrNot(Long departmentId,Long boardId,Long positionId,Long boardLevlId,Long searchLevelValue,Long searchLevelId){
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 List<Long> list = nominatedPostDAO.checkPositionAvailableOrNot(departmentId,boardId,positionId,boardLevlId,searchLevelValue,searchLevelId);
			if(list != null && list.size() >0){
				resultStatus.setMessage("AVAILABLE");
			}else{
				resultStatus.setMessage("NOT AVAILABLE");
			}
		} catch (Exception e) {
			resultStatus.setMessage("FAIL");
			e.printStackTrace();
			LOG.error("Exception riased at checkPositionAvailableOrNot",e);
		}
		 return resultStatus;
	 } 
	 
	 public ResultStatus rejectApplications(Long departmentId,Long boardId,Long boardLevelId,Long positionId,Long levelValue){
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 List<Long> positionIds = null ;
			 if(positionId != null && positionId.longValue() > 0l){
				  positionIds = new ArrayList<Long>();
				 positionIds.add(positionId);
			 }
			 List<Long> levelValues = new ArrayList<Long>();
			 levelValues.add(levelValue);
			 List<Long> status = new ArrayList<Long>();
			 status.add(1l);
			 status.add(3l);
			 status.add(6l);
			Long memberId = nominatedPostMemberDAO.getNominatedPostMemberId(boardLevelId,levelValue,departmentId,boardId,positionId);
			List<Long> applicationIds = nominatedPostApplicationDAO.getApplicationIds(departmentId,boardId,positionIds,boardLevelId,levelValues,null,status);
			//List<Long> nominatedPostFinalIds = nominatedPostFinalDAO.getNominatedPostFinalIdsByMemberOfFinalReview(memberId,status);
			List<Long> nominatedPostIds = nominatedPostDAO.getNominatedPostIdsForBoardLevelId(boardLevelId,levelValue,departmentId,boardId,positionId);
			
			//Long openPostCount = nominatedPostDAO.getOpenedPositions(memberId);
			if(nominatedPostIds != null && nominatedPostIds.size() >0){}
			else{
				if(applicationIds != null && applicationIds.size() >0){
				nominatedPostFinalDAO.updateStatusToGOPassed(applicationIds,dateUtilService.getCurrentDateAndTime(),IConstants.rejectedInFinalized);
				
				for(Long long1 : applicationIds){
					NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(long1);
					savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
					
					nominatedPostApplication.setApplicationStatusId(IConstants.rejectedInFinalized);
					//nominatedPostApplication.setUpdatedBy(userId);
					nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					nominatedPostApplicationDAO.save(nominatedPostApplication);
				}
			}
			}
			 
			resultStatus.setExceptionMsg("SUCCESS");
		 }catch (Exception e) {
				resultStatus.setExceptionMsg("FAIL");
				e.printStackTrace();
				LOG.error("Exception riased at rejectApplications",e);
			}
			 return resultStatus;
	 }
	 public ResultStatus assginGOToNominationPostCandidate(final GovtOrderVO goVO,final Long userId,final Map<File,String> mapfiles){
		 final ResultStatus rs = new ResultStatus();
		 try {
			 
			 final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			 
			 final List<Long> applicationIds = new ArrayList<Long>(0);
			 if(goVO.getApplicationIds() != null && goVO.getApplicationIds().trim() != ""){
				 String[] idsStr = goVO.getApplicationIds().split(",");
				 for (int i = 0; i < idsStr.length; i++) {
					if(!idsStr[i].trim().isEmpty()){
						applicationIds.add(Long.parseLong(idsStr[i]));
					}
				}
			 }
			 
			 final Map<Long,Long> postMap = new HashMap<Long, Long>(0);//NPID,NPCID
			 final Set<Long> postIds = new LinkedHashSet<Long>(); 
			 
			 if(applicationIds != null && applicationIds.size() > 0){
					
				//get nominated postids for applications
				 
				 //0.postId,1.postCandidateId				
					List<Object[]> objList = nominatedPostFinalDAO.getNPCAndNpForApplication(applicationIds);//NPID,NPCID
					
					if(objList != null && objList.size() > 0){
						for (Object[] obj : objList) {
							postMap.put((Long)obj[0], (Long)obj[1]);
							postIds.add((Long)obj[0]);
						}
					}
			 }
			 
			 
			 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						if(goVO.getStatus() != null && goVO.getStatus() == 1l){//go issue
							
							
								for (Entry<Long, Long> entry : postMap.entrySet()) {
									
									//NominatedPost np = nominatedPostDAO.get(entry.getKey());
									List<Long> data = nominatedPostGovtOrderDAO.getNominatedPostGovtOrderByPostId(entry.getKey());
									if(data == null || data.size() ==0){
										GovtOrder govtOrder = new GovtOrder();
										govtOrder.setOrderName(goVO.getGoName());
										govtOrder.setOrderCode(goVO.getGoCode());
										
										try {
											govtOrder.setFromDate(sdf.parse(goVO.getFromDate()));
											govtOrder.setToDate(sdf.parse(goVO.getToDate()));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										
										govtOrder.setRemarks(goVO.getRemarks());
										govtOrder.setInsertedBy(userId);
										govtOrder.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										govtOrder.setUpdatedBy(userId);
										govtOrder.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										govtOrder.setIsDeleted("N");
										
										govtOrder = govtOrderDAO.save(govtOrder);
										
										if(applicationIds != null && applicationIds.size() > 0){
											
											//update status to go-passed
											nominatedPostFinalDAO.updateStatusToGOPassed(applicationIds,dateUtilService.getCurrentDateAndTime(),7l);
										
											for(Long long1 : applicationIds){
												NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(long1);
												savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
												
												nominatedPostApplication.setApplicationStatusId(7l);
												nominatedPostApplication.setUpdatedBy(userId);
												nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
												nominatedPostApplicationDAO.save(nominatedPostApplication);
											}
											NominatedPostGovtOrder nogo = new NominatedPostGovtOrder();
										nogo.setNominatedPostId(entry.getKey());
										nogo.setGovtOrderId(govtOrder.getGovtOrderId());
										nogo.setInsertedBy(userId);
										nogo.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										nogo.setUpdatedBy(userId);
										nogo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										nogo.setIsDeleted("N");
										nogo.setIsExpired("N");
										nominatedPostGovtOrderDAO.save(nogo);
										}
									
									nominatedPostDAO.updateNominatedPost(entry.getKey(),entry.getValue(),dateUtilService.getCurrentDateAndTime(),userId);
									
									if(mapfiles.size() > 0){
										String status1 = saveGODocuments(govtOrder.getGovtOrderId(),mapfiles,userId);
										//rs.setExceptionMsg("GO Issued Succesfully.");
										rs.setExceptionMsg("SUCCESS");
									}
									rs.setExceptionMsg("SUCCESS");
									}else{
										rs.setExceptionMsg("AlreadyPassed");
									}
								
							}
								
						}else if(goVO.getStatus() != null && goVO.getStatus() == 2l){//go reject
							if(applicationIds != null && applicationIds.size() > 0){
								
								//update status to go-passed
								nominatedPostFinalDAO.updateStatusToGOPassed(applicationIds,dateUtilService.getCurrentDateAndTime(),IConstants.rejectedInFinalized);
								
								for(Long long1 : applicationIds){
									NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(long1);
									savingNominatedPostApplicationHistoryDetails(nominatedPostApplication,null,null);
									
									nominatedPostApplication.setApplicationStatusId(IConstants.rejectedInFinalized);
									nominatedPostApplication.setUpdatedBy(userId);
									nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									nominatedPostApplicationDAO.save(nominatedPostApplication);
								}
								
								int count = nominatedPostDAO.updateNominatedPostsForOpenState(postIds,userId,dateUtilService.getCurrentDateAndTime());
								
								if(count>0){
									rs.setExceptionMsg("SUCCESS");
								}							
								
							}
						}
					}
			 });
			 
		} catch (Exception e) {
			LOG.error("Exception raised at assginGOToNominationPostCandidate", e);
			//rs.setExceptionMsg("Error Occured Please Try Again.");
			rs.setExceptionMsg("Failure");
		}
		 
		 return rs;
	 }
	 	 /*  " D.departmentId, " +//0
	 	   " D.deptName, " +//1
	 	   " NPS.nominatedPostStatusId, " +//2
		   " NPS.status, " +//3
		   " model.nominatedPostId, " +//4
		   " NPC.candidateName, " + //5
		   " TC.firstname, " +//6
		   " NPC.mobileNo, " +//7
		   " TC.mobileNo, " +//8
		   " TC.memberShipNo, " +//9
		   " NPC.age, " +//10
		   " TC.age, " +//11
		   " B.boardId, " +//12
		   " B.boardName, " +//13
		   " P.positionId, " +//14
		   " P.positionName, " + //15 
		   " C.casteId, " +//16
		   " C.casteName, " +//17
		   " CC.casteCategoryId, " +//18
		   " CC.categoryName, " +//19
		   " GO.govtOrderId, " +//20
		   " GO.orderName, " +//21
		   " GO.fromDate, " +//22
		   " GO.toDate ");   //23
		   //commonMethodsUtilService.getLongValueForObject
*/	 public List<NomintedPostMemberVO> getFinalReviewCandidateCountForLocation(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId, Long positionId, String status){ 
		 try{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 DateUtilService dateUtilService = new DateUtilService();
			 List<NomintedPostMemberVO> nominatedPostMemberVOs = new ArrayList<NomintedPostMemberVO>(0);
			 NomintedPostMemberVO nominatedPostMemberVO = null;
			 List<Object[]> candidateList = nominatedPostApplicationDAO.getFinalReviewCandidateCountForLocation(LocationLevelId, lctnLevelValueList, departmentId, boardId, positionId, status);
			 if(candidateList != null && candidateList.size() > 0){
				 for(Object[] candidate : candidateList){
					 nominatedPostMemberVO = new NomintedPostMemberVO();
					 nominatedPostMemberVO.setDeptId(commonMethodsUtilService.getLongValueForObject(candidate[0]));
					 nominatedPostMemberVO.setDeptName(commonMethodsUtilService.getStringValueForObject(candidate[1]));
					 nominatedPostMemberVO.setNominatedPostStatusId(commonMethodsUtilService.getLongValueForObject(candidate[2]));
					 nominatedPostMemberVO.setStatus(commonMethodsUtilService.getStringValueForObject(candidate[3]));
					 nominatedPostMemberVO.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(candidate[4]));
					 if(candidate[5] != null ){
						 nominatedPostMemberVO.setName(commonMethodsUtilService.getStringValueForObject(candidate[5]));
					 }else{
						 nominatedPostMemberVO.setName(commonMethodsUtilService.getStringValueForObject(candidate[6]));
					 }
					 if(candidate[7] != null){
						 nominatedPostMemberVO.setCadreMobile(commonMethodsUtilService.getStringValueForObject(candidate[7]));
					 }else{
						 nominatedPostMemberVO.setCadreMobile(commonMethodsUtilService.getStringValueForObject(candidate[8]));
					 }
					 nominatedPostMemberVO.setMembershipNO(commonMethodsUtilService.getStringValueForObject(candidate[9]));//membershipId
					 
					 if(candidate[10] != null){
						 nominatedPostMemberVO.setAge(commonMethodsUtilService.getLongValueForObject(candidate[10]));
					 }else{
						 nominatedPostMemberVO.setAge(commonMethodsUtilService.getLongValueForObject(candidate[11]));
					 }
					 nominatedPostMemberVO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(candidate[12]));
					 nominatedPostMemberVO.setBoardName(commonMethodsUtilService.getStringValueForObject(candidate[13]));
					 nominatedPostMemberVO.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(candidate[14]));
					 nominatedPostMemberVO.setPositionName(commonMethodsUtilService.getStringValueForObject(candidate[15]));
					 nominatedPostMemberVO.setCastId(commonMethodsUtilService.getLongValueForObject(candidate[16]));
					 nominatedPostMemberVO.setCasteName(commonMethodsUtilService.getStringValueForObject(candidate[17]));
					 nominatedPostMemberVO.setCastCategoryId(commonMethodsUtilService.getLongValueForObject(candidate[18]));
					 nominatedPostMemberVO.setCastCategoryName(commonMethodsUtilService.getStringValueForObject(candidate[19]));
					 nominatedPostMemberVO.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(candidate[20]));
					 nominatedPostMemberVO.setGovtOrderName(commonMethodsUtilService.getStringValueForObject(candidate[21]));
					 nominatedPostMemberVO.setFromDate(commonMethodsUtilService.getStringValueForObject(candidate[22]));
					 nominatedPostMemberVO.setToDate(commonMethodsUtilService.getStringValueForObject(candidate[23])); 
					 Date today = new Date();
					 String toDate = commonMethodsUtilService.getStringValueForObject(candidate[23]); 
					 if(toDate.length() > 10){
						 toDate = toDate.substring(0, 10);
						 Date lastDate = sdf.parse(toDate);
						 String expairStr = dateUtilService.getDayMonthAndYearsBetweenTwoDates(today,lastDate);
						 nominatedPostMemberVO.setExpireDate(expairStr);
					 }
					 nominatedPostMemberVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(candidate[26]));
					 if(nominatedPostMemberVO.getTdpCadreId() != null && nominatedPostMemberVO.getTdpCadreId().longValue() > 0l ){
						 nominatedPostMemberVO.setImageURL(commonMethodsUtilService.getStringValueForObject(candidate[24]));
					 }else{
						 nominatedPostMemberVO.setImageURL(commonMethodsUtilService.getStringValueForObject(candidate[25]));
					 }
					 
					
					 nominatedPostMemberVOs.add(nominatedPostMemberVO);
				 }
			 }
			 return nominatedPostMemberVOs;
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 LOG.error("Exception riased at getFinalReviewCandidateCountForLocation() ",e);
		 }
		 return null; 
	 }
@SuppressWarnings("deprecation")
public List<NomintedPostMemberVO> getFinalReviewCandidateCountForLocationFilter(Long LocationLevelId, List<Long> lctnLevelValueList, List<Long> deptList, List<Long> boardList, List<Long> positionList, String fromDateStr, String expireDate, String status){ 
	 try{
		 Date fromDate1 = null;
		 Date expDate1 = null;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy"); 
		 
		 if(fromDateStr !=null && fromDateStr.trim().length()>0 && expireDate != null &&  expireDate.trim().length()>0 ){
			 fromDate1 = sdf2.parse(fromDateStr);
			 expDate1 = sdf2.parse(expireDate); 
			 //expDate.setMonth( expDate.getMonth() + 1 );     
		 } 
		 
		 DateUtilService dateUtilService = new DateUtilService();
		 List<NomintedPostMemberVO> nominatedPostMemberVOs = new ArrayList<NomintedPostMemberVO>(0);
		 NomintedPostMemberVO nominatedPostMemberVO = null;
		 List<Object[]> candidateList = nominatedPostApplicationDAO.getFinalReviewCandidateCountForLocationFilter(LocationLevelId, lctnLevelValueList, deptList, boardList, positionList, fromDate1, expDate1, status);
		 
		 Date fromDate = null;
		 Date expDate = null;
		 
		 if(candidateList != null && candidateList.size() > 0){
			 for(Object[] candidate : candidateList){
				 nominatedPostMemberVO = new NomintedPostMemberVO();
				 nominatedPostMemberVO.setDeptId(commonMethodsUtilService.getLongValueForObject(candidate[0]));
				 nominatedPostMemberVO.setDeptName(commonMethodsUtilService.getStringValueForObject(candidate[1]));
				 nominatedPostMemberVO.setNominatedPostStatusId(commonMethodsUtilService.getLongValueForObject(candidate[2]));
				 nominatedPostMemberVO.setStatus(commonMethodsUtilService.getStringValueForObject(candidate[3]));
				 nominatedPostMemberVO.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(candidate[4]));
				 if(candidate[5] != null ){
					 nominatedPostMemberVO.setName(commonMethodsUtilService.getStringValueForObject(candidate[5]));
				 }else{
					 nominatedPostMemberVO.setName(commonMethodsUtilService.getStringValueForObject(candidate[6]));
				 }
				 if(candidate[7] != null){
					 nominatedPostMemberVO.setCadreMobile(commonMethodsUtilService.getStringValueForObject(candidate[7]));
				 }else{
					 nominatedPostMemberVO.setCadreMobile(commonMethodsUtilService.getStringValueForObject(candidate[8]));
				 }
				 nominatedPostMemberVO.setMembershipNO(commonMethodsUtilService.getStringValueForObject(candidate[9]));
				 
				 if(candidate[10] != null){
					 nominatedPostMemberVO.setAge(commonMethodsUtilService.getLongValueForObject(candidate[10]));
				 }else{
					 nominatedPostMemberVO.setAge(commonMethodsUtilService.getLongValueForObject(candidate[11]));
				 }
				 nominatedPostMemberVO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(candidate[12]));
				 nominatedPostMemberVO.setBoardName(commonMethodsUtilService.getStringValueForObject(candidate[13]));
				 nominatedPostMemberVO.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(candidate[14]));
				 nominatedPostMemberVO.setPositionName(commonMethodsUtilService.getStringValueForObject(candidate[15]));
				 nominatedPostMemberVO.setCastId(commonMethodsUtilService.getLongValueForObject(candidate[16]));
				 nominatedPostMemberVO.setCasteName(commonMethodsUtilService.getStringValueForObject(candidate[17]));
				 nominatedPostMemberVO.setCastCategoryId(commonMethodsUtilService.getLongValueForObject(candidate[18]));
				 nominatedPostMemberVO.setCastCategoryName(commonMethodsUtilService.getStringValueForObject(candidate[19]));
				 nominatedPostMemberVO.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(candidate[20]));
				 nominatedPostMemberVO.setGovtOrderName(commonMethodsUtilService.getStringValueForObject(candidate[21]));
				 nominatedPostMemberVO.setFromDate(commonMethodsUtilService.getStringValueForObject(candidate[22]));
				 nominatedPostMemberVO.setToDate(commonMethodsUtilService.getStringValueForObject(candidate[23])); 
				 Date today = new DateUtilService().getCurrentDateAndTime();
				 
				 String tempFromDate = commonMethodsUtilService.getStringValueForObject(candidate[22]);
				 String tempToDateStr = commonMethodsUtilService.getStringValueForObject(candidate[23]); 

				 Date frmDate = null;
				 Date lastDate = null;
				 if(tempFromDate.length() > 10){
					 tempFromDate = tempFromDate.substring(0, 10);
					 frmDate = sdf.parse(tempFromDate);
				 }
				 if(tempToDateStr.length() > 10){
					 tempToDateStr = tempToDateStr.substring(0, 10);
					 lastDate = sdf.parse(tempToDateStr);
					 String expairStr = " All Ready Expired";
					 //String expairStr = dateUtilService.getDayMonthAndYearsBetweenTwoDates(today,lastDate);
					 //if(fromDate1 == null || lastDate.after(fromDate1)){
						 List<String> dates =  dateUtilService.getDaysBetweenDatesStringFormat(frmDate, lastDate);
						 if(dates != null && dates.size() > 0){
							 if(dates.contains(sdf.format(dateUtilService.getCurrentDateAndTime()))){
								 List<String> dates1 =  dateUtilService.getDaysBetweenDatesStringFormat(dateUtilService.getCurrentDateAndTime(), lastDate);
								 expairStr = setExpireString(dates1,sdf.format(dateUtilService.getCurrentDateAndTime())); 
							 }else{
								 expairStr = " All Ready Expired";
							 }
						}
					// }
					
					
					 nominatedPostMemberVO.setExpireDate(expairStr);
				 }
				 
				 if(frmDate != null && lastDate != null){
					 if(frmDate.before(lastDate)){ //frmDate <lastDate
						 fromDate = frmDate;
						 expDate = lastDate;
					 }else{//frmDate > lastDate
						 fromDate = lastDate;
						 expDate = frmDate;
					 }
				 }
				
				 
				
				 if(nominatedPostMemberVO.getTdpCadreId() != null && nominatedPostMemberVO.getTdpCadreId().longValue() > 0l ){
					 nominatedPostMemberVO.setImageURL(commonMethodsUtilService.getStringValueForObject(candidate[24]));
				 }else{
					 nominatedPostMemberVO.setImageURL(commonMethodsUtilService.getStringValueForObject(candidate[25]));
				 }
				 nominatedPostMemberVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(candidate[26]));
				 
				 nominatedPostMemberVOs.add(nominatedPostMemberVO);
			 }
		 }
		 
		 if(commonMethodsUtilService.isListOrSetValid(nominatedPostMemberVOs) && fromDate != null && expDate != null) {
			 NomintedPostMemberVO vo =  nominatedPostMemberVOs.get(0);
			 vo.setUiFromDateStr(sdf2.format(fromDate));
			 vo.setUiToDateStr(sdf2.format(expDate));
		 }
		 
		 return nominatedPostMemberVOs;
		 
	 }catch(Exception e){
		 e.printStackTrace();
		 LOG.error("Exception riased at getFinalReviewCandidateCountForLocation() ",e);
	 }
	 return null; 
}

public String setExpireString(List<String> dates,String todayDate){
	
		 String returnDate = "";
	        Long year=0l;
	        Long month=0l;
	        Long day=0l;
	        Long remenderValue=0l;
	        String noOfYear="";
	        String noOfMonth="";
	        String noOfDay="";
	        long  numberOfDaysCount =dates.size();
	      //  long diff = (toDate.getTime())-(fromDate.getTime());
	        //long numberOfDaysCount =   TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	      if(numberOfDaysCount < 0l){
	        returnDate=" All Ready Expired";
	      }else{
	       //System.out.println(numberOfDaysCount);
	        if(numberOfDaysCount > 365L){
	          year = numberOfDaysCount /365L;
	          remenderValue = numberOfDaysCount %365;
	          if(remenderValue > 30L){
	             month = remenderValue/30;
	             day = remenderValue%30;
	           }else {
	             day=remenderValue;
	           }
	          if(year>1){
	            noOfYear="Years";  
	          }else{
	            noOfYear="Year";    
	          }
	          if(month >1){
	            noOfMonth="Months";
	          }else{
	            noOfMonth="Month";
	          }
	          if(day > 1 ){
	            noOfDay="Days";
	          }else{
	            noOfDay="Day";
	          }
	          returnDate = year.toString()+" "+noOfYear+" "+month.toString()+" "+noOfMonth+" "+day.toString()+" "+noOfDay;
	        }else if(numberOfDaysCount > 30L){
	          month = numberOfDaysCount /30L;
	          remenderValue = numberOfDaysCount%30;
	          day = remenderValue;
	          if(month >1){
	            noOfMonth="Months";
	          }else{
	            noOfMonth="Month";
	          }
	          if(day > 1 ){
	            noOfDay="Days";
	          }else{
	            noOfDay="Day";
	          }
	           returnDate =month.toString()+" "+noOfMonth+" "+day.toString()+" "+noOfDay;
	            
	        }else{
	          day = numberOfDaysCount;
	          if(day > 1 ){
	            noOfDay="Days";
	            returnDate =day.toString()+" "+noOfDay;
	          }else{
	        	  if(dates.contains(todayDate))
	        		  	returnDate ="Today";
	        	  else
	        		  	returnDate =" All Ready Expired";
	          }
	          //returnDate =day.toString()+" "+noOfDay;
	        }
	      }
	    return returnDate;
	
}

/**
 * @Author  Hymavathi
 * @Version NominatedPostProfileService.java  Aug 29, 2016 12:30:00 PM 
 * @return status
 * description  { Updating Application Status To Rejected }
 */
public String savingStatusAsReject(final Long userId,final Long applicationId,final Long candidateId,final Long levelId,final Long levelValue,
		final Long statusId,final String comment){
String status = null;
try {
	Long successValue = (Long) transactionTemplate.execute(new TransactionCallback() {
		public Object doInTransaction(TransactionStatus arg0) {
			//NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(applicationId);
			int succesCnt = 0;
			Long statusValue = 0l;
			if(statusId == 2){
			   succesCnt = nominatedPostApplicationDAO.updateApllicationStatusToReject(applicationId,statusId,userId);
			   if(succesCnt > 0){
					statusValue = 1l;
				}
			 }
			
			if(statusId == 2){
			 succesCnt = nominatedPostFinalDAO.updateApllicationStatusToReject(applicationId,statusId,userId);
			
			 if(succesCnt > 0){
					statusValue = 1l;
				}
			} 
			
			NominatedPostComment nominatedPostComment = new NominatedPostComment();
			
			nominatedPostComment.setNominatedPostApplicationId(applicationId);
			nominatedPostComment.setRemarks(comment);  
			nominatedPostComment.setInsertedBy(userId);
			nominatedPostComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			
			nominatedPostComment = nominatedPostCommentDAO.save(nominatedPostComment);
			return statusValue;
		}
	});
	if(successValue.longValue() == 0l)
		status="failure";
	else
		status = "success";
	
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
			LOG.error("Exception raised at savingStatusAsReject", e);
		}
		return status;
	}
public String isApplicationAlreadyShortlisted(Long nominatePostApplicationId,Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId,Long candId){
	String status = "Not Shortlisted";
	try {
		Long nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
		Long nominatedPostFinalId = nominatedPostFinalDAO.getIsApplicationShortlistedOrNot(nominatedPostMemberId,candId,nominatePostApplicationId);
	
		if(nominatedPostFinalId != null && nominatedPostFinalId.longValue() > 0l){
			status = "Shortlisted";
		}
	} catch (Exception e) {
		status = "failure";
		e.printStackTrace();
		LOG.error("Exception Occured in isApplicationAlreadyShortlisted()", e);
	}
	return status;	
}
/*
 * Author:Santosh
 */
public NominatedPostDashboardVO getNominatedPostDetails(Long locationLevelId,List<Long> locationValues,Long departmentId,Long boardId,Long positionId){
	NominatedPostDashboardVO resultVO = new NominatedPostDashboardVO();
	try{
		List<Object[]> rtrnObjLst = nominatedPostDAO.getNominatedPostDetails(locationLevelId,locationValues,departmentId,boardId,positionId);
			if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				for(Object[] param:rtrnObjLst){
					Long nominatedPostStatusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long postCnt = commonMethodsUtilService.getLongValueForObject(param[1]);
					if(nominatedPostStatusId == 1l || nominatedPostStatusId == 2l){//statusId: 1-Open,2-Final Review
					 resultVO.setOpenPostCnt(resultVO.getOpenPostCnt()+postCnt);		
					}else if(nominatedPostStatusId == 3l || nominatedPostStatusId == 4l){//statusId: 3-Confirmed,4-GO Issued
					 resultVO.setFinalizedAndGoPassedCnt(resultVO.getFinalizedAndGoPassedCnt()+postCnt);	
					}
					resultVO.setTotalCnt(resultVO.getTotalCnt()+postCnt);
				}
			}
	}catch(Exception e){
		LOG.error("Exception Occured in getNominatedPostDetails()", e);
	}
	return resultVO;
}

public List<IdAndNameVO> getApplicationDocuments(Long tdpCadreId, String searchType, Long nominateCandId, Long applicationId,Long statusId,String applicationType){
	List<IdAndNameVO> retrurnList = new ArrayList<IdAndNameVO>();
	try{
		if(statusId != null && (applicationType.equalsIgnoreCase("goPassedStatus") || applicationType.equalsIgnoreCase("") ) && (statusId.longValue() == 0l || statusId.longValue() == 7l || statusId.longValue() == 9l)){
			List<Long> postIds = nominatedPostFinalDAO.getNominatedPostIds(nominateCandId,applicationId,statusId);
			if(postIds != null && postIds.size() >0){
			List<Object[]> goDocuments = govtOrderDocumentsDAO.getGoPassedDocuments(postIds);
			setDocuments(retrurnList,goDocuments,"godocuments");
			}
		}
		if(statusId != null && (applicationType.equalsIgnoreCase("applicationStatus") || applicationType.equalsIgnoreCase("") ) && (statusId.longValue() == 0l || statusId.longValue() == 1l || statusId.longValue() == 2l ||
				statusId.longValue() == 3l || statusId.longValue() == 4l || statusId.longValue() == 5l || statusId.longValue() == 6l || statusId.longValue() == 8l )){	
			List<Object[]> applnDocs = applicationDocumentDAO.getApplicationDocuments(tdpCadreId, searchType, nominateCandId, applicationId);
			setDocuments(retrurnList,applnDocs,"appDocs");
		}
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in getApplicationDocuments()", e);
	}
	return retrurnList;
}

public void setDocuments(List<IdAndNameVO> retrurnList,List<Object[]> documents,String type){
	try{
		if(commonMethodsUtilService.isListOrSetValid(documents)){
			for(Object[] param :documents){
				IdAndNameVO vo = new IdAndNameVO();
				
				vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setImagePathStr(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setStartTime(commonMethodsUtilService.getStringValueForObject(param[2]));
				if(type != null && !type.equalsIgnoreCase("godocuments")){
					vo.setApTotal(commonMethodsUtilService.getLongValueForObject(param[3]));// nominated Post Application Id
					vo.setAttenteeCount(commonMethodsUtilService.getLongValueForObject(param[4]));//nominated Post candidate Id
				}
				retrurnList.add(vo);
				
				
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in setDocuments()", e);
	}
}
	public List<IdAndNameVO> getAllAgeRangesByOrder(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = nominatedPostAgeRangeDAO.getAllAgeRangesByOrder();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllAgeRangesByOrder()", e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getAllCasteDetailsForVoters(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = casteStateDAO.getAllCasteDetailsForVoters(1l);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllCasteDetailsForVoters()", e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getAllCasteCategoryDetails(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = casteCategoryDAO.getAllCasteCategoryDetails();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllCasteCategoryDetails()", e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getEducationalQualifications(){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = educationalQualificationsDAO.getEducationalQualifications();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getEducationalQualifications()", e);
		}
		return returnList;
	}
	public List<IdNameVO> getDepartmentBoardPositions1(List<Long> deptIds,List<Long> boardIds,Long boardLevelId,List<Long> searchLevelValueIds,Long searchLevelId,Long nominatedPostCandId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try{
			
			List<Object[]>  list = nominatedPostDAO.getLevelWiseDepartmentsBoardPosition1(deptIds,boardIds,boardLevelId,searchLevelValueIds);
			
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"id","name"};
				returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getDepartmentBoardPositions1()", e);
		}
		return returnList;
	}

	public ResultStatus savingNominatedPostDocumnets(final Long nominatedPostCandidateId,final Long tdpCadreId,final Long applicationId,final Map<File,String> mapfiles,final Long loggerUserId){
		
		final ResultStatus rs = new ResultStatus();
		try{
			saveApplicationDocuments(applicationId,nominatedPostCandidateId,mapfiles);
			rs.setResultCode(0);
			rs.setMessage("SUCCESS ");
		return rs;
		}catch(Exception e){
			LOG.error("Exception Occured in savingNominatedPostDocumnets()", e);
			rs.setResultCode(1);
			rs.setMessage("FAIL");
		}
		return rs;
	}

	public List<NomintedPostMemberVO> getGovtOrderDocumentsPath(List<NomintedPostMemberVO> subList)
	{
		List<NomintedPostMemberVO> returnList = new ArrayList<NomintedPostMemberVO>();
	try{				
		NomintedPostMemberVO returnVo = null;
		List<Long> govtOrderIds = new ArrayList<Long>();
		//Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId		 
		for(NomintedPostMemberVO Vo : subList){
			Long nominateCandId = Vo.getNominatedPostCandidateId();
			Long nominatedPostMemberId =nominatedPostMemberDAO.getNominatedPostMemberId(Vo.getId(),Vo.getLevelId(),Vo.getDeptId(),Vo.getDeptBoardId(),Vo.getDeptBoardPostnId());
			Long nominatedPostId = nominatedPostDAO.getOfNominatedPostCondidates(nominateCandId,nominatedPostMemberId);
			List<Object[]> goPassedDates = nominatedPostGovtOrderDAO.gettingGoPassedDates(nominatedPostId);
			if(goPassedDates != null && goPassedDates.size()>0){
				for(Object[] level : goPassedDates){
					Vo.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(level[0]));
					Vo.setFromDate(commonMethodsUtilService.getStringValueForObject(level[1]));
					Vo.setToDate(commonMethodsUtilService.getStringValueForObject(level[2]));
					Vo.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(level[3]));
				}
			}
		List<Object[]> pathList = govtOrderDocumentsDAO.getGovtOrderDocumentsPath(Vo.getGovtOrderId());
		 if(pathList != null && pathList.size()>0){
			 for(Object[] path :pathList){
				 Vo.setPath(commonMethodsUtilService.getStringValueForObject(path[1]));
				 if(Vo.getPath() != null)
					 Vo.setPath("http://mytdp.com/GO_documents/"+Vo.getPath());
			 }
		 }
		}
	}catch(Exception e){
		LOG.error("Exception Occured in getGovtOrderDocumentsPath()", e);
	}
	return returnList;
}
	public String UpdateExpiredAppicationsInNominatedPosts(final Long userId){
		SchedulersInfo schedulersInfo = new SchedulersInfo();
		try {
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
				return "failure";
			
			schedulersInfo.setName("UpdateExpiredAppicationsInNominatedPosts");
			schedulersInfo.setSchedulerStartTime(dateUtilService.getCurrentDateAndTime());
			
			String schedulerStatus = (String) transactionTemplate.execute(new TransactionCallback() {

				public Object doInTransaction(TransactionStatus arg0) {
					int count1 =0;
					int count2 =0;
					int count3 =0;
					int count4 =0;
					
					Date currentDate = dateUtilService.getCurrentDateAndTime();
					//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					/*try {
						currentDate = sdf.parse("01/12/2001 11:12:12 ");
					} catch (Exception e) {}*/
					
					List<Long> nominatedPostIdsLsist = nominatedPostGovtOrderDAO.getExpiredNominatedPostIdsLsit(currentDate);
					List<Long> applciationIdsList = null;
					if(commonMethodsUtilService.isListOrSetValid(nominatedPostIdsLsist))
					{
						applciationIdsList = nominatedPostFinalDAO.getNominatedPostApplicationIdsByPostIds(nominatedPostIdsLsist);
						if(commonMethodsUtilService.isListOrSetValid(applciationIdsList)){
							count1 = nominatedPostFinalDAO.updateApplicationExpiredByPostIds(nominatedPostIdsLsist,userId,currentDate);
							count2 = nominatedPostApplicationDAO.updateApplicationExpiredByApplns(applciationIdsList,userId,currentDate);
						}
						count3 = nominatedPostGovtOrderDAO.updateApplicationExpiredByPostIds(nominatedPostIdsLsist,currentDate,userId);
						count4 = nominatedPostDAO.updatePoststoOpenByPostIds(nominatedPostIdsLsist,currentDate,userId);
						LOG.error ("Total :"+count4+" Posts Expired ("+nominatedPostIdsLsist+"), \n  Total :"+count2+" Applciations Expired ("+applciationIdsList+") , \n Total : "+count3+" GO Orders  Expired ");	
						return "Total :"+count4+" Posts Expired ("+nominatedPostIdsLsist+"), \n  Total :"+count2+" Applciations Expired ("+applciationIdsList+") , \n Total : "+count3+" GO Orders  Expired ";
					}
					LOG.error ("Total :"+count4+" Posts Expired ("+nominatedPostIdsLsist+"), \n  No Applciations Expired ("+applciationIdsList+") , \n Total : "+count3+" GO Orders  Expired ");	
					return "Total :"+count4+" Posts Expired ("+nominatedPostIdsLsist+"), \n  Total :"+count2+" Applciations Expired ("+applciationIdsList+") , \n Total : "+count3+" GO Orders  Expired ";
					
				}
			});
			
			if(schedulerStatus != null && !schedulerStatus.trim().isEmpty() && !schedulerStatus.trim().equalsIgnoreCase("success")){
				if(!schedulerStatus.trim().equalsIgnoreCase("failure")){
					schedulersInfo.setDescription(schedulerStatus);
					schedulersInfo.setStatus("success");
				}else
					schedulersInfo.setStatus("failure");
				
				schedulersInfo.setUserId(IConstants.JOB_SCHEDULER_USER_ID);
			}
			schedulersInfo.setSchedulerEndTime(dateUtilService.getCurrentDateAndTime());
			schedulersInfoDAO.save(schedulersInfo);
			
			return schedulerStatus;
		} catch (Exception e) {
			schedulersInfo.setStatus("failure :  "+e.getMessage());
			LOG.error("Exception Occured in getPanchayatOrWardsByMandalOrMuncId() method, Exception - ",e);
		}
		schedulersInfo.setSchedulerEndTime(dateUtilService.getCurrentDateAndTime());
		schedulersInfoDAO.save(schedulersInfo);
		return "failure";
	}
	
	public Map<Long,List<CadreEventsVO>> getCadresEventsSummary(List<Long> cadreIds){
		Map<Long,List<CadreEventsVO>> finalMap = new HashMap<Long, List<CadreEventsVO>>(0);
		try {
			
			
			if(commonMethodsUtilService.isListOrSetValid(cadreIds)){
				for (Long id : cadreIds) {
					List<CadreEventsVO> tempList2 = new ArrayList<CadreEventsVO>();
					CadreEventsVO voIn2 = new CadreEventsVO();
					voIn2.setId(7L);
					voIn2.setName("Mahanadu - 2015");
					voIn2.setInvitedCount(0l);
					voIn2.setAttendedCount(0l);
					tempList2.add(voIn2);
					
					//finalMap.put(id,tempList2);
					
					CadreEventsVO voIn1 = new CadreEventsVO();
					voIn1.setId(30L);
					voIn1.setName("Mahanadu - 2016");
					voIn1.setInvitedCount(0l);
					voIn1.setAttendedCount(0l);
					tempList2.add(voIn1);
					
					//finalMap.put(id,tempList2);
					
					CadreEventsVO voIn = new CadreEventsVO();
					voIn.setName("Training");
					voIn.setInvitedCount(0L);
					voIn.setAttendedCount(0L);
					tempList2.add(voIn);
					
					//finalMap.put(id,tempList2);
					
					

					CadreEventsVO vo = new CadreEventsVO();
					vo.setName("PartyMeetings");
					vo.setInvitedCount(0L);
					vo.setAttendedCount(0L);
					tempList2.add(vo);
					
					//finalMap.put(id,tempList2);
					
					CadreEventsVO vo1 = new CadreEventsVO();
					vo1.setName("Alert");
					vo1.setInvitedCount(0L);
					vo1.setAttendedCount(0L);
					tempList2.add(vo1);
					
					finalMap.put(id,tempList2);
					
				}
			}
			
			
			
			if(cadreIds != null && cadreIds.size() > 0){
				//get mahanadu invitee and attendence details
				//0-eventId,1-cadreId
				List<Object[]> objList = eventInviteeDAO.getEventInviteeDetails(cadreIds,IConstants.MAHANADUEVENTIDS);
				
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						CadreEventsVO voIn = getMatchedEventVO((Long)objects[0],finalMap.get((Long)objects[1]));
						if(voIn != null)
							voIn.setInvitedCount(1l);
						
						/*CadreEventsVO voIn = new CadreEventsVO();
						voIn.setId((Long)objects[0]);
						if((Long)objects[0] == 7l){//2015 mahanadu
							voIn.setName("Mahanadu - 2015");
						}else if((Long)objects[0] == 30l){//2016 mahanadu
							voIn.setName("Mahanadu - 2016");
						}
						voIn.setInvitedCount(1l);
						tempList.add(voIn);
						finalMap.put((Long)objects[1],tempList);	*/					
					}
				}
				
				List<Object[]> objList1 = eventAttendeeDAO.getEventAttendeeSummary(cadreIds,IConstants.MAHANADUEVENTIDS);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] objects : objList1) {
						if(finalMap.get((Long)objects[1]) == null){
							List<CadreEventsVO> tempList = new ArrayList<CadreEventsVO>();
							CadreEventsVO voIn = new CadreEventsVO();
							voIn.setId((Long)objects[0]);
							if((Long)objects[0] == 7l){//2015 mahanadu
								voIn.setName("Mahanadu - 2015");
							}else if((Long)objects[0] == 30l){//2016 mahanadu
								voIn.setName("Mahanadu - 2016");
							}
							voIn.setAttendedCount(commonMethodsUtilService.getLongValueForObject(objects[1]));
							tempList.add(voIn);
							finalMap.put((Long)objects[1], tempList);
						}else{
							CadreEventsVO matchedEventVO = getMatchedEventVO((Long)objects[0],finalMap.get((Long)objects[1]));
							if(matchedEventVO != null)
								matchedEventVO.setAttendedCount(1l);
						}
					}
				}
				
				//get training invitee and attendence details
				//0-tdpCadreId,1-count
				List<Object[]> objList2 = trainingCampBatchAttendeeDAO.getTrainingCampInviteeSummary(cadreIds);
				if(objList2 != null && objList2.size() > 0){
					setInviteeCounts(finalMap,objList2,"Training");
				}
				
				List<Object[]> objList3 = trainingCampAttendanceDAO.getTrainingCampAttendanceSummary(cadreIds);
				if(objList3 != null && objList3.size() > 0){
					setAttendeeCount(finalMap,objList3,"Training");
				}
				
				//get partyMeetings attendee and invitee summary
				List<Object[]> objList4 = partyMeetingInviteeDAO.getPartyMeetingInviteeSummary(cadreIds);
				if(objList4 != null && objList4.size() > 0){
					setInviteeCounts(finalMap,objList4,"PartyMeetings");
				}

				List<Object[]> objList5 = partyMeetingAttendanceDAO.getPartyMeetingAttendanceSummary(cadreIds);
				if(objList5 != null && objList5.size() > 0){
					setAttendeeCount(finalMap,objList5,"PartyMeetings");
				}
				
				//get involved and assigned alerts summary
				List<Object[]> objList6 = alertCandidateDAO.getInvolvedAlertsSummary(cadreIds);
				if(objList6 != null && objList6.size() > 0){
					setInviteeCounts(finalMap,objList6,"Alert");
				}
				
				List<Object[]> objList7 = alertAssignedDAO.getAssignedAlertsSummary(cadreIds);
				if(objList7 != null && objList7.size() > 0){
					setAttendeeCount(finalMap,objList7,"Alert");
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCadresEventsSummary", e);
		}
		return finalMap;
	}
	
	public CadreEventsVO getMatchedEventVO(Long eventId,List<CadreEventsVO> voList){
		if(voList != null && voList.size() > 0){
			for (CadreEventsVO cadreEventsVO : voList) {
				if(cadreEventsVO.getId().equals(eventId))
					return cadreEventsVO;
			}
		}
		return null;
	}
	
	public CadreEventsVO getMatchedEventVO(List<CadreEventsVO> voList,String type){
		if(voList != null && voList.size() > 0){
			for (CadreEventsVO cadreEventsVO : voList) {
				if(cadreEventsVO.getName().equalsIgnoreCase(type))
					return cadreEventsVO;
			}
		}
		return null;
	}
	
	public void setInviteeCounts(Map<Long,List<CadreEventsVO>> finalMap,List<Object[]> objList,String type){
		for (Object[] objects : objList) {
			if((Long)objects[0] != null){
			if(finalMap.get((Long)objects[0]) == null){
				List<CadreEventsVO> tempList = new ArrayList<CadreEventsVO>();
				finalMap.put((Long)objects[0], tempList);
			}
			
			CadreEventsVO voIn = new CadreEventsVO();
			voIn.setName(type);
			voIn.setInvitedCount((Long)objects[1]);
			//finalMap.get((Long)objects[0]).add(voIn);
			}
		}
	}
	
	public void setAttendeeCount(Map<Long,List<CadreEventsVO>> finalMap,List<Object[]> objList,String type){
		for (Object[] objects : objList) {
			if((Long)objects[0] != null){
			if(finalMap.get((Long)objects[0]) == null){
				List<CadreEventsVO> tempList = new ArrayList<CadreEventsVO>();
				CadreEventsVO voIn = new CadreEventsVO();
				voIn.setName(type);
				voIn.setAttendedCount((Long)objects[1]);
				tempList.add(voIn);
				//finalMap.put((Long)objects[0], tempList);
			}
			else{
				CadreEventsVO matchedEventVO = getMatchedEventVO(finalMap.get((Long)objects[0]),type);
				if(matchedEventVO == null){
					matchedEventVO = new CadreEventsVO();
					matchedEventVO.setName(type);
					matchedEventVO.setAttendedCount((Long)objects[1]);
					//finalMap.get((Long)objects[0]).add(matchedEventVO);
				}else{
					matchedEventVO.setAttendedCount((Long)objects[1]);
				}
			}
			}
		}
	}
	
	/**
	 * @Author: Srishailam Pittala
	 * @description : to get cadre wise designation details for tdpcadreidsList
	 * @param tdpCadreIdsList
	 * @return Map<Long,String>
	 */
	
	public Map<Long,String> getCadreRolesDetailsByTdpCadreIdsList(List<Long> tdpCadreIdsList,String searchType){
		Map<Long,String> partyPostMap = new LinkedHashMap<Long, String>();
		try {
			if(tdpCadreIdsList != null && tdpCadreIdsList.size() >0){

				if(searchType != null && searchType.equalsIgnoreCase("All") || searchType != null && searchType.equalsIgnoreCase("GOVT_Designation")){
				      List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(tdpCadreIdsList);
				       if(commonMethodsUtilService.isListOrSetValid(partyPositionDetails)){
				         for (Object[] obj : partyPositionDetails) {
				           
				           String level = obj[0] != null ? obj[0].toString() : "" ;
				           String role = obj[1] != null ? obj[1].toString() : "";
				           Long cadreId = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
				           String state = commonMethodsUtilService.getStringValueForObject(obj[6]);
				           String commiteestr = obj[2] != null ? obj[2].toString() : "";
				           if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
				           {
				             level = state+" "+level;
				           }
				           String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
				           partyPostMap.put(cadreId, partyPositionStr);
				        }
				      }	
				       if(searchType != null && searchType.equalsIgnoreCase("GOVT_Designation"))
				    	   return partyPostMap;
				}
				
				if(searchType != null && searchType.equalsIgnoreCase("All") || searchType != null && searchType.equalsIgnoreCase("Party_Designation")){
					 List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(tdpCadreIdsList);
				      if(commonMethodsUtilService.isListOrSetValid(publicRepDertails)){
				        for(Object[] obj:publicRepDertails){
				          Long cadrePositionId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				          String positiontype = obj[2] != null ? obj[2].toString():"";
				          String position = partyPostMap.get(cadrePositionId);
				          if(position != null && position.trim().length() > 0l){
				            position = position+" , "+positiontype;
				          }
				          else{
				            position = positiontype;
				          }
				          partyPostMap.put(cadrePositionId, position);
				        }
				      }
				      
				      if(searchType != null && searchType.equalsIgnoreCase("Party_Designation"))
				    	  return partyPostMap;
				}
		    } 
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreRolesDetailsByTdpCadreIdsList", e);
		}
		return partyPostMap;
	}
	public CadrePerformanceVO getCadrePeoplePerformanceDetails(List<Long> tdpCadreIdsList){
		CadrePerformanceVO returnVO = new CadrePerformanceVO();
		try {
			if(commonMethodsUtilService.isListOrSetValid(tdpCadreIdsList)){
				Map<Long,CadreBasicPerformaceVO> cadreBasicMap =  cadreDetailsService.getCadreCasteDetailsByTdpCadreIds(tdpCadreIdsList);
				Map<Long,CadrePerformanceVO> cadresMap =  new HashMap<Long, CadrePerformanceVO>(0);
				Map<Long,String> cadreGovtDesigntionsMap = getCadreRolesDetailsByTdpCadreIdsList(tdpCadreIdsList,"GOVT_Designation");
				Map<Long,String> cadrePartyDesigntionsMap = getCadreRolesDetailsByTdpCadreIdsList(tdpCadreIdsList,"Party_Designation");
				
				if(commonMethodsUtilService.isMapValid(cadreBasicMap)){
					for (Long cadreId : cadreBasicMap.keySet()) {
						CadrePerformanceVO cadreVO = new CadrePerformanceVO();
						cadreVO.setCadreBasicPerformaceVO(cadreBasicMap.get(cadreId));
						cadreVO.getCadreBasicPerformaceVO().setDesignation(cadreGovtDesigntionsMap.get(cadreId));
						cadreVO.getCadreBasicPerformaceVO().setPartyPosition(cadrePartyDesigntionsMap.get(cadreId));
						
						CadreStatsVO electionVO = new CadreStatsVO();
						electionVO.setSubList(cadreVO.getCadreBasicPerformaceVO().getCadreStatsVOList());
						cadreVO.setCadreStatsVO(electionVO);
						cadresMap.put(cadreId, cadreVO);
					}
					
					Map<Long, List<CadreEventsVO>> eventsMap =  getCadresEventsSummary(tdpCadreIdsList);
					if(commonMethodsUtilService.isMapValid(eventsMap)){
						for (Long cadreId : eventsMap.keySet()) {
							CadrePerformanceVO vo = cadresMap.get(cadreId);							
							CadreEventsVO eventsVO = new CadreEventsVO();
							eventsVO.getSubList().addAll( eventsMap.get(cadreId));
							if(vo != null)
							vo.setCadreEventsVO(eventsVO);
						}
					}
					
					if(commonMethodsUtilService.isMapValid(eventsMap)){
						returnVO.getSubList().addAll(cadresMap.values());
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getCadrePeoplePerformanceDetails() Method, Exception is - ",e);
		}
		return returnVO;
	}
	
	public List<NomintedPostMemberVO> getGoPassedDocumentDuration(List<NomintedPostMemberVO> subList,Long nominateCandId)
	{
		List<NomintedPostMemberVO> returnList = new ArrayList<NomintedPostMemberVO>();
	try{				
		NomintedPostMemberVO returnVo = null;
		List<Long> govtOrderIds = new ArrayList<Long>();
		//Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId		 
		for(NomintedPostMemberVO Vo : subList){
			Long nominatedPostMemberId =nominatedPostMemberDAO.getNominatedPostMemberId(Vo.getId(),Vo.getLevelId(),Vo.getDeptId(),Vo.getDeptBoardId(),Vo.getDeptBoardPostnId());
			Long nominatedPostId = nominatedPostDAO.getOfNominatedPostCondidates(nominateCandId,nominatedPostMemberId);
			List<Object[]> goPassedDates = nominatedPostGovtOrderDAO.gettingGoPassedDates(nominatedPostId);
			if(goPassedDates != null && goPassedDates.size()>0){
				for(Object[] level : goPassedDates){
					Vo.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(level[0]));
					Vo.setFromDate(commonMethodsUtilService.getStringValueForObject(level[1]));
					Vo.setToDate(commonMethodsUtilService.getStringValueForObject(level[2]));
					Vo.setGovtOrderId(commonMethodsUtilService.getLongValueForObject(level[3]));
				}
			}
		}
	}catch(Exception e){
		LOG.error("Exception Occured in getGoPassedDocumentDuration()", e);
	}
	return returnList;
}
	
  public List<LocationsVO> getLocationByDepartment(Long levelId,Long departmentId,Long boardId,Long positionId)
	{
		List<LocationsVO> returnList = new ArrayList<LocationsVO>();
	try{				
		LocationsVO returnVo = null;
		//Long levelId,Long deptId,Long boardId,Long positionId		 
			List<Object[]> locationWiseDepartments = nominatedPostMemberDAO.getLocationByDepartment(levelId,departmentId,boardId,positionId);
			if(locationWiseDepartments != null && locationWiseDepartments.size()>0){
				for(Object[] level : locationWiseDepartments){
					returnVo =new LocationsVO();
					returnVo.setPostMemberId(commonMethodsUtilService.getLongValueForObject(level[0]));
					returnVo.setStateId(commonMethodsUtilService.getLongValueForObject(level[1])); 
					returnVo.setDistrictId(commonMethodsUtilService.getLongValueForObject(level[2]));
					returnVo.setConstId(commonMethodsUtilService.getLongValueForObject(level[3]));
					returnVo.setTehsilId(commonMethodsUtilService.getLongValueForObject(level[4]));
					returnVo.setPanchayatId(commonMethodsUtilService.getLongValueForObject(level[5]));
					returnVo.setLocatElectBodyId(commonMethodsUtilService.getLongValueForObject(level[6]));
					returnVo.setWardId(commonMethodsUtilService.getLongValueForObject(level[7]));
				}
				returnList.add(returnVo);
			}
		
	}catch(Exception e){
		LOG.error("Exception Occured in getGoPassedDocumentDuration()", e);
	}
	return returnList;
} 
  
  public void setBrdWisNominPstAppliedDepOrCorpApplledDetails(List<Object[]> depOCorpList,List<Long> apllicationIds,List<NominatedPostVO> returnVoList,String status)
  {
	  try{
		  if(depOCorpList != null && depOCorpList.size() > 0){
				for (Object[] obj : depOCorpList) {
					apllicationIds.add(commonMethodsUtilService.getLongValueForObject(obj[11]));
					NominatedPostVO VO = new NominatedPostVO();	
					VO.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					VO.setLocationVal(commonMethodsUtilService.getLongValueForObject(obj[10]));
					if(VO.getId() == 1L){
						VO.setName("India");
					}
					else if(VO.getId() == 2L){
						VO.setName(stateDAO.get(VO.getLocationVal()).getStateName());
					}
					else if(VO.getId() == 3L){
						VO.setName(districtDAO.get(VO.getLocationVal()).getDistrictName());
					}
					else if(VO.getId() == 4L){
						VO.setName(constituencyDAO.get(VO.getLocationVal()).getName());
					}
					else if(VO.getId() == 5L){
						VO.setName(tehsilDAO.get(VO.getLocationVal()).getTehsilName());
					}
					else if(VO.getId() == 6L){
						VO.setName(localElectionBodyDAO.get(VO.getLocationVal()).getName());
					}
					else if(VO.getId() == 7L){
						VO.setName(panchayatDAO.get(VO.getLocationVal()).getPanchayatName());
					}
					VO.setStateId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					VO.setPerc(commonMethodsUtilService.getStringValueForObject(obj[1]));
					VO.setHno(commonMethodsUtilService.getStringValueForObject(obj[3]));
					VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[4]));//deptId
					VO.setMobileNo(commonMethodsUtilService.getStringValueForObject(obj[5]));//deptName
					VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));//boardId
					VO.setPincode(commonMethodsUtilService.getStringValueForObject(obj[7]));//boardName
					VO.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));//positnId
					VO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(obj[9]));//positnName
					VO.setAddDistrictName(commonMethodsUtilService.getLongValueForObject(obj[11]));//applicationId
					if(status != null && status.equalsIgnoreCase("expired") ){
						returnVoList.add(0,VO);
					}else{
					returnVoList.add(VO);
					}
					
				}
								
			}
		  
	  }catch(Exception e){
		  LOG.error("Exception Occured in setBrdWisNominPstAppliedDepOrCorpApplledDetails()", e);
	  }
	
  }
  /* public List<CadreEventsVO> getCadreDetailedReportEventAttendee(Long parentEventId,Long cadreId,List<Long> extraEventIdsList)
	{
		List<CadreEventsVO> returnList = new ArrayList<CadreEventsVO>();
		List<CadreEventsVO> daywiseList = new ArrayList<CadreEventsVO>();
		//List<Object[]>  eventAttendeeDetailsMaxTime =null;
		//List<Object[]>  eventAttendeeDetailsMinTime =null;
		String eventAttendeeDetailsMaxTime1 = null;
		String eventAttendeeDetailsMinTime1 =null;
	try{				
		CadreEventsVO returnVo = null;
		    List<Object[]> eventAttendeeDetails = eventAttendeeDAO.getCadreDetailedReportEventAttendeeDetailsDayWise(parentEventId,cadreId);
			//List<Object[]> eventAttendeeDetailsDayWise = eventAttendeeDAO.getCadreDetailedReportEventAttendeeDetailsDayWise(parentEventId,cadreId);
			//eventAttendeeDetailsMaxTime =eventAttendeeDAO.getCadreDetailedReportEventAttendeeMaxTime(extraEventIdsList);
			//eventAttendeeDetailsMinTime =eventAttendeeDAO.getCadreDetailedReportEventAttendeeMinTime(extraEventIdsList);
			
			//eventAttendeeDetailsMaxTime1 =eventAttendeeDAO.getCadreDetailedReportEventAttendeeMaxTime1(extraEventIdsList);
			//eventAttendeeDetailsMinTime1 =eventAttendeeDAO.getCadreDetailedReportEventAttendeeMinTime1(extraEventIdsList);
			
		   if(eventAttendeeDetails != null && eventAttendeeDetails.size()>0){
				for(Object[] param : eventAttendeeDetails){
					CadreEventsVO subVo = new CadreEventsVO();
					subVo.setEventId(commonMethodsUtilService.getLongValueForObject(param[0]));
					//subVo.setName("day1");
					//subVo.setEventName("25-3-2017");
					daywiseList.add(subVo);
				}
			}
			if(eventAttendeeDetails != null && eventAttendeeDetails.size()>0){
				for(Object[] param : eventAttendeeDetails){
					returnVo =new CadreEventsVO();
					returnVo.setEventId(commonMethodsUtilService.getLongValueForObject(param[0]));
					returnVo.setEventName(commonMethodsUtilService.getStringValueForObject(param[1])); 
					returnVo.setAttendedTime(commonMethodsUtilService.getStringValueForObject(param[2]));					
					
				returnVo.setSubList(daywiseList);
				
				}
				
				Long timeDeff =null;
				String eventMaxTime = null;
				String eventMinTime =null;
				/*if(eventAttendeeDetailsMaxTime != null && eventAttendeeDetailsMaxTime.size()>0){
					for(Object[] param : eventAttendeeDetailsMaxTime){
						CadreEventsVO subVo1 = new CadreEventsVO();
						subVo1.setEventAttendedTime(commonMethodsUtilService.getStringValueForObject(param[0]));
						subVo1.setEventMaxTime(commonMethodsUtilService.getStringValueForObject(param[1]).substring(11, 18));
						
						 //eventMaxTime = subVo1.getEventMaxTime();
						durationList.add(subVo1);
						//returnVo.setSubList(durationList);
					}
				}*/
				//returnVo.setTimedifferance(timeDeff);
				/*returnList.add(returnVo);
			}
		
	}catch(Exception e){
		LOG.error("Exception Occured in getGoPassedDocumentDuration()", e);
	}
	return returnList;
 }*/
  public List<CadrePerformanceVO> getCampDetails(List<Long> tdpCadreIdsList){
	  List<CadrePerformanceVO> returnList = new ArrayList<CadrePerformanceVO>();
	  List<Long> batchIds = new ArrayList<Long>();
	  List<Long> cadreIds = new ArrayList<Long>();
	  try{	  
		  List<Object[]> batchIdDetails =trainingCampAttendanceDAO.getBatchIds(tdpCadreIdsList);
		  if(batchIdDetails != null && batchIdDetails.size()>0){
			for(Object[] param : batchIdDetails) {
				CadrePerformanceVO  returnVo = getMatchedVO1(returnList,commonMethodsUtilService.getLongValueForObject(param[0]));
				if(returnVo == null){
					returnVo = new CadrePerformanceVO();
					returnList.add(returnVo);
				}
                if(returnVo.getAttendedTime() != null && returnVo.getAttendedTime() != commonMethodsUtilService.getStringValueForObject(param[3]) && !returnVo.getAttendedTime().contains(commonMethodsUtilService.getStringValueForObject(param[3]))){
                	returnVo.setNoOfAttendanceDays(returnVo.getNoOfAttendanceDays()+1l);
                	returnVo.setAttendedTime(returnVo.getAttendedTime()+','+commonMethodsUtilService.getStringValueForObject(param[3]));
				}else{
					returnVo.setAttendedTime(commonMethodsUtilService.getStringValueForObject(param[3]));
					returnVo.setNoOfAttendanceDays(1l);
				}
				 returnVo.setAttendanceId(commonMethodsUtilService.getLongValueForObject(param[2]));
				
				 returnVo.setBatchId(commonMethodsUtilService.getLongValueForObject(param[0]));
				
				 returnVo.setNoOfDaysProgram(commonMethodsUtilService.getLongValueForObject(param[4]));
				 returnVo.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
			 //if(!cadreIds.contains(commonMethodsUtilService.getLongValueForObject(param[1])))
					cadreIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				
		     if(!batchIds.contains(commonMethodsUtilService.getLongValueForObject(param[0])))
				 batchIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));	
				 
			}
			
		  }
		List<Object[]> campDetails =trainingCampAttendanceDAO.getCampDetails(batchIds);
		  if(campDetails != null && campDetails.size()>0){
			  for(Object[] obj : campDetails){
				  CadrePerformanceVO  Vo = getMatchedVO1(returnList,commonMethodsUtilService.getLongValueForObject(obj[0]));
				  if(Vo != null){
					  Vo.setTraingName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					  Vo.setTraingCamp(commonMethodsUtilService.getStringValueForObject(obj[2]));
					  Vo.setBatchNmae(commonMethodsUtilService.getStringValueForObject(obj[3]));
					  Vo.setStartDate(commonMethodsUtilService.getStringValueForObject(obj[4]));
					  Vo.setEnddate(commonMethodsUtilService.getStringValueForObject(obj[5]));
					  Vo.setNoOfBatchDays(commonMethodsUtilService.getLongValueForObject(obj[6]));
					  Vo.setNoOfDayBetweenDates(dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(obj[4]),commonMethodsUtilService.getStringValueForObject(obj[5])));
				  }
			  }
		  }
		  List<Object[]> trainingFeedbackDetails = trainingCampCadreFeedbackDetailsDAO.getTrainingFeedbackDetails(cadreIds);
		  if(trainingFeedbackDetails != null && trainingFeedbackDetails.size()>0){
			  for(Object[] event :trainingFeedbackDetails){
				  CadrePerformanceVO stausVo =getMatchedVOBycadre(returnList,commonMethodsUtilService.getLongValueForObject(event[0]));
				  if(stausVo != null){
					  stausVo.setId(commonMethodsUtilService.getLongValueForObject(event[0]));
					  stausVo.setFeedBackStatus(commonMethodsUtilService.getStringValueForObject(event[1]));
				  }
			  }
		  }
		  
	    }catch(Exception e){
		  LOG.error("Exception Occured in getCampDetails()", e);
	   }
	return returnList;
  }
  public CadrePerformanceVO getMatchedVO1(List<CadrePerformanceVO> returnList,Long id)
	{
		try{
			if(returnList == null || returnList.size() == 0)
				return null;
			for(CadrePerformanceVO vo : returnList)
			{
				if(vo.getBatchId() == id.longValue() )
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
  public CadrePerformanceVO getMatchedVOBycadre(List<CadrePerformanceVO> returnList,Long id)
 	{
 		try{
 			if(returnList == null || returnList.size() == 0)
 				return null;
 			for(CadrePerformanceVO vo : returnList)
 			{
 				if(vo.getId() == id.longValue() )
 					return vo;
 			}
 		}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		return null;
 	}
  /**
	 * @Author: Krishna 
	 * @description : to get cadre wise mahanadu event details for tdpcadreidsList
	 * @param tdpCadreIdsList
	 * @param parentEventId
	 * @return List<EventDetailsVO>
	 */
	  public List<EventDetailsVO> getMahanaduEventDetilsByCadreIdDetils(Long parentEventId,Long tdpCadreId){
		             List<EventDetailsVO> finalList  = new ArrayList<EventDetailsVO>();
			       try{
			         LOG.info("Entered into nominatedPostProfileService of getMahanaduEventDetilsByCadreIdDetils");
					  Map<String,Map<String,EventDetailsVO>> newEventMap = new HashMap<String,Map<String,EventDetailsVO>>();
					  Map<String,EventDetailsVO> subEventMap = null;
					  
					  SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
					  DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
					
					  SimpleDateFormat newFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
					  SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					 
					  List<Long> subEventIds = new ArrayList<Long>();
					  List<Object[]> subEventObjs = eventDAO.getSubEventsDetailsByParentEventId(parentEventId);
					  if(subEventObjs != null && !subEventObjs.isEmpty()){
						  for(Object[] objs : subEventObjs){
							  EventDetailsVO vo = new EventDetailsVO();
							  vo.setId(objs[0]!= null?(Long)objs[0]:0l);
							  vo.setName(objs[1]!= null?objs[1].toString():"");
							  subEventIds.add(objs[0]!= null?(Long)objs[0]:0l);
							  finalList.add(vo);
						  }
					  }
			  
			 
					    Object[] dateObj = eventDAO.getBetweenDatesOfEvent(parentEventId);
					    if(dateObj != null){
					    	Date fromDate = (Date)dateObj[0];
					    	Date toDate = (Date)dateObj[1];
					    	List<Date> datesList = commonMethodsUtilService.getBetweenDates(fromDate,toDate);
						    	for(EventDetailsVO vo : finalList){
							    	  for(Date dateStr : datesList){		    		  
							    		      EventDetailsVO entsVO = new EventDetailsVO();
							    		      entsVO.setDateStr(newDateFormat.format(newFormat.parse(dateStr.toString())));
							    	    	  vo.getLocationList().add(entsVO);
							    		    }
							           }  		    	  
						       }   
					    
			    
							    List<Object[]> eventAttendeeDetilsObj = eventAttendeeDAO.getMahanaduEventCadreDetails(subEventIds, tdpCadreId);
								  if(eventAttendeeDetilsObj != null && eventAttendeeDetilsObj.size()>0){
										  
										    for(Object[] param : eventAttendeeDetilsObj){
										    	
										     String eventName = param[1] != null ? param[1].toString():"";
											  
										       subEventMap = newEventMap.get(eventName);
											     if(subEventMap == null){
												  subEventMap = new HashMap<String,EventDetailsVO>();
												  newEventMap.put(eventName, subEventMap);
											   }
											  String eventDate =  param[3] != null ? param[3].toString():"";
											  EventDetailsVO evntsVO = subEventMap.get(eventDate);
											  
												   if(evntsVO == null){
													   evntsVO = new EventDetailsVO();
													   evntsVO.setName(eventName);
													   evntsVO.setDateStr(eventDate);
													   evntsVO.setType(param[4] != null ? dateFormat.format(sdf.parse(param[4].toString())):"");
													
													   subEventMap.put(eventDate, evntsVO);
												    }
										        }	   
								            }
								  
							 
							  if(finalList != null && finalList.size()>0 && !finalList.isEmpty()){
								    for(EventDetailsVO eventsVO : finalList){
										   Map<String,EventDetailsVO> subMap = newEventMap.get(eventsVO.getName());
										   if(subMap == null){
											   subMap = new HashMap<String,EventDetailsVO>();
											   List<EventDetailsVO> subList = eventsVO.getLocationList();
												 for(EventDetailsVO childVO : subList){
													 String date = childVO.getDateStr();
													 Set<String> dateKeys = subMap.keySet();
													 for(String dateStrng : dateKeys){
														if(dateStrng.contains(date)){
															childVO.setDateStr(date);
															if(subMap.containsKey(date)) {
																EventDetailsVO detailsVO1 = subMap.get(date);
																childVO.setTime(detailsVO1.getType());
															 }	  
														  }
													  }	  
												  }
										   }else{
											   List<EventDetailsVO> subList = eventsVO.getLocationList();
												  for(EventDetailsVO childVO : subList){
													  String date = childVO.getDateStr();
													  Set<String> dateKeys = subMap.keySet();
													  for(String dateStrng : dateKeys){
														  if(dateStrng.contains(date)){
															  childVO.setDateStr(date);
														   if(subMap.containsKey(date)) {
															   EventDetailsVO detailsVO1 = subMap.get(date);
															   childVO.setTime(detailsVO1.getType());
															}	  
														  }	  
													  }	  
											     }
										     }
								         }	  
							        }				
			}catch(Exception e){
			  LOG.error("Exception Occured into nominatedPostProfileService of getMahanaduEventDetilsByCadreIdDetils");
			}
	return finalList;
	 }
	  
	    /**
		 * @Author : Santosh 
		 * @param  :  NominatedPostDetailsVO nominatedPostDtlsVO 
		 * @param  :  Long userId
		 * @return : ResultStatus
		 * description :  This service method is used to saving nominated post candidate details.
		 */
	  public ResultStatus saveNominatedPostProfileDtls(final NominatedPostDetailsVO nominatedPostDtlsVO,final Long userId){
		  ResultStatus statusVO = new ResultStatus();	
		   try {
			   statusVO = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					ResultStatus statusVO = new ResultStatus();
					
					if (nominatedPostDtlsVO.getSubList() != null && nominatedPostDtlsVO.getSubList().size() > 0) { // candiate saving
						
						 for (NominatedPostDetailsVO positioDetailsVO : nominatedPostDtlsVO.getSubList()) {
							 
							  if (positioDetailsVO.getSubList1() != null && positioDetailsVO.getSubList1().size() > 0 ) {
								  
								   for (NominatedPostCandidateDtlsVO postCandiateVO : positioDetailsVO.getSubList1()) {
									   List<Long> candiateIdList = nominationPostCandidateDAO.getNominatedPostCondidates(postCandiateVO.getTdpCadreId());
									   Long nominatedPostCandiateId = 0l;
									   if (candiateIdList != null && candiateIdList.size() > 0 ) {
										   nominatedPostCandiateId = candiateIdList.get(0);
									   } else {
										   NominationPostCandidate nominationPostCandidate = new NominationPostCandidate(); 
										   TdpCadre tdpCadre = tdpCadreDAO.get(postCandiateVO.getTdpCadreId());
										   nominationPostCandidate.setTdpCadreId(postCandiateVO.getTdpCadreId());
										   nominationPostCandidate.setVoterId(tdpCadre.getVoterId());
										   nominationPostCandidate.setCandidateName(tdpCadre.getFirstname());
										   nominationPostCandidate.setMobileNo(tdpCadre.getMobileNo());
										   nominationPostCandidate.setHouseno(tdpCadre.getHouseNo());
										   nominationPostCandidate.setGender(tdpCadre.getGender());
										   nominationPostCandidate.setAge(tdpCadre.getAge());
										   nominationPostCandidate.setDob1(tdpCadre.getDateOfBirth());
										   nominationPostCandidate.setNominatedPostAgeRangeId(null);//need to run script at backend
										   nominationPostCandidate.setRelativename(tdpCadre.getRelativename());
										   nominationPostCandidate.setRelativetype(tdpCadre.getRelativeType());
										   nominationPostCandidate.setImageurl(tdpCadre.getImage());
										   nominationPostCandidate.setCastestateId(tdpCadre.getCasteStateId());
										   nominationPostCandidate.setAddressId(tdpCadre.getUserAddress().getUserAddressId());
										   nominationPostCandidate.setInsertedBy(userId);
										   nominationPostCandidate.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										   nominationPostCandidate.setUpdatedBy(userId);
										   nominationPostCandidate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										   nominationPostCandidate.setIsDeleted("N");
										   nominationPostCandidate = nominationPostCandidateDAO.save(nominationPostCandidate);

										   nominatedPostCandiateId = nominationPostCandidate.getNominationPostCandidateId();
										   
									   }
									   /*Setting nominatedPostCandiateId  to save in other place*/
									   postCandiateVO.setNominatedPostCandiateId(nominatedPostCandiateId);
									   
									  /*getting nominatedPostMemberId,boardlevelId,levelvalue and addressId */
									   List<Object[]> objList = nominatedPostMemberDAO.getNominatedPostPositionDtls(postCandiateVO.getDepartmentId(), postCandiateVO.getBoardId(), postCandiateVO.getPositionId());
									    if (objList != null && objList.size() > 0 ){
									    	Object[] obj = objList.get(0);
									    	 if (obj.length > 0) {
									    		 postCandiateVO.setNominatedPostMemberId(commonMethodsUtilService.getLongValueForObject(obj[0]));
									    		 postCandiateVO.setBoardLevelId(commonMethodsUtilService.getLongValueForObject(obj[1]));
									    		 postCandiateVO.setLevelValue(commonMethodsUtilService.getLongValueForObject(obj[2]));
									    		 postCandiateVO.setAddressId(commonMethodsUtilService.getLongValueForObject(obj[3]));
									    		 
									    	 }
									    }
									    
									    NominatedPostApplication nominatedPostApplication = new NominatedPostApplication(); 
									    nominatedPostApplication.setNominationPostCandidateId(postCandiateVO.getNominatedPostCandiateId());
									    nominatedPostApplication.setNominatedPostMemberId(postCandiateVO.getNominatedPostMemberId());
									    nominatedPostApplication.setDepartmentId(postCandiateVO.getDepartmentId());
									    nominatedPostApplication.setBoardId(postCandiateVO.getBoardId());
									    nominatedPostApplication.setPositionId(postCandiateVO.getPositionId());
									    nominatedPostApplication.setAddressId(postCandiateVO.getAddressId());
									    nominatedPostApplication.setBoardLevelId(postCandiateVO.getBoardLevelId());
									    nominatedPostApplication.setLocationValue(postCandiateVO.getLevelValue());
									    nominatedPostApplication.setPostTypeId(1l);//1 - nominatedPost
									    nominatedPostApplication.setApplicationStatusId(5l);//5-confirm 
									    nominatedPostApplication.setInsertedBy(userId);
									    nominatedPostApplication.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostApplication.setUpdatedBy(userId);
									    nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostApplication.setIsDeleted("N");
									    nominatedPostApplication.setIsExpired("N");
									    nominatedPostApplication = nominatedPostApplicationDAO.save(nominatedPostApplication);
									    
									    /*setting nominatedPostApplicationId in candidateVO */ 
									    postCandiateVO.setNominatedPostApplicationId(nominatedPostApplication.getNominatedPostApplicationId());
									    
									    
									    NominatedPostApplicationHistory nominatedPostApplicationHistory = new NominatedPostApplicationHistory(); 
									    nominatedPostApplicationHistory.setTrackedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostApplicationHistory.setNominatedPostApplicationId(postCandiateVO.getNominatedPostApplicationId());
									    nominatedPostApplicationHistory.setNominationPostCandidateId(postCandiateVO.getNominatedPostCandiateId());
									    nominatedPostApplicationHistory.setDepartmentId(postCandiateVO.getDepartmentId());
									    nominatedPostApplicationHistory.setBoardId(postCandiateVO.getBoardId());
									    nominatedPostApplicationHistory.setPositionId(postCandiateVO.getPositionId());
									    nominatedPostApplicationHistory.setBoardLevelId(postCandiateVO.getBoardLevelId());
									    nominatedPostApplicationHistory.setLocationValue(postCandiateVO.getLevelValue());
									    nominatedPostApplicationHistory.setApplicationStatusId(5l);//5-confirm 
									    nominatedPostApplicationHistory.setInsertedBy(userId);
									    nominatedPostApplicationHistory.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostApplicationHistory.setUpdatedBy(userId);
									    nominatedPostApplicationHistory.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostApplicationHistory.setIsDeleted("N");
									    nominatedPostApplicationHistoryDAO.save(nominatedPostApplicationHistory);
									    
									    
									    /* getting nominatedPosId by nominatedPostMemberid */
									     if (postCandiateVO.getNominatedPostMemberId() != null && postCandiateVO.getNominatedPostMemberId() > 0 ) {
									    	 List<Long> nominatedPostIds = nominatedPostDAO.getNominatedPostIdByMemberId(postCandiateVO.getNominatedPostMemberId());
										     if (nominatedPostIds != null && nominatedPostIds.size() > 0 ) {
										    	 postCandiateVO.setNominatedPostCandiateId(nominatedPostIds.get(0));
										     } 
									     }
									    
									    NominatedPostFinal nominatedPostFinal = new NominatedPostFinal();
									    nominatedPostFinal.setNominatedPostMemberId(postCandiateVO.getNominatedPostMemberId());
									    nominatedPostFinal.setNominationPostCandidateId(postCandiateVO.getNominatedPostCandiateId());
									    nominatedPostFinal.setNominatedPostApplicationId(postCandiateVO.getNominatedPostApplicationId());
									    nominatedPostFinal.setNominatedPostId(postCandiateVO.getNominatedPostId());
									    nominatedPostFinal.setApplicationStatusId(5l);//5-confirm
									    nominatedPostFinal.setInsertedBy(userId);
									    nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostFinal.setUpdatedBy(userId);
									    nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									    nominatedPostFinal.setIsDeleted("N");
									    nominatedPostFinal.setIsExpired("N");
									    /* saving isPrefered Y if referal member is there else N */
									     if (nominatedPostDtlsVO.getSubList2() != null && nominatedPostDtlsVO.getSubList2() .size() > 0 ) {
									    	 nominatedPostFinal.setIsPrefered("Y");
									     } else {
									    	 nominatedPostFinal.setIsPrefered("N");
									     }
									      nominatedPostFinalDAO.save(nominatedPostFinal);
									      
									      /* Updating Nominated post */
									      if (postCandiateVO.getNominatedPostId() != null && postCandiateVO.getNominatedPostId().longValue() > 0 ) {
									    	  NominatedPost nominatedPost = nominatedPostDAO.get(postCandiateVO.getNominatedPostId());
									    	  nominatedPost.setNominatedPostMemberId(postCandiateVO.getNominatedPostMemberId());
									    	  nominatedPost.setNominationPostCandidateId(postCandiateVO.getNominatedPostCandiateId());
									    	  nominatedPost.setNominatedPostStatusId(3l);//3 - confirm
									    	  nominatedPost.setUpdatedBy(userId);
									    	  nominatedPost.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									    	  nominatedPostDAO.save(nominatedPost);
									    	  
									      }
									     /*saving referal details if exist*/
									     if (nominatedPostDtlsVO.getSubList2() != null && nominatedPostDtlsVO.getSubList2() .size() > 0 ) {
									    	 for (NominatedPostCandidateDtlsVO refCandidVO : nominatedPostDtlsVO.getSubList2()) {
												 NominatedPostReferDetails nominatedPostReferDetails = new NominatedPostReferDetails();
												 nominatedPostReferDetails.setNominationPostCandidateId(postCandiateVO.getNominatedPostCandiateId());
												 nominatedPostReferDetails.setNominatedPostApplicationId(postCandiateVO.getNominatedPostApplicationId());
												 nominatedPostReferDetails.setReferCadreId(refCandidVO.getTdpCadreId());
												 nominatedPostReferDetails.setInsertedBy(userId);
												 nominatedPostReferDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
												 nominatedPostReferDetails.setUpdatedBy(userId);
												 nominatedPostReferDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
												 nominatedPostReferDetails.setIsDeleted("N");
												 nominatedPostReferDetailsDAO.save(nominatedPostReferDetails);
											}
									     }
								}
							  }
						}
						statusVO.setMessage("Saved Successfully");
						statusVO.setResultCode(1);
					}
					return statusVO;
				}
			});
			} catch (Exception e) {
				 statusVO.setMessage("Exception Occured.Pls Try Again");
				 statusVO.setResultCode(0);
				Log.error("Exception raised at saveNominatedPostProfileDtls in NominatedPostProfileService class", e);
			}
			return statusVO;
	}
 }


