/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dto.HamletProblemVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.ProblemSource;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemManagementService implements IProblemManagementService {

	private IProblemLocationDAO problemLocationDAO;
	private static final Logger log = Logger.getLogger("ProblemManagementService.class");
	private IRegistrationDAO registrationDAO;
	private IHamletDAO hamletDAO;
	private ITownshipDAO townshipDAO;
	private ProblemExternalSourceDAO problemExternalSourceDAO;
	private TransactionTemplate transactionTemplate;
	private ProblemBeanVO problemBeanVO;
		
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
		}
		
	return problemManagementDataVO;
	}
	
	public List<HamletProblemVO> getHamletProblems(List<ProblemLocation> problemLocations) throws Exception{
		
		List<HamletProblemVO> hamletProblemsVO = null;
		if(problemLocations != null){
			Problem problem = null;
			ProblemSource problemSource = null;
			ProblemExternalSource problemExternalSource = null;
			hamletProblemsVO = new ArrayList<HamletProblemVO>();
			
			for(ProblemLocation problemLoctn:problemLocations){
				problem = problemLoctn.getProblemSource().getProblem();
				problemSource = problemLoctn.getProblemSource();
				problemExternalSource = problemLoctn.getProblemSource().getProblemExternalSource();
				
				HamletProblemVO hamletProblemVO = new HamletProblemVO();
				hamletProblemVO.setProblemDesc(problem.getDescription());
				hamletProblemVO.setIdentifiedDate(problem.getIdentifiedOn().toString());
				hamletProblemVO.setProblemId(problem.getProblemId());
				hamletProblemVO.setYear(problem.getYear());
				hamletProblemVO.setProblemStatus("--");
				
				if(problemSource.getUser() != null)
					hamletProblemVO.setProblemSource(problemSource.getUser().getFirstName());
				if(problemExternalSource != null)
					hamletProblemVO.setProblemSource(problemExternalSource.getName());
				
				hamletProblemsVO.add(hamletProblemVO);		
			}
		}
		return hamletProblemsVO;
	}
	
	/**
	 * This method is used for saving the problem data that is entered by user in UserInterface
	 * in to database
	 */
	public ProblemBeanVO saveProblemData(ProblemBeanVO problemBeanVO)
	{
		this.problemBeanVO = problemBeanVO;
		if(log.isDebugEnabled()){
			log.debug("Entered Into saveProblemData Method.....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				ProblemBeanVO problemBeanFromDB = new ProblemBeanVO();
				Problem problem = null;
				ProblemSource problemSource = null;
				ProblemLocation problemLocation = null;
				try{
					ProblemExternalSource problemExternalSource = null;
					Registration reg = null;
					Hamlet hamlet = null;
					Township township = null;
					problem = new Problem();
					problemSource = new ProblemSource();
					problem.setProblem(ProblemManagementService.this.problemBeanVO.getProblem());
					problem.setDescription(ProblemManagementService.this.problemBeanVO.getDescription());
					problem.setYear(ProblemManagementService.this.problemBeanVO.getYear());
					SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
					Date iDate = sdf.parse(ProblemManagementService.this.problemBeanVO.getReportedDate());
					Date eDate = sdf.parse(ProblemManagementService.this.problemBeanVO.getExistingFrom());
					problem.setIdentifiedOn(iDate);
					problem.setExistingFrom(eDate);
					problemSource.setSource(ProblemManagementService.this.problemBeanVO.getProbSource());
					problemSource.setProblem(problem);
					reg = registrationDAO.get(ProblemManagementService.this.problemBeanVO.getUserID());
					problemSource.setUser(reg);
					
					if(ProblemManagementService.this.problemBeanVO.getProbSource().equals(IConstants.CALL_CENTER) || ProblemManagementService.this.problemBeanVO.getProbSource().equals(IConstants.EXTERNAL_PERSON))
					{
						problemExternalSource = new ProblemExternalSource();
						problemExternalSource.setName(ProblemManagementService.this.problemBeanVO.getName());
						problemExternalSource.setMobile(ProblemManagementService.this.problemBeanVO.getMobile());
						problemExternalSource.setTelePhone(ProblemManagementService.this.problemBeanVO.getPhone());
						problemExternalSource.setEmail(ProblemManagementService.this.problemBeanVO.getEmail());
						problemExternalSource.setAddress(ProblemManagementService.this.problemBeanVO.getAddress());
						problemSource.setProblemExternalSource(problemExternalSource);
					}
					problemLocation = new ProblemLocation();
					hamlet = hamletDAO.get(new Long(ProblemManagementService.this.problemBeanVO.getHamlet()));
					township = townshipDAO.get(new Long(ProblemManagementService.this.problemBeanVO.getVillage()));
					problemLocation.setHamlet(hamlet);
					problemLocation.setTownship(township);
					problemLocation.setProblemSource(problemSource);
					problemLocation = problemLocationDAO.save(problemLocation);
					problemBeanFromDB.setProblem(problemLocation.getProblemSource().getProblem().getProblem());
					problemBeanFromDB.setDescription(problemLocation.getProblemSource().getProblem().getDescription());
					Date idenDate = problemLocation.getProblemSource().getProblem().getIdentifiedOn();
					Date existDate = problemLocation.getProblemSource().getProblem().getExistingFrom();
					problemBeanFromDB.setReportedDate(sdf.format(idenDate));
					problemBeanFromDB.setExistingFrom(sdf.format(existDate));
					problemBeanFromDB.setHamlet(problemLocation.getHamlet().getHamletName());
					problemBeanFromDB.setProbSource(problemLocation.getProblemSource().getSource());	
					System.out.println("RAGHU:::::::::::::::::::::::::::::In TRY:::::::::");
				}catch(Exception e){
					status.setRollbackOnly();
					e.printStackTrace();
					System.out.println(e.getMessage());
					System.out.println("RAGHU:::::::::::::::::::::::::::::In Catch:::::::::");
				}
				ProblemManagementService.this.problemBeanVO = problemBeanFromDB;
			}
		});
		System.out.println("RAGHU:::::::::::::::::::::::::::::AFTER TRY:::::::::");
		return this.problemBeanVO;
	}
	/**
	 * This method is used to get the list of problems saved for the corresponding USer.
	 * @param registrationId
	 * @return problemBeanVOs
	 */
	public List<ProblemBeanVO> getProblemsForUser(Long registrationId) {
		
		List<ProblemBeanVO> problemBeanVOs = null;
		ResultStatus resultStatus = new ResultStatus();
		if(log.isDebugEnabled()){
			log.debug("Entered Into getProblemsForUser  Method.....");
			log.debug("registrationId: " + registrationId);
					}
		try{
		List<ProblemLocation> problemLocations = problemLocationDAO.findProblemsByUserId(registrationId); 
		if(problemLocations.size() > 0){
			problemBeanVOs = getUserProblems(problemLocations);
		}
		
		}catch(Exception ex){
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);			
		}
		return problemBeanVOs;
	}
	/**
	 * This method is invoked inside getProblemsForUser(Long registrationId).   
	 * This method retrieves values from models and  populates all the required fields in problemBeanVO 
	 * @param problemLocations
	 * @return problemBean
	 */
	public List<ProblemBeanVO> getUserProblems(List<ProblemLocation> problemLocations)
	{
		List<ProblemBeanVO> problemBean = null;
		if(problemLocations != null)
		{
			Problem problem = null;
			ProblemSource problemSource = null;
			problemBean = new ArrayList<ProblemBeanVO>();
			SimpleDateFormat sdf;
			Date iDate,eDate;			
			for(ProblemLocation problemLocation:problemLocations){
				problem = problemLocation.getProblemSource().getProblem();
				problemSource = problemLocation.getProblemSource();
				ProblemBeanVO problemBeanVO = new ProblemBeanVO();
				sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				iDate = problem.getIdentifiedOn();
				eDate = problem.getExistingFrom();
				problemBeanVO.setProblem(problem.getProblem());
				problemBeanVO.setDescription(problem.getDescription());
				problemBeanVO.setReportedDate(sdf.format(iDate));
				problemBeanVO.setExistingFrom(sdf.format(eDate));
				problemBeanVO.setHamlet(problemLocation.getHamlet().getHamletName());
				problemBeanVO.setProbSource(problemSource.getSource());
				problemBean.add(problemBeanVO);				
			}
		}
		return problemBean;
 	}	
}
