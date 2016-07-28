package com.itgrids.partyanalyst.service.impl;


import java.io.File;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IApplicationDocumentDAO;
import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationHistoryDAO;
import com.itgrids.partyanalyst.dao.INominatedPostCommentDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.dao.INominatedPostReferDetailsDAO;
import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreReportDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.TehsilDAO;
import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ApplicationDocument;
import com.itgrids.partyanalyst.model.NominatedPost;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.model.NominatedPostApplicationHistory;
import com.itgrids.partyanalyst.model.NominatedPostComment;
import com.itgrids.partyanalyst.model.NominatedPostFinal;
import com.itgrids.partyanalyst.model.NominatedPostReferDetails;
import com.itgrids.partyanalyst.model.NominationPostCandidate;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
	public List<IdNameVO> getDepartments(Long postTpe){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = departmentsDAO.getDepartments(postTpe);
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
	public List<IdNameVO> getDepartmentBoard(Long depmtId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = departmentBoardDAO.getDepartmentBoardByDeptId(depmtId);
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name","districtid"};
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
	public List<IdNameVO> getDepartmentBoardPositions(Long deptId,Long boardId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = departmentBoardPositionDAO.getDepartmentBoardPositionsByDeptIdABrdId(deptId,boardId);
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name","districtid"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
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
	public NomintedPostMemberVO getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type){
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
			
			//0.nominationPostCandidateId,1.tdpCadreId,2.voterId,3.candidateName,4.mobileNo,5.cadreFirstname,6.cadreMobileNo,7.age,
						//8.caste,9.subCaste,10.casteName,11.applicationStatusId,12.status,13.nominatedPostId
			List<Object[]> list = nominatedPostFinalDAO.getNominatedPostMemberDetails(levelId, levelValue, departmentId, boardId, positionId, type);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"nominatedPostCandidateId","tdpCadreId","voterId","voterName","voterMoblie","cadreName","cadreMobile","age",
							"caste","subCaste","casteName","applStatusId","status","nominatePostApplicationId"};
				subList = (List<NomintedPostMemberVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.NomintedPostMemberVO");
			}
			
			if(commonMethodsUtilService.isListOrSetValid(subList)){
				for (NomintedPostMemberVO vo : subList) {
					Long cadreId = vo.getTdpCadreId();
					Long nominatedPostCandidateId = vo.getNominatedPostCandidateId();
					if(cadreId != null && cadreId.longValue() > 0l){
						tdpCadreIds.add(cadreId);
					}
					if(nominatedPostCandidateId != null && nominatedPostCandidateId.longValue() > 0l){
						nominatedPostCandidateIds.add(nominatedPostCandidateId);
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(nominatedPostCandidateIds)){
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getAnyAppliedDepartmentsCountForCandidateList(nominatedPostCandidateIds);
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
				List<Long> otherDeptShortListed = nominatedPostFinalDAO.getAnyShortlistedDepartmentsForCandidateList(nominatedPostCandidateIds);
				if(commonMethodsUtilService.isListOrSetValid(otherDeptShortListed)){
					for (Long id : otherDeptShortListed) {
						deptShortListedMap.put(id, "YES");
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
			 
			if(commonMethodsUtilService.isListOrSetValid(nominatedPostCandidateIds)){
				List<Object[]> referList = nominatedPostReferDetailsDAO.getReferedCountForCandidateList(nominatedPostCandidateIds);
				 if(commonMethodsUtilService.isListOrSetValid(referList)){
					 for (Object[] obj : referList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						referMap.put(id, count);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NomintedPostMemberVO vo : subList) {
						 Long count = referMap.get(vo.getNominatedPostCandidateId());
						 vo.setReferCandCount(count);
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
	
	public List<IdNameVO> getReferCadreDetailsForCandidate(Long candidateId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try {
			List<Long> cadreIds = new ArrayList<Long>();
			Map<Long,String> publicRepMap = new LinkedHashMap<Long, String>();
			Map<Long,String> partyPostMap = new LinkedHashMap<Long, String>();
			Map<Long,Long> referedCount = new LinkedHashMap<Long, Long>();
			
			List<Object[]> list = nominatedPostReferDetailsDAO.getReferedCadreDetailsForCandidate(candidateId);
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
						publicRepMap.put(cadreId, position);
					}
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
					Long nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
					if(nominatedPostMemberId != null && nominatedPostMemberId.longValue() > 0l){
							NominatedPostFinal nominatedPostFinal = new NominatedPostFinal();
							
							nominatedPostFinal.setNominatedPostMemberId(nominatedPostMemberId);
							nominatedPostFinal.setNominationPostCandidateId(candidateId);
							nominatedPostFinal.setApplicationStatusId(statusId);
							nominatedPostFinal.setInsertedBy(userId);
							nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							nominatedPostFinal.setUpdatedBy(userId);
							nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							nominatedPostFinal.setIsDeleted("N");
							
							nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
						}
					
						NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(nominatePostApplicationId);
						
						savingNominatedPostApplicationHistoryDetails(nominatedPostApplication);
						
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
	
	public void savingNominatedPostApplicationHistoryDetails(final NominatedPostApplication nominatedPostApplication){
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
					nominatedPostApplicationHistory.setApplicationStatusId(nominatedPostApplication.getApplicationStatusId());
					nominatedPostApplicationHistory.setInsertedBy(nominatedPostApplication.getInsertedBy());
					nominatedPostApplicationHistory.setInsertedTime(nominatedPostApplication.getInsertedTime());
					nominatedPostApplicationHistory.setUpdatedBy(nominatedPostApplication.getUpdatedBy());
					nominatedPostApplicationHistory.setUpdatedTime(nominatedPostApplication.getUpdatedTime());
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
								nominationPostCandidate.setAge(commonMethodsUtilService.getLongValueForObject(cadre[17]));
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
					
					if(nominatedCandiPostId !=null && nominatedCandiPostId.longValue()>0l){						
						NominationPostCandidate tc = nominationPostCandidateDAO.getUserAddressByCandidate(nominatedCandiPostId);//tdpCadreId						
						//UserAddress UA = new UserAddress();					
						UserAddress UA = null;					
						if(tc !=null){ 
							 UA = userAddressDAO.get(tc.getAddressId());
							 tc.setMobileNo(nominatedPostVO.getMobileNo());
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
		nominatedPostApplication.setPositionId(position != null ? Long.parseLong(position.trim()) : null) ;
		
		nominatedPostApplication.setNominationPostCandidateId(nominatedPostCandi != null ? nominatedPostCandi : null);
		
		if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 1){
			nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
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
		
		nominatedPostApplication.setDepartmentId(Vo.getDeptId() != null ? Vo.getDeptId() : null) ;
		nominatedPostApplication.setBoardId(Vo.getDeptBoardId() != null ? Vo.getDeptBoardId() : null) ;
		nominatedPostApplication.setInsertedBy(loggerUserId);
		nominatedPostApplication.setUpdatedBy(loggerUserId);
		nominatedPostApplication.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		nominatedPostApplication.setIsDeleted("N");
		nominatedPostApplication.setApplicationStatusId(1l);
		nominatedPostApplication.setAddressId(nominatedPostAddress.getUserAddressId());
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
					//District
					vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[5]));
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
					}
					vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(obj[6]));
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
					vo.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[7]));
					if(vo.getMandalId()!= null && vo.getMandalId() > 0){
						List<Long> constituencyIds = new ArrayList<Long>(0);
						constituencyIds.add(vo.getConstituencyId());
						List<LocationWiseBoothDetailsVO> list = cadreCommitteeService.getMandalMunicCorpDetailsOfConstituencies(constituencyIds);
						if(commonMethodsUtilService.isListOrSetValid(list))
							for (LocationWiseBoothDetailsVO vo1 : list) {
								vo.getMandalsList().add(new IdNameVO(vo1.getLocationId(),vo1.getLocationName()));
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
					
					vo.setPanchayatId(commonMethodsUtilService.getLongValueForObject(obj[8]));
					//Panchayats
					//if(vo.getPanchayatId()!= null && vo.getPanchayatId() > 0){
						List<Long>  locationIds = new ArrayList<Long>(0);
						locationIds.add(vo.getMandalId());
						
						List<Long>  lebsList = new ArrayList<Long>(0);
						lebsList.add(commonMethodsUtilService.getLongValueForObject(obj[22]));
						
						
						List<LocationWiseBoothDetailsVO> list = cadreCommitteeService.getPanchayatWardDivisionDetailsOfSubLocation(null, locationIds, lebsList);
						if(commonMethodsUtilService.isListOrSetValid(list))
							for (LocationWiseBoothDetailsVO vo1 : list) 
								vo.getPanList().add(new IdNameVO(vo1.getLocationId(),vo1.getLocationName()));
							
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
			
			//{"TOTAL CORPORATIONS","APPLICATIONS NOT RECIEVED","YET TO START","RUNNING","READY FOR FINAL REVIEW","FINALYZED","GO PASSED / COMPLETED"}
			Map<String,NominatedPostVO> applicationsStatusDtlsMap = new LinkedHashMap<String,NominatedPostVO>(0);
			String[] applicationStatusArr = IConstants.NOMINATED_POST_APPLICATION_STATUSES;
			if(applicationStatusArr != null && applicationStatusArr.length>0)
				for (int i = 0; i < applicationStatusArr.length; i++) 
					applicationsStatusDtlsMap.put(applicationStatusArr[i].trim(),  new NominatedPostVO(Long.valueOf(String.valueOf(i)),applicationStatusArr[i]) );
			
			//if(commonMethodsUtilService.isMapValid(levelwiseNominatedPostsMap)){
				List<Long> statusList = nominatedPostStatusDAO.getStatusIdsList();
				List<Object[]> totalAvailablePostsList = nominatedPostDAO.getTotalAvaiablePostDetails(levelId,startDate,endDate,statusList,stateId);
				Long totalPositionsCount=0L;
				Long totalCorpCount=0L;
				if(commonMethodsUtilService.isListOrSetValid(totalAvailablePostsList)){
					for (Object[] param : totalAvailablePostsList) {
						NominatedPostVO vo = applicationsStatusDtlsMap.get("TOTAL CORPORATIONS");
						if(vo != null){
							vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
							vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
							vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							totalPositionsCount = vo.getTotalPositions();
							totalCorpCount = vo.getTotalCorp();
						}
					}
				}
				
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
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[4].trim());
								if(vo != null){
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									
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
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[5].trim());
								if(vo != null){
									
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									
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
								NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[6].trim());
								if(vo != null){
									vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[3]));
									vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[4]));
									vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[6]));
									
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
				
				List<Object[]> levelWiseApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDtals(levelId,startDate,endDate,stateId);
				if(commonMethodsUtilService.isListOrSetValid(levelWiseApplicatinStatusDetailsList)){
					for (Object[] param : levelWiseApplicatinStatusDetailsList) {
						//Long postLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
						//NominatedPostVO vo1 = levelwiseNominatedPostsMap.get(postLevelId);
						//if(vo1 != null){
							NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[2].trim());
							if(vo != null){
								vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[1]));
								vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[2]));
								vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[3]));
								
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
							NominatedPostVO vo = applicationsStatusDtlsMap.get(applicationStatusArr[3].trim());
							if(vo != null){
								vo.setTotalPositions(commonMethodsUtilService.getLongValueForObject(param[1]));
								vo.setTotalDept(commonMethodsUtilService.getLongValueForObject(param[2]));
								vo.setTotalCorp(commonMethodsUtilService.getLongValueForObject(param[3]));
								
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
				NominatedPostVO vo2 = applicationsStatusDtlsMap.get(applicationStatusArr[1].trim());
				if(vo2 != null){
					if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
						if(levelId != null && levelId.longValue()>0L){
								NominatedPostVO totalVO = applicationsStatusDtlsMap.get(applicationStatusArr[0].trim());
								NominatedPostVO appliedVO = applicationsStatusDtlsMap.get(applicationStatusArr[2].trim());
								NominatedPostVO runningVO = applicationsStatusDtlsMap.get(applicationStatusArr[3].trim());
								if(appliedVO != null && runningVO != null && appliedVO.getTotalPositions().longValue()>0L){
									//NominatedPostVO vo2 = applicationsStatusDtlsMap.get(applicationStatusArr[1].trim());
									if(vo2 != null){
										vo2.setTotalPositions(totalVO.getTotalPositions().longValue()>0L?(totalVO.getTotalPositions()-(appliedVO.getTotalPositions() + runningVO.getTotalPositions())):0L);
										vo2.setTotalDept(totalVO.getTotalDept().longValue()>0L ? (totalVO.getTotalDept()-(appliedVO.getTotalDept() + runningVO.getTotalDept())):0L);
										vo2.setTotalCorp(totalVO.getTotalCorp().longValue()>0l? (totalVO.getTotalCorp()-(appliedVO.getTotalCorp() + runningVO.getTotalCorp())):0L);
										
										if(totalPositionsCount != null && totalPositionsCount.longValue()>0L){
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
					}
				}
			//}

				if(commonMethodsUtilService.isMapValid(applicationsStatusDtlsMap)){
					returnList = new ArrayList<NominatedPostVO>();
					returnList.addAll(applicationsStatusDtlsMap.values());
							
				}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostsStatusDetailsInAllLevels()", e);
		}
		return returnList;
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
			//0-statusId,1-status,2-boardLevelId,3-level,4-deptId,5-deptName,6-boardId,7-boardName,8-positionId,9-positionName
			List<Object[]> appCandList = nominatedPostApplicationDAO.getCandidateAppliedPostsByCadre(tdpCadreId, searchType,nominateCandId);
			if(appCandList!= null && appCandList.size() >0){
				for (Object[] obj : appCandList) {
					NomintedPostMemberVO Vo = new NomintedPostMemberVO();
					Vo.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(obj[10]));//postTypeId
					if(Vo.getNominatedPostId() == 1l){
						Vo.setApplStatusId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						Vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[1]));
						Vo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));//boardLevelId
						Vo.setLevel(commonMethodsUtilService.getStringValueForObject(obj[3]));
						Vo.setDeptId(commonMethodsUtilService.getLongValueForObject(obj[4]));
						Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[5]));
						Vo.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));
						Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[7]));
						Vo.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));
						Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[9]));
						subList.add(Vo);
					}
					else if(Vo.getNominatedPostId() == 2l){
						Vo.setApplStatusId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						Vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[1]));
						Vo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));//boardLevelId
						Vo.setLevel(commonMethodsUtilService.getStringValueForObject(obj[3]));
						Vo.setDeptId(commonMethodsUtilService.getLongValueForObject(obj[4]));
						Vo.setCadreName(commonMethodsUtilService.getStringValueForObject(obj[5]));
						Vo.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));
						Vo.setSubCaste(commonMethodsUtilService.getStringValueForObject(obj[7]));
						Vo.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));
						Vo.setVoterName(commonMethodsUtilService.getStringValueForObject(obj[9]));
						subList1.add(Vo);
					}
					
				}
			}
			returnVo.getSubList().addAll(subList);
			returnVo.getSubList1().addAll(subList1);
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getAppliedCandidatePostsByCadre", e);
		}
		return returnVo;
	}
	
	public List<NominatedPostVO> getNominatedPostPostionDetails(Long departmentId,Long boardId,Long positionId,Long boardLevelId,Long locationValue){
		
		List<NominatedPostVO> finalList = new ArrayList<NominatedPostVO>(0);
		
		try{
			/*Long departmentId = 1l;
			Long boardId = 36l;
			Long positionId = 1l;
			Long boardLevelId = 5l;
			Long locationValue = 835l;*/
			
			Map<Long,NominatedPostVO> finalMap = new HashMap<Long, NominatedPostVO>();
			
			NominatedPostVO VO = new NominatedPostVO();				
			VO.setId(positionId);		
			VO.setIdNameVoList(getCasteCategoryDetails());
							
			NominatedPostVO anyVO = new NominatedPostVO();
			anyVO.setId(null);
			anyVO.setIdNameVoList(getCasteCategoryDetails());
			
			finalMap.put(positionId, VO);
			finalMap.put(null, anyVO);
			
			List<Object[]> receivedObj = nominatedPostApplicationDAO.getAppliationsReceievedStatus(departmentId,boardId,
					null,boardLevelId,locationValue,null);
			
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
			
			List<Object[]> receivedAnyObj = nominatedPostApplicationDAO.getAppliationsReceievedStatus(departmentId,boardId,
					positionId,boardLevelId,locationValue,"Any");
			
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
			
			//Short Listed Candidates
			
			List<Object[]> shrtObj = nominatedPostApplicationDAO.getShortlistedCandidatesStatus(departmentId, boardId, null, boardLevelId, locationValue, null);
			
					if(shrtObj !=null && shrtObj.size()>0){
						for(Object[] obj : shrtObj){
							
							NominatedPostVO mainVo =null;
							if(obj[0] ==null){
								 mainVo =	finalMap.get(null);
							}else{
								 mainVo = finalMap.get((Long)obj[0]);
							}
							
							if(mainVo != null){
								mainVo.setName(obj[1] !=null ? obj[1].toString():"");
								mainVo.setShortListedCount(obj[2] !=null ? (Long)obj[2]:0l);						
							}						
						}
					} 
					
				List<Object[]> shrtAnyObj = nominatedPostApplicationDAO.getShortlistedCandidatesStatus(departmentId, boardId, null, boardLevelId, locationValue, "Any");
					
					if(shrtAnyObj !=null && shrtAnyObj.size()>0){
						for(Object[] obj : shrtAnyObj){
							
							NominatedPostVO mainVo =null;
							if(obj[0] ==null){
								 mainVo =	finalMap.get(null);
							}else{
								 mainVo = finalMap.get((Long)obj[0]);
							}
							
							if(mainVo != null){
								mainVo.setName(obj[1] !=null ? obj[1].toString():"");
								mainVo.setShortListedCount(obj[2] !=null ? (Long)obj[2]:0l);						
							}						
						}
					} 
			
			List<Object[]> casteObj = nominatedPostApplicationDAO.getCasteWiseApplications(departmentId, boardId, null, boardLevelId, locationValue, null);
			if(casteObj !=null && casteObj.size()>0){
				for(Object[] obj : casteObj){
					
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
			
			List<Object[]> casteAnyObj = nominatedPostApplicationDAO.getCasteWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, "Any");
			
			if(casteAnyObj !=null && casteAnyObj.size()>0){
				for(Object[] obj : casteAnyObj){
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
			
			List<Object[]> ageObj = nominatedPostApplicationDAO.getAgeRangeWiseApplications(departmentId, boardId, null, boardLevelId, locationValue, null);
			
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
			
			List<Object[]> ageAnyObj = nominatedPostApplicationDAO.getAgeRangeWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, "Any");
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
	            month = months[num];
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
	public List<NominatedPostVO> getBrdWisNominPstAppliedDepOrCorpDetails(Long candidateId){
		List<NominatedPostVO> returnVoList = new ArrayList<NominatedPostVO>();
		try {
			//0-statusId,1-status,2-boardLevelId,3-level,4-deptId,5-deptName,6-boardId,7-boardName,8-positionId,9-positionName
			List<Object[]> DepOCorpList = nominatedPostApplicationDAO.getBrdWisNominPstAppliedDepOrCorpDetails(candidateId);
			if(DepOCorpList != null && DepOCorpList.size() > 0){
				for (Object[] obj : DepOCorpList) {
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
					VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[4]));
					VO.setMobileNo(commonMethodsUtilService.getStringValueForObject(obj[5]));
					VO.setDeptBoardId(commonMethodsUtilService.getLongValueForObject(obj[6]));
					VO.setPincode(commonMethodsUtilService.getStringValueForObject(obj[7]));
					VO.setDeptBoardPostnId(commonMethodsUtilService.getLongValueForObject(obj[8]));
					VO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(obj[9]));
					returnVoList.add(VO);
				}
								
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getBrdWisNominPstAppliedDepOrCorpDetails", e);
		}
		return returnVoList;
	}
	
	public List<IdNameVO> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> locationValues,String statusType){
		
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
					List<Object[]> mandalObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(5l, mandalList,statusType);	
					deptMap = setDataToDeptBoardMap(mandalObj,deptMap);
				}
				if(townList !=null && townList.size()>0){
					List<Object[]> townObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(6l, townList,statusType);	
					deptMap = setDataToDeptBoardMap(townObj,deptMap);
				}
				if(divisonList !=null && divisonList.size()>0){
					List<Object[]> divObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(7l, divisonList,statusType);
					 deptMap = setDataToDeptBoardMap(divObj,deptMap);
				}
				
			}else{
				deptBoardObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(boardLevelId, locationValues,statusType);
				deptMap = setDataToDeptBoardMap(deptBoardObj,deptMap);
			}
			
			
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
			
			if(deptMap !=null && deptMap.size()>0){
				finalList = new ArrayList<IdNameVO>(deptMap.values());
			}
				
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getAllDeptsAndBoardsByLevel", e);
		}
		Collections.sort(finalList,sortByName);
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
						VO.setId((Long)obj[0]);
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
									if(!(idNameVO.getId().equals((Long)obj[2]))){
										IdNameVO inneVo = new IdNameVO();
										inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
										inneVo.setName(obj[3] !=null ? obj[3].toString():"");
										boardList.add(inneVo);
										break;
									}
								}
							}
						}									
				}				
			}
			
			
		}catch (Exception e) {
			LOG.error("Exceptionr riased at setDataToDeptBoardMap", e);
		}
		return deptMap;
	}
	
	
	public List<NominatedPostVO> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValues,List<Long> deptIds,
			List<Long> boardIds,String statusType){
		
		List<NominatedPostVO> finalList = new ArrayList<NominatedPostVO>(0);
		
		try{			
			
			//Setting default "Any Post" key and Name to the finalMap 
			Map<Long,NominatedPostVO> finalMap = new HashMap<Long, NominatedPostVO>();
			
			Object[] anyObj = {null,""};
			List<Object[]> anyTypeListObj = new ArrayList<Object[]>();
			anyTypeListObj.add(anyObj);
			
			finalMap = setDataToPostWiseDetailsMap(anyTypeListObj,finalMap);
			
			
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
					List<Object[]> mandalObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(5l,mandalList,deptIds,boardIds,statusType);
					finalMap = setDataToPostWiseDetailsMap(mandalObj,finalMap);
					
					
					
					//postionId,position,nomiatedPostStatusId,status,count
					List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(5l,mandalList,deptIds,boardIds,statusType,"post");
					
					if(deptsObj !=null && deptsObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
					}
					
					//Any Positions Type
					
					List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
					
					if(deptsAnyObj !=null && deptsAnyObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
					}
					
					
					
					
					
					//postionId,position,applicationStatusId,status,count
					List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"post");
					
					if(applicationSttusObj !=null && applicationSttusObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
					}
					
					List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(5l,mandalList,deptIds,boardIds,statusType,"anyPost");
					
					if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
					}
					
					
				}
				if(townList !=null && townList.size()>0){
					List<Object[]> townObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(6l,townList,deptIds,boardIds,statusType);
					finalMap = setDataToPostWiseDetailsMap(townObj,finalMap);
					
					//postionId,position,nomiatedPostStatusId,status,count
					List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(6l,townList,deptIds,boardIds,statusType,"post");
					
					if(deptsObj !=null && deptsObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
					}
					
					List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(6l,townList,deptIds,boardIds,statusType,"anyPost");
					
					if(deptsAnyObj !=null && deptsAnyObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
					}
					
					//postionId,position,applicationStatusId,status,count
					List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"post");
					
					if(applicationSttusObj !=null && applicationSttusObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
					}
					
					List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(6l,townList,deptIds,boardIds,statusType,"anyPost");
					
					if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
					}
					
				}
				if(divisonList !=null && divisonList.size()>0){
					List<Object[]> divObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(7l,divisonList,deptIds,boardIds,statusType);
					finalMap = setDataToPostWiseDetailsMap(divObj,finalMap);
					
					//postionId,position,nomiatedPostStatusId,status,count
					List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(7l,divisonList,deptIds,boardIds,statusType,"post");
					
					if(deptsObj !=null && deptsObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
					}
					
					List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
					
					if(deptsAnyObj !=null && deptsAnyObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
					}
					
					
					//postionId,position,applicationStatusId,status,count
					List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"post");
					
					if(applicationSttusObj !=null && applicationSttusObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
					}
					
					
					List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(7l,divisonList,deptIds,boardIds,statusType,"anyPost");
					
					if(applicationSttusObj !=null && applicationSttusObj.size()>0){
						finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
					}
					
				}
				
			}else{
				List<Object[]> postObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(boardLevelId,levelValues,deptIds,boardIds,statusType);
				finalMap = setDataToPostWiseDetailsMap(postObj,finalMap);
				
				//postionId,position,nomiatedPostStatusId,status,count
				List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
				
				if(deptsObj !=null && deptsObj.size()>0){
					finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
				}
				
				
				List<Object[]> deptsAnyObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
				
				if(deptsAnyObj !=null && deptsAnyObj.size()>0){
					finalMap = setDataToFinalMap(finalMap,deptsAnyObj,"nominatedStatus");
				}
				
				//postionId,position,applicationStatusId,status,count
				List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"post");
				
				if(applicationSttusObj !=null && applicationSttusObj.size()>0){
					finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
				}
				
				List<Object[]> applicationSttusAnyObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds,statusType,"anyPost");
				
				if(applicationSttusAnyObj !=null && applicationSttusAnyObj.size()>0){
					finalMap = setDataToFinalMap(finalMap,applicationSttusAnyObj,"applicationStatus");
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
					}else if(type !=null && type.trim().equalsIgnoreCase("applicationStatus")){
						statusList = mainVo.getDistList();	
					}
				}
				
								
				if(statusList !=null && statusList.size()>0){						
					for (IdNameVO idNameVO : statusList) {							
						if(idNameVO.getId() == (obj[2] !=null ? (Long)obj[2]:0l)){
							idNameVO.setCount(idNameVO.getCount() + (obj[4] !=null ? (Long)obj[4]:0l));
						}							
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
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Long nominatedPostMemberId = nominatedPostMemberDAO.getNominatedPostMemberId(levelId, levelValue, deptId, boardId, positionId);
					if(nominatedPostMemberId.longValue() > 0l){
						NominatedPostComment nominatedPostComment = new NominatedPostComment();
						
						nominatedPostComment.setNominatedPostApplicationId(applicationId);
						nominatedPostComment.setRemarks(comment);
						nominatedPostComment.setInsertedBy(userId);
						nominatedPostComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostComment = nominatedPostCommentDAO.save(nominatedPostComment);
						
						NominatedPostFinal nominatedPostFinal = new NominatedPostFinal();
						
						nominatedPostFinal.setNominatedPostMemberId(nominatedPostMemberId);
						nominatedPostFinal.setNominationPostCandidateId(candidateId);
						nominatedPostFinal.setApplicationStatusId(statusId);
						nominatedPostFinal.setInsertedBy(userId);
						nominatedPostFinal.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostFinal.setUpdatedBy(userId);
						nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						nominatedPostFinal.setIsDeleted("N");
						
						nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
					}
				}
			});
			status = "success";
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
			LOG.error("Exception raised at savingAnyPostCandidatesToPosition", e);
		}
		return status;
	}
	
	public Map<Long,NominatedPostVO> setDataToPostWiseDetailsMap(List<Object[]> postObj,Map<Long,NominatedPostVO> finalMap){
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
					VO.setDistList(getAllApplicationStatusList());
					
					finalMap.put(VO.getId(), VO);
				}
			}
		}catch (Exception e) {
			LOG.error("Exceptionr riased at setDataToDeptBoardMap", e);
		}
		return finalMap;
	}
	/**
	 * @Author  Santosh
	 * @Version NominatedPostProfileService.java  July 22, 2016 06:00:00 PM 
	 * @return List<IdNameVO>
	 * description  { This service is used to get final Review Candidate count  }
	 */
	public List<IdNameVO> getFinalReviewCandidateCountLocationWise(Long LocationLevelId,List<Long> lctnLevelValueList,Long departmentId,Long boardId){
		List<IdNameVO> fnlCnddtCuntLst = new ArrayList<IdNameVO>(0);
		  try{
			  
			  Map<Long,IdNameVO> finalMap = new HashMap<Long, IdNameVO>();
			  
			  List<Long> mandalList = new ArrayList<Long>();
			  List<Long> townList = new ArrayList<Long>();
			  List<Long> divisonList = new ArrayList<Long>();			  
			  
			  
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
			        	 List<Object[]> mandalObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(5l, mandalList, departmentId, boardId);
					     finalMap = setDataToMapForFinalReview(mandalObjList,finalMap);
				    }
			        if(townList != null && townList.size() > 0){
			        	List<Object[]> townObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(6l, townList, departmentId, boardId);
					      finalMap =  setDataToMapForFinalReview(townObjList,finalMap);
					        
			        }
			        if(divisonList != null && divisonList.size()>0){
			        	 List<Object[]> divObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(7l, divisonList, departmentId, boardId);
					        finalMap = setDataToMapForFinalReview(divObjList,finalMap);
			        }
			  }else{
				  List<Object[]> rtrnObjList = nominatedPostApplicationDAO.getFinalReviewCandidateCountLocationWise(LocationLevelId, lctnLevelValueList, departmentId, boardId);
				  finalMap = setDataToMapForFinalReview(rtrnObjList,finalMap);
				  
			  }
			  
			  if(finalMap !=null && finalMap.size() > 0){
				  fnlCnddtCuntLst = new ArrayList<IdNameVO>(finalMap.values());
			  }
			
			  
		 }catch(Exception e) {
			 LOG.error("Exceptionr riased at getFinalReviewCandidateCountLocationWise in NominatedPostProfileService class", e); 
		 }
		  return fnlCnddtCuntLst;
	}

	public Map<Long,IdNameVO> setDataToMapForFinalReview(List<Object[]> rtrnObjList,Map<Long,IdNameVO> finalMap){
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
		   		   vo.setCount(vo.getCount() +( obj[4] != null ? (Long)obj[4]:0l));
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
			    			   vo.setImageURL(obj[4]!=null?"images/cadre_images/"+obj[4].toString():null);
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
                        
                        Long voterId = voterDAO.getVoterIdByIdCardNo(notcadreRegistrationVO.getVoterId().toString());
                        NPC.setVoterId(voterId != null ? voterId : null);
                        NPC.setCandidateName(notcadreRegistrationVO.getName()!=null?notcadreRegistrationVO.getName():null);
	                    NPC.setAge(notcadreRegistrationVO.getAge()!=null?notcadreRegistrationVO.getAge():null);
	                    NPC.setGender(notcadreRegistrationVO.getGender()!=null?notcadreRegistrationVO.getGender():null);
	                    NPC.setHouseno(notcadreRegistrationVO.getHouseno()!=null?notcadreRegistrationVO.getHouseno():null);
	                    NPC.setMobileNo(notcadreRegistrationVO.getMobileno()!=null?notcadreRegistrationVO.getMobileno():null);
	                    NPC.setRelativename(notcadreRegistrationVO.getRelativename()!=null?notcadreRegistrationVO.getRelativename():null);
	                    NPC.setRelativetype(notcadreRegistrationVO.getRelativetype()!=null?notcadreRegistrationVO.getRelativetype():null);
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
			 String notCadreImagesFoldr = ActivityService.createFolder(staticPath+"/"+IConstants.NOT_CADRE_IMAGES);
			 
			 String foldrSts = ActivityService.createFolder(notCadreImagesFoldr);
			 if(!foldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/"+IConstants.NOT_CADRE_IMAGES;
			 
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
			    			   finalList.add(vo);
				    	}
			    	  }
			        
			   	} catch (Exception e) {
			LOG.error("Exception raised at notCadresearch() method of NominatedPostProfileService", e);
		}
		 return finalList;
	 }
	
	public ResultStatus updateNominatedPostStatusDetails(final Long deptId,final Long boardId,final Long positionId,final Long levelId,final Long searchLevelId,final Long searchLevelValue,final Long statusId){
		ResultStatus status = new ResultStatus();
		try {
			String statusStr = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					List<NominatedPost> nominatedPostsList = nominatedPostDAO.getnominatedPostDetailsBySearchCriteria(deptId,boardId,positionId,levelId,searchLevelId,searchLevelValue);
					if(commonMethodsUtilService.isListOrSetValid(nominatedPostsList)){
						NominatedPost nominatedPostObj = nominatedPostsList.get(0);
						nominatedPostObj.setNominatedPostStatusId(statusId);
						nominatedPostDAO.save(nominatedPostObj);
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
	
	public NominatedPostReferVO getAllReferredMemberDetailsForPosition(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId){
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
			
			List<Object[]> list = nominatedPostFinalDAO.getAllReferredMemberDetailsForPosition(levelId, levelValue, departmentId, boardId, positionId);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"nominatedPostFinalId","nominatedPostCandidateId","tdpCadreId","voterId","voterName","voterMoblie","voterGender","cadreName",
						"cadreMobile","age","cadreGender","caste","subCaste","casteName","applStatusId","status"};
			subList = (List<NominatedPostReferVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostReferVO");
			}
			
			if(commonMethodsUtilService.isListOrSetValid(subList)){
				for (NominatedPostReferVO vo : subList) {
					Long cadreId = vo.getTdpCadreId();
					Long nominatedPostCandidateId = vo.getNominatedPostCandidateId();
					if(cadreId != null && cadreId.longValue() > 0l){
						tdpCadreIds.add(cadreId);
					}
					if(nominatedPostCandidateId != null && nominatedPostCandidateId.longValue() > 0l){
						candidateIds.add(nominatedPostCandidateId);
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getAnyAppliedDepartmentsCountForCandidateList(candidateIds);
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
				List<Object[]> totalDepartments = nominatedPostFinalDAO.getShortlistedDepartmentsCountForCandidateList(candidateIds);
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
			
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
				List<Object[]> referList = nominatedPostReferDetailsDAO.getReferedCountForCandidateList(candidateIds);
				 if(commonMethodsUtilService.isListOrSetValid(referList)){
					 for (Object[] obj : referList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						referenceMap.put(id, count);
					}
				 }
				 if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = referenceMap.get(vo.getNominatedPostCandidateId());
						 vo.setReferenceCount(count);
					 }
				 }
			}
			
			if(commonMethodsUtilService.isListOrSetValid(candidateIds)){
				List<Object[]> commentList = nominatedPostCommentDAO.getCommentsCountForCandidateIds(candidateIds);
				if(commonMethodsUtilService.isListOrSetValid(commentList)){
					for (Object[] obj : commentList) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						commentMap.put(id, count);
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(subList)){
					 for (NominatedPostReferVO vo : subList) {
						 Long count = commentMap.get(vo.getNominatedPostCandidateId());
						 vo.setCommentCount(count);
					 }
				 }
			}
			
			returnvo.setSubList(subList);
		} catch (Exception e) {
			LOG.error("Exception raised at getAllReferredMemberDetailsForPosition() method of NominatedPostProfileService", e);
		}
		return returnvo;
	}
	
	public IdNameVO getOverAllCommentsForCandidate(Long candidateId){
		IdNameVO returnvo = new IdNameVO();
		try {
			List<IdNameVO> subList = new ArrayList<IdNameVO>();
			List<IdNameVO> subList1 = new ArrayList<IdNameVO>();
			
			List<Object[]> finalList = nominatedPostCommentDAO.getFinalyzedCommentsForCandidate(candidateId);
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				for (Object[] obj : finalList) {
					IdNameVO vo = new IdNameVO();
					vo.setStatus(obj[0] != null ? obj[0].toString():"");
					vo.setDateStr(obj[1] != null ? obj[1].toString():"");
					vo.setName(obj[2] != null ? obj[2].toString():"");
					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
					subList.add(vo);
				}
			}
			List<Object[]> shortList = nominatedPostCommentDAO.getShortListingCommentsForCandidate(candidateId);
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
	
	
}
