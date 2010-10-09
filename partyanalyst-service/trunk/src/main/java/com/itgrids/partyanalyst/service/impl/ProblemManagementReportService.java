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

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
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
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeopleDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
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
	private InfluencingPeopleDAO influencingPeopleDAO;
	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private List result = null;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDateService dateService;
	private IProblemDAO problemDAO;
	private TransactionTemplate transactionTemplate;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IProblemLocationDAO problemLocationDAO;
	
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


	public InfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(InfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
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
	 * selected hamlet by location-wise,department-wise as well as  status-wise.
	 * @author Ravi Kiran.Y
	 */
		public List<ProblemBeanVO> getHamletProblemsInfo(Long hamletId,Long registrationId,String taskType){
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			
			if(log.isDebugEnabled())
				log.debug("Entered into getHamletProblemsInfo() method....");
			try{
		/*
		 * Modified in order to remove hard coding of status
		 * Please check previous version to know exact modifications
		 * @Author Ravi Kiran.Y
		 * @Date 29-09-10
		 * Starts from here.. 	
		 */
			//if(taskType.equalsIgnoreCase("new") || taskType.equalsIgnoreCase("classify") || taskType.equalsIgnoreCase("assigned") || taskType.equalsIgnoreCase("progress") || taskType.equalsIgnoreCase("pending") || taskType.equalsIgnoreCase("fixed")){	
			if(taskType!=null && taskType!=""){
				result = problemHistoryDAO.findProblemsByStatusForALocationsByHamletId(hamletId,taskType);
			}
		//Ends here..
			else{
					result = problemHistoryDAO.findProblemsForALocationsByHamletId(hamletId);
				}
			if(result == null){
				if(log.isDebugEnabled())
					log.debug("0 rows have been retrived......");
			}
			else{
				problemBeanVO = populateProblemInfo(result,registrationId,taskType);
			}
			}catch(Exception e){
				e.printStackTrace();
				if(log.isDebugEnabled())
					log.debug("Exception Raised--->"+e);
				return null;				
			}
			return problemBeanVO;
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
					result = problemHistoryDAO.findProblemsByStatusForALocationsByTehsilId(tehsilId,taskType);
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Exception Raised--->"+e);
				return null;			
			}
			}
			else{
				try{
				result = problemHistoryDAO.findProblemsForALocationsByTehsilId(tehsilId);
				}catch(Exception e){
						e.printStackTrace();
						System.out.println("Exception Raised--->"+e);
					return null;			
				}
			}
			problemBeanVO = populateProblemInfo(result,registrationId,taskType);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception Raised--->"+e);
			return null;			
		}
			return problemBeanVO;
		}

		/**
		 * This method takes hamletId,registrationId and taskType and generates a report of problems for the
		 * selected Constituency by location-wise,department-wise as well as  status-wise.
		 * @author Ravi Kiran.Y
		 */
		public List<ProblemBeanVO> getConstituencyProblemsInfo(Long constituencyId,Long registrationId,String taskType, String constituencyType) {
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();
			String tehsilIds = "";
			try{
				
			/*
			 * Modified in order to remove hard coding of status
			 * Please check previous version to know exact modifications
			 * @Author Ravi Kiran.Y
			 * @Date 29-09-10
			 * Starts from here.. 	
			 */
				
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(constituencyType))
				tehsilIds = getCommaSeperatedTehsilIdsForAccessType(IConstants.MLA, constituencyId);
			else
				tehsilIds = getCommaSeperatedTehsilIdsForAccessType(IConstants.MP,constituencyId);
				
			if(taskType!=null && taskType!="" && tehsilIds.trim().length() > 0){
				result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId(tehsilIds,taskType);				
			}
			/**
			 * modification ends here...
			 */
			
			else{
				if(tehsilIds.trim().length() > 0)
					result = problemHistoryDAO.findProblemsForALocationsByConstituencyId(tehsilIds);	
			}
			problemBeanVO = populateProblemInfo(result,registrationId,taskType);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Exception Raised--->"+e);
			return null;			
		}		
			return problemBeanVO;
		}
		
		/**
		 * This method is used for designing ProblemManagementReport based on
		 * user selected Location.
		 * @author Ravi Kiran.Y
		 */
		public List<ProblemBeanVO> populateProblemInfo(List list,Long registrationId,String taskType){
			
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
						if(taskType.equalsIgnoreCase(departmentName)){
							if( !(problemBean.getDepartment().equalsIgnoreCase("Not Assigned."))){
								problemBeanVO.add(problemBean);
							}
						}
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
				problemHistoryVO.setMovedDate(dateService.timeStampConversion(problemData[2].toString()));
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
					problemBeanVO.setHamlet(checkForNull(problemHistory.getProblemLocation().getHamlet().getHamletName()));
					problemBeanVO.setExistingFrom(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getExistingFrom().toString()));
					problemBeanVO.setReportedDate(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblem().getIdentifiedOn().toString()));
					
					if(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource() != null){
						problemBeanVO.setPostedPersonName(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getName()));
						problemBeanVO.setPhone(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getMobile()));
						problemBeanVO.setAddress(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getAddress()));
						problemBeanVO.setEmail(checkForNull(problemHistory.getProblemLocation().getProblemAndProblemSource().getProblemExternalSource().getEmail()));	
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
				problemHistoryVO.setMovedDate(dateService.timeStampConversion(problemData[2].toString()));
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
		
		
			
		public List<ProblemBeanVO> getProblemsPostedByStatusAndDates(String fromDate, String toDate, Long statusId, String accessType, Long accessValue){
			
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
			List problemsRawData = null;
			ProblemBeanVO problemBeanVO = null;
			List<ProblemBeanVO> problems = new ArrayList<ProblemBeanVO>();
			String tehsilIds = "";
			try{
				tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
				if(statusId == 0)
					problemsRawData = problemHistoryDAO.findProblemsByDateAndLocation(tehsilIds, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));
				else
					problemsRawData = problemHistoryDAO.findProblemsByStatusDateAndLocation(tehsilIds, statusId, sdf.parse(dateService.timeStampConversionToYYMMDD(fromDate)), sdf.parse(dateService.timeStampConversionToYYMMDD(toDate)));	
					

			}catch(Exception ex){
				log.debug("Exception Raised While Formating Date:"+ex);
			}
			
			for(Object[] values:(List<Object[]>)problemsRawData){
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setProblemLocationId((Long)values[0]);
				problemBeanVO.setProblem(values[1].toString());
				problemBeanVO.setDescription(values[2].toString());
				problemBeanVO.setHamlet(values[3].toString());
				problemBeanVO.setProblemSourceScope(values[4].toString());
				problemBeanVO.setProblemAndProblemSourceId((Long)values[5]);
				problemBeanVO.setStatus(values[6].toString());
				problemBeanVO.setExistingFrom(dateService.timeStampConversionToDDMMYY(values[7].toString()));
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
		
		public List<InfluencingPeopleVO> findInfluencingPeopleInfoInLocation(String accessType, Long accessValue, Long hamletId, String flag){
						
			List<InfluencingPeople> impPeople = null;
			
			if(accessType.equals(IConstants.STATE_LEVEL))
			{
				impPeople = influencingPeopleDAO.findByStateId(new Long(accessValue));
			}
			
			if(accessType.equals(IConstants.DISTRICT_LEVEL))
			{
				impPeople = influencingPeopleDAO.findByDistrictId(new Long(accessValue));
			}
			
			if(accessType.equals(IConstants.MLA))
			{
				impPeople = influencingPeopleDAO.findByConstituencyId(new Long(accessValue));
			}
			
			if(accessType.equals(IConstants.MP))
			{
				List assemblies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(new Long(accessValue));
				if(assemblies.size() >0)
				{
					for(Object[] values:(List<Object[]>)assemblies)
					{
						long assemblyId = Long.parseLong((values[0].toString()));
						impPeople = influencingPeopleDAO.findByConstituencyId(new Long(assemblyId));
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
					
					if(IConstants.CONSTITUENCY_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(constituencyDAO.get(new Long(people.getInfluencingScopeValue())).getName());
					}else if(IConstants.STATE_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(stateDAO.get(new Long(people.getInfluencingScopeValue())).getStateName());
					}else if(IConstants.DISTRICT_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(districtDAO.get(new Long(people.getInfluencingScopeValue())).getDistrictName());
					}else if(IConstants.TEHSIL_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(tehsilDAO.get(new Long(people.getInfluencingScopeValue())).getTehsilName());
					}else if(IConstants.REVENUE_VILLAGE_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(townshipDAO.get(new Long(people.getInfluencingScopeValue())).getTownshipName());
					}else if(IConstants.HAMLET_LEVEL.equalsIgnoreCase(people.getInfluencingScope())){
						influencingPeopleVO.setInfluencingRangeName(hamletDAO.get(new Long(people.getInfluencingScopeValue())).getHamletName());
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
				String tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
				List newProblems = problemHistoryDAO.findLatestProblemsByMandals(tehsilIds, statusId);
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
				problemBeanVO.setExistingFrom(dateService.timeStampConversionToDDMMYY(values[7].toString()));
				problems.add(problemBeanVO);
			}
			return problems;
		}
		
		@SuppressWarnings("unchecked")
		public LocationwiseProblemStatusInfoVO getProblemsStatusCount(String accessType, Long accessValue, int limit) {
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
					problemsCountByStatusRawList = problemHistoryDAO.getProblemsCountInAllStatusByLocation(constituencyIds);
				
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
				problemsByStatusAndDateRawList = problemHistoryDAO.findLatestProblemsGroupByDatePostedByMandalsAndStatus(tehsilIds, "1,6");
				
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
		
		public List<ProblemBeanVO> getProblemsInfoByStatusInALocation(Long accessValue,String accessType,Long registrationId,String status) {
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
			if(status!=null && status!=""){
				result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId(tehsilIds,status);
			}
			/**
			 * modification ends here...
			 */
			else{
				result = problemHistoryDAO.findProblemsForALocationsByConstituencyId(tehsilIds);	
			}
			problemBeanVO = populateProblemInfo(result,registrationId,status);
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
				Date firstDate = dateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN);
				Date secondDate = dateService.convertStringToDate(toDate, IConstants.DATE_PATTERN);
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
			try{
				carryingObject = new NavigationVO();
				problemBeanVO = new ArrayList<ProblemBeanVO>();		
				if(list.size()!=0){
					for(int i=0;i<list.size();i++){
						Object[] parms = (Object[])list.get(i);
						ProblemBeanVO resultStorage = new ProblemBeanVO();
						resultStorage.setProblem(parms[0].toString());
						resultStorage.setDescription(parms[1].toString());
						resultStorage.setImpactLevel(parms[2].toString());
						resultStorage.setPostedDate(parms[4].toString());
						resultStorage.setName(parms[5].toString());
						resultStorage.setProblemId((Long)parms[6]);
						resultStorage.setProblemHistoryId((Long)parms[7]);
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
							result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAConstituency(assemblyConstituencies,locationType));
						}
					}
					
				}else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInALocalElectionBody(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.TEHSIL_LEVEL)){
					result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInATehsil(locationIds,locationType));
				}else if(locationType.equalsIgnoreCase(IConstants.HAMLET_LEVEL)){
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
		public List<Object> getAllAcceptedProblemsInATehsil(List<Long> locationIds, String locationType) {			
			List<Object> teshilResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType,IConstants.TRUE);			
			if(teshilResult.size()<IConstants.MAX_PROBLEMS_DISPLAY){
				List<Long> listOfHamlets = hamletDAO.findHamletsByTehsilIds(locationIds);
				List<Object> hamletResult = getAllAcceptedProblemsInAHamlet(listOfHamlets,IConstants.HAMLET_LEVEL);
				teshilResult.addAll(hamletResult);
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
				List<Object> hamletResult = getAllAcceptedProblemsInAWard(listOfwards,IConstants.CENSUS_WARD_LEVEL);
				localElectionBodyResult.addAll(hamletResult);
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
					List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(ruralConstituencies);
					if(localElectionBodyIds!=null && localElectionBodyIds.size()!=0){
						localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.LOCALELECTIONBODY);
						constituencyResult.addAll(localElectionBodyResult);
					}				
				}
				if(urbanConstituencies!=null && urbanConstituencies.size()!=0){
					List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(urbanConstituencies);
					if(tehsilIds!=null && tehsilIds.size()!=0){
						tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.TEHSIL_LEVEL);
						constituencyResult.addAll(tehsilResult);
					}				
				}
				if(urban_rural_constituencies!=null && urban_rural_constituencies.size()!=0){
					List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(urban_rural_constituencies);
					if(tehsilIds!=null && tehsilIds.size()!=0){
						tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.TEHSIL_LEVEL);
						constituencyResult.addAll(tehsilResult);
					}				
					List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(urban_rural_constituencies);
					if(localElectionBodyIds!=null && localElectionBodyIds.size()!=0){
						localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.LOCALELECTIONBODY);
						constituencyResult.addAll(localElectionBodyResult);
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
		
}
