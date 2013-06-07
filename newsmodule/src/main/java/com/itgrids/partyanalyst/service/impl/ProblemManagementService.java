/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.service.IProblemManagementService;


public class ProblemManagementService implements IProblemManagementService {
	
	
	private IRegionScopesDAO regionScopesDAO;
	private static final Logger log = Logger
			.getLogger("ProblemManagementService.class");
	
/*
	private IProblemLocationDAO problemLocationDAO = null;
	
	private IHamletDAO hamletDAO = null;
	private ITownshipDAO townshipDAO = null;
	private IProblemExternalSourceDAO problemExternalSourceDAO = null;
	private TransactionTemplate transactionTemplate = null;
	private IProblemSourceScopeDAO problemSourceScopeDAO = null;
	private IProblemAndProblemSourceDAO problemAndProblemSourceDAO = null;
	private IProblemClassificationDAO problemClassificationDAO = null;
	private IProblemBackupDAO problemBackupDAO = null;
	private IProblemHistoryDAO problemHistoryDAO = null;
	private IProblemStatusDAO problemStatusDAO = null;
	private IInformationSourceDAO informationSourceDAO = null;
	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO = null;
	private IAssignedProblemProgressDAO assignedProblemProgressDAO = null;
	private IProblemImpactLevelDAO problemImpactLevelDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	
	private ICadreDAO cadreDAO;
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;
	private IProblemTypeDAO problemTypeDAO;
	private ProblemBeanVO problemBeanVO = null;
	private List<ProblemBeanVO> problemBeanVOs = null;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private SimpleDateFormat sdf1 = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
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
	private String refNo = null;
	private IFileDAO fileDAO;
	private IFileTypeDAO fileTypeDAO;
	private IProblemFileDAO problemFileDAO;
	private String tempName;
	private IUserProblemApprovalDAO userProblemApprovalDAO;
	private IMailsSendingService mailsSendingService;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	private IFilePathsDAO filePathsDAO;
	private IUserDAO userDAO; 
	private DateUtilService dateUtilService = new DateUtilService();
	private IProblemDAO problemDAO;
	private IProblemFilesDAO problemFilesDAO;
	private ICadreProblemsDAO cadreProblemsDAO;
	private IVisibilityDAO visibilityDAO;
	private IUserProblemDAO userProblemDAO;
	private IProblemCommentsDAO problemCommentsDAO;
	private IProblemLikesDAO problemLikesDAO;
	private IClassifiedProblemsDAO classifiedProblemsDAO;
	private IProblemProgressDAO problemProgressDAO;
	private IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO;
	private IProblemAssignedCadreDAO problemAssignedCadreDAO;
    private IProblemRatingDAO problemRatingDAO;
    private ICommentDAO commentDAO;
    private IAbusedCommentsDAO abusedCommentsDAO; 
    private IFileGallaryDAO fileGallaryDAO;
    private ICandidateDetailsService candidateDetailsService;
    private IPanchayatHamletDAO panchayatHamletDAO;
    private INewsProblemDAO newsProblemDAO;
    
    public INewsProblemDAO getNewsProblemDAO() {
		return newsProblemDAO;
	}

	public void setNewsProblemDAO(INewsProblemDAO newsProblemDAO) {
		this.newsProblemDAO = newsProblemDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	
	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}

	public IProblemAssignedDepartmentDAO getProblemAssignedDepartmentDAO() {
		return problemAssignedDepartmentDAO;
	}

	public void setProblemAssignedDepartmentDAO(
			IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO) {
		this.problemAssignedDepartmentDAO = problemAssignedDepartmentDAO;
	}

	public IProblemLikesDAO getProblemLikesDAO() {
		return problemLikesDAO;
	}

	public void setProblemLikesDAO(IProblemLikesDAO problemLikesDAO) {
		this.problemLikesDAO = problemLikesDAO;
	}

	public IProblemCommentsDAO getProblemCommentsDAO() {
		return problemCommentsDAO;
	}

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}

	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IVisibilityDAO getVisibilityDAO() {
		return visibilityDAO;
	}

	public void setVisibilityDAO(IVisibilityDAO visibilityDAO) {
		this.visibilityDAO = visibilityDAO;
	}

	public ICadreProblemsDAO getCadreProblemsDAO() {
		return cadreProblemsDAO;
	}

	public void setCadreProblemsDAO(ICadreProblemsDAO cadreProblemsDAO) {
		this.cadreProblemsDAO = cadreProblemsDAO;
	}

	public IProblemFilesDAO getProblemFilesDAO() {
		return problemFilesDAO;
	}

	public void setProblemFilesDAO(IProblemFilesDAO problemFilesDAO) {
		this.problemFilesDAO = problemFilesDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}

	public IMailsSendingService getMailsSendingService() {
		return mailsSendingService;
	}

	public void setMailsSendingService(IMailsSendingService mailsSendingService) {
		this.mailsSendingService = mailsSendingService;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public IProblemFileDAO getProblemFileDAO() {
		return problemFileDAO;
	}

	public void setProblemFileDAO(IProblemFileDAO problemFileDAO) {
		this.problemFileDAO = problemFileDAO;
	}

	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	public IFileTypeDAO getFileTypeDAO() {
		return fileTypeDAO;
	}

	public void setFileTypeDAO(IFileTypeDAO fileTypeDAO) {
		this.fileTypeDAO = fileTypeDAO;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public SmsCountrySmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(
			SmsCountrySmsService smsCountrySmsService) {
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

	public void setInformationSourceDAO(
			IInformationSourceDAO informationSourceDAO) {
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

	public IProblemClassificationDAO getProblemClassificationDAO() {
		return problemClassificationDAO;
	}

	public IProblemBackupDAO getProblemBackupDAO() {
		return problemBackupDAO;
	}

	public void setProblemBackupDAO(IProblemBackupDAO problemBackupDAO) {
		this.problemBackupDAO = problemBackupDAO;
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

	public void setLocalElectionBodyDAO(
			ILocalElectionBodyDAO localElectionBodyDAO) {
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
*/
	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
/*
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
	
	

	public IUserProblemApprovalDAO getUserProblemApprovalDAO() {
		return userProblemApprovalDAO;
	}

	public void setUserProblemApprovalDAO(
			IUserProblemApprovalDAO userProblemApprovalDAO) {
		this.userProblemApprovalDAO = userProblemApprovalDAO;
	}

	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	public IClassifiedProblemsDAO getClassifiedProblemsDAO() {
		return classifiedProblemsDAO;
	}
	
	public void setClassifiedProblemsDAO(
			IClassifiedProblemsDAO classifiedProblemsDAO) {
		this.classifiedProblemsDAO = classifiedProblemsDAO;
	}
	
	public IProblemProgressDAO getProblemProgressDAO() {
		return problemProgressDAO;
	}

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}	
	
	public IProblemAssignedCadreDAO getProblemAssignedCadreDAO() {
		return problemAssignedCadreDAO;
	}

	public void setProblemAssignedCadreDAO(
			IProblemAssignedCadreDAO problemAssignedCadreDAO) {
		this.problemAssignedCadreDAO = problemAssignedCadreDAO;
	}

	public IProblemRatingDAO getProblemRatingDAO() {
		return problemRatingDAO;
	}

	public void setProblemRatingDAO(IProblemRatingDAO problemRatingDAO) {
		this.problemRatingDAO = problemRatingDAO;
	}

	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public IAbusedCommentsDAO getAbusedCommentsDAO() {
		return abusedCommentsDAO;
	}

	public void setAbusedCommentsDAO(IAbusedCommentsDAO abusedCommentsDAO) {
		this.abusedCommentsDAO = abusedCommentsDAO;
	}
*/

