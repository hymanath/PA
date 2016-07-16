package com.itgrids.partyanalyst.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.dao.IDepartmentsDAO;
import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.model.NominatedPostFinal;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.NominatedPostApplication;
import com.itgrids.partyanalyst.model.NominationPostCandidate;
import com.itgrids.partyanalyst.service.INominatedPostProfileService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;


public class NominatedPostProfileService implements INominatedPostProfileService{

	private final static Logger LOG =  Logger.getLogger(NominatedPostProfileService.class);
	private IBoardLevelDAO boardLevelDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService ;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IDepartmentsDAO departmentsDAO;
	private IDepartmentBoardDAO departmentBoardDAO;
	private IDepartmentBoardPositionDAO departmentBoardPositionDAO;
	private INominatedPostFinalDAO nominatedPostFinalDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private IApplicationStatusDAO applicationStatusDAO;
	private INominationPostCandidateDAO nominationPostCandidateDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private TransactionTemplate transactionTemplate;
	private ITdpCadreDAO tdpCadreDAO;
	
	

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
			
			//0.nominationPostCandidateId,1.tdpCadreId,2.voterId,3.candidateName,4.mobileNo,5.cadreFirstname,6.cadreMobileNo,7.age,
						//8.caste,9.subCaste,10.casteName,11.applicationStatusId,12.status,13.nominatedPostId
			List<Object[]> list = nominatedPostFinalDAO.getNominatedPostMemberDetails(levelId, levelValue, departmentId, boardId, positionId, type);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				String[] setterPropertiesList = {"nominatedPostCandidateId","tdpCadreId","voterId","voterName","voterMoblie","cadreName","cadreMobile","age",
							"caste","subCaste","casteName","applStatusId","status","nominatedPostId"};
				subList = (List<NomintedPostMemberVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.NomintedPostMemberVO");
			}
			if(commonMethodsUtilService.isListOrSetValid(subList)){
				for (NomintedPostMemberVO vo : subList) {
					Long cadreId = vo.getTdpCadreId();
					if(cadreId != null && cadreId.longValue() > 0l){
						tdpCadreIds.add(cadreId);
					}
				}
			}
			
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
					 NomintedPostMemberVO vo = getMatchedVO(subList, cadreId);
					 if(vo != null){
						 vo.setPartyPosition(partyPositionStr);
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
	
	public String updateApplicationStatusDetails(final Long userId,final Long nominatedPostId,final Long nominatedPostCandidateId,final Long statusId){
		String status = null;
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					Long nominatedPostFinalId = nominatedPostFinalDAO.getNominatedPostFinalDetails(nominatedPostId, nominatedPostCandidateId);
					if(nominatedPostFinalId != null && nominatedPostFinalId.longValue() > 0l){
						NominatedPostFinal nominatedPostFinal = nominatedPostFinalDAO.get(nominatedPostFinalId);
						
						nominatedPostFinal.setApplicationStatusId(statusId);
						nominatedPostFinal.setUpdatedBy(userId);
						nominatedPostFinal.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						nominatedPostFinal = nominatedPostFinalDAO.save(nominatedPostFinal);
					}
					
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
	
	public ResultStatus savingNominatedPostProfileApplication(final NominatedPostVO nominatedPostVO,final Long loggerUserId){
		ResultStatus status = new ResultStatus ();
		
		try{
			
			status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					ResultStatus rs = new ResultStatus ();
						NominationPostCandidate nominationPostCandidate = new NominationPostCandidate();
						NominatedPostApplication nominatedPostApplication = null;
						
						
					if(nominatedPostVO.getName() != null){
							nominationPostCandidate.setCandidateName(nominatedPostVO.getName());
					}
						
					if(nominatedPostVO.getId() != null && nominatedPostVO.getId().longValue() > 0l){
						Long voterId = tdpCadreDAO.getVoterIdByTdpCadreId(nominatedPostVO.getId().longValue());
						nominationPostCandidate.setVoterId(voterId != null ? voterId : null);
						nominationPostCandidate.setTdpCadreId(nominatedPostVO.getId());
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
							
							nominatedPostApplicationDAO.save(nominatedPostApplication);
						
						}
				}
		
				rs.setMessage("SUCCESS");
				rs.setResultCode(0);
				return rs;
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
	
}
