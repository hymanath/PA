package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.appfuse.dao.BaseDaoTestCase;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.utils.IConstants;
public class ProblemHistroryDAOHibernateTest extends BaseDaoTestCase {

	private IProblemHistoryDAO problemHistoryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	
	
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
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

	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}
	/*
	public void testSave(){
		ProblemHistory obj = new ProblemHistory();
		problemHistoryDAO.save(obj);
		setComplete();
	}
	
	public void testGetAll(){
		List<ProblemHistory> list = problemHistoryDAO.getAll();
		assertEquals(1, list.size());
	}
	
	public void testGetByUserId(){
		List<ProblemHistory> list = problemHistoryDAO.findProblemLocationsByUserId(new Long(3), new Long(1));
		assertEquals(1,list.size());
	}
	
	public void testByHamletLocationId(){			
		List result = problemHistoryDAO.findProblemsForALocationsByHamletId(836l);
		assertEquals(1, result.size());				
	}
	
	public void testByLocationId(){
		List result = problemHistoryDAO.findCompleteProblems(153l);
		assertEquals(1, result.size());	
	}
	
	public void testByStatus(){
		List result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId("836","new");
		assertEquals(1, result.size());
	}
	public void testByLocation(){
		List result = problemHistoryDAO.findProblemsForALocationsByHamletId(66l);
		assertEquals(1, result.size());
	}*/
	/*public void testGetProblemsCountInAllStatusByLocationForAUser(){
		List result = problemHistoryDAO.getProblemsCountInAllStatusByLocation("231,232");
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString())+"\t");
			System.out.print(parms[1].toString()+"\t");
			System.out.println(Long.parseLong(parms[2].toString()));
		}
	}*/
	
	/*public void testFindProblemsForALocationsByConstituencyId(){
		List list = problemHistoryDAO.findLatestProblemsByMandals("835,836,843,844", 2l);
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[2]+" "+values[7]);
	}*/
	
	/*public void testFindProblemsByDateAndLocation()throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
		System.out.println(sdf.parse("2010/15/02"));
		System.out.println(sdf.parse("2010/15/02"));
		List list = problemHistoryDAO.findProblemsByDateAndLocation("836,843,844", sdf.parse("1/12/2010"), sdf.parse("1/12/2010"));
		System.out.println(list.size());
	}*/
	
	/*public void testFindLatestProblemsGroupByDatePostedByMandalsAndStatus(){
		List list = problemHistoryDAO.findLatestProblemsGroupByDatePostedByMandalsAndStatus("835,836,843,844", "1,2,6");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]);
		}
	}*/
	
	/*public void testDate(){
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd/MM/yyyy");
		for(int i=0; i<10; i++){
			calendar.setTime(new Date());
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -i);
			System.out.println("i::"+i+"::"+sdfOutput.format((Date) calendar.getTime()));
		}	  
	  
	}*/
	
	/*public void testGetAssignedProblemsProgress(){
		List list = problemHistoryDAO.getAssignedProblemsProgress(3l);
		System.out.println(list.size());
	}
	*/
	
	/*public void testFindProblemHistoryByProblemLocationAndStatusId(){
		List list = problemHistoryDAO.findProblemHistoryByProblemLocation(2l);
		System.out.println(list.size());
	}*/

	
