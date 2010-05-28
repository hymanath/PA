package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemStatusDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeopleDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemStatus;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.utils.IConstants;


public class ProblemManagementReportService implements
		IProblemManagementReportService {
	private static final Logger log = Logger.getLogger("ProblemManagementReportService.class");
	private IProblemHistoryDAO problemHistoryDAO = null;	
	private IAssignedProblemProgressDAO assignedProblemProgressDAO = null;
	private IProblemStatusDAO problemStatusDAO = null;
	private IRegistrationDAO registrationDAO = null;
	private IProblemExternalSourceDAO problemExternalSourceDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private InfluencingPeopleDAO influencingPeopleDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private List result = null;
	
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

	/**
	 * This method takes hamletId,registrationId and taskType and generates a report of problems for the
	 * selected hamlet by location-wise,department-wise as well as  status-wise.
	 */
		public List<ProblemBeanVO> getHamletProblemsInfo(Long hamletId,Long registrationId,String taskType){
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			
			if(log.isDebugEnabled())
				log.debug("Entered into getHamletProblemsInfo() method....");
			try{
					System.out.println("Making a DAO call to problemHistoryDAO.findProblemsForALocationsByHamletId() ");
			if(taskType.equalsIgnoreCase("new") || taskType.equalsIgnoreCase("classify") || taskType.equalsIgnoreCase("assigned") || taskType.equalsIgnoreCase("progress") || taskType.equalsIgnoreCase("pending") || taskType.equalsIgnoreCase("fixed")){
				result = problemHistoryDAO.findProblemsByStatusForALocationsByHamletId(hamletId,taskType);
			}
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
		 */
		public List<ProblemBeanVO> getTehsilProblemsInfo(Long tehsilId,Long registrationId,String taskType) {
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			try{
			if(taskType.equalsIgnoreCase("new") || taskType.equalsIgnoreCase("classify") || taskType.equalsIgnoreCase("assigned") || taskType.equalsIgnoreCase("progress") || taskType.equalsIgnoreCase("pending") || taskType.equalsIgnoreCase("fixed")){
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
		 */
		public List<ProblemBeanVO> getConstituencyProblemsInfo(Long constituencyId,Long registrationId,String taskType) {
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();	
			try{
				String tehsilIds = getCommaSeperatedTehsilIdsForAccessType("MLA", constituencyId);
			if(taskType.equalsIgnoreCase("new") || taskType.equalsIgnoreCase("classify") || taskType.equalsIgnoreCase("assigned") || taskType.equalsIgnoreCase("progress") || taskType.equalsIgnoreCase("pending") || taskType.equalsIgnoreCase("fixed")){
				result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId(tehsilIds,taskType);
			}
			else{
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
		 */
		public List<ProblemBeanVO> populateProblemInfo(List list,Long registrationId,String taskType){
			
			List<ProblemBeanVO> problemBeanVO = new ArrayList<ProblemBeanVO>();
			List<Registration> regUser = new ArrayList<Registration>();
			List<ProblemExternalSource> extRegUser = new ArrayList<ProblemExternalSource>();
			Long problemId=0l;
			String dateConversion=null;
			String departmentName = null;
			if(log.isDebugEnabled())
				log.debug("Entered into populateProblemInfo() method....");
			try{
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
				
				if(parms[5]==null){
					problemBean.setComments("No Comments received..");
				}
				else 
					problemBean.setComments(parms[5].toString());
				if(!(registrationId==0L)){
					if(parms[6]==null){
						regUser = registrationDAO.findByUserRegistrationId(registrationId.longValue());					
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
								
				// To get all the problem history details...
				
				/*
				List result = problemHistoryDAO.findCompleteProblems(Long.parseLong(parms[9].toString()));
				 List<ProblemHistoryVO> problemHistory = new ArrayList<ProblemHistoryVO>(0);
				for(int j=0;j<result.size();j++){
					Object[] problemData = (Object[])result.get(j);
					ProblemHistoryVO problemHistoryVO = new ProblemHistoryVO();
					problemHistoryVO.setProblemHistoryId(Long.parseLong(problemData[0].toString()));					
					if(!(problemData[1]!= "null")){
						problemHistoryVO.setComments(problemData[1].toString());
					}					
					problemHistoryVO.setMovedDate(problemData[2].toString());					
					if(!(problemData[3]!= "null")){
						problemHistoryVO.setIsDelete(problemData[3].toString());
					}
					problemHistory.add(problemHistoryVO);
				}
				problemBean.setProblemHistory(problemHistory);*/				
				
				if(parms[8]==null)
					problemBean.setUpdatedDate("No Updations..");
				else {
					dateConversion = dateConversion(parms[8].toString());
					problemBean.setUpdatedDate(dateConversion); 
				}				
				if(!(taskType.equalsIgnoreCase("new") || taskType.equalsIgnoreCase("classify") || taskType.equalsIgnoreCase("assigned") || taskType.equalsIgnoreCase("progress") || taskType.equalsIgnoreCase("pending")  || taskType.equalsIgnoreCase("fixed") || taskType.equalsIgnoreCase(""))){
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
				problemHistoryVO.setMovedDate(timeStampConversion(problemData[2].toString()));
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
		 * To convert timestamp which is in yyyy-MM-dd hh:mm:ss format to dd-MM-yyyy hh:mm:ss format.
		 */
		public String timeStampConversion(String idate){
			String convertedDated=null;
			SimpleDateFormat sdfInput =  
		        new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss") ;  
		     SimpleDateFormat sdfOutput =  
		        new SimpleDateFormat  ("dd-MM-yyyy hh:mm:ss") ;  		  
		  
		     Date date;
			try {
				date = sdfInput.parse (idate);
				convertedDated = sdfOutput.format(date).toString();
			} catch (ParseException e){
				e.printStackTrace();
			}
			return convertedDated;	
		}
		
		/*
		 * To convert timestamp which is in yyyy-MM-dd hh:mm:ss format to dd-MM-yyyy hh:mm:ss format.
		 */
		public String timeStampConversionToYYMMDD(String idate){
			String convertedDated=null;
			SimpleDateFormat sdfInput =  
		        new SimpleDateFormat ("yyyy/MM/dd");  
		     SimpleDateFormat sdfOutput =  
		        new SimpleDateFormat  ("dd/MM/yyyy");  		  
		  
		     Date date;
			try {
				date = sdfOutput.parse (idate);
				convertedDated = sdfInput.format(date).toString();
			} catch (ParseException e){
				e.printStackTrace();
			}
			return convertedDated;	
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
					problemsRawData = problemHistoryDAO.findProblemsByDateAndLocation(tehsilIds, sdf.parse(timeStampConversionToYYMMDD(fromDate)), sdf.parse(timeStampConversionToYYMMDD(toDate)));
				else
					problemsRawData = problemHistoryDAO.findProblemsByStatusDateAndLocation(tehsilIds, statusId, sdf.parse(timeStampConversionToYYMMDD(fromDate)), sdf.parse(timeStampConversionToYYMMDD(toDate)));	
					
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
				problemBeanVO.setExistingFrom(values[7].toString());
				problems.add(problemBeanVO);
			}
			
			return problems;
			
		}
		
		public String getCommaSeperatedTehsilIdsForAccessType(String accessType, Long accessValue){
			StringBuffer tehsilIds = new StringBuffer();
			List<Tehsil> mandals = null;
			if("MLA".equalsIgnoreCase(accessType)){
				List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(accessValue);
				Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
				mandals = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);

			}else if("STATE".equalsIgnoreCase(accessType))
				mandals = tehsilDAO.findByState(accessValue);
				else if("DISTRICT".equalsIgnoreCase(accessType))
					mandals = tehsilDAO.findByDistrict(accessValue);
			

			for(Tehsil tehsil : mandals)
				tehsilIds.append(",").append(tehsil.getTehsilId());
			return tehsilIds.toString().substring(1); 
		}
		
		public List<InfluencingPeopleVO> findInfluencingPeopleInfoInLocation(String accessType, Long accessValue){
			String tehsilIds = getCommaSeperatedTehsilIdsForAccessType(accessType, accessValue);
			List<InfluencingPeople> impPeople = influencingPeopleDAO.findByTehsils(tehsilIds); 
			List<InfluencingPeopleVO> influencies = new ArrayList<InfluencingPeopleVO>();
			InfluencingPeopleVO influencingPeopleVO = null;
			String name = "";
			for(InfluencingPeople people:impPeople){
				influencingPeopleVO = new InfluencingPeopleVO();
				if(people.getFirstName() != null)
					name += people.getFirstName();
				if(people.getLastName() != null)
					name = name+" "+people.getLastName();
				influencingPeopleVO.setPersonName(name);
				influencingPeopleVO.setOccupation(people.getOccupation());
				influencingPeopleVO.setInfluencingRange(people.getInfluencingScope());
				influencingPeopleVO.setCast(people.getCaste());
				influencingPeopleVO.setContactNumber(people.getPhoneNo());
				influencingPeopleVO.setLocalArea(people.getHamlet().getHamletName());
				influencingPeopleVO.setParty(people.getParty().getShortName());
				influencies.add(influencingPeopleVO);
			}
			return influencies;
		}
		
		public List<SelectOptionVO> getAllProblemStatusInfo(){
			List<SelectOptionVO> status = new ArrayList<SelectOptionVO>();
			List<ProblemStatus> list = problemStatusDAO.getAll();
			for(ProblemStatus problemStatus:list)
				status.add(new SelectOptionVO(problemStatus.getProblemStatusId(), problemStatus.getStatus()));
			status.add(status.size(), new SelectOptionVO(0l,"All"));
			return status;
		}
		
		
		@SuppressWarnings("unchecked")
		public LocationwiseProblemStatusInfoVO getProblemsStatusCount(String accessType, Long accessValue) {
			
			log.debug("Entered in to getProblemsStatusCount method in ProblemManagementReportService");
			LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO = new LocationwiseProblemStatusInfoVO();
			List<ProblemsCountByStatus> problemsCountByStatusList = new ArrayList<ProblemsCountByStatus>();
			List problemsCountByStatus = null;
			List <Constituency> constituencies = null;
			int totalProbsCount = 0;
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
			}
			if(constituencyIds.length() > 0)
				problemsCountByStatus = problemHistoryDAO.getProblemsCountInAllStatusByLocation(constituencyIds);
			
			for(int i=0;i<problemsCountByStatus.size();i++){
				Object[] parms = (Object[])problemsCountByStatus.get(i);
				ProblemsCountByStatus problemsCountByStatusObj = new ProblemsCountByStatus();	
				problemsCountByStatusObj.setStatusId(Long.parseLong(parms[0].toString()));
				problemsCountByStatusObj.setStatus(parms[1].toString());
				problemsCountByStatusObj.setCount(Integer.parseInt(parms[2].toString()));
				problemsCountByStatusList.add(problemsCountByStatusObj);
				if(!IConstants.FIXED.equals(parms[1].toString()))
					totalProbsCount += Integer.parseInt(parms[2].toString());
			}
			
			locationwiseProblemStatusInfoVO.setLocationId(accessValue);
			locationwiseProblemStatusInfoVO.setProblemsCountByStatus(problemsCountByStatusList);
			locationwiseProblemStatusInfoVO.setTotalProblemsCount(totalProbsCount);
			
			return locationwiseProblemStatusInfoVO;
		}

	
}
