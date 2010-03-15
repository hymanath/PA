/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemAndProblemSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.dto.HamletProblemVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;
import com.itgrids.partyanalyst.model.ProblemClassification;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.ProblemSource;
import com.itgrids.partyanalyst.model.ProblemSourceScope;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemManagementService implements IProblemManagementService {

	private IProblemLocationDAO problemLocationDAO = null;
	private static final Logger log = Logger.getLogger("ProblemManagementService.class");
	private IRegistrationDAO registrationDAO = null;
	private IHamletDAO hamletDAO = null;
	private ITownshipDAO townshipDAO = null;
	private ProblemExternalSourceDAO problemExternalSourceDAO = null;
	private TransactionTemplate transactionTemplate = null;
	private IProblemSourceScopeDAO problemSourceScopeDAO = null;
	private IProblemAndProblemSourceDAO problemAndProblemSourceDAO = null;
	private IProblemClassificationDAO problemClassificationDAO = null;
	private IProblemDAO problemDAO = null;
	private IProblemHistoryDAO problemHistoryDAO = null;
	private IProblemStatusDAO problemStatusDAO = null;
	private IProblemSourceDAO problemSourceDAO = null;
	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO = null;
	private IAssignedProblemProgressDAO assignedProblemProgressDAO = null;
	private ProblemBeanVO problemBeanVO = null;
	private List<ProblemBeanVO> problemBeanVOs = null;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	
	
	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}

	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}

	public IProblemSourceScopeConcernedDepartmentDAO getProblemSourceScopeConcernedDepartmentDAO() {
		return problemSourceScopeConcernedDepartmentDAO;
	}

	public void setProblemSourceScopeConcernedDepartmentDAO(
			IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO) {
		this.problemSourceScopeConcernedDepartmentDAO = problemSourceScopeConcernedDepartmentDAO;
	}

	public IProblemSourceDAO getProblemSourceDAO() {
		return problemSourceDAO;
	}

	public void setProblemSourceDAO(IProblemSourceDAO problemSourceDAO) {
		this.problemSourceDAO = problemSourceDAO;
	}

	public IProblemStatusDAO getProblemStatusDAO() {
		return problemStatusDAO;
	}

	public void setProblemStatusDAO(IProblemStatusDAO problemStatusDAO) {
		this.problemStatusDAO = problemStatusDAO;
	}
	
	public IProblemAndProblemSourceDAO getProblemAndProblemSourceDAO() {
		return problemAndProblemSourceDAO;
	}

	public void setProblemAndProblemSourceDAO(
			IProblemAndProblemSourceDAO problemAndProblemSourceDAO) {
		this.problemAndProblemSourceDAO = problemAndProblemSourceDAO;
	}
	
	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}

	public IProblemClassificationDAO getProblemClassificationDAO() {
		return problemClassificationDAO;
	}

	public void setProblemClassificationDAO(
			IProblemClassificationDAO problemClassificationDAO) {
		this.problemClassificationDAO = problemClassificationDAO;
	}

	public IProblemSourceScopeDAO getProblemSourceScopeDAO() {
		return problemSourceScopeDAO;
	}

	public void setProblemSourceScopeDAO(
			IProblemSourceScopeDAO problemSourceScopeDAO) {
		this.problemSourceScopeDAO = problemSourceScopeDAO;
	}

	public IProblemLocationDAO getProblemLocationDAO() {
		return problemLocationDAO;
	}
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}
	
	public ProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			ProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}	
	
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}

	/**
	 * To Get The List Of All Different Sources Of Problems From DB Into SelectOptionVO
	 * @return
	 */
	public List<SelectOptionVO> getAllTypesOfProblemSources(){
		List<SelectOptionVO> sources = new ArrayList<SelectOptionVO>();
		List<ProblemSource> problemSources = problemSourceDAO.getAll();
		for(ProblemSource problemSource:problemSources)
			sources.add(new SelectOptionVO(problemSource.getProblemSourceId(), problemSource.getProblemSource()));
		return sources;
	}
	
	/**
	 * Used To Get The Problems Of a Hamlet In a Particular Year
	 */
	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,String year) {
		
		ProblemManagementDataVO problemManagementDataVO = new ProblemManagementDataVO();
		List<HamletProblemVO> hamletProblemsVO = null;
		ResultStatus resultStatus = new ResultStatus();
		
		if(log.isDebugEnabled()){
			log.debug("Entered Into getProblemsForAHamlet Method.....");
			log.debug("Hamlet Id:: " + hamletId);
			log.debug("Year" + year);
		}
		try{
			List<ProblemLocation> problemLocations = problemLocationDAO.findByHamletIdandYear(hamletId, year);
			if(problemLocations != null)
				hamletProblemsVO = getHamletProblems(problemLocations);
			problemManagementDataVO.setHamletProblems(hamletProblemsVO);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			problemManagementDataVO.setResultStatus(resultStatus);
		}catch(Exception ex){
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			problemManagementDataVO.setResultStatus(resultStatus);
			ex.printStackTrace();
		}
	return problemManagementDataVO;
	}
	
	/**
	 * Used By getProblemsForAHamlet to Get The Problems For ProblemLocation 
	 * @param problemLocations
	 * @return
	 * @throws Exception
	 */
	public List<HamletProblemVO> getHamletProblems(List<ProblemLocation> problemLocations) throws Exception{
		
		List<HamletProblemVO> hamletProblemsVO = null;
		if(problemLocations != null){
			Problem problem = null;
			ProblemAndProblemSource problemAndProblemSource = null;
			ProblemExternalSource problemExternalSource = null;
			hamletProblemsVO = new ArrayList<HamletProblemVO>();
			
			for(ProblemLocation problemLoctn:problemLocations){
				problem = problemLoctn.getProblemAndProblemSource().getProblem();
				problemAndProblemSource = problemLoctn.getProblemAndProblemSource();
				problemExternalSource = problemLoctn.getProblemAndProblemSource().getProblemExternalSource();
				
				HamletProblemVO hamletProblemVO = new HamletProblemVO();
				hamletProblemVO.setProblemDesc(problem.getDescription());
				hamletProblemVO.setIdentifiedDate(problem.getIdentifiedOn().toString());
				hamletProblemVO.setProblemId(problem.getProblemId());
				hamletProblemVO.setYear(problem.getYear());
				hamletProblemVO.setProblemStatus("--");
				
				if(problemAndProblemSource.getUser() != null)
					hamletProblemVO.setProblemSource(problemAndProblemSource.getUser().getFirstName());
				if(problemExternalSource != null)
					hamletProblemVO.setProblemSource(problemExternalSource.getName());
				
				hamletProblemsVO.add(hamletProblemVO);		
			}
		}
		return hamletProblemsVO;
	}
	
	/**
	 * Used To Save The New Problem Data And Return The Data Saved
	 */
	public ProblemBeanVO saveNewProblemData(ProblemBeanVO problemBeanVOToSave){
		this.problemBeanVO = problemBeanVOToSave;
		if(log.isDebugEnabled()){
			log.debug("Entered Into saveProblemData Method.....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				ProblemBeanVO problemBeanFromDB = new ProblemBeanVO();
				Problem problem = null;
				ProblemAndProblemSource problemAndProblemSource = null;
				ProblemLocation problemLocation = null;
				ProblemHistory problemHistory = null;
				try{					
					ProblemSource problemSource = problemSourceDAO.get(ProblemManagementService.this.problemBeanVO.getProbSourceId());
					ProblemExternalSource problemExternalSource = null;
					Registration reg = null;
					Hamlet hamlet = null;
					problem = new Problem();
					problemAndProblemSource = new ProblemAndProblemSource();
					problem.setProblem(ProblemManagementService.this.problemBeanVO.getProblem());
					problem.setDescription(ProblemManagementService.this.problemBeanVO.getDescription());
					problem.setYear(ProblemManagementService.this.problemBeanVO.getYear());					
					Date iDate = sdf.parse(ProblemManagementService.this.problemBeanVO.getReportedDate());
					Date eDate = sdf.parse(ProblemManagementService.this.problemBeanVO.getExistingFrom());
					problem.setIdentifiedOn(iDate);
					problem.setExistingFrom(eDate);
					problemAndProblemSource.setProblemSource(problemSource);
					problemAndProblemSource.setProblem(problem);
					reg = registrationDAO.get(ProblemManagementService.this.problemBeanVO.getUserID());
					problemAndProblemSource.setUser(reg);
					
					if(problemSource.getProblemSource().equals(IConstants.CALL_CENTER) || problemSource.getProblemSource().equals(IConstants.EXTERNAL_PERSON))
					{
						problemExternalSource = new ProblemExternalSource();
						problemExternalSource.setName(ProblemManagementService.this.problemBeanVO.getName());
						problemExternalSource.setMobile(ProblemManagementService.this.problemBeanVO.getMobile());
						problemExternalSource.setEmail(ProblemManagementService.this.problemBeanVO.getEmail());
						problemExternalSource.setAddress(ProblemManagementService.this.problemBeanVO.getAddress());
						problemExternalSource.setTelePhone(ProblemManagementService.this.problemBeanVO.getPhone());
						problemAndProblemSource.setProblemExternalSource(problemExternalSource);
					}
					problemLocation = new ProblemLocation();
					hamlet = hamletDAO.get(new Long(ProblemManagementService.this.problemBeanVO.getHamlet()));
					problemLocation.setHamlet(hamlet);
					problemLocation.setProblemAndProblemSource(problemAndProblemSource);
					problemHistory = new ProblemHistory();
					problemHistory.setProblemLocation(problemLocationDAO.save(problemLocation));
					problemHistory.setProblemStatus(problemStatusDAO.get(problemBeanVO.getProblemStatusId()));
					problemHistory.setDateUpdated(getCurrentDateAndTime());
					problemHistory = problemHistoryDAO.save(problemHistory);				
					
					System.out.println("problemHistory.getProblemHistoryId()----"+problemHistory.getProblemHistoryId());
					problemBeanFromDB.setProblemHistoryId(problemHistory.getProblemHistoryId());
					problemBeanFromDB.setProblemLocationId(problemHistory.getProblemLocation().getProblemLocationId());
					problemBeanFromDB.setProblem(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
					problemBeanFromDB.setDescription(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
					Date iDateOfAddNewProb = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
					Date eDateOfAddNewProb = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom();
					problemBeanFromDB.setReportedDate(sdf.format(iDateOfAddNewProb));
					problemBeanFromDB.setExistingFrom(sdf.format(eDateOfAddNewProb));
					problemBeanFromDB.setHamlet(problemHistory.getProblemLocation().getHamlet().getHamletName());
					problemBeanFromDB.setProbSource(problemSource.getProblemSource());	
					
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
				}
				ProblemManagementService.this.problemBeanVO = problemBeanFromDB;
			}
		});
		return this.problemBeanVO;
	}
	/**
	 * To Get The New Problems Of A User From DB 
	 */
	public ProblemsOfUserVO getNewProblemsForUser(Long registrationId, Long statusId) {
		if(log.isDebugEnabled()){
			log.debug("Inside the method :getProblemsForUser:");
		}
		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		List<ProblemBeanVO> problemBeanVOs = null;
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> problemRegionScopes = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> problemTypes = new ArrayList<SelectOptionVO>();
		if(log.isDebugEnabled()){
			log.debug("Entered Into getProblemsForUser  Method.....");
			log.debug("registrationId: " + registrationId);
			}
			List<ProblemHistory> problemHistories = problemHistoryDAO.findProblemLocationsByUserId(registrationId, statusId); 
			if(problemHistories.size() > 0){
				problemBeanVOs = getUserProblems(problemHistories);
			}
			
			List<ProblemSourceScope> regionScopes = problemSourceScopeDAO.getAll();
			if(log.isDebugEnabled()){
				log.debug("Total Classifications:"+regionScopes.size());
			}
			for(ProblemSourceScope problemSourceScope:regionScopes){
				SelectOptionVO proSelectOptionVO = new SelectOptionVO(problemSourceScope.getProblemSourceScopeId(),	problemSourceScope.getScope());
				problemRegionScopes.add(proSelectOptionVO);
			}
			List<ProblemClassification> problemClassificationList = problemClassificationDAO.getAll();
			
			if(log.isDebugEnabled()){
				log.debug("Total Classifications:"+problemClassificationList.size());
			}
			for(ProblemClassification problemClassification:problemClassificationList){
				SelectOptionVO problemClassificationVO = new SelectOptionVO(problemClassification.getProblemClassificationId(), problemClassification.getClassification());
				problemTypes.add(problemClassificationVO);
			}
			problemsOfUserVO.setProblemRegionScopes(problemRegionScopes);
			problemsOfUserVO.setProblemsByUser(problemBeanVOs);
			problemsOfUserVO.setProblemTypes(problemTypes);
		return problemsOfUserVO;
	}
	
	/**
	 * Used By The getNewProblemsForUser method to Get the Problem Data From ProblemHistories in the Form of ProblemBeanVOs
	 * @param problemHistories
	 * @return
	 */
	public List<ProblemBeanVO> getUserProblems(List<ProblemHistory> problemHistories)
	{
		List<ProblemBeanVO> problemBean = null;
		if(problemHistories != null)
		{
			Problem problem = null;
			ProblemLocation problemLocation = null;
			ProblemAndProblemSource problemAndProblemSource = null;
			problemBean = new ArrayList<ProblemBeanVO>();
			
			Date iDate,eDate;					
			for(ProblemHistory problemHistory:problemHistories){
				if(!(problemHistory.getIsDelete()!=null && problemHistory.getIsDelete().toString().equalsIgnoreCase("true"))){
					problemLocation = problemHistory.getProblemLocation();
					problem = problemLocation.getProblemAndProblemSource().getProblem();
					problemAndProblemSource = problemLocation.getProblemAndProblemSource();
					ProblemBeanVO problemBeanVO = new ProblemBeanVO();				
					problemBeanVO.setProblemId(problem.getProblemId());
					problemBeanVO.setProblemLocationId(problemLocation.getProblemLocationId());
					problemBeanVO.setHamletId(problemLocation.getHamlet().getHamletId());
					problemBeanVO.setProblemHistoryId(problemHistory.getProblemHistoryId());
					problemBeanVO.setProblem(problem.getProblem());
					problemBeanVO.setDescription(problem.getDescription());				
					iDate = problem.getIdentifiedOn();
					eDate = problem.getExistingFrom();
					problemBeanVO.setReportedDate(sdf.format(iDate));
					problemBeanVO.setExistingFrom(sdf.format(eDate));
					problemBeanVO.setHamlet(problemLocation.getHamlet().getHamletName());
					problemBeanVO.setProbSource(problemAndProblemSource.getProblemSource().getProblemSource());	
					problemBean.add(problemBeanVO);
				}
				
			}
		}
		return problemBean;
 	}	
	
	/**
	 * To Get The Classified Problem From DB OF A Particular User
	 */
	public List<ProblemBeanVO> getClassifiedProblemsOfUser(Long registrationId, Long statusId){
		List<ProblemBeanVO> problemsFromDB = new ArrayList<ProblemBeanVO>();
		ProblemBeanVO problemFromDB = null;
		List<ProblemHistory> problemHistories = problemHistoryDAO.findProblemLocationsByUserId(registrationId, statusId);
		List<SelectOptionVO> departmentVOs = null;
		Date iDate;
		for(ProblemHistory problemHistory:problemHistories){
			if(!(problemHistory.getIsDelete()!=null && problemHistory.getIsDelete().toString().equalsIgnoreCase("true"))){
				problemFromDB = new ProblemBeanVO();
				problemFromDB.setProblemHistoryId(problemHistory.getProblemHistoryId());
				problemFromDB.setProblem(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
				iDate = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
				problemFromDB.setReportedDate(sdf.format(iDate));
				problemFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());
				problemFromDB.setProblemSourceScope(problemHistory.getProblemSourceScope().getScope());
				problemFromDB.setProblemType(problemHistory.getProblemLocation().getProblemClassification().getClassification());
				problemFromDB.setProblemLocationId(problemHistory.getProblemLocation().getProblemLocationId());
				problemsFromDB.add(problemFromDB);
			}
		}	
		return problemsFromDB;
	}
	
	/**
	 * To Get the List of all problems that are modified from New status to Classify status
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetClassifiedProblemDataIntoDB(List<ProblemBeanVO> problemsToUpdate){
		problemBeanVOs = problemsToUpdate;
		 List<ProblemBeanVO> updatedProblemsFromDB = (List<ProblemBeanVO>)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status) {
				 Problem problem;
				 ProblemLocation problemLocation;
				 ProblemClassification problemClassification;
				 ProblemHistory problemHistory;
				 ProblemHistory classifiedProblemData = null;
				 ProblemSourceScope problemSourceScope;
				 ProblemStatus problemStatus;
				 ProblemBeanVO problemBeanFromDB;   
				 ResultStatus resultStatus = new ResultStatus();
				 List<ProblemBeanVO> updatedProblems = new ArrayList<ProblemBeanVO>();
				 List<SelectOptionVO> departmentVOs = null;	
				try{
					 for(ProblemBeanVO problemBeanVO:problemBeanVOs){ 
						 departmentVOs = new ArrayList<SelectOptionVO>();
						 problemBeanFromDB = new ProblemBeanVO();
						 problemLocation = problemLocationDAO.get(problemBeanVO.getProblemLocationId());
						 problemStatus = problemStatusDAO.get(problemBeanVO.getProblemStatusId());						 
						 problemSourceScope = problemSourceScopeDAO.findBySourceScope(problemBeanVO.getProblemSourceScope()).get(0);
						 problemClassification = problemClassificationDAO.findByClassification(problemBeanVO.getProblemClassification()).get(0);
						 
						 problem = problemLocation.getProblemAndProblemSource().getProblem();						 
						 problem.setDescription(problemBeanVO.getDescription());
						 problem.setProblem(problemBeanVO.getProblem());
						 
						 problemLocation.getProblemAndProblemSource().setProblem(problem);
						 problemLocation.setProblemClassification(problemClassification);	 				
						
						 problemHistory = problemHistoryDAO.get(problemBeanVO.getProblemHistoryId());
						 problemHistory.setIsDelete("true");						 
						 problemHistory = problemHistoryDAO.save(problemHistory);
						 
						 classifiedProblemData = new ProblemHistory();
						 classifiedProblemData.setProblemLocation(problemLocation);
						 classifiedProblemData.setProblemStatus(problemStatus);
						 classifiedProblemData.setProblemSourceScope(problemSourceScope);
						 classifiedProblemData.setDateUpdated(getCurrentDateAndTime());
						 problemHistory = problemHistoryDAO.save(classifiedProblemData);
						 
						 problemBeanFromDB.setProblemHistoryId(problemHistory.getProblemHistoryId());
						 problemBeanFromDB.setProblem(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
						 Date iDateOfAddNewProb = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
						 problemBeanFromDB.setReportedDate(sdf.format(iDateOfAddNewProb));
						 problemBeanFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());
						 problemBeanFromDB.setProblemSourceScope(problemHistory.getProblemSourceScope().getScope());
						 problemBeanFromDB.setProblemType(problemHistory.getProblemLocation().getProblemClassification().getClassification());					 
						 problemBeanFromDB.setProblemLocationId(problemHistory.getProblemLocation().getProblemLocationId());
						 updatedProblems.add(problemBeanFromDB);						
					 }
					
					
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
					resultStatus.setExceptionEncountered(e);
					resultStatus.setResultPartial(true);
				}
				problemBeanVOs = null;
				return updatedProblems;
			}
		});
		return updatedProblemsFromDB;
	}
	public List<SelectOptionVO> getDepartmentsForProblemScope(String problemScope){
	 
		List<SelectOptionVO> departmentsByScope = new ArrayList<SelectOptionVO>();
		try{
		List<ProblemSourceScopeConcernedDepartment> deptsByScope = problemSourceScopeConcernedDepartmentDAO.findDepartmentsByScope(problemScope);
		if(deptsByScope == null){log.debug("No Departments have been retrieved for the selected scope");}
		for(ProblemSourceScopeConcernedDepartment problemSourceScopeConcnedDept:deptsByScope)
			departmentsByScope.add(new SelectOptionVO(problemSourceScopeConcnedDept.getProblemSourceScopeConcernedDepartmentId(),problemSourceScopeConcnedDept.getDepartment()));
		}catch (Exception e){
			log.debug("Exception Encountered", e);
		}
		return departmentsByScope;
		
	}
	/**
	 * To Get the List of all problems that are modified from classify status to Assigned status
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetAssignedProblems(List<ProblemBeanVO> problemsToAssign){
		this.problemBeanVOs = problemsToAssign;
		List<ProblemBeanVO> assignedProblemsFromDB = (List<ProblemBeanVO>)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				List<ProblemBeanVO> assignedProblems = new ArrayList<ProblemBeanVO>();
				ResultStatus resultStatus = new ResultStatus();
				ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment = null;
				ProblemHistory problemHistory = null;
				ProblemStatus problemStatus = null;
				AssignedProblemProgress assignedProblemProgress = null;
				ProblemHistory assignedProblemData = null;
				try{
					for(ProblemBeanVO problemBeanVO:problemBeanVOs){
						problemSourceScopeConcernedDepartment = problemSourceScopeConcernedDepartmentDAO.findByDepartmentAndScope(problemBeanVO.getDepartment(), problemBeanVO.getProblemSourceScope()).get(0);
						problemStatus = problemStatusDAO.get(problemBeanVO.getProblemStatusId());
						problemHistory = problemHistoryDAO.get(problemBeanVO.getProblemHistoryId());
					
						assignedProblemData = new ProblemHistory();
						assignedProblemData.setProblemLocation(problemHistory.getProblemLocation());
						assignedProblemData.setProblemStatus(problemStatus);
						assignedProblemData.setProblemSourceScope(problemHistory.getProblemSourceScope());
						assignedProblemData.setDateUpdated(getCurrentDateAndTime());
						assignedProblemData = problemHistoryDAO.save(assignedProblemData);
						
						problemHistory.setIsDelete("true");
						problemHistory = problemHistoryDAO.save(problemHistory);

						assignedProblemProgress = new AssignedProblemProgress();
						assignedProblemProgress.setProblemHistory(assignedProblemData);
						assignedProblemProgress.setProblemSourceScopeConcernedDepartment(problemSourceScopeConcernedDepartment);
						assignedProblemProgress.setDescription(problemBeanVO.getComments());
						assignedProblemProgress = assignedProblemProgressDAO.save(assignedProblemProgress);
						problemBeanVO.setAssignedProblemProgressId(assignedProblemProgress.getAssignedProblemProgressId());
						problemBeanVO.setProblem(assignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
						problemBeanVO.setDepartment(assignedProblemProgress.getProblemSourceScopeConcernedDepartment().getDepartment());
						problemBeanVO.setProblemLocationId(assignedProblemProgress.getProblemHistory().getProblemLocation().getProblemLocationId());
						assignedProblems.add(problemBeanVO);
					}
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
					resultStatus.setExceptionEncountered(e);
					resultStatus.setResultPartial(true);
				}
				problemBeanVOs = null;
				return assignedProblems;
			}
		});
		
		return assignedProblemsFromDB;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetProblemsUnderProgress(List<ProblemBeanVO> problemsInProgress){
		problemBeanVOs = problemsInProgress;
		List<ProblemBeanVO> problemsInProgressFromDB = (List<ProblemBeanVO>)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				List<ProblemBeanVO> progressInProblems = new ArrayList<ProblemBeanVO>();
				ResultStatus resultStatus = new ResultStatus();
				AssignedProblemProgress assignedProblemProgress = null;
				ProblemStatus problemStatus = null;
				ProblemHistory progressProblemData = null;
				AssignedProblemProgress newAssignedProblemProgress = null;
				try{
					for(ProblemBeanVO problemBeanVO:problemBeanVOs){
						problemStatus = problemStatusDAO.get(problemBeanVO.getProblemStatusId());
						assignedProblemProgress = assignedProblemProgressDAO.get(problemBeanVO.getAssignedProblemProgressId());
																							
						assignedProblemProgress.getProblemHistory().setIsDelete("true");
						assignedProblemProgress = assignedProblemProgressDAO.save(assignedProblemProgress);				
						
						progressProblemData = new ProblemHistory();
						progressProblemData.setProblemLocation(assignedProblemProgress.getProblemHistory().getProblemLocation());
						progressProblemData.setProblemStatus(problemStatus);
						progressProblemData.setProblemSourceScope(assignedProblemProgress.getProblemHistory().getProblemSourceScope());
						progressProblemData.setDateUpdated(getCurrentDateAndTime());
						progressProblemData.setComments(problemBeanVO.getComments());
						progressProblemData = problemHistoryDAO.save(progressProblemData);
						
						newAssignedProblemProgress = new AssignedProblemProgress();
						newAssignedProblemProgress.setConcernedPersonName(problemBeanVO.getDepartmentConcernedPersonName());
						newAssignedProblemProgress.setDesignation(problemBeanVO.getDesignation());
						newAssignedProblemProgress.setContactNo(problemBeanVO.getDepartmentConcernedPersonPhoneNumber());
						newAssignedProblemProgress.setDescription(problemBeanVO.getComments());
						newAssignedProblemProgress.setProblemHistory(progressProblemData);
						newAssignedProblemProgress.setProblemSourceScopeConcernedDepartment(assignedProblemProgress.getProblemSourceScopeConcernedDepartment());
						newAssignedProblemProgress = assignedProblemProgressDAO.save(newAssignedProblemProgress);
						
						problemBeanVO.setAssignedProblemProgressId(newAssignedProblemProgress.getAssignedProblemProgressId());
						problemBeanVO.setProblem(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());						
						Date iDate = newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
						problemBeanVO.setReportedDate(sdf.format(iDate));						
						problemBeanVO.setDepartmentConcernedPersonName(newAssignedProblemProgress.getConcernedPersonName());
						problemBeanVO.setDesignation(newAssignedProblemProgress.getDesignation());
						problemBeanVO.setContactNo(newAssignedProblemProgress.getContactNo());
						problemBeanVO.setComments(problemBeanVO.getComments());
						problemBeanVO.setReasonForPending(problemBeanVO.getComments());
						problemBeanVO.setProblemLocationId(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemLocationId());
						progressInProblems.add(problemBeanVO);
						
					}
					
				}catch(Exception e){
					status.setRollbackOnly();
					resultStatus.setExceptionEncountered(e);
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
					resultStatus.setResultPartial(true);
				}
				problemBeanVOs = null;
				return progressInProblems;
			}
		});
		return problemsInProgressFromDB;
	}
	
	public List<ProblemBeanVO> getProblemsUnderProgress(Long registrationId, Long statusId){
		List<AssignedProblemProgress> problemsUnderProgress = assignedProblemProgressDAO.findByRegistrationIdAndStatusId(registrationId, statusId);
		List<ProblemBeanVO> problemsUnderProgressFromDB = new ArrayList<ProblemBeanVO>();
		for(AssignedProblemProgress problemProgerss:problemsUnderProgress){
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setAssignedProblemProgressId(problemProgerss.getAssignedProblemProgressId());
			problemBeanVO.setProblem(problemProgerss.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
			Date iDate = problemProgerss.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));			
			problemBeanVO.setDepartment(problemProgerss.getProblemSourceScopeConcernedDepartment().getDepartment());
			problemBeanVO.setDepartmentConcernedPersonName(problemProgerss.getConcernedPersonName());
			problemBeanVO.setDesignation(problemProgerss.getDesignation());
			problemBeanVO.setContactNo(problemProgerss.getContactNo());
			problemBeanVO.setProblemLocationId(problemProgerss.getProblemHistory().getProblemLocation().getProblemLocationId());
			problemsUnderProgressFromDB.add(problemBeanVO);
		}
		return problemsUnderProgressFromDB;
	}
	
	/*
	 * This method will take the problems list which are migrated from progress state to pending state and updates the database
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetProblemsUnderPending(List<ProblemBeanVO> pendingProblems){
		problemBeanVOs = pendingProblems;
		List<ProblemBeanVO> pendingProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate.execute(new TransactionCallback(){
			@SuppressWarnings("deprecation")
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus resultStatus = new ResultStatus();
				List<ProblemBeanVO> pendingProblems = new ArrayList<ProblemBeanVO>();
				ProblemStatus problemStatus = null;
				AssignedProblemProgress assignedProblemProgress = null;
				ProblemHistory pendingProblemData = null;
				AssignedProblemProgress newAssignedProblemProgress = null;
				try{
					for(ProblemBeanVO problemBeanVO : problemBeanVOs) {
						problemStatus = problemStatusDAO.get(problemBeanVO.getProblemStatusId());
						assignedProblemProgress = assignedProblemProgressDAO.get(problemBeanVO.getAssignedProblemProgressId());	
						assignedProblemProgress.getProblemHistory().setIsDelete("true");
						assignedProblemProgress = assignedProblemProgressDAO.save(assignedProblemProgress);
						pendingProblemData = new ProblemHistory();
						pendingProblemData.setProblemLocation(assignedProblemProgress.getProblemHistory().getProblemLocation());
						pendingProblemData.setProblemStatus(problemStatus);
						pendingProblemData.setProblemSourceScope(assignedProblemProgress.getProblemHistory().getProblemSourceScope());
						pendingProblemData.setDateUpdated(getCurrentDateAndTime());
						pendingProblemData.setComments(problemBeanVO.getReasonForPending());
						pendingProblemData = problemHistoryDAO.save(pendingProblemData);
						
						
						newAssignedProblemProgress = new AssignedProblemProgress();
						newAssignedProblemProgress.setConcernedPersonName(problemBeanVO.getDepartmentConcernedPersonName());
						newAssignedProblemProgress.setDesignation(problemBeanVO.getDesignation());
						newAssignedProblemProgress.setContactNo(problemBeanVO.getDepartmentConcernedPersonPhoneNumber());
						newAssignedProblemProgress.setDescription(problemBeanVO.getReasonForPending());
						newAssignedProblemProgress.setProblemHistory(pendingProblemData);
						newAssignedProblemProgress.setProblemSourceScopeConcernedDepartment(assignedProblemProgress.getProblemSourceScopeConcernedDepartment());
						newAssignedProblemProgress = assignedProblemProgressDAO.save(newAssignedProblemProgress);
						
						problemBeanVO.setAssignedProblemProgressId(newAssignedProblemProgress.getAssignedProblemProgressId());
						problemBeanVO.setProblem(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
						Date iDate = newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
						problemBeanVO.setReportedDate(sdf.format(iDate));
						problemBeanVO.setDepartmentConcernedPersonName(newAssignedProblemProgress.getConcernedPersonName());
						problemBeanVO.setDesignation(newAssignedProblemProgress.getDesignation());
						problemBeanVO.setContactNo(newAssignedProblemProgress.getContactNo());
						Date pDate = newAssignedProblemProgress.getProblemHistory().getDateUpdated();						
						problemBeanVO.setUpdatedDate(sdf.format(pDate)); 
						problemBeanVO.setComments(problemBeanVO.getComments());
						problemBeanVO.setReasonForPending(problemBeanVO.getComments());
						problemBeanVO.setProblemLocationId(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemLocationId());
						pendingProblems.add(problemBeanVO);
					}
				}catch(Exception e){
					status.setRollbackOnly();
					e.printStackTrace();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
					resultStatus.setExceptionEncountered(e);
					resultStatus.setResultPartial(true);
				}
				problemBeanVOs = null;
				return pendingProblems;
			}			
		});
		return pendingProblemsFromDB;
	}
	/*
	 * To get all the problems under pending state
	 */
	public List<ProblemBeanVO> getPendingProblemsForAnUser(Long registrationId, Long statusId){
		List<ProblemBeanVO> pendingProblemsFromDB = new ArrayList<ProblemBeanVO>();
		List<AssignedProblemProgress> pendingProblems = assignedProblemProgressDAO.findByRegistrationIdAndStatusId(registrationId, statusId);
		ProblemBeanVO problemBeanVO = null;
		for (AssignedProblemProgress pendingProblem : pendingProblems) {
			problemBeanVO = new ProblemBeanVO();
 			problemBeanVO.setAssignedProblemProgressId(pendingProblem.getAssignedProblemProgressId());
			problemBeanVO.setProblem(pendingProblem.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
			Date iDate = pendingProblem.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));
			problemBeanVO.setDepartment(pendingProblem.getProblemSourceScopeConcernedDepartment().getDepartment());
			problemBeanVO.setDepartmentConcernedPersonName(pendingProblem.getConcernedPersonName());
			problemBeanVO.setDesignation(pendingProblem.getDesignation());
			problemBeanVO.setContactNo(pendingProblem.getContactNo());
			Date pDate = pendingProblem.getProblemHistory().getDateUpdated();
			problemBeanVO.setUpdatedDate(sdf.format(pDate));
			problemBeanVO.setReasonForPending(pendingProblem.getProblemHistory().getComments());
			problemBeanVO.setProblemLocationId(pendingProblem.getProblemHistory().getProblemLocation().getProblemLocationId());
			pendingProblemsFromDB.add(problemBeanVO);
		}
		return pendingProblemsFromDB;
	}
	/*
	 * To get all the problem under assgined state
	 * 
	 */
	public List<ProblemBeanVO> getAssignedProblems(Long registrationId, Long statusId){
		List<ProblemBeanVO> assignedProblemsFromDB = new ArrayList<ProblemBeanVO>();   
		List<AssignedProblemProgress> assignedProblems = assignedProblemProgressDAO.findByRegistrationIdAndStatusId(registrationId, statusId);
		ProblemBeanVO problemBeanVO = null;
		for (AssignedProblemProgress assignedProblem : assignedProblems) {
			if(!(assignedProblem.getProblemHistory().getIsDelete()!=null && assignedProblem.getProblemHistory().getIsDelete().equalsIgnoreCase("true"))){
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setAssignedProblemProgressId(assignedProblem.getAssignedProblemProgressId());
				problemBeanVO.setProblem(assignedProblem.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
				problemBeanVO.setDepartment(assignedProblem.getProblemSourceScopeConcernedDepartment().getDepartment());
				problemBeanVO.setProblemLocationId(assignedProblem.getProblemHistory().getProblemLocation().getProblemLocationId());
				assignedProblemsFromDB.add(problemBeanVO);
			}
		}
		return assignedProblemsFromDB;
	}
	/*
	 * This method takes problems migrated from progress state to fixed state and updates the records in the database
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetProblemsUnderFixed(List<ProblemBeanVO> problemsFixed){
		problemBeanVOs = problemsFixed;
		List<ProblemBeanVO> fixedProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate.execute(new TransactionCallback(){
			@SuppressWarnings("deprecation")
			public Object doInTransaction(TransactionStatus status){
				ResultStatus resultStatus = new ResultStatus();
				List<ProblemBeanVO> fixedProblems = new ArrayList<ProblemBeanVO>();
				ProblemStatus problemStatus = null;
				AssignedProblemProgress assignedProblemProgress = null;
				ProblemHistory fixedProblemData = null;
				try{
					for(ProblemBeanVO problemBeanVO: problemBeanVOs){
						problemStatus = problemStatusDAO.get(problemBeanVO.getProblemStatusId());
						assignedProblemProgress = assignedProblemProgressDAO.get(problemBeanVO.getAssignedProblemProgressId());
												
						assignedProblemProgress.getProblemHistory().setIsDelete("true");
						assignedProblemProgress.getProblemHistory().setComments(problemBeanVO.getComments());
						Date problemFixedDate = sdf.parse(problemBeanVO.getUpdatedDate());
						assignedProblemProgress.getProblemHistory().setDateUpdated(problemFixedDate);
						assignedProblemProgress = assignedProblemProgressDAO.save(assignedProblemProgress);					

						fixedProblemData = new ProblemHistory();
						fixedProblemData.setProblemLocation(assignedProblemProgress.getProblemHistory().getProblemLocation());
						fixedProblemData.setProblemStatus(problemStatus);
						fixedProblemData.setProblemSourceScope(assignedProblemProgress.getProblemHistory().getProblemSourceScope());
						fixedProblemData.setDateUpdated(getCurrentDateAndTime());
						fixedProblemData.setComments(problemBeanVO.getComments());
						fixedProblemData = problemHistoryDAO.save(fixedProblemData);						
						
						AssignedProblemProgress newAssignedProblemProgress = new AssignedProblemProgress();
						newAssignedProblemProgress.setConcernedPersonName(problemBeanVO.getDepartmentConcernedPersonName());
						newAssignedProblemProgress.setDesignation(problemBeanVO.getDesignation());
						newAssignedProblemProgress.setContactNo(problemBeanVO.getDepartmentConcernedPersonPhoneNumber());
						newAssignedProblemProgress.setDescription(problemBeanVO.getComments());
						newAssignedProblemProgress.setProblemHistory(fixedProblemData);
						newAssignedProblemProgress.setProblemSourceScopeConcernedDepartment(assignedProblemProgress.getProblemSourceScopeConcernedDepartment());
						newAssignedProblemProgress = assignedProblemProgressDAO.save(newAssignedProblemProgress);		
						
						problemBeanVO.setAssignedProblemProgressId(newAssignedProblemProgress.getAssignedProblemProgressId());
						problemBeanVO.setProblem(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
						Date iDate = newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
						problemBeanVO.setReportedDate(sdf.format(iDate));
						Date fDate = assignedProblemProgress.getProblemHistory().getDateUpdated();
						problemBeanVO.setUpdatedDate(sdf.format(fDate));
						problemBeanVO.setProblemLocationId(newAssignedProblemProgress.getProblemHistory().getProblemLocation().getProblemLocationId());
						fixedProblems.add(problemBeanVO);
					}
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Fixed::", e);
					}
					resultStatus.setExceptionEncountered(e);
					resultStatus.setResultPartial(true);
				}
				problemBeanVOs = null;
				return fixedProblems;
			}
		});
		return fixedProblemsFromDB;
	}
	/*
	 * To Get all Fixed Probelm's Details
	 */
	public List<ProblemBeanVO> getFixedProblemsForUser(Long registrationId, Long statusId){
		List<ProblemBeanVO> fixedProblemsFromDB = new ArrayList<ProblemBeanVO>();
		List<AssignedProblemProgress> problemsUnderFixed = assignedProblemProgressDAO.findByRegistrationIdAndStatusId(registrationId, statusId);	
		for (AssignedProblemProgress problemsFixed :problemsUnderFixed ) {			
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setAssignedProblemProgressId(problemsFixed.getAssignedProblemProgressId());
			problemBeanVO.setProblem(problemsFixed.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
			Date iDate = problemsFixed.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));
			problemBeanVO.setDepartment(problemsFixed.getProblemSourceScopeConcernedDepartment().getDepartment());
			Date fDate = problemsFixed.getProblemHistory().getDateUpdated();
			problemBeanVO.setUpdatedDate(sdf.format(fDate));
			problemBeanVO.setComments(problemsFixed.getProblemHistory().getComments());	
			problemBeanVO.setProblemLocationId(problemsFixed.getProblemHistory().getProblemLocation().getProblemLocationId());
			fixedProblemsFromDB.add(problemBeanVO);
		}
		return fixedProblemsFromDB;		
	}
	
	/*
	 * To convert the date that is retrived from DB to dd/MM/yyyy HH:mm:ss
	 */
	public Date getCurrentDateAndTime(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			return now;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}