/*	@SuppressWarnings("unchecked")
	public void testGetProblemHistoryIdByHamletIds(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		list.add(new Long(2));
	
		List list1 = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(list,IConstants.HAMLET_LEVEL);
		System.out.println(list1.size());
	}*/
	
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
	
	public void test(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(19));
		NavigationVO result = getAllProblemsForGivenLocation(list,IConstants.DISTRICT_LEVEL);
		for(ProblemBeanVO data : result.getApprovalProblems()){
			System.out.println(data.getProblem());
			System.out.println(data.getDescription());
			System.out.println(data.getImpactLevel());
			System.out.println(data.getPostedDate());
			System.out.println(data.getName());
			System.out.println(data.getProblemId());
			System.out.println(data.getProblemHistoryId());
			System.out.println("==========================");
			System.out.println("==========================");
			System.out.println("==========================");
		}
		
	} 
	public NavigationVO getAllProblemsForGivenLocation(List<Long> locationIds,String locationType){
		NavigationVO result = null;	
		ResultStatus resultStatus = new ResultStatus();			
		try{
			result = new NavigationVO();
			
			if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAState(locationIds,locationType));
			}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInADistrict(locationIds,locationType));
			}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
				result = generateVoContainingAllApprovalProblems(getAllAcceptedProblemsInAConstituency(locationIds,locationType));
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
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			result.setResultStatus(resultStatus);
			return result;
		}	
	}

	private List<Object> getAllAcceptedProblemsInAWard(List<Long> locationIds, String locationType) {			
		List<Object> wardResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);	
		return wardResult;
	}

	private List<Object> getAllAcceptedProblemsInAHamlet(List<Long> locationIds, String locationType) {			
		List<Object> hamletResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);			
		return hamletResult;
	}

	private List<Object> getAllAcceptedProblemsInATehsil(List<Long> locationIds, String locationType) {			
		List<Object> teshilResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);			
		List<Long> listOfHamlets = hamletDAO.findHamletsByTehsilIds(locationIds);
		List<Object> hamletResult = getAllAcceptedProblemsInAHamlet(listOfHamlets,IConstants.HAMLET_LEVEL);
		teshilResult.addAll(hamletResult);
		return teshilResult;
	}

	private List<Object> getAllAcceptedProblemsInALocalElectionBody(List<Long> locationIds, String locationType) {
		List<Object> localElectionBodyResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);
		List<Long> listOfwards = constituencyDAO.getAllWardsByLocalElectionBodyIds(locationIds);
		List<Object> hamletResult = getAllAcceptedProblemsInAWard(listOfwards,IConstants.CENSUS_WARD_LEVEL);
		localElectionBodyResult.addAll(hamletResult);
		return localElectionBodyResult;
	}

	private List getAllAcceptedProblemsInAConstituency(List<Long> locationIds, String locationType) {
		List<Object> constituencyResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);
		List<Long> urbanConstituencies = null;
		List<Long> ruralConstituencies = null;
		List<Long> urban_rural_constituencies = null;
		List<Object> tehsilResult = null;
		List<Object> localElectionBodyResult = null;
		for(Long constituencyId : locationIds){
			Constituency constituency  = constituencyDAO.get(constituencyId);
			if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
				urbanConstituencies.add(constituency.getConstituencyId());
			}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
				ruralConstituencies.add(constituency.getConstituencyId());
			}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
				urban_rural_constituencies.add(constituency.getConstituencyId());
			}
		}
		if(urbanConstituencies!=null && urbanConstituencies.size()!=0){				
			List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(ruralConstituencies);
			localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.LOCALELECTIONBODY);
			constituencyResult.addAll(localElectionBodyResult);
		}
		if(ruralConstituencies!=null && ruralConstituencies.size()!=0){
			List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(urbanConstituencies);
			tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.TEHSIL_LEVEL);
			constituencyResult.addAll(tehsilResult);
		}
		if(urban_rural_constituencies!=null && urban_rural_constituencies.size()!=0){
			List<Long> tehsilIds = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(urbanConstituencies);
			tehsilResult = getAllAcceptedProblemsInATehsil(tehsilIds,IConstants.TEHSIL_LEVEL);
			constituencyResult.addAll(tehsilResult);
			List<Long> localElectionBodyIds = assemblyLocalElectionBodyDAO.getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(ruralConstituencies);
			localElectionBodyResult = getAllAcceptedProblemsInALocalElectionBody(localElectionBodyIds,IConstants.LOCALELECTIONBODY);
			constituencyResult.addAll(localElectionBodyResult);
		}
		return constituencyResult;
	}

	private List<Object> getAllAcceptedProblemsInADistrict(List<Long> locationIds, String locationType) {
		List<Object> districtResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);
		List<Long> listOfconstituencies = constituencyDAO.getAllConstituencysByDistrictIds(locationIds,IConstants.ASSEMBLY_ELECTION_TYPE);
		List<Object> consituencyResult = getAllAcceptedProblemsInAConstituency(listOfconstituencies,IConstants.DISTRICT_LEVEL);
		districtResult.addAll(consituencyResult);
		return districtResult;
	}

	private List<Object> getAllAcceptedProblemsInAState(List<Long> locationIds, String locationType) {			
		List<Object> stateResult = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(locationIds,locationType);
		List<Long> listOfDistricts = districtDAO.getAllDistrictByStateIds(locationIds);
		List<Object> districtResult = getAllAcceptedProblemsInADistrict(listOfDistricts,IConstants.DISTRICT_LEVEL);
		stateResult.addAll(districtResult);
		return stateResult;
	}
}
