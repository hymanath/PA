package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFieldVoterDataDAO;
import com.itgrids.partyanalyst.dao.hibernate.FieldVoterDataDAO;
import com.itgrids.partyanalyst.dto.FieldVoterDataVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.FieldVoterData;
import com.itgrids.partyanalyst.service.IFieldVoterDataService;

public class FieldVoterDataService implements IFieldVoterDataService {
	
	private static Logger log = Logger.getLogger(FieldVoterDataService.class);
	private IFieldVoterDataDAO fieldVoterDataDAO;

	
	public IFieldVoterDataDAO getFieldVoterDataDAO() {
		return fieldVoterDataDAO;
	}


	public void setFieldVoterDataDAO(IFieldVoterDataDAO fieldVoterDataDAO) {
		this.fieldVoterDataDAO = fieldVoterDataDAO;
	}


	public List<FieldVoterDataVO> getFieldVoterDataByBoothId(Long boothId)
	{
		log.debug("Entered into the getFieldVoterDataByBoothId service method");
		List<FieldVoterDataVO> resultList = new ArrayList<FieldVoterDataVO>();
		

		try
		{
		List<Object[]>	fieldVotersData = fieldVoterDataDAO.getFieldVotersDataByBoothId(boothId);
		
		
		for(Object[] voterDtls:fieldVotersData)
		{
			FieldVoterDataVO voterData = new FieldVoterDataVO();
			
			FieldVoterData fieldVoterData = (FieldVoterData)voterDtls[0];
			Booth booth =(Booth)voterDtls[1];
			
			voterData.setPanchayatName(booth.getPanchayat() != null ? booth.getPanchayat().getPanchayatName():"");
			voterData.setMandalName(booth.getTehsil() != null ? booth.getTehsil().getTehsilName():"");
			//voterData.setSeraialNo(fieldVoterData.getVoter().getS)
			voterData.setVoterIdcardNo(fieldVoterData.getVoter().getVoterIDCardNo());
			voterData.setAge(fieldVoterData.getVoter().getAge());
			
			voterData.setGender(fieldVoterData.getVoter().getGender());
			voterData.setHouseNo(fieldVoterData.getVoter().getHouseNo());
			voterData.setRelativeName(fieldVoterData.getVoter().getRelativeName());
			voterData.setRelation(fieldVoterData.getVoter().getRelationshipType());
			voterData.setMobileNo(fieldVoterData.getMobileNo());
			voterData.setCadre(fieldVoterData.getIsCade().equalsIgnoreCase("Y")?true:false);
			voterData.setInfluencePeople(fieldVoterData.getIsInfluencingPeople().equalsIgnoreCase("Y")?true:false);
			voterData.setTags(fieldVoterData.getTags());
			voterData.setSurveyTime(fieldVoterData.getSurveyTime().toString());
			voterData.setDataCollectorName(fieldVoterData.getUser().getFirstName() +" "+fieldVoterData.getUser().getLastName());
			voterData.setDataCollectorId(fieldVoterData.getUser().getUserId());
			voterData.setVerifierId(fieldVoterData.getVerifierId());
			voterData.setSurveyCaste(fieldVoterData.getCasteState() != null ? fieldVoterData.getCasteState().getCaste().getCasteName():"");
			voterData.setSurveyCasteId(fieldVoterData.getCasteState() != null ? fieldVoterData.getCasteState().getCasteStateId():0L);
			voterData.setSurveyHamletName(fieldVoterData.getHamlet() != null ? fieldVoterData.getHamlet().getHamletName():"");
			voterData.setSurveyLocalityId(fieldVoterData.getLocality() != null ? fieldVoterData.getLocality().getLocalityId():0L);
			voterData.setSurveyLocalityName(fieldVoterData.getLocality() != null ? fieldVoterData.getLocality().getName():"");
			voterData.setSurveyWardId(fieldVoterData.getWard() != null ?fieldVoterData.getWard().getWardId():0L);
			voterData.setSurveyWardName(fieldVoterData.getWard() != null ? fieldVoterData.getWard().getWardName():"");
			voterData.setVerifierCasteId(fieldVoterData.getVerifiedCasteStateId());
			voterData.setVerifierHamletId(fieldVoterData.getVerifiedHamletId());
			//voterData.setVerifierWardId(fieldVoterData.getVer)
			voterData.setVerifierId(fieldVoterData.getVerifierId());
			voterData.setVoterName(fieldVoterData.getVoter().getName());
			
			resultList.add(voterData);

		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception raised in  getFieldVoterDataByBoothId service method");
		}
		
		return resultList;
	}
	

}
