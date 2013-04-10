
package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.excel.booth.VoterDataExcelReader;
import com.itgrids.partyanalyst.excel.booth.VoterDataUploadVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.BoothConstituencyElectionVoter;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IVoterDataUploadService;

public class VoterDataUploadService implements IVoterDataUploadService{

	private IHamletDAO hamletDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IVoterDAO voterDAO;
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private ITehsilDAO tehsilDAO;
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	private static final Logger log = Logger.getLogger(VoterDataUploadService.class);
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	} 
	
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	} 
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}
	
	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ResultStatus readExcelAndInsertData(File filePath,
			String electionYear, Long stateId, Long electionTypeId , String publicatonDateId) {
		ResultStatus resultStatus = new ResultStatus();
		try{
			VoterDataExcelReader excelReader = new VoterDataExcelReader();
			List<VoterDataUploadVO> votersInfo = excelReader.readExcel(filePath);
			String constituencyName = "";
			String mandalName = "";
			String partNo = "";
			Long districtId = null;
			List<Tehsil> tehsils = null;
			List<BoothConstituencyElection> boothConstituencyElections = null;
			BoothConstituencyElection boothConstituencyElection = null;
			Booth booth = null;
			Tehsil tehsil = null;
			for(VoterDataUploadVO voterDataUploadVO : votersInfo){			
				constituencyName = voterDataUploadVO.getConstituencyName();
				districtId = voterDataUploadVO.getDistrictId();
				partNo = voterDataUploadVO.getPartNo();
				if(!voterDataUploadVO.getMandalName().equalsIgnoreCase(mandalName)){
					mandalName = voterDataUploadVO.getMandalName();
					tehsils = tehsilDAO.findByTehsilNameAndDistrict(mandalName, districtId);
				}				
				if(tehsils.size() != 1){
					if(log.isDebugEnabled()){
						log.debug("More than One Or No Tehsil Exists with Mandal:"+mandalName+" District Id:"+districtId);
					}
					continue;
				}				
				tehsil = tehsils.get(0);
				
			 Long publicationDateId = new Long(publicatonDateId);

			 
				if (electionYear != null && !electionYear.equalsIgnoreCase("0")
						&& electionTypeId != null && electionTypeId.longValue() != 0 
						&& publicationDateId != null && publicationDateId.longValue() != 0) {
					
					checkForElectionAndPublicationExistence(tehsil,
							voterDataUploadVO, stateId, districtId,
							constituencyName, publicationDateId, partNo,
							electionYear, electionTypeId);
					
					
					
				}				
			 else if(publicationDateId!= null && publicationDateId.longValue() != 0){
				
					List<Booth> booths = boothDAO.findByPublicationDateConstituencyAndPartNo(stateId,
							districtId, constituencyName,publicationDateId, partNo );	
					
					
					if(booths != null && booths.size() < 1){
						
						if(log.isDebugEnabled()){
							log.debug(" No Publication Exists with publishing date:"+publicationDateId);
						}
						continue;
					}
					
					setVoterDetailsAndInsertIntoBoothPublicationVoter(tehsil,
							booths.get(0), null,
							voterDataUploadVO.getVoterVOs());
				
			 }				
              else if (electionYear != null
						&& !electionYear.equalsIgnoreCase("0")
						&& electionTypeId != null
						&& electionTypeId.longValue() != 0) {
					
					boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
					if(boothConstituencyElections.size() != 1){
						if(log.isDebugEnabled()){
							log.debug("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						}
						continue;
					}
					boothConstituencyElection = boothConstituencyElections.get(0);
					List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
					if(boothConstituencyElectionVoters.size() > 0){
						if(log.isDebugEnabled()){
							log.debug("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
						}
						continue;
					}	
					
					setVoterDetailsAndInsertIntoBoothPublicationVoter(tehsil, null, boothConstituencyElection, voterDataUploadVO.getVoterVOs());
					
				}
			}
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultStatus.setResultPartial(true);
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
		}
		return resultStatus;
	}
	
	
	/**
	 * This method will check the existence of given publication or election
	 * existence of election year and existence of voter data
	 * 
	 * @param tehsil
	 * @param voterDataUploadVO
	 * @param stateId
	 * @param districtId
	 * @param constituencyName
	 * @param publicationDateId
	 * @param partNo
	 * @param electionYear
	 * @param electionTypeId
	 */
	public void checkForElectionAndPublicationExistence(Tehsil tehsil,
			VoterDataUploadVO voterDataUploadVO, Long stateId, Long districtId,
			String constituencyName, Long publicationDateId, String partNo,
			String electionYear, Long electionTypeId) {	
		
		if(log.isDebugEnabled()){
			log.debug("Executing checkForElectionAndPublicationExistence service method");			
		}
		
		try{

		List<BoothConstituencyElection> boothConstituencyElections = null;
		BoothConstituencyElection boothConstituencyElection = null;
		Booth booth = null;
		 
		 List<Booth> booths = boothDAO.findByPublicationDateConstituencyAndPartNo(stateId,
					districtId, constituencyName,publicationDateId, partNo);
		 
		 if(booths != null && booths.size() < 1){
			 
			 booth = null;
				if(log.isDebugEnabled()){
					log.debug(" No Publication Exists with publishing date:"+publicationDateId);
				}
			}else						
				booth = booths.get(0);
		 
		 
		 boothConstituencyElections = boothConstituencyElectionDAO.findByElectionElectionTypeConstituencyAndPartNo(stateId, districtId, constituencyName, electionTypeId, electionYear, partNo);
			if(boothConstituencyElections.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("More than One Or No BoothConstituencyElections Exists For Constiteuncy:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
				}
			}
			
			if(boothConstituencyElections.size() == 1 )
			  boothConstituencyElection = boothConstituencyElections.get(0);
			else						
				boothConstituencyElection = null;
			
			List<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = boothConstituencyElectionVoterDAO.findByBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
			if(boothConstituencyElectionVoters.size() > 0){
				boothConstituencyElection = null;
				if(log.isDebugEnabled()){
					log.debug("Voter Data Already Exists For Constituency:"+constituencyName+" Part No:"+partNo+" and Election Year:"+electionYear);
				}
			}
			
			setVoterDetailsAndInsertIntoBoothPublicationVoter(tehsil,
					booth, boothConstituencyElection,
					voterDataUploadVO.getVoterVOs());	 
			
		}catch(Exception e){
			
			e.printStackTrace();
			log.error("Exception raised in  checkForElectionAndPublicationExistence service method");			

		}
	}
	
	
	/**
	 * This method will set the voter details and check for the hamlet existence
	 * @param tehsil
	 * @param booth
	 * @param boothConstituencyElection
	 * @param voterVOs
	 */
	
	public void setVoterDetailsAndInsertIntoBoothPublicationVoter(
			Tehsil tehsil, Booth booth,
			BoothConstituencyElection boothConstituencyElection,
			List<VoterVO> voterVOs) {		
		
         try{
        	 
	        	String townshipName = "";
	     		String hamletName = "";
	     		String voterFirstName = "";
	     		String voterlastName = "";
	     		String houseNo = "";
	     		String relationshipType = "";
	     		String relativeFirstName = "";
	     		String relativeLastName = "";
	     		String cast = "";
	     		String castCatagery = "";
	     		String castSubcatagery = "";
	     		String gender = "";
	     		String localArea = "";
	     		Long age = null;
	     		String voterIDCardNo = "";
	     		List<Hamlet> hamlets = null;
	     		Hamlet hamlet = null;
	     		Voter voter = null;
	     		
	     		
	     		for(VoterVO voterVO:voterVOs){
	    			
	    			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
	    				townshipName = voterVO.getTownShip();
	    				hamletName = voterVO.getHamlet();
	    				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
	    			}
	    			
	    			if(hamlets.size() != 1){
	    				if(log.isDebugEnabled()){
	    					log.debug("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
	    				}
	    				continue;
	    			}
	    			
	    			hamlet = hamlets.get(0);
	    			voterFirstName = voterVO.getFirstName();
	    			voterlastName = voterVO.getVoterLastName();
	    			houseNo = voterVO.getHouseNo();
	    			relationshipType = voterVO.getRelationshipType();
	    			relativeFirstName = voterVO.getRelativeFirstName();
	    			relativeLastName = voterVO.getRelativeLastName();
	    			cast = voterVO.getCast();
	    			localArea = voterVO.getLocalArea();
	    			castCatagery = voterVO.getCastCatagery();
	    			castSubcatagery = voterVO.getCastSubCatagery();
	    			gender = voterVO.getGender();
	    			age = voterVO.getAge();
	    			voterIDCardNo = voterVO.getVoterIDCardNo();
	    			voter = new Voter(houseNo, relationshipType,gender, 
	    					age, voterIDCardNo, null);
	    			
	    			
	    			
				if (booth != null && boothConstituencyElection != null)
					checkAndInsertIntoBoothPublicationVoterAndBoothConstituencyVoter(
							boothConstituencyElection, booth,
							checkAndInsertVoterData(voter));

				else if (booth == null && boothConstituencyElection != null)
					checkAndInsertBoothConstituencyElectionVoter(
							boothConstituencyElection,
							checkAndInsertVoterData(voter));

				else if (boothConstituencyElection == null && booth != null)
					checkAndInsertInBoothPublicationVoter(booth,
							checkAndInsertVoterData(voter));
	    		
	    		}
	     		
        	 
         }catch(Exception e){
        	 
         }
		
		}

	
	private Voter checkAndInsertVoterData(Voter voter){
		/*List<Voter> voters = voterDAO.findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(voter.getFirstName(), 
				voter.getLastName(),voter.getRelativeFirstName(), voter.getRelativeLastName(), voter.getVoterIDCardNo());
		if(voters.size() > 0 ){
			if(log.isDebugEnabled()){
				log.debug("Voter Already Exists Name:"+voter.getFirstName()+" "+voter.getLastName()+" and Voter ID:"+voter.getVoterIDCardNo());
			}
			return voters.get(0);
		}		
		  voter = voterDAO.save(voter);*/
	
		return voter;
	}
	
	private void checkAndInsertBoothConstituencyElectionVoter(BoothConstituencyElection boothConstituencyElection, Voter voter){
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElectionAndVoter(boothConstituencyElection.getBoothConstituencyElectionId(), voter.getVoterId());
		if(list.size() > 0 ){
			if(log.isDebugEnabled()){
				log.debug("BoothConstituencyElectionVoter Already Exists with BoothConstituencyElection ID:"+boothConstituencyElection.getBoothConstituencyElectionId()+" and Voter Id:"+voter.getVoterId());
			}
			return;
		}
		BoothConstituencyElectionVoter boothConstituencyElectionVoter = new BoothConstituencyElectionVoter(boothConstituencyElection, voter);
		boothConstituencyElectionVoterDAO.save(boothConstituencyElectionVoter);
	}
	
	private void checkAndInsertInBoothPublicationVoter(Booth booth ,Voter voter){		
		
		BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter(booth, voter);
		
		List<BoothPublicationVoter> boothPublicationVoters = boothPublicationVoterDAO
				.checkForVoterExistenceInBoothPublicationVoter(
						booth.getBoothId(), voter.getVoterId());
		
		if(boothPublicationVoters.size() > 0){
			
			if(log.isDebugEnabled()){
				log.debug("BoothPublicationVoter Already Exists with Booth Id:"+booth.getBoothId()+"And voter ID:"+voter.getVoterId());
			}
			return;
		}
		
		boothPublicationVoterDAO.save(boothPublicationVoter);		
		
	}
	
	
	/**
	 * This method will insert the records into boothConstituencyElectionVoter and boothPublicationVoter for a voter
	 * @param boothConstituencyElection
	 * @param booth
	 * @param voter
	 */
	public void checkAndInsertIntoBoothPublicationVoterAndBoothConstituencyVoter(
			BoothConstituencyElection boothConstituencyElection, Booth booth,
			Voter voter) {	
		
		if(log.isDebugEnabled())
			log.debug("Excecuting checkAndInsertIntoBoothPublicationVoterAndBoothConstituencyVoter service method");
		
		try{
		
		
		List<BoothConstituencyElectionVoter> list = boothConstituencyElectionVoterDAO.findByBoothConstituencyElectionAndVoter(boothConstituencyElection.getBoothConstituencyElectionId(), voter.getVoterId());
		if(list.size() > 0 ){
			if(log.isDebugEnabled()){
				log.debug("BoothConstituencyElectionVoter Already Exists with BoothConstituencyElection ID:"+boothConstituencyElection.getBoothConstituencyElectionId()+" and Voter Id:"+voter.getVoterId());
			}
			return;
		}
			BoothConstituencyElectionVoter boothConstituencyElectionVoter = new BoothConstituencyElectionVoter(
					boothConstituencyElection, voter);
			BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter(
					booth, voter);
		
		boothConstituencyElectionVoterDAO.save(boothConstituencyElectionVoter);
		boothPublicationVoterDAO.save(boothPublicationVoter);
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			log.debug("Exception raised in checkAndInsertIntoBoothPublicationVoterAndBoothConstituencyVoter service method");

			
		}
		
	}
	/*public void insertVoterAndBoothPublicationVoter(Tehsil tehsil,
			Booth booth,
			List<VoterVO> voterVOs) {
		
		String townshipName = "";
		String hamletName = "";
		String voterFirstName = "";
		String voterlastName = "";
		String houseNo = "";
		String relationshipType = "";
		String relativeFirstName = "";
		String relativeLastName = "";
		String cast = "";
		String castCatagery = "";
		String castSubcatagery = "";
		String gender = "";
		String localArea = "";
		Long age = null;
		String voterIDCardNo = "";
		List<Hamlet> hamlets = null;
		Hamlet hamlet = null;
		Voter voter = null;
		
		for(VoterVO voterVO:voterVOs){
			
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			
			if(hamlets.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				}
				continue;
			}
			
			hamlet = hamlets.get(0);
			voterFirstName = voterVO.getFirstName();
			voterlastName = voterVO.getVoterLastName();
			houseNo = voterVO.getHouseNo();
			relationshipType = voterVO.getRelationshipType();
			relativeFirstName = voterVO.getRelativeFirstName();
			relativeLastName = voterVO.getRelativeLastName();
			cast = voterVO.getCast();
			localArea = voterVO.getLocalArea();
			castCatagery = voterVO.getCastCatagery();
			castSubcatagery = voterVO.getCastSubCatagery();
			gender = voterVO.getGender();
			age = voterVO.getAge();
			voterIDCardNo = voterVO.getVoterIDCardNo();
			voter = new Voter(houseNo, voterFirstName, voterlastName, relationshipType,
					relativeFirstName, relativeLastName, cast, castCatagery, castSubcatagery, gender, 
					age, voterIDCardNo, localArea, null, hamlet, null);
			
			
			
			checkAndInsertInBoothPublicationVoter(booth , checkAndInsertVoterData(voter));
			
		
		}
		
	}*/
	 
	/*private void insertIntoBoothPublicationVoterAndBoothConstituencyVoter(
			Tehsil tehsil, Booth booth,
			BoothConstituencyElection boothConstituencyElection,
			List<VoterVO> voterVOs) {
		
		String townshipName = "";
		String hamletName = "";
		String voterFirstName = "";
		String voterlastName = "";
		String houseNo = "";
		String relationshipType = "";
		String relativeFirstName = "";
		String relativeLastName = "";
		String cast = "";
		String castCatagery = "";
		String castSubcatagery = "";
		String gender = "";
		String localArea = "";
		Long age = null;
		String voterIDCardNo = "";
		List<Hamlet> hamlets = null;
		Hamlet hamlet = null;
		Voter voter = null;
		for(VoterVO voterVO:voterVOs){
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				}
				continue;
			}
			hamlet = hamlets.get(0);
			voterFirstName = voterVO.getFirstName();
			voterlastName = voterVO.getVoterLastName();
			houseNo = voterVO.getHouseNo();
			relationshipType = voterVO.getRelationshipType();
			relativeFirstName = voterVO.getRelativeFirstName();
			relativeLastName = voterVO.getRelativeLastName();
			cast = voterVO.getCast();
			localArea = voterVO.getLocalArea();
			castCatagery = voterVO.getCastCatagery();
			castSubcatagery = voterVO.getCastSubCatagery();
			gender = voterVO.getGender();
			age = voterVO.getAge();
			voterIDCardNo = voterVO.getVoterIDCardNo();
			voter = new Voter(houseNo, voterFirstName, voterlastName, relationshipType,
					relativeFirstName, relativeLastName, cast, castCatagery, castSubcatagery, gender, 
					age, voterIDCardNo, localArea, null, hamlet, null);
			
			checkAndInsertIntoBoothPublicationVoterAndBoothConstituencyVoter(boothConstituencyElection,booth,checkAndInsertVoterData(voter));

		}
		
		
	}*/
	
	
	
	/*public void insertVoterAndBoothConstituencyElectionVoter(Tehsil tehsil, BoothConstituencyElection boothConstituencyElection, List<VoterVO> voterVOs)throws Exception{
		String townshipName = "";
		String hamletName = "";
		String voterFirstName = "";
		String voterlastName = "";
		String houseNo = "";
		String relationshipType = "";
		String relativeFirstName = "";
		String relativeLastName = "";
		String cast = "";
		String castCatagery = "";
		String castSubcatagery = "";
		String gender = "";
		String localArea = "";
		Long age = null;
		String voterIDCardNo = "";
		List<Hamlet> hamlets = null;
		Hamlet hamlet = null;
		Voter voter = null;
		for(VoterVO voterVO:voterVOs){
			if(!voterVO.getTownShip().equalsIgnoreCase(townshipName)||!voterVO.getHamlet().equalsIgnoreCase(hamletName)){
				townshipName = voterVO.getTownShip();
				hamletName = voterVO.getHamlet();
				hamlets = hamletDAO.findByTehsilTownshipAndHamletName(tehsil.getTehsilId(), townshipName, hamletName);
			}
			if(hamlets.size() != 1){
				if(log.isDebugEnabled()){
					log.debug("More than One Or No Hamlets Exists For with tehsilId:"+tehsil.getTehsilId()+" Township Name:"+townshipName+" and Hamlet Name:"+hamletName);
				}
				continue;
			}
			hamlet = hamlets.get(0);
			voterFirstName = voterVO.getFirstName();
			voterlastName = voterVO.getVoterLastName();
			houseNo = voterVO.getHouseNo();
			relationshipType = voterVO.getRelationshipType();
			relativeFirstName = voterVO.getRelativeFirstName();
			relativeLastName = voterVO.getRelativeLastName();
			cast = voterVO.getCast();
			localArea = voterVO.getLocalArea();
			castCatagery = voterVO.getCastCatagery();
			castSubcatagery = voterVO.getCastSubCatagery();
			gender = voterVO.getGender();
			age = voterVO.getAge();
			voterIDCardNo = voterVO.getVoterIDCardNo();
			voter = new Voter(houseNo, voterFirstName, voterlastName, relationshipType,
					relativeFirstName, relativeLastName, cast, castCatagery, castSubcatagery, gender, 
					age, voterIDCardNo, localArea, null, hamlet, null);
			
			//Saving Voter Data and BoothConstituencyElectionVoter
			checkAndInsertBoothConstituencyElectionVoter(boothConstituencyElection, checkAndInsertVoterData(voter));
		}
		
	}*/
	
	

}
