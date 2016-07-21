package com.itgrids.partyanalyst.service.impl;


import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
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
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ApplicationDocument;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.model.NominatedPostComment;
import com.itgrids.partyanalyst.model.NominatedPostFinal;
import com.itgrids.partyanalyst.model.NominationPostCandidate;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
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
	public List<IdNameVO> getDepartments(){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = departmentsDAO.getDepartments();
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
	
	public String updateApplicationStatusDetails(final Long userId,final Long nominatePostApplicationId,final Long statusId,final String comment){
		String status = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					//Long nominatedPostFinalId = nominatedPostFinalDAO.getNominatedPostFinalDetails(nominatedPostId, nominatedPostCandidateId);
					//if(nominatedPostFinalId != null && nominatedPostFinalId.longValue() > 0l){
						NominatedPostApplication nominatedPostApplication = nominatedPostApplicationDAO.get(nominatePostApplicationId);
						
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
					
					//ResultStatus rs = new ResultStatus ();
						NominationPostCandidate nominationPostCandidate = new NominationPostCandidate();
						NominatedPostApplication nominatedPostApplication = null;
						
						
					if(nominatedPostVO.getName() != null){
							nominationPostCandidate.setCandidateName(nominatedPostVO.getName());
					}
						
					if(nominatedPostVO.getId() != null && nominatedPostVO.getId().longValue() > 0l){
						Long voterId = tdpCadreDAO.getVoterIdByTdpCadreId(nominatedPostVO.getId().longValue());
						nominationPostCandidate.setVoterId(voterId != null ? voterId : null);
						nominationPostCandidate.setTdpCadreId(nominatedPostVO.getId());
					}else{
						Long voterId = voterDAO.getVoterIdByIdCardNo(nominatedPostVO.getVoterCardNo());
						nominationPostCandidate.setVoterId(voterId != null ? voterId : null);
					}
						
						
					if(nominatedPostVO.getMobileNo() != null){
							nominationPostCandidate.setMobileNo(nominatedPostVO.getMobileNo());
					}
						
							nominationPostCandidate.setInsertedBy(loggerUserId);
							nominationPostCandidate.setUpdatedBy(loggerUserId);
							nominationPostCandidate.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							nominationPostCandidate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							nominationPostCandidate.setIsDeleted("N");
							nominationPostCandidate = nominationPostCandidateDAO.save(nominationPostCandidate);
					
					if(nominatedPostVO.getNominatdList() != null && !nominatedPostVO.getNominatdList().isEmpty() ){
						nominatedPostApplication = new NominatedPostApplication();
						for(NominatedPostVO Vo : nominatedPostVO.getNominatdList()){
							
							nominatedPostApplication.setNominationPostCandidateId(nominationPostCandidate.getNominationPostCandidateId() != null ? nominationPostCandidate.getNominationPostCandidateId() : null);
							
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
								nominatedPostApplication.setLocationValue(Vo.getMandalId() != null ? Vo.getMandalId() : null) ;
							}else if(Vo.getBoardLevelId() != null && Vo.getBoardLevelId().longValue() == 7){
								nominatedPostApplication.setBoardLevelId(Vo.getBoardLevelId() != null ? Vo.getBoardLevelId() : null) ;
								nominatedPostApplication.setLocationValue(Vo.getPanchayatId() != null ? Vo.getPanchayatId() : null) ;
							}
							
							nominatedPostApplication.setDepartmentId(Vo.getDeptId() != null ? Vo.getDeptId() : null) ;
							nominatedPostApplication.setBoardId(Vo.getDeptBoardId() != null ? Vo.getDeptBoardId() : null) ;
							nominatedPostApplication.setPositionId(Vo.getDeptBoardPostnId() != null ? Vo.getDeptBoardPostnId() : null) ;
							nominatedPostApplication.setInsertedBy(loggerUserId);
							nominatedPostApplication.setUpdatedBy(loggerUserId);
							nominatedPostApplication.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							nominatedPostApplication.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							nominatedPostApplication.setIsDeleted("N");
							nominatedPostApplication.setApplicationStatusId(1l);
							nominatedPostApplicationDAO.save(nominatedPostApplication);
						
						}
				}
					String folderName = folderCreation();
					ApplicationDocument applicationDocument = null;
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					 int year = calendar.get(Calendar.YEAR);
					 int month = calendar.get(Calendar.MONTH);
					 int day = calendar.get(Calendar.DAY_OF_MONTH);
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
								status.setResultCode(ResultCodeMapper.FAILURE);
								LOG.error(" Exception Raise in copying file");
								throw new ArithmeticException();
							}
							
							applicationDocument = new ApplicationDocument();
							applicationDocument.setFilePath(pathBuilder.toString());
							applicationDocument.setNominationPostCandidateId(nominationPostCandidate.getNominationPostCandidateId() != null ? nominationPostCandidate.getNominationPostCandidateId() : null);
							applicationDocument.setIsDeleted("N");
							applicationDocument = applicationDocumentDAO.save(applicationDocument);
							
					 }
					//status.setMessage("SUCCESS");
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
					UserAddress UA = new UserAddress();
					UA.setHouseNo(nominatedPostVO.getHno() != null?nominatedPostVO.getHno():null);
					UA.setAddressLane1(nominatedPostVO.getAddress1() != null?nominatedPostVO.getAddress1():null);
					UA.setAddressLane2(nominatedPostVO.getAddress2() != null?nominatedPostVO.getAddress2():null);
					UA.setPinCode(nominatedPostVO.getPincode() != null?nominatedPostVO.getPincode():null);
					UA.setState(nominatedPostVO.getStateId() != null?stateDAO.get(nominatedPostVO.getStateId()):null);
					UA.setDistrict(nominatedPostVO.getDistrictId() != null?districtDAO.get(nominatedPostVO.getDistrictId()):null);
					UA.setConstituency(nominatedPostVO.getConstituencyId() != null?constituencyDAO.get(nominatedPostVO.getConstituencyId()):null);
					UA.setPanchayatId(nominatedPostVO.getPanchayatId() != null?nominatedPostVO.getPanchayatId():null);
					UA.setTehsil(nominatedPostVO.getMandalId() != null?tehsilDAO.get(nominatedPostVO.getMandalId()):null);
					UserAddress presentAddress =  userAddressDAO.save(UA);
					
					TdpCadre tc = tdpCadreDAO.get(nominatedPostVO.getId());//tdpCadreId
					if(tc != null){
						tc.setPresentAddress(presentAddress);
						tc.setMobileNo(nominatedPostVO.getMobileNo());
						tdpCadreDAO.save(tc);
					}
				}
			});
			status = "success";
		} catch (Exception e) {
			status = "failure";
			Log.error("Exception Occured in savechangeAddressForNominatedPost method in NominatedPostProfileService ",e);
		}
		return status;
	}
	public List<NominatedPostVO> getApplicantDetailsForMember(Long tdpCadreId){
		List<NominatedPostVO> appMembersList = new ArrayList<NominatedPostVO>();
		NominatedPostVO vo = null;
		 try {			
				 
				List<Object[]> memberDetails =  tdpCadreDAO.getApplicationMemberDetails(tdpCadreId);
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
						List<Object[]> distList = districtDAO.getDistrictIdAndNameByState(vo.getStateId());
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
						List<Object[]> manList = tehsilDAO.getMandalsForConstituencyId(vo.getConstituencyId());
						if(manList != null && manList.size() > 0){
							for (Object[] objects : manList) {
								IdNameVO voMan = new IdNameVO();
								voMan.setId((Long)objects[0]);
								voMan.setName(objects[1].toString());
								vo.getMandalsList().add(voMan);
							}
						}
					}
					
					vo.setPanchayatId(commonMethodsUtilService.getLongValueForObject(obj[8]));
					//Panchayats
					if(vo.getPanchayatId()!= null && vo.getPanchayatId() > 0){
						List<Object[]> panList = panchayatDAO.getPanchayatsByTehsilId(vo.getMandalId());
						if(panList != null && panList.size() > 0){
							for (Object[] objects : panList) {
								IdNameVO voPan = new IdNameVO();
								voPan.setId((Long)objects[0]);
								voPan.setName(objects[1].toString());
								vo.getPanList().add(voPan);
							}
						}
					}
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
	
	public List<NominatedPostVO> getNominatedPostsStatusDetailsInAllLevels(Long levelId,String startDateStr, String endDateStr){
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
				List<Object[]> totalAvailablePostsList = nominatedPostDAO.getTotalAvaiablePostDetails(levelId,startDate,endDate,statusList);
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
				
				List<Object[]> levelWiseAvailablePostsList = nominatedPostDAO.getAvaiablePostDetails(levelId,startDate,endDate,statusList);
			
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
				
				List<Object[]> levelWiseApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsAppliedApplciationsDtals(levelId,startDate,endDate);
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
				
				List<Object[]> levelWiseRunningApplicatinStatusDetailsList =  nominatedPostApplicationDAO.getNominatedPostsRunningAppliedApplicationsDtals(levelId,startDate,endDate);
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
										vo2.setTotalPositions((totalVO.getTotalPositions()-(appliedVO.getTotalPositions() + runningVO.getTotalPositions())));
										vo2.setTotalDept((totalVO.getTotalDept()-(appliedVO.getTotalDept() + runningVO.getTotalDept())));
										vo2.setTotalCorp((totalVO.getTotalCorp()-(appliedVO.getTotalCorp() + runningVO.getTotalCorp())));
										
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
						}else{
							;
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
	public NomintedPostMemberVO getCandidateAppliedPostsByCadre(Long tdpCadreId){
		NomintedPostMemberVO returnVo = new NomintedPostMemberVO();
		List<NomintedPostMemberVO> subList = new ArrayList<NomintedPostMemberVO>();
		List<NomintedPostMemberVO> subList1 = new ArrayList<NomintedPostMemberVO>();
		try {
			//0-statusId,1-status,2-boardLevelId,3-level,4-deptId,5-deptName,6-boardId,7-boardName,8-positionId,9-positionName
			List<Object[]> appCandList = nominatedPostApplicationDAO.getCandidateAppliedPostsByCadre(tdpCadreId);
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
			
			/*List<Object[]> receivedAnyObj = nominatedPostApplicationDAO.getAppliationsReceievedStatus(departmentId,boardId,
					positionId,boardLevelId,locationValue,"Any");
			
			if(receivedAnyObj !=null && receivedAnyObj.size()>0){
				for(Object[] obj : receivedAnyObj){
					
				}
			}*/
			
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
			
			/*List<Object[]> casteAnyObj = nominatedPostApplicationDAO.getCasteWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, "Any");
			
			if(casteAnyObj !=null && casteAnyObj.size()>0){
				for(Object[] obj : casteAnyObj){
					
				}
			}*/
			
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
			
			/*List<Object[]> ageAnyObj = nominatedPostApplicationDAO.getAgeRangeWiseApplications(departmentId, boardId, positionId, boardLevelId, locationValue, "Any");
			if(ageAnyObj !=null && ageAnyObj.size()>0){
				for(Object[] obj : ageAnyObj){
					
				}
			}*/
			
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
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
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
	
	public List<IdNameVO> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> locationValue){
		
		List<IdNameVO>  finalList = new ArrayList<IdNameVO>(0);
		try{
			
			
			Map<Long,IdNameVO> deptMap = new HashMap<Long, IdNameVO>();
			List<Object[]> deptBoardObj = nominatedPostMemberDAO.getAllDeptsAndBoardsByLevel(boardLevelId, locationValue);
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
						IdNameVO inneVo = new IdNameVO();
						inneVo.setId(obj[2] !=null ? (Long)obj[2]:0l);
						inneVo.setName(obj[3] !=null ? obj[3].toString():"");			
					
						boardList.add(inneVo);								
				}
				
				if(deptMap !=null && deptMap.size()>0){
					finalList = new ArrayList<IdNameVO>(deptMap.values());
				}
				
				//System.out.println(finalList);
				
			}
		}catch (Exception e) {
			LOG.error("Exceptionr riased at getAllDeptsAndBoardsByLevel", e);
		}
		return finalList;
		
	}
	
	public List<NominatedPostVO> getDepartmentWiseBoardAndPositionDetails(Long boardLevelId,List<Long> levelValues,List<Long> deptIds,List<Long> boardIds){
		
		List<NominatedPostVO> finalList = new ArrayList<NominatedPostVO>(0);
		
		try{			
			
			//
			Map<Long,NominatedPostVO> finalMap = new HashMap<Long, NominatedPostVO>();
			List<Object[]> postObj = nominatedPostDAO.getNominatedPostsByBoardsAndDepts(boardLevelId,levelValues,deptIds,boardIds);
			
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
			
			//postionId,position,nomiatedPostStatusId,status,count
			List<Object[]> deptsObj  = nominatedPostDAO.getDepartmentWiseBoardAndPositionDetails(boardLevelId,levelValues,deptIds,boardIds);
			
			if(deptsObj !=null && deptsObj.size()>0){
				finalMap = setDataToFinalMap(finalMap,deptsObj,"nominatedStatus");
			}
			//postionId,position,applicationStatusId,status,count
			List<Object[]> applicationSttusObj = nominatedPostApplicationDAO.getPositionDetaislOfEveryApplicationStatus(boardLevelId,levelValues,deptIds,boardIds);
			
			if(applicationSttusObj !=null && applicationSttusObj.size()>0){
				finalMap = setDataToFinalMap(finalMap,applicationSttusObj,"applicationStatus");
			}
			
			if(finalMap !=null &&  finalMap.size()>0){
				
				finalList = new ArrayList<NominatedPostVO>(finalMap.values());
				System.out.println(finalList);
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
							idNameVO.setCount(obj[4] !=null ? (Long)obj[4]:0l);
						}							
					}						
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
}