	public Long getRegionScopesIdByScope(String scope)
	{
		try{
			return regionScopesDAO.getRegionScopeIdByScope(scope);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRegionScopesIdByScope() Method, Exception - "+e);
			return 0l;
		}
	}
/*
	*//**
	 * Used To Get The Problems Of a Hamlet In a Particular Year
	 *//*
	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,
			String year) {

		ProblemManagementDataVO problemManagementDataVO = new ProblemManagementDataVO();
		List<HamletProblemVO> hamletProblemsVO = null;
		ResultStatus resultStatus = new ResultStatus();

		if (log.isDebugEnabled()) {
			log.debug("Entered Into getProblemsForAHamlet Method.....");
			log.debug("Hamlet Id:: " + hamletId);
			log.debug("Year" + year);
		}
		try {
			List<ProblemLocation> problemLocations = problemLocationDAO
					.findByHamletIdandYear(hamletId, year);
			if (problemLocations != null)
				hamletProblemsVO = getHamletProblems(problemLocations);
			problemManagementDataVO.setHamletProblems(hamletProblemsVO);
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			problemManagementDataVO.setResultStatus(resultStatus);
		} catch (Exception ex) {
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			problemManagementDataVO.setResultStatus(resultStatus);
			ex.printStackTrace();
		}
		return problemManagementDataVO;
	}

	*//**
	 * Used By getProblemsForAHamlet to Get The Problems For ProblemLocation
	 * 
	 * @param problemLocations
	 * @return
	 * @throws Exception
	 *//*
	public List<HamletProblemVO> getHamletProblems(
			List<ProblemLocation> problemLocations) throws Exception {

		List<HamletProblemVO> hamletProblemsVO = null;
		if (problemLocations != null) {
			ProblemBackup problem = null;
			ProblemAndProblemSource problemAndProblemSource = null;
			ProblemExternalSource problemExternalSource = null;
			hamletProblemsVO = new ArrayList<HamletProblemVO>();

			for (ProblemLocation problemLoctn : problemLocations) {
				problem = problemLoctn.getProblemAndProblemSource()
						.getProblem();
				problemAndProblemSource = problemLoctn
						.getProblemAndProblemSource();
				problemExternalSource = problemLoctn
						.getProblemAndProblemSource()
						.getProblemExternalSource();

				HamletProblemVO hamletProblemVO = new HamletProblemVO();
				hamletProblemVO.setProblemDesc(problem.getDescription());
				hamletProblemVO.setIdentifiedDate(problem.getIdentifiedOn()
						.toString());
				hamletProblemVO.setProblemId(problem.getProblemId());
				hamletProblemVO.setYear(problem.getYear());
				hamletProblemVO.setProblemStatus("--");

				if (problemAndProblemSource.getUser() != null)
					hamletProblemVO.setProblemSource(problemAndProblemSource
							.getUser().getFirstName());
				if (problemExternalSource != null)
					hamletProblemVO.setProblemSource(problemExternalSource
							.getName());

				hamletProblemsVO.add(hamletProblemVO);
			}
		}
		return hamletProblemsVO;
	}

	*//**
	 * Used To Save The New Problem Data And Return The Data Saved
	 *//*
	
	public ProblemCompleteLocation saveProblemCompleteLocation(ProblemBeanVO problemBeanVO)
	{
		ProblemCompleteLocation problemCompleteLocation = null;
		try
		{
			if(problemBeanVO == null)
			{
				log.info("problemBeanVO is null in saveProblemCompleteLocation()");
				return problemCompleteLocation;
			}
			else if(problemBeanVO != null)
			{
				problemCompleteLocation = new ProblemCompleteLocation();
				problemCompleteLocation.setState(stateDAO.get(new Long(
						problemBeanVO.getState())));
				if (problemBeanVO.getIsParliament())
					problemCompleteLocation
							.setParliamentConstituency(constituencyDAO
									.get(problemBeanVO.getPConstituencyId()));
				 
				if (problemBeanVO.getDistrict() != null
						&& !"0".equals(problemBeanVO.getDistrict()))
					problemCompleteLocation.setDistrict(districtDAO
							.get(new Long(problemBeanVO.getDistrict())));
				if (problemBeanVO.getConstituency() != null
						&& !"0".equals(problemBeanVO.getConstituency()))
					problemCompleteLocation
							.setConstituency(constituencyDAO.get(new Long(
									problemBeanVO.getConstituency())));
				if (problemBeanVO.getTehsil() != null
						&& !"0".equals(problemBeanVO.getTehsil())) {
					if (IConstants.RURAL_TYPE.equals(problemBeanVO
							.getTehsil().substring(0, 1)))
						problemCompleteLocation.setTehsil(tehsilDAO
								.get(new Long(problemBeanVO.getTehsil()
										.substring(1))));
					else if (IConstants.URBAN_TYPE.equals(problemBeanVO
							.getTehsil().substring(0, 1))) {
						Long assemblyLocalElectionBodyId = new Long(
								problemBeanVO.getTehsil().substring(1));
						List localElectionBodyIdsList = assemblyLocalElectionBodyDAO
								.getLocalElectionBodyId(assemblyLocalElectionBodyId);
						Object object = (Object) localElectionBodyIdsList
								.get(0);
						problemCompleteLocation
								.setLocalElectionBody(localElectionBodyDAO
										.get((Long) object));
					}
				}
				if (problemBeanVO.getVillage() != null
						&& !"0".equals(problemBeanVO.getVillage())) {
					if (IConstants.RURAL_TYPE.equals(problemBeanVO
							.getVillage().substring(0, 1)))
						problemCompleteLocation.setHamlet(hamletDAO
								.get(new Long(problemBeanVO.getVillage()
										.substring(1))));
					else if (IConstants.URBAN_TYPE.equals(problemBeanVO
							.getVillage().substring(0, 1))) {
						problemCompleteLocation.setWard(constituencyDAO
								.get(new Long(problemBeanVO.getVillage()
										.substring(1))));
					}
				}

				if (problemBeanVO.getBooth() != null
						&& !"0".equals(problemBeanVO.getBooth()))
					problemCompleteLocation.setBooth(boothDAO.get(new Long(
							problemBeanVO.getBooth())));
				
				problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
			}
			return problemCompleteLocation;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveProblemCompleteLocation(), Exception is - "+e);
			return null;
		}
		
	}
	
	public ProblemExternalSource saveProblemExternalSource(ProblemBeanVO problemBeanVO)
	{
		ProblemExternalSource problemExternalSource = null;
		try
		{
			if(problemBeanVO == null)
			{
				log.info("problemBeanVO is null in saveProblemExternalSource() Method");
				return problemExternalSource;
			}
			else if(problemBeanVO != null && problemBeanVO.getProblemPostedBy().equals(IConstants.PARTY_ANALYST_USER))
			{
				if (problemBeanVO.getProbSourceId() == 2
						|| problemBeanVO.getProbSourceId() == 3) {
					
					problemExternalSource = new ProblemExternalSource();
					if(problemBeanVO.getWindowTask().equalsIgnoreCase(
							IConstants.UPDATE_EXISTING))
					{
						
						List<UserProblem> problemDetails = userProblemDAO.getProblemDeatilsByProblemId(problemBeanVO.getProblemId());
						if(problemDetails.get(0).getProblem().getExternalSource() != null && problemDetails.get(0).getProblem().getExternalSource().getProblemExternalSourceId() > 0)
						problemExternalSource = problemExternalSourceDAO.get(problemDetails.get(0).getProblem().getExternalSource().getProblemExternalSourceId());	
					}
					problemExternalSource.setName(problemBeanVO
							.getName());
					problemExternalSource.setMobile(problemBeanVO
							.getMobile());
					problemExternalSource.setEmail(problemBeanVO
							.getEmail());
					problemExternalSource.setAddress(problemBeanVO
							.getAddress());
					problemExternalSource.setTelePhone(problemBeanVO
							.getPhone());
					problemExternalSource = problemExternalSourceDAO
							.save(problemExternalSource);

				}
				
			}
			return problemExternalSource;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveProblemExternalSource() Method, Exception - "+e);
			return null;
		}
	}
	
	public String getProblemReferenceNo()
	{
		String refNo = null;
		try{
			String str = ((Long) System.currentTimeMillis()).toString();
			refNo = str.substring(str.length() - 5, str.length());
			
			return refNo;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getProblemReferenceNo() Method, Exception is - "+e);
			return refNo;
		}
		
	}
	
	public ResultStatus deleteCadreProblems(ProblemBeanVO problemBeanVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			if(problemBeanVO == null)
			{
				log.info("problemBeanVO null in deleteCadreProblemDetails() Method");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(problemBeanVO != null && problemBeanVO.getProblemId() != null && problemBeanVO.getProbSourceId() < 4)
			{
				
				Integer result = cadreProblemsDAO.deleteCadreProblem(problemBeanVO.getProblemId());
				if(result != 0)
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				else if(result == 0)
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				
				}
			return resultStatus;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	public void saveCadreProblemDetails(ProblemBeanVO problemBeanVO, Problem problem)
	{
		try{
			if(problemBeanVO == null || problem == null)
			{
				log.info("problemBeanVO null in saveCadreProblemDetails() Method");
				return;
			}
			else if(problemBeanVO != null && problem != null && problemBeanVO.getProbSourceId() == 4)
			{
				
				CadreProblems cadreProblems = new CadreProblems();
				if(problemBeanVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING))
				{
					List<CadreProblems> cadreProblemDetails = cadreProblemsDAO.getCadreProblemDetailsByProblemId(problemBeanVO.getProblemId());
					if(cadreProblemDetails != null && cadreProblemDetails.size() != 0)
					if(cadreProblemDetails.get(0).getCadreProblemsId() != null && cadreProblemDetails.get(0).getCadreProblemsId() > 0)
					cadreProblems =cadreProblemsDAO.get(cadreProblemDetails.get(0).getCadreProblemsId());
				}
				
				Cadre cadre = cadreDAO.get(problemBeanVO.getCadreId());
				cadreProblems.setCadre(cadre);
				cadreProblems.setProblem(problem);
				cadreProblems.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				cadreProblems.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				cadreProblemsDAO.save(cadreProblems);
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public UserProblem saveUserProblemDetails(ProblemBeanVO problBeanVO,
			Problem problem) {
		UserProblem userProblem = null;
		try {

			if (problBeanVO == null || problem == null) {
				log.info("problBeanVO is null in saveUserProblemDetails() Method");
				return userProblem;
			} else if (problBeanVO != null && problem != null) {

				if (problBeanVO.getWindowTask()
						.equalsIgnoreCase(IConstants.NEW)) {
					userProblem = new UserProblem();
				}
				if (problBeanVO.getWindowTask().equalsIgnoreCase(
						IConstants.UPDATE_EXISTING)) {
					List<Long> userProblemId = userProblemDAO
							.getUserProblemIdByUserIdAndProblemId(
									problBeanVO.getUserID(),
									problBeanVO.getProblemId());
					userProblem = userProblemDAO.get(userProblemId.get(0));
				}
				if (problemBeanVO.getProblemVisibility() == null
						&& problemBeanVO.getProblemPostedBy().equals(
								IConstants.FREE_USER))
					userProblem.setVisibility(visibilityDAO.get(1l));
				else if (problemBeanVO.getProblemVisibility() == null
						&& problemBeanVO.getProblemPostedBy().equals(
								IConstants.PARTY_ANALYST_USER))
					userProblem.setVisibility(visibilityDAO.get(2l));

				else if (problemBeanVO.getProblemVisibility() != null
						&& problemBeanVO.getProblemVisibility()
								.equalsIgnoreCase("public"))
					userProblem.setVisibility(visibilityDAO.get(1l));
				else if (problemBeanVO.getProblemVisibility() != null
						&& problemBeanVO.getProblemVisibility()
								.equalsIgnoreCase("private"))
					userProblem.setVisibility(visibilityDAO.get(2l));
				userProblem.setUser(userDAO.get(problemBeanVO.getUserID()));
				userProblem.setIsOwner(IConstants.TRUE);
				userProblem.setInsertedTime(dateUtilService
						.getCurrentDateAndTime());
				userProblem.setUpdatedTime(dateUtilService
						.getCurrentDateAndTime());
				userProblem.setProblem(problem);
				userProblem = userProblemDAO.save(userProblem);

			}
			return userProblem;
		}

		catch (Exception e) {
			return userProblem;
		}
	}

	public ProblemBeanVO saveNewProblemData(ProblemBeanVO problemBeanVOToSave) {
		this.problemBeanVO = problemBeanVOToSave;
		if (log.isDebugEnabled()) {
			log.debug("Entered Into saveProblemData Method.....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				ProblemBeanVO problemBeanFromDB = new ProblemBeanVO();
				Problem problem = null;
				ProblemCompleteLocation problemCompleteLocation = null;
				try {
					InformationSource problemSource = null;
					problem = new Problem();
					problemCompleteLocation = new ProblemCompleteLocation();
					ProblemType problemType = new ProblemType();
					ProblemExternalSource problemExternalSource = null; 
					UserProblem userProblem = new UserProblem();
					String userName = null;
					ProblemActivity problemActivity = null;
					//problemCompleteLocation = saveProblemCompleteLocation(problemBeanVO);
										
					problemCompleteLocation.setState(stateDAO.get(new Long(
							problemBeanVO.getState())));
					if (problemBeanVO.getIsParliament())
						problemCompleteLocation
								.setParliamentConstituency(constituencyDAO
										.get(problemBeanVO.getPConstituencyId()));
					 
					if (problemBeanVO.getDistrict() != null
							&& !"0".equals(problemBeanVO.getDistrict()))
						problemCompleteLocation.setDistrict(districtDAO
								.get(new Long(problemBeanVO.getDistrict())));
					if (problemBeanVO.getConstituency() != null
							&& !"0".equals(problemBeanVO.getConstituency()))
						problemCompleteLocation
								.setConstituency(constituencyDAO.get(new Long(
										problemBeanVO.getConstituency())));
					if (problemBeanVO.getTehsil() != null
							&& !"0".equals(problemBeanVO.getTehsil())) {
						if (IConstants.RURAL_TYPE.equals(problemBeanVO
								.getTehsil().substring(0, 1)))
							problemCompleteLocation.setTehsil(tehsilDAO
									.get(new Long(problemBeanVO.getTehsil()
											.substring(1))));
						else if (IConstants.URBAN_TYPE.equals(problemBeanVO
								.getTehsil().substring(0, 1))) {
							Long assemblyLocalElectionBodyId = new Long(
									problemBeanVO.getTehsil().substring(1));
							List localElectionBodyIdsList = assemblyLocalElectionBodyDAO
									.getLocalElectionBodyId(assemblyLocalElectionBodyId);
							Object object = (Object) localElectionBodyIdsList
									.get(0);
							problemCompleteLocation
									.setLocalElectionBody(localElectionBodyDAO
											.get((Long) object));
						}
					}
					if (problemBeanVO.getVillage() != null
							&& !"0".equals(problemBeanVO.getVillage())) {
						if (IConstants.RURAL_TYPE.equals(problemBeanVO
								.getVillage().substring(0, 1)))
							problemCompleteLocation.setHamlet(hamletDAO
									.get(new Long(problemBeanVO.getVillage()
											.substring(1))));
						else if (IConstants.URBAN_TYPE.equals(problemBeanVO
								.getVillage().substring(0, 1))) {
							problemCompleteLocation.setWard(constituencyDAO
									.get(new Long(problemBeanVO.getVillage()
											.substring(1))));
						}
					}

					if (problemBeanVO.getBooth() != null
							&& !"0".equals(problemBeanVO.getBooth()))
						problemCompleteLocation.setBooth(boothDAO.get(new Long(
								problemBeanVO.getBooth())));
					
					problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
		
					
					if(problemCompleteLocation != null)
						  problem.setProblemCompleteLocation(problemCompleteLocation);
					
					if (problemBeanVO.getProbSourceId() != null	&& problemBeanVO.getProbSourceId() > 0)
					{
						problemSource = informationSourceDAO.get(problemBeanVO.getProbSourceId());
						problem.setInformationSource(problemSource);
						
						if(problemBeanVO.getProbSourceId().equals(2L) || problemBeanVO.getProbSourceId().equals(3L))
						{
							problemExternalSource = saveProblemExternalSource(problemBeanVO);
							problem.setExternalSource(problemExternalSource);
						}
					}
					
					
					refNo = getProblemReferenceNo();
						if(refNo != null)
						{
							if (new Long(0).equals(problemBeanVO.getProbSourceId()))
								problem.setReferenceNo(getRefNo(refNo, "FU"));
							else if (new Long(1)
									.equals(problemBeanVO.getProbSourceId()))
								problem.setReferenceNo(getRefNo(refNo, "PU"));
							else if (new Long(2)
									.equals(problemBeanVO.getProbSourceId()))
								problem.setReferenceNo(getRefNo(refNo, "EU"));
							else if (new Long(3)
									.equals(problemBeanVO.getProbSourceId()))
								problem.setReferenceNo(getRefNo(refNo, "CC"));
							else if (new Long(4)
									.equals(problemBeanVO.getProbSourceId()))
								problem.setReferenceNo(getRefNo(refNo, "CD"));
						}
					
					RegionScopes problemImpactLevel = regionScopesDAO
							.get(problemBeanVO.getProblemImpactLevelId());
					
					problem.setRegionScopes(regionScopesDAO.get(new Long(problemBeanVO.getProblemImpactLevelId())));
					
					if (!problemBeanVO.getProblem().contains(" ")) {
						problem.setTitle(stringUtilService
								.fragmentARegularString(problemBeanVO
										.getProblem(), 100, " "));
					} else {
						problem.setTitle(problemBeanVO.getProblem());
					}
					
					if (!problemBeanVO.getDescription().contains(" ")) {
						problem.setDescription(stringUtilService
								.fragmentARegularString(problemBeanVO
										.getDescription(), 100, " "));
					} else {
						problem.setDescription(problemBeanVO.getDescription());
					}
					
					
					if (problemBeanVO.getProblemTypeId() != 0) {
						problemType = problemTypeDAO.get(problemBeanVO
								.getProblemTypeId());
						problem.setProblemType(problemType);
					}
					
					Date iDate = sdf.parse(problemBeanVO.getReportedDate());
					Date eDate = sdf.parse(problemBeanVO.getExistingFrom());
					problem.setIdentifiedOn(iDate);
					problem.setExistingFrom(eDate);
					problem.setProblemStatus(problemStatusDAO.get(problemBeanVO.getProblemStatusId()));
					
					
					problem.setIsDelete(IConstants.FALSE);
					
					if(problemSource != null && problemSource.getInformationSource() != null)
					problem.setInformationSource(problemSource);
					
					
					problem.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					problem.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					
					problem.setImpactLevelValue(getProblemImpactValue(
							problemBeanVO.getProblemImpactLevelId(),
							problemBeanVO.getProblemImpactLevelValue()));
					
					
					if (problemBeanVO.getProblemPostedBy().equals(
							IConstants.PARTY_ANALYST_USER)) {
						problem.setIsApproved(IConstants.TRUE);
						
					} else if (problemBeanVO.getProblemPostedBy().equals(
							IConstants.FREE_USER)) {
						problem.setIsApproved(IConstants.TRUE);
						
						}
				
				problem = problemDAO.save(problem);
				
				if(problemBeanVO.getProblemPostedBy().equals(IConstants.PARTY_ANALYST_USER) && problemBeanVO.getProbSourceId() == 4)
				saveCadreProblemDetails(problemBeanVO,problem);
				
				userProblem = saveUserProblemDetails(problemBeanVO, problem);
				problemBeanFromDB.setUserProblemId(userProblem.getUserProblemId());
				if(userProblem.getUser().getFirstName() != null)
					userName = userProblem.getUser().getFirstName().toString();
				if(userProblem.getUser().getLastName() != null)
					userName += " "+userProblem.getUser().getLastName().toString();
				
				problemBeanFromDB.setProblemId(problem.getProblemId());
				problemBeanFromDB.setProblemRefNum(problem.getReferenceNo());
				problemBeanFromDB.setExistingFrom(problem.getExistingFrom().toString());
				problemBeanFromDB.setReportedDate(problem.getIdentifiedOn().toString());
				problemBeanFromDB.setProblem(problem.getTitle());
				problemBeanFromDB.setDescription(problem.getDescription());
				problemBeanFromDB.setEmail(userProblem.getUser().getEmail());
				problemBeanFromDB.setName(userName);
				problemBeanFromDB.setProblemImpactLevelValue(problem.getImpactLevelValue());
				problemBeanFromDB.setProblemImpactLevelId(problem.getRegionScopes().getRegionScopesId());
				//problemActivity = problemActivityDAO.get(1l);
				saveProblemRelatedFiles(problemBeanVO,problem,userProblem);
					
					
				} catch (Exception e) {
					status.setRollbackOnly();
					if (log.isDebugEnabled()) {
						log
								.debug(
										"Exception Raised while Update And Get Problems Under Pending::",
										e);
					}
					problemBeanFromDB.setExceptionEncountered(e);
					e.printStackTrace();
				}
				problemBeanVO = problemBeanFromDB;
			}
		});
		return this.problemBeanVO;
	}
	

	public Long getProblemImpactValue(Long impactLevelId, Long impactLevelValue) {

		// ProblemImpactLevel impactLevel =
		// problemImpactLevelDAO.get(impactLevelId);
		RegionScopes impactLevel = regionScopesDAO.get(impactLevelId);
		if (impactLevel != null) {
			if (impactLevel.getScope().equalsIgnoreCase(IConstants.STATE)
					|| impactLevel.getScope().equalsIgnoreCase(
							IConstants.DISTRICT)
					|| impactLevel.getScope().equalsIgnoreCase(
							IConstants.CONSTITUENCY)
					|| impactLevel.getScope()
							.equalsIgnoreCase(IConstants.BOOTH)) {
				return impactLevelValue;
			} else if (impactLevel.getScope().equalsIgnoreCase(
					IConstants.MUNICIPAL_CORP_GMC)) {
				Long assemblyLocalElectionBodyId = new Long(impactLevelValue
						.toString().substring(1));
				List localElectionBodyIdsList = assemblyLocalElectionBodyDAO
						.getLocalElectionBodyId(assemblyLocalElectionBodyId);
				Object object = (Object) localElectionBodyIdsList.get(0);
				return (Long) object;
			} else {
				String value = impactLevelValue.toString().substring(1);
				return new Long(value);
			}
		}

		return impactLevelValue;
	}

	*//**
	 * To Get The New Problems Of A User From DB
	 *//*
	public ProblemsOfUserVO getNewProblemsForUser(Long userId,
			Long statusId) {
		if (log.isDebugEnabled()) {
			log.debug("Inside the method :getProblemsForUser:");
		}
		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		List<ProblemBeanVO> problemBeanVOs = null;
		ResultStatus resultStatus = new ResultStatus();

		List<SelectOptionVO> problemTypes = new ArrayList<SelectOptionVO>();
		if (log.isDebugEnabled()) {
			log.debug("Entered Into getProblemsForUser  Method.....");
			log.debug("userId: " + userId);
		}
		List<ProblemHistory> problemHistories = problemHistoryDAO
				.findProblemLocationsByUserId(userId, statusId);
		if (problemHistories.size() > 0) {
			problemBeanVOs = getUserProblems(problemHistories);
		}

		List<ProblemClassification> problemClassificationList = problemClassificationDAO
				.getAll();

		if (log.isDebugEnabled()) {
			log.debug("Total Classifications:"
					+ problemClassificationList.size());
		}
		for (ProblemClassification problemClassification : problemClassificationList) {
			SelectOptionVO problemClassificationVO = new SelectOptionVO(
					problemClassification.getProblemClassificationId(),
					problemClassification.getClassification());
			problemTypes.add(problemClassificationVO);
		}

		Long stateId = staticDataService
				.getStateIdForUserByAccessValue(userId);

		problemsOfUserVO
				.setProblemRegionScopes(getAllDepartmentScopes(stateId));
		problemsOfUserVO.setProblemsByUser(problemBeanVOs);
		problemsOfUserVO.setProblemTypes(problemTypes);
		return problemsOfUserVO;
	}

	public List<SelectOptionVO> getAllDepartmentScopes(Long stateId) {
		List<SelectOptionVO> problemRegionScopes = new ArrayList<SelectOptionVO>();

		// Modified By Sai
		List<ProblemSourceScope> regionScopes = null;

		if (stateId == null || stateId.equals(0L))
			regionScopes = problemSourceScopeDAO.getAll();

		else
			regionScopes = problemSourceScopeDAO.findByStateId(stateId);

		if (log.isDebugEnabled()) {
			log.debug("Total Classifications:" + regionScopes.size());
		}
		for (ProblemSourceScope problemSourceScope : regionScopes) {
			SelectOptionVO proSelectOptionVO = new SelectOptionVO(
					problemSourceScope.getProblemSourceScopeId(),
					problemSourceScope.getScope());
			problemRegionScopes.add(proSelectOptionVO);
		}
		return problemRegionScopes;
	}

	*//**
	 * Used By The getNewProblemsForUser method to Get the Problem Data From
	 * ProblemHistories in the Form of ProblemBeanVOs
	 * 
	 * @param problemHistories
	 * @return
	 *//*
	public List<ProblemBeanVO> getUserProblems(
			List<ProblemHistory> problemHistories) {
		List<ProblemBeanVO> problemBean = null;
		if (problemHistories != null) {
			ProblemBackup problem = null;
			ProblemLocation problemLocation = null;
			ProblemAndProblemSource problemAndProblemSource = null;
			problemBean = new ArrayList<ProblemBeanVO>();

			Date iDate, eDate;
			for (ProblemHistory problemHistory : problemHistories) {
				if (!(problemHistory.getIsDelete() != null && problemHistory
						.getIsDelete().toString().equalsIgnoreCase("true"))) {
					problemLocation = problemHistory.getProblemLocation();
					problem = problemLocation.getProblemAndProblemSource()
							.getProblem();
					problemAndProblemSource = problemLocation
							.getProblemAndProblemSource();
					ProblemBeanVO problemBeanVO = new ProblemBeanVO();
					problemBeanVO.setProblemId(problem.getProblemId());
					problemBeanVO.setProblemLocationId(problemLocation
							.getProblemLocationId());
					// problemBeanVO.setHamletId(problemLocation.getHamlet().getHamletId());
					problemBeanVO.setProblemImpactLevelId(problemLocation
							.getProblemImpactLevel().getRegionScopesId());
					problemBeanVO.setProblemImpactLevelValue(problemLocation
							.getProblemImpactLevelValue());
					problemBeanVO.setProblemHistoryId(problemHistory
							.getProblemHistoryId());
					problemBeanVO.setProblem(problem.getProblem());
					problemBeanVO.setDescription(problem.getDescription());
					iDate = problem.getIdentifiedOn();
					eDate = problem.getExistingFrom();
					problemBeanVO.setReportedDate(sdf.format(iDate));
					problemBeanVO.setExistingFrom(sdf.format(eDate));
					// problemBeanVO.setHamlet(problemLocation.getHamlet().getHamletName());
					problemBeanVO.setHamlet(getLocationDetails(problemBeanVO
							.getProblemImpactLevelId(), problemBeanVO
							.getProblemImpactLevelValue()));
					problemBeanVO.setProbSource(problemAndProblemSource
							.getProblemSource().getInformationSource());
					problemBean.add(problemBeanVO);
				}

			}
		}
		return problemBean;
	}

	public String getLocationDetails(Long problemImpactLevelId,
			Long problemImpactLevelValue) {

		String result = "";
		if (problemImpactLevelId != null && !problemImpactLevelId.equals(0L)) {
			// ProblemImpactLevel impactLevel =
			// problemImpactLevelDAO.get(problemImpactLevelId);
			RegionScopes impactLevel = regionScopesDAO
					.get(problemImpactLevelId);
			if (impactLevel.getScope().equals(IConstants.STATE)) {
				State state = stateDAO.get(problemImpactLevelValue);
				return state.getStateName();
			} else if (impactLevel.getScope().equals(IConstants.DISTRICT)) {
				District district = districtDAO.get(problemImpactLevelValue);
				return district.getDistrictName();
			} else if (impactLevel.getScope().equals(IConstants.CONSTITUENCY)) {
				Constituency constituency = constituencyDAO
						.get(problemImpactLevelValue);
				return constituency.getName();
			} else if (impactLevel.getScope().equals(IConstants.MANDAL)) {
				Tehsil tehsil = tehsilDAO.get(problemImpactLevelValue);
				return tehsil.getTehsilName();
			} else if (impactLevel.getScope().equalsIgnoreCase(
					"MUNICIPAL_CORP_GMC")) {
				LocalElectionBody localElec = localElectionBodyDAO
						.get(problemImpactLevelValue);
				return localElec.getName();
			} else if (impactLevel.getScope().equals(IConstants.WARD)) {
				Constituency constituency = constituencyDAO
						.get(problemImpactLevelValue);
				return constituency.getName();
			} else if (impactLevel.getScope().equalsIgnoreCase(
					IConstants.VILLAGE)) {
				Hamlet hamlet = hamletDAO.get(problemImpactLevelValue);
				return hamlet.getHamletName();
			}
		}

		return result;
	}

	*//**
	 * To Get The Classified Problem From DB OF A Particular User
	 *//*
	public List<ProblemBeanVO> getClassifiedProblemsOfUser(Long userId,
			Long statusId) {
		List<ProblemBeanVO> problemsFromDB = new ArrayList<ProblemBeanVO>();
		ProblemBeanVO problemFromDB = null;
		List<ProblemHistory> problemHistories = problemHistoryDAO
				.findProblemLocationsByUserId(userId, statusId);
		List<SelectOptionVO> departmentVOs = null;
		Date iDate;
		for (ProblemHistory problemHistory : problemHistories) {
			if (!(problemHistory.getIsDelete() != null && problemHistory
					.getIsDelete().toString().equalsIgnoreCase("true"))) {
				problemFromDB = new ProblemBeanVO();
				problemFromDB.setProblemHistoryId(problemHistory
						.getProblemHistoryId());
				problemFromDB
						.setProblem(problemHistory.getProblemLocation()
								.getProblemAndProblemSource().getProblem()
								.getProblem());
				iDate = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getProblem()
						.getIdentifiedOn();
				problemFromDB.setReportedDate(sdf.format(iDate));
				// problemFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());
				problemFromDB.setAddress(getLocationDetails(problemHistory
						.getProblemLocation().getProblemImpactLevel()
						.getRegionScopesId(), problemHistory
						.getProblemLocation().getProblemImpactLevelValue()));
				problemFromDB.setProblemSourceScope(problemHistory
						.getProblemSourceScope().getScope());
				problemFromDB.setProblemType(problemHistory
						.getProblemLocation().getProblemClassification()
						.getClassification());
				problemFromDB.setProblemLocationId(problemHistory
						.getProblemLocation().getProblemLocationId());
				problemsFromDB.add(problemFromDB);
			}
		}
		return problemsFromDB;
	}

	*//**
	 * To Get the List of all problems that are modified from New status to
	 * Classify status
	 *//*
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetClassifiedProblemDataIntoDB(
			List<ProblemBeanVO> problemsToUpdate) {
		
		problemBeanVOs = problemsToUpdate;
		List<ProblemBeanVO> updatedProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						ProblemBackup problem;
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
						try {
							for (ProblemBeanVO problemBeanVO : problemBeanVOs) {
								departmentVOs = new ArrayList<SelectOptionVO>();
								problemBeanFromDB = new ProblemBeanVO();
								problemLocation = problemLocationDAO
										.get(problemBeanVO
												.getProblemLocationId());
								problemStatus = problemStatusDAO
										.get(problemBeanVO.getProblemStatusId());
								problemSourceScope = problemSourceScopeDAO
										.findBySourceScope(
												problemBeanVO
														.getProblemSourceScope())
										.get(0);
								problemClassification = problemClassificationDAO
										.findByClassification(
												problemBeanVO
														.getProblemClassification())
										.get(0);

								problem = problemLocation
										.getProblemAndProblemSource()
										.getProblem();
								problem.setDescription(problemBeanVO
										.getDescription());
								problem.setProblem(problemBeanVO.getProblem());

								problemLocation.getProblemAndProblemSource()
										.setProblem(problem);
								problemLocation
										.setProblemClassification(problemClassification);

								problemHistory = problemHistoryDAO
										.get(problemBeanVO
												.getProblemHistoryId());
								problemHistory.setIsDelete("true");
								problemHistory = problemHistoryDAO
										.save(problemHistory);

								classifiedProblemData = new ProblemHistory();
								classifiedProblemData
										.setProblemLocation(problemLocation);
								classifiedProblemData
										.setProblemStatus(problemStatus);
								classifiedProblemData
										.setProblemSourceScope(problemSourceScope);
								classifiedProblemData
										.setDateUpdated(getCurrentDateAndTime());
								problemHistory = problemHistoryDAO
										.save(classifiedProblemData);

								problemBeanFromDB
										.setProblemHistoryId(problemHistory
												.getProblemHistoryId());
								problemBeanFromDB.setProblem(problemHistory
										.getProblemLocation()
										.getProblemAndProblemSource()
										.getProblem().getProblem());
								Date iDateOfAddNewProb = problemHistory
										.getProblemLocation()
										.getProblemAndProblemSource()
										.getProblem().getIdentifiedOn();
								problemBeanFromDB.setReportedDate(sdf
										.format(iDateOfAddNewProb));
								// problemBeanFromDB.setAddress(problemHistory.getProblemLocation().getHamlet().getHamletName());

								problemBeanFromDB
										.setAddress(getLocationDetails(
												problemHistory
														.getProblemLocation()
														.getProblemImpactLevel()
														.getRegionScopesId(),
												problemHistory
														.getProblemLocation()
														.getProblemImpactLevelValue()));
								problemBeanFromDB
										.setProblemSourceScope(problemHistory
												.getProblemSourceScope()
												.getScope());
								problemBeanFromDB.setProblemType(problemHistory
										.getProblemLocation()
										.getProblemClassification()
										.getClassification());
								problemBeanFromDB
										.setProblemLocationId(problemHistory
												.getProblemLocation()
												.getProblemLocationId());
								updatedProblems.add(problemBeanFromDB);
							}

						} catch (Exception e) {
							status.setRollbackOnly();
							if (log.isDebugEnabled()) {
								log
										.debug(
												"Exception Raised while Update And Get Problems Under Pending::",
												e);
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

	public List<SelectOptionVO> getDepartmentsForProblemScope(
			String problemScope) {

		List<SelectOptionVO> departmentsByScope = new ArrayList<SelectOptionVO>();
		try {
			List<ProblemSourceScopeConcernedDepartment> deptsByScope = problemSourceScopeConcernedDepartmentDAO
					.findDepartmentsByScope(problemScope);
			if (deptsByScope == null) {
				log
						.debug("No Departments have been retrieved for the selected scope");
			}

			for (ProblemSourceScopeConcernedDepartment problemSourceScopeConcnedDept : deptsByScope)
				departmentsByScope.add(new SelectOptionVO(
						problemSourceScopeConcnedDept
								.getProblemSourceScopeConcernedDepartmentId(),
						problemSourceScopeConcnedDept.getDepartment()));
			if (departmentsByScope.size() > 0)
				departmentsByScope.add(0, new SelectOptionVO(0l,
						"Select Department"));
		} catch (Exception e) {
			log.debug("Exception Encountered", e);
		}
		return departmentsByScope;

	}

	*//**
	 * To Get the List of all problems that are modified from classify status to
	 * Assigned status
	 *//*
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetAssignedProblems(
			List<ProblemBeanVO> problemsToAssign) {
		this.problemBeanVOs = problemsToAssign;
		List<ProblemBeanVO> assignedProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						List<ProblemBeanVO> assignedProblems = new ArrayList<ProblemBeanVO>();
						ResultStatus resultStatus = new ResultStatus();
						ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment = null;
						ProblemHistory problemHistory = null;
						ProblemStatus problemStatus = null;
						AssignedProblemProgress assignedProblemProgress = null;
						ProblemHistory assignedProblemData = null;
						try {
							for (ProblemBeanVO problemBeanVO : problemBeanVOs) {
								problemSourceScopeConcernedDepartment = problemSourceScopeConcernedDepartmentDAO
										.findByDepartmentAndScope(
												problemBeanVO.getDepartment(),
												problemBeanVO
														.getProblemSourceScope())
										.get(0);
								problemStatus = problemStatusDAO
										.get(problemBeanVO.getProblemStatusId());
								problemHistory = problemHistoryDAO
										.get(problemBeanVO
												.getProblemHistoryId());

								assignedProblemData = new ProblemHistory();
								assignedProblemData
										.setProblemLocation(problemHistory
												.getProblemLocation());
								assignedProblemData
										.setProblemStatus(problemStatus);
								assignedProblemData
										.setProblemSourceScope(problemHistory
												.getProblemSourceScope());
								assignedProblemData
										.setDateUpdated(getCurrentDateAndTime());
								assignedProblemData = problemHistoryDAO
										.save(assignedProblemData);

								problemHistory.setIsDelete("true");
								problemHistory = problemHistoryDAO
										.save(problemHistory);

								assignedProblemProgress = new AssignedProblemProgress();
								assignedProblemProgress
										.setProblemHistory(assignedProblemData);
								assignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(problemSourceScopeConcernedDepartment);
								assignedProblemProgress
										.setDescription(problemBeanVO
												.getComments());
								assignedProblemProgress = assignedProblemProgressDAO
										.save(assignedProblemProgress);
								problemBeanVO
										.setAssignedProblemProgressId(assignedProblemProgress
												.getAssignedProblemProgressId());
								problemBeanVO
										.setProblem(assignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemAndProblemSource()
												.getProblem().getProblem());
								problemBeanVO
										.setDepartment(assignedProblemProgress
												.getProblemSourceScopeConcernedDepartment()
												.getDepartment());
								problemBeanVO
										.setProblemLocationId(assignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemLocationId());
								assignedProblems.add(problemBeanVO);
							}
						} catch (Exception e) {
							status.setRollbackOnly();
							if (log.isDebugEnabled()) {
								log
										.debug(
												"Exception Raised while Update And Get Problems Under Pending::",
												e);
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
	public List<ProblemBeanVO> updateAndGetProblemsUnderProgress(
			List<ProblemBeanVO> problemsInProgress) {
		problemBeanVOs = problemsInProgress;
		List<ProblemBeanVO> problemsInProgressFromDB = (List<ProblemBeanVO>) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						List<ProblemBeanVO> progressInProblems = new ArrayList<ProblemBeanVO>();
						ResultStatus resultStatus = new ResultStatus();
						AssignedProblemProgress assignedProblemProgress = null;
						ProblemStatus problemStatus = null;
						ProblemHistory progressProblemData = null;
						AssignedProblemProgress newAssignedProblemProgress = null;
						try {
							for (ProblemBeanVO problemBeanVO : problemBeanVOs) {
								problemStatus = problemStatusDAO
										.get(problemBeanVO.getProblemStatusId());
								assignedProblemProgress = assignedProblemProgressDAO
										.get(problemBeanVO
												.getAssignedProblemProgressId());

								assignedProblemProgress.getProblemHistory()
										.setIsDelete("true");
								assignedProblemProgress = assignedProblemProgressDAO
										.save(assignedProblemProgress);

								progressProblemData = new ProblemHistory();
								progressProblemData
										.setProblemLocation(assignedProblemProgress
												.getProblemHistory()
												.getProblemLocation());
								progressProblemData
										.setProblemStatus(problemStatus);
								progressProblemData
										.setProblemSourceScope(assignedProblemProgress
												.getProblemHistory()
												.getProblemSourceScope());
								progressProblemData
										.setDateUpdated(getCurrentDateAndTime());
								progressProblemData.setComments(problemBeanVO
										.getComments());
								progressProblemData = problemHistoryDAO
										.save(progressProblemData);

								newAssignedProblemProgress = new AssignedProblemProgress();
								newAssignedProblemProgress
										.setConcernedPersonName(problemBeanVO
												.getDepartmentConcernedPersonName());
								newAssignedProblemProgress
										.setDesignation(problemBeanVO
												.getDesignation());
								newAssignedProblemProgress
										.setContactNo(problemBeanVO
												.getDepartmentConcernedPersonPhoneNumber());
								newAssignedProblemProgress
										.setDescription(problemBeanVO
												.getComments());
								newAssignedProblemProgress
										.setProblemHistory(progressProblemData);
								newAssignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(assignedProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								newAssignedProblemProgress = assignedProblemProgressDAO
										.save(newAssignedProblemProgress);

								problemBeanVO
										.setAssignedProblemProgressId(newAssignedProblemProgress
												.getAssignedProblemProgressId());
								problemBeanVO
										.setProblem(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemAndProblemSource()
												.getProblem().getProblem());
								Date iDate = newAssignedProblemProgress
										.getProblemHistory()
										.getProblemLocation()
										.getProblemAndProblemSource()
										.getProblem().getIdentifiedOn();
								problemBeanVO
										.setReportedDate(sdf.format(iDate));
								problemBeanVO
										.setDepartmentConcernedPersonName(newAssignedProblemProgress
												.getConcernedPersonName());
								problemBeanVO
										.setDesignation(newAssignedProblemProgress
												.getDesignation());
								problemBeanVO
										.setContactNo(newAssignedProblemProgress
												.getContactNo());
								problemBeanVO.setComments(problemBeanVO
										.getComments());
								problemBeanVO.setReasonForPending(problemBeanVO
										.getComments());
								problemBeanVO
										.setProblemLocationId(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemLocationId());
								progressInProblems.add(problemBeanVO);

							}

						} catch (Exception e) {
							status.setRollbackOnly();
							resultStatus.setExceptionEncountered(e);
							if (log.isDebugEnabled()) {
								log
										.debug(
												"Exception Raised while Update And Get Problems Under Pending::",
												e);
							}
							resultStatus.setResultPartial(true);
						}
						problemBeanVOs = null;
						return progressInProblems;
					}
				});
		return problemsInProgressFromDB;
	}

	public List<ProblemBeanVO> getProblemsUnderProgress(Long userId,
			Long statusId) {
		List<AssignedProblemProgress> problemsUnderProgress = assignedProblemProgressDAO
				.findByRegistrationIdAndStatusId(userId, statusId);
		List<ProblemBeanVO> problemsUnderProgressFromDB = new ArrayList<ProblemBeanVO>();
		for (AssignedProblemProgress problemProgerss : problemsUnderProgress) {
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setAssignedProblemProgressId(problemProgerss
					.getAssignedProblemProgressId());
			problemBeanVO.setProblem(problemProgerss.getProblemHistory()
					.getProblemLocation().getProblemAndProblemSource()
					.getProblem().getProblem());
			Date iDate = problemProgerss.getProblemHistory()
					.getProblemLocation().getProblemAndProblemSource()
					.getProblem().getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));
			problemBeanVO
					.setDepartment(problemProgerss
							.getProblemSourceScopeConcernedDepartment()
							.getDepartment());
			problemBeanVO.setDepartmentConcernedPersonName(problemProgerss
					.getConcernedPersonName());
			problemBeanVO.setDesignation(problemProgerss.getDesignation());
			problemBeanVO.setContactNo(problemProgerss.getContactNo());
			problemBeanVO.setProblemLocationId(problemProgerss
					.getProblemHistory().getProblemLocation()
					.getProblemLocationId());
			problemsUnderProgressFromDB.add(problemBeanVO);
		}
		return problemsUnderProgressFromDB;
	}

	
	 * This method will take the problems list which are migrated from progress
	 * state to pending state and updates the database
	 
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetProblemsUnderPending(
			List<ProblemBeanVO> pendingProblems) {
		problemBeanVOs = pendingProblems;
		List<ProblemBeanVO> pendingProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate
				.execute(new TransactionCallback() {
					@SuppressWarnings("deprecation")
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultStatus = new ResultStatus();
						List<ProblemBeanVO> pendingProblems = new ArrayList<ProblemBeanVO>();
						ProblemStatus problemStatus = null;
						AssignedProblemProgress assignedProblemProgress = null;
						ProblemHistory pendingProblemData = null;
						AssignedProblemProgress newAssignedProblemProgress = null;
						try {
							for (ProblemBeanVO problemBeanVO : problemBeanVOs) {
								problemStatus = problemStatusDAO
										.get(problemBeanVO.getProblemStatusId());
								assignedProblemProgress = assignedProblemProgressDAO
										.get(problemBeanVO
												.getAssignedProblemProgressId());
								assignedProblemProgress.getProblemHistory()
										.setIsDelete("true");
								assignedProblemProgress = assignedProblemProgressDAO
										.save(assignedProblemProgress);
								pendingProblemData = new ProblemHistory();
								pendingProblemData
										.setProblemLocation(assignedProblemProgress
												.getProblemHistory()
												.getProblemLocation());
								pendingProblemData
										.setProblemStatus(problemStatus);
								pendingProblemData
										.setProblemSourceScope(assignedProblemProgress
												.getProblemHistory()
												.getProblemSourceScope());
								pendingProblemData
										.setDateUpdated(getCurrentDateAndTime());
								pendingProblemData.setComments(problemBeanVO
										.getReasonForPending());
								pendingProblemData = problemHistoryDAO
										.save(pendingProblemData);

								newAssignedProblemProgress = new AssignedProblemProgress();
								newAssignedProblemProgress
										.setConcernedPersonName(problemBeanVO
												.getDepartmentConcernedPersonName());
								newAssignedProblemProgress
										.setDesignation(problemBeanVO
												.getDesignation());
								newAssignedProblemProgress
										.setContactNo(problemBeanVO
												.getDepartmentConcernedPersonPhoneNumber());
								newAssignedProblemProgress
										.setDescription(problemBeanVO
												.getReasonForPending());
								newAssignedProblemProgress
										.setProblemHistory(pendingProblemData);
								newAssignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(assignedProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								newAssignedProblemProgress = assignedProblemProgressDAO
										.save(newAssignedProblemProgress);

								problemBeanVO
										.setAssignedProblemProgressId(newAssignedProblemProgress
												.getAssignedProblemProgressId());
								problemBeanVO
										.setProblem(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemAndProblemSource()
												.getProblem().getProblem());
								Date iDate = newAssignedProblemProgress
										.getProblemHistory()
										.getProblemLocation()
										.getProblemAndProblemSource()
										.getProblem().getIdentifiedOn();
								problemBeanVO
										.setReportedDate(sdf.format(iDate));
								problemBeanVO
										.setDepartmentConcernedPersonName(newAssignedProblemProgress
												.getConcernedPersonName());
								problemBeanVO
										.setDesignation(newAssignedProblemProgress
												.getDesignation());
								problemBeanVO
										.setContactNo(newAssignedProblemProgress
												.getContactNo());
								Date pDate = newAssignedProblemProgress
										.getProblemHistory().getDateUpdated();
								problemBeanVO.setUpdatedDate(sdf.format(pDate));
								problemBeanVO.setComments(problemBeanVO
										.getComments());
								problemBeanVO.setReasonForPending(problemBeanVO
										.getComments());
								problemBeanVO
										.setProblemLocationId(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemLocationId());
								pendingProblems.add(problemBeanVO);
							}
						} catch (Exception e) {
							status.setRollbackOnly();
							e.printStackTrace();
							if (log.isDebugEnabled()) {
								log
										.debug(
												"Exception Raised while Update And Get Problems Under Pending::",
												e);
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

	
	 * To get all the problems under pending state
	 
	public List<ProblemBeanVO> getPendingProblemsForAnUser(Long userId,
			Long statusId) {
		List<ProblemBeanVO> pendingProblemsFromDB = new ArrayList<ProblemBeanVO>();
		List<AssignedProblemProgress> pendingProblems = assignedProblemProgressDAO
				.findByRegistrationIdAndStatusId(userId, statusId);
		ProblemBeanVO problemBeanVO = null;
		for (AssignedProblemProgress pendingProblem : pendingProblems) {
			problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setAssignedProblemProgressId(pendingProblem
					.getAssignedProblemProgressId());
			problemBeanVO.setProblem(pendingProblem.getProblemHistory()
					.getProblemLocation().getProblemAndProblemSource()
					.getProblem().getProblem());
			Date iDate = pendingProblem.getProblemHistory()
					.getProblemLocation().getProblemAndProblemSource()
					.getProblem().getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));
			problemBeanVO
					.setDepartment(pendingProblem
							.getProblemSourceScopeConcernedDepartment()
							.getDepartment());
			problemBeanVO.setDepartmentConcernedPersonName(pendingProblem
					.getConcernedPersonName());
			problemBeanVO.setDesignation(pendingProblem.getDesignation());
			problemBeanVO.setContactNo(pendingProblem.getContactNo());
			Date pDate = pendingProblem.getProblemHistory().getDateUpdated();
			problemBeanVO.setUpdatedDate(sdf.format(pDate));
			problemBeanVO.setReasonForPending(pendingProblem
					.getProblemHistory().getComments());
			problemBeanVO.setProblemLocationId(pendingProblem
					.getProblemHistory().getProblemLocation()
					.getProblemLocationId());
			pendingProblemsFromDB.add(problemBeanVO);
		}
		return pendingProblemsFromDB;
	}

	
	 * To get all the problem under assgined state
	 
	public List<ProblemBeanVO> getAssignedProblems(Long userId,
			Long statusId) {
		List<ProblemBeanVO> assignedProblemsFromDB = new ArrayList<ProblemBeanVO>();
		List<AssignedProblemProgress> assignedProblems = assignedProblemProgressDAO
				.findByRegistrationIdAndStatusId(userId, statusId);
		ProblemBeanVO problemBeanVO = null;
		for (AssignedProblemProgress assignedProblem : assignedProblems) {
			if (!(assignedProblem.getProblemHistory().getIsDelete() != null && assignedProblem
					.getProblemHistory().getIsDelete().equalsIgnoreCase("true"))) {
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setAssignedProblemProgressId(assignedProblem
						.getAssignedProblemProgressId());
				problemBeanVO.setProblem(assignedProblem.getProblemHistory()
						.getProblemLocation().getProblemAndProblemSource()
						.getProblem().getProblem());
				problemBeanVO.setDepartment(assignedProblem
						.getProblemSourceScopeConcernedDepartment()
						.getDepartment());
				problemBeanVO.setProblemLocationId(assignedProblem
						.getProblemHistory().getProblemLocation()
						.getProblemLocationId());
				assignedProblemsFromDB.add(problemBeanVO);
			}
		}
		return assignedProblemsFromDB;
	}

	
	 * This method takes problems migrated from progress state to fixed state
	 * and updates the records in the database
	 
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> updateAndGetProblemsUnderFixed(
			List<ProblemBeanVO> problemsFixed) {
		problemBeanVOs = problemsFixed;
		List<ProblemBeanVO> fixedProblemsFromDB = (List<ProblemBeanVO>) transactionTemplate
				.execute(new TransactionCallback() {
					@SuppressWarnings("deprecation")
					public Object doInTransaction(TransactionStatus status) {
						ResultStatus resultStatus = new ResultStatus();
						List<ProblemBeanVO> fixedProblems = new ArrayList<ProblemBeanVO>();
						ProblemStatus problemStatus = null;
						AssignedProblemProgress assignedProblemProgress = null;
						ProblemHistory fixedProblemData = null;
						try {
							for (ProblemBeanVO problemBeanVO : problemBeanVOs) {
								problemStatus = problemStatusDAO
										.get(problemBeanVO.getProblemStatusId());
								assignedProblemProgress = assignedProblemProgressDAO
										.get(problemBeanVO
												.getAssignedProblemProgressId());

								assignedProblemProgress.getProblemHistory()
										.setIsDelete("true");
								assignedProblemProgress.getProblemHistory()
										.setComments(
												problemBeanVO.getComments());
								Date problemFixedDate = sdf.parse(problemBeanVO
										.getUpdatedDate());
								assignedProblemProgress.getProblemHistory()
										.setDateUpdated(problemFixedDate);
								assignedProblemProgress = assignedProblemProgressDAO
										.save(assignedProblemProgress);

								fixedProblemData = new ProblemHistory();
								fixedProblemData
										.setProblemLocation(assignedProblemProgress
												.getProblemHistory()
												.getProblemLocation());
								fixedProblemData
										.setProblemStatus(problemStatus);
								fixedProblemData
										.setProblemSourceScope(assignedProblemProgress
												.getProblemHistory()
												.getProblemSourceScope());
								fixedProblemData
										.setDateUpdated(getCurrentDateAndTime());
								fixedProblemData.setComments(problemBeanVO
										.getComments());
								fixedProblemData = problemHistoryDAO
										.save(fixedProblemData);

								AssignedProblemProgress newAssignedProblemProgress = new AssignedProblemProgress();
								newAssignedProblemProgress
										.setConcernedPersonName(problemBeanVO
												.getDepartmentConcernedPersonName());
								newAssignedProblemProgress
										.setDesignation(problemBeanVO
												.getDesignation());
								newAssignedProblemProgress
										.setContactNo(problemBeanVO
												.getDepartmentConcernedPersonPhoneNumber());
								newAssignedProblemProgress
										.setDescription(problemBeanVO
												.getComments());
								newAssignedProblemProgress
										.setProblemHistory(fixedProblemData);
								newAssignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(assignedProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								newAssignedProblemProgress = assignedProblemProgressDAO
										.save(newAssignedProblemProgress);

								problemBeanVO
										.setAssignedProblemProgressId(newAssignedProblemProgress
												.getAssignedProblemProgressId());
								problemBeanVO
										.setProblem(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemAndProblemSource()
												.getProblem().getProblem());
								Date iDate = newAssignedProblemProgress
										.getProblemHistory()
										.getProblemLocation()
										.getProblemAndProblemSource()
										.getProblem().getIdentifiedOn();
								problemBeanVO
										.setReportedDate(sdf.format(iDate));
								Date fDate = assignedProblemProgress
										.getProblemHistory().getDateUpdated();
								problemBeanVO.setUpdatedDate(sdf.format(fDate));
								problemBeanVO
										.setProblemLocationId(newAssignedProblemProgress
												.getProblemHistory()
												.getProblemLocation()
												.getProblemLocationId());
								fixedProblems.add(problemBeanVO);
							}
						} catch (Exception e) {
							status.setRollbackOnly();
							if (log.isDebugEnabled()) {
								log
										.debug(
												"Exception Raised while Update And Get Problems Under Fixed::",
												e);
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

	
	 * To Get all Fixed Probelm's Details
	 
	public List<ProblemBeanVO> getFixedProblemsForUser(Long userId,
			Long statusId) {
		List<ProblemBeanVO> fixedProblemsFromDB = new ArrayList<ProblemBeanVO>();
		List<AssignedProblemProgress> problemsUnderFixed = assignedProblemProgressDAO
				.findByRegistrationIdAndStatusId(userId, statusId);
		for (AssignedProblemProgress problemsFixed : problemsUnderFixed) {
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setAssignedProblemProgressId(problemsFixed
					.getAssignedProblemProgressId());
			problemBeanVO.setProblem(problemsFixed.getProblemHistory()
					.getProblemLocation().getProblemAndProblemSource()
					.getProblem().getProblem());
			Date iDate = problemsFixed.getProblemHistory().getProblemLocation()
					.getProblemAndProblemSource().getProblem()
					.getIdentifiedOn();
			problemBeanVO.setReportedDate(sdf.format(iDate));
			problemBeanVO
					.setDepartment(problemsFixed
							.getProblemSourceScopeConcernedDepartment()
							.getDepartment());
			Date fDate = problemsFixed.getProblemHistory().getDateUpdated();
			problemBeanVO.setUpdatedDate(sdf.format(fDate));
			problemBeanVO.setComments(problemsFixed.getProblemHistory()
					.getComments());
			problemBeanVO.setProblemLocationId(problemsFixed
					.getProblemHistory().getProblemLocation()
					.getProblemLocationId());
			fixedProblemsFromDB.add(problemBeanVO);
		}
		return fixedProblemsFromDB;
	}

	
	 * To convert the date that is retrived from DB to dd/MM/yyyy HH:mm:ss
	 
	public Date getCurrentDateAndTime() {
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
		for (ProblemImpactLevel problemImpactLevel : list) {
			problemImpacts.add(new SelectOptionVO(problemImpactLevel
					.getProblemImpactLevelId(), problemImpactLevel
					.getProblemImpactLevel()));
		}
		return problemImpacts;
	}

	
	 * (non-Javadoc)
	 * 
	 * @seecom.itgrids.partyanalyst.service.IProblemManagementService#
	 * getProblemDetailsBasedOnProblemStatusForAUser(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String) Method that retrieves
	 * problems data of a particular status(like NEW,ASSIGNED,FIXED ...) posted
	 * by a user
	 
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> getProblemDetailsBasedOnProblemStatusForAUser(
			Long userId, Long statusId, String isPushed, String isDeleted) {

		List<ProblemBeanVO> problemsResultList = new ArrayList<ProblemBeanVO>();

		try {
			List resultsList = null;
			if (statusId == null || statusId.equals(0L))
				resultsList = problemHistoryDAO
						.getProblemHistoryByProblemStatusForAUser(userId,
								statusId, isPushed, isDeleted); // results
																// filtered
																// based on
																// status (like
																// NEW,ASSIGNED,FIXED
																// ...)
			else
				resultsList = problemHistoryDAO.getProblemHistoryForAUser(
						userId, isPushed, isDeleted); // get all results
														// irrespective of
														// status

			if (resultsList != null && resultsList.size() > 0) {
				problemsResultList = getProcessedProblemResultsList(
						resultsList, userId, statusId);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ProblemBeanVO resultStatus = new ProblemBeanVO();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setExceptionClass(ex.getClass().toString());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);

			problemsResultList.add(resultStatus);
		}

		return problemsResultList;
	}

	
	 * Method to process posted problems results list and set details to VO
	 
	@SuppressWarnings("unchecked")
	public List<ProblemBeanVO> getProcessedProblemResultsList(List resultsList,
			Long userId, Long statusId) {

		List<ProblemBeanVO> problemsResultList = null;
		if (resultsList != null && resultsList.size() > 0) {

			problemsResultList = new ArrayList<ProblemBeanVO>();
			Iterator resultsIterator = resultsList.listIterator();
			while (resultsIterator.hasNext()) {

				ProblemBeanVO beanVO = new ProblemBeanVO();
				Object[] values = (Object[]) resultsIterator.next();

				// to get problem location
				Long problemImpactLevelId = (Long) values[0];
				Long problemImpactLevelValue = (Long) values[1];
				String locationName = getLocationDetails(problemImpactLevelId,
						problemImpactLevelValue);

				Long problemId = (Long) values[5];
				String problem = (String) values[8];
				String isDelete = (String) values[11];
				String isApproved = (String) values[6];
				String description = (String) values[7];

				Date identifiedOn = (Date) values[9];
				Date existingFrom = (Date) values[10];

				Long problemStatusId = (Long) values[2];
				String problemStatus = (String) values[4];
				Long problemLocationId = (Long) values[12];

				// setting values to VO
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
	
	public ProblemBeanVO getProblemCompleteInfoOfAFreeUserProblem(Long problemHistoryId, Long userId) 
	{
		try{
		
			ProblemBeanVO problemBeanVO = getProblemCompleteInfo(problemHistoryId);
			Long freeUserId = null;
			
			if(problemBeanVO != null && problemBeanVO.getExceptionEncountered() == null)
			{
				if(problemBeanVO.getIsApproved() != null && 
						problemBeanVO.getIsApproved().equalsIgnoreCase(IConstants.TRUE))
					return problemBeanVO;
				else
				{
					//freeUserId = problemHistoryDAO.getFreeUserIdOfAProblem(problemHistoryId);
					freeUserId = userProblemDAO.getFreeUserIdOfAProblem(problemHistoryId);
					if(freeUserId!= null && freeUserId.longValue() != 0 && 
							userId != null && userId.longValue() != 0 && freeUserId.equals(userId))
							return problemBeanVO;
					else
						return null;
				}
			}
			
			else
				return null;
			
		}catch (Exception e) {
			log.error("Exception Occured in getProblemCompleteInfoOfAFreeUserProblem() Method - "+e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ProblemBeanVO getProblemCompleteInfo(Long problemHistoryId) {
		if (log.isDebugEnabled())
			log.info("Entered in to getProblemCompleteInfo");
		if (log.isDebugEnabled())
			log.debug("problem History Id:" + problemHistoryId);

		ProblemBeanVO result = null;
		Date iDate, eDate;
		State state = null;
		District district = null;
		Constituency constituency = null;
		Tehsil tehsil = null;
		Hamlet hamlet = null;
		LocalElectionBody localBody = null;
		Constituency ward = null;
		Booth booth = null;
		try {
			//List list1 = problemHistoryDAO.findProblemCompleteInfo(problemHistoryId);
			List list1 = userProblemDAO.getAllProblemDetails(problemId);

			if (list1.size() != 0) {
				result = new ProblemBeanVO();
				
				Object[] parms = (Object[]) list1.get(0);
				iDate = (Date) parms[4];
				eDate = (Date) parms[8];
				result.setProblem(parms[0].toString());
				result.setDescription(parms[1].toString());
				result.setImpactLevel(parms[2].toString());
				result.setProblemImpactLevelId((Long) parms[10]);
				result.setIsApproved(parms[12] == null ? null : parms[12].toString());
				
				switch (result.getProblemImpactLevelId().intValue()) {

				case 2: {
					state = stateDAO.get((Long) parms[3]);
					result.setProblemLocation(state.getStateName());
					break;
				}
				case 3: {
					district = districtDAO.get((Long) parms[3]);
					result.setProblemLocation(district.getDistrictName());
					result.setState(district.getState().getStateName());
					break;
				}
				case 4: {
					constituency = constituencyDAO.get((Long) parms[3]);
					result.setProblemLocation(constituency.getName());
					if (IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency
							.getElectionScope().getElectionType()
							.getElectionType())) {
						result.setState(constituency.getState().getStateName());
					} else {
						result.setState(constituency.getState().getStateName());
						result.setDistrict(constituency.getDistrict()
								.getDistrictName()
								+ "(Dt.)");
					}
					break;
				}
				case 5: {
					tehsil = tehsilDAO.get((Long) parms[3]);
					result.setProblemLocation(tehsil.getTehsilName());
					result = setConstDistStateTOResult(tehsil.getTehsilId(),
							result);
					result.setTehsil(tehsil.getTehsilName());
					break;
				}
				case 6: {
					hamlet = hamletDAO.get((Long) parms[3]);
					result.setProblemLocation(hamlet.getHamletName());
					result = setConstDistStateTOResult(hamlet.getTownship()
							.getTehsil().getTehsilId(), result);
					result.setTehsil(hamlet.getTownship().getTehsil()
							.getTehsilName());
					break;
				}
				case 7: {
					localBody = localElectionBodyDAO.get((Long) parms[3]);
					result.setProblemLocation(localBody.getName());
					result.setDistrict(localBody.getDistrict()
							.getDistrictName());
					result.setState(localBody.getDistrict().getState()
							.getStateName());
					break;
				}
				case 8: {
					ward = constituencyDAO.get((Long) parms[3]);
					result.setProblemLocation(ward.getName());
					result.setLocalBody(ward.getLocalElectionBody().getName());
					result.setDistrict(ward.getLocalElectionBody()
							.getDistrict().getDistrictName());
					result.setState(ward.getLocalElectionBody().getDistrict()
							.getState().getStateName());
					break;
				}
				case 9: {
					booth = boothDAO.get((Long) parms[3]);
					if (booth.getTehsil() != null) {
						result.setTehsil(booth.getTehsil().getTehsilName());
						result = setConstDistStateTOResult(booth.getTehsil()
								.getTehsilId(), result);
					} else if (booth.getLocalBody() != null) {
						if (booth.getBoothLocalBodyWard() != null)
							result.setWard(booth.getBoothLocalBodyWard()
									.getLocalBodyWard().getName());
						result.setLocalBody(booth.getLocalBody().getName());
						result.setDistrict(booth.getLocalBody().getDistrict()
								.getDistrictName());
						result.setState(booth.getLocalBody().getDistrict()
								.getState().getStateName());

					}
					result.setProblemLocation(booth.getPartName()
							+ booth.getLocation());
					break;
				}
				default:
					System.out.println("Invalid Scope.");
					break;
				}
				result.setPostedDate(sdf.format(iDate));
				result.setName(parms[5].toString());
				result.setProblemId((Long) parms[6]);
				result.setProblemHistoryId((Long) parms[7]);
				result.setExistingFrom(sdf.format(eDate));
				result.setStatus(parms[9].toString());
				result.setLastName(parms[11] != null?parms[11].toString():"");
				result.setProblemLocationId((Long) parms[3]);
			}
		} catch (Exception e) {
			log.error(e);
			result = new ProblemBeanVO();
			result.setExceptionMsg("Exception raised when retriecing problem complete info");
			result.setExceptionEncountered(e);
			return null;
		}

		return result;
	}

	private ProblemBeanVO setConstDistStateTOResult(Long tehsilId,
			ProblemBeanVO result) {
		List stateDistConstMandal = delimitationConstituencyMandalDAO
				.getStateDistConstituencyMandalByMandalID(tehsilId);
		Object[] objVO = (Object[]) stateDistConstMandal.get(0);
		result.setState(objVO[1].toString());
		result.setDistrict(objVO[3].toString());
		result.setConstituency(objVO[5].toString());

		return result;
	}

	*//**
	 * Method To Get Problems Posted For A User Based On Problem Status And
	 * Count (represents how many problems based on startIndex and endIndex)
	 * 
	 * @author Sai Krishna
	 * 
	 * @param UserId
	 * @param ProblemStatusId
	 * @params startIndex,maxIndex
	 * @return ProblemsOfUserVO
	 *//*
	public ProblemsOfUserVO getProblemsForAUserBasedOnStatusAndCount(
			Long userId, Long statusId, Integer startIndex, Integer maxIndex) {

		if (log.isDebugEnabled())
			log.debug(" Entered Into Method To Get Problems For An User .. ");

		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus resultStatus = new ResultStatus();

		try {

			List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();

			// DAO call to get ProblemHistory objects
			List<ProblemHistory> problemHistoryList = problemHistoryDAO
					.getRecentPostedProblemsForAUserByCount(userId, statusId,
							startIndex, maxIndex);
			if (problemHistoryList != null && problemHistoryList.size() > 0)
				problemBeanVOLst = getUserProblems(problemHistoryList);

			problemsOfUserVO.setProblemsByUser(problemBeanVOLst);

			// DAO call to get total Results Count
			Long totalProblemsCount = problemHistoryDAO
					.getRecentPostedProblemsCountForAUserByProblemStatus(
							userId, statusId);
			problemsOfUserVO.setTotalResultsCount(totalProblemsCount);

		} catch (Exception ex) {

			log.error("Exception Raised While Retrieving Problems For A User :"
					+ ex);
			ex.printStackTrace();

			resultStatus.setExceptionEncountered(ex);
			resultStatus.setExceptionMsg(ex.getMessage());
			resultStatus.setResultPartial(true);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);

			problemsOfUserVO.setResultStatus(resultStatus);
		}

		return problemsOfUserVO;
	}

	*//**
	 * Method Get Problems Problems Overall Information for a user to build
	 * chart
	 * 
	 * @param userId
	 * @param problemStatusId
	 * @param startIndex
	 * @param maxIndex
	 * @return ProblemManagementChartVO
	 *//*
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(
			Long userId, Long statusId, Integer startIndex, Integer maxIndex,
			String type) {

		if (log.isDebugEnabled())
			log.debug("Started Getting User Problems Overall Information ..");

		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		List<SelectOptionVO> chartLegends = new ArrayList<SelectOptionVO>();
		ResultStatus rs = new ResultStatus();

		try {

			List problemsData = null;
			Long totalProblems = 0L;
			String chartDesc = "";

			if (type.equals(IConstants.PROBLEMS_BY_DATE)) {
				problemsData = problemHistoryDAO
						.getProblemsPostedForAUserInBetweenDates(userId,
								statusId, startIndex, maxIndex);
				totalProblems = problemHistoryDAO
						.getProblemsCountPostedByAUserInBetweenDates(userId,
								statusId);
			} else if (type.equals(IConstants.PROBLEMS_BY_MONTH)) {
				problemsData = problemHistoryDAO
						.getProblemsPostedForAUserInBetweenMonths(userId,
								statusId, startIndex, maxIndex);
				totalProblems = problemHistoryDAO
						.getProblemsCountPostedByAUserInBetweenMonths(userId,
								statusId);
			}

			if (problemsData != null && problemsData.size() > 0) {

				// Getting problem Status details
				ProblemStatus problemStatus = problemStatusDAO.get(statusId);
				chartLegends.add(new SelectOptionVO(problemStatus
						.getProblemStatusId(), problemStatus.getStatus()));

				// chart basic details
				String chartTitle = "Problem Details Of ".concat(
						problemStatus.getStatus()).concat(" Category");

				// set to VO
				problemManagementChartVO.setChartLegends(chartLegends);
				problemManagementChartVO.setChartTitle(chartTitle);
				problemManagementChartVO.setChartDesc(chartDesc);
				problemManagementChartVO.setStartIndex(new Long(startIndex));
				problemManagementChartVO.setMaxIndex(new Long(maxIndex));
				problemManagementChartVO.setTotalCount(new Long(totalProblems));

				// get problems complete data
				List<ProblemManagementChartDataVO> chartDataVO = getProcessedProblemsData(
						problemsData, type);
				problemManagementChartVO.setChartDataVO(chartDataVO);

			}

		} catch (Exception ex) {

			log.error("Exception Raised while Getting problems for a user :"
					+ ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);

			problemManagementChartVO.setResultStatus(rs);
		}

		return problemManagementChartVO;
	}

	*//**
	 * Method To Process the problems data
	 * 
	 * @param problemsData
	 * @return List<ProblemManagementChartDataVO>
	 *//*
	@SuppressWarnings("unchecked")
	public List<ProblemManagementChartDataVO> getProcessedProblemsData(
			List problemsData, String type) throws Exception {

		if (log.isDebugEnabled())
			log.debug(" Processing Problems Data ..");

		List<ProblemManagementChartDataVO> chartDataVO = new ArrayList<ProblemManagementChartDataVO>();

		Iterator lstItr = problemsData.listIterator();
		while (lstItr.hasNext()) {

			ProblemManagementChartDataVO chartVO = new ProblemManagementChartDataVO();

			Object[] values = (Object[]) lstItr.next();
			Long problemsCount = (Long) values[0];

			chartVO.setProblemsCount(new BigDecimal(problemsCount));

			// if problems are classified by date
			if (type.equals(IConstants.PROBLEMS_BY_DATE)) {

				Date problemDate = (Date) values[1];
				chartVO.setChartLabel(problemDate.toString());
			}

			else if (type.equals(IConstants.PROBLEMS_BY_MONTH)) {

				Integer postedMonth = (Integer) values[1];
				Integer postedYear = (Integer) values[2];
				String month = new DateFormatSymbols().getMonths()[postedMonth - 1];
				month = month + "-" + postedYear.toString();

				chartVO.setChartLabel(month);
			}

			// set to list
			chartDataVO.add(chartVO);
		}

		return chartDataVO;
	}

	*//**
	 * Method Get Problems Of All Categorys Overall Information for a user to
	 * build chart
	 * 
	 * @param userId
	 * @param problemStatusId
	 * @param startIndex
	 * @param maxIndex
	 * @return ProblemManagementChartVO
	 *//*
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getProblemsDataForAUserToBuildChart(
			Long userId, Integer startIndex, Integer maxIndex, String type) {

		if (log.isDebugEnabled())
			log.debug("Started Getting User Problems Overall Information ..");

		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		Map<String, Map<String, BigDecimal>> problemsData = new HashMap<String, Map<String, BigDecimal>>();
		List<SelectOptionVO> chartLegends = new ArrayList<SelectOptionVO>();
		ResultStatus rs = new ResultStatus();

		try {

			chartLegends = getAllDefaultProblemStatus();

			List<SelectOptionVO> problemCategorys = new ArrayList<SelectOptionVO>();
			problemCategorys.addAll(chartLegends);
			// chart basic details
			String chartTitle = "Problem Details Of All Categories";
			String chartDesc = "";
			List totalProblemsCount = null;

			// set to VO
			problemManagementChartVO.setChartLegends(chartLegends);
			problemManagementChartVO.setChartTitle(chartTitle);
			problemManagementChartVO.setChartDesc(chartDesc);
			problemManagementChartVO.setStartIndex(new Long(startIndex));
			problemManagementChartVO.setMaxIndex(new Long(maxIndex));

			List problemsDataList = null;

			// DAO call TO get Problems Data for all categorys
			if (type.equals(IConstants.PROBLEMS_BY_DATE)) {
				totalProblemsCount = problemHistoryDAO
						.getProblemsCountPostedByAUserInBetweenDates(userId);
			} else if (type.equals(IConstants.PROBLEMS_BY_MONTH)) {
				totalProblemsCount = problemHistoryDAO
						.getProblemsCountPostedByAUserInBetweenMonths(userId);
			}

			problemManagementChartVO.setTotalCount(new Long(totalProblemsCount
					.size()));

			for (SelectOptionVO problemCategory : chartLegends) {

				ProblemManagementChartVO problemsChartVO = getProblemsDataForAUserToBuildChart(
						userId, problemCategory.getId(), startIndex, maxIndex,
						type);
				if (problemsChartVO != null
						&& problemsChartVO.getChartDataVO() != null)
					getProblemsDataAssignedToMap(problemsChartVO
							.getChartDataVO(), problemCategory.getName(),
							problemsData, problemCategorys);
			}

			// Set Map Data To VO
			List<ProblemManagementChartDataVO> chartDataVO = processProblemDetailsMapAndSetToVO(problemsData);
			problemManagementChartVO.setChartDataVO(chartDataVO);

		} catch (Exception ex) {

			log.error("Exception Raised while Getting problems for a user :"
					+ ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);

			problemManagementChartVO.setResultStatus(rs);
		}

		return problemManagementChartVO;
	}

	private void getProblemsDataAssignedToMap(
			List<ProblemManagementChartDataVO> chartDataVO,
			String prblmCategory,
			Map<String, Map<String, BigDecimal>> problemsData,
			List<SelectOptionVO> problemCategorys) throws Exception {

		for (ProblemManagementChartDataVO chartData : chartDataVO) {

			if (problemsData.isEmpty()
					|| !problemsData.containsKey(chartData.getChartLabel())) {
				problemsData.put(chartData.getChartLabel(),
						setMapDefaultValues(problemCategorys));
			}

			Map<String, BigDecimal> chartProblemsMap = problemsData
					.get(chartData.getChartLabel());
			chartProblemsMap.put(prblmCategory, chartData.getProblemsCount());

			problemsData.put(chartData.getChartLabel(), chartProblemsMap);

		}

	}

	*//**
	 * Method To Process The Map And Set To VO
	 * 
	 * @param problemsData
	 * @return List<ProblemManagementChartDataVO>
	 *//*
	private List<ProblemManagementChartDataVO> processProblemDetailsMapAndSetToVO(
			Map<String, Map<String, BigDecimal>> problemsData) throws Exception {

		if (log.isDebugEnabled())
			log.debug(" Processing Map And Setting Problems To VO ..");

		List<ProblemManagementChartDataVO> problemsDataList = new ArrayList<ProblemManagementChartDataVO>();
		if (!problemsData.isEmpty()) {

			Set<String> mapKeys = problemsData.keySet();
			for (String problemDate : mapKeys) {

				ProblemManagementChartDataVO dataVO = new ProblemManagementChartDataVO();

				dataVO.setChartLabel(problemDate);
				Map<String, BigDecimal> data = problemsData.get(problemDate);
				List<BigDecimal> chartValues = new ArrayList<BigDecimal>();

				Set<String> chartKeys = data.keySet();

				for (String val : chartKeys) {
					BigDecimal count = data.get(val);
					chartValues.add(count);
				}

				dataVO.setChartValues(chartValues);

				problemsDataList.add(dataVO);
			}
		}

		return problemsDataList;
	}

	*//**
	 * 
	 * @param chartLegends
	 * @return Map<String,BigDecimal>
	 *//*
	private Map<String, BigDecimal> setMapDefaultValues(
			List<SelectOptionVO> chartLegends) {

		Map<String, BigDecimal> defaultMap = new HashMap<String, BigDecimal>();

		for (SelectOptionVO category : chartLegends) {
			defaultMap.put(category.getName(), new BigDecimal(0));
		}

		return defaultMap;
	}

	*//**
	 * Method to get All Problem Status Information
	 * 
	 * @return List<SelectOptionVO>
	 *//*
	public List<SelectOptionVO> getAllDefaultProblemStatus() {

		List<SelectOptionVO> problemStatusVO = new ArrayList<SelectOptionVO>();

		List<ProblemStatus> problemStatus = problemStatusDAO.getAll();
		if (problemStatus != null && problemStatus.size() > 0) {

			for (ProblemStatus pbStat : problemStatus) {

				problemStatusVO.add(new SelectOptionVO(pbStat
						.getProblemStatusId(), pbStat.getStatus()));
			}
		}

		return problemStatusVO;
	}

	*//**
	 * Method To Get Overall Problems Information In Different Stages Of Life
	 * Cycle
	 * 
	 * @param userId
	 * @return ProblemManagementChartVO
	 * 
	 *//*
	@SuppressWarnings("unchecked")
	public ProblemManagementChartVO getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(
			Long userId) {

		if (log.isDebugEnabled())
			log
					.debug("Entered Into Service Method TO Get Overall Problems Count In Different Stages ..");

		ProblemManagementChartVO problemManagementChartVO = new ProblemManagementChartVO();
		ResultStatus rs = new ResultStatus();

		try {

			Map<String, Map<Long, BigDecimal>> problemDetailsMap = new HashMap<String, Map<Long, BigDecimal>>();
			Map<Long, String> problemInfSources = new HashMap<Long, String>();

			// DAO call to get overall problems posted by an user
			List problemsList = userProblemDAO
					.getProblemsCountPostedByUserInDifferentLifeCycleStages(userId);
			if (problemsList != null && problemsList.size() > 0) {
				getProblemsAssignedToMap(problemsList, problemDetailsMap,
						problemInfSources);
				problemManagementChartVO = processTheMapAndSetResultsToVO(
						userId, problemDetailsMap, problemInfSources);
			}

		} catch (Exception ex) {

			log.error("Exception Raised :" + ex);
			ex.printStackTrace();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(rs.getExceptionMsg());
			rs.setResultCode(ResultCodeMapper.FAILURE);

			return problemManagementChartVO;
		}

		return problemManagementChartVO;
	}

	*//**
	 * Method To set Problem Details To Map
	 * 
	 * @param problemsList
	 * @param problemDetailsMap
	 *            Map<String,Map<Long,BigDecimal>>
	 *//*
	@SuppressWarnings("unchecked")
	private void getProblemsAssignedToMap(List problemsList,
			Map<String, Map<Long, BigDecimal>> problemDetailsMap,
			Map<Long, String> problemInfSources) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Started Setting Problems To Map ..");

		Iterator listItr = problemsList.listIterator();
		while (listItr.hasNext()) {

			Object[] values = (Object[]) listItr.next();

			Long problemsCount = (Long) values[0];
			Long problemStatusId = (Long) values[1];
			String problemStatus = (String) values[2];
			Long problemSourceId = (Long) values[3];
			String problemSource = (String) values[4];

			// if map is empty of result for particular source is N/A
			if (problemDetailsMap.isEmpty()
					|| !problemDetailsMap.containsKey(problemStatus)) {
				Map<Long, BigDecimal> problemSources = getDefaultInformationSources(problemInfSources);
				problemDetailsMap.put(problemStatus, problemSources);
			}

			// Set To Map
			Map<Long, BigDecimal> problemSources = problemDetailsMap
					.get(problemStatus);

			BigDecimal pblmsCount = problemSources.get(problemSourceId);
			pblmsCount = new BigDecimal(problemsCount);
			problemSources.put(problemSourceId, pblmsCount);
			problemDetailsMap.put(problemStatus, problemSources);

			// if map is empty of result for particular source is N/A
			
			 * if(problemInfSources.isEmpty() ||
			 * !problemInfSources.containsKey(problemSourceId))
			 * problemInfSources.put(problemSourceId, problemSource);
			 

		}
	}

	*//**
	 * 
	 * @return Map<Long,BigDecimal>
	 *//*
	@SuppressWarnings("unchecked")
	private Map<Long, BigDecimal> getDefaultInformationSources(
			Map<Long, String> problemInfSources) {

		Map<Long, BigDecimal> informationSourcesMap = new HashMap<Long, BigDecimal>();

		List inforSource = informationSourceDAO
				.getAllInformationSourceDetails();
		if (inforSource != null && inforSource.size() > 0) {

			Iterator lstItr = inforSource.listIterator();
			while (lstItr.hasNext()) {

				Object[] values = (Object[]) lstItr.next();

				Long infoSourceId = (Long) values[0];
				String infoSource = (String) values[1];

				informationSourcesMap.put(infoSourceId, new BigDecimal(0));
				problemInfSources.put(infoSourceId, infoSource);
				log.info("Available Information Source :" + infoSource);
			}
		}

		return informationSourcesMap;
	}

	*//**
	 * 
	 * @param problemDetailsMap
	 * @param problemInfSources
	 *//*
	private ProblemManagementChartVO processTheMapAndSetResultsToVO(
			Long userId, Map<String, Map<Long, BigDecimal>> problemDetailsMap,
			Map<Long, String> problemInfSources) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Processing The Map ..");

		ProblemManagementChartVO chartMainVO = new ProblemManagementChartVO();
		List<ProblemManagementChartDataVO> chartDataVO = new ArrayList<ProblemManagementChartDataVO>();

		Set<String> problemClassifications = problemDetailsMap.keySet();

		// Process different Problems Life Cycle Stages
		for (String prbStatus : problemClassifications) {

			ProblemManagementChartDataVO chartVO = new ProblemManagementChartDataVO();
			chartVO.setChartLabel(prbStatus);

			BigDecimal totProbsCount = new BigDecimal(0);
			Integer totLegendsCount = 0;

			Map<Long, BigDecimal> problemsMap = problemDetailsMap
					.get(prbStatus);

			Set<Long> chartLegends = problemsMap.keySet();
			totLegendsCount = chartLegends.size();

			List<BigDecimal> problemsCountLst = new ArrayList<BigDecimal>();

			// Process different scopes
			for (Long prblmSource : chartLegends) {

				totProbsCount = new BigDecimal(totProbsCount.longValue()
						+ problemsMap.get(prblmSource).longValue());
				problemsCountLst.add(problemsMap.get(prblmSource));

			}

			chartVO.setProblemsCount(totProbsCount);
			chartVO.setAvgCount(new BigDecimal(totProbsCount.longValue()
					/ totLegendsCount).setScale(2, BigDecimal.ROUND_HALF_UP));
			chartVO.setChartValues(problemsCountLst);

			chartDataVO.add(chartVO);
		}

		chartMainVO.setChartDataVO(chartDataVO);
		chartMainVO.setChartLegends(getChartLegendsFromSet(problemInfSources));

		User user = userDAO.get(userId);
		String userName = "";
		if (user.getFirstName() != null)
			userName = userName + user.getFirstName();
		if (user.getLastName() != null)
			userName = userName + " " + user.getLastName();
		chartMainVO.setChartTitle("Overall Problems Information Posted By "
				+ userName);

		return chartMainVO;
	}

	*//**
	 * Method To Set Chart Legends To List<SelectOptionVO>
	 * 
	 * @param chartLegends
	 * @return
	 *//*
	private List<SelectOptionVO> getChartLegendsFromSet(
			Map<Long, String> chartLegends) {

		List<SelectOptionVO> selectOptions = new ArrayList<SelectOptionVO>();

		Set<Long> keys = chartLegends.keySet();
		for (Long legend : keys) {
			selectOptions.add(new SelectOptionVO(legend, chartLegends
					.get(legend)));
		}

		return selectOptions;
	}

	*//**
	 * Method To Get Different User Problems Basic details
	 * 
	 * @author Sai Krishna
	 * @param userId
	 * @return ProblemsOfUserVO
	 *//*
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByDate(Long userId,
			Integer startIndex, Integer maxResults) {
		
		if (log.isDebugEnabled())
			log
					.debug("Entered Into Service Method To Get Different User Problems ..");

		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus rs = new ResultStatus();

		try {

			if (userId != null && !userId.equals(0L)) {

				// Get Problems Overall Info
				List<UserProblem> problemList = userProblemDAO
						.getProblemsPostedByUserInDifferentLifeCycleStagesByDate(
								userId, startIndex, maxResults);
				if (problemList != null
						&& problemList.size() > 0) {
					List<ProblemBeanVO> problemsByUser = setUserProblemToVO(problemList);
					problemsOfUserVO.setProblemsByUser(problemsByUser);
				}
				
				// Get Total Problems Count
				Long totalProblemsCount = problemHistoryDAO
						.getProblemsPostedByUserInDifferentLifeCycleStagesByRecentDate(userId);
				List<Long> totalProblemsCount = userProblemDAO
						.getProblemsPostedByUserInDifferentLifeCycleStagesByDateCount(userId);
				problemsOfUserVO.setTotalResultsCount(totalProblemsCount.get(0));
			}
			
		} catch (Exception ex) {
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
	public List<ProblemBeanVO> setUserProblemToVO(List<UserProblem> problemList) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Started Setting Data To VO ..");
		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();

		for (UserProblem userProblem : problemList) {
			Problem problem=userProblem.getProblem();
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			ProblemCompleteLocation problemCompleteLocation = problem.getProblemCompleteLocation();

			// Problem Basic Details
			problemBeanVO.setProblemHistoryId(problem.getProblemId());
			problemBeanVO.setProblem(problem.getTitle());
			problemBeanVO.setDescription(problem.getDescription());
			problemBeanVO.setExistingFrom(sdf1.format(problem.getExistingFrom()));
			problemBeanVO.setReportedDate(sdf1.format(problem.getIdentifiedOn()));
			problemBeanVO.setProblemStatus(problem.getProblemStatus().getStatus());
			problemBeanVO.setVisibility(userProblem.getVisibility().getVisibilityId());

			// Problem Source Details
			if(problem.getInformationSource() != null)
			  problemBeanVO.setProbSource(problem.getInformationSource().getInformationSource());
			else
				problemBeanVO.setProbSource("");
			// Problem Location Details
			if(problemCompleteLocation != null)
			problemBeanVO.setProblemLocation(getProblemLocationString(problemCompleteLocation));

			problemBeanVOLst.add(problemBeanVO);
		}
		return problemBeanVOLst;
		}
	public List<ProblemBeanVO> setProblemsToVO(List<Problem> problemList) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Started Setting Data To VO ..");
		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();

		for (Problem problem : problemList) {

			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			ProblemCompleteLocation problemCompleteLocation = problem.getProblemCompleteLocation();

			// Problem Basic Details
			problemBeanVO.setProblemHistoryId(problem.getProblemId());
			problemBeanVO.setProblem(problem.getTitle());
			problemBeanVO.setDescription(problem.getDescription());
			problemBeanVO.setExistingFrom(problem.getExistingFrom().toString());
			problemBeanVO.setReportedDate(problem.getIdentifiedOn().toString());
			problemBeanVO.setProblemStatus(problem.getProblemStatus().getStatus());

			// Problem Source Details
			if(problem.getInformationSource() != null)
			  problemBeanVO.setProbSource(problem.getInformationSource().getInformationSource());
			else
				problemBeanVO.setProbSource("");
			// Problem Location Details
			problemBeanVO.setProblemLocation(getProblemLocationString(problemCompleteLocation));

			problemBeanVOLst.add(problemBeanVO);
		}

		return problemBeanVOLst;
	}

	*//**
	 * Set Data To VO
	 * 
	 * @param problemHistorysList
	 * @return
	 *//*
	public List<ProblemBeanVO> setUserProblemsToVO(
			List<ProblemHistory> problemHistorysList) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Started Setting Data To VO ..");

		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();

		for (ProblemHistory pblmHistory : problemHistorysList) {

			ProblemBeanVO problemBeanVO = new ProblemBeanVO();

			ProblemBackup problem = pblmHistory.getProblemLocation()
					.getProblemAndProblemSource().getProblem();
			ProblemAndProblemSource pblmAndPblmSource = pblmHistory
					.getProblemLocation().getProblemAndProblemSource();
			ProblemCompleteLocation problemCompleteLocation = pblmHistory
					.getProblemLocation().getProblemCompleteLocation();

			// Problem Basic Details
			problemBeanVO
					.setProblemHistoryId(pblmHistory.getProblemHistoryId());
			problemBeanVO.setProblem(problem.getProblem());
			problemBeanVO.setDescription(problem.getDescription());
			problemBeanVO.setExistingFrom(problem.getExistingFrom().toString());
			problemBeanVO.setReportedDate(pblmHistory.getDateUpdated()
					.toString());
			problemBeanVO.setProblemStatus(pblmHistory.getProblemStatus()
					.getStatus());

			// Problem Source Details
			problemBeanVO.setProbSource(pblmAndPblmSource.getProblemSource()
					.getInformationSource());

			// Problem Location Details
			problemBeanVO
					.setProblemLocation(getProblemLocationString(problemCompleteLocation));

			problemBeanVOLst.add(problemBeanVO);
		}

		return problemBeanVOLst;
	}

	public String getProblemLocationString(
			ProblemCompleteLocation problemCompleteLocation) {

		String problemLocation = "";

		if (problemCompleteLocation.getState() != null)
			problemLocation = problemLocation
					+ problemCompleteLocation.getState().getStateName();

		if (problemCompleteLocation.getDistrict() != null)
			problemLocation = problemLocation + ", "
					+ problemCompleteLocation.getDistrict().getDistrictName();

		if (problemCompleteLocation.getTehsil() != null) {

			problemLocation = problemLocation + " ,"
					+ problemCompleteLocation.getTehsil().getTehsilName();
			if (problemCompleteLocation.getTownship() != null)
				problemLocation = problemLocation
						+ " ,"
						+ problemCompleteLocation.getTownship()
								.getTownshipName();
			if (problemCompleteLocation.getHamlet() != null)
				problemLocation = problemLocation + " ,"
						+ problemCompleteLocation.getHamlet().getHamletName();

		} else {

			if (problemCompleteLocation.getLocalElectionBody() != null)
				problemLocation = problemLocation
						+ " ,"
						+ problemCompleteLocation.getLocalElectionBody()
								.getName();
			if (problemCompleteLocation.getWard() != null)
				problemLocation = problemLocation + " ,"
						+ problemCompleteLocation.getWard().getName();
			if (problemCompleteLocation.getBooth() != null)
				problemLocation = problemLocation + " ,"
						+ problemCompleteLocation.getBooth().getPartNo()
						+ " Booth";
		}

		return problemLocation;
	}

	*//**
	 * Method To Get User Problems Based On Filters (status,date e.t.c ..)
	 * 
	 * @author Sai Krishna
	 * @param userId
	 * @param statusId
	 * @param startDate
	 * @param endDate
	 * @param startIndex
	 * @param maxResults
	 * @return ProblemsOfUserVO
	 *//*
	@SuppressWarnings("unchecked")
	public ProblemsOfUserVO getUserProblemsInDifferentStagesByFilters(
			Long userId, Long statusId, Date startDate, Date endDate,
			Integer startIndex, Integer maxResults) {

		if (log.isDebugEnabled())
			log.debug("Started To Get User Problems Based On Input filters ..");

		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus rs = new ResultStatus();

		try {

			// Get Problems Overall Info
			List<ProblemHistory> problemHistorysList = problemHistoryDAO
					.getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(
							userId, statusId, startDate, endDate, startIndex,
							maxResults);
			if (problemHistorysList != null && problemHistorysList.size() > 0) {
				List<ProblemBeanVO> problemsByUser = setUserProblemsToVO(problemHistorysList);
				
				problemsOfUserVO.setProblemsByUser(problemsByUser);
			}

			// Get Total Problems Count
			List totalProblemsCount = problemHistoryDAO
					.getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(
							userId, statusId, startDate, endDate);

			// Set total problems count to VO
			if (totalProblemsCount != null && totalProblemsCount.size() > 0) {

				Object values = (Object) totalProblemsCount.get(0);
				problemsOfUserVO.setTotalResultsCount((Long) values);
			}

		} catch (Exception ex) {

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

	public ProblemsOfUserVO getUserProblemsInDifferentStagesByFilters(
			Long userId, Long statusId, Date startDate, Date endDate,
			Integer startIndex, Integer maxResults) {
		
		if (log.isDebugEnabled())
			log.debug("Started To Get User Problems Based On Input filters ..");

		ProblemsOfUserVO problemsOfUserVO = new ProblemsOfUserVO();
		ResultStatus rs = new ResultStatus();

		try {
			
			
			// Get Problems Overall Info
			List<Problem> problemList = userProblemDAO
					.getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(
							userId, statusId, startDate, endDate, startIndex,
							maxResults);
			if (problemList != null && problemList.size() > 0) {
				List<ProblemBeanVO> problemsByUser = setProblemsToVO(problemList);
				
				
				problemsOfUserVO.setProblemsByUser(problemsByUser);
			}

			// Get Total Problems Count
			List<Long> totalProblemsCount = userProblemDAO
					.getDifferentLifeCycleProblemsCountOfAUserPostedBetweenDates(
							userId, statusId, startDate, endDate);

			// Set total problems count to VO
			if (totalProblemsCount != null && totalProblemsCount.size() > 0) {

				problemsOfUserVO.setTotalResultsCount(totalProblemsCount.get(0));
			}

		} catch (Exception ex) {

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
	
	*//**
	 * Method To Get A Problem Complete Information
	 * 
	 * @param problemHistoryId
	 * @return ProblemBeanVO
	 * 
	 * @author sai krishna
	 *//*
	public ProblemCompleteDetailsVO getProblemCompleteInformationByProblemHistory(Long userId,Long problemHistoryId) {

		if (log.isDebugEnabled())
			log.debug("Method To Get A Problem Complete Information ..");

		ProblemCompleteDetailsVO problemCompleteInfo = null;
		List<ProblemStatusDataVO> problemLifeCycleData = null;
		
		List<ProblemBeanVO> problemBeanVOLst = new ArrayList<ProblemBeanVO>();
		ResultStatus rs = new ResultStatus();

		try {

			log.info("DAO Call To Get History Object ..");
			List<Problem> problemList = userProblemDAO
					.getProblemHistoryBasedOnId(problemHistoryId,userId);
			Problem problem = null;
			Long problemId = 0L;

			// Problem basic details
			if (problemList != null && problemList.size() > 0) {

				problem = problemList.get(0);
				
				problemCompleteInfo = new ProblemCompleteDetailsVO();
				problemLifeCycleData = new ArrayList<ProblemStatusDataVO>();

				problemId = problem.getProblemId();
				problemBeanVOLst = setProblemsToVO(problemList);
				problemCompleteInfo.setProblemBasicDetails(problemBeanVOLst
						.get(0));

				problemLifeCycleData
						.add(getLifeCycleDataForNewProblem(problem));

				// set problem status label
				problemCompleteInfo
						.setProblemStatusLabel(getProblemStatusLabel(problem.getProblemStatus().getStatus()));
			

			// Set Problem Life Cycle Data in different stages
			List<AssignedProblemProgress> assignedProblemProgress = assignedProblemProgressDAO
					.getProblemDifferentStagesByByProblemId(problemId);

			// this problem gone thru different stages
			if (assignedProblemProgress != null
					&& assignedProblemProgress.size() > 0) {
				for (AssignedProblemProgress probProgress : assignedProblemProgress) {
					problemLifeCycleData
							.add(getLifeCycleDataForNewProblem(probProgress));
				}
			}

			problemCompleteInfo.setProblemLifeCycleData(problemLifeCycleData);
			}else{
				return null;
			}
		} catch (Exception ex) {

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
	
	*//**
	 * Getting New Problem Details And Set To VO
	 * 
	 * @param problemHistory
	 * @return
	 *//*
	private ProblemStatusDataVO getLifeCycleDataForNewProblem(
			Problem problem) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Stated Executing Method To Set New Problem Data ..");

		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();

		if (problem != null) {

			problemStatusData.setProblemStatus("NEW");
			problemStatusData.setUpdatedDate(problem.getIdentifiedOn().toString());
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

	*//**
	 * Getting New Problem Details And Set To VO
	 * 
	 * @param problemHistory
	 * @return
	 *//*
	private ProblemStatusDataVO getLifeCycleDataForNewProblem(
			AssignedProblemProgress problemProgress) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Stated Executing Method To Set New Problem Data ..");

		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		ProblemHistory problemHistory = problemProgress.getProblemHistory();

		if (problemProgress != null) {

			// set Department
			if (problemProgress.getDepartmentOrganisation() != null)
				problemStatusData.setDepartmentOrganisation(problemProgress
						.getDepartmentOrganisation().getOrganisationName());
			else
				problemStatusData.setDepartmentOrganisation("--");

			// set Department Category
			if (problemProgress.getProblemSourceScopeConcernedDepartment() != null)
				problemStatusData.setDepartment(problemProgress
						.getProblemSourceScopeConcernedDepartment()
						.getDepartment());
			else
				problemStatusData.setDepartment("--");

			// set cadre
			if (problemProgress.getCadre() != null)
				problemStatusData.setCadre(problemProgress.getCadre()
						.getFirstName()
						+ " " + problemProgress.getCadre().getLastName());
			else
				problemStatusData.setCadre("--");

			// comments
			problemStatusData.setComments(problemHistory.getComments());
			problemStatusData.setProblemStatus(problemHistory
					.getProblemStatus().getStatus());
			problemStatusData.setUpdatedDate(problemHistory.getDateUpdated()
					.toString());
			if (problemProgress.getConcernedPersonName() != null)
				problemStatusData.setOfficialName(problemProgress
						.getConcernedPersonName());
			else
				problemStatusData.setOfficialName("--");
			if (problemProgress.getContactNo() != null)
				problemStatusData.setPhoneNo(problemProgress.getContactNo());
			else
				problemStatusData.setPhoneNo("--");
			if (problemProgress.getDesignation() != null)
				problemStatusData.setOfficial(problemProgress.getDesignation());
			else
				problemStatusData.setOfficial("--");

			String deptLoc = "";
			if (problemProgress.getDepartmentLocation() != null)
				deptLoc = getProblemLocationString(problemProgress
						.getDepartmentLocation());
			problemStatusData.setDeptLocation(deptLoc);
		}

		return problemStatusData;
	}

	*//**
	 * Methos To Set Problem Status Label
	 * 
	 * @param presentStatus
	 * @return String
	 *//*
	private String getProblemStatusLabel(String presentStatus) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Entered To Set Status Label ..");

		String statusLabel = "MOVE TO ";

		if (presentStatus.equalsIgnoreCase(IConstants.NEW)) {
			statusLabel = statusLabel + IConstants.PROGRESS;
		} else if (presentStatus.equalsIgnoreCase(IConstants.PROGRESS)) {
			statusLabel = statusLabel + IConstants.PENDING + " / "
					+ IConstants.FIXED;
		} else if (presentStatus.equalsIgnoreCase(IConstants.PENDING)) {
			statusLabel = statusLabel + IConstants.PROGRESS;
		}

		return statusLabel;
	}

	*//**
	 * Method That Returns All Department Scopes Related To A User Based On
	 * State
	 * 
	 * @param userId
	 * @return List<SelectOptionVO>
	 *//*
	public List<SelectOptionVO> getDepartmentScopesForAnUser(Long userId) {

		List<SelectOptionVO> departmentScopes = null;

		if (log.isDebugEnabled())
			log.debug("Getting Department Scopes For A User ..");

		Long stateId = staticDataService.getStateIdForUserByAccessValue(userId);
		departmentScopes = getAllDepartmentScopes(stateId);

		return departmentScopes;
	}

	*//**
	 * Method To Get Default Problem Classifications
	 *//*
	public List<SelectOptionVO> getProblemsDefaultClassifications() {

		List<SelectOptionVO> classificationsList = new ArrayList<SelectOptionVO>();

		try {
			List<ProblemClassification> classificationsLst = problemClassificationDAO
					.getAll();

			if (classificationsLst != null && classificationsLst.size() > 0) {

				for (ProblemClassification clssifictn : classificationsLst) {
					classificationsList.add(new SelectOptionVO(clssifictn
							.getProblemClassificationId(), clssifictn
							.getClassification()));
				}
			}
		} catch (Exception ex) {
			log.error("Exception Raised :" + ex);
		}

		return classificationsList;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentCategorysForAProblemScope(
			Long scopeId) {

		List<SelectOptionVO> deptCategorysLst = new ArrayList<SelectOptionVO>();

		try {

			List probClassificationsLst = departmentOrganisationDAO
					.getDepartmentCategorysBasedOnProblemResolvingRegionScope(scopeId);
			if (probClassificationsLst != null
					&& probClassificationsLst.size() > 0) {

				Iterator lstItr = probClassificationsLst.listIterator();
				while (lstItr.hasNext()) {

					Object[] values = (Object[]) lstItr.next();

					deptCategorysLst.add(new SelectOptionVO((Long) values[0],
							(String) values[1]));
				}
			}

		} catch (Exception ex) {
			log.error("Exception Raised :" + ex);
		}

		return deptCategorysLst;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentOrganisationsForADeptOfScope(
			Long deptId, Long scopeId) {

		List<SelectOptionVO> deptOrgsLst = new ArrayList<SelectOptionVO>();

		try {

			List probClassificationsLst = departmentOrganisationDAO
					.getDepartmentOrganisationsForADepartmentByScope(deptId,
							scopeId);
			if (probClassificationsLst != null
					&& probClassificationsLst.size() > 0) {

				Iterator lstItr = probClassificationsLst.listIterator();
				while (lstItr.hasNext()) {

					Object[] values = (Object[]) lstItr.next();

					deptOrgsLst.add(new SelectOptionVO((Long) values[0],
							(String) values[1]));
				}
			}

		} catch (Exception ex) {
			log.error("Exception Raised :" + ex);
		}

		return deptOrgsLst;
	}

	*//**
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
	 *//*
	public ResultStatus changePostedProblemStatusForAnUser(
			final Long problemHistoryId, final Long classificationId,
			final Long scopeId, final Long departmentId, final Long cadreId,
			final Long problemDeptLocId, final String comments,
			final String statusToChange) {

		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Change Problem Status ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();

						try {

							ProblemHistory pbHistory = problemHistoryDAO
									.get(problemHistoryId);
							ProblemStatus presentStatus = pbHistory
									.getProblemStatus();
							ProblemLocation presentProblemLocation = pbHistory
									.getProblemLocation();

							List<AssignedProblemProgress> assignedProbProgress = assignedProblemProgressDAO
									.getAssignedProblemProgressbyHistoryId(problemHistoryId);

							// creating reference to different data objects to
							// save
							Cadre cadre = null;
							ProblemSourceScope problemScope = null;
							DepartmentOrganisation department = null;
							ProblemClassification problemClassification = null;
							AssignedProblemProgress asignedProbProgress = null;
							ProblemSourceScopeConcernedDepartment pbConDept = null;
							ProblemCompleteLocation departmentLocation = null;

							if (assignedProbProgress != null
									&& assignedProbProgress.size() > 0)
								asignedProbProgress = assignedProbProgress
										.get(0);

							// initializing different data objects to save
							if (classificationId != null
									&& !classificationId.equals(0L)) {
								problemClassification = problemClassificationDAO
										.get(classificationId);
							} else {
								problemClassification = presentProblemLocation
										.getProblemClassification();
							}
							if (scopeId != null && !scopeId.equals(0L)) {
								problemScope = problemSourceScopeDAO
										.get(scopeId);
							} else {
								problemScope = pbHistory
										.getProblemSourceScope();
							}
							if (departmentId != null
									&& !departmentId.equals(0L)) {

								department = departmentOrganisationDAO
										.get(departmentId);
								pbConDept = department
										.getProblemDepartmentCategory();

							} else {
								department = asignedProbProgress
										.getDepartmentOrganisation();
								pbConDept = asignedProbProgress
										.getProblemSourceScopeConcernedDepartment();
							}
							if (cadreId != null && !cadreId.equals(0L)) {
								cadre = cadreDAO.get(cadreId);
							} else {
								if (asignedProbProgress != null)
									cadre = asignedProbProgress.getCadre();
							}

							if (problemDeptLocId != null
									&& !problemDeptLocId.equals(0L)) {

								departmentLocation = getProblemCompleteLocation(
										problemDeptLocId, problemScope
												.getScope());

							} else {
								departmentLocation = asignedProbProgress
										.getDepartmentLocation();
							}

							if (departmentLocation != null) {
								departmentLocation = problemCompleteLocationDAO
										.save(departmentLocation);
							}
							// If Problem Present Status is NEW
							if (presentStatus.getStatus().equalsIgnoreCase(
									IConstants.NEW)) {

								presentProblemLocation
										.setProblemClassification(problemClassification);
								pbHistory
										.setProblemLocation(presentProblemLocation);
							}

							// To Update Previous Problem History Status
							pbHistory.setIsDelete("true");
							pbHistory = problemHistoryDAO.save(pbHistory);

							// save problem history details
							ProblemHistory problemHistory = new ProblemHistory();
							problemHistory.setComments(comments);

							problemHistory.setProblemLocation(pbHistory
									.getProblemLocation());
							problemHistory.setProblemSourceScope(problemScope);
							problemHistory
									.setDateUpdated(getCurrentDateAndTime());
							problemHistory.setIsApproved("true");
							problemHistory.setIsDelete("false");

							List<ProblemStatus> problemStatus = problemStatusDAO
									.getByStatus(statusToChange);
							problemHistory.setProblemStatus(problemStatus
									.get(0));

							problemHistory = problemHistoryDAO
									.save(problemHistory);

							// save problem progress details
							AssignedProblemProgress problemProgress = new AssignedProblemProgress();
							problemProgress.setProblemHistory(problemHistory);
							problemProgress
									.setProblemClassification(problemClassification);
							problemProgress
									.setProblemSourceScopeConcernedDepartment(pbConDept);
							problemProgress
									.setDepartmentOrganisation(department);
							problemProgress
									.setPerformedDate(getCurrentDateAndTime());
							problemProgress.setCadre(cadre);
							problemProgress
									.setDepartmentLocation(departmentLocation);
							assignedProblemProgressDAO.save(problemProgress);

							rs.setResultCode(ResultCodeMapper.SUCCESS);
							rs.setExceptionMsg("Updated Successfully ..");

						} catch (Exception ex) {

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

	*//**
	 * Method To Get Problem Location Complete Details
	 * 
	 * @param locationId
	 * @param type
	 * @return
	 *//*
	private ProblemCompleteLocation getProblemCompleteLocation(Long locationId,
			String type) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Getting Problem Location ..");

		ProblemCompleteLocation problemCompleteLocation = new ProblemCompleteLocation();

		if (type.equalsIgnoreCase(IConstants.STATE)) {

			State state = stateDAO.get(locationId);
			problemCompleteLocation.setState(state);
		} else if (type.equalsIgnoreCase(IConstants.DISTRICT)) {

			District district = districtDAO.get(locationId);
			problemCompleteLocation.setDistrict(district);
			problemCompleteLocation.setState(district.getState());

		} else if (type.equalsIgnoreCase(IConstants.MANDAL)
				|| type.equalsIgnoreCase("Revenue Division")) {

			Tehsil tehsil = tehsilDAO.get(Long.parseLong(locationId.toString()
					.substring(1)));
			problemCompleteLocation.setTehsil(tehsil);
			problemCompleteLocation.setDistrict(tehsil.getDistrict());
			problemCompleteLocation.setState(tehsil.getDistrict().getState());

		} else if (type.equalsIgnoreCase("Muncipality")
				|| type.equalsIgnoreCase("Corporation")) {

			LocalElectionBody localBody = localElectionBodyDAO.get(Long
					.parseLong(locationId.toString().substring(1)));
			problemCompleteLocation.setLocalElectionBody(localBody);
			problemCompleteLocation.setDistrict(localBody.getDistrict());
			problemCompleteLocation
					.setState(localBody.getDistrict().getState());

		} else if (type.equalsIgnoreCase(IConstants.VILLAGE)
				|| type.equalsIgnoreCase("Gram Panchayath")) {

			Township township = townshipDAO.get(Long.parseLong(locationId
					.toString().substring(1)));
			problemCompleteLocation.setTownship(township);
			problemCompleteLocation.setTehsil(township.getTehsil());
			problemCompleteLocation.setDistrict(township.getTehsil()
					.getDistrict());
			problemCompleteLocation.setState(township.getTehsil().getDistrict()
					.getState());
		}

		return problemCompleteLocation;
	}

	*//**
	 * Method TO Get Problem Recent Updated Details
	 * 
	 * @param problemHistoryId
	 * @author sai Krishna
	 * @return ProblemStatusDataVO
	 *//*
	@SuppressWarnings("unchecked")
	public ProblemStatusDataVO getProblemRecentDetailsByProblemHistoryId(
			Long problemHistoryId) {

		if (log.isDebugEnabled())
			log.debug("Entered To Get Problem Recent Details ..");

		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		try {
			// get Problem History and Problem Details
			ProblemHistory problemHistory = problemHistoryDAO
					.get(problemHistoryId);
			ProblemBackup problem = problemHistory.getProblemLocation()
					.getProblemAndProblemSource().getProblem();

			if (problem != null) {

				// set basic Details to VO
				setProblemBasicDataToVO(problem, problemStatusData);
				problemStatusData.setProblemStatus(problemHistory
						.getProblemStatus().getStatus());
				problemStatusData.setPostedBy(problemHistory
						.getProblemLocation().getProblemAndProblemSource()
						.getProblemSource().getInformationSource());
				problemStatusData.setPostedByName(getProblemPostedPersonName(
						problemHistoryId, problemHistory.getProblemLocation()
								.getProblemAndProblemSource()));
				problemStatusData
						.setProblemLocation(getProblemLocationString(problemHistory
								.getProblemLocation()
								.getProblemCompleteLocation()));
				if (problemHistory.getProblemLocation()
						.getProblemClassification() != null)
					problemStatusData.setProbClassification(problemHistory
							.getProblemLocation().getProblemClassification()
							.getClassification());

			}
			// DAO call To Get Problem Recent Data
			List resultLst = assignedProblemProgressDAO
					.getProblemRecentUpdatesByProblemId(problem.getProblemId());
			if (resultLst != null && resultLst.size() > 0)
				setProblemRecentUpdatesToVO(resultLst, problemStatusData);

		} catch (Exception ex) {
			log.error("Exception Raised :" + ex);
			ex.printStackTrace();

			problemStatusData.setExceptionEncountered(ex);
			problemStatusData.setExceptionMsg(ex.getMessage());
			problemStatusData.setResultCode(ResultCodeMapper.FAILURE);

			return problemStatusData;
		}

		return problemStatusData;
	}
	public ProblemStatusDataVO getProblemRecentDetailsByProblemId(Long problemId,Long userId) {
		
		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();
		
		Problem  problem = problemDAO.get(problemId);
		
		if(problem != null){			
			
			setProblemDetailsToVO(problem,problemStatusData);
			
			problemStatusData.setProblemStatus(problem.getProblemStatus().getStatus());
			if(problem.getInformationSource() != null)
			  problemStatusData.setPostedBy(problem.getInformationSource().getInformationSource());		
			else{
				List<String> isOwnerList = userProblemDAO.checkIsProblemOwner(problemId, userId);
				if(isOwnerList != null && isOwnerList.size() > 0 && isOwnerList.get(0).equalsIgnoreCase("false")){
					problemStatusData.setPostedBy("Public");
				}else{
					problemStatusData.setPostedBy("");
				}
			}
			
			//String problemLocation = problemManagementReportService.getProblemLocation(problem.getRegionScopes().getRegionScopesId(),problem.getImpactLevelValue());
			
			//problemStatusData.setProblemLocation(problemLocation);
			
			problemStatusData.setPostedByName(getProblemPostedPerson(problem,problem.getExternalSource(),problem.getInformationSource(),userId));
			problemStatusData.setProblemLocation(getProblemLocationString(problem.getProblemCompleteLocation()));
			
		}
		
					
			List<UserProblem> userProblemList = userProblemDAO.getUserProblem(problemId, userId);
			
			
			Long userProblemId = userProblemList.get(0).getUserProblemId();
		 
			//set problem Assigned details to VO
			
			//setProblemAssignedDetailsToVO(problem,problemStatusData,userProblemId);
			
			//set problem assigned cadre details to VO
			
			setProblemAssignedCadredetailsToVO(problemStatusData,userProblemId);
		 
	
		   //set problem classification details to VO
			
			setProblemClassificationDetailsToVO(problemStatusData,userProblemId);
			
			//set problem Assigned Department Details to VO
			setProblemAssignedToDepartmentToVO(problemStatusData,userProblemId);
		
		
		return problemStatusData;		
	}
    private ProblemStatusDataVO setProblemDetailsToVO(Problem problem,ProblemStatusDataVO problemStatusData){		
		
		
		try{
		
		problemStatusData.setProblemId(problem.getProblemId());
		problemStatusData.setProblem(problem.getTitle());
		problemStatusData.setProblemDesc(problem.getDescription());
		problemStatusData.setPostedDate(problem.getIdentifiedOn().toString());
		problemStatusData.setExistingFrom(problem.getExistingFrom().toString());
		DateUtilService dateUtilService = new DateUtilService();
		Date today = dateUtilService.getCurrentDateAndTime();
		Date postedDate = problem.getIdentifiedOn();

		long diffDate = today.getTime() - postedDate.getTime();

		problemStatusData.setDiffDays((diffDate / (1000 * 60 * 60 * 24)));		
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			
		}
		return problemStatusData;	
	}
    private String getProblemPostedPerson(Problem problem,ProblemExternalSource problemExternalSource,InformationSource informationSource,Long userId){
		
		String name="";
		
		if(problemExternalSource != null)			
			name = problem.getExternalSource().getName();
		else if(informationSource != null && informationSource.getInformationSourceId().longValue() == 1l){
			   User user = userDAO.get(userId);
			   name = user.getFirstName()+" "+user.getLastName();
		}else if(informationSource != null && informationSource.getInformationSourceId().longValue() == 4l){
			List<Object[]> caderDetailsList = cadreProblemsDAO.getProblemPostedCadreName(problem.getProblemId());
			  if(caderDetailsList != null && caderDetailsList.size() > 0){
				  Object[] caderDetails = caderDetailsList.get(0);
			     name = caderDetails[0].toString()+" "+caderDetails[1].toString();
			  }
		}else {
			
			List<Object[]> userLst = userProblemDAO.getProblemOwnerName(problem.getProblemId());
			
			if(userLst != null && userLst.size() > 0){
				
				Object[] obj = (Object[])userLst.get(0);				
				name=obj[0].toString()+" "+obj[1].toString();	
			}	
		}
		
		return name;	
	}

	*//**
	 * This method is to set the problem assignment details
	 * @param problem
	 * @param problemStatusData
	 * @param userProblemId
	 *//*
	
	private void setProblemAssignedDetailsToVO(Problem problem,ProblemStatusDataVO problemStatusData ,Long userProblemId){
		
		 List<ProblemAssignedDepartment> prblmAssgndLst = problemAssignedDepartmentDAO.getAllActivitesByProblemId(userProblemId);
		  
		  if(prblmAssgndLst != null && prblmAssgndLst.size() > 0){
			  
			 ProblemAssignedDepartment problemAssignedDepartment = (ProblemAssignedDepartment)prblmAssgndLst.get(0);
			  if(problemAssignedDepartment.getStatus().equalsIgnoreCase("assigned") || problemAssignedDepartment.getStatus().equalsIgnoreCase("modified")){
			    problemStatusData.setDepartmentOrganisation(problemAssignedDepartment.getDepartmentOrganisation().getOrganisationName());
			    problemStatusData.setDepartment(problemAssignedDepartment.getDepartmentOrganisation().getProblemDepartmentCategory().getDepartment());
			    problemStatusData.setDeptLocation(getProblemLocationString(problem.getProblemCompleteLocation()));
			 }else{
				  problemStatusData.setDepartmentOrganisation("");
				  problemStatusData.setDepartment("");
			 }
		 }else{				  
				  problemStatusData.setDepartmentOrganisation("");
				  problemStatusData.setDepartment("");  
				  
		 }
		  
		
	}
	*//**
	 * This method is to set the problem assigned cadre details to VO.
	 * @param problemStatusData
	 * @param userProblemId
	 *//*
	
	
	private void setProblemAssignedCadredetailsToVO(ProblemStatusDataVO problemStatusData ,Long userProblemId){
		
	List<ProblemAssignedCadre> prblmAsgndCdrLst = problemAssignedCadreDAO.getProblemAssignedCadreByUserProblemId(userProblemId);
		
		if(prblmAsgndCdrLst != null && prblmAsgndCdrLst.size() >0){			
			  ProblemAssignedCadre problemAssignedCadre = (ProblemAssignedCadre)prblmAsgndCdrLst.get(0);
			  if(problemAssignedCadre.getStatus().equalsIgnoreCase("assigned") || problemAssignedCadre.getStatus().equalsIgnoreCase("modified")){
			  problemStatusData.setCadre(problemAssignedCadre.getCadre().getFirstName());			
			}
		}	
		
	}
	
	
	public void setProblemAssignedToDepartmentToVO(ProblemStatusDataVO problemStatusData,Long userProblemId)
	{
		log.debug("Entered into setProblemAssignedToDepartmentToVO() of PRoblemManagementService");
		try
		{
		List<ProblemAssignedDepartment> problemAsgndDeptList = problemAssignedDepartmentDAO.getAllActivitesByProblemId(userProblemId);
		if(problemAsgndDeptList != null && problemAsgndDeptList.size() >0)
		{
			ProblemAssignedDepartment problemAssignedDepartment = (ProblemAssignedDepartment)problemAsgndDeptList.get(0);
			if(problemAssignedDepartment.getStatus().equalsIgnoreCase("assigned") || problemAssignedDepartment.getStatus().equalsIgnoreCase("modified")){
			
				problemStatusData.setDepartmentOrganisation(problemAssignedDepartment.getDepartmentOrganisation().getOrganisationName());
			}
			
		}
		}
		catch(Exception e)
		{
			log.error("Error Occured in setProblemAssignedToDepartmentToVO of ProblemManagement Service");
			e.printStackTrace();
		}
	}
	*//**
	 * This method is to set the problem classification details to VO
	 * @param problemStatusData
	 * @param userProblemId
	 *//*
	private void setProblemClassificationDetailsToVO(ProblemStatusDataVO problemStatusData ,Long userProblemId){
		
    List<ClassifiedProblems> clsfdPrblmsLst = classifiedProblemsDAO.getClassifiedproblemByUserProblemId(userProblemId);
		
		if(clsfdPrblmsLst != null && clsfdPrblmsLst.size() > 0){
			
			ClassifiedProblems classifiedProblems = (ClassifiedProblems) clsfdPrblmsLst.get(0);
			
			problemStatusData.setProbClassification(classifiedProblems.getProblemClassification().getClassification());
		}		
		
	}
	*//**
	 * 
	 * @param probAndProbSource
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	private String getProblemPostedPersonName(Long problemHistoryId,
			ProblemAndProblemSource probAndProbSource) throws Exception {

		String personName = "";
		if (probAndProbSource != null) {

			String infSource = probAndProbSource.getProblemSource()
					.getInformationSource();
			if (infSource.equalsIgnoreCase("User")) {

				personName = probAndProbSource.getUser().getFirstName();
				if (probAndProbSource.getUser().getLastName() != null)
					personName = personName + " "
							+ probAndProbSource.getUser().getLastName();
			} else if (infSource.equalsIgnoreCase("External Person")
					|| infSource.equalsIgnoreCase("Call Center")) {

				personName = probAndProbSource.getProblemExternalSource()
						.getName();
			} else if (infSource.equalsIgnoreCase("Cadre")) {

				List cadreDetails = cadreProblemDetailsDAO
						.getCadreDetailsByProblemHistoryId(probAndProbSource
								.getProblem().getProblemId());
				if (cadreDetails != null && cadreDetails.size() > 0) {

					Object[] values = (Object[]) cadreDetails.get(0);
					String firstName = (String) values[0];
					String lastName = (String) values[1];

					personName = firstName + " " + lastName;
				}
			}

		}
		return personName;
	}

	*//**
	 * Method To Set Problem Basic Details From Problem Model
	 * 
	 * @param problem
	 * @param problemStatusData
	 * 
	 * @return void
	 * @author Sai Krishna
	 *//*
	private void setProblemBasicDataToVO(ProblemBackup problem,
			ProblemStatusDataVO problemStatusData) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Entered Method To Set Problem Basic Details ..");

		if (problem != null) {

			problemStatusData.setProblemId(problem.getProblemId());
			problemStatusData.setProblem(problem.getProblem());
			problemStatusData.setProblemDesc(problem.getDescription());
			problemStatusData.setPostedDate(problem.getIdentifiedOn()
					.toString());
			problemStatusData.setExistingFrom(problem.getExistingFrom()
					.toString());

			Date today = new Date();
			Date postedDate = problem.getIdentifiedOn();

			long diffDate = today.getTime() - postedDate.getTime();

			problemStatusData.setDiffDays((diffDate / (1000 * 60 * 60 * 24)));
		}
	}

	*//**
	 * @author Sai Krishna
	 * @param resultsLst
	 *            of type List
	 * @param problemStatusData
	 * 
	 * @return void
	 *//*
	@SuppressWarnings("unchecked")
	private void setProblemRecentUpdatesToVO(List resultsLst,
			ProblemStatusDataVO problemStatusData) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Entered Method To Set Problem Basic Details ..");
		String comments = "";
		String cadre = "";
		if (resultsLst != null && resultsLst.size() > 0) {

			Object value = (Object) resultsLst.get(0);
			AssignedProblemProgress assigedProbProg = (AssignedProblemProgress) value;

			problemStatusData.setUpdatedDate(assigedProbProg.getPerformedDate()
					.toString());
			problemStatusData.setComments(comments = assigedProbProg
					.getComments() != null ? assigedProbProg.getComments()
					: comments);

			// Set Department Data To VO
			if (assigedProbProg.getDepartmentOrganisation() != null) {
				problemStatusData.setDepartmentOrganisation(assigedProbProg
						.getDepartmentOrganisation().getOrganisationName());
				problemStatusData.setDepartment(assigedProbProg
						.getDepartmentOrganisation()
						.getProblemDepartmentCategory().getDepartment());
			} else {
				problemStatusData.setDepartmentOrganisation("");
				problemStatusData.setDepartment("");
			}

			// Set Cadre Data To VO
			problemStatusData
					.setCadre(cadre = assigedProbProg.getCadre() != null ? assigedProbProg
							.getCadre().getFirstName()
							: cadre);

			if (assigedProbProg.getDepartmentLocation() != null)
				problemStatusData
						.setDeptLocation(getProblemLocationString(assigedProbProg
								.getDepartmentLocation()));

			// Problem Classification
			if (assigedProbProg.getProblemClassification() != null)
				problemStatusData.setProbClassification(assigedProbProg
						.getProblemClassification().getClassification());
		}
	}

	*//**
	 * Method to get all problem activities list
	 * 
	 * @param problemHistoryId
	 * 
	 * @return List<ProblemStatusDataVO>
	 * 
	 * @author Sai Krishna
	 * 
	 *//*
	public List<ProblemStatusDataVO> getAllProblemRecentActivityDetails(
			Long problemId,Long userId) {

		if (log.isDebugEnabled())
			log.debug("Entered To Get Problem All Recent Activity Details ..");

		List<ProblemStatusDataVO> problemRecentActivityList = new ArrayList<ProblemStatusDataVO>();

		try {

			// get Problem History and Problem Details
			ProblemHistory problemHistory = problemHistoryDAO
					.get(problemHistoryId);
			List<UserProblem> userProblemList = userProblemDAO.getUserProblem(problemId,userId);

			// DAO Call To get activities data
			List<ProblemProgress> problemDetailsLst = problemProgressDAO.getProblemPrograssDetails(userProblemList.get(0).getUserProblemId());
			if (problemDetailsLst != null && problemDetailsLst.size() > 0) {

				// Iterate Different Activities List and set to List
				for (ProblemProgress problemProgress : problemDetailsLst) {

					problemRecentActivityList
							.add(getProblemActivityDetailsSetToVO(problemProgress));
				}
			}

		} catch (Exception ex) {

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

	*//**
	 * Method To set problem activity Data to VO
	 * 
	 * @param problemProgress
	 * @return
	 *//*
	public ProblemStatusDataVO getProblemActivityDetailsSetToVO(
			ProblemProgress problemProgress) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Entered To Process Recent Activity Details and set to vo ..");

		ProblemStatusDataVO problemStatusData = new ProblemStatusDataVO();

		problemStatusData.setUpdatedDate(problemProgress.getInsertedTime().toString());
		if (problemProgress.getProblemActivity() != null) {
			//problemStatusData.setActivityHapened(problemProgress
					//.getProblemActivity().getComments());

			switch (problemProgress.getProblemActivity().getProblemActivityId()
					.intValue()) {

			case 1:
			case 2:if(problemProgress.getClassifiedProblems() != null)
				problemStatusData.setProbClassification(problemProgress.getClassifiedProblems().getProblemClassification().getClassification());
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments()+" : "+problemProgress.getClassifiedProblems().getProblemClassification().getClassification());
				
				break;
			case 3:
			case 4:if(problemProgress.getProblemAssignedDepartment() != null){
				problemStatusData.setDepartmentOrganisation(problemProgress.getProblemAssignedDepartment()
						.getDepartmentOrganisation().getOrganisationName());
				problemStatusData.setDepartment(problemProgress.getProblemAssignedDepartment()
						.getDepartmentOrganisation()
						.getProblemDepartmentCategory().getDepartment());
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments()+" : " +problemProgress.getProblemAssignedDepartment()
						.getDepartmentOrganisation().getOrganisationName());
			     }
				break;
			case 5:
				problemStatusData.setDepartmentOrganisation("N/A");
				problemStatusData.setDepartment("N/A");
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments());
				break;
			case 6:
			case 7:if(problemProgress.getProblemAssignedCadre() != null)
				problemStatusData.setCadre(problemProgress.getProblemAssignedCadre().getCadre().getFirstName());
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments()+" : " +problemProgress.getProblemAssignedCadre().getCadre().getFirstName());
				break;
			case 8:
				problemStatusData.setCadre("Removed ");
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments());
				break;
			case 11:if(problemProgress.getComment() != null)
				problemStatusData.setComments(problemProgress.getComment().getComment());
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments()+" : "+problemProgress.getComment().getComment());
				
				break;
			case 12:if(problemProgress.getUserProblem()!= null)
				problemStatusData.setProblemStatus(problemProgress
						.getUserProblem().getProblem().getProblemStatus().getStatus());
				problemStatusData.setVisibility(problemProgress.getVisibility().getVisibilityId());
				problemStatusData.setPrblmPrgrssId(problemProgress.getProblemProgressId());
				problemStatusData.setActivityHapened(problemProgress.getProblemActivity().getComments());
				
				break;
			default:
				if (log.isInfoEnabled())
					log.info("Problem Activity Details for :"
							+ problemProgress.getProblemActivity()
									.getActivityDescription());
			}
		}
		return problemStatusData;
	}

	*//**
	 * Method To Update Assigned Cadre
	 * 
	 * @param problemHistoryId
	 * @param statusId
	 * 
	 * @author Sai Krishna
	 * @return ResultStatus
	 *//*
	public ResultStatus updateAssignedCadre(final Long problemHistoryId,
			final Long cadreId, final String pbStatus) {

		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {

							ProblemHistory problemHistory = problemHistoryDAO
									.get(problemHistoryId);
							ProblemBackup problem = problemHistory
									.getProblemLocation()
									.getProblemAndProblemSource().getProblem();

							AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();

							List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO
									.getProblemAllActivitiesByProblemId(problem
											.getProblemId());
							if (problemProgress != null
									&& problemProgress.size() > 0) {

								AssignedProblemProgress existingProblemProgress = problemProgress
										.get(0);
								assignedProblemProgress
										.setDepartmentLocation(existingProblemProgress
												.getDepartmentLocation());
								assignedProblemProgress
										.setDepartmentOrganisation(existingProblemProgress
												.getDepartmentOrganisation());
								assignedProblemProgress
										.setProblemClassification(existingProblemProgress
												.getProblemClassification());
								assignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(existingProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								assignedProblemProgress
										.setComments(existingProblemProgress
												.getComments());
							}

							// setting history and cadre details
							assignedProblemProgress
									.setPerformedDate(getCurrentDateAndTime());
							assignedProblemProgress
									.setProblemHistory(problemHistory);

							if (cadreId != null && !cadreId.equals(0L)) {
								Cadre cadre = cadreDAO.get(cadreId);
								assignedProblemProgress
										.setIsCadreAssigned("true");
								assignedProblemProgress.setCadre(cadre);
							}
							// setting activity
							if (pbStatus.equals(IConstants.CADRE_ADD)) {

								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("ASSIGN_CADRE_ADD");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							} else if (pbStatus.equals(IConstants.CADRE_MODIFY)) {

								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("ASSIGN_CADRE_UPDATE");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							} else if (pbStatus.equals(IConstants.CADRE_DELETE)) {

								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("ASSIGN_CADRE_DELETE");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							}

							// save data
							assignedProblemProgressDAO
									.save(assignedProblemProgress);

						} catch (Exception ex) {
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

	
	public ResultStatus updateAssignedCadre(final Long problemId,
			final Long cadreId, final String pbStatus,final Long userId) {

		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {
							
							ProblemAssignedCadre problemAssignedCadre = new ProblemAssignedCadre();
							ProblemProgress problemProgress = new ProblemProgress();
							
							Problem problem = problemDAO.get(problemId);
							
							List<UserProblem> userProblemDetails = userProblemDAO.getUserProblemIdForCadreProblems(problemId, userId);
							if(userProblemDetails != null && userProblemDetails.size() > 0)
							{
								problemAssignedCadre.setUserProblem(userProblemDetails.get(0));
								problemProgress.setUserProblem(userProblemDetails.get(0));
								
							}
							
														
							if (cadreId != null && !cadreId.equals(0L)) {
								Cadre cadre = cadreDAO.get(cadreId);
								problemAssignedCadre.setCadre(cadre);
								
							}
							// setting activity
							if (pbStatus.equals(IConstants.CADRE_ADD)) {

								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("ASSIGN_CADRE_ADD");
								
								problemAssignedCadre.setStatus(IConstants.ASSIGNED);
								problemProgress.setProblemActivity(problemActivityLst.get(0));
								problemAssignedCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								
							} else if (pbStatus.equals(IConstants.CADRE_MODIFY)) {

							List<ProblemActivity> problemActivityLst = problemActivityDAO
									.getProblemActivityByName("ASSIGN_CADRE_UPDATE");
							
							problemAssignedCadre.setStatus(IConstants.MODIFIED);
							problemProgress.setProblemActivity(problemActivityLst.get(0));
							problemAssignedCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							
								
							} else if (pbStatus.equals(IConstants.CADRE_DELETE)) {

								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("ASSIGN_CADRE_DELETE");
								
								problemAssignedCadre.setStatus(IConstants.DELETED);
								problemProgress.setProblemActivity(problemActivityLst.get(0));
								problemAssignedCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							}
							problemAssignedCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							
							problemAssignedCadre = problemAssignedCadreDAO.save(problemAssignedCadre);
							
							
							problemProgress.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							problemProgress.setVisibility(visibilityDAO.get(2l));
							problemProgress.setIsDelete(IConstants.FALSE);
							problemProgress.setProblemAssignedCadre(problemAssignedCadre);
							problemProgressDAO.save(problemProgress);
							

						} catch (Exception ex) {
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
	
	
	public ResultStatus updateProblemClassification(
			final Long problemHistoryId, final Long classificationId,
			final String pbStatus) {

		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {

							ProblemHistory problemHistory = problemHistoryDAO
									.get(problemHistoryId);
							ProblemBackup problem = problemHistory
									.getProblemLocation()
									.getProblemAndProblemSource().getProblem();

							ProblemClassification pobClassification = problemClassificationDAO
									.get(classificationId);

							if (problemHistory.getProblemLocation()
									.getProblemClassification() == null) {

								problemHistory.getProblemLocation()
										.setProblemClassification(
												pobClassification);
								problemHistory = problemHistoryDAO
										.save(problemHistory);
							}

							AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();

							List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO
									.getProblemAllActivitiesByProblemId(problem
											.getProblemId());
							if (problemProgress != null
									&& problemProgress.size() > 0) {

								AssignedProblemProgress existingProblemProgress = problemProgress
										.get(0);
								assignedProblemProgress
										.setDepartmentLocation(existingProblemProgress
												.getDepartmentLocation());
								assignedProblemProgress
										.setDepartmentOrganisation(existingProblemProgress
												.getDepartmentOrganisation());
								assignedProblemProgress
										.setComments(existingProblemProgress
												.getComments());
								assignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(existingProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								assignedProblemProgress
										.setIsCadreAssigned(existingProblemProgress
												.getIsCadreAssigned());
								assignedProblemProgress
										.setCadre(existingProblemProgress
												.getCadre());

							}

							// setting history and cadre details
							assignedProblemProgress
									.setPerformedDate(getCurrentDateAndTime());
							assignedProblemProgress
									.setProblemHistory(problemHistory);
							assignedProblemProgress
									.setProblemClassification(pobClassification);

							if (pbStatus.equals(IConstants.PROBLEM_TYPE_ADD)) {
								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("PROBLEM_TYPE_ADD");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							} else if (pbStatus
									.equals(IConstants.PROBLEM_TYPE_MODIFY)) {
								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("PROBLEM_TYPE_UPDATE");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							}

							// save data
							assignedProblemProgressDAO
									.save(assignedProblemProgress);

						} catch (Exception ex) {
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

	public ResultStatus updateProblemStatus(final Long problemHistoryId,
			final Long statusId, final String pbstatus) {

		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {

							ProblemStatus problemStatus = problemStatusDAO
									.get(statusId);
							ProblemHistory problemHistory = problemHistoryDAO
									.get(problemHistoryId);
							ProblemBackup problem = problemHistory
									.getProblemLocation()
									.getProblemAndProblemSource().getProblem();

							// check for previous status
							if (problemHistory
									.getProblemStatus()
									.getStatus()
									.equalsIgnoreCase(problemStatus.getStatus())) {
								rs
										.setExceptionMsg("Already  In same status, Please select other status ..");
								return rs;
							}

							problemHistory.setIsApproved("true");
							problemHistory.setIsDelete("true");

							problemHistory = problemHistoryDAO
									.save(problemHistory);

							// create new problem history instance
							ProblemHistory newProbHistory = new ProblemHistory();
							newProbHistory.setProblemLocation(problemHistory
									.getProblemLocation());
							newProbHistory
									.setDateUpdated(getCurrentDateAndTime());
							newProbHistory.setIsApproved("true");
							newProbHistory.setProblemSourceScope(problemHistory
									.getProblemSourceScope());
							newProbHistory.setProblemStatus(problemStatus);
							newProbHistory.setComments(problemHistory
									.getComments());

							newProbHistory = problemHistoryDAO
									.save(newProbHistory);
							rs.setResultState(newProbHistory
									.getProblemHistoryId());

							AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();

							List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO
									.getProblemAllActivitiesByProblemId(problem
											.getProblemId());
							if (problemProgress != null
									&& problemProgress.size() > 0) {

								AssignedProblemProgress existingProblemProgress = problemProgress
										.get(0);
								assignedProblemProgress
										.setDepartmentLocation(existingProblemProgress
												.getDepartmentLocation());
								assignedProblemProgress
										.setDepartmentOrganisation(existingProblemProgress
												.getDepartmentOrganisation());
								assignedProblemProgress
										.setProblemClassification(existingProblemProgress
												.getProblemClassification());
								assignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(existingProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								assignedProblemProgress
										.setComments(existingProblemProgress
												.getComments());
								assignedProblemProgress
										.setIsCadreAssigned(existingProblemProgress
												.getIsCadreAssigned());
								assignedProblemProgress
										.setCadre(existingProblemProgress
												.getCadre());

							}

							// setting history and cadre details
							assignedProblemProgress
									.setPerformedDate(getCurrentDateAndTime());
							assignedProblemProgress
									.setProblemHistory(newProbHistory);

							List<ProblemActivity> problemActivityLst = problemActivityDAO
									.getProblemActivityByName("STATUS_CHANGE");
							assignedProblemProgress
									.setProblemActivity(problemActivityLst
											.get(0));

							// save data
							assignedProblemProgressDAO
									.save(assignedProblemProgress);

						} catch (Exception ex) {
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
			final String comments, final String pbStatus) {
		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {

							ProblemHistory problemHistory = problemHistoryDAO
									.get(problemHistoryId);
							ProblemBackup problem = problemHistory
									.getProblemLocation()
									.getProblemAndProblemSource().getProblem();

							AssignedProblemProgress assignedProblemProgress = new AssignedProblemProgress();

							List<AssignedProblemProgress> problemProgress = assignedProblemProgressDAO
									.getProblemAllActivitiesByProblemId(problem
											.getProblemId());
							if (problemProgress != null
									&& problemProgress.size() > 0) {

								AssignedProblemProgress existingProblemProgress = problemProgress
										.get(0);
								assignedProblemProgress
										.setDepartmentLocation(existingProblemProgress
												.getDepartmentLocation());
								assignedProblemProgress
										.setDepartmentOrganisation(existingProblemProgress
												.getDepartmentOrganisation());
								assignedProblemProgress
										.setProblemClassification(existingProblemProgress
												.getProblemClassification());
								assignedProblemProgress
										.setProblemSourceScopeConcernedDepartment(existingProblemProgress
												.getProblemSourceScopeConcernedDepartment());
								assignedProblemProgress
										.setIsCadreAssigned(existingProblemProgress
												.getIsCadreAssigned());
								assignedProblemProgress
										.setCadre(existingProblemProgress
												.getCadre());

							}

							// setting history and cadre details
							assignedProblemProgress
									.setPerformedDate(getCurrentDateAndTime());
							assignedProblemProgress
									.setProblemHistory(problemHistory);
							assignedProblemProgress.setComments(comments);

							if (pbStatus
									.equals(IConstants.PROBLEM_COMMENTS_ADD)) {
								List<ProblemActivity> problemActivityLst = problemActivityDAO
										.getProblemActivityByName("POSTED_COMMENTS");
								assignedProblemProgress
										.setProblemActivity(problemActivityLst
												.get(0));
							}
							// save data
							assignedProblemProgressDAO
									.save(assignedProblemProgress);

						} catch (Exception ex) {
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

	
	public ResultStatus saveProblemRelatedComments(final ProblemBeanVO problemBeanVO) {
		if (log.isDebugEnabled())
			log.debug("Started Executing Method To Update Assigned Cadre ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						
						try {

							Comment comment = null;
							ProblemComments problemComments = null;
							ProblemProgress problemProgress = null;
							if(problemBeanVO == null)
							{
								log.info("problemBeanVO is null in saveProblemRelatedComments()");
								rs.setResultCode(ResultCodeMapper.FAILURE);
								return rs;
							}
							else if(problemBeanVO != null)
							{
							comment = saveProblemCommentDetails(problemBeanVO);
							problemComments = saveCommentDataInProblemComments(problemBeanVO, comment);
							problemProgress = saveCommentDataInProblemProgress(problemBeanVO, comment);
							rs.setResultCode(ResultCodeMapper.SUCCESS);
							}
						} catch (Exception ex) {
							log.error("Exception Raised :" + ex);
							rs.setResultCode(ResultCodeMapper.FAILURE);
							rs.setExceptionEncountered(ex);
							rs.setExceptionMsg(ex.getMessage());
							return rs;
						}

						return rs;
					}

				});
		return resultStatus;
	}

	public Comment saveProblemCommentDetails(ProblemBeanVO problemBeanVO)
	{
		Comment comment = null;
		
		try{
			if(problemBeanVO == null)
			{
				log.info("problemBeanVO is null in saveProblemCommentDetails()");
				return comment;
			}
			else if(problemBeanVO != null)
			{
				comment = new Comment();
				comment.setIsAbused(IConstants.FALSE);
				comment.setComment(problemBeanVO.getDescription());
				comment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				comment = commentDAO.save(comment);
			}
			log.info("Entered into saveProblemCommentDetails() Method");
			return comment;
		}catch (Exception e) {
			log.error("Exception Occured in saveProblemCommentDetails(), Exception - "+e);
			e.printStackTrace();
			return comment;
		}
	}
	public ProblemComments saveCommentDataInProblemComments(ProblemBeanVO problemBeanVO, Comment comment)
	{
		ProblemComments problemComments = null;
		
		try{
			if(problemBeanVO == null || comment == null)
			{
				log.info("problemBeanVO is null in saveCommentDataInProblemComments()");
				return problemComments;
			}
			else if(problemBeanVO != null || comment != null)
			{
				problemComments = new ProblemComments();
				problemComments.setComment(comment);
				problemComments.setUser(userDAO.get(problemBeanVO.getUserID()));
				problemComments.setProblem(problemDAO.get(problemBeanVO.getProblemId()));
				problemComments.setIsDelete(IConstants.FALSE);
				problemComments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				if(problemBeanVO.getHasPartyAnalystUserRole() != null && problemBeanVO.getHasPartyAnalystUserRole())
					problemComments.setIsApproved(IConstants.TRUE);
				else if(problemBeanVO.getHasFreeUserRole() != null && problemBeanVO.getHasFreeUserRole())
					problemComments.setIsApproved(IConstants.TRUE);
				problemCommentsDAO.save(problemComments);
		}
			return problemComments;
		}catch (Exception e) {
			log.error("Exception Occured in saveCommentDataInProblemComments(), Exception - "+e);
			e.printStackTrace();
			return problemComments;
		}
	}
	public ProblemProgress saveCommentDataInProblemProgress(ProblemBeanVO problemBeanVO, Comment comment)
	{
		ProblemProgress problemProgress = null;
		List<UserProblem> userProblemDetails = null;
		
		try{
			if(problemBeanVO == null || comment == null)
			{
				log.info("problemBeanVO is null in saveCommentDataInProblemComments()");
				return problemProgress;
			}
			else if(problemBeanVO != null && comment != null)
			{
				problemProgress = new ProblemProgress();
				
			 userProblemDetails = userProblemDAO.getUserProblemIdForCadreProblems(problemBeanVO.getProblemId(), problemBeanVO.getUserID());
				if(userProblemDetails != null && userProblemDetails.get(0).getUserProblemId() > 0)
					problemProgress.setUserProblem(userProblemDetails.get(0));
				
				if (problemBeanVO.getStatus().equals(IConstants.PROBLEM_COMMENTS_ADD)) 
				{
					List<ProblemActivity> problemActivityLst = problemActivityDAO
							.getProblemActivityByName("POSTED_COMMENTS");
					problemProgress
							.setProblemActivity(problemActivityLst.get(0));
				}
				problemProgress.setComment(comment);
				
				if(problemBeanVO.getHasPartyAnalystUserRole() != null && problemBeanVO.getHasPartyAnalystUserRole())
					problemProgress.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PRIVATE).get(0));
				else if(problemBeanVO.getHasFreeUserRole() != null && problemBeanVO.getHasFreeUserRole())
					problemProgress.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PUBLIC).get(0));
				
				problemProgress.setIsDelete(IConstants.FALSE);
				problemProgress.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				problemProgressDAO.save(problemProgress);
			}
			return problemProgress;
		}catch (Exception e) {
			log.error("Exception Occured in saveCommentDataInProblemProgress(), Exception - "+e);
			e.printStackTrace();
			return problemProgress;
		}
	}
		
	
	*//**
	 * Method To Update/Add Department For a Problem
	 * 
	 * @param problemHistoryId
	 * @param departmentId
	 * @param scopeId
	 * @param regionId
	 * @param status
	 * 
	 * @return ResultStatus
	 *//*
	public ResultStatus updateProblemDepartment(final Long problemHistoryId,
			final Long departmentId, final Long scopeId, final Long regionId,
			final String pbStatus,final Long userId) {

		if (log.isDebugEnabled())
			log.debug(" Started " + pbStatus
					+ " Department Details For a Problem ..");

		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();
						try {

							
							   List<UserProblem> userProblem = userProblemDAO.getUserProblem(problemHistoryId,userId);
							
							
							    ProblemAssignedDepartment problemAssignedDepartment = new ProblemAssignedDepartment();
								problemAssignedDepartment.setUserProblem(userProblem.get(0));
                               if (departmentId != null && !departmentId.equals(0L)) {
									
									ProblemSourceScope problemSourceScope = problemSourceScopeDAO.get(scopeId);
									DepartmentOrganisation departmentOrganisation = departmentOrganisationDAO.get(departmentId);
									ProblemCompleteLocation problemCompleteLocation = getProblemCompleteLocation(regionId, problemSourceScope.getScope());
									problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
									problemAssignedDepartment.setDepartmentLocation(problemCompleteLocation);
									problemAssignedDepartment.setDepartmentOrganisation(departmentOrganisation);
									
								 }
                                 if (pbStatus.equals(IConstants.DEPARTMENT_ADD)) {
                            	   problemAssignedDepartment.setStatus(IConstants.ASSIGNED);
                          		   
                          	      }
                                 else if (pbStatus.equals(IConstants.DEPARTMENT_MODIFY)) {
                                	 problemAssignedDepartment.setStatus(IConstants.MODIFIED);
                          		   
                          	     }
                          	     else if (pbStatus.equals(IConstants.DEPARTMENT_DELETE)) {
                          	    	 problemAssignedDepartment.setStatus(IConstants.DELETED);
                          	     }
                                 
                                problemAssignedDepartment.setStatus(IConstants.ASSIGNED);
                                problemAssignedDepartment.setInsertedTime(getCurrentDateAndTime());
								
                                problemAssignedDepartment.setUpdatedTime(getCurrentDateAndTime());
								
                                problemAssignedDepartment = problemAssignedDepartmentDAO.save(problemAssignedDepartment);
				
                               //saving details in problemprograss
                        	   ProblemProgress problemProgress = new ProblemProgress();
                        	   
                        	   List<ProblemActivity> problemActivity = null;
                        	   problemProgress.setUserProblem(userProblem.get(0));
                        	   if (pbStatus.equals(IConstants.DEPARTMENT_ADD)) {
                        		 problemActivity =  problemActivityDAO.getProblemActivityByName("DEPARTMENT_ADD");
                        		   
                        	   }
                        	   else if (pbStatus.equals(IConstants.DEPARTMENT_MODIFY)) {
                        		   problemActivity =  problemActivityDAO.getProblemActivityByName("DEPARTMENT_UPDATE");
                        		   
                        	   }
                        	   else if (pbStatus.equals(IConstants.DEPARTMENT_DELETE)) {
                        		   problemActivity =  problemActivityDAO.getProblemActivityByName("DEPARTMENT_DELETE");
                        	   }
                        	   
                        	   
                        	   
                        	   problemProgress.setProblemActivity(problemActivity.get(0));
                        	   problemProgress.setVisibility(visibilityDAO.get(2l));
                        	   problemProgress.setInsertedTime(dateUtilService.getCurrentDateAndTime());
                        	   problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
                        	   problemProgress.setIsDelete(IConstants.FALSE);
                        	   problemProgress.setProblemAssignedDepartment(problemAssignedDepartment);
                        	   
                        	   problemProgressDAO.save(problemProgress);
                        	   
                        	   
                           
							
						} catch (Exception ex) {
							log.error("Exception Raised :" + ex);
							ex.printStackTrace();
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
			String classification, String type) {

		if (log.isDebugEnabled())
			log.debug(" Started setting problem as " + classification);

		ResultStatus rs = new ResultStatus();

		try {

			List<ProblemClassification> problemClassification = problemClassificationDAO
					.findByClassification(classification);

			if (problemClassification != null
					&& problemClassification.size() > 0)
				rs = updateProblemClassification(problemHistoryId,
						problemClassification.get(0)
								.getProblemClassificationId(), type);

		} catch (Exception ex) {

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

		if (log.isDebugEnabled())
			log.debug(" Setting  problem status to " + status);

		ResultStatus rs = new ResultStatus();

		try {

			String problemStatus = "";
			if (status.equalsIgnoreCase(IConstants.PROGRESS))
				problemStatus = IConstants.PROGRESS;
			else if (status.equalsIgnoreCase(IConstants.PENDING))
				problemStatus = IConstants.PENDING;
			else if (status.equalsIgnoreCase(IConstants.FIXED))
				problemStatus = IConstants.FIXED;
			else if (status.equalsIgnoreCase(IConstants.NEW))
				problemStatus = IConstants.NEW;

			List<ProblemStatus> problemStatusLst = problemStatusDAO
					.getByStatus(problemStatus);

			if (problemStatusLst != null && problemStatusLst.size() > 0)
				rs = updateProblemStatus(problemHistoryId, problemStatusLst
						.get(0).getProblemStatusId(), status);

		} catch (Exception ex) {

			log.error("Exception Raised :" + ex);
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);

			return rs;
		}

		return rs;
	}

	public ResultStatus updateProblemDepartment22(final Long problemHistoryId,
	final Long departmentId, final Long scopeId, final Long regionId,
	final String pbStatus) {

if (log.isDebugEnabled())
	log.debug(" Started " + pbStatus
			+ " Department Details For a Problem ..");

ResultStatus resultStatus = (ResultStatus) transactionTemplate
		.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {

				ResultStatus rs = new ResultStatus();
				try {

					
					UserProblem userProblem = userProblemDAO.get(problemHistoryId);
					
					
					
					
				    List<ProblemAssignedDepartment> assigneDepartment = problemAssignedDepartmentDAO.getAllActivitesByProblem(userProblem.getUserProblemId());
					if (assigneDepartment != null && assigneDepartment.size() > 0) {

						ProblemAssignedDepartment existingProblemProgress = assigneDepartment.get(0);
                         
						//problemAssignedDepartment.setUserProblem(existingProblemProgress.getUserProblem());
						
						if (departmentId != null && !departmentId.equals(0L)) {
							
							ProblemSourceScope problemSourceScope = problemSourceScopeDAO.get(scopeId);
							DepartmentOrganisation departmentOrganisation = departmentOrganisationDAO.get(departmentId);
							ProblemCompleteLocation problemCompleteLocation = getProblemCompleteLocation(regionId, problemSourceScope.getScope());
							problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
							existingProblemProgress.setDepartmentLocation(problemCompleteLocation);
							existingProblemProgress.setDepartmentOrganisation(departmentOrganisation);
							
						 }

						
						existingProblemProgress.setStatus(IConstants.DEPARTMENT_MODIFY);
						
						existingProblemProgress.setUpdatedTime(getCurrentDateAndTime());
						
						problemAssignedDepartmentDAO.save(existingProblemProgress);
					}
					else
					{
						ProblemAssignedDepartment problemAssignedDepartment = new ProblemAssignedDepartment();
						problemAssignedDepartment.setUserProblem(userProblem);
                       if (departmentId != null && !departmentId.equals(0L)) {
							
							ProblemSourceScope problemSourceScope = problemSourceScopeDAO.get(scopeId);
							DepartmentOrganisation departmentOrganisation = departmentOrganisationDAO.get(departmentId);
							ProblemCompleteLocation problemCompleteLocation = getProblemCompleteLocation(regionId, problemSourceScope.getScope());
							problemCompleteLocation = problemCompleteLocationDAO.save(problemCompleteLocation);
							problemAssignedDepartment.setDepartmentLocation(problemCompleteLocation);
							problemAssignedDepartment.setDepartmentOrganisation(departmentOrganisation);
							
						 }
                         problemAssignedDepartment.setStatus(IConstants.DEPARTMENT_MODIFY);
						
                         problemAssignedDepartment.setUpdatedTime(getCurrentDateAndTime());
						
						problemAssignedDepartmentDAO.save(problemAssignedDepartment);
		
					}

					// setting history and updated date details
					assignedProblemProgress
							.setPerformedDate(getCurrentDateAndTime());
					assignedProblemProgress
							.setProblemHistory(problemHistory);

					if (departmentId != null
							&& !departmentId.equals(0L)) {
						ProblemSourceScope problemSourceScope = problemSourceScopeDAO
								.get(scopeId);
						DepartmentOrganisation departmentOrganisation = departmentOrganisationDAO
								.get(departmentId);
						ProblemCompleteLocation problemCompleteLocation = getProblemCompleteLocation(
								regionId, problemSourceScope.getScope());
						problemCompleteLocation = problemCompleteLocationDAO
								.save(problemCompleteLocation);

						assignedProblemProgress
								.setDepartmentLocation(problemCompleteLocation);
						assignedProblemProgress
								.setDepartmentOrganisation(departmentOrganisation);
						assignedProblemProgress
								.setProblemSourceScopeConcernedDepartment(departmentOrganisation
										.getProblemDepartmentCategory());
					}

					if (pbStatus.equals(IConstants.DEPARTMENT_ADD)) {
						List<ProblemActivity> problemActivityLst = problemActivityDAO
								.getProblemActivityByName("DEPARTMENT_ADD");
						assignedProblemProgress
								.setProblemActivity(problemActivityLst
										.get(0));
					} else if (pbStatus
							.equals(IConstants.DEPARTMENT_MODIFY)) {
						List<ProblemActivity> problemActivityLst = problemActivityDAO
								.getProblemActivityByName("DEPARTMENT_UPDATE");
						assignedProblemProgress
								.setProblemActivity(problemActivityLst
										.get(0));
					} else if (pbStatus
							.equals(IConstants.DEPARTMENT_DELETE)) {
						List<ProblemActivity> problemActivityLst = problemActivityDAO
								.getProblemActivityByName("DEPARTMENT_DELETE");
						assignedProblemProgress
								.setProblemActivity(problemActivityLst
										.get(0));
					}

					// save data
					assignedProblemProgressDAO
							.save(assignedProblemProgress);

						return rs;
					}

				});

		return resultStatus;
	}

	public ResultStatus updateProblemClassificationData(Long problemHistoryId,
			String classification, String type) {

		if (log.isDebugEnabled())
			log.debug(" Started setting problem as " + classification);

		ResultStatus rs = new ResultStatus();

		try {

			List<ProblemClassification> problemClassification = problemClassificationDAO
					.findByClassification(classification);

			if (problemClassification != null
					&& problemClassification.size() > 0)
				rs = updateProblemClassification(problemHistoryId,
						problemClassification.get(0)
								.getProblemClassificationId(), type);

		} catch (Exception ex) {

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

		if (log.isDebugEnabled())
			log.debug(" Setting  problem status to " + status);

		ResultStatus rs = new ResultStatus();

		try {

			String problemStatus = "";
			if (status.equalsIgnoreCase(IConstants.PROGRESS))
				problemStatus = IConstants.PROGRESS;
			else if (status.equalsIgnoreCase(IConstants.PENDING))
				problemStatus = IConstants.PENDING;
			else if (status.equalsIgnoreCase(IConstants.FIXED))
				problemStatus = IConstants.FIXED;
			else if (status.equalsIgnoreCase(IConstants.NEW))
				problemStatus = IConstants.NEW;

			List<ProblemStatus> problemStatusLst = problemStatusDAO
					.getByStatus(problemStatus);

			if (problemStatusLst != null && problemStatusLst.size() > 0)
				rs = updateProblemStatus(problemHistoryId, problemStatusLst
						.get(0).getProblemStatusId(), status);

		} catch (Exception ex) {

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
	 * 
	 * @param scopeId
	 * @return List<SelectOptionVO>
	 * 
	 * @author Sai Krishna
	 
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDepartmentsForADepartmentResolvingAreaScope(
			Long scopeId) {

		if (log.isDebugEnabled())
			log.debug("Started executing method to get departments ..");

		List<SelectOptionVO> departments = new ArrayList<SelectOptionVO>();

		List departmentsList = departmentOrganisationDAO
				.getDepartmentsBasedOnProblemResolvingDepartmentScope(scopeId);
		if (departmentsList != null && departmentsList.size() > 0) {

			Iterator lstItr = departmentsList.listIterator();
			while (lstItr.hasNext()) {

				Object[] values = (Object[]) lstItr.next();

				departments.add(new SelectOptionVO((Long) values[0],
						(String) values[1]));
			}
		}

		return departments;
	}

	public ProblemBeanVO getProblemCompleteInfoForAUserBasedOnHistoryId(
			Long problemHistoryId) {

		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		List<ProblemHistory> problemHistory = problemHistoryDAO
				.getProblemHistoryBasedOnId(problemHistoryId);
		ProblemHistory problemHistoryObj;

		if (problemHistory != null) {

			problemHistoryObj = problemHistory.get(0);

			problemBeanVO.setProblem(problemHistoryObj.getProblemLocation()
					.getProblemAndProblemSource().getProblem().getProblem());
			problemBeanVO
					.setDescription(problemHistoryObj.getProblemLocation()
							.getProblemAndProblemSource().getProblem()
							.getDescription());
			problemBeanVO.setProblemScope(problemHistoryObj
					.getProblemLocation().getProblemImpactLevel().getScope());
			problemBeanVO.setState(problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getState().getStateName());
			if (problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getDistrict() != null)
				problemBeanVO.setDistrict(problemHistoryObj
						.getProblemLocation().getProblemCompleteLocation()
						.getDistrict().getDistrictName());
			if (problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getConstituency() != null)
				problemBeanVO.setConstituency(problemHistoryObj
						.getProblemLocation().getProblemCompleteLocation()
						.getConstituency().getName());
			if (problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getTehsil() != null)
				problemBeanVO.setMandal(problemHistoryObj.getProblemLocation()
						.getProblemCompleteLocation().getTehsil()
						.getTehsilName());
			if (problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getHamlet() != null)
				problemBeanVO.setVillage(problemHistoryObj.getProblemLocation()
						.getProblemCompleteLocation().getHamlet()
						.getHamletName());
			if (problemHistoryObj.getProblemLocation()
					.getProblemCompleteLocation().getLocalElectionBody() != null)
				problemBeanVO.setMandal(problemHistoryObj.getProblemLocation()
						.getProblemCompleteLocation().getLocalElectionBody()
						.getName());
			Date iDateOfAddNewProb = problemHistoryObj.getProblemLocation()
					.getProblemAndProblemSource().getProblem()
					.getIdentifiedOn();
			Date eDateOfAddNewProb = problemHistoryObj.getProblemLocation()
					.getProblemAndProblemSource().getProblem()
					.getExistingFrom();
			problemBeanVO.setReportedDate(sdf.format(iDateOfAddNewProb));
			problemBeanVO.setExistingFrom(sdf.format(eDateOfAddNewProb));
			problemBeanVO.setProbSource(problemHistoryObj.getProblemLocation()
					.getProblemAndProblemSource().getProblemSource()
					.getInformationSource());

		}
		return problemBeanVO;
	}

	public List<SelectOptionVO> getCadreProblemDetailsForSms(Long userId,Long cadreId, Long pHistoryId) {
		try {
			List<SelectOptionVO> list = new ArrayList<SelectOptionVO>(0);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			List<Long> cadreList = new ArrayList<Long>(0);
			cadreList.add(cadreId);
			String message = "";
			ProblemHistory problemHistory = null;
			long sourceId;
			User user = null;
			List<Object> mobileNos = cadreDAO.getMobileNosOfCadre(cadreList);

			if (mobileNos != null && mobileNos.size() >= 0
					&& mobileNos.get(0).toString().trim().length() > 0)
				selectOptionVO.setId(Long
						.parseLong(mobileNos.get(0).toString()));
			else
				selectOptionVO.setId(0L);

			problemHistory = problemHistoryDAO.get(pHistoryId);
			sourceId = problemHistory.getProblemLocation()
					.getProblemAndProblemSource().getProblemSource()
					.getInformationSourceId();

			if (sourceId == 1) {
				user = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getUser();
				message += user.getFirstName() + IConstants.SPACE
						+ user.getLastName() + IConstants.COMMA;
				message += user.getMobile() != null ? user.getMobile()
						+ IConstants.COMMA : "";
			} else if (sourceId == 2 || sourceId == 3) {
				message += problemHistory.getProblemLocation()
						.getProblemAndProblemSource()
						.getProblemExternalSource().getName()
						+ IConstants.COMMA;
				message += problemHistory.getProblemLocation()
						.getProblemAndProblemSource()
						.getProblemExternalSource().getMobile() != null ? problemHistory
						.getProblemLocation().getProblemAndProblemSource()
						.getProblemExternalSource().getMobile()
						+ IConstants.COMMA
						: "";
			}

			else if (sourceId == 4) {
				List<Object[]> cadreDetails = cadreProblemDetailsDAO
						.getCadreDetailsAndMobileNoByProblemHistoryId(problemHistory
								.getProblemHistoryId());
				if (cadreDetails != null && cadreDetails.size() > 0) {
					message += cadreDetails.get(0)[0].toString()
							+ IConstants.SPACE
							+ cadreDetails.get(0)[1].toString()
							+ IConstants.COMMA;
					message += cadreDetails.get(0)[2] != null ? cadreDetails
							.get(0)[2].toString()
							+ IConstants.COMMA : "";
				}
			}

			message += problemManagementReportService.getProblemLocation(
					problemHistory.getProblemLocation().getProblemImpactLevel()
							.getRegionScopesId(), problemHistory
							.getProblemLocation().getProblemImpactLevelValue())
					+ ".";

			message += "\nPlease Help them in Problem Resolving.";
			message += "\nProblem : "
					+ problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getProblem()
							.getProblem() + ".";
			message += "\nDescription : "
					+ problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getProblem()
							.getDescription() + ".";

			selectOptionVO.setName(message);
			list.add(selectOptionVO);
			return list;
		} catch (Exception e) {
			log.error("Exception Ocuured in getCadreProblemDetailsForSms() "
					+ e);
			return null;
		}
	}

	
	public List<SelectOptionVO> getCadreProblemDetailsForSms(Long userId,Long cadreId, Long pHistoryId) {
		try {
			List<SelectOptionVO> list = new ArrayList<SelectOptionVO>(0);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			List<Long> cadreList = new ArrayList<Long>(0);
			cadreList.add(cadreId);
			String message = "";
			List<UserProblem> userProblem = null;
			Problem problem = null;
			long sourceId;
			User user = null;
			List<Object> mobileNos = cadreDAO.getMobileNosOfCadre(cadreList);

			if (mobileNos != null && mobileNos.size() >= 0
					&& mobileNos.get(0).toString().trim().length() > 0)
				selectOptionVO.setId(Long
						.parseLong(mobileNos.get(0).toString()));
			else
				selectOptionVO.setId(0L);
			
			problem = problemDAO.get(pHistoryId);
			
			userProblem = userProblemDAO.getUserProblemIdForCadreProblems(pHistoryId, userId);
			if(userProblem != null && userProblem.size() >0)
				user = userProblem.get(0).getUser();
				
			
			sourceId = problem.getInformationSource().getInformationSourceId();

			if (sourceId == 1) {
				
				message += user.getFirstName() + IConstants.SPACE
						+ user.getLastName() + IConstants.COMMA;
				message += user.getMobile() != null ? user.getMobile()
						+ IConstants.COMMA : "";
			} else if (sourceId == 2 || sourceId == 3) {
				
				
				message += problem.getExternalSource().getName()+IConstants.COMMA;
				message += problem.getExternalSource().getMobile()+IConstants.COMMA;
			}

			else if (sourceId == 4) {
				List<Object[]> cadreDetails = cadreProblemsDAO
						.getCadreDetailsAndMobileNoByProblemId(problem.getProblemId());
				if (cadreDetails != null && cadreDetails.size() > 0) {
					message += cadreDetails.get(0)[0].toString()
							+ IConstants.SPACE
							+ cadreDetails.get(0)[1].toString()
							+ IConstants.COMMA;
					message += cadreDetails.get(0)[2] != null ? cadreDetails
							.get(0)[2].toString()
							+ IConstants.COMMA : "";
				}
			}

			
			message += problemManagementReportService.getProblemLocation(problem.getRegionScopes().getRegionScopesId()
					     ,problem.getImpactLevelValue());
			
			message += "\nPlease Help them in Problem Resolving.";
			
			message += "\nProblem : "
					+ problem.getTitle()+ ".";
			message += "\nDescription : "
					+ problem.getDescription() + ".";

			selectOptionVO.setName(message);
			list.add(selectOptionVO);
			return list;
		} catch (Exception e) {
			log.error("Exception Ocuured in getCadreProblemDetailsForSms() "
					+ e);
			return null;
		}
	}

	
	public ResultStatus sendSMS(Long userId, String message, String moduleName,
			String[] phoneNumbers) {
		ResultStatus resultStatus = new ResultStatus();
		try {
			Long result = smsCountrySmsService.sendSms(message, true, userId,
					moduleName, phoneNumbers);
			if (result.longValue() == 0) {
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				resultStatus.setResultPartial(false);
			}
			return resultStatus;
		} catch (Exception e) {
			log.error("Exceprtion Occured in Sending SMS " + e.getMessage());
			resultStatus.setExceptionEncountered(e.getCause());
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public ResultStatus sendSMSFromAdmin(String message,String[] phoneNumbers)
	{
		ResultStatus resultStatus = new ResultStatus();
		try {
			resultStatus =  smsCountrySmsService.sendSmsFromAdmin(message,true,phoneNumbers);
			return resultStatus;
		} catch (Exception e) {
			log.error("Exceprtion Occured in Sending SMS " + e.getMessage());
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}

	public String getRefNo(String str, String type) {
		boolean i = true;
		try {
			while (i) {
				if (problemBackupDAO.getProblemReferenceNo(refNo) != null)
					return type + str;
			}
		} catch (Exception e) {
			log.debug("Exception Encountered", e);
		}
		return type + str;
	}

	*//**
	 * This method will send a message, when PA User/Free User Post A Problem.
	 * 
	 * @param Long
	 *            ProblemHistoryId
	 * @return {@link ResultStatus}
	 * @author Kamalakar Dandu
	 * 
	 * 
	 *//*
	public ResultStatus sendSuccessMsgToMobile(Long problemHistoryId) {
		ResultStatus resultStatus = new ResultStatus();
		try {
			if (problemHistoryId == null || problemHistoryId <= 0)
				return null;

			String message = null;
			String[] mobileArray = new String[1];
			String mobile = null;
			ProblemHistory problemHistory = problemHistoryDAO
					.get(problemHistoryId);
			Long userId = null;
			String userType = null;

			if (problemHistory.getProblemLocation()
					.getProblemAndProblemSource().getProblemSource() != null) {
				
				userType = IConstants.PARTY_ANALYST_USER;
				
				long ProblemSource = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getProblemSource()
						.getInformationSourceId();
				userId = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getUser()
						.getUserId();

				if (ProblemSource == 1) {
					mobile = problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getUser().getMobile();
				} else if (ProblemSource == 2 || ProblemSource == 3) {
					mobile = problemHistory.getProblemLocation()
							.getProblemAndProblemSource()
							.getProblemExternalSource().getMobile();
				} else if (ProblemSource == 4) {
					List<Object[]> cadreDetails = cadreProblemDetailsDAO
							.getCadreDetailsAndMobileNoByProblemHistoryId(problemHistory
									.getProblemHistoryId());
					if (cadreDetails != null && cadreDetails.size() > 0) {
						mobile = cadreDetails.get(0)[2].toString();
					}
				}
			} else {
				
				userType = IConstants.FREE_USER;
						
				mobile = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getFreeUser()
						.getMobile();
				userId = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getFreeUser()
						.getUserId();
			}

			message = "Your Problem Posted Successfully.";
			message += "\nYour Problem Reference Number is : "
					+ problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getProblem()
							.getReferenceNo() + ".";
			message += "\nThis Will be usefull for Further Reference";

			if (mobile != null && mobile.length() >= 10) {
				mobileArray[0] = mobile;
				ResultStatus result = null;
				
				if(userType.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER))
					result = sendSMS(userId, message,IConstants.PROBLEM_MANAGEMENT, mobileArray);
				else
					result = sendSMSFromAdmin(message,mobileArray);
				
				if (result.getResultCode() == ResultCodeMapper.SUCCESS) {
					log.debug("Message Sent Successfully To - " + mobile);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				} else {
					log.warn("Message Not Reached To - " + mobile);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setExceptionEncountered(result
							.getExceptionEncountered());
				}
			} else {
				log.debug("Mobile Number is Not Valid");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setExceptionMsg("Mobile NO is Not Valid");
			}
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception Occured & Exception is - " + e);
			return resultStatus;
		}
	}
	
	
	public ResultStatus sendSuccessMsgToMobile(Long userProblemId) {
		ResultStatus resultStatus = new ResultStatus();
		try {
			if (userProblemId == null || userProblemId <= 0)
				return null;

			String message = null;
			String[] mobileArray = new String[1];
			String mobile = null;
			ProblemHistory problemHistory = problemHistoryDAO
					.get(problemHistoryId);
			UserProblem userProblem = userProblemDAO.get(userProblemId);
			Long userId = null;
			String userType = null;

			if (userProblem.getProblem().getInformationSource() != null)
			{
				userType = IConstants.PARTY_ANALYST_USER;
				
				long ProblemSource = userProblem.getProblem().getInformationSource().getInformationSourceId();
				userId = userProblem.getUser().getUserId();

				if (ProblemSource == 1) 
					mobile = userProblem.getUser().getMobile();
				
				else if (ProblemSource == 2 || ProblemSource == 3)
					mobile = userProblem.getProblem().getExternalSource().getMobile();
				
				else if (ProblemSource == 4) 
				{
					List<Object[]> cadreDetails = cadreProblemsDAO.getCadreDetailsAndMobileNoByProblemId(userProblem.getProblem().getProblemId());
						
					if (cadreDetails != null && cadreDetails.size() > 0) {
						mobile = cadreDetails.get(0)[2].toString();
					}
				}
			}
			else {
				
				userType = IConstants.FREE_USER;
					
				mobile = userProblem.getUser().getMobile();
				userId = userProblem.getUser().getUserId();
						
			}

			message = "Your Problem Posted Successfully.";
			message += "\nYour Problem Reference Number is : "
					+ userProblem.getProblem().getReferenceNo()+ ".";
			message += "\nThis Will be usefull for Further Reference";

			if (mobile != null && mobile.length() >= 10) 
			{
				mobileArray[0] = mobile;
				ResultStatus result = null;
				
				if(userType.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER))
					result = sendSMS(userId, message,IConstants.PROBLEM_MANAGEMENT, mobileArray);
				else
					result = sendSMSFromAdmin(message,mobileArray);
				
				if (result.getResultCode() == ResultCodeMapper.SUCCESS) {
					log.debug("Message Sent Successfully To - " + mobile);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				} else {
					log.warn("Message Not Reached To - " + mobile);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setExceptionEncountered(result
							.getExceptionEncountered());
				}
			} else {
				log.debug("Mobile Number is Not Valid");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setExceptionMsg("Mobile NO is Not Valid");
			}
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception Occured & Exception is - " + e);
			return resultStatus;
		}
	}
	
	
	
	public ResultStatus sendEmailToFreeUserAfterProblemAdded(Long problemHistoryId)
	{
		ResultStatus resultStatus = new ResultStatus();
		
		if(problemHistoryId == null || problemHistoryId <= 0)
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
		try
		{
			String email = null;
			Long noOfFilesAttached = null;
			
			ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryId);
		
			if(problemHistory != null)
			{
				ProblemDetailsVO problemDetailsVO = new ProblemDetailsVO();
				EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
				
				email = problemHistory.getProblemLocation()
						.getProblemAndProblemSource().getFreeUser()
						.getEmail();
				
				if(email != null && email.trim().length() > 0)
				{
					List<Object> obj = problemFileDAO.getNoOfFilesUploadedForAUser(problemHistoryId);
					
					noOfFilesAttached = (Long)obj.get(0);
					
					problemDetailsVO.setDescription(problemHistory.getProblemLocation().getProblemAndProblemSource()
							.getProblem().getDescription());
					problemDetailsVO.setDefinition(problemHistory.getProblemLocation().getProblemAndProblemSource()
							.getProblem().getProblem());
					problemDetailsVO.setIdentifiedDate(problemHistory.getProblemLocation().getProblemAndProblemSource()
							.getProblem().getExistingFrom().toString());
					problemDetailsVO.setExistingFrom(problemHistory.getProblemLocation().getProblemAndProblemSource()
							.getProblem().getIdentifiedOn().toString());
					problemDetailsVO.setLocation(problemManagementReportService.getProblemLocation(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(),
							problemHistory.getProblemLocation().getProblemImpactLevelValue()));
					
					problemDetailsVO.setProblemID(noOfFilesAttached);
					
					emailDetailsVO.setFromAddress(problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getFreeUser()
							.getFirstName()+" "+ problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getFreeUser()
							.getLastName());
					emailDetailsVO.setToAddress(email);
					emailDetailsVO.setElectionType(problemHistory.getProblemLocation()
							.getProblemAndProblemSource().getProblem()
							.getReferenceNo());
					problemDetailsVO.setEmailDetailsVO(emailDetailsVO);
					
					mailsSendingService.sendEmailToFreeUserAfterProblemAdded(problemDetailsVO);
				}
			}
		return resultStatus;
				}
		catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception Occured in sendEmailToFreeUserAfterProblemAdded() , Exception is - "+e);
			return resultStatus;
		}
	}

	
	public ResultStatus sendEmailToFreeUserAfterProblemAdded(ProblemBeanVO problemBeanVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		
		if(problemBeanVO == null)
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
		try
		{
			String email = null;
			Long noOfFilesAttached = null;
				ProblemDetailsVO problemDetailsVO = new ProblemDetailsVO();
				EmailDetailsVO emailDetailsVO = new EmailDetailsVO();
				
				email = problemBeanVO.getEmail();
				
				if(email != null && email.trim().length() > 0)
				{
					List<Object> obj = problemFilesDAO.getNoOfFilesUploadedForAUser(problemBeanVO.getProblemId());
					
					noOfFilesAttached = (Long)obj.get(0);
					
					problemDetailsVO.setDescription(problemBeanVO.getDescription());
					problemDetailsVO.setDefinition(problemBeanVO.getProblem());
					problemDetailsVO.setIdentifiedDate(problemBeanVO.getExistingFrom().toString());
					problemDetailsVO.setExistingFrom(problemBeanVO.getReportedDate().toString());
					problemDetailsVO.setLocation(problemManagementReportService.getProblemLocation(problemBeanVO.getProblemImpactLevelId(),
							problemBeanVO.getProblemImpactLevelValue()));
					
					problemDetailsVO.setProblemID(noOfFilesAttached);
					
					emailDetailsVO.setFromAddress(problemBeanVO.getName());
					emailDetailsVO.setToAddress(problemBeanVO.getEmail());
					emailDetailsVO.setElectionType(problemBeanVO.getProblemRefNum());
					problemDetailsVO.setEmailDetailsVO(emailDetailsVO);
					
					mailsSendingService.sendEmailToFreeUserAfterProblemAdded(problemDetailsVO);
				}
			
		return resultStatus;
				}
		catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			log.error("Exception Occured in sendEmailToFreeUserAfterProblemAdded() , Exception is - "+e);
			return resultStatus;
		}
	}
	
	public List<FileVO> getImageDetails()
	{
		List<FileVO> fileVOList  = new ArrayList<FileVO>();
		List<ProblemFile> result1 = problemFileDAO.getAllNonApprovedFilesAndProblemDetails();
		
		fileVOList = setProblemFileToFileVO(result1);
		
		return fileVOList;
		
	}
	public List<FileVO> getImageDetails()
	{
		List<FileVO> fileVOList  = new ArrayList<FileVO>();
		DateUtilService dateUtilService = new DateUtilService();
		try
		{
			if(log.isDebugEnabled())
				log.debug("Entered into getImageDetails() of problemManagementService");
		
		    List<Object[]> object = problemFilesDAO.getCurrentDateFiles(null,null,"false");
		    fileVOList = setFilesToVO(object);
		}   
		catch(Exception e)
		{
			log.error("Exception occured in getImageDetails() of problemManagementService", e);
		}
		return fileVOList;
	}
	public List<FileVO> setFilesToVO(List<Object[]> object)
	{
		List<FileVO> fileVOList =null;
		FileVO fileVO = null;
		try
		{
			if(log.isDebugEnabled())
				log.debug("Entered into setFilesToVO() of problemManagementService");
		
		  if(object != null && object.size()>0)
		  {
			  fileVOList = new ArrayList<FileVO>();
			for(Object[] params : object)
			{
				fileVO = new FileVO();
				fileVO.setProblemFileId((Long)params[0]);
				fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(params[1].toString()));
				fileVO.setFileDescription1(CommonStringUtils.removeSpecialCharsFromAString(params[2].toString()));
				fileVO.setProblem(params[3].toString());
				if(params[4]!= null && params[4].toString().equalsIgnoreCase(IConstants.FALSE))
					fileVO.setScope(IConstants.NEW);
				if(params[4]!= null && params[4].toString().equalsIgnoreCase(IConstants.TRUE))
					fileVO.setScope(IConstants.APPROVED);
				if(params[4]!= null && params[4].toString().equalsIgnoreCase(IConstants.REJECTED))
					fileVO.setScope(IConstants.REJECTED);
				
				fileVO.setExistingDateFrom(params[5].toString());
				fileVO.setIdentifiedDateOn(params[6].toString());
				fileVO.setName(params[7].toString()+""+params[8].toString());
				fileVO.setFileName1(params[9].toString());
				
				
			
				fileVOList.add(fileVO);
			}
		  }
		}
		catch(Exception e)
		{
			log.error("Exception occured in setFilesToVO() of problemManagementService", e);
		}
		return fileVOList;
	}
	

	public void acceptSelectedImagesByAdmin(final Integer[] problemFileIds) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				for (int i = 0; i < problemFileIds.length; i++) {
					ProblemFile problemFile = problemFileDAO
							.get(problemFileIds[i].longValue());
					problemFile.setIsApproved(IConstants.TRUE);
					problemFileDAO.save(problemFile);
					ProblemFiles problemFiles = problemFilesDAO.get(problemFileIds[i].longValue());
					problemFiles.setIsApproved(IConstants.TRUE);
					problemFilesDAO.save(problemFiles);
				}
			}
		});
	}

	public void deleteSelectedImagesByAdmin(final Integer[] problemFileIds) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				for (int i = 0; i < problemFileIds.length; i++) {
					ProblemFile problemFile = problemFileDAO
							.get(problemFileIds[i].longValue());
					problemFile.setIsApproved(IConstants.REJECTED);
					problemFileDAO.save(problemFile);
					ProblemFiles problemFiles = problemFilesDAO.get(problemFileIds[i].longValue());
					problemFiles.setIsApproved(IConstants.REJECTED);
					problemFilesDAO.save(problemFiles);
				}
			}
		});
	}

	public List<FileVO> getAllProblemRelatedImages(Long problemId,Long userId) {

		List<FileVO> fileVOList = new ArrayList<FileVO>();
		FileVO fileVO = new FileVO();

		List<Object[]> imagesList = filePathsDAO.getProblemRelatedFiles(problemId,userId);
		for (Object[] images : imagesList) {
			fileVO = new FileVO();
			fileVO.setFile(images[2].toString());
			fileVO.setTitle(images[0].toString());
			fileVO.setDescription(images[1].toString());
			fileVO.setPathOfFile(IConstants.UPLOADED_FILES + "/Problem_Files/"
					+ images[2]);
			fileVOList.add(fileVO);
		}
		return fileVOList;
	}

	*//**
	 * Method is used to upload diffrent type of files
	 * 
	 * @param problemBeanVO
	 * @return list of file Objects
	 *//*

	public List<File> uploadFiles(ProblemBeanVO problemBeanVO) {

		File file = new File();
		FilePaths filePaths = new FilePaths();
		FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
		ProblemFiles problemFiles = new ProblemFiles();
		
		List<File> filesList = new ArrayList<File>();
		Long orderNo = 0L;

		try {
			if (problemBeanVO.getFileVO().getFileTitle() != null) {

				for (int i = 0; i < problemBeanVO.getFileVO().getFileName().size(); i++) {
					file = new File();
					fileSourceLanguage = new FileSourceLanguage();
					filePaths = new FilePaths();
					file.setFileName(problemBeanVO.getFileVO().getFileName().get(i));
					
					if (problemBeanVO.getFileVO().getFileTitle().get(i) != null)
						file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(problemBeanVO.getFileVO().getFileTitle().get(i)));
					if (problemBeanVO.getFileVO().getFileDescription().get(i) != null)
						file.setFileDescription(CommonStringUtils.removeSpecialCharsFromAString(problemBeanVO.getFileVO().getFileDescription().get(i)));
					file = fileDAO.save(file);
					fileSourceLanguage.setLanguage(null);
					fileSourceLanguage.setSource(null);
					fileSourceLanguage.setFile(file);
					fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
					filePaths.setFileSourceLanguage(fileSourceLanguage);
					if (fileTypeDAO.getFileType(problemBeanVO.getFileVO().getFileContentType().get(i)).get(0).getFileTypeId() != 0)
						filePaths.setFileType(fileTypeDAO.getFileType(problemBeanVO.getFileVO().getFileContentType().get(i)).get(0));
					List<Object> maxOrderNo = filePathsDAO.getMaxOrderNo();
					if(maxOrderNo.size() ==0 && maxOrderNo.get(0)!=null)
						orderNo = orderNo +1L;
					else
						orderNo = (Long)maxOrderNo.get(0)+1L;
					
					filePaths.setOrderNo(orderNo);
					filePaths.setFilePath(problemBeanVO.getFileVO().getFilePath().get(i).trim());
					filePathsDAO.save(filePaths);
					filesList.add(file);
					problemFiles.setFile(file);
				}

			}
			return filesList;
		} catch (Exception e) {
			e.printStackTrace();
			return filesList;
		}
	}

	public void saveProblemFiles(ProblemBeanVO problemBeanVO) {
		List<File> files = new ArrayList<File>();
		ProblemFile problemFile = null;
		files = uploadFiles(problemBeanVO);
		if (files != null) {
			for (File fileObj : files) {

				problemFile = new ProblemFile();

				if (problemBeanVO.getProblemPostedBy() != null
						&& problemBeanVO.getProblemPostedBy().equals(
								IConstants.PARTY_ANALYST_USER))
					problemFile.setIsApproved(IConstants.TRUE);

				problemFile.setFile(fileObj);
				
			
				ProblemHistory problemHistory = problemHistoryDAO
						.get(problemBeanVO.getProblemHistoryId());
				problemFile.setProblemHistory(problemHistory);
				
				problemFileDAO.save(problemFile);
			}
		}
	}
	
	public void saveProblemRelatedFiles(ProblemBeanVO problemBeanVO,Problem problem,UserProblem userProb)
	{
		try{
		
		List<File> files = new ArrayList<File>(0);
		ProblemFiles problemFile = null;
		ProblemProgress problemProgress = null;
		List<UserProblem> userProblem = null;
		List<ProblemActivity> problemActivity = null;
		
		files = uploadFiles(problemBeanVO);
		
		if (files != null)
		{
			for (File fileObj : files)
			{
				problemFile = new ProblemFiles();

				if(problemBeanVO.getHasPartyAnalystUserRole() != null && problemBeanVO.getHasPartyAnalystUserRole())
					problemFile.setIsApproved(IConstants.TRUE);
				else
					problemFile.setIsApproved(IConstants.TRUE);
					
				problemFile.setFile(fileObj);
				problemFile.setProblem(problem); 
				problemFile.setUser(userDAO.get(problemBeanVO.getUserID()));
				if(userProb != null)
					problemFile.setVisibility(userProb.getVisibility());
				else
					problemFile.setVisibility(visibilityDAO.get(1l));
				problemFile.setIsDelete(IConstants.FALSE);
				problemFile.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				problemFile.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				
				problemFilesDAO.save(problemFile);
			}
		}
		if(problemBeanVO.getProblemStatus() != null && problemBeanVO.getProblemStatus().equals("PROBLEM_FILE_ADD"))
		{
			problemProgress = new ProblemProgress();
			problemActivity = problemActivityDAO.getProblemActivityByName(problemBeanVO.getProblemStatus());
			userProblem = userProblemDAO.getUserProblemIdForCadreProblems(problem.getProblemId(), problemBeanVO.getUserID());
			problemProgress.setProblemActivity(problemActivity.get(0));
			problemProgress.setUserProblem(userProblem.get(0));
			problemProgress.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			problemProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			problemProgress.setIsDelete(IConstants.FALSE);
			if(userProblem.get(0).getVisibility().getType() != null && userProblem.get(0).getVisibility().getType().equals(IConstants.PUBLIC))
				problemProgress.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PUBLIC).get(0));
			else if(userProblem.get(0).getVisibility().getType() != null && userProblem.get(0).getVisibility().getType().equals(IConstants.PRIVATE))
			   problemProgress.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PRIVATE).get(0));
			problemProgressDAO.save(problemProgress);
			
		}
		
		}catch (Exception e) {
			log.error("Exception occured in saveProblemRelatedFiles() Method, Exception - "+e);
			e.printStackTrace();
		}
	}
	
	public ResultStatus addProblemRelatedFiles(ProblemBeanVO problemBeanVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		if(problemBeanVO == null)
		{
			log.error("problemBeanVO is null in addProblemRelatedFiles()");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		try{
			if(problemBeanVO != null && problemBeanVO.getProblemHistoryId() > 0)
			{
				Problem problem = problemDAO.get(problemBeanVO.getProblemHistoryId());
				List<UserProblem> userProblemList = userProblemDAO.getUserProblemByUserAndProblemId(problem.getProblemId(),problemBeanVO.getUserID());
				if(userProblemList.size() > 0)
				  saveProblemRelatedFiles(problemBeanVO,problem,userProblemList.get(0));
				else
				  saveProblemRelatedFiles(problemBeanVO,problem,null);	 
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			}
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception occured in addProblemRelatedFiles() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}

	
	 * This method retrives the problem details for BetweenDates
	 

	public List<FileVO> getAllApprovalProblemImagesBetweenEventDates(String fromDatestr, String toDatestr, String status, String type) {
		List<FileVO> problemImages = new ArrayList<FileVO>();
		List<ProblemFile> result = null;
		FileVO fileVO = null;
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null ;
			Date toDate = null;
			
			if(!fromDatestr.equalsIgnoreCase("") && !toDatestr.equalsIgnoreCase("")){
				 fromDate = dateFormat.parse(fromDatestr);
				 toDate = dateFormat.parse(toDatestr);
				 Calendar c = Calendar.getInstance();
				 c.setTime(fromDate);
				 c.setTime(toDate);
			}
			if (type.equalsIgnoreCase("Approved")) {
				result = problemFileDAO.getAllApprovedImagesBetweenDates(
						fromDate, toDate, status);
			} else if (type.equalsIgnoreCase("Rejected")) {
				result = problemFileDAO.getAllNoNApprovalImagesBetweenDates(
						fromDate, toDate, status);
			} else if (type.equalsIgnoreCase("Newly Posted")) {
				result = problemFileDAO.getAllPostedImagesBetweenDates(
						fromDate,toDate, status);
			}

			problemImages = setProblemFileToFileVO(result);

			return problemImages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return problemImages;

	}
	public List<FileVO> getProblemFilesBetweanDates(String fromDatestr, String toDatestr,String choice)
	{
		List<FileVO> fileVOList  = new ArrayList<FileVO>();
		DateUtilService dateUtilService = new DateUtilService();
		try
		{
			
			String isApproved = null;
			if(log.isDebugEnabled())
				log.debug("Entered into getImageDetails() of problemManagementService");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(choice.equalsIgnoreCase(IConstants.NEW))
				isApproved = IConstants.FALSE;
			else if(choice.equalsIgnoreCase(IConstants.APPROVED))
				isApproved = IConstants.TRUE;
			else if(choice.equalsIgnoreCase(IConstants.REJECTED))
				isApproved = IConstants.REJECTED;
		    List<Object[]> object = problemFilesDAO.getCurrentDateFiles(dateFormat.parse(fromDatestr),dateFormat.parse(toDatestr),isApproved);
		    fileVOList = setFilesToVO(object);
		}   
		catch(Exception e)
		{
			log.error("Exception occured in getImageDetails() of problemManagementService", e);
		}
		return fileVOList; 
		
		
	}

	public List<FileVO> getAllApprovalProblemImagesForParticularDate(String particularDateStr, String status, String type) {

		List<FileVO> problemImageDetails = new ArrayList<FileVO>();
		List<ProblemFile> result = null;
		FileVO fileVO = null;
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date particularDate = null ;
			
			if(!particularDateStr.equalsIgnoreCase("")){
				 particularDate = dateFormat.parse(particularDateStr);
				 Calendar c = Calendar.getInstance();
				 c.setTime(particularDate);
			}
			
			if (type.equalsIgnoreCase("Approved")) {
				result = problemFileDAO.getApprovalImagesForParticularDate(particularDate, status);
			} else if (type.equalsIgnoreCase("Rejected")) {
				result = problemFileDAO.getAllNonApprovalImagesForParticularDate(particularDate, status);
			} else if (type.equalsIgnoreCase("Newly Posted")) {
				result = problemFileDAO.getAllImagesForParticularDate(particularDate, status);

			}
		
			if (result != null) {
				
				problemImageDetails = setProblemFileToFileVO(result);
				
				for (Object[] objects : result) {
					fileVO = new FileVO();
					fileVO.setProblemFileId((Long) objects[0]);
					fileVO.setProblem(objects[1].toString());
				
					fileVO.setFileTitle1(objects[2].toString());
					fileVO.setFileDescription1(objects[3].toString());
					fileVO.setScope(objects[4].toString());
					fileVO.setIdentifiedOn((Date) objects[5]);
					fileVO.setExistingFrom((Date) objects[6]);
					fileVO.setName(objects[7].toString());
					fileVO.setLastName(objects[8].toString());
					fileVO.setFileName1(objects[9].toString());
				
					problemImageDetails.add(fileVO);
					}

			}

			return problemImageDetails;

		} catch (Exception e) {
			e.printStackTrace();
			return problemImageDetails;
		}

	}
	public List<FileVO> getProblemFilesForParticularDate(String particularDateStr,String choice) 
	{
		List<FileVO> fileVOList  = new ArrayList<FileVO>();
		DateUtilService dateUtilService = new DateUtilService();
		try
		{
			String isApproved = null;
			if(log.isDebugEnabled())
				log.debug("Entered into getImageDetails() of problemManagementService");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(choice.equalsIgnoreCase(IConstants.NEW))
				isApproved = IConstants.FALSE;
			else if(choice.equalsIgnoreCase(IConstants.APPROVED))
				isApproved = IConstants.TRUE;
			else if(choice.equalsIgnoreCase(IConstants.REJECTED))
				isApproved = IConstants.REJECTED;
		    List<Object[]> object = problemFilesDAO.getCurrentDateFiles(dateFormat.parse(particularDateStr),null,isApproved);
		    fileVOList = setFilesToVO(object);
		}   
		catch(Exception e)
		{
			log.error("Exception occured in getImageDetails() of problemManagementService", e);
		}
		return fileVOList;
		
	}
	
	public List<FileVO> setProblemFileToFileVO(List<ProblemFile> imageFilesList)
	{
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		FileVO fileVO = new FileVO();
		
		if(imageFilesList !=null && imageFilesList.size()>0)
		{
			for (ProblemFile problemFile : imageFilesList) {
				fileVO = new FileVO();
				if(problemFile.getFile() != null)
				{
					fileVO.setProblemFileId(problemFile.getProblemFileId());
					fileVO.setProblem(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
					fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(problemFile.getFile().getFileTitle()));
					fileVO.setFileDescription1(CommonStringUtils.removeSpecialCharsFromAString(problemFile.getFile().getFileDescription()));
					fileVO.setFileName1(problemFile.getFile().getFileName());
					fileVO.setScope(problemFile.getProblemHistory().getProblemLocation().getProblemImpactLevel().getScope());
					fileVO.setIdentifiedOn(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn());
					fileVO.setExistingFrom(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom());
					if(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getFreeUser() !=null && problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getFreeUser().getFirstName()!=null)
					{
					 fileVO.setName(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getFreeUser().getFirstName());
					 fileVO.setLastName(problemFile.getProblemHistory().getProblemLocation().getProblemAndProblemSource().getFreeUser().getLastName());
					}
					
					
					if(problemFile.getIsApproved()!=null && problemFile.getIsApproved().equalsIgnoreCase("true"))
						fileVO.setDescription("Approved");
					else if(problemFile.getIsApproved()!=null && problemFile.getIsApproved().equalsIgnoreCase("false"))
						fileVO.setDescription("Approved");
					else
						fileVO.setDescription("Pending");
					
					fileVOList.add(fileVO);
				 }
			   }
		}
		return fileVOList;
	}
	public List<ProblemBeanVO> getProblemDetailsForHomePage(int startIndex,int maxIndex)
	{
		if (log.isDebugEnabled())
			log.debug(" Enter into getProblemDetailsForHomePage method ..");
		
		List<ProblemBeanVO> returnVal = new ArrayList<ProblemBeanVO>();
		
		try{
			//getting all problem history id's
			List<Long> problemHistoryIds = problemHistoryDAO.getAllValidProblemIds(startIndex,maxIndex);
			
			for(Long problemId : problemHistoryIds){
				
				//getting details for a problem by using id
				ProblemBeanVO problemBeanVO = getProblemCompleteInfo(problemId);
				int regionScope = problemBeanVO.getProblemImpactLevelId().intValue();
				if(regionScope == 4){
					problemBeanVO.setUrl("constituencyPageAction.action?constituencyId="+problemBeanVO.getProblemLocationId()+"");
				}
				else if(regionScope == 3){
					problemBeanVO.setUrl("districtPageAction.action?districtId="+problemBeanVO.getProblemLocationId()+"&districtName="+problemBeanVO.getProblemLocation()+"");
				}
                else if(regionScope == 2){
                	problemBeanVO.setUrl("statePageAction.action?stateId="+problemBeanVO.getProblemLocationId()+"");
				}
				problemBeanVO.setTotalResultsCount(getProblemsCount().toString());
				//getting images for a problem
			    List<FileVO> fileVOList =  getAllProblemRelatedImages(problemId);
			  //setting images count for a problem to its VO
			    problemBeanVO.setFileCount(fileVOList.size());
			    //getting like,dislike,comments count for a problem
		        List<Object[]> problemHistoryList = userProblemApprovalDAO.getProblemHistoryDetails(problemId);
		       //adding like,dislike,comments count for a problem to its VO
		        for(Object[] problemHistory : problemHistoryList){
			    
				    if(problemHistory[1].toString().equalsIgnoreCase("Accept")){
					   problemBeanVO.setLikesCount((Long)problemHistory[2]);
				    }
				    else if(problemHistory[1].toString().equalsIgnoreCase("Reject")){
					   problemBeanVO.setDislikesCount((Long)problemHistory[2]);
				    }
                    else if(problemHistory[1].toString().equalsIgnoreCase("FollowUp")){
                	   problemBeanVO.setCommentCount((Long)problemHistory[2]);
				    } 
		      }
		        returnVal.add(problemBeanVO) ;
			}
			 
		}
		catch(Exception e){
			log.error("Exception Rised in getProblemDetailsForHomePage method ..", e);
		}
		return returnVal;
	}
	
	
	
	public List<ProblemBeanVO> getProblemDetailsForHomePage(int startIndex,int maxIndex)
	{
		log.debug("Entered into getProblemDetailsForHomePage()...");
		
		ProblemBeanVO problemBeanVO = null;
		List<ProblemBeanVO> problemDetails = new ArrayList<ProblemBeanVO>();
		try
		{
			List<Long> problemIds = userProblemDAO.getAllValidProblemIds(startIndex,maxIndex);
			
			if(problemIds != null && problemIds.size() > 0)
			{
				for(Long problemId : problemIds)
				{
					problemBeanVO = getProblemCompleteInfo(problemId);
								 
					int regionScope = problemBeanVO.getProblemImpactLevelId().intValue();
					
					if(regionScope == 4)
						problemBeanVO.setUrl("constituencyPageAction.action?constituencyId="+problemBeanVO.getProblemImpactLevelValue()+"");
					else if(regionScope == 3)
						problemBeanVO.setUrl("districtPageAction.action?districtId="+problemBeanVO.getProblemImpactLevelValue()+"&districtName="+problemBeanVO.getProblemLocation()+"");
		            else if(regionScope == 2)
		             	problemBeanVO.setUrl("statePageAction.action?stateId="+problemBeanVO.getProblemImpactLevelValue()+"");
					
					problemDetails.add(problemBeanVO);
				}
				
			}
			
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getProblemDetailsForHomePage() method of PromblemManagementService");
			return problemDetails;
		}
		return problemDetails;
	
	}
	
	//Problems for voters Page 
	public List<ProblemBeanVO> getProblemDetailsForVoterPage(Long userId,Long locationId,Long locationValue)
	{
	log.debug("Entered into getProblemDetailsForVoterPage().....");
	ProblemBeanVO problemBeanVO = null;
	List<ProblemBeanVO> resultList = new ArrayList<ProblemBeanVO>(0);
	String status = "";
	
	try{
		if(locationId == 5)
		{
			
			if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
			{
				locationId = 5l;
			String id = locationValue.toString().substring(1);
			locationValue = new Long(id);
			}
			else if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1"))
			{
			 List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1)));
				locationId = 7l;
				locationValue = new Long(localElectionBodyList.get(0));
			
			}
		}
		problemBeanVO = new ProblemBeanVO();
		List<Long> New = userProblemDAO.getAllProblemsByLocation(userId,locationId, locationValue,IConstants.NEW);
		List<Long> Progress = userProblemDAO.getAllProblemsByLocation(userId,locationId, locationValue,IConstants.PROGRESS);
		List<Long> Fixed = userProblemDAO.getAllProblemsByLocation(userId,locationId, locationValue,IConstants.FIXED);
		List<Long> Pending = userProblemDAO.getAllProblemsByLocation(userId,locationId, locationValue,IConstants.PENDING);
		List<Long> Cadre = userProblemDAO.getAllUserProblemsBySource(locationValue, userId, locationId,4l);
		List<Long> User = userProblemDAO.getAllUserProblemsBySource(locationValue, userId, locationId,1l);
		List<Long> callcenter =userProblemDAO.getAllUserProblemsBySource(locationValue, userId, locationId,3l);
		if(New != null && New.size() > 0)
			problemBeanVO.setNewStatusProblems(new Long(New.size()));
		else
			problemBeanVO.setNewStatusProblems((0l));
		if(Progress != null && Progress.size() > 0)
		problemBeanVO.setProgressProblems(new Long(Progress.size()));
		else
		problemBeanVO.setProgressProblems(0l);
		if(Pending != null && Pending.size() > 0)
		problemBeanVO.setPendingProblems(new Long(Pending.size()));
		else
		problemBeanVO.setPendingProblems(0l);	
		if(Fixed != null && Fixed.size() > 0)
		problemBeanVO.setFixedProblems(new Long(Fixed.size()));
		else
		problemBeanVO.setFixedProblems(0l);
		if(Cadre != null && Cadre.size() > 0)
		problemBeanVO.setCadreProblems(new Long(Cadre.size()));
		else
			problemBeanVO.setCadreProblems(0l);
		if(User != null && User.size() > 0)
		problemBeanVO.setUserProblems(new Long(User.size()));
		else
			problemBeanVO.setUserProblems(0l);
		if(callcenter != null && callcenter.size() > 0)
		problemBeanVO.setCallCenterProblems(new Long(callcenter.size()));
		else
			problemBeanVO.setCallCenterProblems(0l);	
		resultList.add(problemBeanVO);	
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		log.error("Exception Occured in getProblemDetailsForVoterPage() - +e");
	}
	return resultList;
	
	}
	public List<ProblemBeanVO> getProblemDetailsInfoVoterPage(Long userId,Long locationId,Long locationValue,String status,Long informationsrcId,Integer startIndex,Integer maxIndex)
	{
		List<ProblemBeanVO> problemDetails = new ArrayList<ProblemBeanVO>(0);
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		List<Long> problemIds = new ArrayList<Long>(0);
		List<Long> problemsCount = new ArrayList<Long>(0);

		try{
			if(locationId == 5)
			{
				if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				{
					locationId = 5l;
				String id = locationValue.toString().substring(1);
				locationValue = new Long(id);
				}
				else if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1"))
				{
				 List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationValue.toString().substring(1)));
					locationId = 7l;
					locationValue = new Long(localElectionBodyList.get(0));
				
				}
			}
			if(informationsrcId == 0)
			{
			 problemIds =userProblemDAO.getAllProblemsByLocation(userId,locationId,locationValue,status,startIndex,maxIndex);
			 problemsCount = userProblemDAO.getAllProblemsByLocation(userId,locationId,locationValue,status);
			}
			else if(status.equalsIgnoreCase(IConstants.NEW) && informationsrcId > 0)
			{
			problemIds =userProblemDAO.getAllProblemsBySource(locationValue,userId,locationId,informationsrcId,startIndex,maxIndex);
			 problemsCount = userProblemDAO.getAllUserProblemsBySource(locationValue,userId,locationId,informationsrcId);
			}
			if(problemIds != null && problemIds.size() > 0)
			{
			
			for(Long problemId : problemIds)
			{
				problemBeanVO = getProblemCompleteInfo(problemId);	
				
				 
				int regionScope = problemBeanVO.getProblemImpactLevelId().intValue();
				
				if(regionScope == 4)
					problemBeanVO.setUrl("constituencyPageAction.action?constituencyId="+problemBeanVO.getProblemImpactLevelValue()+"");
				else if(regionScope == 3)
					problemBeanVO.setUrl("districtPageAction.action?districtId="+problemBeanVO.getProblemImpactLevelValue()+"&districtName="+problemBeanVO.getProblemLocation()+"");
	            else if(regionScope == 2)
	             	problemBeanVO.setUrl("statePageAction.action?stateId="+problemBeanVO.getProblemImpactLevelValue()+"");
				
	            else if(regionScope == 5)
	            	problemBeanVO.setUrl("mandalPageElectionInfoAction.action?MANDAL_ID="+problemBeanVO.getProblemImpactLevelValue()+"&MANDAL_NAME="+problemBeanVO.getProblemLocation()+"");
				problemBeanVO.setUserProblems(new Long(problemsCount.size()));	
				problemDetails.add(problemBeanVO);
				
			}
			
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return problemDetails;
	}
	public List<ProblemBeanVO> getProblemCompleteInfoByLocation(Long problemId) {
		
		log.info("Entered in to getProblemCompleteInfo");
		
		ProblemBeanVO result = null;
		List<ProblemBeanVO> resultList = new ArrayList<ProblemBeanVO>(0);
		State state = null;
		District district = null;
		Constituency constituency = null;
		Tehsil tehsil = null;
		Hamlet hamlet = null;
		LocalElectionBody localBody = null;
		Constituency ward = null;
		Booth booth = null;
		
		  
	try
	{
		List<UserProblem> userProblem = userProblemDAO.getAllProblemDetails(problemId);
		for(UserProblem problemDetails : userProblem)
		{
			result = new ProblemBeanVO();
				
			result.setProblemId(problemDetails.getProblem().getProblemId());
			result.setProblem(problemDetails.getProblem().getTitle().toString());
			result.setDescription(problemDetails.getProblem().getDescription().toString());
			result.setExistingFrom(problemDetails.getProblem().getExistingFrom().toString());
			result.setIdentifiedOn(problemDetails.getProblem().getIdentifiedOn().toString());
			result.setProblemImpactLevelId(problemDetails.getProblem().getRegionScopes().getRegionScopesId());
			result.setName(problemDetails.getUser().getFirstName().toString());
			result.setLastName(problemDetails.getUser().getLastName().toString());
			result.setImpactLevel(problemDetails.getProblem().getRegionScopes().getScope().toString());
			result.setProblemImpactLevelValue(problemDetails.getProblem().getImpactLevelValue());
			result.setReferenceNo(problemDetails.getProblem().getReferenceNo().toString());
			if(problemDetails.getProblem().getProblemStatus() != null)
			result.setStatus(problemDetails.getProblem().getProblemStatus().getStatus().toString());
			result.setVisibilityType(problemDetails.getVisibility().getType());
		}
	
		resultList.add(result);
		
		return resultList;
		
	}
	
		
	//set problem Location
	
	
	catch(Exception e)
	{
		log.debug("Exception Occured in getProblemCompleteInfo() method of ProblemMAnagement service");
		return resultList;
	}
	
		}
	
	
	@SuppressWarnings("unchecked")
	public ProblemBeanVO getProblemCompleteInfo(Long problemId) {
		
			log.info("Entered in to getProblemCompleteInfo");
			
			ProblemBeanVO result = null;
			
			State state = null;
			District district = null;
			Constituency constituency = null;
			Tehsil tehsil = null;
			Hamlet hamlet = null;
			LocalElectionBody localBody = null;
			Constituency ward = null;
			Booth booth = null;
			
			  
		try
		{
			List<UserProblem> userProblem = userProblemDAO.getAllProblemDetails(problemId);
			for(UserProblem problemDetails : userProblem)
			{
				result = new ProblemBeanVO();
				result.setProblemHistoryId(problemDetails.getUserProblemId());		
				result.setProblemId(problemDetails.getProblem().getProblemId());
				result.setProblem(problemDetails.getProblem().getTitle().toString());
				result.setDescription(problemDetails.getProblem().getDescription().toString());
				result.setExistingFrom(sdf1.format(problemDetails.getProblem().getExistingFrom()));
				result.setIdentifiedOn(sdf1.format(problemDetails.getProblem().getIdentifiedOn()));
				//result.setPostedPersonName(problemDetails.getUser().getFirstName().toString());
				result.setPostedDate(problemDetails.getProblem().getIdentifiedOn().toString());
				result.setPostDate(problemDetails.getProblem().getIdentifiedOn());
				result.setProblemImpactLevelId(problemDetails.getProblem().getRegionScopes().getRegionScopesId());
				result.setName(problemDetails.getUser().getFirstName().toString());
				result.setLastName(problemDetails.getUser().getLastName().toString());
				result.setImpactLevel(problemDetails.getProblem().getRegionScopes().getScope().toString());
				result.setProblemImpactLevelValue(problemDetails.getProblem().getImpactLevelValue());
				result.setReferenceNo(problemDetails.getProblem().getReferenceNo().toString());
				if(problemDetails.getProblem().getProblemStatus() != null)
				result.setStatus(problemDetails.getProblem().getProblemStatus().getStatus().toString());
				if(problemDetails.getProblem() != null && problemDetails.getProblem().getIsApproved() !=null)
				result.setIsApproved(problemDetails.getProblem().getIsApproved());
				result.setTotalResultsCount(getProblemsCount().toString());
				result.setAverageRating(getAverageRatingOfAProblem(problemId));
				if(problemDetails.getProblem().getInformationSource() != null)
				result.setProblemSourceScope(problemDetails.getProblem().getInformationSource().getInformationSource().toString());
				result.setUserImageURL(userDAO.getUserProfileImageNameByUserId(problemDetails.getUser().getUserId()));
				
				switch (result.getProblemImpactLevelId().intValue()) {

				case 2: {
					state = stateDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(state.getStateName());
					break;
				}
				case 3: {
					district = districtDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(district.getDistrictName());
					result.setState(district.getState().getStateName());
					break;
				}
				case 4: {
					constituency = constituencyDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(constituency.getName());
					if (IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency
							.getElectionScope().getElectionType()
							.getElectionType())) {
						result.setState(constituency.getState().getStateName());
					} else {
						result.setState(constituency.getState().getStateName());
						result.setDistrict(constituency.getDistrict()
								.getDistrictName()
								+ "(Dt.)");
					}
					break;
				}
				case 5: {
					tehsil = tehsilDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(tehsil.getTehsilName());
					result = setConstDistStateTOResult(tehsil.getTehsilId(),
							result);
					result.setTehsil(tehsil.getTehsilName());
					break;
				}
				case 6: {
					hamlet = hamletDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(hamlet.getHamletName());
					result = setConstDistStateTOResult(hamlet.getTownship()
							.getTehsil().getTehsilId(), result);
					result.setTehsil(hamlet.getTownship().getTehsil()
							.getTehsilName());
					break;
				}
				case 7: {
					localBody = localElectionBodyDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(localBody.getName());
					result.setDistrict(localBody.getDistrict()
							.getDistrictName());
					result.setState(localBody.getDistrict().getState()
							.getStateName());
					break;
				}
				case 8: {
					ward = constituencyDAO.get(new Long(result.getProblemImpactLevelValue()));
					result.setProblemLocation(ward.getName());
					result.setLocalBody(ward.getLocalElectionBody().getName());
					result.setDistrict(ward.getLocalElectionBody()
							.getDistrict().getDistrictName());
					result.setState(ward.getLocalElectionBody().getDistrict()
							.getState().getStateName());
					break;
				}
				case 9: {
					booth = boothDAO.get(new Long(result.getProblemImpactLevelValue()));
					if (booth.getTehsil() != null) {
						result.setTehsil(booth.getTehsil().getTehsilName());
						result = setConstDistStateTOResult(booth.getTehsil()
								.getTehsilId(), result);
					} else if (booth.getLocalBody() != null) {
						if (booth.getBoothLocalBodyWard() != null)
							result.setWard(booth.getBoothLocalBodyWard()
									.getLocalBodyWard().getName());
						result.setLocalBody(booth.getLocalBody().getName());
						result.setDistrict(booth.getLocalBody().getDistrict()
								.getDistrictName());
						result.setState(booth.getLocalBody().getDistrict()
								.getState().getStateName());

					}
					result.setProblemLocation(booth.getPartName()
							+ booth.getLocation());
					break;
				}
				default:
					System.out.println("Invalid Scope.");
					break;
				}
				
				
				result.setProblemLocationId(new Long(result.getProblemImpactLevelId().intValue()));
				
				
				
				}
			
			List<Long> approvecommentsCount = problemCommentsDAO.getAllApprovedComments(problemId);
			result.setCommentCount(approvecommentsCount.get(0));
			List<Long> problemLikesCount = problemLikesDAO.getAllLikes(problemId);
			result.setLikesCount(problemLikesCount.get(0));
			List<Long> problemDisLikesCount = problemLikesDAO.getAllDisLikes(problemId);
			result.setDislikesCount(problemDisLikesCount.get(0));
			
			
			return result;
		}
		
			
		//set problem Location
		
		
		catch(Exception e)
		{
			log.debug("Exception Occured in getProblemCompleteInfo() method of ProblemMAnagement service");
			return result;
		}
		
			}

	public Long getProblemsCount()
	{
		try
		{
		//List<Long> problemHistoryCount = problemHistoryDAO.getAllValidProblemIdsCount();
			List<Long> problemsCount = userProblemDAO.getAllValidProblemIdsCount();
		 if(problemsCount.get(0).longValue() < 31l)
			 return problemsCount.get(0);
		 else
			 return 30L;
		}
		catch(Exception e)
		{
			log.error("Exception Rised in getProblemsCount method ..", e);
		}
		return 30L;
	}
	
	public ProblemBeanVO getProblemDetailsByProblemReferenceId(String problemReferenceId , Long userId)
	{
		ProblemBeanVO problemBeanVO = null;
		try
		{
			//List<Object[]> list = problemHistoryDAO.getProblemHistoryIdByReferenceId(problemReferenceId);
			List<Object[]> list = userProblemDAO.getProblemDetailsByProblemReferenceNo(problemReferenceId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					if(params[3] != null && params[3].equals("true"))
					{
						problemBeanVO = new ProblemBeanVO();
						problemBeanVO.setProblemHistoryId((Long)params[0]);
						problemBeanVO.setDescription(params[1].toString());
						return problemBeanVO;
					}
					else if(params[2] != null && (Long)params[2] != 0 && userId != null && userId != 0 && 
							params[2].equals(userId))
					{
						problemBeanVO = new ProblemBeanVO();
						problemBeanVO.setProblemHistoryId((Long)params[0]);
						problemBeanVO.setDescription(params[1].toString());
						return problemBeanVO;
					}
					else
						return null;
				}
			}
			return problemBeanVO;
			
		}catch (Exception e) {
			log.error("Exception Occured in getProblemDetailsByProblemReferenceId() Method , Exception - "+e);
			return null;
		}
		
	}
	
	public List<SelectOptionVO> getStates()
	{
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>(); 
		try{
			//List<Object[]> states = problemHistoryDAO.getStates();
			List<Object[]> states = userProblemDAO.getStates();
			if(states != null && states.size() > 0)
			{
				for(Object[] result : states)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)result[0]);
					selectOptionVO.setName(result[1].toString());
					selectOptionList.add(selectOptionVO);
				}
			}
			
			return selectOptionList;
		}catch (Exception e) {
			log.error("Exception Occured in getStates() Method , Exception - "+e);
			return selectOptionList;
		}
	}
	
	public List<SelectOptionVO> getDistrictsByAStateID(Long stateId)
	{
		List<SelectOptionVO> districtList = new ArrayList<SelectOptionVO>();
		try{
			
			List<Object[]> list = districtDAO.getDistrictIdAndNameByState(stateId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					districtList.add(selectOptionVO);
					
				}
			}
			return districtList;
		}catch (Exception e) {
			log.error("Exception Occured in getDistrictsByStateID() Method , Exception - "+e);
			return districtList;
		}
		
	}
		
	public List<SelectOptionVO> getProblemPostedUserDetails()
	{
		List<SelectOptionVO> userList = new ArrayList<SelectOptionVO>();
		try{
			//List<Object[]> list = problemHistoryDAO.getProblemPostedUserDetails();
			List<Object[]> list = userProblemDAO.getProblemPostedUserDetails();
			if(list != null && list.size() > 0)
  			 {
				for(Object[] params : list)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString()+" "+params[2].toString());
					userList.add(selectOptionVO);
				}
					
			 }
			return userList;
		}catch (Exception e) {
			log.error("Exception Occured in getProblemPostedUserDetails() Method , Exception - "+e);
			return userList;
		}
	}
	
	public List<SelectOptionVO> getProblemTypes()
	{
		List<SelectOptionVO> problemTypes = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> list = problemTypeDAO.getProblemTypes();
			if(list != null && list.size() >0)
			{
				for(Object[] params : list)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					problemTypes.add(selectOptionVO);
				}
			}
			
			return problemTypes;
	}catch (Exception e) {
		log.error("Exception Occured in getProblemTypes() Method , Exception is - "+e);
		return null;
	}
	}
	
	public List<ProblemBeanVO> getProblemDetailsForFreeUser(ProblemSearchVO problemSearchVO,int startIndex,int maxIndex)
	{
		if(problemSearchVO == null)
		{
			log.error("problemSearchVO is null in getProblemDetailsForFreeUser() method");
			return null;
		}
		List<ProblemBeanVO> problemBeanVOList =  new ArrayList<ProblemBeanVO>(0);
		try{
		
		Long scopeId = problemSearchVO.getScopeId();
		Long locationValue = problemSearchVO.getLocationValue();
		
		if(scopeId != null && locationValue != null && 
				(scopeId.equals(5L) || scopeId.equals(6L) || scopeId.equals(7L) || scopeId.equals(8L)))
		{
			String locStr = locationValue.toString();
			locStr = locStr.substring(1);
			locationValue = Long.parseLong(locStr);
			
			if(scopeId.equals(7L))
			{
				List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
				locationValue = (Long)localElectionBodies.get(0);
			}
			problemSearchVO.setLocationValue(locationValue);
		}
		
		//List<Object[]> list = problemHistoryDAO.getFreeUserProblemsInSearch(problemSearchVO,startIndex,maxIndex,false);
		//List<Object[]> countList = problemHistoryDAO.getFreeUserProblemsInSearch(problemSearchVO,startIndex,maxIndex,true);
		List<Object[]> list = userProblemDAO.getFreeUserProblemsInSearch(problemSearchVO,startIndex,maxIndex,false);
		List<Object[]> countList = userProblemDAO.getFreeUserProblemsInSearch(problemSearchVO,startIndex,maxIndex,true);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				ProblemBeanVO problemBeanVO = new ProblemBeanVO();
				
				problemBeanVO.setProblemHistoryId((Long)params[0]);
				problemBeanVO.setProblem(params[1].toString());
				problemBeanVO.setDescription(params[2].toString());
				problemBeanVO.setExistingFrom(params[3].toString());
				problemBeanVO.setReportedDate(params[4].toString());
				problemBeanVO.setProblemLocation(problemManagementReportService.getProblemLocation((Long)params[5], (Long)params[6]));
				problemBeanVO.setName(params[7].toString()+" "+params[8].toString());
				problemBeanVO.setUserID((Long)params[9]);
				problemBeanVO.setProblemCount(countList.size());
				problemBeanVOList.add(problemBeanVO);
				
			}
			
			
		}
		
		}catch (Exception e) {
			log.error("Exception Occured in getProblemDetailsForFreeUser() Method , Exception - "+e);
			return problemBeanVOList;
		}
		
		return problemBeanVOList;			
	}
	
	public ResultStatus updateClassificationOfProblem(Long problemId, Long userId, String classification, String status){
		ResultStatus resultStatus = null;

		if (log.isDebugEnabled()) {
			log.debug("Entered Into updateProblemClassification Method.....");
			log.debug("Problem Id:: " + problemId);
			log.debug("User Id" + userId);
		}
		try {
			Long userProblemId=getUserProblemIdByUserIdAndProblemId(userId, problemId);
			Long problemClassificationId=getUserProblemClassificationId(classification);
			if(problemClassificationId!=null && userProblemId!=null)
				resultStatus=saveOrUpdateClassifiedProblems(userProblemId, problemClassificationId);						
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultStatus;		
	}
	public Long getUserProblemIdByUserIdAndProblemId(Long userId, Long problemId){
		List<Long> usrProbLst=userProblemDAO.getUserProblemIdByUserIdAndProblemId(userId, problemId);
		Long userProblemId=null;
		if(usrProbLst!=null && usrProbLst.size()>0){
			userProblemId=usrProbLst.get(0);
		}
		return userProblemId;
	}
	public Long getUserProblemClassificationId(String classification){
		List<Long> probClssfctnLst=problemClassificationDAO.getProblemClassificationId(classification);
		Long problemClassificationId=null;
		if(probClssfctnLst!=null && probClssfctnLst.size()>0){
			problemClassificationId=probClssfctnLst.get(0);
		}
		return problemClassificationId;
	}
	public ResultStatus saveOrUpdateClassifiedProblems(final Long userProblemId, final Long problemClassificationId){
		ResultStatus resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
				try {
					String problemActivityStr="";
					ClassifiedProblems classifiedProblems=null;
					UserProblem userProblem=userProblemDAO.get(userProblemId);
					List<Long> classifiedProblemsIdList=classifiedProblemsDAO.checkIfProblemAlreadyClassified(userProblemId);
					Long classifiedProblemId=null;
					if(classifiedProblemsIdList!=null && classifiedProblemsIdList.size()>0){
						classifiedProblemId=classifiedProblemsIdList.get(0);
						ClassifiedProblems classifiedProblemObj=classifiedProblemsDAO.get(classifiedProblemId);
						ProblemClassification problemClassificationObj=problemClassificationDAO.get(problemClassificationId);
						if(classifiedProblemObj!=null && problemClassificationObj!=null){							
							classifiedProblemObj.setProblemClassification(problemClassificationObj);
							classifiedProblemObj.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							classifiedProblems = classifiedProblemsDAO.save(classifiedProblemObj);
							problemActivityStr=IConstants.PROBLEM_TYPE_UPDATE;							
						}
					}
					else{
						classifiedProblems=new ClassifiedProblems();
						ProblemClassification problemClassification=problemClassificationDAO.get(problemClassificationId);
						classifiedProblems.setUserProblem(userProblem);
						classifiedProblems.setProblemClassification(problemClassification);
						classifiedProblems.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						classifiedProblems.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						classifiedProblems = classifiedProblemsDAO.save(classifiedProblems);
						if(classifiedProblemsIdList != null && classifiedProblemsIdList.size() > 0 )
							problemActivityStr=IConstants.PROBLEM_TYPE_UPDATE;
						else
						problemActivityStr=IConstants.PROBLEM_TYPE_ADD;
					//}
					updateProblemProgress(problemActivityStr, userProblem,classifiedProblems);
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					rs.setExceptionMsg("Problem classification successful");
				}
				catch(Exception e){
					rs.setResultCode(ResultCodeMapper.FAILURE);
					rs.setExceptionEncountered(e);
					rs.setExceptionMsg(e.getMessage());
				}
				return rs;
			}
		});
		return resultStatus;
	}
	public void updateProblemProgress(String problemActvtyStr, UserProblem userProblem,ClassifiedProblems classifiedProblems){
		log.debug("Entered into updateProblemProgress method of ProblemManagementService");
		
		try{
			List<ProblemActivity> problemActivityLst=problemActivityDAO.getProblemActivityByName(problemActvtyStr);
			ProblemActivity problemActivity=null;
			if(problemActivityLst!=null && problemActivityLst.size()>0){
				problemActivity=problemActivityLst.get(0);
			}
			Long privateVisibility=2L;
			Visibility visibility=visibilityDAO.get(privateVisibility);
			ProblemProgress problemProgress=new ProblemProgress();
			problemProgress.setProblemActivity(problemActivity);
			problemProgress.setUserProblem(userProblem);
			problemProgress.setComment(null);
			problemProgress.setVisibility(userProblem.getVisibility());
			Date currentDateTime=dateUtilService.getCurrentDateAndTime();
			problemProgress.setInsertedTime(currentDateTime);
			problemProgress.setUpdatedTime(currentDateTime);
			problemProgress.setIsDelete(IConstants.FALSE);
			problemProgress.setClassifiedProblems(classifiedProblems);
			List<Object[]> classifiedProblems = classifiedProblemsDAO.getClassifiedProblemId(userProblem.getUserProblemId());
			if(classifiedProblems != null)
				for(Object[] params : classifiedProblems)
				{
					problemProgress.setClassifiedProblems(params[0]);
				}
			
			problemProgressDAO.save(problemProgress);
		}
		catch(Exception e){
			log.error("Exception raised in updateProblemProgress method of ProblemManagementService");
		}
	}
	public ResultStatus updateStatusOfProblem(Long problemId, Long userId, String probStatus){
		ResultStatus resultStatus=new ResultStatus();
		if (log.isDebugEnabled()) {
			log.debug("Entered Into updateStatusOfProblem Method.....");
			log.debug("Problem Id: " + problemId);
			log.debug("Status: " + probStatus);
		}
		try {
			Problem problem=problemDAO.get(problemId);
			ProblemStatus problemStatus=null;
			String status = "";
			if (probStatus.equalsIgnoreCase(IConstants.PROGRESS))
				status = IConstants.PROGRESS;
			else if (probStatus.equalsIgnoreCase(IConstants.PENDING))
				status = IConstants.PENDING;
			else if (probStatus.equalsIgnoreCase(IConstants.FIXED))
				status = IConstants.FIXED;
			else if (probStatus.equalsIgnoreCase(IConstants.NEW))
				status = IConstants.NEW;

			List<ProblemStatus> problemStatusLst = problemStatusDAO
					.getByStatus(status);
			if(problemStatusLst!=null && problemStatusLst.size()>0){
				problemStatus=problemStatusLst.get(0);
			}
			if(problem!=null){
				if(problem.getProblemStatus()!=null){
					if(problem.getProblemStatus().getStatus().equalsIgnoreCase(problemStatus.getStatus())){
						resultStatus.setExceptionMsg("Please update to some other status ..");
						return resultStatus;
					}
					else{
						problem.setProblemStatus(problemStatus);
						problem.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						problemDAO.save(problem);
						
						Long userProblemId=getUserProblemIdByUserIdAndProblemId(userId, problemId);
						UserProblem userProblem=userProblemDAO.get(userProblemId);
						updateProblemProgress(IConstants.STATUS_CHANGE, userProblem,null);
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						resultStatus.setResultState(problemId);
					}
				}
			}											
		}
		catch (Exception ex) {
			log.error("Exception raised in updateStatusOfProblem method of ProblemManagementService");
			ex.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setExceptionMsg(ex.getMessage());
		}
		return resultStatus;	
	}
	public ResultStatus setProblemLikeOrDislike(Long problemId, Long userId, String userAction){
		ResultStatus resultStatus=new ResultStatus();
		try{
			String isLiked="";
			String msg="";
			if(userAction.equalsIgnoreCase("like")){
				msg="liked";
				isLiked=IConstants.TRUE;
			}
			else if(userAction.equalsIgnoreCase("dislike")){
				msg="disliked";
				isLiked=IConstants.FALSE;
			}
			
			List<ProblemLikes> problemLikesLst=problemLikesDAO.checkIfUserAlreadyLikedOrDisliked(problemId, userId);
			ProblemLikes problemLikes=null;
			if(problemLikesLst!=null && problemLikesLst.size()>0){
				problemLikes=problemLikesLst.get(0);
			}
			if(problemLikes==null){
				ProblemLikes problemLikesObj=new ProblemLikes();
				User user=userDAO.get(userId);
				Problem problem=problemDAO.get(problemId);
				problemLikesObj.setUser(user);
				problemLikesObj.setProblem(problem);
				problemLikesObj.setIsLiked(isLiked);
				problemLikesObj.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				problemLikesDAO.save(problemLikesObj);
			}
			else{
				if(problemLikes.getIsLiked()!=null){
					if(problemLikes.getIsLiked().equalsIgnoreCase(isLiked)){
						resultStatus.setResultState(userId);
						resultStatus.setExceptionMsg("You have already "+msg+" this problem");
						return resultStatus;
					}
					else{
						problemLikesDAO.updateUserLikeOrDislike(userId, problemId, isLiked);
					}
				}				
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch(Exception e){
			log.error("Exception raised in setProblemLikeOrDislike method of ProblemManagementService");
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}		
		return resultStatus;
	}	
	public ResultStatus deleteProblemDetails(Long problemId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			Problem problem = problemDAO.get(problemId);
			if(problem == null)
			{
				log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(problem != null && problem.getProblemId() > 0)
			{
				problem.setIsDelete(IConstants.TRUE);
				problemDAO.save(problem);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Occured in deleteProblemDetails() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}

	
	public ResultStatus deleteProblemDetails(Long problemId)
	{
		ResultStatus resultStatus = new ResultStatus();
		Date currentDate = dateUtilService.getCurrentDateAndTime();
		//List<Long> problemId = new ArrayList<Long>();
		try{
			if(problemIds == null)
			{
				log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(problemIds != null)
			{
				for(Object list : problemIds)
				{
				problemId.add((Long)list);
				Integer result = problemDAO.deleteProblem(problemId, currentDate);
				if(result != 0)
					{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					}
				}
				
			}
			if(problemId == 0)
			{
				log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(problemId !=0 && problemId > 0)
			{
				Integer result = problemDAO.deleteProblemDetails(problemId, currentDate);
				
				List<Long> countList = newsProblemDAO.getCountByProblemId(problemId);
				
				if(countList != null && countList.size() >0){
					
					List<NewsProblem> newsProblemList = newsProblemDAO.getNewsProblemByProblemId(problemId);
					
					if(newsProblemList != null && newsProblemList.size() >0){
						
						NewsProblem newsProblem = newsProblemList.get(0);
						newsProblem.setIsDelete(IConstants.TRUE);
						newsProblemDAO.save(newsProblem);
						
					}
					
				}
				
				if(result != 0)
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				else if(result == 0)
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception Occured in deleteProblemDetails() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	
	public ResultStatus freeUserProblemAssignedToCustomer(Long userId, String visibility, Long problemId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.info("Entered into freeUserProblemAssignedToCustomer() Method");
			UserProblem userProblem = new UserProblem();
				userProblem.setUser(userDAO.get(userId));
				userProblem.setProblem(problemDAO.get(problemId));
				userProblem.setIsOwner(IConstants.FALSE);
				userProblem.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				userProblem.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				if(visibility != null && visibility.equals(IConstants.PUBLIC))
					userProblem.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PUBLIC).get(0));
				else if(visibility != null && visibility.equals(IConstants.PRIVATE))
					userProblem.setVisibility(visibilityDAO.getVisibilityByVisibilityType(IConstants.PRIVATE).get(0));
				userProblemDAO.save(userProblem);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in freeUserProblemAssignedToCustomer() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public ResultStatus saveRatingOfAProblem(Long userId, Long problemId, String rating)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			String saveReq = getIsUserRatedAProblem(userId,problemId);
			if(saveReq == null || saveReq.equalsIgnoreCase("false")){
			ProblemRating problemRating = new ProblemRating();
			problemRating.setUser(userDAO.get(userId));
			problemRating.setProblem(problemDAO.get(problemId));
			problemRating.setRating(Integer.parseInt(rating));
			problemRating.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			problemRating.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			problemRatingDAO.save(problemRating);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveRatingOfAProblem() Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	public Integer getUserRatingOfAProblem(Long problemId, Long userId)
	{
		try{
			
			List<Integer>  ratingList = problemRatingDAO.getRatingGivenByUser(problemId, userId);
			if(!ratingList.isEmpty()){
				return ratingList.get(0);
			}
			return null;
		}catch (Exception e) {
			log.error("Exception Occured in getUserRatingOfAProblem() Exception - "+e);
			return null;
		}
	}
	public String getIsUserRatedAProblem(Long userId, Long problemId)
	{
		try{
			List<Long>  countList = problemRatingDAO.getIsRatingGivenByUser(problemId,userId);
            String returnVal = "false";
			if(!countList.isEmpty()){
				if(countList.get(0).longValue() > 0l){
					returnVal = "true";
				}
			}
			return returnVal;
		}catch (Exception e) {
			log.error("Exception Occured in getIsUserRatedAProblem() Exception - "+e);
			return null;
		}
	}
	public CompleteProblemDetailsVO getAverageRatingOfAProblem(Long problemId)
	{
		String avgRatingOfAProblem = null;
		CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
		try{
			List<Object[]> result = problemRatingDAO.getAverageRatingOfAProblem(problemId);
			if(!result.isEmpty() && result.size() >0)
			{
				Object[] avgRating = result.get(0);
				DecimalFormat resString = new DecimalFormat("#.##");
				if(avgRating[1] != null){
				  Double twoDecimal =  Double.valueOf(resString.format(avgRating[1]));
				  avgRatingOfAProblem = twoDecimal.toString();
				  completeProblemDetailsVO.setAvgRating(avgRatingOfAProblem);
				}
				if(avgRating[0] != null)
					completeProblemDetailsVO.setTotalpeople(avgRating[0].toString());
			}
			return completeProblemDetailsVO;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAverageRatingOfAProblem() Exception - "+e);
			return completeProblemDetailsVO;
		}
		
	}
	
	public List<CompleteProblemDetailsVO> getRatingWiseCountOfAProblem(Long problemId)
	{
		try{
			List<CompleteProblemDetailsVO> completeProblemDetailsVOList = new ArrayList<CompleteProblemDetailsVO>();
			Map<Integer,CompleteProblemDetailsVO> ratingMap = new HashMap<Integer,CompleteProblemDetailsVO>();
			List<Object[]> result = problemRatingDAO.getRatingWiseCountOfAProblem(problemId);
			int totalCount = 0;
			if(result != null && result.size() >0)
			{
				
				for(Object[] params : result)
				{
					CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
					completeProblemDetailsVO.setRatingGiven(((Integer)params[0]));
					completeProblemDetailsVO.setTotalpeople(params[1].toString());
					totalCount = totalCount+ new Integer(params[1].toString());
					ratingMap.put(((Integer)params[0]), completeProblemDetailsVO);
				}
			}
				for(int i = 1;i < 6;i++){
					if(ratingMap.get(new Integer(i)) == null){
						CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
						completeProblemDetailsVO.setRatingGiven(i);
						completeProblemDetailsVO.setTotalpeople("0");
						completeProblemDetailsVO.setRating("0");
						ratingMap.put(new Integer(i), completeProblemDetailsVO);
					}else{
						CompleteProblemDetailsVO completeProblemDetailsVO = ratingMap.get(new Integer(i));
						Float rating = 0.0f;
						if(totalCount != 0)
						  rating = (new Float(completeProblemDetailsVO.getTotalpeople())/totalCount)*100;
						  completeProblemDetailsVO.setRating(rating.toString());
					}
				}
				completeProblemDetailsVOList = new ArrayList<CompleteProblemDetailsVO>(ratingMap.values());
				Collections.sort(completeProblemDetailsVOList,ProblemManagementService.completeProblemDetailsVOSort);
			
			return completeProblemDetailsVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRatingWiseCountOfAProblem(), Exception - "+e);
			return null;
		}
	}
	public static Comparator<CompleteProblemDetailsVO> completeProblemDetailsVOSort = new Comparator<CompleteProblemDetailsVO>()
			{
						  
					  public int compare(CompleteProblemDetailsVO completeProblemDetailsVO1, CompleteProblemDetailsVO completeProblemDetailsVO2)
						{
						   return (completeProblemDetailsVO1.getRatingGiven().intValue()) - (completeProblemDetailsVO2.getRatingGiven().intValue());
						}
			};
	public ResultStatus changeActivityState(Long prblmPrgrssId,String task)
	{
		ResultStatus resultStatus = new ResultStatus();
		Long visibility;
		int result;
		try{
			if(prblmPrgrssId == 0)
			{
				log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(prblmPrgrssId !=0 && prblmPrgrssId > 0)
			{
				if(task.equalsIgnoreCase("makeActivityPublic")){
					visibility=1l;
					result = problemProgressDAO.updateActivityVisibility(prblmPrgrssId,visibility);
				}
				else{
					visibility=2l;
					result = problemProgressDAO.updateActivityVisibility(prblmPrgrssId,visibility);
				}
				if(result != 0)
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			return resultStatus;
		}
		catch(Exception e){
			log.info("Exception Occured in changeActivityState() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}
	public ResultStatus changeProblemToPublic(Long problemId,String task)
	{
		ResultStatus resultStatus = new ResultStatus();
		Long visibility;
		int result;
		try{
			if(problemId == 0)
			{
				log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			else if(problemId !=0 && problemId > 0)
			{
				if(task.equalsIgnoreCase("makeProblemPublic")){
				visibility=1l;
				result = userProblemDAO.makeProblemPublic(problemId,visibility);
				}
			}
			return resultStatus;
			}
			catch(Exception e){
				log.info("Exception Occured in changeActivityState() Method, Exception is - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}

			
	}
	
	public ResultStatus sendEmailToConnectedUsersAfterProblemApproval(ProblemBeanVO problemBeanVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		if(problemBeanVO == null)
		{
			log.error("Error Occured in deleteProblemDetails() Method in ProblemManagementService");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		else if(problemBeanVO != null)
		{
			
		}
		return resultStatus;

	}
	
	public List<CompleteProblemDetailsVO> getProblemDetailsForUpdate(Long problemId)
	{
		log.debug("Entered into getProblemDetailsForUpdate() of ProblemManagementService()");
		List<CompleteProblemDetailsVO> completeProblemDetails = new ArrayList<CompleteProblemDetailsVO>();
		try
		{
		List<UserProblem> problemDetails = userProblemDAO.getProblemDeatilsByProblemId(problemId);
		if(problemDetails !=null)
		{
		for(UserProblem params : problemDetails)
		{
			CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
			completeProblemDetailsVO.setProblemTitle(params.getProblem().getTitle());
			completeProblemDetailsVO.setProblemDesc(params.getProblem().getDescription());
			completeProblemDetailsVO.setExistingFrom(params.getProblem().getExistingFrom().toString().replace("-", "/"));
			
			if(params.getProblem().getProblemType() != null && params.getProblem().getProblemType().getProblemTypeId() != null)
			completeProblemDetailsVO.setProblemTypeId((Long)params.getProblem().getProblemType().getProblemTypeId());
			if(params.getProblem().getProblemType() != null && params.getProblem().getProblemType().getProblemType() != null)
			completeProblemDetailsVO.setProblemType(params.getProblem().getProblemType().getProblemType());
			completeProblemDetails.add(completeProblemDetailsVO);
		}
		}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception occured in getProblemDetailsForUpdate() of problemManagement Service" +e);
		}
		return completeProblemDetails;
		
		
	}
	
	public ResultStatus updateProblemDetails(Long problemId,List<CompleteProblemDetailsVO> completeProblemDetailsVO)
	{
		log.debug("Entered into updateProblemDetails of ProblemManagementService()");
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			for(CompleteProblemDetailsVO params:completeProblemDetailsVO)
			{
			String str_date=params.getExistingFrom();
			DateFormat formatter ; 
			Date date ; 
			formatter = new SimpleDateFormat("yyyy/mm/dd");
			date = (Date)formatter.parse(str_date);  
			Problem problem = problemDAO.get(problemId);
			problem.setTitle(params.getProblemTitle());
			problem.setDescription(params.getProblemDesc());
			problem.setExistingFrom(date);
			if(params.getProblemTypeId() == 0)
			{
				problem.setProblemType(null);
			}
			if(params.getProblemTypeId() > 0)
			problem.setProblemType(problemTypeDAO.get(new Long(params.getProblemTypeId())));
			problemDAO.save(problem);

			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
	     	}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 log.error("Exception Occured in updateProfileDescription() method - "+e);
			return resultStatus;
		    }
	}
	
	public ResultStatus saveAbusedCommentsToProblem(Long commentId, Long userId)
	{
		ResultStatus resultStatus = new ResultStatus();
		AbusedComments abusedComments = null;
		try{
			if(commentId == 0 || userId == 0)
			{
				log.error("Error Occured in saveAbusedCommentsToProblem()");
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			if(commentId != 0 && userId != 0)
			{
				abusedComments = new AbusedComments();
				abusedComments.setCommentId(commentId);
				abusedComments.setUserId(userId);
				abusedComments.setStatus(IConstants.FALSE);
				abusedComments.setIsDelete(IConstants.FALSE);
				abusedComments.setTime(dateUtilService.getCurrentDateAndTime());
				abusedCommentsDAO.save(abusedComments);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveAbusedCommentsToProblem(), Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	
	public ProblemBeanVO getProblemCompleteInfoForAUserBasedOnProblemId(
			Long problemId) {
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		String eDate = null;
		String iDate = null;
		try {
			List<UserProblem> userProblem = userProblemDAO
					.getProblemDeatilsByProblemId(problemId);
			if (userProblem != null)
				for (UserProblem params : userProblem) {
					if (params.getProblem() != null) {
						if (params.getProblem().getTitle() != null)
							problemBeanVO.setProblem(params.getProblem()
									.getTitle());
						if (params.getProblem().getDescription() != null)
							problemBeanVO.setDescription(params.getProblem()
									.getDescription());
						if (params.getProblem().getRegionScopes()
								.getRegionScopesId() > 0)
							problemBeanVO.setProblemScopeId(params.getProblem()
									.getRegionScopes().getRegionScopesId());
						problemBeanVO.setProblemScope(params.getProblem()
								.getRegionScopes().getScope());
						if (params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getState() != null)
						{
							problemBeanVO.setState(params.getProblem()
									.getProblemCompleteLocation().getState()
									.getStateName());
							problemBeanVO.setStateId(params.getProblem().getProblemCompleteLocation().getState().getStateId());
						
						}	
						if (params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getDistrict() != null)
						{
							problemBeanVO.setDistrict(params.getProblem()
									.getProblemCompleteLocation().getDistrict()
									.getDistrictName());
							problemBeanVO.setDistrictId(params.getProblem().getProblemCompleteLocation().getDistrict().getDistrictId());
						}
						if (params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getConstituency() != null)
						{
							problemBeanVO.setConstituency(params.getProblem()
									.getProblemCompleteLocation()
									.getConstituency().getName());
							problemBeanVO.setPConstituencyId(params.getProblem().getProblemCompleteLocation().getConstituency().getConstituencyId());
						}
						
						if (params.getProblem() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getTehsil() != null)
						{
							problemBeanVO.setMandal(params.getProblem()
									.getProblemCompleteLocation().getTehsil()
									.getTehsilName());
							String tehsilValue=params.getProblem().getProblemCompleteLocation().getTehsil().getTehsilId().toString();
							String tehsilNumber="2";
							String tehsilId=tehsilNumber+tehsilValue;
							problemBeanVO.setTehsilId(new Long(tehsilId));
						}	
						else if(params.getProblem() != null
								&& params.getProblem()
								.getProblemCompleteLocation().getLocalElectionBody() != null){
							problemBeanVO.setMandal(params.getProblem()
									.getProblemCompleteLocation().getLocalElectionBody().getName());
							
							Long value=assemblyLocalElectionBodyDAO.getTehsilValues(params.getProblem()
									.getProblemCompleteLocation().getLocalElectionBody().getLocalElectionBodyId(),params.getProblem().getProblemCompleteLocation().getConstituency().getConstituencyId());
							String tehsilNumber="1";
							String tehsilId=tehsilNumber+value;
							problemBeanVO.setTehsilId(new Long(tehsilId));
						}
						if(params.getProblem().getRegionScopes().getRegionScopesId()==6)
						{
						if (params.getProblem() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getTehsil() != null)
						{
							problemBeanVO.setMandal(params.getProblem()
									.getProblemCompleteLocation().getTehsil()
									.getTehsilName());
							String tehsilValue=params.getProblem().getProblemCompleteLocation().getTehsil().getTehsilId().toString();
							String tehsilNumber="2";
							String tehsilId=tehsilNumber+tehsilValue;
						problemBeanVO.setTehsilId(new Long(tehsilId));
						}						
						if (params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getHamlet() != null)
						{
							problemBeanVO.setVillage(params.getProblem()
									.getProblemCompleteLocation().getHamlet()
									.getHamletName());
							String hamletValue=params.getProblem().getProblemCompleteLocation().getHamlet().getHamletId().toString();
							String hamletState="2";
							String hamletId=hamletState + hamletValue;
							problemBeanVO.setHamletId(new Long(hamletId));
						}
						}
						
						if(params.getProblem().getRegionScopes().getRegionScopesId()==8)
						{
						if (params.getProblem() != null
									&& params.getProblem()
											.getProblemCompleteLocation()
											.getTehsil() != null)
							{
								problemBeanVO.setMandal(params.getProblem()
										.getProblemCompleteLocation().getLocalElectionBody().getName());
								
								problemBeanVO.setLocationElectionId(params.getProblem().getProblemCompleteLocation().getLocalElectionBody().getLocalElectionBodyId());
								Long value=assemblyLocalElectionBodyDAO.getTehsilValues(params.getProblem()
										.getProblemCompleteLocation().getLocalElectionBody().getLocalElectionBodyId(),params.getProblem().getProblemCompleteLocation().getConstituency().getConstituencyId());
								//String tehsilNumber="1";
								//String tehsilId=tehsilNumber+value;
							problemBeanVO.setTehsilId(value);
							}
						if(params.getProblem().getProblemCompleteLocation() !=null 
								&& params.getProblem().getProblemCompleteLocation().getWard()!= null)
						{
							String wardValue=params.getProblem().getProblemCompleteLocation().getWard().getConstituencyId().toString();
							String wardState="1";
							String wardId=wardState + wardValue;
							problemBeanVO.setVillage(params.getProblem().getProblemCompleteLocation().getWard().getName());
							problemBeanVO.setHamletId(new Long(wardId));
														
						}
						}
						if(params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
								.getProblemCompleteLocation() != null
								&& params.getProblem()
								.getProblemCompleteLocation().getBooth() != null)
							problemBeanVO.setBoothId(params.getProblem()
									.getProblemCompleteLocation().getBooth().getBoothId());
							
						if (params.getProblem().getProblemCompleteLocation() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getLocalElectionBody() != null
								&& params.getProblem()
										.getProblemCompleteLocation()
										.getLocalElectionBody().getName() != null)
						//	String mandalName=params.getProblem()
							//.getProblemCompleteLocation()
						//	.getLocalElectionBody().getName();
						
							problemBeanVO.setMandal(params.getProblem()
									.getProblemCompleteLocation()
									.getLocalElectionBody().getName());
						
						if (params.getProblem().getExistingFrom() != null)

							 
						
					//problemBeanVO.setExistingFrom(params.getProblem().getExistingFrom().toString().replace("-","/"));
						
						eDate =params.getProblem().getExistingFrom().toString().replace("-","/");
						
						
						
						Date dateStr = formatter.parse(eDate);
						String formattedDate = formatter.format(dateStr); 
						
						Date date1 = formatter.parse(formattedDate);
						formatter = new SimpleDateFormat("dd/MM/yyyy");
						formattedDate = formatter.format(date1); 
						problemBeanVO.setExistingFrom(formattedDate);
							if (params.getProblem().getIdentifiedOn() != null)
								problemBeanVO.setReportedDate(params
										.getProblem().getIdentifiedOn()
										.toString().replace("-", "/"));
							iDate = params
									.getProblem().getIdentifiedOn()
									.toString().replace("-", "/");
							Date idateStr= formatter.parse(iDate);
							
							String iformattedDate = formatter.format(idateStr); 
							
							Date date2 = formatter.parse(iformattedDate);
							formatter = new SimpleDateFormat("MM/dd/yyyy");
							iformattedDate = formatter.format(date2); 
							problemBeanVO.setReportedDate(iformattedDate);	
						if (params.getProblem().getProblemType() != null
								&& params.getProblem().getProblemType()
										.getProblemTypeId() != null)
							problemBeanVO.setProblemTypeId(params.getProblem()
									.getProblemType().getProblemTypeId());
						if (params.getProblem().getProblemType() != null
								&& params.getProblem().getProblemType()
										.getProblemType() != null)
							problemBeanVO.setProblemType(params.getProblem()
									.getProblemType().getProblemType());
						if (params.getProblem().getInformationSource() != null
								&& params.getProblem().getInformationSource()
										.getInformationSourceId() != null)
							problemBeanVO.setProblemSourceScopeId(params
									.getProblem().getInformationSource()
									.getInformationSourceId());
						if (params.getProblem().getInformationSource() != null
								&& params.getProblem().getInformationSource()
										.getInformationSource() != null)
							
							problemBeanVO.setProbSource(params.getProblem()
									.getInformationSource()
									.getInformationSource());
						if (params.getProblem().getInformationSource() != null)
							problemBeanVO.setProblemSourceScopeId(params.getProblem().getInformationSource().getInformationSourceId());
						
						if (params.getVisibility() != null)
							problemBeanVO.setVisibility(params.getVisibility()
									.getVisibilityId());
						problemBeanVO.setVisibilityType(params.getVisibility()
								.getType());
						if(params.getProblem().getExternalSource() != null)
						{
							if(params.getProblem().getExternalSource().getName() != null && !params.getProblem().getExternalSource().getName().equalsIgnoreCase(""))
							problemBeanVO.setName(params.getProblem().getExternalSource().getName());
							if(params.getProblem().getExternalSource().getMobile() != null && !params.getProblem().getExternalSource().getMobile().equalsIgnoreCase(""))
								problemBeanVO.setMobile(params.getProblem().getExternalSource().getMobile());
							if(params.getProblem().getExternalSource().getAddress() != null && !params.getProblem().getExternalSource().getAddress().equalsIgnoreCase(""))
								problemBeanVO.setAddress(params.getProblem().getExternalSource().getAddress());
							if(params.getProblem().getExternalSource().getEmail() != null && !params.getProblem().getExternalSource().getEmail().equalsIgnoreCase(""))
								problemBeanVO.setEmail(params.getProblem().getExternalSource().getEmail());
							if(params.getProblem().getExternalSource().getTelePhone() != null && !params.getProblem().getExternalSource().getTelePhone().equalsIgnoreCase(""))
								problemBeanVO.setPhone(params.getProblem().getExternalSource().getTelePhone());
						}

					}

				}
			
			List<CadreProblems> cadreProblem = cadreProblemsDAO
					.getCadreProblemDetailsByProblemId(problemId);
			if (cadreProblem != null)
				for (CadreProblems params : cadreProblem) {
					if (params.getCadre()!= null) {
						if (params.getCadre().getFirstName() != null){
							problemBeanVO.setCadreId(params.getCadre().getCadreId());
							problemBeanVO.setCadreName(params.getCadre().getFirstName());
						}
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return problemBeanVO;
	}


	
	
	public ProblemBeanVO saveProblemDataForNews(final ProblemBeanVO problemBeanVO){
		this.problemBeanVO = problemBeanVO;
		
		ProblemBeanVO problemBeanVO1 = (ProblemBeanVO)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
		
		try{
			
			Problem problem = new Problem();
			ProblemCompleteLocation problemCompleteLocation = new ProblemCompleteLocation();
			ProblemType problemType = new ProblemType();
			UserProblem userProblem = new UserProblem();
			ProblemActivity problemActivity = null;
			InformationSource problemSource = null;
			
			FileGallary fileGallary = fileGallaryDAO.get(problemBeanVO.getContentId());
			
			if(fileGallary.getFile().getRegionScopes() == null || fileGallary.getFile().getLocationValue() == null){
				problemBeanVO.setLocationExist("false");
				return problemBeanVO;
			}
			
			Long locationScope = fileGallary.getFile().getRegionScopes().getRegionScopesId();
			Long locationValue = fileGallary.getFile().getLocationValue();
			String fileTitle = fileGallary.getFile().getFileTitle();
			String fileDescription = fileGallary.getFile().getFileDescription();
			Date fileDate = fileGallary.getFile().getFileDate();
			File file = fileGallary.getFile();
			
			problemCompleteLocation = getProblemCompleteLocationDetailsByLocationIdAndLocationValue(locationScope , locationValue);
			
			problemCompleteLocation = problemCompleteLocationDAO
					.save(problemCompleteLocation);
			
			if (problemCompleteLocation != null)
				problem.setProblemCompleteLocation(problemCompleteLocation);
			
			problemSource = informationSourceDAO.get(1L);
			problem.setInformationSource(problemSource);
			
			refNo = getProblemReferenceNo();
			
			if (refNo != null)
			  problem.setReferenceNo(getRefNo(refNo, "PU"));
			
			problem.setRegionScopes(regionScopesDAO.get(locationScope));
			
			
			if (!fileTitle.contains(" ")) {
				problem.setTitle(stringUtilService
						.fragmentARegularString(
								fileTitle, 100, " "));
			} else {
				problem.setTitle(fileTitle);
			}

			if (!fileDescription.contains(" ")) {
				problem.setDescription(stringUtilService
						.fragmentARegularString(
								fileDescription, 100,
								" "));
			} else {
				problem.setDescription(fileDescription);
			}
			
			Date eDate = sdf.parse(problemBeanVO.getExistingFrom());
			
			//problem.setIdentifiedOn(fileDate);
			problem.setIdentifiedOn(new Date());
			problem.setExistingFrom(eDate);
			
			problem.setProblemStatus(problemStatusDAO.get(1L));
			
			problem.setIsDelete(IConstants.FALSE);
			
			//AGAIN PROBLEM SOURCE
			
			problem.setInsertedTime(dateUtilService
					.getCurrentDateAndTime());
			problem.setUpdatedTime(dateUtilService
					.getCurrentDateAndTime());
			problem.setImpactLevelValue(locationValue);
			problem.setIsApproved(IConstants.TRUE);
			
			problem = problemDAO.save(problem);
			
			problemBeanVO.setProblemId(problem.getProblemId());
			
			NewsProblem newsProblem = new NewsProblem();
			
			newsProblem.setFile(file);
			newsProblem.setProblem(problem);
			newsProblem.setIsDelete(IConstants.FALSE);
			newsProblemDAO.save(newsProblem);
			
			problemBeanVO.setWindowTask(IConstants.NEW);
			problemBeanVO.setProblemPostedBy(IConstants.PARTY_ANALYST_USER);
			
			userProblem = saveUserProblemDetails(problemBeanVO , problem);	
			
			
			List<String> fileName = new ArrayList<String>();
			List<String> fileDescrptn = new ArrayList<String>();
			List<String> fileContentType = new ArrayList<String>();
			List<String> filePaths1 = new ArrayList<String>();
			List<String> fileTitle1 = new ArrayList<String>();
			
			Set<FileSourceLanguage> fileSourceLanguages = file.getFileSourceLanguage();
			
			
			for(FileSourceLanguage fileSource:fileSourceLanguages){
				
				Set<FilePaths> filePaths = fileSource.getFilePaths();
				
				   for(FilePaths filePath :filePaths){
					   
					  
					   String[] filePth = filePath.getFilePath().split("/");
					   
					  //copyNewsFiles(filePath,filePth);
					   
					   copyNewsFiles(filePath,filePth,fileTitle1,fileDescrptn,
							   fileContentType,filePaths1,fileName,fileTitle,fileDescription);
					 
					   
					   fileTitle1.add(fileTitle);
					  // fileName.add(fileTitle);
					   fileDescrptn.add(fileDescription);
					   fileContentType.add(fileTypeDAO.get(filePath.getFileType().getFileTypeId()).getType());
					   filePaths1.add(filePath.getFilePath());
					
					   
					   fileName.add(filePth[filePth.length-1]);
				   }
			}
			
           FileVO fileVO = new FileVO();
           
          
           fileVO.setFileName(fileName);
           fileVO.setFileDescription(fileDescrptn);
           fileVO.setFileContentType(fileContentType);
           fileVO.setFilePath(filePaths1);
           fileVO.setFileTitle(fileTitle1);
           
           problemBeanVO.setFileVO(fileVO);
           
		   saveProblemRelatedFiles(problemBeanVO, problem, userProblem);
			
			
		}catch(Exception e){
			e.printStackTrace();
			status.setRollbackOnly();
			log.debug("Exception Raised :" + e);
		}
		return problemBeanVO;
			}
		});
		return problemBeanVO;
		
	}
	
	public void copyNewsFiles(FilePaths filePath , String[] filePth,
			List<String> fileTitle1,List<String> fileDescrptn,List<String>fileContentType,
			List<String> filePaths1,List<String> fileName , String fileTitle,String fileDescription){
		
		log.debug("Entered into the copyNewsFiles service method");
		
		    InputStream inStream = null;
		    OutputStream outStream = null;
		    
		    try{
			   
    	    java.io.File afile =new java.io.File(problemBeanVO.getSourceFilePath()+filePath.getFilePath());
    	    java.io.File bfile =new java.io.File(problemBeanVO.getDestinationFilePath()+problemBeanVO.getPathSepecrator()+filePth[filePth.length-1]);
 
    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
 
    	    	outStream.write(buffer, 0, length);
 
    	    }
 
    	    inStream.close();
    	    outStream.close();
    	    
    	      fileTitle1.add(fileTitle);
			  // fileName.add(fileTitle);
			   fileDescrptn.add(fileDescription);
			   fileContentType.add(fileTypeDAO.get(filePath.getFileType().getFileTypeId()).getType());
			   filePaths1.add(filePath.getFilePath());
			
			   
			   fileName.add(filePth[filePth.length-1]);
 
    	    System.out.println("File is copied successful!");
		    }catch(Exception e){
		    	log.error("Exception raised in copyNewsFiles service method");
		    	e.printStackTrace();
		    	
		    }
	 
	}


public ProblemBeanVO saveNewProblemData(ProblemBeanVO problemBeanVOToSave) {
		this.problemBeanVO = problemBeanVOToSave;
		if (log.isDebugEnabled()) {
			log.debug("Entered Into saveProblemData Method.....");
		}
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				ProblemBeanVO problemBeanFromDB = new ProblemBeanVO();
				Problem problem = null;
				ProblemExternalSource problemExternalSource = null;
				ProblemType problemType = null;
				UserProblem userProblem = null;
				ProblemCompleteLocation problemCompleteLocation = null;
				String userName = null;

				try {
					InformationSource problemSource = null;

					if (problemBeanVO.getWindowTask().equalsIgnoreCase(
							IConstants.NEW)) {
						problem = new Problem();
						problemCompleteLocation = new ProblemCompleteLocation();
						problemType = new ProblemType();

						userProblem = new UserProblem();

						ProblemActivity problemActivity = null;
						// problemCompleteLocation =
						// saveProblemCompleteLocation(problemBeanVO);

					}
					if (problemBeanVO.getWindowTask().equalsIgnoreCase(
							IConstants.UPDATE_EXISTING)) {
						problem = problemDAO.get(problemBeanVO.getProblemId());
						problemCompleteLocation = problemCompleteLocationDAO
								.get(problem.getProblemCompleteLocation()
										.getProblemCompleteLocationId());

						}

					problemCompleteLocation.setState(stateDAO.get(new Long(
							problemBeanVO.getState())));
					if (problemBeanVO.getIsParliament())
						problemCompleteLocation.setParliamentConstituency(constituencyDAO
								.get(problemBeanVO.getPConstituencyId()));

					if (problemBeanVO.getDistrict() != null
							&& !"0".equals(problemBeanVO.getDistrict()))
						problemCompleteLocation.setDistrict(districtDAO
								.get(new Long(problemBeanVO.getDistrict())));

					if (problemBeanVO.getDistrict().equals("0")
							|| problemBeanVO.getDistrict() == null) {
						problemCompleteLocation.setDistrict(null);
						problemCompleteLocation.setConstituency(null);
						problemCompleteLocation.setTehsil(null);
						problemCompleteLocation.setHamlet(null);

						problemCompleteLocation.setBooth(null);

					}
					if (problemBeanVO.getConstituency() != null
							&& !"0".equals(problemBeanVO.getConstituency()))
						problemCompleteLocation.setConstituency(constituencyDAO
								.get(new Long(problemBeanVO.getConstituency())));

					if (problemBeanVO.getTehsil() != null
							&& !"0".equals(problemBeanVO.getTehsil())) {
						if (IConstants.RURAL_TYPE.equals(problemBeanVO
								.getTehsil().substring(0, 1)))
						{
							problemCompleteLocation.setLocalElectionBody(null);
							problemCompleteLocation.setTehsil(tehsilDAO
									.get(new Long(problemBeanVO.getTehsil()
											.substring(1))));
						}
						else if (IConstants.URBAN_TYPE.equals(problemBeanVO
								.getTehsil().substring(0, 1))) {
							problemCompleteLocation.setTehsil(null);
							Long assemblyLocalElectionBodyId = new Long(
									problemBeanVO.getTehsil().substring(1));
							List localElectionBodyIdsList = assemblyLocalElectionBodyDAO
									.getLocalElectionBodyId(assemblyLocalElectionBodyId);
							Object object = (Object) localElectionBodyIdsList
									.get(0);
							problemCompleteLocation
									.setLocalElectionBody(localElectionBodyDAO
											.get((Long) object));
						}
					}
					if (problemBeanVO.getVillage() != null
							&& !"0".equals(problemBeanVO.getVillage())) {
						if (IConstants.RURAL_TYPE.equals(problemBeanVO
								.getVillage().substring(0, 1)))
							problemCompleteLocation.setHamlet(hamletDAO
									.get(new Long(problemBeanVO.getVillage()
											.substring(1))));

						else if (IConstants.URBAN_TYPE.equals(problemBeanVO
								.getVillage().substring(0, 1))) {
							problemCompleteLocation.setWard(constituencyDAO
									.get(new Long(problemBeanVO.getVillage()
											.substring(1))));
						}
					}

					if (problemBeanVO.getBooth() != null
							&& !"0".equals(problemBeanVO.getBooth()))
						problemCompleteLocation.setBooth(boothDAO.get(new Long(
								problemBeanVO.getBooth())));

					problemCompleteLocation = problemCompleteLocationDAO
							.save(problemCompleteLocation);

					if (problemCompleteLocation != null)
						problem.setProblemCompleteLocation(problemCompleteLocation);

					if (problemBeanVO.getProbSourceId() != null
							&& problemBeanVO.getProbSourceId() > 0) {
						problemSource = informationSourceDAO.get(problemBeanVO
								.getProbSourceId());
						problem.setInformationSource(problemSource);
						if(problemBeanVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING) 
							&& problemBeanVO.getProbSourceId() == 1 && problemBeanVO.getProbSourceId() == 4)
						problem.setExternalSource(null);
					
					if(problemBeanVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING) 
							&& problemBeanVO.getProbSourceId() > 0)
					
						
						deleteCadreProblems(problemBeanVO);
					
						if (problemBeanVO.getProbSourceId().equals(2L)
								|| problemBeanVO.getProbSourceId().equals(3L)) {
							problemExternalSource = saveProblemExternalSource(problemBeanVO);
							problem.setExternalSource(problemExternalSource);
						}
				}
					
					if (problemBeanVO.getWindowTask().equalsIgnoreCase(
							IConstants.NEW)) {
					refNo = getProblemReferenceNo();
					if (refNo != null) {
						if (new Long(0).equals(problemBeanVO.getProbSourceId()))
							problem.setReferenceNo(getRefNo(refNo, "FU"));
						else if (new Long(1).equals(problemBeanVO
								.getProbSourceId()))
							problem.setReferenceNo(getRefNo(refNo, "PU"));
						else if (new Long(2).equals(problemBeanVO
								.getProbSourceId()))
							problem.setReferenceNo(getRefNo(refNo, "EU"));
						else if (new Long(3).equals(problemBeanVO
								.getProbSourceId()))
							problem.setReferenceNo(getRefNo(refNo, "CC"));
						else if (new Long(4).equals(problemBeanVO
								.getProbSourceId()))
							problem.setReferenceNo(getRefNo(refNo, "CD"));
					}
					}

					RegionScopes problemImpactLevel = regionScopesDAO
							.get(problemBeanVO.getProblemImpactLevelId());

					problem.setRegionScopes(regionScopesDAO.get(new Long(
							problemBeanVO.getProblemImpactLevelId())));

					if (!problemBeanVO.getProblem().contains(" ")) {
						problem.setTitle(stringUtilService
								.fragmentARegularString(
										problemBeanVO.getProblem(), 100, " "));
					} else {
						problem.setTitle(problemBeanVO.getProblem());
					}

					if (!problemBeanVO.getDescription().contains(" ")) {
						problem.setDescription(stringUtilService
								.fragmentARegularString(
										problemBeanVO.getDescription(), 100,
										" "));
					} else {
						problem.setDescription(problemBeanVO.getDescription());
					}

					if (problemBeanVO.getProblemTypeId() != 0) {
						problemType = problemTypeDAO.get(problemBeanVO
								.getProblemTypeId());
						problem.setProblemType(problemType);
					}
					if (problemBeanVO.getProblemTypeId() == 0) {
						problem.setProblemType(null);
					}
					Date iDate = sdf.parse(problemBeanVO.getReportedDate());
					Date eDate = sdf.parse(problemBeanVO.getExistingFrom());

					problem.setIdentifiedOn(iDate);
					problem.setExistingFrom(eDate);

					problem.setProblemStatus(problemStatusDAO.get(problemBeanVO
							.getProblemStatusId()));

					problem.setIsDelete(IConstants.FALSE);

					if (problemSource != null
							&& problemSource.getInformationSource() != null)
						problem.setInformationSource(problemSource);

					problem.setInsertedTime(dateUtilService
							.getCurrentDateAndTime());
					problem.setUpdatedTime(dateUtilService
							.getCurrentDateAndTime());

					problem.setImpactLevelValue(getProblemImpactValue(
							problemBeanVO.getProblemImpactLevelId(),
							problemBeanVO.getProblemImpactLevelValue()));

					if (problemBeanVO.getProblemPostedBy().equals(
							IConstants.PARTY_ANALYST_USER)) {
						problem.setIsApproved(IConstants.TRUE);

					} else if (problemBeanVO.getProblemPostedBy().equals(
							IConstants.FREE_USER)) {
						problem.setIsApproved(IConstants.TRUE);

					}

					problem = problemDAO.save(problem);

					if (problemBeanVO.getProblemPostedBy().equals(
							IConstants.PARTY_ANALYST_USER)
							&& problemBeanVO.getProbSourceId() == 4)
						saveCadreProblemDetails(problemBeanVO, problem);

					userProblem = saveUserProblemDetails(problemBeanVO, problem);
					problemBeanFromDB.setUserProblemId(userProblem
							.getUserProblemId());
					if (userProblem.getUser().getFirstName() != null)
						userName = userProblem.getUser().getFirstName()
								.toString();
					if (userProblem.getUser().getLastName() != null)
						userName += " "
								+ userProblem.getUser().getLastName()
										.toString();

					problemBeanFromDB.setProblemId(problem.getProblemId());
					problemBeanFromDB.setProblemRefNum(problem.getReferenceNo());
					problemBeanFromDB.setExistingFrom(problem.getExistingFrom()
							.toString());
					problemBeanFromDB.setReportedDate(problem.getIdentifiedOn()
							.toString());
					problemBeanFromDB.setProblem(problem.getTitle());
					problemBeanFromDB.setDescription(problem.getDescription());
					problemBeanFromDB
							.setEmail(userProblem.getUser().getEmail());
					problemBeanFromDB.setName(userName);
					problemBeanFromDB.setProblemImpactLevelValue(problem
							.getImpactLevelValue());
					problemBeanFromDB.setProblemImpactLevelId(problem
							.getRegionScopes().getRegionScopesId());
					// problemActivity = problemActivityDAO.get(1l);
					saveProblemRelatedFiles(problemBeanVO, problem, userProblem);

				} catch (Exception e) {
					status.setRollbackOnly();
					if (log.isDebugEnabled()) {
						log.debug(
								"Exception Raised while Update And Get Problems Under Pending::",
								e);
					}
					problemBeanFromDB.setExceptionEncountered(e);
					e.printStackTrace();
				}
				problemBeanVO = problemBeanFromDB;
			}
		});
		return this.problemBeanVO;
	}



public List<ProblemBeanVO> getProblemDetailsForProfilePage(int startIndex,int maxIndex)
{
	List<ProblemBeanVO> problemsList = null;
	List<ProblemBeanVO> problemBeanVOList = getProblemDetailsForHomePage(startIndex,maxIndex);
	try{
		
	
	Map<String, ProblemBeanVO> problemsMapList = new HashMap<String, ProblemBeanVO>(0);
	
	
	for(ProblemBeanVO problemDetails : problemBeanVOList)
	{
		String key = problemDetails.getPostedDate();
		
		if(problemsMapList.get(key) == null)
		{ 
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		    problemBeanVO.setPostedDate(key);
		    problemBeanVO.setPostDate(problemDetails.getPostDate());
			List<ProblemBeanVO> list = new ArrayList<ProblemBeanVO>();
			list.add(problemDetails);
			problemBeanVO.setProblemBeanVOList(list);
			problemsMapList.put(key, problemBeanVO);
		}else{
			ProblemBeanVO problemBeanVO = problemsMapList.get(key);
			problemBeanVO.getProblemBeanVOList().add(problemDetails);
		}
		
	}
	problemsList = new ArrayList<ProblemBeanVO>(problemsMapList.values());
	Collections.sort(problemsList, new Comparator<ProblemBeanVO>() {
	    public int compare(ProblemBeanVO m1, ProblemBeanVO m2) {
	        return m1.getPostDate().compareTo(m2.getPostDate());
	    }
	});
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getProblemDetailsForProfilePage() Method, Exception- "+e);
	}
	return problemsList;
}


public List<ProblemBeanVO> getProblemDetailsForPublicProfile(Long profileId, int startIndex, int maxIndex)
{
	List<ProblemBeanVO> problemBeanVOList = new ArrayList<ProblemBeanVO>(0);
	try{
		ProblemBeanVO problemBeanVO = null;
		List<Object[]> problemsList = userProblemDAO.getProblemDetailsForPublicProfile(profileId, startIndex, maxIndex);
		
		if(problemsList != null && problemsList.size() > 0)
		{
			for(Object[] params : problemsList)
			{
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setProblemId((Long)params[0]);
				problemBeanVO.setProblem(params[1].toString());
				problemBeanVO.setDescription(params[2].toString());
				problemBeanVO.setReportedDate(params[3].toString());
				problemBeanVO.setExistingFrom(params[4].toString());
				problemBeanVO.setPostDate((Date)params[3]);
				problemBeanVOList.add(problemBeanVO);
				
			}
		}
		
	return problemBeanVOList;
	}catch (Exception e) {
		log.error("Exception Occured in getProblemDetailsForPublicProfile() Method, Exception - "+e);
		return problemBeanVOList;
	}
	
	
			
}



public List<ProblemBeanVO> getProblemDetailsByProfileId(Long profileId,int startIndex,int maxIndex)
{
	List<ProblemBeanVO> problemsList = null;
	List<ProblemBeanVO> problemBeanVOList = getProblemDetailsForPublicProfile(profileId,startIndex,maxIndex);
	try{
		
	
	Map<String, ProblemBeanVO> problemsMapList = new HashMap<String, ProblemBeanVO>(0);
	
	
	for(ProblemBeanVO problemDetails : problemBeanVOList)
	{
		String key = problemDetails.getPostedDate();
		
		if(problemsMapList.get(key) == null)
		{ 
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		    problemBeanVO.setPostedDate(key);
		    problemBeanVO.setPostDate(problemDetails.getPostDate());
			List<ProblemBeanVO> list = new ArrayList<ProblemBeanVO>();
			list.add(problemDetails);
			problemBeanVO.setProblemBeanVOList(list);
			problemsMapList.put(key, problemBeanVO);
		}else{
			ProblemBeanVO problemBeanVO = problemsMapList.get(key);
			problemBeanVO.getProblemBeanVOList().add(problemDetails);
		}
		
	}
	problemsList = new ArrayList<ProblemBeanVO>(problemsMapList.values());
	Collections.sort(problemsList, new Comparator<ProblemBeanVO>() {
	    public int compare(ProblemBeanVO m1, ProblemBeanVO m2) {
	        return m1.getPostDate().compareTo(m2.getPostDate());
	    }
	});
	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getProblemDetailsByProfileId() Method, Exception- "+e);
	}
	return problemsList;
}

	public ProblemCompleteLocation getProblemCompleteLocationDetailsByLocationIdAndLocationValue(
			Long locationScopeId, Long locationValue) {
		
		ProblemCompleteLocation problemCompleteLocation = new ProblemCompleteLocation();
		
		log.debug("Entered into the getProblemCompleteLocationDetailsByLocationIdAndLocationValue " +
				"service method");
		
		try{
			
			
			if(locationScopeId.longValue() == 2){
				problemCompleteLocation.setState(stateDAO.get(locationValue));
			}else if(locationScopeId.longValue() == 3){
				
				problemCompleteLocation.setDistrict(districtDAO.get(locationValue));
				problemCompleteLocation.setState(districtDAO.get(locationValue).getState());
				
			}else if(locationScopeId.longValue() == 4){
				
				problemCompleteLocation.setConstituency(constituencyDAO.get(locationValue));
				problemCompleteLocation.setDistrict(constituencyDAO.get(locationValue).getDistrict());
				problemCompleteLocation.setState(constituencyDAO.get(locationValue).getDistrict().getState());
				
			}else if(locationScopeId.longValue() == 5){				
				List<Constituency> constituencies = delimitationConstituencyMandalDAO.getConstituencyByTehsilId(locationValue);
				
				Constituency constituency = constituencies.get(0);				
				problemCompleteLocation.setConstituency(constituency);
				problemCompleteLocation.setDistrict(constituency.getDistrict());
				problemCompleteLocation.setState(constituency.getDistrict().getState());
				
			}else if(locationScopeId.longValue() == 6){
				
				List<Panchayat> panchayatList= panchayatHamletDAO.getPanchayatByHamletId(locationValue);
				
				if(panchayatList != null && panchayatList.size() >0){
					
					problemCompleteLocation.setHamlet(hamletDAO.get(locationValue));
					problemCompleteLocation.setTehsil(panchayatList.get(0).getTehsil());
					problemCompleteLocation.setDistrict(panchayatList.get(0).getTehsil().getDistrict());
					problemCompleteLocation.setState(panchayatList.get(0).getTehsil().getDistrict().getState());
				}
               
				
			}else if(locationScopeId.longValue() == 7){
				
				LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);
				
                problemCompleteLocation.setLocalElectionBody(localElectionBody);
                problemCompleteLocation.setDistrict(localElectionBody.getDistrict());
                problemCompleteLocation.setState(localElectionBody.getDistrict().getState());
                
			}else if(locationScopeId.longValue() == 8){
				
				Constituency  constituency = constituencyDAO.get(locationValue);
				
				  problemCompleteLocation.setWard(constituency);
				  problemCompleteLocation.setLocalElectionBody(constituency.getLocalElectionBody());
	              problemCompleteLocation.setDistrict(constituency.getLocalElectionBody().getDistrict());
	              problemCompleteLocation.setState(constituency.getLocalElectionBody().getDistrict().getState());
				}	
			
			
		}catch(Exception e){
		
			log.error("Exception raised in getProblemCompleteLocationDetailsByLocationIdAndLocationValue service method");
			e.printStackTrace();
			
		}
		
		return problemCompleteLocation;
	
    }
	
	
	public ResultStatus deleteProblemFile(Long problemFileId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			Integer result = problemFilesDAO.deleteProblemFile(problemFileId);
			if(result != 0)
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			else if(result == 0)
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);	
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultStatus;
	}

*/}
