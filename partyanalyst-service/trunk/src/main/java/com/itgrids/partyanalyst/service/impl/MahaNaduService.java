package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IMahaNaduService;

public class MahaNaduService implements IMahaNaduService{
 private IBoothDAO boothDAO;
 private static final Logger LOG = Logger.getLogger(MahaNaduService.class);
public IBoothDAO getBoothDAO() {
	return boothDAO;
}

public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
}
 
 public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> boothsList = boothDAO.getBoothOfAConstituencyByPublication(constituencyId, publicationID);
		 for(Object[] boothInfo:boothsList){
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)boothInfo[0]);
			 vo.setName(boothInfo[1].toString());
		 }
	 }catch(Exception e){
		 LOG.error("Exception rised in getBoothsInAConstituency ",e);
	 }
	 return returnList;
 }
}
