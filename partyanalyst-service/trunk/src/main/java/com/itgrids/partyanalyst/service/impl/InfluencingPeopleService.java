package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;

public class InfluencingPeopleService implements IInfluencingPeopleService{
	
	private IPartyDAO partyDAO;
	private IHamletDAO hamletDAO;
	private InfluencingPeopleDAO influencingPeopleDAO;
	private InfluencingPeoplePositionDAO influencingPeoplePositionDAO;
	private static final Logger log = Logger.getLogger(InfluencingPeopleService.class);
	private TransactionTemplate transactionTemplate = null;
	private InfluencingPeopleVO influencingPeopleVO;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public InfluencingPeoplePositionDAO getInfluencingPeoplePositionDAO() {
		return influencingPeoplePositionDAO;
	}

	public void setInfluencingPeoplePositionDAO(
			InfluencingPeoplePositionDAO influencingPeoplePositionDAO) {
		this.influencingPeoplePositionDAO = influencingPeoplePositionDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public InfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(InfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public Long saveInfluencePeople(InfluencingPeopleVO infPeopleVO){
		influencingPeopleVO = infPeopleVO;
		try{
			if(log.isDebugEnabled()){
				log.debug("Entered in to saveInfluencePeople() method..");
			}
			Long SUCCESS=1l;
						
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				InfluencingPeople influencingPeople = new InfluencingPeople();
				influencingPeople.setFirstName(influencingPeopleVO.getPersonName());
				influencingPeople.setLastName(influencingPeopleVO.getLastName());
				influencingPeople.setGender(influencingPeopleVO.getGender());
				influencingPeople.setPhoneNo(influencingPeopleVO.getContactNumber());
				influencingPeople.setEmail(influencingPeopleVO.getEmail());
				influencingPeople.setHamlet(hamletDAO.get(influencingPeopleVO.getHamletId()));
				influencingPeople.setParty(partyDAO.get(influencingPeopleVO.getPartyId()));
				influencingPeople.setCaste(influencingPeopleVO.getCast());
				influencingPeople.setOccupation(influencingPeopleVO.getOccupation());
				influencingPeople.setInfluencingScope(influencingPeopleVO.getInfluencingRange());
				if(influencingPeopleVO.getPosition() instanceof String){
					InfluencingPeoplePosition influencingPeoplePosition = new InfluencingPeoplePosition();						
					List<InfluencingPeoplePosition> influencePeop = influencingPeoplePositionDAO.getAll();
					int flag = 0,pos=0,counter=0;
					Long count = 0l;
					for(InfluencingPeoplePosition InfluencingPeople : influencePeop){		
						pos++;
						if(InfluencingPeople.getPosition().toString().equalsIgnoreCase(influencingPeopleVO.getPosition())){
							flag = 1;
						}else{					
						}		
						if(flag==1){
							counter++;
							if(counter==1){
								count = InfluencingPeople.getInfluencingPeoplePositionId();
							}
						}
					}			
					if(flag==1){						
						influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(count));
					}else{							
						influencingPeoplePosition.setPosition(influencingPeopleVO.getPosition().toUpperCase());
						influencingPeoplePosition = influencingPeoplePositionDAO.save(influencingPeoplePosition);
						influencingPeople.setInfluencingPeoplePosition(influencingPeoplePosition);	
					}
							
				}else{
					influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(new Long(influencingPeopleVO.getPosition())));
				}			
				influencingPeople = influencingPeopleDAO.save(influencingPeople);
			}
			});
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;
		}
	}
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions(){
		try{
			List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>();
			List<InfluencingPeoplePosition>  result = influencingPeoplePositionDAO.getAll();
			SelectOptionVO selectOptionVo = new SelectOptionVO();
			selectOptionVo.setId(0l);
			selectOptionVo.setName("Select Position");
			selectOptionVO.add(selectOptionVo);
			for(InfluencingPeoplePosition influencingPeoplePosition : result){
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(influencingPeoplePosition.getInfluencingPeoplePositionId());
				selectOption.setName(influencingPeoplePosition.getPosition());
				selectOptionVO.add(selectOption);
			}
			return selectOptionVO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SelectOptionVO> getInfluenceRange(){
		try{
			List<SelectOptionVO> selectOptionVOs = new ArrayList<SelectOptionVO>();			
			selectOptionVOs.add(new SelectOptionVO(1l,IConstants.STATE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(2l,IConstants.DISTRICT_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(3l,IConstants.CONSTITUENCY_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(4l,IConstants.TEHSIL_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(5l,IConstants.REVENUE_VILLAGE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(6l,IConstants.HAMLET_LEVEL));
			return selectOptionVOs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void hamletIdsQuery(){
	//	getHamletIdBasedOnDistrictNameMandalIdAndTownship(String districtName,String mandalName,String townshipName,String hamletName);
	}
	public void readAndSaveInfluencePeopleDataIntoDB(final File file){
		System.out.println("===============");
		System.out.println("Inside Service...........");
		System.out.println("File Path is ..."+file);
		System.out.println("===============");		
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				try {
					Workbook workbook = Workbook.getWorkbook(file);
						int totalSheets = workbook.getNumberOfSheets();
						for(int totSheet=0;totSheet<totalSheets;totSheet++){
							Sheet sheet = workbook.getSheet(totSheet);
							Cell[] cells = sheet.getRow(1);
							for(int k = 0;k<=cells.length;k++){
								System.out.println(cells);
							}
							List hamletId = hamletDAO.getHamletIdBasedOnDistrictNameMandalIdAndTownship(cells[2].getContents(),cells[1].getContents(),cells[0].getContents(),cells[4].getContents());
							int rowCount = sheet.getRows();
							for(int i=4;i<=rowCount;i++){
								InfluencingPeople influencingPeople = new InfluencingPeople();
								Cell[] cell = sheet.getRow(i);
								influencingPeople.setFirstName(cell[4].getContents());
								influencingPeople.setLastName(cells[5].getContents());
								influencingPeople.setGender(cells[11].getContents());
								influencingPeople.setPhoneNo(cells[10].getContents());
								influencingPeople.setEmail(cells[12].getContents());
								influencingPeople.setHamlet(hamletDAO.get(new Long(hamletId.get(0).toString())));
								influencingPeople.setParty(partyDAO.get(influencingPeopleVO.getPartyId()));
								influencingPeople.setCaste(influencingPeopleVO.getCast());
								influencingPeople.setOccupation(influencingPeopleVO.getOccupation());
								influencingPeople.setInfluencingScope(influencingPeopleVO.getInfluencingRange());
								System.out.print(cells[0].getContents()+"\t");
							}			
						}
				} catch (BiffException e) {					
					e.printStackTrace();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			});		
	}

	private Map<String, Long> influRangeAndValueMap;
	public InfluencingPeopleBeanVO saveInfluencePeopleInfo(
			InfluencingPeopleBeanVO influencingPeopleBeanVO1,
			Map<String, Long> influRangeAndValueMap1) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO1;
		this.influRangeAndValueMap = influRangeAndValueMap1;
		InfluencingPeopleBeanVO obj = (InfluencingPeopleBeanVO)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				InfluencingPeople influencingPeople = new InfluencingPeople();
				InfluencingPeopleBeanVO influencingPeopleVO = new InfluencingPeopleBeanVO();
				try{
					influencingPeople.setFirstName(influencingPeopleBeanVO.getFirstName());
					influencingPeople.setLastName(influencingPeopleBeanVO.getLastName());
					influencingPeople.setInfluencingScope(influencingPeopleBeanVO.getInfluencingRange());
					influencingPeople.setInfluencingScopeValue(influRangeAndValueMap.get(influencingPeopleBeanVO.getInfluencingRange()).toString());
					influencingPeople.setParty(partyDAO.get(new Long(influencingPeopleBeanVO.getParty())));
					influencingPeople.setCaste(influencingPeopleBeanVO.getCast());
					influencingPeople.setOccupation(influencingPeopleBeanVO.getOccupation());
					influencingPeople.setPhoneNo(influencingPeopleBeanVO.getMobile());
					influencingPeople.setGender(influencingPeopleBeanVO.getGender());
					influencingPeople.setEmail(influencingPeopleBeanVO.getEmail());
					influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(new Long(influencingPeopleBeanVO.getPosition())));
					influencingPeople.setHamlet(hamletDAO.get(new Long(influencingPeopleBeanVO.getHamlet())));
					
					influencingPeople = influencingPeopleDAO.save(influencingPeople);
					influencingPeopleVO.setFirstName(influencingPeople.getFirstName());
					influencingPeopleVO.setLastName(influencingPeople.getLastName());
				}catch(Exception ex){
					influencingPeopleVO.setExceptionEncountered(ex);
					ex.printStackTrace();
				}
					return influencingPeopleVO;		
			}
		});
		
		return obj;
	}		
}
