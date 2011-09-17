package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemClassificationVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.GenericException;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ProblemsCountByStatusComparator;


public class ProblemManagementReportService implements
		IProblemManagementReportService {
	private static final Logger log = Logger.getLogger("ProblemManagementReportService.class");
	private IProblemHistoryDAO problemHistoryDAO = null;	
	private IAssignedProblemProgressDAO assignedProblemProgressDAO = null;
	private IProblemStatusDAO problemStatusDAO = null;
	private IRegistrationDAO registrationDAO = null;
	private IProblemExternalSourceDAO problemExternalSourceDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private IInfluencingPeopleDAO influencingPeopleDAO;
	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private List result = null;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDateService dateService;
	private IProblemDAO problemDAO;
	private TransactionTemplate transactionTemplate;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IProblemLocationDAO problemLocationDAO;
	private IProblemManagementService problemManagementService;
	private IBoothDAO boothDAO;
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;
	private IDataApprovalService dataApprovalService;
	private CadreManagementService cadreManagementService;
	
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IDataApprovalService getDataApprovalService() {
		return dataApprovalService;
	}

	public void setDataApprovalService(IDataApprovalService dataApprovalService) {
		this.dataApprovalService = dataApprovalService;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IProblemLocationDAO getProblemLocationDAO() {
		return problemLocationDAO;
	}

	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}

	public IDateService getDateService() {
		return dateService;
	}

	public void setDateService(IDateService dateService) {
		this.dateService = dateService;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	

	public IProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			IProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}

	public IProblemSourceScopeConcernedDepartmentDAO getProblemSourceScopeConcernedDepartmentDAO() {
		return problemSourceScopeConcernedDepartmentDAO;
	}

	public void setProblemSourceScopeConcernedDepartmentDAO(
			IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO) {
		this.problemSourceScopeConcernedDepartmentDAO = problemSourceScopeConcernedDepartmentDAO;
	}

	public IProblemStatusDAO getProblemStatusDAO() {
		return problemStatusDAO;
	}


	public void setProblemStatusDAO(IProblemStatusDAO problemStatusDAO) {
		this.problemStatusDAO = problemStatusDAO;
	}


	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}


	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}


		public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}


	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}


	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}


	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public ICadreProblemDetailsDAO getCadreProblemDetailsDAO() {
		return cadreProblemDetailsDAO;
	}

	public void setCadreProblemDetailsDAO(
			ICadreProblemDetailsDAO cadreProblemDetailsDAO) {
		this.cadreProblemDetailsDAO = cadreProblemDetailsDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	

		/**
		 * This method takes hamletId,registrationId and taskType and generates a report of problems for the
		 * selected Tehsil by location-wise,department-wise as well as  status-wise.
		 * @author Ravi Kiran.Y
		 */
		public List<ProblemBeanVO> getTehsilProblemsInfo(Long tehsilId,Long registrationId,String taskType) {
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			try{
			/*
			 * Modified in order to remove hard coding of status
			 * Please check previous version to know exact modifications
			 * @Author Ravi Kiran.Y
			 * @Date 29-09-10
			 * Starts from here.. 	
			 */
				
				if(taskType!=null && taskType!=""){
			//Ends here...
				try{
					//result = problemHistoryDAO.findProblemsByStatusForALocationsByTehsilId(tehsilId,taskType,registrationId);
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Exception Raised--->"+e);
				return null;			
			}
			}
			else{
				try{
				//result = problemHistoryDAO.findProblemsForALocationsByTehsilId(tehsilId,registrationId);
				}catch(Exception e){
						e.printStackTrace();
						System.out.println("Exception Raised--->"+e);
					return null;			
				}
			}
			//problemBeanVO = populateProblemInfo(result,registrationId,taskType,null);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception Raised--->"+e);
			return null;			
		}
			return problemBeanVO;
		}
		
		/**
		 * This method takes hamletId,registrationId and taskType and generates a report of problems for the
		 * selected Constituency by location-wise,department-wise as well as  status-wise by grouping problems
		 * based on department or cadre.
		 * @author Sai Krishna
		 */
		public List<ProblemClassificationVO> getProblemsInfoBasedOnLocation(Long locationId,Long userId,Long status, Long regionScope,Long deptId,Boolean groupCadre,Boolean groupDept) {
			
			
			if(log.isDebugEnabled())
				log.debug(" Started Executing Method getProblemsInfoBasedOnLocation ..");
			
			List<ProblemClassificationVO> problemsDetilsList = new ArrayList<ProblemClassificationVO>();
			ProblemClassificationVO exceptionResult = new ProblemClassificationVO();
			
			try{
				
				Map<String,List<ProblemBeanVO>> problemsListByClassification = new HashMap<String,List<ProblemBeanVO>>();
				String model = null,idToCompare = null;
				Long id = 0L;
				
				//Obtaining problem location details
				Map<String,String> locationDetailsMap = getProblemLocationDetails(regionScope,locationId);
				if(!locationDetailsMap.isEmpty()){
					
					model       = locationDetailsMap.get(IConstants.MODEL);
					idToCompare = locationDetailsMap.get(IConstants.MODEL_ID);
					id          = Long.parseLong(locationDetailsMap.get(IConstants.ID));
					
				}
				
				
				//Get Problems By Input Parameters
				List<ProblemHistory> result = problemHistoryDAO.findProblemsForSelectedSearchOptions(id,status,userId,model,idToCompare,deptId,groupCadre,groupDept);
				
				//Get Problems By Classification
				problemsListByClassification = getProblemsMapByClassification(result,groupCadre,groupDept);
				
				//Process the map and set to vo
				if(!problemsListByClassification.isEmpty()){
					
					Set<String> classificationList = problemsListByClassification.keySet();
					for(String classification:classificationList){
						
						ProblemClassificationVO problemClassificatn = new ProblemClassificationVO();
						
						problemClassificatn.setClassificationType(classification);
						problemClassificatn.setProblemsList(problemsListByClassification.get(classification));
						
						problemsDetilsList.add(problemClassificatn);
					}
				}
				
			}catch(Exception ex){
				
			   log.error("Exception Raised In getProblemsInfoBasedOnLocation Method :" + ex);
			   
			   exceptionResult.setExceptionEncountered(ex);
			   exceptionResult.setExceptionMsg(ex.getMessage());
			   exceptionResult.setResultCode(ResultCodeMapper.FAILURE);
			   
			   ex.printStackTrace();
			}
					
		 return problemsDetilsList;
		}
		
		private Map<String,List<ProblemBeanVO>> getProblemsMapByClassification(List<ProblemHistory> problemHistoryList,Boolean groupCadre,Boolean groupDept) throws Exception{
			
			if(log.isDebugEnabled())
				log.debug(" Classifying The Problems ..");
			
			Map<String,List<ProblemBeanVO>> problemClassificationMap = new HashMap<String,List<ProblemBeanVO>>();
			String locationStr = "";
			
			//Process The ProblemsHistory List
			for(ProblemHistory problemInfo:problemHistoryList){
				
				String category = "";
				ProblemBeanVO problemBeanVO = new ProblemBeanVO();
				Long impactValue = problemInfo.getProblemLocation().getProblemImpactLevelValue();
				Long impactLevel = problemInfo.getProblemLocation().getProblemImpactLevel().getRegionScopesId();
				problemBeanVO.setProblem(problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
				problemBeanVO.setDescription(problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
				Date reportedDate = problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
				problemBeanVO.setReportedDate(sdf.format(reportedDate));
				Date existingFrom = problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom();
				problemBeanVO.setExistingFrom(sdf.format(existingFrom));
				
				switch (impactLevel.intValue()) {
	            case 2:  
	        	{
	        		State state = stateDAO.get(impactValue);
	        		locationStr = state.getStateName();
	        		break;
	        	}
	            case 3:
	            {
	            	District district = districtDAO.get(impactValue);
	            	locationStr = district.getDistrictName() + ", "+ district.getState().getStateName() ;	            	
	            	break;
	            }
	            case 4: {
	            	Constituency constituency = constituencyDAO.get(impactValue);
					
					if(IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency.getElectionScope().getElectionType().getElectionType()))
					{	
						locationStr = constituency.getName()+ " " +constituency.getState().getStateName();						
					} else 
					{
						locationStr = constituency.getName()+", "+ constituency.getDistrict().getDistrictName()+"(Dt.)"+", "+constituency.getState().getStateName();						
					}				
	            	break;
	            }
	            case 5: 
	            {
	            	Tehsil tehsil = tehsilDAO.get(impactValue);
	            	locationStr = tehsil.getTehsilName()+ " (Mandal), "+ setConstDistStateTOResult(tehsil.getTehsilId());
					break;
	            }            
	            case 6:
	            {
	            	Hamlet hamlet = hamletDAO.get(impactValue);
	            	locationStr = hamlet.getHamletName()+"(Village, )" +setConstDistStateTOResult(hamlet.getTownship().getTehsil().getTehsilId());
	            	break;
	            }
	            case 7:
	            {
	            	LocalElectionBody localBody = localElectionBodyDAO.get(impactValue);
	            	locationStr = localBody.getName()+ "-" +localBody.getElectionType().getElectionType() +", "+localBody.getDistrict().getDistrictName()+"(Dt.)"+", " +localBody.getDistrict().getState().getStateName();
	            	break;
	            }
	            case 8:
	            {
	            	Constituency ward = constituencyDAO.get(impactValue);
	            	locationStr = ward.getName()+", "+ ward.getLocalElectionBody().getName()+"-" +ward.getLocalElectionBody().getElectionType().getElectionType()+ ", "+ward.getLocalElectionBody().getDistrict().getDistrictName()+", "+ ward.getLocalElectionBody().getDistrict().getState().getStateName();
	            	break;
	            }
	            case 9:
	            {
	            	Booth booth = boothDAO.get(impactValue);
	            	if(booth.getTehsil()!= null)
	            	{
	            		locationStr = booth.getTehsil().getTehsilName() + ", "+booth.getTehsil().getTehsilName()+setConstDistStateTOResult(booth.getTehsil().getTehsilId());	            		           		
	            	}else if(booth.getLocalBody() != null)
	            	{            		            		
	            		locationStr = booth.getLocalBody().getName()+booth.getLocalBody().getDistrict().getDistrictName()+booth.getLocalBody().getDistrict().getState().getStateName();	            		
	            	}
	            	
	            	break;
	            }
	            default: System.out.println("Invalid Scope.");break;
	        }
				problemBeanVO.setProblemLocation(locationStr);
				
				problemBeanVO.setStatus(problemInfo.getProblemStatus().getStatus());
				
				
				if(groupDept){
					
					problemBeanVO.setDepartment(((AssignedProblemProgress)problemInfo.getAssignedProblemProgresses().toArray()[0]).getProblemSourceScopeConcernedDepartment().getDepartment());
					problemBeanVO.setDesignation(((AssignedProblemProgress)problemInfo.getAssignedProblemProgresses().toArray()[0]).getDesignation());
					
					category = problemBeanVO.getDepartment().trim();
				}
				
				if(groupCadre){
					
					problemBeanVO.setCadreName(((CadreProblemDetails)problemInfo.getCadreProblemDetails().toArray()[0]).getCadre().getLastName());
					problemBeanVO.setCadreId(((CadreProblemDetails)problemInfo.getCadreProblemDetails().toArray()[0]).getCadre().getCadreId());
					
					category = problemBeanVO.getCadreName().trim();
				}
								
						
				//Set Data TO Map
				if(problemClassificationMap.isEmpty() || !problemClassificationMap.containsKey(category)){
					
					List<ProblemBeanVO> problemsVOList = new ArrayList<ProblemBeanVO>();
					problemsVOList.add(problemBeanVO);
					
					problemClassificationMap.put(category, problemsVOList);
					
				}else if(problemClassificationMap.containsKey(category)){
					
					List<ProblemBeanVO> problemsVOList = problemClassificationMap.get(category);
					problemsVOList.add(problemBeanVO);
					
					problemClassificationMap.put(category, problemsVOList);
				}

			}
			
			
		 return problemClassificationMap;
		}
		
		/**
		 * Method Gets Problem Location Details And Sets To Map
		 * @param regionScope
		 * @return
		 */
		@SuppressWarnings("unchecked")
		private Map<String,String> getProblemLocationDetails(Long regionScope,Long locationId) throws Exception{
			
			if(log.isDebugEnabled())
				log.debug(" Obtaining Problem Location Details ..");
			
			Map<String,String> locationDetailsMap = new HashMap<String,String>();
			String model = null,idToCompare = null;
			Long id = locationId;
			
			locationDetailsMap.put(IConstants.ID, id.toString());
			
			switch (regionScope.intValue()) {
	            case 2:  
	        	{
	        		model = "state";
					idToCompare = "stateId";
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					
					break;
	        	}
	            case 3:
	            {
	            	model = "district";
					idToCompare = "districtId";
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					
	            	break;
	            }
	            case 4:
	            case 8:
	            {
	            	model = "constituency";
					idToCompare = "constituencyId";
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					
	            	break;
	            }
	            case 5: 
	            {
	            	model = "tehsil";
					idToCompare = "tehsilId";
					id = new Long(locationId.toString().substring(1));
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					
	            	break;
	            }            
	            case 6:
	            {
	            	model = "hamlet";
					idToCompare = "hamletId";
					id = new Long(locationId.toString().substring(1));
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					locationDetailsMap.put(IConstants.ID, id.toString());
					
	            	break;
	            }
	            case 7:	            	
	            {
	            	model = "localElectionBody";
					idToCompare = "localElectionBodyId";
					//retrieve local election body id from assembly local election body
					List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1)));
					Object object = (Object)localElectionBodies.get(0);
					id = (Long)object;	
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					locationDetailsMap.put(IConstants.ID, id.toString());
					
	            	break;
	            }
	            case 9:
	            {
	            	model = "booth";
					idToCompare = "boothId";
					
					locationDetailsMap.put(IConstants.MODEL, model);
					locationDetailsMap.put(IConstants.MODEL_ID, idToCompare);
					
	            	break;
	            }	            
	            default: System.out.println("Invalid Scope.");break;
           }
			
		 return locationDetailsMap;
		}

		/**
		 * This method takes hamletId,registrationId and taskType and generates a report of problems for the
		 * selected Constituency by location-wise,department-wise as well as  status-wise.
		 * @author Ravi Kiran.Y
		 */
		public List<ProblemBeanVO> getProblemsInfoBasedOnLocation(Long locationId,Long userId,Long status, Long regionScope,Long deptId) {
			List<ProblemBeanVO> problemBeanVOList = new ArrayList<ProblemBeanVO>();			
			String model = null,idToCompare = null;			
			try{
				Long id = locationId;
				String locationStr = "";
				switch (regionScope.intValue()) {
	            case 2:  
	        	{
	        		model = "state";
					idToCompare = "stateId";
	        		break;
	        	}
	            case 3:
	            {
	            	model = "district";
					idToCompare = "districtId";
	            	break;
	            }
	            case 4:
	            case 8:
	            {
	            	model = "constituency";
					idToCompare = "constituencyId";				
	            	break;
	            }
	            case 5: 
	            {
	            	model = "tehsil";
					idToCompare = "tehsilId";
					id = new Long(locationId.toString().substring(1));
	            	break;
	            }            
	            case 6:
	            {
	            	model = "hamlet";
					idToCompare = "hamletId";
					id = new Long(locationId.toString().substring(1));
	            	break;
	            }
	            case 7:	            	
	            {
	            	model = "localElectionBody";
					idToCompare = "localElectionBodyId";
					//retrieve local election body id from assembly local election body
					List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1)));
					Object object = (Object)localElectionBodies.get(0);
					id = (Long)object;					
	            	break;
	            }
	            case 9:
	            {
	            	model = "booth";
					idToCompare = "boothId";
	            	break;
	            }	            
	            default: System.out.println("Invalid Scope.");break;
	        }					
				
			List<ProblemHistory> result = problemHistoryDAO.findProblemsForSelectedSearchOptions(id,status,userId,model,idToCompare,deptId,false,false);
				
			ProblemBeanVO problemBeanVO = null;
			for(ProblemHistory problemInfo:result){
				problemBeanVO = new ProblemBeanVO();
				Long impactValue = problemInfo.getProblemLocation().getProblemImpactLevelValue();
				Long impactLevel = problemInfo.getProblemLocation().getProblemImpactLevel().getRegionScopesId();
				problemBeanVO.setProblem(problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem());
				problemBeanVO.setDescription(problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
				Date reportedDate = problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn();
				problemBeanVO.setReportedDate(sdf.format(reportedDate));
				Date existingFrom = problemInfo.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom();
				problemBeanVO.setExistingFrom(sdf.format(existingFrom));
				
				switch (impactLevel.intValue()) {
	            case 2:  
	        	{
	        		State state = stateDAO.get(impactValue);
	        		locationStr = state.getStateName();
	        		break;
	        	}
	            case 3:
	            {
	            	District district = districtDAO.get(impactValue);
	            	locationStr = district.getDistrictName() + ", "+ district.getState().getStateName() ;	            	
	            	break;
	            }
	            case 4: {
	            	Constituency constituency = constituencyDAO.get(impactValue);
					
					if(IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency.getElectionScope().getElectionType().getElectionType()))
					{	
						locationStr = constituency.getName()+ " " +constituency.getState().getStateName();						
					} else 
					{
						locationStr = constituency.getName()+", "+ constituency.getDistrict().getDistrictName()+"(Dt.)"+", "+constituency.getState().getStateName();						
					}				
	            	break;
	            }
	            case 5: 
	            {
	            	Tehsil tehsil = tehsilDAO.get(impactValue);
	            	locationStr = tehsil.getTehsilName()+ " (Mandal), "+ setConstDistStateTOResult(tehsil.getTehsilId());
					break;
	            }            
	            case 6:
	            {
	            	Hamlet hamlet = hamletDAO.get(impactValue);
	            	locationStr = hamlet.getHamletName()+"(Village, )" +setConstDistStateTOResult(hamlet.getTownship().getTehsil().getTehsilId());
	            	break;
	            }
	            case 7:
	            {
	            	LocalElectionBody localBody = localElectionBodyDAO.get(impactValue);
	            	locationStr = localBody.getName()+ "-" +localBody.getElectionType().getElectionType() +", "+localBody.getDistrict().getDistrictName()+"(Dt.)"+", " +localBody.getDistrict().getState().getStateName();
	            	break;
	            }
	            case 8:
	            {
	            	Constituency ward = constituencyDAO.get(impactValue);
	            	locationStr = ward.getName()+", "+ ward.getLocalElectionBody().getName()+"-" +ward.getLocalElectionBody().getElectionType().getElectionType()+ ", "+ward.getLocalElectionBody().getDistrict().getDistrictName()+", "+ ward.getLocalElectionBody().getDistrict().getState().getStateName();
	            	break;
	            }
	            case 9:
	            {
	            	Booth booth = boothDAO.get(impactValue);
	            	if(booth.getTehsil()!= null)
	            	{
	            		locationStr = booth.getTehsil().getTehsilName() + ", "+booth.getTehsil().getTehsilName()+setConstDistStateTOResult(booth.getTehsil().getTehsilId());	            		           		
	            	}else if(booth.getLocalBody() != null)
	            	{            		            		
	            		locationStr = booth.getLocalBody().getName()+booth.getLocalBody().getDistrict().getDistrictName()+booth.getLocalBody().getDistrict().getState().getStateName();	            		
	            	}
	            	
	            	break;
	            }
	            default: System.out.println("Invalid Scope.");break;
	        }
				problemBeanVO.setProblemLocation(locationStr);
				
				problemBeanVO.setStatus(problemInfo.getProblemStatus().getStatus());
				
				if("4".equalsIgnoreCase(status.toString())){
					problemBeanVO.setDepartment(((AssignedProblemProgress)problemInfo.getAssignedProblemProgresses().toArray()[0]).getProblemSourceScopeConcernedDepartment().getDepartment());
					problemBeanVO.setDesignation(((AssignedProblemProgress)problemInfo.getAssignedProblemProgresses().toArray()[0]).getDesignation());
				}					
						
				problemBeanVOList.add(problemBeanVO);
			}
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception Raised--->"+e);					
			}		
			return problemBeanVOList;
		}
		
		private String setConstDistStateTOResult(Long tehsilId)
		{
			List stateDistConstMandal = delimitationConstituencyMandalDAO.getStateDistConstituencyMandalByMandalID(tehsilId);
			Object[] objVO = (Object[]) stateDistConstMandal.get(0);
			String str = objVO[3].toString()+ " (Dt.), " + objVO[1].toString();
			
			return str;
		}
		
		/**
		 * This method is used for designing ProblemManagementReport based on
		 * user selected Location.
		 * @author Ravi Kiran.Y
		 */
		@SuppressWarnings("unchecked")
		public List<ProblemBeanVO> populateProblemInfo(List list,Long registrationId,Long taskType,String status){
			
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();
			List<Registration> regUser = new ArrayList<Registration>();
			List<ProblemExternalSource> extRegUser = new ArrayList<ProblemExternalSource>();
			Long problemId=0l;
			String dateConversion=null;
			String departmentName = null;
			Long userId = 0l;
			if(log.isDebugEnabled())
				log.debug("Entered into populateProblemInfo() method....");
			try{
				if(list != null)
				for(int i=0;i<list.size();i++){				
					Object[] parms = (Object[]) list.get(i);
					ProblemBeanVO problemBean = new ProblemBeanVO();
					problemId = Long.parseLong(parms[4].toString());
					if(!(parms[0].toString().equalsIgnoreCase("NEW") || parms[0].toString().equalsIgnoreCase("CLASSIFY"))){
						if(log.isDebugEnabled())
							log.debug("Making a DAO call to assignedProblemProgressDAO.findProblemsForAHamletByHistoryId() "); 
						List result =  assignedProblemProgressDAO.findProblemsForAHamletByHistoryId(problemId);
						if(result == null){
							if(log.isDebugEnabled())
								log.debug("0 rows have been retrived......");
						}
						else if(parms[0].toString().equalsIgnoreCase("ASSIGNED")){
							for(int j=0;j<result.size();j++){
								Object[] problemData = (Object[]) result.get(j);
								departmentName = problemData[0].toString();
								problemBean.setDepartment(problemData[0].toString());
								problemBean.setDepartmentConcernedPersonName("Not Assigned.");
								problemBean.setUpdatedDate("Not Assigned.");
								problemBean.setContactNo("Not Assigned.");
								problemBean.setDesignation("Not Assigned.");
								}
						}
						else{
							if(log.isDebugEnabled())
								log.debug("Setting data into ProblemBeanVO......");
							for(int j=0;j<result.size();j++){
								Object[] problemData = (Object[]) result.get(j);
								departmentName = problemData[0].toString();
								problemBean.setDepartment(problemData[0].toString());
								problemBean.setDepartmentConcernedPersonName(problemData[1].toString());
								problemBean.setUpdatedDate(problemData[1].toString());
								if(!(problemData[3]==null && problemData[4]==null)){
									problemBean.setContactNo(problemData[3].toString());
									problemBean.setDesignation(problemData[4].toString());
								}
								else{
									problemBean.setContactNo(problemData[3].toString());
									problemBean.setDesignation(problemData[4].toString());
								}
							}
						}
					}else{
	
						problemBean.setContactNo("Not Assigned.");
						problemBean.setDesignation("Not Assigned.");
						problemBean.setDepartment("Not Assigned.");
						problemBean.setDepartmentConcernedPersonName("Not Assigned.");
					}
					
					if(log.isDebugEnabled())
						log.debug("Setting data into ProblemBeanVO......");
					problemBean.setStatus(parms[0].toString());
					if(status != null && status.equals(IConstants.PROBLEMS_MANAGEMENT)){
						Long impactValue = (Long)parms[11];
						Long impactId = (Long)parms[1];
						problemBean.setHamlet(problemManagementService.getLocationDetails(impactId, impactValue));
					}
					else
						problemBean.setHamlet(parms[1].toString());
					dateConversion = dateConversion(parms[2].toString());
					problemBean.setExistingFrom(dateConversion); 	
					problemBean.setName(parms[3].toString());
					problemBean.setProblemId(problemId);//Added ProblemId in this version Ravi Kiran.Y
					if(parms[5]==null){
						problemBean.setComments("No Comments received..");
					}
					else 
						problemBean.setComments(parms[5].toString());
					
					userId = new Long(parms[10].toString());
					if(userId!=0L){
						if(parms[6]==null){
							regUser = registrationDAO.findByUserRegistrationId(userId);					
							for(Registration registerdUser : regUser){
								problemBean.setPostedPersonName(registerdUser.getFirstName());
								problemBean.setPhone(registerdUser.getMobile());
								problemBean.setMobile(registerdUser.getPhone());
								problemBean.setEmail(registerdUser.getEmail());
								problemBean.setAddress(registerdUser.getAddress());			
							}
						}
						else {
							extRegUser = problemExternalSourceDAO.findByProblemExternalSourceId(Long.parseLong(parms[6].toString()));
							for(ProblemExternalSource problemSource : extRegUser){
								problemBean.setPostedPersonName(problemSource.getName());
								problemBean.setPhone(problemSource.getMobile());
								problemBean.setMobile(problemSource.getTelePhone());
								problemBean.setEmail(problemSource.getEmail());
								problemBean.setAddress(problemSource.getAddress());
							}
						}
					}
					else{
					}
					problemBean.setDescription(parms[7].toString());
					problemBean.setProblemLocationId(Long.parseLong(parms[9].toString()));				
									
					if(parms[8]==null)
						problemBean.setUpdatedDate("No Updations..");
					else {
						dateConversion = dateConversion(parms[8].toString());
						problemBean.setUpdatedDate(dateConversion); 
					}			
					/*
					 * Modified in order to remove hard coding of status
					 * Please check previous version to know exact modifications
					 * @Author Ravi Kiran.Y
					 * @Date 29-09-10
					 * Starts from here.. 	
					 */
					if(taskType==null){
					//Ends here..
						/*if(taskType.equalsIgnoreCase(departmentName)){
							if( !(problemBean.getDepartment().equalsIgnoreCase("Not Assigned."))){
								problemBeanVO.add(problemBean);
							}
						}*/
					}
					else{
						problemBeanVO.add(problemBean);	
					}				
				}	
			}catch(Exception e){
				e.printStackTrace();
				if(log.isDebugEnabled())
					log.debug("Exception Raised--->"+e);
				return null;
			}
			
			return problemBeanVO;
		}
		/*
		 * To convert date which is in yyyy-MM-dd format to dd-MM-yyyy format	
		 */
		public String dateConversion(String idate){
			String convertedDated=null;
			SimpleDateFormat sdfInput =  
		        new SimpleDateFormat (  "yyyy-MM-dd"  ) ;  
		     SimpleDateFormat sdfOutput =  
		        new SimpleDateFormat  (  "dd-MM-yyyy"  ) ;  		  
		  
		     Date date;
			try {
				date = sdfInput.parse (idate);
				convertedDated = sdfOutput.format(date).toString(); 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return convertedDated; 			
		}

		/*
		 * Retrives all the history regarding the selected problem and sets them in to ProblemHistoryVO.
		 */
		public List<ProblemHistoryVO> getCompleteDetailsForAProblem(Long problemLocationId) {
			List<ProblemHistoryVO> problemHistory = new ArrayList<ProblemHistoryVO>(0);
			try {
			List result = problemHistoryDAO.findCompleteProblems(problemLocationId);
			for(int j=0;j<result.size();j++){
				Object[] problemData = (Object[])result.get(j);
				ProblemHistoryVO problemHistoryVO = new ProblemHistoryVO();
				problemHistoryVO.setProblemHistoryId(Long.parseLong(problemData[0].toString()));
				if(problemData[1] != null){
					problemHistoryVO.setComments(problemData[1].toString());
				}	
				else{
					problemHistoryVO.setComments("--");
				}
				problemHistoryVO.setMovedDate(DateService.timeStampConversion(problemData[2].toString()));
				if(!(problemData[3] == null)){
					problemHistoryVO.setIsDelete(problemData[3].toString());
				}
				else{
					problemHistoryVO.setIsDelete("--");
				}
				problemHistoryVO.setStatus(problemData[5].toString());
				problemHistory.add(problemHistoryVO);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return problemHistory;
		}
		
		/*
		 * To View the Complete Life Cycle Of A Problem From Start to Current Status
		 */
		
		public ProblemBeanVO getProblemHistoryInfo(Long problemLocationId) {
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			ProblemHistory problemHistory = null;
			List<ProblemHistoryVO> problemHistories = new ArrayList<ProblemHistoryVO>(0);
			try{
				List<ProblemHistory> problemsInHistory = problemHistoryDAO.findProblemHistoryByProblemLocation(problemLocationId);
				if(problemsInHistory.size() > 0){
					problemHistory = problemsInHistory.get(0);
					problemBeanVO.setProblem(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getProblem()));
					problemBeanVO.setDescription(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getDescription()));
					//problemBeanVO.setHamlet(checkForNull(problemHistory.getProblemLocation().getHamlet().getHamletName()));
					problemBeanVO.setHamlet(problemManagementService.getLocationDetails(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(), problemHistory.getProblemLocation().getProblemImpactLevelValue()));
					problemBeanVO.setExistingFrom(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom().toString()));
					problemBeanVO.setReportedDate(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn().toString()));
					
					if(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource() != null){
						/*problemBeanVO.setPostedPersonName(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getName()));
						problemBeanVO.setPhone(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getMobile()));
						problemBeanVO.setAddress(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getAddress()));
						problemBeanVO.setEmail(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getEmail()));	*/
						problemBeanVO.setPostedPersonName(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getFirstName()));
						problemBeanVO.setPhone(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getMobile()));
						problemBeanVO.setAddress(checkForNull(null));
						problemBeanVO.setEmail(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getEmail()));
						
					}else{
						problemBeanVO.setPostedPersonName(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getFirstName()+" "+problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getLastName()));
						problemBeanVO.setPhone(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getMobile()));
						problemBeanVO.setAddress(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getAddress()));
						problemBeanVO.setEmail(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getUser().getEmail()));
					}
					
					List assignedProbs = assignedProblemProgressDAO.getAssignedProblemsProgressByLocation(problemLocationId);
					
					if(assignedProbs.size() > 0 ){
						Object[] values = ((List<Object[]>)assignedProbs).get(0);
						problemBeanVO.setIsAssigned(true);
						problemBeanVO.setDepartment(checkForNull(values[0]));
						problemBeanVO.setDepartmentConcernedPersonName(checkForNull(values[1]));
						problemBeanVO.setDepartmentConcernedPersonPhoneNumber(checkForNull(values[2]));
						problemBeanVO.setDesignation(checkForNull(values[3]));
						problemBeanVO.setUpdatedDate(checkForNull(values[4]));
					}else
						problemBeanVO.setIsAssigned(false);
					
				}else{
					throw new Exception("No Data Available For Problem ");
				}
			}catch(Exception ex){
				problemBeanVO.setExceptionEncountered(ex);
				ex.printStackTrace();
			}
			
			List result = problemHistoryDAO.findCompleteProblems(problemLocationId);
			for(int j=0;j<result.size();j++){
				Object[] problemData = (Object[])result.get(j);
				ProblemHistoryVO problemHistoryVO = new ProblemHistoryVO();
				problemHistoryVO.setProblemHistoryId(Long.parseLong(problemData[0].toString()));
				if(problemData[1] != null){
					problemHistoryVO.setComments(problemData[1].toString());
				}	
				else{
					problemHistoryVO.setComments("--");
				}
				problemHistoryVO.setMovedDate(DateService.timeStampConversion(problemData[2].toString()));
				if(!(problemData[3] == null)){
					problemHistoryVO.setIsDelete(problemData[3].toString());
				}
				else{
					problemHistoryVO.setIsDelete("--");
				}
				problemHistoryVO.setStatus(problemData[5].toString());
				problemHistories.add(problemHistoryVO);
			}
			
			problemBeanVO.setProblemHistories(problemHistories);
			return problemBeanVO;
		}
		
		private String checkForNull(Object value){
			if(value != null)
				return value.toString();
			return "--";			
		}
		
		
			
		public List<ProblemBeanVO> getProblemsPostedByStatusAndDates(Long userId,String fromDate, String toDate, Long statusId, String accessType, Long accessValue){
			
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
			List problemsRawData = null;
			ProblemBeanVO problemBeanVO = null;
			List<ProblemBeanVO> problems = new ArrayList<ProblemBeanVO>();
			String tehsilIds = "";
			try{
				//tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
				if(statusId.equals(0L))
					problemsRawData = problemHistoryDAO.findProblemsByDateAndLocation(userId, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));
				else
					problemsRawData = problemHistoryDAO.findProblemsByStatusDateAndLocation(userId, statusId, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));
					//problemsRawData = problemHistoryDAO.findProblemsByDateAndLocation(tehsilIds, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));
				
					//problemsRawData = problemHistoryDAO.findProblemsByStatusDateAndLocation(tehsilIds, statusId, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));	
					

			}catch(Exception ex){
				log.debug("Exception Raised While Formating Date:"+ex);
			}
			
			for(Object[] values:(List<Object[]>)problemsRawData){
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setProblemLocationId((Long)values[0]);
				problemBeanVO.setProblem(values[1].toString());
				problemBeanVO.setDescription(values[2].toString());
				//problemBeanVO.setHamlet(values[3].toString());
				Long impactLevelId = (Long)values[3];
				Long impactLevelValue = (Long)values[8];
				problemBeanVO.setHamlet(problemManagementService.getLocationDetails(impactLevelId,impactLevelValue));
				
				problemBeanVO.setProblemSourceScope(values[4].toString());
				problemBeanVO.setProblemAndProblemSourceId((Long)values[5]);
				problemBeanVO.setStatus(values[6].toString());
				problemBeanVO.setExistingFrom(DateService.timeStampConversionToDDMMYY(values[7].toString()));
				problems.add(problemBeanVO);
			}
			
			return problems;
			
		}
		
		public String getCommaSeperatedTehsilIdsForAccessType(String accessType, Long accessValue){
			StringBuffer tehsilIds = new StringBuffer();
			StringBuffer assemblyIds = new StringBuffer();
			List<Tehsil> mandals = null;
			if("MLA".equalsIgnoreCase(accessType)){
				List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(accessValue);
				Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
				mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);

			}else if("STATE".equalsIgnoreCase(accessType))
				mandals = tehsilDAO.findByState(accessValue);
				else if("DISTRICT".equalsIgnoreCase(accessType))
					mandals = tehsilDAO.findByDistrict(accessValue);
				else if("MP".equalsIgnoreCase(accessType)){
					List assemblies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(accessValue);
					if(assemblies.size() >0){
						for(Object[] values:(List<Object[]>)assemblies)
							assemblyIds.append(",").append(values[0].toString());
						mandals = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituencies(assemblyIds.toString().substring(1));						
					}
				}

			for(Tehsil tehsil : mandals)
				tehsilIds.append(",").append(tehsil.getTehsilId());
			
			if(tehsilIds.length() > 0)
				return tehsilIds.toString().substring(1);
			else
				return tehsilIds.toString();
		}
		
		@SuppressWarnings("unchecked")
		public List<InfluencingPeopleVO> findInfluencingPeopleInfoInLocation(String accessType, Long accessValue, Long hamletId, String flag,Long userId){
						
			List<InfluencingPeople> impPeople = null;
			
			if(accessType.equals(IConstants.STATE_LEVEL))
			{
				impPeople = influencingPeopleDAO.findByStateId(new Long(accessValue),userId);
			}
			
			if(accessType.equals(IConstants.DISTRICT_LEVEL))
			{
				impPeople = influencingPeopleDAO.findByDistrictId(new Long(accessValue),userId);
			}
			
			if(accessType.equals(IConstants.MLA))
			{
				impPeople = influencingPeopleDAO.findByConstituencyId(new Long(accessValue),userId);
			}
			
			if(accessType.equals(IConstants.MP))
			{
				List assemblies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(new Long(accessValue));
				if(assemblies.size() >0)
				{
					for(Object[] values:(List<Object[]>)assemblies)
					{
						long assemblyId = Long.parseLong((values[0].toString()));
						impPeople = influencingPeopleDAO.findByConstituencyId(new Long(assemblyId),userId);
					}
				}
				
			 }
				
			List<InfluencingPeopleVO> influencies = new ArrayList<InfluencingPeopleVO>();
			InfluencingPeopleVO influencingPeopleVO = null;
			
			if(impPeople != null)
				for(InfluencingPeople people:impPeople){
					String name = "";
					influencingPeopleVO = new InfluencingPeopleVO();
					if(people.getFirstName() != null)
						name += people.getFirstName();
					if(people.getLastName() != null)
						name = name+" "+people.getLastName();
					influencingPeopleVO.setInfluencingPeopleId(people.getInfluencingPeopleId());
					influencingPeopleVO.setPersonName(name);
					influencingPeopleVO.setOccupation(people.getOccupation());
					influencingPeopleVO.setInfluencingRange(people.getInfluencingScope());
					
					if(IConstants.STATE_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(stateDAO.get(new Long(people.getInfluencingScopeValue())).getStateName());
					}
					else if(IConstants.DISTRICT_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(districtDAO.get(new Long(people.getInfluencingScopeValue())).getDistrictName());
					}
					else if(IConstants.CONSTITUENCY_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(constituencyDAO.get(new Long(people.getInfluencingScopeValue())).getName());
					}
					else if(IConstants.MUNCIPALITY_CORPORATION_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(localElectionBodyDAO.get(new Long(people.getInfluencingScopeValue())).getName());
					}
					else if(IConstants.MANDAL_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(tehsilDAO.get(new Long(people.getInfluencingScopeValue())).getTehsilName());
					}
					else if(IConstants.CENSUS_VILLAGE_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(hamletDAO.get(new Long(people.getInfluencingScopeValue())).getHamletName());
					}
					else if(IConstants.WARD.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(constituencyDAO.get(new Long(people.getInfluencingScopeValue())).getName());
					}
					influencingPeopleVO.setCast(people.getCaste());
					influencingPeopleVO.setContactNumber(people.getPhoneNo());
					influencingPeopleVO.setParty(people.getParty().getShortName());
					
					if(people.getUserAddress().getHamlet() != null)
					{
						String localArea = people.getUserAddress().getHamlet().getHamletName();
						localArea = localArea + ", ";
						localArea = localArea + people.getUserAddress().getTehsil().getTehsilName();
						influencingPeopleVO.setLocalArea(localArea);
					}
					else
					{
						String localArea = people.getUserAddress().getWard().getName();
						localArea = localArea + ", ";
						localArea = localArea + people.getUserAddress().getLocalElectionBody().getName();
						influencingPeopleVO.setLocalArea(localArea);
					}
										
					influencies.add(influencingPeopleVO);
				}
			return influencies;
		}
		
		public List<SelectOptionVO> getAllProblemStatusInfo(){
			List<SelectOptionVO> status = new ArrayList<SelectOptionVO>();
			List<ProblemStatus> list = problemStatusDAO.getAll();
			for(ProblemStatus problemStatus:list)
				status.add(new SelectOptionVO(problemStatus.getProblemStatusId(), problemStatus.getStatus()));
			status.add(0, new SelectOptionVO(0l,"All"));
			return status;
		}
		
		public LocationwiseProblemStatusInfoVO getRecentProblemsWithInTheRegion(String accessType, Long accessValue, Long statusId, int limit){
			LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = new LocationwiseProblemStatusInfoVO();
			try{
				List newProblems = new ArrayList();
				String tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
				if(!tehsilIds.equalsIgnoreCase("")){
				newProblems = problemHistoryDAO.findLatestProblemsByMandals(tehsilIds, statusId);
				}
				if(newProblems.size() > limit)
					locationwiseProblemStatusInfoVO.setRecentProblems(getProblemVOsListFromRawData(newProblems.subList(0, limit-1)));
				else
					locationwiseProblemStatusInfoVO.setRecentProblems(getProblemVOsListFromRawData(newProblems));
		}catch(Exception ex){
				ex.printStackTrace();
			}			
			return locationwiseProblemStatusInfoVO;
		}
		
		public List<ProblemBeanVO> getProblemVOsListFromRawData(List problemsRawData){
			ProblemBeanVO problemBeanVO;
			List<ProblemBeanVO> problems = new ArrayList<ProblemBeanVO>();
			for(Object[] values:(List<Object[]>)problemsRawData){
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setProblemLocationId((Long)values[0]);
				problemBeanVO.setProblem(values[1].toString());
				problemBeanVO.setDescription(values[2].toString());
				problemBeanVO.setHamlet(values[3].toString());
				problemBeanVO.setProbSource(values[4].toString());
				problemBeanVO.setProblemAndProblemSourceId((Long)values[5]);
				problemBeanVO.setStatus(values[6].toString());
				problemBeanVO.setExistingFrom(DateService.timeStampConversionToDDMMYY(values[7].toString()));
				problems.add(problemBeanVO);
			}
			return problems;
		}
		
		@SuppressWarnings("unchecked")
		public LocationwiseProblemStatusInfoVO getProblemsStatusCount(Long userId,String accessType, Long accessValue, int limit) {
			LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = new LocationwiseProblemStatusInfoVO();
			List<ProblemsCountByStatus> problemsCountByStatusList = new ArrayList<ProblemsCountByStatus>();
			ProblemsCountByStatus problemsCountByStatusObj = null;
			List problemsCountByStatusRawList = null;
			List problemsByStatusAndDateRawList = null;
			List <Constituency> constituencies = null;
			List<ProblemsCountByStatus> problemsCountByStatusAndDate = new ArrayList<ProblemsCountByStatus>();
			Map<String, Long> probsCountByStatusAndDateMap = new HashMap<String, Long>();
			int totalProbsCount = 0;
			String date = "";
			String status = "";
			Long count;
			GregorianCalendar calendar = new GregorianCalendar();
			SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
			
			try{
				log.debug("Entered in to getProblemsStatusCount method in ProblemManagementReportService");
				
				StringBuffer str = new StringBuffer();
				String constituencyIds = "";
				if("MLA".equals(accessType))
					constituencyIds = accessValue.toString();
				else if("DISTRICT".equalsIgnoreCase(accessType)){
					constituencies = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(accessValue);
					for(Constituency constituency:constituencies)
						str.append(",").append(constituency.getConstituencyId());
					constituencyIds = str.toString().substring(1);
					
				}else if("STATE".equalsIgnoreCase(accessType)){
					constituencies = delimitationConstituencyDAO.getLatestAssemblyConstituenciesInState(accessValue);
					for(Constituency constituency:constituencies)
						str.append(",").append(constituency.getConstituencyId());
					constituencyIds = str.toString().substring(1);
				}else if("MP".equalsIgnoreCase(accessType)){
					List assemblies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(accessValue);
					if(assemblies.size() >0){
						for(Object[] values:(List<Object[]>)assemblies)
							str.append(",").append(values[0].toString());
						constituencyIds = str.toString().substring(1);
					}
				}
				if(constituencyIds.length() > 0)
					problemsCountByStatusRawList = problemHistoryDAO.getProblemsCountInAllStatusByLocation(userId);
					//problemsCountByStatusRawList = problemHistoryDAO.getProblemsCountInAllStatusByLocation(constituencyIds);
				
				for(int i=0;i<problemsCountByStatusRawList.size();i++){
					Object[] parms = (Object[])problemsCountByStatusRawList.get(i);
					problemsCountByStatusObj = new ProblemsCountByStatus();	
					problemsCountByStatusObj.setStatusId(Long.parseLong(parms[0].toString()));
					problemsCountByStatusObj.setStatus(parms[1].toString());
					problemsCountByStatusObj.setCount(Integer.parseInt(parms[2].toString()));
					problemsCountByStatusList.add(problemsCountByStatusObj);
					if(!IConstants.FIXED.equals(parms[1].toString()))
						totalProbsCount += Integer.parseInt(parms[2].toString());
				}
				
				String tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
				//problemsByStatusAndDateRawList = problemHistoryDAO.findLatestProblemsGroupByDatePostedByMandalsAndStatus(tehsilIds, "1,6");
				problemsByStatusAndDateRawList = problemHistoryDAO.findLatestProblemsGroupByDatePostedByMandalsAndStatus(userId,"1,6");
				
				for(Object[] values:(List<Object[]>)problemsByStatusAndDateRawList){
					date = dateFormater.format((Date)values[1]);
					status = values[3].toString();
					count = (Long)values[0];
					problemsCountByStatusObj = new ProblemsCountByStatus();
					problemsCountByStatusObj.setCount(count.intValue());
					problemsCountByStatusObj.setDate(date);
					problemsCountByStatusObj.setStatus(values[3].toString());
					problemsCountByStatusObj.setStatusId((Long)values[2]);
					probsCountByStatusAndDateMap.put(date+status, count);
					problemsCountByStatusAndDate.add(problemsCountByStatusObj);
				}
				
				
				
				for(int i=0; i<limit; i++){
					calendar.setTime(new Date());
					calendar.add(GregorianCalendar.DAY_OF_MONTH, -i);
					date = dateFormater.format((Date) calendar.getTime());
					if(probsCountByStatusAndDateMap.get(date+IConstants.NEW) == null){
						problemsCountByStatusObj = new ProblemsCountByStatus();
						problemsCountByStatusObj.setCount(0);
						problemsCountByStatusObj.setDate(date);
						problemsCountByStatusObj.setStatus(IConstants.NEW);
						problemsCountByStatusAndDate.add(problemsCountByStatusObj);
					}
						
					if(probsCountByStatusAndDateMap.get(date+IConstants.FIXED) == null){
						problemsCountByStatusObj = new ProblemsCountByStatus();
						problemsCountByStatusObj.setCount(0);
						problemsCountByStatusObj.setDate(date);
						problemsCountByStatusObj.setStatus(IConstants.FIXED);
						problemsCountByStatusAndDate.add(problemsCountByStatusObj);
					}
				}
				
				Collections.sort(problemsCountByStatusAndDate, new ProblemsCountByStatusComparator());
				
				locationwiseProblemStatusInfoVO.setLocationId(accessValue);
				locationwiseProblemStatusInfoVO.setProblemsCountByStatus(problemsCountByStatusList);
				locationwiseProblemStatusInfoVO.setTotalProblemsCount(totalProbsCount);
				locationwiseProblemStatusInfoVO.setProblemsCountByStatusForChart(problemsCountByStatusAndDate);
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return locationwiseProblemStatusInfoVO;
		}
		
		public List<ProblemBeanVO> getProblemsInfoByStatusInALocation(Long accessValue,String accessType,Long registrationId,Long status) {
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			try{
				String tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
			
			/*
			 * Modified in order to remove hard coding of status
			 * Please check previous version to know exact modifications
			 * @Author Ravi Kiran.Y
			 * @Date 29-09-10
			 * Starts from here.. 	
			 */
			if(status!=null && status!=0){
				//result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId(tehsilIds,status);
				result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId(registrationId,status);
			}
			/**
			 * modification ends here...
			 */
			else{
				//result = problemHistoryDAO.findProblemsForALocationsByConstituencyId(tehsilIds);	
				result = problemHistoryDAO.findProblemsForALocationsByConstituencyId(registrationId);	
			}
			problemBeanVO = populateProblemInfo(result,registrationId,status,IConstants.PROBLEMS_MANAGEMENT);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception Raised--->"+e);
			return null;			
		}
			return problemBeanVO;
		}

		/**
		 * The below method can be used to get all the problems count(for each day)
		 * that are specified fromDate-toDate.
		 * 
		 * @author Ravi Kiran.Y
		 * @date 29-09-10.
		 * @return NavigationVO
		 */
		public NavigationVO getProblemsCountInAWeek(Date fromDate,Date toDate,String status,String type){
			List list = null;	
			NavigationVO carryingObject = null;
			ResultStatus resultStatus = new ResultStatus();
			try {
				list = problemHistoryDAO.getAllNonApprovedProblemsBetweenDates(fromDate,toDate,status,type);
				
				carryingObject = setDataInToVOForNonApprovalProblemsCount(list);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				 carryingObject.setResultStatus(resultStatus);
				return carryingObject; 
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject;
			}
		}
		
		/**
		 * This method is used to set all the data into a Data Transer Object that contains all the non approval problems
		 * count based on calling method.
		 * 
		 * @author Ravi Kiran.Y
		 * @date 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO setDataInToVOForNonApprovalProblemsCount(List list){
			NavigationVO carryingObject = null;
			List<SelectOptionVO> result = null;	
			ResultStatus resultStatus = new ResultStatus();
			try{
					carryingObject = new NavigationVO(); 
					if(list.size()!=0){
						result = new ArrayList<SelectOptionVO>(0); 
						for(int i=0;i<list.size();i++){
							Object[] parms = (Object[])list.get(i);
							SelectOptionVO selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)parms[0]);
							selectOptionVO.setName(parms[1].toString());
							result.add(selectOptionVO);
						}
					}
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
				 carryingObject.setProblemsCount(result);
				 carryingObject.setResultStatus(resultStatus);
				return carryingObject; 
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject;
			}
		}
		
		/**
		 * This method retrives count of all the non approved problems by state,district,constituency,mandal,hamlet,booth wise
		 * for the current day or present day.
		 *  
		 * @author Ravi Kiran.Y
		 * @date 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(Date date,String status,String type){
			List list = null;	
			NavigationVO carryingObject = null;
			ResultStatus resultStatus = new ResultStatus();
			try {
				list = problemHistoryDAO.getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(date,status,type);
				
				carryingObject = setDataInToVOForNonApprovalProblemsCount(list);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject; 
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject;
			}
		}
		
		/**
		 * This method retrives all the approval problems that are needed to be approved by
		 * the user between the user selected dates .
		 * @author Ravi Kiran.Y
		 * @date 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO getAllApprovalProblemsBetweenTheDates(String fromDate,String toDate,String status,String type){
			List<Object> list = null;
			NavigationVO navigationVO = null;	
			try{
				Date firstDate = DateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN);
				Date secondDate = DateService.convertStringToDate(toDate, IConstants.DATE_PATTERN);
				list = problemHistoryDAO.getAllNonApprovedProblemsBetweenDatesWithCompleteData(firstDate,secondDate,status,getUserSelectedChoice(type));
				navigationVO = generateVoContainingAllApprovalProblems(list);
				return navigationVO;
			}catch(Exception e){				
				return navigationVO;
			}	
		}
	
		/** 
		 * This method retrives all the approval problems that are needed to be approved by the user for the current or
		 * present day.
		 *  
		 * @author Ravi Kiran.Y
		 * @date 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO getAllApprovalProblemsForTheCurrentDay(String status,String type){
			List<Object> list = null;
			NavigationVO navigationVO = null;	
			Date todayDate = dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY);			
			try{
				list = problemHistoryDAO.getAllNonApprovedProblemsPostedForCurrentDay(todayDate,status,type);
				navigationVO = generateVoContainingAllApprovalProblems(list);
								
				return navigationVO;
			}catch(Exception e){
				return navigationVO;
			}	
		}
		
		/** 
		 * This method retrives all the approval problems that are needed to be approved by the user for a particular date.
		 *  
		 * @author Ravi Kiran.Y
		 * @version 1.0, 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO getAllApprovalProblemsForSelectedDate(Date date,String status,String type){
			List<Object> list = null;
			NavigationVO navigationVO = null;			
			try{
				
				list = problemHistoryDAO.getAllNonApprovedProblemsPostedForCurrentDay(date,status,getUserSelectedChoice(type));
				navigationVO = generateVoContainingAllApprovalProblems(list);
								
				return navigationVO;
			}catch(Exception e){
				return navigationVO;
			}	
		}
		
		public String getUserSelectedChoice(String choice){
			if(choice.equalsIgnoreCase("Newly Posted")){
				return IConstants.FALSE;
			}else if(choice.equalsIgnoreCase("Approved")){
				return IConstants.TRUE;
			}else{
				return IConstants.REJECTED;
			}			
		}
		/**
		 * This method process and generates a Data Transer Object based on the given input by handling exception.
		 * @author Ravi Kiran.Y
		 * @date 01-10-10
		 * @param list
		 * @return NavigationVO
		 */
		public NavigationVO generateVoContainingAllApprovalProblems(List<Object> list){
			List<ProblemBeanVO> problemBeanVO = null;
			NavigationVO carryingObject = null;
			ResultStatus resultStatus = new ResultStatus();
			Long problemHistoryId = 0l;
			String acceptCount,rejectCount;
			String problemDesc;
			try{
				carryingObject = new NavigationVO();
				problemBeanVO = new ArrayList<ProblemBeanVO>();		
				if(list.size()!=0){
					for(int i=0;i<list.size();i++){
						Object[] parms = (Object[])list.get(i);
						ProblemBeanVO resultStorage = new ProblemBeanVO();
						problemDesc = parms[0].toString(); 						
						resultStorage.setProblem(problemDesc.length()>35 ? problemDesc.substring(0,35).concat("..."):problemDesc);
						resultStorage.setDescription(parms[1].toString());
						resultStorage.setImpactLevel(parms[2].toString());
						resultStorage.setPostedDate(parms[4].toString());
						resultStorage.setName(parms[5].toString());
						resultStorage.setProblemId((Long)parms[6]);
						
						problemHistoryId = (Long)parms[7];
						
						resultStorage.setProblemHistoryId(problemHistoryId);	
						
						acceptCount = dataApprovalService.getCountOfPosts(problemHistoryId).getAcceptedCount();
						rejectCount = dataApprovalService.getCountOfPosts(problemHistoryId).getRejectedCount();
						resultStorage.setAcceptedCount(acceptCount==null?"0":acceptCount);
						resultStorage.setRejectedCount(rejectCount==null?"0":rejectCount);
						
						problemBeanVO.add(resultStorage);
					}
				}
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				carryingObject.setApprovalProblems(problemBeanVO);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject; 
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				carryingObject.setResultStatus(resultStatus);
				return carryingObject;
			}	
		}
		
		
		/**
		 * The below method deletes all the dependencies of problems that are rejected for 
		 * approval by the administrator.
		 * @param problemIds
		 * @author Ravi Kiran.Y
		 */
		public void deleteSelectedProblemsByAdmin(final Integer[] problemIds){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					for(int i=0;i<problemIds.length;i++){
						ProblemHistory problemHistory = problemHistoryDAO.get(problemIds[i].longValue());				
						problemHistory.setIsApproved(IConstants.REJECTED);
						problemHistoryDAO.save(problemHistory);
					}	
				}
			});
		}
		
		
		/**
		 * The below method approves all the problems that accepted for approval by the administrator.
		 * @param problemIds
		 * @author Ravi Kiran.Y
		 */
		public void acceptSelectedProblemsByAdmin(final Integer[] problemHistoryIds){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					for(int i=0;i<problemHistoryIds.length;i++){						
						ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryIds[i].longValue());						
						problemHistory.setIsApproved(IConstants.TRUE);
						problemHistoryDAO.save(problemHistory);
						ProblemLocation problemLocation = problemLocationDAO.get(problemHistory.getProblemLocation().getProblemLocationId());
						problemLocation.setUpdatedDate(dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY));
						problemLocationDAO.save(problemLocation);
					}
				}
			});
		}
		
		
		/** 
		 * The below method can be used to retrive all the approved problems based on location-type and location-id
		 * 
		 * for example 
		 * location-id = 19(Location Id here (District-Id))  and
		 * location-type = District
		 *  
		 * which are being approved by the administrator.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public NavigationVO getAllProblemsForGivenLocation(List<Long> locationIds,String locationType){
			NavigationVO result = null;	
			ResultStatus resultStatus = new ResultStatus();	
			List<Long> assemblyConstituencies = new ArrayList<Long>();
			List<Long> parliamentConstituencies = new ArrayList<Long>();
			try{
				result = new NavigationVO();
				/*
				 * The below if and else if conditions is helpful to get problems based on the user choices
				 * and it calls appropriate methods and after getting results the data is sent to the 
				 * generateVoContainingAllApprovalProblems method to set the data in to the DataTransferObject so that
				 * it can be helpful for proper displaying purpose.
				 */
				if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAState(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInADistrict(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
					for(Long constituencyId : locationIds){
						Constituency constituency  = constituencyDAO.get(constituencyId);
						if(constituency.getElectionScope().getElectionType().getElectionType().toString().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
							parliamentConstituencies.add(constituencyId);
						}else{
							assemblyConstituencies.add(constituencyId);
						}
					}
					if(assemblyConstituencies!=null && assemblyConstituencies.size()!=0){
						result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAConstituency(assemblyConstituencies,locationType));
					}
					if(parliamentConstituencies!=null && parliamentConstituencies.size()!=0){
						List<Long> listOfAssemblyConstituencies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(parliamentConstituencies);
						
						if(listOfAssemblyConstituencies!=null && listOfAssemblyConstituencies.size()!=0){
							listOfAssemblyConstituencies.addAll(parliamentConstituencies);							
							result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAConstituency(listOfAssemblyConstituencies,locationType));
						}else{
							if(assemblyConstituencies!=null && assemblyConstituencies.size()!=0)
								result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAConstituency(assemblyConstituencies,locationType));
							else
								throw new GenericException("There are no assemblies mapped to this parliament constituency");
						}
					}
					
				}else if(locationType.equalsIgnoreCase(IConstants.MUNICIPAL_CORP_GMC)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInALocalElectionBody(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.MANDAL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInATehsil(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.CENSUS_VILLAGE_LEVEL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAHamlet(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.CENSUS_WARD_LEVEL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAWard(locationIds,locationType));
				}				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				result.setResultStatus(resultStatus);
				return result; 
			}catch(Exception e){
				e.printStackTrace();
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				result.setResultStatus(resultStatus);
				return result;
			}	
		}

		
		/**
		 * This method can be used to get all the problems in a ward or wards and this method
		 * can be internally used by other methods to get all the problems in a LocalElectionBodies.
		 *  
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public List<Object> getAllAcceptedProblemsInAWard(List<Long> locationIds, String locationType) {			
			List<Object> wardResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);	
			return wardResult;
		}

		/** 
		 * This method can be used to get all the problems in a Hamlet or Hamlets and this method
		 * can be internally used by other methods to get all the problems in a Mandals/Tehsils.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public List<Object> getAllAcceptedProblemsInAHamlet(List<Long> locationIds, String locationType) {			
			List<Object> hamletResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);			
			return hamletResult;
		}

		/**
		 * This method can be used to get all the problems in a Mandal or Mandals and this method
		 * can be internally used by other methods to get all the problems in a Constituency.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		@SuppressWarnings("unchecked")
		public List<Object> getAllAcceptedProblemsInATehsil(List<Long> locationIds, String locationType) {			
			List<Object> teshilResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);			
			if(teshilResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				
				for(Long tehsils:locationIds){
					
					List<Long> ids = new ArrayList<Long>();
					ids.add(tehsils);
					List<Long> listOfHamlets = hamletDAO.findHamletsByTehsilIds(ids);
					if(listOfHamlets!=null && listOfHamlets.size()!=0){
						List<Object> hamletResult = getAllAcceptedProblemsInAHamlet(listOfHamlets,IConstants.HAMLET_LEVEL);
						teshilResult.addAll(hamletResult);
						if(hamletResult != null && hamletResult.size() >= IConstants.MAX_PROBLEMS_DISPLAY)
							return teshilResult;
						
					}	
				}
			}			
			return teshilResult;
		}

		/**
		 * This method can be used to get all the problems in a LocalElectionBody or LocalElectionBody's and this method
		 * can be internally used by other methods to get all the problems in a Constituency.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public List<Object> getAllAcceptedProblemsInALocalElectionBody(List<Long> locationIds, String locationType) {
			List<Object> localElectionBodyResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);
			if(localElectionBodyResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				List<Long> listOfwards = constituencyDAO.getAllWardsByLocalElectionBodyIds(locationIds);
				if(listOfwards!=null && listOfwards.size()!=0){
					List<Object> hamletResult = getAllAcceptedProblemsInAWard(listOfwards,IConstants.CENSUS_WARD_LEVEL);
					localElectionBodyResult.addAll(hamletResult);	
				}
			}			
			return localElectionBodyResult;
		}

		/**
		 * This method can be used to get all the problems in a Constituency or Constituency's and this method
		 * can be internally used by other methods to get all the problems in a District.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		@SuppressWarnings("unchecked")
		public List getAllAcceptedProblemsInAConstituency(List<Long> locationIds, String locationType) {
			List<Object> constituencyResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);
			if(constituencyResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				List<Long> urbanConstituencies = new ArrayList<Long>();
				List<Long> ruralConstituencies = new ArrayList<Long>();
				List<Long> urban_rural_constituencies = new ArrayList<Long>();
				List<Object> tehsilResult = new ArrayList<Object>();
				List<Object> localElectionBodyResult = new ArrayList<Object>();
				for(Long constituencyId : locationIds){
					Constituency constituency  = constituencyDAO.get(constituencyId);
					if(constituency.getAreaType()!=null){
						if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
							urbanConstituencies.add(constituency.getConstituencyId());
						}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
							ruralConstituencies.add(constituency.getConstituencyId());
						}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
							urban_rural_constituencies.add(constituency.getConstituencyId());
						}
					}				
				}
				if(ruralConstituencies!=null && ruralConstituencies.size()!=0){				
					
					List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(ruralConstituencies);
					if(tehsilIds!=null && tehsilIds.size()!=0){
						tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.MANDAL);
						constituencyResult.addAll(tehsilResult);
						if(tehsilResult != null && tehsilResult.size() >= IConstants.MAX_PROBLEMS_DISPLAY)
							return constituencyResult;
						
					}
				}
				if(urbanConstituencies!=null && urbanConstituencies.size()!=0 && ruralConstituencies!=null && ruralConstituencies.size()!=0){
					List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(ruralConstituencies);
					if(localElectionBodyIds!=null && localElectionBodyIds.size()!=0){
						localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.MUNICIPAL_CORP_GMC);
						constituencyResult.addAll(localElectionBodyResult);
						if(localElectionBodyResult != null && localElectionBodyResult.size() >= IConstants.MAX_PROBLEMS_DISPLAY)
							return constituencyResult;
					}				
				}
				if(urban_rural_constituencies!=null && urban_rural_constituencies.size()!=0){
					List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(urban_rural_constituencies);
					if(tehsilIds!=null && tehsilIds.size()!=0){
						tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.MANDAL);
						constituencyResult.addAll(tehsilResult);
						if(tehsilResult != null && tehsilResult.size() >= IConstants.MAX_PROBLEMS_DISPLAY)
							return constituencyResult;
					}				
					List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(urban_rural_constituencies);
					if(localElectionBodyIds!=null && localElectionBodyIds.size()!=0){
						localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.MUNICIPAL_CORP_GMC);
						constituencyResult.addAll(localElectionBodyResult);
						if(localElectionBodyResult != null && localElectionBodyResult.size() >= IConstants.MAX_PROBLEMS_DISPLAY)
							return constituencyResult;
					}				
				}
			}
			return constituencyResult;
		}

		/** 
		 * This method can be used to get all the problems in a District or District's and this method
		 * can be internally used by other methods to get all the problems in a State.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public List<Object> getAllAcceptedProblemsInADistrict(List<Long> locationIds, String locationType) {
			List<Object> districtResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);
			if(districtResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				List<Long> listOfconstituencies = constituencyDAO.getAllConstituencysByDistrictIds(locationIds,IConstants.ASSEMBLY_ELECTION_TYPE);
				List<Object> consituencyResult = getAllAcceptedProblemsInAConstituency(listOfconstituencies,IConstants.CONSTITUENCY_LEVEL);
				districtResult.addAll(consituencyResult);
			}			
			return districtResult;
		}

		/**
		 * This method can be used to get all the problems in a State or State's and this method
		 * can be internally used by other methods to get all the problems in the Country.
		 * 
		 * @author Ravi Kiran.Y
		 * @param locationIds
		 * @param locationType
		 * @return List<Object>
		 * @date 06-10-10
		 */
		public List<Object> getAllAcceptedProblemsInAState(List<Long> locationIds, String locationType) {			
			List<Object> stateResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);
			if(stateResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				List<Long> listOfDistricts = districtDAO.getAllDistrictByStateIds(locationIds);
				List<Object> districtResult = getAllAcceptedProblemsInADistrict(listOfDistricts,IConstants.DISTRICT_LEVEL);
				stateResult.addAll(districtResult);
			}		
			return stateResult;
		}

		public String getLocationStringFromProblemHistory(Long impactedRegionId,Long locationId)
		{
			try{
				String locationStr = null;
				long regionId = impactedRegionId.longValue();
				
				if(regionId == 2)
					locationStr = " and model.problemLocation.problemCompleteLocation.state.stateId = "+locationId;
				else if(regionId == 3)
					locationStr = " and model.problemLocation.problemCompleteLocation.district.districtId = "+locationId;
				else if(regionId == 4)
					locationStr = " and model.problemLocation.problemCompleteLocation.constituency.constituencyId = "+locationId;
				else if(regionId == 5)
					locationStr = " and model.problemLocation.problemCompleteLocation.tehsil.tehsilId = "+locationId;
				else if(regionId == 6)
					locationStr = " and model.problemLocation.problemCompleteLocation.hamlet.hamletId = "+locationId;
				else if(regionId == 7)
					locationStr = " and model.problemLocation.problemCompleteLocation.localElectionBody.localElectionBodyId = "+locationId;
				else if(regionId == 8)
					locationStr = " and model.problemLocation.problemCompleteLocation.ward.constituencyId = "+locationId;
				else if(regionId == 9)
					locationStr = " and model.problemLocation.problemCompleteLocation.booth.boothId = "+locationId;
				
				return locationStr;
			}catch(Exception e){
				return null;
			}
		}
		
		public String getLocationStringFromCadreProblemDetails(Long impactedRegionId,Long locationId)
		{
			try{
				String locationStr = null;
				long regionId = impactedRegionId.longValue();
				
				if(regionId == 2)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.state.stateId = "+locationId;
				else if(regionId == 3)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.district.districtId = "+locationId;
				else if(regionId == 4)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.constituency.constituencyId = "+locationId;
				else if(regionId == 5)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.tehsil.tehsilId = "+locationId;
				else if(regionId == 6)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.hamlet.hamletId = "+locationId;
				else if(regionId == 7)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.localElectionBody.localElectionBodyId = "+locationId;
				else if(regionId == 8)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.ward.constituencyId = "+locationId;
				else if(regionId == 9)
					locationStr = " and model.problemHistory.problemLocation.problemCompleteLocation.booth.boothId = "+locationId;
				
				return locationStr;
			}catch(Exception e){
				return null;
			}
		}
		
		public List<SelectOptionVO> getTotalProblemsCountForAnUserInARegion(Long userId,Long impactedRegionId,Long locationId)
		{
			try{
				List<Object> params = problemHistoryDAO.getTotalProblemsCountForAnUserInARegion(userId,getLocationStringFromProblemHistory(impactedRegionId, locationId));
				
				return getStatusWiseProblemsCount(params);
				
			}catch(Exception e){
				return null;
			}
		}
		
		public List<SelectOptionVO> getTotalProblemsStatusForAnUser(Long userId)
		{
			try{
				List<Object> params = problemHistoryDAO.getTotalProblemsStatusForAnUser(userId);
				
				return getStatusWiseProblemsCount(params);
				
			}catch(Exception e){
				return null;
			}
		}
		
		public List<SelectOptionVO> getStatusWiseProblemsCount(List<Object> params)
		{
			try{
				
				List<SelectOptionVO> probCountList = new ArrayList<SelectOptionVO>(0);
				Long New = 0l;
				Long prog = 0l;
				Long pending = 0l;
				Long fixed = 0l;
				Long total = 0l;
				
				for(Object status : params)
				{
					total++;
					
					if(status.toString().equalsIgnoreCase(IConstants.NEW))
						New++;
					else if(status.toString().equalsIgnoreCase(IConstants.PROGRESS))
						prog++;
					else if(status.toString().equalsIgnoreCase(IConstants.PENDING))
						pending++;
					else if(status.toString().equalsIgnoreCase(IConstants.FIXED))
						fixed++;
				}
				
				probCountList.add(new SelectOptionVO(New,IConstants.NEW));
				probCountList.add(new SelectOptionVO(prog,IConstants.PROGRESS));
				probCountList.add(new SelectOptionVO(pending,IConstants.PENDING));
				probCountList.add(new SelectOptionVO(fixed,IConstants.FIXED));
				probCountList.add(new SelectOptionVO(total,"Total"));
				
				return probCountList;
			}catch(Exception e){
				return null;
			}
		}
		
		public List<ProblemBeanVO> getStatusWiseProblemsForAnUserInARegion(Long userId,Long impactedRegionId,Long locationId,String status)
		{
			try{
				String statusStr = " and model.problemStatus.status = '"+status+"' ";
				if(status.equalsIgnoreCase("Total"))
					statusStr = " ";
				List<ProblemHistory> list = problemHistoryDAO.getStatusWiseProblemsForAnUserInARegion(userId,getLocationStringFromProblemHistory(impactedRegionId,locationId),statusStr);
				
				return convetProblemHistotyToProblemBeanVO(list);
				
			}catch(Exception e){
				return null;
			}
		}
		
		public List<ProblemBeanVO> getStatusWiseProblemsForAnUser(Long userId,String status)
		{
			try{
			String statusStr = " and model.problemStatus.status = '"+status+"' ";
			if(status.equalsIgnoreCase("Total"))
				statusStr = " ";
			List<ProblemHistory> list = problemHistoryDAO.getStatusWiseProblemsForAnUser(userId,statusStr);
			
			return convetProblemHistotyToProblemBeanVO(list);
			
			}catch(Exception e){
				return null;
			}
		}
		
		
		public List<SelectOptionVO> getCadreProblemsCountInARegion(Long userId,Long impactedRegionId,Long locationId)
		{
			try{
				List<SelectOptionVO> probCountList = new ArrayList<SelectOptionVO>(0);
				Long personal,assidned,total,both=0l;
				List<Object> cadreprob = null;
				List<Object> cadreAssprob = null;
				
				if(impactedRegionId == null || locationId == null)
				{
					cadreprob = cadreProblemDetailsDAO.getCadreProblemsCountForAnUser(userId);
					cadreAssprob = assignedProblemProgressDAO.getAssignedCadreProblemsCountForAnUser(userId);
				}
				else
				{
					cadreprob = cadreProblemDetailsDAO.getCadreProblemsCountInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
					cadreAssprob = assignedProblemProgressDAO.getAssignedCadreProblemsCountInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
				}
				
				if(cadreprob != null && cadreAssprob != null)
				{
					for(Object obj:cadreAssprob)
						if(cadreprob.contains(obj))
							both++;
				}
				
				if(cadreprob != null && cadreprob.size() > 0)
					personal = ((long)cadreprob.size());
				else
					personal = 0l;
				
				
				if(cadreAssprob != null && cadreAssprob.size() > 0)
					assidned = ((long)cadreAssprob.size());
				else
					assidned = 0l;	
				
				total = personal + assidned - both;
				
				probCountList.add(new SelectOptionVO(personal,IConstants.CADRE_PERSONAL));
				probCountList.add(new SelectOptionVO(assidned,IConstants.CADRE_ASSIGNED));
				probCountList.add(new SelectOptionVO(both,"Both"));
				probCountList.add(new SelectOptionVO(total,IConstants.TOTAL));
				
				return probCountList;
			}catch(Exception e){
				return null;
			}
			
		}
		
		public List<ProblemBeanVO> getCadreProblemsInARegion(Long userId,Long impactedRegionId,Long locationId,String status)
		{
			try{
				List<ProblemHistory> cadreProblemsList = null;
				List<ProblemHistory> cadreAssignedProblemsList = null;
				List<ProblemHistory> totalProblemsList = new ArrayList<ProblemHistory>(0);
				
				if(status.equalsIgnoreCase(IConstants.CADRE_PERSONAL) || status.equalsIgnoreCase(IConstants.TOTAL))
				{
					if(impactedRegionId != null && locationId != null)
						cadreProblemsList = cadreProblemDetailsDAO.getCadreProblemsInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
					else
						cadreProblemsList = cadreProblemDetailsDAO.getCadreProblemsForAnUser(userId);
				}
				
				if(status.equalsIgnoreCase(IConstants.CADRE_ASSIGNED) || status.equalsIgnoreCase(IConstants.TOTAL))
				{
					if(impactedRegionId != null && locationId != null)
						cadreAssignedProblemsList = assignedProblemProgressDAO.getAssignedCadreProblemsInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
					else
						cadreAssignedProblemsList = assignedProblemProgressDAO.getAssignedCadreProblemsForAnUser(userId);
				}
				
				if(cadreProblemsList != null && cadreProblemsList.size() > 0)
					addProblemsToMainList(totalProblemsList,cadreProblemsList);
				
				if(cadreAssignedProblemsList != null && cadreAssignedProblemsList.size() > 0)
					addProblemsToMainList(totalProblemsList,cadreAssignedProblemsList);
				
				return convetProblemHistotyToProblemBeanVO(totalProblemsList);
				
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public List<ProblemBeanVO> getCadreDetailsForProblemsInARegion(Long userId,Long impactedRegionId,Long locationId,String status)
		{
		try{
			List<ProblemBeanVO> list = new ArrayList<ProblemBeanVO>(0);
			List<Cadre> cadreList = new ArrayList<Cadre>(0);
			ProblemBeanVO problemBeanVO = null;
			if(status.equalsIgnoreCase(IConstants.CADRE_PERSONAL))
			{
				if(impactedRegionId != null && locationId != null)
					cadreList = cadreProblemDetailsDAO.getCadreForCadreProblemsInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
				else
					cadreList = cadreProblemDetailsDAO.getCadreForCadreProblemsForAnUser(userId);
			}
			else if(status.equalsIgnoreCase(IConstants.CADRE_ASSIGNED))
			{
				if(impactedRegionId != null && locationId != null)
					cadreList = assignedProblemProgressDAO.getCadreForCadreProblemsInARegion(userId,getLocationStringFromCadreProblemDetails(impactedRegionId,locationId));
				else
					cadreList = assignedProblemProgressDAO.getCadreForCadreProblemsForAnUser(userId);
			}
			
			for(Cadre cadre : cadreList)
			{
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setProblemId(cadre.getCadreId());
				problemBeanVO.setDeptName(cadre.getFirstName()+" "+cadre.getLastName());
				problemBeanVO.setProblemLocation(cadreManagementService.setAddress(cadre));
				
				if(status.equalsIgnoreCase(IConstants.CADRE_PERSONAL))
					problemBeanVO.setDepartments(getStatusWiseProblemsCount(cadreProblemDetailsDAO.getProblemStatusOfACadre(cadre.getCadreId())));
				else if(status.equalsIgnoreCase(IConstants.CADRE_ASSIGNED))
					problemBeanVO.setDepartments(getStatusWiseProblemsCount(assignedProblemProgressDAO.getProblemStatusOfACadre(cadre.getCadreId())));
				
				list.add(problemBeanVO);
			}
			return list;
		  }
		catch (Exception e) {
				log.error("Exception Occured In getCadreDetailsForProblemsInARegion() Method "+e); 
				return null;
			}
		}
		
		public List<ProblemBeanVO> getDeptProblemsBasedOnStatusInARegion(Long userId,Long impactedRegionId,Long locationId,Long deptId,String status)
		{
			try{
				
				String statusStr = null;
				String deptStr = null;
				if(status.equalsIgnoreCase(IConstants.TOTAL))
					statusStr = "";
				else
					statusStr = " and model.problemHistory.problemStatus.status = '"+status+"'"; 
				
				if(deptId != null && deptId.longValue() > 0)
					deptStr = " and model.departmentOrganisation.departmentOrganisationId = "+deptId;
				else
					deptStr = "and model.departmentOrganisation.departmentOrganisationId is not null";
				
				List<Long> progressIdList = assignedProblemProgressDAO.getDeptWiseAssignedProblemProgressIds(userId,getDepartmentLocationString(impactedRegionId,locationId));
				
				if(progressIdList != null && progressIdList.size() > 0)
				{
					return convetProblemHistotyToProblemBeanVO(assignedProblemProgressDAO.getProblemsBasedOnAssignedProblemProgressIdAndStatus(userId,progressIdList,deptStr,statusStr));
				}
				else
					return null;
			}catch(Exception e){
				 log.error("Exception Raised In getDeptProblemsBasedOnStatusInARegion Method :" + e);
				return null;
			}
		}
		public List<ProblemBeanVO> getDeptWiseProblemsCountInALocation(Long userId,Long impactedRegionId,Long locationId)
		{
			try{
				
				List<Long> progressIdList = assignedProblemProgressDAO.getDeptWiseAssignedProblemProgressIds(userId,getDepartmentLocationString(impactedRegionId,locationId));
				
				List<Object[]> deptInfoList = assignedProblemProgressDAO.getProblemsStatusBasedOnAssignedProblemProgressId(userId,progressIdList);
				
				if(deptInfoList != null && deptInfoList.size() > 0)
				{
					List<ProblemBeanVO> beanVOList = new ArrayList<ProblemBeanVO>(0); 
					
					for(Object[] params : deptInfoList)
					{
						Object status = params[0];
						Long id = (Long) params[1];
						String deptName = params[2].toString();
						boolean flag = true;
						
						for(ProblemBeanVO problemBeanVO:beanVOList)
						{
							if(id.equals(problemBeanVO.getDepartmentId()))
							{
								problemBeanVO.getStatusList().add(status);
								flag = false;
							}
						}
						
						if(flag)
						{
							ProblemBeanVO problemBeanVO = new ProblemBeanVO();
							List<Object> objList = new ArrayList<Object>(0);
							
							problemBeanVO.setDepartmentId(id);
							problemBeanVO.setDeptName(deptName);
							objList.add(status);
							problemBeanVO.setStatusList(objList);
							beanVOList.add(problemBeanVO);
						}
					}
					
					for(ProblemBeanVO problemBeanVO : beanVOList)
					{
						problemBeanVO.setDepartments(getStatusWiseProblemsCount(problemBeanVO.getStatusList()));
						problemBeanVO.setProblemsCount((long)problemBeanVO.getStatusList().size());
					}
					return beanVOList;
				}
				else
					return null;
			
			}catch(Exception e){
				return null;
			}
			
		}
		
		public String getDepartmentLocationString(Long impactedRegionId,Long locationId)
		{
			try{
				String deptLocationStr = null;
				long regionId = impactedRegionId.longValue();
				
				if(regionId == 2)
					deptLocationStr = " and model.departmentLocation.state.stateId = "+locationId;
				else if(regionId == 3)
					deptLocationStr = " and model.departmentLocation.district.districtId = "+locationId;
				else if(regionId == 4)
					deptLocationStr = " and model.departmentLocation.constituency.constituencyId = "+locationId;
				else if(regionId == 5)
					deptLocationStr = " and model.departmentLocation.tehsil.tehsilId = "+locationId;
				else if(regionId == 6)
					deptLocationStr = " and model.departmentLocation.hamlet.hamletId = "+locationId;
				else if(regionId == 7)
					deptLocationStr = " and model.departmentLocation.localElectionBody.localElectionBodyId = "+locationId;
				else if(regionId == 8)
					deptLocationStr = " and model.departmentLocation.ward.constituencyId = "+locationId;
				else if(regionId == 9)
					deptLocationStr = " and model.departmentLocation.booth.boothId = "+locationId;
				
				return deptLocationStr;
			}catch(Exception e){
				return null;
			}
		}
		public List<ProblemBeanVO> getDeptWiseProblemsCountForAnUser(Long userId,Long deptScopeId)
		{
			try{
				List<SelectOptionVO> deptList = problemManagementService.getDepartmentsForADepartmentResolvingAreaScope(deptScopeId);
				List<ProblemBeanVO> deptWiseProbList = null;
				if(deptList != null && deptList.size() > 0)
				{
					deptWiseProbList = new ArrayList<ProblemBeanVO>(0);
					ProblemBeanVO problemBeanVO = null;
					
					for(SelectOptionVO dept : deptList)
					{
						problemBeanVO = new ProblemBeanVO();
						problemBeanVO.setDepartmentId(dept.getId());
						problemBeanVO.setDeptName(dept.getName());
						
						List<Object> list = assignedProblemProgressDAO.getDepartmentWiseProblemStatus(userId,dept.getId());
						
						if(list != null && list.size() > 0)
							problemBeanVO.setProblemsCount((long)(list.size()));
						else
							problemBeanVO.setProblemsCount(0l);
						
						problemBeanVO.setDepartments(getStatusWiseProblemsCount(list));
						
						deptWiseProbList.add(problemBeanVO);
					}
				}
				return deptWiseProbList;
			}catch(Exception e){
				return null;
			}
			
		}
		
		public List<ProblemBeanVO> getDepartmentWiseProblemsBasedOnStatus(Long userId,Long deptId,String status)
		{
			try{
				String statusStr = null;
				if(status.equalsIgnoreCase(IConstants.TOTAL))
					statusStr = "";
				else
					statusStr = " and model.problemHistory.problemStatus.status = '"+status+"'"; 
				
				return convetProblemHistotyToProblemBeanVO(assignedProblemProgressDAO.getDepartmentWiseProblemsBasedOnStatus(userId,deptId,statusStr));
				
			}catch(Exception e){
				return null;
			}
		}
		
		public List<ProblemBeanVO> getProblemsInADeptScopeBasedOnScope(Long userId,Long scopeId,String status)
		{
			try{
				List<SelectOptionVO> deptList = problemManagementService.getDepartmentsForADepartmentResolvingAreaScope(scopeId);
				if(deptList != null && deptList.size() > 0)
				{
					List<ProblemBeanVO> totalProbList = new ArrayList<ProblemBeanVO>(0);
					List<ProblemBeanVO> deptWiseProbList = null;
					for(SelectOptionVO dept : deptList)
					{
						deptWiseProbList = getDepartmentWiseProblemsBasedOnStatus(userId,dept.getId(),status);
						if(deptWiseProbList != null && deptWiseProbList.size() > 0)
							for(ProblemBeanVO problemBeanVO : deptWiseProbList)
								totalProbList.add(problemBeanVO);
					}
					return totalProbList;
				}
				else 
					return null;
			}catch(Exception e){
				return null;
			}
		}
		
		public List<ProblemBeanVO> convetProblemHistotyToProblemBeanVO(List<ProblemHistory> list)
		{
			try{
				if(list != null && list.size() > 0)
				{
					List<ProblemBeanVO> problemBeanList = new ArrayList<ProblemBeanVO>(0);
					
					for(ProblemHistory problemHistory : list)
					{
						ProblemBeanVO problemBeanVO = new ProblemBeanVO();
						Problem problem = problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
						Set<AssignedProblemProgress> set = problemHistory.getAssignedProblemProgresses();
						AssignedProblemProgress assignedProblemProgress = null;
						
						problemBeanVO.setProblem(problem.getProblem());
						problemBeanVO.setDescription(problem.getDescription());
						problemBeanVO.setReportedDate(problem.getIdentifiedOn() != null ? problem.getIdentifiedOn().toString() : "");
						problemBeanVO.setProblemStatus(problemHistory.getProblemStatus().getStatus());
						problemBeanVO.setProblemHistoryId(problemHistory.getProblemHistoryId());
						
						problemBeanVO.setProblemLocation(getProblemLocation(problemHistory.getProblemLocation().getProblemImpactLevel().getRegionScopesId(),
											problemHistory.getProblemLocation().getProblemImpactLevelValue()));
						
						for(AssignedProblemProgress assigned : set)
						{
							if(assignedProblemProgress == null)
								assignedProblemProgress = assigned;
							else if(assignedProblemProgress.getAssignedProblemProgressId() < assigned.getAssignedProblemProgressId())
								assignedProblemProgress = assigned;
						}
						if(assignedProblemProgress != null && assignedProblemProgress.getCadre() != null)
						{
							problemBeanVO.setCadreId(assignedProblemProgress.getCadre().getCadreId());
							problemBeanVO.setCadreName(assignedProblemProgress.getCadre().getFirstName()+" "+
									assignedProblemProgress.getCadre().getLastName());
						}
						if(assignedProblemProgress != null && assignedProblemProgress.getDepartmentOrganisation() != null)
						{
							problemBeanVO.setDepartment(assignedProblemProgress.getDepartmentOrganisation().getOrganisationName());
						}
						if(assignedProblemProgress != null && assignedProblemProgress.getProblemActivity() != null)
						{
							problemBeanVO.setRecentActivity(assignedProblemProgress.getProblemActivity().getComments());
						}
						if(assignedProblemProgress != null && assignedProblemProgress.getComments() != null)
						{
							problemBeanVO.setComments(assignedProblemProgress.getComments());
						}
						problemBeanList.add(problemBeanVO);
					}
					return problemBeanList;
				}
				else
					return null;

			}catch(Exception e)
			{
				return null;
			}
		}
		
		public String getProblemLocation(Long impactLevel,Long impactValue)
		{
			try{
				String locationStr = "";
				
				switch (impactLevel.intValue()) {
	            case 2:  
	        	{
	        		State state = stateDAO.get(impactValue);
	        		locationStr = state.getStateName();
	        		break;
	        	}
	            case 3:
	            {
	            	District district = districtDAO.get(impactValue);
	            	locationStr = district.getDistrictName() + ", "+ district.getState().getStateName() ;	            	
	            	break;
	            }
	            case 4: {
	            	Constituency constituency = constituencyDAO.get(impactValue);
					
					if(IConstants.PARLIAMENT_ELECTION_TYPE.equals(constituency.getElectionScope().getElectionType().getElectionType()))
					{	
						locationStr = constituency.getName()+ " " +constituency.getState().getStateName();						
					} else 
					{
						locationStr = constituency.getName()+", "+ constituency.getDistrict().getDistrictName()+"(Dt.)"+", "+constituency.getState().getStateName();						
					}				
	            	break;
	            }
	            case 5: 
	            {
	            	Tehsil tehsil = tehsilDAO.get(impactValue);
	            	locationStr = tehsil.getTehsilName()+ " (Mandal), "+ setConstDistStateTOResult(tehsil.getTehsilId());
					break;
	            }            
	            case 6:
	            {
	            	Hamlet hamlet = hamletDAO.get(impactValue);
	            	locationStr = hamlet.getHamletName()+"(Village), " +setConstDistStateTOResult(hamlet.getTownship().getTehsil().getTehsilId());
	            	break;
	            }
	            case 7:
	            {
	            	LocalElectionBody localBody = localElectionBodyDAO.get(impactValue);
	            	locationStr = localBody.getName()+ "-" +localBody.getElectionType().getElectionType() +", "+localBody.getDistrict().getDistrictName()+"(Dt.)"+", " +localBody.getDistrict().getState().getStateName();
	            	break;
	            }
	            case 8:
	            {
	            	Constituency ward = constituencyDAO.get(impactValue);
	            	locationStr = ward.getName()+", "+ ward.getLocalElectionBody().getName()+"-" +ward.getLocalElectionBody().getElectionType().getElectionType()+ ", "+ward.getLocalElectionBody().getDistrict().getDistrictName()+", "+ ward.getLocalElectionBody().getDistrict().getState().getStateName();
	            	break;
	            }
	            case 9:
	            {
	            	Booth booth = boothDAO.get(impactValue);
	            	if(booth.getTehsil()!= null)
	            	{
	            		locationStr = booth.getTehsil().getTehsilName() + ", "+booth.getTehsil().getTehsilName()+setConstDistStateTOResult(booth.getTehsil().getTehsilId());	            		           		
	            	}else if(booth.getLocalBody() != null)
	            	{            		            		
	            		locationStr = booth.getLocalBody().getName()+booth.getLocalBody().getDistrict().getDistrictName()+booth.getLocalBody().getDistrict().getState().getStateName();	            		
	            	}
	            	
	            	break;
	            }
	            default: System.out.println("Invalid Scope.");break;
	        }
			
			return locationStr;
			}catch(Exception e){
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public Long getLocationValue(long scopeId,Long value)
		{
			try{
				if(scopeId <= 4 || scopeId == 9)
					return value;
				else if(scopeId == 5 || scopeId == 6 || scopeId == 8)
				{
					return Long.parseLong((value.toString().substring(1)));
				}
				else if(scopeId == 7)
				{
					List<Long> localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(value.toString().substring(1)));
					return localElectionBodies.get(0);
				}
				return 0l;
			}catch(Exception e){
				return 0l;
			}
		}
	
		public List<ProblemHistory> addProblemsToMainList(List<ProblemHistory> mainList,List<ProblemHistory> subList)
		{
			try{
				for(ProblemHistory problemHistory:subList)
				{
					if(!mainList.contains(problemHistory))
					mainList.add(problemHistory);
				}
				return mainList;
			}catch(Exception e){
				return mainList;
			}
		}
}
