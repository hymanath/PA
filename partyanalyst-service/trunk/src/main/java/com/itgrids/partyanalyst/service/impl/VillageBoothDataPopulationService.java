package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.HamletBoothElection;
import com.itgrids.partyanalyst.model.HamletBoothPublication;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.VillageBoothElection;
import com.itgrids.partyanalyst.service.IVillageBoothDataPopulationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VillageBoothDataPopulationService implements IVillageBoothDataPopulationService{
	
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothDAO boothDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IElectionDAO electionDAO;
	private ITehsilDAO tehsilDAO;
	private IHamletBoothPublicationDAO hamletBoothPublicationDAO;
	private TransactionTemplate transactionTemplate;
	private IPanchayatHamletDAO panchayatHamletDAO;

	
	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	private static final Logger log = Logger.getLogger(VillageBoothDataPopulationService.class);

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public VillageBoothElectionVO readExcelAndInsertData(final File fPath, final Long elecId, final Boolean isValidate){
		log.debug("The File Uploaded::"+fPath+" And Election Id ::"+ elecId);
		VillageBoothElectionVO villageBoothElectionVO = (VillageBoothElectionVO)transactionTemplate.execute
		(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status) {
				VillageBoothElectionVO villageBoothElectionVO = new VillageBoothElectionVO();				
				try{
					readFromExcel(fPath, elecId, villageBoothElectionVO, isValidate);
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
					villageBoothElectionVO.setExceptionEncountered(e);
				}		
				return villageBoothElectionVO;
			}					
		});
		return villageBoothElectionVO;
	}
	
	private void readFromExcel(File filePath, Long electionId, VillageBoothElectionVO villageBoothElectionVO, Boolean isValidate)throws Exception {
		log.debug("Entered Into Uploaded Part...........");
		Workbook workbook = Workbook.getWorkbook(filePath);	
		Sheet[] sheets = workbook.getSheets();
		String constituencyName = "";
		String mandalName = "";
		String revenueVillage = "";
		String partNos = "";
		String hamletName = "";
		Long districtId = null;
		Township township = null;
		Hamlet hamlet = null;
		Long constituencyId = null;
		List<Constituency> constituencyInfo = null;
		List<Township> townships = null;
		List<Hamlet> hamlets = null;
		List<String> villageErrors = new ArrayList<String>();
		List<String> hamletErrors = new ArrayList<String>();
		List<String> boothErrors = new ArrayList<String>();
		List<String> dataDuplicateErrors = new ArrayList<String>();
		Election election = electionDAO.get(electionId);
		Long electionYear = new Long(election.getElectionYear());
		boolean isHamletExists;
		
		for(Sheet sheet:sheets){
			districtId = new Long(sheet.getCell(0,0).getContents().trim());
			
			for (int row = 2; row < sheet.getRows(); row++) {
				partNos = sheet.getCell(4, row).getContents().trim();
				if(partNos.length() == 0)
					continue;
				
				if(!constituencyName.equalsIgnoreCase(sheet.getCell(0, row).getContents().trim())){
					constituencyName = sheet.getCell(0, row).getContents().trim();
					
					constituencyInfo = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId(constituencyName, districtId, 
							election.getElectionScope().getElectionScopeId());
					if(constituencyInfo.size() != 1)
						throw new Exception(constituencyInfo.size() + "No. Of Constituencies Exists With Name \'"+constituencyName+"\' At Row No::"+(row+1));
					constituencyId = constituencyInfo.get(0).getConstituencyId();
				}
				
				mandalName = sheet.getCell(1, row).getContents().trim();
				if(!revenueVillage.equalsIgnoreCase(sheet.getCell(2, row).getContents().trim())){
					villageBoothElectionDAO.flushAndclearSession();
					revenueVillage = sheet.getCell(2, row).getContents().trim();
					townships = townshipDAO.findByTownshipNameTehsilNameDistrictId(districtId, mandalName, revenueVillage);
					if(townships.size() != 1){
						villageErrors.add(townships.size() + "No. of Townships Exists With Name \'"+revenueVillage+"\' In Mandal:"+mandalName+" At Row No::"+(row+1));
						continue;
					}
					township = townships.get(0);
				}
				
				isHamletExists = false;
				if(!hamletName.equalsIgnoreCase(sheet.getCell(3, row).getContents().trim())){
					hamletName = sheet.getCell(3, row).getContents().trim();
					if(hamletName.length() > 0){
						isHamletExists = true;		
						hamlets = hamletDAO.findByHamletNameAndTownshipId(township.getTownshipId(), hamletName);
						if(hamlets.size() != 1){
							hamletErrors.add(hamlets.size() + " No. Of Hamlets Exists With Name \'"+hamletName+"\' In Revenue Village:"+revenueVillage+" And In Mandal:"+mandalName+" At Row No::"+(row+1));
							continue;
						}
						hamlet = hamlets.get(0);
					}					
				}
				
				
				insertDataIntoDB(constituencyId, electionYear, township, hamlet, partNos, 
						isHamletExists, boothErrors, dataDuplicateErrors, isValidate, constituencyName);	
				
			}
		}		
		
		villageBoothElectionVO.setVillageErrors(villageErrors);
		villageBoothElectionVO.setHamletErrors(hamletErrors);
		villageBoothElectionVO.setBoothErrors(boothErrors);
		villageBoothElectionVO.setDataDuplicateErrors(dataDuplicateErrors);
		
	}
	
	private void insertDataIntoDB(Long constituencyId, Long electionYear, Township township,
			Hamlet hamlet, String partNos, boolean isHamletExists, List<String> boothErrors,
			List<String> dataDuplicateErrors, Boolean isValidate, String constituencyName)throws Exception {
		List<Long> boothIdsFromDB = boothDAO.findByConstituencyElectionAndPartNo(constituencyId, electionYear, partNos);
		if(boothIdsFromDB.size() == 0){
			boothErrors.add(" Booth Data Not available For Part Nos: "+partNos +" in Constituency:"+ constituencyName+" for Year:"+electionYear);
			return;
		}
		StringBuilder boothIds = new StringBuilder();
		for(Long boothId:boothIdsFromDB)
			boothIds.append(IConstants.COMMA).append(boothId);
		List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByBoothIds(boothIds.toString().substring(1));
		for(BoothConstituencyElection boothConstituencyElection:boothConstituencyElections){
			if(isHamletExists && !isValidate)
				checkAndInsertHamletBooth(hamlet, boothConstituencyElection);
			checkAndInsertTownshipBooth(township, boothConstituencyElection, dataDuplicateErrors, isValidate);			
		}
	}

	private void checkAndInsertTownshipBooth(Township township,
			BoothConstituencyElection boothConstituencyElection, List<String> dataDuplicateErrors, Boolean isValidate) {
		List list = villageBoothElectionDAO.findByTownshipAndBoothConstituencyElection(township.getTownshipId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(list.size() == 0){
			List existingBoothInfo = villageBoothElectionDAO.findVillageAndBoothInfoForBoothConstituencyElection(boothConstituencyElection.getBoothConstituencyElectionId());
			if(existingBoothInfo.size() > 0){
				Object[] values = (Object[])existingBoothInfo.get(0);
				dataDuplicateErrors.add(values[3].toString()+" Assembly Part No: "+values[4].toString()+" is Already Mapped To "+
						values[0].toString()+" Revenue Village In "+values[1].toString()+" Mandal In "+values[2].toString()+" District");
				return;
			} 
			if(!isValidate)
				villageBoothElectionDAO.save(new VillageBoothElection(township, boothConstituencyElection));
		}
	}

	private void checkAndInsertHamletBooth(Hamlet hamlet,
			BoothConstituencyElection boothConstituencyElection) {
		List list = hamletBoothElectionDAO.findByHamletAndBoothConstituencyElection(hamlet.getHamletId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(list.size() == 0)
			hamletBoothElectionDAO.save(new HamletBoothElection(hamlet, boothConstituencyElection));
		return;
	}

	public VillageBoothElectionVO readExcelAndInsertDataForPublication(final File fPath, final Long publicationId, final Boolean isValidate,final Long electionType){
				log.debug("The File Uploaded::"+fPath+" And Election Id ::"+ publicationId);
				VillageBoothElectionVO villageBoothElectionVO = (VillageBoothElectionVO)transactionTemplate.execute
				(new TransactionCallback(){
					public Object doInTransaction(TransactionStatus status) {
						VillageBoothElectionVO villageBoothElectionVO = new VillageBoothElectionVO();				
						try{
							readFromExcelForPublication(fPath, publicationId, villageBoothElectionVO, isValidate,electionType);
						}catch(Exception e){
							e.printStackTrace();
							status.setRollbackOnly();
							villageBoothElectionVO.setExceptionEncountered(e);
						}		
						return villageBoothElectionVO;
					}					
				});
				return villageBoothElectionVO;
	}
	
	private void readFromExcelForPublication(File filePath, Long publicationId, VillageBoothElectionVO villageBoothElectionVO, Boolean isValidate,Long electionType)throws Exception {
		log.debug("Entered Into Uploaded Part...........");
		Workbook workbook = Workbook.getWorkbook(filePath);	
		Sheet[] sheets = workbook.getSheets();
		String constituencyName = "";
		String mandalName = "";
		String revenueVillage = "";
		String partNos = "";
		String hamletName = "";
		Long districtId = null;
		Long tehsilId = 0l;
		Long constituencyId = 0l;
		Township township = null;
		Hamlet hamlet = null;
		//Long constituencyId = null;
		//List<Constituency> constituencyInfo = null;
		List<Township> townships = null;
		List<Hamlet> hamlets = null;
		List<String> villageErrors = new ArrayList<String>();
		List<String> hamletErrors = new ArrayList<String>();
		List<String> boothErrors = new ArrayList<String>();
		List<String> dataDuplicateErrors = new ArrayList<String>();
		boolean isHamletExists = false;
		final Map<Long,Long> boothAndPanchayatsMap = new HashMap<Long,Long>();
		
		for(Sheet sheet:sheets){
			districtId = new Long(sheet.getCell(0,0).getContents().trim());
			
			for (int row = 2; row < sheet.getRows(); row++) {
				partNos = sheet.getCell(4, row).getContents().trim();
				if(partNos.length() == 0)
					continue;
				constituencyName = sheet.getCell(0, row).getContents().trim();
				/*if(!constituencyName.equalsIgnoreCase(sheet.getCell(0, row).getContents().trim())){
					constituencyName = sheet.getCell(0, row).getContents().trim();
					
					constituencyInfo = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId(constituencyName, districtId, 
							election.getElectionScope().getElectionScopeId());
					if(constituencyInfo.size() != 1)
						throw new Exception(constituencyInfo.size() + "No. Of Constituencies Exists With Name \'"+constituencyName+"\' At Row No::"+(row+1));
					constituencyId = constituencyInfo.get(0).getConstituencyId();
				}*/
				
				mandalName = sheet.getCell(1, row).getContents().trim();
				
				List tehsilIds = tehsilDAO.findTehsilIdByTehsilNameAndDistrict(mandalName.trim(),districtId);
					if(tehsilIds.size() != 1){
						villageErrors.add(tehsilIds.size() + "No. of Tehsils Exists With Name \'"+mandalName+"\' In district:"+districtId);
						continue;
					}
					tehsilId = (Long)tehsilIds.get(0);
				
				List<Constituency> constituenciesList = constituencyDAO.findByConstituencyNameAndDistrictIdElectionType(constituencyName,districtId,electionType);
				if(constituenciesList.size() != 1){
					boothErrors.add(constituenciesList.size() + "No. of Constituency Exists With Name \'"+constituencyName+"\' In district:"+districtId);
					continue;
				}
				constituencyId = constituenciesList.get(0).getConstituencyId();
				
				if(!revenueVillage.equalsIgnoreCase(sheet.getCell(2, row).getContents().trim())){
					villageBoothElectionDAO.flushAndclearSession();
					revenueVillage = sheet.getCell(2, row).getContents().trim();
					townships = townshipDAO.findByTownshipNameTehsilNameDistrictId(districtId, mandalName, revenueVillage);
					if(townships.size() != 1){
						villageErrors.add(townships.size() + "No. of Townships Exists With Name \'"+revenueVillage+"\' In Mandal:"+mandalName+" At Row No::"+(row+1));
						continue;
					}
					township = townships.get(0);
				}
				
				isHamletExists = false;
				if(!hamletName.equalsIgnoreCase(sheet.getCell(3, row).getContents().trim())){
					hamletName = sheet.getCell(3, row).getContents().trim();
					if(hamletName.length() > 0){
						isHamletExists = true;		
						hamlets = hamletDAO.findByHamletNameAndTownshipId(township.getTownshipId(), hamletName);
						if(hamlets.size() != 1){
							hamletErrors.add(hamlets.size() + " No. Of Hamlets Exists With Name \'"+hamletName+"\' In Revenue Village:"+revenueVillage+" And In Mandal:"+mandalName+" At Row No::"+(row+1));
							continue;
						}
						hamlet = hamlets.get(0);
					}					
				}
				if(isHamletExists){
				   List<Booth> boothsList = boothDAO.getBoothsByPublicationDateTehsilConstituenctPartNos(publicationId, tehsilId, constituencyId, partNos);
				  
				   if(boothsList.isEmpty()){
					 boothErrors.add(0 + "No. of Booths Exists With publicationId \'"+publicationId+"\' tehsilId:"+tehsilId+"\' constituencyId:"+constituencyId+"\' partNos:"+partNos);
				   }
				   HamletBoothPublication hamletBoothPublication = null;
				   if(!isValidate){
					 
				     for(Booth booth:boothsList){
				    	 
				    	List<Panchayat> panchayatsList =  panchayatHamletDAO.getPanchayatByHamletId(hamlet.getHamletId());
				    	
				    	if(panchayatsList != null && panchayatsList.size() > 0)
				    	{
				    		boothAndPanchayatsMap.put(booth.getBoothId(), panchayatsList.get(0).getPanchayatId());
				    		booth.setPanchayat(panchayatsList.get(0));
					    	boothDAO.save(booth);
				    	}
				    	
				    	hamletBoothPublication = new HamletBoothPublication(booth,hamlet);
					    hamletBoothPublicationDAO.save(hamletBoothPublication);
				     }
				     
				   }
			    }
				
			}
		}		
		
		if(!isValidate && boothAndPanchayatsMap.size() > 0)
		{
			villageBoothElectionDAO.flushAndclearSession();
			transactionTemplate.execute(new TransactionCallbackWithoutResult()
			{
	 			public void doInTransactionWithoutResult(TransactionStatus status) {
	    	 for(Map.Entry<Long,Long> entry : boothAndPanchayatsMap.entrySet())
	    		 boothDAO.updatePanchayatByBoothId(entry.getKey(),entry.getValue());
	 			}
	 		});
			villageBoothElectionDAO.flushAndclearSession();
	     }
		
		villageBoothElectionVO.setVillageErrors(villageErrors);
		villageBoothElectionVO.setHamletErrors(hamletErrors);
		villageBoothElectionVO.setBoothErrors(boothErrors);
		villageBoothElectionVO.setDataDuplicateErrors(dataDuplicateErrors);
		
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IHamletBoothPublicationDAO getHamletBoothPublicationDAO() {
		return hamletBoothPublicationDAO;
	}

	public void setHamletBoothPublicationDAO(
			IHamletBoothPublicationDAO hamletBoothPublicationDAO) {
		this.hamletBoothPublicationDAO = hamletBoothPublicationDAO;
	}
	
	
	
}
