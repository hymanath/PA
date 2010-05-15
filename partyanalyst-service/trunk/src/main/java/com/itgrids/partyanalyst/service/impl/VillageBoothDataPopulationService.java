package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.HamletBoothElection;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.VillageBoothElection;
import com.itgrids.partyanalyst.service.IVillageBoothDataPopulationService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VillageBoothDataPopulationService implements IVillageBoothDataPopulationService{
	
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private File filePath;
	private Long electionId;
	private TransactionTemplate transactionTemplate;
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

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
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

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public VillageBoothElectionVO readExcelAndInsertData(File fPath, Long elecId){
		log.debug("The File Uploaded::"+fPath+" And Election Id ::"+ elecId);
		this.filePath = fPath;
		this.electionId = elecId;
		VillageBoothElectionVO villageBoothElectionVO = (VillageBoothElectionVO)transactionTemplate.execute
		(new TransactionCallback(){
			public Object doInTransaction(TransactionStatus status) {
				VillageBoothElectionVO villageBoothElectionVO = new VillageBoothElectionVO();				
				try{
					readFromExcel(villageBoothElectionVO);
				}catch(Exception e){
					e.printStackTrace();
					status.setRollbackOnly();
					villageBoothElectionVO.setExceptionEncountered(e);
				}		
				return villageBoothElectionVO;
			}					
		});
		this.filePath = null;
		this.electionId = null;
		return villageBoothElectionVO;
	}
	
	private void readFromExcel(VillageBoothElectionVO villageBoothElectionVO)throws Exception {
		log.debug("Ented Into Uploaded Part...........");
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
		Long constituencyElectionId = null;
		List constituencyInfo = null;
		List<Township> townships = null;
		List<Hamlet> hamlets = null;
		boolean isHamletExists;
		for(Sheet sheet:sheets){
			districtId = new Long(sheet.getCell(0,0).getContents().trim());
			for (int row = 2; row < sheet.getRows(); row++) {
				isHamletExists = true;
				if(!constituencyName.equalsIgnoreCase(sheet.getCell(0, row).getContents().trim())){
					constituencyName = sheet.getCell(0, row).getContents().trim();
					constituencyInfo = constituencyElectionDAO.findByDistrictElectionConstituency(electionId, districtId, constituencyName);
					if(constituencyInfo.size() != 1)
						throw new Exception(constituencyInfo.size() + "No. Of Constituencies Exists With Name \'"+constituencyName+"\' At Row No::"+(row+1));
					constituencyElectionId = (Long)((Object[])constituencyInfo.get(0))[0];
				}
				
				mandalName = sheet.getCell(1, row).getContents().trim();
				if(!revenueVillage.equalsIgnoreCase(sheet.getCell(2, row).getContents().trim())){
					revenueVillage = sheet.getCell(2, row).getContents().trim();
					townships = townshipDAO.findByTownshipNameTehsilNameDistrictId(districtId, mandalName, revenueVillage);
					if(townships.size() != 1)
						throw new Exception(townships.size() + "No. of Townships Exists With Name \'"+revenueVillage+"\' At Row No::"+(row+1));
					township = townships.get(0);
				}
				
				if(!hamletName.equalsIgnoreCase(sheet.getCell(3, row).getContents().trim())){
					hamletName = sheet.getCell(3, row).getContents().trim();
					if(hamletName.length() == 0)
						isHamletExists = false;		
					else{
						hamlets = hamletDAO.findByHamletNameAndTownshipId(township.getTownshipId(), hamletName);
						if(hamlets.size() != 1)
							throw new Exception(hamlets.size() + " No. Of Hamlets Exists With Name \'"+hamletName+"\' At Row No::"+(row+1));
						hamlet = hamlets.get(0);
					}					
				}
				partNos = sheet.getCell(4, row).getContents().trim();
				if(partNos.length() > 0){	
					insertDataIntoDB(constituencyElectionId, township, hamlet, partNos, isHamletExists);		
				}
			}
		}		
	}
	
	private void insertDataIntoDB(Long constituencyElectionId, Township township,
			Hamlet hamlet, String partNos, boolean isHamletExists)throws Exception {
		List<Long> boothIdsFromDB = boothConstituencyElectionDAO.findByConstituencyElectionAndPartNo(constituencyElectionId, partNos);
		if(boothIdsFromDB.size() == 0){
			throw new Exception(" No boothIds Exists for "+partNos +" and ConstituencyElectionId:"+ constituencyElectionId);
		}
		StringBuilder boothIds = new StringBuilder();
		for(Long boothId:boothIdsFromDB)
			boothIds.append(IConstants.COMMA).append(boothId);
		List<BoothConstituencyElection> boothConstituencyElections = boothConstituencyElectionDAO.findByBoothIds(boothIds.toString().substring(1));
		for(BoothConstituencyElection boothConstituencyElection:boothConstituencyElections){
			if(isHamletExists)
				checkAndInsertHamletBooth(hamlet, boothConstituencyElection);
			checkAndInsertTownshipBooth(township, boothConstituencyElection);			
		}
	}

	private void checkAndInsertTownshipBooth(Township township,
			BoothConstituencyElection boothConstituencyElection) {
		List list = villageBoothElectionDAO.findByTownshipAndBoothConstituencyElection(township.getTownshipId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(list.size() == 0)
			villageBoothElectionDAO.save(new VillageBoothElection(township, boothConstituencyElection));
		return;
	}

	private void checkAndInsertHamletBooth(Hamlet hamlet,
			BoothConstituencyElection boothConstituencyElection) {
		List list = hamletBoothElectionDAO.findByHamletAndBoothConstituencyElection(hamlet.getHamletId(), boothConstituencyElection.getBoothConstituencyElectionId());
		if(list.size() == 0)
			hamletBoothElectionDAO.save(new HamletBoothElection(hamlet, boothConstituencyElection));
		return;
	}

}
