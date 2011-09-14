/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service.impl;


import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDepartmentOrganisationDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IProblemActivityDAO;
import com.itgrids.partyanalyst.dao.IProblemAndProblemSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemClassificationDAO;
import com.itgrids.partyanalyst.dao.IProblemCompleteLocationDAO;
import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemImpactLevelDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeDAO;
import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.dao.IProblemTypeDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.HamletProblemVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemCompleteDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemManagementChartDataVO;
import com.itgrids.partyanalyst.dto.ProblemManagementChartVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemStatusDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DepartmentOrganisation;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemActivity;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;
import com.itgrids.partyanalyst.model.ProblemClassification;
import com.itgrids.partyanalyst.model.ProblemCompleteLocation;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.ProblemImpactLevel;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.ProblemSourceScope;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.ProblemType;
import com.itgrids.partyanalyst.model.RegionScopes;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStringUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemManagementService implements IProblemManagementService {

	private IProblemLocationDAO problemLocationDAO = null;
	private static final Logger log = Logger.getLogger("ProblemManagementService.class");
	private IRegistrationDAO registrationDAO = null;
	private IHamletDAO hamletDAO = null;
	private ITownshipDAO townshipDAO = null;
	private IProblemExternalSourceDAO problemExternalSourceDAO = null;
	private TransactionTemplate transactionTemplate = null;
	private IProblemSourceScopeDAO problemSourceScopeDAO = null;
	private IProblemAndProblemSourceDAO problemAndProblemSourceDAO = null;
	private IProblemClassificationDAO problemClassificationDAO = null;
	private IProblemDAO problemDAO = null;
	private IProblemHistoryDAO problemHistoryDAO = null;
	private IProblemStatusDAO problemStatusDAO = null;
	private IInformationSourceDAO informationSourceDAO = null;
	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO = null;
	private IAssignedProblemProgressDAO assignedProblemProgressDAO = null;
	private IAnanymousUserDAO ananymousUserDAO;
	private IProblemImpactLevelDAO problemImpactLevelDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	private IRegionScopesDAO regionScopesDAO;
	private ICadreDAO cadreDAO;
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;
	private IProblemTypeDAO problemTypeDAO;
	private ProblemBeanVO problemBeanVO = null;
	private List<ProblemBeanVO> problemBeanVOs = null;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO; 	
	private IBoothDAO boothDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;	
	private IStaticDataService staticDataService;
	private IDepartmentOrganisationDAO departmentOrganisationDAO;
	private IProblemCompleteLocationDAO problemCompleteLocationDAO;
	private IProblemActivityDAO problemActivityDAO;
	private IStringUtilService stringUtilService;
	private IProblemManagementReportService problemManagementReportService;
	private SmsCountrySmsService smsCountrySmsService;
	private String refNo=null;
	
	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	public SmsCountrySmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public IStringUtilService getStringUtilService() {
		return stringUtilService;
	}

	public void setStringUtilService(IStringUtilService stringUtilService) {
		this.stringUtilService = stringUtilService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public IProblemTypeDAO getProblemTypeDAO() {
		return problemTypeDAO;
	}

	public void setProblemTypeDAO(IProblemTypeDAO problemTypeDAO) {
		this.problemTypeDAO = problemTypeDAO;
	}
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
	
	public IInformationSourceDAO getInformationSourceDAO() {
		return informationSourceDAO;
	}

	public void setInformationSourceDAO(IInformationSourceDAO informationSourceDAO) {
		this.informationSourceDAO = informationSourceDAO;
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

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IProblemCompleteLocationDAO getProblemCompleteLocationDAO() {
		return problemCompleteLocationDAO;
	}

	public void setProblemCompleteLocationDAO(
			IProblemCompleteLocationDAO problemCompleteLocationDAO) {
		this.problemCompleteLocationDAO = problemCompleteLocationDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDepartmentOrganisationDAO getDepartmentOrganisationDAO() {
		return departmentOrganisationDAO;
	}

	public void setDepartmentOrganisationDAO(
			IDepartmentOrganisationDAO departmentOrganisationDAO) {
		this.departmentOrganisationDAO = departmentOrganisationDAO;
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

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public ICadreProblemDetailsDAO getCadreProblemDetailsDAO() {
		return cadreProblemDetailsDAO;
	}

	public void setCadreProblemDetailsDAO(
			ICadreProblemDetailsDAO cadreProblemDetailsDAO) {
		this.cadreProblemDetailsDAO = cadreProblemDetailsDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}
	
	public IProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			IProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
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

	public IProblemImpactLevelDAO getProblemImpactLevelDAO() {
		return problemImpactLevelDAO;
	}
	
	public void setProblemImpactLevelDAO(
			IProblemImpactLevelDAO problemImpactLevelDAO) {
		this.problemImpactLevelDAO = problemImpactLevelDAO;
	}
	
	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}	

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}	
	
	public IProblemActivityDAO getProblemActivityDAO() {
		return problemActivityDAO;
	}

	public void setProblemActivityDAO(IProblemActivityDAO problemActivityDAO) {
		this.problemActivityDAO = problemActivityDAO;
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
				ProblemCompleteLocation problemCompleteLocation = null;
				
				try{					
					//InformationSource problemSource = informationSourceDAO.get(ProblemManagementService.this.problemBeanVO.getProbSourceId());
					InformationSource problemSource = null;
					if(problemBeanVO.getProbSourceId() != null && problemBeanVO.getProbSourceId() > 0)
						problemSource = informationSourceDAO.get(problemBeanVO.getProbSourceId());
					
					ProblemExternalSource problemExternalSource = null;
					Registration reg = null;
					AnanymousUser externalUser = null;
					Hamlet hamlet = null;
					problem = new Problem();
					problemLocation = new ProblemLocation();
					problemAndProblemSource = new ProblemAndProblemSource();	
					problemHistory = new ProblemHistory();
					problemCompleteLocation = new ProblemCompleteLocation();
					ProblemType problemType = new ProblemType();
					
					if(!problemBeanVO.getProblem().contains(" ")){
						problem.setProblem(stringUtilService.fragmentARegularString(problemBeanVO.getProblem(), 100, " "));
					}else{
						problem.setProblem(problemBeanVO.getProblem());
					}
					
					if(!problemBeanVO.getDescription().contains(" ")){
						problem.setDescription(stringUtilService.fragmentARegularString(problemBeanVO.getDescription(), 100, " "));
					}else{
						problem.setDescription(problemBeanVO.getDescription());
					}
					if(problemBeanVO.getProblemTypeId()!=0){
						problemType = problemTypeDAO.get(problemBeanVO.getProblemTypeId());
						problem.setProblemType(problemType);
					}
				//	problem.setProblem(problemBeanVO.getProblem());
				//	problem.setDescription(problemBeanVO.getDescription());
					problem.setYear(problemBeanVO.getYear());					
					Date iDate = sdf.parse(problemBeanVO.getReportedDate());
					Date eDate = sdf.parse(problemBeanVO.getExistingFrom());
					problem.setIdentifiedOn(iDate);
					problem.setExistingFrom(eDate);
					String str = ((Long)System.currentTimeMillis()).toString();
					refNo= str.substring(str.length()-5,str.length());
					if(new Long(0).equals(problemBeanVO.getProbSourceId()))
						problem.setReferenceNo(getRefNo(refNo,"FU"));	 
					else if(new Long(1).equals(problemBeanVO.getProbSourceId()))
						problem.setReferenceNo(getRefNo(refNo,"PU"));	 
					else if(new Long(2).equals(problemBeanVO.getProbSourceId()))
						problem.setReferenceNo(getRefNo(refNo,"EU"));	 
					else if(new Long(3).equals(problemBeanVO.getProbSourceId()))
						problem.setReferenceNo(getRefNo(refNo,"CC"));	 
					else if(new Long(4).equals(problemBeanVO.getProbSourceId()))
						problem.setReferenceNo(getRefNo(refNo,"CD"));	 
					problemAndProblemSource.setProblem(problem);			
					//Check for Party_Analyst Or Free User
					if(problemBeanVO.getProblemPostedBy().equals(IConstants.PARTY_ANALYST_USER)){
					     
						problemAndProblemSource.setProblemSource(problemSource);
						reg = registrationDAO.get(problemBeanVO.getUserID());
					    problemAndProblemSource.setUser(reg);
					    problemAndProblemSource.setSubUser(registrationDAO.get(problemBeanVO.getSubUserId()));
					    problemHistory.setIsApproved(IConstants.TRUE);
					    
					    if(problemBeanVO.getProbSourceId() == 2 || problemBeanVO.getProbSourceId() == 3)
					    {
					    	problemExternalSource = new ProblemExternalSource();
							problemExternalSource.setName(problemBeanVO.getName());
							problemExternalSource.setMobile(problemBeanVO.getMobile());
							problemExternalSource.setEmail(problemBeanVO.getEmail());
							problemExternalSource.setAddress(problemBeanVO.getAddress());
							problemExternalSource.setTelePhone(problemBeanVO.getPhone());
							problemExternalSource = problemExternalSourceDAO.save(problemExternalSource);
							
							problemAndProblemSource.setProblemExternalSource(problemExternalSource);
					    }
					}
					else if(problemBeanVO.getProblemPostedBy().equals(IConstants.FREE_USER)){
						externalUser = ananymousUserDAO.get(problemBeanVO.getUserID());
						problemAndProblemSource.setExternalUser(externalUser);
						problemHistory.setIsApproved(IConstants.FALSE);
					}
					
					problemCompleteLocation.setState(stateDAO.get(new Long(problemBeanVO.getState())));
					if(problemBeanVO.getIsParliament())
						problemCompleteLocation.setParliamentConstituency(constituencyDAO.get(problemBeanVO.getPConstituencyId()));
					/*if(!problemBeanVO.getIsParliament())
						problemCompleteLocation.setDistrict(districtDAO.get(new Long(problemBeanVO.getDistrict())));*/
					if(problemBeanVO.getDistrict() != null && !"0".equals(problemBeanVO.getDistrict()))
						problemCompleteLocation.setDistrict(districtDAO.get(new Long(problemBeanVO.getDistrict())));
					if(problemBeanVO.getConstituency() != null && !"0".equals(problemBeanVO.getConstituency()))
						problemCompleteLocation.setConstituency(constituencyDAO.get(new Long(problemBeanVO.getConstituency())));
					if(problemBeanVO.getTehsil() != null && !"0".equals(problemBeanVO.getTehsil()))
					{
						if(IConstants.RURAL_TYPE.equals(problemBeanVO.getTehsil().substring(0,1)))
							problemCompleteLocation.setTehsil(tehsilDAO.get(new Long(problemBeanVO.getTehsil().substring(1))));
						else if(IConstants.URBAN_TYPE.equals(problemBeanVO.getTehsil().substring(0,1)))
						{
							Long assemblyLocalElectionBodyId = new Long(problemBeanVO.getTehsil().substring(1));
							List localElectionBodyIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalElectionBodyId);
							Object object = (Object)localElectionBodyIdsList.get(0);
							problemCompleteLocation.setLocalElectionBody(localElectionBodyDAO.get((Long)object));							
						}
					} 
					if(problemBeanVO.getVillage() != null && !"0".equals(problemBeanVO.getVillage()))
					{
						if(IConstants.RURAL_TYPE.equals(problemBeanVO.getVillage().substring(0,1)))
							problemCompleteLocation.setHamlet(hamletDAO.get(new Long(problemBeanVO.getVillage().substring(1))));
						else if(IConstants.URBAN_TYPE.equals(problemBeanVO.getVillage().substring(0,1)))
						{
							problemCompleteLocation.setWard(constituencyDAO.get(new Long(problemBeanVO.getVillage().substring(1))));
						}
					}	
					
					if(problemBeanVO.getBooth() != null && !"0".equals(problemBeanVO.getBooth()))
						problemCompleteLocation.setBooth(boothDAO.get(new Long(problemBeanVO.getBooth())));
										
					/*hamlet = hamletDAO.get(new Long(problemBeanVO.getHamlet()));
					problemLocation.setHamlet(hamlet);*/
					RegionScopes problemImpactLevel = regionScopesDAO.get(problemBeanVO.getProblemImpactLevelId());
					problemLocation.setProblemImpactLevel(problemImpactLevel);
					problemLocation.setProblemImpactLevelValue(getProblemImpactValue(problemBeanVO.getProblemImpactLevelId(),problemBeanVO.getProblemImpactLevelValue()));
					problemLocation.setProblemAndProblemSource(problemAndProblemSource);
					problemLocation.setProblemCompleteLocation(problemCompleteLocation);
					problemHistory.setProblemLocation(problemLocationDAO.save(problemLocation));
					problemHistory.setProblemStatus(problemStatusDAO.get(problemBeanVO.getProblemStatusId()));
					problemHistory.setDateUpdated(getCurrentDateAndTime());
					problemHistory = problemHistoryDAO.save(problemHistory);		
					
					if(problemBeanVO.getProblemPostedBy().equals(IConstants.PARTY_ANALYST_USER) && problemBeanVO.getProbSourceId() == 4)
				    {
						CadreProblemDetails cadreProblemDetails = new CadreProblemDetails();
				    	Cadre cadre = cadreDAO.get(problemBeanVO.getCadreId());
				    	
				    	cadreProblemDetails.setCadre(cadre);
				    	cadreProblemDetails.setProblemHistory(problemHistory);
				    	cadreProblemDetails.setStatus(IConstants.CADRE_PERSONAL);
				    	cadreProblemDetails.setUpdatedDate(iDate);
				    	
				    	cadreProblemDetailsDAO.save(cadreProblemDetails);
				    }
										
					problemBeanFromDB.setProblemHistoryId(problemHistory.getProblemHistoryId());
					problemBeanFromDB.setProblemLocationId(problemHistory.getProblemLocation().getProblemLocationId());
					problemBeanFromDB.setProblem(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
					problemBeanFromDB.setDescription(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
					Date iDateOfAddNewProb = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
					Date eDateOfAddNewProb = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom();
					problemBeanFromDB.setProblemRefNum(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getReferenceNo());
					problemBeanFromDB.setReportedDate(sdf.format(iDateOfAddNewProb));
					problemBeanFromDB.setExistingFrom(sdf.format(eDateOfAddNewProb));
					
					if(problemSource != null && problemSource.getInformationSource() != null)
						problemBeanFromDB.setProbSource(problemSource.getInformationSource());	
					
					problemBeanFromDB.setProblemImpactLevelId(problemImpactLevel.getRegionScopesId());
					
					problemBeanFromDB.setProblemImpactLevelValue(getProblemImpactValue(problemImpactLevel.getRegionScopesId(),problemLocation.getProblemImpactLevelValue()));
					problemBeanFromDB.setIsApproved(problemHistory.getIsApproved());
					
				}catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
					}
					problemBeanFromDB.setExceptionEncountered(e);
					e.printStackTrace();
				}
				problemBeanVO = problemBeanFromDB;
			}
		});
		return this.problemBeanVO;
	}
	
	
	public Long getProblemImpactValue(Long impactLevelId,Long impactLevelValue){
		
		//ProblemImpactLevel impactLevel = problemImpactLevelDAO.get(impactLevelId);
		RegionScopes impactLevel = regionScopesDAO.get(impactLevelId);
		if(impactLevel != null){
			if(impactLevel.getScope().equalsIgnoreCase(IConstants.STATE) || impactLevel.getScope().equalsIgnoreCase(IConstants.DISTRICT) 
					|| impactLevel.getScope().equalsIgnoreCase(IConstants.CONSTITUENCY) || impactLevel.getScope().equalsIgnoreCase(IConstants.BOOTH)){
			   return impactLevelValue;
			}
			else if(impactLevel.getScope().equalsIgnoreCase(IConstants.MUNICIPAL_CORP_GMC)) {
				Long assemblyLocalElectionBodyId = new Long(impactLevelValue.toString().substring(1));
				List localElectionBodyIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalElectionBodyId);
				Object object = (Object)localElectionBodyIdsList.get(0);
				return (Long)object;				
			}			
			else {
				String value = impactLevelValue.toString().substring(1);
				return new Long(value);
			}
		}
		
	 return impactLevelValue;	
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
		
		List<SelectOptionVO> problemTypes = new ArrayList<SelectOptionVO>();
		if(log.isDebugEnabled()){
			log.debug("Entered Into getProblemsForUser  Method.....");
			log.debug("registrationId: " + registrationId);
			}
			List<ProblemHistory> problemHistories = problemHistoryDAO.findProblemLocationsByUserId(registrationId, statusId); 
			if(problemHistories.size() > 0){
				problemBeanVOs = getUserProblems(problemHistories);
			}		
			
			List<ProblemClassification> problemClassificationList = problemClassificationDAO.getAll();
			
			if(log.isDebugEnabled()){
				log.debug("Total Classifications:"+problemClassificationList.size());
			}
			for(ProblemClassification problemClassification:problemClassificationList){
				SelectOptionVO problemClassificationVO = new SelectOptionVO(problemClassification.getProblemClassificationId(), problemClassification.getClassification());
				problemTypes.add(problemClassificationVO);
			}
			
			Long stateId = staticDataService.getStateIdForUserByAccessValue(registrationId);
				
			problemsOfUserVO.setProblemRegionScopes(getAllDepartmentScopes(stateId));
			problemsOfUserVO.setProblemsByUser(problemBeanVOs);
			problemsOfUserVO.setProblemTypes(problemTypes);
		return problemsOfUserVO;
	}
	
	public List<SelectOptionVO> getAllDepartmentScopes(Long stateId)
	{
		List<SelectOptionVO> problemRegionScopes = new ArrayList<SelectOptionVO>();
		
		//Modified By Sai
		List<ProblemSourceScope> regionScopes = null;
		
		if(stateId == null || stateId.equals(0L))
			regionScopes = problemSourceScopeDAO.getAll();
		
		else
			regionScopes = problemSourceScopeDAO.findByStateId(stateId);		
		
		if(log.isDebugEnabled()){
			log.debug("Total Classifications:"+regionScopes.size());
		}
		for(ProblemSourceScope problemSourceScope:regionScopes){
			SelectOptionVO proSelectOptionVO = new SelectOptionVO(problemSourceScope.getProblemSourceScopeId(),	problemSourceScope.getScope());
			problemRegionScopes.add(proSelectOptionVO);
		}
		return problemRegionScopes;
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
					//problemBeanVO.setHamletId(problemLocation.getHamlet().getHamletId());
					problemBeanVO.setProblemImpactLevelId(problemLocation.getProblemImpactLevel().getRegionScopesId());
					problemBeanVO.setProblemImpactLevelValue(problemLocation.getProblemImpactLevelValue());
					problemBeanVO.setProblemHistoryId(problemHistory.getProblemHistoryId());
					problemBeanVO.setProblem(problem.getProblem());
					problemBeanVO.setDescription(problem.getDescription());				
					iDate = problem.getIdentifiedOn();
					eDate = problem.getExistingFrom();
					problemBeanVO.setReportedDate(sdf.format(iDate));
					problemBeanVO.setExistingFrom(sdf.format(eDate));
					//problemBeanVO.setHamlet(problemLocation.getHamlet().getHamletName());
					problemBeanVO.setHamlet(getLocationDetails(problemBeanVO.getProblemImpactLevelId(),problemBeanVO.getProblemImpactLevelValue()));
					problemBeanVO.setProbSource(problemAndProblemSource.getProblemSource().getInformationSource());	
					problemBean.add(problemBeanVO);
				}
				
			}
		}
		return problemBean;
 	}	
	
	public String getLocationDetails(Long problemImpactLevelId,Long problemImpactLevelValue){
		
		String result = "";
		if(problemImpactLevelId != null && !problemImpactLevelId.equals(0L)){
		//ProblemImpactLevel impactLevel = problemImpactLevelDAO.get(problemImpactLevelId);
			RegionScopes impactLevel = regionScopesDAO.get(problemImpactLevelId);
			if(impactLevel.getScope().equals(IConstants.STATE)){
				State state = stateDAO.get(problemImpactLevelValue);
				return state.getStateName();
			}else if(impactLevel.getScope().equals(IConstants.DISTRICT)){
				District district = districtDAO.get(problemImpactLevelValue);
				return district.getDistrictName();
			}else if(impactLevel.getScope().equals(IConstants.CONSTITUENCY)){
				Constituency constituency = constituencyDAO.get(problemImpactLevelValue);
				return constituency.getName();
			}else if(impactLevel.getScope().equals(IConstants.MANDAL)){
				Tehsil tehsil = tehsilDAO.get(problemImpactLevelValue);
				return tehsil.getTehsilName();
			}else if(impactLevel.getScope().equalsIgnoreCase("MUNICIPAL_CORP_GMC")){
				LocalElectionBody localElec = localElectionBodyDAO.get(problemImpactLevelValue);
				return localElec.getName();
			}else if(impactLevel.getScope().equals(IConstants.WARD)){
				Constituency constituency = constituencyDAO.get(problemImpactLevelValue);
				return constituency.getName();
			}else if(impactLevel.getScope().equalsIgnoreCase(IConstants.VILLAGE)){
				Hamlet hamlet = hamletDAO.get(problemImpactLevelValue);
				return hamlet.getHamletName();
			}
		}
		
	 return result;
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
				//problemFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());
				problemFromDB.setAddress(getLocationDetails(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(),problemHistory.getProblemLocation().getProblemImpactLevelValue()));
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
						 //problemBeanFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());
						 
						 problemBeanFromDB.setAddress(getLocationDetails(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(),problemHistory.getProblemLocation().getProblemImpactLevelValue()));
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
		if(departmentsByScope.size()>0)
			departmentsByScope.add(0, new SelectOptionVO(0l,"Select Department"));
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

	public List<SelectOptionVO> getAllProblemImpactLevel() {
		List<ProblemImpactLevel> list = problemImpactLevelDAO.getAll();
		List<SelectOptionVO> problemImpacts = new ArrayList<SelectOptionVO>();
		for(ProblemImpactLevel problemImpactLevel:list){
			problemImpacts.add(new SelectOptionVO(problemImpactLevel.getProblemImpactLevelId(),problemImpactLevel.getProblemImpactLevel()));
		}
		return problemImpacts;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IProblemManagementService#getProblemDetailsBasedOnProblemStatusForAUser(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 * Method that retrieves problems data of a particular status(like NEW,ASSIGNED,FIXED ...) posted by a user
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> getProblemDetailsBasedOnProblemStatusForAUser(
			Long userId, Long statusId, String isPushed, String isDeleted) {
		
		List<ProblemBeanVO> problemsResultList = new ArrayList<ProblemBeanVO>();
				
		try{
			List resultsList = null;
			if(statusId == null || statusId.equals(0L))
				resultsList = problemHistoryDAO.getProblemHistoryByProblemStatusForAUser(userId, statusId, isPushed, isDeleted); // results filtered based on status (like NEW,ASSIGNED,FIXED ...)
			else 
				resultsList = problemHistoryDAO.getProblemHistoryForAUser(userId, isPushed, isDeleted); // get all results irrespective of status 
			
			if(resultsList != null && resultsList.size() > 0){
				problemsResultList = getProcessedProblemResultsList(resultsList,userId,statusId);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			ProblemBeanVO resultStatus = new ProblemBeanVO();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setExceptionClass(ex.getClass().toString());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			
			problemsResultList.add(resultStatus);
		}
		
	 return problemsResultList;
	}
	
	/*
	 * Method to process posted problems results list and set details to VO 
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> getProcessedProblemResultsList(List resultsList,Long userId,Long statusId){
		
		List<ProblemBeanVO> problemsResultList = null;
		if(resultsList != null && resultsList.size() > 0){
			
			problemsResultList = new ArrayList<ProblemBeanVO>();
			Iterator resultsIterator = resultsList.listIterator();
			while(resultsIterator.hasNext()){
				
				ProblemBeanVO beanVO = new ProblemBeanVO();
				Object[] values = (Object[])resultsIterator.next();
				
				//to get problem location
				Long problemImpactLevelId    = (Long)values[0];
				Long problemImpactLevelValue = (Long)values[1];
            	String locationName = getLocationDetails(problemImpactLevelId,problemImpactLevelValue);
			 
            	Long problemId = (Long)values[5];
            	String problem = (String)values[8];
            	String isDelete = (String)values[11];
            	String isApproved = (String)values[6];
            	String description = (String)values[7];
            	
            	Date identifiedOn = (Date)values[9];
            	Date existingFrom  = (Date)values[10];
            	
            	Long problemStatusId = (Long)values[2];
            	String problemStatus  = (String)values[4];
            	Long problemLocationId = (Long)values[12];
            	
            	//setting values to VO
            	beanVO.setProblem(problem);
            	beanVO.setIsDeleted(isDelete);
            	beanVO.setProblemId(problemId);
            	beanVO.setStatus(problemStatus);
            	beanVO.setIsApproved(isApproved);
            	
            	beanVO.setDescription(description);
            	beanVO.setProblemLocation(locationName);
            	beanVO.setProblemStatusId(problemStatusId);
            	
            	beanVO.setProblemLocationId(problemLocationId);
            	beanVO.setReportedDate(identifiedOn.toString());
            	beanVO.setExistingFrom(existingFrom.toString());
            	
            	problemsResultList.add(beanVO);
			}
		}
		
	 return problemsResultList;
	}

	@SuppressWarnings("unchecked")
	public ProblemBeanVO getProblemCompleteInfo(Long problemHistoryId) {
		if(log.isDebugEnabled())
			log.info("Entered in to getProblemCompleteInfo");
		if(log.isDebugEnabled())
			log.debug("problem History Id:"+problemHistoryId);
		
		ProblemBeanVO result = new ProblemBeanVO();
		Date iDate,eDate;
		State state = null;
		District district = null;
		Constituency constituency = null;
		Tehsil tehsil = null;
		Hamlet hamlet = null;
		LocalElectionBody localBody = null;
		Constituency ward = null;
		Booth booth = null;
		try{
		List list1 = problemHistoryDAO.findProblemCompleteInfo(problemHistoryId);
		
		if(list1.size()!=0){			
			Object[] parms = (Object[])list1.get(0);
			iDate = (Date)parms[4];
			eDate = (Date)parms[8];
			result.setProblem(parms[0].toString());
			result.setDescription(parms[1].toString());
			result.setImpactLevel(parms[2].toString());
			result.setProblemImpactLevelId((Long)parms[10]);
			
			switch (result.getProblemImpactLevelId().intValue()) {
            
            case 2:  
        	{
        		state = stateDAO.get((Long)parms[3]);
				result.setProblemLocation(state.getStateName());
        		break;
        	}
            case 3:
            {
            	district = districtDAO.get((Long)parms[3]);
            	result.setProblemLocation(district.getDistrictName());
            	result.setState(district.getState().getStateName());
            	break;
            }
            case 4: {
            	constituency = constituencyDAO.get((Long)parms[3]);
				result.setProblemLocation(constituency.getName());
				if(IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency.getElectionScope().getElectionType().getElectionType()))
				{					
					result.setState(constituency.getState().getStateName());
				} else 
				{
					result.setState(constituency.getState().getStateName());
					result.setDistrict(constituency.getDistrict().getDistrictName()+"(Dt.)");
				}				
            	break;
            }
            case 5: 
            {
            	tehsil = tehsilDAO.get((Long)parms[3]);
				result.setProblemLocation(tehsil.getTehsilName());
				result = setConstDistStateTOResult(tehsil.getTehsilId(),result);
				result.setTehsil(tehsil.getTehsilName());
				break;
            }            
            case 6:
            {
            	hamlet = hamletDAO.get((Long)parms[3]);
            	result.setProblemLocation(hamlet.getHamletName());
            	result = setConstDistStateTOResult(hamlet.getTownship().getTehsil().getTehsilId(),result);
				result.setTehsil(hamlet.getTownship().getTehsil().getTehsilName());            	
            	break;
            }
            case 7:
            {
            	localBody = localElectionBodyDAO.get((Long)parms[3]);
            	result.setProblemLocation(localBody.getName()); 
            	result.setDistrict(localBody.getDistrict().getDistrictName());
            	result.setState(localBody.getDistrict().getState().getStateName());
            	break;
            }
            case 8:
            {
            	ward = constituencyDAO.get((Long)parms[3]);
            	result.setProblemLocation(ward.getName());
            	result.setLocalBody(ward.getLocalElectionBody().getName());
            	result.setDistrict(ward.getLocalElectionBody().getDistrict().getDistrictName());
            	result.setState(ward.getLocalElectionBody().getDistrict().getState().getStateName());
            	break;
            }
            case 9:
            {
            	booth = boothDAO.get((Long)parms[3]);
            	if(booth.getTehsil()!= null)
            	{
            		result.setTehsil(booth.getTehsil().getTehsilName());
            		result = setConstDistStateTOResult(booth.getTehsil().getTehsilId(),result);           		
            	}else if(booth.getLocalBody() != null)
            	{
            		if(booth.getBoothLocalBodyWard() != null)
            			result.setWard(booth.getBoothLocalBodyWard().getLocalBodyWard().getName());
            		result.setLocalBody(booth.getLocalBody().getName());
            		result.setDistrict(booth.getLocalBody().getDistrict().getDistrictName());
                	result.setState(booth.getLocalBody().getDistrict().getState().getStateName());
                	
            	}
            	result.setProblemLocation(booth.getPartName()+booth.getLocation());
            	break;
            }
            default: System.out.println("Invalid Scope.");break;
        }			
		result.setPostedDate(sdf.format(iDate));
		result.setName(parms[5].toString());
		result.setProblemId((Long)parms[6]);
		result.setProblemHistoryId((Long)parms[7]);
		result.setExistingFrom(sdf.format(eDate));
		result.setStatus(parms[9].toString());
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			result.setExceptionMsg("Exception raised when retriecing problem complete info");
			result.setExceptionEncountered(e);
		}
		
		return result;
	}
	
	private ProblemBeanVO setConstDistStateTOResult(Long tehsilId, ProblemBeanVO result)
	{
		List stateDistConstMandal = delimitationConstituencyMandalDAO.getStateDistConstituencyMandalByMandalID(tehsilId);
		Object[] objVO = (Object[]) stateDistConstMandal.get(0);
		result.setState(objVO[1].toString());
		result.setDistrict(objVO[3].toString());
		result.setConstituency(objVO[5].toString());
		
		return result;
	}

	
	/**
	 * Method To Get Problems Posted For A User Based On Problem Status And Count (represents how many problems based on startIndex and endIndex)
	 * @author Sai Krishna
	 * 
	 * @param UserId
	 * @param ProblemStatusId
	 * @params startIndex,maxIndex
	 * @return ProblemsOfUserVO
	 */
	public ProblemsOfUserVO getProblemsForAUserBasedOnStatusAndCount(
			Long userId, Long statusId, Integer startIndex, Integer maxIndex) {
		
		if(log.isDebugEnabled())
			log.debug(" Entered Into Method To Get Problems For An User .. ");
		
		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			
			List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();
			
			//DAO call to get ProblemHistory objects
			List<ProblemHistory> problemHistoryList = problemHistoryDAO.getRecentPostedProblemsForAUserByCount(userId, statusId, startIndex, maxIndex);
			if(problemHistoryList != null && problemHistoryList.size() > 0)
				problemBeanVOLst = getUserProblems(problemHistoryList);
			
			problemsOfUserVO.setProblemsByUser(problemBeanVOLst);
			
			//DAO call to get total Results Count
			Long totalProblemsCount = problemHistoryDAO.getRecentPostedProblemsCountForAUserByProblemStatus(userId, statusId);
			problemsOfUserVO.setTotalResultsCount(totalProblemsCount);
				
				
		}catch(Exception ex){
			
			log.error("Exception Raised While Retrieving Problems For A User :" + ex);
			ex.printStackTrace();
			
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setExceptionMsg(ex.getMessage());
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			
			problemsOfUserVO.setResultStatus(resultStatus);
		}
		
		
	 return problemsOfUserVO;
	}

	/**
	 * Method Get Problems Problems Overall Information for a user to build chart
	 * @param userId
	 * @param problemStatusId
	 * @param startIndex
	 * @param maxIndex
	 * @return ProblemManagementChartVO
	 */
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(
			Long userId, Long statusId, Integer startIndex, Integer maxIndex,String type) {
		
		if(log.isDebugEnabled())
			log.debug("Started Getting User Problems Overall Information ..");
		
		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		List<SelectOptionVO> chartLegends = new ArrayList<SelectOptionVO>();
		ResultStatus rs = new ResultStatus();
		
		try{
			
			List problemsData = null;
			Long totalProblems = 0L;
			String chartDesc = "";
			
			if(type.equals(IConstants.PROBLEMS_BY_DATE)){
				problemsData = problemHistoryDAO.getProblemsPostedForAUserInBetweenDates(userId, statusId, startIndex, maxIndex);
				totalProblems = problemHistoryDAO.getProblemsCountPostedByAUserInBetweenDates(userId, statusId);
			}
			else if(type.equals(IConstants.PROBLEMS_BY_MONTH)){
				problemsData = problemHistoryDAO.getProblemsPostedForAUserInBetweenMonths(userId, statusId, startIndex, maxIndex);
				totalProblems = problemHistoryDAO.getProblemsCountPostedByAUserInBetweenMonths(userId, statusId);
			}
			
						
			if(problemsData != null && problemsData.size() > 0){
				
				//Getting problem Status details
				ProblemStatus problemStatus = problemStatusDAO.get(statusId);
				chartLegends.add(new SelectOptionVO(problemStatus.getProblemStatusId(),problemStatus.getStatus()));
				
				//chart basic details
				String chartTitle = "Problem Details Of ".concat(problemStatus.getStatus()).concat(" Category");
								
				//set to VO
				problemManagementChartVO.setChartLegends(chartLegends);
				problemManagementChartVO.setChartTitle(chartTitle);
				problemManagementChartVO.setChartDesc(chartDesc);
				problemManagementChartVO.setStartIndex(new Long(startIndex));
				problemManagementChartVO.setMaxIndex(new Long(maxIndex));
				problemManagementChartVO.setTotalCount(new Long(totalProblems));
				
				//get problems complete data
				List<ProblemManagementChartDataVO> chartDataVO = getProcessedProblemsData(problemsData,type);
				problemManagementChartVO.setChartDataVO(chartDataVO);
				
			}
				
								
			
		}catch(Exception ex){
			
			log.error("Exception Raised while Getting problems for a user :" + ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);
			
			problemManagementChartVO.setResultStatus(rs);
		}
		
	 return problemManagementChartVO;
	}
	
	/**
	 * Method To Process the problems data 
	 * 
	 * @param problemsData
	 * @return List<ProblemManagementChartDataVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ProblemManagementChartDataVO> getProcessedProblemsData(List problemsData,String type) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug(" Processing Problems Data ..");
		
		List<ProblemManagementChartDataVO> chartDataVO = new ArrayList<ProblemManagementChartDataVO>();
		
		Iterator lstItr = problemsData.listIterator();
		while(lstItr.hasNext()){
			
			ProblemManagementChartDataVO chartVO = new ProblemManagementChartDataVO();
						
			Object[] values = (Object[])lstItr.next();
			Long problemsCount = (Long)values[0];
			
			chartVO.setProblemsCount(new BigDecimal(problemsCount));
						
			//if problems are classified by date
			if(type.equals(IConstants.PROBLEMS_BY_DATE)){
				
				Date problemDate = (Date)values[1];
				chartVO.setChartLabel(problemDate.toString());
			}
			
			else if(type.equals(IConstants.PROBLEMS_BY_MONTH)){
				
                Integer postedMonth    = (Integer)values[1];
                Integer postedYear     = (Integer)values[2];                
				String month = new DateFormatSymbols().getMonths()[postedMonth-1];
				month = month + "-" + postedYear.toString();
				
				chartVO.setChartLabel(month);
			}
			
			//set to list
			chartDataVO.add(chartVO);
		}
		
	 return chartDataVO;
	}

	/**
	 * Method Get Problems Of All Categorys Overall Information for a user to build chart
	 * @param userId
	 * @param problemStatusId
	 * @param startIndex
	 * @param maxIndex
	 * @return ProblemManagementChartVO
	 */
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(
			Long userId, Integer startIndex, Integer maxIndex, String type) {
		
		if(log.isDebugEnabled())
			log.debug("Started Getting User Problems Overall Information ..");
		
		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		Map<String,Map<String,BigDecimal>> problemsData = new HashMap<String,Map<String,BigDecimal>>();
		List<SelectOptionVO> chartLegends = new ArrayList<SelectOptionVO>();
		ResultStatus rs = new ResultStatus();
		
		try{
			
			chartLegends = getAllDefaultProblemStatus();
			
			List<SelectOptionVO> problemCategorys = new ArrayList<SelectOptionVO>();
			problemCategorys.addAll(chartLegends);
			//chart basic details
			String chartTitle = "Problem Details Of All Categories";
			String chartDesc = "";
			List totalProblemsCount = null;
							
			//set to VO
			problemManagementChartVO.setChartLegends(chartLegends);
			problemManagementChartVO.setChartTitle(chartTitle);
			problemManagementChartVO.setChartDesc(chartDesc);
			problemManagementChartVO.setStartIndex(new Long(startIndex));
			problemManagementChartVO.setMaxIndex(new Long(maxIndex));
						
			List problemsDataList = null;
			
			//DAO call TO get Problems Data for all categorys
			if(type.equals(IConstants.PROBLEMS_BY_DATE)){
				totalProblemsCount = problemHistoryDAO.getProblemsCountPostedByAUserInBetweenDates(userId);
			}
			else if(type.equals(IConstants.PROBLEMS_BY_MONTH)){
				totalProblemsCount = problemHistoryDAO.getProblemsCountPostedByAUserInBetweenMonths(userId);
			}
			
			problemManagementChartVO.setTotalCount(new Long(totalProblemsCount.size()));
			
			for(SelectOptionVO problemCategory:chartLegends){
				
				ProblemManagementChartVO problemsChartVO = getProblemsDataForAUserToBuildChart(userId, problemCategory.getId(), startIndex, maxIndex, type);
				if(problemsChartVO != null && problemsChartVO.getChartDataVO() != null)
					getProblemsDataAssignedToMap(problemsChartVO.getChartDataVO(),problemCategory.getName(),problemsData,problemCategorys);
			}
			
			//Set Map Data To VO
			List<ProblemManagementChartDataVO> chartDataVO = processProblemDetailsMapAndSetToVO(problemsData);
			problemManagementChartVO.setChartDataVO(chartDataVO);
		
			
		}catch(Exception ex){
			
			log.error("Exception Raised while Getting problems for a user :" + ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);
			
			problemManagementChartVO.setResultStatus(rs);
		}
		
	 return problemManagementChartVO;
	}
	
	private void getProblemsDataAssignedToMap(List<ProblemManagementChartDataVO> chartDataVO,String prblmCategory,Map<String,Map<String,BigDecimal>> problemsData,List<SelectOptionVO> problemCategorys) throws Exception{
		
		for(ProblemManagementChartDataVO chartData:chartDataVO){
			
			if(problemsData.isEmpty() || !problemsData.containsKey(chartData.getChartLabel())){
				problemsData.put(chartData.getChartLabel(), setMapDefaultValues(problemCategorys));
			}
			
			Map<String,BigDecimal> chartProblemsMap = problemsData.get(chartData.getChartLabel());
			chartProblemsMap.put(prblmCategory, chartData.getProblemsCount());
			
			problemsData.put(chartData.getChartLabel(), chartProblemsMap);
			
		}
		
	}
	
	/**
	 * Method To Process The Map And Set To VO 
	 * @param problemsData
	 * @return List<ProblemManagementChartDataVO>
	 */
	private List<ProblemManagementChartDataVO> processProblemDetailsMapAndSetToVO(Map<String,Map<String,BigDecimal>> problemsData) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug(" Processing Map And Setting Problems To VO ..");
		
		List<ProblemManagementChartDataVO> problemsDataList = new ArrayList<ProblemManagementChartDataVO>();
		if(!problemsData.isEmpty()){
			
			Set<String> mapKeys = problemsData.keySet();
			for(String problemDate:mapKeys){
				
				ProblemManagementChartDataVO dataVO = new ProblemManagementChartDataVO();
				
				dataVO.setChartLabel(problemDate);
				Map<String,BigDecimal> data = problemsData.get(problemDate);
				List<BigDecimal> chartValues = new ArrayList<BigDecimal>();
				
				Set<String> chartKeys = data.keySet();
								
				for(String val:chartKeys){
					BigDecimal count = data.get(val);
					chartValues.add(count);
				}
				
				dataVO.setChartValues(chartValues);				
				
				problemsDataList.add(dataVO);
			} 
		}
		
	 return problemsDataList;
	}
		
	/**
	 * 
	 * @param chartLegends
	 * @return Map<String,BigDecimal>
	 */
	private Map<String,BigDecimal> setMapDefaultValues(List<SelectOptionVO> chartLegends){
		
		Map<String,BigDecimal> defaultMap = new HashMap<String,BigDecimal>();
		
		for(SelectOptionVO category:chartLegends){
			defaultMap.put(category.getName(), new BigDecimal(0));
		}
		
     return defaultMap;
	}
	
	/**
	 * Method to get All Problem Status Information
	 * @return List<SelectOptionVO>
	 */
	public List<SelectOptionVO> getAllDefaultProblemStatus(){
		
		List<SelectOptionVO> problemStatusVO = new ArrayList<SelectOptionVO>();
		
		List<ProblemStatus> problemStatus = problemStatusDAO.getAll();
		if(problemStatus != null && problemStatus.size() > 0){
			
			for(ProblemStatus pbStat:problemStatus){
				
				problemStatusVO.add(new SelectOptionVO(pbStat.getProblemStatusId(),pbStat.getStatus()));
			}
		}
		
	 return problemStatusVO;
	}

	/**
	 * Method To Get Overall Problems Information In Different Stages Of Life Cycle
	 * @param userId
	 * @return ProblemManagementChartVO
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(
			Long userId) {
		
		if(log.isDebugEnabled())
			log.debug("Entered Into Service Method TO Get Overall Problems Count In Different Stages ..");
		
		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		ResultStatus rs = new ResultStatus();
		
		try{
			
			Map<String,Map<Long,BigDecimal>> problemDetailsMap = new HashMap<String,Map<Long,BigDecimal>>();
			Map<Long,String> problemInfSources = new HashMap<Long,String>();
			
			//DAO call to get overall problems posted by an user
			List problemsList = problemHistoryDAO.getProblemsCountPostedByUserInDifferentLifeCycleStages(userId);
			if(problemsList != null && problemsList.size() > 0){
				getProblemsAssignedToMap(problemsList,problemDetailsMap,problemInfSources);
				problemManagementChartVO = processTheMapAndSetResultsToVO(userId,problemDetailsMap,problemInfSources);
			}
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			return problemManagementChartVO;
		}
				
	 return problemManagementChartVO;
	}
	
	/**
	 * Method To set Problem Details To Map
	 * @param problemsList
	 * @param problemDetailsMap Map<String,Map<Long,BigDecimal>>
	 */
	@SuppressWarnings("unchecked")
	private void getProblemsAssignedToMap(List problemsList,Map<String,Map<Long,BigDecimal>> problemDetailsMap,Map<Long,String> problemInfSources) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Setting Problems To Map ..");
		
		Iterator listItr = problemsList.listIterator();
		while(listItr.hasNext()){
			
			Object[] values = (Object[])listItr.next();
			
			Long problemsCount     = (Long)values[0];
			Long problemStatusId   = (Long)values[1];
			String problemStatus   = (String)values[2];
			Long problemSourceId   = (Long)values[3];
			String problemSource   = (String)values[4];
			
			
			
			//if map is empty of result for particular source is N/A
			if(problemDetailsMap.isEmpty() || !problemDetailsMap.containsKey(problemStatus)){
				Map<Long,BigDecimal> problemSources = getDefaultInformationSources(problemInfSources);
				problemDetailsMap.put(problemStatus, problemSources);
			}
			
			//Set To Map
			Map<Long,BigDecimal> problemSources = problemDetailsMap.get(problemStatus);
			
			BigDecimal pblmsCount = problemSources.get(problemSourceId);
			pblmsCount = new BigDecimal(problemsCount);
			problemSources.put(problemSourceId, pblmsCount);
			problemDetailsMap.put(problemStatus, problemSources);
						
			//if map is empty of result for particular source is N/A
			/*if(problemInfSources.isEmpty() || !problemInfSources.containsKey(problemSourceId))
				problemInfSources.put(problemSourceId, problemSource);*/
			
		}
	}
	
	/**
	 * 
	 * @return Map<Long,BigDecimal>
	 */
	@SuppressWarnings("unchecked")
	private Map<Long,BigDecimal> getDefaultInformationSources(Map<Long,String> problemInfSources){
		
		Map<Long,BigDecimal> informationSourcesMap = new HashMap<Long,BigDecimal>(); 
		
		List inforSource = informationSourceDAO.getAllInformationSourceDetails();
		if(inforSource != null && inforSource.size() > 0){
			
			Iterator lstItr = inforSource.listIterator();
			while(lstItr.hasNext()){
				
				Object[] values = (Object[])lstItr.next();
				
				Long infoSourceId = (Long)values[0];
				String infoSource = (String)values[1];
				
				informationSourcesMap.put(infoSourceId, new BigDecimal(0));
				problemInfSources.put(infoSourceId, infoSource);
				log.info("Available Information Source :" + infoSource);
			}
		}
		
	 return informationSourcesMap;
	}
	
	/**
	 * 
	 * @param problemDetailsMap
	 * @param problemInfSources
	 */
	private ProblemManagementChartVO processTheMapAndSetResultsToVO(Long userId,Map<String,Map<Long,BigDecimal>> problemDetailsMap,Map<Long,String> problemInfSources) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Processing The Map ..");
		
		ProblemManagementChartVO chartMainVO = new ProblemManagementChartVO();
		List<ProblemManagementChartDataVO> chartDataVO = new ArrayList<ProblemManagementChartDataVO>();
				
		Set<String> problemClassifications = problemDetailsMap.keySet();
		
		//Process different Problems Life Cycle Stages
		for(String prbStatus:problemClassifications){
			
			ProblemManagementChartDataVO chartVO = new ProblemManagementChartDataVO();
			chartVO.setChartLabel(prbStatus);			
			
			BigDecimal totProbsCount = new BigDecimal(0);
			Integer totLegendsCount = 0;
			
			Map<Long,BigDecimal> problemsMap = problemDetailsMap.get(prbStatus);
						
			Set<Long> chartLegends = problemsMap.keySet();
			totLegendsCount = chartLegends.size();
			
			List<BigDecimal> problemsCountLst = new ArrayList<BigDecimal>();
			
			//Process different scopes
			for(Long prblmSource:chartLegends){
				
				totProbsCount = new BigDecimal(totProbsCount.longValue() + problemsMap.get(prblmSource).longValue());
				problemsCountLst.add(problemsMap.get(prblmSource));
				
			}
			
			chartVO.setProblemsCount(totProbsCount);
			chartVO.setAvgCount(new BigDecimal(totProbsCount.longValue()/totLegendsCount).setScale(2, BigDecimal.ROUND_HALF_UP));
			chartVO.setChartValues(problemsCountLst);
			
			chartDataVO.add(chartVO);
		}
		
		chartMainVO.setChartDataVO(chartDataVO);
		chartMainVO.setChartLegends(getChartLegendsFromSet(problemInfSources));
		
		Registration user =  registrationDAO.get(userId);
		String userName = "";
		if(user.getFirstName() != null)
			userName = userName + user.getFirstName();
		if(user.getLastName() != null)
			userName = userName +  " " + user.getLastName();
		chartMainVO.setChartTitle("Overall Problems Information Posted By " + userName);
			
	 return chartMainVO;
	}
	
	/**
	 * Method To Set Chart Legends To List<SelectOptionVO>
	 * @param chartLegends
	 * @return
	 */
	private List<SelectOptionVO> getChartLegendsFromSet(Map<Long,String> chartLegends){
		
		List<SelectOptionVO> selectOptions = new ArrayList<SelectOptionVO>();
		
		Set<Long> keys = chartLegends.keySet();
		for(Long legend:keys){
			selectOptions.add(new SelectOptionVO(legend,chartLegends.get(legend)));
		}
		
	 return selectOptions;
	}

	/**
	 * Method To Get Different User Problems Basic details
	 * @author Sai Krishna
	 * @param userId
	 * @return ProblemsOfUserVO
	 */
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByDate(Long userId,Integer startIndex,Integer maxResults) {
		
		
		if(log.isDebugEnabled())
			log.debug("Entered Into Service Method To Get Different User Problems .." );
		
		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus rs = new ResultStatus();
		
		try{
			
			if(userId != null && !userId.equals(0L)){
				
				//Get Problems Overall Info
				List<ProblemHistory> problemHistorysList = problemHistoryDAO.getProblemsPostedByUserInDifferentLifeCycleStagesByDate(userId, startIndex, maxResults);
				if(problemHistorysList != null && problemHistorysList.size() > 0){
					List<ProblemBeanVO> problemsByUser = setUserProblemsToVO(problemHistorysList);
					problemsOfUserVO.setProblemsByUser(problemsByUser);
				}
					
				//Get Total Problems Count
				Long totalProblemsCount = problemHistoryDAO.getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(userId);
				problemsOfUserVO.setTotalResultsCount(totalProblemsCount);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			log.error("Exception Raised :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			problemsOfUserVO.setResultStatus(rs);
			return problemsOfUserVO;
		}
		
	 return problemsOfUserVO;
	}
	
	/**
	 * Set Data To VO
	 * @param problemHistorysList
	 * @return
	 */
	public List<ProblemBeanVO> setUserProblemsToVO(List<ProblemHistory> problemHistorysList) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Setting Data To VO ..");
		
		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();
		
		for(ProblemHistory pblmHistory:problemHistorysList){
			
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			
			Problem problem = pblmHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
			ProblemAndProblemSource pblmAndPblmSource = pblmHistory.getProblemLocation().getProblemAndProblemSource();
			ProblemCompleteLocation problemCompleteLocation = pblmHistory.getProblemLocation().getProblemCompleteLocation();
			
			//Problem Basic Details
			problemBeanVO.setProblemHistoryId(pblmHistory.getProblemHistoryId());
			problemBeanVO.setProblem(problem.getProblem());
			problemBeanVO.setDescription(problem.getDescription());
			problemBeanVO.setExistingFrom(problem.getExistingFrom().toString());
			problemBeanVO.setReportedDate(pblmHistory.getDateUpdated().toString());
			problemBeanVO.setProblemStatus(pblmHistory.getProblemStatus().getStatus());
			
			//Problem Source Details
			problemBeanVO.setProbSource(pblmAndPblmSource.getProblemSource().getInformationSource());
			
			//Problem Location Details
			problemBeanVO.setProblemLocation(getProblemLocationString(problemCompleteLocation));
			
			problemBeanVOLst.add(problemBeanVO);
		}
		
	 return problemBeanVOLst;
	}
	
	private String getProblemLocationString(ProblemCompleteLocation problemCompleteLocation){
		
		String problemLocation = "";
		
		if(problemCompleteLocation.getState() != null)
			problemLocation = problemLocation + problemCompleteLocation.getState().getStateName();
		
		if(problemCompleteLocation.getDistrict() != null)
			problemLocation = problemLocation + ", "+problemCompleteLocation.getDistrict().getDistrictName() ;
		
		if(problemCompleteLocation.getTehsil() != null){
			
			problemLocation = problemLocation +" ,"+ problemCompleteLocation.getTehsil().getTehsilName() ;
			if(problemCompleteLocation.getTownship() != null)
				problemLocation = problemLocation +" ,"+problemCompleteLocation.getTownship().getTownshipName() ;
			if(problemCompleteLocation.getHamlet() != null)
				problemLocation = problemLocation +" ,"+problemCompleteLocation.getHamlet().getHamletName();
			
		}else{
		
			if(problemCompleteLocation.getLocalElectionBody() != null)
				problemLocation = problemLocation + " ," + problemCompleteLocation.getLocalElectionBody().getName();
			if(problemCompleteLocation.getWard() != null)
				problemLocation = problemLocation +" ,"+problemCompleteLocation.getWard().getName();
			if(problemCompleteLocation.getBooth() != null)
				problemLocation = problemLocation +" ,"+problemCompleteLocation.getBooth().getPartNo() + " Booth";
		}
		
	 return problemLocation;
	}
	
	/**
	 * Method To Get User Problems Based On Filters (status,date e.t.c ..)
	 * @author Sai Krishna
	 * @param userId
	 * @param statusId
	 * @param startDate
	 * @param endDate
	 * @param startIndex
	 * @param maxResults
	 * @return ProblemsOfUserVO
	 */
	@SuppressWarnings("unchecked")
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByFilters(Long userId,Long statusId,Date startDate,Date endDate,Integer startIndex,Integer maxResults){
		
		if(log.isDebugEnabled())
			log.debug("Started To Get User Problems Based On Input filters ..");
		
		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus rs = new ResultStatus();
		
		try{
			
			//Get Problems Overall Info
			List<ProblemHistory> problemHistorysList = problemHistoryDAO.getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(userId,statusId, startDate,endDate,startIndex, maxResults);
			if(problemHistorysList != null && problemHistorysList.size() > 0){
				List<ProblemBeanVO> problemsByUser = setUserProblemsToVO(problemHistorysList);
				problemsOfUserVO.setProblemsByUser(problemsByUser);
			}
				
			//Get Total Problems Count
			List totalProblemsCount = problemHistoryDAO.getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(userId,statusId,startDate,endDate);
			
			//Set total problems count to VO
			if(totalProblemsCount != null && totalProblemsCount.size() > 0){
				
				Object values = (Object)totalProblemsCount.get(0);
				problemsOfUserVO.setTotalResultsCount((Long)values);
			}
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			log.error("Exception Raised While Retrieving User Problems :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			problemsOfUserVO.setResultStatus(rs);
			
		 return problemsOfUserVO;
		}
		
	 return problemsOfUserVO;
	}

	/**
	 * Method To Get A Problem Complete Information 
	 * @param problemHistoryId
	 * @return ProblemBeanVO
	 * 
	 * @author sai krishna
	 */
	public ProblemCompleteDetailsVO getProblemCompleteInformationByProblemHistory(
			Long problemHistoryId) {
		
		if(log.isDebugEnabled())
			log.debug("Method To Get A Problem Complete Information ..");
		
		ProblemCompleteDetailsVO problemCompleteInfo = new ProblemCompleteDetailsVO();
		 List<ProblemStatusDataVO> problemLifeCycleData = new ArrayList<ProblemStatusDataVO>();
		
		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();	
		ResultStatus rs = new ResultStatus();
		
		try{
			
			log.info("DAO Call To Get History Object ..");
			List<ProblemHistory> pbHistory = problemHistoryDAO.getProblemHistoryBasedOnId(problemHistoryId);
			ProblemHistory problemHistory = null;
			Long problemId = 0L;
			
			//Problem basic details
			if(pbHistory != null && pbHistory.size() > 0){
				
				problemHistory = pbHistory.get(0);
				
				problemId = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblemId();
				problemBeanVOLst = setUserProblemsToVO(pbHistory);
				problemCompleteInfo.setProblemBasicDetails(problemBeanVOLst.get(0));
				
				problemLifeCycleData.add(getLifeCycleDataForNewProblem(problemHistory));
				
				//set problem status label
				problemCompleteInfo.setProblemStatusLabel(getProblemStatusLabel(problemHistory.getProblemStatus().getStatus()));
			}
			
			//Set Problem Life Cycle Data in different stages
			List<AssignedProblemProgress> assignedProblemProgress = assignedProblemProgressDAO.getProblemDifferentStagesByByProblemId(problemId);
			
			//this problem gone thru different stages
			if(assignedProblemProgress != null && assignedProblemProgress.size() > 0){
				for(AssignedProblemProgress probProgress:assignedProblemProgress){
					problemLifeCycleData.add(getLifeCycleDataForNewProblem(probProgress));
				}
			}
			
			problemCompleteInfo.setProblemLifeCycleData(problemLifeCycleData);
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);
			
			problemCompleteInfo.setResultStatus(rs);
			
		 return problemCompleteInfo;
		}
		
	 return problemCompleteInfo;
	}
	
	/**
	 * Getting New Problem Details And Set To VO
	 * @param problemHistory
	 * @return
	 */
	private ProblemStatusDataVO getLifeCycleDataForNewProblem(ProblemHistory problemHistory) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Stated Executing Method To Set New Problem Data ..");
		
		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		
		if(problemHistory != null){
			
			problemStatusData.setProblemStatus("NEW");
			problemStatusData.setUpdatedDate(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn().toString());
			problemStatusData.setCadre("Not Yet Assigned");
			problemStatusData.setDepartment("Not Yet Assigned");
			problemStatusData.setDepartmentOrganisation("Not Yet Assigned");
			problemStatusData.setDeptLocation("Not Yet Assigned");
			problemStatusData.setComments("--");
			problemStatusData.setOfficial("N/A");
			problemStatusData.setOfficialName("N/A");
			problemStatusData.setPhoneNo("N/A");
		}
		
	 return problemStatusData;
	}
	
	/**
	 * Getting New Problem Details And Set To VO
	 * @param problemHistory
	 * @return
	 */
	private ProblemStatusDataVO getLifeCycleDataForNewProblem(AssignedProblemProgress problemProgress) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Stated Executing Method To Set New Problem Data ..");
		
		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		ProblemHistory problemHistory = problemProgress.getProblemHistory();
		
		if(problemProgress != null){
			
			//set Department
			if(problemProgress.getDepartmentOrganisation() != null)
				problemStatusData.setDepartmentOrganisation(problemProgress.getDepartmentOrganisation().getOrganisationName());
			else
				problemStatusData.setDepartmentOrganisation("--");
			
			//set Department Category
			if(problemProgress.getProblemSourceScopeConcernedDepartment() != null)
				problemStatusData.setDepartment(problemProgress.getProblemSourceScopeConcernedDepartment().getDepartment());
			else
				problemStatusData.setDepartment("--");
			
			//set cadre
			if(problemProgress.getCadre() != null)
				problemStatusData.setCadre(problemProgress.getCadre().getFirstName()+ " " +problemProgress.getCadre().getLastName());
			else
				problemStatusData.setCadre("--");
			
			//comments
			problemStatusData.setComments(problemHistory.getComments());
			problemStatusData.setProblemStatus(problemHistory.getProblemStatus().getStatus());
			problemStatusData.setUpdatedDate(problemHistory.getDateUpdated().toString());
			if(problemProgress.getConcernedPersonName() != null)
				problemStatusData.setOfficialName(problemProgress.getConcernedPersonName());
			else
				problemStatusData.setOfficialName("--");
			if(problemProgress.getContactNo() != null)
				problemStatusData.setPhoneNo(problemProgress.getContactNo());
			else
				problemStatusData.setPhoneNo("--");
			if(problemProgress.getDesignation() != null)
				problemStatusData.setOfficial(problemProgress.getDesignation());
			else
				problemStatusData.setOfficial("--");
			
			String deptLoc = "";
			if(problemProgress.getDepartmentLocation() != null)
				deptLoc = getProblemLocationString(problemProgress.getDepartmentLocation());
			problemStatusData.setDeptLocation(deptLoc);
		}
		
	 return problemStatusData;
	}
	/**
	 * Methos To Set Problem Status Label
	 * @param presentStatus
	 * @return String
	 */
	private String getProblemStatusLabel(String presentStatus) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered To Set Status Label ..");
		
		String statusLabel = "MOVE TO ";
		
		if(presentStatus.equalsIgnoreCase(IConstants.NEW)){
			statusLabel = statusLabel + IConstants.PROGRESS;
		}else if(presentStatus.equalsIgnoreCase(IConstants.PROGRESS)){
			statusLabel = statusLabel + IConstants.PENDING + " / " + IConstants.FIXED;
		}else if(presentStatus.equalsIgnoreCase(IConstants.PENDING)){
			statusLabel = statusLabel + IConstants.PROGRESS;
		}
		
	 return statusLabel;
	}
	
	/**
	 * Method That Returns All Department Scopes Related To A User Based On State
	 * @param userId
	 * @return List<SelectOptionVO>
	 */
	public List<SelectOptionVO> getDepartmentScopesForAnUser(Long userId) {
		
		
		List<SelectOptionVO> departmentScopes = null;
		
		if(log.isDebugEnabled())
			log.debug("Getting Department Scopes For A User ..");
		
		Long stateId = staticDataService.getStateIdForUserByAccessValue(userId);
		departmentScopes = getAllDepartmentScopes(stateId);
		
	 return departmentScopes;
	}
	
	/**
	 * Method To Get Default Problem Classifications
	 */
	public List<SelectOptionVO> getProblemsDefaultClassifications(){
		
		List<SelectOptionVO> classificationsList = new ArrayList<SelectOptionVO>();
		
		try{
		List<ProblemClassification> classificationsLst = problemClassificationDAO.getAll();
		
		if(classificationsLst != null && classificationsLst.size() > 0){
			
			for(ProblemClassification clssifictn:classificationsLst){
				classificationsList.add(new SelectOptionVO(clssifictn.getProblemClassificationId(),clssifictn.getClassification()));
			}
		}
		}catch(Exception ex){
			log.error("Exception Raised :" + ex);
		}
		
	 return classificationsList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentCategorysForAProblemScope(
			Long scopeId) {
		
		List<SelectOptionVO> deptCategorysLst = new ArrayList<SelectOptionVO>();
		
		try{
			
			List probClassificationsLst = departmentOrganisationDAO.getDepartmentCategorysBasedOnProblemResolvingRegionScope(scopeId);
			if(probClassificationsLst != null && probClassificationsLst.size() > 0){
				
				Iterator lstItr = probClassificationsLst.listIterator();
				while(lstItr.hasNext()){
					
					Object[] values = (Object[])lstItr.next();
					
					deptCategorysLst.add(new SelectOptionVO((Long)values[0],(String)values[1]));
				}
			}
			
		}catch(Exception ex){
			log.error("Exception Raised :" + ex);
		}
			
	 return deptCategorysLst;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentOrganisationsForADeptOfScope(
			Long deptId, Long scopeId) {
		
		List<SelectOptionVO> deptOrgsLst = new ArrayList<SelectOptionVO>();
		
        try{
			
			List probClassificationsLst = departmentOrganisationDAO.getDepartmentOrganisationsForADepartmentByScope(deptId, scopeId);
			if(probClassificationsLst != null && probClassificationsLst.size() > 0){
				
				Iterator lstItr = probClassificationsLst.listIterator();
				while(lstItr.hasNext()){
					
					Object[] values = (Object[])lstItr.next();
					
					deptOrgsLst.add(new SelectOptionVO((Long)values[0],(String)values[1]));
				}
			}
			
		}catch(Exception ex){
			log.error("Exception Raised :" + ex);
		}
		
	 return deptOrgsLst;
	}

	/**
	 * Method that changes state of a problem of an user with provided inputs
	 * 
	 * @param problemHistoryId
	 * @param classificationId
	 * @param scopeId
	 * @param departmentId
	 * @param cadreId
	 * @param problemDeptLocId
	 * @param comments
	 * 
	 * @return ResultStatus
	 * 
	 * @author sai krishna
	 */
	public ResultStatus changePostedProblemStatusForAnUser(
			final Long problemHistoryId, final Long classificationId, final Long scopeId,
			final Long departmentId, final Long cadreId, final Long problemDeptLocId,
			final String comments,final String statusToChange) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Change Problem Status ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
			
				ResultStatus rs = new ResultStatus();
				
				try{
					
					ProblemHistory pbHistory    = problemHistoryDAO.get(problemHistoryId);
					ProblemStatus presentStatus = pbHistory.getProblemStatus();
					ProblemLocation presentProblemLocation = pbHistory.getProblemLocation();
					
					List<AssignedProblemProgress> assignedProbProgress = assignedProblemProgressDAO.getAssignedProblemProgressbyHistoryId(problemHistoryId);
									
					
					//creating reference to different data objects to save
					Cadre cadre                                 = null;
					ProblemSourceScope problemScope             = null;
					DepartmentOrganisation department           = null;
					ProblemClassification problemClassification = null;
					AssignedProblemProgress asignedProbProgress = null;
					ProblemSourceScopeConcernedDepartment pbConDept = null;
					ProblemCompleteLocation departmentLocation = null;
					
					
					if(assignedProbProgress != null && assignedProbProgress.size() > 0)
						asignedProbProgress = assignedProbProgress.get(0);
					
					//initializing different data objects to save
					if(classificationId != null && !classificationId.equals(0L)){
						problemClassification = problemClassificationDAO.get(classificationId);
					}else{
						problemClassification = presentProblemLocation.getProblemClassification();
					}
					if(scopeId != null && !scopeId.equals(0L)){
						problemScope = problemSourceScopeDAO.get(scopeId);
					}else{
						problemScope = pbHistory.getProblemSourceScope();
					}
					if(departmentId != null && !departmentId.equals(0L)){
						
						department = departmentOrganisationDAO.get(departmentId);
						pbConDept = department.getProblemDepartmentCategory();
						
					}else{
						department = asignedProbProgress.getDepartmentOrganisation();
						pbConDept = asignedProbProgress.getProblemSourceScopeConcernedDepartment();
					}
					if(cadreId != null && !cadreId.equals(0L)){
						cadre = cadreDAO.get(cadreId);			
					}else{
						if(asignedProbProgress != null)
						cadre = asignedProbProgress.getCadre();
					}
					
					if(problemDeptLocId != null && !problemDeptLocId.equals(0L)){
						
						departmentLocation = getProblemCompleteLocation(problemDeptLocId,problemScope.getScope());
						
					}else{
						departmentLocation = asignedProbProgress.getDepartmentLocation();
					}
					
					if(departmentLocation != null){
						departmentLocation = problemCompleteLocationDAO.save(departmentLocation);
					}
					//If Problem Present Status is NEW
					if(presentStatus.getStatus().equalsIgnoreCase(IConstants.NEW)){
												
						presentProblemLocation.setProblemClassification(problemClassification);
						pbHistory.setProblemLocation(presentProblemLocation);
					}
					
					//To Update Previous Problem History Status
					pbHistory.setIsDelete("true");
					pbHistory = problemHistoryDAO.save(pbHistory);
					
					//save problem history details 
					ProblemHistory problemHistory = new ProblemHistory();
					problemHistory.setComments(comments);
					
					problemHistory.setProblemLocation(pbHistory.getProblemLocation());
					problemHistory.setProblemSourceScope(problemScope);
					problemHistory.setDateUpdated(getCurrentDateAndTime());
					problemHistory.setIsApproved("true");
					problemHistory.setIsDelete("false");
					
					List<ProblemStatus> problemStatus = problemStatusDAO.getByStatus(statusToChange);
					problemHistory.setProblemStatus(problemStatus.get(0));
					
					problemHistory = problemHistoryDAO.save(problemHistory);
					
					
					//save problem progress details
					AssignedProblemProgress problemProgress = new AssignedProblemProgress();
					problemProgress.setProblemHistory(problemHistory);
					problemProgress.setProblemClassification(problemClassification);
					problemProgress.setProblemSourceScopeConcernedDepartment(pbConDept);
					problemProgress.setDepartmentOrganisation(department);
					problemProgress.setPerformedDate(getCurrentDateAndTime());
					problemProgress.setCadre(cadre);
					problemProgress.setDepartmentLocation(departmentLocation);
					assignedProblemProgressDAO.save(problemProgress);
					
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					rs.setExceptionMsg("Updated Successfully ..");
					
				}catch(Exception ex){
					
					status.setRollbackOnly();
					ex.printStackTrace();
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
					rs.setResultCode(ResultCodeMapper.FAILURE);
					
				 return rs;
				}
			 return rs;
			}
		});
	 
	 return resultStatus;
	}
	
	/**
	 * Method To Get Problem Location Complete Details
	 * @param locationId
	 * @param type
	 * @return
	 */
	private ProblemCompleteLocation getProblemCompleteLocation(Long locationId,String type) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Getting Problem Location ..");
		
	     ProblemCompleteLocation problemCompleteLocation = new ProblemCompleteLocation();
	     
	     if(type.equalsIgnoreCase(IConstants.STATE)){
	    	 
	    	 State state = stateDAO.get(locationId);
	    	 problemCompleteLocation.setState(state);
	     }
	     else if(type.equalsIgnoreCase(IConstants.DISTRICT)){
	    	 
	    	 District district = districtDAO.get(locationId);
	    	 problemCompleteLocation.setDistrict(district);
	    	 problemCompleteLocation.setState(district.getState());
	    	 
	     }else if(type.equalsIgnoreCase(IConstants.MANDAL) || type.equalsIgnoreCase("Revenue Division")){
	    	 
	    	 Tehsil tehsil = tehsilDAO.get(Long.parseLong(locationId.toString().substring(1)));
	    	 problemCompleteLocation.setTehsil(tehsil);
	    	 problemCompleteLocation.setDistrict(tehsil.getDistrict());
	    	 problemCompleteLocation.setState(tehsil.getDistrict().getState());
	    	 
	     }else if(type.equalsIgnoreCase("Muncipality") || type.equalsIgnoreCase("Corporation")){
	    	 
	    	 LocalElectionBody localBody = localElectionBodyDAO.get(Long.parseLong(locationId.toString().substring(1)));
	    	 problemCompleteLocation.setLocalElectionBody(localBody);
	    	 problemCompleteLocation.setDistrict(localBody.getDistrict());
	    	 problemCompleteLocation.setState(localBody.getDistrict().getState());
	    	 
	     }else if(type.equalsIgnoreCase(IConstants.VILLAGE) || type.equalsIgnoreCase("Gram Panchayath")){
	    	 
	    	 Township township = townshipDAO.get(Long.parseLong(locationId.toString().substring(1)));
	    	 problemCompleteLocation.setTownship(township);
	    	 problemCompleteLocation.setTehsil(township.getTehsil());
	    	 problemCompleteLocation.setDistrict(township.getTehsil().getDistrict());
	    	 problemCompleteLocation.setState(township.getTehsil().getDistrict().getState());
	     }
	     
     return problemCompleteLocation; 	    
	}
	
	/**
	 * Method TO Get Problem Recent Updated Details
	 * @param problemHistoryId
	 * @author sai Krishna
	 * @return ProblemStatusDataVO
	 */
	@SuppressWarnings("unchecked")
	public ProblemStatusDataVO getProblemRecentDetailsByProblemHistoryId(
			Long problemHistoryId) {
		
		if(log.isDebugEnabled())
			log.debug("Entered To Get Problem Recent Details ..");
		
		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		try{
			//get Problem History and Problem Details
			ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
			Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
			
			if(problem != null){
				
				//set basic Details to VO
				setProblemBasicDataToVO(problem,problemStatusData);
				problemStatusData.setProblemStatus(problemHistory.getProblemStatus().getStatus());
				problemStatusData.setPostedBy(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemSource().getInformationSource());
				problemStatusData.setPostedByName(getProblemPostedPersonName(problemHistoryId,problemHistory.getProblemLocation().getProblemAndProblemSource()));
				problemStatusData.setProblemLocation(getProblemLocationString(problemHistory.getProblemLocation().getProblemCompleteLocation()));
				if(problemHistory.getProblemLocation().getProblemClassification() != null)
				problemStatusData.setProbClassification(problemHistory.getProblemLocation().getProblemClassification().getClassification());
				
			}
			//DAO call To Get Problem Recent Data
			List resultLst = assignedProblemProgressDAO.getProblemRecentUpdatesByProblemId(problem.getProblemId());
			if(resultLst != null && resultLst.size() > 0)
				setProblemRecentUpdatesToVO(resultLst,problemStatusData);
			
		}catch(Exception ex){
		  log.error("Exception Raised :" + ex);
		  ex.printStackTrace();
		  
		  problemStatusData.setExceptionEncountered(ex);
		  problemStatusData.setExceptionMsg(ex.getMessage());
		  problemStatusData.setResultCode(ResultCodeMapper.FAILURE);
		  
		  return problemStatusData;
		}
				
	 return problemStatusData;
	}
	
	/**
	 * 
	 * @param probAndProbSource
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getProblemPostedPersonName(Long problemHistoryId,ProblemAndProblemSource probAndProbSource) throws Exception{
		
		String personName = "";
		if(probAndProbSource != null){
			
			String infSource = probAndProbSource.getProblemSource().getInformationSource();
			if(infSource.equalsIgnoreCase("User")){
				
				personName = probAndProbSource.getUser().getFirstName();
				if(probAndProbSource.getUser().getLastName() != null)
					personName = personName + " " + probAndProbSource.getUser().getLastName();
			}else if(infSource.equalsIgnoreCase("External Person") || infSource.equalsIgnoreCase("Call Center")){
				
				personName = probAndProbSource.getProblemExternalSource().getName();
			}else if(infSource.equalsIgnoreCase("Cadre")){
				
				List cadreDetails = cadreProblemDetailsDAO.getCadreDetailsByProblemHistoryId(probAndProbSource.getProblem().getProblemId());
				if(cadreDetails != null && cadreDetails.size() > 0){
					
					Object[] values = (Object[])cadreDetails.get(0);
					String firstName = (String)values[0];
					String lastName  = (String)values[1];
					
					personName = firstName + " " + lastName;
				}
			}
			
		}
	 return personName;
	}
	
	/**
	 * Method To Set Problem Basic Details From Problem Model
	 * @param problem
	 * @param problemStatusData
	 * 
	 * @return void
	 * @author Sai Krishna
	 */
	private void setProblemBasicDataToVO(Problem problem,ProblemStatusDataVO problemStatusData) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Method To Set Problem Basic Details ..");
		
		if(problem != null){
			
			problemStatusData.setProblemId(problem.getProblemId());
			problemStatusData.setProblem(problem.getProblem());
			problemStatusData.setProblemDesc(problem.getDescription());
			problemStatusData.setPostedDate(problem.getIdentifiedOn().toString());
			problemStatusData.setExistingFrom(problem.getExistingFrom().toString());
			
			Date today = new Date();
			Date postedDate = problem.getIdentifiedOn();
			
			long diffDate = today.getTime() - postedDate.getTime();
			
			problemStatusData.setDiffDays((diffDate / (1000 * 60 * 60 * 24)));
		}
	}
	

	/**
	 * @author Sai Krishna
	 * @param resultsLst of type List
	 * @param problemStatusData
	 * 
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void setProblemRecentUpdatesToVO(List resultsLst ,ProblemStatusDataVO problemStatusData) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Method To Set Problem Basic Details ..");
		String comments = "";
		String cadre = "";
		if(resultsLst != null && resultsLst.size() > 0){
			
			Object value = (Object)resultsLst.get(0);
			AssignedProblemProgress assigedProbProg = (AssignedProblemProgress)value;
			
			problemStatusData.setUpdatedDate(assigedProbProg.getPerformedDate().toString());
			problemStatusData.setComments(comments = assigedProbProg.getComments() != null ? assigedProbProg.getComments():comments);
			
			//Set Department Data To VO
			if(assigedProbProg.getDepartmentOrganisation() != null){
				problemStatusData.setDepartmentOrganisation(assigedProbProg.getDepartmentOrganisation().getOrganisationName());
				problemStatusData.setDepartment(assigedProbProg.getDepartmentOrganisation().getProblemDepartmentCategory().getDepartment());
			}else{
				problemStatusData.setDepartmentOrganisation("");
				problemStatusData.setDepartment("");
			}
			
			//Set Cadre Data To VO
			problemStatusData.setCadre(cadre = assigedProbProg.getCadre() != null ? assigedProbProg.getCadre().getFirstName() : cadre);
			
			if(assigedProbProg.getDepartmentLocation() != null)
			problemStatusData.setDeptLocation(getProblemLocationString(assigedProbProg.getDepartmentLocation()));
			
			//Problem Classification
			if(assigedProbProg.getProblemClassification() != null)
			problemStatusData.setProbClassification(assigedProbProg.getProblemClassification().getClassification());
		}
	}

	/**
	 * Method to get all problem activities list
	 * @param problemHistoryId
	 * 
	 * @return List<ProblemStatusDataVO>
	 * 
	 * @author Sai Krishna
	 * 
	 */
	public List<ProblemStatusDataVO> getAllProblemRecentActivityDetails(
			Long problemHistoryId) {
		
		if(log.isDebugEnabled())
			log.debug("Entered To Get Problem All Recent Activity Details ..");
		
		List<ProblemStatusDataVO> problemRecentActivityList = new ArrayList<ProblemStatusDataVO>();
		
		try{
			
			//get Problem History and Problem Details
			ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
			Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
			
			//DAO Call To get activities data
			List<AssignedProblemProgress> problemDetailsLst = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
			if(problemDetailsLst != null && problemDetailsLst.size() > 0){
				
				//Iterate Different Activities List and set to List
				for(AssignedProblemProgress problemProgress:problemDetailsLst){
					
					problemRecentActivityList.add(getProblemActivityDetailsSetToVO(problemProgress));
				}
			}
			
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			ProblemStatusDataVO errorVO = new ProblemStatusDataVO();
			errorVO.setExceptionEncountered(ex);
			errorVO.setExceptionMsg(ex.getMessage());
			errorVO.setResultCode(ResultCodeMapper.FAILURE);
			
			problemRecentActivityList.add(errorVO);
		 return problemRecentActivityList; 				
		}
				
	 return problemRecentActivityList;
	}
	
	/**
	 * Method To set problem activity Data to VO
	 * @param problemProgress
	 * @return
	 */
	private ProblemStatusDataVO getProblemActivityDetailsSetToVO(AssignedProblemProgress problemProgress) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered To Process Recent Activity Details and set to vo ..");
		
		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		
		problemStatusData.setUpdatedDate(problemProgress.getPerformedDate().toString());
		if(problemProgress.getProblemActivity() != null){
			problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments());
		
			switch(problemProgress.getProblemActivity().getProblemActivityId().intValue()){
			
			case 1: 
			case 2:problemStatusData.setProbClassification(problemProgress.getProblemClassification().getClassification());
	               break;
			case 3:
			case 4:problemStatusData.setDepartmentOrganisation(problemProgress.getDepartmentOrganisation().getOrganisationName());
			       problemStatusData.setDepartment(problemProgress.getDepartmentOrganisation().getProblemDepartmentCategory().getDepartment());
			       break;
			case 5:problemStatusData.setDepartmentOrganisation("N/A");
			       problemStatusData.setDepartment("N/A");
			       break;
			case 6:
			case 7:problemStatusData.setCadre(problemProgress.getCadre().getFirstName());
			       break;
			case 8:problemStatusData.setCadre("Removed ");
			       break;
			case 11:problemStatusData.setComments(problemProgress.getComments());
			        break;
			case 12:problemStatusData.setProblemStatus(problemProgress.getProblemHistory().getProblemStatus().getStatus());
			        break;
			default:if(log.isInfoEnabled())
					log.info("Problem Activity Details for :" + problemProgress.getProblemActivity().getActivityDescription());
			}
		}
	 return problemStatusData;
	}

	/**
	 * Method To Update Assigned Cadre
	 * @param problemHistoryId
	 * @param statusId
	 * 
	 * @author Sai Krishna
	 * @return ResultStatus
	 */
	public ResultStatus updateAssignedCadre(final Long problemHistoryId,final Long cadreId,final String pbStatus) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
				ResultStatus rs = new ResultStatus();
				try{
					
					ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
					Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
					
					AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();
					
					List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
					if(problemProgress != null && problemProgress.size() > 0){
						
						AssignedProblemProgress existingProblemProgress = problemProgress.get(0);
						assignedProblemProgress.setDepartmentLocation(existingProblemProgress.getDepartmentLocation());
						assignedProblemProgress.setDepartmentOrganisation(existingProblemProgress.getDepartmentOrganisation());
						assignedProblemProgress.setProblemClassification(existingProblemProgress.getProblemClassification());
						assignedProblemProgress.setProblemSourceScopeConcernedDepartment(existingProblemProgress.getProblemSourceScopeConcernedDepartment());
						assignedProblemProgress.setComments(existingProblemProgress.getComments());							
					}
					
					//setting history and cadre details
					assignedProblemProgress.setPerformedDate(getCurrentDateAndTime());
					assignedProblemProgress.setProblemHistory(problemHistory);
					
					if(cadreId != null && !cadreId.equals(0L)){
						Cadre cadre = cadreDAO.get(cadreId);
					    assignedProblemProgress.setIsCadreAssigned("true");
					    assignedProblemProgress.setCadre(cadre);
					}
					//setting activity
					if(pbStatus.equals(IConstants.CADRE_ADD)){
						
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("ASSIGN_CADRE_ADD");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}else if(pbStatus.equals(IConstants.CADRE_MODIFY)){
						
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("ASSIGN_CADRE_UPDATE");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}else if(pbStatus.equals(IConstants.CADRE_DELETE)){
						
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("ASSIGN_CADRE_DELETE");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}
					
					//save data
					assignedProblemProgressDAO.save(assignedProblemProgress);
					
				}catch(Exception ex){
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
				 return rs;
				}
				
			 return rs;
			}	
				
		});	
	 return resultStatus;
	}

	public ResultStatus updateProblemClassification(final Long problemHistoryId,
			final Long classificationId,final String pbStatus) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
				ResultStatus rs = new ResultStatus();
				try{
					
					ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
					Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
					
					ProblemClassification pobClassification = problemClassificationDAO.get(classificationId);
					
					if(problemHistory.getProblemLocation().getProblemClassification() == null){
						
						problemHistory.getProblemLocation().setProblemClassification(pobClassification);
						problemHistory = problemHistoryDAO.save(problemHistory);
					}
					
					  AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();
						
						List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
						if(problemProgress != null && problemProgress.size() > 0){
							
							AssignedProblemProgress existingProblemProgress = problemProgress.get(0);
							assignedProblemProgress.setDepartmentLocation(existingProblemProgress.getDepartmentLocation());
							assignedProblemProgress.setDepartmentOrganisation(existingProblemProgress.getDepartmentOrganisation());
							assignedProblemProgress.setComments(existingProblemProgress.getComments());
							assignedProblemProgress.setProblemSourceScopeConcernedDepartment(existingProblemProgress.getProblemSourceScopeConcernedDepartment());
							assignedProblemProgress.setIsCadreAssigned(existingProblemProgress.getIsCadreAssigned());
							assignedProblemProgress.setCadre(existingProblemProgress.getCadre());
													
						}
						
						//setting history and cadre details
						assignedProblemProgress.setPerformedDate(getCurrentDateAndTime());
						assignedProblemProgress.setProblemHistory(problemHistory);
						assignedProblemProgress.setProblemClassification(pobClassification);
						
						if(pbStatus.equals(IConstants.PROBLEM_TYPE_ADD)){
							List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("PROBLEM_TYPE_ADD");
							assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
						}else if(pbStatus.equals(IConstants.PROBLEM_TYPE_MODIFY)){
							List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("PROBLEM_TYPE_UPDATE");
							assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
						}
						
						//save data
						assignedProblemProgressDAO.save(assignedProblemProgress);
					
				}catch(Exception ex){
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
				 return rs;
				}
				
			 return rs;
			}	
				
		});	
	 return resultStatus;
	}

	public ResultStatus updateProblemStatus(final Long problemHistoryId,final Long statusId,final String pbstatus) {
		
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
				ResultStatus rs = new ResultStatus();
				try{
					
					ProblemStatus problemStatus = problemStatusDAO.get(statusId);
					ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
					Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
					
					//check for previous status
					if(problemHistory.getProblemStatus().getStatus().equalsIgnoreCase(problemStatus.getStatus())){
						rs.setExceptionMsg("Already  In same status, Please select other status ..");
						return rs;
					}
						
					
					problemHistory.setIsApproved("true");
					problemHistory.setIsDelete("true");
					
					problemHistory = problemHistoryDAO.save(problemHistory);
					
					//create new problem history instance
					ProblemHistory newProbHistory = new ProblemHistory();
					newProbHistory.setProblemLocation(problemHistory.getProblemLocation());
					newProbHistory.setDateUpdated(getCurrentDateAndTime());
					newProbHistory.setIsApproved("true");
					newProbHistory.setProblemSourceScope(problemHistory.getProblemSourceScope());
					newProbHistory.setProblemStatus(problemStatus);
					newProbHistory.setComments(problemHistory.getComments());
					
					newProbHistory = problemHistoryDAO.save(newProbHistory);
					rs.setResultState(newProbHistory.getProblemHistoryId());
					
                    AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();
					
					List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
					if(problemProgress != null && problemProgress.size() > 0){
						
						AssignedProblemProgress existingProblemProgress = problemProgress.get(0);
						assignedProblemProgress.setDepartmentLocation(existingProblemProgress.getDepartmentLocation());
						assignedProblemProgress.setDepartmentOrganisation(existingProblemProgress.getDepartmentOrganisation());
						assignedProblemProgress.setProblemClassification(existingProblemProgress.getProblemClassification());
						assignedProblemProgress.setProblemSourceScopeConcernedDepartment(existingProblemProgress.getProblemSourceScopeConcernedDepartment());
						assignedProblemProgress.setComments(existingProblemProgress.getComments());
						assignedProblemProgress.setIsCadreAssigned(existingProblemProgress.getIsCadreAssigned());
						assignedProblemProgress.setCadre(existingProblemProgress.getCadre());
												
					}
					
					//setting history and cadre details
					assignedProblemProgress.setPerformedDate(getCurrentDateAndTime());
					assignedProblemProgress.setProblemHistory(newProbHistory);
					
					List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("STATUS_CHANGE");
					assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					
					//save data
					assignedProblemProgressDAO.save(assignedProblemProgress);
					
				}catch(Exception ex){
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
				 return rs;
				}
				
			 return rs;
			}	
				
		});	
	 return resultStatus;
	}

	public ResultStatus updateProblemComments(final Long problemHistoryId,
			final String comments,final String pbStatus) {
		if(log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
				ResultStatus rs = new ResultStatus();
				try{
					
					ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
					Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
					
									
					    AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();
						
						List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
						if(problemProgress != null && problemProgress.size() > 0){
							
							AssignedProblemProgress existingProblemProgress = problemProgress.get(0);
							assignedProblemProgress.setDepartmentLocation(existingProblemProgress.getDepartmentLocation());
							assignedProblemProgress.setDepartmentOrganisation(existingProblemProgress.getDepartmentOrganisation());
							assignedProblemProgress.setProblemClassification(existingProblemProgress.getProblemClassification());
							assignedProblemProgress.setProblemSourceScopeConcernedDepartment(existingProblemProgress.getProblemSourceScopeConcernedDepartment());
							assignedProblemProgress.setIsCadreAssigned(existingProblemProgress.getIsCadreAssigned());
							assignedProblemProgress.setCadre(existingProblemProgress.getCadre());
													
						}
						
						//setting history and cadre details
						assignedProblemProgress.setPerformedDate(getCurrentDateAndTime());
						assignedProblemProgress.setProblemHistory(problemHistory);
						assignedProblemProgress.setComments(comments);
						
						if(pbStatus.equals(IConstants.PROBLEM_COMMENTS_ADD)){
							List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("POSTED_COMMENTS");
							assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
						}						
						//save data
						assignedProblemProgressDAO.save(assignedProblemProgress);
					
				}catch(Exception ex){
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
				 return rs;
				}
				
			 return rs;
			}	
				
		});	
	 return resultStatus;
	}

	/**
	 * Method To Update/Add Department For a Problem
	 * @param problemHistoryId
	 * @param departmentId
	 * @param scopeId
	 * @param regionId
	 * @param status
	 * 
	 * @return ResultStatus
	 */
	public ResultStatus updateProblemDepartment(final Long problemHistoryId,final Long departmentId,final Long scopeId,final Long regionId,final String pbStatus) {
		
		if(log.isDebugEnabled())
			log.debug(" Started " + pbStatus + " Department Details For a Problem ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status){
				
				ResultStatus rs = new ResultStatus();
				try{
					
					//get problem history and problem 
					ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
					Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
					
					AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();
					
					List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO.getProblemAllActivitiesByProblemId(problem.getProblemId());
					if(problemProgress != null && problemProgress.size() > 0){
						
						AssignedProblemProgress existingProblemProgress = problemProgress.get(0);
						
						assignedProblemProgress.setProblemClassification(existingProblemProgress.getProblemClassification());
						assignedProblemProgress.setIsCadreAssigned(existingProblemProgress.getIsCadreAssigned());
						assignedProblemProgress.setCadre(existingProblemProgress.getCadre());
						assignedProblemProgress.setComments(existingProblemProgress.getComments());
												
					}
					
					//setting history and updated date details
					assignedProblemProgress.setPerformedDate(getCurrentDateAndTime());
					assignedProblemProgress.setProblemHistory(problemHistory);
					
					if(departmentId != null && !departmentId.equals(0L)){
						ProblemSourceScope problemSourceScope = problemSourceScopeDAO.get(scopeId);
						DepartmentOrganisation departmentOrganisation = departmentOrganisationDAO.get(departmentId);
						ProblemCompleteLocation problemCompleteLocation = getProblemCompleteLocation(regionId,problemSourceScope.getScope());
						problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
											
						assignedProblemProgress.setDepartmentLocation(problemCompleteLocation);
						assignedProblemProgress.setDepartmentOrganisation(departmentOrganisation);
						assignedProblemProgress.setProblemSourceScopeConcernedDepartment(departmentOrganisation.getProblemDepartmentCategory());
					}
					
					if(pbStatus.equals(IConstants.DEPARTMENT_ADD)){
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("DEPARTMENT_ADD");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}else if(pbStatus.equals(IConstants.DEPARTMENT_MODIFY)){
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("DEPARTMENT_UPDATE");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}else if(pbStatus.equals(IConstants.DEPARTMENT_DELETE)){
						List<ProblemActivity> problemActivityLst = problemActivityDAO.getProblemActivityByName("DEPARTMENT_DELETE");
						assignedProblemProgress.setProblemActivity(problemActivityLst.get(0));
					}
					
					//save data
					assignedProblemProgressDAO.save(assignedProblemProgress);
					
				}catch(Exception ex){
					log.error("Exception Raised :" + ex);
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
				 return rs;
				}
				
			 return rs;
			}	
				
		});	
		
		
     return resultStatus;
	}

	public ResultStatus updateProblemClassificationData(Long problemHistoryId,
			String classification,String type) {
		
		if(log.isDebugEnabled())
			log.debug(" Started setting problem as " + classification );
		
		ResultStatus rs = new ResultStatus();
		
		try{
		
			List<ProblemClassification> problemClassification = problemClassificationDAO.findByClassification(classification);
			
			if(problemClassification != null && problemClassification.size() > 0)
				rs = updateProblemClassification(problemHistoryId,problemClassification.get(0).getProblemClassificationId(),type);
					
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
		 return rs;
		}
		
	  return rs;
	}

	public ResultStatus updateProblemStatusData(Long problemHistoryId,
			String status) {

		if(log.isDebugEnabled())
			log.debug(" Setting  problem status to " + status );
		
		ResultStatus rs = new ResultStatus();
		
		try{
			
			String problemStatus = "";
			if(status.equalsIgnoreCase(IConstants.PROGRESS))
				problemStatus = IConstants.PROGRESS;
			else if(status.equalsIgnoreCase(IConstants.PENDING))
				problemStatus = IConstants.PENDING;
			else if(status.equalsIgnoreCase(IConstants.FIXED))
				problemStatus = IConstants.FIXED;
			else if(status.equalsIgnoreCase(IConstants.NEW))
				problemStatus = IConstants.NEW;
		
			List<ProblemStatus> problemStatusLst = problemStatusDAO.getByStatus(problemStatus);
			
			if(problemStatusLst != null && problemStatusLst.size() > 0)
				rs = updateProblemStatus(problemHistoryId,problemStatusLst.get(0).getProblemStatusId(),status);
					
		}catch(Exception ex){
			
			log.error("Exception Raised :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
		 return rs;
		}
			
	 return rs;
	}

	/**
	 * Method to get departments for a resolving area scope
	 * @param scopeId
	 * @return List<SelectOptionVO>
	 * 
	 * @author Sai Krishna
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentsForADepartmentResolvingAreaScope(
			Long scopeId) {
		
		if(log.isDebugEnabled())
			log.debug("Started executing method to get departments ..");
		
		List<SelectOptionVO> departments = new ArrayList<SelectOptionVO>();
		
		List departmentsList = departmentOrganisationDAO.getDepartmentsBasedOnProblemResolvingDepartmentScope(scopeId);
		if(departmentsList != null && departmentsList.size() > 0){
			
			Iterator lstItr = departmentsList.listIterator();
			while(lstItr.hasNext()){
				
				Object[] values = (Object[])lstItr.next();
				
				departments.add(new SelectOptionVO((Long)values[0],(String)values[1]));
			}
		}
		
	 return departments;
	}
	public ProblemBeanVO getProblemCompleteInfoForAUserBasedOnHistoryId(Long problemHistoryId){
		
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		List<ProblemHistory> problemHistory= problemHistoryDAO.getProblemHistoryBasedOnId(problemHistoryId);
		ProblemHistory problemHistoryObj ;
		String districtName ;
		String constituency;
		if(problemHistory !=null){
			
			problemHistoryObj = problemHistory.get(0);
			
			problemBeanVO.setProblem(problemHistoryObj.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
			problemBeanVO.setDescription(problemHistoryObj.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
			problemBeanVO.setProblemScope(problemHistoryObj.getProblemLocation().getProblemImpactLevel().getScope());
			problemBeanVO.setState(problemHistoryObj.getProblemLocation().getProblemCompleteLocation().getState().getStateName());
			districtName = problemHistoryObj.getProblemLocation().getProblemCompleteLocation().getDistrict().getDistrictName();
			if(districtName!=null){
			 problemBeanVO.setDistrict(districtName);
			}
			constituency = problemHistoryObj.getProblemLocation().getProblemCompleteLocation().getConstituency().getName();
			if(constituency!=null)
			 problemBeanVO.setConstituency(constituency);
			Date iDateOfAddNewProb = problemHistoryObj.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
			Date eDateOfAddNewProb = problemHistoryObj.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom();
			problemBeanVO.setReportedDate(sdf.format(iDateOfAddNewProb));
			problemBeanVO.setExistingFrom(sdf.format(eDateOfAddNewProb));
			problemBeanVO.setProbSource(problemHistoryObj.getProblemLocation().getProblemAndProblemSource().getProblemSource().getInformationSource());
						
			}
		return problemBeanVO;
	}
	
	
	public List<SelectOptionVO> getCadreProblemDetailsForSms(Long userId,Long cadreId,Long pHistoryId)
	{
		try{
			List<SelectOptionVO> list = new ArrayList<SelectOptionVO>(0);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			List<Long> cadreList = new ArrayList<Long>(0);
			cadreList.add(cadreId);
			String message = "";
			ProblemHistory problemHistory = null;
			long sourceId;
			Registration user = null;
			List<Object> mobileNos = cadreDAO.getMobileNosOfCadre(cadreList);
			
			if(mobileNos != null && mobileNos.size() >= 0 && mobileNos.get(0).toString().trim().length() > 0)
				selectOptionVO.setId(Long.parseLong(mobileNos.get(0).toString()));
			else
				selectOptionVO.setId(0L);
			
			problemHistory = problemHistoryDAO.get(pHistoryId);
			sourceId = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemSource().getInformationSourceId();
			
			if(sourceId == 1)
			{
				user = problemHistory.getProblemLocation().getProblemAndProblemSource().getUser();
				message += user.getFirstName()+IConstants.SPACE+user.getLastName()+IConstants.COMMA;
				message += user.getMobile() != null ? user.getMobile()+IConstants.COMMA : "";
			}
			else if(sourceId == 2 || sourceId == 3)
			{
				message += problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getName()+IConstants.COMMA;
				message += problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getMobile()!= null ?
						   problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getMobile()+IConstants.COMMA : "";
			}
			
			else if(sourceId == 4)
			{
				List<Object[]> cadreDetails = cadreProblemDetailsDAO.getCadreDetailsAndMobileNoByProblemHistoryId(problemHistory.getProblemHistoryId());
				if(cadreDetails != null && cadreDetails.size() > 0)
				{
					message += cadreDetails.get(0)[0].toString()+IConstants.SPACE+cadreDetails.get(0)[1].toString()+IConstants.COMMA;
					message += cadreDetails.get(0)[2] != null ?  cadreDetails.get(0)[2].toString()+IConstants.COMMA : "";
				}
			}
			
			message += problemManagementReportService.getProblemLocation(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(),
					problemHistory.getProblemLocation().getProblemImpactLevelValue())+".";
			
			message += "\nPlease Help them in Problem Resolving.";
			message += "\nProblem : "+problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem()+".";
			message += "\nDescription : "+problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription()+".";
			
			selectOptionVO.setName(message);
			list.add(selectOptionVO);
			return list;
		}catch (Exception e) {
			log.error("Exception Ocuured in getCadreProblemDetailsForSms() "+e);
			return null;
		}
	}
	
	public ResultStatus sendSMS(Long userId,String message,String moduleName,String[] phoneNumbers)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			Long result = smsCountrySmsService.sendSms(message, true, userId, moduleName, phoneNumbers);
			if(result.longValue() == 0)
			{
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				resultStatus.setResultPartial(false);
			}
			return resultStatus;
		}catch (Exception e) {
			log.error("Exceprtion Occured in Sending SMS "+e.getMessage());
			resultStatus.setExceptionEncountered(e.getCause());
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public String getRefNo(String str,String type){
		boolean i=true;
		try{
		while(i){
		if(problemDAO.getProblemReferenceNo(refNo)!=null)
     	return type+str;
	    }
		}catch (Exception e){
			log.debug("Exception Encountered", e);
		}
		return type+str;
	}
	
	/**
	 * This method will send a message, when PA User/Free User Post A Problem.
	 * 
	 * @param Long ProblemHistoryId
	 * @return {@link ResultStatus}
	 * @author Kamalakar Dandu
	 * 
	 * 
	 */
	public ResultStatus sendSuccessMsgToMobile(Long problemHistoryId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(problemHistoryId == null || problemHistoryId <= 0)
				return null;
			
			String message  = null;
			String[] mobileArray = new String[1];
			String mobile = null;
			ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
			Long userId = null;
			
			if(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemSource() != null)
			{
				long ProblemSource = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemSource().getInformationSourceId();
				userId = problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getRegistrationId();
				
				if(ProblemSource == 1)
				{
					mobile = problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getMobile();
				}
				else if(ProblemSource == 2 || ProblemSource == 3)
				{
					mobile = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getMobile();
				}
				else if(ProblemSource == 4)
				{
					List<Object[]> cadreDetails = cadreProblemDetailsDAO.getCadreDetailsAndMobileNoByProblemHistoryId(problemHistory.getProblemHistoryId());
					if(cadreDetails != null && cadreDetails.size() > 0)
					{
						mobile = cadreDetails.get(0)[2].toString();
					}
				}
			}
			else
			{
				mobile = problemHistory.getProblemLocation().getProblemAndProblemSource().getExternalUser().getMobile();
				userId = problemHistory.getProblemLocation().getProblemAndProblemSource().getExternalUser().getUserId();
			}
			
			message = "Your Problem Added Successfully.";
			message += "\nYour Problem Reference Number is : "+problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getReferenceNo()+".";
			message += "\nThis Will be usefull for Further Reference";
			
			if(mobile != null && mobile.length() >= 10)
			{
				mobileArray[0] = mobile;
				ResultStatus result = sendSMS(userId, message, IConstants.PROBLEM_MANAGEMENT, mobileArray);
				if(result.getResultCode() == ResultCodeMapper.SUCCESS)
				{
					log.debug("Message Sent Successfully To - "+mobile);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
				else
				{
					log.warn("Message Not Reached To - "+mobile);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setExceptionEncountered(result.getExceptionEncountered());
				}
			}
			else
			{
				log.debug("Mobile Number is Not Valid");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setExceptionMsg("Mobile NO is Not Valid");
			}
			return resultStatus;
		}catch(Exception e)
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception Occured & Exception is - "+e);
			return resultStatus;
		}
	}
}

