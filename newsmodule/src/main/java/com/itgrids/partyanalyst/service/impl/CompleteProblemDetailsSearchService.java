package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.ICompleteProblemDetailsSearchService;

public class CompleteProblemDetailsSearchService implements
		ICompleteProblemDetailsSearchService {/*
	private final static Logger Log = Logger.getLogger(CompleteProblemDetailsSearchService.class);
	private IUserProblemDAO userProblemDAO;
	private IProblemSourceScopeDAO problemSourceScopeDAO;
	private IModuleRegionScopesDAO moduleRegionScopesDAO;
	private IModuleDetailsDAO moduleDetailsDAO;
	private IProblemManagementService problemManagementService;
	private IProblemManagementReportService problemManagementReportService;
	private IProblemAssignedCadreDAO problemAssignedCadreDAO;
	private IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO;
	private IProblemStatusDAO problemStatusDAO;
	private IProblemCommentsDAO problemCommentsDAO;
	private ICadreProblemsDAO cadreProblemsDAO;
	
	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IProblemSourceScopeDAO getProblemSourceScopeDAO() {
		return problemSourceScopeDAO;
	}

	public void setProblemSourceScopeDAO(
			IProblemSourceScopeDAO problemSourceScopeDAO) {
		this.problemSourceScopeDAO = problemSourceScopeDAO;
	}

	public IModuleRegionScopesDAO getModuleRegionScopesDAO() {
		return moduleRegionScopesDAO;
	}

	public void setModuleRegionScopesDAO(
			IModuleRegionScopesDAO moduleRegionScopesDAO) {
		this.moduleRegionScopesDAO = moduleRegionScopesDAO;
	}

	public IModuleDetailsDAO getModuleDetailsDAO() {
		return moduleDetailsDAO;
	}

	public void setModuleDetailsDAO(IModuleDetailsDAO moduleDetailsDAO) {
		this.moduleDetailsDAO = moduleDetailsDAO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public IProblemAssignedCadreDAO getProblemAssignedCadreDAO() {
		return problemAssignedCadreDAO;
	}

	public void setProblemAssignedCadreDAO(
			IProblemAssignedCadreDAO problemAssignedCadreDAO) {
		this.problemAssignedCadreDAO = problemAssignedCadreDAO;
	}

	public IProblemAssignedDepartmentDAO getProblemAssignedDepartmentDAO() {
		return problemAssignedDepartmentDAO;
	}

	public void setProblemAssignedDepartmentDAO(
			IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO) {
		this.problemAssignedDepartmentDAO = problemAssignedDepartmentDAO;
	}

	public IProblemStatusDAO getProblemStatusDAO() {
		return problemStatusDAO;
	}

	public void setProblemStatusDAO(IProblemStatusDAO problemStatusDAO) {
		this.problemStatusDAO = problemStatusDAO;
	}

	public IProblemCommentsDAO getProblemCommentsDAO() {
		return problemCommentsDAO;
	}

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}

	public ICadreProblemsDAO getCadreProblemsDAO() {
		return cadreProblemsDAO;
	}

	public void setCadreProblemsDAO(ICadreProblemsDAO cadreProblemsDAO) {
		this.cadreProblemsDAO = cadreProblemsDAO;
	}

	public List<SelectOptionVO> getProblemPostedUserDetails(Long userId)
	{
		List<SelectOptionVO> userList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> list = userProblemDAO.getAllProblemPostedUserDetails(userId);
			if(list != null && list.size() > 0)
  			 {
				for(Object[] params : list)
				{
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName((params[1]!=null?params[1].toString():"")+" "+(params[2]!=null?params[2].toString():""));
					userList.add(selectOptionVO);
				}
					
			 }
			return userList;
		}catch (Exception e) {
			Log.error("Exception Occured in getProblemPostedUserDetails() Method , Exception - "+e);
			return userList;
		}
	}
	
	public List<SelectOptionVO> getStatesForDepartments(){
		List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> states = problemSourceScopeDAO.getStatesForDepartment();
		  if(!states.isEmpty()){
			 for(Object[] state:states){
			  SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)state[0]);
				selectOptionVO.setName(state[1]!=null?state[1].toString():"");
				statesList.add(selectOptionVO);
			 }
		  }
		}catch(Exception e){
			Log.error("Exception Occured in getStatesForDepartments() Method , Exception - "+e);
		}
		return statesList;
	}
	
	public List<SelectOptionVO> getDepartmentsByStateId(Long stateId){
		List<SelectOptionVO> departmentsList = new ArrayList<SelectOptionVO>();
		try{
		  List<ProblemSourceScope> problemSourceScopeList = problemSourceScopeDAO.findByStateId(stateId);
		  if(!problemSourceScopeList.isEmpty()){
			 for(ProblemSourceScope problemSourceScope:problemSourceScopeList){
			  SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(problemSourceScope.getProblemSourceScopeId());
				selectOptionVO.setName(problemSourceScope.getScope());
				departmentsList.add(selectOptionVO);
			 }
		  }
		}catch(Exception e){
			Log.error("Exception Occured in getDepartmentsByStateId() Method , Exception - "+e);
		}
		return departmentsList;
	}
	
	public List<SelectOptionVO> getProblemsContainStates(Long userId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
		try{
			getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
			List<Object[]> states = userProblemDAO.getAllProblemContainStates(problemSearchFilterOptionsVO.getInitialConditionsIds());
		  if(!states.isEmpty()){
			 for(Object[] state:states){
		      if(state[2] != null && new Long(state[2].toString()).longValue() > 0l){
			    SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)state[0]);
				selectOptionVO.setName(state[1]!=null?state[1].toString()+"-"+state[2].toString():"");
				statesList.add(selectOptionVO);
		      }
			 }
		  }
		}catch(Exception e){
			Log.error("Exception Occured in getProblemsContainStates() Method , Exception - "+e);
		}
		return statesList;
	}
	
	public List<SelectOptionVO> getProblemImpactRegions(Long stateId,String userType,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
		try{
			List result = moduleDetailsDAO.findModuleIdByModuleName(IConstants.ADD_NEW_PROBLEM);
			Long  moduleId = Long.parseLong(result.get(0).toString());
			List allScopes = moduleRegionScopesDAO.findRegionScopesForModuleByState(moduleId, stateId);
			getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
			if(allScopes != null && allScopes.size()>0)
				for(int i = 0;i<allScopes.size();i++)
				{					 
					Object[] obj = (Object[])allScopes.get(i);
					Long count = userProblemDAO.getRegionWideProbCount(stateId,getLocationString(new Long(obj[0].toString())),problemSearchFilterOptionsVO.getInitialConditionsIds());
					if(count != null && count.longValue() > 0){
					if(!userType.equalsIgnoreCase(IConstants.NOT_LOGGED_IN) && !userType.equalsIgnoreCase(IConstants.FREE_USER)){
					  statesList.add(new SelectOptionVO(Long.parseLong(obj[0].toString()),obj[1].toString()+"-"+count.toString()));
					}else if((new Long(obj[0].toString())).longValue() <= 4l){
						statesList.add(new SelectOptionVO(Long.parseLong(obj[0].toString()),obj[1].toString()+"-"+count.toString()));
					}
					}
				}
		}catch(Exception e){
			Log.error("Exception Occured in getProblemsContainStates() Method , Exception - "+e);
		}
		return statesList;
	}
	public String getLocationString(Long impactedRegionId)
	{
		try{
			String locationStr = null;
			long regionId = impactedRegionId.longValue();
			
			if(regionId == 2)
				locationStr = " and model.problem.problemCompleteLocation.state.stateId != null ";
			else if(regionId == 3)
				locationStr = " and model.problem.problemCompleteLocation.district.districtId != null ";
			else if(regionId == 4)
				locationStr = " and model.problem.problemCompleteLocation.constituency.constituencyId != null ";
			else if(regionId == 5)
				locationStr = " and model.problem.problemCompleteLocation.tehsil.tehsilId != null ";
			else if(regionId == 6)
				locationStr = " and model.problem.problemCompleteLocation.hamlet.hamletId != null ";
			else if(regionId == 7)
				locationStr = " and model.problem.problemCompleteLocation.localElectionBody.localElectionBodyId != null ";
			else if(regionId == 8)
				locationStr = " and model.problem.problemCompleteLocation.ward.constituencyId != null ";
			else if(regionId == 9)
				locationStr = " and model.problem.problemCompleteLocation.booth.boothId != null ";
			
			return locationStr;
		}catch(Exception e){
			return "";
		}	
	}
	
	public List<SelectOptionVO> getAllProblemContainDistricts(Long stateId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> districtDetails = userProblemDAO.getAllProblemContainDistricts(stateId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertToSelectOptionVO(districtDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainConstituencies(Long districtId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> constituencyDetails = userProblemDAO.getAllProblemContainConstituencies(districtId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertToSelectOptionVO(constituencyDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainMandals(Long constituencyId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> mandalDetails = userProblemDAO.getAllProblemContainMandals(constituencyId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertToSelectOptionVO(mandalDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainVillages(Long mandalId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> villageDetails = userProblemDAO.getAllProblemContainVillages(mandalId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertToSelectOptionVO(villageDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainBooths(Long mandalId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> boothDetails = userProblemDAO.getAllProblemContainBooths(mandalId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertBoothToSelectOptionVO(boothDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainMuncpCorpGmc(Long constituencyId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> muncpCorpGmcDetails = userProblemDAO.getAllProblemContainMuncpCorpGmc(constituencyId,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertMuncpGmcCorpToSelectOptionVO(muncpCorpGmcDetails);
	}
	
	public List<SelectOptionVO> getAllProblemContainWards(Long localElection,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		
		List<Object[]> wardDetails = userProblemDAO.getAllProblemContainWards(localElection,problemSearchFilterOptionsVO.getInitialConditionsIds(),getLocationString(problemSearchFilterOptionsVO.getImpactLevel()));
		return convertToSelectOptionVO(wardDetails);
	}
	
	public List<SelectOptionVO> convertToSelectOptionVO(List<Object[]> detailsArray){
		List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		for(Object[] reqVals:detailsArray){
			if(reqVals[2] != null &&  ((Long)reqVals[2]).longValue() > 0l){
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)reqVals[0]);
				selectOptionVO.setName(reqVals[1].toString()+"-"+reqVals[2].toString());
				returnVal.add(selectOptionVO);
			}
		}
		
		return returnVal;
	}
	
	public List<SelectOptionVO> convertBoothToSelectOptionVO(List<Object[]> detailsArray){
		List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		for(Object[] reqVals:detailsArray){
			if(reqVals[2] != null &&  ((Long)reqVals[2]).longValue() > 0l){
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)reqVals[0]);
				selectOptionVO.setName("Booth No "+reqVals[1].toString()+"-"+reqVals[2].toString());
				returnVal.add(selectOptionVO);
			}
		}
		
		return returnVal;
	}
	
	public List<SelectOptionVO> convertMuncpGmcCorpToSelectOptionVO(List<Object[]> detailsArray){
		List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		for(Object[] reqVals:detailsArray){
			if(reqVals[2] != null &&  ((Long)reqVals[2]).longValue() > 0l){
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)reqVals[0]);
				selectOptionVO.setName(reqVals[1].toString()+" "+reqVals[3].toString()+"-"+reqVals[2].toString());
				returnVal.add(selectOptionVO);
			}
		}
		
		return returnVal;
	}
	
	public List<CompleteProblemDetailsVO> getProblemsByFilterOptions(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		if(Log.isDebugEnabled()){
			Log.debug("Enter into getProblemsByFilterOptions method");
		}
		List<CompleteProblemDetailsVO> completeProblemDetailsVOList =  new ArrayList<CompleteProblemDetailsVO>();
		try{
			getDepartmentDetails(problemSearchFilterOptionsVO);
			getLocationDetails(problemSearchFilterOptionsVO);
			getCadreProblemDetails(problemSearchFilterOptionsVO);
			getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
			List<Problem> problemsList = userProblemDAO.getAllProblemsByFilterOptions(problemSearchFilterOptionsVO);
			List<Long> problemsCountList = userProblemDAO.getAllProblemsByFilterOptionsCount(problemSearchFilterOptionsVO);
		    convertListToVo(completeProblemDetailsVOList,problemsList,problemsCountList.get(0),problemSearchFilterOptionsVO);
		}catch(Exception e){
			Log.error("Exception Occured in getProblemsByFilterOptions() Method , Exception - "+e);
		}
		return completeProblemDetailsVOList;
	}
	
	public void  convertListToVo(List<CompleteProblemDetailsVO> completeProblemDetailsVOList,List<Problem> problemsList,Long count,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		CompleteProblemDetailsVO completeProblemDetailsVO = null;
		for(Problem problem:problemsList){
			completeProblemDetailsVO = new CompleteProblemDetailsVO();
			completeProblemDetailsVO.setProblemId(problem.getProblemId());
			completeProblemDetailsVO.setProblemTitle(problem.getTitle());
			completeProblemDetailsVO.setProblemDesc(problem.getDescription());
			if(problem.getRegionScopes() != null && problem.getRegionScopes().getRegionScopesId() != null && problem.getImpactLevelValue() != null)
			completeProblemDetailsVO.setProblemCompleteLoc(problemManagementReportService.getProblemLocation(problem.getRegionScopes().getRegionScopesId(),problem.getImpactLevelValue()));
			completeProblemDetailsVO.setProblemsCount(count);
			completeProblemDetailsVO.setStatus(problem.getProblemStatus().getStatus());
			final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			  completeProblemDetailsVO.setExistingFrom(dateFormat.format(problem.getExistingFrom()));
			  completeProblemDetailsVO.setIdentifiedOn(dateFormat.format(problem.getIdentifiedOn()));
			completeProblemDetailsVO.setRating(problemManagementService.getAverageRatingOfAProblem(problem.getProblemId()).getAvgRating());
			if(problemSearchFilterOptionsVO.getDepartmentId() != null && problemSearchFilterOptionsVO.getDepartmntnames() != null){
				completeProblemDetailsVO.setDeptsetng("true");
				completeProblemDetailsVO.setDeptName(problemSearchFilterOptionsVO.getDepartmntnames().get(problem.getProblemId()))	;		
			}
			if(problemSearchFilterOptionsVO.isCadreReq()){
				completeProblemDetailsVO.setCadreseting("true");
				SelectOptionVO selectOptionVO = problemSearchFilterOptionsVO.getCadretakennames().get(problem.getProblemId()) ;
				if(selectOptionVO != null){
				 completeProblemDetailsVO.setCadretakenName(selectOptionVO.getName());
				 completeProblemDetailsVO.setCadretakenId(selectOptionVO.getId());
				}
				List<Object[]> cadrepostedprob = cadreProblemsDAO.getProblemPostedCadreNameAndId(problem.getProblemId());
			   if(cadrepostedprob != null && cadrepostedprob.size() >0){
				   Object[] cadreData = cadrepostedprob.get(0);
				   completeProblemDetailsVO.setCadrepostedId((Long)cadreData[0]);
				   completeProblemDetailsVO.setCadrepostedName(cadreData[1].toString()+" "+cadreData[2].toString());
			   }
			}
			completeProblemDetailsVOList.add(completeProblemDetailsVO);
		}
	}
	
	public void getProblemIdsWithInitialConditions(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		
		
		Set<Long> reqProbIds = new HashSet<Long>();
		reqProbIds.add(0l);
		List<Long> probIdsList = new ArrayList<Long>();
		
	try{
		if(problemSearchFilterOptionsVO.isMyPrivateProb()){
			 probIdsList = userProblemDAO.getMyProblems(getUserRelatedProblemsCountQuery("myprivateprob",problemSearchFilterOptionsVO.getUserId()));
		    addIdsToVO(probIdsList,reqProbIds);
		}
        if(problemSearchFilterOptionsVO.isMyPublicProb()){
        	probIdsList = userProblemDAO.getMyProblems(getUserRelatedProblemsCountQuery("mypublicprob",problemSearchFilterOptionsVO.getUserId()));
        	addIdsToVO(probIdsList,reqProbIds);
        }
        if(problemSearchFilterOptionsVO.isTakenUpProb()){
        	probIdsList = userProblemDAO.getMyProblems(getUserRelatedProblemsCountQuery("probtakenbyme",problemSearchFilterOptionsVO.getUserId()));
        	addIdsToVO(probIdsList,reqProbIds);
        }
        if(problemSearchFilterOptionsVO.isCommentByMeProb()){
        	probIdsList =getCommentedByMeProblemIds(problemSearchFilterOptionsVO.getUserId());
        	addIdsToVO(probIdsList,reqProbIds);
        }
        if(problemSearchFilterOptionsVO.isAllPublicProb()){
        	probIdsList = userProblemDAO.getMyProblems(getUserRelatedProblemsCountQuery("allpublicprob",problemSearchFilterOptionsVO.getUserId()));
        	addIdsToVO(probIdsList,reqProbIds);
        }
		if(problemSearchFilterOptionsVO.isPostedByMeProb()){
			probIdsList = userProblemDAO.getMyProblems(getUserRelatedProblemsCountQuery("probpostedbyme",problemSearchFilterOptionsVO.getUserId()));
			addIdsToVO(probIdsList,reqProbIds);
		}
	 }catch(Exception e){
		 Log.error("Exception Occured in getProblemIdsWithInitialConditions() Method , Exception - "+e);
	 }
	problemSearchFilterOptionsVO.setInitialConditionsIds(new ArrayList<Long>(reqProbIds));
	}
	
	public void addIdsToVO(List<Long> probIdsList,Set<Long> initialProbIdsList){
		for(Long problemId:probIdsList){
			initialProbIdsList.add(problemId);
		}
		
	}
	
	public void getDepartmentDetails(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		try{
		  if(problemSearchFilterOptionsVO.getDepartmentId() != null){
			 List<Long> organisationIds =  getOrganisationsInDepartment(problemSearchFilterOptionsVO.getDepartmentId());
			 List<Object[]> problemsList = problemAssignedDepartmentDAO.getProblemIds(problemSearchFilterOptionsVO.getUserId() ,organisationIds,problemSearchFilterOptionsVO.isOnlyUserProb());
			 getProbIdsFromList(problemsList,problemSearchFilterOptionsVO);
		  }
		}catch(Exception e){
			Log.error("Exception Occured in getDepartmentDetails() Method , Exception - "+e);
		}
	}
	
	public void getProbIdsFromList(List<Object[]> problemsList,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		List<Long> problemIdsList = new ArrayList<Long>();
		Map<Long,String> deptname = new HashMap<Long,String>();
		for(Object[] problem : problemsList){
			if(problem[0] != null && problem[1] != null){
				problemIdsList.add((Long)problem[0]);
				deptname.put((Long)problem[0], problem[1].toString());
			}
		}
		Collections.sort(problemIdsList);
		 problemIdsList.add(0l);
		problemSearchFilterOptionsVO.setDepartmntProblemIds(problemIdsList);
		problemSearchFilterOptionsVO.setDepartmntnames(deptname);
		
	}
	public List<Long> getOrganisationsInDepartment(Long departmentId){
		  List<Long> organisationIdsList = new ArrayList<Long>();
		try{
			if(departmentId != null){
				  List<SelectOptionVO> deptList = problemManagementService.getDepartmentsForADepartmentResolvingAreaScope(departmentId);
			     if(!deptList.isEmpty()){
			    	  for(SelectOptionVO selectOptionVO :deptList){
			    		  organisationIdsList.add(selectOptionVO.getId());		    		  
			    	  }
			     }
			  } 
		}catch(Exception e){
			Log.error("Exception Occured in getOrganisationsInDepartment() Method , Exception - "+e);
		}
		organisationIdsList.add(0l);
		return organisationIdsList;
	}

	public void getLocationDetails(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		try{
			if(problemSearchFilterOptionsVO.getLocationId() != null && problemSearchFilterOptionsVO.getLocationValue() != null){
				 Long locationVal = problemSearchFilterOptionsVO.getLocationValue();
				 problemSearchFilterOptionsVO.setLocationValue(locationVal);
				 problemSearchFilterOptionsVO.setLocationString(problemManagementReportService.getLocationStringFromProblemHistory(problemSearchFilterOptionsVO.getLocationId(),locationVal));
			} 
		}catch(Exception e){
			Log.error("Exception Occured in getLocationDetails() Method , Exception - "+e);
		}
		
	}
	
	public void getCadreProblemDetails(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		 List<Long> problemIdsList = new ArrayList<Long>();
		try{
			if(problemSearchFilterOptionsVO.isCadreReq()){
				List<Long> cadreAssigProb = getCadreAssignedProblems(problemSearchFilterOptionsVO.getUserId(),problemSearchFilterOptionsVO.isOnlyUserProb(),problemSearchFilterOptionsVO);
				List<Long> cadrePostProb = getCadrePostedProblems(problemSearchFilterOptionsVO.getUserId(),problemSearchFilterOptionsVO.isOnlyUserProb());
				problemIdsList = getTotalCadreRelatedProblems(cadreAssigProb,cadrePostProb);
				Collections.sort(problemIdsList);
			} 
		}catch(Exception e){
			Log.error("Exception Occured in getCadreProblemDetails() Method , Exception - "+e);
		}
		
		problemSearchFilterOptionsVO.setCadreProblemIds(problemIdsList);
	}
	public List<Long> getCadreAssignedProblems(Long userId,boolean isOnlyUserProb,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		List<Long> returnVal = new ArrayList<Long>();
		Map<Long,SelectOptionVO> cadretakennames = new HashMap<Long,SelectOptionVO>();
		try{
			SelectOptionVO selectOptionVO = null;
			List<Object[]> cadreAssigProb = problemAssignedCadreDAO.getProblemIds(userId,isOnlyUserProb);
			for(Object[] problem:cadreAssigProb){
				if(problem[0] != null){
					returnVal.add((Long)problem[0]);
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)problem[3]);
					selectOptionVO.setName(problem[1].toString()+" "+problem[2].toString());
					cadretakennames.put((Long)problem[0],selectOptionVO);
				}
				
			}
			Collections.sort(returnVal);
		}catch(Exception e){
			Log.error("Exception Occured in getCadreAssignedProblems() Method , Exception - "+e);
		}
		if(problemSearchFilterOptionsVO != null)
			problemSearchFilterOptionsVO.setCadretakennames(cadretakennames);
		returnVal.add(0l);
		return returnVal;
	}
	public List<Long> getCadrePostedProblems(Long userId,boolean isOnlyUserProb){
		List<Long> returnVal = new ArrayList<Long>();
		try{
		  
			  returnVal = userProblemDAO.getCadrePostedProblems(userId,isOnlyUserProb);
			  Collections.sort(returnVal);
		}catch(Exception e){
			Log.error("Exception Occured in getCadrePostedProblems() Method , Exception - "+e);
		}
		returnVal.add(0l);
		return returnVal;
	}
	
	private List<Long> getTotalCadreRelatedProblems(List<Long> cadreAssigProb,List<Long> cadrePostProb){
		Set<Long> problemIds = new HashSet<Long>();
	  try{
		for(Long problemId : cadreAssigProb){
			problemIds.add(problemId);
		}
		for(Long problemId : cadrePostProb){
			problemIds.add(problemId);
		}
	  }catch(Exception e){
		  Log.error("Exception Occured in getTotalCadreRelatedProblems() Method , Exception - "+e);
	  }
	  problemIds.add(0l);
	  return new ArrayList<Long>(problemIds);
	}
	
	public List<SelectOptionVO> getStatusWiseProblemCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		
		if(Log.isDebugEnabled()){
			Log.debug("Enter into getStatusWiseProblemCount method");
		}
		List<SelectOptionVO> selectOptionVOList =  new ArrayList<SelectOptionVO>();
		try{
			getDepartmentDetails(problemSearchFilterOptionsVO);
			getLocationDetails(problemSearchFilterOptionsVO);
			getCadreProblemDetails(problemSearchFilterOptionsVO);
			getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
			List<Object[]> problemsList = userProblemDAO.getAllProblemsByFilterOptionsStatusCount(problemSearchFilterOptionsVO);
			
			selectOptionVOList = convertListToSelectOptionVO(problemsList);
		}catch(Exception e){
			Log.error("Exception Occured in getStatusWiseProblemCount() Method , Exception - "+e);
		}
		return selectOptionVOList;
	}
	
	private List<SelectOptionVO> convertListToSelectOptionVO(List<Object[]> problemsList){
		List<ProblemStatus> problemStatusList = problemStatusDAO.getAll();
		SelectOptionVO selectOptionVO = null;
		Map<String,SelectOptionVO> selectOptionVOMap = new HashMap<String,SelectOptionVO>();
		for(ProblemStatus problemStatus:problemStatusList){
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName(problemStatus.getStatus());
			selectOptionVO.setId(0l);
			selectOptionVOMap.put(problemStatus.getStatus(), selectOptionVO);
		}
		for(Object[] statusCount:problemsList){
			selectOptionVOMap.get(statusCount[0].toString()).setId((Long)statusCount[1]);
		}
		
		return new ArrayList<SelectOptionVO>(selectOptionVOMap.values());
	}
    public List<SelectOptionVO> getCadreWiseProblemCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		
		if(Log.isDebugEnabled()){
			Log.debug("Enter into getCadreWiseProblemCount method");
		}
		List<SelectOptionVO> selectOptionVOList =  new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = null;
		try{
			problemSearchFilterOptionsVO.setOnlyUserProb(true);
			getDepartmentDetails(problemSearchFilterOptionsVO);
			getLocationDetails(problemSearchFilterOptionsVO);
			
			List<Long> cadrPersProbIds = getCadrePersonalProblemCount(problemSearchFilterOptionsVO);
			
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName("PERSONAL");
			selectOptionVO.setId(cadrPersProbIds != null?new Long(cadrPersProbIds.size()):0l);
			selectOptionVOList.add(selectOptionVO);
			List<Long> cadrAssigProbIds = getCadreAssignedCount(problemSearchFilterOptionsVO);
			
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName("ASSIGNED");
			selectOptionVO.setId(cadrAssigProbIds != null?new Long(cadrAssigProbIds.size()):0l);
			selectOptionVOList.add(selectOptionVO);
			int totalcount = getTotalCadreProblemCount(cadrPersProbIds,cadrAssigProbIds);
			int bothcommoncount = getBothProblemCount(cadrPersProbIds,cadrAssigProbIds);
			
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName("Both");
			selectOptionVO.setId(new Long(bothcommoncount));
			selectOptionVOList.add(selectOptionVO);
			
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName("Total");
			selectOptionVO.setId(new Long(totalcount));
			selectOptionVOList.add(selectOptionVO);
		}catch(Exception e){
			Log.error("Exception Occured in getCadreWiseProblemCount() Method , Exception - "+e);
		}
		return selectOptionVOList;
	}
    
    public List<Long> getCadrePersonalProblemCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
    	problemSearchFilterOptionsVO.setOnlyUserProb(true);
    	getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
    	problemSearchFilterOptionsVO.setCadreProblemIds(getCadrePostedProblems(problemSearchFilterOptionsVO.getUserId(),true));
    	
    	List<Problem> problemsList = userProblemDAO.getAllProblemsByFilterOptions(problemSearchFilterOptionsVO);
          return getProblemIds(problemsList);
    }
    
    public List<Long> getCadreAssignedCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
    	
    	problemSearchFilterOptionsVO.setCadreProblemIds(getCadreAssignedProblems(problemSearchFilterOptionsVO.getUserId(),true,null));
    	List<Problem> problemsList = userProblemDAO.getAllProblemsByFilterOptions(problemSearchFilterOptionsVO);
    	return getProblemIds(problemsList);
    }
	public int getTotalCadreProblemCount(List<Long> cadrPersonl,List<Long> cadrAssign){
		Set<Long> totalCountSet = new HashSet<Long>();
		for(Long count:cadrPersonl)
			totalCountSet.add(count);
		for(Long count:cadrAssign)
			totalCountSet.add(count);
		return totalCountSet.size();
	}
	public int getBothProblemCount(List<Long> cadrPersonl,List<Long> cadrAssign){
		int bothcount = 0;
		for(Long cadrPersonlcount:cadrPersonl)
		{
		   for(Long cadrAssigncount:cadrAssign){
			  if(cadrPersonlcount.longValue() == cadrAssigncount.longValue())
				  bothcount = bothcount+1;
		   }
			
		}
		return bothcount;
	}
	private List<Long> getProblemIds(List<Problem> problemsList){
		List<Long> problemIdsList = new ArrayList<Long>();
		for(Problem problem:problemsList)
			problemIdsList.add(problem.getProblemId());
		return problemIdsList;
	}
	
	public List<ProblemBeanVO> getDepartmentWiseProblemCount(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		problemSearchFilterOptionsVO.setOnlyUserProb(true);
		List<ProblemBeanVO> problemBeanVoList = new ArrayList<ProblemBeanVO>();
		List<SelectOptionVO> deptList = problemManagementService.getDepartmentsForADepartmentResolvingAreaScope(problemSearchFilterOptionsVO.getDepartmentId());
		ProblemBeanVO problemBeanVO = null;
		for(SelectOptionVO department:deptList) {
			problemBeanVO = new ProblemBeanVO();
			problemBeanVO.setDepartmentId(department.getId());
			problemBeanVO.setDeptName(department.getName());
			problemBeanVO.setDepartments(getStatusWiseProbCountInDept(department.getId(),problemSearchFilterOptionsVO));
			problemBeanVoList.add(problemBeanVO);
		}
		return problemBeanVoList;
	}
	
	private List<SelectOptionVO> getStatusWiseProbCountInDept(Long deptId,ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		List<Long> organisationIds = new ArrayList<Long>();
		organisationIds.add(deptId);
		getLocationDetails(problemSearchFilterOptionsVO);
		getCadreProblemDetails(problemSearchFilterOptionsVO);
		List<Object[]> problems = problemAssignedDepartmentDAO.getProblemIds(problemSearchFilterOptionsVO.getUserId() ,organisationIds,problemSearchFilterOptionsVO.isOnlyUserProb());
		getProbIdsFromList(problems,problemSearchFilterOptionsVO);
		getProblemIdsWithInitialConditions(problemSearchFilterOptionsVO);
		List<Object[]> problemsList = userProblemDAO.getAllProblemsByFilterOptionsStatusCount(problemSearchFilterOptionsVO);
		return convertListToSelectOptionVO( problemsList);
	}
	public CompleteProblemDetailsVO getCadreAndDeptWiseCounts(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
		CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
		List<ProblemBeanVO> deptwiseCount = getDepartmentWiseProblemCount(problemSearchFilterOptionsVO);
		if(deptwiseCount != null && deptwiseCount.size() > 0){
			long count = 0l;
			for(ProblemBeanVO problemBeanVO:deptwiseCount){
				for(SelectOptionVO selectOptionVO:problemBeanVO.getDepartments()){
					if(selectOptionVO.getId() != null)
						count = count+selectOptionVO.getId().longValue();
				}
			}
			if(count > 0)
			 completeProblemDetailsVO.setDeptcount("true");
		}
		completeProblemDetailsVO.setDeptwiseCount(deptwiseCount);
		List<SelectOptionVO> cadreWiseCount = getCadreWiseProblemCount(problemSearchFilterOptionsVO);
		
		if(cadreWiseCount != null && cadreWiseCount.size() > 0){
			long count = 0l;
			for(SelectOptionVO selectOptionVO:cadreWiseCount){
				if(selectOptionVO.getId() != null)
					count = count+selectOptionVO.getId().longValue();
			}
			if(count > 0)
			completeProblemDetailsVO.setCadrecount("true");
		}
		completeProblemDetailsVO.setCadreWiseCount(cadreWiseCount);
		return completeProblemDetailsVO;
	}
	
	
	//to get total prob count like myprivateprobcount,mypublicprob count etc for completeProblemDetailsSearch page
	public MyProblemsCountVO getUserRelatedProblemsCount(MyProblemsCountVO myProblemsCountVO){
	 try{
		if(myProblemsCountVO.getUserType().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER) || myProblemsCountVO.getUserType().equalsIgnoreCase(IConstants.BOTH)){
			getUserRelatedProblemsCountForCustmor(myProblemsCountVO);
		}
		else if(myProblemsCountVO.getUserType().equalsIgnoreCase(IConstants.FREE_USER)){
			getUserRelatedProblemsCountForUser(myProblemsCountVO);
		}
        else if(myProblemsCountVO.getUserType().equalsIgnoreCase(IConstants.NOT_LOGGED_IN)){
        	getUserRelatedProblemsCountForNotLogged(myProblemsCountVO);
		}
	  }catch(Exception e){
		  Log.error("Exception Occured in getUserRelatedProblemsCount() Method , Exception - "+e); 
	  }
		return myProblemsCountVO;
	}
	
	//to get required problems count for customor
	private void getUserRelatedProblemsCountForCustmor(MyProblemsCountVO myProblemsCountVO){
	 try{
		List<Long> countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("myprivateprob",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setMyPrivateProbCount(countList.get(0));
		
		countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("mypublicprob",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setMyPublicProbCount(countList.get(0));
		
		countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("probtakenbyme",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setTakenByMeCount(countList.get(0));
		
		countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("allpublicprob",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setAllPublicProbcount(countList.get(0));
		
		myProblemsCountVO.setCommntByMecount(getCommentedByMeProbCount(myProblemsCountVO.getUserId()));
	 }catch(Exception e){
		 Log.error("Exception Occured in getUserRelatedProblemsCountForCustmor() Method , Exception - "+e);  
	 }
	}
	
	//to get required problems count for free logged user
    private void getUserRelatedProblemsCountForUser(MyProblemsCountVO myProblemsCountVO){
     try{
    	List<Long> countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("allpublicprob",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setAllPublicProbcount(countList.get(0));
		
		 countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("probpostedbyme",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setPostedByMeCount(countList.get(0));
		
		myProblemsCountVO.setCommntByMecount(getCommentedByMeProbCount(myProblemsCountVO.getUserId()));
      }catch(Exception e){
    	  Log.error("Exception Occured in getUserRelatedProblemsCountForUser() Method , Exception - "+e);
      }
	}
    
  //to get required problems count for not logged user
    private void getUserRelatedProblemsCountForNotLogged(MyProblemsCountVO myProblemsCountVO){
     try{
    	List<Long> countList = userProblemDAO.getMyProblemsCount(getUserRelatedProblemsCountQuery("allpublicprob",myProblemsCountVO.getUserId()));
		myProblemsCountVO.setAllPublicProbcount(countList.get(0));
     }catch(Exception e){
   	  Log.error("Exception Occured in getUserRelatedProblemsCountForUser() Method , Exception - "+e);
     }
	}
    
    // to get query string for different conditions
    private String getUserRelatedProblemsCountQuery(String type,Long userId){
    	String query = "";
    	if(type.equalsIgnoreCase("myprivateprob"))
    		query = " where model.user.userId = "+userId+" and model.isOwner = '"+IConstants.TRUE+"' and model.visibility.visibilityId = 2 ";   	
    	else if(type.equalsIgnoreCase("mypublicprob"))
    		query = " where model.user.userId = "+userId+" and model.isOwner = '"+IConstants.TRUE+"' and model.visibility.visibilityId = 1 ";
    	else if(type.equalsIgnoreCase("probtakenbyme"))
    		query = " where model.user.userId = "+userId+" and model.isOwner = '"+IConstants.FALSE+"'  ";
    	else if(type.equalsIgnoreCase("probpostedbyme"))
    		query = " where model.user.userId = "+userId+" and model.isOwner = '"+IConstants.TRUE+"'  ";
    	else if(type.equalsIgnoreCase("allpublicprob"))
    		query = " where model.isOwner = '"+IConstants.TRUE+"' and model.visibility.visibilityId = 1  ";
    	return query;
    }
    
    // to get commented by user ,problems count
    private Long getCommentedByMeProbCount(Long userId){
    	Long count = 0l;
     try{
    	List<Long> problemIdsList = problemCommentsDAO.getAllProblemsCommentedByMe(userId);
    	problemIdsList.add(0l);
    	count = userProblemDAO.getCommentedByMeProbCount(userId,problemIdsList);
     }catch(Exception e){
    	 Log.error("Exception Occured in getCommentedByMeProbCount() Method , Exception - "+e);
     }
    	return count;
    }
    
 // to get commented by user, problemsIds
    private List<Long> getCommentedByMeProblemIds(Long userId){
    	List<Long> problemIds = new ArrayList<Long>();
     try{
    	List<Long> problemIdsList = problemCommentsDAO.getAllProblemsCommentedByMe(userId);
    	problemIdsList.add(0l);
    	problemIds = userProblemDAO.getCommentedByMeProblemIds(userId,problemIdsList);
     }catch(Exception e){
    	 Log.error("Exception Occured in getCommentedByMeProblemIds() Method , Exception - "+e);
     }
    	return problemIds;
    }
    
*/}
