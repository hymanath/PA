/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemBackupDAO;
import com.itgrids.partyanalyst.dao.IProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.problem.HamletProblemUploadVO;
import com.itgrids.partyanalyst.excel.problem.HamletProblemVO;
import com.itgrids.partyanalyst.excel.problem.PollingBoothPartVO;
import com.itgrids.partyanalyst.excel.problem.ProblemDataExcelReader;
import com.itgrids.partyanalyst.excel.problem.ProblemDataUploadVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.ProblemBackup;
import com.itgrids.partyanalyst.model.ProblemAndProblemSource;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.model.ProblemLocation;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IProblemUploadService;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class ProblemUploadService implements IProblemUploadService {

	private IHamletDAO hamletDAO;
	private ITownshipDAO townshipDAO;
	private ITehsilDAO tehsilDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothDAO boothDAO;
	private IProblemLocationDAO problemLocationDAO;
	private IProblemExternalSourceDAO problemExternalSourceDAO;
	
	private TransactionTemplate transactionTemplate;
	
	
	private static final Logger log = Logger.getLogger(ProblemUploadService.class);
		
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
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

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
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

	public IProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}

	public void setProblemExternalSourceDAO(
			IProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IProblemUploadService#readExcelAndInsertData(java.io.File, java.lang.String, java.lang.String)
	 * This method reads the data from ExcelFile and saves it in valueObjects and then uploads the data to corresponding database schema.
	 * First parameter "filePath" is the ExcelFile that needs to be uploaded.
	 * Second parameter "year" is the data processed year.
	 * Third parameter "stateName" is the state to which this data belongs.
	 */
	public ResultStatus readExcelAndInsertData(final File filePath,final String year,String stateName,final String problemSourceName) {
		
		final ResultStatus resultStatus = new ResultStatus();
		
		if(log.isDebugEnabled())
			log.debug("Entered Into readExcelAndInsertData Method .......");
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		    
		    protected void doInTransactionWithoutResult(TransactionStatus status) {
		    	//ResultStatus resultStatus = new ResultStatus();
		    	try{
					ProblemDataExcelReader problemDataExcelReader = new ProblemDataExcelReader();
					List<ProblemDataUploadVO> problemsDataForUpload = problemDataExcelReader.readExcelData(filePath);
					String tehsilName = "";
					String townshipName = "";
					String constituencyName = "";
					String areaTypeDetails = "";
					String problemDate = "";
					Long districtId = null;
					List<Tehsil> tehsils = null;
					List<Township> townships = null;
					List<Constituency> constituencies = null;
					Tehsil tehsil = null;
					Township township = null;
					Constituency constituency = null;
					List<ProblemExternalSource> problemExternalSource = null;
					ProblemExternalSource problemExternalSrc = null;
		 			
					for(ProblemDataUploadVO problemUploadData:problemsDataForUpload){
						
						districtId = problemUploadData.getDistrictId();
						areaTypeDetails = problemUploadData.getAreaType();
						problemDate = problemUploadData.getProblemDate();
						
						if(log.isDebugEnabled())
							log.debug("Area Type ::" + areaTypeDetails);
						
						if(!problemUploadData.getConstituencyName().equalsIgnoreCase(constituencyName)){
							constituencyName = problemUploadData.getConstituencyName();
							constituencies = constituencyDAO.findByConstituencyNameAndDistrictId(constituencyName, districtId);
						}
						if(constituencies.size() != 1){
							log.debug("More Than One Or No Consituency Exists by name :" +constituencyName);
							continue;
						}
						constituency = constituencies.get(0);
						
						if(!problemUploadData.getMandalName().equalsIgnoreCase(tehsilName)){
							tehsilName = problemUploadData.getMandalName();
							tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
						}
						if(tehsils.size() != 1){
							log.debug("More Than One Or No Tehsil Exists with Mandal :"+tehsilName+" District Id:"+districtId);
							continue;
						}
						tehsil = tehsils.get(0);
						if(!problemUploadData.getTownshipName().equalsIgnoreCase(townshipName) && tehsil != null){
							townshipName = problemUploadData.getTownshipName();
							townships = townshipDAO.findByTownshipNameAndTehsilId(townshipName, tehsil.getTehsilId());
						}
						if(townships.size() != 1){
							log.debug("More Than One Or No Township Exists with Township :" +townshipName+ " And In Mandal Name :" +tehsilName+ "And With DistrictId :" +districtId);
							continue;
						}
						township = townships.get(0);
						if(problemSourceName != null){
							problemExternalSource = problemExternalSourceDAO.findByProblemExternalSourceName(problemSourceName);
						}
						if(problemExternalSource.size() == 1)
							problemExternalSrc = problemExternalSource.get(0);
						
						if(problemExternalSource.size() == 0){
							resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
							resultStatus.setResultPartial(true);
							break;
						}
									
						List<PollingBoothPartVO> pollingBoothPartVO = problemUploadData.getPollingBoothPartVO();
						List<HamletProblemUploadVO> hamletProblemUploadVO = null;
						String partNo ="";
						List<Booth> booths = null;
						for(PollingBoothPartVO partData:pollingBoothPartVO){
							if(!partData.getPartNo().equalsIgnoreCase(partNo)){
								partNo = partData.getPartNo();
								booths = boothDAO.findByTehsilAndPartNo(tehsilName, partNo);
							}
							if(booths.size() != 1){
								log.debug("More Than One Or No Polling Booth Exists With Same PartNo In Mandal :" +tehsilName+ " With PartNO :" + partNo);
								continue;
							}
							hamletProblemUploadVO = partData.getHamletProblemUploadVO();
							String hamletName = "";
							List<Hamlet> hamlets = null;
							Hamlet hamlet = null;
							List<HamletProblemVO> hamletProblems = null;
							for(HamletProblemUploadVO hamletProblemUpload:hamletProblemUploadVO){
								if(!hamletProblemUpload.getHamletName().equalsIgnoreCase(hamletName)){
									hamletName = hamletProblemUpload.getHamletName();
									hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
								}
								if(hamlets.size() != 1){
									log.debug("More Than One Or No Hamlet Exists With HamletName :" +hamletName+" In Township :" +townshipName+ " And Tehsil :" +tehsil.getTehsilName());
									continue;
								}
								hamlet = hamlets.get(0);
								hamletProblems = hamletProblemUpload.getHamletProblems();
								log.debug("Hamlet " +hamletName+"Details Found....");
								log.debug("Problems for " +hamletName+ " identified And Ready To Upload");
								
								checkAndInsertHamletProblems(hamlet,township,tehsil,constituency,hamletProblems,problemDate,problemExternalSrc,year);
							}
						}
						
					}
				}catch(IndexOutOfBoundsException ex){
					ex.printStackTrace();
					resultStatus.setExceptionEncountered(ex);
					resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					resultStatus.setResultPartial(true);
					status.setRollbackOnly();
				}
				catch(Exception ex){
					ex.printStackTrace();
					resultStatus.setExceptionEncountered(ex);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setResultPartial(true);
					status.setRollbackOnly();
				}
		    }
		});
		
				
		return resultStatus;
	}
	
	/**
	 * This method inserts the problems data into three entities.
	 * 1.Problem
	 * 2.ProblemSource.
	 * 3.ProblemLocation.
	 * @param hamlet
	 * @param township
	 * @param tehsil
	 * @param constituency
	 * @param hamletProblems
	 */
	public void checkAndInsertHamletProblems(Hamlet hamlet,Township township,Tehsil tehsil,Constituency constituency,List<HamletProblemVO> hamletProblems,String problemDate,ProblemExternalSource problemExternalSrc,String year) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Entered Into checkAndInsertHamletProblems method.......");
		ProblemBackup problem = null;
		ProblemAndProblemSource problemAndProblemSource = null;
		ProblemLocation problemLocation = null;
				
		java.util.Date now = new java.util.Date();
		String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String strDateNew = sdf.format(now) ;
        now = sdf.parse(strDateNew);
        
       	for(HamletProblemVO hamletProblem:hamletProblems){
			problem = new ProblemBackup();
			problem = insertProblemData(hamletProblem,now,year);
			
			problemAndProblemSource = new ProblemAndProblemSource();
			problemAndProblemSource.setProblem(problem);
			problemAndProblemSource.setProblemSource(new InformationSource(new Long(1)));
			problemAndProblemSource.setPostedBy("PartyAnalyst Survey Data Upload");
			problemAndProblemSource.setProblemExternalSource(problemExternalSrc);
					
			problemLocation = new ProblemLocation();
			problemLocation.setHamlet(hamlet);
			problemLocation.setTownship(township);
			problemLocation.setProblemAndProblemSource(problemAndProblemSource);
			problemLocationDAO.save(problemLocation);
			
			if(log.isDebugEnabled())
				log.debug("Problems saved successfully.......");
		}
		
	}

	/**
	 * This method sets data and returns problem model.
	 * @param hamletProblem contains data for a problem in a hamlet.
	 * @return Problem.
	 */
	public ProblemBackup insertProblemData(HamletProblemVO hamletProblem,Date problemIdentifiedDate,String year){
		ProblemBackup problem = null;
		if(log.isDebugEnabled())
			log.debug("Entered Into insertProblemData method.......");
		
		    problem = new ProblemBackup();
			problem.setDescription(hamletProblem.getProblemDesc());
			problem.setIdentifiedOn(problemIdentifiedDate);
			problem.setYear(year);
			
	return problem;
	}
	
	/**
	 * This method is for validating the excel data before uploading into database.
	 * First parameter "filePath" is the ExcelFile that needs to be uploaded.
	 * Second parameter "year" is the data processed year.
	 * Third parameter "stateName" is the state to which this data belongs.
	 */
    public UploadDataErrorMessageVO readExcelAndInsertDataValidation(File filePath,String year,String stateName,String problemSourceName){
		
		UploadDataErrorMessageVO uploadDataErrorMessageVO = new UploadDataErrorMessageVO();
		List<String> corrections = new ArrayList<String>();
		List<Exception> exceptions = new ArrayList<Exception>();
		if(log.isDebugEnabled())
			log.debug("Entered Into readExcelAndInsertDataValidation Method .......");
		corrections.add("Entered Into Method To Validate Data......");
		try{
			ProblemDataExcelReader problemDataExcelReader = new ProblemDataExcelReader();
			List<ProblemDataUploadVO> problemsDataForUpload = problemDataExcelReader.readExcelData(filePath);
			String tehsilName = "";
			String townshipName = "";
			String constituencyName = "";
			String areaTypeDetails = "";
			Long districtId = null;
			List<Tehsil> tehsils = null;
			List<Township> townships = null;
			List<Constituency> constituencies = null;
			Tehsil tehsil = null;
			Township township = null;
			Constituency constituency = null;
			List<ProblemExternalSource> problemExternalSource = null;
			ProblemExternalSource problemExternalSrc = null;
 			
			for(ProblemDataUploadVO problemUploadData:problemsDataForUpload){
				districtId = problemUploadData.getDistrictId();
				areaTypeDetails = problemUploadData.getAreaType();
				if(log.isDebugEnabled()){
					log.debug("Area Type ::" + areaTypeDetails);
				}
				if(!problemUploadData.getConstituencyName().equalsIgnoreCase(constituencyName)){
					constituencyName = problemUploadData.getConstituencyName();
					constituencies = constituencyDAO.findByConstituencyNameAndDistrictId(constituencyName, districtId);
				}
				if(constituencies.size() != 1){
					corrections.add("More Than One Or No Consituency Exists by name :" +constituencyName);
					continue;
				}
				constituency = constituencies.get(0);
				if(!problemUploadData.getMandalName().equalsIgnoreCase(tehsilName)){
					tehsilName = problemUploadData.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
				}
				if(tehsils.size() != 1){
					corrections.add("More Than One Or No Tehsil Exists with Mandal :"+tehsilName+" District Id:"+districtId);
					continue;
				}
				tehsil = tehsils.get(0);
				if(!problemUploadData.getTownshipName().equalsIgnoreCase(townshipName) && tehsil != null){
					townshipName = problemUploadData.getTownshipName();
					townships = townshipDAO.findByTownshipNameAndTehsilId(townshipName, tehsil.getTehsilId());
				}
				if(townships.size() != 1){
					corrections.add("More Than One Or No Township Exists with Township :" +townshipName+ " And In Mandal Name :" +tehsilName+ "And With DistrictId :" +districtId);
					continue;
				}
				township = townships.get(0);
				
				List<PollingBoothPartVO> pollingBoothPartVO = problemUploadData.getPollingBoothPartVO();
				List<HamletProblemUploadVO> hamletProblemUploadVO = null;
				String partNo ="";
				List<Booth> booths = null;
				if(problemSourceName != null){
					problemExternalSource = problemExternalSourceDAO.findByProblemExternalSourceName(problemSourceName);
				}
				if(problemExternalSource.size() == 1)
					problemExternalSrc = problemExternalSource.get(0);
				if(problemExternalSource.size() != 1)
					corrections.add("Zero Or More Than One Source For PartyAnalyst Exists in Database... Create Unique PartyAnalyst Record In ProblemExternalSource Table...");
				for(PollingBoothPartVO partData:pollingBoothPartVO){
					if(!partData.getPartNo().equalsIgnoreCase(partNo)){
						partNo = partData.getPartNo();
						booths = boothDAO.findByTehsilAndPartNo(tehsilName, partNo);
					}
					if(booths.size() != 1){
						corrections.add("More Than One Or No Polling Booth Exists With Same PartNo In Mandal :" +tehsilName+ " With PartNO :" + partNo);
						continue;
					}
					hamletProblemUploadVO = partData.getHamletProblemUploadVO();
					String hamletName = "";
					List<Hamlet> hamlets = null;
					for(HamletProblemUploadVO hamletProblemUpload:hamletProblemUploadVO){
						if(!hamletProblemUpload.getHamletName().equalsIgnoreCase(hamletName)){
							hamletName = hamletProblemUpload.getHamletName();
							hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
						}
						if(hamlets.size() != 1){
							corrections.add("More Than One Or No Hamlet Exists With HamletName :" +hamletName+" In Township :" +townshipName+ " And Tehsil :" +tehsil.getTehsilName());
							continue;
						}
						corrections.add("Hamlet " +hamletName+"Details Found....");
						corrections.add("Problems for " +hamletName+ " identified");
					}
				}
				
			}
		}catch(Exception ex){
			exceptions.add(ex);
		}
		corrections.add("Validation Completed......");
		uploadDataErrorMessageVO.setExceptions(exceptions);
		uploadDataErrorMessageVO.setCorrections(corrections);
		
	return uploadDataErrorMessageVO;
	}
}
